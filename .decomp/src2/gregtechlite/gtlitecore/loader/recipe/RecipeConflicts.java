package gregtechlite.gtlitecore.loader.recipe;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtechlite.gtlitecore.api.GTLiteValues;
import gregtechlite.gtlitecore.api.extension.RecipeBuilderExtKt;
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler;
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/RecipeConflicts;", "", "<init>", "()V", "init", "", "gtlitecore"}
)
public final class RecipeConflicts {
   @NotNull
   public static final RecipeConflicts INSTANCE = new RecipeConflicts();

   private RecipeConflicts() {
   }

   public final void init() {
      ItemStack[] var1 = new ItemStack[]{OreDictUnifier.get(OrePrefix.dust, Materials.Silicon), IntCircuitIngredient.getIntegratedCircuit(2)};
      ItemStack[] var10000 = var1;
      FluidStack[] var2 = new FluidStack[]{Materials.Water.getFluid(1000), Materials.Chlorine.getFluid(4000), Materials.Methane.getFluid(2000)};
      GTLiteRecipeHandler.removeChemicalRecipes(var10000, var2);
      RecipeMap var10 = RecipeMaps.LARGE_CHEMICAL_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10, "LARGE_CHEMICAL_RECIPES");
      RecipeBuilderExtKt.addRecipe(var10, RecipeConflicts::init$lambda$0);
      ItemStack[] var3 = new ItemStack[]{OreDictUnifier.get(OrePrefix.dust, Materials.Silicon)};
      ItemStack[] var11 = var3;
      FluidStack[] var4 = new FluidStack[]{Materials.Chloromethane.getFluid(2000)};
      GTLiteRecipeHandler.removeChemicalRecipes(var11, var4);
      RecipeMap var12 = RecipeMaps.CHEMICAL_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var12, "CHEMICAL_RECIPES");
      RecipeBuilderExtKt.addRecipe(var12, RecipeConflicts::init$lambda$1);
      var4 = new FluidStack[]{Materials.Propene.getFluid(1000), Materials.Hydrogen.getFluid(2000), Materials.CarbonMonoxide.getFluid(1000)};
      GTLiteRecipeHandler._removeChemicalRecipes(var4);
      var12 = RecipeMaps.CHEMICAL_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var12, "CHEMICAL_RECIPES");
      RecipeBuilderExtKt.addRecipe(var12, RecipeConflicts::init$lambda$2);
      ItemStack[] var6 = new ItemStack[]{OreDictUnifier.get(OrePrefix.dust, Materials.SodiumHydroxide, 3)};
      ItemStack[] var14 = var6;
      FluidStack[] var7 = new FluidStack[]{Materials.SulfuricAcid.getFluid(1000)};
      GTLiteRecipeHandler.removeChemicalRecipes(var14, var7);
      RecipeMap var15 = RecipeMaps.CHEMICAL_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var15, "CHEMICAL_RECIPES");
      RecipeBuilderExtKt.addRecipe(var15, RecipeConflicts::init$lambda$3);
      var7 = new FluidStack[]{Materials.Ethylbenzene.getFluid(1000)};
      GTLiteRecipeHandler._removeChemicalRecipes(var7);
      RecipeBuilderExtKt.addRecipe(GTLiteRecipeMaps.BURNER_REACTOR_RECIPES, RecipeConflicts::init$lambda$4);
      var7 = new FluidStack[]{Materials.Methanol.getFluid(1000), Materials.CarbonMonoxide.getFluid(1000)};
      GTLiteRecipeHandler._removeChemicalRecipes(var7);
      var15 = RecipeMaps.CHEMICAL_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var15, "CHEMICAL_RECIPES");
      RecipeBuilderExtKt.addRecipe(var15, RecipeConflicts::init$lambda$5);
   }

   private static final Unit init$lambda$0(SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(24);
      $this$addRecipe.input(OrePrefix.dust, Materials.Silicon, 3);
      $this$addRecipe.fluidInputs(Materials.Water.getFluid(3000));
      $this$addRecipe.fluidInputs(Materials.Methane.getFluid(6000));
      $this$addRecipe.fluidInputs(Materials.Chlorine.getFluid(12000));
      $this$addRecipe.output(OrePrefix.dust, Materials.Polydimethylsiloxane, 9);
      $this$addRecipe.fluidOutputs(Materials.HydrochloricAcid.getFluid(6000));
      $this$addRecipe.fluidOutputs(Materials.DilutedHydrochloricAcid.getFluid(6000));
      $this$addRecipe.EUt(96L);
      $this$addRecipe.duration(GTLiteValues.getMin(2) + GTLiteValues.getS(24));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$1(SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(1);
      $this$addRecipe.input(OrePrefix.dust, Materials.Silicon);
      $this$addRecipe.fluidInputs(Materials.Chloromethane.getFluid(2000));
      $this$addRecipe.fluidOutputs(Materials.Dimethyldichlorosilane.getFluid(1000));
      $this$addRecipe.EUt(96L);
      $this$addRecipe.duration(GTLiteValues.getS(12));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$2(SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(3);
      $this$addRecipe.fluidInputs(Materials.Propene.getFluid(1000));
      $this$addRecipe.fluidInputs(Materials.Hydrogen.getFluid(2000));
      $this$addRecipe.fluidInputs(Materials.CarbonMonoxide.getFluid(1000));
      $this$addRecipe.fluidOutputs(Materials.Butyraldehyde.getFluid(1000));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[3]);
      $this$addRecipe.duration(GTLiteValues.getS(10));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$3(SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(1);
      $this$addRecipe.input(OrePrefix.dust, Materials.SodiumHydroxide, 3);
      $this$addRecipe.fluidInputs(Materials.SulfuricAcid.getFluid(1000));
      $this$addRecipe.output(OrePrefix.dust, Materials.SodiumBisulfate, 7);
      $this$addRecipe.fluidOutputs(Materials.Water.getFluid(1000));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[1]);
      $this$addRecipe.duration(GTLiteValues.getS(5));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$4(SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.fluidInputs(Materials.Ethylbenzene.getFluid(1000));
      $this$addRecipe.fluidOutputs(Materials.Styrene.getFluid(1000));
      $this$addRecipe.fluidOutputs(Materials.Hydrogen.getFluid(2000));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[1]);
      $this$addRecipe.duration(GTLiteValues.getS(1) + GTLiteValues.getT(10));
      return Unit.INSTANCE;
   }

   private static final Unit init$lambda$5(SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(1);
      $this$addRecipe.fluidInputs(Materials.Methanol.getFluid(1000));
      $this$addRecipe.fluidInputs(Materials.CarbonMonoxide.getFluid(1000));
      $this$addRecipe.fluidOutputs(Materials.AceticAcid.getFluid(1000));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[1]);
      $this$addRecipe.duration(GTLiteValues.getS(15));
      return Unit.INSTANCE;
   }
}
