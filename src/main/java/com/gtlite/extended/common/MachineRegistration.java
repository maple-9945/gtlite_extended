package com.gtlite.extended.common;

import com.gtlite.extended.Tags;
import com.gtlite.extended.common.machine.MetaTileEntityOreProcessor;
import gregtech.api.metatileentity.registry.MTEManager;
import gregtech.api.metatileentity.registry.MTERegistry;
import net.minecraft.util.ResourceLocation;

public final class MachineRegistration {

    private MachineRegistration() {
    }

    public static void register() {
        MTERegistry registry = MTEManager.getInstance().getRegistry(Tags.MOD_ID);
        if (registry == null) {
            com.gtlite.extended.GTLiteExtended.LOGGER.error("MTE registry not found for {}", Tags.MOD_ID);
            return;
        }
        ResourceLocation id = new ResourceLocation(Tags.MOD_ID, "ore_processor");
        registry.register(0, id, new MetaTileEntityOreProcessor(id));
    }
}
