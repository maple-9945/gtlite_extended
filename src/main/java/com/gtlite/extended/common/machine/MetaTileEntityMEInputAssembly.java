package com.gtlite.extended.common.machine;

import appeng.api.AEApi;
import appeng.api.config.Actionable;
import appeng.api.networking.security.IActionSource;
import appeng.api.storage.IMEMonitor;
import appeng.api.storage.channels.IFluidStorageChannel;
import appeng.api.storage.data.IAEFluidStack;
import appeng.api.storage.data.IItemList;
import appeng.me.GridAccessException;
import appeng.me.helpers.AENetworkProxy;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.metatileentity.multiblock.AbilityInstances;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.gui.widget.appeng.AEFluidConfigWidget;
import gregtech.common.gui.widget.appeng.AEItemConfigWidget;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.gui.widgets.ImageCycleButtonWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.gui.widgets.WidgetGroup;
import gregtech.common.metatileentities.multi.multiblockpart.appeng.MetaTileEntityMEStockingBus;
import gregtech.common.metatileentities.multi.multiblockpart.appeng.slot.ExportOnlyAEFluidList;
import gregtech.common.metatileentities.multi.multiblockpart.appeng.slot.ExportOnlyAEFluidSlot;
import gregtech.common.metatileentities.multi.multiblockpart.appeng.stack.WrappedFluidStack;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * ME 库存输入总成：把 GT 原生的“ME 库存输入总线（物品）”与“ME 库存输入仓（流体）”合成一个部件。
 *
 * <p><b>物品侧完全继承</b> {@link MetaTileEntityMEStockingBus}：自动拉取、配置槽、编程电路槽、
 * 额外槽、存量同步、通知全部由 GT 原类负责。</p>
 *
 * <p><b>流体侧移植</b> GT {@code MetaTileEntityMEStockingHatch}：
 * 16 个 {@link ExportOnlyAEFluidSlot} 虚拟仓、自动拉取配置文件、SIMULATE 填充 stock，
 * 以及“抽取时按 config 真正从 AE 提取”的 slot 行为都与 GT 库存输入仓一致（搬移，不改语义）。</p>
 *
 * <p>GUI：GT 原生 ModularUI（1.12.2 无 MUI2）：左侧物品配置 + 右侧流体配置 + 中间电路/额外槽。</p>
 */
public class MetaTileEntityMEInputAssembly extends MetaTileEntityMEStockingBus {

    private static final int FLUID_SLOTS = 16;
    private static final long FLUID_REFRESH_INTERVAL = 100L;
    private static final long FLUID_REQUEST_AMOUNT = 2147483647L;

    private static final String NBT_FLUID_TANKS = "FluidTanks";
    private static final String NBT_FLUID_SLOT = "slot";
    private static final String NBT_FLUID_TANK = "tank";

    private StockingFluidList fluidHandler;
    private boolean fluidAutoPull = false;

    /** GT 库存输入总线的 autoPull 是 private，这里用反射读取/切换，行为仍走 GT 自己的 setAutoPull。 */
    private static Method setAutoPullMethod;
    private static Field autoPullField;

    static {
        try {
            setAutoPullMethod = MetaTileEntityMEStockingBus.class.getDeclaredMethod("setAutoPull", boolean.class);
            setAutoPullMethod.setAccessible(true);
            autoPullField = MetaTileEntityMEStockingBus.class.getDeclaredField("autoPull");
            autoPullField.setAccessible(true);
        } catch (Throwable ignored) {
        }
    }

    public MetaTileEntityMEInputAssembly(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity holder) {
        return new MetaTileEntityMEInputAssembly(this.metaTileEntityId);
    }

    // ==================== 能力：物品 + 流体 ====================

    @Override
    public List<MultiblockAbility<?>> getAbilities() {
        return Arrays.asList(MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS);
    }

    private DualAbilityHandler dualAbilityHandler;

    /** 物品能力 + 流体能力的复合 handler：让 GT/gtlitecore 的隔离逻辑把本总成的流体绑定到本总成的物品总线上。 */
    private DualAbilityHandler getDualAbilityHandler() {
        if (this.dualAbilityHandler == null) {
            this.dualAbilityHandler = new DualAbilityHandler(((ItemHandlerList) this.getImportItems()).getBackingHandlers());
        }
        return this.dualAbilityHandler;
    }

    @Override
    public void registerAbilities(@NotNull AbilityInstances abilityInstances) {
        if (abilityInstances.isKey(MultiblockAbility.IMPORT_ITEMS)) {
            abilityInstances.add(this.getDualAbilityHandler());
        } else if (abilityInstances.isKey(MultiblockAbility.IMPORT_FLUIDS)) {
            for (ExportOnlyAEFluidSlot slot : this.getAEFluidHandler().getInventory()) {
                abilityInstances.add(slot);
            }
        }
    }

    /**
     * 物品总线 + 流体仓的复合能力。
     *
     * <p>GT 1.12.2 的“总线隔离(distinct)”只按 IMPORT_ITEMS 逐个总线搜索配方；
     * 流体是否跟随该总线取决于该 handler 是否同时是 {@link IMultipleTankHandler}
     * （gtlitecore 的通道隔离正是这样把物品与流体配对的）。让本总成的物品能力同时
     * 暴露自己的 16 个流体仓，隔离开启后它只会用自己仓里的流体参与配方匹配。</p>
     */
    protected class DualAbilityHandler extends ItemHandlerList implements IMultipleTankHandler {

        private final FluidTankList fluidTanks;

        DualAbilityHandler(Collection<IItemHandler> backingHandlers) {
            super(new ArrayList<IItemHandler>(backingHandlers));
            this.fluidTanks = new FluidTankList(false, this.getFluidTankSlots());
        }

        private List<IFluidTank> getFluidTankSlots() {
            List<IFluidTank> tanks = new ArrayList<>();
            for (ExportOnlyAEFluidSlot slot : getAEFluidHandler().getInventory()) {
                tanks.add(slot);
            }
            return tanks;
        }

        @Override
        public List<IMultipleTankHandler.ITankEntry> getFluidTanks() {
            return this.fluidTanks.getFluidTanks();
        }

        @Override
        public int getTanks() {
            return this.fluidTanks.getTanks();
        }

        @Override
        public IMultipleTankHandler.ITankEntry getTankAt(int index) {
            return this.fluidTanks.getTankAt(index);
        }

        @Override
        public boolean allowSameFluidFill() {
            return this.fluidTanks.allowSameFluidFill();
        }

        @Override
        public IFluidTankProperties[] getTankProperties() {
            return this.fluidTanks.getTankProperties();
        }

        @Override
        public int fill(FluidStack resource, boolean doFill) {
            return this.fluidTanks.fill(resource, doFill);
        }

        @Override
        public FluidStack drain(FluidStack resource, boolean doDrain) {
            return this.fluidTanks.drain(resource, doDrain);
        }

        @Override
        public FluidStack drain(int maxDrain, boolean doDrain) {
            return this.fluidTanks.drain(maxDrain, doDrain);
        }
    }

    // ==================== 流体虚拟仓（GT 库存输入仓移植） ====================

    protected StockingFluidList getAEFluidHandler() {
        if (this.fluidHandler == null) {
            this.fluidHandler = new StockingFluidList(this, FLUID_SLOTS, this.getController());
        }
        return this.fluidHandler;
    }

    @Override
    protected void initializeInventory() {
        this.getAEFluidHandler();
        super.initializeInventory();
        // 物品 handler 可能被 GT 重建，清掉复合能力的缓存，保证和当前 backing handlers 一致
        this.dualAbilityHandler = null;
    }

    IMEMonitor<IAEFluidStack> getFluidMonitor() {
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

    IActionSource fluidActionSource() {
        return this.getActionSource();
    }

    // ==================== 每 tick：物品走 GT，流体按 GT 库存输入仓同步 ====================

    @Override
    public void update() {
        super.update();
        if (this.getWorld() == null || this.getWorld().isRemote) {
            return;
        }
        if (this.fluidAutoPull && this.isWorkingEnabled()
                && this.getOffsetTimer() % FLUID_REFRESH_INTERVAL == 0L) {
            this.refreshFluidList();
        }
        this.syncFluidStock();

        if (this.meStatusChanged && !this.isOnline) {
            // 断开 AE 时只清掉虚拟 stock，保留 config（标记），这样才能看到标记了哪些流体
            this.clearFluidStocks();
        }
    }

    /** 自动拉取：把 AE 里的流体按顺序填入 16 个流体配置槽（同 GT 库存输入仓 refreshList）。 */
    private void refreshFluidList() {
        IMEMonitor<IAEFluidStack> monitor = this.getFluidMonitor();
        if (monitor == null) {
            // 离线时不要清 config，否则玩家看不到已标记的流体
            this.clearFluidStocks();
            return;
        }
        IItemList<IAEFluidStack> storageList = monitor.getStorageList();
        if (storageList == null) {
            this.clearFluidStocks();
            return;
        }
        int index = 0;
        for (IAEFluidStack stack : storageList) {
            if (index >= FLUID_SLOTS) {
                break;
            }
            if (stack.getStackSize() == 0L) {
                continue;
            }
            IAEFluidStack simulated = monitor.extractItems(stack,
                    Actionable.SIMULATE, this.getActionSource());
            if (simulated == null || simulated.getStackSize() == 0L) {
                continue;
            }
            FluidStack fluidStack = simulated.getFluidStack();
            if (fluidStack == null) {
                continue;
            }
            IAEFluidStack configStack = WrappedFluidStack.fromFluidStack(fluidStack);
            configStack.setStackSize(1L);
            ExportOnlyAEFluidSlot slot = this.getAEFluidHandler().getInventory()[index];
            slot.setConfig(configStack);
            slot.setStack(simulated);
            index++;
        }
        this.clearFluidInventory(index);
    }

    /** 只清虚拟 stock，保留 config（用于离线/无网络时继续显示标记）。 */
    private void clearFluidStocks() {
        for (ExportOnlyAEFluidSlot slot : this.getAEFluidHandler().getInventory()) {
            slot.setStack((IAEFluidStack) null);
        }
    }

    private void clearFluidInventory(int startIndex) {
        for (int i = startIndex; i < FLUID_SLOTS; i++) {
            ExportOnlyAEFluidSlot slot = this.getAEFluidHandler().getInventory()[i];
            slot.setConfig((IAEFluidStack) null);
            slot.setStack((IAEFluidStack) null);
        }
    }

    /** 按配置刷新 stock（GT 库存输入仓 syncME 的 SIMULATE 部分）。 */
    private void syncFluidStock() {
        IMEMonitor<IAEFluidStack> monitor = this.getFluidMonitor();
        if (monitor == null) {
            return;
        }
        for (ExportOnlyAEFluidSlot slot : this.getAEFluidHandler().getInventory()) {
            IAEFluidStack config = slot.getConfig();
            if (config == null) {
                slot.setStack((IAEFluidStack) null);
                continue;
            }
            IAEFluidStack request;
            if (config instanceof WrappedFluidStack) {
                IAEFluidStack aeConfig = ((WrappedFluidStack) config).getAEStack();
                if (aeConfig == null) {
                    slot.setStack((IAEFluidStack) null);
                    continue;
                }
                request = aeConfig.copy();
            } else {
                request = config.copy();
            }
            request.setStackSize(FLUID_REQUEST_AMOUNT);
            IAEFluidStack result = monitor.extractItems(request,
                    Actionable.SIMULATE, this.getActionSource());
            slot.setStack(result);
        }
    }

    /** 物品侧自动拉取状态（GT 库存输入总线的 autoPull）。 */
    private boolean isItemAutoPull() {
        try {
            return autoPullField != null && autoPullField.getBoolean(this);
        } catch (Throwable t) {
            return false;
        }
    }

    /** 切换流体侧自动拉取（客户端/服务端都会回调，回调里只改本地字段）。 */
    private void setFluidAutoPull(boolean value) {
        this.fluidAutoPull = value;
        if (!value) {
            this.clearFluidStocks();
        }
    }

    /** 切换物品侧自动拉取，调用 GT 自己的 setAutoPull（会 markDirty + 同步客户端）。 */
    private void setItemAutoPull(boolean value) {
        try {
            if (setAutoPullMethod != null) {
                setAutoPullMethod.invoke(this, value);
            }
        } catch (Throwable ignored) {
        }
    }

    @Override
    public void addToMultiBlock(MultiblockControllerBase controllerBase) {
        super.addToMultiBlock(controllerBase);
        for (ExportOnlyAEFluidSlot slot : this.getAEFluidHandler().getInventory()) {
            slot.addNotifiableMetaTileEntity(controllerBase);
            slot.addToNotifiedList(this, slot, false);
        }
    }

    @Override
    public void removeFromMultiBlock(MultiblockControllerBase controllerBase) {
        for (ExportOnlyAEFluidSlot slot : this.getAEFluidHandler().getInventory()) {
            slot.removeNotifiableMetaTileEntity(controllerBase);
        }
        super.removeFromMultiBlock(controllerBase);
    }

    // ==================== NBT ====================

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        NBTTagList tanks = new NBTTagList();
        for (int i = 0; i < FLUID_SLOTS; i++) {
            ExportOnlyAEFluidSlot slot = this.getAEFluidHandler().getInventory()[i];
            NBTTagCompound slotTag = new NBTTagCompound();
            slotTag.setInteger(NBT_FLUID_SLOT, i);
            slotTag.setTag(NBT_FLUID_TANK, slot.serializeNBT());
            tanks.appendTag(slotTag);
        }
        data.setTag(NBT_FLUID_TANKS, tanks);
        data.setBoolean("FluidAutoPull", this.fluidAutoPull);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        if (data.hasKey(NBT_FLUID_TANKS)) {
            NBTTagList tanks = data.getTagList(NBT_FLUID_TANKS, 10);
            for (int i = 0; i < tanks.tagCount(); i++) {
                NBTTagCompound slotTag = tanks.getCompoundTagAt(i);
                int slotIndex = slotTag.getInteger(NBT_FLUID_SLOT);
                if (slotIndex >= 0 && slotIndex < FLUID_SLOTS) {
                    this.getAEFluidHandler().getInventory()[slotIndex]
                            .deserializeNBT(slotTag.getCompoundTag(NBT_FLUID_TANK));
                }
            }
        }
        if (data.hasKey("FluidAutoPull")) {
            this.fluidAutoPull = data.getBoolean("FluidAutoPull");
        }
    }

    // ==================== GUI：物品配置 + 流体配置 + 电路槽 ====================

    @Override
    protected ModularUI.Builder createUITemplate(EntityPlayer player) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 264)
                .label(10, 5, this.getMetaFullName());
        builder.dynamicLabel(10, 15, () -> this.isOnline
                ? I18n.format("gregtech.gui.me_network.online")
                : I18n.format("gregtech.gui.me_network.offline"), 4210752);
        // 物品配置（GT 库存输入总线原生控件）
        builder.widget(new AEItemConfigWidget(7, 25, this.getAEItemHandler()));
        // 自动拉取按钮统一放到标题行右侧空白处，避免压住物品/流体配置槽
        builder.widget(new ImageCycleButtonWidget(128, 15, 16, 16, GuiTextures.BUTTON_AUTO_PULL,
                this::isItemAutoPull, this::setItemAutoPull)
                .setTooltipHoverString("gregtech.gui.me_bus.auto_pull_button"));
        builder.widget(new ImageCycleButtonWidget(148, 15, 16, 16, GuiTextures.BUTTON_AUTO_PULL,
                () -> this.fluidAutoPull, this::setFluidAutoPull)
                .setTooltipHoverString("gtlite_extended.gui.fluid_auto_pull_button"));

        // 流体页：流体配置（整组位于物品区下方）
        WidgetGroup fluidPage = new WidgetGroup(7, 105, 162, 72);
        fluidPage.addWidget(new AEFluidConfigWidget(0, 0, this.getAEFluidHandler()));
        builder.widget(fluidPage);

        // 编程电路槽：GT 原生左/右键/滚轮行为 + Shift+左键打开 0~32 选择面板
        WidgetGroup circuitSelector = new WidgetGroup(7, 105, 9 * 18, 4 * 18);
        circuitSelector.setVisible(false);
        ProgrammedCircuitSlotWidget circuitSlot =
                new ProgrammedCircuitSlotWidget(this.circuitInventory, 0, 79, 79);
        circuitSlot.setBackgroundTexture(GuiTextures.SLOT, GuiTextures.INT_CIRCUIT_OVERLAY);
        for (int value = 0; value <= 32; value++) {
            final int circuitValue = value;
            circuitSelector.addWidget(new ClickButtonWidget(
                    (value % 9) * 18, (value / 9) * 18, 18, 18,
                    String.valueOf(value), data -> circuitSlot.selectValue(circuitValue))
                    .setShouldClientCallback(true)
                    .setTooltipText("gtlite_extended.gui.circuit_button", circuitValue));
        }
        circuitSlot.attachSelector(circuitSelector, fluidPage);
        builder.widget(circuitSelector);
        builder.widget(circuitSlot);
        builder.widget(new SlotWidget(this.extraSlotInventory, 0, 79, 61)
                .setBackgroundTexture(GuiTextures.SLOT)
                .setTooltipText("gregtech.gui.me_bus.extra_slot"));
        builder.image(79, 43, 18, 18, GuiTextures.ARROW_DOUBLE);
        builder.bindPlayerInventory(player.inventory, GuiTextures.SLOT, 7, 182);
        return builder;
    }

    // ==================== 外观：ME 输入总线贴图 ====================

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        if (this.shouldRenderOverlay()) {
            if (this.isOnline) {
                Textures.ME_INPUT_BUS_ACTIVE.renderSided(this.getFrontFacing(), renderState, translation, pipeline);
            } else {
                Textures.ME_INPUT_BUS.renderSided(this.getFrontFacing(), renderState, translation, pipeline);
            }
        }
    }

    // ==================== Tooltip ====================

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.machine.fluid_hatch.import.tooltip"));
        tooltip.add(I18n.format("gregtech.machine.me.stocking_fluid.tooltip"));
        tooltip.add(I18n.format("gregtech.machine.me_import_fluid_hatch.configs.tooltip"));
        tooltip.add(I18n.format("gregtech.machine.me.stocking_fluid.tooltip.2"));
    }

    // ==================== GT 库存输入仓的流体槽（移植其 drain 真实提取行为） ====================

    protected class StockingFluidList extends ExportOnlyAEFluidList {

        StockingFluidList(MetaTileEntity holder, int slots, MetaTileEntity entityToNotify) {
            super(holder, slots, entityToNotify);
        }

        @Override
        protected void createInventory(MetaTileEntity holder, MetaTileEntity entityToNotify) {
            this.inventory = new ExportOnlyAEFluidSlot[this.size];
            for (int i = 0; i < this.size; i++) {
                this.inventory[i] = new StockingFluidSlot(holder, entityToNotify);
            }
        }

        @Override
        public boolean isStocking() {
            return true;
        }

        @Override
        public boolean isAutoPull() {
            return fluidAutoPull;
        }
    }

    protected class StockingFluidSlot extends ExportOnlyAEFluidSlot {

        StockingFluidSlot(MetaTileEntity holder, MetaTileEntity entityToNotify) {
            super(holder, entityToNotify);
        }

        @Override
        public @Nullable FluidStack drain(int maxDrain, boolean doDrain) {
            IAEFluidStack stock = this.getStock();
            IAEFluidStack config = this.getConfig();
            if (stock == null || config == null || maxDrain <= 0) {
                return null;
            }
            IMEMonitor<IAEFluidStack> monitor = MetaTileEntityMEInputAssembly.this.getFluidMonitor();
            if (monitor == null) {
                return null;
            }
            IAEFluidStack request;
            if (config instanceof WrappedFluidStack) {
                IAEFluidStack aeConfig = ((WrappedFluidStack) config).getAEStack();
                if (aeConfig == null) {
                    return null;
                }
                request = aeConfig.copy();
            } else {
                request = config.copy();
            }
            request.setStackSize(maxDrain);
            IAEFluidStack result = monitor.extractItems(request,
                    doDrain ? Actionable.MODULATE : Actionable.SIMULATE,
                    MetaTileEntityMEInputAssembly.this.fluidActionSource());
            if (result == null || result.getStackSize() <= 0L) {
                return null;
            }
            int extracted = (int) Math.min(result.getStackSize(), (long) maxDrain);
            stock.setStackSize(Math.max(0L, stock.getStackSize() - extracted));
            this.trigger();
            FluidStack output = config.getFluidStack();
            if (output == null) {
                return null;
            }
            output.amount = extracted;
            return output;
        }
    }
}
