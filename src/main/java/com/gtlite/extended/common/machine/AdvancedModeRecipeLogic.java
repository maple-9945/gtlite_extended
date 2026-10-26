package com.gtlite.extended.common.machine;

import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.logic.OverclockingLogic;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;

/**
 * 芯片效果公共父类：所有使用“无损超频 / 高级模式”芯片的多方块机器共用这套逻辑。
 *
 * <ul>
 *     <li>无损超频：{@link #getOverclockingDurationFactor()} 返回完美超频因子；</li>
 *     <li>高级模式：并行上限 {@link Integer#MAX_VALUE}、并行电压 {@link Long#MAX_VALUE}、
 *         {@link #hasEnoughPower(long, int)} 恒真、1 tick + 零耗电；</li>
 *     <li>单 tick 暴食：{@link #updateWorkable()} 在 GT 正常跑一次之后，
 *         继续 {@code findRecipe → prepareRecipe → completeRecipe}，把剩余输入在本 tick 内吃完
 *         （硬上限 {@value #MAX_BURST_LOOPS} 次，且“没消耗输入就退出”防死循环）。</li>
 * </ul>
 *
 * 子类只需要实现 {@link #isLosslessOverclock()} / {@link #isAdvancedMode()} 两个状态查询。
 */
public abstract class AdvancedModeRecipeLogic extends MultiblockRecipeLogic {

    /** 高级模式单 tick 最多连续结算的配方条数。 */
    private static final int MAX_BURST_LOOPS = 100;

    protected AdvancedModeRecipeLogic(RecipeMapMultiblockController machine) {
        super(machine);
    }

    /** 是否已解锁无损超频（芯片 A）。 */
    protected abstract boolean isLosslessOverclock();

    /** 是否已开启高级模式（芯片 B，需先解锁无损超频）。 */
    protected abstract boolean isAdvancedMode();

    @Override
    protected double getOverclockingDurationFactor() {
        return isLosslessOverclock()
                ? OverclockingLogic.PERFECT_DURATION_FACTOR
                : super.getOverclockingDurationFactor();
    }

    @Override
    public int getParallelLimit() {
        return isAdvancedMode() ? Integer.MAX_VALUE : super.getParallelLimit();
    }

    @Override
    protected long getMaxParallelVoltage() {
        return isAdvancedMode() ? Long.MAX_VALUE : super.getMaxParallelVoltage();
    }

    @Override
    protected boolean hasEnoughPower(long EUt, int duration) {
        return isAdvancedMode() || super.hasEnoughPower(EUt, duration);
    }

    @Override
    protected void setupRecipe(Recipe recipe) {
        super.setupRecipe(recipe);
        if (isAdvancedMode()) {
            this.maxProgressTime = 1;
            this.recipeEUt = 0;
        }
    }

    @Override
    public void updateWorkable() {
        super.updateWorkable();
        if (isAdvancedMode() && isWorkingEnabled()) {
            processAllInputsThisTick();
        }
    }

    /** 高级模式的单 tick 暴食循环。 */
    private void processAllInputsThisTick() {
        IItemHandlerModifiable inputs = getInputInventory();
        IMultipleTankHandler inputTanks = getInputTank();
        if (inputs == null) {
            return;
        }

        for (int loop = 0; loop < MAX_BURST_LOOPS; loop++) {
            if (maxProgressTime > 0) {
                completeRecipe();
                continue;
            }
            if (!hasAnyItem(inputs) && isEmptyTank(inputTanks)) {
                return;
            }
            Recipe recipe = findRecipe(Long.MAX_VALUE, inputs, inputTanks);
            if (recipe == null) {
                return;
            }

            long itemsBefore = countItems(inputs);
            long fluidBefore = countFluids(inputTanks);
            if (!prepareRecipe(recipe)) {
                return;
            }
            completeRecipe();

            if (countItems(inputs) >= itemsBefore && countFluids(inputTanks) >= fluidBefore) {
                // 一次都没消耗输入，防止非消耗型配方无限刷产出
                return;
            }
        }
    }

    private static boolean hasAnyItem(IItemHandlerModifiable handler) {
        for (int slot = 0; slot < handler.getSlots(); slot++) {
            ItemStack stack = handler.getStackInSlot(slot);
            if (stack != null && !stack.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static long countItems(IItemHandlerModifiable handler) {
        long total = 0;
        for (int slot = 0; slot < handler.getSlots(); slot++) {
            ItemStack stack = handler.getStackInSlot(slot);
            if (stack != null && !stack.isEmpty()) {
                total += stack.getCount();
            }
        }
        return total;
    }

    private static boolean isEmptyTank(IMultipleTankHandler tanks) {
        return countFluids(tanks) <= 0;
    }

    private static long countFluids(IMultipleTankHandler tanks) {
        if (tanks == null) {
            return 0;
        }
        long total = 0;
        for (IMultipleTankHandler.ITankEntry tank : tanks.getFluidTanks()) {
            net.minecraftforge.fluids.FluidStack fluid = tank.getFluid();
            if (fluid != null) {
                total += fluid.amount;
            }
        }
        return total;
    }
}
