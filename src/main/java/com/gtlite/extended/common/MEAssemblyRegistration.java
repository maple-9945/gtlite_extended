package com.gtlite.extended.common;

import com.gtlite.extended.GTLiteExtended;
import com.gtlite.extended.Tags;
import com.gtlite.extended.common.machine.MetaTileEntityMEOutputAssembly;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.registry.MTERegistry;
import com.gtlite.extended.common.machine.MetaTileEntityMEInputAssembly;
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

        // 输入侧：单方块总成，物品逻辑继承 GT 库存输入总线，流体逻辑移植 GT 库存输入仓
        MetaTileEntityMEInputAssembly input = new MetaTileEntityMEInputAssembly(
                new ResourceLocation(Tags.MOD_ID, "me_input_assembly"));
        register(registry, 3, input);

        GTLiteExtended.LOGGER.info("[GTLite Extended] 已注册 ME 输出总成（meta 2）与 ME 库存输入总成（meta 3）");
    }

    private static void register(MTERegistry registry, int id, MetaTileEntity metaTileEntity) {
        registry.register(id, metaTileEntity.metaTileEntityId, metaTileEntity);
        // 让 JEI 的多方块结构预览把注册的部件算作对应能力的候选
        if (metaTileEntity instanceof IMultiblockAbilityPart) {
            for (MultiblockAbility<?> ability : ((IMultiblockAbilityPart<?>) metaTileEntity).getAbilities()) {
                MultiblockAbility.registerMultiblockAbility(ability, metaTileEntity);
            }
        }
    }
}
