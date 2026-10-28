package gregtechlite.gtlitecore.loader.recipe.handler;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.WireProperties;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTUtility;
import gregtechlite.gtlitecore.api.GTLiteValues;
import gregtechlite.gtlitecore.api.extension.RecipeBuilderExtKt;
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps;
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials;
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.KCallablesJvm;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nJ \u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\"\u0010\u0004\u001a\u0016\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/handler/WireRecipeHandler;", "", "<init>", "()V", "INSULATION_AMOUNT", "", "Lgregtech/api/unification/ore/OrePrefix;", "kotlin.jvm.PlatformType", "", "init", "", "generateCableCovering", "wirePrefix", "material", "Lgregtech/api/unification/material/Material;", "properties", "Lgregtech/api/unification/material/properties/WireProperties;", "gtlitecore"}
)
@SourceDebugExtension({"SMAP\nWireRecipeHandler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WireRecipeHandler.kt\ngregtechlite/gtlitecore/loader/recipe/handler/WireRecipeHandler\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,228:1\n1#2:229\n*E\n"})
public final class WireRecipeHandler {
   @NotNull
   public static final WireRecipeHandler INSTANCE = new WireRecipeHandler();
   @NotNull
   private static final Map<OrePrefix, Integer> INSULATION_AMOUNT;

   private WireRecipeHandler() {
   }

   public final void init() {
      OrePrefix.wireGtSingle.addProcessingHandler(PropertyKey.WIRE, this::generateCableCovering);
      OrePrefix.wireGtDouble.addProcessingHandler(PropertyKey.WIRE, this::generateCableCovering);
      OrePrefix.wireGtQuadruple.addProcessingHandler(PropertyKey.WIRE, this::generateCableCovering);
      OrePrefix.wireGtOctal.addProcessingHandler(PropertyKey.WIRE, this::generateCableCovering);
      OrePrefix.wireGtHex.addProcessingHandler(PropertyKey.WIRE, this::generateCableCovering);
   }

   private final void generateCableCovering(OrePrefix wirePrefix, Material material, WireProperties properties) {
      Iterable var7 = (Iterable)KClasses.getDeclaredFunctions(Reflection.getOrCreateKotlinClass(gregtech.loaders.recipe.handlers.WireRecipeHandler.class));
      Iterator var8 = var7.iterator();

      Object var10000;
      while(true) {
         if (var8.hasNext()) {
            Object var9 = var8.next();
            KFunction it = (KFunction)var9;
            int var11 = 0;
            if (!Intrinsics.areEqual(it.getName(), "generateManualRecipe")) {
               continue;
            }

            var10000 = var9;
            break;
         }

         var10000 = null;
         break;
      }

      KFunction var22 = (KFunction)var10000;
      if (var22 == null) {
         throw new IllegalArgumentException("Method 'generateManualRecipe' is not found in target class");
      } else {
         KFunction generateManualRecipe = var22;
         KCallablesJvm.setAccessible((KCallable)generateManualRecipe, true);
         if (!properties.isSuperconductor()) {
            int cableAmount = (int)(wirePrefix.getMaterialAmount(material) * (long)2 / 3628800L);
            StringBuilder var23 = (new StringBuilder()).append("cable");
            String var10001 = wirePrefix.name();
            Intrinsics.checkNotNullExpressionValue(var10001, "name(...)");
            String var12 = var10001;
            int insulationAmount = 4;
            var10001 = var12.substring(insulationAmount);
            Intrinsics.checkNotNullExpressionValue(var10001, "substring(...)");
            OrePrefix cablePrefix = OrePrefix.getPrefix(var23.append(var10001).toString());
            int voltageTier = GTUtility.getTierByVoltage((long)properties.getVoltage());
            Object var24 = INSULATION_AMOUNT.get(cablePrefix);
            Intrinsics.checkNotNull(var24);
            insulationAmount = ((Number)var24).intValue();
            if (voltageTier <= 1) {
               Object[] var16 = new Object[]{wirePrefix, material, cablePrefix, cableAmount};
               generateManualRecipe.call(var16);
            }

            if (voltageTier <= 4) {
               RecipeBuilder var25 = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)GTLiteRecipeMaps.LAMINATOR_RECIPES.recipeBuilder()).input(wirePrefix, material)).fluidInputs(Materials.Rubber.getFluid(144 * insulationAmount))).output(cablePrefix, material);
               Intrinsics.checkNotNullExpressionValue(var25, "output(...)");
               SimpleRecipeBuilder builder = (SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeBuilderExtKt.EUt(var25, GTValues.VA[0])).duration(GTLiteValues.getS(5));
               if (voltageTier == 4) {
                  builder.input(OrePrefix.foil, Materials.PolyvinylChloride, insulationAmount);
               }

               builder.buildAndRegister();
            }

            if (voltageTier <= 8) {
               RecipeBuilder var26 = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)GTLiteRecipeMaps.LAMINATOR_RECIPES.recipeBuilder()).input(wirePrefix, material)).output(cablePrefix, material);
               Intrinsics.checkNotNullExpressionValue(var26, "output(...)");
               SimpleRecipeBuilder builder = (SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeBuilderExtKt.EUt(var26, GTValues.VA[0])).duration(GTLiteValues.getS(5));
               if (voltageTier >= 4) {
                  builder.input(OrePrefix.foil, Materials.PolyvinylChloride, insulationAmount);
               }

               if (voltageTier >= 6) {
                  builder.input(OrePrefix.foil, Materials.PolyphenyleneSulfide, insulationAmount);
               }

               ((SimpleRecipeBuilder)builder.fluidInputs(Materials.SiliconeRubber.getFluid(144 * insulationAmount / 2))).buildAndRegister();
               var26 = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)GTLiteRecipeMaps.LAMINATOR_RECIPES.recipeBuilder()).input(wirePrefix, material)).output(cablePrefix, material);
               Intrinsics.checkNotNullExpressionValue(var26, "output(...)");
               builder = (SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeBuilderExtKt.EUt(var26, GTValues.VA[0])).duration(GTLiteValues.getS(5));
               if (voltageTier >= 4) {
                  builder.input(OrePrefix.foil, Materials.PolyvinylChloride, insulationAmount);
               }

               if (voltageTier >= 6) {
                  builder.input(OrePrefix.foil, Materials.PolyphenyleneSulfide, insulationAmount);
               }

               ((SimpleRecipeBuilder)builder.fluidInputs(Materials.StyreneButadieneRubber.getFluid(144 * insulationAmount / 4))).buildAndRegister();
            }

            if (voltageTier <= 13) {
               RecipeBuilder var28 = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)GTLiteRecipeMaps.LAMINATOR_RECIPES.recipeBuilder()).input(wirePrefix, material)).output(cablePrefix, material);
               Intrinsics.checkNotNullExpressionValue(var28, "output(...)");
               SimpleRecipeBuilder builder = (SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeBuilderExtKt.EUt(var28, GTValues.VA[0])).duration(GTLiteValues.getS(5));
               if (voltageTier >= 4) {
                  builder.input(OrePrefix.foil, Materials.PolyvinylChloride, insulationAmount);
               }

               if (voltageTier >= 6) {
                  builder.input(OrePrefix.foil, Materials.PolyphenyleneSulfide, insulationAmount);
               }

               if (voltageTier >= 8) {
                  builder.input(OrePrefix.foil, GTLiteMaterials.INSTANCE.getPolyetheretherketone(), insulationAmount);
               }

               if (voltageTier >= 10) {
                  builder.input(OrePrefix.foil, GTLiteMaterials.INSTANCE.getZylon(), insulationAmount);
               }

               ((SimpleRecipeBuilder)builder.fluidInputs(GTLiteMaterials.INSTANCE.getPolytetramethyleneGlycolRubber().getFluid(144 * insulationAmount / 2))).buildAndRegister();
               var28 = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)GTLiteRecipeMaps.LAMINATOR_RECIPES.recipeBuilder()).input(wirePrefix, material)).output(cablePrefix, material);
               Intrinsics.checkNotNullExpressionValue(var28, "output(...)");
               builder = (SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeBuilderExtKt.EUt(var28, GTValues.VA[0])).duration(GTLiteValues.getS(5));
               if (voltageTier >= 4) {
                  builder.input(OrePrefix.foil, Materials.PolyvinylChloride, insulationAmount);
               }

               if (voltageTier >= 6) {
                  builder.input(OrePrefix.foil, Materials.PolyphenyleneSulfide, insulationAmount);
               }

               if (voltageTier >= 8) {
                  builder.input(OrePrefix.foil, GTLiteMaterials.INSTANCE.getPolyetheretherketone(), insulationAmount);
               }

               if (voltageTier >= 10) {
                  builder.input(OrePrefix.foil, GTLiteMaterials.INSTANCE.getZylon(), insulationAmount);
               }

               ((SimpleRecipeBuilder)builder.fluidInputs(GTLiteMaterials.INSTANCE.getPolyphosphonitrileFluoroRubber().getFluid(144 * insulationAmount / 4))).buildAndRegister();
            }

            if (voltageTier == 14) {
               RecipeBuilder var30 = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)GTLiteRecipeMaps.LAMINATOR_RECIPES.recipeBuilder()).input(wirePrefix, material)).input(OrePrefix.foil, Materials.PolyvinylChloride, insulationAmount)).input(OrePrefix.foil, Materials.PolyphenyleneSulfide, insulationAmount)).input(OrePrefix.foil, GTLiteMaterials.INSTANCE.getPolyetheretherketone(), insulationAmount)).input(OrePrefix.foil, GTLiteMaterials.INSTANCE.getZylon(), insulationAmount)).input(GTLiteMetaItems.INSTANCE.getPOLYMER_INSULATOR_FOIL(), insulationAmount)).fluidInputs(GTLiteMaterials.INSTANCE.getCosmicFabric().getFluid(144 * insulationAmount / 2))).fluidInputs(GTLiteMaterials.INSTANCE.getDimensionallyShiftedSuperfluid().getFluid(1000 * insulationAmount / 2))).output(cablePrefix, material);
               Intrinsics.checkNotNullExpressionValue(var30, "output(...)");
               ((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeBuilderExtKt.EUt(var30, GTValues.VA[0])).duration(GTLiteValues.getS(5))).buildAndRegister();
            }

         }
      }
   }

   static {
      Pair[] var0 = new Pair[]{TuplesKt.to(OrePrefix.cableGtSingle, 1), TuplesKt.to(OrePrefix.cableGtDouble, 1), TuplesKt.to(OrePrefix.cableGtQuadruple, 2), TuplesKt.to(OrePrefix.cableGtOctal, 3), TuplesKt.to(OrePrefix.cableGtHex, 5)};
      INSULATION_AMOUNT = MapsKt.mapOf(var0);
   }
}
