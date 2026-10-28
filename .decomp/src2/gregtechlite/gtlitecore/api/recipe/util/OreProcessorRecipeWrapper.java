package gregtechlite.gtlitecore.api.recipe.util;

import gregtech.api.GTValues;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.chance.output.impl.ChancedItemOutput;
import gregtech.api.unification.OreDictUnifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u000fJZ\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000f\"\u000e\b\u0000\u0010\u0017*\b\u0012\u0004\u0012\u0002H\u00170\u00182\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001a2\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u000f2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000fR\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\u001e"},
   d2 = {"Lgregtechlite/gtlitecore/api/recipe/util/OreProcessorRecipeWrapper;", "", "<init>", "()V", "orePrefixes", "", "", "getOrePrefixes", "()[Ljava/lang/String;", "[Ljava/lang/String;", "isOre", "", "itemStack", "Lnet/minecraft/item/ItemStack;", "getOutputStack", "", "recipe", "Lgregtech/api/recipes/Recipe;", "factor", "", "compressOutput", "input", "doRecipe", "T", "Lgregtech/api/recipes/RecipeBuilder;", "recipeMap", "Lgregtech/api/recipes/RecipeMap;", "extraFluid", "Lnet/minecraftforge/fluids/FluidStack;", "extraItem", "gtlitecore"}
)
public final class OreProcessorRecipeWrapper {
   @NotNull
   public static final OreProcessorRecipeWrapper INSTANCE = new OreProcessorRecipeWrapper();
   @NotNull
   private static final String[] orePrefixes;

   private OreProcessorRecipeWrapper() {
   }

   @NotNull
   public final String[] getOrePrefixes() {
      return orePrefixes;
   }

   public final boolean isOre(@NotNull ItemStack itemStack) {
      Intrinsics.checkNotNullParameter(itemStack, "itemStack");
      if (itemStack.func_190926_b()) {
         return false;
      } else {
         Set var10000 = OreDictUnifier.getOreDictionaryNames(itemStack);
         Intrinsics.checkNotNullExpressionValue(var10000, "getOreDictionaryNames(...)");
         Set ids = var10000;
         String[] var3 = orePrefixes;
         int var4 = 0;

         for(int var5 = var3.length; var4 < var5; ++var4) {
            String prefix = var3[var4];

            for(String id : ids) {
               Intrinsics.checkNotNull(id);
               if (StringsKt.startsWith$default(id, prefix, false, 2, (Object)null)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   @NotNull
   public final List<ItemStack> getOutputStack(@NotNull Recipe recipe, int factor) {
      Intrinsics.checkNotNullParameter(recipe, "recipe");
      List output = (List)(new ArrayList());

      for(ItemStack recipeItemStack : recipe.getOutputs()) {
         ItemStack outputItemStack = recipeItemStack.func_77946_l();
         outputItemStack.func_190920_e(outputItemStack.func_190916_E() * factor);
         Intrinsics.checkNotNull(outputItemStack);
         output.add(outputItemStack);
      }

      for(Object var10000 : recipe.getChancedOutputs().getChancedEntries()) {
         Intrinsics.checkNotNullExpressionValue(var10000, "next(...)");
         ChancedItemOutput chancedItemOutput = (ChancedItemOutput)var10000;
         ItemStack itemStack = ((ItemStack)chancedItemOutput.getIngredient()).func_77946_l();
         itemStack.func_190920_e((int)Math.ceil((double)((long)itemStack.func_190916_E() * (long)factor * (long)chancedItemOutput.getChance()) * 1.0E-4));
         Intrinsics.checkNotNull(itemStack);
         output.add(itemStack);
      }

      return output;
   }

   @NotNull
   public final List<ItemStack> compressOutput(@NotNull List<ItemStack> input) {
      Intrinsics.checkNotNullParameter(input, "input");
      List compressedOutput = (List)(new ArrayList());

      for(ItemStack item : input) {
         boolean found = false;

         for(ItemStack outputItemStack : compressedOutput) {
            if (outputItemStack.func_77969_a(item)) {
               outputItemStack.func_190920_e(outputItemStack.func_190916_E() + item.func_190916_E());
               found = true;
               break;
            }
         }

         if (!found) {
            ItemStack var10001 = item.func_77946_l();
            Intrinsics.checkNotNullExpressionValue(var10001, "copy(...)");
            compressedOutput.add(var10001);
         }
      }

      return compressedOutput;
   }

   @Nullable
   public final <T extends RecipeBuilder<T>> List<ItemStack> doRecipe(@NotNull List<ItemStack> input, @NotNull RecipeMap<T> recipeMap, @Nullable List<? extends FluidStack> extraFluid, @Nullable List<ItemStack> extraItem) {
      Intrinsics.checkNotNullParameter(input, "input");
      Intrinsics.checkNotNullParameter(recipeMap, "recipeMap");
      List output = (List)(new ArrayList());
      boolean hasChanged = false;

      for(ItemStack itemStack : input) {
         if (this.isOre(itemStack)) {
            ItemStack[] var10 = new ItemStack[]{itemStack};
            List itemList = CollectionsKt.mutableListOf(var10);
            if (extraItem != null) {
               itemList.addAll((Collection)extraItem);
            }

            long var10001 = GTValues.V[14];
            List var10003 = extraFluid;
            if (extraFluid == null) {
               var10003 = CollectionsKt.emptyList();
            }

            Recipe recipe = recipeMap.findRecipe(var10001, itemList, var10003);
            if (recipe != null) {
               hasChanged = true;
               output.addAll((Collection)this.getOutputStack(recipe, itemStack.func_190916_E()));
            } else {
               output.add(itemStack);
            }
         } else {
            output.add(itemStack);
         }
      }

      if (!hasChanged) {
         return null;
      } else {
         return this.compressOutput(output);
      }
   }

   static {
      String[] var0 = new String[]{"crushedPurified", "crushedCentrifuged", "crushed", "dustImpure", "dustPure", "ore"};
      orePrefixes = var0;
   }
}
