package com.gtlite.extended.proxy;

import com.gtlite.extended.GTLiteExtended;
import com.gtlite.extended.Tags;
import com.gtlite.extended.client.jei.OreProcessingJeiPlugin;
import com.gtlite.extended.common.ModItems;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.metatileentity.registry.MTEManager;
import gregtech.api.metatileentity.registry.MTERegistry;
import gregtech.client.renderer.handler.MetaTileEntityRenderer;
import gregtech.integration.jei.multiblock.MultiblockInfoCategory;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;

public class ClientProxy extends CommonProxy {

    /** JEI 的 mod id（Had Enough Items 沿用 jei 这个 id）。 */
    private static final String JEI_MODID = "jei";

    /** 本 mod 注册的 MTE 顺序（meta id），与 MachineRegistration 保持一致（物品模型按这个顺序绑定）。 */
    private static final String[] MACHINE_IDS = {"ore_processor", "petrochemical_plant", "me_output_assembly", "me_input_assembly"};

    /** 需要出现在 JEI“多方块结构”页里的机器（只有控制器，部件不算）。 */
    private static final String[] MULTIBLOCK_IDS = {"ore_processor", "petrochemical_plant"};

    /**
     * ME 总成的物品模型。
     *
     * <p>GTCEu 1.12.2 里 ME 输出总线/输入总线<b>没有</b>独立的 JSON 模型
     * （不存在 {@code gregtech:me_output_bus#inventory} 这样的模型文件），
     * 总线外观是 {@code Textures.ME_OUTPUT_BUS / ME_INPUT_BUS} 覆盖贴图，
     * 物品统一由 GT 的动态 MTE 模型 {@link MetaTileEntityRenderer#MODEL_LOCATION}
     * （{@code gregtech:machine#normal}）按 MTE 自身贴图 + {@code renderMetaTileEntity}
     * 里叠的覆盖层来渲染。所以这里把两个 ME 总成指向 GT 的动态 MTE 模型，
     * 再由两个总成类各自叠 ME 输出/输入总线贴图，外观即与 GT 的 ME 总线一致。</p>
     */
    private static final ModelResourceLocation MTE_MODEL = MetaTileEntityRenderer.MODEL_LOCATION;

    /** 两个控制器沿用本 mod 自己的占位 JSON 模型。 */
    private static final String PLACEHOLDER_MODEL_ID = Tags.MOD_ID + ":mte";

    @Override
    public void preInit() {
        MTERegistry registry = MTEManager.getInstance().getRegistry(Tags.MOD_ID);
        if (registry != null) {
            Block block = registry.getBlock();
            if (block != null) {
                Item item = Item.getItemFromBlock(block);
                ModelResourceLocation placeholderModel = new ModelResourceLocation(PLACEHOLDER_MODEL_ID, "inventory");
                for (int meta = 0; meta < MACHINE_IDS.length; meta++) {
                    ModelLoader.setCustomModelResourceLocation(item, meta,
                            isMEAssembly(MACHINE_IDS[meta]) ? MTE_MODEL : placeholderModel);
                }
            }
        }

        // 多方块结构页必须在 JEI 启动（FMLLoadCompleteEvent 里的 JeiStarter）之前塞进
        // MultiblockInfoCategory.REGISTER：GT 的 JustEnoughItemsModule#register 会在启动时
        // 一次性读走这个列表，之后再 registerMultiblock 就没有任何效果了。
        for (String machineId : MULTIBLOCK_IDS) {
            registerMultiblock(machineId);
        }

        // 升级芯片的物品模型在 ItemModelRegistry（ModelRegistryEvent）里注册，
        // 1.12.2 只有这个时机能保证 Item 已经注册完毕、模型映射稳定生效。
        // 下面按排查要求打印注册名 / 翻译键 / 贴图资源是否存在。
        GTLiteExtended.LOGGER.info("[GTLite Extended] Registering chip advanced model");
        GTLiteExtended.LOGGER.info("[GTLite Extended] chip_advanced registryName={} translationKey={}",
                ModItems.CHIP_ADVANCED.getRegistryName(), ModItems.CHIP_ADVANCED.getTranslationKey());
        try {
            boolean textureExists;
            try {
                net.minecraft.client.Minecraft.getMinecraft().getResourceManager()
                        .getResource(new ResourceLocation(Tags.MOD_ID, "textures/items/chip_advanced.png"));
                textureExists = true;
            } catch (java.io.IOException notFound) {
                textureExists = false;
            }
            GTLiteExtended.LOGGER.info("[GTLite Extended] 贴图是否存在: {}", textureExists);
        } catch (Throwable t) {
            GTLiteExtended.LOGGER.warn("[GTLite Extended] 贴图资源检查失败", t);
        }
    }

    /** 是否是需要用 GT 动态 MTE 模型（总线外观）的 ME 总成。 */
    private static boolean isMEAssembly(String machineId) {
        return "me_output_assembly".equals(machineId) || "me_input_assembly".equals(machineId);
    }

    @Override
    public void onLoadComplete() {
        if (Loader.isModLoaded(JEI_MODID)) {
            // 配方是在 LoadComplete 生成的；如果 JEI 此时已经启动，就运行时补注册。
            OreProcessingJeiPlugin.syncRuntimeRecipes();
        }
        logChipDiagnostics();
    }

    /** 启动后打印芯片的显示名与贴图拼贴结果，便于直接在日志里确认模型/汉化是否生效。 */
    private static void logChipDiagnostics() {
        try {
            net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getMinecraft();
            GTLiteExtended.LOGGER.info("[GTLite Extended] chip names: '{}' / '{}'",
                    new net.minecraft.item.ItemStack(ModItems.CHIP_PERFECT_OC).getDisplayName(),
                    new net.minecraft.item.ItemStack(ModItems.CHIP_ADVANCED).getDisplayName());
            logChipSprite(mc, "gtlite_extended:items/chip_perfect_oc");
            logChipSprite(mc, "gtlite_extended:items/chip_advanced");
            logChipModel(mc, new net.minecraft.item.ItemStack(ModItems.CHIP_PERFECT_OC), "chip_perfect_oc");
            logChipModel(mc, new net.minecraft.item.ItemStack(ModItems.CHIP_ADVANCED), "chip_advanced");
            GTLiteExtended.LOGGER.info("[GTLite Extended] chip items same={} ids={}/{}",
                    ModItems.CHIP_PERFECT_OC == ModItems.CHIP_ADVANCED,
                    net.minecraft.item.Item.getIdFromItem(ModItems.CHIP_PERFECT_OC),
                    net.minecraft.item.Item.getIdFromItem(ModItems.CHIP_ADVANCED));
            logResourceJson(mc, "models/item/chip_perfect_oc.json");
            logResourceJson(mc, "models/item/chip_advanced.json");
        } catch (Throwable t) {
            GTLiteExtended.LOGGER.warn("[GTLite Extended] chip diagnostics failed", t);
        }
    }

    private static void logChipModel(net.minecraft.client.Minecraft mc,
                                     net.minecraft.item.ItemStack stack, String label) {
        net.minecraft.client.renderer.block.model.IBakedModel model =
                mc.getRenderItem().getItemModelMesher().getItemModel(stack);
        GTLiteExtended.LOGGER.info("[GTLite Extended] chip model {} ({}) -> class={} particle={} quads={}",
                label, stack.getItem().getRegistryName(), model.getClass().getName(),
                model.getParticleTexture().getIconName(), model.getQuads(null, null, 0).size());
    }

    private static void logResourceJson(net.minecraft.client.Minecraft mc, String path) {
        try {
            net.minecraft.util.ResourceLocation resource =
                    new ResourceLocation(Tags.MOD_ID, path);
            StringBuilder text = new StringBuilder();
            try (java.io.InputStream in = mc.getResourceManager().getResource(resource).getInputStream();
                 java.io.BufferedReader reader = new java.io.BufferedReader(
                         new java.io.InputStreamReader(in, java.nio.charset.StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    text.append(line.trim()).append(' ');
                }
            }
            GTLiteExtended.LOGGER.info("[GTLite Extended] resource {} -> {}", path, text);
        } catch (Throwable t) {
            GTLiteExtended.LOGGER.warn("[GTLite Extended] cannot read resource {}", path, t);
        }
    }

    private static void logChipSprite(net.minecraft.client.Minecraft mc, String texture) {
        net.minecraft.client.renderer.texture.TextureAtlasSprite sprite =
                mc.getTextureMapBlocks().getAtlasSprite(texture);
        GTLiteExtended.LOGGER.info("[GTLite Extended] chip texture {} -> {} (missing={})",
                texture, sprite.getIconName(), "missingno".equals(sprite.getIconName()));
    }

    private static void registerMultiblock(String machineId) {
        if (!Loader.isModLoaded(JEI_MODID)) {
            return;
        }
        MTERegistry registry = MTEManager.getInstance().getRegistry(Tags.MOD_ID);
        if (registry == null) {
            return;
        }
        MetaTileEntity metaTileEntity = registry.getObject(new ResourceLocation(Tags.MOD_ID, machineId));
        if (metaTileEntity instanceof MultiblockControllerBase) {
            MultiblockInfoCategory.registerMultiblock((MultiblockControllerBase) metaTileEntity);
            GTLiteExtended.LOGGER.info("[GTLite Extended] 已把 {} 结构注册进 JEI 多方块页", machineId);
        } else {
            GTLiteExtended.LOGGER.warn("[GTLite Extended] 找不到 {}，JEI 多方块页不会显示该结构", machineId);
        }
    }
}
