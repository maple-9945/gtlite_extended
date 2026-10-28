package gregtechlite.gtlitecore.loader.recipe;

import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.AssemblyLineRecipeBuilder;
import gregtech.api.recipes.builders.FusionRecipeBuilder;
import gregtech.api.recipes.builders.ResearchRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.MarkerMaterials.Tier;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.items.MetaItems;
import gregtech.common.items.ToolItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.electric.MetaTileEntityHull;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityFusionReactor;
import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityAutoMaintenanceHatch;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityCleaningMaintenanceHatch;
import gregtech.loaders.recipe.CraftingComponent;
import gregtechlite.gtlitecore.api.GTLiteValues;
import gregtechlite.gtlitecore.api.extension.ItemStackExtKt;
import gregtechlite.gtlitecore.api.extension.RecipeBuilderExtKt;
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials;
import gregtechlite.gtlitecore.common.block.GTLiteBlocks;
import gregtechlite.gtlitecore.common.block.adapter.GTCleanroomCasing;
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing;
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems;
import gregtechlite.gtlitecore.common.item.GTLiteToolItems;
import gregtechlite.gtlitecore.loader.recipe.component.CraftingComponents;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/OverrideRecipeLoader;", "", "<init>", "()V", "init", "", "gtlitecore"}
)
public final class OverrideRecipeLoader {
   @NotNull
   public static final OverrideRecipeLoader INSTANCE = new OverrideRecipeLoader();

   private OverrideRecipeLoader() {
   }

   public final void init() {
      if (!ConfigHolder.recipes.nerfPaperCrafting) {
         ModHandler.removeRecipeByName("minecraft:paper");
         Item var10001 = Items.field_151121_aF;
         Intrinsics.checkNotNullExpressionValue(var10001, "PAPER");
         ItemStack var103 = ItemStackExtKt.stack$default(var10001, 0, 1, (Object)null);
         Object[] var1 = new Object[]{GTLiteToolItems.INSTANCE.getROLLING_PIN(), null};
         Item var10004 = Items.field_151120_aE;
         Intrinsics.checkNotNullExpressionValue(var10004, "REEDS");
         var1[1] = ItemStackExtKt.stack$default(var10004, 0, 1, (Object)null);
         ModHandler.addShapelessRecipe("paper", var103, var1);
         var103 = OreDictUnifier.get(OrePrefix.dust, Materials.Paper);
         var1 = new Object[]{ToolItems.MORTAR, null};
         var10004 = Items.field_151121_aF;
         Intrinsics.checkNotNullExpressionValue(var10004, "PAPER");
         var1[1] = ItemStackExtKt.stack$default(var10004, 0, 1, (Object)null);
         ModHandler.addShapelessRecipe("paper_dust", var103, var1);
      }

      ModHandler.removeRecipeByName("minecraft:sugar");
      Item var105 = Items.field_151102_aT;
      Intrinsics.checkNotNullExpressionValue(var105, "SUGAR");
      ItemStack var106 = ItemStackExtKt.stack$default(var105, 0, 1, (Object)null);
      FluidStack[] var8 = new Object[]{ToolItems.MORTAR, null};
      Item var205 = Items.field_151120_aE;
      Intrinsics.checkNotNullExpressionValue(var205, "REEDS");
      var8[1] = ItemStackExtKt.stack$default(var205, 0, 1, (Object)null);
      ModHandler.addShapelessRecipe("sugar", var106, var8);
      MetaItem.MetaValueItem var10002 = MetaItems.COMPRESSED_CLAY;
      Intrinsics.checkNotNullExpressionValue(var10002, "COMPRESSED_CLAY");
      ItemStack var134 = ItemStackExtKt.getStack(var10002, 8);
      var8 = new Object[]{"CCC", "CMC", "CCC", 'C', null, null, null};
      Item var10005 = Items.field_151119_aD;
      Intrinsics.checkNotNullExpressionValue(var10005, "CLAY_BALL");
      var8[4] = ItemStackExtKt.stack$default(var10005, 0, 1, (Object)null);
      var8[5] = 'M';
      var8[6] = MetaItems.WOODEN_FORM_BRICK;
      ModHandler.addShapedRecipe(true, "compressed_clay_brick", var134, var8);
      Item var10000 = Items.field_151119_aD;
      Intrinsics.checkNotNullExpressionValue(var10000, "CLAY_BALL");
      ModHandler.removeFurnaceSmelting(ItemStackExtKt.stack$default(var10000, 0, 1, (Object)null));
      MetaItem.MetaValueItem var61 = MetaItems.COMPRESSED_CLAY;
      Intrinsics.checkNotNullExpressionValue(var61, "COMPRESSED_CLAY");
      ItemStack var62 = ItemStackExtKt.stack(var61);
      Item var107 = Items.field_151118_aC;
      Intrinsics.checkNotNullExpressionValue(var107, "BRICK");
      ModHandler.addSmeltingRecipe(var62, ItemStackExtKt.stack$default(var107, 0, 1, (Object)null));
      ModHandler.addSmeltingRecipe(OreDictUnifier.get(OrePrefix.ingot, Materials.Iron), OreDictUnifier.get(OrePrefix.ingot, Materials.WroughtIron));
      RecipeMap var63 = RecipeMaps.FORGE_HAMMER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "FORGE_HAMMER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$0);
      var63 = RecipeMaps.CHEMICAL_BATH_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "CHEMICAL_BATH_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$1);
      var63 = RecipeMaps.ELECTROLYZER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ELECTROLYZER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$2);
      var63 = RecipeMaps.ELECTROLYZER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ELECTROLYZER_RECIPES");
      var8 = new FluidStack[]{Materials.Water.getFluid(1000)};
      RecipeBuilderExtKt.removeRecipe(var63, var8);
      var63 = RecipeMaps.ELECTROLYZER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ELECTROLYZER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$3);
      var63 = RecipeMaps.ASSEMBLER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLER_RECIPES");
      ItemStack[] var11 = new ItemStack[]{OreDictUnifier.get(OrePrefix.frameGt, Materials.Steel), OreDictUnifier.get(OrePrefix.plate, Materials.Polyethylene, 6)};
      FluidStack[] var12 = new FluidStack[]{Materials.Concrete.getFluid(144)};
      RecipeBuilderExtKt.removeRecipe(var63, var11, var12);
      var63 = RecipeMaps.ASSEMBLER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$4);

      for(int voltage = 1; voltage < 5; ++voltage) {
         StringBuilder var70 = (new StringBuilder()).append("gregtech:conveyor_module_");
         String var109 = GTValues.VN[voltage];
         Intrinsics.checkNotNullExpressionValue(var109, "get(...)");
         var109 = var109.toLowerCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var109, "toLowerCase(...)");
         ModHandler.removeRecipeByName(var70.append(var109).append("_rubber").toString());
         var70 = (new StringBuilder()).append("gregtech:conveyor_module_");
         var109 = GTValues.VN[voltage];
         Intrinsics.checkNotNullExpressionValue(var109, "get(...)");
         var109 = var109.toLowerCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var109, "toLowerCase(...)");
         ModHandler.removeRecipeByName(var70.append(var109).append("_silicone_rubber").toString());
         var70 = (new StringBuilder()).append("gregtech:conveyor_module_");
         var109 = GTValues.VN[voltage];
         Intrinsics.checkNotNullExpressionValue(var109, "get(...)");
         var109 = var109.toLowerCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var109, "toLowerCase(...)");
         ModHandler.removeRecipeByName(var70.append(var109).append("_styrene_butadiene_rubber").toString());
         StringBuilder var115 = (new StringBuilder()).append("conveyor_module.");
         String var135 = GTValues.VN[voltage];
         Intrinsics.checkNotNullExpressionValue(var135, "get(...)");
         var135 = var135.toLowerCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var135, "toLowerCase(...)");
         String var116 = var115.append(var135).toString();
         Object var137 = CraftingComponent.CONVEYOR.getIngredient(voltage);
         Intrinsics.checkNotNull(var137, "null cannot be cast to non-null type net.minecraft.item.ItemStack");
         ItemStack var138 = (ItemStack)var137;
         Material[] var2 = new Object[]{"PPP", "MCM", "PPP", 'M', CraftingComponent.MOTOR.getIngredient(voltage), 'P', "plateAnyRubber", 'C', null};
         OrePrefix var10007 = OrePrefix.cableGtSingle;
         Object var10008 = CraftingComponents.getCABLE_COMPONENT().getIngredient(voltage);
         Intrinsics.checkNotNull(var10008, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
         var2[8] = new UnificationEntry(var10007, ((UnificationEntry)var10008).material);
         ModHandler.addShapedRecipe(true, var116, var138, var2);
         Material[] var3 = new Material[]{Materials.Rubber, Materials.SiliconeRubber, Materials.StyreneButadieneRubber};
         var2 = var3;
         int var47 = 0;

         for(int var4 = var3.length; var47 < var4; ++var47) {
            Material rubber = var2[var47];
            RecipeMap var73 = RecipeMaps.ASSEMBLER_RECIPES;
            Intrinsics.checkNotNullExpressionValue(var73, "ASSEMBLER_RECIPES");
            ItemStack[] var6 = new ItemStack[3];
            Object var10003 = CraftingComponent.MOTOR.getIngredient(voltage);
            Intrinsics.checkNotNull(var10003, "null cannot be cast to non-null type net.minecraft.item.ItemStack");
            var6[0] = ItemStackExtKt.copy((ItemStack)var10003, 2);
            OrePrefix var153 = OrePrefix.cableGtSingle;
            Object var206 = CraftingComponents.getCABLE_COMPONENT().getIngredient(voltage);
            Intrinsics.checkNotNull(var206, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
            var6[1] = OreDictUnifier.get(var153, ((UnificationEntry)var206).material);
            var6[2] = IntCircuitIngredient.getIntegratedCircuit(1);
            FluidStack[] var59 = new FluidStack[]{rubber.getFluid(864)};
            RecipeBuilderExtKt.removeRecipe(var73, var6, var59);
         }

         RecipeMap var74 = RecipeMaps.ASSEMBLER_RECIPES;
         Intrinsics.checkNotNullExpressionValue(var74, "ASSEMBLER_RECIPES");
         RecipeBuilderExtKt.addRecipe(var74, OverrideRecipeLoader::init$lambda$5);
      }

      ModHandler.removeRecipeByName("gregtech:conveyor_module_iv_silicone_rubber");
      ModHandler.removeRecipeByName("gregtech:conveyor_module_iv_styrene_butadiene_rubber");
      MetaItem.MetaValueItem var139 = MetaItems.CONVEYOR_MODULE_IV;
      Intrinsics.checkNotNullExpressionValue(var139, "CONVEYOR_MODULE_IV");
      ItemStack var140 = ItemStackExtKt.stack(var139);
      Material[] var14 = new Object[]{"PPP", "MCM", "PPP", 'M', MetaItems.ELECTRIC_MOTOR_IV, 'P', "plateAnySyntheticRubber", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten)};
      ModHandler.addShapedRecipe(true, "conveyor_module.iv", var140, var14);
      Material[] var41 = new Material[]{Materials.SiliconeRubber, Materials.StyreneButadieneRubber};
      var14 = var41;
      int var42 = 0;

      for(int var48 = var41.length; var42 < var48; ++var42) {
         Material rubber = var14[var42];
         var63 = RecipeMaps.ASSEMBLER_RECIPES;
         Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLER_RECIPES");
         ItemStack[] rubber = new ItemStack[3];
         MetaItem.MetaValueItem var154 = MetaItems.ELECTRIC_MOTOR_IV;
         Intrinsics.checkNotNullExpressionValue(var154, "ELECTRIC_MOTOR_IV");
         rubber[0] = ItemStackExtKt.getStack(var154, 2);
         rubber[1] = OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Tungsten);
         rubber[2] = IntCircuitIngredient.getIntegratedCircuit(1);
         FluidStack[] var56 = new FluidStack[]{rubber.getFluid(864)};
         RecipeBuilderExtKt.removeRecipe(var63, rubber, var56);
      }

      var63 = RecipeMaps.ASSEMBLER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$6);

      for(int voltage = 1; voltage < 5; ++voltage) {
         StringBuilder var77 = (new StringBuilder()).append("gregtech:electric_pump_");
         String var119 = GTValues.VN[voltage];
         Intrinsics.checkNotNullExpressionValue(var119, "get(...)");
         var119 = var119.toLowerCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var119, "toLowerCase(...)");
         ModHandler.removeRecipeByName(var77.append(var119).append("_rubber").toString());
         var77 = (new StringBuilder()).append("gregtech:electric_pump_");
         var119 = GTValues.VN[voltage];
         Intrinsics.checkNotNullExpressionValue(var119, "get(...)");
         var119 = var119.toLowerCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var119, "toLowerCase(...)");
         ModHandler.removeRecipeByName(var77.append(var119).append("_silicone_rubber").toString());
         var77 = (new StringBuilder()).append("gregtech:electric_pump_");
         var119 = GTValues.VN[voltage];
         Intrinsics.checkNotNullExpressionValue(var119, "get(...)");
         var119 = var119.toLowerCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var119, "toLowerCase(...)");
         ModHandler.removeRecipeByName(var77.append(var119).append("_styrene_butadiene_rubber").toString());
         StringBuilder var125 = (new StringBuilder()).append("electric_pump.");
         String var141 = GTValues.VN[voltage];
         Intrinsics.checkNotNullExpressionValue(var141, "get(...)");
         var141 = var141.toLowerCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var141, "toLowerCase(...)");
         String var126 = var125.append(var141).toString();
         Object var143 = CraftingComponent.PUMP.getIngredient(voltage);
         Intrinsics.checkNotNull(var143, "null cannot be cast to non-null type net.minecraft.item.ItemStack");
         ItemStack var144 = (ItemStack)var143;
         Material[] var43 = new Object[15];
         var43[0] = "STR";
         var43[1] = "hPw";
         var43[2] = "RMC";
         var43[3] = 'S';
         OrePrefix var215 = OrePrefix.screw;
         Object var219 = CraftingComponent.ROTOR.getIngredient(voltage);
         Intrinsics.checkNotNull(var219, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
         var43[4] = new UnificationEntry(var215, ((UnificationEntry)var219).material);
         var43[5] = 'T';
         var215 = OrePrefix.rotor;
         var219 = CraftingComponent.ROTOR.getIngredient(voltage);
         Intrinsics.checkNotNull(var219, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
         var43[6] = new UnificationEntry(var215, ((UnificationEntry)var219).material);
         var43[7] = 'R';
         var43[8] = "ringAnyRubber";
         var43[9] = 'P';
         var215 = OrePrefix.pipeNormalFluid;
         var219 = CraftingComponent.PIPE_NORMAL.getIngredient(voltage);
         Intrinsics.checkNotNull(var219, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
         var43[10] = new UnificationEntry(var215, ((UnificationEntry)var219).material);
         var43[11] = 'M';
         Object var211 = CraftingComponent.MOTOR.getIngredient(voltage);
         Intrinsics.checkNotNull(var211, "null cannot be cast to non-null type net.minecraft.item.ItemStack");
         var43[12] = (Material)var211;
         var43[13] = 'C';
         var215 = OrePrefix.cableGtSingle;
         var219 = CraftingComponents.getCABLE_COMPONENT().getIngredient(voltage);
         Intrinsics.checkNotNull(var219, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
         var43[14] = new UnificationEntry(var215, ((UnificationEntry)var219).material);
         ModHandler.addShapedRecipe(true, var126, var144, var43);
         Material[] var49 = new Material[]{Materials.Rubber, Materials.SiliconeRubber, Materials.StyreneButadieneRubber};
         var43 = var49;
         int var50 = 0;

         for(int var53 = var49.length; var50 < var53; ++var50) {
            Material rubber = var43[var50];
            RecipeMap var80 = RecipeMaps.ASSEMBLER_RECIPES;
            Intrinsics.checkNotNullExpressionValue(var80, "ASSEMBLER_RECIPES");
            ItemStack[] var60 = new ItemStack[6];
            OrePrefix var155 = OrePrefix.cableGtSingle;
            Object var207 = CraftingComponents.getCABLE_COMPONENT().getIngredient(voltage);
            Intrinsics.checkNotNull(var207, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
            var60[0] = OreDictUnifier.get(var155, ((UnificationEntry)var207).material);
            var155 = OrePrefix.pipeNormalFluid;
            var207 = CraftingComponent.PIPE_NORMAL.getIngredient(voltage);
            Intrinsics.checkNotNull(var207, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
            var60[1] = OreDictUnifier.get(var155, ((UnificationEntry)var207).material);
            var155 = OrePrefix.screw;
            var207 = CraftingComponent.ROTOR.getIngredient(voltage);
            Intrinsics.checkNotNull(var207, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
            var60[2] = OreDictUnifier.get(var155, ((UnificationEntry)var207).material);
            var155 = OrePrefix.rotor;
            var207 = CraftingComponent.ROTOR.getIngredient(voltage);
            Intrinsics.checkNotNull(var207, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
            var60[3] = OreDictUnifier.get(var155, ((UnificationEntry)var207).material);
            var60[4] = OreDictUnifier.get(OrePrefix.ring, rubber, 2);
            Object var159 = CraftingComponent.MOTOR.getIngredient(voltage);
            Intrinsics.checkNotNull(var159, "null cannot be cast to non-null type net.minecraft.item.ItemStack");
            var60[5] = (ItemStack)var159;
            RecipeBuilderExtKt.removeRecipe(var80, var60);
         }

         RecipeMap var81 = RecipeMaps.ASSEMBLER_RECIPES;
         Intrinsics.checkNotNullExpressionValue(var81, "ASSEMBLER_RECIPES");
         RecipeBuilderExtKt.addRecipe(var81, OverrideRecipeLoader::init$lambda$7);
      }

      ModHandler.removeRecipeByName("gregtech:electric_pump_iv_silicone_rubber");
      ModHandler.removeRecipeByName("gregtech:electric_pump_iv_styrene_butadiene_rubber");
      MetaItem.MetaValueItem var145 = MetaItems.ELECTRIC_PUMP_IV;
      Intrinsics.checkNotNullExpressionValue(var145, "ELECTRIC_PUMP_IV");
      ItemStack var146 = ItemStackExtKt.stack(var145);
      Material[] var17 = new Object[]{"STR", "hPw", "RMC", 'S', new UnificationEntry(OrePrefix.screw, Materials.TungstenSteel), 'T', new UnificationEntry(OrePrefix.rotor, Materials.TungstenSteel), 'R', "ringAnySyntheticRubber", 'P', new UnificationEntry(OrePrefix.pipeNormalFluid, Materials.TungstenSteel), 'M', MetaItems.ELECTRIC_MOTOR_IV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten)};
      ModHandler.addShapedRecipe(true, "electric_pump.iv", var146, var17);
      Material[] var45 = new Material[]{Materials.SiliconeRubber, Materials.StyreneButadieneRubber};
      var17 = var45;
      int var46 = 0;

      for(int var51 = var45.length; var46 < var51; ++var46) {
         Material rubber = var17[var46];
         var63 = RecipeMaps.ASSEMBLER_RECIPES;
         Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLER_RECIPES");
         ItemStack[] var58 = new ItemStack[]{OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Tungsten), OreDictUnifier.get(OrePrefix.pipeNormalFluid, Materials.TungstenSteel), OreDictUnifier.get(OrePrefix.screw, Materials.TungstenSteel), OreDictUnifier.get(OrePrefix.rotor, Materials.TungstenSteel), OreDictUnifier.get(OrePrefix.ring, rubber, 2), null};
         MetaItem.MetaValueItem var160 = MetaItems.ELECTRIC_MOTOR_IV;
         Intrinsics.checkNotNullExpressionValue(var160, "ELECTRIC_MOTOR_IV");
         var58[5] = ItemStackExtKt.stack(var160);
         RecipeBuilderExtKt.removeRecipe(var63, var58);
      }

      var63 = RecipeMaps.ASSEMBLER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$8);
      var63 = RecipeMaps.ASSEMBLY_LINE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLY_LINE_RECIPES");
      ItemStack[] var19 = new ItemStack[]{GTFusionCasing.SUPERCONDUCTOR_COIL.getStack(), OreDictUnifier.get(OrePrefix.circuit, Tier.ZPM, 4), OreDictUnifier.get(OrePrefix.plateDouble, Materials.Plutonium241), OreDictUnifier.get(OrePrefix.plateDouble, Materials.Osmiridium), null, null, null};
      MetaItem.MetaValueItem var161 = MetaItems.FIELD_GENERATOR_IV;
      Intrinsics.checkNotNullExpressionValue(var161, "FIELD_GENERATOR_IV");
      var19[4] = ItemStackExtKt.getStack(var161, 2);
      var161 = MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT;
      Intrinsics.checkNotNullExpressionValue(var161, "ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT");
      var19[5] = ItemStackExtKt.getStack(var161, 64);
      var19[6] = OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.IndiumTinBariumTitaniumCuprate, 32);
      FluidStack[] var20 = new FluidStack[]{Materials.SolderingAlloy.getFluid(1152), Materials.NiobiumTitanium.getFluid(1152)};
      RecipeBuilderExtKt.removeRecipe(var63, var19, var20);
      var63 = RecipeMaps.ASSEMBLY_LINE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLY_LINE_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$10);
      var63 = RecipeMaps.ASSEMBLY_LINE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLY_LINE_RECIPES");
      var19 = new ItemStack[8];
      var19[0] = GTFusionCasing.FUSION_COIL.getStack();
      var19[1] = OreDictUnifier.get(OrePrefix.circuit, Tier.UV, 4);
      var19[2] = OreDictUnifier.get(OrePrefix.plateDouble, Materials.Naquadria);
      var19[3] = OreDictUnifier.get(OrePrefix.plateDouble, Materials.Europium);
      var161 = MetaItems.FIELD_GENERATOR_LuV;
      Intrinsics.checkNotNullExpressionValue(var161, "FIELD_GENERATOR_LuV");
      var19[4] = ItemStackExtKt.getStack(var161, 2);
      var161 = MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT;
      Intrinsics.checkNotNullExpressionValue(var161, "ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT");
      var19[5] = ItemStackExtKt.getStack(var161, 64);
      var161 = MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT;
      Intrinsics.checkNotNullExpressionValue(var161, "ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT");
      var19[6] = ItemStackExtKt.getStack(var161, 32);
      var19[7] = OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.UraniumRhodiumDinaquadide, 32);
      FluidStack[] var22 = new FluidStack[]{Materials.SolderingAlloy.getFluid(1152), Materials.VanadiumGallium.getFluid(1152)};
      RecipeBuilderExtKt.removeRecipe(var63, var19, var22);
      var63 = RecipeMaps.ASSEMBLY_LINE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLY_LINE_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$12);
      var63 = RecipeMaps.RESEARCH_STATION_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "RESEARCH_STATION_RECIPES");
      var19 = new ItemStack[2];
      var161 = MetaItems.TOOL_DATA_MODULE;
      Intrinsics.checkNotNullExpressionValue(var161, "TOOL_DATA_MODULE");
      var19[0] = ItemStackExtKt.stack(var161);
      MetaTileEntityFusionReactor var167 = MetaTileEntities.FUSION_REACTOR[1];
      Intrinsics.checkNotNullExpressionValue(var167, "get(...)");
      var19[1] = ItemStackExtKt.stack((MetaTileEntity)var167);
      RecipeBuilderExtKt.removeRecipe(var63, var19);
      var63 = RecipeMaps.ASSEMBLY_LINE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLY_LINE_RECIPES");
      var19 = new ItemStack[8];
      var19[0] = GTFusionCasing.FUSION_COIL.getStack();
      var19[1] = OreDictUnifier.get(OrePrefix.circuit, Tier.UHV, 4);
      MetaItem.MetaValueItem var168 = MetaItems.QUANTUM_STAR;
      Intrinsics.checkNotNullExpressionValue(var168, "QUANTUM_STAR");
      var19[2] = ItemStackExtKt.stack(var168);
      var19[3] = OreDictUnifier.get(OrePrefix.plateDouble, Materials.Americium);
      var168 = MetaItems.FIELD_GENERATOR_ZPM;
      Intrinsics.checkNotNullExpressionValue(var168, "FIELD_GENERATOR_ZPM");
      var19[4] = ItemStackExtKt.getStack(var168, 2);
      var168 = MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT;
      Intrinsics.checkNotNullExpressionValue(var168, "ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT");
      var19[5] = ItemStackExtKt.getStack(var168, 64);
      var168 = MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT;
      Intrinsics.checkNotNullExpressionValue(var168, "ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT");
      var19[6] = ItemStackExtKt.getStack(var168, 64);
      var19[7] = OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.EnrichedNaquadahTriniumEuropiumDuranide, 32);
      FluidStack[] var25 = new FluidStack[]{Materials.SolderingAlloy.getFluid(1152), Materials.YttriumBariumCuprate.getFluid(1152)};
      RecipeBuilderExtKt.removeRecipe(var63, var19, var25);
      var63 = RecipeMaps.ASSEMBLY_LINE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLY_LINE_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$14);
      var63 = RecipeMaps.FUSION_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "FUSION_RECIPES");
      FluidStack[] var26 = new FluidStack[]{Materials.Americium.getFluid(128), Materials.Naquadria.getFluid(128)};
      RecipeBuilderExtKt.removeRecipe(var63, var26);
      var63 = RecipeMaps.FUSION_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "FUSION_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$15);
      ModHandler.removeRecipeByName("gregtech:blacklight");
      MetaItem.MetaValueItem var147 = MetaItems.BLACKLIGHT;
      Intrinsics.checkNotNullExpressionValue(var147, "BLACKLIGHT");
      ItemStack var148 = ItemStackExtKt.stack(var147);
      ItemStack[] var27 = new Object[]{"SPS", "QEQ", "XPW", 'P', new UnificationEntry(OrePrefix.plate, Materials.TungstenCarbide), 'Q', new UnificationEntry(OrePrefix.plate, GTLiteMaterials.INSTANCE.getWoodsGlass()), 'E', new UnificationEntry(OrePrefix.spring, Materials.Europium), 'S', new UnificationEntry(OrePrefix.screw, Materials.TungstenCarbide), 'X', new UnificationEntry(OrePrefix.circuit, Tier.IV), 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Platinum)};
      ModHandler.addShapedRecipe(true, "blacklight", var148, var27);
      var63 = RecipeMaps.LARGE_CHEMICAL_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "LARGE_CHEMICAL_RECIPES");
      var27 = new ItemStack[]{OreDictUnifier.get(OrePrefix.ingot, Materials.Plutonium239, 8), OreDictUnifier.get(OrePrefix.dust, Materials.Uranium238)};
      FluidStack[] var29 = new FluidStack[]{Materials.Air.getFluid(10000)};
      RecipeBuilderExtKt.removeRecipe(var63, var27, var29);
      var63 = RecipeMaps.LARGE_CHEMICAL_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "LARGE_CHEMICAL_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$16);
      ModHandler.removeRecipeByName("gregtech:large_plasma_turbine");
      MetaTileEntityLargeTurbine var149 = MetaTileEntities.LARGE_PLASMA_TURBINE;
      Intrinsics.checkNotNullExpressionValue(var149, "LARGE_PLASMA_TURBINE");
      ItemStack var150 = ItemStackExtKt.stack((MetaTileEntity)var149);
      ItemStack[] var30 = new Object[11];
      var30[0] = "XGX";
      var30[1] = "GHG";
      var30[2] = "PGP";
      var30[3] = 'H';
      MetaTileEntityHull var212 = MetaTileEntities.HULL[5];
      Intrinsics.checkNotNullExpressionValue(var212, "get(...)");
      var30[4] = ItemStackExtKt.stack((MetaTileEntity)var212);
      var30[5] = 'G';
      var30[6] = new UnificationEntry(OrePrefix.gear, Materials.TungstenSteel);
      var30[7] = 'X';
      var30[8] = new UnificationEntry(OrePrefix.circuit, Tier.LuV);
      var30[9] = 'P';
      var30[10] = new UnificationEntry(OrePrefix.pipeLargeFluid, Materials.TungstenSteel);
      ModHandler.addShapedRecipe(true, "large_plasma_turbine", var150, var30);
      var63 = RecipeMaps.ASSEMBLY_LINE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLY_LINE_RECIPES");
      var30 = new ItemStack[13];
      var168 = MetaItems.ELITE_CIRCUIT_BOARD;
      Intrinsics.checkNotNullExpressionValue(var168, "ELITE_CIRCUIT_BOARD");
      var30[0] = ItemStackExtKt.stack(var168);
      var30[1] = OreDictUnifier.get(OrePrefix.plateDouble, Materials.Europium, 8);
      var30[2] = OreDictUnifier.get(OrePrefix.circuit, Tier.ZPM, 4);
      var168 = MetaItems.ENERGY_LAPOTRONIC_ORB_CLUSTER;
      Intrinsics.checkNotNullExpressionValue(var168, "ENERGY_LAPOTRONIC_ORB_CLUSTER");
      var30[3] = ItemStackExtKt.stack(var168);
      var168 = MetaItems.FIELD_GENERATOR_LuV;
      Intrinsics.checkNotNullExpressionValue(var168, "FIELD_GENERATOR_LuV");
      var30[4] = ItemStackExtKt.stack(var168);
      var168 = MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT;
      Intrinsics.checkNotNullExpressionValue(var168, "HIGH_POWER_INTEGRATED_CIRCUIT");
      var30[5] = ItemStackExtKt.getStack(var168, 32);
      var168 = MetaItems.ADVANCED_SMD_DIODE;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_DIODE");
      var30[6] = ItemStackExtKt.getStack(var168, 12);
      var168 = MetaItems.ADVANCED_SMD_CAPACITOR;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_CAPACITOR");
      var30[7] = ItemStackExtKt.getStack(var168, 12);
      var168 = MetaItems.ADVANCED_SMD_RESISTOR;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_RESISTOR");
      var30[8] = ItemStackExtKt.getStack(var168, 12);
      var168 = MetaItems.ADVANCED_SMD_TRANSISTOR;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_TRANSISTOR");
      var30[9] = ItemStackExtKt.getStack(var168, 12);
      var168 = MetaItems.ADVANCED_SMD_INDUCTOR;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_INDUCTOR");
      var30[10] = ItemStackExtKt.getStack(var168, 12);
      var30[11] = OreDictUnifier.get(OrePrefix.wireFine, Materials.Ruridit, 64);
      var30[12] = OreDictUnifier.get(OrePrefix.bolt, Materials.Trinium, 16);
      FluidStack[] var32 = new FluidStack[]{Materials.SolderingAlloy.getFluid(1440)};
      RecipeBuilderExtKt.removeRecipe(var63, var30, var32);
      var63 = RecipeMaps.ASSEMBLY_LINE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLY_LINE_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$18);
      var63 = RecipeMaps.RESEARCH_STATION_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "RESEARCH_STATION_RECIPES");
      var30 = new ItemStack[2];
      var168 = MetaItems.TOOL_DATA_MODULE;
      Intrinsics.checkNotNullExpressionValue(var168, "TOOL_DATA_MODULE");
      var30[0] = ItemStackExtKt.stack(var168);
      var168 = MetaItems.ENERGY_MODULE;
      Intrinsics.checkNotNullExpressionValue(var168, "ENERGY_MODULE");
      var30[1] = ItemStackExtKt.stack(var168);
      RecipeBuilderExtKt.removeRecipe(var63, var30);
      var63 = RecipeMaps.ASSEMBLY_LINE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLY_LINE_RECIPES");
      var30 = new ItemStack[13];
      var168 = MetaItems.WETWARE_CIRCUIT_BOARD;
      Intrinsics.checkNotNullExpressionValue(var168, "WETWARE_CIRCUIT_BOARD");
      var30[0] = ItemStackExtKt.stack(var168);
      var30[1] = OreDictUnifier.get(OrePrefix.plate, Materials.Americium, 16);
      var168 = MetaItems.WETWARE_SUPER_COMPUTER_UV;
      Intrinsics.checkNotNullExpressionValue(var168, "WETWARE_SUPER_COMPUTER_UV");
      var30[2] = ItemStackExtKt.getStack(var168, 4);
      var168 = MetaItems.ENERGY_MODULE;
      Intrinsics.checkNotNullExpressionValue(var168, "ENERGY_MODULE");
      var30[3] = ItemStackExtKt.stack(var168);
      var168 = MetaItems.FIELD_GENERATOR_ZPM;
      Intrinsics.checkNotNullExpressionValue(var168, "FIELD_GENERATOR_ZPM");
      var30[4] = ItemStackExtKt.stack(var168);
      var168 = MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT;
      Intrinsics.checkNotNullExpressionValue(var168, "ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT");
      var30[5] = ItemStackExtKt.getStack(var168, 32);
      var168 = MetaItems.ADVANCED_SMD_DIODE;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_DIODE");
      var30[6] = ItemStackExtKt.getStack(var168, 16);
      var168 = MetaItems.ADVANCED_SMD_CAPACITOR;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_CAPACITOR");
      var30[7] = ItemStackExtKt.getStack(var168, 16);
      var168 = MetaItems.ADVANCED_SMD_RESISTOR;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_RESISTOR");
      var30[8] = ItemStackExtKt.getStack(var168, 16);
      var168 = MetaItems.ADVANCED_SMD_TRANSISTOR;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_TRANSISTOR");
      var30[9] = ItemStackExtKt.getStack(var168, 16);
      var168 = MetaItems.ADVANCED_SMD_INDUCTOR;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_INDUCTOR");
      var30[10] = ItemStackExtKt.getStack(var168, 16);
      var30[11] = OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmiridium, 64);
      var30[12] = OreDictUnifier.get(OrePrefix.bolt, Materials.Naquadria, 16);
      FluidStack[] var35 = new FluidStack[]{Materials.SolderingAlloy.getFluid(2880), Materials.Polybenzimidazole.getFluid(576)};
      RecipeBuilderExtKt.removeRecipe(var63, var30, var35);
      var63 = RecipeMaps.ASSEMBLY_LINE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLY_LINE_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$20);
      var63 = RecipeMaps.RESEARCH_STATION_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "RESEARCH_STATION_RECIPES");
      var30 = new ItemStack[2];
      var168 = MetaItems.TOOL_DATA_MODULE;
      Intrinsics.checkNotNullExpressionValue(var168, "TOOL_DATA_MODULE");
      var30[0] = ItemStackExtKt.stack(var168);
      var168 = MetaItems.ENERGY_CLUSTER;
      Intrinsics.checkNotNullExpressionValue(var168, "ENERGY_CLUSTER");
      var30[1] = ItemStackExtKt.stack(var168);
      RecipeBuilderExtKt.removeRecipe(var63, var30);
      var63 = RecipeMaps.ASSEMBLY_LINE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLY_LINE_RECIPES");
      var30 = new ItemStack[13];
      var30[0] = OreDictUnifier.get(OrePrefix.plateDouble, Materials.Darmstadtium, 16);
      var30[1] = OreDictUnifier.get(OrePrefix.circuit, Tier.UHV, 4);
      var168 = MetaItems.ENERGY_CLUSTER;
      Intrinsics.checkNotNullExpressionValue(var168, "ENERGY_CLUSTER");
      var30[2] = ItemStackExtKt.getStack(var168, 16);
      var168 = MetaItems.FIELD_GENERATOR_UV;
      Intrinsics.checkNotNullExpressionValue(var168, "FIELD_GENERATOR_UV");
      var30[3] = ItemStackExtKt.getStack(var168, 4);
      var168 = MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER;
      Intrinsics.checkNotNullExpressionValue(var168, "ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER");
      var30[4] = ItemStackExtKt.getStack(var168, 64);
      var168 = MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER;
      Intrinsics.checkNotNullExpressionValue(var168, "ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER");
      var30[5] = ItemStackExtKt.getStack(var168, 64);
      var168 = MetaItems.ADVANCED_SMD_DIODE;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_DIODE");
      var30[6] = ItemStackExtKt.getStack(var168, 64);
      var168 = MetaItems.ADVANCED_SMD_CAPACITOR;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_CAPACITOR");
      var30[7] = ItemStackExtKt.getStack(var168, 64);
      var168 = MetaItems.ADVANCED_SMD_RESISTOR;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_RESISTOR");
      var30[8] = ItemStackExtKt.getStack(var168, 64);
      var168 = MetaItems.ADVANCED_SMD_TRANSISTOR;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_TRANSISTOR");
      var30[9] = ItemStackExtKt.getStack(var168, 64);
      var168 = MetaItems.ADVANCED_SMD_INDUCTOR;
      Intrinsics.checkNotNullExpressionValue(var168, "ADVANCED_SMD_INDUCTOR");
      var30[10] = ItemStackExtKt.getStack(var168, 64);
      var30[11] = OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.EnrichedNaquadahTriniumEuropiumDuranide, 64);
      var30[12] = OreDictUnifier.get(OrePrefix.bolt, Materials.Neutronium, 64);
      FluidStack[] var38 = new FluidStack[]{Materials.SolderingAlloy.getFluid(5760), Materials.Polybenzimidazole.getFluid(2304), Materials.Naquadria.getFluid(2592)};
      RecipeBuilderExtKt.removeRecipe(var63, var30, var38);
      var63 = RecipeMaps.ASSEMBLY_LINE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var63, "ASSEMBLY_LINE_RECIPES");
      RecipeBuilderExtKt.addRecipe(var63, OverrideRecipeLoader::init$lambda$22);
      ModHandler.removeRecipeByName("gregtech:maintenance_hatch_cleaning");
      MetaTileEntityCleaningMaintenanceHatch var151 = MetaTileEntities.CLEANING_MAINTENANCE_HATCH;
      Intrinsics.checkNotNullExpressionValue(var151, "CLEANING_MAINTENANCE_HATCH");
      ItemStack var152 = ItemStackExtKt.stack((MetaTileEntity)var151);
      Object[] var39 = new Object[13];
      var39[0] = "XAX";
      var39[1] = "RHR";
      var39[2] = "WXW";
      var39[3] = 'H';
      var212 = MetaTileEntities.HULL[5];
      Intrinsics.checkNotNullExpressionValue(var212, "get(...)");
      var39[4] = ItemStackExtKt.stack((MetaTileEntity)var212);
      var39[5] = 'A';
      MetaTileEntityAutoMaintenanceHatch var214 = MetaTileEntities.AUTO_MAINTENANCE_HATCH;
      Intrinsics.checkNotNullExpressionValue(var214, "AUTO_MAINTENANCE_HATCH");
      var39[6] = ItemStackExtKt.stack((MetaTileEntity)var214);
      var39[7] = 'R';
      var39[8] = MetaItems.ROBOT_ARM_IV;
      var39[9] = 'W';
      var39[10] = new UnificationEntry(OrePrefix.cableGtSingle, Materials.Platinum);
      var39[11] = 'X';
      var39[12] = new UnificationEntry(OrePrefix.circuit, Tier.IV);
      ModHandler.addShapedRecipe(true, "cleaning_maintenance_hatch", var152, var39);
   }

   private static final Unit init$lambda$0(SimpleRecipeBuilder $this$addRecipe) {
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilder var10000 = (RecipeBuilder)$this$addRecipe;
      BlockSand var10001 = Blocks.field_150354_m;
      Intrinsics.checkNotNullExpressionValue(var10001, "SAND");
      RecipeBuilderExtKt.inputs$default(var10000, (Block)var10001, 0, 0, 6, (Object)null);
      RecipeBuilderExtKt.outputs$default((RecipeBuilder)$this$addRecipe, GTLiteBlocks.INSTANCE.getDUST_BLOCK(), 0, 0, 6, (Object)null);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VH[1]);
      $this$addRecipe.duration(GTLiteValues.getT(10));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$1(SimpleRecipeBuilder $this$addRecipe) {
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.inputs$default((RecipeBuilder)$this$addRecipe, GTLiteBlocks.INSTANCE.getDUST_BLOCK(), 0, 0, 6, (Object)null);
      $this$addRecipe.fluidInputs(Materials.Water.getFluid(1000));
      RecipeBuilder var10000 = (RecipeBuilder)$this$addRecipe;
      Block var10001 = Blocks.field_150435_aG;
      Intrinsics.checkNotNullExpressionValue(var10001, "CLAY");
      RecipeBuilderExtKt.outputs$default(var10000, var10001, 0, 0, 6, (Object)null);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[1]);
      $this$addRecipe.duration(GTLiteValues.getS(10));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$2(SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input(OrePrefix.dust, Materials.Clay, 13);
      $this$addRecipe.output(OrePrefix.dust, Materials.Sodium, 2);
      $this$addRecipe.output(OrePrefix.dust, Materials.Lithium);
      $this$addRecipe.output(OrePrefix.dust, Materials.Aluminium, 2);
      $this$addRecipe.output(OrePrefix.dust, Materials.Silicon, 2);
      $this$addRecipe.fluidOutputs(Materials.Water.getFluid(6000));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[1]);
      $this$addRecipe.duration(GTLiteValues.getS(9) + GTLiteValues.getT(2));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$3(SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(1);
      $this$addRecipe.fluidInputs(Materials.Water.getFluid(1000));
      $this$addRecipe.fluidOutputs(Materials.Hydrogen.getFluid(2000));
      $this$addRecipe.fluidOutputs(Materials.Oxygen.getFluid(1000));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[1]);
      $this$addRecipe.duration(GTLiteValues.getS(15));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$4(SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(6);
      $this$addRecipe.input(OrePrefix.frameGt, Materials.Steel);
      $this$addRecipe.input(OrePrefix.plate, Materials.Polyethylene, 6);
      $this$addRecipe.fluidInputs(Materials.Concrete.getFluid(144));
      $this$addRecipe.outputs(GTCleanroomCasing.PLASCRETE.getStack(ConfigHolder.recipes.casingsPerCraft));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[1]);
      $this$addRecipe.duration(GTLiteValues.getS(2) + GTLiteValues.getT(10));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$5(int $voltage, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(1);
      OrePrefix var10001 = OrePrefix.cableGtSingle;
      Object var10002 = CraftingComponents.getCABLE_COMPONENT().getIngredient($voltage);
      Intrinsics.checkNotNull(var10002, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
      $this$addRecipe.input(var10001, ((UnificationEntry)var10002).material);
      Object var2 = CraftingComponent.MOTOR.getIngredient($voltage);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type net.minecraft.item.ItemStack");
      $this$addRecipe.inputs(ItemStackExtKt.copy((ItemStack)var2, 2));
      $this$addRecipe.input("plateAnyRubber", 6);
      var2 = CraftingComponent.CONVEYOR.getIngredient($voltage);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type net.minecraft.item.ItemStack");
      $this$addRecipe.outputs((ItemStack)var2);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[1]);
      $this$addRecipe.duration(GTLiteValues.getS(5));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$6(SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(1);
      $this$addRecipe.input(OrePrefix.cableGtSingle, Materials.Tungsten);
      $this$addRecipe.input(MetaItems.ELECTRIC_MOTOR_IV, 2);
      $this$addRecipe.input("plateAnySyntheticRubber", 6);
      $this$addRecipe.output(MetaItems.CONVEYOR_MODULE_IV);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[1]);
      $this$addRecipe.duration(GTLiteValues.getS(5));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$7(int $voltage, SimpleRecipeBuilder $this$addRecipe) {
      OrePrefix var10001 = OrePrefix.cableGtSingle;
      Object var10002 = CraftingComponents.getCABLE_COMPONENT().getIngredient($voltage);
      Intrinsics.checkNotNull(var10002, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
      $this$addRecipe.input(var10001, ((UnificationEntry)var10002).material);
      var10001 = OrePrefix.pipeNormalFluid;
      var10002 = CraftingComponent.PIPE_NORMAL.getIngredient($voltage);
      Intrinsics.checkNotNull(var10002, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
      $this$addRecipe.input(var10001, ((UnificationEntry)var10002).material);
      var10001 = OrePrefix.screw;
      var10002 = CraftingComponent.ROTOR.getIngredient($voltage);
      Intrinsics.checkNotNull(var10002, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
      $this$addRecipe.input(var10001, ((UnificationEntry)var10002).material);
      var10001 = OrePrefix.rotor;
      var10002 = CraftingComponent.ROTOR.getIngredient($voltage);
      Intrinsics.checkNotNull(var10002, "null cannot be cast to non-null type gregtech.api.unification.stack.UnificationEntry");
      $this$addRecipe.input(var10001, ((UnificationEntry)var10002).material);
      $this$addRecipe.input("ringAnyRubber", 2);
      Object var5 = CraftingComponent.MOTOR.getIngredient($voltage);
      Intrinsics.checkNotNull(var5, "null cannot be cast to non-null type net.minecraft.item.ItemStack");
      $this$addRecipe.inputs((ItemStack)var5);
      var5 = CraftingComponent.PUMP.getIngredient($voltage);
      Intrinsics.checkNotNull(var5, "null cannot be cast to non-null type net.minecraft.item.ItemStack");
      $this$addRecipe.outputs((ItemStack)var5);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[1]);
      $this$addRecipe.duration(GTLiteValues.getS(5));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$8(SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input(OrePrefix.cableGtSingle, Materials.Tungsten);
      $this$addRecipe.input(OrePrefix.pipeNormalFluid, Materials.TungstenSteel);
      $this$addRecipe.input(OrePrefix.screw, Materials.TungstenSteel);
      $this$addRecipe.input(OrePrefix.rotor, Materials.TungstenSteel);
      $this$addRecipe.input("ringAnySyntheticRubber", 2);
      $this$addRecipe.input(MetaItems.ELECTRIC_MOTOR_IV);
      $this$addRecipe.output(MetaItems.ELECTRIC_PUMP_IV);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[1]);
      $this$addRecipe.duration(GTLiteValues.getS(5));
      return Unit.INSTANCE;
   }

   private static final ResearchRecipeBuilder.ScannerRecipeBuilder init$lambda$10$lambda$9(ResearchRecipeBuilder.ScannerRecipeBuilder it) {
      ResearchRecipeBuilder var10000 = it.researchStack(OrePrefix.wireGtSingle, Materials.IndiumTinBariumTitaniumCuprate);
      Intrinsics.checkNotNullExpressionValue(var10000, "researchStack(...)");
      return ((ResearchRecipeBuilder.ScannerRecipeBuilder)RecipeBuilderExtKt.EUt(var10000, GTValues.VA[5])).duration(GTLiteValues.getMin(1));
   }

   private static final Unit init$lambda$10(AssemblyLineRecipeBuilder $this$addRecipe) {
      $this$addRecipe.inputs(GTFusionCasing.SUPERCONDUCTOR_COIL.getStack());
      $this$addRecipe.input(OrePrefix.circuit, Tier.ZPM, 4);
      $this$addRecipe.input(OrePrefix.plateDouble, Materials.Curium);
      $this$addRecipe.input(OrePrefix.plateDouble, Materials.Osmiridium);
      $this$addRecipe.input(MetaItems.FIELD_GENERATOR_IV, 2);
      $this$addRecipe.input(MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 64);
      $this$addRecipe.input(OrePrefix.wireGtSingle, Materials.IndiumTinBariumTitaniumCuprate, 32);
      $this$addRecipe.fluidInputs(Materials.SolderingAlloy.getFluid(1152));
      $this$addRecipe.fluidInputs(Materials.NiobiumTitanium.getFluid(1152));
      $this$addRecipe.output((MetaTileEntity)MetaTileEntities.FUSION_REACTOR[0]);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[6]);
      $this$addRecipe.duration(GTLiteValues.getS(30));
      $this$addRecipe.scannerResearch(OverrideRecipeLoader::init$lambda$10$lambda$9);
      return Unit.INSTANCE;
   }

   private static final ResearchRecipeBuilder.StationRecipeBuilder init$lambda$12$lambda$11(ResearchRecipeBuilder.StationRecipeBuilder it) {
      ResearchRecipeBuilder var10000 = it.researchStack((MetaTileEntity)MetaTileEntities.FUSION_REACTOR[0]);
      Intrinsics.checkNotNullExpressionValue(var10000, "researchStack(...)");
      return ((ResearchRecipeBuilder.StationRecipeBuilder)RecipeBuilderExtKt.EUt(var10000, GTValues.VA[7])).CWUt(16);
   }

   private static final Unit init$lambda$12(AssemblyLineRecipeBuilder $this$addRecipe) {
      $this$addRecipe.inputs(GTFusionCasing.FUSION_COIL.getStack());
      $this$addRecipe.input(OrePrefix.circuit, Tier.UV, 4);
      $this$addRecipe.input(OrePrefix.plateDouble, Materials.Einsteinium);
      $this$addRecipe.input(OrePrefix.plateDouble, Materials.Naquadria);
      $this$addRecipe.input(MetaItems.FIELD_GENERATOR_LuV, 2);
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getNANO_PIC_CHIP(), 64);
      $this$addRecipe.input(OrePrefix.wireGtSingle, Materials.UraniumRhodiumDinaquadide, 32);
      $this$addRecipe.fluidInputs(Materials.SolderingAlloy.getFluid(1152));
      $this$addRecipe.fluidInputs(Materials.VanadiumGallium.getFluid(1152));
      $this$addRecipe.output((MetaTileEntity)MetaTileEntities.FUSION_REACTOR[1]);
      $this$addRecipe.EUt(61440L);
      $this$addRecipe.duration(GTLiteValues.getMin(1));
      $this$addRecipe.stationResearch(OverrideRecipeLoader::init$lambda$12$lambda$11);
      return Unit.INSTANCE;
   }

   private static final ResearchRecipeBuilder.StationRecipeBuilder init$lambda$14$lambda$13(ResearchRecipeBuilder.StationRecipeBuilder it) {
      ResearchRecipeBuilder var10000 = it.researchStack((MetaTileEntity)MetaTileEntities.FUSION_REACTOR[1]);
      Intrinsics.checkNotNullExpressionValue(var10000, "researchStack(...)");
      return ((ResearchRecipeBuilder.StationRecipeBuilder)RecipeBuilderExtKt.EUt(var10000, GTValues.VA[8])).CWUt(32);
   }

   private static final Unit init$lambda$14(AssemblyLineRecipeBuilder $this$addRecipe) {
      $this$addRecipe.inputs(GTFusionCasing.FUSION_COIL.getStack());
      $this$addRecipe.input(OrePrefix.circuit, Tier.UHV, 4);
      $this$addRecipe.input(OrePrefix.plateDouble, Materials.Mendelevium);
      $this$addRecipe.input(OrePrefix.plateDouble, Materials.Rutherfordium);
      $this$addRecipe.input(MetaItems.FIELD_GENERATOR_ZPM, 2);
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getPICO_PIC_CHIP(), 64);
      $this$addRecipe.input(OrePrefix.wireGtSingle, Materials.EnrichedNaquadahTriniumEuropiumDuranide, 32);
      $this$addRecipe.fluidInputs(Materials.SolderingAlloy.getFluid(1152));
      $this$addRecipe.fluidInputs(Materials.YttriumBariumCuprate.getFluid(1152));
      $this$addRecipe.output((MetaTileEntity)MetaTileEntities.FUSION_REACTOR[2]);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[7]);
      $this$addRecipe.duration(GTLiteValues.getMin(1) + GTLiteValues.getS(30));
      $this$addRecipe.stationResearch(OverrideRecipeLoader::init$lambda$14$lambda$13);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$15(FusionRecipeBuilder $this$addRecipe) {
      $this$addRecipe.fluidInputs(Materials.Americium.getFluid(144));
      $this$addRecipe.fluidInputs(Materials.Naquadria.getFluid(144));
      $this$addRecipe.fluidOutputs(Materials.Neutronium.getFluid(72));
      $this$addRecipe.EUt(98304L);
      $this$addRecipe.duration(GTLiteValues.getS(10));
      $this$addRecipe.EUToStart(600000000L);
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$16(SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input(OrePrefix.ingot, GTLiteMaterials.INSTANCE.getPlutonium244(), 8);
      $this$addRecipe.input(OrePrefix.dust, Materials.Uranium238);
      $this$addRecipe.fluidInputs(Materials.Air.getFluid(10000));
      $this$addRecipe.output(OrePrefix.dust, GTLiteMaterials.INSTANCE.getPlutonium244(), 8);
      $this$addRecipe.fluidOutputs(Materials.Radon.getFluid(1000));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[3]);
      $this$addRecipe.duration(GTLiteValues.getMin(3) + GTLiteValues.getS(20));
      return Unit.INSTANCE;
   }

   private static final ResearchRecipeBuilder.StationRecipeBuilder init$lambda$18$lambda$17(ResearchRecipeBuilder.StationRecipeBuilder it) {
      ResearchRecipeBuilder var10000 = it.researchStack(MetaItems.ENERGY_LAPOTRONIC_ORB_CLUSTER);
      Intrinsics.checkNotNullExpressionValue(var10000, "researchStack(...)");
      return ((ResearchRecipeBuilder.StationRecipeBuilder)RecipeBuilderExtKt.EUt(var10000, GTValues.VA[6])).CWUt(16);
   }

   private static final Unit init$lambda$18(AssemblyLineRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input(MetaItems.ELITE_CIRCUIT_BOARD);
      $this$addRecipe.input(OrePrefix.plate, Materials.Rutherfordium, 8);
      $this$addRecipe.input(OrePrefix.circuit, Tier.ZPM, 4);
      $this$addRecipe.input(MetaItems.ENERGY_LAPOTRONIC_ORB_CLUSTER);
      $this$addRecipe.input(MetaItems.FIELD_GENERATOR_LuV);
      $this$addRecipe.input(MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 32);
      $this$addRecipe.input(MetaItems.ADVANCED_SMD_DIODE, 16);
      $this$addRecipe.input(MetaItems.ADVANCED_SMD_CAPACITOR, 16);
      $this$addRecipe.input(MetaItems.ADVANCED_SMD_RESISTOR, 16);
      $this$addRecipe.input(MetaItems.ADVANCED_SMD_TRANSISTOR, 16);
      $this$addRecipe.input(MetaItems.ADVANCED_SMD_INDUCTOR, 16);
      $this$addRecipe.input(OrePrefix.wireFine, Materials.Ruridit, 64);
      $this$addRecipe.input(OrePrefix.bolt, Materials.Trinium, 16);
      $this$addRecipe.fluidInputs(Materials.SolderingAlloy.getFluid(1440));
      $this$addRecipe.output(MetaItems.ENERGY_MODULE);
      $this$addRecipe.EUt(100000L);
      $this$addRecipe.duration(GTLiteValues.getMin(1));
      $this$addRecipe.stationResearch(OverrideRecipeLoader::init$lambda$18$lambda$17);
      return Unit.INSTANCE;
   }

   private static final ResearchRecipeBuilder.StationRecipeBuilder init$lambda$20$lambda$19(ResearchRecipeBuilder.StationRecipeBuilder it) {
      ResearchRecipeBuilder var10000 = it.researchStack(MetaItems.ENERGY_MODULE);
      Intrinsics.checkNotNullExpressionValue(var10000, "researchStack(...)");
      return ((ResearchRecipeBuilder.StationRecipeBuilder)RecipeBuilderExtKt.EUt(var10000, GTValues.VA[7])).CWUt(32);
   }

   private static final Unit init$lambda$20(AssemblyLineRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input(MetaItems.WETWARE_CIRCUIT_BOARD);
      $this$addRecipe.input(OrePrefix.plate, Materials.Dubnium, 8);
      $this$addRecipe.input(OrePrefix.circuit, Tier.UV, 4);
      $this$addRecipe.input(MetaItems.ENERGY_MODULE);
      $this$addRecipe.input(MetaItems.FIELD_GENERATOR_ZPM);
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getNANO_PIC_CHIP(), 32);
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getGOOWARE_SMD_DIODE(), 16);
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getGOOWARE_SMD_CAPACITOR(), 16);
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getGOOWARE_SMD_RESISTOR(), 16);
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getGOOWARE_SMD_TRANSISTOR(), 16);
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getGOOWARE_SMD_INDUCTOR(), 16);
      $this$addRecipe.input(OrePrefix.wireFine, Materials.Osmiridium, 64);
      $this$addRecipe.input(OrePrefix.bolt, Materials.Naquadria, 16);
      $this$addRecipe.fluidInputs(Materials.SolderingAlloy.getFluid(2880));
      $this$addRecipe.fluidInputs(Materials.Polybenzimidazole.getFluid(576));
      $this$addRecipe.output(MetaItems.ENERGY_CLUSTER);
      $this$addRecipe.EUt(200000L);
      $this$addRecipe.duration(GTLiteValues.getMin(1) + GTLiteValues.getS(30));
      $this$addRecipe.stationResearch(OverrideRecipeLoader::init$lambda$20$lambda$19);
      return Unit.INSTANCE;
   }

   private static final ResearchRecipeBuilder.StationRecipeBuilder init$lambda$22$lambda$21(ResearchRecipeBuilder.StationRecipeBuilder it) {
      ResearchRecipeBuilder var10000 = it.researchStack(MetaItems.ENERGY_CLUSTER);
      Intrinsics.checkNotNullExpressionValue(var10000, "researchStack(...)");
      return ((ResearchRecipeBuilder.StationRecipeBuilder)RecipeBuilderExtKt.EUt(var10000, GTValues.VA[9])).CWUt(48);
   }

   private static final Unit init$lambda$22(AssemblyLineRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getULTIMATE_CIRCUIT_BOARD());
      $this$addRecipe.input(OrePrefix.plateDouble, Materials.Bohrium, 16);
      $this$addRecipe.input(OrePrefix.circuit, Tier.UHV, 4);
      $this$addRecipe.input(MetaItems.ENERGY_CLUSTER, 16);
      $this$addRecipe.input(MetaItems.FIELD_GENERATOR_UV, 4);
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getPICO_PIC_WAFER(), 64);
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getOPTICAL_SMD_DIODE(), 32);
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getOPTICAL_SMD_CAPACITOR(), 32);
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getOPTICAL_SMD_RESISTOR(), 32);
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getOPTICAL_SMD_TRANSISTOR(), 32);
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getOPTICAL_SMD_INDUCTOR(), 32);
      $this$addRecipe.input(OrePrefix.wireGtSingle, Materials.EnrichedNaquadahTriniumEuropiumDuranide, 64);
      $this$addRecipe.input(OrePrefix.bolt, Materials.Neutronium, 64);
      $this$addRecipe.fluidInputs(Materials.SolderingAlloy.getFluid(5760));
      $this$addRecipe.fluidInputs(GTLiteMaterials.INSTANCE.getPolyetheretherketone().getFluid(2304));
      $this$addRecipe.fluidInputs(GTLiteMaterials.INSTANCE.getVibranium().getFluid(2592));
      $this$addRecipe.output(MetaItems.ULTIMATE_BATTERY);
      $this$addRecipe.EUt(800000L);
      $this$addRecipe.duration(GTLiteValues.getMin(2));
      $this$addRecipe.stationResearch(OverrideRecipeLoader::init$lambda$22$lambda$21);
      return Unit.INSTANCE;
   }
}
