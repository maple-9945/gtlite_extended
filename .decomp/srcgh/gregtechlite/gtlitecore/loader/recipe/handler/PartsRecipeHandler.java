package gregtechlite.gtlitecore.loader.recipe.handler;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterial;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.MarkerMaterials.Color;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.DustProperty;
import gregtech.api.unification.material.properties.GemProperty;
import gregtech.api.unification.material.properties.IngotProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.DyeUtil;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.common.items.MetaItems;
import gregtechlite.gtlitecore.api.GTLiteValues;
import gregtechlite.gtlitecore.api.extension.ItemStackExtKt;
import gregtechlite.gtlitecore.api.extension.RecipeBuilderExtKt;
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps;
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials;
import gregtechlite.gtlitecore.api.unification.material.properties.AmorphousLensProperty;
import gregtechlite.gtlitecore.api.unification.material.properties.GTLitePropertyKey;
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix;
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J \u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000fH\u0002J \u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0011H\u0002J \u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J \u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0016H\u0002J \u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0016H\u0002J \u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0016H\u0002J\u001e\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001e\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0002¨\u0006%"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/handler/PartsRecipeHandler;", "", "<init>", "()V", "init", "", "processStick", "stickPrefix", "Lgregtech/api/unification/ore/OrePrefix;", "material", "Lgregtech/api/unification/material/Material;", "property", "Lgregtech/api/unification/material/properties/DustProperty;", "processLens", "lensPrefix", "Lgregtech/api/unification/material/properties/GemProperty;", "processAmorphousLens", "Lgregtechlite/gtlitecore/api/unification/material/properties/AmorphousLensProperty;", "processScrew", "screwPrefix", "processRound", "roundPrefix", "Lgregtech/api/unification/material/properties/IngotProperty;", "processDrillHead", "drillHeadPrefix", "processTurbineBlade", "turbineBladePrefix", "processSheetedFrame", "sheetedFramePrefix", "processWall", "wallGtPrefix", "getVoltageMultiplier", "", "scaleVoltage", "voltage", "", "workingTier", "gtlitecore"}
)
public final class PartsRecipeHandler {
   @NotNull
   public static final PartsRecipeHandler INSTANCE = new PartsRecipeHandler();

   private PartsRecipeHandler() {
   }

   public final void init() {
      OrePrefix.stick.addProcessingHandler(PropertyKey.DUST, this::processStick);
      OrePrefix.lens.addProcessingHandler(PropertyKey.GEM, this::processLens);
      OrePrefix.lens.addProcessingHandler(GTLitePropertyKey.AMORPHOUS_LENS, this::processAmorphousLens);
      OrePrefix.screw.addProcessingHandler(PropertyKey.DUST, this::processScrew);
      OrePrefix.round.addProcessingHandler(PropertyKey.INGOT, this::processRound);
      OrePrefix.toolHeadDrill.addProcessingHandler(PropertyKey.INGOT, this::processDrillHead);
      OrePrefix.turbineBlade.addProcessingHandler(PropertyKey.INGOT, this::processTurbineBlade);
      GTLiteOrePrefix.sheetedFrame.addProcessingHandler(PropertyKey.DUST, this::processSheetedFrame);
      GTLiteOrePrefix.wallGt.addProcessingHandler(PropertyKey.DUST, this::processWall);
   }

   private final void processStick(OrePrefix stickPrefix, Material material, DustProperty property) {
      int workingTier = material.getWorkingTier();
      if (material.hasProperty(PropertyKey.GEM)) {
         RecipeBuilder var10000 = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.LATHE_RECIPES.recipeBuilder()).input(OrePrefix.gem, material)).EUt(this.scaleVoltage(GTValues.VH[1], workingTier));
         Intrinsics.checkNotNullExpressionValue(var10000, "EUt(...)");
         SimpleRecipeBuilder builder = (SimpleRecipeBuilder)RecipeBuilderExtKt.duration(var10000, Math.max(material.getMass() * (long)2, 1L));
         if (ConfigHolder.recipes.harderRods) {
            SimpleRecipeBuilder var11 = (SimpleRecipeBuilder)((SimpleRecipeBuilder)builder.output(stickPrefix, material)).output(OrePrefix.dustSmall, material, 2);
         } else {
            SimpleRecipeBuilder var12 = (SimpleRecipeBuilder)builder.output(OrePrefix.stick, material, 2);
         }

         builder.buildAndRegister();
      }

      if (material.hasProperty(PropertyKey.INGOT)) {
         if (!material.hasFlag(MaterialFlags.NO_SMASHING)) {
            RecipeBuilder var13 = ((SimpleRecipeBuilder)RecipeMaps.LATHE_RECIPES.recipeBuilder()).input(OrePrefix.ingot, material);
            Intrinsics.checkNotNullExpressionValue(var13, "input(...)");
            SimpleRecipeBuilder builder = (SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeBuilderExtKt.duration(var13, Math.max(material.getMass() * (long)2, 1L))).EUt(this.scaleVoltage(GTValues.VH[1], workingTier));
            if (ConfigHolder.recipes.harderRods) {
               SimpleRecipeBuilder var14 = (SimpleRecipeBuilder)((SimpleRecipeBuilder)builder.output(stickPrefix, material)).output(OrePrefix.dustSmall, material, 2);
            } else {
               SimpleRecipeBuilder var15 = (SimpleRecipeBuilder)builder.output(stickPrefix, material, 2);
            }

            builder.buildAndRegister();
         } else {
            RecipeBuilder var16 = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)GTLiteRecipeMaps.SLICER_RECIPES.recipeBuilder()).notConsumable(GTLiteMetaItems.INSTANCE.getSLICER_BLADE_STRIPES())).input(OrePrefix.ingot, material);
            Intrinsics.checkNotNullExpressionValue(var16, "input(...)");
            RecipeBuilder var6 = RecipeBuilderExtKt.duration(var16, Math.max(material.getMass() * (long)2, 1L));
            Intrinsics.checkNotNullExpressionValue(var6, "duration(...)");
            SimpleRecipeBuilder builder = (SimpleRecipeBuilder)RecipeBuilderExtKt.EUt(var6, GTValues.VH[1]);
            if (ConfigHolder.recipes.harderRods) {
               SimpleRecipeBuilder var17 = (SimpleRecipeBuilder)((SimpleRecipeBuilder)builder.output(OrePrefix.stick, material)).output(OrePrefix.dustSmall, material, 2);
            } else {
               SimpleRecipeBuilder var18 = (SimpleRecipeBuilder)builder.output(OrePrefix.stick, material, 2);
            }

            builder.buildAndRegister();
         }
      }

      if (material.hasFlag(MaterialFlags.GENERATE_BOLT_SCREW)) {
         ItemStack boltStack = OreDictUnifier.get(OrePrefix.bolt, material);
         if (!material.hasFlag(MaterialFlags.NO_SMASHING)) {
            SimpleRecipeBuilder var19 = (SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.CUTTER_RECIPES.recipeBuilder()).input(stickPrefix, material);
            Intrinsics.checkNotNull(boltStack);
            RecipeBuilder var20 = var19.outputs(ItemStackExtKt.copy(boltStack, 4));
            Intrinsics.checkNotNullExpressionValue(var20, "outputs(...)");
            ((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeBuilderExtKt.duration(var20, Math.max(material.getMass() * (long)2, 1L))).EUt(this.scaleVoltage(4, workingTier))).buildAndRegister();
         } else if (!material.hasProperty(PropertyKey.GEM)) {
            SimpleRecipeBuilder var21 = (SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)GTLiteRecipeMaps.SLICER_RECIPES.recipeBuilder()).notConsumable(GTLiteMetaItems.INSTANCE.getSLICER_BLADE_OCTAGONAL())).input(stickPrefix, material);
            Intrinsics.checkNotNull(boltStack);
            RecipeBuilder var22 = var21.outputs(ItemStackExtKt.copy(boltStack, 4));
            Intrinsics.checkNotNullExpressionValue(var22, "outputs(...)");
            ((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeBuilderExtKt.duration(var22, Math.max(material.getMass() * (long)2, 1L))).EUt(this.scaleVoltage(4, workingTier))).buildAndRegister();
         }

         if (workingTier <= 3) {
            String var23 = "bolt_saw_" + material;
            Intrinsics.checkNotNull(boltStack);
            ItemStack var10001 = ItemStackExtKt.copy(boltStack, 2);
            Object[] var10 = new Object[]{"s ", " X", 'X', new UnificationEntry(OrePrefix.stick, material)};
            ModHandler.addShapedRecipe(var23, var10001, var10);
         }
      }

      if (material.hasFluid()) {
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()).notConsumable(MetaItems.SHAPE_MOLD_ROD)).fluidInputs(material.getFluid(72))).output(stickPrefix, material)).EUt(this.scaleVoltage(GTValues.VA[1], workingTier))).duration(GTLiteValues.getS(7) + GTLiteValues.getT(5))).buildAndRegister();
      }

   }

   private final void processLens(OrePrefix lensPrefix, Material material, GemProperty property) {
      int workingTier = material.getWorkingTier();
      ItemStack var10000 = OreDictUnifier.get(OrePrefix.plate, material);
      Intrinsics.checkNotNull(var10000);
      if (!var10000.func_190926_b()) {
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)GTLiteRecipeMaps.POLISHER_RECIPES.recipeBuilder()).input(OrePrefix.plate, material)).output(lensPrefix, material)).output(OrePrefix.dustSmall, material)).EUt(this.scaleVoltage(GTValues.VA[2], workingTier))).duration(GTLiteValues.getMin(1))).buildAndRegister();
      }

      var10000 = OreDictUnifier.get(OrePrefix.gemExquisite, material);
      Intrinsics.checkNotNull(var10000);
      if (!var10000.func_190926_b()) {
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)GTLiteRecipeMaps.POLISHER_RECIPES.recipeBuilder()).input(OrePrefix.gemExquisite, material)).output(lensPrefix, material)).output(OrePrefix.dust, material, 2)).EUt(this.scaleVoltage(GTValues.VA[1], workingTier))).duration(GTLiteValues.getMin(2))).buildAndRegister();
      }

      var10000 = OreDictUnifier.get(GTLiteOrePrefix.gemSolitary, material);
      Intrinsics.checkNotNull(var10000);
      if (!var10000.func_190926_b()) {
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)GTLiteRecipeMaps.POLISHER_RECIPES.recipeBuilder()).input(GTLiteOrePrefix.gemSolitary, material)).output(lensPrefix, material, 2)).output(OrePrefix.dust, material, 4)).EUt(this.scaleVoltage(GTValues.VA[2], workingTier))).duration(GTLiteValues.getMin(1))).buildAndRegister();
      }

      ItemStack lensStack = OreDictUnifier.get(lensPrefix, material);
      if (Intrinsics.areEqual(material, Materials.Diamond)) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.LightBlue);
      } else if (Intrinsics.areEqual(material, Materials.Ruby)) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Red);
      } else if (Intrinsics.areEqual(material, Materials.Emerald)) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Green);
      } else if (Intrinsics.areEqual(material, Materials.Glass)) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens.name() + material.toCamelCaseString());
      } else if (Intrinsics.areEqual(material, Materials.Uvarovite)) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Lime);
      } else if (Intrinsics.areEqual(material, Materials.Malachite)) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Green);
      } else if (Intrinsics.areEqual(material, Materials.Andradite)) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Brown);
      } else if (Intrinsics.areEqual(material, Materials.GreenSapphire)) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Lime);
      } else if (Intrinsics.areEqual(material, Materials.Pyrope)) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Purple);
      } else if (Intrinsics.areEqual(material, Materials.Rutile)) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Pink);
      } else if (Intrinsics.areEqual(material, Materials.Spessartine)) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Red);
      } else if (Intrinsics.areEqual(material, Materials.Olivine)) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Lime);
      } else if (Intrinsics.areEqual(material, Materials.Amethyst)) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Purple);
      } else if (Intrinsics.areEqual(material, GTLiteMaterials.INSTANCE.getTanzanite())) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Purple);
      } else if (Intrinsics.areEqual(material, GTLiteMaterials.INSTANCE.getAlbite())) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Pink);
      } else if (Intrinsics.areEqual(material, GTLiteMaterials.INSTANCE.getFluorite())) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Green);
      } else if (Intrinsics.areEqual(material, GTLiteMaterials.INSTANCE.getCelestine())) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Cyan);
      } else if (Intrinsics.areEqual(material, GTLiteMaterials.INSTANCE.getBaddeleyite())) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Cyan);
      } else if (Intrinsics.areEqual(material, GTLiteMaterials.INSTANCE.getNephelite())) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Red);
      } else if (Intrinsics.areEqual(material, GTLiteMaterials.INSTANCE.getCryolite())) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.LightBlue);
      } else if (Intrinsics.areEqual(material, GTLiteMaterials.INSTANCE.getJade())) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Green);
      } else if (Intrinsics.areEqual(material, GTLiteMaterials.INSTANCE.getHeterodiamond())) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Purple);
      } else if (Intrinsics.areEqual(material, GTLiteMaterials.INSTANCE.getHexagonalSiliconNitride())) {
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)Color.Purple);
      } else {
         EnumDyeColor var11 = DyeUtil.determineDyeColor(material.getMaterialRGB());
         Intrinsics.checkNotNullExpressionValue(var11, "determineDyeColor(...)");
         EnumDyeColor dyeColor = var11;
         MarkerMaterial colorMaterial = (MarkerMaterial)Color.COLORS.get(dyeColor);
         OreDictUnifier.registerOre(lensStack, OrePrefix.craftingLens, (Material)colorMaterial);
      }

   }

   private final void processAmorphousLens(OrePrefix lensPrefix, Material material, AmorphousLensProperty property) {
      int workingTier = material.getWorkingTier();
      ItemStack var10000 = OreDictUnifier.get(OrePrefix.plate, material);
      Intrinsics.checkNotNull(var10000);
      if (!var10000.func_190926_b()) {
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)GTLiteRecipeMaps.POLISHER_RECIPES.recipeBuilder()).input(OrePrefix.plate, material)).output(lensPrefix, material)).output(OrePrefix.dustSmall, material)).EUt(this.scaleVoltage(GTValues.VA[2], workingTier))).duration(GTLiteValues.getMin(1))).buildAndRegister();
      }

   }

   private final void processScrew(OrePrefix screwPrefix, Material material, DustProperty property) {
      int workingTier = material.getWorkingTier();
      if (material.hasFluid()) {
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()).notConsumable(GTLiteMetaItems.INSTANCE.getSHAPE_MOLD_SCREW())).fluidInputs(material.getFluid(18))).output(screwPrefix, material)).EUt(GTUtility.scaleVoltage(Math.max((long)GTValues.VA[2], (long)4 * this.getVoltageMultiplier(material)), workingTier))).duration(GTLiteValues.getS(2) + GTLiteValues.getT(5))).buildAndRegister();
      }

   }

   private final void processRound(OrePrefix roundPrefix, Material material, IngotProperty property) {
      int workingTier = material.getWorkingTier();
      if (!OreDictUnifier.get(OrePrefix.ingot, material).func_190926_b()) {
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()).notConsumable(GTLiteMetaItems.INSTANCE.getSHAPE_EXTRUDER_ROUND())).input(OrePrefix.ingot, material)).output(roundPrefix, material, 4)).EUt(GTUtility.scaleVoltage(Math.max((long)GTValues.VA[2], (long)4 * this.getVoltageMultiplier(material)), workingTier))).duration(GTLiteValues.getS(2))).buildAndRegister();
      } else {
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()).notConsumable(GTLiteMetaItems.INSTANCE.getSHAPE_EXTRUDER_ROUND())).input(OrePrefix.dust, material)).output(roundPrefix, material, 4)).EUt(GTUtility.scaleVoltage(Math.max((long)GTValues.VA[2], (long)4 * this.getVoltageMultiplier(material)), workingTier))).duration(GTLiteValues.getS(2))).buildAndRegister();
      }

   }

   private final void processDrillHead(OrePrefix drillHeadPrefix, Material material, IngotProperty property) {
      int workingTier = material.getWorkingTier();
      if (material.hasFluid()) {
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()).notConsumable(GTLiteMetaItems.INSTANCE.getSHAPE_MOLD_DRILL_HEAD())).fluidInputs(material.getFluid(576))).output(drillHeadPrefix, material)).EUt(GTUtility.scaleVoltage((long)GTValues.VA[2], workingTier))).duration(GTLiteValues.getS(5))).buildAndRegister();
      }

      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()).notConsumable(GTLiteMetaItems.INSTANCE.getSHAPE_EXTRUDER_DRILL_HEAD())).input(OrePrefix.ingot, material, 4)).output(drillHeadPrefix, material, 1)).EUt(GTUtility.scaleVoltage((long)GTValues.VA[2], workingTier))).duration(GTLiteValues.getS(5))).buildAndRegister();
   }

   private final void processTurbineBlade(OrePrefix turbineBladePrefix, Material material, IngotProperty property) {
      int workingTier = material.getWorkingTier();
      if (material.hasFluid()) {
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()).notConsumable(GTLiteMetaItems.INSTANCE.getSHAPE_MOLD_TURBINE_BLADE())).fluidInputs(material.getFluid(864))).output(turbineBladePrefix, material)).EUt(GTUtility.scaleVoltage(Math.max((long)GTValues.VA[2], (long)6 * this.getVoltageMultiplier(material)), workingTier))).duration(GTLiteValues.getS(20))).buildAndRegister();
      }

      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()).notConsumable(GTLiteMetaItems.INSTANCE.getSHAPE_EXTRUDER_TURBINE_BLADE())).input(OrePrefix.ingot, material, 6)).output(turbineBladePrefix, material)).EUt(GTUtility.scaleVoltage((long)GTValues.VA[2], workingTier))).duration(GTLiteValues.getS(20))).buildAndRegister();
   }

   public final void processSheetedFrame(@NotNull OrePrefix sheetedFramePrefix, @NotNull Material material, @NotNull DustProperty property) {
      Intrinsics.checkNotNullParameter(sheetedFramePrefix, "sheetedFramePrefix");
      Intrinsics.checkNotNullParameter(material, "material");
      Intrinsics.checkNotNullParameter(property, "property");
      if (material.hasFlag(MaterialFlags.GENERATE_FRAME)) {
         String var10000 = material + "_sheeted_frame";
         ItemStack var10001 = OreDictUnifier.get(sheetedFramePrefix, material, 12);
         Object[] var4 = new Object[]{"PFP", "PhP", "PFP", 'P', new UnificationEntry(OrePrefix.plate, material), 'F', new UnificationEntry(OrePrefix.frameGt, material)};
         ModHandler.addShapedRecipe(var10000, var10001, var4);
         RecipeBuilder var5 = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()).circuitMeta(10)).input(OrePrefix.plate, material, 3)).input(OrePrefix.frameGt, material, 1)).output(sheetedFramePrefix, material, 6);
         Intrinsics.checkNotNullExpressionValue(var5, "output(...)");
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeBuilderExtKt.EUt(var5, GTValues.VA[0])).duration(GTLiteValues.getS(2) + GTLiteValues.getT(5))).buildAndRegister();
      }
   }

   public final void processWall(@NotNull OrePrefix wallGtPrefix, @NotNull Material material, @NotNull DustProperty property) {
      Intrinsics.checkNotNullParameter(wallGtPrefix, "wallGtPrefix");
      Intrinsics.checkNotNullParameter(material, "material");
      Intrinsics.checkNotNullParameter(property, "property");
      if (material.hasFlag(MaterialFlags.GENERATE_PLATE) && material.hasFlag(MaterialFlags.GENERATE_BOLT_SCREW)) {
         String var10000 = material + "_wall_gt";
         ItemStack var10001 = OreDictUnifier.get(wallGtPrefix, material, 6);
         Object[] var4 = new Object[]{"hPS", "P P", "SPd", 'P', new UnificationEntry(OrePrefix.plate, material), 'S', new UnificationEntry(OrePrefix.screw, material)};
         ModHandler.addShapedRecipe(var10000, var10001, var4);
         RecipeBuilder var5 = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()).circuitMeta(11)).input(OrePrefix.plate, material, 2)).input(OrePrefix.screw, material, 1)).output(wallGtPrefix, material, 3);
         Intrinsics.checkNotNullExpressionValue(var5, "output(...)");
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeBuilderExtKt.EUt(var5, GTValues.VA[0])).duration(GTLiteValues.getS(2) + GTLiteValues.getT(5))).buildAndRegister();
      }
   }

   private final long getVoltageMultiplier(Material material) {
      return material.getBlastTemperature() >= 2800 ? (long)GTValues.VA[1] : (long)GTValues.VA[0];
   }

   private final long scaleVoltage(int voltage, int workingTier) {
      return GTUtility.scaleVoltage((long)voltage, workingTier);
   }
}
