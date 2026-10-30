package com.gtlite.extended.common.machine;

import appeng.api.config.Actionable;
import appeng.api.storage.IMEMonitor;
import appeng.api.storage.data.IAEFluidStack;
import appeng.api.storage.data.IAEItemStack;
import appeng.fluids.util.AEFluidStack;
import appeng.util.item.AEItemStack;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.gtlite.extended.common.gui.GhostFilterSlotWidget;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.Widget;
import gregtech.api.gui.widgets.ScrollableListWidget;
import gregtech.api.gui.widgets.WidgetGroup;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.AbilityInstances;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.client.renderer.texture.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.ArrayList;
import java.util.List;

/**
 * ME 库存输入总成（ME Input Assembly）。
 *
 * <p>放在多方块结构里同时充当“输入总线 + 输入仓”，但**不缓存任何物品/流体**：
 * 它只保存过滤标记，机器需要原料时直接向 AE 网络发起提取请求。</p>
 *
 * <ul>
 *     <li>物品侧：对多方块暴露 {@link MultiblockAbility#IMPORT_ITEMS}，
 *         每个标记对应一个槽位，{@code getStackInSlot} 用 {@code Actionable.SIMULATE}
 *         查询 AE 里有多少，{@code extractItem(...)} 真正从 AE 提取并直接交给机器；</li>
 *     <li>流体侧：对多方块暴露 {@link MultiblockAbility#IMPORT_FLUIDS}，
 *         每个标记槽注册一个 {@link IFluidTank}；{@code getFluid()/getFluidAmount()}
 *         查询 AE 可用量，{@code drain(amount, true)} 直接从 AE 提取；</li>
 *     <li><b>GUI 只有幽灵标记槽</b>：8 列 × 6 行共 48 个标记槽（一屏 4 行，带滚动条），
 *         不显示任何缓存区；</li>
 *     <li>外观叠 ME 输入总线贴图 {@link Textures#ME_INPUT_BUS}（在线时用 _ACTIVE）。</li>
 * </ul>
 */
public class MetaTileEntityMEInputAssembly extends MetaTileEntityMEAssembly {

    /** 单次模拟查询的最大数量（只影响“看得见多少”，真实提取不受此限制）。 */
    private static final int SIMULATE_QUERY = 4096;

    /** 单次流体模拟查询的最大数量。 */
    private static final int SIMULATE_FLUID = 1_000_000;

    private final AEItemFilterHandler itemHandler = new AEItemFilterHandler();
    private final AEFluidFilterTank[] fluidTanks = new AEFluidFilterTank[FILTER_SLOTS];

    private long itemCacheTick = Long.MIN_VALUE;
    private final List<ItemStack> itemCacheFilters = new ArrayList<>();
    private final List<ItemStack> itemCacheAvailable = new ArrayList<>();

    public MetaTileEntityMEInputAssembly(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, false);
        for (int slot = 0; slot < FILTER_SLOTS; slot++) {
            this.fluidTanks[slot] = new AEFluidFilterTank(slot);
        }
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity holder) {
        return new MetaTileEntityMEInputAssembly(this.metaTileEntityId);
    }

    // ==================== 能力定义 ====================

    @Override
    protected MultiblockAbility<IItemHandlerModifiable> itemAbility() {
        return MultiblockAbility.IMPORT_ITEMS;
    }

    @Override
    protected MultiblockAbility<IFluidTank> fluidAbility() {
        return MultiblockAbility.IMPORT_FLUIDS;
    }

    @Override
    protected IItemHandlerModifiable itemAbilityHandler() {
        return this.itemHandler;
    }

    @Override
    protected void registerFluidAbility(AbilityInstances instances) {
        // 每个标记槽一个“虚拟仓”，仓里有什么完全取决于 AE 网络
        for (AEFluidFilterTank tank : this.fluidTanks) {
            instances.add(tank);
        }
    }

    // ==================== 物品侧：按标记向 AE 取货 ====================

    private void refreshItemCache() {
        World world = this.getWorld();
        long now = world == null ? 0L : world.getTotalWorldTime();
        if (now == this.itemCacheTick) {
            return;
        }
        this.itemCacheTick = now;
        this.itemCacheFilters.clear();
        this.itemCacheFilters.addAll(this.collectActiveItemFilters());
        this.itemCacheAvailable.clear();

        IMEMonitor<IAEItemStack> monitor = this.getItemMonitor();
        for (ItemStack filter : this.itemCacheFilters) {
            ItemStack available = ItemStack.EMPTY;
            if (monitor != null) {
                ItemStack request = filter.copy();
                request.setCount(SIMULATE_QUERY);
                IAEItemStack simulated = monitor.extractItems(AEItemStack.fromItemStack(request),
                        Actionable.SIMULATE, this.getActionSource());
                if (simulated != null) {
                    available = simulated.createItemStack();
                }
            }
            this.itemCacheAvailable.add(available);
        }
    }

    private ItemStack extractItemFromAE(ItemStack filter, int amount, boolean simulate) {
        IMEMonitor<IAEItemStack> monitor = this.getItemMonitor();
        if (monitor == null || filter == null || filter.isEmpty() || amount <= 0) {
            return ItemStack.EMPTY;
        }
        ItemStack request = filter.copy();
        request.setCount(Math.max(1, Math.min(amount, SIMULATE_QUERY)));
        IAEItemStack extracted = monitor.extractItems(AEItemStack.fromItemStack(request),
                simulate ? Actionable.SIMULATE : Actionable.MODULATE, this.getActionSource());
        return extracted == null ? ItemStack.EMPTY : extracted.createItemStack();
    }

    private class AEItemFilterHandler implements IItemHandlerModifiable {

        @Override
        public void setStackInSlot(int slot, ItemStack stack) {
            // 只读的 AE 代理槽，禁止回写
        }

        @Override
        public int getSlots() {
            refreshItemCache();
            return itemCacheFilters.size();
        }

        @Override
        public ItemStack getStackInSlot(int slot) {
            refreshItemCache();
            if (slot < 0 || slot >= itemCacheAvailable.size()) {
                return ItemStack.EMPTY;
            }
            return itemCacheAvailable.get(slot).copy();
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            // 输入总成不接受回灌
            return stack;
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            refreshItemCache();
            if (slot < 0 || slot >= itemCacheFilters.size() || amount <= 0) {
                return ItemStack.EMPTY;
            }
            ItemStack filter = itemCacheFilters.get(slot);
            ItemStack extracted = extractItemFromAE(filter, amount, simulate);
            if (!simulate && !extracted.isEmpty() && slot < itemCacheAvailable.size()) {
                ItemStack cached = itemCacheAvailable.get(slot);
                int left = cached.getCount() - extracted.getCount();
                itemCacheAvailable.set(slot, left > 0 ? copyStackWithSize(cached, left) : ItemStack.EMPTY);
            }
            return extracted;
        }

        @Override
        public int getSlotLimit(int slot) {
            return Integer.MAX_VALUE;
        }
    }

    // ==================== 流体侧：每个标记一个虚拟仓 ====================

    private class AEFluidFilterTank implements IFluidTank {

        private final int slot;
        private long cacheTick = Long.MIN_VALUE;
        private FluidStack cachedFluid;

        private AEFluidFilterTank(int slot) {
            this.slot = slot;
        }

        @Override
        public FluidStack getFluid() {
            this.refresh();
            return this.cachedFluid == null ? null : this.cachedFluid.copy();
        }

        @Override
        public int getFluidAmount() {
            this.refresh();
            return this.cachedFluid == null ? 0 : this.cachedFluid.amount;
        }

        @Override
        public int getCapacity() {
            return Integer.MAX_VALUE;
        }

        @Override
        public FluidTankInfo getInfo() {
            this.refresh();
            return new FluidTankInfo(this.cachedFluid, Integer.MAX_VALUE);
        }

        @Override
        public int fill(FluidStack resource, boolean doFill) {
            return 0;
        }

        @Override
        public FluidStack drain(int maxDrain, boolean doDrain) {
            FluidStack filter = getFluidFilter(this.slot);
            if (filter == null || maxDrain <= 0) {
                return null;
            }
            IMEMonitor<IAEFluidStack> monitor = getFluidMonitor();
            if (monitor == null) {
                return null;
            }
            FluidStack request = filter.copy();
            request.amount = maxDrain;
            IAEFluidStack extracted = monitor.extractItems(AEFluidStack.fromFluidStack(request),
                    doDrain ? Actionable.MODULATE : Actionable.SIMULATE, getActionSource());
            if (extracted == null) {
                return null;
            }
            FluidStack result = extracted.getFluidStack();
            if (doDrain && result != null && this.cachedFluid != null) {
                int left = this.cachedFluid.amount - result.amount;
                this.cachedFluid = left > 0 ? copyFluidWithSize(this.cachedFluid, left) : null;
            }
            return result;
        }

        private void refresh() {
            World world = getWorld();
            long now = world == null ? 0L : world.getTotalWorldTime();
            if (now == this.cacheTick) {
                return;
            }
            this.cacheTick = now;
            FluidStack filter = getFluidFilter(this.slot);
            if (filter == null) {
                this.cachedFluid = null;
                return;
            }
            IMEMonitor<IAEFluidStack> monitor = getFluidMonitor();
            if (monitor == null) {
                this.cachedFluid = null;
                return;
            }
            FluidStack request = filter.copy();
            request.amount = SIMULATE_FLUID;
            IAEFluidStack simulated = monitor.extractItems(AEFluidStack.fromFluidStack(request),
                    Actionable.SIMULATE, getActionSource());
            this.cachedFluid = simulated == null ? null : simulated.getFluidStack();
        }
    }

    // ==================== 小工具 ====================

    private static ItemStack copyStackWithSize(ItemStack stack, int count) {
        ItemStack copy = stack.copy();
        copy.setCount(count);
        return copy;
    }

    private static FluidStack copyFluidWithSize(FluidStack stack, int amount) {
        FluidStack copy = stack.copy();
        copy.amount = amount;
        return copy;
    }

    // ==================== 外观：叠 ME 输入总线贴图 ====================

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
        // 只调用一次父类（父类只加通用的剪线钳提示）
        super.addInformation(stack, player, tooltip, advanced);
        // 本机专属说明：只加这一行
        addTooltipOnce(tooltip, "§7" + I18n.translateToLocal("gtlite_extended.machine.me_input_assembly.tooltip"));
        // 总线隔离提示：只加一次
        if (this.isBusIsolated()) {
            addTooltipOnce(tooltip, "§e" + I18n.translateToLocal("gtlite_extended.machine.me.bus_isolated"));
        }
    }

    // ==================== GUI：只有幽灵标记槽 ====================

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 214)
                .label(8, 6, this.getMetaFullName());
        builder.dynamicLabel(8, 18, () -> I18n.translateToLocal(this.isOnline
                ? "gregtech.gui.me_network.online"
                : "gregtech.gui.me_network.offline"), 4210752);
        builder.dynamicLabel(8, 28, () -> I18n.translateToLocal(this.isBusIsolated()
                ? "gtlite_extended.gui.bus.isolated"
                : "gtlite_extended.gui.bus.global"), 4210752);
        builder.label(8, 40, "gtlite_extended.gui.filter_hint");
        builder.widget(this.createFilterGrid(8, 50));
        builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 7, 128);
        return builder.build(this.getHolder(), entityPlayer);
    }

    /** 8 列 × 6 行、带滚动条的幽灵标记网格（一屏 4 行）。 */
    private Widget createFilterGrid(int x, int y) {
        int rows = FILTER_SLOTS / GRID_COLUMNS;
        ScrollableListWidget list = new ScrollableListWidget(x, y, GRID_COLUMNS * 18 + 10, VISIBLE_ROWS * 18);
        for (int row = 0; row < rows; row++) {
            WidgetGroup rowGroup = new WidgetGroup(0, 0, GRID_COLUMNS * 18, 18);
            for (int column = 0; column < GRID_COLUMNS; column++) {
                int slot = row * GRID_COLUMNS + column;
                rowGroup.addWidget(new GhostFilterSlotWidget(this, slot, column * 18, 0));
            }
            list.addWidget(rowGroup);
        }
        return list;
    }
}
