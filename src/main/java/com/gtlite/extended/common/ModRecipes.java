package com.gtlite.extended.common;

import com.gtlite.extended.Tags;
import gregtech.api.metatileentity.registry.MTEManager;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * 三条配方的排版完全一致，只替换等级材料：
 *
 * <pre>
 *   马达    活塞    机械臂
 *   立场    外壳    传感器
 *   传送带  泵      发射器
 * </pre>
 *
 * 矿石处理机 = HV，芯片 A = LuV，芯片 B = UHV。
 */
@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public final class ModRecipes {

    private ModRecipes() {
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        ItemStack machineStack = MTEManager.getInstance().getRegistry(Tags.MOD_ID)
                .getObject(new ResourceLocation(Tags.MOD_ID, "ore_processor"))
                .getStackForm();

        // ============ 矿石处理机（HV） ============
        registerTierRecipe(event, "ore_processor_recipe", machineStack,
                BlockMachineCasing.MachineCasingType.HV,
                MetaItems.ELECTRIC_MOTOR_HV.getStackForm(),
                MetaItems.ELECTRIC_PISTON_HV.getStackForm(),
                MetaItems.ROBOT_ARM_HV.getStackForm(),
                MetaItems.SENSOR_HV.getStackForm(),
                MetaItems.EMITTER_HV.getStackForm(),
                MetaItems.ELECTRIC_PUMP_HV.getStackForm(),
                MetaItems.CONVEYOR_MODULE_HV.getStackForm(),
                MetaItems.FIELD_GENERATOR_HV.getStackForm());

        // ============ 芯片 A（LuV） ============
        registerTierRecipe(event, "chip_perfect_oc_recipe", ModItems.CHIP_PERFECT_OC.asStack(),
                BlockMachineCasing.MachineCasingType.LuV,
                MetaItems.ELECTRIC_MOTOR_LuV.getStackForm(),
                MetaItems.ELECTRIC_PISTON_LUV.getStackForm(),
                MetaItems.ROBOT_ARM_LuV.getStackForm(),
                MetaItems.SENSOR_LuV.getStackForm(),
                MetaItems.EMITTER_LuV.getStackForm(),
                MetaItems.ELECTRIC_PUMP_LuV.getStackForm(),
                MetaItems.CONVEYOR_MODULE_LuV.getStackForm(),
                MetaItems.FIELD_GENERATOR_LuV.getStackForm());

        // ============ 芯片 B（UHV） ============
        registerTierRecipe(event, "chip_advanced_recipe", ModItems.CHIP_ADVANCED.asStack(),
                BlockMachineCasing.MachineCasingType.UHV,
                MetaItems.ELECTRIC_MOTOR_UHV.getStackForm(),
                MetaItems.ELECTRIC_PISTON_UHV.getStackForm(),
                MetaItems.ROBOT_ARM_UHV.getStackForm(),
                MetaItems.SENSOR_UHV.getStackForm(),
                MetaItems.EMITTER_UHV.getStackForm(),
                MetaItems.ELECTRIC_PUMP_UHV.getStackForm(),
                MetaItems.CONVEYOR_MODULE_UHV.getStackForm(),
                MetaItems.FIELD_GENERATOR_UHV.getStackForm());
    }

    /**
     * 统一排版的 3x3 配方：
     * <pre>
     *   M P R     马达   活塞   机械臂
     *   F C S     立场   外壳   传感器
     *   V U E     传送带 泵     发射器
     * </pre>
     */
    private static void registerTierRecipe(RegistryEvent.Register<IRecipe> event, String recipeName, ItemStack result,
                                           BlockMachineCasing.MachineCasingType casingType,
                                           ItemStack motor, ItemStack piston, ItemStack robotArm,
                                           ItemStack sensor, ItemStack emitter, ItemStack pump,
                                           ItemStack conveyor, ItemStack fieldGenerator) {
        ItemStack casing = MetaBlocks.MACHINE_CASING.getItemVariant(casingType);
        event.getRegistry().register(
                new ShapedOreRecipe(new ResourceLocation(Tags.MOD_ID, recipeName), result,
                        "MPR",
                        "FCS",
                        "VUE",
                        'M', motor,
                        'P', piston,
                        'R', robotArm,
                        'S', sensor,
                        'E', emitter,
                        'U', pump,
                        'V', conveyor,
                        'F', fieldGenerator,
                        'C', casing)
                        .setRegistryName(new ResourceLocation(Tags.MOD_ID, recipeName))
        );
    }
}
