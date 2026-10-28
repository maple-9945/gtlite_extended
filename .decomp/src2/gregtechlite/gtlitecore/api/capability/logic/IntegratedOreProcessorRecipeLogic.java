package gregtechlite.gtlitecore.api.capability.logic;

import gregtech.api.GTValues;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.chance.output.impl.ChancedItemOutput;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.util.GTUtility;
import gregtechlite.gtlitecore.api.extension.ItemStackExtKt;
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps;
import gregtechlite.gtlitecore.api.recipe.util.OreProcessorRecipeWrapper;
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockIntegratedOreProcessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0016\u0010\r\u001a\u00020\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0015"},
   d2 = {"Lgregtechlite/gtlitecore/api/capability/logic/IntegratedOreProcessorRecipeLogic;", "Lgregtech/api/capability/impl/MultiblockRecipeLogic;", "mte", "Lgregtechlite/gtlitecore/common/metatileentity/multiblock/MultiblockIntegratedOreProcessor;", "<init>", "(Lgregtechlite/gtlitecore/common/metatileentity/multiblock/MultiblockIntegratedOreProcessor;)V", "getMte", "()Lgregtechlite/gtlitecore/common/metatileentity/multiblock/MultiblockIntegratedOreProcessor;", "update", "", "recipeProcessing", "getParallelLimit", "", "getMaxFluidDeductionMultiplier", "inputFluids", "", "Lnet/minecraftforge/fluids/FluidStack;", "shouldProcessRecipe", "", "getProgressPercent", "", "gtlitecore"}
)
public class IntegratedOreProcessorRecipeLogic extends MultiblockRecipeLogic {
   @NotNull
   private final MultiblockIntegratedOreProcessor mte;

   public IntegratedOreProcessorRecipeLogic(@NotNull MultiblockIntegratedOreProcessor mte) {
      Intrinsics.checkNotNullParameter(mte, "mte");
      super(mte, true);
      this.mte = mte;
   }

   @NotNull
   public final MultiblockIntegratedOreProcessor getMte() {
      return this.mte;
   }

   public void update() {
      World world = this.mte.getWorld();
      if (world != null && !world.field_72995_K) {
         if (this.isWorkingEnabled()) {
            if (this.progressTime > 0) {
               this.setActive(true);
               this.updateRecipeProgress();
            }

            if (this.progressTime == 0 && this.shouldProcessRecipe()) {
               this.recipeProcessing();
            }
         }

         if (this.wasActiveAndNeedsUpdate) {
            this.wasActiveAndNeedsUpdate = false;
            this.setActive(false);
         }
      }

   }

   private final void recipeProcessing() {
      int processedCount = 0;
      long remainingVoltage = this.getMaxVoltage();
      List inputFluid = GTUtility.fluidHandlerToList(this.mte.getInputFluidInventory());
      int mode = -1;
      IItemHandlerModifiable inputInventory = this.mte.getInputInventory();
      if (inputInventory != null) {
         int i = 0;

         for(int var8 = inputInventory.getSlots(); i < var8; ++i) {
            ItemStack var10000 = inputInventory.getStackInSlot(i);
            Intrinsics.checkNotNullExpressionValue(var10000, "getStackInSlot(...)");
            ItemStack stack = var10000;
            if (!stack.func_190926_b() && IntCircuitIngredient.isIntegratedCircuit(stack)) {
               mode = IntCircuitIngredient.getCircuitConfiguration(stack);
               break;
            }
         }

         if (mode > 0) {
            List inputStacks = (List)(new ArrayList());
            int i = 0;

            for(int outputStacks = inputInventory.getSlots(); i < outputStacks; ++i) {
               ItemStack var52 = inputInventory.getStackInSlot(i);
               Intrinsics.checkNotNullExpressionValue(var52, "getStackInSlot(...)");
               ItemStack stack = var52;
               if (!stack.func_190926_b()) {
                  ItemStack var10001 = stack.func_77946_l();
                  Intrinsics.checkNotNullExpressionValue(var10001, "copy(...)");
                  inputStacks.add(var10001);
               }
            }

            List compressedInputStacks = OreProcessorRecipeWrapper.INSTANCE.compressOutput(inputStacks);
            inputStacks.clear();
            inputStacks.addAll((Collection)compressedInputStacks);
            List outputStacks = (List)(new ArrayList());
            List consumedItemStacks = (List)(new ArrayList());
            List consumedFluidStacks = (List)(new ArrayList());

            for(ItemStack inputStack : inputStacks) {
               if (processedCount >= this.getParallelLimit()) {
                  break;
               }

               RecipeMap var53 = GTLiteRecipeMaps.INTEGRATED_ORE_PROCESSOR_RECIPES;
               long var58 = GTValues.V[1];
               ItemStack[] var15 = new ItemStack[]{inputStack, IntCircuitIngredient.getIntegratedCircuit(mode)};
               List var10002 = CollectionsKt.listOf(var15);
               Intrinsics.checkNotNull(inputFluid);
               Recipe recipe = var53.findRecipe(var58, var10002, CollectionsKt.toList((Iterable)inputFluid));
               if (recipe != null) {
                  int countToProcess = (int)RangesKt.coerceAtMost(RangesKt.coerceAtMost((long)inputStack.func_190916_E(), (long)(this.getParallelLimit() - processedCount) + 0L), remainingVoltage / recipe.getEUt());
                  List fluidInputStacks = (List)(new ArrayList());

                  for(GTRecipeInput fluidInput : recipe.getFluidInputs()) {
                     FluidStack var59 = fluidInput.getInputFluidStack();
                     Intrinsics.checkNotNullExpressionValue(var59, "getInputFluidStack(...)");
                     fluidInputStacks.add(var59);
                  }

                  countToProcess = RangesKt.coerceAtMost(countToProcess, this.getMaxFluidDeductionMultiplier(fluidInputStacks));
                  if (countToProcess == 0) {
                     return;
                  }

                  remainingVoltage -= (long)countToProcess * recipe.getEUt();
                  processedCount += countToProcess;
                  ItemStack consumedItemStack = inputStack.func_77946_l();
                  consumedItemStack.func_190920_e(countToProcess);
                  Intrinsics.checkNotNull(consumedItemStack);
                  consumedItemStacks.add(consumedItemStack);

                  for(FluidStack inputFluidStack : fluidInputStacks) {
                     FluidStack consumedFluidStack = inputFluidStack.copy();
                     consumedFluidStack.amount *= countToProcess;
                     Intrinsics.checkNotNull(consumedFluidStack);
                     consumedFluidStacks.add(consumedFluidStack);
                  }

                  for(ItemStack recipeOutputStack : recipe.getOutputs()) {
                     ItemStack outputStack = recipeOutputStack.func_77946_l();
                     outputStack.func_190920_e(outputStack.func_190916_E() * countToProcess);
                     Intrinsics.checkNotNull(outputStack);
                     outputStacks.add(outputStack);
                  }

                  Random random = GTValues.RNG;

                  for(Object var54 : recipe.getChancedOutputs().getChancedEntries()) {
                     Intrinsics.checkNotNullExpressionValue(var54, "next(...)");
                     ChancedItemOutput recipeChancedOutput = (ChancedItemOutput)var54;
                     int amount = RangesKt.coerceAtLeast(recipeChancedOutput.getChance() * countToProcess, 10000);
                     Object var55 = recipeChancedOutput.getIngredient();
                     Intrinsics.checkNotNullExpressionValue(var55, "getIngredient(...)");
                     ItemStack stack = ItemStackExtKt.copy((ItemStack)var55, amount / 10000 + (random.nextInt(10000) < amount % 10000 ? 1 : 0));
                     outputStacks.add(stack);
                  }
               }
            }

            if (!outputStacks.isEmpty()) {
               for(ItemStack itemStack : consumedItemStacks) {
                  int remainingToExtract = itemStack.func_190916_E();
                  int i = 0;

                  for(int var41 = inputInventory.getSlots(); i < var41; ++i) {
                     ItemStack var56 = inputInventory.getStackInSlot(i);
                     Intrinsics.checkNotNullExpressionValue(var56, "getStackInSlot(...)");
                     ItemStack inputStack = var56;
                     if (!inputStack.func_190926_b() && inputStack.func_77969_a(itemStack)) {
                        int countToExtract = Math.min(remainingToExtract, inputStack.func_190916_E());
                        if (countToExtract > 0) {
                           inputInventory.extractItem(i, countToExtract, false);
                           remainingToExtract -= countToExtract;
                           if (remainingToExtract == 0) {
                              break;
                           }
                        }
                     }
                  }
               }

               for(FluidStack fluidStack : consumedFluidStacks) {
                  IMultipleTankHandler var57 = this.mte.getInputFluidInventory();
                  if (var57 != null) {
                     var57.drain(fluidStack, true);
                  }
               }

               int ocMult = RangesKt.coerceAtLeast((int)Math.pow(Math.floor((double)1.0F * (double)this.getMaxVoltage() / (double)(this.getMaxVoltage() - remainingVoltage)), (double)0.5F), 1);
               this.progressTime = 1;
               this.setMaxProgress(RangesKt.coerceAtLeast(100 / ocMult, 1));
               this.recipeEUt = (this.getMaxVoltage() - remainingVoltage) * (long)(10000 / this.getMaxProgress() / this.getMaxProgress());
               List compressedOutputStacks = OreProcessorRecipeWrapper.INSTANCE.compressOutput(outputStacks);
               this.itemOutputs = (List)NonNullList.func_191196_a();

               for(ItemStack itemStack : compressedOutputStacks) {
                  this.itemOutputs.add(itemStack);
               }

            }
         }
      }
   }

   public int getParallelLimit() {
      return this.getMaxVoltage() <= GTValues.V[5] ? 1024 : (int)Math.ceil((double)1024 * Math.pow((double)1.0F * (double)this.getMaxVoltage() / (double)GTValues.V[5], 0.292481));
   }

   private final int getMaxFluidDeductionMultiplier(List<? extends FluidStack> inputFluids) {
      long maxMultiplier = (long)this.getParallelLimit();

      for(FluidStack fluidStack : inputFluids) {
         long remainingCount = 0L;

         for(FluidStack existingFluidStack : GTUtility.fluidHandlerToList(this.mte.getInputFluidInventory())) {
            if (existingFluidStack != null && existingFluidStack.isFluidEqual(fluidStack)) {
               remainingCount += (long)existingFluidStack.amount;
            }
         }

         maxMultiplier = RangesKt.coerceAtMost(maxMultiplier, remainingCount / (long)fluidStack.amount);
      }

      return (int)maxMultiplier;
   }

   private final boolean shouldProcessRecipe() {
      return true;
   }

   public double getProgressPercent() {
      return this.getMaxProgress() == 0 ? (double)0.0F : (double)this.getProgress() / ((double)this.getMaxProgress() * (double)1.0F);
   }
}
