package gregtech.loaders.recipe.handlers;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import java.util.List;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

public class OreRecipeHandler {
   public static void register() {
      OrePrefix.ore.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
      OrePrefix.oreEndstone.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOreDouble);
      OrePrefix.oreNetherrack.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOreDouble);
      if (ConfigHolder.worldgen.allUniqueStoneTypes) {
         OrePrefix.oreGranite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
         OrePrefix.oreDiorite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
         OrePrefix.oreAndesite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
         OrePrefix.oreBasalt.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
         OrePrefix.oreBlackgranite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
         OrePrefix.oreMarble.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
         OrePrefix.oreRedgranite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
         OrePrefix.oreSand.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
         OrePrefix.oreRedSand.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
      }

      OrePrefix.crushed.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processCrushedOre);
      OrePrefix.crushedPurified.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processCrushedPurified);
      OrePrefix.crushedCentrifuged.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processCrushedCentrifuged);
      OrePrefix.dustImpure.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processDirtyDust);
      OrePrefix.dustPure.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processPureDust);
   }

   private static void processMetalSmelting(OrePrefix crushedPrefix, Material material, OreProperty property) {
      Material smeltingResult = property.getDirectSmeltResult() != null ? property.getDirectSmeltResult() : material;
      if (smeltingResult.hasProperty(PropertyKey.INGOT)) {
         ItemStack ingotStack = OreDictUnifier.get(OrePrefix.ingot, smeltingResult);
         if (!ingotStack.func_190926_b() && doesMaterialUseNormalFurnace(smeltingResult)) {
            ModHandler.addSmeltingRecipe(new UnificationEntry(crushedPrefix, material), ingotStack, 0.5F);
         }
      }

   }

   public static void processOre(OrePrefix orePrefix, Material material, OreProperty property) {
      processOre(orePrefix, material, property, 1);
   }

   public static void processOreDouble(OrePrefix orePrefix, Material material, OreProperty property) {
      processOre(orePrefix, material, property, 2);
   }

   public static void processOre(OrePrefix orePrefix, Material material, OreProperty property, int oreTypeMultiplier) {
      Material byproductMaterial = property.getOreByProduct(0, material);
      ItemStack byproductStack = OreDictUnifier.get(OrePrefix.gem, byproductMaterial);
      if (byproductStack.func_190926_b()) {
         byproductStack = OreDictUnifier.get(OrePrefix.dust, byproductMaterial);
      }

      ItemStack crushedStack = OreDictUnifier.get(OrePrefix.crushed, material);
      Material smeltingMaterial = property.getDirectSmeltResult() == null ? material : property.getDirectSmeltResult();
      double amountOfCrushedOre = (double)property.getOreMultiplier();
      ItemStack ingotStack;
      if (smeltingMaterial.hasProperty(PropertyKey.INGOT)) {
         ingotStack = OreDictUnifier.get(OrePrefix.ingot, smeltingMaterial);
      } else if (smeltingMaterial.hasProperty(PropertyKey.GEM)) {
         ingotStack = OreDictUnifier.get(OrePrefix.gem, smeltingMaterial);
      } else {
         ingotStack = OreDictUnifier.get(OrePrefix.dust, smeltingMaterial);
      }

      ingotStack.func_190920_e(ingotStack.func_190916_E() * property.getOreMultiplier() * oreTypeMultiplier);
      crushedStack.func_190920_e(crushedStack.func_190916_E() * property.getOreMultiplier());
      if (!crushedStack.func_190926_b()) {
         RecipeBuilder<?> builder = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()).input(orePrefix, material)).duration(10)).EUt(16L);
         if (material.hasProperty(PropertyKey.GEM) && !OreDictUnifier.get(OrePrefix.gem, material).func_190926_b()) {
            builder.outputs(GTUtility.copy((int)Math.ceil(amountOfCrushedOre) * oreTypeMultiplier, OreDictUnifier.get(OrePrefix.gem, material, crushedStack.func_190916_E())));
         } else {
            builder.outputs(GTUtility.copy((int)Math.ceil(amountOfCrushedOre) * oreTypeMultiplier, crushedStack));
         }

         builder.buildAndRegister();
         builder = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.MACERATOR_RECIPES.recipeBuilder()).input(orePrefix, material)).outputs(GTUtility.copy((int)Math.round(amountOfCrushedOre) * 2 * oreTypeMultiplier, crushedStack))).chancedOutput(byproductStack, 1400, 850)).duration(400);

         for(MaterialStack secondaryMaterial : orePrefix.secondaryMaterials) {
            if (secondaryMaterial.material.hasProperty(PropertyKey.DUST)) {
               ItemStack dustStack = OreDictUnifier.getGem(secondaryMaterial);
               builder.chancedOutput(dustStack, 6700, 800);
            }
         }

         builder.buildAndRegister();
      }

      if (!ingotStack.func_190926_b() && doesMaterialUseNormalFurnace(smeltingMaterial)) {
         ModHandler.addSmeltingRecipe(new UnificationEntry(orePrefix, material), ingotStack, 0.5F);
      }

   }

   public static void processCrushedOre(OrePrefix crushedPrefix, Material material, OreProperty property) {
      ItemStack impureDustStack = OreDictUnifier.get(OrePrefix.dustImpure, material);
      Material byproductMaterial = property.getOreByProduct(0, material);
      if (impureDustStack.func_190926_b()) {
         impureDustStack = GTUtility.copyFirst(new ItemStack[]{OreDictUnifier.get(OrePrefix.dirtyGravel, material), OreDictUnifier.get(OrePrefix.shard, material), OreDictUnifier.get(OrePrefix.clump, material), OreDictUnifier.get(OrePrefix.dust, material)});
      }

      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()).input(crushedPrefix, material)).outputs(impureDustStack)).duration(10)).EUt(16L)).buildAndRegister();
      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.MACERATOR_RECIPES.recipeBuilder()).input(crushedPrefix, material)).outputs(impureDustStack)).duration(400)).chancedOutput(OreDictUnifier.get(OrePrefix.dust, byproductMaterial, property.getByProductMultiplier()), 1400, 850)).buildAndRegister();
      ItemStack crushedPurifiedOre = GTUtility.copyFirst(new ItemStack[]{OreDictUnifier.get(OrePrefix.crushedPurified, material), OreDictUnifier.get(OrePrefix.dust, material)});
      ItemStack crushedCentrifugedOre = GTUtility.copyFirst(new ItemStack[]{OreDictUnifier.get(OrePrefix.crushedCentrifuged, material), OreDictUnifier.get(OrePrefix.dust, material)});
      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.ORE_WASHER_RECIPES.recipeBuilder()).input(crushedPrefix, material)).circuitMeta(2)).fluidInputs(Materials.Water.getFluid(100))).outputs(crushedPurifiedOre)).duration(8)).EUt(4L)).buildAndRegister();
      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.ORE_WASHER_RECIPES.recipeBuilder()).input(crushedPrefix, material)).fluidInputs(Materials.Water.getFluid(1000))).circuitMeta(1)).outputs(crushedPurifiedOre)).chancedOutput(OrePrefix.dust, byproductMaterial, 3333, 0)).output(OrePrefix.dust, Materials.Stone)).buildAndRegister();
      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.ORE_WASHER_RECIPES.recipeBuilder()).input(crushedPrefix, material)).fluidInputs(Materials.DistilledWater.getFluid(100))).outputs(crushedPurifiedOre)).chancedOutput(OrePrefix.dust, byproductMaterial, 3333, 0)).output(OrePrefix.dust, Materials.Stone)).duration(200)).buildAndRegister();
      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()).input(crushedPrefix, material)).outputs(crushedCentrifugedOre)).chancedOutput(OrePrefix.dust, property.getOreByProduct(1, material), property.getByProductMultiplier(), 3333, 0)).output(OrePrefix.dust, Materials.Stone)).buildAndRegister();
      if (property.getWashedIn().getKey() != null) {
         Material washingByproduct = property.getOreByProduct(3, material);
         Pair<Material, Integer> washedInTuple = property.getWashedIn();
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()).input(crushedPrefix, material)).fluidInputs(((Material)washedInTuple.getKey()).getFluid((Integer)washedInTuple.getValue()))).outputs(crushedPurifiedOre)).chancedOutput(OreDictUnifier.get(OrePrefix.dust, washingByproduct, property.getByProductMultiplier()), 7000, 580)).chancedOutput(OreDictUnifier.get(OrePrefix.dust, Materials.Stone), 4000, 650)).duration(200)).EUt((long)GTValues.VA[1])).buildAndRegister();
      }

      ModHandler.addShapelessRecipe(String.format("crushed_ore_to_dust_%s", material), impureDustStack, new Object[]{'h', new UnificationEntry(crushedPrefix, material)});
      processMetalSmelting(crushedPrefix, material, property);
   }

   public static void processCrushedCentrifuged(OrePrefix centrifugedPrefix, Material material, OreProperty property) {
      ItemStack dustStack = OreDictUnifier.get(OrePrefix.dust, material);
      ItemStack byproductStack = OreDictUnifier.get(OrePrefix.dust, property.getOreByProduct(2, material), 1);
      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()).input(centrifugedPrefix, material)).outputs(dustStack)).duration(10)).EUt(16L)).buildAndRegister();
      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.MACERATOR_RECIPES.recipeBuilder()).input(centrifugedPrefix, material)).outputs(dustStack)).chancedOutput(byproductStack, 1400, 850)).duration(400)).buildAndRegister();
      ModHandler.addShapelessRecipe(String.format("centrifuged_ore_to_dust_%s", material), dustStack, new Object[]{'h', new UnificationEntry(centrifugedPrefix, material)});
      processMetalSmelting(centrifugedPrefix, material, property);
   }

   public static void processCrushedPurified(OrePrefix purifiedPrefix, Material material, OreProperty property) {
      ItemStack crushedCentrifugedStack = OreDictUnifier.get(OrePrefix.crushedCentrifuged, material);
      ItemStack dustStack = OreDictUnifier.get(OrePrefix.dustPure, material);
      Material byproductMaterial = property.getOreByProduct(1, material);
      ItemStack byproductStack = OreDictUnifier.get(OrePrefix.dust, byproductMaterial);
      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()).input(purifiedPrefix, material)).outputs(dustStack)).duration(10)).EUt(16L)).buildAndRegister();
      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.MACERATOR_RECIPES.recipeBuilder()).input(purifiedPrefix, material)).outputs(dustStack)).chancedOutput(byproductStack, 1400, 850)).duration(400)).buildAndRegister();
      ModHandler.addShapelessRecipe(String.format("purified_ore_to_dust_%s", material), dustStack, new Object[]{'h', new UnificationEntry(purifiedPrefix, material)});
      if (!crushedCentrifugedStack.func_190926_b()) {
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()).input(purifiedPrefix, material)).outputs(crushedCentrifugedStack)).chancedOutput(OrePrefix.dust, byproductMaterial, 3333, 0)).buildAndRegister();
      }

      if (material.hasProperty(PropertyKey.GEM)) {
         ItemStack exquisiteStack = OreDictUnifier.get(OrePrefix.gemExquisite, material);
         ItemStack flawlessStack = OreDictUnifier.get(OrePrefix.gemFlawless, material);
         ItemStack gemStack = OreDictUnifier.get(OrePrefix.gem, material);
         ItemStack flawedStack = OreDictUnifier.get(OrePrefix.gemFlawed, material);
         ItemStack chippedStack = OreDictUnifier.get(OrePrefix.gemChipped, material);
         if (material.hasFlag(MaterialFlags.HIGH_SIFTER_OUTPUT)) {
            RecipeBuilder<SimpleRecipeBuilder> builder = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.SIFTER_RECIPES.recipeBuilder()).input(purifiedPrefix, material)).chancedOutput(exquisiteStack, 500, 150)).chancedOutput(flawlessStack, 1500, 200)).chancedOutput(gemStack, 5000, 1000)).chancedOutput(dustStack, 2500, 500)).duration(400)).EUt(16L);
            if (!flawedStack.func_190926_b()) {
               builder.chancedOutput(flawedStack, 2000, 500);
            }

            if (!chippedStack.func_190926_b()) {
               builder.chancedOutput(chippedStack, 3000, 350);
            }

            builder.buildAndRegister();
         } else {
            RecipeBuilder<SimpleRecipeBuilder> builder = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.SIFTER_RECIPES.recipeBuilder()).input(purifiedPrefix, material)).chancedOutput(exquisiteStack, 300, 100)).chancedOutput(flawlessStack, 1000, 150)).chancedOutput(gemStack, 3500, 500)).chancedOutput(dustStack, 5000, 750)).duration(400)).EUt(16L);
            if (!flawedStack.func_190926_b()) {
               builder.chancedOutput(flawedStack, 2500, 300);
            }

            if (!exquisiteStack.func_190926_b()) {
               builder.chancedOutput(chippedStack, 3500, 400);
            }

            builder.buildAndRegister();
         }
      }

      processMetalSmelting(purifiedPrefix, material, property);
   }

   public static void processDirtyDust(OrePrefix dustPrefix, Material material, OreProperty property) {
      ItemStack dustStack = OreDictUnifier.get(OrePrefix.dust, material);
      Material byproduct = property.getOreByProduct(0, material);
      RecipeBuilder<?> builder = ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()).input(dustPrefix, material)).outputs(dustStack)).duration((int)(material.getMass() * 4L))).EUt(24L);
      if (byproduct.hasProperty(PropertyKey.DUST)) {
         builder.chancedOutput(OrePrefix.dust, byproduct, 1111, 0);
      } else {
         builder.fluidOutputs(byproduct.getFluid(16));
      }

      builder.buildAndRegister();
      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.ORE_WASHER_RECIPES.recipeBuilder()).input(dustPrefix, material)).circuitMeta(2)).fluidInputs(Materials.Water.getFluid(100))).outputs(dustStack)).duration(8)).EUt(4L)).buildAndRegister();
      processMetalSmelting(dustPrefix, material, property);
   }

   public static void processPureDust(OrePrefix purePrefix, Material material, OreProperty property) {
      ItemStack dustStack = OreDictUnifier.get(OrePrefix.dust, material);
      Material byproductMaterial = property.getOreByProduct(1, material);
      if (property.getSeparatedInto() != null && !property.getSeparatedInto().isEmpty()) {
         List<Material> separatedMaterial = property.getSeparatedInto();
         OrePrefix prefix = ((Material)separatedMaterial.get(separatedMaterial.size() - 1)).getBlastTemperature() == 0 && ((Material)separatedMaterial.get(separatedMaterial.size() - 1)).hasProperty(PropertyKey.INGOT) ? OrePrefix.nugget : OrePrefix.dust;
         ItemStack separatedStack2 = OreDictUnifier.get(prefix, (Material)separatedMaterial.get(separatedMaterial.size() - 1), prefix == OrePrefix.nugget ? 2 : 1);
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder()).input(purePrefix, material)).outputs(dustStack)).chancedOutput(OrePrefix.dust, (Material)separatedMaterial.get(0), 1000, 250)).chancedOutput(separatedStack2, prefix == OrePrefix.dust ? 500 : 2000, prefix == OrePrefix.dust ? 150 : 600)).duration(200)).EUt(24L)).buildAndRegister();
      }

      if (dustStack.func_190926_b()) {
         dustStack = GTUtility.copyFirst(new ItemStack[]{OreDictUnifier.get(OrePrefix.reduced, material), OreDictUnifier.get(OrePrefix.cleanGravel, material)});
      }

      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()).input(purePrefix, material)).outputs(dustStack)).chancedOutput(OrePrefix.dust, byproductMaterial, 1111, 0)).duration(100)).EUt(5L)).buildAndRegister();
      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)RecipeMaps.ORE_WASHER_RECIPES.recipeBuilder()).input(purePrefix, material)).circuitMeta(2)).fluidInputs(Materials.Water.getFluid(100))).outputs(dustStack)).duration(8)).EUt(4L)).buildAndRegister();
      processMetalSmelting(purePrefix, material, property);
   }

   private static boolean doesMaterialUseNormalFurnace(Material material) {
      return !material.hasProperty(PropertyKey.BLAST);
   }
}
