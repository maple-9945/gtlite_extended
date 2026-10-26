package com.gtlite.extended.common.recipe;

import com.gtlite.extended.GTLiteExtended;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.category.GTRecipeCategory;
import gregtech.api.recipes.ui.RecipeMapUI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * 石化工厂配方池：4 条“基础油 + 辅助流体 → 12 种化工流体”的配方。
 * 统一 1 tick、1920 EU/t（EV 级）。
 *
 * <p>输入 1B 基础油 + 1B 辅助流体（默认水蒸气 Steam）。12 种产物流体依次为：
 * 甲苯、苯、辛烷、丁烷、丁烯、丁二烯、丙烷、丙烯、乙烷、乙烯、甲烷、氦气。</p>
 */
public final class PetrochemicalRecipeMaps {

    public static final int DURATION = 1;
    public static final long EUT = 1920L;
    public static final int INPUT_AMOUNT = 1000;
    public static final int MAX_FLUID_OUTPUTS = 12;

    private static final int MAX_INPUTS = 1;
    private static final int MAX_OUTPUTS = 1;
    private static final int MAX_FLUID_INPUTS = 2;

    /** 12 种产物流体，顺序与配方数量数组一一对应。 */
    private static final Material[] OUTPUT_MATERIALS = {
            Materials.Toluene,
            Materials.Benzene,
            Materials.Octane,
            Materials.Butane,
            Materials.Butene,
            Materials.Butadiene,
            Materials.Propane,
            Materials.Propene,
            Materials.Ethane,
            Materials.Ethylene,
            Materials.Methane,
            Materials.Helium
    };

    /** 材料没有流体时退回的 FluidRegistry 名字。 */
    private static final String[] OUTPUT_FLUID_NAMES = {
            "toluene", "benzene", "octane", "butane", "butene", "butadiene",
            "propane", "propene", "ethane", "ethylene", "methane", "helium"
    };

    public static final RecipeMap<SimpleRecipeBuilder> PETROCHEMICAL = new RecipeMap<>(
            "gtlite_extended:petrochemical",
            new SimpleRecipeBuilder(),
            map -> new RecipeMapUI<>(map, true, true, true, true, false),
            MAX_INPUTS, MAX_OUTPUTS, MAX_FLUID_INPUTS, MAX_FLUID_OUTPUTS);

    /** 与配方表配套的 JEI 分类（配方表构造时自动创建的 primary 分类）。 */
    public static final GTRecipeCategory CATEGORY = PETROCHEMICAL.getPrimaryRecipeCategory();

    private static boolean initialized;

    private PetrochemicalRecipeMaps() {
    }

    /** 注册 4 条配方。由 {@code GTLiteExtended#preInit} 调用。 */
    public static synchronized void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        register("oil", oil(), new int[]{60, 180, 60, 80, 100, 90, 80, 400, 80, 400, 400, 20});
        register("crude_oil", crudeOil(), new int[]{40, 200, 30, 70, 100, 100, 30, 600, 130, 1000, 1000, 10});
        register("heavy_fuel", heavyFuel(), new int[]{240, 1200, 20, 60, 240, 150, 30, 300, 45, 450, 450, 10});
        register("light_fuel", lightFuel(), new int[]{20, 100, 20, 120, 80, 80, 140, 90, 200, 250, 2000, 40});

        GTLiteExtended.LOGGER.info("[GTLite Extended] petrochemical recipes: {}", recipeCount());
    }

    public static int recipeCount() {
        return PETROCHEMICAL.getRecipeList().size();
    }

    private static void register(String baseOilName, FluidStack baseOil, int[] amounts) {
        if (baseOil == null) {
            GTLiteExtended.LOGGER.error("[GTLite Extended] 找不到基础油流体 {}，跳过石化配方", baseOilName);
            return;
        }
        FluidStack auxiliary = materialFluid(Materials.Steam, INPUT_AMOUNT);
        if (auxiliary == null) {
            auxiliary = registryFluid("steam", INPUT_AMOUNT);
        }
        if (auxiliary == null) {
            GTLiteExtended.LOGGER.error("[GTLite Extended] 找不到辅助流体 steam，跳过石化配方 {}", baseOilName);
            return;
        }

        FluidStack[] outputs = new FluidStack[OUTPUT_MATERIALS.length];
        for (int i = 0; i < OUTPUT_MATERIALS.length; i++) {
            outputs[i] = materialFluid(OUTPUT_MATERIALS[i], amounts[i]);
            if (outputs[i] == null) {
                outputs[i] = registryFluid(OUTPUT_FLUID_NAMES[i], amounts[i]);
            }
            if (outputs[i] == null) {
                GTLiteExtended.LOGGER.error("[GTLite Extended] 找不到产物流体 {}，跳过石化配方 {}",
                        OUTPUT_FLUID_NAMES[i], baseOilName);
                return;
            }
        }

        PETROCHEMICAL.recipeBuilder()
                .fluidInputs(baseOil, auxiliary)
                .fluidOutputs(outputs)
                .duration(DURATION)
                .EUt(EUT)
                .buildAndRegister();
    }

    // ==================== 输入流体 ====================

    /** 石油：优先 GT 的 oil 材料，退回 FluidRegistry 的 "oil"。 */
    private static FluidStack oil() {
        FluidStack stack = materialFluid(Materials.Oil, INPUT_AMOUNT);
        return stack != null ? stack : registryFluid("oil", INPUT_AMOUNT);
    }

    /** 原油：优先 FluidRegistry 的 "crude_oil"，没有就退回 GT 的重油（OilHeavy）。 */
    private static FluidStack crudeOil() {
        FluidStack stack = registryFluid("crude_oil", INPUT_AMOUNT);
        return stack != null ? stack : materialFluid(Materials.OilHeavy, INPUT_AMOUNT);
    }

    /** 重油：优先 GT 的 HeavyFuel，退回 FluidRegistry 的 "heavy_fuel"。 */
    private static FluidStack heavyFuel() {
        FluidStack stack = materialFluid(Materials.HeavyFuel, INPUT_AMOUNT);
        return stack != null ? stack : registryFluid("heavy_fuel", INPUT_AMOUNT);
    }

    /** 轻油：优先 GT 的 LightFuel，退回 FluidRegistry 的 "light_fuel"。 */
    private static FluidStack lightFuel() {
        FluidStack stack = materialFluid(Materials.LightFuel, INPUT_AMOUNT);
        return stack != null ? stack : registryFluid("light_fuel", INPUT_AMOUNT);
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
