package gregtechlite.gtlitecore.loader.recipe.handler;

import gregtech.api.items.toolitem.IGTTool;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.MarkerMaterials.Color;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.ToolItems;
import gregtechlite.gtlitecore.api.unification.material.properties.GTLiteToolPropertyAdder;
import gregtechlite.gtlitecore.common.item.GTLiteToolItems;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u0006\u001a\u00020\u0005J \u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u0005H\u0002J9\u0010\u000f\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0015\"\u00020\u0001H\u0002¢\u0006\u0002\u0010\u0016¨\u0006\u0017"},
   d2 = {"Lgregtechlite/gtlitecore/loader/recipe/handler/ToolRecipeHandler;", "", "<init>", "()V", "init", "", "registerCustomToolRecipes", "processTool", "toolPrefix", "Lgregtech/api/unification/ore/OrePrefix;", "material", "Lgregtech/api/unification/material/Material;", "property", "Lgregtech/api/unification/material/properties/ToolProperty;", "registerSoftToolRecipes", "addToolRecipe", "tool", "Lgregtech/api/items/toolitem/IGTTool;", "mirrored", "", "recipe", "", "(Lgregtech/api/unification/material/Material;Lgregtech/api/items/toolitem/IGTTool;Z[Ljava/lang/Object;)V", "gtlitecore"}
)
public final class ToolRecipeHandler {
   @NotNull
   public static final ToolRecipeHandler INSTANCE = new ToolRecipeHandler();

   private ToolRecipeHandler() {
   }

   public final void init() {
      OrePrefix.ingot.addProcessingHandler(PropertyKey.TOOL, this::processTool);
   }

   public final void registerCustomToolRecipes() {
      this.registerSoftToolRecipes();
   }

   private final void processTool(OrePrefix toolPrefix, Material material, ToolProperty property) {
      if (material.hasProperty(PropertyKey.INGOT)) {
         if (material.hasFlag(MaterialFlags.GENERATE_PLATE)) {
            IGTTool var10002 = GTLiteToolItems.INSTANCE.getCOMBINATION_WRENCH();
            Object[] var4 = new Object[]{"PhP", "IPI", "fP ", 'I', new UnificationEntry(OrePrefix.ingot, material), 'P', new UnificationEntry(OrePrefix.plate, material)};
            this.addToolRecipe(material, var10002, true, var4);
         }

         if (material.hasFlag(MaterialFlags.GENERATE_ROD)) {
            IGTTool var13 = GTLiteToolItems.INSTANCE.getROLLING_PIN();
            Object[] var5 = new Object[]{"  R", " I ", "R f", 'R', new UnificationEntry(OrePrefix.stick, material), 'I', new UnificationEntry(OrePrefix.ingot, material)};
            this.addToolRecipe(material, var13, true, var5);
            var13 = GTLiteToolItems.INSTANCE.getCLUB();
            var5 = new Object[]{"hII", "III", "RIf", 'I', new UnificationEntry(OrePrefix.ingot, material), 'R', new UnificationEntry(OrePrefix.stick, material)};
            this.addToolRecipe(material, var13, true, var5);
         }

         MaterialFlag[] var7 = new MaterialFlag[]{MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_ROD};
         if (material.hasFlags(var7)) {
            IGTTool var15 = GTLiteToolItems.INSTANCE.getUNIVERSAL_SPADE();
            Object[] var8 = new Object[]{"hPP", "DRP", "RDf", 'P', new UnificationEntry(OrePrefix.plate, material), 'R', new UnificationEntry(OrePrefix.stick, material), 'D', new UnificationEntry(OrePrefix.dye, (Material)Color.Blue)};
            this.addToolRecipe(material, var15, true, var8);
         }

         var7 = new MaterialFlag[]{MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_LONG_ROD, MaterialFlags.GENERATE_RING, MaterialFlags.GENERATE_BOLT_SCREW};
         if (material.hasFlags(var7)) {
            IGTTool var16 = GTLiteToolItems.INSTANCE.getMULTITOOL_CLOSED();
            Object[] var10 = new Object[]{"wPR", "PPS", "RSB", 'B', new UnificationEntry(OrePrefix.screw, material), 'S', new UnificationEntry(OrePrefix.stickLong, material), 'P', new UnificationEntry(OrePrefix.plate, material), 'R', new UnificationEntry(OrePrefix.ring, material)};
            this.addToolRecipe(material, var16, true, var10);
         }

         var7 = new MaterialFlag[]{MaterialFlags.GENERATE_SPRING_SMALL, MaterialFlags.GENERATE_SMALL_GEAR};
         if (material.hasFlags(var7) && !Intrinsics.areEqual(material, Materials.Steel)) {
            IGTTool var17 = GTLiteToolItems.INSTANCE.getFLINT_AND_STEEL();
            Object[] var12 = new Object[]{" G ", " F ", " S ", 'G', new UnificationEntry(OrePrefix.gearSmall, material), 'S', new UnificationEntry(OrePrefix.springSmall, material), 'F', new ItemStack(Items.field_151145_ak)};
            this.addToolRecipe(material, var17, false, var12);
         }
      }

   }

   private final void registerSoftToolRecipes() {
      List softMaterials = GTLiteToolPropertyAdder.INSTANCE.getSoftMaterials$gtlitecore();
      UnificationEntry stick = new UnificationEntry(OrePrefix.stick, Materials.Wood);

      for(Pair mat : softMaterials) {
         Material material = (Material)mat.getFirst();
         IGTTool var10002 = ToolItems.SOFT_MALLET;
         Intrinsics.checkNotNullExpressionValue(var10002, "SOFT_MALLET");
         Object[] var6 = new Object[]{"II ", "IIS", "II ", 'I', new UnificationEntry(OrePrefix.ingot, material), 'S', stick};
         this.addToolRecipe(material, var10002, true, var6);
         var10002 = ToolItems.PLUNGER;
         Intrinsics.checkNotNullExpressionValue(var10002, "PLUNGER");
         var6 = new Object[]{"xPP", " SP", "S f", 'P', new UnificationEntry(OrePrefix.plate, material), 'S', stick};
         this.addToolRecipe(material, var10002, true, var6);
      }

   }

   private final void addToolRecipe(Material material, IGTTool tool, boolean mirrored, Object... recipe) {
      String toolId = tool.getToolId();
      ItemStack toolMaterial = tool.get(material);
      if (mirrored) {
         ModHandler.addMirroredShapedRecipe(toolId + '_' + material, toolMaterial, Arrays.copyOf(recipe, recipe.length));
      } else {
         ModHandler.addShapedRecipe(toolId + '_' + material, toolMaterial, Arrays.copyOf(recipe, recipe.length));
      }

   }
}
