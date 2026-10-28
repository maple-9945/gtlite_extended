package gregtechlite.gtlitecore.loader.recipe.producer;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.ConfigHolder;
import gregtechlite.gtlitecore.api.GTLiteValues;
import gregtechlite.gtlitecore.api.extension.ItemStackExtKt;
import gregtechlite.gtlitecore.api.extension.RecipeBuilderExtKt;
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps;
import gregtechlite.gtlitecore.api.recipe.util.OreProcessorRecipeWrapper;
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials;
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u0018B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\n\u001a\u00020\u000bJF\u0010\f\u001a\u00020\u000b\"\u000e\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00130\u00122\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0012H\u0002J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0006H\u0002R\u001f\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0019"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/producer/IntegratedOreProcessorRecipeProducer;", "", "<init>", "()V", "orePrefixes", "", "Lgregtech/api/unification/ore/OrePrefix;", "kotlin.jvm.PlatformType", "getOrePrefixes", "()Ljava/util/List;", "produce", "", "initRecipe", "T", "Lgregtech/api/recipes/RecipeBuilder;", "circuit", "", "ops", "", "Lgregtechlite/gtlitecore/loader/recipe/producer/IntegratedOreProcessorRecipeProducer$OreProcessorOps;", "extraFluids", "Lnet/minecraftforge/fluids/FluidStack;", "getOreProductMultiplier", "prefix", "OreProcessorOps", "gtlitecore"}
)
@SourceDebugExtension({"SMAP\nIntegratedOreProcessorRecipeProducer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IntegratedOreProcessorRecipeProducer.kt\ngregtechlite/gtlitecore/loader/recipe/producer/IntegratedOreProcessorRecipeProducer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,233:1\n1#2:234\n*E\n"})
public final class IntegratedOreProcessorRecipeProducer {
   @NotNull
   public static final IntegratedOreProcessorRecipeProducer INSTANCE = new IntegratedOreProcessorRecipeProducer();
   @NotNull
   private static final List<OrePrefix> orePrefixes;

   private IntegratedOreProcessorRecipeProducer() {
   }

   @NotNull
   public final List<OrePrefix> getOrePrefixes() {
      return orePrefixes;
   }

   public final void produce() {
      OreProcessorOps[] var1 = new OreProcessorOps[4];
      RecipeMap var10006 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
      var1[0] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.ORE_WASHER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "ORE_WASHER_RECIPES");
      var1[1] = new OreProcessorOps(var10006, CollectionsKt.listOf(Materials.DistilledWater.getFluid(Integer.MAX_VALUE)), (List)null, 4, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.THERMAL_CENTRIFUGE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "THERMAL_CENTRIFUGE_RECIPES");
      var1[2] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
      var1[3] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      initRecipe$default(this, 1, CollectionsKt.listOf(var1), (List)null, 4, (Object)null);
      var1 = new OreProcessorOps[4];
      var10006 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
      var1[0] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.ORE_WASHER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "ORE_WASHER_RECIPES");
      var1[1] = new OreProcessorOps(var10006, CollectionsKt.listOf(GTLiteMaterials.INSTANCE.getTectonicPetrotheum().getFluid(Integer.MAX_VALUE)), CollectionsKt.listOf(IntCircuitIngredient.getIntegratedCircuit(3)));
      var10006 = RecipeMaps.THERMAL_CENTRIFUGE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "THERMAL_CENTRIFUGE_RECIPES");
      var1[2] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
      var1[3] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      this.initRecipe(2, CollectionsKt.listOf(var1), CollectionsKt.listOf(GTLiteMaterials.INSTANCE.getTectonicPetrotheum().getFluid(100)));
      var1 = new OreProcessorOps[4];
      var10006 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
      var1[0] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.ORE_WASHER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "ORE_WASHER_RECIPES");
      var1[1] = new OreProcessorOps(var10006, CollectionsKt.listOf(Materials.DistilledWater.getFluid(Integer.MAX_VALUE)), (List)null, 4, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
      var1[2] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.CENTRIFUGE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "CENTRIFUGE_RECIPES");
      var1[3] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      initRecipe$default(this, 3, CollectionsKt.listOf(var1), (List)null, 4, (Object)null);
      var1 = new OreProcessorOps[4];
      var10006 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
      var1[0] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.ORE_WASHER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "ORE_WASHER_RECIPES");
      var1[1] = new OreProcessorOps(var10006, CollectionsKt.listOf(GTLiteMaterials.INSTANCE.getTectonicPetrotheum().getFluid(Integer.MAX_VALUE)), CollectionsKt.listOf(IntCircuitIngredient.getIntegratedCircuit(3)));
      var10006 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
      var1[2] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.CENTRIFUGE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "CENTRIFUGE_RECIPES");
      var1[3] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      this.initRecipe(4, CollectionsKt.listOf(var1), CollectionsKt.listOf(GTLiteMaterials.INSTANCE.getTectonicPetrotheum().getFluid(100)));
      var1 = new OreProcessorOps[3];
      var10006 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
      var1[0] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
      var1[1] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.CENTRIFUGE_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "CENTRIFUGE_RECIPES");
      var1[2] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      initRecipe$default(this, 5, CollectionsKt.listOf(var1), (List)null, 4, (Object)null);
      var1 = new OreProcessorOps[3];
      var10006 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
      var1[0] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.ORE_WASHER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "ORE_WASHER_RECIPES");
      var1[1] = new OreProcessorOps(var10006, CollectionsKt.listOf(Materials.DistilledWater.getFluid(Integer.MAX_VALUE)), (List)null, 4, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.SIFTER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "SIFTER_RECIPES");
      var1[2] = new OreProcessorOps(var10006, (List)null, CollectionsKt.listOf(IntCircuitIngredient.getIntegratedCircuit(1)), 2, (DefaultConstructorMarker)null);
      initRecipe$default(this, 6, CollectionsKt.listOf(var1), (List)null, 4, (Object)null);
      var1 = new OreProcessorOps[3];
      var10006 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
      var1[0] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.ORE_WASHER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "ORE_WASHER_RECIPES");
      var1[1] = new OreProcessorOps(var10006, CollectionsKt.listOf(GTLiteMaterials.INSTANCE.getTectonicPetrotheum().getFluid(Integer.MAX_VALUE)), CollectionsKt.listOf(IntCircuitIngredient.getIntegratedCircuit(3)));
      var10006 = RecipeMaps.SIFTER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "SIFTER_RECIPES");
      var1[2] = new OreProcessorOps(var10006, (List)null, CollectionsKt.listOf(IntCircuitIngredient.getIntegratedCircuit(1)), 2, (DefaultConstructorMarker)null);
      this.initRecipe(7, CollectionsKt.listOf(var1), CollectionsKt.listOf(GTLiteMaterials.INSTANCE.getTectonicPetrotheum().getFluid(100)));
      var1 = new OreProcessorOps[3];
      var10006 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
      var1[0] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.ORE_WASHER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "ORE_WASHER_RECIPES");
      var1[1] = new OreProcessorOps(var10006, CollectionsKt.listOf(Materials.DistilledWater.getFluid(Integer.MAX_VALUE)), (List)null, 4, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.SIFTER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "SIFTER_RECIPES");
      var1[2] = new OreProcessorOps(var10006, CollectionsKt.listOf(GTLiteMaterials.INSTANCE.getZephyreanAerotheum().getFluid(Integer.MAX_VALUE)), CollectionsKt.listOf(IntCircuitIngredient.getIntegratedCircuit(2)));
      this.initRecipe(8, CollectionsKt.listOf(var1), CollectionsKt.listOf(GTLiteMaterials.INSTANCE.getZephyreanAerotheum().getFluid(250)));
      var1 = new OreProcessorOps[3];
      var10006 = RecipeMaps.MACERATOR_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
      var1[0] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
      var10006 = RecipeMaps.ORE_WASHER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "ORE_WASHER_RECIPES");
      var1[1] = new OreProcessorOps(var10006, CollectionsKt.listOf(GTLiteMaterials.INSTANCE.getTectonicPetrotheum().getFluid(Integer.MAX_VALUE)), CollectionsKt.listOf(IntCircuitIngredient.getIntegratedCircuit(3)));
      var10006 = RecipeMaps.SIFTER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10006, "SIFTER_RECIPES");
      var1[2] = new OreProcessorOps(var10006, CollectionsKt.listOf(GTLiteMaterials.INSTANCE.getZephyreanAerotheum().getFluid(Integer.MAX_VALUE)), CollectionsKt.listOf(IntCircuitIngredient.getIntegratedCircuit(2)));
      List var10002 = CollectionsKt.listOf(var1);
      FluidStack[] var12 = new FluidStack[]{GTLiteMaterials.INSTANCE.getTectonicPetrotheum().getFluid(100), GTLiteMaterials.INSTANCE.getZephyreanAerotheum().getFluid(250)};
      this.initRecipe(9, var10002, CollectionsKt.listOf(var12));
      Material fluid = new Material[]{Materials.DistilledWater, Materials.Mercury, Materials.SodiumPersulfate};

      for(fluid : CollectionsKt.listOf(fluid)) {
         OreProcessorOps[] var3 = new OreProcessorOps[4];
         var10006 = RecipeMaps.MACERATOR_RECIPES;
         Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
         var3[0] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
         var10006 = RecipeMaps.CHEMICAL_BATH_RECIPES;
         Intrinsics.checkNotNullExpressionValue(var10006, "CHEMICAL_BATH_RECIPES");
         var3[1] = new OreProcessorOps(var10006, CollectionsKt.listOf(fluid.getFluid(Integer.MAX_VALUE)), (List)null, 4, (DefaultConstructorMarker)null);
         var10006 = RecipeMaps.MACERATOR_RECIPES;
         Intrinsics.checkNotNullExpressionValue(var10006, "MACERATOR_RECIPES");
         var3[2] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
         var10006 = RecipeMaps.CENTRIFUGE_RECIPES;
         Intrinsics.checkNotNullExpressionValue(var10006, "CENTRIFUGE_RECIPES");
         var3[3] = new OreProcessorOps(var10006, (List)null, (List)null, 6, (DefaultConstructorMarker)null);
         this.initRecipe(10, CollectionsKt.listOf(var3), CollectionsKt.listOf(fluid.getFluid(100)));
      }

   }

   private final <T extends RecipeBuilder<T>> void initRecipe(int circuit, List<OreProcessorOps<T>> ops, List<? extends FluidStack> extraFluids) {
      List oreItems = (List)(new ArrayList());
      Iterator var5 = ArrayIteratorKt.iterator(OreDictionary.getOreNames());

      while(var5.hasNext()) {
         String name = (String)var5.next();
         Intrinsics.checkNotNull(name);
         if (StringsKt.startsWith$default(name, "ore", false, 2, (Object)null)) {
            ItemStack itemStack = OreDictUnifier.get(name);
            if (Intrinsics.areEqual(OreDictUnifier.getPrefix(itemStack), OrePrefix.ore) && OreDictUnifier.getMaterial(itemStack) != null) {
               Intrinsics.checkNotNull(itemStack);
               oreItems.add(itemStack);
            }
         }
      }

      for(ItemStack item : oreItems) {
         OrePrefix var10000 = OreDictUnifier.getPrefix(item);
         ItemStack var29;
         if (var10000 != null) {
            OrePrefix it = var10000;
            int var11 = 0;
            var29 = ItemStackExtKt.copy(item, 10000 * INSTANCE.getOreProductMultiplier(it));
         } else {
            var29 = null;
         }

         ItemStack inputItem = var29;
         Intrinsics.checkNotNull(inputItem);
         List output = CollectionsKt.listOf(inputItem.func_77946_l());

         for(OreProcessorOps op : ops) {
            if (output == null) {
               break;
            }

            output = OreProcessorRecipeWrapper.INSTANCE.doRecipe(output, op.getRecipeMap(), op.getExtraFluids(), op.getExtraItems());
         }

         if (output != null && !output.isEmpty() && (output.size() != 1 || !((ItemStack)output.get(0)).func_77969_a(item))) {
            MaterialStack var30 = OreDictUnifier.getMaterial(item);
            Material material = var30 != null ? var30.material : null;

            for(OrePrefix prefix : orePrefixes) {
               ItemStack itemStack = OreDictUnifier.get(prefix, material);
               if (!itemStack.func_190926_b()) {
                  itemStack.func_190920_e(1);
                  SimpleRecipeBuilder var31 = (SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)GTLiteRecipeMaps.INTEGRATED_ORE_PROCESSOR_RECIPES.recipeBuilder()).circuitMeta(circuit)).inputs(itemStack);
                  FluidStack[] var14 = new FluidStack[]{Materials.DistilledWater.getFluid(100), Materials.Lubricant.getFluid(5)};
                  SimpleRecipeBuilder builder = (SimpleRecipeBuilder)var31.fluidInputs(var14);
                  if (extraFluids != null) {
                     for(FluidStack fluid : extraFluids) {
                        builder.fluidInputs(fluid);
                     }
                  }

                  for(ItemStack outputItem : output) {
                     int var32 = outputItem.func_190916_E();
                     Intrinsics.checkNotNull(prefix);
                     int chancedCount = var32 * this.getOreProductMultiplier(prefix);
                     if (chancedCount < 10000) {
                        ItemStack outputItemStack = ItemStackExtKt.copy(outputItem, 1);
                        SimpleRecipeBuilder var33 = (SimpleRecipeBuilder)builder.chancedOutput(outputItemStack, chancedCount, 0);
                     } else {
                        ItemStack outputItemStack = ItemStackExtKt.copy(outputItem, (chancedCount + 5000) / 10000);
                        SimpleRecipeBuilder var34 = (SimpleRecipeBuilder)builder.outputs(outputItemStack);
                     }
                  }

                  Intrinsics.checkNotNull(builder);
                  ((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeBuilderExtKt.EUt((RecipeBuilder)builder, GTValues.VA[1])).duration(GTLiteValues.getS(5))).buildAndRegister();
               }
            }
         }
      }

   }

   // $FF: synthetic method
   static void initRecipe$default(IntegratedOreProcessorRecipeProducer var0, int var1, List var2, List var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = null;
      }

      var0.initRecipe(var1, var2, var3);
   }

   private final int getOreProductMultiplier(OrePrefix prefix) {
      return Intrinsics.areEqual(prefix, OrePrefix.oreNetherrack) ? 2 : (Intrinsics.areEqual(prefix, OrePrefix.oreEndstone) ? 4 : 1);
   }

   static {
      OrePrefix[] var0 = new OrePrefix[]{OrePrefix.ore, OrePrefix.oreNetherrack, OrePrefix.oreEndstone};
      orePrefixes = CollectionsKt.mutableListOf(var0);
      if (ConfigHolder.worldgen.allUniqueStoneTypes) {
         IntegratedOreProcessorRecipeProducer var10000 = INSTANCE;
         List var2 = orePrefixes;
         var0 = new OrePrefix[]{OrePrefix.oreGranite, OrePrefix.oreDiorite, OrePrefix.oreAndesite, OrePrefix.oreBlackgranite, OrePrefix.oreRedgranite, OrePrefix.oreMarble, OrePrefix.oreBasalt, OrePrefix.oreSand, OrePrefix.oreRedSand, GTLiteOrePrefix.oreLimestone, GTLiteOrePrefix.oreKomatiite, GTLiteOrePrefix.oreGreenSchist, GTLiteOrePrefix.oreBlueSchist, GTLiteOrePrefix.oreKimberlite, GTLiteOrePrefix.oreQuartzite, GTLiteOrePrefix.oreSlate, GTLiteOrePrefix.oreShale};
         var2.addAll((Collection)CollectionsKt.listOf(var0));
      }

   }

   @Metadata(
      mv = {2, 1, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B9\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005HÆ\u0003J\u0011\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J\u0011\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007HÆ\u0003JC\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001d"},
      d2 = {"Lgregtechlite/gtlitecore/loader/recipe/producer/IntegratedOreProcessorRecipeProducer$OreProcessorOps;", "T", "Lgregtech/api/recipes/RecipeBuilder;", "", "recipeMap", "Lgregtech/api/recipes/RecipeMap;", "extraFluids", "", "Lnet/minecraftforge/fluids/FluidStack;", "extraItems", "Lnet/minecraft/item/ItemStack;", "<init>", "(Lgregtech/api/recipes/RecipeMap;Ljava/util/List;Ljava/util/List;)V", "getRecipeMap", "()Lgregtech/api/recipes/RecipeMap;", "getExtraFluids", "()Ljava/util/List;", "getExtraItems", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "gtlitecore"}
   )
   public static final class OreProcessorOps<T extends RecipeBuilder<T>> {
      @NotNull
      private final RecipeMap<T> recipeMap;
      @Nullable
      private final List<FluidStack> extraFluids;
      @Nullable
      private final List<ItemStack> extraItems;

      public OreProcessorOps(@NotNull RecipeMap<T> recipeMap, @Nullable List<? extends FluidStack> extraFluids, @Nullable List<ItemStack> extraItems) {
         Intrinsics.checkNotNullParameter(recipeMap, "recipeMap");
         super();
         this.recipeMap = recipeMap;
         this.extraFluids = extraFluids;
         this.extraItems = extraItems;
      }

      // $FF: synthetic method
      public OreProcessorOps(RecipeMap var1, List var2, List var3, int var4, DefaultConstructorMarker var5) {
         if ((var4 & 2) != 0) {
            var2 = null;
         }

         if ((var4 & 4) != 0) {
            var3 = null;
         }

         this(var1, var2, var3);
      }

      @NotNull
      public final RecipeMap<T> getRecipeMap() {
         return this.recipeMap;
      }

      @Nullable
      public final List<FluidStack> getExtraFluids() {
         return this.extraFluids;
      }

      @Nullable
      public final List<ItemStack> getExtraItems() {
         return this.extraItems;
      }

      @NotNull
      public final RecipeMap<T> component1() {
         return this.recipeMap;
      }

      @Nullable
      public final List<FluidStack> component2() {
         return this.extraFluids;
      }

      @Nullable
      public final List<ItemStack> component3() {
         return this.extraItems;
      }

      @NotNull
      public final OreProcessorOps<T> copy(@NotNull RecipeMap<T> recipeMap, @Nullable List<? extends FluidStack> extraFluids, @Nullable List<ItemStack> extraItems) {
         Intrinsics.checkNotNullParameter(recipeMap, "recipeMap");
         return new OreProcessorOps<T>(recipeMap, extraFluids, extraItems);
      }

      // $FF: synthetic method
      public static OreProcessorOps copy$default(OreProcessorOps var0, RecipeMap var1, List var2, List var3, int var4, Object var5) {
         if ((var4 & 1) != 0) {
            var1 = var0.recipeMap;
         }

         if ((var4 & 2) != 0) {
            var2 = var0.extraFluids;
         }

         if ((var4 & 4) != 0) {
            var3 = var0.extraItems;
         }

         return var0.copy(var1, var2, var3);
      }

      @NotNull
      public String toString() {
         return "OreProcessorOps(recipeMap=" + this.recipeMap + ", extraFluids=" + this.extraFluids + ", extraItems=" + this.extraItems + ')';
      }

      public int hashCode() {
         int result = this.recipeMap.hashCode();
         result = result * 31 + (this.extraFluids == null ? 0 : this.extraFluids.hashCode());
         result = result * 31 + (this.extraItems == null ? 0 : this.extraItems.hashCode());
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof OreProcessorOps)) {
            return false;
         } else {
            OreProcessorOps var2 = (OreProcessorOps)other;
            if (!Intrinsics.areEqual(this.recipeMap, var2.recipeMap)) {
               return false;
            } else if (!Intrinsics.areEqual(this.extraFluids, var2.extraFluids)) {
               return false;
            } else {
               return Intrinsics.areEqual(this.extraItems, var2.extraItems);
            }
         }
      }
   }
}
