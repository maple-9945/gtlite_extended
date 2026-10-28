package gregtechlite.gtlitecore.loader.recipe.handler;

import com.google.common.collect.UnmodifiableIterator;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.BlastRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.GemProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtechlite.gtlitecore.api.extension.RecipeBuilderExtKt;
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps;
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags;
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\r"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/handler/BouleRecipeHandler;", "", "<init>", "()V", "init", "", "processCrystallizer", "gemPrefix", "Lgregtech/api/unification/ore/OrePrefix;", "material", "Lgregtech/api/unification/material/Material;", "property", "Lgregtech/api/unification/material/properties/GemProperty;", "gtlitecore"}
)
@SourceDebugExtension({"SMAP\nBouleRecipeHandler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BouleRecipeHandler.kt\ngregtechlite/gtlitecore/loader/recipe/handler/BouleRecipeHandler\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,141:1\n37#2:142\n36#2,3:143\n*S KotlinDebug\n*F\n+ 1 BouleRecipeHandler.kt\ngregtechlite/gtlitecore/loader/recipe/handler/BouleRecipeHandler\n*L\n118#1:142\n118#1:143,3\n*E\n"})
public final class BouleRecipeHandler {
   @NotNull
   public static final BouleRecipeHandler INSTANCE = new BouleRecipeHandler();

   private BouleRecipeHandler() {
   }

   public final void init() {
      OrePrefix.gem.addProcessingHandler(PropertyKey.GEM, this::processCrystallizer);
   }

   private final void processCrystallizer(OrePrefix gemPrefix, Material material, GemProperty property) {
      if (!material.hasFlag(GTLiteMaterialFlags.DISABLE_CRYSTALLIZATION)) {
         if (material.hasFlag(MaterialFlags.CRYSTALLIZABLE) || material.hasFlag(GTLiteMaterialFlags.GENERATE_BOULE)) {
            if (material.getMaterialComponents().size() <= GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES.getMaxInputs() - 1 + GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES.getMaxFluidInputs()) {
               int componentAmount = 0;
               int temperature = 0;
               ArrayList inputs = new ArrayList();
               ArrayList fluidInputs = new ArrayList();
               UnmodifiableIterator var10000 = material.getMaterialComponents().iterator();
               Intrinsics.checkNotNullExpressionValue(var10000, "iterator(...)");
               UnmodifiableIterator var8 = var10000;

               while(var8.hasNext()) {
                  MaterialStack materialStack = (MaterialStack)var8.next();
                  Material component = materialStack.material;
                  int amount = (int)materialStack.amount;
                  if (!component.isSolid() && !component.hasProperty(PropertyKey.DUST)) {
                     if (component.hasProperty(PropertyKey.FLUID)) {
                        componentAmount += amount;
                        if (fluidInputs.size() > GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES.getMaxFluidInputs()) {
                           return;
                        }

                        fluidInputs.add(component.getFluid(amount * 1000));
                     }
                  } else {
                     componentAmount += amount;
                     temperature += component.getBlastTemperature() * amount;
                     if (inputs.size() > GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES.getMaxInputs() - 1) {
                        return;
                     }

                     inputs.add(OreDictUnifier.get(OrePrefix.dust, component, amount));
                  }

                  if (!component.hasProperty(PropertyKey.BLAST)) {
                     temperature += 1200 * amount;
                  }
               }

               if (componentAmount != 0) {
                  temperature /= componentAmount;
                  BlastRecipeBuilder var26 = ((BlastRecipeBuilder)GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES.recipeBuilder()).blastFurnaceTemp(temperature);
                  Intrinsics.checkNotNullExpressionValue(var26, "blastFurnaceTemp(...)");
                  BlastRecipeBuilder builder = (BlastRecipeBuilder)RecipeBuilderExtKt.EUt((RecipeBuilder)var26, GTValues.VA[temperature <= 2800 ? 3 : 4]);
                  if (componentAmount == 2) {
                     Iterator var27 = inputs.iterator();
                     Intrinsics.checkNotNullExpressionValue(var27, "iterator(...)");
                     Iterator var15 = var27;

                     while(var15.hasNext()) {
                        ItemStack stack = (ItemStack)var15.next();
                        stack.func_190920_e(stack.func_190916_E() * 2);
                     }

                     var27 = fluidInputs.iterator();
                     Intrinsics.checkNotNullExpressionValue(var27, "iterator(...)");

                     FluidStack fluid;
                     for(Iterator var16 = var27; var16.hasNext(); fluid.amount *= 2) {
                        fluid = (FluidStack)var16.next();
                     }

                     componentAmount = 1;
                     Intrinsics.checkNotNull(builder);
                     BlastRecipeBuilder var29 = (BlastRecipeBuilder)RecipeBuilderExtKt.duration((RecipeBuilder)builder, material.getMass() * (long)8);
                  } else if (componentAmount % 4 != 0) {
                     Iterator var30 = inputs.iterator();
                     Intrinsics.checkNotNullExpressionValue(var30, "iterator(...)");
                     Iterator var17 = var30;

                     while(var17.hasNext()) {
                        ItemStack stack = (ItemStack)var17.next();
                        stack.func_190920_e(stack.func_190916_E() * 4);
                     }

                     var30 = fluidInputs.iterator();
                     Intrinsics.checkNotNullExpressionValue(var30, "iterator(...)");

                     FluidStack fluid;
                     for(Iterator var18 = var30; var18.hasNext(); fluid.amount *= 4) {
                        fluid = (FluidStack)var18.next();
                     }

                     Intrinsics.checkNotNull(builder);
                     BlastRecipeBuilder var32 = (BlastRecipeBuilder)RecipeBuilderExtKt.duration((RecipeBuilder)builder, material.getMass() * (long)16);
                  } else {
                     componentAmount /= 4;
                     Intrinsics.checkNotNull(builder);
                     var26 = (BlastRecipeBuilder)RecipeBuilderExtKt.duration((RecipeBuilder)builder, material.getMass() * (long)4);
                  }

                  ((BlastRecipeBuilder)builder.input(GTLiteOrePrefix.seedCrystal, material, componentAmount)).output(GTLiteOrePrefix.boule, material, componentAmount);
                  if (!((Collection)inputs).isEmpty()) {
                     builder.inputStacks((Collection)inputs);
                  }

                  if (!((Collection)fluidInputs).isEmpty()) {
                     Collection $this$toTypedArray$iv = (Collection)fluidInputs;
                     int $i$f$toTypedArray = 0;
                     FluidStack[] var19 = (FluidStack[])$this$toTypedArray$iv.toArray(new FluidStack[0]);
                     builder.fluidInputs((FluidStack[])Arrays.copyOf(var19, var19.length));
                  }

                  builder.buildAndRegister();
                  RecipeMap var34 = RecipeMaps.CUTTER_RECIPES;
                  Intrinsics.checkNotNullExpressionValue(var34, "CUTTER_RECIPES");
                  RecipeBuilderExtKt.addRecipe(var34, BouleRecipeHandler::processCrystallizer$lambda$0);
                  var34 = RecipeMaps.AUTOCLAVE_RECIPES;
                  Intrinsics.checkNotNullExpressionValue(var34, "AUTOCLAVE_RECIPES");
                  RecipeBuilderExtKt.addRecipe(var34, BouleRecipeHandler::processCrystallizer$lambda$1);
               }
            }
         }
      }
   }

   private static final Unit processCrystallizer$lambda$0(Material $material, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input(GTLiteOrePrefix.boule, $material);
      $this$addRecipe.output(OrePrefix.gemExquisite, $material);
      $this$addRecipe.output(GTLiteOrePrefix.seedCrystal, $material);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[1]);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, $material.getMass() * (long)4);
      return Unit.INSTANCE;
   }

   private static final Unit processCrystallizer$lambda$1(Material $material, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input(OrePrefix.gemExquisite, $material);
      $this$addRecipe.fluidInputs(Materials.DistilledWater.getFluid(8000));
      $this$addRecipe.output(GTLiteOrePrefix.seedCrystal, $material);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[3]);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, $material.getMass() * (long)9);
      return Unit.INSTANCE;
   }
}
