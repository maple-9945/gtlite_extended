package gregtech.api.unification;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.registry.MaterialRegistry;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.ItemAndMetadata;
import gregtech.api.unification.stack.ItemVariantMap;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.MultiItemVariantMap;
import gregtech.api.unification.stack.SingleItemVariantMap;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.CustomModPriorityComparator;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OreDictUnifier {
   private static final Map<ItemAndMetadata, UnificationEntry> stackUnificationInfo = new Object2ObjectOpenHashMap();
   private static final Map<UnificationEntry, ArrayList<ItemAndMetadata>> stackUnificationItems = new Object2ObjectOpenHashMap();
   private static final Map<Item, ItemVariantMap.Mutable<Set<String>>> stackOreDictName = new Object2ObjectOpenHashMap();
   private static final Map<String, List<ItemStack>> oreDictNameStacks = new Object2ObjectOpenHashMap();
   private static @Nullable Comparator<ItemAndMetadata> stackComparator;

   private OreDictUnifier() {
   }

   public static Comparator<ItemAndMetadata> getSimpleItemStackComparator() {
      if (stackComparator == null) {
         List<String> modPriorities = Arrays.asList(ConfigHolder.compat.modPriorities);
         if (modPriorities.isEmpty()) {
            Function<ItemAndMetadata, String> modIdExtractor = (stack) -> stack.item.getRegistryName().func_110624_b();
            stackComparator = Comparator.comparing(modIdExtractor);
         } else {
            stackComparator = Collections.reverseOrder(new CustomModPriorityComparator(modPriorities));
         }
      }

      return stackComparator;
   }

   public static Comparator<ItemStack> getItemStackComparator() {
      Comparator<ItemAndMetadata> comparator = getSimpleItemStackComparator();
      return (first, second) -> comparator.compare(new ItemAndMetadata(first), new ItemAndMetadata(second));
   }

   public static void registerOre(ItemStack itemStack, OrePrefix orePrefix, @Nullable Material material) {
      registerOre(itemStack, orePrefix.name(), material);
   }

   public static void registerOre(ItemStack itemStack, String customOrePrefix, @Nullable Material material) {
      if (!itemStack.func_190926_b()) {
         OreDictionary.registerOre(customOrePrefix + (material == null ? "" : material.toCamelCaseString()), itemStack);
      }
   }

   public static void registerOre(ItemStack itemStack, String oreDict) {
      if (!itemStack.func_190926_b()) {
         OreDictionary.registerOre(oreDict, itemStack);
      }
   }

   public static void init() {
      for(String registeredOreName : OreDictionary.getOreNames()) {
         for(ItemStack itemStack : OreDictionary.getOres(registeredOreName)) {
            onItemRegistration(new OreDictionary.OreRegisterEvent(registeredOreName, itemStack));
         }
      }

      MinecraftForge.EVENT_BUS.register(OreDictUnifier.class);
   }

   @SubscribeEvent
   public static void onItemRegistration(OreDictionary.OreRegisterEvent event) {
      String oreName = event.getName();
      ItemVariantMap.Mutable<Set<String>> entry = (ItemVariantMap.Mutable)stackOreDictName.computeIfAbsent(event.getOre().func_77973_b(), (item) -> (ItemVariantMap.Mutable)(item.func_77614_k() ? new MultiItemVariantMap() : new SingleItemVariantMap()));
      Set<String> set = (Set)entry.get(event.getOre());
      if (set == null) {
         set = new ObjectOpenHashSet();
         entry.put(event.getOre(), set);
      }

      set.add(oreName);
      List<ItemStack> itemStackListForOreDictName = (List)oreDictNameStacks.computeIfAbsent(oreName, (k) -> new ArrayList());
      addAndSort(itemStackListForOreDictName, event.getOre().func_77946_l(), getItemStackComparator());
      OrePrefix orePrefix = OrePrefix.getPrefix(oreName);
      Material material = null;
      if (orePrefix == null) {
         ArrayList<String> splits = new ArrayList();
         StringBuilder builder = new StringBuilder();

         for(char character : oreName.toCharArray()) {
            if (Character.isUpperCase(character)) {
               if (builder.length() > 0) {
                  splits.add(builder.toString());
                  builder = (new StringBuilder()).append(character);
               } else {
                  splits.add(Character.toString(character));
               }
            } else {
               builder.append(character);
            }
         }

         if (builder.length() > 0) {
            splits.add(builder.toString());
         }

         for(MaterialRegistry registry : GregTechAPI.materialManager.getRegistries()) {
            StringBuilder buffer = new StringBuilder();

            for(int i = 0; i < splits.size(); ++i) {
               buffer.append((String)splits.get(i));
               OrePrefix maybePrefix = OrePrefix.getPrefix(buffer.toString());
               String possibleMaterialName = Joiner.on("").join(splits.subList(i + 1, splits.size()));
               String underscoreName = GTUtility.toLowerCaseUnderscore(possibleMaterialName);
               Material possibleMaterial = (Material)registry.func_82594_a(underscoreName);
               if (possibleMaterial == null) {
                  possibleMaterial = GregTechAPI.markerMaterialRegistry.getMarkerMaterial(underscoreName);
               }

               if (maybePrefix != null && possibleMaterial != null) {
                  orePrefix = maybePrefix;
                  material = possibleMaterial;
                  break;
               }
            }

            if (material != null) {
               break;
            }
         }
      }

      if (orePrefix != null && (material != null || orePrefix.isSelfReferencing)) {
         ItemAndMetadata key = new ItemAndMetadata(event.getOre());
         UnificationEntry unificationEntry = new UnificationEntry(orePrefix, material);
         ArrayList<ItemAndMetadata> itemListForUnifiedEntry = (ArrayList)stackUnificationItems.computeIfAbsent(unificationEntry, (p) -> new ArrayList());
         addAndSort(itemListForUnifiedEntry, key, getSimpleItemStackComparator());
         if (!unificationEntry.orePrefix.isMarkerPrefix()) {
            stackUnificationInfo.put(key, unificationEntry);
         }

         orePrefix.processOreRegistration(material);
      }

   }

   public static @NotNull Set<String> getOreDictionaryNames(@NotNull ItemStack itemStack) {
      if (itemStack.func_190926_b()) {
         return Collections.emptySet();
      } else {
         ItemVariantMap<Set<String>> nameEntry = (ItemVariantMap)stackOreDictName.get(itemStack.func_77973_b());
         if (nameEntry == null) {
            return Collections.emptySet();
         } else {
            short itemDamage = (short)itemStack.func_77952_i();
            Set<String> names = (Set)nameEntry.get(itemDamage);
            Set<String> wildcardNames = itemDamage == 32767 ? null : (Set)nameEntry.get((short)32767);
            if (names == null) {
               return wildcardNames == null ? Collections.emptySet() : Collections.unmodifiableSet(wildcardNames);
            } else {
               return (Set<String>)(wildcardNames != null && names != wildcardNames ? Sets.union(names, wildcardNames) : Collections.unmodifiableSet(names));
            }
         }
      }
   }

   public static @Nullable ItemVariantMap<Set<String>> getOreDictionaryEntry(@NotNull Item item) {
      ItemVariantMap.Mutable<Set<String>> entry = (ItemVariantMap.Mutable)stackOreDictName.get(item);
      return entry == null ? null : ItemVariantMap.unmodifiableSetView(entry);
   }

   public static @NotNull ItemVariantMap<Set<String>> getOreDictionaryEntryOrEmpty(@NotNull Item item) {
      ItemVariantMap.Mutable<Set<String>> entry = (ItemVariantMap.Mutable)stackOreDictName.get(item);
      return entry == null ? ItemVariantMap.empty() : ItemVariantMap.unmodifiableSetView(entry);
   }

   public static boolean hasOreDictionaryEntry(@NotNull Item item) {
      return stackOreDictName.containsKey(item);
   }

   public static boolean hasOreDictionary(@NotNull ItemStack itemStack, @NotNull String oreDictName) {
      if (itemStack.func_190926_b()) {
         return false;
      } else {
         ItemVariantMap<Set<String>> nameEntry = (ItemVariantMap)stackOreDictName.get(itemStack.func_77973_b());
         if (nameEntry == null) {
            return false;
         } else {
            short itemDamage = (short)itemStack.func_77952_i();
            Set<String> names = (Set)nameEntry.get(itemDamage);
            if (names != null && names.contains(oreDictName)) {
               return true;
            } else if (itemDamage == 32767) {
               return false;
            } else {
               Set<String> wildcardNames = (Set)nameEntry.get((short)32767);
               return wildcardNames != null && wildcardNames != names && wildcardNames.contains(oreDictName);
            }
         }
      }
   }

   public static @NotNull List<@NotNull ItemStack> getAllWithOreDictionaryName(@NotNull String oreDictionaryName) {
      List<ItemStack> stacks = (List)oreDictNameStacks.get(oreDictionaryName);
      return stacks == null ? Collections.emptyList() : (List)stacks.stream().map(ItemStack::func_77946_l).collect(Collectors.toList());
   }

   public static @Nullable MaterialStack getMaterial(ItemStack itemStack) {
      if (itemStack.func_190926_b()) {
         return null;
      } else {
         ItemAndMetadata key = new ItemAndMetadata(itemStack);
         UnificationEntry entry = (UnificationEntry)GTUtility.getOrWildcardMeta(stackUnificationInfo, key);
         if (entry != null) {
            Material entryMaterial = entry.material;
            if (entryMaterial == null) {
               entryMaterial = entry.orePrefix.materialType;
            }

            if (entryMaterial != null) {
               return new MaterialStack(entryMaterial, entry.orePrefix.getMaterialAmount(entryMaterial));
            }
         }

         return null;
      }
   }

   public static @Nullable OrePrefix getPrefix(ItemStack itemStack) {
      if (itemStack.func_190926_b()) {
         return null;
      } else {
         UnificationEntry entry = (UnificationEntry)GTUtility.getOrWildcardMeta(stackUnificationInfo, new ItemAndMetadata(itemStack));
         return entry != null ? entry.orePrefix : null;
      }
   }

   public static @Nullable UnificationEntry getUnificationEntry(ItemStack itemStack) {
      return itemStack.func_190926_b() ? null : (UnificationEntry)GTUtility.getOrWildcardMeta(stackUnificationInfo, new ItemAndMetadata(itemStack));
   }

   public static ItemStack getUnificated(ItemStack itemStack) {
      if (itemStack.func_190926_b()) {
         return ItemStack.field_190927_a;
      } else {
         UnificationEntry unificationEntry = getUnificationEntry(itemStack);
         if (unificationEntry != null && stackUnificationItems.containsKey(unificationEntry) && unificationEntry.orePrefix.isUnificationEnabled) {
            ArrayList<ItemAndMetadata> keys = (ArrayList)stackUnificationItems.get(unificationEntry);
            return keys.size() > 0 ? ((ItemAndMetadata)keys.get(0)).toItemStack(itemStack.func_190916_E()) : itemStack;
         } else {
            return itemStack;
         }
      }
   }

   public static ItemStack get(UnificationEntry unificationEntry) {
      return get(unificationEntry.orePrefix, unificationEntry.material);
   }

   public static ItemStack get(OrePrefix orePrefix, Material material) {
      return get(orePrefix, material, 1);
   }

   public static ItemStack get(OrePrefix orePrefix, Material material, int stackSize) {
      UnificationEntry unificationEntry = new UnificationEntry(orePrefix, material);
      if (!stackUnificationItems.containsKey(unificationEntry)) {
         return ItemStack.field_190927_a;
      } else {
         ArrayList<ItemAndMetadata> keys = (ArrayList)stackUnificationItems.get(unificationEntry);
         return keys.size() > 0 ? ((ItemAndMetadata)keys.get(0)).toItemStack(stackSize) : ItemStack.field_190927_a;
      }
   }

   public static ItemStack get(String oreDictName) {
      List<ItemStack> itemStacks = (List)oreDictNameStacks.get(oreDictName);
      return itemStacks != null && itemStacks.size() != 0 ? ((ItemStack)itemStacks.get(0)).func_77946_l() : ItemStack.field_190927_a;
   }

   public static List<ItemStack> getAll(UnificationEntry unificationEntry) {
      if (!stackUnificationItems.containsKey(unificationEntry)) {
         return Collections.emptyList();
      } else {
         ArrayList<ItemAndMetadata> keys = (ArrayList)stackUnificationItems.get(unificationEntry);
         return (List)keys.stream().map(ItemAndMetadata::toItemStack).collect(Collectors.toList());
      }
   }

   public static ItemStack getDust(Material material, long materialAmount) {
      if (material.hasProperty(PropertyKey.DUST) && materialAmount > 0L) {
         if (materialAmount % 3628800L != 0L && materialAmount < 58060800L) {
            if (materialAmount * 4L % 3628800L != 0L && materialAmount < 29030400L) {
               return materialAmount * 9L >= 3628800L ? get(OrePrefix.dustTiny, material, (int)(materialAmount * 9L / 3628800L)) : ItemStack.field_190927_a;
            } else {
               return get(OrePrefix.dustSmall, material, (int)(materialAmount * 4L / 3628800L));
            }
         } else {
            return get(OrePrefix.dust, material, (int)(materialAmount / 3628800L));
         }
      } else {
         return ItemStack.field_190927_a;
      }
   }

   public static ItemStack getDust(MaterialStack materialStack) {
      return getDust(materialStack.material, materialStack.amount);
   }

   public static ItemStack getIngot(Material material, long materialAmount) {
      if (material.hasProperty(PropertyKey.INGOT) && materialAmount > 0L) {
         if (materialAmount % 32659200L == 0L) {
            return get(OrePrefix.block, material, (int)(materialAmount / 32659200L));
         } else if (materialAmount % 3628800L != 0L && materialAmount < 58060800L) {
            return materialAmount * 9L >= 3628800L ? get(OrePrefix.nugget, material, (int)(materialAmount * 9L / 3628800L)) : ItemStack.field_190927_a;
         } else {
            return get(OrePrefix.ingot, material, (int)(materialAmount / 3628800L));
         }
      } else {
         return ItemStack.field_190927_a;
      }
   }

   public static ItemStack getIngot(MaterialStack materialStack) {
      return getIngot(materialStack.material, materialStack.amount);
   }

   public static ItemStack getIngotOrDust(Material material, long materialAmount) {
      ItemStack ingotStack = getIngot(material, materialAmount);
      return ingotStack != ItemStack.field_190927_a ? ingotStack : getDust(material, materialAmount);
   }

   public static ItemStack getIngotOrDust(MaterialStack materialStack) {
      return getIngotOrDust(materialStack.material, materialStack.amount);
   }

   public static ItemStack getGem(MaterialStack materialStack) {
      return materialStack.material.hasProperty(PropertyKey.GEM) && !OrePrefix.gem.isIgnored(materialStack.material) && materialStack.amount == OrePrefix.gem.getMaterialAmount(materialStack.material) ? get(OrePrefix.gem, materialStack.material, (int)(materialStack.amount / 3628800L)) : getDust(materialStack);
   }

   private static synchronized <T> void addAndSort(List<T> list, T itemToAdd, Comparator<T> comparator) {
      list.add(itemToAdd);
      if (list.size() > 1) {
         list.sort(comparator);
      }

   }
}
