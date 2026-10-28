package gregtechlite.gtlitecore.api.extension;

import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.ResearchRecipeBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000d\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a9\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0007\u001aA\u0010\b\u001a\u0002H\u0002\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000e\u001aA\u0010\b\u001a\u0002H\u0002\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0011\u001aA\u0010\u0012\u001a\u0002H\u0002\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000e\u001aA\u0010\u0012\u001a\u0002H\u0002\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0011\u001a-\u0010\u0013\u001a\u0002H\u0002\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0014\u001a\u00020\f¢\u0006\u0002\u0010\u0015\u001a-\u0010\u0013\u001a\u0002H\u0002\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0016*\b\u0012\u0004\u0012\u0002H\u00020\u00162\u0006\u0010\u0014\u001a\u00020\f¢\u0006\u0002\u0010\u0017\u001a-\u0010\u0018\u001a\u0002H\u0002\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0002\u0010\u001a\u001a-\u0010\u0018\u001a\u0002H\u0002\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u001b¢\u0006\u0002\u0010\u001c\u001a%\u0010\u001d\u001a\u0002H\u0002\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010\u001e\u001a%\u0010\u001f\u001a\u0002H\u0002\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010\u001e\u001a9\u0010 \u001a\u00020!\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0012\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020$0#\"\u00020$¢\u0006\u0002\u0010%\u001a9\u0010 \u001a\u00020!\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0012\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020'0#\"\u00020'¢\u0006\u0002\u0010(\u001aA\u0010 \u001a\u00020!\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020'0#¢\u0006\u0002\u0010)\u001a9\u0010 \u001a\u00020!\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0012\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0#\"\u00020\n¢\u0006\u0002\u0010*¨\u0006+"},
   d2 = {"addRecipe", "", "T", "Lgregtech/api/recipes/RecipeBuilder;", "Lgregtech/api/recipes/RecipeMap;", "builder", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "inputs", "item", "Lnet/minecraft/item/Item;", "amount", "", "meta", "(Lgregtech/api/recipes/RecipeBuilder;Lnet/minecraft/item/Item;II)Lgregtech/api/recipes/RecipeBuilder;", "block", "Lnet/minecraft/block/Block;", "(Lgregtech/api/recipes/RecipeBuilder;Lnet/minecraft/block/Block;II)Lgregtech/api/recipes/RecipeBuilder;", "outputs", "EUt", "eut", "(Lgregtech/api/recipes/RecipeBuilder;I)Lgregtech/api/recipes/RecipeBuilder;", "Lgregtech/api/recipes/builders/ResearchRecipeBuilder;", "(Lgregtech/api/recipes/builders/ResearchRecipeBuilder;I)Lgregtech/api/recipes/builders/ResearchRecipeBuilder;", "duration", "", "(Lgregtech/api/recipes/RecipeBuilder;J)Lgregtech/api/recipes/RecipeBuilder;", "", "(Lgregtech/api/recipes/RecipeBuilder;D)Lgregtech/api/recipes/RecipeBuilder;", "cleanroom", "(Lgregtech/api/recipes/RecipeBuilder;)Lgregtech/api/recipes/RecipeBuilder;", "sterileCleanroom", "removeRecipe", "", "itemInputs", "", "Lnet/minecraft/item/ItemStack;", "(Lgregtech/api/recipes/RecipeMap;[Lnet/minecraft/item/ItemStack;)Z", "fluidInputs", "Lnet/minecraftforge/fluids/FluidStack;", "(Lgregtech/api/recipes/RecipeMap;[Lnet/minecraftforge/fluids/FluidStack;)Z", "(Lgregtech/api/recipes/RecipeMap;[Lnet/minecraft/item/ItemStack;[Lnet/minecraftforge/fluids/FluidStack;)Z", "(Lgregtech/api/recipes/RecipeMap;[Lnet/minecraft/item/Item;)Z", "gtlitecore"}
)
@SourceDebugExtension({"SMAP\nRecipeBuilderExt.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RecipeBuilderExt.kt\ngregtechlite/gtlitecore/api/extension/RecipeBuilderExtKt\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,95:1\n37#2:96\n36#2,3:97\n*S KotlinDebug\n*F\n+ 1 RecipeBuilderExt.kt\ngregtechlite/gtlitecore/api/extension/RecipeBuilderExtKt\n*L\n92#1:96\n92#1:97,3\n*E\n"})
public final class RecipeBuilderExtKt {
   public static final <T extends RecipeBuilder<T>> void addRecipe(@NotNull RecipeMap<T> $this$addRecipe, @NotNull Function1<? super T, Unit> builder) {
      Intrinsics.checkNotNullParameter($this$addRecipe, "<this>");
      Intrinsics.checkNotNullParameter(builder, "builder");
      RecipeBuilder var2 = $this$addRecipe.recipeBuilder();
      builder.invoke(var2);
      var2.buildAndRegister();
   }

   @NotNull
   public static final <T extends RecipeBuilder<T>> T inputs(@NotNull RecipeBuilder<T> $this$inputs, @NotNull Item item, int amount, int meta) {
      Intrinsics.checkNotNullParameter($this$inputs, "<this>");
      Intrinsics.checkNotNullParameter(item, "item");
      RecipeBuilder var10000 = $this$inputs.inputs(new ItemStack(item, amount, meta));
      Intrinsics.checkNotNullExpressionValue(var10000, "inputs(...)");
      return (T)var10000;
   }

   // $FF: synthetic method
   public static RecipeBuilder inputs$default(RecipeBuilder var0, Item var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 1;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return inputs(var0, var1, var2, var3);
   }

   @NotNull
   public static final <T extends RecipeBuilder<T>> T inputs(@NotNull RecipeBuilder<T> $this$inputs, @NotNull Block block, int amount, int meta) {
      Intrinsics.checkNotNullParameter($this$inputs, "<this>");
      Intrinsics.checkNotNullParameter(block, "block");
      RecipeBuilder var10000 = $this$inputs.inputs(new ItemStack(block, amount, meta));
      Intrinsics.checkNotNullExpressionValue(var10000, "inputs(...)");
      return (T)var10000;
   }

   // $FF: synthetic method
   public static RecipeBuilder inputs$default(RecipeBuilder var0, Block var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 1;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return inputs(var0, var1, var2, var3);
   }

   @NotNull
   public static final <T extends RecipeBuilder<T>> T outputs(@NotNull RecipeBuilder<T> $this$outputs, @NotNull Item item, int amount, int meta) {
      Intrinsics.checkNotNullParameter($this$outputs, "<this>");
      Intrinsics.checkNotNullParameter(item, "item");
      RecipeBuilder var10000 = $this$outputs.outputs(new ItemStack(item, amount, meta));
      Intrinsics.checkNotNullExpressionValue(var10000, "outputs(...)");
      return (T)var10000;
   }

   // $FF: synthetic method
   public static RecipeBuilder outputs$default(RecipeBuilder var0, Item var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 1;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return outputs(var0, var1, var2, var3);
   }

   @NotNull
   public static final <T extends RecipeBuilder<T>> T outputs(@NotNull RecipeBuilder<T> $this$outputs, @NotNull Block block, int amount, int meta) {
      Intrinsics.checkNotNullParameter($this$outputs, "<this>");
      Intrinsics.checkNotNullParameter(block, "block");
      RecipeBuilder var10000 = $this$outputs.outputs(new ItemStack(block, amount, meta));
      Intrinsics.checkNotNullExpressionValue(var10000, "outputs(...)");
      return (T)var10000;
   }

   // $FF: synthetic method
   public static RecipeBuilder outputs$default(RecipeBuilder var0, Block var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 1;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return outputs(var0, var1, var2, var3);
   }

   @NotNull
   public static final <T extends RecipeBuilder<T>> T EUt(@NotNull RecipeBuilder<T> $this$EUt, int eut) {
      Intrinsics.checkNotNullParameter($this$EUt, "<this>");
      RecipeBuilder var10000 = $this$EUt.EUt((long)eut);
      Intrinsics.checkNotNullExpressionValue(var10000, "EUt(...)");
      return (T)var10000;
   }

   @NotNull
   public static final <T extends ResearchRecipeBuilder<T>> T EUt(@NotNull ResearchRecipeBuilder<T> $this$EUt, int eut) {
      Intrinsics.checkNotNullParameter($this$EUt, "<this>");
      ResearchRecipeBuilder var10000 = $this$EUt.EUt((long)eut);
      Intrinsics.checkNotNullExpressionValue(var10000, "EUt(...)");
      return (T)var10000;
   }

   @NotNull
   public static final <T extends RecipeBuilder<T>> T duration(@NotNull RecipeBuilder<T> $this$duration, long duration) {
      Intrinsics.checkNotNullParameter($this$duration, "<this>");
      RecipeBuilder var10000 = $this$duration.duration((int)duration);
      Intrinsics.checkNotNullExpressionValue(var10000, "duration(...)");
      return (T)var10000;
   }

   @NotNull
   public static final <T extends RecipeBuilder<T>> T duration(@NotNull RecipeBuilder<T> $this$duration, double duration) {
      Intrinsics.checkNotNullParameter($this$duration, "<this>");
      RecipeBuilder var10000 = $this$duration.duration((int)duration);
      Intrinsics.checkNotNullExpressionValue(var10000, "duration(...)");
      return (T)var10000;
   }

   @NotNull
   public static final <T extends RecipeBuilder<T>> T cleanroom(@NotNull RecipeBuilder<T> $this$cleanroom) {
      Intrinsics.checkNotNullParameter($this$cleanroom, "<this>");
      RecipeBuilder var10000 = $this$cleanroom.cleanroom(CleanroomType.CLEANROOM);
      Intrinsics.checkNotNullExpressionValue(var10000, "cleanroom(...)");
      return (T)var10000;
   }

   @NotNull
   public static final <T extends RecipeBuilder<T>> T sterileCleanroom(@NotNull RecipeBuilder<T> $this$sterileCleanroom) {
      Intrinsics.checkNotNullParameter($this$sterileCleanroom, "<this>");
      RecipeBuilder var10000 = $this$sterileCleanroom.cleanroom(CleanroomType.STERILE_CLEANROOM);
      Intrinsics.checkNotNullExpressionValue(var10000, "cleanroom(...)");
      return (T)var10000;
   }

   public static final <T extends RecipeBuilder<T>> boolean removeRecipe(@NotNull RecipeMap<T> $this$removeRecipe, @NotNull ItemStack... itemInputs) {
      Intrinsics.checkNotNullParameter($this$removeRecipe, "<this>");
      Intrinsics.checkNotNullParameter(itemInputs, "itemInputs");
      return GTRecipeHandler.removeRecipesByInputs($this$removeRecipe, (ItemStack[])Arrays.copyOf(itemInputs, itemInputs.length));
   }

   public static final <T extends RecipeBuilder<T>> boolean removeRecipe(@NotNull RecipeMap<T> $this$removeRecipe, @NotNull FluidStack... fluidInputs) {
      Intrinsics.checkNotNullParameter($this$removeRecipe, "<this>");
      Intrinsics.checkNotNullParameter(fluidInputs, "fluidInputs");
      return GTRecipeHandler.removeRecipesByInputs($this$removeRecipe, (FluidStack[])Arrays.copyOf(fluidInputs, fluidInputs.length));
   }

   public static final <T extends RecipeBuilder<T>> boolean removeRecipe(@NotNull RecipeMap<T> $this$removeRecipe, @NotNull ItemStack[] itemInputs, @NotNull FluidStack[] fluidInputs) {
      Intrinsics.checkNotNullParameter($this$removeRecipe, "<this>");
      Intrinsics.checkNotNullParameter(itemInputs, "itemInputs");
      Intrinsics.checkNotNullParameter(fluidInputs, "fluidInputs");
      return GTRecipeHandler.removeRecipesByInputs($this$removeRecipe, itemInputs, fluidInputs);
   }

   public static final <T extends RecipeBuilder<T>> boolean removeRecipe(@NotNull RecipeMap<T> $this$removeRecipe, @NotNull Item... itemInputs) {
      Intrinsics.checkNotNullParameter($this$removeRecipe, "<this>");
      Intrinsics.checkNotNullParameter(itemInputs, "itemInputs");
      List items = (List)(new ArrayList());
      int var3 = 0;

      for(int thisCollection$iv = itemInputs.length; var3 < thisCollection$iv; ++var3) {
         Item itemInput = itemInputs[var3];
         items.add(new ItemStack(itemInput));
      }

      Collection $this$toTypedArray$iv = (Collection)items;
      int $i$f$toTypedArray = 0;
      ItemStack[] var7 = (ItemStack[])$this$toTypedArray$iv.toArray(new ItemStack[0]);
      return removeRecipe($this$removeRecipe, (ItemStack[])Arrays.copyOf(var7, var7.length));
   }
}
