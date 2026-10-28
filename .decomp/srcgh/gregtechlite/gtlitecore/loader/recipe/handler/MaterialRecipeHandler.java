package gregtechlite.gtlitecore.loader.recipe.handler;

import gregtech.api.GTValues;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.BlastRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.MarkerMaterials.Color;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.DustProperty;
import gregtech.api.unification.material.properties.FluidProperty;
import gregtech.api.unification.material.properties.GemProperty;
import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.IngotProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.common.items.MetaItems;
import gregtechlite.gtlitecore.api.GTLiteValues;
import gregtechlite.gtlitecore.api.extension.ItemStackExtKt;
import gregtechlite.gtlitecore.api.extension.RecipeBuilderExtKt;
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps;
import gregtechlite.gtlitecore.api.unification.material.properties.AlloyBlastProperty;
import gregtechlite.gtlitecore.api.unification.material.properties.GTLitePropertyKey;
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix;
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems;
import gregtechlite.gtlitecore.loader.recipe.producer.AlloyBlastRecipeProducer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tJ \u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J \u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0012H\u0002J\"\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\rH\u0002J \u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0012H\u0002J \u0010\u0018\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0019H\u0002J \u0010\u001a\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0012H\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0002R\u001c\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/handler/MaterialRecipeHandler;", "", "<init>", "()V", "GEM_ORDER", "", "Lgregtech/api/unification/ore/OrePrefix;", "kotlin.jvm.PlatformType", "init", "", "processIngot", "ingotPrefix", "material", "Lgregtech/api/unification/material/Material;", "property", "Lgregtech/api/unification/material/properties/IngotProperty;", "processBlock", "blockPrefix", "Lgregtech/api/unification/material/properties/DustProperty;", "processGemConversion", "gemPrefix", "prevPrefix", "generateImplosionRecipes", "dustPrefix", "generateABSRecipes", "Lgregtechlite/gtlitecore/api/unification/material/properties/AlloyBlastProperty;", "generateMBFRecipes", "getVoltageMultiplier", "", "scaleVoltage", "voltage", "", "workingTier", "gtlitecore"}
)
@SourceDebugExtension({"SMAP\nMaterialRecipeHandler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MaterialRecipeHandler.kt\ngregtechlite/gtlitecore/loader/recipe/handler/MaterialRecipeHandler\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,568:1\n37#2:569\n36#2,3:570\n*S KotlinDebug\n*F\n+ 1 MaterialRecipeHandler.kt\ngregtechlite/gtlitecore/loader/recipe/handler/MaterialRecipeHandler\n*L\n363#1:569\n363#1:570,3\n*E\n"})
public final class MaterialRecipeHandler {
   @NotNull
   public static final MaterialRecipeHandler INSTANCE = new MaterialRecipeHandler();
   @NotNull
   private static final List<OrePrefix> GEM_ORDER;

   private MaterialRecipeHandler() {
   }

   public final void init() {
      OrePrefix.ingot.addProcessingHandler(PropertyKey.INGOT, this::processIngot);
      OrePrefix.block.addProcessingHandler(PropertyKey.DUST, this::processBlock);
      int i = 0;

      for(int var2 = ((Collection)GEM_ORDER).size(); i < var2; ++i) {
         OrePrefix gemPrefix = (OrePrefix)GEM_ORDER.get(i);
         OrePrefix prevGemPrefix = i == 0 ? null : (OrePrefix)GEM_ORDER.get(i - 1);
         gemPrefix.addProcessingHandler(PropertyKey.GEM, MaterialRecipeHandler::init$lambda$0);
      }

      OrePrefix.dust.addProcessingHandler(PropertyKey.DUST, this::generateImplosionRecipes);
      OrePrefix.ingot.addProcessingHandler(GTLitePropertyKey.ALLOY_BLAST, this::generateABSRecipes);
      OrePrefix.dust.addProcessingHandler(PropertyKey.DUST, this::generateMBFRecipes);
   }

   private final void processIngot(OrePrefix ingotPrefix, Material material, IngotProperty property) {
      int workingTier = material.getWorkingTier();
      if (material.hasFlag(MaterialFlags.MORTAR_GRINDABLE) && workingTier <= 3) {
         StringCompanionObject var10000 = StringCompanionObject.INSTANCE;
         String var6 = "mortar_grind_%s";
         Object[] var7 = new Object[]{material};
         String var13 = String.format(var6, Arrays.copyOf(var7, var7.length));
         Intrinsics.checkNotNullExpressionValue(var13, "format(...)");
         ItemStack var10001 = OreDictUnifier.get(OrePrefix.dust, material);
         Object[] var5 = new Object[]{"X", "m", 'X', new UnificationEntry(ingotPrefix, material)};
         ModHandler.addShapedRecipe(var13, var10001, var5);
      }

      if (material.hasFlag(MaterialFlags.GENERATE_ROD)) {
         if (workingTier <= 3) {
            StringCompanionObject var14 = StringCompanionObject.INSTANCE;
            String var10 = "stick_%s";
            Object[] var12 = new Object[]{material};
            String var15 = String.format(var10, Arrays.copyOf(var12, var12.length));
            Intrinsics.checkNotNullExpressionValue(var15, "format(...)");
            ItemStack var30 = OreDictUnifier.get(OrePrefix.stick, material);
            Object[] var8 = new Object[]{"f ", " X", 'X', new UnificationEntry(ingotPrefix, material)};
            ModHandler.addShapedRecipe(var15, var30, var8);
         }

         if (!material.hasFlag(MaterialFlags.NO_WORKING)) {
            RecipeMap var16 = RecipeMaps.EXTRUDER_RECIPES;
            Intrinsics.checkNotNullExpressionValue(var16, "EXTRUDER_RECIPES");
            RecipeBuilderExtKt.addRecipe(var16, MaterialRecipeHandler::processIngot$lambda$1);
            if (material.hasFlag(MaterialFlags.NO_SMASHING)) {
               RecipeBuilderExtKt.addRecipe(GTLiteRecipeMaps.SLICER_RECIPES, MaterialRecipeHandler::processIngot$lambda$2);
            }
         }
      }

      if (material.hasFluid()) {
         Fluid var17 = ((FluidProperty)material.getProperty(PropertyKey.FLUID)).solidifiesFrom();
         Intrinsics.checkNotNull(var17);
         if (!var17.equals((Object)null)) {
            RecipeMap var18 = RecipeMaps.FLUID_SOLIDFICATION_RECIPES;
            Intrinsics.checkNotNullExpressionValue(var18, "FLUID_SOLIDFICATION_RECIPES");
            RecipeBuilderExtKt.addRecipe(var18, MaterialRecipeHandler::processIngot$lambda$3);
         }
      }

      if (material.hasFlag(MaterialFlags.NO_SMASHING)) {
         RecipeMap var19 = RecipeMaps.EXTRUDER_RECIPES;
         Intrinsics.checkNotNullExpressionValue(var19, "EXTRUDER_RECIPES");
         RecipeBuilderExtKt.addRecipe(var19, MaterialRecipeHandler::processIngot$lambda$4);
      }

      RecipeMap var20 = RecipeMaps.ALLOY_SMELTER_RECIPES;
      Intrinsics.checkNotNullExpressionValue(var20, "ALLOY_SMELTER_RECIPES");
      RecipeBuilderExtKt.addRecipe(var20, MaterialRecipeHandler::processIngot$lambda$5);
      ItemStack var21 = OreDictUnifier.get(OrePrefix.block, material);
      Intrinsics.checkNotNull(var21);
      if (!var21.func_190926_b()) {
         RecipeMap var22 = RecipeMaps.ALLOY_SMELTER_RECIPES;
         Intrinsics.checkNotNullExpressionValue(var22, "ALLOY_SMELTER_RECIPES");
         RecipeBuilderExtKt.addRecipe(var22, MaterialRecipeHandler::processIngot$lambda$6);
         var22 = RecipeMaps.COMPRESSOR_RECIPES;
         Intrinsics.checkNotNullExpressionValue(var22, "COMPRESSOR_RECIPES");
         RecipeBuilderExtKt.addRecipe(var22, MaterialRecipeHandler::processIngot$lambda$7);
      }

      if (material.hasFlag(MaterialFlags.GENERATE_PLATE) && !material.hasFlag(MaterialFlags.NO_WORKING)) {
         if (!material.hasFlag(MaterialFlags.NO_SMASHING)) {
            ItemStack plateStack = OreDictUnifier.get(OrePrefix.plate, material);
            Intrinsics.checkNotNull(plateStack);
            if (!plateStack.func_190926_b()) {
               if (workingTier <= 3) {
                  String var24 = "plate_" + material;
                  Object[] var11 = new Object[]{"h", "I", "I", 'I', new UnificationEntry(ingotPrefix, material)};
                  ModHandler.addShapedRecipe(var24, plateStack, var11);
               }

               RecipeMap var25 = RecipeMaps.BENDER_RECIPES;
               Intrinsics.checkNotNullExpressionValue(var25, "BENDER_RECIPES");
               RecipeBuilderExtKt.addRecipe(var25, MaterialRecipeHandler::processIngot$lambda$8);
               var25 = RecipeMaps.FORGE_HAMMER_RECIPES;
               Intrinsics.checkNotNullExpressionValue(var25, "FORGE_HAMMER_RECIPES");
               RecipeBuilderExtKt.addRecipe(var25, MaterialRecipeHandler::processIngot$lambda$9);
            }
         }

         var21 = OreDictUnifier.get(OrePrefix.plate, material);
         Intrinsics.checkNotNull(var21);
         if (!var21.func_190926_b()) {
            if (material.hasFlag(MaterialFlags.NO_SMASHING)) {
               RecipeBuilderExtKt.addRecipe(GTLiteRecipeMaps.SLICER_RECIPES, MaterialRecipeHandler::processIngot$lambda$10);
               RecipeMap var28 = RecipeMaps.EXTRUDER_RECIPES;
               Intrinsics.checkNotNullExpressionValue(var28, "EXTRUDER_RECIPES");
               RecipeBuilderExtKt.addRecipe(var28, MaterialRecipeHandler::processIngot$lambda$11);
            } else {
               RecipeMap var29 = RecipeMaps.EXTRUDER_RECIPES;
               Intrinsics.checkNotNullExpressionValue(var29, "EXTRUDER_RECIPES");
               RecipeBuilderExtKt.addRecipe(var29, MaterialRecipeHandler::processIngot$lambda$12);
            }
         }
      }

   }

   private final void processBlock(OrePrefix blockPrefix, Material material, DustProperty property) {
      ItemStack blockStack = OreDictUnifier.get(blockPrefix, material);
      int workingTier = material.getWorkingTier();
      long materialAmount = blockPrefix.getMaterialAmount(material);
      if (material.hasFluid()) {
         Fluid var10000 = ((FluidProperty)material.getProperty(PropertyKey.FLUID)).solidifiesFrom();
         Intrinsics.checkNotNull(var10000);
         if (!var10000.equals((Object)null)) {
            RecipeMap var19 = RecipeMaps.FLUID_SOLIDFICATION_RECIPES;
            Intrinsics.checkNotNullExpressionValue(var19, "FLUID_SOLIDFICATION_RECIPES");
            RecipeBuilderExtKt.addRecipe(var19, MaterialRecipeHandler::processBlock$lambda$13);
         }
      }

      if (material.hasFlag(MaterialFlags.GENERATE_PLATE)) {
         ItemStack plateStack = OreDictUnifier.get(OrePrefix.plate, material);
         Intrinsics.checkNotNull(plateStack);
         if (!plateStack.func_190926_b()) {
            if (material.hasFlag(MaterialFlags.NO_SMASHING) && !material.hasProperty(PropertyKey.GEM)) {
               RecipeBuilderExtKt.addRecipe(GTLiteRecipeMaps.SLICER_RECIPES, MaterialRecipeHandler::processBlock$lambda$14);
            } else {
               RecipeMap var20 = RecipeMaps.CUTTER_RECIPES;
               Intrinsics.checkNotNullExpressionValue(var20, "CUTTER_RECIPES");
               RecipeBuilderExtKt.addRecipe(var20, MaterialRecipeHandler::processBlock$lambda$15);
            }
         }
      }

      UnificationEntry blockEntry = material.hasProperty(PropertyKey.GEM) ? new UnificationEntry(OrePrefix.gem, material) : (material.hasProperty(PropertyKey.INGOT) ? new UnificationEntry(OrePrefix.ingot, material) : new UnificationEntry(OrePrefix.dust, material));
      ArrayList result = new ArrayList();
      int var10 = (int)(materialAmount / 3628800L);

      for(int thisCollection$iv = 0; thisCollection$iv < var10; ++thisCollection$iv) {
         int var13 = 0;
         result.add(blockEntry);
      }

      if (!material.hasFlag(MaterialFlags.EXCLUDE_BLOCK_CRAFTING_RECIPES)) {
         if (!material.hasFlag(MaterialFlags.EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES) && !ConfigHolder.recipes.disableManualCompression) {
            String var21 = "block_compress_" + material;
            Collection $this$toTypedArray$iv = (Collection)result;
            int $i$f$toTypedArray = 0;
            ItemStack[] var15 = $this$toTypedArray$iv.toArray(new Object[0]);
            ModHandler.addShapelessRecipe(var21, blockStack, Arrays.copyOf(var15, var15.length));
            var21 = "block_decompress_" + material;
            int var10001 = (int)(materialAmount / 3628800L);
            var15 = new ItemStack[]{OreDictUnifier.get(blockEntry)};
            ItemStack var27 = GTUtility.copyFirst(var10001, var15);
            Object[] var17 = new Object[]{new UnificationEntry(blockPrefix, material)};
            ModHandler.addShapelessRecipe(var21, var27, var17);
         }

         if (material.hasProperty(PropertyKey.INGOT)) {
            RecipeMap var23 = RecipeMaps.EXTRUDER_RECIPES;
            Intrinsics.checkNotNullExpressionValue(var23, "EXTRUDER_RECIPES");
            RecipeBuilderExtKt.addRecipe(var23, MaterialRecipeHandler::processBlock$lambda$17);
            var23 = RecipeMaps.ALLOY_SMELTER_RECIPES;
            Intrinsics.checkNotNullExpressionValue(var23, "ALLOY_SMELTER_RECIPES");
            RecipeBuilderExtKt.addRecipe(var23, MaterialRecipeHandler::processBlock$lambda$18);
         } else if (material.hasProperty(PropertyKey.GEM)) {
            RecipeMap var25 = RecipeMaps.COMPRESSOR_RECIPES;
            Intrinsics.checkNotNullExpressionValue(var25, "COMPRESSOR_RECIPES");
            RecipeBuilderExtKt.addRecipe(var25, MaterialRecipeHandler::processBlock$lambda$19);
            var25 = RecipeMaps.FORGE_HAMMER_RECIPES;
            Intrinsics.checkNotNullExpressionValue(var25, "FORGE_HAMMER_RECIPES");
            RecipeBuilderExtKt.addRecipe(var25, MaterialRecipeHandler::processBlock$lambda$20);
         }
      }

   }

   private final void processGemConversion(OrePrefix gemPrefix, OrePrefix prevPrefix, Material material) {
      long materialAmount = gemPrefix.getMaterialAmount(material);
      int workingTier = material.getWorkingTier();
      ItemStack crushedStack = OreDictUnifier.getDust(material, materialAmount);
      if (material.hasFlag(MaterialFlags.MORTAR_GRINDABLE) && workingTier <= 3) {
         String var10000 = "gem_to_dust_" + material + '_' + gemPrefix;
         Object[] var8 = new Object[]{"X", "m", 'X', new UnificationEntry(gemPrefix, material)};
         ModHandler.addShapedRecipe(var10000, crushedStack, var8);
      }

      ItemStack prevStack = prevPrefix == null ? ItemStack.field_190927_a : OreDictUnifier.get(prevPrefix, material, 2);
      if (!prevStack.func_190926_b()) {
         String var11 = "gem_to_gem_" + prevPrefix + '_' + material;
         Object[] var9 = new Object[]{"h", new UnificationEntry(gemPrefix, material)};
         ModHandler.addShapelessRecipe(var11, prevStack, var9);
         RecipeMap var12 = RecipeMaps.CUTTER_RECIPES;
         Intrinsics.checkNotNullExpressionValue(var12, "CUTTER_RECIPES");
         RecipeBuilderExtKt.addRecipe(var12, MaterialRecipeHandler::processGemConversion$lambda$21);
         var12 = RecipeMaps.LASER_ENGRAVER_RECIPES;
         Intrinsics.checkNotNullExpressionValue(var12, "LASER_ENGRAVER_RECIPES");
         RecipeBuilderExtKt.addRecipe(var12, MaterialRecipeHandler::processGemConversion$lambda$22);
      }

   }

   private final void generateImplosionRecipes(OrePrefix dustPrefix, Material material, DustProperty property) {
      int workingTier = material.getWorkingTier();
      ItemStack dustStack = OreDictUnifier.get(dustPrefix, material);
      if (material.hasProperty(PropertyKey.GEM)) {
         ItemStack gemStack = OreDictUnifier.get(OrePrefix.gem, material);
         if (!material.hasFlag(MaterialFlags.EXPLOSIVE) && !material.hasFlag(MaterialFlags.FLAMMABLE)) {
            RecipeBuilderExtKt.addRecipe(GTLiteRecipeMaps.ELECTRIC_IMPLOSION_RECIPES, MaterialRecipeHandler::generateImplosionRecipes$lambda$23);
         }
      }

   }

   private final void generateABSRecipes(OrePrefix ingotPrefix, Material material, AlloyBlastProperty property) {
      if (material.hasProperty(PropertyKey.BLAST)) {
         AlloyBlastRecipeProducer var10000 = property.getRecipeProducer();
         IMaterialProperty var10002 = material.getProperty(PropertyKey.BLAST);
         Intrinsics.checkNotNullExpressionValue(var10002, "getProperty(...)");
         var10000.produce(material, (BlastProperty)var10002);
      }

   }

   private final void generateMBFRecipes(OrePrefix dustPrefix, Material material, DustProperty property) {
      if (material.hasProperty(PropertyKey.INGOT)) {
         MaterialFlag[] var4 = new MaterialFlag[]{MaterialFlags.FLAMMABLE, MaterialFlags.NO_SMELTING};
         if (!material.hasAnyOfFlags(var4)) {
            ItemStack ingotStack = OreDictUnifier.get(OrePrefix.ingot, material);
            Ref.IntRef blastTemp = new Ref.IntRef();
            blastTemp.element = material.getBlastTemperature();
            if (!ingotStack.func_190926_b()) {
               if (blastTemp.element == 0) {
                  blastTemp.element += 1200;
               }

               BlastProperty blastProp = (BlastProperty)material.getProperty(PropertyKey.BLAST);
               if (blastProp != null) {
                  Ref.IntRef duration = new Ref.IntRef();
                  duration.element = blastProp.getDurationOverride();
                  if (duration.element <= 0) {
                     duration.element = (int)Math.max(1L, material.getMass() * (long)blastTemp.element / (long)50);
                  }

                  Ref.IntRef eut = new Ref.IntRef();
                  eut.element = blastProp.getEUtOverride();
                  if (eut.element <= 0) {
                     eut.element = GTValues.VA[2];
                  }

                  RecipeBuilderExtKt.addRecipe(GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES, MaterialRecipeHandler::generateMBFRecipes$lambda$24);
                  if (material.hasFluid()) {
                     RecipeBuilderExtKt.addRecipe(GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES, MaterialRecipeHandler::generateMBFRecipes$lambda$25);
                     RecipeBuilderExtKt.addRecipe(GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES, MaterialRecipeHandler::generateMBFRecipes$lambda$26);
                     if (material.getFluid(FluidStorageKeys.PLASMA) != null) {
                        RecipeBuilderExtKt.addRecipe(GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES, MaterialRecipeHandler::generateMBFRecipes$lambda$27);
                        RecipeBuilderExtKt.addRecipe(GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES, MaterialRecipeHandler::generateMBFRecipes$lambda$28);
                     }
                  }
               }
            }
         }
      }

   }

   private final long getVoltageMultiplier(Material material) {
      return material.getBlastTemperature() >= 2800 ? (long)GTValues.VA[1] : (long)GTValues.VA[0];
   }

   private final long scaleVoltage(int voltage, int workingTier) {
      return GTUtility.scaleVoltage((long)voltage, workingTier);
   }

   private static final void init$lambda$0(OrePrefix $prevGemPrefix, OrePrefix gemPrefix, Material material, GemProperty var3) {
      MaterialRecipeHandler var10000 = INSTANCE;
      Intrinsics.checkNotNull(gemPrefix);
      Intrinsics.checkNotNull(material);
      var10000.processGemConversion(gemPrefix, $prevGemPrefix, material);
   }

   private static final Unit processIngot$lambda$1(OrePrefix $ingotPrefix, Material $material, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.notConsumable(MetaItems.SHAPE_EXTRUDER_ROD);
      $this$addRecipe.input($ingotPrefix, $material);
      $this$addRecipe.output(OrePrefix.stick, $material, 2);
      $this$addRecipe.EUt(GTUtility.scaleVoltage((long)6 * INSTANCE.getVoltageMultiplier($material), $workingTier));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, $material.getMass() * (long)2);
      return Unit.INSTANCE;
   }

   private static final Unit processIngot$lambda$2(OrePrefix $ingotPrefix, Material $material, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.notConsumable(GTLiteMetaItems.INSTANCE.getSLICER_BLADE_STRIPES());
      $this$addRecipe.input($ingotPrefix, $material);
      $this$addRecipe.output(OrePrefix.stick, $material, 2);
      $this$addRecipe.EUt(GTUtility.scaleVoltage((long)6 * INSTANCE.getVoltageMultiplier($material), $workingTier));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, $material.getMass() * (long)2);
      return Unit.INSTANCE;
   }

   private static final Unit processIngot$lambda$3(Material $material, OrePrefix $ingotPrefix, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.notConsumable(MetaItems.SHAPE_MOLD_INGOT);
      $this$addRecipe.fluidInputs(((FluidProperty)$material.getProperty(PropertyKey.FLUID)).solidifiesFrom(144));
      $this$addRecipe.output($ingotPrefix, $material);
      $this$addRecipe.EUt(INSTANCE.scaleVoltage(GTValues.VA[0], $workingTier));
      $this$addRecipe.duration(GTLiteValues.getS(1));
      return Unit.INSTANCE;
   }

   private static final Unit processIngot$lambda$4(Material $material, OrePrefix $ingotPrefix, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.notConsumable(MetaItems.SHAPE_EXTRUDER_INGOT);
      $this$addRecipe.input(OrePrefix.dust, $material);
      $this$addRecipe.output($ingotPrefix, $material);
      $this$addRecipe.EUt(GTUtility.scaleVoltage((long)4 * INSTANCE.getVoltageMultiplier($material), $workingTier));
      $this$addRecipe.duration(GTLiteValues.getT(10));
      return Unit.INSTANCE;
   }

   private static final Unit processIngot$lambda$5(OrePrefix $ingotPrefix, Material $material, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.notConsumable(MetaItems.SHAPE_MOLD_NUGGET);
      $this$addRecipe.input($ingotPrefix, $material);
      $this$addRecipe.output(OrePrefix.nugget, $material, 9);
      $this$addRecipe.EUt(INSTANCE.scaleVoltage(GTValues.VA[0], $workingTier));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, $material.getMass());
      return Unit.INSTANCE;
   }

   private static final Unit processIngot$lambda$6(Material $material, OrePrefix $ingotPrefix, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.notConsumable(MetaItems.SHAPE_MOLD_INGOT);
      $this$addRecipe.input(OrePrefix.block, $material);
      $this$addRecipe.output($ingotPrefix, $material, 9);
      $this$addRecipe.EUt(INSTANCE.scaleVoltage(GTValues.VA[0], $workingTier));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, $material.getMass() * (long)9);
      return Unit.INSTANCE;
   }

   private static final Unit processIngot$lambda$7(OrePrefix $ingotPrefix, Material $material, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input($ingotPrefix, $material, (int)(OrePrefix.block.getMaterialAmount($material) / 3628800L));
      $this$addRecipe.output(OrePrefix.block, $material);
      return Unit.INSTANCE;
   }

   private static final Unit processIngot$lambda$8(OrePrefix $ingotPrefix, Material $material, ItemStack $plateStack, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.circuitMeta(1);
      $this$addRecipe.input($ingotPrefix, $material);
      $this$addRecipe.outputs($plateStack);
      $this$addRecipe.EUt(INSTANCE.scaleVoltage(24, $workingTier));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, $material.getMass());
      return Unit.INSTANCE;
   }

   private static final Unit processIngot$lambda$9(OrePrefix $ingotPrefix, Material $material, ItemStack $plateStack, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input($ingotPrefix, $material, 3);
      ItemStack[] var5 = new ItemStack[]{$plateStack};
      $this$addRecipe.outputs(GTUtility.copyFirst(2, var5));
      $this$addRecipe.EUt(INSTANCE.scaleVoltage(GTValues.VH[1], $workingTier));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, $material.getMass());
      return Unit.INSTANCE;
   }

   private static final Unit processIngot$lambda$10(OrePrefix $ingotPrefix, Material $material, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.notConsumable(GTLiteMetaItems.INSTANCE.getSLICER_BLADE_FLAT());
      $this$addRecipe.input($ingotPrefix, $material);
      $this$addRecipe.output(OrePrefix.plate, $material);
      $this$addRecipe.EUt(GTUtility.scaleVoltage((long)8 * INSTANCE.getVoltageMultiplier($material), $workingTier));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, $material.getMass());
      return Unit.INSTANCE;
   }

   private static final Unit processIngot$lambda$11(Material $material, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.notConsumable(MetaItems.SHAPE_EXTRUDER_PLATE);
      $this$addRecipe.input(OrePrefix.dust, $material);
      $this$addRecipe.output(OrePrefix.plate, $material);
      $this$addRecipe.EUt(GTUtility.scaleVoltage((long)8 * INSTANCE.getVoltageMultiplier($material), $workingTier));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, $material.getMass());
      return Unit.INSTANCE;
   }

   private static final Unit processIngot$lambda$12(OrePrefix $ingotPrefix, Material $material, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.notConsumable(MetaItems.SHAPE_EXTRUDER_PLATE);
      $this$addRecipe.input($ingotPrefix, $material);
      $this$addRecipe.output(OrePrefix.plate, $material);
      $this$addRecipe.EUt(GTUtility.scaleVoltage((long)8 * INSTANCE.getVoltageMultiplier($material), $workingTier));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, $material.getMass());
      return Unit.INSTANCE;
   }

   private static final Unit processBlock$lambda$13(Material $material, long $materialAmount, ItemStack $blockStack, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.notConsumable(MetaItems.SHAPE_MOLD_BLOCK);
      $this$addRecipe.fluidInputs(((FluidProperty)$material.getProperty(PropertyKey.FLUID)).solidifiesFrom((int)($materialAmount * (long)144 / 3628800L)));
      $this$addRecipe.outputs($blockStack);
      $this$addRecipe.EUt(INSTANCE.scaleVoltage(GTValues.VA[0], $workingTier));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, $material.getMass());
      $this$addRecipe.buildAndRegister();
      return Unit.INSTANCE;
   }

   private static final Unit processBlock$lambda$14(OrePrefix $blockPrefix, Material $material, long $materialAmount, ItemStack $plateStack, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.notConsumable(GTLiteMetaItems.INSTANCE.getSLICER_BLADE_OCTAGONAL());
      $this$addRecipe.input($blockPrefix, $material);
      int var10001 = (int)($materialAmount / 3628800L);
      ItemStack[] var7 = new ItemStack[]{$plateStack};
      $this$addRecipe.outputs(GTUtility.copyFirst(var10001, var7));
      $this$addRecipe.EUt(INSTANCE.scaleVoltage(GTValues.VA[1], $workingTier));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, $material.getMass() * (long)8);
      return Unit.INSTANCE;
   }

   private static final Unit processBlock$lambda$15(OrePrefix $blockPrefix, Material $material, long $materialAmount, ItemStack $plateStack, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input($blockPrefix, $material);
      int var10001 = (int)($materialAmount / 3628800L);
      ItemStack[] var7 = new ItemStack[]{$plateStack};
      $this$addRecipe.outputs(GTUtility.copyFirst(var10001, var7));
      $this$addRecipe.EUt(INSTANCE.scaleVoltage(GTValues.VA[1], $workingTier));
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, $material.getMass() * (long)8);
      return Unit.INSTANCE;
   }

   private static final Unit processBlock$lambda$17(Material $material, long $materialAmount, ItemStack $blockStack, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.notConsumable(MetaItems.SHAPE_EXTRUDER_BLOCK);
      $this$addRecipe.input(OrePrefix.ingot, $material, (int)($materialAmount / 3628800L));
      $this$addRecipe.outputs($blockStack);
      $this$addRecipe.EUt(GTUtility.scaleVoltage((long)8 * INSTANCE.getVoltageMultiplier($material), $workingTier));
      $this$addRecipe.duration(GTLiteValues.getT(10));
      return Unit.INSTANCE;
   }

   private static final Unit processBlock$lambda$18(Material $material, long $materialAmount, ItemStack $blockStack, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.notConsumable(MetaItems.SHAPE_MOLD_BLOCK);
      $this$addRecipe.input(OrePrefix.ingot, $material, (int)($materialAmount / 3628800L));
      $this$addRecipe.outputs($blockStack);
      $this$addRecipe.EUt(GTUtility.scaleVoltage((long)4 * INSTANCE.getVoltageMultiplier($material), $workingTier));
      $this$addRecipe.duration(GTLiteValues.getT(5));
      return Unit.INSTANCE;
   }

   private static final Unit processBlock$lambda$19(Material $material, ItemStack $blockStack, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input(OrePrefix.gem, $material, (int)(OrePrefix.block.getMaterialAmount($material) / 3628800L));
      $this$addRecipe.outputs($blockStack);
      $this$addRecipe.EUt(INSTANCE.scaleVoltage(2, $workingTier));
      $this$addRecipe.duration(GTLiteValues.getS(15));
      return Unit.INSTANCE;
   }

   private static final Unit processBlock$lambda$20(OrePrefix $blockPrefix, Material $material, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input($blockPrefix, $material);
      $this$addRecipe.output(OrePrefix.gem, $material, (int)(OrePrefix.block.getMaterialAmount($material) / 3628800L));
      $this$addRecipe.EUt(INSTANCE.scaleVoltage(24, $workingTier));
      $this$addRecipe.duration(GTLiteValues.getS(5));
      return Unit.INSTANCE;
   }

   private static final Unit processGemConversion$lambda$21(OrePrefix $gemPrefix, Material $material, ItemStack $prevStack, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.input($gemPrefix, $material);
      $this$addRecipe.outputs($prevStack);
      Intrinsics.checkNotNull($this$addRecipe);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, GTValues.VH[1]);
      $this$addRecipe.duration(GTLiteValues.getS(1));
      return Unit.INSTANCE;
   }

   private static final Unit processGemConversion$lambda$22(ItemStack $prevStack, OrePrefix $gemPrefix, Material $material, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      $this$addRecipe.notConsumable(OrePrefix.craftingLens, (Material)Color.White);
      $this$addRecipe.inputs($prevStack);
      $this$addRecipe.output($gemPrefix, $material);
      $this$addRecipe.EUt(INSTANCE.scaleVoltage(GTValues.VHA[3], $workingTier));
      $this$addRecipe.duration(GTLiteValues.getS(15));
      return Unit.INSTANCE;
   }

   private static final Unit generateImplosionRecipes$lambda$23(ItemStack $dustStack, ItemStack $gemStack, int $workingTier, SimpleRecipeBuilder $this$addRecipe) {
      Intrinsics.checkNotNullParameter($this$addRecipe, "$this$addRecipe");
      Intrinsics.checkNotNull($dustStack);
      $this$addRecipe.inputs(ItemStackExtKt.copy($dustStack, 4));
      Intrinsics.checkNotNull($gemStack);
      $this$addRecipe.outputs(ItemStackExtKt.copy($gemStack, 3));
      $this$addRecipe.chancedOutput(OrePrefix.dust, Materials.DarkAsh, 2500, 0);
      $this$addRecipe.EUt(INSTANCE.scaleVoltage(GTValues.VA[1], $workingTier));
      $this$addRecipe.duration(GTLiteValues.getS(1));
      return Unit.INSTANCE;
   }

   private static final Unit generateMBFRecipes$lambda$24(OrePrefix $dustPrefix, Material $material, ItemStack $ingotStack, Ref.IntRef $eut, Ref.IntRef $duration, Ref.IntRef $blastTemp, BlastRecipeBuilder $this$addRecipe) {
      Intrinsics.checkNotNullParameter($this$addRecipe, "$this$addRecipe");
      $this$addRecipe.circuitMeta(1);
      $this$addRecipe.input($dustPrefix, $material);
      $this$addRecipe.outputs($ingotStack);
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, $eut.element);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, (double)$duration.element * (double)0.5F);
      $this$addRecipe.blastFurnaceTemp($blastTemp.element);
      return Unit.INSTANCE;
   }

   private static final Unit generateMBFRecipes$lambda$25(OrePrefix $dustPrefix, Material $material, Ref.IntRef $eut, Ref.IntRef $duration, Ref.IntRef $blastTemp, BlastRecipeBuilder $this$addRecipe) {
      Intrinsics.checkNotNullParameter($this$addRecipe, "$this$addRecipe");
      $this$addRecipe.circuitMeta(2);
      $this$addRecipe.input($dustPrefix, $material);
      $this$addRecipe.fluidOutputs($material.getFluid(144));
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, $eut.element);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, (double)$duration.element * (double)0.5F);
      $this$addRecipe.blastFurnaceTemp($blastTemp.element);
      return Unit.INSTANCE;
   }

   private static final Unit generateMBFRecipes$lambda$26(ItemStack $ingotStack, Material $material, Ref.IntRef $eut, Ref.IntRef $duration, Ref.IntRef $blastTemp, BlastRecipeBuilder $this$addRecipe) {
      Intrinsics.checkNotNullParameter($this$addRecipe, "$this$addRecipe");
      $this$addRecipe.circuitMeta(4);
      $this$addRecipe.inputs($ingotStack);
      $this$addRecipe.fluidOutputs($material.getFluid(144));
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, $eut.element);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, (double)$duration.element * (double)0.5F);
      $this$addRecipe.blastFurnaceTemp($blastTemp.element);
      return Unit.INSTANCE;
   }

   private static final Unit generateMBFRecipes$lambda$27(OrePrefix $dustPrefix, Material $material, Ref.IntRef $eut, Ref.IntRef $duration, Ref.IntRef $blastTemp, BlastRecipeBuilder $this$addRecipe) {
      Intrinsics.checkNotNullParameter($this$addRecipe, "$this$addRecipe");
      $this$addRecipe.circuitMeta(3);
      $this$addRecipe.input($dustPrefix, $material);
      $this$addRecipe.fluidOutputs($material.getPlasma(144));
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, $eut.element);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, (double)$duration.element * (double)0.5F);
      $this$addRecipe.blastFurnaceTemp($blastTemp.element);
      return Unit.INSTANCE;
   }

   private static final Unit generateMBFRecipes$lambda$28(ItemStack $ingotStack, Material $material, Ref.IntRef $eut, Ref.IntRef $duration, Ref.IntRef $blastTemp, BlastRecipeBuilder $this$addRecipe) {
      Intrinsics.checkNotNullParameter($this$addRecipe, "$this$addRecipe");
      $this$addRecipe.circuitMeta(5);
      $this$addRecipe.inputs($ingotStack);
      $this$addRecipe.fluidOutputs($material.getPlasma(144));
      RecipeBuilderExtKt.EUt((RecipeBuilder)$this$addRecipe, $eut.element);
      RecipeBuilderExtKt.duration((RecipeBuilder)$this$addRecipe, (double)$duration.element * (double)0.5F);
      $this$addRecipe.blastFurnaceTemp($blastTemp.element);
      return Unit.INSTANCE;
   }

   static {
      OrePrefix[] var0 = new OrePrefix[]{OrePrefix.gemChipped, OrePrefix.gemFlawed, OrePrefix.gem, OrePrefix.gemFlawless, OrePrefix.gemExquisite, GTLiteOrePrefix.gemSolitary};
      GEM_ORDER = CollectionsKt.listOf(var0);
   }
}
