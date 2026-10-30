package com.gtlite.extended.common;

import com.gtlite.extended.GTLiteExtended;
import com.gtlite.extended.Tags;
import com.gtlite.extended.common.machine.MetaTileEntityMEAssembly;
import com.gtlite.extended.common.machine.MetaTileEntityMEInputAssembly;
import com.gtlite.extended.common.machine.MetaTileEntityMEOutputAssembly;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.registry.MTERegistry;
import net.minecraft.util.ResourceLocation;

/**
 * ME 总成注册：ME 输出总成（meta id 2）与 ME 库存输入总成（meta id 3）。
 *
 * <p>单独放一个类是为了“只在装了 AE2 时才加载 appeng.* 的类”：
 * {@link MachineRegistration} 用 {@code Loader.isModLoaded("appliedenergistics2")} 判断后
 * 才会调用这里，没装 AE2 时这两个总成类根本不会被类加载。</p>
 */
public final class MEAssemblyRegistration {

    private MEAssemblyRegistration() {
    }

    public static void register(MTERegistry registry) {
        MetaTileEntityMEOutputAssembly output = new MetaTileEntityMEOutputAssembly(
                new ResourceLocation(Tags.MOD_ID, "me_output_assembly"));
        register(registry, 2, output);

        MetaTileEntityMEInputAssembly input = new MetaTileEntityMEInputAssembly(
                new ResourceLocation(Tags.MOD_ID, "me_input_assembly"));
        register(registry, 3, input);

        GTLiteExtended.LOGGER.info("[GTLite Extended] 已注册 ME 输出总成（meta 2）与 ME 库存输入总成（meta 3）");
    }

    private static void register(MTERegistry registry, int id, MetaTileEntityMEAssembly assembly) {
        registry.register(id, assembly.metaTileEntityId, assembly);
        // 让 JEI 的多方块结构预览把这两个总成算作对应能力的候选
        for (MultiblockAbility<?> ability : assembly.getAbilities()) {
            MultiblockAbility.registerMultiblockAbility(ability, assembly);
        }
    }
}
