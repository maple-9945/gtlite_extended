package com.gtlite.extended.common.machine;

import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.logic.OverclockingLogic;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;

/**
 * 矿石处理机的超频 / 高级模式逻辑。
 *
 * <p>高级模式（芯片 B，需先开启无损超频）下要打的补丁：</p>
 * <ul>
 *     <li><b>并行上限</b>：{@link #getParallelLimit()} 直接返回 {@link Integer#MAX_VALUE}，
 *         不再走 GT 默认的“能源仓电压 / 配方 EUt”限制；</li>
 *     <li><b>并行电压</b>：{@link #getMaxParallelVoltage()} 返回 {@link Long#MAX_VALUE}，
 *         让 {@code ParallelLogic.doParallelRecipes} 里 {@code maxVoltage / EUt} 这一项失效；</li>
 *     <li><b>电力检查</b>：{@link #hasEnoughPower(long, int)} 恒为 true，没有能源仓也能跑；</li>
 *     <li><b>强制接管</b>：{@link #setupRecipe(Recipe)} 里把 {@code maxProgressTime} 压到 1、
 *         {@code recipeEUt} 归零，1 tick 出全部产物且零耗电；</li>
 *     <li><b>单 tick 暴食</b>：{@link #updateWorkable()} 在 GT 正常跑完一次之后，
 *         继续循环 {@code findRecipe → prepareRecipe → completeRecipe}，
 *         把总线里剩下的不同种类矿石在本 tick 内全部结算掉（上限 {@value #MAX_BURST_LOOPS} 次）。</li>
 * </ul>
 *
 * <p>为什么挂在 {@code updateWorkable()} 而不是 {@code checkRecipe()}：{@code checkRecipe(Recipe)}
 * 只做洁净室/维度校验，它在每次寻找配方前都会被调用；在那儿开循环会和 GT 自己的
 * “搜索一次 + 准备一次”流程叠加，造成同一条配方重复消耗输入。{@code updateWorkable()}
 * 是控制器 {@code updateFormedValid()} 每 tick 调用的唯一入口，在这里先让 GT 正常跑一次、
 * 再把剩余输入循环吃完，既不会重复结算，也不会和 GT 的进度逻辑打架。</p>
 *
 * <p>防死循环：循环硬上限 {@link #MAX_BURST_LOOPS}；每次结算后都会比较输入物品数量，
 * 没消耗任何输入就立刻退出（避免“非消耗型配方”被无限刷产出）；
 * 输出空间不足或没有匹配配方时也会立刻退出，交给下一个 tick。</p>
 */
public class OreProcessorRecipeLogic extends MultiblockRecipeLogic {

    /** 高级模式单 tick 最多连续结算的配方条数，防止异常情况下卡死服务器。 */
    private static final int MAX_BURST_LOOPS = 100;

    private final MetaTileEntityOreProcessor machine;

    public OreProcessorRecipeLogic(MetaTileEntityOreProcessor machine) {
        super(machine);
        this.machine = machine;
    }

    @Override
    protected double getOverclockingDurationFactor() {
        return machine.isLosslessOverclock()
                ? OverclockingLogic.PERFECT_DURATION_FACTOR
                : super.getOverclockingDurationFactor();
    }

    /**
     * 拦截并行上限：高级模式直接拉满。
     * 不调用 super，避免被能源仓电压等级或配方 EUt 卡住。
     */
    @Override
    public int getParallelLimit() {
        return machine.isAdvancedMode() ? Integer.MAX_VALUE : super.getParallelLimit();
    }

    /**
     * 拦截“并行电压”：高级模式给一个极大值，GT 的 maxVoltage / EUt 不再限制并行数。
     * （{@code ParallelLogic} 内部会用 safeCastLongToInt 把结果截到 Integer.MAX_VALUE，不会溢出成负数。）
     */
    @Override
    protected long getMaxParallelVoltage() {
        return machine.isAdvancedMode() ? Long.MAX_VALUE : super.getMaxParallelVoltage();
    }

    /** 无视能量检查：高级模式永远算“电够用”。 */
    @Override
    protected boolean hasEnoughPower(long EUt, int duration) {
        return machine.isAdvancedMode() || super.hasEnoughPower(EUt, duration);
    }

    /**
     * 高级模式接管配方：1 tick 完成 + 零耗电。
     *
     * <p>此时 {@code prepareRecipe} 已经把并行数乘进产物里（{@code parallelRecipesPerformed} 就是实际并行数），
     * 所以这里只压时间、清耗电，产物数量保持 GT 算好的结果。</p>
     */
    @Override
    protected void setupRecipe(Recipe recipe) {
        super.setupRecipe(recipe);
        if (machine.isAdvancedMode()) {
            this.maxProgressTime = 1;
            this.recipeEUt = 0;
        }
    }

    /**
     * 每 tick 的驱动入口（{@code RecipeMapMultiblockController#updateFormedValid} 调用）。
     * 先让 GT 正常跑一次，再在高级模式下把总线里剩下的矿石一次性吃完。
     */
    @Override
    public void updateWorkable() {
        super.updateWorkable();
        if (machine.isAdvancedMode() && isWorkingEnabled()) {
            processAllInputsThisTick();
        }
    }

    /**
     * 高级模式的“吞天噬地”循环：只要总线里还有能匹配配方的物品，
     * 就在本 tick 内继续 {@code findRecipe → prepareRecipe → completeRecipe}。
     *
     * <p>每次循环都会消耗输入并把产物直接写进输出总线，不同种类的矿石会依次匹配到各自的配方，
     * 所以一 tick 内可以处理多种矿物。</p>
     */
    private void processAllInputsThisTick() {
        IItemHandlerModifiable inputs = getInputInventory();
        if (inputs == null) {
            return;
        }
        IMultipleTankHandler inputTanks = getInputTank();

        for (int loop = 0; loop < MAX_BURST_LOOPS; loop++) {
            // 上一步可能留下“已经准备好、还没结算”的配方（progress=1），先结算掉再继续
            if (maxProgressTime > 0) {
                completeRecipe();
                continue;
            }
            if (!hasAnyItem(inputs)) {
                return;
            }

            // 高级模式彻底无视能源仓：查询配方时直接给一个极大电压
            Recipe recipe = findRecipe(Long.MAX_VALUE, inputs, inputTanks);
            if (recipe == null) {
                return;
            }

            long itemsBefore = countItems(inputs);
            if (!prepareRecipe(recipe)) {
                // 输出满了 / 水不够 / 输入不足：留给下一个 tick
                return;
            }
            // 立即结算：产物直接进输出总线，不需要等进度条
            completeRecipe();

            if (countItems(inputs) >= itemsBefore) {
                // 一次都没消耗输入（非消耗型配方），立刻停手，防止无限刷产出
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
}
