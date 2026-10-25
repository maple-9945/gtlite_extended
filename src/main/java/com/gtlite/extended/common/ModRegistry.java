package com.gtlite.extended.common;

import com.gtlite.extended.Tags;
import gregtech.api.metatileentity.registry.MTEManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * GT 在 CoreModule.preInit 里 post MTERegistryEvent；addon 在这个事件里创建自己的
 * MTERegistry，GT 随后（MetaBlocks.init）会为它创建并注册 modid:mte 方块。
 */
@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public final class ModRegistry {

    private ModRegistry() {
    }

    @SubscribeEvent
    public static void createMetaTileEntityRegistry(MTEManager.MTERegistryEvent event) {
        MTEManager.getInstance().createRegistry(Tags.MOD_ID);
    }
}
