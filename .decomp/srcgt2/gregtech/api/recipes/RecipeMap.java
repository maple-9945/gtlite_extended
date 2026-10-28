package gregtech.api.recipes;

import com.cleanroommc.groovyscript.api.GroovyLog;
import com.google.common.collect.ImmutableList;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap.1;
import gregtech.api.recipes.category.GTRecipeCategory;
import gregtech.api.recipes.chance.boost.ChanceBoostFunction;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.recipes.map.AbstractMapIngredient;
import gregtech.api.recipes.map.Branch;
import gregtech.api.recipes.map.Either;
import gregtech.api.recipes.map.MapFluidIngredient;
import gregtech.api.recipes.map.MapItemStackIngredient;
import gregtech.api.recipes.map.MapItemStackNBTIngredient;
import gregtech.api.recipes.map.MapOreDictIngredient;
import gregtech.api.recipes.map.MapOreDictNBTIngredient;
import gregtech.api.recipes.ui.RecipeMapUI;
import gregtech.api.recipes.ui.RecipeMapUIFunction;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import gregtech.api.util.GTUtility;
import gregtech.api.util.LocalizationUtils;
import gregtech.api.util.ValidationResult;
import gregtech.common.ConfigHolder;
import gregtech.integration.crafttweaker.CTRecipeHelper;
import gregtech.integration.crafttweaker.recipe.CTRecipe;
import gregtech.integration.crafttweaker.recipe.CTRecipeBuilder;
import gregtech.integration.groovy.GroovyScriptModule;
import gregtech.integration.groovy.VirtualizedRecipeMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ReferenceOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.DoubleSupplier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.oredict.OreDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenSetter;

@ZenClass("mods.gregtech.recipe.RecipeMap")
@ZenRegister
public class RecipeMap<R extends RecipeBuilder<R>> {
   private static final Map<String, RecipeMap<?>> RECIPE_MAP_REGISTRY = new Object2ReferenceOpenHashMap();
   private static final Comparator<Recipe> RECIPE_DURATION_THEN_EU = Comparator.comparingInt(Recipe::getDuration).thenComparingLong(Recipe::getEUt).thenComparing(Recipe::hashCode);
   private static boolean foundInvalidRecipe = false;
   public static final ChanceBoostFunction DEFAULT_CHANCE_FUNCTION;
   protected RecipeMapUI<?> recipeMapUI;
   public ChanceBoostFunction chanceFunction;
   public final String unlocalizedName;
   private final R recipeBuilderSample;
   private int maxInputs;
   private int maxOutputs;
   private int maxFluidInputs;
   private int maxFluidOutputs;
   private final GTRecipeCategory primaryRecipeCategory;
   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   public final boolean isHidden;
   private boolean allowEmptyOutput;
   private final Object grsVirtualizedRecipeMap;
   private final Branch lookup;
   private boolean hasOreDictedInputs;
   private boolean hasNBTMatcherInputs;
   private static final WeakHashMap<AbstractMapIngredient, WeakReference<AbstractMapIngredient>> ingredientRoot;
   private final WeakHashMap<AbstractMapIngredient, WeakReference<AbstractMapIngredient>> fluidIngredientRoot;
   private final Map<GTRecipeCategory, List<Recipe>> recipeByCategory;
   private final Map<ResourceLocation, RecipeBuildAction<R>> recipeBuildActions;
   protected @Nullable SoundEvent sound;
   private @Nullable RecipeMap<?> smallRecipeMap;

   public RecipeMap(@NotNull String unlocalizedName, @NotNull R defaultRecipeBuilder, @NotNull RecipeMapUIFunction recipeMapUI, int maxInputs, int maxOutputs, int maxFluidInputs, int maxFluidOutputs) {
      this.chanceFunction = DEFAULT_CHANCE_FUNCTION;
      this.isHidden = false;
      this.lookup = new Branch();
      this.hasOreDictedInputs = false;
      this.hasNBTMatcherInputs = false;
      this.fluidIngredientRoot = new WeakHashMap();
      this.recipeByCategory = new Object2ObjectOpenHashMap();
      this.recipeBuildActions = new Object2ObjectOpenHashMap();
      this.unlocalizedName = unlocalizedName;
      this.recipeMapUI = recipeMapUI.apply(this);
      this.maxInputs = maxInputs;
      this.maxFluidInputs = maxFluidInputs;
      this.maxOutputs = maxOutputs;
      this.maxFluidOutputs = maxFluidOutputs;
      this.primaryRecipeCategory = GTRecipeCategory.create("gregtech", unlocalizedName, this.getTranslationKey(), this);
      defaultRecipeBuilder.setRecipeMap(this);
      defaultRecipeBuilder.category(this.primaryRecipeCategory);
      this.recipeBuilderSample = defaultRecipeBuilder;
      RECIPE_MAP_REGISTRY.put(unlocalizedName, this);
      this.grsVirtualizedRecipeMap = GregTechAPI.moduleManager.isModuleEnabled("grs_integration") ? new VirtualizedRecipeMap(this) : null;
   }

   @ZenMethod
   public static List<RecipeMap<? extends RecipeBuilder<?>>> getRecipeMaps() {
      return ImmutableList.copyOf(RECIPE_MAP_REGISTRY.values());
   }

   @ZenMethod
   public static RecipeMap<? extends RecipeBuilder<?>> getByName(String unlocalizedName) {
      return (RecipeMap)RECIPE_MAP_REGISTRY.get(unlocalizedName);
   }

   @ZenMethod
   public ChanceBoostFunction getChanceFunction() {
      return this.chanceFunction;
   }

   public static boolean isFoundInvalidRecipe() {
      return foundInvalidRecipe;
   }

   public static void setFoundInvalidRecipe(boolean foundInvalidRecipe) {
      RecipeMap.foundInvalidRecipe = RecipeMap.foundInvalidRecipe || foundInvalidRecipe;
      OrePrefix currentOrePrefix = OrePrefix.getCurrentProcessingPrefix();
      if (currentOrePrefix != null) {
         Material currentMaterial = OrePrefix.getCurrentMaterial();
         GTLog.logger.error("Error happened during processing ore registration of prefix {} and material {}. Seems like cross-mod compatibility issue. Report to GTCEu github.", currentOrePrefix, currentMaterial);
      }

   }

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   public RecipeMap<R> setProgressBar(TextureArea progressBar, ProgressWidget.MoveType moveType) {
      this.recipeMapUI.setProgressBar(progressBar, moveType);
      return this;
   }

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   public RecipeMap<R> setSlotOverlay(boolean isOutput, boolean isFluid, TextureArea slotOverlay) {
      return this.setSlotOverlay(isOutput, isFluid, false, slotOverlay).setSlotOverlay(isOutput, isFluid, true, slotOverlay);
   }

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   public RecipeMap<R> setSlotOverlay(boolean isOutput, boolean isFluid, boolean isLast, TextureArea slotOverlay) {
      if (isFluid) {
         this.recipeMapUI.setFluidSlotOverlay(slotOverlay, isOutput, isLast);
      } else {
         this.recipeMapUI.setItemSlotOverlay(slotOverlay, isOutput, isLast);
      }

      return this;
   }

   public RecipeMap<R> setSound(SoundEvent sound) {
      this.sound = sound;
      return this;
   }

   public RecipeMap<R> setChanceFunction(@NotNull ChanceBoostFunction function) {
      this.chanceFunction = function;
      return this;
   }

   public RecipeMap<R> onRecipeBuild(@NotNull ResourceLocation name, @NotNull RecipeBuildAction<R> action) {
      if (this.recipeBuildActions.containsKey(name)) {
         throw new IllegalArgumentException("Cannot register RecipeBuildAction with duplicate name: " + name);
      } else {
         this.recipeBuildActions.put(name, action);
         return this;
      }
   }

   public void removeBuildAction(@NotNull ResourceLocation name) {
      this.recipeBuildActions.remove(name);
   }

   @Internal
   protected void onRecipeBuild(@NotNull Map<ResourceLocation, RecipeBuildAction<R>> actions) {
      this.recipeBuildActions.putAll(actions);
   }

   @Internal
   protected @UnmodifiableView @NotNull Map<ResourceLocation, RecipeBuildAction<R>> getBuildActions() {
      return this.recipeBuildActions;
   }

   public RecipeMap<R> allowEmptyOutput() {
      this.allowEmptyOutput = true;
      return this;
   }

   public RecipeMap<R> setSmallRecipeMap(RecipeMap<?> recipeMap) {
      this.smallRecipeMap = recipeMap;
      return this;
   }

   public RecipeMap<? extends RecipeBuilder<?>> getSmallRecipeMap() {
      return this.smallRecipeMap;
   }

   public boolean addRecipe(@NotNull ValidationResult<Recipe> validationResult) {
      validationResult = this.postValidateRecipe(validationResult);
      switch (1.$SwitchMap$gregtech$api$util$EnumValidationResult[validationResult.getType().ordinal()]) {
         case 1:
            return false;
         case 2:
            setFoundInvalidRecipe(true);
            return false;
         default:
            Recipe recipe = (Recipe)validationResult.getResult();
            if (recipe.isGroovyRecipe()) {
               this.getGroovyScriptRecipeMap().addScripted(recipe);
            }

            return this.compileRecipe(recipe);
      }
   }

   public boolean compileRecipe(Recipe recipe) {
      if (recipe == null) {
         return false;
      } else {
         List<List<AbstractMapIngredient>> items = this.fromRecipe(recipe);
         if (this.recurseIngredientTreeAdd(recipe, items, this.lookup, 0, 0)) {
            this.recipeByCategory.compute(recipe.getRecipeCategory(), (k, v) -> {
               if (v == null) {
                  v = new ArrayList();
               }

               v.add(recipe);
               return v;
            });
            return true;
         } else {
            return false;
         }
      }
   }

   public boolean removeRecipe(@NotNull Recipe recipe) {
      List<List<AbstractMapIngredient>> items = this.fromRecipe(recipe);
      if (this.recurseIngredientTreeRemove(recipe, items, this.lookup, 0) != null) {
         if (GroovyScriptModule.isCurrentlyRunning()) {
            this.getGroovyScriptRecipeMap().addBackup(recipe);
         }

         this.recipeByCategory.compute(recipe.getRecipeCategory(), (k, v) -> {
            if (v != null) {
               v.remove(recipe);
            }

            return v != null && !v.isEmpty() ? v : null;
         });
         return true;
      } else {
         return false;
      }
   }

   @Internal
   protected void removeAllRecipes() {
      if (GroovyScriptModule.isCurrentlyRunning()) {
         Stream var10000 = this.lookup.getRecipes(false);
         VirtualizedRecipeMap var10001 = this.getGroovyScriptRecipeMap();
         Objects.requireNonNull(var10001);
         var10000.forEach(var10001::addBackup);
      }

      this.lookup.getNodes().clear();
      this.lookup.getSpecialNodes().clear();
      this.recipeByCategory.clear();
   }

   protected @NotNull ValidationResult<Recipe> postValidateRecipe(@NotNull ValidationResult<Recipe> validationResult) {
      EnumValidationResult recipeStatus = validationResult.getType();
      Recipe recipe = (Recipe)validationResult.getResult();
      if (recipe.isGroovyRecipe()) {
         return validationResult;
      } else {
         boolean emptyInputs = recipe.getInputs().isEmpty() && recipe.getFluidInputs().isEmpty();
         if (emptyInputs) {
            GTLog.logger.error("Invalid amount of recipe inputs. Recipe inputs are empty.", new Throwable());
            if (recipe.getIsCTRecipe()) {
               CraftTweakerAPI.logError("Invalid amount of recipe inputs. Recipe inputs are empty.", new Throwable());
            }

            recipeStatus = EnumValidationResult.INVALID;
         }

         boolean emptyOutputs = !this.allowEmptyOutput && recipe.getEUt() > 0L && recipe.getOutputs().isEmpty() && recipe.getFluidOutputs().isEmpty() && recipe.getChancedOutputs().getChancedEntries().isEmpty() && recipe.getChancedFluidOutputs().getChancedEntries().isEmpty();
         if (emptyOutputs) {
            GTLog.logger.error("Invalid amount of recipe outputs. Recipe outputs are empty.", new Throwable());
            if (recipe.getIsCTRecipe()) {
               CraftTweakerAPI.logError("Invalid amount of outputs inputs. Recipe outputs are empty.", new Throwable());
            }

            recipeStatus = EnumValidationResult.INVALID;
         }

         int amount = recipe.getInputs().size();
         if (amount > this.getMaxInputs()) {
            GTLog.logger.error("Invalid amount of recipe inputs. Actual: {}. Should be at most {}.", amount, this.getMaxInputs(), new Throwable());
            if (recipe.getIsCTRecipe()) {
               CraftTweakerAPI.logError(String.format("Invalid amount of recipe inputs. Actual: %s. Should be at most %s.", amount, this.getMaxInputs()), new Throwable());
            }

            recipeStatus = EnumValidationResult.INVALID;
         }

         amount = recipe.getOutputs().size() + recipe.getChancedOutputs().getChancedEntries().size();
         if (amount > this.getMaxOutputs()) {
            GTLog.logger.error("Invalid amount of recipe outputs. Actual: {}. Should be at most {}.", amount, this.getMaxOutputs(), new Throwable());
            if (recipe.getIsCTRecipe()) {
               CraftTweakerAPI.logError(String.format("Invalid amount of recipe outputs. Actual: %s. Should be at most %s.", amount, this.getMaxOutputs()), new Throwable());
            }

            recipeStatus = EnumValidationResult.INVALID;
         }

         amount = recipe.getFluidInputs().size();
         if (amount > this.getMaxFluidInputs()) {
            GTLog.logger.error("Invalid amount of recipe fluid inputs. Actual: {}. Should be at most {}.", amount, this.getMaxFluidInputs(), new Throwable());
            if (recipe.getIsCTRecipe()) {
               CraftTweakerAPI.logError(String.format("Invalid amount of recipe fluid inputs. Actual: %s. Should be at most %s.", amount, this.getMaxFluidInputs()), new Throwable());
            }

            recipeStatus = EnumValidationResult.INVALID;
         }

         amount = recipe.getFluidOutputs().size() + recipe.getChancedFluidOutputs().getChancedEntries().size();
         if (amount > this.getMaxFluidOutputs()) {
            GTLog.logger.error("Invalid amount of recipe fluid outputs. Actual: {}. Should be at most {}.", amount, this.getMaxFluidOutputs(), new Throwable());
            if (recipe.getIsCTRecipe()) {
               CraftTweakerAPI.logError(String.format("Invalid amount of recipe fluid outputs. Actual: %s. Should be at most %s.", amount, this.getMaxFluidOutputs()), new Throwable());
            }

            recipeStatus = EnumValidationResult.INVALID;
         }

         return ValidationResult.newResult(recipeStatus, recipe);
      }
   }

   public @Nullable Recipe findRecipe(long voltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
      return this.findRecipe(voltage, GTUtility.itemHandlerToList(inputs), GTUtility.fluidHandlerToList(fluidInputs));
   }

   public @Nullable Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs) {
      return this.findRecipe(voltage, inputs, fluidInputs, false);
   }

   public @Nullable Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, boolean exactVoltage) {
      List<ItemStack> items = (List)inputs.stream().filter((s) -> !s.func_190926_b()).collect(Collectors.toList());
      List<FluidStack> fluids = (List)fluidInputs.stream().filter((f) -> f != null && f.amount != 0).collect(Collectors.toList());
      return this.find(items, fluids, (recipe) -> {
         if (exactVoltage && recipe.getEUt() != voltage) {
            return false;
         } else {
            return recipe.getEUt() > voltage ? false : recipe.matches(false, inputs, fluidInputs);
         }
      });
   }

   protected @Nullable List<List<AbstractMapIngredient>> prepareRecipeFind(@NotNull Collection<ItemStack> items, @NotNull Collection<FluidStack> fluids) {
      if (items.size() != Integer.MAX_VALUE && fluids.size() != Integer.MAX_VALUE) {
         if (items.size() == 0 && fluids.size() == 0) {
            return null;
         } else {
            List<List<AbstractMapIngredient>> list = new ObjectArrayList(items.size() + fluids.size());
            if (items.size() > 0) {
               this.buildFromItemStacks(list, uniqueItems(items));
            }

            if (fluids.size() > 0) {
               this.buildFromFluidStacks(list, fluids);
            }

            return list.size() == 0 ? null : list;
         }
      } else {
         return null;
      }
   }

   public @Nullable Recipe find(@NotNull Collection<ItemStack> items, @NotNull Collection<FluidStack> fluids, @NotNull Predicate<Recipe> canHandle) {
      List<List<AbstractMapIngredient>> list = this.prepareRecipeFind(items, fluids);
      return list == null ? null : this.recurseIngredientTreeFindRecipe(list, this.lookup, canHandle);
   }

   public static @NotNull ItemStack[] uniqueItems(@NotNull Collection<ItemStack> inputs) {
      int index = 0;
      ItemStack[] uniqueItems = new ItemStack[inputs.size()];

      label43:
      for(ItemStack input : inputs) {
         if (!input.func_190926_b()) {
            if (index > 0) {
               for(ItemStack unique : uniqueItems) {
                  if (unique == null) {
                     break;
                  }

                  if (input.func_77969_a(unique) && ItemStack.func_77970_a(input, unique)) {
                     continue label43;
                  }
               }
            }

            uniqueItems[index++] = input;
         }
      }

      if (index == uniqueItems.length) {
         return uniqueItems;
      } else {
         ItemStack[] retUniqueItems = new ItemStack[index];
         System.arraycopy(uniqueItems, 0, retUniqueItems, 0, index);
         return retUniqueItems;
      }
   }

   public static @NotNull List<GTRecipeInput> uniqueIngredientsList(@NotNull Collection<GTRecipeInput> inputs) {
      List<GTRecipeInput> list = new ObjectArrayList(inputs.size());

      for(GTRecipeInput item : inputs) {
         boolean isEqual = false;

         for(GTRecipeInput obj : list) {
            if (item.equalIgnoreAmount(obj)) {
               isEqual = true;
               break;
            }
         }

         if (!isEqual) {
            if (item instanceof IntCircuitIngredient) {
               list.add(0, item);
            } else {
               list.add(item);
            }
         }
      }

      return list;
   }

   private @Nullable Recipe recurseIngredientTreeFindRecipe(@NotNull List<List<AbstractMapIngredient>> ingredients, @NotNull Branch branchRoot, @NotNull Predicate<Recipe> canHandle) {
      for(int i = 0; i < ingredients.size(); ++i) {
         Recipe r = this.recurseIngredientTreeFindRecipe(ingredients, branchRoot, canHandle, i, 0, 1L << i);
         if (r != null) {
            return r;
         }
      }

      return null;
   }

   private @Nullable Recipe recurseIngredientTreeFindRecipe(@NotNull List<List<AbstractMapIngredient>> ingredients, @NotNull Branch branchMap, @NotNull Predicate<Recipe> canHandle, int index, int count, long skip) {
      if (count == ingredients.size()) {
         return null;
      } else {
         for(AbstractMapIngredient obj : (List)ingredients.get(index)) {
            Map<AbstractMapIngredient, Either<Recipe, Branch>> targetMap = determineRootNodes(obj, branchMap);
            Either<Recipe, Branch> result = (Either)targetMap.get(obj);
            if (result != null) {
               Recipe r = (Recipe)result.map((potentialRecipe) -> canHandle.test(potentialRecipe) ? potentialRecipe : null, (potentialBranch) -> this.diveIngredientTreeFindRecipe(ingredients, potentialBranch, canHandle, index, count, skip));
               if (r != null) {
                  return r;
               }
            }
         }

         return null;
      }
   }

   private @Nullable Recipe diveIngredientTreeFindRecipe(@NotNull List<List<AbstractMapIngredient>> ingredients, @NotNull Branch map, @NotNull Predicate<Recipe> canHandle, int currentIndex, int count, long skip) {
      for(int i = (currentIndex + 1) % ingredients.size(); i != currentIndex; i = (i + 1) % ingredients.size()) {
         if ((skip & 1L << i) == 0L) {
            Recipe found = this.recurseIngredientTreeFindRecipe(ingredients, map, canHandle, i, count + 1, skip | 1L << i);
            if (found != null) {
               return found;
            }
         }
      }

      return null;
   }

   public @Nullable Set<Recipe> findRecipeCollisions(Collection<ItemStack> items, Collection<FluidStack> fluids) {
      List<List<AbstractMapIngredient>> list = this.prepareRecipeFind(items, fluids);
      if (list == null) {
         return null;
      } else {
         Set<Recipe> collidingRecipes = new ObjectOpenHashSet();
         this.recurseIngredientTreeFindRecipeCollisions(list, this.lookup, collidingRecipes);
         return collidingRecipes;
      }
   }

   private void recurseIngredientTreeFindRecipeCollisions(@NotNull List<List<AbstractMapIngredient>> ingredients, @NotNull Branch branchRoot, @NotNull Set<Recipe> collidingRecipes) {
      for(int i = 0; i < ingredients.size(); ++i) {
         this.recurseIngredientTreeFindRecipeCollisions(ingredients, branchRoot, i, 0, 1L << i, collidingRecipes);
      }

   }

   private @Nullable Recipe recurseIngredientTreeFindRecipeCollisions(@NotNull List<List<AbstractMapIngredient>> ingredients, @NotNull Branch branchMap, int index, int count, long skip, @NotNull Set<Recipe> collidingRecipes) {
      if (count == ingredients.size()) {
         return null;
      } else {
         for(AbstractMapIngredient obj : (List)ingredients.get(index)) {
            Map<AbstractMapIngredient, Either<Recipe, Branch>> targetMap = determineRootNodes(obj, branchMap);
            Either<Recipe, Branch> result = (Either)targetMap.get(obj);
            if (result != null) {
               Recipe r = (Recipe)result.map((recipe) -> recipe, (right) -> this.diveIngredientTreeFindRecipeCollisions(ingredients, right, index, count, skip, collidingRecipes));
               if (r != null) {
                  collidingRecipes.add(r);
               }
            }
         }

         return null;
      }
   }

   private @Nullable Recipe diveIngredientTreeFindRecipeCollisions(@NotNull List<List<AbstractMapIngredient>> ingredients, @NotNull Branch map, int currentIndex, int count, long skip, @NotNull Set<Recipe> collidingRecipes) {
      for(int i = (currentIndex + 1) % ingredients.size(); i != currentIndex; i = (i + 1) % ingredients.size()) {
         if ((skip & 1L << i) == 0L) {
            Recipe r = this.recurseIngredientTreeFindRecipeCollisions(ingredients, map, i, count + 1, skip | 1L << i, collidingRecipes);
            if (r != null) {
               return r;
            }
         }
      }

      return null;
   }

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   public ModularUI.Builder createJeiUITemplate(IItemHandlerModifiable importItems, IItemHandlerModifiable exportItems, FluidTankList importFluids, FluidTankList exportFluids, int yOffset) {
      return this.recipeMapUI.createJeiUITemplate(importItems, exportItems, importFluids, exportFluids, yOffset);
   }

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   public ModularUI.Builder createUITemplate(DoubleSupplier progressSupplier, IItemHandlerModifiable importItems, IItemHandlerModifiable exportItems, FluidTankList importFluids, FluidTankList exportFluids, int yOffset) {
      return this.recipeMapUI.createUITemplate(progressSupplier, importItems, exportItems, importFluids, exportFluids, yOffset);
   }

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   public ModularUI.Builder createUITemplateNoOutputs(DoubleSupplier progressSupplier, IItemHandlerModifiable importItems, IItemHandlerModifiable exportItems, FluidTankList importFluids, FluidTankList exportFluids, int yOffset) {
      return this.recipeMapUI.createUITemplateNoOutputs(progressSupplier, importItems, exportItems, importFluids, exportFluids, yOffset);
   }

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   protected void addInventorySlotGroup(ModularUI.Builder builder, IItemHandlerModifiable itemHandler, FluidTankList fluidHandler, boolean isOutputs, int yOffset) {
   }

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   protected void addSlot(ModularUI.Builder builder, int x, int y, int slotIndex, IItemHandlerModifiable itemHandler, FluidTankList fluidHandler, boolean isFluid, boolean isOutputs) {
   }

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   protected TextureArea[] getOverlaysForSlot(boolean isOutput, boolean isFluid, boolean isLast) {
      return null;
   }

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   public int getPropertyHeightShift() {
      return this.recipeMapUI.getPropertyHeightShift();
   }

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   private boolean shouldShiftWidgets() {
      return false;
   }

   @Method(
      modid = "groovyscript"
   )
   private VirtualizedRecipeMap getGroovyScriptRecipeMap() {
      return (VirtualizedRecipeMap)this.grsVirtualizedRecipeMap;
   }

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   public int getPropertyListHeight(Recipe recipe) {
      return 0;
   }

   private boolean recurseIngredientTreeAdd(@NotNull Recipe recipe, @NotNull List<List<AbstractMapIngredient>> ingredients, @NotNull Branch branchMap, int index, int count) {
      if (count >= ingredients.size()) {
         return true;
      } else if (index >= ingredients.size()) {
         throw new IllegalStateException("Index out of bounds for recurseItemTreeAdd, should not happen");
      } else {
         List<AbstractMapIngredient> current = (List)ingredients.get(index);
         Branch branchRight = new Branch();

         for(AbstractMapIngredient obj : current) {
            Map<AbstractMapIngredient, Either<Recipe, Branch>> targetMap = determineRootNodes(obj, branchMap);
            Either<Recipe, Branch> r = (Either)targetMap.compute(obj, (k, v) -> {
               if (count == ingredients.size() - 1) {
                  if (v == null) {
                     return Either.left(recipe);
                  } else {
                     if (!v.left().isPresent() || v.left().get() != recipe) {
                        if (recipe.getIsCTRecipe()) {
                           CraftTweakerAPI.logError(String.format("Recipe duplicate or conflict found in RecipeMap %s and was not added. See next lines for details.", this.unlocalizedName));
                           CraftTweakerAPI.logError(String.format("Attempted to add Recipe: %s", CTRecipeHelper.getRecipeAddLine(this, recipe)));
                           if (v.left().isPresent()) {
                              CraftTweakerAPI.logError(String.format("Which conflicts with: %s", CTRecipeHelper.getRecipeAddLine(this, (Recipe)v.left().get())));
                           } else {
                              CraftTweakerAPI.logError("Could not identify exact duplicate/conflict.");
                           }
                        }

                        if (recipe.isGroovyRecipe()) {
                           GroovyLog log = GroovyLog.get();
                           log.warn("Recipe duplicate or conflict found in RecipeMap {} and was not added. See next lines for details", new Object[]{this.unlocalizedName});
                           log.warn("Attempted to add Recipe: {}", new Object[]{recipe.toString()});
                           if (v.left().isPresent()) {
                              log.warn("Which conflicts with: {}", new Object[]{((Recipe)v.left().get()).toString()});
                           } else {
                              log.warn("Could not find exact duplicate/conflict.");
                           }
                        }

                        if (ConfigHolder.misc.debug || GTValues.isDeobfEnvironment()) {
                           GTLog.logger.warn("Recipe duplicate or conflict found in RecipeMap {} and was not added. See next lines for details", this.unlocalizedName);
                           GTLog.logger.warn("Attempted to add Recipe: {}", recipe.toString());
                           if (v.left().isPresent()) {
                              GTLog.logger.warn("Which conflicts with: {}", ((Recipe)v.left().get()).toString());
                           } else {
                              GTLog.logger.warn("Could not find exact duplicate/conflict.");
                           }
                        }
                     }

                     return v;
                  }
               } else {
                  return v == null ? Either.right(branchRight) : v;
               }
            });
            if (r.left().isPresent()) {
               if (r.left().get() != recipe) {
                  return false;
               }
            } else {
               boolean addedNextBranch = r.right().filter((m) -> this.recurseIngredientTreeAdd(recipe, ingredients, m, (index + 1) % ingredients.size(), count + 1)).isPresent();
               if (!addedNextBranch) {
                  if (count == ingredients.size() - 1) {
                     targetMap.remove(obj);
                  } else if (((Either)targetMap.get(obj)).right().isPresent() && ((Branch)((Either)targetMap.get(obj)).right().get()).isEmptyBranch()) {
                     targetMap.remove(obj);
                  }

                  return false;
               }
            }
         }

         return true;
      }
   }

   protected static @NotNull Map<AbstractMapIngredient, Either<Recipe, Branch>> determineRootNodes(@NotNull AbstractMapIngredient ingredient, @NotNull Branch branchMap) {
      return ingredient.isSpecialIngredient() ? branchMap.getSpecialNodes() : branchMap.getNodes();
   }

   protected void buildFromRecipeFluids(@NotNull List<List<AbstractMapIngredient>> list, @NotNull List<GTRecipeInput> fluidInputs) {
      for(GTRecipeInput fluidInput : fluidInputs) {
         AbstractMapIngredient ingredient = new MapFluidIngredient(fluidInput);
         retrieveCachedIngredient(list, ingredient, this.fluidIngredientRoot);
      }

   }

   protected static void retrieveCachedIngredient(@NotNull List<List<AbstractMapIngredient>> list, @NotNull AbstractMapIngredient defaultIngredient, @NotNull WeakHashMap<AbstractMapIngredient, WeakReference<AbstractMapIngredient>> cache) {
      WeakReference<AbstractMapIngredient> cached = (WeakReference)cache.get(defaultIngredient);
      if (cached != null && cached.get() != null) {
         list.add(Collections.singletonList((AbstractMapIngredient)cached.get()));
      } else {
         cache.put(defaultIngredient, new WeakReference(defaultIngredient));
         list.add(Collections.singletonList(defaultIngredient));
      }

   }

   protected void buildFromFluidStacks(@NotNull List<List<AbstractMapIngredient>> list, @NotNull Iterable<FluidStack> ingredients) {
      for(FluidStack t : ingredients) {
         list.add(Collections.singletonList(new MapFluidIngredient(t)));
      }

   }

   protected @NotNull List<List<AbstractMapIngredient>> fromRecipe(@NotNull Recipe r) {
      List<List<AbstractMapIngredient>> list = new ObjectArrayList(r.getInputs().size() + r.getFluidInputs().size());
      if (r.getInputs().size() > 0) {
         this.buildFromRecipeItems(list, uniqueIngredientsList(r.getInputs()));
      }

      if (r.getFluidInputs().size() > 0) {
         this.buildFromRecipeFluids(list, r.getFluidInputs());
      }

      return list;
   }

   protected void buildFromRecipeItems(List<List<AbstractMapIngredient>> list, @NotNull List<GTRecipeInput> inputs) {
      for(GTRecipeInput r : inputs) {
         if (r.isOreDict()) {
            this.hasOreDictedInputs = true;
            AbstractMapIngredient ingredient;
            if (r.hasNBTMatchingCondition()) {
               this.hasNBTMatcherInputs = true;
               ingredient = new MapOreDictNBTIngredient(r.getOreDict(), r.getNBTMatcher(), r.getNBTMatchingCondition());
            } else {
               ingredient = new MapOreDictIngredient(r.getOreDict());
            }

            retrieveCachedIngredient(list, ingredient, ingredientRoot);
         } else {
            List<AbstractMapIngredient> ingredients;
            if (r.hasNBTMatchingCondition()) {
               ingredients = MapItemStackNBTIngredient.from(r);
               this.hasNBTMatcherInputs = true;
            } else {
               ingredients = MapItemStackIngredient.from(r);
            }

            for(int i = 0; i < ingredients.size(); ++i) {
               AbstractMapIngredient mappedIngredient = (AbstractMapIngredient)ingredients.get(i);
               WeakReference<AbstractMapIngredient> cached = (WeakReference)ingredientRoot.get(mappedIngredient);
               if (cached != null && cached.get() != null) {
                  ingredients.set(i, (AbstractMapIngredient)cached.get());
               } else {
                  ingredientRoot.put(mappedIngredient, new WeakReference(mappedIngredient));
               }
            }

            list.add(ingredients);
         }
      }

   }

   protected void buildFromItemStacks(@NotNull List<List<AbstractMapIngredient>> list, @NotNull ItemStack[] ingredients) {
      for(ItemStack stack : ingredients) {
         int meta = stack.func_77960_j();
         NBTTagCompound nbt = stack.func_77978_p();
         List<AbstractMapIngredient> ls = new ObjectArrayList(1);
         ls.add(new MapItemStackIngredient(stack, meta, nbt));
         if (this.hasOreDictedInputs) {
            for(int i : OreDictionary.getOreIDs(stack)) {
               AbstractMapIngredient ingredient = new MapOreDictIngredient(i);
               ls.add(ingredient);
               if (this.hasNBTMatcherInputs) {
                  AbstractMapIngredient var15 = new MapOreDictNBTIngredient(i, nbt);
                  ls.add(var15);
               }
            }
         }

         if (this.hasNBTMatcherInputs) {
            ls.add(new MapItemStackNBTIngredient(stack, meta, nbt));
         }

         if (!ls.isEmpty()) {
            list.add(ls);
         }
      }

   }

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   protected RecipeMap<R> setSpecialTexture(int x, int y, int width, int height, TextureArea area) {
      this.recipeMapUI.setSpecialTexture(area, x, y, width, height);
      return this;
   }

   /** @deprecated */
   @Deprecated
   @ScheduledForRemoval(
      inVersion = "2.9"
   )
   protected ModularUI.Builder addSpecialTexture(ModularUI.Builder builder) {
      return this.recipeMapUI.addSpecialTexture(builder);
   }

   public Collection<Recipe> getRecipeList() {
      ObjectOpenHashSet<Recipe> recipes = new ObjectOpenHashSet();
      Stream var10000 = this.lookup.getRecipes(true);
      Objects.requireNonNull(recipes);
      return (Collection)var10000.filter(recipes::add).sorted(RECIPE_DURATION_THEN_EU).collect(Collectors.toList());
   }

   public @Nullable SoundEvent getSound() {
      return this.sound;
   }

   @ZenMethod("findRecipe")
   @Method(
      modid = "crafttweaker"
   )
   public @Nullable CTRecipe ctFindRecipe(long maxVoltage, IItemStack[] itemInputs, ILiquidStack[] fluidInputs, @Optional(valueLong = 2147483647L) int outputFluidTankCapacity) {
      List<ItemStack> mcItemInputs = itemInputs == null ? Collections.emptyList() : (List)Arrays.stream(itemInputs).map(CraftTweakerMC::getItemStack).collect(Collectors.toList());
      List<FluidStack> mcFluidInputs = fluidInputs == null ? Collections.emptyList() : (List)Arrays.stream(fluidInputs).map(CraftTweakerMC::getLiquidStack).collect(Collectors.toList());
      Recipe backingRecipe = this.findRecipe(maxVoltage, mcItemInputs, mcFluidInputs, true);
      return backingRecipe == null ? null : new CTRecipe(this, backingRecipe);
   }

   @ZenGetter("recipes")
   @Method(
      modid = "crafttweaker"
   )
   public List<CTRecipe> ctGetRecipeList() {
      return (List)this.getRecipeList().stream().map((recipe) -> new CTRecipe(this, recipe)).collect(Collectors.toList());
   }

   @ZenGetter("localizedName")
   public String getLocalizedName() {
      return LocalizationUtils.format(this.getTranslationKey(), new Object[0]);
   }

   @ZenGetter("translationKey")
   public String getTranslationKey() {
      return "recipemap." + this.unlocalizedName + ".name";
   }

   @ZenGetter("unlocalizedName")
   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   public R recipeBuilder() {
      return (R)this.recipeBuilderSample.copy();
   }

   private @Nullable Recipe recurseIngredientTreeRemove(@NotNull Recipe recipeToRemove, @NotNull List<List<AbstractMapIngredient>> ingredients, @NotNull Branch branchMap, int depth) {
      for(List<AbstractMapIngredient> current : ingredients) {
         for(AbstractMapIngredient obj : current) {
            Map<AbstractMapIngredient, Either<Recipe, Branch>> targetMap = determineRootNodes(obj, branchMap);
            Recipe found = null;
            Either<Recipe, Branch> result = (Either)targetMap.get(obj);
            if (result != null) {
               Recipe r = (Recipe)result.map((potentialRecipe) -> potentialRecipe, (potentialBranch) -> this.recurseIngredientTreeRemove(recipeToRemove, ingredients.subList(1, ingredients.size()), potentialBranch, depth + 1));
               if (r == recipeToRemove) {
                  found = r;
               } else {
                  if (recipeToRemove.getIsCTRecipe()) {
                     CraftTweakerAPI.logError(String.format("Failed to remove Recipe from RecipeMap %s: %s", this.unlocalizedName, CTRecipeHelper.getRecipeRemoveLine(this, recipeToRemove)));
                  }

                  if (ConfigHolder.misc.debug || GTValues.isDeobfEnvironment()) {
                     GTLog.logger.warn("Failed to remove recipe from RecipeMap {}. See next lines for details", this.unlocalizedName);
                     GTLog.logger.warn("Failed to remove Recipe: {}", recipeToRemove.toString());
                  }
               }
            }

            if (found != null) {
               if (ingredients.size() == 1) {
                  targetMap.remove(obj);
               } else if (((Either)targetMap.get(obj)).right().isPresent()) {
                  Branch branch = (Branch)((Either)targetMap.get(obj)).right().get();
                  if (branch.isEmptyBranch()) {
                     targetMap.remove(obj);
                  }
               }

               return found;
            }
         }
      }

      return null;
   }

   @ZenMethod("recipeBuilder")
   @Method(
      modid = "crafttweaker"
   )
   public CTRecipeBuilder ctRecipeBuilder() {
      return new CTRecipeBuilder(this.recipeBuilder());
   }

   @ZenGetter("maxInputs")
   public int getMaxInputs() {
      return this.maxInputs;
   }

   @ZenSetter("maxInputs")
   public void setMaxInputs(int maxInputs) {
      this.maxInputs = Math.max(this.maxInputs, maxInputs);
      if (!this.recipeMapUI.canModifyItemInputs()) {
         GTLog.logger.warn("RecipeMap {} ui does not support changing max item inputs. Replace with a supporting UI for proper behavior.", this.getUnlocalizedName(), new Throwable());
      }

   }

   @ZenGetter("maxOutputs")
   public int getMaxOutputs() {
      return this.maxOutputs;
   }

   @ZenSetter("maxOutputs")
   public void setMaxOutputs(int maxOutputs) {
      this.maxOutputs = Math.max(this.maxOutputs, maxOutputs);
      if (!this.recipeMapUI.canModifyItemOutputs()) {
         GTLog.logger.warn("RecipeMap {} ui does not support changing max item outputs. Replace with a supporting UI for proper behavior.", this.getUnlocalizedName(), new Throwable());
      }

   }

   @ZenGetter("maxFluidInputs")
   public int getMaxFluidInputs() {
      return this.maxFluidInputs;
   }

   @ZenSetter("maxFluidInputs")
   public void setMaxFluidInputs(int maxFluidInputs) {
      this.maxFluidInputs = Math.max(this.maxFluidInputs, maxFluidInputs);
      if (!this.recipeMapUI.canModifyFluidInputs()) {
         GTLog.logger.warn("RecipeMap {} ui does not support changing max fluid inputs. Replace with a supporting UI for proper behavior.", this.getUnlocalizedName(), new Throwable());
      }

   }

   @ZenGetter("maxFluidOutputs")
   public int getMaxFluidOutputs() {
      return this.maxFluidOutputs;
   }

   @ZenSetter("maxFluidOutputs")
   public void setMaxFluidOutputs(int maxFluidOutputs) {
      this.maxFluidOutputs = Math.max(this.maxFluidOutputs, maxFluidOutputs);
      if (!this.recipeMapUI.canModifyFluidOutputs()) {
         GTLog.logger.warn("RecipeMap {} ui does not support changing max fluid outputs. Replace with a supporting UI for proper behavior.", this.getUnlocalizedName(), new Throwable());
      }

   }

   public @NotNull Map<GTRecipeCategory, List<Recipe>> getRecipesByCategory() {
      return Collections.unmodifiableMap(this.recipeByCategory);
   }

   public @NotNull RecipeMapUI<?> getRecipeMapUI() {
      return this.recipeMapUI;
   }

   public void setRecipeMapUI(@NotNull RecipeMapUI<?> recipeMapUI) {
      if (this.recipeMapUI.recipeMap() != recipeMapUI.recipeMap()) {
         throw new IllegalArgumentException("RecipeMap UI RecipeMap '" + recipeMapUI.recipeMap().unlocalizedName + "' does not match this RecipeMap '" + this.unlocalizedName + "'");
      } else {
         this.recipeMapUI = recipeMapUI;
      }
   }

   public @NotNull GTRecipeCategory getPrimaryRecipeCategory() {
      return this.primaryRecipeCategory;
   }

   @ZenMethod
   public String toString() {
      return "RecipeMap{" + this.unlocalizedName + '}';
   }

   public int hashCode() {
      return this.unlocalizedName.hashCode();
   }

   public boolean equals(Object obj) {
      return !(obj instanceof RecipeMap) ? false : ((RecipeMap)obj).unlocalizedName.equals(this.unlocalizedName);
   }

   static {
      DEFAULT_CHANCE_FUNCTION = ChanceBoostFunction.OVERCLOCK;
      ingredientRoot = new WeakHashMap();
   }
}
