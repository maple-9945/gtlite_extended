package gregtechlite.gtlitecore.common.metatileentity.multiblock;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.utils.TooltipHelper;
import gregtech.core.sound.GTSoundEvents;
import gregtechlite.gtlitecore.api.capability.logic.IntegratedOreProcessorRecipeLogic;
import gregtechlite.gtlitecore.api.entity.GTLiteDamageSources;
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps;
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays;
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures;
import gregtechlite.gtlitecore.client.renderer.texture.custom.OreProcessorRenderer;
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing;
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing;
import gregtechlite.gtlitecore.common.block.adapter.GTTurbineCasing;
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing;
import gregtechlite.gtlitecore.common.block.variant.GlassCasing;
import gregtechlite.gtlitecore.common.block.variant.MetalCasing;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 72\u00020\u0001:\u00017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\rH\u0014J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0017J\b\u0010\u0012\u001a\u00020\u000fH\u0015J1\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0010\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0018\u00010\u0019H\u0017¢\u0006\u0002\u0010\u001bJ0\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010$\u001a\u00020%H\u0017J\b\u0010&\u001a\u00020%H\u0016J\b\u0010'\u001a\u00020(H\u0016J.\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u000101H\u0016J$\u00102\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020+2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u000101H\u0016J.\u00103\u001a\u00020%2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u00104\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u000101H\u0016J.\u00105\u001a\u00020%2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\u0010\u00106\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020+H\u0002¨\u00068"},
   d2 = {"Lgregtechlite/gtlitecore/common/metatileentity/multiblock/MultiblockIntegratedOreProcessor;", "Lgregtech/api/metatileentity/multiblock/RecipeMapMultiblockController;", "id", "Lnet/minecraft/util/ResourceLocation;", "<init>", "(Lnet/minecraft/util/ResourceLocation;)V", "createMetaTileEntity", "Lgregtech/api/metatileentity/MetaTileEntity;", "te", "Lgregtech/api/metatileentity/interfaces/IGregTechTileEntity;", "initializeAbilities", "", "createStructurePattern", "Lgregtech/api/pattern/BlockPattern;", "getBaseTexture", "Lgregtech/client/renderer/ICubeRenderer;", "sourcePart", "Lgregtech/api/metatileentity/multiblock/IMultiblockPart;", "getFrontOverlay", "renderMetaTileEntity", "renderState", "Lcodechicken/lib/render/CCRenderState;", "translation", "Lcodechicken/lib/vec/Matrix4;", "pipeline", "", "Lcodechicken/lib/render/pipeline/IVertexOperation;", "(Lcodechicken/lib/render/CCRenderState;Lcodechicken/lib/vec/Matrix4;[Lcodechicken/lib/render/pipeline/IVertexOperation;)V", "addInformation", "stack", "Lnet/minecraft/item/ItemStack;", "player", "Lnet/minecraft/world/World;", "tooltip", "", "", "advanced", "", "hasMaintenanceMechanics", "getBreakdownSound", "Lnet/minecraft/util/SoundEvent;", "onRightClick", "playerIn", "Lnet/minecraft/entity/player/EntityPlayer;", "hand", "Lnet/minecraft/util/EnumHand;", "facing", "Lnet/minecraft/util/EnumFacing;", "hitResult", "Lcodechicken/lib/raytracer/CuboidRayTraceResult;", "onLeftClick", "onWrenchClick", "wrenchSide", "onScrewdriverClick", "onControllerInteract", "Companion", "gtlitecore"}
)
public final class MultiblockIntegratedOreProcessor extends RecipeMapMultiblockController {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private static final IBlockState casingState;
   @NotNull
   private static final IBlockState secondCasingState;
   @NotNull
   private static final IBlockState thirdCasingState;
   @NotNull
   private static final IBlockState fourthCasingState;
   @NotNull
   private static final IBlockState fifthCasingState;
   @NotNull
   private static final IBlockState gearboxCasingState;
   @NotNull
   private static final IBlockState pipeCasingState;
   @NotNull
   private static final IBlockState uniqueCasingState;
   @NotNull
   private static final IBlockState glassState;

   public MultiblockIntegratedOreProcessor(@NotNull ResourceLocation id) {
      Intrinsics.checkNotNullParameter(id, "id");
      super(id, GTLiteRecipeMaps.INTEGRATED_ORE_PROCESSOR_RECIPES);
      this.recipeMapWorkable = new IntegratedOreProcessorRecipeLogic(this);
   }

   @NotNull
   public MetaTileEntity createMetaTileEntity(@NotNull IGregTechTileEntity te) {
      Intrinsics.checkNotNullParameter(te, "te");
      ResourceLocation var10002 = this.metaTileEntityId;
      Intrinsics.checkNotNullExpressionValue(var10002, "metaTileEntityId");
      return (MetaTileEntity)(new MultiblockIntegratedOreProcessor(var10002));
   }

   protected void initializeAbilities() {
      super.initializeAbilities();
      ArrayList inputEnergy = new ArrayList((Collection)this.getAbilities(MultiblockAbility.INPUT_ENERGY));
      inputEnergy.addAll((Collection)this.getAbilities(MultiblockAbility.SUBSTATION_INPUT_ENERGY));
      inputEnergy.addAll((Collection)this.getAbilities(MultiblockAbility.INPUT_LASER));
      this.energyContainer = (IEnergyContainer)(new EnergyContainerList((List)inputEnergy));
   }

   @NotNull
   protected BlockPattern createStructurePattern() {
      FactoryBlockPattern var10000 = FactoryBlockPattern.start();
      String[] var1 = new String[]{" JJJG     GJJJ ", "  J G     G J  ", "    G     G    ", "    GG   GG    ", "     DDDDD     ", "               ", "               ", "               ", "               "};
      var10000 = var10000.aisle(var1);
      var1 = new String[]{"JFFFJDDDDDJFFFJ", " FFFFDDDDDFFFF ", " FFFFDDDDDFFFF ", " FAFFDDDDDFFAF ", " FAFFDDDDDFFAF ", " FAFFFF FFFFAF ", " FAFF     FFAF ", " FAF       FAF ", " FFF       FFF "};
      var10000 = var10000.aisle(var1);
      var1 = new String[]{"JFFFJDDDDDJFFFJ", "JFIFJ      FHFJ", " FIF       FHF ", " AIF       FHA ", " AIFFDDDDDFFHA ", " AIFFJJGJJFFHA ", " AIFJ     JFHA ", " AIF       FHA ", " FFF       FFF "};
      var10000 = var10000.aisle(var1);
      var1 = new String[]{"JFFFJDDDDDJFFFJ", " FFFF     FFFF ", " FFFF     FFFF ", " FAFF     FFAF ", " FAFFDDDDDFFAF ", " FAFFFF FFFFAF ", " FAFF     FFAF ", " FAF       FAF ", " FFF       FFF "};
      var10000 = var10000.aisle(var1);
      var1 = new String[]{" JJJDDDDDDDJJJ ", "  J D     D J  ", "    D     D    ", "    D     D    ", "    DDDDDDD    ", "               ", "               ", "               ", "               "};
      var10000 = var10000.aisle(var1);
      var1 = new String[]{"JFFFJDDDDDJFFFJ", " FFFF     FFFF ", " FFFF     FFFF ", " FAFF     FFAF ", " FAFFDDDDDFFAF ", " FAFFFF FFFFAF ", " FAFF     FFAF ", " FAF       FAF ", " FFF       FFF "};
      var10000 = var10000.aisle(var1);
      var1 = new String[]{"JFFFJDDDDDJFFFJ", "JFCFJ     JF FJ", " FCF       F F ", " ACF       F A ", " ACFFDDDDDFF A ", " ACFFJJGJJFF A ", " ACFJ     JF A ", " ACF       F A ", " FFF       FFF "};
      var10000 = var10000.aisle(var1);
      var1 = new String[]{"JFFFJDDDDDJFFFJ", " FFFF     FFFF ", " FFFF     FFFF ", " FAFF     FFAF ", " FAFFDDDDDFFAF ", " FAFFFF FFFFAF ", " FAFF     FFAF ", " FAF       FAF ", " FFF       FFF "};
      var10000 = var10000.aisle(var1);
      var1 = new String[]{" JJJDDDDDDDJJJ ", "  J D     D J  ", "    D     D    ", "    D     D    ", "    DDDDDDD    ", "               ", "               ", "               ", "               "};
      var10000 = var10000.aisle(var1);
      var1 = new String[]{"JFFFJDDDDDJFFFJ", " FFFF     FFFF ", " FFFF     FFFF ", " FAFF     FFAF ", " FAFFDDDDDFFAF ", " FAFFFF FFFFAF ", " FAFF     FFAF ", " FAF       FAF ", " FFF       FFF "};
      var10000 = var10000.aisle(var1);
      var1 = new String[]{"JFFFJDDDDDJFFFJ", "JFBFJ      FEFJ", " FBF       FEF ", " ABF       FEA ", " ABFFDDDDDFFEA ", " ABFFJJGJJFFEA ", " ABFJ     JFEA ", " ABF       FEA ", " FFF       FFF "};
      var10000 = var10000.aisle(var1);
      var1 = new String[]{"JFFFJDDDDDJFFFJ", " FFFFDDDDDFFFF ", " FFFFDDSDDFFFF ", " FAFFDDDDDFFAF ", " FAFFDDDDDFFAF ", " FAFFFF FFFFAF ", " FAFF     FFAF ", " FAF       FAF ", " FFF       FFF "};
      var10000 = var10000.aisle(var1);
      var1 = new String[]{" JJJG     GJJJ ", "  J G     G J  ", "    G     G    ", "    GG   GG    ", "     DDDDD     ", "               ", "               ", "               ", "               "};
      var10000 = var10000.aisle(var1).where('S', this.selfPredicate());
      IBlockState[] var14 = new IBlockState[]{casingState};
      TraceabilityPredicate var10002 = RecipeMapMultiblockController.states(var14).setMinGlobalLimited(140).or(this.autoAbilities(false, false, true, true, true, false, false));
      MultiblockAbility[] var15 = new MultiblockAbility[]{MultiblockAbility.INPUT_ENERGY};
      var10002 = var10002.or(RecipeMapMultiblockController.abilities(var15).setMaxGlobalLimited(8).setPreviewCount(1));
      var15 = new MultiblockAbility[]{MultiblockAbility.INPUT_LASER};
      var10000 = var10000.where('D', var10002.or(RecipeMapMultiblockController.abilities(var15).setMaxGlobalLimited(1).setPreviewCount(0)));
      IBlockState[] var17 = new IBlockState[]{glassState};
      var10000 = var10000.where('A', RecipeMapMultiblockController.states(var17));
      var17 = new IBlockState[]{gearboxCasingState};
      var10000 = var10000.where('B', RecipeMapMultiblockController.states(var17));
      var17 = new IBlockState[]{fourthCasingState};
      var10000 = var10000.where('C', RecipeMapMultiblockController.states(var17));
      var17 = new IBlockState[]{pipeCasingState};
      var10000 = var10000.where('E', RecipeMapMultiblockController.states(var17));
      var17 = new IBlockState[]{secondCasingState};
      var10000 = var10000.where('F', RecipeMapMultiblockController.states(var17));
      Material[] var22 = new Material[]{Materials.VanadiumGallium};
      var10000 = var10000.where('G', RecipeMapMultiblockController.frames(var22));
      IBlockState[] var23 = new IBlockState[]{uniqueCasingState};
      var10000 = var10000.where('H', RecipeMapMultiblockController.states(var23));
      var23 = new IBlockState[]{fifthCasingState};
      var10000 = var10000.where('I', RecipeMapMultiblockController.states(var23));
      var23 = new IBlockState[]{thirdCasingState};
      BlockPattern var48 = var10000.where('J', RecipeMapMultiblockController.states(var23)).where(' ', RecipeMapMultiblockController.any()).build();
      Intrinsics.checkNotNullExpressionValue(var48, "build(...)");
      return var48;
   }

   @SideOnly(Side.CLIENT)
   @NotNull
   public ICubeRenderer getBaseTexture(@Nullable IMultiblockPart sourcePart) {
      return (ICubeRenderer)GTLiteOverlays.VANADIUM_GALLIUM_CASING;
   }

   @SideOnly(Side.CLIENT)
   @NotNull
   protected ICubeRenderer getFrontOverlay() {
      return (ICubeRenderer)GTLiteOverlays.INTEGRATED_ORE_PROCESSOR_OVERLAY;
   }

   @SideOnly(Side.CLIENT)
   public void renderMetaTileEntity(@Nullable CCRenderState renderState, @NotNull Matrix4 translation, @Nullable IVertexOperation[] pipeline) {
      Intrinsics.checkNotNullParameter(translation, "translation");
      super.renderMetaTileEntity(renderState, translation, pipeline);
      OreProcessorRenderer var10000 = GTLiteTextures.getORE_PROCESSOR_CONTROLLER();
      EnumFacing var10004 = this.frontFacing;
      Intrinsics.checkNotNullExpressionValue(var10004, "frontFacing");
      var10000.renderSided(renderState, translation, pipeline, var10004, this.isStructureFormed(), this.recipeMapWorkable.isActive());
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(@NotNull ItemStack stack, @Nullable World player, @NotNull List<String> tooltip, boolean advanced) {
      Intrinsics.checkNotNullParameter(stack, "stack");
      Intrinsics.checkNotNullParameter(tooltip, "tooltip");
      super.addInformation(stack, player, tooltip, advanced);
      String var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.1", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      tooltip.add(TooltipHelper.RAINBOW_SLOW + I18n.func_135052_a("gregtech.machine.perfect_oc", new Object[0]));
      var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.2", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.3", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.4", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.5", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.6", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.7", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.8", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.9", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.10", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.11", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.12", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.13", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.14", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      var10001 = I18n.func_135052_a("gtlitecore.machine.integrated_ore_processor.tooltip.15", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
      var10001 = I18n.func_135052_a("gtlitecore.tooltip.machine.laser_hatch", new Object[0]);
      Intrinsics.checkNotNullExpressionValue(var10001, "format(...)");
      tooltip.add(var10001);
   }

   public boolean hasMaintenanceMechanics() {
      return false;
   }

   @NotNull
   public SoundEvent getBreakdownSound() {
      SoundEvent var10000 = GTSoundEvents.BREAKDOWN_ELECTRICAL;
      Intrinsics.checkNotNullExpressionValue(var10000, "BREAKDOWN_ELECTRICAL");
      return var10000;
   }

   public boolean onRightClick(@NotNull EntityPlayer playerIn, @Nullable EnumHand hand, @Nullable EnumFacing facing, @Nullable CuboidRayTraceResult hitResult) {
      Intrinsics.checkNotNullParameter(playerIn, "playerIn");
      return this.onControllerInteract(playerIn) || super.onRightClick(playerIn, hand, facing, hitResult);
   }

   public void onLeftClick(@NotNull EntityPlayer player, @Nullable EnumFacing facing, @Nullable CuboidRayTraceResult hitResult) {
      Intrinsics.checkNotNullParameter(player, "player");
      this.onControllerInteract(player);
   }

   public boolean onWrenchClick(@NotNull EntityPlayer playerIn, @Nullable EnumHand hand, @Nullable EnumFacing wrenchSide, @Nullable CuboidRayTraceResult hitResult) {
      Intrinsics.checkNotNullParameter(playerIn, "playerIn");
      return this.onControllerInteract(playerIn) || super.onWrenchClick(playerIn, hand, wrenchSide, hitResult);
   }

   public boolean onScrewdriverClick(@NotNull EntityPlayer playerIn, @Nullable EnumHand hand, @Nullable EnumFacing facing, @Nullable CuboidRayTraceResult hitResult) {
      Intrinsics.checkNotNullParameter(playerIn, "playerIn");
      return this.onControllerInteract(playerIn);
   }

   private final boolean onControllerInteract(EntityPlayer player) {
      if (player.func_184812_l_()) {
         return false;
      } else if (!this.getWorld().field_72995_K && this.isActive()) {
         player.func_70097_a(GTLiteDamageSources.INSTANCE.getORE_CRUSHING(), 7.0F);
         return true;
      } else {
         return false;
      }
   }

   static {
      casingState = MetalCasing.VANADIUM_GALLIUM.getState();
      secondCasingState = MetalCasing.HASTELLOY_N.getState();
      thirdCasingState = MetalCasing.STELLITE.getState();
      fourthCasingState = GTMultiblockCasing.GRATE_CASING.getState();
      fifthCasingState = GTMultiblockCasing.EXTREME_ENGINE_INTAKE_CASING.getState();
      gearboxCasingState = GTTurbineCasing.TUNGSTENSTEEL_GEARBOX.getState();
      pipeCasingState = GTBoilerCasing.TUNGSTENSTEEL_PIPE.getState();
      uniqueCasingState = ActiveUniqueCasing.HEAT_VENT.getState();
      glassState = GlassCasing.SILICON_CARBIDE.getState();
   }

   @Metadata(
      mv = {2, 1, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"},
      d2 = {"Lgregtechlite/gtlitecore/common/metatileentity/multiblock/MultiblockIntegratedOreProcessor$Companion;", "", "<init>", "()V", "casingState", "Lnet/minecraft/block/state/IBlockState;", "secondCasingState", "thirdCasingState", "fourthCasingState", "fifthCasingState", "gearboxCasingState", "pipeCasingState", "uniqueCasingState", "glassState", "gtlitecore"}
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
