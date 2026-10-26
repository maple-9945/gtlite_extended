package com.gtlite.extended.common.machine;

import com.gtlite.extended.common.recipe.PetrochemicalRecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

/**
 * 石化工厂：3x3x3 不锈钢机械方块外壳，控制器放在最外层正面中间。
 * 与矿石处理机共用同一套芯片逻辑（{@link AdvancedModeRecipeLogic}）。
 */
public class MetaTileEntityPetrochemicalPlant extends RecipeMapMultiblockController implements IChipUpgradable {

    private boolean losslessOverclock = false;
    private boolean advancedMode = false;

    public MetaTileEntityPetrochemicalPlant(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, PetrochemicalRecipeMaps.PETROCHEMICAL);
        this.recipeMapWorkable = new PetrochemicalRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity holder) {
        return new MetaTileEntityPetrochemicalPlant(metaTileEntityId);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart part) {
        return Textures.CLEAN_STAINLESS_STEEL_CASING;
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX")
                .aisle("XXX", "XXX", "XXX")
                .aisle("XXX", "XSX", "XXX") // S 放在最后一排的中间，这是机器的正面
                .where('S', selfPredicate())
                .where('X', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN))
                        .setMinGlobalLimited(20)
                        .or(autoAbilities()))
                .build();
    }

    @Override
    public boolean isLosslessOverclock() {
        return losslessOverclock;
    }

    @Override
    public boolean isAdvancedMode() {
        return advancedMode;
    }

    @Override
    public void setLosslessOverclock(boolean value) {
        this.losslessOverclock = value;
        markDirty();
    }

    @Override
    public void setAdvancedMode(boolean value) {
        if (value && !losslessOverclock) {
            return;
        }
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
