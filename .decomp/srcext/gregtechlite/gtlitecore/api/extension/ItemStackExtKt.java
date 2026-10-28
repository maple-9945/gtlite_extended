package gregtechlite.gtlitecore.api.extension;

import com.google.common.collect.ImmutableList;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.MetaOreDictItem;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockMaterialBase;
import gregtechlite.gtlitecore.client.util.ItemStackMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000h\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u001a\u001e\u0010\u0007\u001a\u00020\u0001*\u00020\u00062\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u001a(\u0010\u0007\u001a\u00020\u0001*\u00020\u00062\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\t\u001a\u0012\u0010\n\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\n0\rR\u0006\u0012\u0002\b\u00030\u000e\u001a\u001c\u0010\u0007\u001a\u00020\u0001*\n0\rR\u0006\u0012\u0002\b\u00030\u000e2\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a3\u0010\u000f\u001a\n0\rR\u0006\u0012\u0002\b\u00030\u000e*\n0\rR\u0006\u0012\u0002\b\u00030\u000e2\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u0011\"\u00020\u0012¢\u0006\u0002\u0010\u0013\u001a\u000e\u0010\u0005\u001a\u00020\u0001*\u00060\u0014R\u00020\u0015\u001a\u0016\u0010\u0007\u001a\u00020\u0001*\u00060\u0014R\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u0003\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0016\u001a\u001e\u0010\u0007\u001a\u00020\u0001*\u00020\u00162\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0017\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\u00172\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a\u0014\u0010\u0018\u001a\u00020\u0001*\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u0003\u001a$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u001c\"\n\b\u0000\u0010\u001d\u0018\u0001*\u00020\u0016*\u0002H\u001dH\u0086\b¢\u0006\u0002\u0010\u001e\u001a1\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u001c\"\n\b\u0000\u0010\u001d\u0018\u0001*\u00020\u0016*\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001d0\u00110\u001fH\u0086\b\u001a%\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u001c\"\n\b\u0000\u0010\u001d\u0018\u0001*\u00020\u0016*\b\u0012\u0004\u0012\u0002H\u001d0\u001cH\u0086\b\u001a%\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010\u001c\"\n\b\u0000\u0010\u001d\u0018\u0001*\u00020!*\b\u0012\u0004\u0012\u0002H\u001d0\u001cH\u0086\b¨\u0006\""},
   d2 = {"copy", "Lnet/minecraft/item/ItemStack;", "count", "", "meta", "stack", "Lnet/minecraft/item/Item;", "getStack", "tag", "Lnet/minecraft/nbt/NBTTagCompound;", "getWildcardStack", "nbtSensitive", "", "Lgregtech/api/items/metaitem/MetaItem$MetaValueItem;", "Lgregtech/api/items/metaitem/MetaItem;", "addOreDicts", "oreDictNames", "", "", "(Lgregtech/api/items/metaitem/MetaItem$MetaValueItem;[Ljava/lang/String;)Lgregtech/api/items/metaitem/MetaItem$MetaValueItem;", "Lgregtech/api/items/metaitem/MetaOreDictItem$OreDictValueItem;", "Lgregtech/api/items/metaitem/MetaOreDictItem;", "Lnet/minecraft/block/Block;", "Lgregtech/api/metatileentity/MetaTileEntity;", "toItem", "Lnet/minecraft/block/state/IBlockState;", "amount", "unzipSubBlocks", "", "T", "(Lnet/minecraft/block/Block;)Ljava/util/List;", "", "unzipSubVariants", "Lgregtech/common/blocks/BlockMaterialBase;", "gtlitecore"}
)
@SourceDebugExtension({"SMAP\nItemStackExt.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ItemStackExt.kt\ngregtechlite/gtlitecore/api/extension/ItemStackExtKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,128:1\n117#1:139\n117#1:147\n1#2:129\n1#2:140\n1#2:148\n1381#3:130\n1469#3,5:131\n1368#3:136\n1454#3,2:137\n1456#3,3:141\n1368#3:144\n1454#3,2:145\n1456#3,3:149\n1368#3:152\n1454#3,2:153\n774#3:155\n865#3,2:156\n1557#3:158\n1628#3,3:159\n1456#3,3:162\n*S KotlinDebug\n*F\n+ 1 ItemStackExt.kt\ngregtechlite/gtlitecore/api/extension/ItemStackExtKt\n*L\n120#1:139\n123#1:147\n120#1:140\n123#1:148\n120#1:130\n120#1:131,5\n120#1:136\n120#1:137,2\n120#1:141,3\n123#1:144\n123#1:145,2\n123#1:149,3\n126#1:152\n126#1:153,2\n126#1:155\n126#1:156,2\n126#1:158\n126#1:159,3\n126#1:162,3\n*E\n"})
public final class ItemStackExtKt {
   @NotNull
   public static final ItemStack copy(@NotNull ItemStack $this$copy, int count) {
      Intrinsics.checkNotNullParameter($this$copy, "<this>");
      ItemStack stack = $this$copy.func_77946_l();
      stack.func_190920_e(count);
      Intrinsics.checkNotNull(stack);
      return stack;
   }

   // $FF: synthetic method
   public static ItemStack copy$default(ItemStack var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 1;
      }

      return copy(var0, var1);
   }

   @NotNull
   public static final ItemStack copy(@NotNull ItemStack $this$copy, int meta, int count) {
      Intrinsics.checkNotNullParameter($this$copy, "<this>");
      ItemStack stack = $this$copy.func_77946_l();
      stack.func_77964_b(meta);
      stack.func_190920_e(count);
      Intrinsics.checkNotNull(stack);
      return stack;
   }

   // $FF: synthetic method
   public static ItemStack copy$default(ItemStack var0, int var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 1;
      }

      return copy(var0, var1, var2);
   }

   @NotNull
   public static final ItemStack stack(@NotNull Item $this$stack, int meta) {
      Intrinsics.checkNotNullParameter($this$stack, "<this>");
      return getStack$default((Item)$this$stack, 0, meta, 1, (Object)null);
   }

   // $FF: synthetic method
   public static ItemStack stack$default(Item var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 0;
      }

      return stack(var0, var1);
   }

   @NotNull
   public static final ItemStack getStack(@NotNull Item $this$getStack, int count, int meta) {
      Intrinsics.checkNotNullParameter($this$getStack, "<this>");
      return new ItemStack($this$getStack, count, meta);
   }

   // $FF: synthetic method
   public static ItemStack getStack$default(Item var0, int var1, int var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = 1;
      }

      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      return getStack(var0, var1, var2);
   }

   @NotNull
   public static final ItemStack getStack(@NotNull Item $this$getStack, int count, int meta, @Nullable NBTTagCompound tag) {
      Intrinsics.checkNotNullParameter($this$getStack, "<this>");
      ItemStack $this$getStack_u24lambda_u240 = new ItemStack($this$getStack, count, meta);
      int var6 = 0;
      $this$getStack_u24lambda_u240.func_77982_d(tag);
      return $this$getStack_u24lambda_u240;
   }

   // $FF: synthetic method
   public static ItemStack getStack$default(Item var0, int var1, int var2, NBTTagCompound var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 1;
      }

      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      return getStack(var0, var1, var2, var3);
   }

   @NotNull
   public static final ItemStack getWildcardStack(@NotNull Item $this$getWildcardStack, boolean nbtSensitive) {
      Intrinsics.checkNotNullParameter($this$getWildcardStack, "<this>");
      return getStack($this$getWildcardStack, 1, 32767, nbtSensitive ? ItemStackMap.Companion.getWILDCARD_TAG() : null);
   }

   @NotNull
   public static final ItemStack stack(@NotNull MetaItem<?>.MetaValueItem $this$stack) {
      Intrinsics.checkNotNullParameter($this$stack, "<this>");
      return getStack$default((MetaItem.MetaValueItem)$this$stack, 0, 1, (Object)null);
   }

   @NotNull
   public static final ItemStack getStack(@NotNull MetaItem<?>.MetaValueItem $this$getStack, int count) {
      Intrinsics.checkNotNullParameter($this$getStack, "<this>");
      ItemStack var10000 = $this$getStack.getStackForm(count);
      Intrinsics.checkNotNullExpressionValue(var10000, "getStackForm(...)");
      return var10000;
   }

   // $FF: synthetic method
   public static ItemStack getStack$default(MetaItem.MetaValueItem var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 1;
      }

      return getStack(var0, var1);
   }

   @NotNull
   public static final MetaItem<?>.MetaValueItem addOreDicts(@NotNull MetaItem<?>.MetaValueItem $this$addOreDicts, @NotNull String... oreDictNames) {
      Intrinsics.checkNotNullParameter($this$addOreDicts, "<this>");
      Intrinsics.checkNotNullParameter(oreDictNames, "oreDictNames");
      int var2 = 0;

      for(int var3 = oreDictNames.length; var2 < var3; ++var2) {
         String oreDictName = oreDictNames[var2];
         $this$addOreDicts.addOreDict(oreDictName);
      }

      return $this$addOreDicts;
   }

   @NotNull
   public static final ItemStack stack(@NotNull MetaOreDictItem.OreDictValueItem $this$stack) {
      Intrinsics.checkNotNullParameter($this$stack, "<this>");
      return getStack($this$stack, 1);
   }

   @NotNull
   public static final ItemStack getStack(@NotNull MetaOreDictItem.OreDictValueItem $this$getStack, int count) {
      Intrinsics.checkNotNullParameter($this$getStack, "<this>");
      ItemStack var10000 = $this$getStack.getItemStack(count);
      Intrinsics.checkNotNullExpressionValue(var10000, "getItemStack(...)");
      return var10000;
   }

   @NotNull
   public static final ItemStack stack(@NotNull Block $this$stack) {
      Intrinsics.checkNotNullParameter($this$stack, "<this>");
      return getStack$default((Block)$this$stack, 1, 0, 2, (Object)null);
   }

   @NotNull
   public static final ItemStack getStack(@NotNull Block $this$getStack, int count, int meta) {
      Intrinsics.checkNotNullParameter($this$getStack, "<this>");
      return new ItemStack($this$getStack, count, meta);
   }

   // $FF: synthetic method
   public static ItemStack getStack$default(Block var0, int var1, int var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = 1;
      }

      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      return getStack(var0, var1, var2);
   }

   @NotNull
   public static final ItemStack stack(@NotNull MetaTileEntity $this$stack) {
      Intrinsics.checkNotNullParameter($this$stack, "<this>");
      return getStack$default((MetaTileEntity)$this$stack, 0, 1, (Object)null);
   }

   @NotNull
   public static final ItemStack getStack(@NotNull MetaTileEntity $this$getStack, int count) {
      Intrinsics.checkNotNullParameter($this$getStack, "<this>");
      ItemStack var10000 = $this$getStack.getStackForm();
      Intrinsics.checkNotNullExpressionValue(var10000, "getStackForm(...)");
      return copy(var10000, count);
   }

   // $FF: synthetic method
   public static ItemStack getStack$default(MetaTileEntity var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 1;
      }

      return getStack(var0, var1);
   }

   @NotNull
   public static final ItemStack toItem(@NotNull IBlockState $this$toItem, int amount) {
      Intrinsics.checkNotNullParameter($this$toItem, "<this>");
      return new ItemStack($this$toItem.func_177230_c(), amount, $this$toItem.func_177230_c().func_176201_c($this$toItem));
   }

   // $FF: synthetic method
   public static ItemStack toItem$default(IBlockState var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 1;
      }

      return toItem(var0, var1);
   }

   // $FF: synthetic method
   public static final <T extends Block> List<ItemStack> unzipSubBlocks(T $this$unzipSubBlocks) {
      Intrinsics.checkNotNullParameter($this$unzipSubBlocks, "<this>");
      int $i$f$unzipSubBlocks = 0;
      NonNullList it = NonNullList.func_191196_a();
      int var4 = 0;
      $this$unzipSubBlocks.func_149666_a(CreativeTabs.field_78027_g, it);
      Intrinsics.checkNotNullExpressionValue(it, "also(...)");
      return (List)it;
   }

   // $FF: synthetic method
   public static final <T extends Block> List<ItemStack> unzipSubBlocks(Map<String, T[]> $this$unzipSubBlocks) {
      Intrinsics.checkNotNullParameter($this$unzipSubBlocks, "<this>");
      int $i$f$unzipSubBlocks = 0;
      Iterable $this$flatMap$iv = (Iterable)$this$unzipSubBlocks.values();
      int $i$f$flatMapSequence = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$flatMapSequenceTo = 0;

      for(Object element$iv$iv : $this$flatMap$iv) {
         Block[] it = (Block[])element$iv$iv;
         int var10 = 0;
         Sequence list$iv$iv = ArraysKt.asSequence(it);
         CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
      }

      $this$flatMap$iv = (Iterable)((List)destination$iv$iv);
      $i$f$flatMapSequence = 0;
      destination$iv$iv = (Collection)(new ArrayList());
      $i$f$flatMapSequenceTo = 0;

      for(Object element$iv$iv : $this$flatMap$iv) {
         Block it = (Block)element$iv$iv;
         int var25 = 0;
         int $i$f$unzipSubBlocks = 0;
         NonNullList it$iv = NonNullList.func_191196_a();
         int var15 = 0;
         it.func_149666_a(CreativeTabs.field_78027_g, it$iv);
         Intrinsics.checkNotNullExpressionValue(it$iv, "also(...)");
         Iterable list$iv$iv = (Iterable)((List)it$iv);
         CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
      }

      return CollectionsKt.toList((Iterable)((List)destination$iv$iv));
   }

   // $FF: synthetic method
   public static final <T extends Block> List<ItemStack> unzipSubBlocks(List<? extends T> $this$unzipSubBlocks) {
      Intrinsics.checkNotNullParameter($this$unzipSubBlocks, "<this>");
      int $i$f$unzipSubBlocks = 0;
      Iterable $this$flatMap$iv = (Iterable)$this$unzipSubBlocks;
      int $i$f$flatMap = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$flatMapTo = 0;

      for(Object element$iv$iv : $this$flatMap$iv) {
         Block it = (Block)element$iv$iv;
         int var10 = 0;
         int $i$f$unzipSubBlocks = 0;
         NonNullList it$iv = NonNullList.func_191196_a();
         int var15 = 0;
         it.func_149666_a(CreativeTabs.field_78027_g, it$iv);
         Intrinsics.checkNotNullExpressionValue(it$iv, "also(...)");
         Iterable list$iv$iv = (Iterable)((List)it$iv);
         CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
      }

      return CollectionsKt.toList((Iterable)((List)destination$iv$iv));
   }

   // $FF: synthetic method
   public static final <T extends BlockMaterialBase> List<ItemStack> unzipSubVariants(List<? extends T> $this$unzipSubVariants) {
      Intrinsics.checkNotNullParameter($this$unzipSubVariants, "<this>");
      int $i$f$unzipSubVariants = 0;
      Iterable $this$flatMap$iv = (Iterable)$this$unzipSubVariants;
      int $i$f$flatMap = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$flatMapTo = 0;

      for(Object element$iv$iv : $this$flatMap$iv) {
         BlockMaterialBase block = (BlockMaterialBase)element$iv$iv;
         int var10 = 0;
         ImmutableList var10000 = block.getVariantProperty().getAllowedValues();
         Intrinsics.checkNotNullExpressionValue(var10000, "getAllowedValues(...)");
         Iterable $this$filter$iv = (Iterable)var10000;
         int $i$f$filter = 0;
         Collection destination$iv$iv = (Collection)(new ArrayList());
         int $i$f$filterTo = 0;

         for(Object element$iv$iv : $this$filter$iv) {
            Material it = (Material)element$iv$iv;
            int var19 = 0;
            if (!Intrinsics.areEqual(it, Materials.NULL)) {
               destination$iv$iv.add(element$iv$iv);
            }
         }

         $this$filter$iv = (Iterable)((List)destination$iv$iv);
         $i$f$filter = 0;
         destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$filter$iv, 10)));
         $i$f$filterTo = 0;

         for(Object item$iv$iv : $this$filter$iv) {
            Material it = (Material)item$iv$iv;
            int var29 = 0;
            destination$iv$iv.add(block.getItem(it));
         }

         Iterable list$iv$iv = (Iterable)((List)destination$iv$iv);
         CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
      }

      return CollectionsKt.toList((Iterable)((List)destination$iv$iv));
   }
}
