package gregtechlite.gtlitecore.api.recipe;

import com.morphismmc.morphismlib.integration.Mods;
import crafttweaker.annotations.ZenRegister;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget.MoveType;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMapBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.BlastRecipeBuilder;
import gregtech.api.recipes.builders.FuelRecipeBuilder;
import gregtech.api.recipes.builders.PrimitiveRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ui.RecipeMapUI;
import gregtech.api.unification.material.Materials;
import gregtech.core.sound.GTSoundEvents;
import gregtechlite.gtlitecore.GTLiteMod;
import gregtechlite.gtlitecore.api.extension.ItemStackExtKt;
import gregtechlite.gtlitecore.api.extension.RecipeBuilderExtKt;
import gregtechlite.gtlitecore.api.gui.GTLiteGuiTextures;
import gregtechlite.gtlitecore.api.recipe.builder.AccelerationTrackRecipeBuilder;
import gregtechlite.gtlitecore.api.recipe.builder.AdvancedFusionRecipeBuilder;
import gregtechlite.gtlitecore.api.recipe.builder.CircuitAssemblyLineRecipeBuilder;
import gregtechlite.gtlitecore.api.recipe.builder.ComponentAssemblyLineRecipeBuilder;
import gregtechlite.gtlitecore.api.recipe.builder.MinimumHeightRecipeBuilder;
import gregtechlite.gtlitecore.api.recipe.builder.MobProximityRecipeBuilder;
import gregtechlite.gtlitecore.api.recipe.builder.NanoForgeRecipeBuilder;
import gregtechlite.gtlitecore.api.recipe.builder.NoCoilTemperatureRecipeBuilder;
import gregtechlite.gtlitecore.api.recipe.builder.PCBFactoryRecipeBuilder;
import gregtechlite.gtlitecore.api.recipe.builder.PseudoMultiRecipeBuilder;
import gregtechlite.gtlitecore.api.recipe.builder.QuantumForceTransformerRecipeBuilder;
import gregtechlite.gtlitecore.api.recipe.map.PseudoGroupRecipeMapBuilder;
import gregtechlite.gtlitecore.api.recipe.ui.AntimatterForgeUI;
import gregtechlite.gtlitecore.api.recipe.ui.BlackholeFormerUI;
import gregtechlite.gtlitecore.api.recipe.ui.ComponentAssemblyLineUI;
import gregtechlite.gtlitecore.api.recipe.ui.ConcentratedCokingClusterUI;
import gregtechlite.gtlitecore.api.recipe.ui.IntegratedOreProcessorUI;
import gregtechlite.gtlitecore.api.recipe.ui.LargeMixerUI;
import gregtechlite.gtlitecore.api.recipe.ui.MiningDroneAirportUI;
import gregtechlite.gtlitecore.api.recipe.ui.NanoAssemblyMatrixUI;
import gregtechlite.gtlitecore.api.recipe.ui.SingularityAssemblyUnitUI;
import gregtechlite.gtlitecore.api.recipe.ui.SpaceAssemblerUI;
import gregtechlite.gtlitecore.api.recipe.ui.StellarForgeUI;
import gregtechlite.gtlitecore.core.sound.GTLiteSoundEvents;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenClass("mods.gtlitecore.recipe.RecipeMaps")
@ZenRegister
@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010V\u001a\u00020WH\u0007R#\u0010\u0004\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\t\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\n\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u000b\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\f\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\r\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u000e\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u000f\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u0010\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00110\u00110\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u0012\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u0013\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u0014\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u0015\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u0016\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u0017\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u0018\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u0019\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u001a\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u001b0\u001b0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u001c\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010\u001d\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u001e0\u001e0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010 \u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010#\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010$0$0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010%\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010&\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010'0'0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010(\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010)\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010*0*0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010+\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010$0$0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010,\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010*0*0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010-\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010.\u001a\b\u0012\u0004\u0012\u00020*0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010/\u001a\b\u0012\u0004\u0012\u00020*0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u00100\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u00101\u001a\b\u0012\u0004\u0012\u0002020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u00103\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u000104040\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u00105\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u000106060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u00107\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u00108\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u000109090\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010:\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010;0;0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010<\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010=0=0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010>\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010?\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010@0@0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010A\u001a\b\u0012\u0004\u0012\u00020$0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001e\u0010B\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010E\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001e\u0010G\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001e\u0010H\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010J\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010K\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010L\u001a\b\u0012\u0004\u0012\u00020$0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010M\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010N0N0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010O\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010N0N0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010P\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010N0N0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010Q\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010N0N0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010R\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010N0N0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010S\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010N0N0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010T\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010N0N0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R#\u0010U\u001a\u0015\u0012\f\u0012\n \u0007*\u0004\u0018\u00010N0N0\u0005¢\u0006\u0002\b\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006X"},
   d2 = {"Lgregtechlite/gtlitecore/api/recipe/GTLiteRecipeMaps;", "", "<init>", "()V", "POLISHER_RECIPES", "Lgregtech/api/recipes/RecipeMap;", "Lgregtech/api/recipes/builders/SimpleRecipeBuilder;", "kotlin.jvm.PlatformType", "Lorg/jetbrains/annotations/NotNull;", "SLICER_RECIPES", "TOOL_CASTER_RECIPES", "LOOM_RECIPES", "LAMINATOR_RECIPES", "CHEMICAL_DEHYDRATOR_RECIPES", "VULCANIZATION_RECIPES", "VACUUM_CHAMBER_RECIPES", "SAP_COLLECTOR_RECIPES", "Lgregtechlite/gtlitecore/api/recipe/builder/PseudoMultiRecipeBuilder;", "GREENHOUSE_RECIPES", "BIO_REACTOR_RECIPES", "ROASTER_RECIPES", "BURNER_REACTOR_RECIPES", "BATH_CONDENSER_RECIPES", "CRYOGENIC_REACTOR_RECIPES", "FOOD_PROCESSOR_RECIPES", "MULTICOOKER_RECIPES", "MOB_EXTRACTOR_RECIPES", "Lgregtechlite/gtlitecore/api/recipe/builder/MobProximityRecipeBuilder;", "BIO_SIMULATOR_RECIPES", "COAGULATION_RECIPES", "Lgregtech/api/recipes/builders/PrimitiveRecipeBuilder;", "LARGE_MIXER_RECIPES", "CATALYTIC_REFORMER_RECIPES", "LARGE_GAS_COLLECTOR_RECIPES", "ELECTRIC_IMPLOSION_RECIPES", "ALLOY_BLAST_RECIPES", "Lgregtech/api/recipes/builders/BlastRecipeBuilder;", "CHEMICAL_PLANT_RECIPES", "CIRCUIT_ASSEMBLY_LINE_RECIPES", "Lgregtechlite/gtlitecore/api/recipe/builder/CircuitAssemblyLineRecipeBuilder;", "MINING_DRONE_RECIPES", "CVD_RECIPES", "Lgregtechlite/gtlitecore/api/recipe/builder/NoCoilTemperatureRecipeBuilder;", "CRYSTALLIZATION_RECIPES", "MOLECULAR_BEAM_RECIPES", "SONICATION_RECIPES", "LASER_CVD_RECIPES", "PLASMA_CVD_RECIPES", "DRILLING_RECIPES", "ADVANCED_FUSION_RECIPES", "Lgregtechlite/gtlitecore/api/recipe/builder/AdvancedFusionRecipeBuilder;", "COMPONENT_ASSEMBLY_LINE_RECIPES", "Lgregtechlite/gtlitecore/api/recipe/builder/ComponentAssemblyLineRecipeBuilder;", "COSMIC_RAY_DETECTING_RECIPES", "Lgregtechlite/gtlitecore/api/recipe/builder/MinimumHeightRecipeBuilder;", "STELLAR_FORGE_RECIPES", "PCB_FACTORY_RECIPES", "Lgregtechlite/gtlitecore/api/recipe/builder/PCBFactoryRecipeBuilder;", "NANO_FORGE_RECIPES", "Lgregtechlite/gtlitecore/api/recipe/builder/NanoForgeRecipeBuilder;", "QUANTUM_FORCE_TRANSFORMER_RECIPES", "Lgregtechlite/gtlitecore/api/recipe/builder/QuantumForceTransformerRecipeBuilder;", "ANTIMATTER_FORGE_RECIPES", "SPACE_ASSEMBLER_RECIPES", "Lgregtechlite/gtlitecore/api/recipe/builder/AccelerationTrackRecipeBuilder;", "TOPOLOGICAL_ORDER_CHANGING_RECIPES", "PLASMA_ARC_TRANSMITTER_RECIPES", "NANO_ASSEMBLY_MATRIX_RECIPES", "SINGULARITY_ASSEMBLY_RECIPES", "INTEGRATED_ORE_PROCESSOR_RECIPES", "MATTER_RESHAPING_RECIPES", "SOLIDIFICATION_SUBMODULE_RECIPES", "RECOMBINATION_SUBMODULE_RECIPES", "MOB_COLLECTING_RECIPES", "MOB_SLAUGHTERING_RECIPES", "BLACKHOLE_FORMING_RECIPES", "COMPLEX_PYROLYSIS_RECIPES", "HOT_COOLANT_TURBINE_FUELS", "Lgregtech/api/recipes/builders/FuelRecipeBuilder;", "SUPERCRITICAL_FLUID_TURBINE_FUELS", "NUCLEAR_FUELS", "ROCKET_ENGINE_FUELS", "NAQUADAH_REACTOR_FUELS", "ANTIMATTER_GENERATOR_FUELS", "DYSON_SWARM_FUELS", "ACID_GENERATOR_FUELS", "preInit", "", "gtlitecore"}
)
@SourceDebugExtension({"SMAP\nGTLiteRecipeMaps.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GTLiteRecipeMaps.kt\ngregtechlite/gtlitecore/api/recipe/GTLiteRecipeMaps\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1191:1\n37#2:1192\n36#2,3:1193\n37#2:1196\n36#2,3:1197\n37#2:1204\n36#2,3:1205\n37#2:1212\n36#2,3:1213\n37#2:1220\n36#2,3:1221\n37#2:1224\n36#2,3:1225\n1557#3:1200\n1628#3,3:1201\n1557#3:1208\n1628#3,3:1209\n1557#3:1216\n1628#3,3:1217\n*S KotlinDebug\n*F\n+ 1 GTLiteRecipeMaps.kt\ngregtechlite/gtlitecore/api/recipe/GTLiteRecipeMaps\n*L\n559#1:1192\n559#1:1193,3\n561#1:1196\n561#1:1197,3\n574#1:1204\n574#1:1205,3\n579#1:1212\n579#1:1213,3\n748#1:1220\n748#1:1221,3\n750#1:1224\n750#1:1225,3\n573#1:1200\n573#1:1201,3\n578#1:1208\n578#1:1209,3\n583#1:1216\n583#1:1217,3\n*E\n"})
public final class GTLiteRecipeMaps {
   @NotNull
   public static final GTLiteRecipeMaps INSTANCE = new GTLiteRecipeMaps();
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> POLISHER_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> SLICER_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> TOOL_CASTER_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> LOOM_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> LAMINATOR_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> CHEMICAL_DEHYDRATOR_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> VULCANIZATION_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> VACUUM_CHAMBER_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<PseudoMultiRecipeBuilder> SAP_COLLECTOR_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> GREENHOUSE_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> BIO_REACTOR_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> ROASTER_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> BURNER_REACTOR_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> BATH_CONDENSER_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> CRYOGENIC_REACTOR_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> FOOD_PROCESSOR_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> MULTICOOKER_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<MobProximityRecipeBuilder> MOB_EXTRACTOR_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> BIO_SIMULATOR_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<PrimitiveRecipeBuilder> COAGULATION_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> LARGE_MIXER_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> CATALYTIC_REFORMER_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> LARGE_GAS_COLLECTOR_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> ELECTRIC_IMPLOSION_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<BlastRecipeBuilder> ALLOY_BLAST_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> CHEMICAL_PLANT_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<CircuitAssemblyLineRecipeBuilder> CIRCUIT_ASSEMBLY_LINE_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> MINING_DRONE_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<NoCoilTemperatureRecipeBuilder> CVD_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<BlastRecipeBuilder> CRYSTALLIZATION_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<NoCoilTemperatureRecipeBuilder> MOLECULAR_BEAM_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> SONICATION_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<NoCoilTemperatureRecipeBuilder> LASER_CVD_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<NoCoilTemperatureRecipeBuilder> PLASMA_CVD_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> DRILLING_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<AdvancedFusionRecipeBuilder> ADVANCED_FUSION_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<ComponentAssemblyLineRecipeBuilder> COMPONENT_ASSEMBLY_LINE_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<MinimumHeightRecipeBuilder> COSMIC_RAY_DETECTING_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> STELLAR_FORGE_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<PCBFactoryRecipeBuilder> PCB_FACTORY_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<NanoForgeRecipeBuilder> NANO_FORGE_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<QuantumForceTransformerRecipeBuilder> QUANTUM_FORCE_TRANSFORMER_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> ANTIMATTER_FORGE_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<AccelerationTrackRecipeBuilder> SPACE_ASSEMBLER_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<BlastRecipeBuilder> TOPOLOGICAL_ORDER_CHANGING_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> PLASMA_ARC_TRANSMITTER_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> NANO_ASSEMBLY_MATRIX_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> SINGULARITY_ASSEMBLY_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> INTEGRATED_ORE_PROCESSOR_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> MATTER_RESHAPING_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> SOLIDIFICATION_SUBMODULE_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> RECOMBINATION_SUBMODULE_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> MOB_COLLECTING_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> MOB_SLAUGHTERING_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<SimpleRecipeBuilder> BLACKHOLE_FORMING_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<BlastRecipeBuilder> COMPLEX_PYROLYSIS_RECIPES;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<FuelRecipeBuilder> HOT_COOLANT_TURBINE_FUELS;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<FuelRecipeBuilder> SUPERCRITICAL_FLUID_TURBINE_FUELS;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<FuelRecipeBuilder> NUCLEAR_FUELS;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<FuelRecipeBuilder> ROCKET_ENGINE_FUELS;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<FuelRecipeBuilder> NAQUADAH_REACTOR_FUELS;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<FuelRecipeBuilder> ANTIMATTER_GENERATOR_FUELS;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<FuelRecipeBuilder> DYSON_SWARM_FUELS;
   @ZenProperty
   @JvmField
   @NotNull
   public static final RecipeMap<FuelRecipeBuilder> ACID_GENERATOR_FUELS;

   private GTLiteRecipeMaps() {
   }

   @JvmStatic
   public static final void preInit() {
      DYSON_SWARM_FUELS.getRecipeMapUI().setJEIVisible(false);
      RecipeMaps.ELECTROLYZER_RECIPES.setMaxFluidInputs(2);
      RecipeMaps.FORGE_HAMMER_RECIPES.setMaxInputs(2);
      RecipeMaps.SIFTER_RECIPES.setMaxInputs(2);
      RecipeMaps.SIFTER_RECIPES.setMaxFluidInputs(2);
      RecipeMaps.SIFTER_RECIPES.setMaxOutputs(9);
      RecipeMaps.SIFTER_RECIPES.setMaxFluidOutputs(2);
      RecipeMaps.SIFTER_RECIPES.getRecipeMapUI().setItemSlotOverlay(GuiTextures.DUST_OVERLAY, false);
      RecipeMaps.MASS_FABRICATOR_RECIPES.getRecipeMapUI().setItemSlotOverlay(GuiTextures.DUST_OVERLAY, false);
      RecipeMaps.REPLICATOR_RECIPES.setMaxFluidInputs(3);
      RecipeMaps.REPLICATOR_RECIPES.getRecipeMapUI().setItemSlotOverlay(GuiTextures.DUST_OVERLAY, false);
      RecipeMaps.REPLICATOR_RECIPES.getRecipeMapUI().setItemSlotOverlay(GuiTextures.DUST_OVERLAY, true);
      RecipeMaps.ARC_FURNACE_RECIPES.setMaxInputs(4);
      RecipeMaps.ARC_FURNACE_RECIPES.setMaxFluidInputs(2);
      RecipeMaps.ARC_FURNACE_RECIPES.getRecipeMapUI().setItemSlotOverlay(GuiTextures.LIGHTNING_OVERLAY_1, false);
      RecipeMaps.ARC_FURNACE_RECIPES.getRecipeMapUI().setFluidSlotOverlay(GuiTextures.LIGHTNING_OVERLAY_2, false);
      RecipeMaps.LASER_ENGRAVER_RECIPES.setMaxInputs(4);
      RecipeMaps.LASER_ENGRAVER_RECIPES.setMaxOutputs(4);
      RecipeMaps.LASER_ENGRAVER_RECIPES.setMaxFluidInputs(2);
      RecipeMaps.LASER_ENGRAVER_RECIPES.setMaxFluidOutputs(2);
      RecipeMaps.LASER_ENGRAVER_RECIPES.getRecipeMapUI().setItemSlotOverlay(GuiTextures.LENS_OVERLAY, false);
      RecipeMaps.BREWING_RECIPES.setMaxOutputs(1);
      RecipeMaps.CUTTER_RECIPES.setMaxOutputs(4);
      RecipeMaps.FERMENTING_RECIPES.setMaxInputs(2);
      RecipeMaps.FERMENTING_RECIPES.setMaxFluidInputs(2);
      RecipeMaps.FERMENTING_RECIPES.getRecipeMapUI().setItemSlotOverlay(GuiTextures.DUST_OVERLAY, false, false);
      RecipeMaps.THERMAL_CENTRIFUGE_RECIPES.setMaxOutputs(4);
      RecipeMaps.VACUUM_RECIPES.setMaxFluidOutputs(2);
      RecipeMaps.POLARIZER_RECIPES.setMaxFluidInputs(1);
      RecipeMaps.COMPRESSOR_RECIPES.setMaxFluidInputs(1);
      RecipeMaps.COMPRESSOR_RECIPES.setMaxFluidOutputs(1);
      if (Mods.EnderIO.isActive()) {
         BIO_SIMULATOR_RECIPES.setMaxFluidOutputs(1);
      }

      RecipeMaps.CHEMICAL_BATH_RECIPES.setMaxInputs(2);
   }

   private static final void POLISHER_RECIPES$lambda$0(SimpleRecipeBuilder recipeBuilder) {
      Intrinsics.checkNotNullParameter(recipeBuilder, "recipeBuilder");
      if (recipeBuilder.getFluidInputs().isEmpty()) {
         long eut = recipeBuilder.getEUt();
         int duration = recipeBuilder.getDuration();
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)recipeBuilder.copy().fluidInputs(Materials.Water.getFluid((int)Math.max(4L, Math.min(500L, (long)duration * eut / (long)320))))).duration(duration * 2)).buildAndRegister();
         RecipeBuilder var10000 = recipeBuilder.copy().fluidInputs(Materials.DistilledWater.getFluid((int)Math.max(3L, Math.min(250L, (long)duration * eut / (long)426))));
         Intrinsics.checkNotNullExpressionValue(var10000, "fluidInputs(...)");
         ((SimpleRecipeBuilder)RecipeBuilderExtKt.duration(var10000, (double)duration * (double)1.5F)).buildAndRegister();
         ((SimpleRecipeBuilder)recipeBuilder.fluidInputs(Materials.Lubricant.getFluid((int)Math.max(1L, Math.min(125L, (long)duration * eut / (long)1280))))).duration(Math.max(1, duration));
      }

   }

   private static final void SLICER_RECIPES$lambda$1(SimpleRecipeBuilder recipeBuilder) {
      Intrinsics.checkNotNullParameter(recipeBuilder, "recipeBuilder");
      if (recipeBuilder.getFluidInputs().isEmpty()) {
         long eut = recipeBuilder.getEUt();
         int duration = recipeBuilder.getDuration();
         ((SimpleRecipeBuilder)((SimpleRecipeBuilder)recipeBuilder.copy().fluidInputs(Materials.Water.getFluid((int)Math.max(4L, Math.min(200L, (long)duration * eut / (long)320))))).duration(duration * 2)).buildAndRegister();
         RecipeBuilder var10000 = recipeBuilder.copy().fluidInputs(Materials.DistilledWater.getFluid((int)Math.max(3L, Math.min(100L, (long)duration * eut / (long)426))));
         Intrinsics.checkNotNullExpressionValue(var10000, "fluidInputs(...)");
         ((SimpleRecipeBuilder)RecipeBuilderExtKt.duration(var10000, (double)duration * (double)1.5F)).buildAndRegister();
         ((SimpleRecipeBuilder)recipeBuilder.fluidInputs(Materials.Lubricant.getFluid((int)Math.max(1L, Math.min(50L, (long)duration * eut / (long)1280))))).duration(Math.max(1, duration));
      }

   }

   private static final RecipeMapUI LARGE_MIXER_RECIPES$lambda$2(RecipeMap it) {
      Intrinsics.checkNotNullParameter(it, "it");
      return (RecipeMapUI)(new LargeMixerUI(it));
   }

   private static final void CIRCUIT_ASSEMBLY_LINE_RECIPES$lambda$6(CircuitAssemblyLineRecipeBuilder builder) {
      Intrinsics.checkNotNullParameter(builder, "builder");
      SimpleRecipeBuilder var10000 = (SimpleRecipeBuilder)((SimpleRecipeBuilder)SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()).circuitMeta(1);
      List var10001 = builder.getInputs();
      Intrinsics.checkNotNullExpressionValue(var10001, "getInputs(...)");
      Collection $this$toTypedArray$iv = (Collection)var10001;
      int $i$f$toTypedArray = 0;
      GTRecipeInput[] var1 = (GTRecipeInput[])$this$toTypedArray$iv.toArray(new GTRecipeInput[0]);
      var10000 = (SimpleRecipeBuilder)((SimpleRecipeBuilder)var10000.inputs((GTRecipeInput[])Arrays.copyOf(var1, var1.length))).fluidInputs((Collection)builder.getFluidInputs());
      var10001 = builder.getOutputs();
      Intrinsics.checkNotNullExpressionValue(var10001, "getOutputs(...)");
      $this$toTypedArray$iv = (Collection)var10001;
      $i$f$toTypedArray = 0;
      ItemStack[] var14 = (ItemStack[])$this$toTypedArray$iv.toArray(new ItemStack[0]);
      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)var10000.outputs((ItemStack[])Arrays.copyOf(var14, var14.length))).chancedOutputs(builder.getChancedOutputs())).fluidOutputs((Collection)builder.getFluidOutputs())).chancedFluidOutputs(builder.getChancedFluidOutputs())).cleanroom(builder.getCleanroom())).duration(builder.getDuration())).EUt(builder.getEUt())).buildAndRegister();
      List var40 = builder.getInputs();
      Intrinsics.checkNotNullExpressionValue(var40, "getInputs(...)");
      Iterable $this$map$iv = (Iterable)CollectionsKt.filterNotNull((Iterable)var40);
      $i$f$toTypedArray = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         GTRecipeInput it = (GTRecipeInput)item$iv$iv;
         int var10 = 0;
         destination$iv$iv.add(it.copyWithAmount(it.getAmount() * 64));
      }

      Collection $this$toTypedArray$iv = (Collection)((List)destination$iv$iv);
      $i$f$toTypedArray = 0;
      GTRecipeInput[] inputs = (GTRecipeInput[])$this$toTypedArray$iv.toArray(new GTRecipeInput[0]);
      var40 = builder.getOutputs();
      Intrinsics.checkNotNullExpressionValue(var40, "getOutputs(...)");
      Iterable $this$map$iv = (Iterable)CollectionsKt.filterNotNull((Iterable)var40);
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         ItemStack it = (ItemStack)item$iv$iv;
         int var11 = 0;
         destination$iv$iv.add(ItemStackExtKt.copy(it, it.func_190916_E() * 64));
      }

      Collection $this$toTypedArray$iv = (Collection)((List)destination$iv$iv);
      $i$f$map = 0;
      ItemStack[] outputs = (ItemStack[])$this$toTypedArray$iv.toArray(new ItemStack[0]);
      var40 = builder.getFluidInputs();
      Intrinsics.checkNotNullExpressionValue(var40, "getFluidInputs(...)");
      Iterable $this$mapTo$iv$iv = (Iterable)CollectionsKt.filterNotNull((Iterable)var40);
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapTo$iv$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$mapTo$iv$iv) {
         GTRecipeInput it = (GTRecipeInput)item$iv$iv;
         int var12 = 0;
         destination$iv$iv.add(it.copyWithAmount(it.getAmount() * 64));
      }

      List fluidInputs = CollectionsKt.toList((Iterable)((List)destination$iv$iv));
      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder()).circuitMeta(2)).inputs((GTRecipeInput[])Arrays.copyOf(inputs, inputs.length))).fluidInputs((Collection)fluidInputs)).outputs((ItemStack[])Arrays.copyOf(outputs, outputs.length))).chancedOutputs(builder.getChancedOutputs())).fluidOutputs((Collection)builder.getFluidOutputs())).chancedFluidOutputs(builder.getChancedFluidOutputs())).cleanroom(builder.getCleanroom())).duration(builder.getDuration() * 64)).EUt(builder.getEUt())).buildAndRegister();
   }

   private static final RecipeMapUI MINING_DRONE_RECIPES$lambda$7(RecipeMap it) {
      Intrinsics.checkNotNullParameter(it, "it");
      return (RecipeMapUI)(new MiningDroneAirportUI(it));
   }

   private static final RecipeMapUI COMPONENT_ASSEMBLY_LINE_RECIPES$lambda$8(RecipeMap it) {
      Intrinsics.checkNotNullParameter(it, "it");
      return (RecipeMapUI)(new ComponentAssemblyLineUI(it));
   }

   private static final void COMPONENT_ASSEMBLY_LINE_RECIPES$lambda$9(ComponentAssemblyLineRecipeBuilder builder) {
      Intrinsics.checkNotNullParameter(builder, "builder");
      SimpleRecipeBuilder var10000 = (SimpleRecipeBuilder)SINGULARITY_ASSEMBLY_RECIPES.recipeBuilder();
      List var10001 = builder.getInputs();
      Intrinsics.checkNotNullExpressionValue(var10001, "getInputs(...)");
      Collection $this$toTypedArray$iv = (Collection)var10001;
      int $i$f$toTypedArray = 0;
      GTRecipeInput[] var1 = (GTRecipeInput[])$this$toTypedArray$iv.toArray(new GTRecipeInput[0]);
      var10000 = (SimpleRecipeBuilder)((SimpleRecipeBuilder)var10000.inputs((GTRecipeInput[])Arrays.copyOf(var1, var1.length))).fluidInputs((Collection)builder.getFluidInputs());
      var10001 = builder.getOutputs();
      Intrinsics.checkNotNullExpressionValue(var10001, "getOutputs(...)");
      $this$toTypedArray$iv = (Collection)var10001;
      $i$f$toTypedArray = 0;
      ItemStack[] var5 = (ItemStack[])$this$toTypedArray$iv.toArray(new ItemStack[0]);
      ((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)((SimpleRecipeBuilder)var10000.outputs((ItemStack[])Arrays.copyOf(var5, var5.length))).chancedOutputs(builder.getChancedOutputs())).fluidOutputs((Collection)builder.getFluidOutputs())).chancedFluidOutputs(builder.getChancedFluidOutputs())).cleanroom(builder.getCleanroom())).duration(builder.getDuration())).EUt(builder.getEUt())).buildAndRegister();
   }

   private static final RecipeMapUI STELLAR_FORGE_RECIPES$lambda$10(RecipeMap it) {
      Intrinsics.checkNotNullParameter(it, "it");
      return (RecipeMapUI)(new StellarForgeUI(it));
   }

   private static final RecipeMapUI ANTIMATTER_FORGE_RECIPES$lambda$11(RecipeMap it) {
      Intrinsics.checkNotNullParameter(it, "it");
      return (RecipeMapUI)(new AntimatterForgeUI(it));
   }

   private static final RecipeMapUI SPACE_ASSEMBLER_RECIPES$lambda$12(RecipeMap it) {
      Intrinsics.checkNotNullParameter(it, "it");
      return (RecipeMapUI)(new SpaceAssemblerUI(it));
   }

   private static final RecipeMapUI NANO_ASSEMBLY_MATRIX_RECIPES$lambda$13(RecipeMap it) {
      Intrinsics.checkNotNullParameter(it, "it");
      return (RecipeMapUI)(new NanoAssemblyMatrixUI(it));
   }

   private static final RecipeMapUI SINGULARITY_ASSEMBLY_RECIPES$lambda$14(RecipeMap it) {
      Intrinsics.checkNotNullParameter(it, "it");
      return (RecipeMapUI)(new SingularityAssemblyUnitUI(it));
   }

   private static final RecipeMapUI INTEGRATED_ORE_PROCESSOR_RECIPES$lambda$15(RecipeMap it) {
      Intrinsics.checkNotNullParameter(it, "it");
      return new IntegratedOreProcessorUI(it);
   }

   private static final RecipeMapUI BLACKHOLE_FORMING_RECIPES$lambda$16(RecipeMap it) {
      Intrinsics.checkNotNullParameter(it, "it");
      return (RecipeMapUI)(new BlackholeFormerUI(it));
   }

   private static final RecipeMapUI COMPLEX_PYROLYSIS_RECIPES$lambda$17(RecipeMap it) {
      Intrinsics.checkNotNullParameter(it, "it");
      return (RecipeMapUI)(new ConcentratedCokingClusterUI(it));
   }

   static {
      RecipeMap var10000 = (new RecipeMapBuilder("polisher", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(1).itemOutputs(2).fluidInputs(1).itemSlotOverlay(GuiTextures.SAWBLADE_OVERLAY, false).itemSlotOverlay(GuiTextures.CUTTER_OVERLAY, true, false).itemSlotOverlay(GuiTextures.DUST_OVERLAY, true, true).fluidSlotOverlay(GuiTextures.HEATING_OVERLAY_2, false).progressBar(GuiTextures.PROGRESS_BAR_BATH, MoveType.CIRCULAR).sound(GTSoundEvents.CUT).onBuild(GTLiteMod.Companion.id("polisher_fluid"), GTLiteRecipeMaps::POLISHER_RECIPES$lambda$0).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      POLISHER_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("slicer", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(2).itemOutputs(2).fluidInputs(1).fluidOutputs(1).itemSlotOverlay(GTLiteGuiTextures.CHOPPING_BLOCK_OVERLAY, false, false).itemSlotOverlay(GTLiteGuiTextures.SLICED_CONTAINER_OVERLAY, false, true).itemSlotOverlay(GTLiteGuiTextures.SLICED_MATTER_OVERLAY, true, false).itemSlotOverlay(GuiTextures.DUST_OVERLAY, true, true).progressBar(GTLiteGuiTextures.PROGRESS_BAR_SLICING).sound(GTSoundEvents.CUT).onBuild(GTLiteMod.Companion.id("slicer_fluid"), GTLiteRecipeMaps::SLICER_RECIPES$lambda$1).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      SLICER_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("tool_caster", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(1).itemOutputs(9).fluidInputs(1).itemSlotOverlay(GuiTextures.MOLD_OVERLAY, false).progressBar(GuiTextures.PROGRESS_BAR_UNPACKER).sound(GTSoundEvents.FORGE_HAMMER).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      TOOL_CASTER_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("loom", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(3).itemOutputs(2).fluidInputs(1).fluidOutputs(0).itemSlotOverlay(GuiTextures.STRING_SLOT_OVERLAY, false).fluidSlotOverlay(GTLiteGuiTextures.STRING_SLOT_OVERLAY_2, false).progressBar(GuiTextures.PROGRESS_BAR_MAGNET, MoveType.HORIZONTAL).sound(GTSoundEvents.COMPRESSOR).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      LOOM_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("laminator", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(6).itemOutputs(1).fluidInputs(2).fluidOutputs(0).itemSlotOverlay(GTLiteGuiTextures.CHOPPING_BLOCK_OVERLAY, false).progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, MoveType.HORIZONTAL).sound(GTSoundEvents.COOLING).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      LAMINATOR_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("chemical_dehydrator", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(2).itemOutputs(6).fluidInputs(2).fluidOutputs(3).itemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, false).itemSlotOverlay(GuiTextures.DUST_OVERLAY, true).fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, false).progressBar(GuiTextures.PROGRESS_BAR_SIFT).sound(GTSoundEvents.FURNACE).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      CHEMICAL_DEHYDRATOR_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("vulcanizing_press", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(4).itemOutputs(2).fluidInputs(2).fluidOutputs(1).itemSlotOverlay(GuiTextures.DUST_OVERLAY, false, false).itemSlotOverlay(GuiTextures.MOLD_OVERLAY, false, true).progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE).sound(GTSoundEvents.COMBUSTION).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      VULCANIZATION_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("vacuum_chamber", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(4).itemOutputs(1).fluidInputs(2).fluidOutputs(1).itemSlotOverlay(GuiTextures.CIRCUIT_OVERLAY, false).progressBar(GuiTextures.PROGRESS_BAR_COMPRESS).sound(GTSoundEvents.ASSEMBLER).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      VACUUM_CHAMBER_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("sap_collector", (RecipeBuilder)(new PseudoMultiRecipeBuilder()))).itemOutputs(2).fluidInputs(1).fluidOutputs(2).progressBar(GTLiteGuiTextures.PROGRESS_BAR_EXTRACTION, MoveType.VERTICAL_DOWNWARDS).sound(GTSoundEvents.DRILL_TOOL).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      SAP_COLLECTOR_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("greenhouse", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(2).itemOutputs(4).fluidInputs(2).fluidOutputs(1).progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE).sound(SoundEvents.field_187542_ac).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      GREENHOUSE_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("bio_reactor", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(6).itemOutputs(1).fluidInputs(3).fluidOutputs(2).itemSlotOverlay(GTLiteGuiTextures.DISH_OVERLAY, false).itemSlotOverlay(GuiTextures.DUST_OVERLAY, true).fluidSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_3, false, false).fluidSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_4, false, true).fluidSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_3, true).progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE).sound(GTSoundEvents.CHEMICAL_REACTOR).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      BIO_REACTOR_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("roaster", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(3).itemOutputs(3).fluidInputs(3).fluidOutputs(3).itemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, false).itemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, true).fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, false).fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, true).sound(GTSoundEvents.FURNACE).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      ROASTER_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("burner_reactor", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(3).itemOutputs(3).fluidInputs(3).fluidOutputs(3).itemSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_1, false, false).itemSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_2, false, true).itemSlotOverlay(GuiTextures.VIAL_OVERLAY_1, true).fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, false).fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, true).progressBar(GuiTextures.PROGRESS_BAR_ARC_FURNACE).sound(GTSoundEvents.ARC).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      BURNER_REACTOR_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("bath_condenser", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(1).itemOutputs(1).fluidInputs(2).fluidOutputs(1).itemSlotOverlay(GuiTextures.DUST_OVERLAY, false).itemSlotOverlay(GuiTextures.DUST_OVERLAY, true).progressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR).sound(GTSoundEvents.COOLING).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      BATH_CONDENSER_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("cryogenic_reactor", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(3).itemOutputs(3).fluidInputs(3).fluidOutputs(3).itemSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_1, false, false).itemSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_2, false, true).itemSlotOverlay(GuiTextures.VIAL_OVERLAY_1, true, false).itemSlotOverlay(GuiTextures.VIAL_OVERLAY_2, true, true).sound(GTSoundEvents.COOLING).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      CRYOGENIC_REACTOR_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("food_processor", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(6).itemOutputs(2).fluidInputs(3).fluidOutputs(1).itemSlotOverlay(GTLiteGuiTextures.DISK_OVERLAY, false).fluidSlotOverlay(GuiTextures.HEATING_OVERLAY_2, false).progressBar(GTLiteGuiTextures.PROGRESS_BAR_FOOD_PROCESSING).sound(GTSoundEvents.ASSEMBLER).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      FOOD_PROCESSOR_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("multicooker", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(6).itemOutputs(3).fluidInputs(3).fluidOutputs(2).itemSlotOverlay(GuiTextures.DUST_OVERLAY, false).fluidSlotOverlay(GuiTextures.HEATING_OVERLAY_2, false).progressBar(GuiTextures.PROGRESS_BAR_MIXER, MoveType.CIRCULAR).sound(GTSoundEvents.MIXER).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      MULTICOOKER_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("mob_extractor", (RecipeBuilder)(new MobProximityRecipeBuilder()))).itemInputs(1).itemOutputs(1).fluidOutputs(1).itemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false).progressBar(GuiTextures.PROGRESS_BAR_EXTRACT).sound(GTSoundEvents.COMPRESSOR).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      MOB_EXTRACTOR_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("bio_simulator", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(2).itemOutputs(1).itemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false, true).progressBar(GTLiteGuiTextures.PROGRESS_BAR_SWORD).sound(GTSoundEvents.ELECTROLYZER).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      BIO_SIMULATOR_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("coagulation_tank", (RecipeBuilder)(new PrimitiveRecipeBuilder()))).itemInputs(2).itemOutputs(1).fluidInputs(2).sound(GTSoundEvents.COOLING).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      COAGULATION_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("large_mixer", (RecipeBuilder)(new SimpleRecipeBuilder()))).ui(GTLiteRecipeMaps::LARGE_MIXER_RECIPES$lambda$2).itemInputs(9).itemOutputs(1).fluidInputs(6).fluidOutputs(1).progressBar(GuiTextures.PROGRESS_BAR_MIXER, MoveType.CIRCULAR).sound(GTSoundEvents.MIXER).build().setSmallRecipeMap(RecipeMaps.MIXER_RECIPES);
      Intrinsics.checkNotNullExpressionValue(var10000, "setSmallRecipeMap(...)");
      LARGE_MIXER_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("catalytic_reformer", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(1).itemOutputs(0).fluidInputs(1).fluidOutputs(4).itemSlotOverlay(GTLiteGuiTextures.PLATE_OVERLAY, false).progressBar(GuiTextures.PROGRESS_BAR_CRACKING).sound(GTSoundEvents.FURNACE).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      CATALYTIC_REFORMER_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("large_gas_collector", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(2).fluidOutputs(4).itemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false, true).fluidSlotOverlay(GuiTextures.CENTRIFUGE_OVERLAY, true).progressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR).sound(GTSoundEvents.COOLING).build().setSmallRecipeMap(RecipeMaps.GAS_COLLECTOR_RECIPES);
      Intrinsics.checkNotNullExpressionValue(var10000, "setSmallRecipeMap(...)");
      LARGE_GAS_COLLECTOR_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("electric_implosion_compressor", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(6).fluidInputs(1).itemOutputs(2).fluidOutputs(1).itemSlotOverlay(GuiTextures.IMPLOSION_OVERLAY_1, false).itemSlotOverlay(GuiTextures.DUST_OVERLAY, true).fluidSlotOverlay(GuiTextures.IMPLOSION_OVERLAY_2, false).progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE).sound(SoundEvents.field_187539_bB).build().setSmallRecipeMap(RecipeMaps.IMPLOSION_RECIPES);
      Intrinsics.checkNotNullExpressionValue(var10000, "setSmallRecipeMap(...)");
      ELECTRIC_IMPLOSION_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("alloy_blast_smelter", (RecipeBuilder)(new BlastRecipeBuilder()))).itemInputs(9).fluidInputs(3).fluidOutputs(1).itemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, false).itemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, true).fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, false).fluidSlotOverlay(GuiTextures.FURNACE_OVERLAY_2, true).sound(GTSoundEvents.FURNACE).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      ALLOY_BLAST_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("chemical_plant", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(6).itemOutputs(4).fluidInputs(5).fluidOutputs(4).itemSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_1, false, false).itemSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_2, false, true).itemSlotOverlay(GuiTextures.VIAL_OVERLAY_1, true).fluidSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_3, false).fluidSlotOverlay(GuiTextures.VIAL_OVERLAY_2, true).progressBar(GTLiteGuiTextures.PROGRESS_BAR_REACTION, MoveType.CIRCULAR).sound(GTSoundEvents.CHEMICAL_REACTOR).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      CHEMICAL_PLANT_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("circuit_assembly_line", (RecipeBuilder)(new CircuitAssemblyLineRecipeBuilder()))).itemInputs(6).itemOutputs(6).fluidInputs(1).itemSlotOverlay(GuiTextures.CIRCUIT_OVERLAY, false).progressBar(GuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER, MoveType.HORIZONTAL).sound(GTSoundEvents.ASSEMBLER).onBuild(GTLiteMod.Companion.id("circ_ass_copy"), GTLiteRecipeMaps::CIRCUIT_ASSEMBLY_LINE_RECIPES$lambda$6).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      CIRCUIT_ASSEMBLY_LINE_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("mining_drone_airport", (RecipeBuilder)(new SimpleRecipeBuilder()))).ui(GTLiteRecipeMaps::MINING_DRONE_RECIPES$lambda$7).itemInputs(4).itemOutputs(16).fluidInputs(1).sound(GTSoundEvents.COMPRESSOR).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      MINING_DRONE_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("cvd_unit", (RecipeBuilder)(new NoCoilTemperatureRecipeBuilder()))).itemInputs(2).itemOutputs(3).fluidInputs(3).fluidOutputs(3).progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE).sound(GTSoundEvents.COOLING).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      CVD_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("crystallization_crucible", (RecipeBuilder)(new BlastRecipeBuilder()))).itemInputs(6).itemOutputs(1).fluidInputs(3).itemSlotOverlay(GuiTextures.DUST_OVERLAY, false).itemSlotOverlay(GuiTextures.CRYSTAL_OVERLAY, true).progressBar(GuiTextures.PROGRESS_BAR_CRYSTALLIZATION).sound(GTSoundEvents.FURNACE).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      CRYSTALLIZATION_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("nanoscale_fabricator", (RecipeBuilder)(new NoCoilTemperatureRecipeBuilder()))).itemInputs(6).itemOutputs(2).fluidInputs(2).itemSlotOverlay(GTLiteGuiTextures.NANOSCALE_OVERLAY_1, false).itemSlotOverlay(GTLiteGuiTextures.NANOSCALE_OVERLAY_1, true).fluidSlotOverlay(GTLiteGuiTextures.NANOSCALE_OVERLAY_2, false).fluidSlotOverlay(GTLiteGuiTextures.NANOSCALE_OVERLAY_2, true).progressBar(GTLiteGuiTextures.PROGRESS_BAR_NANOSCALE).sound(GTSoundEvents.ELECTROLYZER).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      MOLECULAR_BEAM_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("sonicator", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(1).itemOutputs(1).fluidInputs(2).fluidOutputs(2).itemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false).itemSlotOverlay(GTLiteGuiTextures.FOIL_OVERLAY, true).fluidSlotOverlay(GuiTextures.BREWER_OVERLAY, false, false).fluidSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_3, false, true).fluidSlotOverlay(GuiTextures.MOLECULAR_OVERLAY_4, true).progressBar(GuiTextures.PROGRESS_BAR_EXTRACT).sound(GTSoundEvents.CENTRIFUGE).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      SONICATION_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("laser_induced_cvd_unit", (RecipeBuilder)(new NoCoilTemperatureRecipeBuilder()))).itemInputs(2).itemOutputs(3).fluidInputs(4).fluidOutputs(3).progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE).sound(GTSoundEvents.COOLING).build().setSmallRecipeMap(CVD_RECIPES);
      Intrinsics.checkNotNullExpressionValue(var10000, "setSmallRecipeMap(...)");
      LASER_CVD_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("plasma_enhanced_cvd_unit", (RecipeBuilder)(new NoCoilTemperatureRecipeBuilder()))).itemInputs(3).itemOutputs(3).fluidInputs(3).fluidOutputs(3).progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE).sound(GTSoundEvents.COOLING).build().setSmallRecipeMap(CVD_RECIPES);
      Intrinsics.checkNotNullExpressionValue(var10000, "setSmallRecipeMap(...)");
      PLASMA_CVD_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("bedrock_drilling_rig", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(1).itemOutputs(1).fluidOutputs(1).itemSlotOverlay(GuiTextures.CRUSHED_ORE_OVERLAY, false, true).itemSlotOverlay(GuiTextures.DUST_OVERLAY, true).sound(GTSoundEvents.MACERATOR).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      DRILLING_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("advanced_fusion_reactor", (RecipeBuilder)(new AdvancedFusionRecipeBuilder()))).fluidInputs(3).fluidOutputs(2).progressBar(GuiTextures.PROGRESS_BAR_FUSION).sound(GTSoundEvents.ARC).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      ADVANCED_FUSION_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("component_assembly_line", (RecipeBuilder)(new ComponentAssemblyLineRecipeBuilder()))).ui(GTLiteRecipeMaps::COMPONENT_ASSEMBLY_LINE_RECIPES$lambda$8).itemInputs(12).itemOutputs(1).fluidInputs(12).sound(GTSoundEvents.ASSEMBLER).onBuild(GTLiteMod.Companion.id("coal_copy"), GTLiteRecipeMaps::COMPONENT_ASSEMBLY_LINE_RECIPES$lambda$9).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      COMPONENT_ASSEMBLY_LINE_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("cosmic_ray_detector", (RecipeBuilder)(new MinimumHeightRecipeBuilder()))).itemInputs(1).fluidOutputs(1).itemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false).sound(GTSoundEvents.ARC).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      COSMIC_RAY_DETECTING_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("stellar_forge", (RecipeBuilder)(new SimpleRecipeBuilder()))).ui(GTLiteRecipeMaps::STELLAR_FORGE_RECIPES$lambda$10).itemInputs(9).itemOutputs(9).fluidInputs(9).fluidOutputs(9).sound(GTLiteSoundEvents.INSTANCE.getSTELLAR_FORGE()).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      STELLAR_FORGE_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("pcb_factory", (RecipeBuilder)(new PCBFactoryRecipeBuilder()))).itemInputs(6).itemOutputs(9).fluidInputs(3).itemSlotOverlay(GuiTextures.CIRCUIT_OVERLAY, false).itemSlotOverlay(GuiTextures.CIRCUIT_OVERLAY, true).progressBar(GuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER).sound(GTLiteSoundEvents.INSTANCE.getPCB_FACTORY()).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      PCB_FACTORY_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("nano_forge", (RecipeBuilder)(new NanoForgeRecipeBuilder()))).itemInputs(6).itemOutputs(1).fluidInputs(3).itemSlotOverlay(GuiTextures.CIRCUIT_OVERLAY, false).itemSlotOverlay(GuiTextures.CENTRIFUGE_OVERLAY, true).sound(GTSoundEvents.FURNACE).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      NANO_FORGE_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("quantum_force_transformer", (RecipeBuilder)(new QuantumForceTransformerRecipeBuilder()))).itemInputs(6).itemOutputs(6).fluidInputs(6).fluidOutputs(6).progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE).sound(GTLiteSoundEvents.INSTANCE.getQUANTUM()).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      QUANTUM_FORCE_TRANSFORMER_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("antimatter_forge", (RecipeBuilder)(new SimpleRecipeBuilder()))).ui(GTLiteRecipeMaps::ANTIMATTER_FORGE_RECIPES$lambda$11).itemInputs(1).fluidInputs(21).fluidOutputs(1).sound(GTLiteSoundEvents.INSTANCE.getSTELLAR_FORGE()).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      ANTIMATTER_FORGE_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("space_assembler", (RecipeBuilder)(new AccelerationTrackRecipeBuilder()))).ui(GTLiteRecipeMaps::SPACE_ASSEMBLER_RECIPES$lambda$12).itemInputs(16).itemOutputs(1).fluidInputs(4).sound(GTLiteSoundEvents.INSTANCE.getSPACE_ASSEMBLER()).build();
      Intrinsics.checkNotNullExpressionValue(var10000, "build(...)");
      SPACE_ASSEMBLER_RECIPES = var10000;
      var10000 = (new RecipeMapBuilder("entrodynamically_phase_changer", (RecipeBuilder)(new BlastRecipeBuilder()))).itemInputs(6).itemOutputs(1).fluidInputs(1).fluidOutputs(1).progressBar(GTLiteGuiTextures.PROGRESS_BAR_PHASE_CHANGE).sound(GTSoundEvents.FURNACE).build().setSmallRecipeMap(RecipeMaps.BLAST_RECIPES);
      Intrinsics.checkNotNullExpressionValue(var10000, "setSmallRecipeMap(...)");
      TOPOLOGICAL_ORDER_CHANGING_RECIPES = var10000;
      PseudoGroupRecipeMapBuilder var47 = new PseudoGroupRecipeMapBuilder("plasma_arc_transmitter", (RecipeBuilder)(new SimpleRecipeBuilder()));
      RecipeMap[] var0 = new RecipeMap[]{RecipeMaps.ARC_FURNACE_RECIPES, RecipeMaps.ALLOY_SMELTER_RECIPES};
      PLASMA_ARC_TRANSMITTER_RECIPES = var47.groupKt(var0).itemInputs(4).itemOutputs(9).fluidInputs(2).fluidOutputs(1).build();
      RecipeMap var48 = (new RecipeMapBuilder("nano_assembly_matrix", (RecipeBuilder)(new SimpleRecipeBuilder()))).ui(GTLiteRecipeMaps::NANO_ASSEMBLY_MATRIX_RECIPES$lambda$13).itemInputs(16).itemOutputs(1).fluidInputs(4).sound(GTSoundEvents.ASSEMBLER).build();
      Intrinsics.checkNotNullExpressionValue(var48, "build(...)");
      NANO_ASSEMBLY_MATRIX_RECIPES = var48;
      var48 = (new RecipeMapBuilder("singularity_assembly_unit", (RecipeBuilder)(new SimpleRecipeBuilder()))).ui(GTLiteRecipeMaps::SINGULARITY_ASSEMBLY_RECIPES$lambda$14).itemInputs(16).itemOutputs(4).fluidInputs(12).sound(GTSoundEvents.ASSEMBLER).build();
      Intrinsics.checkNotNullExpressionValue(var48, "build(...)");
      SINGULARITY_ASSEMBLY_RECIPES = var48;
      var48 = (new RecipeMapBuilder("integrated_ore_processor", (RecipeBuilder)(new SimpleRecipeBuilder()))).ui(GTLiteRecipeMaps::INTEGRATED_ORE_PROCESSOR_RECIPES$lambda$15).itemInputs(4).itemOutputs(12).fluidInputs(4).sound(GTSoundEvents.CENTRIFUGE).build();
      Intrinsics.checkNotNullExpressionValue(var48, "build(...)");
      INTEGRATED_ORE_PROCESSOR_RECIPES = var48;
      var48 = (new RecipeMapBuilder("matter_reshaping_framework", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(4).fluidInputs(2).itemOutputs(4).fluidOutputs(2).itemSlotOverlay(GuiTextures.HAMMER_OVERLAY, false).specialTexture(GuiTextures.PROGRESS_BAR_HAMMER_BASE, 78, 42, 20, 6).progressBar(GuiTextures.PROGRESS_BAR_HAMMER, MoveType.VERTICAL_DOWNWARDS).build().setSmallRecipeMap(RecipeMaps.FORGE_HAMMER_RECIPES);
      Intrinsics.checkNotNullExpressionValue(var48, "setSmallRecipeMap(...)");
      MATTER_RESHAPING_RECIPES = var48;
      PseudoGroupRecipeMapBuilder var52 = new PseudoGroupRecipeMapBuilder("solidification_submodule", (RecipeBuilder)(new SimpleRecipeBuilder()));
      var0 = new RecipeMap[]{MATTER_RESHAPING_RECIPES, RecipeMaps.FLUID_SOLIDFICATION_RECIPES, RecipeMaps.COMPRESSOR_RECIPES, TOOL_CASTER_RECIPES};
      SOLIDIFICATION_SUBMODULE_RECIPES = var52.groupKt(var0).itemInputs(2).itemOutputs(9).fluidInputs(2).fluidOutputs(2).build();
      var52 = new PseudoGroupRecipeMapBuilder("recombination_submodule", (RecipeBuilder)(new SimpleRecipeBuilder()));
      var0 = new RecipeMap[]{RecipeMaps.EXTRACTOR_RECIPES, RecipeMaps.CANNER_RECIPES, LAMINATOR_RECIPES, VULCANIZATION_RECIPES};
      RECOMBINATION_SUBMODULE_RECIPES = var52.groupKt(var0).itemInputs(6).itemOutputs(2).fluidInputs(2).fluidOutputs(1).build();
      RecipeMap var54 = (new RecipeMapBuilder("mob_collecting_mode", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(1).itemOutputs(2).fluidOutputs(2).progressBar(GuiTextures.PROGRESS_BAR_EXTRACT).sound(GTSoundEvents.COMPRESSOR).build().setSmallRecipeMap(MOB_EXTRACTOR_RECIPES);
      Intrinsics.checkNotNullExpressionValue(var54, "setSmallRecipeMap(...)");
      MOB_COLLECTING_RECIPES = var54;
      var54 = (new RecipeMapBuilder("mob_slaughtering_mode", (RecipeBuilder)(new SimpleRecipeBuilder()))).itemInputs(2).itemOutputs(4).fluidOutputs(2).progressBar(GTLiteGuiTextures.PROGRESS_BAR_SWORD).sound(SoundEvents.field_187680_c).build();
      Intrinsics.checkNotNullExpressionValue(var54, "build(...)");
      MOB_SLAUGHTERING_RECIPES = var54;
      var54 = (new RecipeMapBuilder("blackhole_former", (RecipeBuilder)(new SimpleRecipeBuilder()))).ui(GTLiteRecipeMaps::BLACKHOLE_FORMING_RECIPES$lambda$16).itemInputs(6).itemOutputs(1).sound(GTLiteSoundEvents.INSTANCE.getBLACKHOLE()).build();
      Intrinsics.checkNotNullExpressionValue(var54, "build(...)");
      BLACKHOLE_FORMING_RECIPES = var54;
      var54 = (new RecipeMapBuilder("concentrated_coking_cluster", (RecipeBuilder)(new BlastRecipeBuilder()))).ui(GTLiteRecipeMaps::COMPLEX_PYROLYSIS_RECIPES$lambda$17).itemInputs(2).fluidInputs(2).itemOutputs(2).fluidOutputs(6).sound(GTSoundEvents.BATH).build();
      Intrinsics.checkNotNullExpressionValue(var54, "build(...)");
      COMPLEX_PYROLYSIS_RECIPES = var54;
      var54 = (new RecipeMapBuilder("hot_coolant_turbine", (RecipeBuilder)(new FuelRecipeBuilder()))).fluidInputs(1).fluidOutputs(1).fluidSlotOverlay(GuiTextures.DARK_CANISTER_OVERLAY, false, true).progressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR).sound(GTSoundEvents.TURBINE).generator().allowEmptyOutputs().build();
      Intrinsics.checkNotNullExpressionValue(var54, "build(...)");
      HOT_COOLANT_TURBINE_FUELS = var54;
      var54 = (new RecipeMapBuilder("supercritical_fluid_turbine", (RecipeBuilder)(new FuelRecipeBuilder()))).fluidInputs(1).fluidOutputs(1).fluidSlotOverlay(GuiTextures.DARK_CANISTER_OVERLAY, false, true).progressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR).sound(GTSoundEvents.TURBINE).generator().allowEmptyOutputs().build();
      Intrinsics.checkNotNullExpressionValue(var54, "build(...)");
      SUPERCRITICAL_FLUID_TURBINE_FUELS = var54;
      var54 = (new RecipeMapBuilder("nuclear_reactor", (RecipeBuilder)(new FuelRecipeBuilder()))).itemInputs(1).itemOutputs(1).fluidInputs(1).fluidOutputs(1).sound(GTSoundEvents.FIRE).generator().build();
      Intrinsics.checkNotNullExpressionValue(var54, "build(...)");
      NUCLEAR_FUELS = var54;
      var54 = (new RecipeMapBuilder("rocket_engine", (RecipeBuilder)(new FuelRecipeBuilder()))).fluidInputs(1).allowEmptyOutputs().sound(GTSoundEvents.COMBUSTION).generator().build();
      Intrinsics.checkNotNullExpressionValue(var54, "build(...)");
      ROCKET_ENGINE_FUELS = var54;
      var54 = (new RecipeMapBuilder("naquadah_reactor", (RecipeBuilder)(new FuelRecipeBuilder()))).fluidInputs(1).allowEmptyOutputs().sound(GTSoundEvents.ARC).generator().build();
      Intrinsics.checkNotNullExpressionValue(var54, "build(...)");
      NAQUADAH_REACTOR_FUELS = var54;
      var54 = (new RecipeMapBuilder("antimatter_generator", (RecipeBuilder)(new FuelRecipeBuilder()))).fluidInputs(2).allowEmptyOutputs().sound(GTSoundEvents.ARC).generator().build();
      Intrinsics.checkNotNullExpressionValue(var54, "build(...)");
      ANTIMATTER_GENERATOR_FUELS = var54;
      var54 = (new RecipeMapBuilder("dyson_swarm_fuels", (RecipeBuilder)(new FuelRecipeBuilder()))).fluidInputs(2).allowEmptyOutputs().sound(GTSoundEvents.ARC).generator().build();
      Intrinsics.checkNotNullExpressionValue(var54, "build(...)");
      DYSON_SWARM_FUELS = var54;
      var54 = (new RecipeMapBuilder("acid_generator", (RecipeBuilder)(new FuelRecipeBuilder()))).fluidInputs(1).allowEmptyOutputs().sound(GTSoundEvents.BATH).generator().build();
      Intrinsics.checkNotNullExpressionValue(var54, "build(...)");
      ACID_GENERATOR_FUELS = var54;
   }
}
