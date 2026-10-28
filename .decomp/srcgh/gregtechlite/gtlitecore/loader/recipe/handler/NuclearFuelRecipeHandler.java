package gregtechlite.gtlitecore.loader.recipe.handler;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.DustProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtechlite.gtlitecore.api.GTLiteValues;
import gregtechlite.gtlitecore.api.extension.RecipeBuilderExtKt;
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix;
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\r"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/handler/NuclearFuelRecipeHandler;", "", "<init>", "()V", "init", "", "processFuelRod", "fuelRodPrefix", "Lgregtech/api/unification/ore/OrePrefix;", "material", "Lgregtech/api/unification/material/Material;", "property", "Lgregtech/api/unification/material/properties/DustProperty;", "gtlitecore"}
)
public final class NuclearFuelRecipeHandler {
   @NotNull
   public static final NuclearFuelRecipeHandler INSTANCE = new NuclearFuelRecipeHandler();

   private NuclearFuelRecipeHandler() {
   }

   public final void init() {
      GTLiteOrePrefix.fuelRod.addProcessingHandler(PropertyKey.DUST, this::processFuelRod);
   }

   private final void processFuelRod(OrePrefix fuelRodPrefix, Material material, DustProperty property) {
      RecipeMap var10000 = RecipeMaps.CANNER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10000, "CANNER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var10000, NuclearFuelRecipeHandler::processFuelRod$lambda$0);
      var10000 = RecipeMaps.PACKER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10000, "PACKER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var10000, NuclearFuelRecipeHandler::processFuelRod$lambda$1);
      var10000 = RecipeMaps.PACKER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10000, "PACKER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var10000, NuclearFuelRecipeHandler::processFuelRod$lambda$2);
      var10000 = RecipeMaps.PACKER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10000, "PACKER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var10000, NuclearFuelRecipeHandler::processFuelRod$lambda$3);
      var10000 = RecipeMaps.PACKER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10000, "PACKER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var10000, NuclearFuelRecipeHandler::processFuelRod$lambda$4);
      var10000 = RecipeMaps.PACKER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10000, "PACKER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var10000, NuclearFuelRecipeHandler::processFuelRod$lambda$5);
      var10000 = RecipeMaps.PACKER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10000, "PACKER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var10000, NuclearFuelRecipeHandler::processFuelRod$lambda$6);
      var10000 = RecipeMaps.PACKER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10000, "PACKER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var10000, NuclearFuelRecipeHandler::processFuelRod$lambda$7);
   }

   private static final Unit processFuelRod$lambda$0(Material $material, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input(GTLiteMetaItems.INSTANCE.getFUEL_ROD_EMPTY());
      $this$addRecipe.input(OrePrefix.dust, $material, 4);
      $this$addRecipe.output(GTLiteOrePrefix.fuelRod, $material);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[1]);
      $this$addRecipe.duration(GTLiteValues.getS(20));
      return Unit.INSTANCE;
   }

   private static final Unit processFuelRod$lambda$1(Material $material, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(1);
      $this$addRecipe.input(GTLiteOrePrefix.fuelRod, $material);
      $this$addRecipe.output(OrePrefix.dust, $material);
      $this$addRecipe.output(GTLiteMetaItems.INSTANCE.getFUEL_ROD_EMPTY());
      $this$addRecipe.EUt(7L);
      $this$addRecipe.duration(GTLiteValues.getS(7) + GTLiteValues.getT(10));
      return Unit.INSTANCE;
   }

   private static final Unit processFuelRod$lambda$2(Material $material, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(2);
      $this$addRecipe.input(GTLiteOrePrefix.fuelRod, $material, 2);
      $this$addRecipe.output(GTLiteOrePrefix.fuelRodEnriched, $material);
      $this$addRecipe.EUt(7L);
      $this$addRecipe.duration(GTLiteValues.getS(5));
      return Unit.INSTANCE;
   }

   private static final Unit processFuelRod$lambda$3(Material $material, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(1);
      $this$addRecipe.input(GTLiteOrePrefix.fuelRodEnriched, $material);
      $this$addRecipe.output(GTLiteOrePrefix.fuelRod, $material, 2);
      $this$addRecipe.EUt(7L);
      $this$addRecipe.duration(GTLiteValues.getS(7) + GTLiteValues.getT(10));
      return Unit.INSTANCE;
   }

   private static final Unit processFuelRod$lambda$4(Material $material, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(2);
      $this$addRecipe.input(GTLiteOrePrefix.fuelRodEnriched, $material, 2);
      $this$addRecipe.output(GTLiteOrePrefix.fuelRodHighDensity, $material);
      $this$addRecipe.EUt(7L);
      $this$addRecipe.duration(GTLiteValues.getS(5));
      return Unit.INSTANCE;
   }

   private static final Unit processFuelRod$lambda$5(Material $material, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(4);
      $this$addRecipe.input(GTLiteOrePrefix.fuelRod, $material, 4);
      $this$addRecipe.output(GTLiteOrePrefix.fuelRodHighDensity, $material);
      $this$addRecipe.EUt(7L);
      $this$addRecipe.duration(GTLiteValues.getS(10));
      return Unit.INSTANCE;
   }

   private static final Unit processFuelRod$lambda$6(Material $material, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(3);
      $this$addRecipe.input(GTLiteOrePrefix.fuelRodHighDensity, $material);
      $this$addRecipe.output(GTLiteOrePrefix.fuelRod, $material, 4);
      $this$addRecipe.EUt(7L);
      $this$addRecipe.duration(GTLiteValues.getS(12) + GTLiteValues.getT(10));
      return Unit.INSTANCE;
   }

   private static final Unit processFuelRod$lambda$7(Material $material, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(5);
      $this$addRecipe.input(GTLiteOrePrefix.fuelRodHighDensity, $material);
      $this$addRecipe.output(GTLiteOrePrefix.fuelRodEnriched, $material, 2);
      $this$addRecipe.EUt(7L);
      $this$addRecipe.duration(GTLiteValues.getS(7) + GTLiteValues.getT(10));
      return Unit.INSTANCE;
   }
}
