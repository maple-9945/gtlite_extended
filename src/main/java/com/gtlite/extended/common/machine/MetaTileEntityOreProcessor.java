package com.gtlite.extended.common.machine;

import com.gtlite.extended.common.recipe.OreProcessingRecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

/**
 * 矿石处理多方块机器：最简 3x3x3 铁块外壳（端口用 autoAbilities）。
 * 控制器 S 放在最外层正面的中间，避免被外壳包在正中心。
 * 默认有损超频；芯片 A 开启无损超频，芯片 B 在 A 基础上开启零耗电 + 无限并行。
 */
public class MetaTileEntityOreProcessor extends RecipeMapMultiblockController {

    private boolean losslessOverclock = false;
    private boolean advancedMode = false;

    public MetaTileEntityOreProcessor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, OreProcessingRecipeMaps.ORE_PROCESSING);
        this.recipeMapWorkable = new OreProcessorRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity holder) {
        return new MetaTileEntityOreProcessor(metaTileEntityId);
    }

    @Override
    public gregtech.client.renderer.ICubeRenderer getBaseTexture(IMultiblockPart part) {
        return gregtech.client.renderer.texture.Textures.SOLID_STEEL_CASING;
    }

    @Override
    protected BlockPattern createStructurePattern() {
        // 3x3x3 铁块外壳。S = 控制器，硬编码在最后一个 aisle 切片的中间：
        // 最后一个切片就是机器的正面，控制器在外表面，JEI 预览和实际成型时玩家都站在这面交互。
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX")
                .aisle("XXX", "XXX", "XXX")
                .aisle("XXX", "XSX", "XXX") // S 放在最后一排的中间，这是机器的正面
                .where('S', selfPredicate())
                .where('X', states(Blocks.IRON_BLOCK.getDefaultState())
                        .setMinGlobalLimited(20)
                        .or(autoAbilities()))
                .build();
    }

    public boolean isLosslessOverclock() {
        return losslessOverclock;
    }

    public boolean isAdvancedMode() {
        return advancedMode;
    }

    /** 芯片 A：有损 → 无损超频 */
    public void setLosslessOverclock(boolean value) {
        this.losslessOverclock = value;
        markDirty();
    }

    /** 芯片 B：需先有无损超频 */
    public void setAdvancedMode(boolean value) {
        if (value && !losslessOverclock) return;
        this.advancedMode = value;
        markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("LosslessOverclock", losslessOverclock);
        data.setBoolean("AdvancedMode", advancedMode);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        losslessOverclock = data.getBoolean("LosslessOverclock");
        advancedMode = data.getBoolean("AdvancedMode");
    }
}
