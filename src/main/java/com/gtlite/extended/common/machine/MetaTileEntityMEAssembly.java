package com.gtlite.extended.common.machine;

import appeng.api.AEApi;
import appeng.api.networking.IGrid;
import appeng.api.storage.IMEMonitor;
import appeng.api.storage.channels.IFluidStorageChannel;
import appeng.api.storage.channels.IItemStorageChannel;
import appeng.api.storage.data.IAEFluidStack;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IItemList;
import appeng.fluids.util.AEFluidStack;
import appeng.fluids.util.FluidList;
import appeng.me.GridAccessException;
import appeng.me.helpers.AENetworkProxy;
import appeng.util.item.AEItemStack;
import appeng.util.item.ItemList;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.IDistinctBusController;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.AbilityInstances;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.multi.multiblockpart.appeng.MetaTileEntityAEHostablePart;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ME 总成（ME Assembly）通用基类：ME 输出总成 / ME 库存输入总成共用这一套
 * “过滤标记 + 统一网格 GUI + AE 网络访问 + 总线隔离”逻辑。
 *
 * <h3>过滤标记（Ghost Slot）</h3>
 * 每个总成有 {@link #FILTER_SLOTS} 个标记槽，槽位是“幽灵槽”：
 * <ul>
 *     <li>左键：把光标上的物品设为物品标记（不消耗物品，数量固定记 1）；</li>
 *     <li>右键：把光标上流体容器里的流体设为流体标记（不消耗容器）；</li>
 *     <li>空光标左键/右键：清除该槽的物品/流体标记；</li>
 *     <li>未标记的槽位直接跳过、不参与过滤。</li>
 * </ul>
 * 标记槽的 GUI 由输入总成自己绘制；输出总成的 GUI 只显示内部缓存。两个总成不再共用 UI。
 *
 * <h3>总线隔离</h3>
 * 读取所在多方块控制器（{@link MultiblockControllerBase}）的
 * {@link IDistinctBusController#isDistinct()} 开关：
 * <ul>
 *     <li>开启隔离：本总成的标记只作用于它自己；</li>
 *     <li>关闭隔离（全局）：本总成除自己的标记外，还读取结构内所有同类型总成的标记
 *         （{@link #collectActiveItemFilters()}），即“标记作用于整个多方块结构内的所有同类型总线”。</li>
 * </ul>
 *
 * <h3>AE 访问</h3>
 * 复用 GT 的 {@link MetaTileEntityAEHostablePart}：连接面、频道、在线状态、扳手额外连接
 * 与 GT 自带 ME 总线/仓完全一致；网络监视器通过 {@code proxy.getStorage().getInventory(...)} 获取。
 */
public abstract class MetaTileEntityMEAssembly extends MetaTileEntityAEHostablePart<IAEItemStack>
        implements IMultiblockAbilityPart<IItemHandlerModifiable> {

    /** 标记槽总数（8 列 × 6 行）。 */
    public static final int FILTER_SLOTS = 48;
    /** 网格列数。 */
    protected static final int GRID_COLUMNS = 8;
    /** 一屏显示的行数（其余靠滚动条）。 */
    protected static final int VISIBLE_ROWS = 4;

    /** 过滤标记的网络同步包 id（只在本类内部使用，父类忽略未知 id）。 */
    private static final int FILTER_SYNC_DATA = 8157;
    /** 网络存量同步包 id。 */
    private static final int STOCK_SYNC_DATA = 8158;
    /** 网络存量同步间隔（tick）。 */
    private static final int STOCK_SYNC_INTERVAL = 20;

    private static final String NBT_WORKING = "WorkingEnabled";
    private static final String NBT_ITEM_FILTERS = "ItemFilters";
    private static final String NBT_FLUID_FILTERS = "FluidFilters";
    private static final String NBT_SLOT = "Slot";
    private static final String NBT_ITEM = "Item";
    private static final String NBT_FLUID = "Fluid";

    private final ItemStack[] itemFilters = new ItemStack[FILTER_SLOTS];
    private final FluidStack[] fluidFilters = new FluidStack[FILTER_SLOTS];

    private boolean workingEnabled = true;

    /** 客户端显示的 AE 网络存量（每 20 tick 由服务端同步一次；0 表示离线或没有）。 */
    private final long[] itemStock = new long[FILTER_SLOTS];
    private final long[] fluidStock = new long[FILTER_SLOTS];

    protected MetaTileEntityMEAssembly(ResourceLocation metaTileEntityId, boolean isExportHatch) {
        // tier 4（EV）与 GT 的 ME 总线一致；isExportHatch 由子类给出（输出 true / 输入 false）
        super(metaTileEntityId, 4, isExportHatch, IItemStorageChannel.class);
        Arrays.fill(this.itemFilters, ItemStack.EMPTY);
    }

    // ==================== 子类必须实现的能力定义 ====================

    /** 物品侧多方块能力：输出总成 = EXPORT_ITEMS，输入总成 = IMPORT_ITEMS。 */
    protected abstract MultiblockAbility<IItemHandlerModifiable> itemAbility();

    /** 流体侧多方块能力：输出总成 = EXPORT_FLUIDS，输入总成 = IMPORT_FLUIDS。 */
    protected abstract MultiblockAbility<IFluidTank> fluidAbility();

    /** 物品侧注册给多方块的 handler。 */
    protected abstract IItemHandlerModifiable itemAbilityHandler();

    /** 流体侧注册给多方块的 handler（输入总成会注册多个仓）。 */
    protected abstract void registerFluidAbility(AbilityInstances instances);

    // ==================== IMultiblockAbilityPart ====================

    @Override
    public MultiblockAbility<IItemHandlerModifiable> getAbility() {
        return itemAbility();
    }

    @Override
    public List<MultiblockAbility<?>> getAbilities() {
        List<MultiblockAbility<?>> abilities = new ArrayList<>(2);
        abilities.add(itemAbility());
        abilities.add(fluidAbility());
        return abilities;
    }

    @Override
    public void registerAbilities(AbilityInstances instances) {
        if (instances.isKey(itemAbility())) {
            instances.add(itemAbilityHandler());
        } else if (instances.isKey(fluidAbility())) {
            registerFluidAbility(instances);
        }
    }

    // ==================== 过滤标记 ====================

    public ItemStack getItemFilter(int slot) {
        if (slot < 0 || slot >= FILTER_SLOTS) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = this.itemFilters[slot];
        return stack == null ? ItemStack.EMPTY : stack;
    }

    public FluidStack getFluidFilter(int slot) {
        if (slot < 0 || slot >= FILTER_SLOTS) {
            return null;
        }
        return this.fluidFilters[slot];
    }

    /** AE 网络中该槽位物品标记的总存量（离线或没有时为 0）。 */
    public long getItemStock(int slot) {
        if (slot < 0 || slot >= FILTER_SLOTS) {
            return 0L;
        }
        return this.itemStock[slot];
    }

    /** AE 网络中该槽位流体标记的总存量（离线或没有时为 0）。 */
    public long getFluidStock(int slot) {
        if (slot < 0 || slot >= FILTER_SLOTS) {
            return 0L;
        }
        return this.fluidStock[slot];
    }

    /** 设置物品标记（数量固定为 1，作为纯标记使用）。 */
    public void setItemFilter(int slot, ItemStack stack) {
        if (slot < 0 || slot >= FILTER_SLOTS) {
            return;
        }
        if (stack == null || stack.isEmpty()) {
            this.itemFilters[slot] = ItemStack.EMPTY;
        } else {
            ItemStack marker = stack.copy();
            marker.setCount(1);
            this.itemFilters[slot] = marker;
        }
        this.syncFilters();
    }

    /** 设置流体标记（数量固定为 1 桶，仅作标记）。 */
    public void setFluidFilter(int slot, FluidStack stack) {
        if (slot < 0 || slot >= FILTER_SLOTS) {
            return;
        }
        if (stack == null || stack.getFluid() == null || stack.amount <= 0) {
            this.fluidFilters[slot] = null;
        } else {
            FluidStack marker = stack.copy();
            marker.amount = Fluid.BUCKET_VOLUME;
            this.fluidFilters[slot] = marker;
        }
        this.syncFilters();
    }

    // ==================== 隔离 / 标记汇总 ====================

    /**
     * 所在多方块是否开启了“总线隔离”。
     * 只有支持隔离的控制器（{@link IDistinctBusController}）才会返回 true。
     */
    protected boolean isBusIsolated() {
        MultiblockControllerBase controller = this.getController();
        return controller instanceof IDistinctBusController && ((IDistinctBusController) controller).isDistinct();
    }

    /** 结构内所有与当前总成“同类型”的总成（输出总成与输入总成互不相通）。 */
    protected List<MetaTileEntityMEAssembly> siblingAssemblies() {
        List<MetaTileEntityMEAssembly> result = new ArrayList<>();
        MultiblockControllerBase controller = this.getController();
        if (controller == null) {
            return result;
        }
        for (IMultiblockPart part : controller.getMultiblockParts()) {
            if (part == this || !(part instanceof MetaTileEntityMEAssembly)) {
                continue;
            }
            MetaTileEntityMEAssembly other = (MetaTileEntityMEAssembly) part;
            if (other.getClass() == this.getClass()) {
                result.add(other);
            }
        }
        return result;
    }

    /**
     * 当前生效的物品标记列表：自己的标记 + （未开启隔离时）所有同类型总成的标记。
     * 未标记时返回空列表，调用方据此判断“不过滤”。
     */
    protected List<ItemStack> collectActiveItemFilters() {
        List<ItemStack> result = new ArrayList<>();
        for (ItemStack stack : this.itemFilters) {
            if (stack != null && !stack.isEmpty() && !containsItem(result, stack)) {
                result.add(stack);
            }
        }
        if (!this.isBusIsolated()) {
            for (MetaTileEntityMEAssembly other : this.siblingAssemblies()) {
                for (ItemStack stack : other.itemFilters) {
                    if (stack != null && !stack.isEmpty() && !containsItem(result, stack)) {
                        result.add(stack);
                    }
                }
            }
        }
        return result;
    }

    /** 当前生效的流体标记列表（规则同上）。 */
    protected List<FluidStack> collectActiveFluidFilters() {
        List<FluidStack> result = new ArrayList<>();
        for (FluidStack stack : this.fluidFilters) {
            if (stack != null && !containsFluid(result, stack)) {
                result.add(stack);
            }
        }
        if (!this.isBusIsolated()) {
            for (MetaTileEntityMEAssembly other : this.siblingAssemblies()) {
                for (FluidStack stack : other.fluidFilters) {
                    if (stack != null && !containsFluid(result, stack)) {
                        result.add(stack);
                    }
                }
            }
        }
        return result;
    }

    /** 物品是否通过过滤：没有任何物品标记时一律通过。 */
    protected boolean matchesItemFilter(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        List<ItemStack> filters = this.collectActiveItemFilters();
        if (filters.isEmpty()) {
            return true;
        }
        for (ItemStack filter : filters) {
            if (isSameItem(filter, stack)) {
                return true;
            }
        }
        return false;
    }

    /** 流体是否通过过滤：没有任何流体标记时一律通过。 */
    protected boolean matchesFluidFilter(FluidStack stack) {
        if (stack == null || stack.getFluid() == null) {
            return false;
        }
        List<FluidStack> filters = this.collectActiveFluidFilters();
        if (filters.isEmpty()) {
            return true;
        }
        for (FluidStack filter : filters) {
            if (isSameFluid(filter, stack)) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isSameItem(ItemStack a, ItemStack b) {
        if (a == null || b == null || a.isEmpty() || b.isEmpty()) {
            return false;
        }
        return ItemStack.areItemsEqual(a, b) && ItemStack.areItemStackTagsEqual(a, b);
    }

    protected static boolean isSameFluid(FluidStack a, FluidStack b) {
        if (a == null || b == null || a.getFluid() == null || b.getFluid() == null) {
            return false;
        }
        return a.getFluid() == b.getFluid() && FluidStack.areFluidStackTagsEqual(a, b);
    }

    private static boolean containsItem(List<ItemStack> list, ItemStack stack) {
        for (ItemStack entry : list) {
            if (isSameItem(entry, stack)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsFluid(List<FluidStack> list, FluidStack stack) {
        for (FluidStack entry : list) {
            if (isSameFluid(entry, stack)) {
                return true;
            }
        }
        return false;
    }

    // ==================== AE 网络访问 ====================

    /** 物品 ME 监视器（未连接/未通电时返回 null）。 */
    protected IMEMonitor<IAEItemStack> getItemMonitor() {
        AENetworkProxy proxy = this.getProxy();
        if (proxy == null) {
            return null;
        }
        try {
            return proxy.getStorage().getInventory(
                    AEApi.instance().storage().getStorageChannel(IItemStorageChannel.class));
        } catch (GridAccessException e) {
            return null;
        }
    }

    /** 流体 ME 监视器（未连接/未通电时返回 null）。 */
    protected IMEMonitor<IAEFluidStack> getFluidMonitor() {
        AENetworkProxy proxy = this.getProxy();
        if (proxy == null) {
            return null;
        }
        try {
            return proxy.getStorage().getInventory(
                    AEApi.instance().storage().getStorageChannel(IFluidStorageChannel.class));
        } catch (GridAccessException e) {
            return null;
        }
    }

    // ==================== Tooltip（只放通用提示，具体说明由子类加） ====================

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        // 通用提示：可用剪线钳切换额外连接面（GT 的 ME 部件标准提示）
        addTooltipOnce(tooltip, I18n.translateToLocal("gregtech.machine.me.extra_connections.tooltip"));
    }

    /** 只在 tooltip 里没有这一行时才添加，避免任何原因造成的重复显示。 */
    protected static void addTooltipOnce(List<String> tooltip, String line) {
        if (line != null && !line.isEmpty() && !tooltip.contains(line)) {
            tooltip.add(line);
        }
    }

    // ==================== 每 tick：网络在线状态 + 每 20 tick 同步存量 ====================

    @Override
    public void update() {
        super.update();
        World world = this.getWorld();
        if (world == null || world.isRemote) {
            return;
        }
        // 兜底：确保 AE 代理已创建（放置时 BlockMachine 也会创建一次）
        if (this.getProxy() == null) {
            return;
        }
        // 在线状态：按 shouldSyncME() 的间隔检测并只在变化时发包，GUI 里的“在线/离线”会实时刷新
        if (this.shouldSyncME()) {
            boolean online = this.isNetworkOnline();
            if (this.isOnline != online) {
                this.isOnline = online;
                this.meStatusChanged = true;
                this.writeCustomData(GregtechDataCodes.UPDATE_ONLINE_STATUS, buf -> buf.writeBoolean(online));
            } else {
                this.meStatusChanged = false;
            }
        }
        // 存量：每 20 tick 刷新一次并同步给客户端，保证幽灵槽里的数字动态刷新
        if (world.getTotalWorldTime() % STOCK_SYNC_INTERVAL == 0L) {
            this.refreshNetworkStock();
            this.writeCustomData(STOCK_SYNC_DATA, this::writeStockData);
            this.markDirty();
        }
    }

    /**
     * 网络是否在线：AE 代理已就绪、有电，并且 {@code getGrid()} 里确实挂着机器节点。
     *
     * <p>注意 {@code IGrid#getMachines(Class)} 的参数必须是 {@code IGridHost} 的类，
     * MTE 本身不是 IGridHost（{@code MetaTileEntityHolder} 才是），直接传 {@code getClass()}
     * 会永远得到空集合，所以这里用 {@code getMachinesClasses()} 判断网格里是否已有机器。</p>
     */
    public boolean isNetworkOnline() {
        AENetworkProxy proxy = this.getProxy();
        if (proxy == null || !proxy.isActive() || !proxy.isPowered()) {
            return false;
        }
        try {
            IGrid grid = proxy.getGrid();
            return grid != null && !grid.getMachinesClasses().isEmpty();
        } catch (GridAccessException e) {
            return false;
        }
    }

    /** 读取 AE 网络里每个标记的“总存量”（{@link IMEMonitor#getAvailableItems}）。 */
    protected void refreshNetworkStock() {
        Arrays.fill(this.itemStock, 0L);
        Arrays.fill(this.fluidStock, 0L);
        if (!this.isOnline) {
            return;
        }
        IMEMonitor<IAEItemStack> itemMonitor = this.getItemMonitor();
        IItemList<IAEItemStack> itemList = itemMonitor == null ? null : itemMonitor.getAvailableItems(new ItemList());
        IMEMonitor<IAEFluidStack> fluidMonitor = this.getFluidMonitor();
        IItemList<IAEFluidStack> fluidList = fluidMonitor == null ? null : fluidMonitor.getAvailableItems(new FluidList());

        for (int slot = 0; slot < FILTER_SLOTS; slot++) {
            ItemStack itemFilter = this.getItemFilter(slot);
            if (itemList != null && !itemFilter.isEmpty()) {
                IAEItemStack found = itemList.findPrecise(AEItemStack.fromItemStack(itemFilter));
                if (found != null) {
                    this.itemStock[slot] = found.getStackSize();
                }
            }
            FluidStack fluidFilter = this.getFluidFilter(slot);
            if (fluidList != null && fluidFilter != null) {
                IAEFluidStack found = fluidList.findPrecise(AEFluidStack.fromFluidStack(fluidFilter));
                if (found != null) {
                    this.fluidStock[slot] = found.getStackSize();
                }
            }
        }
    }

    private void writeStockData(PacketBuffer buf) {
        buf.writeBoolean(this.isOnline);
        buf.writeVarInt(FILTER_SLOTS);
        for (int slot = 0; slot < FILTER_SLOTS; slot++) {
            buf.writeLong(this.itemStock[slot]);
            buf.writeLong(this.fluidStock[slot]);
        }
    }

    private void readStockData(PacketBuffer buf) {
        boolean online = buf.readBoolean();
        if (this.isOnline != online) {
            this.isOnline = online;
            this.scheduleRenderUpdate();
        }
        int slots = buf.readVarInt();
        int readable = Math.min(slots, FILTER_SLOTS);
        for (int slot = 0; slot < slots; slot++) {
            long items = buf.readLong();
            long fluids = buf.readLong();
            if (slot < readable) {
                this.itemStock[slot] = items;
                this.fluidStock[slot] = fluids;
            }
        }
    }

    // ==================== 开关（IControllable） ====================

    @Override
    public boolean isWorkingEnabled() {
        return this.workingEnabled;
    }

    @Override
    public void setWorkingEnabled(boolean workingEnabled) {
        if (this.workingEnabled == workingEnabled) {
            return;
        }
        this.workingEnabled = workingEnabled;
        World world = this.getWorld();
        if (world != null && !world.isRemote) {
            this.writeCustomData(GregtechDataCodes.WORKING_ENABLED, buf -> buf.writeBoolean(workingEnabled));
        }
    }

    // ==================== NBT / 同步 ====================

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean(NBT_WORKING, this.workingEnabled);
        data.setTag(NBT_ITEM_FILTERS, writeItemFilters());
        data.setTag(NBT_FLUID_FILTERS, writeFluidFilters());
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        if (data.hasKey(NBT_WORKING)) {
            this.workingEnabled = data.getBoolean(NBT_WORKING);
        }
        Arrays.fill(this.itemFilters, ItemStack.EMPTY);
        Arrays.fill(this.fluidFilters, null);

        NBTTagList itemList = data.getTagList(NBT_ITEM_FILTERS, 10);
        for (int i = 0; i < itemList.tagCount(); i++) {
            NBTTagCompound tag = itemList.getCompoundTagAt(i);
            int slot = tag.getInteger(NBT_SLOT);
            if (slot < 0 || slot >= FILTER_SLOTS || !tag.hasKey(NBT_ITEM, 10)) {
                continue;
            }
            ItemStack stack = new ItemStack(tag.getCompoundTag(NBT_ITEM));
            this.itemFilters[slot] = stack.isEmpty() ? ItemStack.EMPTY : stack;
        }

        NBTTagList fluidList = data.getTagList(NBT_FLUID_FILTERS, 10);
        for (int i = 0; i < fluidList.tagCount(); i++) {
            NBTTagCompound tag = fluidList.getCompoundTagAt(i);
            int slot = tag.getInteger(NBT_SLOT);
            if (slot < 0 || slot >= FILTER_SLOTS || !tag.hasKey(NBT_FLUID, 10)) {
                continue;
            }
            this.fluidFilters[slot] = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag(NBT_FLUID));
        }
    }

    private NBTTagList writeItemFilters() {
        NBTTagList list = new NBTTagList();
        for (int slot = 0; slot < FILTER_SLOTS; slot++) {
            ItemStack stack = this.itemFilters[slot];
            if (stack == null || stack.isEmpty()) {
                continue;
            }
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger(NBT_SLOT, slot);
            tag.setTag(NBT_ITEM, stack.writeToNBT(new NBTTagCompound()));
            list.appendTag(tag);
        }
        return list;
    }

    private NBTTagList writeFluidFilters() {
        NBTTagList list = new NBTTagList();
        for (int slot = 0; slot < FILTER_SLOTS; slot++) {
            FluidStack stack = this.fluidFilters[slot];
            if (stack == null) {
                continue;
            }
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger(NBT_SLOT, slot);
            tag.setTag(NBT_FLUID, stack.writeToNBT(new NBTTagCompound()));
            list.appendTag(tag);
        }
        return list;
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(this.workingEnabled);
        this.writeFilters(buf);
        this.writeStockData(buf);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.workingEnabled = buf.readBoolean();
        this.readFilters(buf);
        this.readStockData(buf);
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == GregtechDataCodes.WORKING_ENABLED) {
            this.workingEnabled = buf.readBoolean();
        } else if (dataId == FILTER_SYNC_DATA) {
            this.readFilters(buf);
        } else if (dataId == STOCK_SYNC_DATA) {
            this.readStockData(buf);
        }
    }

    /** 标记变化后：写脏 + 广播给正在看这个总成的客户端。 */
    protected void syncFilters() {
        this.markDirty();
        World world = this.getWorld();
        if (world != null && !world.isRemote) {
            this.writeCustomData(FILTER_SYNC_DATA, this::writeFilters);
        }
    }

    private void writeFilters(PacketBuffer buf) {
        buf.writeVarInt(FILTER_SLOTS);
        for (int slot = 0; slot < FILTER_SLOTS; slot++) {
            ItemStack item = this.itemFilters[slot];
            boolean hasItem = item != null && !item.isEmpty();
            buf.writeBoolean(hasItem);
            if (hasItem) {
                buf.writeItemStack(item);
            }
            FluidStack fluid = this.fluidFilters[slot];
            boolean hasFluid = fluid != null;
            buf.writeBoolean(hasFluid);
            if (hasFluid) {
                NBTTagCompound tag = new NBTTagCompound();
                fluid.writeToNBT(tag);
                buf.writeCompoundTag(tag);
            }
        }
    }

    private void readFilters(PacketBuffer buf) {
        try {
            int slots = buf.readVarInt();
            int readable = Math.min(slots, FILTER_SLOTS);
            Arrays.fill(this.itemFilters, ItemStack.EMPTY);
            Arrays.fill(this.fluidFilters, null);
            for (int slot = 0; slot < slots; slot++) {
                boolean hasItem = buf.readBoolean();
                ItemStack item = hasItem ? buf.readItemStack() : ItemStack.EMPTY;
                boolean hasFluid = buf.readBoolean();
                FluidStack fluid = null;
                if (hasFluid) {
                    NBTTagCompound tag = buf.readCompoundTag();
                    if (tag != null) {
                        fluid = FluidStack.loadFluidStackFromNBT(tag);
                    }
                }
                if (slot < readable) {
                    this.itemFilters[slot] = item == null ? ItemStack.EMPTY : item;
                    this.fluidFilters[slot] = fluid;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
