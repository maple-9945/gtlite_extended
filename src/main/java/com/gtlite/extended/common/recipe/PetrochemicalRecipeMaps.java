package com.gtlite.extended.common.recipe;

import com.gtlite.extended.GTLiteExtended;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.ModularUI;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.category.GTRecipeCategory;
import gregtech.api.recipes.ui.RecipeMapUI;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;

/**
 * 石化工厂配方池：4 条“基础油 + 蒸汽 → 全套石油化工产物”的超级配方。
 * 统一 10 tick、192 EU/t。
 *
 * <p>这 4 条配方不是把油“直接映射”成产物，而是把 GTCEu 里整套石油化工流程做<b>数学等效合并</b>，
 * 所有产率都取自 GTCEu 自己的配方数据（{@code gregtech.loaders.recipe.chemistry.PetrochemRecipes}），
 * 不在运行时遍历任何 RecipeMap：</p>
 *
 * <ol>
 *     <li><b>蒸馏塔（一次）</b>：1000mB 基础油按 GT 的蒸馏塔配方分成
 *         含硫重油（SulfuricHeavyFuel）、含硫轻油（SulfuricLightFuel）、
 *         含硫石脑油（SulfuricNaphtha）、含硫炼油气（SulfuricGas）四股馏分；</li>
 *     <li><b>化学反应釜（加氢脱硫）</b>：<b>四股馏分全部</b>加氢脱硫，得到对应的
 *         重油/轻油/石脑油/炼油气，同时生成硫化氢（H2S）。
 *         GT 的配比是：重油 8000 + 2000H2 → 8000 + 1000H2S；
 *         轻油/石脑油 12000 + 2000H2 → 12000 + 1000H2S；
 *         炼油气 16000 + 2000H2 → 16000 + 1000H2S；</li>
 *     <li><b>石油裂化机（重度蒸汽裂化）</b>：脱硫后的<b>四股馏分全部</b>进裂化机，
 *         分别得到重度蒸汽裂化重油 / 轻油 / 石脑油 / 炼油气
 *         （GT 配方都是 1000mB 馏分 + 1000mB 蒸汽 → 1000mB 裂化产物）；</li>
 *     <li><b>蒸馏塔（二次）</b>：四种裂化产物分别再蒸馏（GT 各自的蒸馏数据），
 *         得到重油、轻油、石脑油、甲苯、苯、丁烯、丁二烯、丙烷、丙烯、乙烷、乙烯、甲烷、氦气
 *         以及少量碳粉。这一步的产物就是最终产物，不再回流裂化机；</li>
 *     <li><b>电解（硫化氢）</b>：GT 对 H2S 的电解配方（由
 *         {@code DecompositionRecipeHandler} 按 H 2 + S 1 自动生成）为
 *         1000mB H2S → 2000mB 氢气 + 1 个硫粉，氢气 100% 回补给加氢脱硫。</li>
 * </ol>
 *
 * <p><b>氢平衡（数学上恰好闭合）：</b>加氢脱硫每产生 1000mB H2S 就消耗 2000mB 氢气，
 * 而电解 1000mB H2S 又恰好返还 2000mB 氢气，所以四种油的净氢差额都是 <b>0</b>：
 * 配方既不需要额外输入氢气，也不会副产氢气。代码里仍然保留了
 * {@link #applyHydrogenBalance} 这条通路：一旦以后产率数据变化导致缺氢/余氢，
 * 缺氢就加氢气输入、余氢就加氢气输出。</p>
 *
 * <p>蒸汽固定消耗 1000mB：按你的要求输入恒为“1000mB 油 + 1000mB 蒸汽”，
 * 裂化用的蒸汽不再单独做物料平衡（作为流程蒸汽一次性消耗）。</p>
 */
public final class PetrochemicalRecipeMaps {

    /** 统一参数：10 tick、192 EU/t。 */
    public static final int DURATION = 10;
    public static final long EUT = 192L;

    /** 基础油 / 蒸汽的输入量。 */
    public static final int INPUT_AMOUNT = 1000;

    /**
     * 配方表容量（必须精确等于配方实际用到的槽位，否则 JEI 里会多出空格子）：
     * 物品输入 0（原料全是流体）、流体输入 2（基础油 + 蒸汽）、
     * 物品输出 2（硫粉、碳粉）、流体输出 13（合并后的 13 种产物流体）。
     */
    public static final int MAX_FLUID_OUTPUTS = 13;

    private static final int MAX_INPUTS = 0;
    private static final int MAX_OUTPUTS = 2;
    private static final int MAX_FLUID_INPUTS = 2;

    /**
     * 合并后的 13 种产物流体，顺序即产率数组的顺序（主产物在前）：
     * 重油、精制轻质燃料、石脑油、甲苯、苯、丁烯、丁二烯、丙烷、丙烯、乙烷、乙烯、甲烷、氦气。
     */
    private static final Material[] OUTPUT_MATERIALS = {
            Materials.HeavyFuel,
            Materials.LightFuel,
            Materials.Naphtha,
            Materials.Toluene,
            Materials.Benzene,
            Materials.Butene,
            Materials.Butadiene,
            Materials.Propane,
            Materials.Propene,
            Materials.Ethane,
            Materials.Ethylene,
            Materials.Methane,
            Materials.Helium
    };

    /** 材料没有流体时退回的 FluidRegistry 名字（顺序与 {@link #OUTPUT_MATERIALS} 一一对应）。 */
    private static final String[] OUTPUT_FLUID_NAMES = {
            "heavy_fuel", "light_fuel", "naphtha", "toluene", "benzene", "butene", "butadiene",
            "propane", "propene", "ethane", "ethylene", "methane", "helium"
    };

    /**
     * 每种油合并后的产率（单位 mB，与 {@link #OUTPUT_MATERIALS} 一一对应）。
     *
     * <p>数值由 GT 数据换算得到，例如石油：
     * 一次蒸馏 1000mB 油 → 300 含硫重油 + 1000 含硫轻油 + 400 含硫石脑油 + 1200 含硫炼油气
     * （GT 配方 50mB 油 → 15/50/20/60）；
     * 四股馏分全部加氢脱硫后再全部重度蒸汽裂化，裂化产物按 GT 各自的二次蒸馏数据拆分：
     * 裂化重油 ×0.3 得 30 轻油、37.5 石脑油、24 甲苯、120 苯、24 丁烯、15 丁二烯、3 丙烷、30 丙烯、4.5 乙烷、45 乙烯、45 甲烷；
     * 裂化轻油 ×1.0 得 50 重油、100 石脑油、30 甲苯、150 苯、65 丁烯、50 丁二烯、50 丙烷、250 丙烯、50 乙烷、250 乙烯、250 甲烷；
     * 裂化石脑油 ×0.4 得 10 重油、20 轻油、8 甲苯、40 苯、20 丁烯、20 丁二烯、6 丙烷、120 丙烯、26 乙烷、200 乙烯、200 甲烷；
     * 裂化炼油气 ×1.2 得 9.6 丙烯、54 乙烷、110.4 乙烯、1221.6 甲烷、24 氦气；
     * 合并后四舍五入。</p>
     */
    private static final int[] OIL_OUTPUTS = {
            60, 50, 138, 62, 310, 109, 85, 59, 410, 135, 605, 1717, 24
    };

    /** 原油（RawOil）：一次蒸馏 1000mB → 100/500/1500/600，其余流程同上。 */
    private static final int[] RAW_OIL_OUTPUTS = {
            63, 85, 63, 53, 265, 116, 105, 49, 590, 151, 945, 1501, 12
    };

    /** 重油（OilHeavy）：一次蒸馏 1000mB → 2500/450/150/600，其余流程同上。 */
    private static final int[] HEAVY_OIL_OUTPUTS = {
            26, 258, 358, 217, 1083, 237, 155, 50, 412, 97, 618, 1173, 12
    };

    /** 轻油（OilLight）：一次蒸馏 1000mB → 66.7/133.3/200/1600，其余流程同上。 */
    private static final int[] LIGHT_OIL_OUTPUTS = {
            12, 17, 22, 13, 67, 24, 20, 10, 113, 93, 291, 1772, 32
    };

    /**
     * 每条配方的氢差额（单位 mB）：正数 = 缺氢（要加氢气输入），负数 = 余氢（要加氢气输出）。
     *
     * <p>按 GT 数据计算的结果如下（全部恰好为 0）：
     * <ul>
     *     <li>石油：加氢需 458.3，H2S 229.2 → 电解返还 458.3 → 0；</li>
     *     <li>原油：加氢需 433.3，H2S 216.7 → 电解返还 433.3 → 0；</li>
     *     <li>重油：加氢需 800.0，H2S 400.0 → 电解返还 800.0 → 0；</li>
     *     <li>轻油：加氢需 272.2，H2S 136.1 → 电解返还 272.2 → 0。</li>
     * </ul>
     * 即 GT 的加氢脱硫与 H2S 电解在氢上完全闭环，所以 4 条配方都不额外进出氢气。
     * 若以后产率数据被改动，只要把这里的差额改掉，{@link #applyHydrogenBalance} 会自动
     * 加上氢气输入或输出。</p>
     */
    private static final int OIL_HYDROGEN_BALANCE = 0;
    private static final int RAW_OIL_HYDROGEN_BALANCE = 0;
    private static final int HEAVY_OIL_HYDROGEN_BALANCE = 0;
    private static final int LIGHT_OIL_HYDROGEN_BALANCE = 0;

    /** 电解 H2S 的硫粉副产物：每 1000mB H2S → 1 个硫粉，合并配方里按 100% 给 1 个。 */
    private static final int SULFUR_DUST = 1;

    /** 裂化油蒸馏的碳粉几率副产物：合并配方里按 100% 给 1 个。 */
    private static final int CARBON_DUST = 1;

    public static final RecipeMap<SimpleRecipeBuilder> PETROCHEMICAL = new RecipeMap<>(
            "gtlite_extended:petrochemical",
            new SimpleRecipeBuilder(),
            PetrochemicalUI::new,
            MAX_INPUTS, MAX_OUTPUTS, MAX_FLUID_INPUTS, MAX_FLUID_OUTPUTS);

    /**
     * 石化工厂专用界面：GT 默认的 {@link RecipeMapUI} 会把 13 个流体输出按 3 列向下排到 y≈132，
     * 而 JEI 配方分类的实际高度只有 {@code 166 * 2/3 = 110}，最后几个流体格子会画到分类外面、
     * 看起来像是“显示错乱”。这里改成紧凑布局：
     * <ul>
     *     <li>输入侧：没有物品输入，只有 2 个流体槽（基础油 + 蒸汽），照默认的“纯流体”位置摆放；</li>
     *     <li>输出侧：2 个物品输出（硫粉/碳粉）+ 13 个流体输出合成一个 3 列网格，
     *         从 y=8 开始一共 5 行，最底一格 98，完全落在 110 高的 JEI 分类内。</li>
     * </ul>
     */
    private static final class PetrochemicalUI extends RecipeMapUI<RecipeMap<SimpleRecipeBuilder>> {

        @SuppressWarnings("unchecked")
        private PetrochemicalUI(RecipeMap<?> recipeMap) {
            // 这张配方表的 builder 就是 SimpleRecipeBuilder，强转是安全的（RecipeMapUIFunction 只给 RecipeMap<?>）
            super((RecipeMap<SimpleRecipeBuilder>) recipeMap, true, true, true, true, false);
        }

        @Override
        protected void addInventorySlotGroup(ModularUI.Builder builder, IItemHandlerModifiable itemHandler,
                                             FluidTankList fluidHandler, boolean isOutputs, int yOffset) {
            if (!isOutputs) {
                // 输入：0 个物品槽、只有 2 个流体槽，交给 GT 默认的“纯流体”布局
                super.addInventorySlotGroup(builder, itemHandler, fluidHandler, false, yOffset);
                return;
            }

            // 输出：物品 + 流体合并成 3 列网格，整体压进 JEI 分类的 110 高区域
            int itemCount = itemHandler.getSlots();
            int fluidCount = fluidHandler.getTanks();
            int columns = 3;
            int startX = 106;
            int startY = 8 + yOffset;
            for (int i = 0; i < itemCount; i++) {
                addSlot(builder, startX + 18 * (i % columns), startY + 18 * (i / columns),
                        i, itemHandler, fluidHandler, false, true);
            }
            for (int i = 0; i < fluidCount; i++) {
                int cell = itemCount + i;
                addSlot(builder, startX + 18 * (cell % columns), startY + 18 * (cell / columns),
                        i, itemHandler, fluidHandler, true, true);
            }
        }
    }

    /** 与配方表配套的 JEI 分类（配方表构造时自动创建的 primary 分类）。 */
    public static final GTRecipeCategory CATEGORY = PETROCHEMICAL.getPrimaryRecipeCategory();

    private static boolean initialized;

    private PetrochemicalRecipeMaps() {
    }

    /** 注册 4 条超级配方。由 {@code GTLiteExtended#preInit} 调用。 */
    public static synchronized void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        register("raw_oil", oil("raw_oil", Materials.RawOil), RAW_OIL_OUTPUTS, RAW_OIL_HYDROGEN_BALANCE);
        register("oil", oil("oil", Materials.Oil), OIL_OUTPUTS, OIL_HYDROGEN_BALANCE);
        register("oil_heavy", oil("oil_heavy", Materials.OilHeavy), HEAVY_OIL_OUTPUTS, HEAVY_OIL_HYDROGEN_BALANCE);
        register("oil_light", oil("oil_light", Materials.OilLight), LIGHT_OIL_OUTPUTS, LIGHT_OIL_HYDROGEN_BALANCE);

        GTLiteExtended.LOGGER.info("[GTLite Extended] petrochemical recipes: {} ({} tick, {} EU/t)",
                recipeCount(), DURATION, EUT);
    }

    public static int recipeCount() {
        return PETROCHEMICAL.getRecipeList().size();
    }

    /**
     * 注册一条“油 + 蒸汽 → 全套合并产物”的超级配方。
     *
     * @param name           日志用的油名
     * @param baseOil        1000mB 基础油
     * @param amounts        13 种产物流体的数量（mB）
     * @param hydrogenBalance 氢差额：>0 加氢气输入，<0 加氢气输出，0 不处理
     */
    private static void register(String name, FluidStack baseOil, int[] amounts, int hydrogenBalance) {
        if (baseOil == null) {
            GTLiteExtended.LOGGER.error("[GTLite Extended] 找不到基础油流体 {}，跳过石化配方", name);
            return;
        }
        FluidStack steam = materialFluid(Materials.Steam, INPUT_AMOUNT);
        if (steam == null) {
            steam = registryFluid("steam", INPUT_AMOUNT);
        }
        if (steam == null) {
            GTLiteExtended.LOGGER.error("[GTLite Extended] 找不到蒸汽流体，跳过石化配方 {}", name);
            return;
        }
        if (amounts.length != OUTPUT_MATERIALS.length) {
            GTLiteExtended.LOGGER.error("[GTLite Extended] {} 的产率数组长度 {} 与产物流体数 {} 不一致，跳过石化配方",
                    name, amounts.length, OUTPUT_MATERIALS.length);
            return;
        }

        FluidStack[] outputs = new FluidStack[OUTPUT_MATERIALS.length];
        for (int i = 0; i < OUTPUT_MATERIALS.length; i++) {
            if (amounts[i] <= 0) {
                continue;
            }
            outputs[i] = materialFluid(OUTPUT_MATERIALS[i], amounts[i]);
            if (outputs[i] == null) {
                outputs[i] = registryFluid(OUTPUT_FLUID_NAMES[i], amounts[i]);
            }
            if (outputs[i] == null) {
                GTLiteExtended.LOGGER.error("[GTLite Extended] 找不到产物流体 {}，跳过石化配方 {}",
                        OUTPUT_FLUID_NAMES[i], name);
                return;
            }
        }

        SimpleRecipeBuilder builder = PETROCHEMICAL.recipeBuilder()
                .fluidInputs(baseOil, steam)
                .duration(DURATION)
                .EUt(EUT);

        // 氢平衡：缺氢补到输入，余氢放到输出（GT 数据下四种油的差额都是 0）
        applyHydrogenBalance(builder, hydrogenBalance);

        // 合并后的主产物：13 种流体一次输出
        for (FluidStack output : outputs) {
            if (output != null) {
                builder.fluidOutputs(output);
            }
        }

        // 副产物：H2S 电解的硫粉 + 裂化油蒸馏的碳粉
        addDust(builder, Materials.Sulfur, SULFUR_DUST, name);
        addDust(builder, Materials.Carbon, CARBON_DUST, name);

        builder.buildAndRegister();

        GTLiteExtended.LOGGER.info(
                "[GTLite Extended] 石化超级配方 {}: 1000mB 油 + 1000mB 蒸汽 → {} 种流体 + 硫粉 {} + 碳粉 {}，氢差额 {}mB",
                name, countNonNull(outputs), SULFUR_DUST, CARBON_DUST, hydrogenBalance);
    }

    /** 氢平衡：正数差额补氢气输入，负数差额产氢气输出（0 不处理）。 */
    private static void applyHydrogenBalance(SimpleRecipeBuilder builder, int hydrogenBalance) {
        if (hydrogenBalance == 0) {
            return;
        }
        FluidStack hydrogen = materialFluid(Materials.Hydrogen, Math.abs(hydrogenBalance));
        if (hydrogen == null) {
            hydrogen = registryFluid("hydrogen", Math.abs(hydrogenBalance));
        }
        if (hydrogen == null) {
            GTLiteExtended.LOGGER.error("[GTLite Extended] 氢平衡需要调整 {}mB 氢气，但找不到氢气流体",
                    Math.abs(hydrogenBalance));
            return;
        }
        if (hydrogenBalance > 0) {
            builder.fluidInputs(hydrogen);
        } else {
            builder.fluidOutputs(hydrogen);
        }
    }

    /** 追加一个粉状副产物（材料没有粉物品时只告警，不影响主配方）。 */
    private static void addDust(SimpleRecipeBuilder builder, Material material, int amount, String recipeName) {
        if (material == null || amount <= 0) {
            return;
        }
        ItemStack dust = OreDictUnifier.get(OrePrefix.dust, material, amount);
        if (dust.isEmpty()) {
            GTLiteExtended.LOGGER.warn("[GTLite Extended] 石化配方 {} 的副产物 {} 没有粉物品，已跳过",
                    recipeName, material.getName());
            return;
        }
        builder.outputs(dust);
    }

    private static int countNonNull(FluidStack[] stacks) {
        int count = 0;
        for (FluidStack stack : stacks) {
            if (stack != null) {
                count++;
            }
        }
        return count;
    }

    // ==================== 输入流体 ====================

    /** 取基础油：优先 GT 材料流体，退回 FluidRegistry 名字。 */
    private static FluidStack oil(String registryName, Material material) {
        FluidStack stack = materialFluid(material, INPUT_AMOUNT);
        return stack != null ? stack : registryFluid(registryName, INPUT_AMOUNT);
    }

    // ==================== 流体工具 ====================

    private static FluidStack materialFluid(Material material, int amount) {
        if (material == null) {
            return null;
        }
        FluidStack stack = material.getFluid(amount);
        return stack != null && stack.getFluid() != null ? stack : null;
    }

    private static FluidStack registryFluid(String name, int amount) {
        Fluid fluid = FluidRegistry.getFluid(name);
        return fluid == null ? null : new FluidStack(fluid, amount);
    }
}
