package gregtech.api.recipes;

import com.google.common.collect.ImmutableList;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.recipes.category.GTRecipeCategory;
import gregtech.api.recipes.chance.boost.ChanceBoostFunction;
import gregtech.api.recipes.chance.output.ChancedOutputList;
import gregtech.api.recipes.chance.output.ChancedOutputLogic;
import gregtech.api.recipes.chance.output.impl.ChancedFluidOutput;
import gregtech.api.recipes.chance.output.impl.ChancedItemOutput;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.properties.RecipeProperty;
import gregtech.api.recipes.properties.RecipePropertyStorage;
import gregtech.api.util.GTUtility;
import gregtech.api.util.ItemStackHashStrategy;
import gregtech.integration.groovy.GroovyScriptModule;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;

public class Recipe {
   private final List<GTRecipeInput> inputs;
   private final List<ItemStack> outputs;
   private final ChancedOutputList<ItemStack, ChancedItemOutput> chancedOutputs;
   private final List<GTRecipeInput> fluidInputs;
   private final List<FluidStack> fluidOutputs;
   private final ChancedOutputList<FluidStack, ChancedFluidOutput> chancedFluidOutputs;
   private final int duration;
   private final long EUt;
   private final boolean hidden;
   private final GTRecipeCategory recipeCategory;
   private final boolean isCTRecipe;
   private final boolean groovyRecipe;
   private final RecipePropertyStorage recipePropertyStorage;
   private final int hashCode;

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   public static int getMaxChancedValue() {
      return ChancedOutputLogic.getMaxChancedValue();
   }

   public Recipe(@NotNull List<GTRecipeInput> inputs, List<ItemStack> outputs, @NotNull ChancedOutputList<ItemStack, ChancedItemOutput> chancedOutputs, List<GTRecipeInput> fluidInputs, List<FluidStack> fluidOutputs, @NotNull ChancedOutputList<FluidStack, ChancedFluidOutput> chancedFluidOutputs, int duration, long EUt, boolean hidden, boolean isCTRecipe, @NotNull RecipePropertyStorage recipePropertyStorage, @NotNull GTRecipeCategory recipeCategory) {
      this.recipePropertyStorage = recipePropertyStorage;
      this.inputs = GTRecipeInputCache.deduplicateInputs(inputs);
      if (outputs.isEmpty()) {
         this.outputs = Collections.emptyList();
      } else {
         this.outputs = new ArrayList(outputs);
      }

      this.chancedOutputs = chancedOutputs;
      this.chancedFluidOutputs = chancedFluidOutputs;
      this.fluidInputs = GTRecipeInputCache.deduplicateInputs(fluidInputs);
      this.fluidOutputs = (List<FluidStack>)(fluidOutputs.isEmpty() ? Collections.emptyList() : ImmutableList.copyOf(fluidOutputs));
      this.duration = duration;
      this.EUt = EUt;
      this.hidden = hidden;
      this.recipeCategory = recipeCategory;
      this.isCTRecipe = isCTRecipe;
      this.hashCode = this.makeHashCode();
      this.groovyRecipe = GroovyScriptModule.isCurrentlyRunning();
   }

   public @NotNull Recipe copy() {
      return new Recipe(this.inputs, this.outputs, this.chancedOutputs, this.fluidInputs, this.fluidOutputs, this.chancedFluidOutputs, this.duration, this.EUt, this.hidden, this.isCTRecipe, this.recipePropertyStorage, this.recipeCategory);
   }

   public static Recipe trimRecipeOutputs(Recipe currentRecipe, RecipeMap<?> recipeMap, int itemTrimLimit, int fluidTrimLimit) {
      if (itemTrimLimit == -1 && fluidTrimLimit == -1) {
         return currentRecipe;
      } else {
         currentRecipe = currentRecipe.copy();
         RecipeBuilder<?> builder = new RecipeBuilder(currentRecipe, recipeMap);
         builder.clearOutputs();
         builder.clearChancedOutput();
         builder.clearFluidOutputs();
         builder.clearChancedFluidOutputs();
         Pair<List<ItemStack>, List<ChancedItemOutput>> recipeOutputs = currentRecipe.getItemAndChanceOutputs(itemTrimLimit);
         builder.chancedOutputs((List)recipeOutputs.getRight());
         builder.outputs((Collection)recipeOutputs.getLeft());
         Pair<List<FluidStack>, List<ChancedFluidOutput>> recipeFluidOutputs = currentRecipe.getFluidAndChanceOutputs(fluidTrimLimit);
         builder.chancedFluidOutputs((List)recipeFluidOutputs.getRight());
         builder.fluidOutputs((Collection)recipeFluidOutputs.getLeft());
         return (Recipe)builder.build().getResult();
      }
   }

   public final boolean matches(boolean consumeIfSuccessful, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
      Pair<Boolean, int[]> fluids = null;
      Pair<Boolean, int[]> items = null;
      if (fluidInputs.getFluidTanks().size() > 0) {
         fluids = this.matchesFluid(GTUtility.fluidHandlerToList(fluidInputs));
         if (!(Boolean)fluids.getKey()) {
            return false;
         }
      }

      if (inputs.getSlots() > 0) {
         items = this.matchesItems(GTUtility.itemHandlerToList(inputs));
         if (!(Boolean)items.getKey()) {
            return false;
         }
      }

      if (consumeIfSuccessful) {
         if (fluids != null) {
            int[] fluidAmountInTank = (int[])fluids.getValue();
            List<IMultipleTankHandler.ITankEntry> backedList = fluidInputs.getFluidTanks();

            for(int i = 0; i < fluidAmountInTank.length; ++i) {
               IMultipleTankHandler.ITankEntry tank = (IMultipleTankHandler.ITankEntry)backedList.get(i);
               FluidStack fluidStack = tank.getFluid();
               int fluidAmount = fluidAmountInTank[i];
               if (fluidStack != null && fluidStack.amount != fluidAmount) {
                  tank.drain(Math.abs(fluidAmount - fluidStack.amount), true);
               }
            }
         }

         if (items != null) {
            int[] itemAmountInSlot = (int[])items.getValue();

            for(int i = 0; i < itemAmountInSlot.length; ++i) {
               ItemStack itemInSlot = inputs.getStackInSlot(i);
               int itemAmount = itemAmountInSlot[i];
               if (!itemInSlot.func_190926_b() && itemInSlot.func_190916_E() != itemAmount) {
                  inputs.extractItem(i, Math.abs(itemAmount - itemInSlot.func_190916_E()), false);
               }
            }
         }
      }

      return true;
   }

   public boolean matches(boolean consumeIfSuccessful, List<ItemStack> inputs, List<FluidStack> fluidInputs) {
      if (inputs.size() == 0 && fluidInputs.size() == 0) {
         return false;
      } else {
         Pair<Boolean, int[]> fluids = this.matchesFluid(fluidInputs);
         if (!(Boolean)fluids.getKey()) {
            return false;
         } else {
            Pair<Boolean, int[]> items = this.matchesItems(inputs);
            if (!(Boolean)items.getKey()) {
               return false;
            } else {
               if (consumeIfSuccessful) {
                  int[] fluidAmountInTank = (int[])fluids.getValue();

                  for(int i = 0; i < fluidAmountInTank.length; ++i) {
                     FluidStack fluidStack = (FluidStack)fluidInputs.get(i);
                     int fluidAmount = fluidAmountInTank[i];
                     if (fluidStack != null && fluidStack.amount != fluidAmount) {
                        fluidStack.amount = fluidAmount;
                        if (fluidStack.amount == 0) {
                           fluidInputs.set(i, (Object)null);
                        }
                     }
                  }

                  int[] itemAmountInSlot = (int[])items.getValue();

                  for(int i = 0; i < itemAmountInSlot.length; ++i) {
                     ItemStack itemInSlot = (ItemStack)inputs.get(i);
                     int itemAmount = itemAmountInSlot[i];
                     if (!itemInSlot.func_190926_b() && itemInSlot.func_190916_E() != itemAmount) {
                        itemInSlot.func_190920_e(itemAmountInSlot[i]);
                     }
                  }
               }

               return true;
            }
         }
      }
   }

   private Pair<Boolean, int[]> matchesItems(List<ItemStack> inputs) {
      int[] itemAmountInSlot = new int[inputs.size()];
      int indexed = 0;

      for(GTRecipeInput ingredient : this.inputs) {
         int ingredientAmount = ingredient.getAmount();

         for(int j = 0; j < inputs.size(); ++j) {
            ItemStack inputStack = (ItemStack)inputs.get(j);
            if (j == indexed) {
               itemAmountInSlot[j] = inputStack.func_190926_b() ? 0 : inputStack.func_190916_E();
               ++indexed;
            }

            if (!inputStack.func_190926_b() && ingredient.acceptsStack(inputStack)) {
               int itemAmountToConsume = Math.min(itemAmountInSlot[j], ingredientAmount);
               ingredientAmount -= itemAmountToConsume;
               if (!ingredient.isNonConsumable()) {
                  itemAmountInSlot[j] -= itemAmountToConsume;
               }

               if (ingredientAmount == 0) {
                  break;
               }
            }
         }

         if (ingredientAmount > 0) {
            return Pair.of(false, itemAmountInSlot);
         }
      }

      int[] retItemAmountInSlot = new int[indexed];
      System.arraycopy(itemAmountInSlot, 0, retItemAmountInSlot, 0, indexed);
      return Pair.of(true, retItemAmountInSlot);
   }

   private Pair<Boolean, int[]> matchesFluid(List<FluidStack> fluidInputs) {
      int[] fluidAmountInTank = new int[fluidInputs.size()];
      int indexed = 0;

      for(GTRecipeInput fluid : this.fluidInputs) {
         int fluidAmount = fluid.getAmount();

         for(int j = 0; j < fluidInputs.size(); ++j) {
            FluidStack tankFluid = (FluidStack)fluidInputs.get(j);
            if (j == indexed) {
               ++indexed;
               fluidAmountInTank[j] = tankFluid == null ? 0 : tankFluid.amount;
            }

            if (tankFluid != null && fluid.acceptsFluid(tankFluid)) {
               int fluidAmountToConsume = Math.min(fluidAmountInTank[j], fluidAmount);
               fluidAmount -= fluidAmountToConsume;
               if (!fluid.isNonConsumable()) {
                  fluidAmountInTank[j] -= fluidAmountToConsume;
               }

               if (fluidAmount == 0) {
                  break;
               }
            }
         }

         if (fluidAmount > 0) {
            return Pair.of(false, fluidAmountInTank);
         }
      }

      int[] retfluidAmountInTank = new int[indexed];
      System.arraycopy(fluidAmountInTank, 0, retfluidAmountInTank, 0, indexed);
      return Pair.of(true, retfluidAmountInTank);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         Recipe recipe = (Recipe)o;
         return this.hasSameInputs(recipe) && this.hasSameFluidInputs(recipe);
      } else {
         return false;
      }
   }

   private int makeHashCode() {
      int hash = 31 * this.hashInputs();
      hash = 31 * hash + hashFluidList(this.fluidInputs);
      return hash;
   }

   public int hashCode() {
      return this.hashCode;
   }

   private int hashInputs() {
      int hash = 0;

      for(GTRecipeInput recipeIngredient : this.inputs) {
         if (!recipeIngredient.isOreDict()) {
            for(ItemStack is : recipeIngredient.getInputStacks()) {
               hash = 31 * hash + ItemStackHashStrategy.comparingAll().hashCode(is);
            }
         } else {
            hash = 31 * hash + recipeIngredient.getOreDict();
         }
      }

      return hash;
   }

   private boolean hasSameInputs(Recipe otherRecipe) {
      List<ItemStack> otherStackList = new ObjectArrayList(otherRecipe.inputs.size());

      for(GTRecipeInput otherInputs : otherRecipe.inputs) {
         otherStackList.addAll(Arrays.asList(otherInputs.getInputStacks()));
      }

      if (!(Boolean)this.matchesItems(otherStackList).getLeft()) {
         return false;
      } else {
         List<ItemStack> thisStackList = new ObjectArrayList(this.inputs.size());

         for(GTRecipeInput thisInputs : this.inputs) {
            thisStackList.addAll(Arrays.asList(thisInputs.getInputStacks()));
         }

         return (Boolean)otherRecipe.matchesItems(thisStackList).getLeft();
      }
   }

   public static int hashFluidList(@NotNull List<GTRecipeInput> fluids) {
      int hash = 0;

      for(GTRecipeInput fluidInput : fluids) {
         hash = 31 * hash + fluidInput.hashCode();
      }

      return hash;
   }

   private boolean hasSameFluidInputs(Recipe otherRecipe) {
      List<FluidStack> otherFluidList = new ObjectArrayList(otherRecipe.fluidInputs.size());

      for(GTRecipeInput otherInputs : otherRecipe.fluidInputs) {
         FluidStack fluidStack = otherInputs.getInputFluidStack();
         otherFluidList.add(fluidStack);
      }

      if (!(Boolean)this.matchesFluid(otherFluidList).getLeft()) {
         return false;
      } else {
         List<FluidStack> thisFluidsList = new ObjectArrayList(this.fluidInputs.size());

         for(GTRecipeInput thisFluidInputs : this.fluidInputs) {
            FluidStack fluidStack = thisFluidInputs.getInputFluidStack();
            thisFluidsList.add(fluidStack);
         }

         return (Boolean)otherRecipe.matchesFluid(thisFluidsList).getLeft();
      }
   }

   public String toString() {
      return (new ToStringBuilder(this)).append("inputs", this.inputs).append("outputs", this.outputs).append("chancedOutputs", this.chancedOutputs).append("fluidInputs", this.fluidInputs).append("fluidOutputs", this.fluidOutputs).append("duration", this.duration).append("EUt", this.EUt).append("hidden", this.hidden).append("CTRecipe", this.isCTRecipe).append("GSRecipe", this.groovyRecipe).toString();
   }

   public List<GTRecipeInput> getInputs() {
      return this.inputs;
   }

   public List<ItemStack> getOutputs() {
      return this.outputs;
   }

   public List<ItemStack> getResultItemOutputs(int recipeTier, int machineTier, RecipeMap<?> recipeMap) {
      List<ItemStack> outputs = new ArrayList(this.getOutputs());
      ChanceBoostFunction function = recipeMap.getChanceFunction();
      List<ChancedItemOutput> chancedOutputsList = this.getChancedOutputs().roll(function, recipeTier, machineTier);
      if (chancedOutputsList == null) {
         return outputs;
      } else {
         Collection<ItemStack> resultChanced = new ArrayList();

         for(ChancedItemOutput chancedOutput : chancedOutputsList) {
            ItemStack stackToAdd = ((ItemStack)chancedOutput.getIngredient()).func_77946_l();

            for(ItemStack stackInList : resultChanced) {
               int insertable = stackInList.func_77976_d() - stackInList.func_190916_E();
               if (insertable > 0 && ItemHandlerHelper.canItemStacksStack(stackInList, stackToAdd)) {
                  if (insertable >= stackToAdd.func_190916_E()) {
                     stackInList.func_190917_f(stackToAdd.func_190916_E());
                     stackToAdd = ItemStack.field_190927_a;
                     break;
                  }

                  stackInList.func_190917_f(insertable);
                  stackToAdd.func_190918_g(insertable);
               }
            }

            if (!stackToAdd.func_190926_b()) {
               resultChanced.add(stackToAdd);
            }
         }

         outputs.addAll(resultChanced);
         return outputs;
      }
   }

   public Pair<List<ItemStack>, List<ChancedItemOutput>> getItemAndChanceOutputs(int outputLimit) {
      List<ItemStack> outputs = new ArrayList();
      List<ChancedItemOutput> chancedOutputs = new ArrayList(this.getChancedOutputs().getChancedEntries());
      if (outputLimit == -1) {
         outputs.addAll(GTUtility.copyStackList(this.getOutputs()));
      } else if (this.getOutputs().size() >= outputLimit) {
         outputs.addAll(GTUtility.copyStackList(this.getOutputs()).subList(0, Math.min(outputLimit, this.getOutputs().size())));
         chancedOutputs.clear();
      } else if (!this.getOutputs().isEmpty() && this.getOutputs().size() + chancedOutputs.size() >= outputLimit) {
         outputs.addAll(GTUtility.copyStackList(this.getOutputs()));
         int numChanced = outputLimit - this.getOutputs().size();
         chancedOutputs = chancedOutputs.subList(0, Math.min(numChanced, chancedOutputs.size()));
      } else if (this.getOutputs().isEmpty()) {
         chancedOutputs = chancedOutputs.subList(0, Math.min(outputLimit, chancedOutputs.size()));
      } else {
         outputs.addAll(GTUtility.copyStackList(this.getOutputs()));
      }

      return Pair.of(outputs, chancedOutputs);
   }

   public List<ItemStack> getAllItemOutputs() {
      List<ItemStack> recipeOutputs = new ArrayList(this.outputs);

      for(ChancedItemOutput entry : this.chancedOutputs.getChancedEntries()) {
         recipeOutputs.add(((ItemStack)entry.getIngredient()).func_77946_l());
      }

      return recipeOutputs;
   }

   public ChancedOutputList<ItemStack, ChancedItemOutput> getChancedOutputs() {
      return this.chancedOutputs;
   }

   public List<GTRecipeInput> getFluidInputs() {
      return this.fluidInputs;
   }

   public ChancedOutputList<FluidStack, ChancedFluidOutput> getChancedFluidOutputs() {
      return this.chancedFluidOutputs;
   }

   public boolean hasInputFluid(FluidStack fluid) {
      for(GTRecipeInput fluidInput : this.fluidInputs) {
         FluidStack fluidStack = fluidInput.getInputFluidStack();
         if (fluid.getFluid() == fluidStack.getFluid()) {
            return fluidStack.isFluidEqual(fluid);
         }
      }

      return false;
   }

   public List<FluidStack> getFluidOutputs() {
      return this.fluidOutputs;
   }

   public Pair<List<FluidStack>, List<ChancedFluidOutput>> getFluidAndChanceOutputs(int outputLimit) {
      List<FluidStack> outputs = new ArrayList();
      List<ChancedFluidOutput> chancedOutputs = new ArrayList(this.getChancedFluidOutputs().getChancedEntries());
      if (outputLimit == -1) {
         outputs.addAll(GTUtility.copyFluidList(this.getFluidOutputs()));
      } else if (this.getFluidOutputs().size() >= outputLimit) {
         outputs.addAll(GTUtility.copyFluidList(this.getFluidOutputs()).subList(0, Math.min(outputLimit, this.getFluidOutputs().size())));
         chancedOutputs.clear();
      } else if (!this.getFluidOutputs().isEmpty() && this.getFluidOutputs().size() + chancedOutputs.size() >= outputLimit) {
         outputs.addAll(GTUtility.copyFluidList(this.getFluidOutputs()));
         int numChanced = outputLimit - this.getFluidOutputs().size();
         chancedOutputs = chancedOutputs.subList(0, Math.min(numChanced, chancedOutputs.size()));
      } else if (this.getFluidOutputs().isEmpty()) {
         chancedOutputs = chancedOutputs.subList(0, Math.min(outputLimit, chancedOutputs.size()));
      } else {
         outputs.addAll(GTUtility.copyFluidList(this.getFluidOutputs()));
      }

      return Pair.of(outputs, chancedOutputs);
   }

   public List<FluidStack> getAllFluidOutputs() {
      List<FluidStack> recipeOutputs = new ArrayList(this.fluidOutputs);

      for(ChancedFluidOutput entry : this.chancedFluidOutputs.getChancedEntries()) {
         recipeOutputs.add(((FluidStack)entry.getIngredient()).copy());
      }

      return recipeOutputs;
   }

   public List<FluidStack> getResultFluidOutputs(int recipeTier, int machineTier, RecipeMap<?> recipeMap) {
      List<FluidStack> outputs = new ArrayList(GTUtility.copyFluidList(this.getFluidOutputs()));
      ChanceBoostFunction function = recipeMap.getChanceFunction();
      List<ChancedFluidOutput> chancedOutputsList = this.getChancedFluidOutputs().roll(function, recipeTier, machineTier);
      if (chancedOutputsList == null) {
         return outputs;
      } else {
         Collection<FluidStack> resultChanced = new ArrayList();

         for(ChancedFluidOutput chancedOutput : chancedOutputsList) {
            FluidStack stackToAdd = ((FluidStack)chancedOutput.getIngredient()).copy();

            for(FluidStack stackInList : resultChanced) {
               int insertable = stackInList.amount;
               if (insertable > 0 && stackInList.getFluid() == stackToAdd.getFluid()) {
                  stackInList.amount += stackToAdd.amount;
                  stackToAdd = null;
                  break;
               }
            }

            if (stackToAdd != null) {
               resultChanced.add(stackToAdd);
            }
         }

         outputs.addAll(resultChanced);
         return outputs;
      }
   }

   public int getDuration() {
      return this.duration;
   }

   public long getEUt() {
      return this.EUt;
   }

   public boolean isHidden() {
      return this.hidden;
   }

   public boolean getIsCTRecipe() {
      return this.isCTRecipe;
   }

   public boolean isGroovyRecipe() {
      return this.groovyRecipe;
   }

   public boolean hasValidInputsForDisplay() {
      for(GTRecipeInput ingredient : this.inputs) {
         if (ingredient.isOreDict()) {
            if (OreDictionary.getOres(OreDictionary.getOreName(ingredient.getOreDict())).stream().anyMatch((s) -> !s.func_190926_b())) {
               return true;
            }
         } else if (Arrays.stream(ingredient.getInputStacks()).anyMatch((s) -> !s.func_190926_b())) {
            return true;
         }
      }

      for(GTRecipeInput fluidInput : this.fluidInputs) {
         FluidStack fluidIngredient = fluidInput.getInputFluidStack();
         if (fluidIngredient != null && fluidIngredient.amount > 0) {
            return true;
         }
      }

      return false;
   }

   public @NotNull GTRecipeCategory getRecipeCategory() {
      return this.recipeCategory;
   }

   @Contract("_, !null -> !null")
   public <T> @Nullable T getProperty(@NotNull RecipeProperty<T> property, @Nullable T defaultValue) {
      return (T)this.recipePropertyStorage.get(property, defaultValue);
   }

   public boolean hasProperty(@NotNull RecipeProperty<?> property) {
      return this.recipePropertyStorage.contains(property);
   }

   public @NotNull RecipePropertyStorage propertyStorage() {
      return this.recipePropertyStorage;
   }
}
