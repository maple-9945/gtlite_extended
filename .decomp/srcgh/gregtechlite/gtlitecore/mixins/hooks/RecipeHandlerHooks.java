package gregtechlite.gtlitecore.mixins.hooks;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.ConfigHolder;
import gregtech.loaders.recipe.handlers.MaterialRecipeHandler;
import gregtech.loaders.recipe.handlers.OreRecipeHandler;
import gregtech.loaders.recipe.handlers.PartsRecipeHandler;
import gregtech.loaders.recipe.handlers.WireRecipeHandler;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007J\b\u0010\u0006\u001a\u00020\u0005H\u0007J\b\u0010\u0007\u001a\u00020\u0005H\u0007J\b\u0010\b\u001a\u00020\u0005H\u0007¨\u0006\t"},
   d2 = {"Lgregtechlite/gtlitecore/mixins/hooks/RecipeHandlerHooks;", "", "<init>", "()V", "registerMaterialHandlers", "", "registerOreHandlers", "registerPartsHandlers", "registerWireHandlers", "gtlitecore"}
)
public final class RecipeHandlerHooks {
   @NotNull
   public static final RecipeHandlerHooks INSTANCE = new RecipeHandlerHooks();

   private RecipeHandlerHooks() {
   }

   @JvmStatic
   public static final void registerMaterialHandlers() {
      OrePrefix.nugget.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processNugget);
      OrePrefix.frameGt.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processFrame);
      OrePrefix.dust.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processDust);
      OrePrefix.dustSmall.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processSmallDust);
      OrePrefix.dustTiny.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processTinyDust);
   }

   @JvmStatic
   public static final void registerOreHandlers() {
      OrePrefix.ore.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
      OrePrefix.oreEndstone.addProcessingHandler(PropertyKey.ORE, RecipeHandlerHooks::registerOreHandlers$lambda$0);
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
      OrePrefix.crushedCentrifuged.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processCrushedCentrifuged);
      OrePrefix.dustImpure.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processDirtyDust);
      OrePrefix.dustPure.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processPureDust);
   }

   @JvmStatic
   public static final void registerPartsHandlers() {
      OrePrefix.stickLong.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processLongStick);
      OrePrefix.plate.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processPlate);
      OrePrefix.plateDouble.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processPlateDouble);
      OrePrefix.plateDense.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processPlateDense);
      OrePrefix.turbineBlade.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processTurbine);
      OrePrefix.rotor.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processRotor);
      OrePrefix.bolt.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processBolt);
      OrePrefix.screw.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processScrew);
      OrePrefix.wireFine.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processFineWire);
      OrePrefix.foil.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processFoil);
      OrePrefix.gear.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processGear);
      OrePrefix.gearSmall.addProcessingHandler(PropertyKey.DUST, PartsRecipeHandler::processGear);
      OrePrefix.ring.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processRing);
      OrePrefix.springSmall.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processSpringSmall);
      OrePrefix.spring.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processSpring);
      OrePrefix.round.addProcessingHandler(PropertyKey.INGOT, PartsRecipeHandler::processRound);
   }

   @JvmStatic
   public static final void registerWireHandlers() {
      OrePrefix.wireGtSingle.addProcessingHandler(PropertyKey.WIRE, WireRecipeHandler::processWireSingle);
   }

   private static final void registerOreHandlers$lambda$0(OrePrefix prefix, Material material, OreProperty property) {
      OreRecipeHandler.processOre(prefix, material, property, 4);
   }
}
