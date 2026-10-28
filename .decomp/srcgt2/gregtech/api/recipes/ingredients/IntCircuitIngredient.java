package gregtech.api.recipes.ingredients;

import gregtech.api.items.gui.PlayerInventoryHolder;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.common.items.MetaItems;
import java.util.Objects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

public class IntCircuitIngredient extends GTRecipeInput {
   public static final int CIRCUIT_MIN = 0;
   public static final int CIRCUIT_MAX = 32;
   private static final IntCircuitIngredient[] INGREDIENTS = new IntCircuitIngredient[33];
   private final int matchingConfigurations;
   private ItemStack[] inputStacks;

   public static IntCircuitIngredient circuitInput(int meta) {
      if (meta >= 0 && meta <= 32) {
         IntCircuitIngredient ingredient = INGREDIENTS[meta];
         if (ingredient == null) {
            INGREDIENTS[meta] = ingredient = new IntCircuitIngredient(meta);
            ingredient.isConsumable = false;
            ingredient.setCached();
         }

         return ingredient;
      } else {
         throw new IndexOutOfBoundsException("Circuit meta " + meta + " is out of range");
      }
   }

   /** @deprecated */
   @Deprecated
   public static IntCircuitIngredient getOrCreate(IntCircuitIngredient ri) {
      return ri;
   }

   public IntCircuitIngredient(int matchingConfigurations) {
      this.amount = 1;
      this.matchingConfigurations = matchingConfigurations;
   }

   protected IntCircuitIngredient copy() {
      IntCircuitIngredient copy = new IntCircuitIngredient(this.matchingConfigurations);
      copy.isConsumable = this.isConsumable;
      return copy;
   }

   public GTRecipeInput copyWithAmount(int amount) {
      return this.copy();
   }

   public GTRecipeInput setNBTMatchingCondition(NBTMatcher nbtMatcher, NBTCondition nbtCondition) {
      return this;
   }

   public int getAmount() {
      return 1;
   }

   public ItemStack[] getInputStacks() {
      if (this.inputStacks == null) {
         this.inputStacks = new ItemStack[]{getIntegratedCircuit(this.matchingConfigurations)};
      }

      return this.inputStacks;
   }

   public boolean acceptsStack(@Nullable ItemStack itemStack) {
      return itemStack != null && MetaItems.INTEGRATED_CIRCUIT.isItemEqual(itemStack) && this.matchingConfigurations == getCircuitConfiguration(itemStack);
   }

   protected int computeHash() {
      return Objects.hash(new Object[]{this.matchingConfigurations, this.isConsumable});
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof IntCircuitIngredient)) {
         return false;
      } else {
         IntCircuitIngredient other = (IntCircuitIngredient)obj;
         return this.isConsumable == other.isConsumable && this.matchingConfigurations == other.matchingConfigurations;
      }
   }

   public boolean equalIgnoreAmount(GTRecipeInput input) {
      if (this == input) {
         return true;
      } else if (!(input instanceof IntCircuitIngredient)) {
         return false;
      } else {
         IntCircuitIngredient other = (IntCircuitIngredient)input;
         return this.matchingConfigurations == other.matchingConfigurations;
      }
   }

   public int getSortingOrder() {
      return this.isNonConsumable() ? 10 : super.getSortingOrder();
   }

   public String toString() {
      return "1xcircuit(" + this.matchingConfigurations + ")";
   }

   public static ItemStack getIntegratedCircuit(int configuration) {
      ItemStack stack = MetaItems.INTEGRATED_CIRCUIT.getStackForm();
      setCircuitConfiguration(stack, configuration);
      return stack;
   }

   public static void setCircuitConfiguration(ItemStack itemStack, int configuration) {
      if (!MetaItems.INTEGRATED_CIRCUIT.isItemEqual(itemStack)) {
         throw new IllegalArgumentException("Given item stack is not an integrated circuit!");
      } else if (configuration >= 0 && configuration <= 32) {
         NBTTagCompound tagCompound = itemStack.func_77978_p();
         if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
            itemStack.func_77982_d(tagCompound);
         }

         tagCompound.func_74768_a("Configuration", configuration);
      } else {
         throw new IllegalArgumentException("Given configuration number is out of range!");
      }
   }

   public static int getCircuitConfiguration(ItemStack itemStack) {
      if (!isIntegratedCircuit(itemStack)) {
         return 0;
      } else {
         NBTTagCompound tagCompound = itemStack.func_77978_p();
         return tagCompound != null ? tagCompound.func_74762_e("Configuration") : 0;
      }
   }

   public static boolean isIntegratedCircuit(ItemStack itemStack) {
      boolean isCircuit = MetaItems.INTEGRATED_CIRCUIT.isItemEqual(itemStack);
      if (isCircuit && !itemStack.func_77942_o()) {
         NBTTagCompound compound = new NBTTagCompound();
         compound.func_74768_a("Configuration", 0);
         itemStack.func_77982_d(compound);
      }

      return isCircuit;
   }

   public static void adjustConfiguration(PlayerInventoryHolder holder, int amount) {
      adjustConfiguration(holder.getCurrentItem(), amount);
      holder.markAsDirty();
   }

   public static void adjustConfiguration(ItemStack stack, int amount) {
      if (isIntegratedCircuit(stack)) {
         int configuration = getCircuitConfiguration(stack);
         configuration += amount;
         configuration = MathHelper.func_76125_a(configuration, 0, 32);
         setCircuitConfiguration(stack, configuration);
      }
   }
}
