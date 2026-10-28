package gregtechlite.gtlitecore.loader.recipe.handler;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/handler/RecipeHandlers;", "", "<init>", "()V", "init", "", "gtlitecore"}
)
public final class RecipeHandlers {
   @NotNull
   public static final RecipeHandlers INSTANCE = new RecipeHandlers();

   private RecipeHandlers() {
   }

   public final void init() {
      MaterialRecipeHandler.INSTANCE.init();
      BouleRecipeHandler.INSTANCE.init();
      PartsRecipeHandler.INSTANCE.init();
      OreRecipeHandler.INSTANCE.init();
      ToolRecipeHandler.INSTANCE.init();
      WireCombinationHandler.INSTANCE.init();
      WireRecipeHandler.INSTANCE.init();
      NuclearFuelRecipeHandler.INSTANCE.init();
   }
}
