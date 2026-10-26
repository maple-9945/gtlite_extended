package com.gtlite.extended.proxy;

import com.gtlite.extended.GTLiteExtended;
import com.gtlite.extended.Tags;
import com.gtlite.extended.client.jei.OreProcessingJeiPlugin;
import com.gtlite.extended.common.ModItems;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.metatileentity.registry.MTEManager;
import gregtech.api.metatileentity.registry.MTERegistry;
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

    /** 本 mod 注册的 MTE 顺序（meta id），与 MachineRegistration 保持一致。 */
    private static final String[] MACHINE_IDS = {"ore_processor", "petrochemical_plant"};

    @Override
    public void preInit() {
        MTERegistry registry = MTEManager.getInstance().getRegistry(Tags.MOD_ID);
        if (registry != null) {
            Block block = registry.getBlock();
            if (block != null) {
                Item item = Item.getItemFromBlock(block);
                ModelResourceLocation model = new ModelResourceLocation(Tags.MOD_ID + ":mte", "inventory");
                for (int meta = 0; meta < MACHINE_IDS.length; meta++) {
                    ModelLoader.setCustomModelResourceLocation(item, meta, model);
                }
            }
        }

        // 多方块结构页必须在 JEI 启动（FMLLoadCompleteEvent 里的 JeiStarter）之前塞进
        // MultiblockInfoCategory.REGISTER：GT 的 JustEnoughItemsModule#register 会在启动时
        // 一次性读走这个列表，之后再 registerMultiblock 就没有任何效果了。
        for (String machineId : MACHINE_IDS) {
            registerMultiblock(machineId);
        }

        // 升级芯片物品模型（assets/gtlite_extended/models/item/chip_*.json）
        ModelLoader.setCustomModelResourceLocation(ModItems.CHIP_PERFECT_OC, 0,
                new ModelResourceLocation(Tags.MOD_ID + ":chip_perfect_oc", "inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.CHIP_ADVANCED, 0,
                new ModelResourceLocation(Tags.MOD_ID + ":chip_advanced", "inventory"));
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
