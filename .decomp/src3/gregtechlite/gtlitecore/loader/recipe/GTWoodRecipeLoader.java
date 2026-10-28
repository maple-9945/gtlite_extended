package gregtechlite.gtlitecore.loader.recipe;

import gregtech.loaders.WoodTypeEntry;
import gregtech.loaders.recipe.WoodRecipeLoader;
import gregtechlite.gtlitecore.loader.WoodTypeEntryLoader;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/GTWoodRecipeLoader;", "", "<init>", "()V", "init", "", "gtlitecore"}
)
public final class GTWoodRecipeLoader {
   @NotNull
   public static final GTWoodRecipeLoader INSTANCE = new GTWoodRecipeLoader();

   private GTWoodRecipeLoader() {
   }

   public final void init() {
      WoodTypeEntry[] var2 = new WoodTypeEntry[]{WoodTypeEntryLoader.INSTANCE.getBANANA(), WoodTypeEntryLoader.INSTANCE.getORANGE(), WoodTypeEntryLoader.INSTANCE.getMANGO(), WoodTypeEntryLoader.INSTANCE.getAPRICOT(), WoodTypeEntryLoader.INSTANCE.getLEMON(), WoodTypeEntryLoader.INSTANCE.getLIME(), WoodTypeEntryLoader.INSTANCE.getOLIVE(), WoodTypeEntryLoader.INSTANCE.getNUTMEG(), WoodTypeEntryLoader.INSTANCE.getCOCONUT(), WoodTypeEntryLoader.INSTANCE.getRAINBOW()};
      WoodTypeEntry[] var1 = var2;
      int var5 = 0;

      for(int var3 = var2.length; var5 < var3; ++var5) {
         WoodTypeEntry woodEntry = var1[var5];
         WoodRecipeLoader.registerWoodUnificationInfo(woodEntry);
         WoodRecipeLoader.registerWoodTypeRecipe(woodEntry);
      }

   }
}
