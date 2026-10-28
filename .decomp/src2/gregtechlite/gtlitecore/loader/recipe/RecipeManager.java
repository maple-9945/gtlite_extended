package gregtechlite.gtlitecore.loader.recipe;

import gregtechlite.gtlitecore.loader.recipe.chain.ChemistryRecipeList;
import gregtechlite.gtlitecore.loader.recipe.circuit.CircuitRecipeList;
import gregtechlite.gtlitecore.loader.recipe.component.ComponentRecipeList;
import gregtechlite.gtlitecore.loader.recipe.foodprocessing.FoodProcessingList;
import gregtechlite.gtlitecore.loader.recipe.machine.GTMetaTileEntityLoader;
import gregtechlite.gtlitecore.loader.recipe.machine.MachineRecipeList;
import gregtechlite.gtlitecore.loader.recipe.machine.MachineRecipeLoader;
import gregtechlite.gtlitecore.loader.recipe.machine.casing.MachineCasingRecipeList;
import gregtechlite.gtlitecore.loader.recipe.oreprocessing.OreProcessingList;
import gregtechlite.gtlitecore.loader.recipe.producer.RecipeProducerList;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/RecipeManager;", "", "<init>", "()V", "init", "", "gtlitecore"}
)
public final class RecipeManager {
   @NotNull
   public static final RecipeManager INSTANCE = new RecipeManager();

   private RecipeManager() {
   }

   public final void init() {
      CraftingRecipeLoader.INSTANCE.init();
      GTWoodRecipeLoader.INSTANCE.init();
      RecipeProducerList.INSTANCE.init();
      GTMetaTileEntityLoader.INSTANCE.init();
      MachineRecipeLoader.INSTANCE.init();
      MachineCasingRecipeList.INSTANCE.init();
      ChemistryRecipeList.INSTANCE.init();
      OreProcessingList.INSTANCE.init();
      FoodProcessingList.INSTANCE.init();
      ComponentRecipeList.INSTANCE.init();
      MachineRecipeList.INSTANCE.init();
      CircuitRecipeList.INSTANCE.init();
      OverrideRecipeLoader.INSTANCE.init();
      RecipeConflicts.INSTANCE.init();
      RecipeProducerList.INSTANCE.postInit();
   }
}
