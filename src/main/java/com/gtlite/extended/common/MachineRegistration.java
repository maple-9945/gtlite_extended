package com.gtlite.extended.common;

import com.gtlite.extended.GTLiteExtended;
import com.gtlite.extended.Tags;
import com.gtlite.extended.common.machine.MetaTileEntityOreProcessor;
import com.gtlite.extended.common.machine.MetaTileEntityPetrochemicalPlant;
import gregtech.api.metatileentity.registry.MTEManager;
import gregtech.api.metatileentity.registry.MTERegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

public final class MachineRegistration {

    /** AE2（AE2 Unofficial Extended Life 沿用这个 modid）。 */
    private static final String AE2_MODID = "appliedenergistics2";

    private MachineRegistration() {
    }

    public static void register() {
        MTERegistry registry = MTEManager.getInstance().getRegistry(Tags.MOD_ID);
        if (registry == null) {
            GTLiteExtended.LOGGER.error("MTE registry not found for {}", Tags.MOD_ID);
            return;
        }

        ResourceLocation oreProcessorId = new ResourceLocation(Tags.MOD_ID, "ore_processor");
        registry.register(0, oreProcessorId, new MetaTileEntityOreProcessor(oreProcessorId));

        ResourceLocation petrochemicalId = new ResourceLocation(Tags.MOD_ID, "petrochemical_plant");
        registry.register(1, petrochemicalId, new MetaTileEntityPetrochemicalPlant(petrochemicalId));

        // ME 总成依赖 AE2 的类，注册逻辑单独放在 MEAssemblyRegistration 里：
        // 没装 AE2 时这个类不会被加载，也就不会碰到 appeng.*
        if (Loader.isModLoaded(AE2_MODID)) {
            MEAssemblyRegistration.register(registry);
        } else {
            GTLiteExtended.LOGGER.warn("[GTLite Extended] 未检测到 AE2（{}），跳过 ME 总成注册", AE2_MODID);
        }
    }
}
