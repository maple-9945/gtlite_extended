package com.gtlite.extended.proxy;

import com.gtlite.extended.GTLiteExtended;
import com.gtlite.extended.Tags;
import com.gtlite.extended.client.jei.OreProcessingJeiPlugin;
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

    @Override
    public void preInit() {
        MTERegistry registry = MTEManager.getInstance().getRegistry(Tags.MOD_ID);
        if (registry != null) {
            Block block = registry.getBlock();
            if (block != null) {
                Item item = Item.getItemFromBlock(block);
                ModelLoader.setCustomModelResourceLocation(item, 0,
                        new ModelResourceLocation(Tags.MOD_ID + ":mte", "inventory"));
            }
        }

        // 多方块结构页必须在 JEI 启动（FMLLoadCompleteEvent 里的 JeiStarter）之前塞进
        // MultiblockInfoCategory.REGISTER：GT 的 JustEnoughItemsModule#register 会在启动时
        // 一次性读走这个列表，之后再 registerMultiblock 就没有任何效果了。
        registerOreProcessorMultiblock();
    }

    @Override
    public void onLoadComplete() {
        if (Loader.isModLoaded(JEI_MODID)) {
            // 配方是在 LoadComplete 生成的；如果 JEI 此时已经启动，就运行时补注册。
            OreProcessingJeiPlugin.syncRuntimeRecipes();
        }
    }

    private static void registerOreProcessorMultiblock() {
        if (!Loader.isModLoaded(JEI_MODID)) {
            return;
        }
        MTERegistry registry = MTEManager.getInstance().getRegistry(Tags.MOD_ID);
        if (registry == null) {
            return;
        }
        MetaTileEntity metaTileEntity = registry.getObject(new ResourceLocation(Tags.MOD_ID, "ore_processor"));
        if (metaTileEntity instanceof MultiblockControllerBase) {
            MultiblockInfoCategory.registerMultiblock((MultiblockControllerBase) metaTileEntity);
            GTLiteExtended.LOGGER.info("[GTLite Extended] 已把矿石处理机结构注册进 JEI 多方块页");
        } else {
            GTLiteExtended.LOGGER.warn("[GTLite Extended] 找不到矿石处理机，JEI 多方块页不会显示该结构");
        }
    }
}
