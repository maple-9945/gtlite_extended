package gregtech.api.recipes.ingredients;

import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.util.GTLog;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

public abstract class GTRecipeInput {
   public static final int SORTING_ORDER_COMMON = 0;
   public static final int SORTING_ORDER_NC = 5;
   public static final int SORTING_ORDER_INT_CIRCUIT = 10;
   public static final Comparator<GTRecipeInput> RECIPE_INPUT_COMPARATOR = Comparator.comparingInt(GTRecipeInput::getSortingOrder);
   protected int amount;
   protected boolean isConsumable = true;
   protected NBTMatcher nbtMatcher;
   protected NBTCondition nbtCondition;
   private boolean cached;
   private int hash;
   protected boolean hashCached;

   /** @deprecated */
   @Deprecated
   public static GTRecipeInput getOrCreate(GTRecipeInput gtRecipeIngredient) {
      return gtRecipeIngredient;
   }

   public int getAmount() {
      return this.amount;
   }

   public boolean isCached() {
      return this.cached;
   }

   public void setCached() {
      this.cached = true;
   }

   protected abstract GTRecipeInput copy();

   public abstract GTRecipeInput copyWithAmount(int var1);

   public GTRecipeInput withAmount(int amount) {
      if (this.getAmount() == amount) {
         return this;
      } else if (this.isCached()) {
         return this.copyWithAmount(amount);
      } else {
         this.amount = amount;
         this.hashCached = false;
         return this;
      }
   }

   public GTRecipeInput setNonConsumable() {
      if (!this.isConsumable) {
         return this;
      } else {
         GTRecipeInput recipeInput = this.cached ? this.copy() : this;
         recipeInput.isConsumable = false;
         recipeInput.hashCached = false;
         return recipeInput;
      }
   }

   public GTRecipeInput setNBTMatchingCondition(NBTMatcher nbtMatcher, NBTCondition nbtCondition) {
      GTRecipeInput recipeInput = this.cached ? this.copy() : this;
      recipeInput.nbtMatcher = nbtMatcher;
      recipeInput.nbtCondition = nbtCondition;
      recipeInput.hashCached = false;
      return recipeInput;
   }

   public boolean hasNBTMatchingCondition() {
      return this.nbtMatcher != null;
   }

   public NBTMatcher getNBTMatcher() {
      return this.nbtMatcher;
   }

   public NBTCondition getNBTMatchingCondition() {
      return this.nbtCondition;
   }

   public boolean isNonConsumable() {
      return !this.isConsumable;
   }

   public ItemStack[] getInputStacks() {
      return null;
   }

   public FluidStack getInputFluidStack() {
      return null;
   }

   public boolean isOreDict() {
      return false;
   }

   public int getOreDict() {
      return -1;
   }

   public boolean acceptsStack(@Nullable ItemStack input) {
      return false;
   }

   public boolean acceptsFluid(@Nullable FluidStack input) {
      return false;
   }

   public int hashCode() {
      if (!this.hashCached) {
         this.hash = this.computeHash();
         this.hashCached = true;
      }

      return this.hash;
   }

   protected abstract int computeHash();

   public abstract boolean equals(Object var1);

   public abstract boolean equalIgnoreAmount(GTRecipeInput var1);

   public int getSortingOrder() {
      return this.isNonConsumable() ? 5 : 0;
   }

   public static NBTTagCompound writeToNBT(GTRecipeInput input) {
      NBTTagCompound tag = new NBTTagCompound();
      if (input instanceof GTRecipeItemInput) {
         NBTTagList stackList = new NBTTagList();

         for(ItemStack stack : input.getInputStacks()) {
            stackList.func_74742_a(stack.serializeNBT());
         }

         tag.func_74782_a("stacks", stackList);
      } else if (input instanceof GTRecipeOreInput) {
         tag.func_74768_a("ore", input.getOreDict());
      } else if (input instanceof GTRecipeFluidInput) {
         tag.func_74782_a("fluid", input.getInputFluidStack().writeToNBT(new NBTTagCompound()));
      }

      tag.func_74768_a("amount", input.getAmount());
      return tag;
   }

   public static GTRecipeInput readFromNBT(NBTTagCompound tag) {
      int amount = tag.func_74762_e("amount");
      if (tag.func_74764_b("stacks")) {
         NBTTagList list = tag.func_150295_c("stacks", 10);
         ItemStack[] stacks = new ItemStack[list.func_74745_c()];
         Arrays.setAll(stacks, (i) -> new ItemStack(list.func_150305_b(i)));
         return new GTRecipeItemInput(stacks, amount);
      } else if (tag.func_74764_b("ore")) {
         return new GTRecipeOreInput(tag.func_74762_e("ore"), amount);
      } else if (tag.func_74764_b("fluid")) {
         FluidStack stack = FluidStack.loadFluidStackFromNBT(tag.func_74775_l("fluid"));
         return new GTRecipeFluidInput((FluidStack)Objects.requireNonNull(stack), amount);
      } else {
         GTLog.logger.warn("unable to read tag!: " + tag);
         return null;
      }
   }
}
