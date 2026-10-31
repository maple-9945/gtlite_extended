package com.gtlite.extended.common.recipe;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.chance.BaseChanceEntry;
import gregtech.api.recipes.chance.output.ChancedOutputList;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import gregtech.api.util.ValidationResult;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * 激光蚀刻机（{@link RecipeMaps#LASER_ENGRAVER_RECIPES}）配方重写器：
 * 把配方里的透镜（crafting lens / lens）替换成编程电路，并按“除电路外其余输入输出相同”合并重复配方。
 *
 * <p>压膜机（forming press）、流体固化器（fluid solidifier）、压模器（extruder）的模头处理已全部撤销，
 * 这三个配方表保持 GT 原样，本类不再触碰。</p>
 *
 * <h3>透镜 → 电路编号</h3>
 * 白1、橙2、品红3、淡蓝4、黄5、黄绿6、粉7、灰8、淡灰9、青10、紫11、蓝12、棕13、绿14、红15、黑16、无色17；
 * 其他 {@code OrePrefix.lens} 从 18 起动态分配（最多 32）。
 *
 * <h3>执行入口</h3>
 * <ol>
 *     <li>{@code MixinRecipeReplace} 在 {@code RecipeMap#addRecipe} 的 HEAD 调用 {@link #rewrite}；</li>
 *     <li>主类 {@code GTLiteExtended#postInit(FMLPostInitializationEvent)} → {@link #onPostInit} → {@link #sweepAll()} 兜底重扫。</li>
 * </ol>
 * 已带编程电路的配方会被跳过，因此重复执行无副作用。
 */
public final class LensMoldRecipeRewriter {

    /** 颜色透镜固定映射：ore dict 名“craftingLens”后缀（小写）→ 电路号。 */
    private static final Map<String, Integer> LENS_COLORS = new HashMap<>();

    /** 未在固定表里的透镜：18~32 动态编号。 */
    private static final Map<String, Integer> DYNAMIC_LENS = new LinkedHashMap<>();

    /** 已经见过的“归一化键”（按配方表隔离），用于合并重复配方。 */
    private static final Map<RecipeMap<?>, Set<String>> SEEN_KEYS = new IdentityHashMap<>();

    static {
        String[] colors = {"white", "orange", "magenta", "lightblue", "yellow", "lime", "pink",
                "gray", "lightgray", "cyan", "purple", "blue", "brown", "green", "red", "black"};
        for (int i = 0; i < colors.length; i++) {
            LENS_COLORS.put(colors[i], i + 1);
        }
        LENS_COLORS.put("colorless", 17);
    }

    private LensMoldRecipeRewriter() {
    }

    // ==================== 执行入口 ====================

    /** 主类 {@code postInit(FMLPostInitializationEvent)} 里调用。 */
    public static void onPostInit(FMLPostInitializationEvent event) {
        sweepAll();
    }

    /** 是否是需要改写的配方表（目前只有激光蚀刻机）。 */
    public static boolean isTarget(RecipeMap<?> map) {
        return map != null && map == RecipeMaps.LASER_ENGRAVER_RECIPES;
    }

    // ==================== 注册期改写（Mixin 调用） ====================

    /**
     * 处理一条待注册配方。
     *
     * @return {@code null} 表示不是透镜配方、保持原样；否则返回重写结果（可能是“重复，丢弃”）。
     */
    public static Result rewrite(RecipeMap<?> map, Recipe recipe) {
        if (map == null || recipe == null || recipe.isGroovyRecipe() || recipe.getInputs() == null) {
            return null;
        }
        if (!isTarget(map)) {
            return null;
        }
        List<GTRecipeInput> inputs = recipe.getInputs();

        // 已经带编程电路的配方不动，避免出现两个电路输入
        for (GTRecipeInput input : inputs) {
            if (input instanceof IntCircuitIngredient) {
                return null;
            }
        }

        int replaceIndex = -1;
        int circuit = -1;
        for (int i = 0; i < inputs.size(); i++) {
            GTRecipeInput input = inputs.get(i);
            if (input == null) {
                continue;
            }
            int mapped = lensCircuit(input);
            if (mapped > 0) {
                replaceIndex = i;
                circuit = mapped;
                break;
            }
        }
        if (replaceIndex < 0) {
            return null;
        }

        // 归一化键：去掉透镜之后的全部输入 + 全部输出 + 时长 + 耗电
        List<GTRecipeInput> restInputs = new ArrayList<>(inputs);
        restInputs.remove(replaceIndex);
        List<GTRecipeInput> restFluids = new ArrayList<>(recipe.getFluidInputs());
        String key = buildKey(restInputs, restFluids, recipe);

        Set<String> seen = SEEN_KEYS.computeIfAbsent(map, m -> new HashSet<>());
        if (!seen.add(key)) {
            // 除透镜（以及由此得到的电路号）外完全一致 → 合并为第一条
            return Result.duplicate();
        }

        // 把透镜替换成编程电路（IntCircuitIngredient 本身就是“不消耗”的）
        List<GTRecipeInput> newInputs = new ArrayList<>(inputs);
        newInputs.set(replaceIndex, IntCircuitIngredient.circuitInput(circuit));

        Recipe rewritten = new Recipe(
                newInputs,
                new ArrayList<>(recipe.getOutputs()),
                recipe.getChancedOutputs(),
                new ArrayList<>(recipe.getFluidInputs()),
                new ArrayList<>(recipe.getFluidOutputs()),
                recipe.getChancedFluidOutputs(),
                recipe.getDuration(),
                recipe.getEUt(),
                recipe.isHidden(),
                recipe.getIsCTRecipe(),
                recipe.propertyStorage(),
                recipe.getRecipeCategory());
        return Result.rewrite(rewritten, circuit);
    }

    // ==================== 兜底扫描 ====================

    /**
     * 扫一遍激光蚀刻机配方表：删除旧配方 → 用改写后的配方重新 {@code addRecipe}。
     * 已经带编程电路的配方会被跳过，因此可以重复调用。
     */
    public static void sweepAll() {
        sweep(RecipeMaps.LASER_ENGRAVER_RECIPES);
    }

    private static void sweep(RecipeMap<?> map) {
        if (map == null) {
            return;
        }
        List<Recipe> snapshot = new ArrayList<>(map.getRecipeList());
        int rewritten = 0;
        int merged = 0;
        for (Recipe recipe : snapshot) {
            Result result = rewrite(map, recipe);
            if (result == null) {
                continue;
            }
            map.removeRecipe(recipe);
            if (result.isDuplicate()) {
                merged++;
                continue;
            }
            if (map.addRecipe(ValidationResult.newResult(EnumValidationResult.VALID, result.getRecipe()))) {
                rewritten++;
            } else {
                GTLog.logger.error("[GTLite Extended] failed to re-add rewritten laser recipe: {}",
                        result.getRecipe());
            }
        }
        GTLog.logger.info("[GTLite Extended] laser engraver: {} recipes scanned, {} rewritten to circuits, {} merged",
                snapshot.size(), rewritten, merged);
    }

    // ==================== 透镜 ====================

    private static int lensCircuit(GTRecipeInput input) {
        // GT 的 notConsumable(OrePrefix.craftingLens, Color) 生成的是 ore dict 输入
        if (input.isOreDict()) {
            Integer mapped = lensFromOreName(OreDictionary.getOreName(input.getOreDict()));
            if (mapped != null) {
                return mapped;
            }
        }
        ItemStack[] stacks = input.getInputStacks();
        if (stacks == null) {
            return -1;
        }
        for (ItemStack stack : stacks) {
            if (stack == null || stack.isEmpty()) {
                continue;
            }
            for (String oreName : OreDictUnifier.getOreDictionaryNames(stack)) {
                Integer mapped = lensFromOreName(oreName);
                if (mapped != null) {
                    return mapped;
                }
            }
            OrePrefix prefix = OreDictUnifier.getPrefix(stack);
            if (prefix == OrePrefix.craftingLens || prefix == OrePrefix.lens) {
                MaterialStack material = OreDictUnifier.getMaterial(stack);
                String name = material == null ? toStringKey(stack) : material.material.toString();
                return lensDynamic(name);
            }
        }
        return -1;
    }

    private static Integer lensFromOreName(String oreName) {
        if (oreName == null) {
            return null;
        }
        if (oreName.startsWith("craftingLens")) {
            String color = oreName.substring("craftingLens".length()).toLowerCase(Locale.ROOT);
            Integer fixed = LENS_COLORS.get(color);
            return fixed != null ? fixed : lensDynamic(color);
        }
        if (oreName.startsWith("lens")) {
            return lensDynamic(oreName.substring("lens".length()));
        }
        return null;
    }

    private static int lensDynamic(String name) {
        String key = (name == null || name.isEmpty()) ? "unknown" : name;
        Integer existing = DYNAMIC_LENS.get(key);
        if (existing != null) {
            return existing;
        }
        int next = 18 + DYNAMIC_LENS.size();
        if (next > IntCircuitIngredient.CIRCUIT_MAX) {
            return -1;
        }
        DYNAMIC_LENS.put(key, next);
        return next;
    }

    /** 稳定键：优先使用翻译键（不受本地化影响）。 */
    private static String toStringKey(ItemStack stack) {
        try {
            String key = stack.getItem().getTranslationKey(stack);
            if (key != null && !key.isEmpty()) {
                return key;
            }
        } catch (Throwable ignored) {
            // 某些模组的物品可能抛异常，退回注册名
        }
        return String.valueOf(stack.getItem().getRegistryName()) + ":" + stack.getMetadata();
    }

    // ==================== 合并键 ====================

    private static String buildKey(List<GTRecipeInput> inputs, List<GTRecipeInput> fluidInputs, Recipe recipe) {
        StringBuilder sb = new StringBuilder(128);
        sb.append("I:").append(sorted(inputs));
        sb.append(" FI:").append(sorted(fluidInputs));
        sb.append(" O:").append(itemKeys(recipe.getOutputs()));
        sb.append(" CO:").append(chancedKeys(recipe.getChancedOutputs()));
        sb.append(" FO:").append(fluidKeys(recipe.getFluidOutputs()));
        sb.append(" CFO:").append(chancedKeys(recipe.getChancedFluidOutputs()));
        sb.append(" D:").append(recipe.getDuration());
        sb.append(" E:").append(recipe.getEUt());
        return sb.toString();
    }

    private static List<String> itemKeys(List<ItemStack> stacks) {
        List<String> keys = new ArrayList<>(stacks.size());
        for (ItemStack stack : stacks) {
            keys.add(stack == null || stack.isEmpty() ? "empty"
                    : stack.getItem().getRegistryName() + "/" + stack.getMetadata() + "/" + stack.getCount()
                    + "/" + stack.getTagCompound());
        }
        Collections.sort(keys);
        return keys;
    }

    private static List<String> fluidKeys(List<FluidStack> stacks) {
        List<String> keys = new ArrayList<>(stacks.size());
        for (FluidStack stack : stacks) {
            keys.add(stack == null || stack.getFluid() == null ? "empty"
                    : stack.getFluid().getName() + "/" + stack.amount + "/" + stack.tag);
        }
        Collections.sort(keys);
        return keys;
    }

    private static List<String> chancedKeys(ChancedOutputList<?, ?> chanced) {
        List<String> keys = new ArrayList<>();
        if (chanced == null) {
            return keys;
        }
        for (Object entry : chanced.getChancedEntries()) {
            if (!(entry instanceof BaseChanceEntry)) {
                continue;
            }
            BaseChanceEntry<?> chanceEntry = (BaseChanceEntry<?>) entry;
            Object ingredient = chanceEntry.getIngredient();
            String key;
            if (ingredient instanceof ItemStack) {
                key = itemKeys(Collections.singletonList((ItemStack) ingredient)).get(0);
            } else if (ingredient instanceof FluidStack) {
                key = fluidKeys(Collections.singletonList((FluidStack) ingredient)).get(0);
            } else {
                key = String.valueOf(ingredient);
            }
            keys.add(key + "@" + chanceEntry.getChance());
        }
        Collections.sort(keys);
        return keys;
    }

    private static List<String> sorted(List<GTRecipeInput> inputs) {
        List<String> parts = new ArrayList<>(inputs.size());
        for (GTRecipeInput input : inputs) {
            parts.add(String.valueOf(input));
        }
        Collections.sort(parts);
        return parts;
    }

    // ==================== 结果 ====================

    public static final class Result {

        private static final Result DUPLICATE = new Result(null, -1);

        private final Recipe recipe;
        private final int circuit;

        private Result(Recipe recipe, int circuit) {
            this.recipe = recipe;
            this.circuit = circuit;
        }

        static Result duplicate() {
            return DUPLICATE;
        }

        static Result rewrite(Recipe recipe, int circuit) {
            return new Result(recipe, circuit);
        }

        public boolean isDuplicate() {
            return this.recipe == null;
        }

        public Recipe getRecipe() {
            return this.recipe;
        }

        public int getCircuit() {
            return this.circuit;
        }
    }
}
