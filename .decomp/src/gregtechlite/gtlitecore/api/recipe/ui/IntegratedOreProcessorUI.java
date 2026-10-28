package gregtechlite.gtlitecore.api.recipe.ui;

import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.Widget;
import gregtech.api.gui.resources.IGuiTexture;
import gregtech.api.gui.widgets.RecipeProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ui.RecipeMapUI;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u0000*\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0004\b\u0005\u0010\u0006J0\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J0\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0010H\u0014¨\u0006\u0018"},
   d2 = {"Lgregtechlite/gtlitecore/api/recipe/ui/IntegratedOreProcessorUI;", "R", "Lgregtech/api/recipes/RecipeMap;", "Lgregtech/api/recipes/ui/RecipeMapUI;", "recipeMap", "<init>", "(Lgregtech/api/recipes/RecipeMap;)V", "createJeiUITemplate", "Lgregtech/api/gui/ModularUI$Builder;", "importItems", "Lnet/minecraftforge/items/IItemHandlerModifiable;", "exportItems", "importFluids", "Lgregtech/api/capability/impl/FluidTankList;", "exportFluids", "yOffset", "", "addInventorySlotGroup", "", "builder", "itemHandler", "fluidHandler", "isOutputs", "", "gtlitecore"}
)
public final class IntegratedOreProcessorUI<R extends RecipeMap<?>> extends RecipeMapUI<R> {
   public IntegratedOreProcessorUI(@NotNull R recipeMap) {
      Intrinsics.checkNotNullParameter(recipeMap, "recipeMap");
      super(recipeMap, true, true, true, true, false);
      this.setItemSlotOverlay(GuiTextures.DUST_OVERLAY, true);
      this.setProgressBarTexture(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE);
   }

   @NotNull
   public ModularUI.Builder createJeiUITemplate(@NotNull IItemHandlerModifiable importItems, @NotNull IItemHandlerModifiable exportItems, @NotNull FluidTankList importFluids, @NotNull FluidTankList exportFluids, int yOffset) {
      Intrinsics.checkNotNullParameter(importItems, "importItems");
      Intrinsics.checkNotNullParameter(exportItems, "exportItems");
      Intrinsics.checkNotNullParameter(importFluids, "importFluids");
      Intrinsics.checkNotNullParameter(exportFluids, "exportFluids");
      ModularUI.Builder builder = (new ModularUI.Builder((IGuiTexture)GuiTextures.BACKGROUND, 176, 156)).widget((Widget)(new RecipeProgressWidget(200, 70, 25, 22, 22, this.progressBarTexture(), this.progressBarMoveType(), this.recipeMap())));
      Intrinsics.checkNotNull(builder);
      this.addInventorySlotGroup(builder, importItems, importFluids, false, yOffset);
      this.addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset);
      return builder;
   }

   protected void addInventorySlotGroup(@NotNull ModularUI.Builder builder, @NotNull IItemHandlerModifiable itemHandler, @NotNull FluidTankList fluidHandler, boolean isOutputs, int yOffset) {
      Intrinsics.checkNotNullParameter(builder, "builder");
      Intrinsics.checkNotNullParameter(itemHandler, "itemHandler");
      Intrinsics.checkNotNullParameter(fluidHandler, "fluidHandler");
      int startInputsX = 27;
      int startInputsY = 36;
      int startOutputsX = startInputsX + 70;
      if (!isOutputs) {
         for(int h = 0; h < 2; ++h) {
            for(int w = 0; w < 2; ++w) {
               int slotIdx = h * 2 + w;
               this.addSlot(builder, startInputsX + w * 18, h * 18, slotIdx, itemHandler, fluidHandler, false, false);
            }
         }

         for(int h = 0; h < 2; ++h) {
            for(int w = 0; w < 2; ++w) {
               int slotIdx = h * 2 + w;
               this.addSlot(builder, startInputsX + w * 18, startInputsY + h * 18, slotIdx, itemHandler, fluidHandler, true, false);
            }
         }
      } else {
         for(int h = 0; h < 4; ++h) {
            for(int w = 0; w < 3; ++w) {
               int slotIdx = h * 3 + w;
               this.addSlot(builder, startOutputsX + 18 * w, h * 18, slotIdx, itemHandler, fluidHandler, false, true);
            }
         }
      }

   }
}
