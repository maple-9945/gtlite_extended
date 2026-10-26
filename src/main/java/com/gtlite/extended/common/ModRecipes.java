package com.gtlite.extended.common;

import com.gtlite.extended.Tags;
import gregtech.api.metatileentity.registry.MTEManager;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * 三条配方的排版完全一致，只替换中间方块 / 等级材料：
 *
 * <pre>
 *   马达    活塞    机械臂
 *   立场    中间    传感器
 *   传送带  泵      发射器
 * </pre>
 *
 * 矿石处理机 = 中间脱氧钢机械方块；芯片 A = 中间 LuV 外壳；芯片 B = 中间 UHV 外壳；
 * 石化工厂 = 中间 GT 蒸馏塔控制器。
 */
@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public final class ModRecipes {

    private ModRecipes() {
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        MTEManager mteManager = MTEManager.getInstance();

        ItemStack oreProcessor = mteManager.getRegistry(Tags.MOD_ID)
                .getObject(new ResourceLocation(Tags.MOD_ID, "ore_processor"))
                .getStackForm();
        ItemStack petrochemicalPlant = mteManager.getRegistry(Tags.MOD_ID)
                .getObject(new ResourceLocation(Tags.MOD_ID, "petrochemical_plant"))
                .getStackForm();

        // ============ 矿石处理机（中间：脱氧钢机械方块） ============
        registerTierRecipe(event, "ore_processor_recipe", oreProcessor,
                MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STEEL_SOLID),
                MetaItems.ELECTRIC_MOTOR_HV.getStackForm(),
                MetaItems.ELECTRIC_PISTON_HV.getStackForm(),
                MetaItems.ROBOT_ARM_HV.getStackForm(),
                MetaItems.SENSOR_HV.getStackForm(),
                MetaItems.EMITTER_HV.getStackForm(),
                MetaItems.ELECTRIC_PUMP_HV.getStackForm(),
                MetaItems.CONVEYOR_MODULE_HV.getStackForm(),
                MetaItems.FIELD_GENERATOR_HV.getStackForm());

        // ============ 石化工厂（中间：GT 蒸馏塔控制器） ============
        registerTierRecipe(event, "petrochemical_plant_recipe", petrochemicalPlant,
                MetaTileEntities.DISTILLATION_TOWER.getStackForm(),
                MetaItems.ELECTRIC_MOTOR_HV.getStackForm(),
                MetaItems.ELECTRIC_PISTON_HV.getStackForm(),
                MetaItems.ROBOT_ARM_HV.getStackForm(),
                MetaItems.SENSOR_HV.getStackForm(),
                MetaItems.EMITTER_HV.getStackForm(),
                MetaItems.ELECTRIC_PUMP_HV.getStackForm(),
                MetaItems.CONVEYOR_MODULE_HV.getStackForm(),
                MetaItems.FIELD_GENERATOR_HV.getStackForm());

        // ============ 芯片 A（中间：LuV 外壳） ============
        registerTierRecipe(event, "chip_perfect_oc_recipe", ModItems.CHIP_PERFECT_OC.asStack(),
                MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV),
                MetaItems.ELECTRIC_MOTOR_LuV.getStackForm(),
                MetaItems.ELECTRIC_PISTON_LUV.getStackForm(),
                MetaItems.ROBOT_ARM_LuV.getStackForm(),
                MetaItems.SENSOR_LuV.getStackForm(),
                MetaItems.EMITTER_LuV.getStackForm(),
                MetaItems.ELECTRIC_PUMP_LuV.getStackForm(),
                MetaItems.CONVEYOR_MODULE_LuV.getStackForm(),
                MetaItems.FIELD_GENERATOR_LuV.getStackForm());

        // ============ 芯片 B（中间：UHV 外壳） ============
        registerTierRecipe(event, "chip_advanced_recipe", ModItems.CHIP_ADVANCED.asStack(),
                MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UHV),
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
     *   F C S     立场   中间   传感器
     *   V U E     传送带 泵     发射器
     * </pre>
     */
    private static void registerTierRecipe(RegistryEvent.Register<IRecipe> event, String recipeName, ItemStack result,
                                           ItemStack center, ItemStack motor, ItemStack piston, ItemStack robotArm,
                                           ItemStack sensor, ItemStack emitter, ItemStack pump,
                                           ItemStack conveyor, ItemStack fieldGenerator) {
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
                        'C', center)
                        .setRegistryName(new ResourceLocation(Tags.MOD_ID, recipeName))
        );
    }
}
