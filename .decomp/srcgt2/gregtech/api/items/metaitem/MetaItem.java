package gregtech.api.items.metaitem;

import com.enderio.core.common.interfaces.IOverlayRenderAware;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.capability.IFilter;
import gregtech.api.capability.IFilteredFluidContainer;
import gregtech.api.capability.IPropertyFluidFilter;
import gregtech.api.capability.impl.CombinedCapabilityProvider;
import gregtech.api.gui.ModularUI;
import gregtech.api.items.gui.ItemUIFactory;
import gregtech.api.items.gui.PlayerInventoryHolder;
import gregtech.api.items.metaitem.stats.IEnchantabilityHelper;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.items.metaitem.stats.IItemCapabilityProvider;
import gregtech.api.items.metaitem.stats.IItemComponent;
import gregtech.api.items.metaitem.stats.IItemContainerItemProvider;
import gregtech.api.items.metaitem.stats.IItemModelDispatcher;
import gregtech.api.items.metaitem.stats.IItemUseManager;
import gregtech.api.util.GTUtility;
import gregtech.api.util.LocalizationUtils;
import gregtech.client.utils.ToolChargeBarRenderer;
import gregtech.common.ConfigHolder;
import gregtech.common.creativetab.GTCreativeTabs;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.shorts.Short2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.shorts.Short2ObjectMap;
import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.Optional.Interface;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Interface(
   modid = "endercore",
   iface = "com.enderio.core.common.interfaces.IOverlayRenderAware"
)
public abstract class MetaItem<T extends MetaItem<?>.MetaValueItem> extends Item implements ItemUIFactory, IOverlayRenderAware {
   private static final List<MetaItem<?>> META_ITEMS = new ArrayList();
   private final Map<String, T> names = new Object2ObjectOpenHashMap();
   protected final Short2ObjectMap<T> metaItems = new Short2ObjectLinkedOpenHashMap();
   protected final Short2ObjectMap<ModelResourceLocation> metaItemsModels = new Short2ObjectOpenHashMap();
   protected final Short2ObjectMap<ModelResourceLocation[]> specialItemsModels = new Short2ObjectOpenHashMap();
   protected static final ModelResourceLocation MISSING_LOCATION = new ModelResourceLocation("builtin/missing", "inventory");
   protected final short metaItemOffset;
   private CreativeTabs[] defaultCreativeTabs;
   private final Set<CreativeTabs> additionalCreativeTabs;
   private String translationKey;

   public static List<MetaItem<?>> getMetaItems() {
      return Collections.unmodifiableList(META_ITEMS);
   }

   public MetaItem(short metaItemOffset) {
      this.defaultCreativeTabs = new CreativeTabs[]{GTCreativeTabs.TAB_GREGTECH};
      this.additionalCreativeTabs = new ObjectArraySet();
      this.translationKey = "metaitem";
      this.func_77627_a(true);
      this.metaItemOffset = metaItemOffset;
      META_ITEMS.add(this);
   }

   @SideOnly(Side.CLIENT)
   public void registerColor() {
      Minecraft.func_71410_x().getItemColors().func_186730_a(this::getColorForItemStack, new Item[]{this});
   }

   @SideOnly(Side.CLIENT)
   public void registerModels() {
      ShortIterator var1 = this.metaItems.keySet().iterator();

      while(var1.hasNext()) {
         short itemMetaKey = (Short)var1.next();
         T metaValueItem = (T)(this.metaItems.get(itemMetaKey));
         int numberOfModels = metaValueItem.getModelAmount();
         if (numberOfModels > 1) {
            ModelResourceLocation[] resourceLocations = new ModelResourceLocation[numberOfModels];

            for(int i = 0; i < resourceLocations.length; ++i) {
               ResourceLocation resourceLocation = this.createItemModelPath(metaValueItem, "/" + (i + 1));
               ModelBakery.registerItemVariants(this, new ResourceLocation[]{resourceLocation});
               resourceLocations[i] = new ModelResourceLocation(resourceLocation, "inventory");
            }

            this.specialItemsModels.put((short)(this.metaItemOffset + itemMetaKey), resourceLocations);
         } else {
            ResourceLocation resourceLocation = this.createItemModelPath(metaValueItem, "");
            if (numberOfModels > 0) {
               ModelBakery.registerItemVariants(this, new ResourceLocation[]{resourceLocation});
            }

            this.metaItemsModels.put((short)(this.metaItemOffset + itemMetaKey), new ModelResourceLocation(resourceLocation, "inventory"));
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void registerTextureMesh() {
      ModelLoader.setCustomMeshDefinition(this, (itemStack) -> {
         short itemDamage = this.formatRawItemDamage((short)itemStack.func_77952_i());
         if (this.specialItemsModels.containsKey(itemDamage)) {
            int modelIndex = this.getModelIndex(itemStack);
            return ((ModelResourceLocation[])this.specialItemsModels.get(itemDamage))[modelIndex];
         } else {
            return this.metaItemsModels.containsKey(itemDamage) ? (ModelResourceLocation)this.metaItemsModels.get(itemDamage) : MISSING_LOCATION;
         }
      });
   }

   public ResourceLocation createItemModelPath(T metaValueItem, String postfix) {
      return GTUtility.gregtechId(this.formatModelPath(metaValueItem) + postfix);
   }

   protected String formatModelPath(T metaValueItem) {
      return "metaitems/" + metaValueItem.unlocalizedName;
   }

   protected int getModelIndex(ItemStack itemStack) {
      T metaValueItem = this.getItem(itemStack);
      Objects.requireNonNull(metaValueItem);
      IItemModelDispatcher modelDispatcher = metaValueItem.getItemModelDispatcher();
      if (modelDispatcher == null) {
         return 0;
      } else {
         int maxIndex = metaValueItem.getModelAmount() - 1;
         int index = modelDispatcher.getModelIndex(itemStack, maxIndex);
         Validate.inclusiveBetween(0, maxIndex, index, "Model index should be in range from 0 to %d (inclusive), where %d is supplied", new Object[]{maxIndex, index});
         return index;
      }
   }

   @SideOnly(Side.CLIENT)
   protected int getColorForItemStack(ItemStack stack, int tintIndex) {
      T metaValueItem = this.getItem(stack);
      return metaValueItem != null && metaValueItem.getColorProvider() != null ? metaValueItem.getColorProvider().getItemStackColor(stack, tintIndex) : 16777215;
   }

   public boolean showDurabilityBar(@NotNull ItemStack stack) {
      return false;
   }

   public double getDurabilityForDisplay(@NotNull ItemStack stack) {
      T metaValueItem = this.getItem(stack);
      return metaValueItem != null && metaValueItem.getDurabilityManager() != null ? metaValueItem.getDurabilityManager().getDurabilityForDisplay(stack) : (double)-1.0F;
   }

   public @NotNull EnumRarity func_77613_e(@NotNull ItemStack stack) {
      T metaValueItem = this.getItem(stack);
      return metaValueItem != null && metaValueItem.getRarity() != null ? metaValueItem.getRarity() : super.func_77613_e(stack);
   }

   protected abstract T constructMetaValueItem(short var1, String var2);

   public final T addItem(int metaValue, String unlocalizedName) {
      Validate.inclusiveBetween(0L, 32766L, (long)(metaValue + this.metaItemOffset), "MetaItem ID should be in range from 0 to Short.MAX_VALUE-1");
      T metaValueItem = this.constructMetaValueItem((short)metaValue, unlocalizedName);
      if (this.metaItems.containsKey((short)metaValue)) {
         T registeredItem = (T)(this.metaItems.get((short)metaValue));
         throw new IllegalArgumentException(String.format("MetaId %d is already occupied by item %s (requested by item %s)", metaValue, registeredItem.unlocalizedName, unlocalizedName));
      } else {
         this.metaItems.put((short)metaValue, metaValueItem);
         this.names.put(unlocalizedName, metaValueItem);
         return metaValueItem;
      }
   }

   public final Collection<T> getAllItems() {
      return Collections.unmodifiableCollection(this.metaItems.values());
   }

   public final @Nullable T getItem(short metaValue) {
      return (T)(this.metaItems.get(this.formatRawItemDamage(metaValue)));
   }

   public final @Nullable T getItem(String valueName) {
      return (T)(this.names.get(valueName));
   }

   public final @Nullable T getItem(ItemStack itemStack) {
      return (T)this.getItem((short)(itemStack.func_77952_i() - this.metaItemOffset));
   }

   protected short formatRawItemDamage(short metaValue) {
      return metaValue;
   }

   public void registerSubItems() {
   }

   public ICapabilityProvider initCapabilities(@NotNull ItemStack stack, @Nullable NBTTagCompound nbt) {
      T metaValueItem = this.getItem(stack);
      if (metaValueItem == null) {
         return null;
      } else {
         ArrayList<ICapabilityProvider> providers = new ArrayList();

         for(IItemComponent itemComponent : metaValueItem.getAllStats()) {
            if (itemComponent instanceof IItemCapabilityProvider) {
               IItemCapabilityProvider provider = (IItemCapabilityProvider)itemComponent;
               providers.add(provider.createProvider(stack));
            }
         }

         return new CombinedCapabilityProvider(providers);
      }
   }

   public int getItemBurnTime(@NotNull ItemStack itemStack) {
      T metaValueItem = this.getItem(itemStack);
      return metaValueItem == null ? super.getItemBurnTime(itemStack) : metaValueItem.getBurnValue();
   }

   private IItemUseManager getUseManager(ItemStack itemStack) {
      T metaValueItem = this.getItem(itemStack);
      return metaValueItem == null ? null : metaValueItem.getUseManager();
   }

   public List<IItemBehaviour> getBehaviours(ItemStack itemStack) {
      T metaValueItem = this.getItem(itemStack);
      return (List<IItemBehaviour>)(metaValueItem == null ? ImmutableList.of() : metaValueItem.getBehaviours());
   }

   public int getItemStackLimit(@NotNull ItemStack stack) {
      T metaValueItem = this.getItem(stack);
      return metaValueItem == null ? 64 : metaValueItem.getMaxStackSize(stack);
   }

   public @NotNull EnumAction func_77661_b(@NotNull ItemStack stack) {
      IItemUseManager useManager = this.getUseManager(stack);
      return useManager != null ? useManager.getUseAction(stack) : EnumAction.NONE;
   }

   public int func_77626_a(@NotNull ItemStack stack) {
      IItemUseManager useManager = this.getUseManager(stack);
      return useManager != null ? useManager.getMaxItemUseDuration(stack) : 0;
   }

   public void onUsingTick(@NotNull ItemStack stack, @NotNull EntityLivingBase player, int count) {
      if (player instanceof EntityPlayer) {
         IItemUseManager useManager = this.getUseManager(stack);
         if (useManager != null) {
            useManager.onItemUsingTick(stack, (EntityPlayer)player, count);
         }
      }

   }

   public void func_77615_a(@NotNull ItemStack stack, @NotNull World world, @NotNull EntityLivingBase player, int timeLeft) {
      if (player instanceof EntityPlayer) {
         IItemUseManager useManager = this.getUseManager(stack);
         if (useManager != null) {
            useManager.onPlayerStoppedItemUsing(stack, (EntityPlayer)player, timeLeft);
         }
      }

   }

   public @NotNull ItemStack func_77654_b(@NotNull ItemStack stack, @NotNull World world, @NotNull EntityLivingBase player) {
      if (player instanceof EntityPlayer) {
         IItemUseManager useManager = this.getUseManager(stack);
         if (useManager != null) {
            return useManager.onItemUseFinish(stack, (EntityPlayer)player);
         }
      }

      return stack;
   }

   public boolean onLeftClickEntity(@NotNull ItemStack stack, @NotNull EntityPlayer player, @NotNull Entity entity) {
      boolean returnValue = false;

      for(IItemBehaviour behaviour : this.getBehaviours(stack)) {
         if (behaviour.onLeftClickEntity(stack, player, entity)) {
            returnValue = true;
         }
      }

      return returnValue;
   }

   public boolean func_111207_a(@NotNull ItemStack stack, @NotNull EntityPlayer playerIn, @NotNull EntityLivingBase target, @NotNull EnumHand hand) {
      boolean returnValue = false;

      for(IItemBehaviour behaviour : this.getBehaviours(stack)) {
         if (behaviour.itemInteractionForEntity(stack, playerIn, target, hand)) {
            returnValue = true;
         }
      }

      return returnValue;
   }

   public @NotNull ActionResult<ItemStack> func_77659_a(@NotNull World world, EntityPlayer player, @NotNull EnumHand hand) {
      ItemStack itemStack = player.func_184586_b(hand);

      for(IItemBehaviour behaviour : this.getBehaviours(itemStack)) {
         ActionResult<ItemStack> behaviourResult = behaviour.onItemRightClick(world, player, hand);
         itemStack = (ItemStack)behaviourResult.func_188398_b();
         if (behaviourResult.func_188397_a() != EnumActionResult.PASS) {
            return ActionResult.newResult(behaviourResult.func_188397_a(), itemStack);
         }

         if (itemStack.func_190926_b()) {
            return ActionResult.newResult(EnumActionResult.PASS, ItemStack.field_190927_a);
         }
      }

      IItemUseManager useManager = this.getUseManager(itemStack);
      if (useManager != null && useManager.canStartUsing(itemStack, player)) {
         useManager.onItemUseStart(itemStack, player);
         player.func_184598_c(hand);
         return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
      } else {
         return ActionResult.newResult(EnumActionResult.PASS, itemStack);
      }
   }

   public @NotNull EnumActionResult onItemUseFirst(EntityPlayer player, @NotNull World world, @NotNull BlockPos pos, @NotNull EnumFacing side, float hitX, float hitY, float hitZ, @NotNull EnumHand hand) {
      ItemStack itemStack = player.func_184586_b(hand);

      for(IItemBehaviour behaviour : this.getBehaviours(itemStack)) {
         EnumActionResult behaviourResult = behaviour.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
         if (behaviourResult != EnumActionResult.PASS) {
            return behaviourResult;
         }

         if (itemStack.func_190926_b()) {
            return EnumActionResult.PASS;
         }
      }

      return EnumActionResult.PASS;
   }

   public @NotNull EnumActionResult func_180614_a(EntityPlayer player, @NotNull World world, @NotNull BlockPos pos, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ) {
      ItemStack stack = player.func_184586_b(hand);
      ItemStack originalStack = stack.func_77946_l();

      for(IItemBehaviour behaviour : this.getBehaviours(stack)) {
         ActionResult<ItemStack> behaviourResult = behaviour.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
         stack = (ItemStack)behaviourResult.func_188398_b();
         if (behaviourResult.func_188397_a() != EnumActionResult.PASS) {
            if (!ItemStack.func_77989_b(originalStack, stack)) {
               player.func_184611_a(hand, stack);
            }

            return behaviourResult.func_188397_a();
         }

         if (stack.func_190926_b()) {
            player.func_184611_a(hand, ItemStack.field_190927_a);
            return EnumActionResult.PASS;
         }
      }

      return EnumActionResult.PASS;
   }

   public @NotNull Multimap<String, AttributeModifier> getAttributeModifiers(@NotNull EntityEquipmentSlot slot, @NotNull ItemStack stack) {
      HashMultimap<String, AttributeModifier> modifiers = HashMultimap.create();
      T metaValueItem = this.getItem(stack);
      if (metaValueItem != null) {
         for(IItemBehaviour behaviour : this.getBehaviours(stack)) {
            modifiers.putAll(behaviour.getAttributeModifiers(slot, stack));
         }
      }

      return modifiers;
   }

   public boolean func_77616_k(@NotNull ItemStack stack) {
      T metaValueItem = this.getItem(stack);
      if (metaValueItem == null) {
         return super.func_77616_k(stack);
      } else {
         IEnchantabilityHelper helper = metaValueItem.getEnchantabilityHelper();
         return helper != null && helper.isEnchantable(stack);
      }
   }

   public int getItemEnchantability(@NotNull ItemStack stack) {
      T metaValueItem = this.getItem(stack);
      if (metaValueItem != null) {
         IEnchantabilityHelper helper = metaValueItem.getEnchantabilityHelper();
         return helper == null ? 0 : helper.getItemEnchantability(stack);
      } else {
         return super.getItemEnchantability(stack);
      }
   }

   public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack, @NotNull Enchantment enchantment) {
      T metaValueItem = this.getItem(stack);
      if (metaValueItem == null) {
         return super.canApplyAtEnchantingTable(stack, enchantment);
      } else {
         IEnchantabilityHelper helper = metaValueItem.getEnchantabilityHelper();
         return helper != null && helper.canApplyAtEnchantingTable(stack, enchantment);
      }
   }

   public void func_77663_a(@NotNull ItemStack stack, @NotNull World worldIn, @NotNull Entity entityIn, int itemSlot, boolean isSelected) {
      for(IItemBehaviour behaviour : this.getBehaviours(stack)) {
         behaviour.onUpdate(stack, entityIn);
      }

   }

   public boolean shouldCauseReequipAnimation(@NotNull ItemStack oldStack, @NotNull ItemStack newStack, boolean slotChanged) {
      if (ItemStack.func_179545_c(oldStack, newStack) && oldStack.hasCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, (EnumFacing)null) && oldStack.func_77942_o() && newStack.func_77942_o()) {
         oldStack = oldStack.func_77946_l();
         newStack = newStack.func_77946_l();
         NBTTagCompound oldTag = oldStack.func_77978_p();
         NBTTagCompound newTag = newStack.func_77978_p();
         if (oldTag != null && newTag != null) {
            oldTag.func_82580_o("Charge");
            newTag.func_82580_o("Charge");
            if (oldTag.func_74764_b("terminal")) {
               oldTag.func_74775_l("terminal").func_74775_l("_hw").func_82580_o("battery");
               newTag.func_74775_l("terminal").func_74775_l("_hw").func_82580_o("battery");
            }
         }
      }

      return !ItemStack.func_77989_b(oldStack, newStack);
   }

   public @NotNull MetaItem<T> setTranslationKey(@NotNull String key) {
      this.translationKey = (String)Objects.requireNonNull(key, "key == null");
      return this;
   }

   public @NotNull String func_77658_a() {
      return this.getTranslationKey((MetaValueItem)null);
   }

   public @NotNull String func_77667_c(@NotNull ItemStack stack) {
      return this.getTranslationKey(this.getItem(stack));
   }

   protected @NotNull String getTranslationKey(@Nullable T metaValueItem) {
      return metaValueItem == null ? this.translationKey : this.translationKey + "." + metaValueItem.unlocalizedName;
   }

   public @NotNull String func_77653_i(ItemStack stack) {
      if (stack.func_77952_i() >= this.metaItemOffset) {
         T item = this.getItem(stack);
         if (item == null) {
            return "invalid item";
         } else {
            String unlocalizedName = this.getTranslationKey(item) + ".name";
            if (item.getNameProvider() != null) {
               return item.getNameProvider().getItemStackDisplayName(stack, unlocalizedName);
            } else {
               IFluidHandlerItem fluidHandlerItem = (IFluidHandlerItem)ItemHandlerHelper.copyStackWithSize(stack, 1).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, (EnumFacing)null);
               if (fluidHandlerItem != null) {
                  FluidStack fluidInside = fluidHandlerItem.drain(Integer.MAX_VALUE, false);
                  return LocalizationUtils.format(unlocalizedName, new Object[]{fluidInside == null ? LocalizationUtils.format("metaitem.fluid_cell.empty", new Object[0]) : fluidInside.getLocalizedName()});
               } else {
                  return LocalizationUtils.format(unlocalizedName, new Object[0]);
               }
            }
         }
      } else {
         return super.func_77653_i(stack);
      }
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(@NotNull ItemStack itemStack, @Nullable World worldIn, @NotNull List<String> lines, @NotNull ITooltipFlag tooltipFlag) {
      T item = this.getItem(itemStack);
      if (item != null) {
         String unlocalizedTooltip = this.getTranslationKey(item) + ".tooltip";
         if (I18n.func_188566_a(unlocalizedTooltip)) {
            Collections.addAll(lines, LocalizationUtils.formatLines(unlocalizedTooltip, new Object[0]));
         }

         IElectricItem electricItem = (IElectricItem)itemStack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, (EnumFacing)null);
         if (electricItem != null) {
            if (electricItem.canProvideChargeExternally()) {
               addDischargeItemTooltip(lines, electricItem.getMaxCharge(), electricItem.getCharge(), electricItem.getTier());
            } else {
               lines.add(I18n.func_135052_a("metaitem.generic.electric_item.tooltip", new Object[]{electricItem.getCharge(), electricItem.getMaxCharge(), GTValues.VNF[electricItem.getTier()]}));
            }
         }

         IFluidHandlerItem fluidHandler = (IFluidHandlerItem)ItemHandlerHelper.copyStackWithSize(itemStack, 1).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, (EnumFacing)null);
         if (fluidHandler != null) {
            IFluidTankProperties fluidTankProperties = fluidHandler.getTankProperties()[0];
            FluidStack fluid = fluidTankProperties.getContents();
            lines.add(I18n.func_135052_a("metaitem.generic.fluid_container.tooltip", new Object[]{fluid == null ? 0 : fluid.amount, fluidTankProperties.getCapacity(), fluid == null ? "" : fluid.getLocalizedName()}));
            if (fluidHandler instanceof IFilteredFluidContainer) {
               IFilteredFluidContainer filtered = (IFilteredFluidContainer)fluidHandler;
               IFilter var13 = filtered.getFilter();
               if (var13 instanceof IPropertyFluidFilter) {
                  IPropertyFluidFilter propertyFilter = (IPropertyFluidFilter)var13;
                  propertyFilter.appendTooltips(lines, false, true);
               }
            }
         }

         for(IItemBehaviour behaviour : this.getBehaviours(itemStack)) {
            behaviour.addInformation(itemStack, lines);
         }

         if (ConfigHolder.misc.debug) {
            lines.add("MetaItem Id: " + item.unlocalizedName);
         }

      }
   }

   private static void addDischargeItemTooltip(List<String> tooltip, long maxCharge, long currentCharge, int tier) {
      if (currentCharge == 0L) {
         tooltip.add(I18n.func_135052_a("metaitem.generic.electric_item.tooltip", new Object[]{currentCharge, maxCharge, GTValues.VNF[tier]}));
      } else {
         Instant start = Instant.now();
         Instant end = Instant.now().plusSeconds((long)((double)currentCharge * (double)1.0F / (double)GTValues.V[tier] / (double)20.0F));
         Duration duration = Duration.between(start, end);
         double percentRemaining = (double)currentCharge * (double)1.0F / (double)maxCharge * (double)100.0F;
         long timeRemaining;
         String unit;
         if (duration.getSeconds() <= 180L) {
            timeRemaining = duration.getSeconds();
            unit = I18n.func_135052_a("metaitem.battery.charge_unit.second", new Object[0]);
         } else if (duration.toMinutes() <= 180L) {
            timeRemaining = duration.toMinutes();
            unit = I18n.func_135052_a("metaitem.battery.charge_unit.minute", new Object[0]);
         } else {
            timeRemaining = duration.toHours();
            unit = I18n.func_135052_a("metaitem.battery.charge_unit.hour", new Object[0]);
         }

         tooltip.add(I18n.func_135052_a("metaitem.battery.charge_detailed", new Object[]{currentCharge, maxCharge, GTValues.VNF[tier], (char)(percentRemaining < (double)30.0F ? 'c' : (percentRemaining < (double)60.0F ? 'e' : 'a')), timeRemaining, unit}));
      }
   }

   public boolean hasContainerItem(@NotNull ItemStack itemStack) {
      T item = this.getItem(itemStack);
      if (item == null) {
         return false;
      } else {
         return item.getContainerItemProvider() != null;
      }
   }

   public @NotNull ItemStack getContainerItem(@NotNull ItemStack itemStack) {
      T item = this.getItem(itemStack);
      if (item == null) {
         return ItemStack.field_190927_a;
      } else {
         itemStack = itemStack.func_77946_l();
         itemStack.func_190920_e(1);
         IItemContainerItemProvider provider = item.getContainerItemProvider();
         return provider == null ? ItemStack.field_190927_a : provider.getContainerItem(itemStack);
      }
   }

   public @NotNull CreativeTabs @NotNull [] getCreativeTabs() {
      if (this.additionalCreativeTabs.isEmpty()) {
         return this.defaultCreativeTabs;
      } else {
         Set<CreativeTabs> tabs = new ObjectArraySet(this.additionalCreativeTabs);
         tabs.addAll(Arrays.asList(this.defaultCreativeTabs));
         return (CreativeTabs[])tabs.toArray(new CreativeTabs[0]);
      }
   }

   public @NotNull MetaItem<T> setCreativeTab(@NotNull CreativeTabs tab) {
      this.defaultCreativeTabs = new CreativeTabs[]{tab};
      return this;
   }

   public @NotNull MetaItem<T> setCreativeTabs(@NotNull CreativeTabs... tabs) {
      this.defaultCreativeTabs = tabs;
      return this;
   }

   public void addAdditionalCreativeTabs(@NotNull CreativeTabs... tabs) {
      for(CreativeTabs tab : tabs) {
         if (!ArrayUtils.contains(this.defaultCreativeTabs, tab) && tab != CreativeTabs.field_78027_g) {
            this.additionalCreativeTabs.add(tab);
         }
      }

   }

   protected boolean func_194125_a(@NotNull CreativeTabs tab) {
      return tab == CreativeTabs.field_78027_g || ArrayUtils.contains(this.defaultCreativeTabs, tab) || this.additionalCreativeTabs.contains(tab);
   }

   public void func_150895_a(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> subItems) {
      if (this.func_194125_a(tab)) {
         ObjectIterator var3 = this.metaItems.values().iterator();

         while(var3.hasNext()) {
            T item = (T)(var3.next());
            if (item.isInCreativeTab(tab)) {
               item.getSubItemHandler().getSubItems(item.getStackForm(), tab, subItems);
            }
         }

      }
   }

   public ModularUI createUI(PlayerInventoryHolder holder, EntityPlayer entityPlayer) {
      ItemStack itemStack = holder.getCurrentItem();
      T metaValueItem = this.getItem(itemStack);
      ItemUIFactory uiFactory = metaValueItem == null ? null : metaValueItem.getUIManager();
      return uiFactory == null ? null : uiFactory.createUI(holder, entityPlayer);
   }

   public void renderItemOverlayIntoGUI(@NotNull ItemStack stack, int xPosition, int yPosition) {
      ToolChargeBarRenderer.renderBarsItem(this, stack, xPosition, yPosition);
   }

   // $FF: synthetic method
   static CreativeTabs[] access$000(MetaItem x0) {
      return x0.defaultCreativeTabs;
   }
}
