package com.gtlite.extended.client.jei;

import com.gtlite.extended.GTLiteExtended;
import com.gtlite.extended.Tags;
import com.gtlite.extended.common.recipe.OreProcessingRecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.registry.MTEManager;
import gregtech.api.metatileentity.registry.MTERegistry;
import gregtech.api.recipes.Recipe;
import gregtech.integration.jei.multiblock.MultiblockInfoCategory;
import gregtech.integration.jei.recipe.GTRecipeWrapper;
import gregtech.integration.jei.recipe.RecipeMapCategory;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

/**
 * 把 {@link OreProcessingRecipeMaps#ORE_PROCESSING} 注册进 JEI。
 *
 * <p>这里有两件事必须说清楚（都是对着 GTCEu + Had Enough Items 的字节码确认过的）：</p>
 *
 * <ol>
 *     <li><b>传一个“活的 List”并不能让 JEI 自动刷新。</b>
 *     HEI 的 {@code ModRegistry.addRecipes(Collection, uid)} 会在插件 {@code register()} 被调用的当下
 *     立刻 {@code iterator()} 一遍、把元素塞进 {@code ListMultiMap}，之后再也不看这个集合。
 *     也就是说即使传入一个 size()/get() 都实时读 {@code getRecipeList()} 的包装类，
 *     只要 JEI 启动时配方池是空的，最终仍然是空列表。
 *     所以这里仍然使用了 {@link LiveRecipeList}（保证“读的是同一份池子”），
 *     但真正兜底的是 {@link #syncRuntimeRecipes()}：在配方生成之后，通过
 *     {@code IJeiRuntime.getRecipeRegistry().addRecipe(...)} 把条目补进已经建好的 JEI 注册表。</li>
 *
 *     <li><b>配方生成（FMLLoadCompleteEvent）和 JEI 启动（同样在 FMLLoadCompleteEvent）谁先谁后不确定。</b>
 *     如果本 mod 的 LoadComplete 处理器先跑，配方在 JEI 启动前就已经在池子里，
 *     GT 自己的 {@code JustEnoughItemsModule} 会把这张配方表（含分类、配方、机器催化剂）
 *     自动注册掉，这时本插件必须完全让路，否则会出现同一 uid 的分类/配方重复注册。
 *     反之若 JEI 先启动，池子是空的，GT 不会碰这张表，本插件就负责分类 + 运行时补注册。</li>
 * </ol>
 */
@JEIPlugin
public class OreProcessingJeiPlugin implements IModPlugin {

    /** 已经交给 JEI 的配方（按对象身份去重，避免重复 addRecipe）。 */
    private static final Set<Recipe> REGISTERED_RECIPES =
            Collections.newSetFromMap(new IdentityHashMap<Recipe, Boolean>());

    /** GT 多方块信息页的固定 UID（MultiblockInfoCategory.UID，只用于日志验证）。 */
    private static final String MULTIBLOCK_CATEGORY_UID = "gregtech.multiblock_info";

    private static IJeiRuntime jeiRuntime;

    /** true 表示“配方在 JEI 启动时就已经存在，GT 的 JEI 集成会自动处理这张表”。 */
    private static boolean handledByGregTech;

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        // 这个判断在“分类注册阶段”做一次就够了：JEI 的整个启动流程
        // （registerCategories -> register -> build）是一次同步调用，中间不会有配方生成插进来。
        handledByGregTech = !OreProcessingRecipeMaps.ORE_PROCESSING.getRecipesByCategory().isEmpty();

        if (handledByGregTech) {
            GTLiteExtended.LOGGER.info(
                    "[GTLite Extended] JEI: 配方在 JEI 启动前已生成（{} 条），交给 GT 的 JEI 集成自动注册",
                    OreProcessingRecipeMaps.recipeCount());
            return;
        }

        RecipeMapCategory category = new RecipeMapCategory(
                OreProcessingRecipeMaps.ORE_PROCESSING,
                OreProcessingRecipeMaps.CATEGORY,
                registry.getJeiHelpers().getGuiHelper());
        ItemStack catalyst = catalystStack();
        if (!catalyst.isEmpty()) {
            category.setIcon(catalyst);
        }
        registry.addRecipeCategories(category);

        GTLiteExtended.LOGGER.info("[GTLite Extended] JEI: 注册分类 {}（配方稍后运行时补注册）",
                OreProcessingRecipeMaps.CATEGORY.getUniqueID());
    }

    @Override
    public void register(IModRegistry registry) {
        if (handledByGregTech) {
            return;
        }
        String uid = OreProcessingRecipeMaps.CATEGORY.getUniqueID();

        ItemStack catalyst = catalystStack();
        if (!catalyst.isEmpty()) {
            registry.addRecipeCatalyst(catalyst, uid);
        }

        // 活视图：此刻通常还是空的（配方在 LoadComplete 才生成），
        // 但只要池子里已经有条目，JEI 立刻就能拿到，不会出现“两套配方”。
        registry.addRecipes(new LiveRecipeList(), uid);
        REGISTERED_RECIPES.addAll(OreProcessingRecipeMaps.ORE_PROCESSING.getRecipeList());
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        jeiRuntime = runtime;
        syncRuntimeRecipes();
        logJeiState(runtime.getRecipeRegistry());
    }

    /**
     * 把配方池里“JEI 还没拿到的”配方补注册进 JEI。由
     * {@code ClientProxy#onLoadComplete()} 在配方生成之后调用；若此时 JEI 运行时还没准备好，
     * {@link #onRuntimeAvailable(IJeiRuntime)} 会再调一次。
     */
    public static void syncRuntimeRecipes() {
        if (handledByGregTech || jeiRuntime == null) {
            return;
        }
        IRecipeRegistry recipeRegistry = jeiRuntime.getRecipeRegistry();
        String uid = OreProcessingRecipeMaps.CATEGORY.getUniqueID();

        // 快照遍历，避免注册过程中集合变化
        List<Recipe> recipes = new ArrayList<>(OreProcessingRecipeMaps.ORE_PROCESSING.getRecipeList());
        int added = 0;
        for (Recipe recipe : recipes) {
            if (!REGISTERED_RECIPES.add(recipe)) {
                continue;
            }
            recipeRegistry.addRecipe(
                    new GTRecipeWrapper(OreProcessingRecipeMaps.ORE_PROCESSING, recipe), uid);
            added++;
        }
        if (added > 0) {
            GTLiteExtended.LOGGER.info("[GTLite Extended] JEI: 运行时补注册 {} 条矿石处理配方（池内 {} 条）",
                    added, recipes.size());
        }
        // 反过来问 JEI 一遍：确认这些配方真的进了 JEI 的注册表，而不只是我们自己的池子
        GTLiteExtended.LOGGER.info("[GTLite Extended] JEI: 分类 {} 当前 JEI 可见配方 {} 条",
                uid, jeiRecipeCount(recipeRegistry, uid));
    }

    /** 打印多方块页的可见结构数，用来验证矿石处理机结构确实进了 JEI。 */
    private static void logJeiState(IRecipeRegistry recipeRegistry) {
        int multiblockCount = jeiRecipeCount(recipeRegistry, MULTIBLOCK_CATEGORY_UID);
        GTLiteExtended.LOGGER.info("[GTLite Extended] JEI: 多方块页可见 {} 个结构（已登记 {} 个）",
                multiblockCount, MultiblockInfoCategory.REGISTER.size());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static int jeiRecipeCount(IRecipeRegistry recipeRegistry, String categoryUid) {
        IRecipeCategory category = recipeRegistry.getRecipeCategory(categoryUid);
        if (category == null) {
            return -1;
        }
        List wrappers = recipeRegistry.getRecipeWrappers(category);
        return wrappers == null ? -1 : wrappers.size();
    }

    /** JEI 分类图标 / 机器催化剂：直接使用已注册的 MetaTileEntity 物品形态。 */
    private static ItemStack catalystStack() {
        MTERegistry registry = MTEManager.getInstance().getRegistry(Tags.MOD_ID);
        if (registry == null) {
            return ItemStack.EMPTY;
        }
        MetaTileEntity metaTileEntity = registry.getObject(new ResourceLocation(Tags.MOD_ID, "ore_processor"));
        if (metaTileEntity != null) {
            return metaTileEntity.getStackForm();
        }
        return registry.getBlock() == null ? ItemStack.EMPTY : new ItemStack(registry.getBlock());
    }

    /**
     * 配方池的实时 List 视图：size()/get() 每次都去问
     * {@code ORE_PROCESSING.getRecipeList()}，同时把结果包装成 GT 的 {@link GTRecipeWrapper}
     * （HEI 没有为裸 {@link Recipe} 注册 IRecipeHandler，直接传 Recipe 会被丢弃）。
     */
    private static final class LiveRecipeList extends AbstractList<GTRecipeWrapper> {

        private List<Recipe> snapshot;
        private int snapshotSize = -1;

        private List<Recipe> snapshot() {
            Collection<Recipe> live = OreProcessingRecipeMaps.ORE_PROCESSING.getRecipeList();
            int liveSize = live.size();
            if (snapshot == null || snapshotSize != liveSize) {
                snapshot = new ArrayList<>(live);
                snapshotSize = liveSize;
            }
            return snapshot;
        }

        @Override
        public int size() {
            return OreProcessingRecipeMaps.ORE_PROCESSING.getRecipeList().size();
        }

        @Override
        public GTRecipeWrapper get(int index) {
            return new GTRecipeWrapper(OreProcessingRecipeMaps.ORE_PROCESSING, snapshot().get(index));
        }
    }
}
