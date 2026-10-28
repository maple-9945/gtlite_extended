package gregtechlite.gtlitecore.loader.recipe.producer;

import com.morphismmc.morphismlib.integration.Mods;
import gregtechlite.gtlitecore.core.GTLiteConfigHolder;
import gregtechlite.gtlitecore.integration.appeng.recipe.AppEngCALRecipeProducer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/producer/RecipeProducerList;", "", "<init>", "()V", "init", "", "postInit", "gtlitecore"}
)
public final class RecipeProducerList {
   @NotNull
   public static final RecipeProducerList INSTANCE = new RecipeProducerList();

   private RecipeProducerList() {
   }

   public final void init() {
      StoneVariantRecipeProducer.INSTANCE.produce();
      DisposableToolRecipeProducer.INSTANCE.produce();
      MassReplicationRecipeProducer.INSTANCE.produce();
      CircuitAssemblyLineRecipeProducer.INSTANCE.produce();
      if (Mods.AppliedEnergistics2.isActive() && GTLiteConfigHolder.compat.ae2Compat.addProcessorCALRecipes) {
         AppEngCALRecipeProducer.INSTANCE.produce();
      }

      WrapItemRecipeProducer.INSTANCE.produce();
      GreenhouseRecipeProducer.INSTANCE.produce();
      MiningDroneAsteroidRecipeProducer.INSTANCE.produce();
      NuclearFissionRecipeProducer.INSTANCE.produce();
      PCBFactoryRecipeProducer.INSTANCE.produce();
      IntegratedOreProcessorRecipeProducer.INSTANCE.produce();
   }

   public final void postInit() {
      ComponentAssemblyLineRecipeProducer.INSTANCE.produce();
      AdvancedFusionRecipeProducer.INSTANCE.produce();
      BlackholeFormerRecipeProducer.INSTANCE.produce();
   }
}
