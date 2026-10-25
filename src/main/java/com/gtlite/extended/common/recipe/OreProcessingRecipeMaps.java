package com.gtlite.extended.common.recipe;

import com.gtlite.extended.GTLiteExtended;
import gregtech.api.GregTechAPI;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.category.GTRecipeCategory;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ui.RecipeMapUI;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 矿石处理配方池。
 *
 * <p>规则（全mod只有这一套配方，运行时机器与 JEI 读的都是同一个 {@link #ORE_PROCESSING}）：</p>
 * <ul>
 *     <li>输入：任意 GT 矿石前缀（ore / oreGranite / oreNetherrack / oreEndstone ...）+ 1000mB 水；</li>
 *     <li>输出：有 GEM 属性的材料并列输出完整宝石谱系（碎裂/有瑕/普通/无瑕/精致，每种数量 = 研磨机粉碎数量），
 *         同时输出该材料的粉（数量同样是研磨机粉碎数量）；纯粉材料只出粉；
 *         副产物数量 = byProductMultiplier × 研磨机粉碎数量，100% 必出；</li>
 *     <li>主产物数量直接取 GT 研磨机（{@link RecipeMaps#MACERATOR_RECIPES}）对该矿石的普通产物数量：
 *         普通矿石 2 个、红石 10 个、下界岩/末地石矿石再翻倍（研磨机配方本身已经包含这个倍率）。
 *         读不到研磨机配方时退回 {@code oreMultiplier * 2}，红石等特殊矿石另有写死的保底值；</li>
 *     <li>产出只允许 {@code gregtech} 与 {@code minecraft} 两个命名空间（见 {@link #isValidStack}），
 *         其他模组的物品一律丢弃；没有合法产出的材料不生成配方。</li>
 * </ul>
 *
 * <p>生成时机：{@code FMLLoadCompleteEvent}（所有 mod 的 postInit 与 GT 的
 * {@code OrePrefix.runMaterialHandlers()} 都已完成，材料表与统一物品表才是最终状态）。
 * 因此配方一定晚于 {@code FMLPostInitializationEvent} 生成，JEI 侧由
 * {@code OreProcessingJeiPlugin} 做运行时补注册（详见该类的注释）。</p>
 */
public final class OreProcessingRecipeMaps {

    public static final int DURATION = 128;
    public static final int EUT = 30;

    /** 主产物 + 副产物的最大槽位数量，必须与配方表容量保持一致（宝石谱系 5 个 + 粉 1 个 + 副产物 3 个）。 */
    public static final int MAX_OUTPUTS = 9;

    /** 输入物品数 / 输入流体数。 */
    private static final int MAX_INPUTS = 1;
    private static final int MAX_FLUID_INPUTS = 1;
    private static final int MAX_FLUID_OUTPUTS = 0;

    private static final int WATER_AMOUNT = 1000;
    private static final String GREGTECH_MODID = "gregtech";
    private static final String MINECRAFT_MODID = "minecraft";

    /** 与既有行为保持一致：下界岩 / 末地石矿石的副产物翻倍（主产物的倍率已由研磨机配方给出）。 */
    private static final int DOUBLED_MULTIPLIER = 2;

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
            map -> new RecipeMapUI<>(map, true, true, true, true, false),
            MAX_INPUTS, MAX_OUTPUTS, MAX_FLUID_INPUTS, MAX_FLUID_OUTPUTS);

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

        int materials = 0;
        int attempted = 0;
        int registered = 0;
        int noGregTechOutput = 0;
        int failed = 0;

        List<OrePrefix> orePrefixes = collectOrePrefixes();
        List<Material> oreMaterials = collectOreMaterials();

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
                List<ItemStack> outputs = buildOutputs(prefix, material, oreProperty);
                if (outputs.isEmpty()) {
                    noGregTechOutput++;
                    continue;
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
                "[GTLite Extended] ore_processing: materials={} prefixes={} attempted={} registered={} failed={} dropped(no gregtech output)={} total={}",
                materials, orePrefixes.size(), attempted, registered, failed, noGregTechOutput, recipeCount());
        logPrimaryAmountSamples();
        return registered;
    }

    /** 打一行主产物倍率抽样，方便在日志里直接确认“红石给 10 个”“宝石同时给粉”这类修正。 */
    private static void logPrimaryAmountSamples() {
        GTLiteExtended.LOGGER.info(
                "[GTLite Extended] 主产物倍率抽样(ore): iron={} gold={} diamond={} lapis={} redstone={} coal={}",
                samplePrimaryAmount(Materials.Iron),
                samplePrimaryAmount(Materials.Gold),
                samplePrimaryAmount(Materials.Diamond),
                samplePrimaryAmount(Materials.Lapis),
                samplePrimaryAmount(Materials.Redstone),
                samplePrimaryAmount(Materials.Coal));
        GTLiteExtended.LOGGER.info("[GTLite Extended] 产出抽样(ore): emerald=[{}] diamond=[{}] iron=[{}] redstone=[{}]",
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
        StringBuilder builder = new StringBuilder();
        for (ItemStack stack : buildOutputs(OrePrefix.ore, material, oreProperty)) {
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

    /**
     * 由 Materials + OrePrefixes 直接推导某一材料矿石的全部产出（不再扫 GT 的机器配方表）。
     * 结果顺序固定：宝石谱系 → 粉 → 副产物。
     *
     * <p>核心倍率规则：先取该矿石的基准数量 {@code baseAmount} = GT 研磨机对该矿石的粉碎产出数量
     * （绿宝石 = 4、红石 = 10、普通矿石 = 2，下界岩/末地石矿石由研磨机配方本身给出翻倍值）。
     * 然后所有主产物都按 {@code baseAmount} 给：</p>
     * <ul>
     *     <li>有 GEM 属性：各等级宝石（碎裂/有瑕/普通/无瑕/精致）每种 {@code baseAmount} 个；</li>
     *     <li>有 DUST 属性：粉 {@code baseAmount} 个（宝石材料同样如此）；</li>
     *     <li>副产物：{@code byProductMultiplier * baseAmount} 个。</li>
     * </ul>
     * <p>GEM 和 DUST 用两个并列的 if 判断，绝不 else-if 短路，保证同时带 GEM + DUST 的材料两者都出。</p>
     */
    private static List<ItemStack> buildOutputs(OrePrefix prefix, Material material, OreProperty oreProperty) {
        boolean doubled = isDoubled(prefix);
        Map<String, ItemStack> merged = new LinkedHashMap<>();

        // 基准数量 = 该矿石在 GT 研磨机里的粉碎产出数量（绿宝石 4、红石 10、普通矿 2）
        int baseAmount = resolvePrimaryAmount(prefix, material, oreProperty, doubled);

        // 并列判断 1：宝石谱系，每个等级数量都 = baseAmount（取不到的等级会被 isValidStack 过滤）
        if (material.hasProperty(PropertyKey.GEM)) {
            for (OrePrefix gemTier : gemTiers()) {
                addOutput(merged, OreDictUnifier.get(gemTier, material), baseAmount);
            }
        }

        // 并列判断 2：粉，数量 = baseAmount（宝石材料也照此，不再单独特判）
        if (material.hasProperty(PropertyKey.DUST)) {
            addOutput(merged, OreDictUnifier.get(OrePrefix.dust, material), baseAmount);
        }

        // 副产物：每种副产物给对应材料的粉，数量 = byProductMultiplier * baseAmount
        int byProductAmount = Math.max(1, oreProperty.getByProductMultiplier()) * baseAmount;
        for (Material byProduct : oreProperty.getOreByProducts()) {
            if (byProduct == null || !byProduct.hasProperty(PropertyKey.DUST)) {
                continue;
            }
            addOutput(merged, OreDictUnifier.get(OrePrefix.dust, byProduct), byProductAmount);
        }

        List<ItemStack> outputs = new ArrayList<>(merged.values());
        if (outputs.size() > MAX_OUTPUTS) {
            // 超过配方表容量的部分直接丢弃，避免 postValidateRecipe 把整条配方拒收
            GTLiteExtended.LOGGER.debug("[GTLite Extended] {} 的产出超过 {} 个，多余的被丢弃",
                    material.getName(), MAX_OUTPUTS);
            outputs = new ArrayList<>(outputs.subList(0, MAX_OUTPUTS));
        }
        return outputs;
    }

    /**
     * 完整宝石谱系。GTCEu 1.12.2 并没有 {@code gemRare}，实际等级就是这五个：
     * gemChipped（碎裂）→ gemFlawed（有瑕）→ gem（普通/原版）→ gemFlawless（无瑕）→ gemExquisite（精致）。
     *
     * <p>每个等级都尝试取一次：绿宝石/钻石的普通 {@code gem} 是原版物品（{@code minecraft:emerald}，
     * 白名单允许保留），高阶等级由 GT 生成（{@code gregtech:meta_gem_flawless} 等）；
     * 取不到或命名空间不合规的等级会被 {@link #addOutput} 直接丢弃。</p>
     */
    private static OrePrefix[] gemTiers() {
        return new OrePrefix[]{
                OrePrefix.gemChipped,
                OrePrefix.gemFlawed,
                OrePrefix.gem,
                OrePrefix.gemFlawless,
                OrePrefix.gemExquisite
        };
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

    /** 合并同物品产出；命名空间不合规的产出直接丢弃。 */
    private static void addOutput(Map<String, ItemStack> outputs, ItemStack stack, int amount) {
        if (!isValidStack(stack)) {
            return;
        }
        int total = stack.getCount() * Math.max(1, amount);
        String key = stack.getItem().getRegistryName() + "@" + stack.getMetadata();
        ItemStack existing = outputs.get(key);
        if (existing == null) {
            ItemStack copy = stack.copy();
            copy.setCount(Math.min(total, copy.getMaxStackSize()));
            outputs.put(key, copy);
        } else {
            existing.setCount(Math.min(existing.getCount() + total, existing.getMaxStackSize()));
        }
    }

    /**
     * 白名单校验：只允许 {@code gregtech} 与 {@code minecraft} 两个命名空间。
     *
     * <p>放行 minecraft 是为了让红石粉（{@code minecraft:redstone}）、煤（{@code minecraft:coal}）、
     * 绿宝石/钻石这类被 GT 统一到原版物品的材料也能正常出主产物；
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
        return GREGTECH_MODID.equals(namespace) || MINECRAFT_MODID.equals(namespace);
    }

    private static boolean isDoubled(OrePrefix prefix) {
        return prefix == OrePrefix.oreNetherrack || prefix == OrePrefix.oreEndstone;
    }
}
