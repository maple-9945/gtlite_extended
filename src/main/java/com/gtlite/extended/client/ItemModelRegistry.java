package com.gtlite.extended.client;

import com.gtlite.extended.GTLiteExtended;
import com.gtlite.extended.Tags;
import com.gtlite.extended.common.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * 芯片物品模型注册：放在 {@link ModelRegistryEvent} 里（1.12.2 的标准时机），
 * 此时所有 Item 已经注册完毕，模型映射才能稳定生效。
 */
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Tags.MOD_ID)
public final class ItemModelRegistry {

    private ItemModelRegistry() {
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
        GTLiteExtended.LOGGER.info("[GTLite Extended] Registering chip advanced model");
        ModelLoader.setCustomModelResourceLocation(ModItems.CHIP_PERFECT_OC, 0,
                new ModelResourceLocation(Tags.MOD_ID + ":chip_perfect_oc", "inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.CHIP_ADVANCED, 0,
                new ModelResourceLocation(Tags.MOD_ID + ":chip_advanced", "inventory"));
        GTLiteExtended.LOGGER.info("[GTLite Extended] chip advanced registryName={} translationKey={}",
                ModItems.CHIP_ADVANCED.getRegistryName(),
                ModItems.CHIP_ADVANCED.getTranslationKey());
    }
}
