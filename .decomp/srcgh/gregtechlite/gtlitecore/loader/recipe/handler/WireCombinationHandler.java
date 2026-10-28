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
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.WireProperties;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtechlite.gtlitecore.api.GTLiteValues;
import gregtechlite.gtlitecore.api.extension.RecipeBuilderExtKt;
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u000b\u001a\u00020\fJ \u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J \u0010\u0013\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0012H\u0002J \u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0012H\u0002R\u001e\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR*\u0010\t\u001a\u001e\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/handler/WireCombinationHandler;", "", "<init>", "()V", "WIRE_DOUBLING_ORDER", "", "Lgregtech/api/unification/ore/OrePrefix;", "kotlin.jvm.PlatformType", "[Lgregtech/api/unification/ore/OrePrefix;", "cableToWireMap", "", "init", "", "processWireCompression", "wirePrefix", "material", "Lgregtech/api/unification/material/Material;", "properties", "Lgregtech/api/unification/material/properties/WireProperties;", "generateWireCombiningRecipe", "property", "processCableStripping", "prefix", "gtlitecore"}
)
public final class WireCombinationHandler {
   @NotNull
   public static final WireCombinationHandler INSTANCE = new WireCombinationHandler();
   @NotNull
   private static final OrePrefix[] WIRE_DOUBLING_ORDER;
   @NotNull
   private static final Map<OrePrefix, OrePrefix> cableToWireMap;

   private WireCombinationHandler() {
   }

   public final void init() {
      OrePrefix.wireGtSingle.addProcessingHandler(PropertyKey.WIRE, this::processWireCompression);
      OrePrefix[] var1 = WIRE_DOUBLING_ORDER;
      int var2 = 0;

      for(int var3 = var1.length; var2 < var3; ++var2) {
         OrePrefix wirePrefix = var1[var2];
         wirePrefix.addProcessingHandler(PropertyKey.WIRE, this::generateWireCombiningRecipe);
      }

      for(OrePrefix cablePrefix : cableToWireMap.keySet()) {
         cablePrefix.addProcessingHandler(PropertyKey.WIRE, this::processCableStripping);
      }

   }

   private final void processWireCompression(OrePrefix wirePrefix, Material material, WireProperties properties) {
      for(int startTier = 0; startTier < 4; ++startTier) {
         int i = 1;

         for(int var6 = 5 - startTier; i < var6; ++i) {
            RecipeBuilderExtKt.addRecipe(GTLiteRecipeMaps.LOOM_RECIPES, WireCombinationHandler::processWireCompression$lambda$0);
         }
      }

      for(int i = 1; i < 5; ++i) {
         RecipeBuilderExtKt.addRecipe(GTLiteRecipeMaps.LOOM_RECIPES, WireCombinationHandler::processWireCompression$lambda$1);
      }

   }

   private final void generateWireCombiningRecipe(OrePrefix wirePrefix, Material material, WireProperties property) {
      int wireIndex = ArraysKt.indexOf(WIRE_DOUBLING_ORDER, wirePrefix);
      if (wireIndex < WIRE_DOUBLING_ORDER.length - 1) {
         StringCompanionObject var10000 = StringCompanionObject.INSTANCE;
         String var6 = "%s_wire_%s_doubling";
         Object[] var7 = new Object[]{material, wirePrefix};
         String var14 = String.format(var6, Arrays.copyOf(var7, var7.length));
         Intrinsics.checkNotNullExpressionValue(var14, "format(...)");
         ItemStack var10001 = OreDictUnifier.get(WIRE_DOUBLING_ORDER[wireIndex + 1], material);
         Object[] var5 = new Object[]{new UnificationEntry(wirePrefix, material), new UnificationEntry(wirePrefix, material)};
         ModHandler.addShapelessRecipe(var14, var10001, var5);
      }

      if (wireIndex > 0) {
         StringCompanionObject var15 = StringCompanionObject.INSTANCE;
         String var10 = "%s_wire_%s_splitting";
         Object[] var12 = new Object[]{material, wirePrefix};
         String var16 = String.format(var10, Arrays.copyOf(var12, var12.length));
         Intrinsics.checkNotNullExpressionValue(var16, "format(...)");
         ItemStack var19 = OreDictUnifier.get(WIRE_DOUBLING_ORDER[wireIndex - 1], material, 2);
         Object[] var8 = new Object[]{new UnificationEntry(wirePrefix, material)};
         ModHandler.addShapelessRecipe(var16, var19, var8);
      }

      if (wireIndex < 3) {
         StringCompanionObject var17 = StringCompanionObject.INSTANCE;
         String var11 = "%s_wire_%s_quadrupling";
         Object[] var13 = new Object[]{material, wirePrefix};
         String var18 = String.format(var11, Arrays.copyOf(var13, var13.length));
         Intrinsics.checkNotNullExpressionValue(var18, "format(...)");
         ItemStack var20 = OreDictUnifier.get(WIRE_DOUBLING_ORDER[wireIndex + 2], material);
         Object[] var9 = new Object[]{new UnificationEntry(wirePrefix, material), new UnificationEntry(wirePrefix, material), new UnificationEntry(wirePrefix, material), new UnificationEntry(wirePrefix, material)};
         ModHandler.addShapelessRecipe(var18, var20, var9);
      }

   }

   private final void processCableStripping(OrePrefix prefix, Material material, WireProperties property) {
      RecipeMap var10000 = RecipeMaps.PACKER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var10000, "PACKER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var10000, WireCombinationHandler::processCableStripping$lambda$2);
   }

   private static final Unit processWireCompression$lambda$0(int $i, int $startTier, Material $material, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta((int)Math.pow((double)2.0F, (double)$i));
      $this$addRecipe.inputs(OreDictUnifier.get(WIRE_DOUBLING_ORDER[$startTier], $material, 1 << $i));
      $this$addRecipe.outputs(OreDictUnifier.get(WIRE_DOUBLING_ORDER[$startTier + $i], $material, 1));
      $this$addRecipe.EUt(12L);
      $this$addRecipe.duration(GTLiteValues.getT(10));
      return Unit.INSTANCE;
   }

   private static final Unit processWireCompression$lambda$1(int $i, Material $material, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(1);
      $this$addRecipe.inputs(OreDictUnifier.get(WIRE_DOUBLING_ORDER[$i], $material, 1));
      $this$addRecipe.outputs(OreDictUnifier.get(WIRE_DOUBLING_ORDER[0], $material, (int)Math.pow((double)2.0F, (double)$i)));
      $this$addRecipe.EUt(12L);
      $this$addRecipe.duration(GTLiteValues.getT(10));
      return Unit.INSTANCE;
   }

   private static final Unit processCableStripping$lambda$2(OrePrefix $prefix, Material $material, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input($prefix, $material);
      $this$addRecipe.output((OrePrefix)cableToWireMap.get($prefix), $material);
      $this$addRecipe.output(OrePrefix.plate, Materials.Rubber, (int)(((MaterialStack)$prefix.secondaryMaterials.get(0)).amount / 3628800L));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VA[0]);
      $this$addRecipe.duration(GTLiteValues.getS(5));
      return Unit.INSTANCE;
   }

   static {
      OrePrefix[] var0 = new OrePrefix[]{OrePrefix.wireGtSingle, OrePrefix.wireGtDouble, OrePrefix.wireGtQuadruple, OrePrefix.wireGtOctal, OrePrefix.wireGtHex};
      WIRE_DOUBLING_ORDER = var0;
      Pair[] var1 = new Pair[]{TuplesKt.to(OrePrefix.cableGtSingle, OrePrefix.wireGtSingle), TuplesKt.to(OrePrefix.cableGtDouble, OrePrefix.wireGtDouble), TuplesKt.to(OrePrefix.cableGtQuadruple, OrePrefix.wireGtQuadruple), TuplesKt.to(OrePrefix.cableGtOctal, OrePrefix.wireGtOctal), TuplesKt.to(OrePrefix.cableGtHex, OrePrefix.wireGtHex)};
      cableToWireMap = MapsKt.mapOf(var1);
   }
}
