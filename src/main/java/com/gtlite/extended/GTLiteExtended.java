package com.gtlite.extended;

import com.gtlite.extended.common.MachineRegistration;
import com.gtlite.extended.common.network.ModNetwork;
import com.gtlite.extended.common.recipe.OreProcessingRecipeMaps;
import com.gtlite.extended.common.recipe.PetrochemicalRecipeMaps;
import com.gtlite.extended.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION,
        dependencies = "required-after:gregtech;required-after:gtlitecore"
)
public class GTLiteExtended {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    @SidedProxy(clientSide = "com.gtlite.extended.proxy.ClientProxy",
            serverSide = "com.gtlite.extended.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("[GTLite Extended] preInit");
        ModNetwork.register();
        MachineRegistration.register();
        // 石化配方只依赖 Materials / FluidRegistry，preInit 末尾这些都已经注册完毕，
        // 在这里补齐可以让 JEI 启动时直接看到 4 条石化配方。
        PetrochemicalRecipeMaps.init();
        proxy.preInit();
    }

    /**
     * 矿石处理配方在 LoadComplete 才生成：此时所有 mod 的 postInit 已结束，
     * GT 的 Material / OrePrefix 注册（runMaterialHandlers）也全部收尾，
     * 材料表与统一物品表才是最终状态。
     */
    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        OreProcessingRecipeMaps.init();
        LOGGER.info("[GTLite Extended] ore processing recipes: {}", OreProcessingRecipeMaps.recipeCount());
        // 客户端：JEI 若已经启动，就把新配方补注册进去
        proxy.onLoadComplete();
    }
}
