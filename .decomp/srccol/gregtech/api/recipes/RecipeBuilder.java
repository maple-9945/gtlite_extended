package gregtech.api.recipes;

import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.helper.ingredient.OreDictIngredient;
import crafttweaker.CraftTweakerAPI;
import gregtech.api.GregTechAPI;
import gregtech.api.fluids.store.FluidStorageKey;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.category.GTRecipeCategory;
import gregtech.api.recipes.chance.output.ChancedOutputList;
import gregtech.api.recipes.chance.output.ChancedOutputLogic;
import gregtech.api.recipes.chance.output.impl.ChancedFluidOutput;
import gregtech.api.recipes.chance.output.impl.ChancedItemOutput;
import gregtech.api.recipes.ingredients.GTRecipeFluidInput;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.GTRecipeOreInput;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.properties.RecipeProperty;
import gregtech.api.recipes.properties.RecipePropertyStorage;
import gregtech.api.recipes.properties.RecipePropertyStorageImpl;
import gregtech.api.recipes.properties.impl.CleanroomProperty;
import gregtech.api.recipes.properties.impl.DimensionProperty;
import gregtech.api.recipes.properties.impl.DimensionProperty.DimensionPropertyList;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.RecyclingData;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import gregtech.api.util.GTUtility;
import gregtech.api.util.ValidationResult;
import gregtech.common.ConfigHolder;
import gregtech.integration.groovy.GroovyScriptModule;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Optional.Method;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Obsolete;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;

public class RecipeBuilder<R extends RecipeBuilder<R>> {
   protected RecipeMap<R> recipeMap;
   protected final List<GTRecipeInput> inputs;
   protected final List<ItemStack> outputs;
   protected final List<ChancedItemOutput> chancedOutputs;
   protected final List<GTRecipeInput> fluidInputs;
   protected final List<FluidStack> fluidOutputs;
   protected final List<ChancedFluidOutput> chancedFluidOutputs;
   protected ChancedOutputLogic chancedOutputLogic;
   protected ChancedOutputLogic chancedFluidOutputLogic;
   protected int duration;
   protected long EUt;
   protected boolean hidden;
   protected GTRecipeCategory category;
   protected boolean isCTRecipe;
   protected int parallel;
   protected EnumValidationResult recipeStatus;
   protected RecipePropertyStorage recipePropertyStorage;
   protected boolean recipePropertyStorageErrored;
   protected boolean ignoreAllBuildActions;
   protected Map<ResourceLocation, RecipeBuildAction<R>> ignoredBuildActions;
   private boolean withItemRecycling;

   protected RecipeBuilder() {
      this.chancedOutputLogic = ChancedOutputLogic.OR;
      this.chancedFluidOutputLogic = ChancedOutputLogic.OR;
      this.hidden = false;
      this.isCTRecipe = false;
      this.parallel = 0;
      this.recipeStatus = EnumValidationResult.VALID;
      this.recipePropertyStorage = RecipePropertyStorage.EMPTY;
      this.recipePropertyStorageErrored = false;
      this.ignoreAllBuildActions = false;
      this.inputs = new ArrayList();
      this.outputs = new ArrayList();
      this.chancedOutputs = new ArrayList();
      this.fluidInputs = new ArrayList();
      this.fluidOutputs = new ArrayList();
      this.chancedFluidOutputs = new ArrayList();
   }

   public RecipeBuilder(Recipe recipe, RecipeMap<R> recipeMap) {
      this.chancedOutputLogic = ChancedOutputLogic.OR;
      this.chancedFluidOutputLogic = ChancedOutputLogic.OR;
      this.hidden = false;
      this.isCTRecipe = false;
      this.parallel = 0;
      this.recipeStatus = EnumValidationResult.VALID;
      this.recipePropertyStorage = RecipePropertyStorage.EMPTY;
      this.recipePropertyStorageErrored = false;
      this.ignoreAllBuildActions = false;
      this.recipeMap = recipeMap;
      this.inputs = new ArrayList(recipe.getInputs());
      this.outputs = new ArrayList(recipe.getOutputs());
      this.chancedOutputs = new ArrayList(recipe.getChancedOutputs().getChancedEntries());
      this.fluidInputs = new ArrayList(recipe.getFluidInputs());
      this.fluidOutputs = GTUtility.copyFluidList(recipe.getFluidOutputs());
      this.chancedFluidOutputs = new ArrayList(recipe.getChancedFluidOutputs().getChancedEntries());
      this.duration = recipe.getDuration();
      this.EUt = recipe.getEUt();
      this.hidden = recipe.isHidden();
      this.category = recipe.getRecipeCategory();
      this.recipePropertyStorage = recipe.propertyStorage().copy();
   }

   protected RecipeBuilder(RecipeBuilder<R> recipeBuilder) {
      this.chancedOutputLogic = ChancedOutputLogic.OR;
      this.chancedFluidOutputLogic = ChancedOutputLogic.OR;
      this.hidden = false;
      this.isCTRecipe = false;
      this.parallel = 0;
      this.recipeStatus = EnumValidationResult.VALID;
      this.recipePropertyStorage = RecipePropertyStorage.EMPTY;
      this.recipePropertyStorageErrored = false;
      this.ignoreAllBuildActions = false;
      this.recipeMap = recipeBuilder.recipeMap;
      this.inputs = new ArrayList(recipeBuilder.getInputs());
      this.outputs = new ArrayList(recipeBuilder.getOutputs());
      this.chancedOutputs = new ArrayList(recipeBuilder.chancedOutputs);
      this.fluidInputs = new ArrayList(recipeBuilder.getFluidInputs());
      this.fluidOutputs = GTUtility.copyFluidList(recipeBuilder.getFluidOutputs());
      this.chancedFluidOutputs = new ArrayList(recipeBuilder.chancedFluidOutputs);
      this.chancedOutputLogic = recipeBuilder.chancedOutputLogic;
      this.chancedFluidOutputLogic = recipeBuilder.chancedFluidOutputLogic;
      this.duration = recipeBuilder.duration;
      this.EUt = recipeBuilder.EUt;
      this.hidden = recipeBuilder.hidden;
      this.category = recipeBuilder.category;
      this.recipePropertyStorage = recipeBuilder.recipePropertyStorage.copy();
      this.ignoreAllBuildActions = recipeBuilder.ignoreAllBuildActions;
      if (recipeBuilder.ignoredBuildActions != null) {
         this.ignoredBuildActions = new Object2ObjectOpenHashMap(recipeBuilder.ignoredBuildActions);
      }

      this.withItemRecycling = recipeBuilder.hasItemRecycling();
   }

   public R cleanroom(@Nullable CleanroomType cleanroom) {
      if (ConfigHolder.machines.enableCleanroom && cleanroom != null) {
         this.applyProperty(CleanroomProperty.getInstance(), cleanroom);
      }

      return (R)this;
   }

   public R dimension(int dimensionID) {
      return (R)this.dimension(dimensionID, false);
   }

   public R dimension(int dimensionID, boolean toBlackList) {
      DimensionProperty.DimensionPropertyList dimensionIDs = this.getCompleteDimensionIDs();
      if (dimensionIDs == null) {
         dimensionIDs = new DimensionProperty.DimensionPropertyList();
         this.applyProperty(DimensionProperty.getInstance(), dimensionIDs);
      }

      dimensionIDs.add(dimensionID, toBlackList);
      return (R)this;
   }

   public DimensionProperty.@Nullable DimensionPropertyList getCompleteDimensionIDs() {
      return (DimensionProperty.DimensionPropertyList)this.recipePropertyStorage.get(DimensionProperty.getInstance(), (Object)null);
   }

   public @NotNull IntList getDimensionIDs() {
      return ((DimensionProperty.DimensionPropertyList)this.recipePropertyStorage.get(DimensionProperty.getInstance(), DimensionPropertyList.EMPTY_LIST)).whiteListDimensions;
   }

   public @NotNull IntList getBlockedDimensionIDs() {
      return ((DimensionProperty.DimensionPropertyList)this.recipePropertyStorage.get(DimensionProperty.getInstance(), DimensionPropertyList.EMPTY_LIST)).blackListDimensions;
   }

   @MustBeInvokedByOverriders
   public boolean applyPropertyCT(@NotNull String key, @NotNull Object value) {
      if (key.equals("dimension")) {
         if (value instanceof DimensionProperty.DimensionPropertyList) {
            DimensionProperty.DimensionPropertyList list = (DimensionProperty.DimensionPropertyList)value;
            DimensionProperty.DimensionPropertyList dimensionIDs = this.getCompleteDimensionIDs();
            if (dimensionIDs == null) {
               dimensionIDs = new DimensionProperty.DimensionPropertyList();
               this.applyProperty(DimensionProperty.getInstance(), dimensionIDs);
            }

            dimensionIDs.merge(list);
            return true;
         } else {
            return false;
         }
      } else if (key.equals("cleanroom")) {
         if (value instanceof CleanroomType) {
            this.cleanroom((CleanroomType)value);
         } else {
            if (!(value instanceof String)) {
               return false;
            }

            this.cleanroom(CleanroomType.getByName((String)value));
         }

         return true;
      } else {
         return false;
      }
   }

   public final boolean applyProperty(@NotNull RecipeProperty<?> property, @NotNull Object value) {
      if (this.recipePropertyStorage == RecipePropertyStorage.EMPTY) {
         this.recipePropertyStorage = new RecipePropertyStorageImpl();
      }

      boolean stored = this.recipePropertyStorage.store(property, value);
      if (!stored) {
         this.recipePropertyStorageErrored = true;
      }

      return stored;
   }

   public R input(GTRecipeInput input) {
      if (input.getAmount() < 0) {
         GTLog.logger.error("Count cannot be less than 0. Actual: {}.", input.getAmount(), new Throwable());
      } else {
         this.inputs.add(input);
      }

      return (R)this;
   }

   public R input(String oredict) {
      return (R)this.input((GTRecipeInput)(new GTRecipeOreInput(oredict)));
   }

   public R input(String oredict, int count) {
      return (R)this.input((GTRecipeInput)(new GTRecipeOreInput(oredict, count)));
   }

   public R input(OrePrefix orePrefix, Material material) {
      return (R)this.input((GTRecipeInput)(new GTRecipeOreInput(orePrefix, material)));
   }

   public R input(OrePrefix orePrefix, Material material, int count) {
      return (R)this.input((GTRecipeInput)(new GTRecipeOreInput(orePrefix, material, count)));
   }

   public R input(Item item) {
      return (R)this.input((GTRecipeInput)(new GTRecipeItemInput(new ItemStack(item))));
   }

   public R input(Item item, int count) {
      return (R)this.input((GTRecipeInput)(new GTRecipeItemInput(new ItemStack(item), count)));
   }

   public R input(Item item, int count, int meta) {
      return (R)this.input((GTRecipeInput)(new GTRecipeItemInput(new ItemStack(item, count, meta))));
   }

   public R input(Item item, int count, boolean wild) {
      return (R)this.input((GTRecipeInput)(new GTRecipeItemInput(new ItemStack(item, count, 32767))));
   }

   public R input(Block block) {
      return (R)this.input((Block)block, 1);
   }

   public R input(Block block, int count) {
      return (R)this.input((GTRecipeInput)(new GTRecipeItemInput(new ItemStack(block, count))));
   }

   public R input(Block block, int count, int meta) {
      return (R)this.input((GTRecipeInput)(new GTRecipeItemInput(new ItemStack(block, count, meta))));
   }

   public R input(Block block, int count, boolean wild) {
      return (R)this.input((GTRecipeInput)(new GTRecipeItemInput(new ItemStack(block, count, 32767))));
   }

   public R input(MetaItem<?>.MetaValueItem item, int count) {
      return (R)this.input((GTRecipeInput)(new GTRecipeItemInput(item.getStackForm(count))));
   }

   public R input(MetaItem<?>.MetaValueItem item) {
      return (R)this.input((GTRecipeInput)(new GTRecipeItemInput(item.getStackForm())));
   }

   public R input(MetaTileEntity mte) {
      return (R)this.input((GTRecipeInput)(new GTRecipeItemInput(mte.getStackForm())));
   }

   public R input(MetaTileEntity mte, int amount) {
      return (R)this.input((GTRecipeInput)(new GTRecipeItemInput(mte.getStackForm(amount))));
   }

   public R inputNBT(GTRecipeInput input, NBTMatcher matcher, NBTCondition condition) {
      if (input.getAmount() < 0) {
         GTLog.logger.error("Count cannot be less than 0. Actual: {}.", input.getAmount(), new Throwable());
         return (R)this;
      } else if (matcher == null) {
         GTLog.logger.error("NBTMatcher must not be null", new Throwable());
         return (R)this;
      } else if (condition == null) {
         GTLog.logger.error("NBTCondition must not be null", new Throwable());
         return (R)this;
      } else {
         this.inputs.add(input.setNBTMatchingCondition(matcher, condition));
         return (R)this;
      }
   }

   public R inputNBT(String oredict, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT((GTRecipeInput)(new GTRecipeOreInput(oredict)), matcher, condition);
   }

   public R inputNBT(String oredict, int count, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT((GTRecipeInput)(new GTRecipeOreInput(oredict, count)), matcher, condition);
   }

   public R inputNBT(OrePrefix orePrefix, Material material, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT((GTRecipeInput)(new GTRecipeOreInput(orePrefix, material)), matcher, condition);
   }

   public R inputNBT(OrePrefix orePrefix, Material material, int count, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT((GTRecipeInput)(new GTRecipeOreInput(orePrefix, material, count)), matcher, condition);
   }

   public R inputNBT(Item item, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT(new ItemStack(item), matcher, condition);
   }

   public R inputNBT(Item item, int count, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT(new ItemStack(item, count), matcher, condition);
   }

   public R inputNBT(Item item, int count, int meta, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT(new ItemStack(item, count, meta), matcher, condition);
   }

   public R inputNBT(Item item, int count, boolean wild, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT(new ItemStack(item, count, 32767), matcher, condition);
   }

   public R inputNBT(Block block, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT((Block)block, 1, matcher, condition);
   }

   public R inputNBT(Block block, int count, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT(new ItemStack(block, count), matcher, condition);
   }

   public R inputNBT(Block block, int count, boolean wild, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT(new ItemStack(block, count, 32767), matcher, condition);
   }

   public R inputNBT(MetaItem<?>.MetaValueItem item, int count, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT(item.getStackForm(count), matcher, condition);
   }

   public R inputNBT(MetaItem<?>.MetaValueItem item, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT(item.getStackForm(), matcher, condition);
   }

   public R inputNBT(MetaTileEntity mte, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT(mte.getStackForm(), matcher, condition);
   }

   public R inputNBT(MetaTileEntity mte, int amount, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT(mte.getStackForm(amount), matcher, condition);
   }

   public R inputNBT(@NotNull ItemStack stack, NBTMatcher matcher, NBTCondition condition) {
      return (R)this.inputNBT((GTRecipeInput)(new GTRecipeItemInput(stack)), matcher, condition);
   }

   public R inputs(ItemStack input) {
      if (input != null && !input.func_190926_b()) {
         this.inputs.add(new GTRecipeItemInput(input));
      } else {
         GTLog.logger.error("Input cannot be null or empty. Input: {}", input, new Throwable());
         this.recipeStatus = EnumValidationResult.INVALID;
      }

      return (R)this;
   }

   public R inputs(ItemStack... inputs) {
      for(ItemStack input : inputs) {
         if (input != null && !input.func_190926_b()) {
            this.inputs.add(new GTRecipeItemInput(input));
         } else {
            GTLog.logger.error("Inputs cannot contain null or empty ItemStacks. Inputs: {}", input, new Throwable());
            this.recipeStatus = EnumValidationResult.INVALID;
         }
      }

      return (R)this;
   }

   public R inputStacks(Collection<ItemStack> inputs) {
      for(ItemStack input : inputs) {
         if (input != null && !input.func_190926_b()) {
            this.inputs.add(new GTRecipeItemInput(input));
         } else {
            GTLog.logger.error("Input cannot contain null or empty ItemStacks. Inputs: {}", input, new Throwable());
            this.recipeStatus = EnumValidationResult.INVALID;
         }
      }

      return (R)this;
   }

   public R inputs(GTRecipeInput input) {
      if (input.getAmount() < 0) {
         GTLog.logger.error("Input count cannot be less than 0. Actual: {}.", input.getAmount(), new Throwable());
         this.recipeStatus = EnumValidationResult.INVALID;
      } else {
         this.inputs.add(input);
      }

      return (R)this;
   }

   public R inputs(GTRecipeInput... inputs) {
      for(GTRecipeInput input : inputs) {
         if (input.getAmount() < 0) {
            GTLog.logger.error("Input count cannot be less than 0. Actual: {}.", input.getAmount(), new Throwable());
            this.recipeStatus = EnumValidationResult.INVALID;
         } else {
            this.inputs.add(input);
         }
      }

      return (R)this;
   }

   public R inputIngredients(Collection<GTRecipeInput> inputs) {
      for(GTRecipeInput input : inputs) {
         if (input.getAmount() < 0) {
            GTLog.logger.error("Count cannot be less than 0. Actual: {}.", input.getAmount(), new Throwable());
            this.recipeStatus = EnumValidationResult.INVALID;
         } else {
            this.inputs.add(input);
         }
      }

      return (R)this;
   }

   public R clearInputs() {
      this.inputs.clear();
      return (R)this;
   }

   public R notConsumable(GTRecipeInput gtRecipeIngredient) {
      return (R)this.input(gtRecipeIngredient.setNonConsumable());
   }

   public R notConsumable(ItemStack itemStack) {
      return (R)this.input((new GTRecipeItemInput(itemStack)).setNonConsumable());
   }

   public R notConsumable(OrePrefix prefix, Material material, int amount) {
      return (R)this.input((new GTRecipeOreInput(prefix, material, amount)).setNonConsumable());
   }

   public R notConsumable(OrePrefix prefix, Material material) {
      return (R)this.notConsumable(prefix, material, 1);
   }

   public R notConsumable(MetaItem<?>.MetaValueItem item) {
      return (R)this.input((new GTRecipeItemInput(item.getStackForm(), 1)).setNonConsumable());
   }

   public R notConsumable(Fluid fluid, int amount) {
      return (R)this.fluidInputs((new GTRecipeFluidInput(fluid, amount)).setNonConsumable());
   }

   public R notConsumable(Fluid fluid) {
      return (R)this.fluidInputs((new GTRecipeFluidInput(fluid, 1)).setNonConsumable());
   }

   public R notConsumable(FluidStack fluidStack) {
      return (R)this.fluidInputs((new GTRecipeFluidInput(fluidStack)).setNonConsumable());
   }

   public R circuitMeta(int circuitNumber) {
      if (0 <= circuitNumber && circuitNumber <= 32) {
         return (R)this.input((GTRecipeInput)IntCircuitIngredient.circuitInput(circuitNumber));
      } else {
         GTLog.logger.error("Integrated Circuit Number cannot be less than {} and more than {}", 0, 32, new Throwable());
         this.recipeStatus = EnumValidationResult.INVALID;
         return (R)this;
      }
   }

   public R output(OrePrefix orePrefix, Material material) {
      return (R)this.outputs(OreDictUnifier.get(orePrefix, material, 1));
   }

   public R output(OrePrefix orePrefix, Material material, int count) {
      return (R)this.outputs(OreDictUnifier.get(orePrefix, material, count));
   }

   public R output(Item item) {
      return (R)this.output((Item)item, 1);
   }

   public R output(Item item, int count) {
      return (R)this.outputs(new ItemStack(item, count));
   }

   public R output(Item item, int count, int meta) {
      return (R)this.outputs(new ItemStack(item, count, meta));
   }

   public R output(Block item) {
      return (R)this.output((Block)item, 1);
   }

   public R output(Block item, int count) {
      return (R)this.outputs(new ItemStack(item, count));
   }

   public R output(Block item, int count, int meta) {
      return (R)this.outputs(new ItemStack(item, count, meta));
   }

   public R output(MetaItem<?>.MetaValueItem item, int count) {
      return (R)this.outputs(item.getStackForm(count));
   }

   public R output(MetaItem<?>.MetaValueItem item) {
      return (R)this.output((MetaItem.MetaValueItem)item, 1);
   }

   public R output(MetaTileEntity mte) {
      return (R)this.output((MetaTileEntity)mte, 1);
   }

   public R output(MetaTileEntity mte, int amount) {
      return (R)this.outputs(mte.getStackForm(amount));
   }

   public R outputs(ItemStack output) {
      if (output != null && !output.func_190926_b()) {
         this.outputs.add(output);
      }

      return (R)this;
   }

   public R outputs(ItemStack... outputs) {
      return (R)this.outputs((Collection)Arrays.asList(outputs));
   }

   public R outputs(Collection<ItemStack> outputs) {
      outputs = new ArrayList(outputs);
      outputs.removeIf((stack) -> stack == null || stack.func_190926_b());
      this.outputs.addAll(outputs);
      return (R)this;
   }

   public R clearOutputs() {
      this.outputs.clear();
      return (R)this;
   }

   public R fluidInput(@NotNull Fluid fluid) {
      return (R)this.fluidInputs(new GTRecipeFluidInput(fluid, 1));
   }

   public R fluidInput(@NotNull Fluid fluid, int amount) {
      return (R)this.fluidInputs(new GTRecipeFluidInput(fluid, amount));
   }

   public R fluidInputs(Collection<GTRecipeInput> fluidIngredients) {
      this.fluidInputs.addAll(fluidIngredients);
      return (R)this;
   }

   public R fluidInputs(GTRecipeInput fluidIngredient) {
      this.fluidInputs.add(fluidIngredient);
      return (R)this;
   }

   public R fluidInputs(FluidStack input) {
      if (input != null && input.amount > 0) {
         this.fluidInputs.add(new GTRecipeFluidInput(input));
      } else if (input != null) {
         GTLog.logger.error("Fluid Input count cannot be less than 1. Actual: {}.", input.amount, new IllegalArgumentException());
      } else {
         GTLog.logger.error("FluidStack cannot be null.");
      }

      return (R)this;
   }

   public R fluidInputs(@NotNull Material material, int amount) {
      return (R)this.fluidInputs(material.getFluid(amount));
   }

   public R fluidInputs(@NotNull Material material, @NotNull FluidStorageKey storageKey, int amount) {
      return (R)this.fluidInputs(material.getFluid(storageKey, amount));
   }

   public R fluidInputs(FluidStack... fluidStacks) {
      ArrayList<GTRecipeInput> fluidIngredients = new ArrayList();

      for(FluidStack fluidStack : fluidStacks) {
         if (fluidStack != null && fluidStack.amount > 0) {
            fluidIngredients.add(new GTRecipeFluidInput(fluidStack));
         } else if (fluidStack != null) {
            GTLog.logger.error("Fluid Input count cannot be less than 1. Actual: {}.", fluidStack.amount, new Throwable());
         } else {
            GTLog.logger.error("FluidStack cannot be null.");
         }
      }

      this.fluidInputs.addAll(fluidIngredients);
      return (R)this;
   }

   public R clearFluidInputs() {
      this.fluidInputs.clear();
      return (R)this;
   }

   public R fluidOutputs(@NotNull Fluid fluid) {
      return (R)this.fluidOutputs(new FluidStack(fluid, 1));
   }

   public R fluidOutputs(@NotNull Fluid fluid, int amount) {
      return (R)this.fluidOutputs(new FluidStack(fluid, amount));
   }

   public R fluidOutputs(FluidStack output) {
      if (output != null && output.amount > 0) {
         this.fluidOutputs.add(output);
      }

      return (R)this;
   }

   public R fluidOutputs(@NotNull Material material, int amount) {
      return (R)this.fluidOutputs(material.getFluid(amount));
   }

   public R fluidOutputs(@NotNull Material material, @NotNull FluidStorageKey storageKey, int amount) {
      return (R)this.fluidOutputs(material.getFluid(storageKey, amount));
   }

   public R fluidOutputs(FluidStack... outputs) {
      return (R)this.fluidOutputs((Collection)Arrays.asList(outputs));
   }

   public R fluidOutputs(Collection<FluidStack> outputs) {
      outputs = new ArrayList(outputs);
      outputs.removeIf((o) -> o == null || o.amount <= 0);
      this.fluidOutputs.addAll(outputs);
      return (R)this;
   }

   public R clearFluidOutputs() {
      this.fluidOutputs.clear();
      return (R)this;
   }

   public R chancedOutput(ItemStack stack, int chance, int tierChanceBoost) {
      if (stack != null && !stack.func_190926_b()) {
         if (0 < chance && chance <= ChancedOutputLogic.getMaxChancedValue()) {
            this.chancedOutputs.add(new ChancedItemOutput(stack.func_77946_l(), chance, tierChanceBoost));
            return (R)this;
         } else {
            GTLog.logger.error("Chance cannot be less or equal to 0 or more than {}. Actual: {}.", ChancedOutputLogic.getMaxChancedValue(), chance, new Throwable());
            this.recipeStatus = EnumValidationResult.INVALID;
            return (R)this;
         }
      } else {
         return (R)this;
      }
   }

   public R chancedOutput(OrePrefix prefix, Material material, int count, int chance, int tierChanceBoost) {
      return (R)this.chancedOutput(OreDictUnifier.get(prefix, material, count), chance, tierChanceBoost);
   }

   public R chancedOutput(OrePrefix prefix, Material material, int chance, int tierChanceBoost) {
      return (R)this.chancedOutput(prefix, material, 1, chance, tierChanceBoost);
   }

   public R chancedOutput(MetaItem<?>.MetaValueItem item, int count, int chance, int tierChanceBoost) {
      return (R)this.chancedOutput(item.getStackForm(count), chance, tierChanceBoost);
   }

   public R chancedOutput(MetaItem<?>.MetaValueItem item, int chance, int tierChanceBoost) {
      return (R)this.chancedOutput(item, 1, chance, tierChanceBoost);
   }

   public R chancedOutputs(List<ChancedItemOutput> chancedOutputs) {
      for(ChancedItemOutput output : chancedOutputs) {
         this.chancedOutputs.add(output.copy());
      }

      return (R)this;
   }

   public R clearChancedOutput() {
      this.chancedOutputs.clear();
      return (R)this;
   }

   public R chancedOutputLogic(@NotNull ChancedOutputLogic logic) {
      this.chancedOutputLogic = logic;
      return (R)this;
   }

   public R chancedFluidOutput(FluidStack stack, int chance, int tierChanceBoost) {
      if (stack != null && stack.amount != 0) {
         if (0 < chance && chance <= ChancedOutputLogic.getMaxChancedValue()) {
            this.chancedFluidOutputs.add(new ChancedFluidOutput(stack.copy(), chance, tierChanceBoost));
            return (R)this;
         } else {
            GTLog.logger.error("Chance cannot be less or equal to 0 or more than {}. Actual: {}.", ChancedOutputLogic.getMaxChancedValue(), chance, new Throwable());
            this.recipeStatus = EnumValidationResult.INVALID;
            return (R)this;
         }
      } else {
         return (R)this;
      }
   }

   public R chancedFluidOutputs(List<ChancedFluidOutput> chancedOutputs) {
      for(ChancedFluidOutput output : chancedOutputs) {
         this.chancedFluidOutputs.add(output.copy());
      }

      return (R)this;
   }

   public R clearChancedFluidOutputs() {
      this.chancedFluidOutputs.clear();
      return (R)this;
   }

   public R chancedFluidOutputLogic(@NotNull ChancedOutputLogic logic) {
      this.chancedFluidOutputLogic = logic;
      return (R)this;
   }

   @Method(
      modid = "groovyscript"
   )
   public R inputs(IIngredient ingredient) {
      return (R)this.input(ofGroovyIngredient(ingredient));
   }

   @Method(
      modid = "groovyscript"
   )
   public R inputs(IIngredient... ingredients) {
      for(IIngredient ingredient : ingredients) {
         this.inputs(ingredient);
      }

      return (R)this;
   }

   @Method(
      modid = "groovyscript"
   )
   public R inputs(Collection<IIngredient> ingredients) {
      for(IIngredient ingredient : ingredients) {
         this.inputs(ingredient);
      }

      return (R)this;
   }

   @Method(
      modid = "groovyscript"
   )
   public R notConsumable(IIngredient ingredient) {
      return (R)this.notConsumable(ofGroovyIngredient(ingredient));
   }

   @Method(
      modid = "groovyscript"
   )
   private static GTRecipeInput ofGroovyIngredient(IIngredient ingredient) {
      if (ingredient instanceof OreDictIngredient) {
         return new GTRecipeOreInput(((OreDictIngredient)ingredient).getOreDict(), ingredient.getAmount());
      } else if (ingredient instanceof ItemStack) {
         return new GTRecipeItemInput((ItemStack)ingredient);
      } else if (ingredient instanceof FluidStack) {
         return new GTRecipeFluidInput((FluidStack)ingredient);
      } else {
         throw new IllegalArgumentException("Could not add groovy ingredient " + ingredient + " to recipe!");
      }
   }

   public void chancedOutputsMultiply(Recipe chancedOutputsFrom, int numberOfOperations) {
      for(ChancedItemOutput entry : chancedOutputsFrom.getChancedOutputs().getChancedEntries()) {
         int chance = entry.getChance();
         int boost = entry.getChanceBoost();

         for(int i = 0; i < numberOfOperations; ++i) {
            this.chancedOutput(((ItemStack)entry.getIngredient()).func_77946_l(), chance, boost);
         }
      }

      for(ChancedFluidOutput entry : chancedOutputsFrom.getChancedFluidOutputs().getChancedEntries()) {
         int chance = entry.getChance();
         int boost = entry.getChanceBoost();

         for(int i = 0; i < numberOfOperations; ++i) {
            this.chancedFluidOutput(((FluidStack)entry.getIngredient()).copy(), chance, boost);
         }
      }

   }

   public R append(Recipe recipe, int multiplier, boolean multiplyDuration) {
      for(Map.Entry<RecipeProperty<?>, Object> property : recipe.propertyStorage().entrySet()) {
         this.applyPropertyCT(((RecipeProperty)property.getKey()).getKey(), property.getValue());
      }

      List<GTRecipeInput> newRecipeInputs = new ArrayList();
      List<GTRecipeInput> newFluidInputs = new ArrayList();
      List<ItemStack> outputItems = new ArrayList();
      List<FluidStack> outputFluids = new ArrayList();
      multiplyInputsAndOutputs(newRecipeInputs, newFluidInputs, outputItems, outputFluids, recipe, multiplier);
      this.inputIngredients(newRecipeInputs);
      this.fluidInputs(newFluidInputs);
      this.outputs((Collection)outputItems);
      this.fluidOutputs((Collection)outputFluids);
      this.chancedOutputsMultiply(recipe, multiplier);
      this.EUt(multiplyDuration ? recipe.getEUt() : this.EUt + recipe.getEUt() * (long)multiplier);
      this.duration(multiplyDuration ? this.duration + recipe.getDuration() * multiplier : recipe.getDuration());
      if (this.parallel == 0) {
         this.parallel = multiplier;
      } else if (multiplyDuration) {
         this.parallel += multiplier;
      } else {
         this.parallel *= multiplier;
      }

      return (R)this;
   }

   protected static void multiplyInputsAndOutputs(List<GTRecipeInput> newRecipeInputs, List<GTRecipeInput> newFluidInputs, List<ItemStack> outputItems, List<FluidStack> outputFluids, Recipe recipe, int numberOfOperations) {
      recipe.getInputs().forEach((ri) -> {
         if (ri.isNonConsumable()) {
            newRecipeInputs.add(ri);
         } else {
            newRecipeInputs.add(ri.copyWithAmount(ri.getAmount() * numberOfOperations));
         }

      });
      recipe.getFluidInputs().forEach((fi) -> {
         if (fi.isNonConsumable()) {
            newFluidInputs.add(fi);
         } else {
            newFluidInputs.add(fi.copyWithAmount(fi.getAmount() * numberOfOperations));
         }

      });
      recipe.getOutputs().forEach((itemStack) -> outputItems.add(copyItemStackWithCount(itemStack, itemStack.func_190916_E() * numberOfOperations)));
      recipe.getFluidOutputs().forEach((fluidStack) -> outputFluids.add(copyFluidStackWithAmount(fluidStack, fluidStack.amount * numberOfOperations)));
   }

   public int getParallel() {
      return this.parallel;
   }

   protected static ItemStack copyItemStackWithCount(ItemStack itemStack, int count) {
      ItemStack itemCopy = itemStack.func_77946_l();
      itemCopy.func_190920_e(count);
      return itemCopy;
   }

   protected static FluidStack copyFluidStackWithAmount(FluidStack fluidStack, int count) {
      FluidStack fluidCopy = fluidStack.copy();
      fluidCopy.amount = count;
      return fluidCopy;
   }

   public R duration(int duration) {
      this.duration = duration;
      return (R)this;
   }

   public R EUt(long EUt) {
      this.EUt = EUt;
      return (R)this;
   }

   public R hidden() {
      this.hidden = true;
      return (R)this;
   }

   public R category(@NotNull GTRecipeCategory category) {
      this.category = category;
      return (R)this;
   }

   public R isCTRecipe() {
      this.isCTRecipe = true;
      return (R)this;
   }

   public R setRecipeMap(RecipeMap<R> recipeMap) {
      this.recipeMap = recipeMap;
      return (R)this;
   }

   public R copy() {
      return (R)(new RecipeBuilder(this));
   }

   public R ignoreAllBuildActions() {
      this.ignoreAllBuildActions = true;
      return (R)this;
   }

   public R ignoreBuildAction(ResourceLocation buildActionName) {
      if (this.ignoredBuildActions == null) {
         this.ignoredBuildActions = new Object2ObjectOpenHashMap();
      } else {
         if (!this.recipeMap.getBuildActions().containsKey(buildActionName)) {
            GTLog.logger.error("Recipe map {} does not contain build action {}!", this.recipeMap, buildActionName, new Throwable());
            return (R)this;
         }

         if (this.ignoredBuildActions.containsKey(buildActionName)) {
            return (R)this;
         }
      }

      this.ignoredBuildActions.put(buildActionName, (RecipeBuildAction)this.recipeMap.getBuildActions().get(buildActionName));
      return (R)this;
   }

   public R withRecycling() {
      this.withItemRecycling = true;
      return (R)this;
   }

   public ValidationResult<Recipe> build() {
      EnumValidationResult result = this.recipePropertyStorageErrored ? EnumValidationResult.INVALID : this.validate();
      return ValidationResult.newResult(result, new Recipe(this.inputs, this.outputs, new ChancedOutputList(this.chancedOutputLogic, this.chancedOutputs), this.fluidInputs, this.fluidOutputs, new ChancedOutputList(this.chancedFluidOutputLogic, this.chancedFluidOutputs), this.duration, this.EUt, this.hidden, this.isCTRecipe, this.recipePropertyStorage, this.category));
   }

   protected EnumValidationResult validate() {
      if (GroovyScriptModule.isCurrentlyRunning()) {
         GroovyLog.Msg msg = GroovyLog.msg("Error adding GregTech " + this.recipeMap.unlocalizedName + " recipe", new Object[0]).error();
         this.validateGroovy(msg);
         return msg.postIfNotEmpty() ? EnumValidationResult.SKIP : EnumValidationResult.VALID;
      } else {
         if (this.EUt == 0L) {
            GTLog.logger.error("EU/t cannot be equal to 0", new Throwable());
            if (this.isCTRecipe) {
               CraftTweakerAPI.logError("EU/t cannot be equal to 0", new Throwable());
            }

            this.recipeStatus = EnumValidationResult.INVALID;
         }

         if (this.duration <= 0) {
            GTLog.logger.error("Duration cannot be less or equal to 0", new Throwable());
            if (this.isCTRecipe) {
               CraftTweakerAPI.logError("Duration cannot be less or equal to 0", new Throwable());
            }

            this.recipeStatus = EnumValidationResult.INVALID;
         }

         if (this.recipeMap != null) {
            if (this.category == null) {
               GTLog.logger.error("Recipes must have a category", new Throwable());
               if (this.isCTRecipe) {
                  CraftTweakerAPI.logError("Recipes must have a category", new Throwable());
               }

               this.recipeStatus = EnumValidationResult.INVALID;
            } else if (this.category.getRecipeMap() != this.recipeMap) {
               GTLog.logger.error("Cannot apply Category with incompatible RecipeMap", new Throwable());
               if (this.isCTRecipe) {
                  CraftTweakerAPI.logError("Cannot apply Category with incompatible RecipeMap", new Throwable());
               }

               this.recipeStatus = EnumValidationResult.INVALID;
            }
         }

         if (this.recipeStatus == EnumValidationResult.INVALID) {
            GTLog.logger.error("Invalid recipe, read the errors above: {}", this);
         }

         return this.recipeStatus;
      }
   }

   @Method(
      modid = "groovyscript"
   )
   protected void validateGroovy(GroovyLog.Msg errorMsg) {
      errorMsg.add(this.EUt == 0L, () -> "EU/t must not be to 0");
      errorMsg.add(this.duration <= 0, () -> "Duration must not be less or equal to 0");
      int maxInput = this.recipeMap.getMaxInputs();
      int maxOutput = this.recipeMap.getMaxOutputs();
      int maxFluidInput = this.recipeMap.getMaxFluidInputs();
      int maxFluidOutput = this.recipeMap.getMaxFluidOutputs();
      errorMsg.add(this.inputs.size() > maxInput, () -> getRequiredString(maxInput, this.inputs.size(), "item input"));
      errorMsg.add(this.outputs.size() > maxOutput, () -> getRequiredString(maxOutput, this.outputs.size(), "item output"));
      errorMsg.add(this.fluidInputs.size() > maxFluidInput, () -> getRequiredString(maxFluidInput, this.fluidInputs.size(), "fluid input"));
      errorMsg.add(this.fluidOutputs.size() > maxFluidOutput, () -> getRequiredString(maxFluidOutput, this.fluidOutputs.size(), "fluid output"));
   }

   protected static @NotNull String getRequiredString(int max, int actual, @NotNull String type) {
      if (max <= 0) {
         return "No " + type + "s allowed, but found " + actual;
      } else {
         String out = "Must have at most " + max + " " + type;
         if (max != 1) {
            out = out + "s";
         }

         out = out + ", but found " + actual;
         return out;
      }
   }

   /** @deprecated */
   @Deprecated
   @Obsolete
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   protected R invalidateOnBuildAction() {
      return (R)this;
   }

   @MustBeInvokedByOverriders
   public void buildAndRegister() {
      if (!this.ignoreAllBuildActions) {
         for(Map.Entry<ResourceLocation, RecipeBuildAction<R>> buildAction : this.recipeMap.getBuildActions().entrySet()) {
            if (this.ignoredBuildActions == null || !this.ignoredBuildActions.containsKey(buildAction.getKey())) {
               ((RecipeBuildAction)buildAction.getValue()).accept(this);
            }
         }
      }

      if (this.hasItemRecycling()) {
         ItemStack outputStack = (ItemStack)this.getOutputs().get(0);
         RecyclingData data = RecyclingHandler.getRecyclingIngredients(this.getInputs(), outputStack.func_190916_E());
         if (data != null) {
            GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(outputStack, data);
         }
      }

      ValidationResult<Recipe> validationResult = this.build();
      this.recipeMap.addRecipe(validationResult);
   }

   public List<GTRecipeInput> getInputs() {
      return this.inputs;
   }

   public List<ItemStack> getOutputs() {
      return this.outputs;
   }

   public List<ChancedItemOutput> getChancedOutputs() {
      return this.chancedOutputs;
   }

   public List<ChancedFluidOutput> getChancedFluidOutputs() {
      return this.chancedFluidOutputs;
   }

   public List<ItemStack> getAllItemOutputs() {
      List<ItemStack> stacks = new ArrayList(this.getOutputs());

      for(ChancedItemOutput entry : this.chancedOutputs) {
         stacks.add(((ItemStack)entry.getIngredient()).func_77946_l());
      }

      return stacks;
   }

   public List<GTRecipeInput> getFluidInputs() {
      return this.fluidInputs;
   }

   public List<FluidStack> getFluidOutputs() {
      return this.fluidOutputs;
   }

   public long getEUt() {
      return this.EUt;
   }

   public int getDuration() {
      return this.duration;
   }

   public @Nullable CleanroomType getCleanroom() {
      return (CleanroomType)this.recipePropertyStorage.get(CleanroomProperty.getInstance(), (Object)null);
   }

   public boolean ignoresAllBuildActions() {
      return this.ignoreAllBuildActions;
   }

   public @NotNull Map<ResourceLocation, RecipeBuildAction<R>> getIgnoredBuildActions() {
      if (this.ignoreAllBuildActions) {
         return this.recipeMap.getBuildActions();
      } else {
         return this.ignoredBuildActions == null ? Collections.emptyMap() : this.ignoredBuildActions;
      }
   }

   public boolean hasItemRecycling() {
      return this.withItemRecycling;
   }

   public String toString() {
      return (new ToStringBuilder(this)).append("recipeMap", this.recipeMap).append("inputs", this.inputs).append("outputs", this.outputs).append("chancedOutputs", this.chancedOutputs).append("chancedFluidOutputs", this.chancedFluidOutputs).append("fluidInputs", this.fluidInputs).append("fluidOutputs", this.fluidOutputs).append("duration", this.duration).append("EUt", this.EUt).append("hidden", this.hidden).append("cleanroom", this.getCleanroom()).append("dimensions", this.getDimensionIDs().toString()).append("dimensions_blocked", this.getBlockedDimensionIDs().toString()).append("recipeStatus", this.recipeStatus).append("ignoresBuildActions", this.ignoresAllBuildActions()).append("ignoredBuildActions", this.getIgnoredBuildActions()).toString();
   }
}
