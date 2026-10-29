package com.gtlite.extended.common.recipe;

import com.gtlite.extended.GTLiteExtended;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.ModularUI;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.category.GTRecipeCategory;
import gregtech.api.recipes.chance.output.impl.ChancedItemOutput;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.recipes.ui.RecipeMapUI;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 矿石处理配方池。
 *
 * <p>规则（全mod只有这一套配方，运行时机器与 JEI 读的都是同一个 {@link #ORE_PROCESSING}）：</p>
 * <ul>
 *     <li>输入：任意 GT 矿石前缀（ore / oreGranite / oreNetherrack / oreEndstone ...）+ 1000mB 水；</li>
 *     <li><b>产物直接照抄 gtlitecore 集成矿石处理厂的配方并集</b>（见 {@link #copyGtliteOutputs}）：
 *         gtlite 的集成矿石处理厂有 10 种模式（编程电路 1~10），这里把同一块矿石
 *         <b>所有模式</b>的产物合并成一份：每种物品只出现一次、数量取各模式中的最大值
 *         （所以主产物数量保持原样、不会因为并集翻倍），几率产出一律按 100% 给 1 个。
 *         主产物、宝石、副产物、甚至冰晶石这类由 gtlitecore 自己注册的物品都原样保留，
 *         不再自己推导；</li>
 *     <li><b>品级折算</b>（见 {@link #convertLowGradeGemsAndPureDust}）：并集里的
 *         有瑕宝石（gemFlawed）、破碎宝石（gemChipped）和纯净的粉（dustPure）
 *         全部折算成<b>同材料、同数量</b>的粉，并累加到该材料的粉里——所以最终配方里
 *         只会看到普通粉、完整品级的宝石和副产物；</li>
 *     <li>只有 gtlite 没有这条矿石的配方时，才退回旧的兜底规则（见 {@link #buildOutputs}）：
 *         主产物粉（{@code max(4, resolvePrimaryAmount(...))}）→ 宝石变体 gem/gemExquisite/gemFlawless
 *         各 1 → 副产物粉；超过 {@link #OUTPUT_SLOTS} 个槽位时从<b>末尾</b>截断；</li>
 *     <li>旧兜底规则的产出白名单是 {@code gregtech} / {@code gtlitecore} / {@code minecraft}
 *         三个命名空间（见 {@link #isValidStack}），其他模组的物品一律丢弃；
 *         抄 gtlite 配方时不做过滤（配方本身只含 GT 体系物品）。</li>
 * </ul>
 *
 * <p>生成时机：{@code FMLLoadCompleteEvent}（所有 mod 的 postInit 与 GT 的
 * {@code OrePrefix.runMaterialHandlers()} 都已完成，材料表与统一物品表才是最终状态）。
 * gtlitecore 的集成矿石处理配方在 {@code RegistryEvent.Register<IRecipe>} 阶段就注册完了
 * （早于本事件），因此这里读到的是它的最终配方。JEI 侧由
 * {@code OreProcessingJeiPlugin} 做运行时补注册（详见该类的注释）。</p>
 */
public final class OreProcessingRecipeMaps {

    public static final int DURATION = 128;
    public static final int EUT = 30;

    /**
     * 产出槽位上限：机器、配方表与产物构建共用这一个数字。
     *
     * <p>抄 gtlite 配方时按“主产物粉 → 宝石变体 → 其余副产物”的顺序占槽，超槽从末尾截断
     * （丢的永远是副产物）；走旧兜底规则时顺序是“主产物粉 → 宝石变体 → 副产物粉”，
     * 超槽同样从末尾截断。</p>
     */
    public static final int OUTPUT_SLOTS = 16;

    /** 排查日志里每条配方最多展示的产物种类数，超过就截断并告警。 */
    private static final int LOG_MAX_OUTPUTS = 12;

    /** 主产物数量的下限：{@link #resolvePrimaryAmount} 返回值小于它时一律抬到它（冰晶石这类矿石就靠这条兜底）。 */
    private static final int MIN_PRIMARY_AMOUNT = 4;

    /** 输入物品数 / 输入流体数。 */
    private static final int MAX_INPUTS = 1;
    private static final int MAX_FLUID_INPUTS = 1;
    private static final int MAX_FLUID_OUTPUTS = 0;

    private static final int WATER_AMOUNT = 100;
    private static final String GREGTECH_MODID = "gregtech";
    private static final String GTLITECORE_MODID = "gtlitecore";
    private static final String MINECRAFT_MODID = "minecraft";

    /** 与既有行为保持一致：下界岩 / 末地石矿石的副产物翻倍（主产物的倍率已由研磨机配方给出）。 */
    private static final int DOUBLED_MULTIPLIER = 2;

    /**
     * 矿石物品（{@code registry@meta}）→ gtlitecore 集成矿石处理厂的<b>全部模式</b>配方列表。
     *
     * <p>gtlite 的集成矿石处理厂有 10 种模式（编程电路 1~10，对应研磨/洗矿/热力离心/筛选/化学浸洗
     * 等不同流程）。这里不再只取模式1，而是把同一块矿石的所有模式都收进来，生成产物时取<b>并集</b>：
     * 每种物品只出现一次，数量取各模式中的<b>最大值</b>（所以主产物数量不会因为并集翻倍），
     * 几率产出一律按 100% 给 1 个。</p>
     */
    private static final Map<String, List<Recipe>> GTLITE_RECIPES = new HashMap<>();

    /**
     * 研磨机“单输入矿石配方”的主产物数量索引：物品 key → 普通产物的数量。
     *
     * <p>GT 的 {@code OreRecipeHandler} 为每种矿石生成研磨机配方时，主产物数量是
     * {@code round(oreMultiplier) * 2 * (下界岩/末地石 ? 2 : 1)}，
     * 也就是 GT 对“一块矿石值多少产物”的权威定义（红石 = 5 * 2 = 10）。</p>
     */
    private static final Map<String, Integer> MACERATOR_PRIMARY_AMOUNTS = new HashMap<>();

    /** 读不到研磨机配方时的写死保底值（红石等特殊矿石）。 */
    private static final Map<Material, Integer> SPECIAL_PRIMARY_AMOUNTS = new HashMap<>();

    static {
        SPECIAL_PRIMARY_AMOUNTS.put(Materials.Redstone, 10);
    }

    /**
     * 配方表。
     *
     * <p>构造参数顺序（已从 GTCEu 字节码确认）是：
     * unlocalizedName, recipeBuilder, uiFunction, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs。
     * 旧代码写成 (1, 1, 6, 1)，实际含义是“最多 1 个物品产出、6 个流体输入”，
     * 于是 {@code RecipeMap.postValidateRecipe} 把每一条多产出配方全部拒收，这才是“配方数为 0”的直接原因。</p>
     */
    public static final RecipeMap<SimpleRecipeBuilder> ORE_PROCESSING = new RecipeMap<>(
            "gtlite_extended:ore_processing",
            new SimpleRecipeBuilder(),
            OreProcessingUI::new,
            MAX_INPUTS, OUTPUT_SLOTS, MAX_FLUID_INPUTS, MAX_FLUID_OUTPUTS);

    /**
     * 矿石处理专用界面：输出最多 {@link #OUTPUT_SLOTS}=16 个物品槽，
     * GT 默认布局会算出 4×4 网格并把起始 y 算成 -3（第一行跑到 JEI 分类外面）。
     * 这里改成固定 4 列、从 y=8 开始向下排，最底一行 80，整块落在 JEI 分类高度（110）以内；
     * 输入侧保持 GT 默认布局（1 个矿石物品 + 1 个水槽）。
     */
    private static final class OreProcessingUI extends RecipeMapUI<RecipeMap<SimpleRecipeBuilder>> {

        @SuppressWarnings("unchecked")
        private OreProcessingUI(RecipeMap<?> recipeMap) {
            super((RecipeMap<SimpleRecipeBuilder>) recipeMap, true, true, true, true, false);
        }

        @Override
        protected void addInventorySlotGroup(ModularUI.Builder builder, IItemHandlerModifiable itemHandler,
                                             FluidTankList fluidHandler, boolean isOutputs, int yOffset) {
            if (!isOutputs) {
                super.addInventorySlotGroup(builder, itemHandler, fluidHandler, false, yOffset);
                return;
            }
            int itemCount = itemHandler.getSlots();
            int fluidCount = fluidHandler.getTanks();
            int columns = 4;
            int startX = 100;
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

    /**
     * JEI 分类必须是配方表自己的 primary 分类。
     *
     * <p>{@code RecipeMap} 构造时会自动创建 primary 分类，而 {@code recipeBuilder()} 建的配方默认就落在它下面。
     * 旧代码另外 {@code GTRecipeCategory.create(...)} 了一个新分类注册进 JEI，结果就是
     * “JEI 里能看到分类，但配方永远为 0”。</p>
     */
    public static final GTRecipeCategory CATEGORY = ORE_PROCESSING.getPrimaryRecipeCategory();

    /** 防止重复生成（配方表没有公开的 removeAllRecipes，生成了就不能再来一遍）。 */
    private static boolean built;

    private OreProcessingRecipeMaps() {
    }

    /**
     * 生成全部矿石处理配方。由 {@code GTLiteExtended#loadComplete(FMLLoadCompleteEvent)} 调用。
     *
     * @return 本次新注册的配方数量
     */
    public static synchronized int init() {
        if (built) {
            return 0;
        }
        built = true;

        indexMaceratorPrimaryAmounts();
        indexGtliteRecipes();

        int materials = 0;
        int attempted = 0;
        int registered = 0;
        int copiedFromGtlite = 0;
        int legacyFallback = 0;
        int dropped = 0;
        int failed = 0;

        List<OrePrefix> orePrefixes = collectOrePrefixes();
        List<Material> oreMaterials = collectOreMaterials();
        logMaterialDiagnostics(oreMaterials);

        for (Material material : oreMaterials) {
            materials++;
            OreProperty oreProperty = material.getProperty(PropertyKey.ORE);
            if (oreProperty == null) {
                continue;
            }
            for (OrePrefix prefix : orePrefixes) {
                if (!prefix.doGenerateItem(material)) {
                    continue;
                }
                if (OreDictUnifier.get(prefix, material).isEmpty()) {
                    // 该材料没有生成这种矿石形态的物品，也就没有对应的矿石字典输入
                    continue;
                }

                // 产物抄 gtlitecore 集成矿石处理厂全部模式的并集；抄不到才退回旧的兜底规则
                List<ItemStack> outputs = copyGtliteOutputs(prefix, material);
                boolean copied = outputs != null;
                if (copied) {
                    copiedFromGtlite++;
                } else {
                    outputs = buildOutputs(prefix, material, oreProperty);
                    if (outputs.isEmpty()) {
                        dropped++;
                        continue;
                    }
                    legacyFallback++;
                }

                attempted++;
                try {
                    int before = ORE_PROCESSING.getRecipeList().size();
                    SimpleRecipeBuilder builder = ORE_PROCESSING.recipeBuilder()
                            .input(prefix, material)
                            .fluidInputs(new FluidStack(FluidRegistry.WATER, WATER_AMOUNT))
                            .duration(DURATION)
                            .EUt(EUT);
                    for (ItemStack output : outputs) {
                        builder.outputs(output.copy());
                    }
                    builder.buildAndRegister();
                    if (ORE_PROCESSING.getRecipeList().size() > before) {
                        registered++;
                    }
                } catch (Throwable t) {
                    failed++;
                    GTLiteExtended.LOGGER.warn("[GTLite Extended] 生成矿石处理配方失败: {} + {}",
                            prefix.name(), material.getName(), t);
                }
            }
        }

        GTLiteExtended.LOGGER.info(
                "[GTLite Extended] ore_processing: materials={} prefixes={} attempted={} registered={} failed={} "
                        + "copied(gtlite 全模式并集)={} fallback(legacy)={} dropped(no output)={} total={}",
                materials, orePrefixes.size(), attempted, registered, failed,
                copiedFromGtlite, legacyFallback, dropped, recipeCount());
        if (GTLITE_RECIPES.isEmpty()) {
            GTLiteExtended.LOGGER.warn("[GTLite Extended] 没有读到 gtlitecore 集成矿石处理配方，本次全部按旧规则生成产物");
        }
        logPrimaryAmountSamples();
        logAllRecipes();
        return registered;
    }

    /**
     * 全量配方排查日志：遍历 {@link #ORE_PROCESSING} 里注册好的每一条配方，
     * 打印“输入矿石名称 -> 产物名 x 数量, ...”，超过 {@link #LOG_MAX_OUTPUTS} 种就截断并告警。
     *
     * <p>输出示例：{@code [Ore Test] 冰晶石矿石 -> 冰晶石粉 x 4, 冰晶石 x 1, 精致的冰晶石 x 1,
     * 无瑕的冰晶石 x 1, 钠粉 x 4, 铝粉 x 4}——主产物永远在前，钠/铝等副产物永远在后。</p>
     */
    private static void logAllRecipes() {
        java.util.Collection<Recipe> recipes = ORE_PROCESSING.getRecipeList();
        GTLiteExtended.LOGGER.info("[Ore Test] 开始输出全部矿石处理配方，共 {} 条", recipes.size());

        int truncated = 0;
        for (Recipe recipe : recipes) {
            List<ItemStack> outputs = recipe.getOutputs();
            boolean over = outputs.size() > LOG_MAX_OUTPUTS;
            if (over) {
                truncated++;
            }
            int shown = Math.min(outputs.size(), LOG_MAX_OUTPUTS);
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < shown; i++) {
                ItemStack output = outputs.get(i);
                if (i > 0) {
                    text.append(", ");
                }
                text.append(displayName(output)).append(" x ").append(output.getCount());
            }
            if (over) {
                text.append(" ... 已截断（实际 ").append(outputs.size()).append(" 种）");
            }
            GTLiteExtended.LOGGER.info("[Ore Test] {} -> {}", describeRecipeInput(recipe), text);
        }
        if (truncated > 0) {
            GTLiteExtended.LOGGER.warn("[Ore Test] 有 {} 条配方的产物超过 {} 种，日志里已截断显示",
                    truncated, LOG_MAX_OUTPUTS);
        }
        GTLiteExtended.LOGGER.info("[Ore Test] 全部矿石处理配方输出完毕");
    }

    /** 取配方第一个输入物品的显示名（矿石前缀物品，如“冰晶石矿石”）。 */
    private static String describeRecipeInput(Recipe recipe) {
        List<GTRecipeInput> inputs = recipe.getInputs();
        if (inputs.isEmpty()) {
            return "?";
        }
        ItemStack[] stacks = inputs.get(0).getInputStacks();
        if (stacks != null) {
            for (ItemStack stack : stacks) {
                if (stack != null && !stack.isEmpty()) {
                    return displayName(stack);
                }
            }
        }
        return inputs.get(0).toString();
    }

    /** 物品显示名；取不到时退回注册名，保证日志一定可读。 */
    private static String displayName(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return "?";
        }
        String name = stack.getDisplayName();
        if (name == null || name.isEmpty()) {
            ResourceLocation registryName = stack.getItem().getRegistryName();
            name = registryName == null ? "?" : registryName.toString();
        }
        return name;
    }

    /**
     * 临时排查：打印“有 GEM 无 DUST”“有 DUST 但粉物品为空”“无 GEM 无 DUST”的矿石材料，
     * 方便确认产物缺失到底是因为材料本身没有该形态，还是代码判断有问题。
     */
    private static void logMaterialDiagnostics(List<Material> oreMaterials) {
        for (Material material : oreMaterials) {
            boolean hasGem = material.hasProperty(PropertyKey.GEM);
            boolean hasDust = material.hasProperty(PropertyKey.DUST);
            boolean dustEmpty = OreDictUnifier.get(OrePrefix.dust, material).isEmpty();
            boolean flawlessEmpty = OreDictUnifier.get(OrePrefix.gemFlawless, material).isEmpty();
            boolean suspicious = (hasGem && !hasDust)
                    || (!hasDust && !hasGem)
                    || (hasDust && dustEmpty)
                    || (hasGem && flawlessEmpty);
            if (!suspicious) {
                continue;
            }
            OreProperty oreProperty = material.getProperty(PropertyKey.ORE);
            StringBuilder byProducts = new StringBuilder();
            if (oreProperty != null) {
                for (Material byProduct : oreProperty.getOreByProducts()) {
                    if (byProducts.length() > 0) {
                        byProducts.append(", ");
                    }
                    byProducts.append(byProduct == null ? "null" : byProduct.getName())
                            .append(byProduct != null && byProduct.hasProperty(PropertyKey.DUST) ? "(dust)" : "(no-dust)");
                }
            }
            GTLiteExtended.LOGGER.info(
                    "[Ore Diag] {} hasGem={} hasDust={} dustEmpty={} flawlessEmpty={} byProducts=[{}]",
                    material.getName(), hasGem, hasDust, dustEmpty, flawlessEmpty, byProducts);
        }
    }


    private static void logPrimaryAmountSamples() {
        GTLiteExtended.LOGGER.info(
                "[GTLite Extended] 主产物倍率抽样(ore): iron={} gold={} diamond={} lapis={} redstone={} coal={}",
                samplePrimaryAmount(Materials.Iron),
                samplePrimaryAmount(Materials.Gold),
                samplePrimaryAmount(Materials.Diamond),
                samplePrimaryAmount(Materials.Lapis),
                samplePrimaryAmount(Materials.Redstone),
                samplePrimaryAmount(Materials.Coal));
        GTLiteExtended.LOGGER.info("[GTLite Extended] 产出抽样(ore，抄 gtlite 全模式并集/旧规则兜底): emerald=[{}] diamond=[{}] iron=[{}] redstone=[{}]",
                describeOutputs(Materials.Emerald),
                describeOutputs(Materials.Diamond),
                describeOutputs(Materials.Iron),
                describeOutputs(Materials.Redstone));
    }

    /** 把某种矿石的产物列表拼成 "数量x 显示名(registry@meta)" 形式，仅用于日志验证。 */
    private static String describeOutputs(Material material) {
        OreProperty oreProperty = material.getProperty(PropertyKey.ORE);
        if (oreProperty == null) {
            return "-";
        }
        // 与 init() 完全一致：优先抄 gtlite 配方，抄不到才用旧规则
        List<ItemStack> outputs = copyGtliteOutputs(OrePrefix.ore, material);
        if (outputs == null) {
            outputs = buildOutputs(OrePrefix.ore, material, oreProperty);
        }
        StringBuilder builder = new StringBuilder();
        for (ItemStack stack : outputs) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(stack.getCount()).append('x')
                    .append(stack.getDisplayName())
                    .append('(').append(stack.getItem().getRegistryName()).append('@')
                    .append(stack.getMetadata()).append(')');
        }
        return builder.toString();
    }

    private static int samplePrimaryAmount(Material material) {
        OreProperty oreProperty = material.getProperty(PropertyKey.ORE);
        if (oreProperty == null) {
            return -1;
        }
        return resolvePrimaryAmount(OrePrefix.ore, material, oreProperty, false);
    }

    /** 当前配方池里的配方数量（机器运行时与 JEI 读的是同一份）。 */
    public static int recipeCount() {
        return ORE_PROCESSING.getRecipeList().size();
    }

    public static boolean isBuilt() {
        return built;
    }

    /**
     * 收集全部“矿石”前缀。GTCEu 1.12.2 使用 {@link OrePrefix}（不是旧版的 OrePrefixes 枚举），
     * 通过 {@link OrePrefix#values()} 遍历，避免硬编码列表漏掉整合包新增的矿石形态。
     */
    private static List<OrePrefix> collectOrePrefixes() {
        List<OrePrefix> prefixes = new ArrayList<>();
        for (OrePrefix prefix : OrePrefix.values()) {
            if (prefix == null) {
                continue;
            }
            String name = prefix.name();
            // ore / oreGranite / oreDiorite / ... / oreNetherrack / oreEndstone / oreSand / ...
            if (name != null && name.startsWith("ore")) {
                prefixes.add(prefix);
            }
        }
        // 固定顺序，保证每次启动生成的配方顺序一致（JEI 里的排列也就稳定）
        prefixes.sort(Comparator.comparing(OrePrefix::name));
        return prefixes;
    }

    /** 收集全部带 ORE 属性的材料，按名字排序保证顺序稳定。 */
    private static List<Material> collectOreMaterials() {
        List<Material> materials = new ArrayList<>();
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            if (material != null && material.hasProperty(PropertyKey.ORE)) {
                materials.add(material);
            }
        }
        materials.sort(Comparator.comparing(Material::getName));
        return materials;
    }

    // ── gtlitecore 集成矿石处理厂（全部模式并集）产物照抄 ────────────────────────

    /**
     * 建立“矿石物品（{@code registry@meta}）→ gtlitecore 集成矿石处理厂全部模式配方”的索引。
     *
     * <p>gtlitecore 的 {@code IntegratedOreProcessorRecipeProducer} 在
     * {@code RegistryEvent.Register<IRecipe>}（LOW 优先级）阶段为每种矿石 × 每种模式
     * （编程电路 1~10）各注册一条配方，时间早于本类所在的 {@code FMLLoadCompleteEvent}，
     * 所以这里读到的是最终配方。</p>
     *
     * <p>这里不再按电路筛选，而是把<b>所有模式</b>都收进同一个列表；一个配方里的物品输入
     * 可能有多个（矿石 + 电路，个别模式还有额外物品），这里把非电路输入的每个候选物品登记一次。
     * 生成产物时对列表里的每条配方取并集（见 {@link #copyGtliteOutputs}）。</p>
     */
    private static void indexGtliteRecipes() {
        GTLITE_RECIPES.clear();
        int scanned = 0;
        try {
            Collection<Recipe> recipes = GTLiteRecipeMaps.INTEGRATED_ORE_PROCESSOR_RECIPES.getRecipeList();
            for (Recipe recipe : recipes) {
                if (recipe == null) {
                    continue;
                }
                scanned++;
                for (GTRecipeInput input : recipe.getInputs()) {
                    if (input instanceof IntCircuitIngredient) {
                        // 电路只决定模式，不是矿石输入，跳过
                        continue;
                    }
                    ItemStack[] stacks = input.getInputStacks();
                    if (stacks == null) {
                        continue;
                    }
                    for (ItemStack stack : stacks) {
                        if (stack == null || stack.isEmpty()) {
                            continue;
                        }
                        List<Recipe> list = GTLITE_RECIPES.computeIfAbsent(stackKey(stack), key -> new ArrayList<>());
                        if (!list.contains(recipe)) {
                            list.add(recipe);
                        }
                    }
                }
            }
            GTLiteExtended.LOGGER.info(
                    "[GTLite Extended] gtlitecore 集成矿石处理配方索引: 扫描 {} 条 → 覆盖 {} 种矿石物品（全部模式并集）",
                    scanned, GTLITE_RECIPES.size());
        } catch (Throwable t) {
            GTLITE_RECIPES.clear();
            GTLiteExtended.LOGGER.warn(
                    "[GTLite Extended] 读取 gtlitecore 集成矿石处理配方失败，本次全部回退到旧产物规则", t);
        }
    }

    /**
     * 取 gtlitecore 集成矿石处理厂对该矿石<b>全部模式</b>的产物并集。
     *
     * <p>并集规则（<b>不做任何推导、不过滤命名空间</b>）：</p>
     * <ul>
     *     <li>同一物品（{@code registry@meta} 相同）只占一个槽位，数量取各模式中的<b>最大值</b>，
     *         不累加——这样主产物粉的数量保持各模式原本的数量，不会因为并集翻倍；</li>
     *     <li>几率产出一律按 <b>100%</b> 计 1 个（本机器 100% 产出）；</li>
     *     <li>有瑕/破碎宝石与纯净的粉在并集之后折算成同材料同数量的粉
     *         （见 {@link #convertLowGradeGemsAndPureDust}）；</li>
     *     <li>槽位顺序：主产物粉 → 该材料的宝石变体（gem / gemExquisite / gemFlawless）→
     *         其余副产物；超过 {@link #OUTPUT_SLOTS} 个槽位时从末尾截断，
     *         所以被丢掉的永远是副产物。</li>
     * </ul>
     *
     * <p><b>石种变体为什么回退到基础矿石：</b>gtlite 的 {@code IntegratedOreProcessorRecipeProducer}
     * 只在 {@code ConfigHolder.worldgen.allUniqueStoneTypes=true} 时才给花岗岩/石灰岩等石种前缀注册配方；
     * 本整合包该配置是 {@code false}，所以 gtlite 自己只注册了 ore / oreNetherrack / oreEndstone 三种。
     * 而 gtlite 生成产物时始终以<b>基础矿石（{@link OrePrefix#ore}）</b>为起点模拟整条处理链，
     * 石种前缀只是把同一份产物再注册一遍、数量乘上该前缀的倍率（石种 = 1，下界岩 = 2，末地石 = 4）。
     * 因此这里在“该前缀没有配方”时直接抄基础矿石的并集，并按同一倍率换算数量，
     * 产物与 gtlite 自己会生成的完全一致。</p>
     *
     * @return 产物列表；gtlitecore 没有这条矿石的配方时返回 {@code null}（调用方回退旧规则）
     */
    private static List<ItemStack> copyGtliteOutputs(OrePrefix prefix, Material material) {
        if (GTLITE_RECIPES.isEmpty()) {
            return null;
        }
        ItemStack oreStack = OreDictUnifier.get(prefix, material);
        if (oreStack.isEmpty()) {
            return null;
        }

        List<Recipe> recipes = GTLITE_RECIPES.get(stackKey(oreStack));
        int countMultiplier = 1;
        if (recipes == null || recipes.isEmpty()) {
            // 石种前缀在 gtlite 的配方表里可能没有条目：抄基础矿石的同一份并集，按前缀倍率换算数量
            ItemStack baseOre = OreDictUnifier.get(OrePrefix.ore, material);
            if (baseOre.isEmpty() || stackKey(baseOre).equals(stackKey(oreStack))) {
                return null;
            }
            recipes = GTLITE_RECIPES.get(stackKey(baseOre));
            if (recipes == null || recipes.isEmpty()) {
                return null;
            }
            countMultiplier = gtliteProductMultiplier(prefix);
        }

        // 并集：同一物品只留一个槽位，数量取各模式最大值（主产物数量因此保持不变）
        Map<String, ItemStack> merged = new LinkedHashMap<>();
        for (Recipe recipe : recipes) {
            for (ItemStack output : recipe.getOutputs()) {
                mergeMax(merged, output, output.getCount() * countMultiplier);
            }
            for (ChancedItemOutput chanced : recipe.getChancedOutputs().getChancedEntries()) {
                if (chanced == null) {
                    continue;
                }
                // 机器 100% 产出：几率产出一律给 1 个
                mergeMax(merged, chanced.getIngredient(), 1);
            }
        }
        if (merged.isEmpty()) {
            return null;
        }

        // 有瑕/破碎宝石、纯净的粉 → 同材料同数量的粉
        convertLowGradeGemsAndPureDust(merged);

        List<ItemStack> ordered = orderProducts(merged, material);
        if (ordered.size() > OUTPUT_SLOTS) {
            GTLiteExtended.LOGGER.warn("[GTLite Extended] {} 抄自 gtlite 的产出 {} 种超过 {} 个槽位，末尾被截断",
                    material.getName(), ordered.size(), OUTPUT_SLOTS);
            return new ArrayList<>(ordered.subList(0, OUTPUT_SLOTS));
        }
        return ordered;
    }

    /**
     * 并集合并：同一物品只占一个槽位，数量取最大值（不累加，保证主产物数量不变）。
     */
    private static void mergeMax(Map<String, ItemStack> merged, ItemStack stack, int amount) {
        if (stack == null || stack.isEmpty()) {
            return;
        }
        int count = Math.min(Math.max(1, amount), stack.getMaxStackSize());
        String key = stackKey(stack);
        ItemStack existing = merged.get(key);
        if (existing == null) {
            ItemStack copy = stack.copy();
            copy.setCount(count);
            merged.put(key, copy);
        } else if (count > existing.getCount()) {
            existing.setCount(count);
        }
    }

    /**
     * 把“有瑕宝石（gemFlawed）/ 破碎宝石（gemChipped）/ 纯净的粉（dustPure）”折算成
     * <b>同材料、同数量</b>的粉（dust），并累加到并集里对应的粉上。
     *
     * <p>例：某个材料并集里是「粉 x2、有瑕宝石 x1、破碎宝石 x1、纯净粉 x1」，
     * 折算后只剩「粉 x5」。找不到对应粉物品时保留原物品，不会凭空丢产物。</p>
     *
     * <p>数量用<b>累加</b>而不是取最大值：这些品级本来就是同一份材料的不同形态，
     * 折算成粉后应当并入主产物粉的数量。</p>
     */
    private static void convertLowGradeGemsAndPureDust(Map<String, ItemStack> merged) {
        if (merged.isEmpty()) {
            return;
        }
        List<ItemStack> converted = new ArrayList<>();
        Iterator<Map.Entry<String, ItemStack>> iterator = merged.entrySet().iterator();
        while (iterator.hasNext()) {
            ItemStack stack = iterator.next().getValue();
            OrePrefix prefix = OreDictUnifier.getPrefix(stack);
            if (prefix != OrePrefix.gemFlawed
                    && prefix != OrePrefix.gemChipped
                    && prefix != OrePrefix.dustPure) {
                continue;
            }
            MaterialStack materialStack = OreDictUnifier.getMaterial(stack);
            Material material = materialStack == null ? null : materialStack.material;
            if (material == null) {
                continue;
            }
            ItemStack dust = OreDictUnifier.get(OrePrefix.dust, material, stack.getCount());
            if (dust.isEmpty()) {
                continue;
            }
            iterator.remove();
            converted.add(dust);
        }
        for (ItemStack dust : converted) {
            mergeAdd(merged, dust, dust.getCount());
        }
    }

    /**
     * 数量累加合并：与 {@link #mergeMax} 不同，这里把数量加到已有槽位上
     * （用于把折算出来的粉并入主产物粉）。
     */
    private static void mergeAdd(Map<String, ItemStack> merged, ItemStack stack, int amount) {
        if (stack == null || stack.isEmpty()) {
            return;
        }
        int total = Math.max(1, amount);
        String key = stackKey(stack);
        ItemStack existing = merged.get(key);
        if (existing == null) {
            ItemStack copy = stack.copy();
            copy.setCount(Math.min(total, copy.getMaxStackSize()));
            merged.put(key, copy);
        } else {
            existing.setCount(Math.min(existing.getCount() + total, existing.getMaxStackSize()));
        }
    }

    /**
     * 产物的槽位顺序：主产物粉 → 该材料的宝石变体 → 其余副产物（并集里先出现的先放）。
     */
    private static List<ItemStack> orderProducts(Map<String, ItemStack> merged, Material material) {
        List<ItemStack> ordered = new ArrayList<>(merged.size());
        addOrdered(ordered, merged, OreDictUnifier.get(OrePrefix.dust, material));
        addOrdered(ordered, merged, OreDictUnifier.get(OrePrefix.gem, material));
        addOrdered(ordered, merged, OreDictUnifier.get(OrePrefix.gemExquisite, material));
        addOrdered(ordered, merged, OreDictUnifier.get(OrePrefix.gemFlawless, material));
        addOrdered(ordered, merged, OreDictUnifier.get(OrePrefix.gemFlawed, material));
        addOrdered(ordered, merged, OreDictUnifier.get(OrePrefix.gemChipped, material));
        ordered.addAll(merged.values());
        return ordered;
    }

    /** 把指定物品从并集里挪到有序列表的末尾（不存在就跳过）。 */
    private static void addOrdered(List<ItemStack> ordered, Map<String, ItemStack> merged, ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return;
        }
        ItemStack value = merged.remove(stackKey(stack));
        if (value != null) {
            ordered.add(value);
        }
    }

    /**
     * gtlite 的矿石前缀产物倍率：下界岩矿石 ×2、末地石矿石 ×4，其余前缀 ×1。
     *
     * <p>与 {@code IntegratedOreProcessorRecipeProducer#getOreProductMultiplier(OrePrefix)} 完全一致。</p>
     */
    private static int gtliteProductMultiplier(OrePrefix prefix) {
        if (prefix == OrePrefix.oreNetherrack) {
            return DOUBLED_MULTIPLIER;
        }
        if (prefix == OrePrefix.oreEndstone) {
            return DOUBLED_MULTIPLIER * DOUBLED_MULTIPLIER;
        }
        return 1;
    }

    /**
     * 旧兜底规则：由 Materials + OrePrefixes 直接推导某一材料矿石的全部产出
     * （只在 {@link #copyGtliteOutputs} 抄不到 gtlite 配方时使用）。
     *
     * <p>插入顺序就是优先级顺序，也就是配方产出的槽位顺序：</p>
     * <ol>
     *     <li><b>主产物粉</b>：严格是 {@code OreDictUnifier.get(OrePrefix.dust, material)}，
     *         即“这块矿石自己的材料”的粉。数量 = {@code max(4, resolvePrimaryAmount(...))}。
     *         这里<b>不允许</b>用 {@link PropertyKey#DUST} 去材料表里反查，
     *         否则冰晶石矿石会被它的副产物钠粉顶替成“主产物”（主副产物颠倒的元凶）；</li>
     *     <li><b>宝石变体</b>：材料有 {@link PropertyKey#GEM} 时输出
     *         gem（普通）/ gemExquisite（精致）/ gemFlawless（无瑕）各 1 个；
     *         绝不输出 gemFlawed（有瑕）与 gemChipped（碎裂）；</li>
     *     <li><b>副产物粉</b>：遍历 {@code oreProperty.getOreByProducts()}，每个副产物
     *         {@code max(1, byProductMultiplier) * mainAmount} 个；</li>
     *     <li><b>槽位保护</b>：结果超过 {@link #OUTPUT_SLOTS} 个时从末尾截断。
     *         因为顺序已经是“主产物粉 → 宝石 → 副产物”，被截掉的只可能是副产物。</li>
     * </ol>
     *
     * <p>同一物品（registry@meta 相同）只占一个槽位，重复命中时数量累加。</p>
     */
    private static List<ItemStack> buildOutputs(OrePrefix prefix, Material material, OreProperty oreProperty) {
        List<ItemStack> outputs = new ArrayList<>(OUTPUT_SLOTS);

        // ── 1. 主产物粉：只认这个材料自己的粉 ─────────────────────────────────────
        // 绝不能写成「遍历材料表找第一个有 DUST 属性的材料」——那样冰晶石会变成钠粉。
        int mainAmount = Math.max(MIN_PRIMARY_AMOUNT,
                resolvePrimaryAmount(prefix, material, oreProperty, isDoubled(prefix)));
        ItemStack mainDust = OreDictUnifier.get(OrePrefix.dust, material);
        if (isValidStack(mainDust)) {
            addOutput(outputs, mainDust, mainAmount);
        } else if (material.hasProperty(PropertyKey.DUST)) {
            // 材料声明有粉、但这套 GT 没生成粉物品：如实告警，绝不用别的材料顶替
            GTLiteExtended.LOGGER.warn(
                    "[GTLite Extended] {} 声明有 DUST 属性，但 OrePrefix.dust 解析不到物品（{}）；"
                            + "该矿石本次没有主产物粉，不会用别的材料替代",
                    material.getName(), stackName(mainDust));
        }

        // ── 2. 宝石变体（同样属于主产物）：gem / gemExquisite / gemFlawless 各 1 ──
        // 绝不输出 gemFlawed / gemChipped。
        if (material.hasProperty(PropertyKey.GEM)) {
            addOutput(outputs, OreDictUnifier.get(OrePrefix.gem, material), 1);
            addOutput(outputs, OreDictUnifier.get(OrePrefix.gemExquisite, material), 1);
            addOutput(outputs, OreDictUnifier.get(OrePrefix.gemFlawless, material), 1);
        }

        // ── 3. 副产物粉：永远排在最后，数量 = max(1, 副产物倍率) × 主产物数量 ─────
        int byProductAmount = Math.max(1, oreProperty.getByProductMultiplier()) * mainAmount;
        for (Material byProduct : oreProperty.getOreByProducts()) {
            if (byProduct == null || byProduct == material) {
                // 副产物列表里偶尔会把自己再列一遍，跳过，避免把数量加到主产物上
                continue;
            }
            if (!byProduct.hasProperty(PropertyKey.DUST)) {
                continue;
            }
            addOutput(outputs, OreDictUnifier.get(OrePrefix.dust, byProduct), byProductAmount);
        }

        // ── 4. 槽位保护：顺序已固定，从末尾截断只会丢副产物 ───────────────────────
        if (outputs.size() > OUTPUT_SLOTS) {
            GTLiteExtended.LOGGER.warn("[GTLite Extended] {} 的产出 {} 种超过 {} 个槽位，末尾（副产物）被截断为 {} 种",
                    material.getName(), outputs.size(), OUTPUT_SLOTS, OUTPUT_SLOTS);
            return new ArrayList<>(outputs.subList(0, OUTPUT_SLOTS));
        }
        return outputs;
    }

    /**
     * 主产物数量：优先用 GT 研磨机配方里的普通产物数量（研磨机配方已包含下界岩/末地石的翻倍），
     * 读不到时退回 {@code oreMultiplier * 2}，红石等特殊矿石再取写死的保底值。
     */
    private static int resolvePrimaryAmount(OrePrefix prefix, Material material, OreProperty oreProperty, boolean doubled) {
        int fromMacerator = maceratorPrimaryAmount(prefix, material);
        if (fromMacerator > 0) {
            return fromMacerator;
        }
        int fallback = Math.max(1, oreProperty.getOreMultiplier()) * 2;
        Integer special = SPECIAL_PRIMARY_AMOUNTS.get(material);
        if (special != null) {
            fallback = Math.max(fallback, special);
        }
        return doubled ? fallback * DOUBLED_MULTIPLIER : fallback;
    }

    /**
     * 建立“矿石物品 → 研磨机主产物数量”索引。
     *
     * <p>只收录单输入、矿石字典输入的研磨机配方，避免把“锭→粉”之类的配方混进来。
     * GT 的矿石研磨配方就是 {@code inputs(ore)} + {@code outputs(crushed, round(oreMultiplier) * 2 * 翻倍)}。</p>
     */
    private static void indexMaceratorPrimaryAmounts() {
        MACERATOR_PRIMARY_AMOUNTS.clear();
        for (Recipe recipe : RecipeMaps.MACERATOR_RECIPES.getRecipeList()) {
            List<GTRecipeInput> inputs = recipe.getInputs();
            if (inputs.size() != 1) {
                continue;
            }
            GTRecipeInput input = inputs.get(0);
            if (!input.isOreDict()) {
                continue;
            }
            List<ItemStack> outputs = recipe.getOutputs();
            if (outputs.isEmpty()) {
                continue;
            }
            ItemStack primary = outputs.get(0);
            if (!isValidStack(primary) || primary.getCount() <= 0) {
                continue;
            }
            ItemStack[] stacks = input.getInputStacks();
            if (stacks == null) {
                continue;
            }
            for (ItemStack stack : stacks) {
                if (stack == null || stack.isEmpty()) {
                    continue;
                }
                // 同一种矿石可能挂在多个字典里，只认第一个（GT 自己的矿石配方）
                MACERATOR_PRIMARY_AMOUNTS.putIfAbsent(stackKey(stack), primary.getCount());
            }
        }
        GTLiteExtended.LOGGER.info("[GTLite Extended] 研磨机主产物索引: {} 种矿石物品", MACERATOR_PRIMARY_AMOUNTS.size());
    }

    /** 查研磨机里这种矿石的主产物数量；查不到返回 -1。 */
    private static int maceratorPrimaryAmount(OrePrefix prefix, Material material) {
        ItemStack oreStack = OreDictUnifier.get(prefix, material);
        if (oreStack.isEmpty()) {
            return -1;
        }
        Integer amount = MACERATOR_PRIMARY_AMOUNTS.get(stackKey(oreStack));
        return amount == null ? -1 : amount;
    }

    private static String stackKey(ItemStack stack) {
        ResourceLocation registryName = stack.getItem().getRegistryName();
        return registryName + "@" + stack.getMetadata();
    }

    /**
     * 追加一个旧兜底规则的产出：严格按调用顺序占槽（不排序、不重排），同一物品只占一个槽并累加数量；
     * 命名空间不合规（非 gregtech / gtlitecore / minecraft）的产出直接丢弃。
     *
     * <p>顺序即优先级，所以调用方（{@link #buildOutputs}）的调用次序就是槽位次序。</p>
     */
    private static void addOutput(List<ItemStack> outputs, ItemStack stack, int amount) {
        if (!isValidStack(stack)) {
            return;
        }
        int total = Math.max(1, stack.getCount()) * Math.max(1, amount);
        String key = stackKey(stack);
        for (ItemStack existing : outputs) {
            if (stackKey(existing).equals(key)) {
                existing.setCount(Math.min(existing.getCount() + total, existing.getMaxStackSize()));
                return;
            }
        }
        ItemStack copy = stack.copy();
        copy.setCount(Math.min(total, copy.getMaxStackSize()));
        outputs.add(copy);
    }

    /** 日志用：{@code registry@meta}，空栈给出 {@code (empty)}。 */
    private static String stackName(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return "(empty)";
        }
        ResourceLocation registryName = stack.getItem().getRegistryName();
        return (registryName == null ? "(no-registry)" : registryName.toString()) + "@" + stack.getMetadata();
    }

    /**
     * 旧兜底规则的产出白名单：只允许 {@code gregtech}、{@code gtlitecore} 与 {@code minecraft} 三个命名空间。
     *
     * <p>放行 minecraft 是为了让红石粉（{@code minecraft:redstone}）、煤（{@code minecraft:coal}）、
     * 绿宝石/钻石这类被 GT 统一到原版物品的材料也能正常出主产物；
     * 放行 gtlitecore 是因为冰晶石等材料由 gtlitecore 自己注册粉物品
     * （{@code gtlitecore:meta_dust@2041}），旧规则以前把它们当“别的模组物品”丢掉，
     * 冰晶石矿石才会只剩钠粉；
     * 同时把其他模组（林业、工业等）的材料彻底挡在外面。</p>
     */
    private static boolean isValidStack(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        ResourceLocation registryName = stack.getItem().getRegistryName();
        if (registryName == null) {
            return false;
        }
        String namespace = registryName.getNamespace();
        return GREGTECH_MODID.equals(namespace)
                || GTLITECORE_MODID.equals(namespace)
                || MINECRAFT_MODID.equals(namespace);
    }

    private static boolean isDoubled(OrePrefix prefix) {
        return prefix == OrePrefix.oreNetherrack || prefix == OrePrefix.oreEndstone;
    }
}
