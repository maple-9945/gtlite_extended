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
import gregtech.api.capability.INotifiableHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.AbilityInstances;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.gui.widget.appeng.AEFluidGridWidget;
import gregtech.common.gui.widget.appeng.AEItemGridWidget;
import gregtech.common.inventory.appeng.SerializableFluidList;
import gregtech.common.inventory.appeng.SerializableItemList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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
 * ME 输出总成（ME Output Assembly）。
 *
 * <p>放在多方块结构里同时充当“输出总线 + 输出仓”：</p>
 * <ul>
 *     <li>对多方块暴露 {@link MultiblockAbility#EXPORT_ITEMS} 与 {@link MultiblockAbility#EXPORT_FLUIDS}，
 *         物品侧是无限接收的 {@link IItemHandlerModifiable}，流体侧是无限接收的 {@link IFluidTank}，
 *         产物先进内部缓存（{@link SerializableItemList} / {@link SerializableFluidList}）；</li>
 *     <li>{@link #update()} 中如果 AE 网络可用，就把缓存里的物品/流体
 *         {@code injectItems(..., Actionable.MODULATE, getActionSource())} 推进去；
 *         没有 AE 网络时产物留在缓存里（写进 NBT，掉线/拆机不丢）；</li>
 *     <li><b>GUI 只有输出缓存</b>：物品缓存 + 流体缓存两个 AE 列表（带滚动条），
 *         不再显示幽灵标记槽；</li>
 *     <li>外观叠 ME 输出总线贴图 {@link Textures#ME_OUTPUT_BUS}（在线时用 _ACTIVE）。</li>
 * </ul>
 */
public class MetaTileEntityMEOutputAssembly extends MetaTileEntityMEAssembly {

    private static final String NBT_ITEM_BUFFER = "ItemBuffer";
    private static final String NBT_FLUID_BUFFER = "FluidBuffer";

    private SerializableItemList itemBuffer;
    private SerializableFluidList fluidBuffer;

    private final ItemSink itemSink = new ItemSink();
    private final FluidSink fluidSink = new FluidSink();

    public MetaTileEntityMEOutputAssembly(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, true);
    }

    /**
     * 父类（{@code MetaTileEntity}）构造期就会回调这个方法创建物品/流体 handler，
     * 此时本类字段初始化还没执行，所以缓存必须在这里创建（与 GT 的 ME 输出总线写法一致）。
     */
    @Override
    protected void initializeInventory() {
        this.itemBuffer = new SerializableItemList();
        this.fluidBuffer = new SerializableFluidList();
        super.initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity holder) {
        return new MetaTileEntityMEOutputAssembly(this.metaTileEntityId);
    }

    // ==================== 能力定义 ====================

    @Override
    protected MultiblockAbility<IItemHandlerModifiable> itemAbility() {
        return MultiblockAbility.EXPORT_ITEMS;
    }

    @Override
    protected MultiblockAbility<IFluidTank> fluidAbility() {
        return MultiblockAbility.EXPORT_FLUIDS;
    }

    @Override
    protected IItemHandlerModifiable itemAbilityHandler() {
        return this.itemSink;
    }

    @Override
    protected void registerFluidAbility(AbilityInstances instances) {
        instances.add(this.fluidSink);
    }

    // ==================== 多方块挂载 ====================

    @Override
    public void addToMultiBlock(MultiblockControllerBase controllerBase) {
        super.addToMultiBlock(controllerBase);
        if (controllerBase instanceof MultiblockWithDisplayBase) {
            // 输出是无底洞，别让机器因为“输出满”停机
            ((MultiblockWithDisplayBase) controllerBase).enableItemInfSink();
            ((MultiblockWithDisplayBase) controllerBase).enableFluidInfSink();
        }
        this.itemSink.addNotifiableMetaTileEntity(controllerBase);
        this.fluidSink.addNotifiableMetaTileEntity(controllerBase);
    }

    @Override
    public void removeFromMultiBlock(MultiblockControllerBase controllerBase) {
        this.itemSink.removeNotifiableMetaTileEntity(controllerBase);
        this.fluidSink.removeNotifiableMetaTileEntity(controllerBase);
        super.removeFromMultiBlock(controllerBase);
    }

    // ==================== 每 tick：把缓存注入 AE ====================

    @Override
    public void update() {
        super.update();
        World world = this.getWorld();
        if (world == null || world.isRemote) {
            return;
        }
        // 在线状态由基类 update() 负责检测并同步，这里只看结果
        if (!this.isWorkingEnabled() || !this.shouldSyncME() || !this.isOnline) {
            return;
        }
        this.pushItems();
        this.pushFluids();
    }

    private void pushItems() {
        if (this.itemBuffer == null || this.itemBuffer.isEmpty()) {
            return;
        }
        IMEMonitor<IAEItemStack> monitor = this.getItemMonitor();
        if (monitor == null) {
            return;
        }
        for (IAEItemStack item : this.itemBuffer) {
            ItemStack prototype = item.createItemStack();
            if (!this.matchesItemFilter(prototype)) {
                continue;
            }
            IAEItemStack notInserted = monitor.injectItems(item.copy(), Actionable.MODULATE, this.getActionSource());
            if (notInserted != null && notInserted.getStackSize() > 0L) {
                item.setStackSize(notInserted.getStackSize());
            } else {
                item.reset();
            }
        }
    }

    private void pushFluids() {
        if (this.fluidBuffer == null || this.fluidBuffer.isEmpty()) {
            return;
        }
        IMEMonitor<IAEFluidStack> monitor = this.getFluidMonitor();
        if (monitor == null) {
            return;
        }
        for (IAEFluidStack fluid : this.fluidBuffer) {
            FluidStack prototype = fluid.getFluidStack();
            if (!this.matchesFluidFilter(prototype)) {
                continue;
            }
            IAEFluidStack notInserted = monitor.injectItems(fluid.copy(), Actionable.MODULATE, this.getActionSource());
            if (notInserted != null && notInserted.getStackSize() > 0L) {
                fluid.setStackSize(notInserted.getStackSize());
            } else {
                fluid.reset();
            }
        }
    }

    @Override
    public void onRemoval() {
        World world = this.getWorld();
        if (world != null && !world.isRemote) {
            // 拆机前最后再推一次；推不进去的会随 NBT 一起留在方块里
            this.pushItems();
            this.pushFluids();
            this.itemSink.clearNotifiables();
            this.fluidSink.clearNotifiables();
        }
        super.onRemoval();
    }

    // ==================== 外观：叠 ME 输出总线贴图 ====================

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        if (this.shouldRenderOverlay()) {
            if (this.isOnline) {
                Textures.ME_OUTPUT_BUS_ACTIVE.renderSided(this.getFrontFacing(), renderState, translation, pipeline);
            } else {
                Textures.ME_OUTPUT_BUS.renderSided(this.getFrontFacing(), renderState, translation, pipeline);
            }
        }
    }

    // ==================== Tooltip ====================

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, boolean advanced) {
        // 只调用一次父类（父类只加通用的剪线钳提示）
        super.addInformation(stack, player, tooltip, advanced);
        // 本机专属说明：只加这一行
        addTooltipOnce(tooltip, "§7" + I18n.translateToLocal("gtlite_extended.machine.me_output_assembly.tooltip"));
        // 总线隔离提示：只加一次
        if (this.isBusIsolated()) {
            addTooltipOnce(tooltip, "§e" + I18n.translateToLocal("gtlite_extended.machine.me.bus_isolated"));
        }
    }

    // ==================== GUI：只显示输出缓存（物品 + 流体） ====================

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 262)
                .label(8, 6, this.getMetaFullName());
        builder.dynamicLabel(8, 18, () -> I18n.translateToLocal(this.isOnline
                ? "gregtech.gui.me_network.online"
                : "gregtech.gui.me_network.offline"), 4210752);
        builder.dynamicLabel(8, 28, () -> I18n.translateToLocal(this.isBusIsolated()
                ? "gtlite_extended.gui.bus.isolated"
                : "gtlite_extended.gui.bus.global"), 4210752);

        // 物品缓存：3 行可滚动 AE 网格
        builder.label(8, 40, "gtlite_extended.gui.buffer_items");
        builder.widget(new AEItemGridWidget(8, 50, 3, this.itemBuffer));

        // 流体缓存：3 行可滚动 AE 网格
        builder.label(8, 108, "gtlite_extended.gui.buffer_fluids");
        builder.widget(new AEFluidGridWidget(8, 118, 3, this.fluidBuffer));

        builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 7, 176);
        return builder.build(this.getHolder(), entityPlayer);
    }

    // ==================== 缓存持久化 ====================

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setTag(NBT_ITEM_BUFFER, this.itemBuffer.serializeNBT());
        data.setTag(NBT_FLUID_BUFFER, this.fluidBuffer.serializeNBT());
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        if (data.hasKey(NBT_ITEM_BUFFER, 9)) {
            this.itemBuffer.deserializeNBT((NBTTagList) data.getTag(NBT_ITEM_BUFFER));
        }
        if (data.hasKey(NBT_FLUID_BUFFER, 9)) {
            this.fluidBuffer.deserializeNBT((NBTTagList) data.getTag(NBT_FLUID_BUFFER));
        }
    }

    // ==================== 两个“无限接收” handler ====================

    private class ItemSink implements IItemHandlerModifiable, INotifiableHandler {

        private final List<MetaTileEntity> notifiableEntities = new ArrayList<>();

        @Override
        public void setStackInSlot(int slot, ItemStack stack) {
            if (stack != null && !stack.isEmpty()) {
                insertItem(slot, stack, false);
            }
        }

        @Override
        public int getSlots() {
            return 1;
        }

        @Override
        public ItemStack getStackInSlot(int slot) {
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if (stack == null || stack.isEmpty()) {
                return ItemStack.EMPTY;
            }
            if (!simulate) {
                itemBuffer.add(AEItemStack.fromItemStack(stack.copy()));
                markDirty();
                notifyController();
            }
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            return ItemStack.EMPTY;
        }

        @Override
        public int getSlotLimit(int slot) {
            return Integer.MAX_VALUE;
        }

        @Override
        public void addNotifiableMetaTileEntity(MetaTileEntity metaTileEntity) {
            this.notifiableEntities.add(metaTileEntity);
        }

        @Override
        public void removeNotifiableMetaTileEntity(MetaTileEntity metaTileEntity) {
            this.notifiableEntities.remove(metaTileEntity);
        }

        private void clearNotifiables() {
            this.notifiableEntities.clear();
        }

        private void notifyController() {
            for (MetaTileEntity metaTileEntity : this.notifiableEntities) {
                if (metaTileEntity != null && metaTileEntity.isValid()) {
                    addToNotifiedList(metaTileEntity, this, true);
                }
            }
        }
    }

    private class FluidSink implements IFluidTank, INotifiableHandler {

        private final List<MetaTileEntity> notifiableEntities = new ArrayList<>();

        @Override
        public FluidStack getFluid() {
            return null;
        }

        @Override
        public int getFluidAmount() {
            return 0;
        }

        @Override
        public int getCapacity() {
            return Integer.MAX_VALUE;
        }

        @Override
        public FluidTankInfo getInfo() {
            return null;
        }

        @Override
        public int fill(FluidStack resource, boolean doFill) {
            if (resource == null || resource.getFluid() == null || resource.amount <= 0) {
                return 0;
            }
            if (doFill) {
                fluidBuffer.add(AEFluidStack.fromFluidStack(resource));
                markDirty();
                notifyController();
            }
            return resource.amount;
        }

        @Override
        public FluidStack drain(int maxDrain, boolean doDrain) {
            return null;
        }

        @Override
        public void addNotifiableMetaTileEntity(MetaTileEntity metaTileEntity) {
            this.notifiableEntities.add(metaTileEntity);
        }

        @Override
        public void removeNotifiableMetaTileEntity(MetaTileEntity metaTileEntity) {
            this.notifiableEntities.remove(metaTileEntity);
        }

        private void clearNotifiables() {
            this.notifiableEntities.clear();
        }

        private void notifyController() {
            for (MetaTileEntity metaTileEntity : this.notifiableEntities) {
                if (metaTileEntity != null && metaTileEntity.isValid()) {
                    addToNotifiedList(metaTileEntity, this, true);
                }
            }
        }
    }
}
