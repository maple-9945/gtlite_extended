package gregtechlite.gtlitecore.loader.recipe.handler;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtechlite.gtlitecore.api.GTLiteValues;
import gregtechlite.gtlitecore.api.extension.RecipeBuilderExtKt;
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials;
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J \u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J \u0010\u000f\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\u0012"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/handler/OreRecipeHandler;", "", "<init>", "()V", "init", "", "processCrushedPurified", "purifiedPrefix", "Lgregtech/api/unification/ore/OrePrefix;", "material", "Lgregtech/api/unification/material/Material;", "property", "Lgregtech/api/unification/material/properties/OreProperty;", "processCrushedOre", "crushedPrefix", "processMetalSmelting", "doesMaterialUseNormalFurnace", "", "gtlitecore"}
)
public final class OreRecipeHandler {
   @NotNull
   public static final OreRecipeHandler INSTANCE = new OreRecipeHandler();

   private OreRecipeHandler() {
   }

   public final void init() {
      OrePrefix.crushedPurified.addProcessingHandler(PropertyKey.ORE, this::processCrushedPurified);
      if (ConfigHolder.worldgen.allUniqueStoneTypes) {
         GTLiteOrePrefix.oreLimestone.addProcessingHandler(PropertyKey.ORE, gregtech.loaders.recipe.handlers.OreRecipeHandler::processOre);
         GTLiteOrePrefix.oreKomatiite.addProcessingHandler(PropertyKey.ORE, gregtech.loaders.recipe.handlers.OreRecipeHandler::processOre);
         GTLiteOrePrefix.oreGreenSchist.addProcessingHandler(PropertyKey.ORE, gregtech.loaders.recipe.handlers.OreRecipeHandler::processOre);
         GTLiteOrePrefix.oreBlueSchist.addProcessingHandler(PropertyKey.ORE, gregtech.loaders.recipe.handlers.OreRecipeHandler::processOre);
         GTLiteOrePrefix.oreKimberlite.addProcessingHandler(PropertyKey.ORE, gregtech.loaders.recipe.handlers.OreRecipeHandler::processOre);
         GTLiteOrePrefix.oreQuartzite.addProcessingHandler(PropertyKey.ORE, gregtech.loaders.recipe.handlers.OreRecipeHandler::processOre);
         GTLiteOrePrefix.oreSlate.addProcessingHandler(PropertyKey.ORE, gregtech.loaders.recipe.handlers.OreRecipeHandler::processOre);
         GTLiteOrePrefix.oreShale.addProcessingHandler(PropertyKey.ORE, gregtech.loaders.recipe.handlers.OreRecipeHandler::processOre);
      }

      OrePrefix.crushed.addProcessingHandler(PropertyKey.ORE, this::processCrushedOre);
   }

   private final void processCrushedPurified(OrePrefix purifiedPrefix, Material material, OreProperty property) {
      ItemStack crushedCentrifugedStack = OreDictUnifier.get(OrePrefix.crushedCentrifuged, material);
      ItemStack dustStack = OreDictUnifier.get(OrePrefix.dustPure, material);
      Material var10000 = property.getOreByProduct(1, material);
      Intrinsics.checkNotNullExpressionValue(var10000, "getOreByProduct(...)");
      Material byproductMaterial = var10000;
      ItemStack byproductStack = OreDictUnifier.get(OrePrefix.dust, byproductMaterial);
      RecipeMap var15 = RecipeMaps.FORGE_HAMMER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var15, "FORGE_HAMMER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var15, OreRecipeHandler::processCrushedPurified$lambda$0);
      var15 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var15, "MACERATOR_RECIPES");
      RecipeBuilderExtKt.addRecipe(var15, OreRecipeHandler::processCrushedPurified$lambda$1);
      String var17 = "purified_ore_to_dust_" + material;
      ItemStack solitaryStack = new Object[]{'h', new UnificationEntry(purifiedPrefix, material)};
      ModHandler.addShapelessRecipe(var17, dustStack, solitaryStack);
      if (!crushedCentrifugedStack.func_190926_b()) {
         RecipeMap var18 = RecipeMaps.THERMAL_CENTRIFUGE_RECIPES;
         Intrinsics.checkNotNullExpressionValue(var18, "THERMAL_CENTRIFUGE_RECIPES");
         RecipeBuilderExtKt.addRecipe(var18, OreRecipeHandler::processCrushedPurified$lambda$2);
      }

      if (material.hasProperty(PropertyKey.GEM)) {
         solitaryStack = OreDictUnifier.get(GTLiteOrePrefix.gemSolitary, material);
         ItemStack exquisiteStack = OreDictUnifier.get(OrePrefix.gemExquisite, material);
         ItemStack flawlessStack = OreDictUnifier.get(OrePrefix.gemFlawless, material);
         ItemStack gemStack = OreDictUnifier.get(OrePrefix.gem, material);
         ItemStack flawedStack = OreDictUnifier.get(OrePrefix.gemFlawed, material);
         ItemStack chippedStack = OreDictUnifier.get(OrePrefix.gemChipped, material);
         if (material.hasFlag(MaterialFlags.HIGH_SIFTER_OUTPUT)) {
            RecipeMap var19 = RecipeMaps.SIFTER_RECIPES;
            Intrinsics.checkNotNullExpressionValue(var19, "SIFTER_RECIPES");
            RecipeBuilderExtKt.addRecipe(var19, OreRecipeHandler::processCrushedPurified$lambda$3);
            var19 = RecipeMaps.SIFTER_RECIPES;
            Intrinsics.checkNotNullExpressionValue(var19, "SIFTER_RECIPES");
            RecipeBuilderExtKt.addRecipe(var19, OreRecipeHandler::processCrushedPurified$lambda$4);
         } else {
            RecipeMap var21 = RecipeMaps.SIFTER_RECIPES;
            Intrinsics.checkNotNullExpressionValue(var21, "SIFTER_RECIPES");
            RecipeBuilderExtKt.addRecipe(var21, OreRecipeHandler::processCrushedPurified$lambda$5);
            var21 = RecipeMaps.SIFTER_RECIPES;
            Intrinsics.checkNotNullExpressionValue(var21, "SIFTER_RECIPES");
            RecipeBuilderExtKt.addRecipe(var21, OreRecipeHandler::processCrushedPurified$lambda$6);
         }
      }

      this.processMetalSmelting(purifiedPrefix, material, property);
   }

   private final void processCrushedOre(OrePrefix crushedPrefix, Material material, OreProperty property) {
      Material var10000 = property.getOreByProduct(0, material);
      Intrinsics.checkNotNullExpressionValue(var10000, "getOreByProduct(...)");
      Material byproductMaterial = var10000;
      ItemStack[] var6 = new ItemStack[]{OreDictUnifier.get(OrePrefix.crushedPurified, material), OreDictUnifier.get(OrePrefix.dust, material)};
      ItemStack var7 = GTUtility.copyFirst(var6);
      Intrinsics.checkNotNullExpressionValue(var7, "copyFirst(...)");
      ItemStack crushedPurifiedOre = var7;
      RecipeMap var8 = RecipeMaps.ORE_WASHER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var8, "ORE_WASHER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var8, OreRecipeHandler::processCrushedOre$lambda$7);
   }

   private final void processMetalSmelting(OrePrefix crushedPrefix, Material material, OreProperty property) {
      Material var10000 = property.getDirectSmeltResult();
      if (var10000 == null) {
         var10000 = material;
      }

      Material smeltingResult = var10000;
      if (smeltingResult.hasProperty(PropertyKey.INGOT)) {
         ItemStack ingotStack = OreDictUnifier.get(OrePrefix.ingot, smeltingResult);
         if (!ingotStack.func_190926_b() && this.doesMaterialUseNormalFurnace(smeltingResult)) {
            ModHandler.addSmeltingRecipe(new UnificationEntry(crushedPrefix, material), ingotStack, 0.5F);
         }
      }

   }

   private final boolean doesMaterialUseNormalFurnace(Material material) {
      return !material.hasProperty(PropertyKey.BLAST);
   }

   private static final Unit processCrushedPurified$lambda$0(OrePrefix $purifiedPrefix, Material $material, ItemStack $dustStack, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input($purifiedPrefix, $material);
      $this$addRecipe.outputs($dustStack);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VH[1]);
      $this$addRecipe.duration(GTLiteValues.getT(10));
      return Unit.INSTANCE;
   }

   private static final Unit processCrushedPurified$lambda$1(OrePrefix $purifiedPrefix, Material $material, ItemStack $dustStack, ItemStack $byproductStack, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input($purifiedPrefix, $material);
      $this$addRecipe.outputs($dustStack);
      $this$addRecipe.chancedOutput($byproductStack, 1400, 850);
      $this$addRecipe.duration(GTLiteValues.getS(20));
      return Unit.INSTANCE;
   }

   private static final Unit processCrushedPurified$lambda$2(OrePrefix $purifiedPrefix, Material $material, ItemStack $crushedCentrifugedStack, Material $byproductMaterial, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input($purifiedPrefix, $material);
      $this$addRecipe.outputs($crushedCentrifugedStack);
      $this$addRecipe.chancedOutput(OrePrefix.dust, $byproductMaterial, 3333, 0);
      return Unit.INSTANCE;
   }

   private static final Unit processCrushedPurified$lambda$3(OrePrefix $purifiedPrefix, Material $material, ItemStack $solitaryStack, ItemStack $exquisiteStack, ItemStack $flawlessStack, ItemStack $gemStack, ItemStack $flawedStack, ItemStack $chippedStack, ItemStack $dustStack, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(1);
      $this$addRecipe.input($purifiedPrefix, $material);
      $this$addRecipe.chancedOutput($solitaryStack, 100, 50);
      $this$addRecipe.chancedOutput($exquisiteStack, 500, 150);
      $this$addRecipe.chancedOutput($flawlessStack, 1500, 200);
      $this$addRecipe.chancedOutput($gemStack, 5000, 1000);
      $this$addRecipe.chancedOutput($flawedStack, 2000, 500);
      $this$addRecipe.chancedOutput($chippedStack, 3000, 350);
      $this$addRecipe.chancedOutput($dustStack, 2500, 500);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VH[1]);
      $this$addRecipe.duration(GTLiteValues.getS(20));
      return Unit.INSTANCE;
   }

   private static final Unit processCrushedPurified$lambda$4(OrePrefix $purifiedPrefix, Material $material, ItemStack $solitaryStack, ItemStack $exquisiteStack, ItemStack $flawlessStack, ItemStack $gemStack, ItemStack $flawedStack, ItemStack $chippedStack, ItemStack $dustStack, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(2);
      $this$addRecipe.input($purifiedPrefix, $material);
      $this$addRecipe.fluidInputs(GTLiteMaterials.INSTANCE.getZephyreanAerotheum().getFluid(250));
      $this$addRecipe.chancedOutput($solitaryStack, 200, 100);
      $this$addRecipe.chancedOutput($exquisiteStack, 1000, 300);
      $this$addRecipe.chancedOutput($flawlessStack, 3000, 400);
      $this$addRecipe.outputs($gemStack);
      $this$addRecipe.chancedOutput($flawedStack, 4000, 1000);
      $this$addRecipe.chancedOutput($chippedStack, 6000, 700);
      $this$addRecipe.chancedOutput($dustStack, 5000, 1000);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VH[1]);
      $this$addRecipe.duration(GTLiteValues.getS(10));
      return Unit.INSTANCE;
   }

   private static final Unit processCrushedPurified$lambda$5(OrePrefix $purifiedPrefix, Material $material, ItemStack $solitaryStack, ItemStack $exquisiteStack, ItemStack $flawlessStack, ItemStack $gemStack, ItemStack $flawedStack, ItemStack $chippedStack, ItemStack $dustStack, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(1);
      $this$addRecipe.input($purifiedPrefix, $material);
      $this$addRecipe.chancedOutput($solitaryStack, 50, 25);
      $this$addRecipe.chancedOutput($exquisiteStack, 300, 100);
      $this$addRecipe.chancedOutput($flawlessStack, 1000, 150);
      $this$addRecipe.chancedOutput($gemStack, 3500, 500);
      $this$addRecipe.chancedOutput($flawedStack, 2500, 300);
      $this$addRecipe.chancedOutput($chippedStack, 3500, 400);
      $this$addRecipe.chancedOutput($dustStack, 5000, 750);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VH[1]);
      $this$addRecipe.duration(GTLiteValues.getS(20));
      return Unit.INSTANCE;
   }

   private static final Unit processCrushedPurified$lambda$6(OrePrefix $purifiedPrefix, Material $material, ItemStack $solitaryStack, ItemStack $exquisiteStack, ItemStack $flawlessStack, ItemStack $gemStack, ItemStack $flawedStack, ItemStack $chippedStack, ItemStack $dustStack, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(2);
      $this$addRecipe.input($purifiedPrefix, $material);
      $this$addRecipe.fluidInputs(GTLiteMaterials.INSTANCE.getZephyreanAerotheum().getFluid(250));
      $this$addRecipe.chancedOutput($solitaryStack, 100, 50);
      $this$addRecipe.chancedOutput($exquisiteStack, 600, 200);
      $this$addRecipe.chancedOutput($flawlessStack, 2000, 300);
      $this$addRecipe.chancedOutput($gemStack, 7000, 1000);
      $this$addRecipe.chancedOutput($flawedStack, 5000, 600);
      $this$addRecipe.chancedOutput($chippedStack, 7000, 800);
      $this$addRecipe.outputs($dustStack);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VH[1]);
      $this$addRecipe.duration(GTLiteValues.getS(10));
      return Unit.INSTANCE;
   }

   private static final Unit processCrushedOre$lambda$7(OrePrefix $crushedPrefix, Material $material, ItemStack $crushedPurifiedOre, Material $byproductMaterial, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(3);
      $this$addRecipe.input($crushedPrefix, $material);
      $this$addRecipe.fluidInputs(GTLiteMaterials.INSTANCE.getTectonicPetrotheum().getFluid(100));
      $this$addRecipe.outputs($crushedPurifiedOre);
      $this$addRecipe.output(OrePrefix.dust, Materials.Stone);
      $this$addRecipe.chancedOutput(OrePrefix.dust, $byproductMaterial, 6666, 0);
      $this$addRecipe.duration(GTLiteValues.getS(5));
      return Unit.INSTANCE;
   }
}
