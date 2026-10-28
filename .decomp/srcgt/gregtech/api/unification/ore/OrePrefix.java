package gregtech.api.unification.ore;

import com.google.common.base.Preconditions;
import crafttweaker.annotations.ZenRegister;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.MarkerMaterials.Color;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.info.MaterialIconType;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.util.LocalizationUtils;
import gregtech.api.util.function.TriConsumer;
import gregtech.common.ConfigHolder;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gregtech.ore.OrePrefix")
@ZenRegister
public class OrePrefix {
   private static final Map<String, OrePrefix> PREFIXES = new HashMap();
   private static final AtomicInteger idCounter = new AtomicInteger(0);
   public static final OrePrefix ore;
   public static final OrePrefix oreGranite;
   public static final OrePrefix oreDiorite;
   public static final OrePrefix oreAndesite;
   public static final OrePrefix oreBlackgranite;
   public static final OrePrefix oreRedgranite;
   public static final OrePrefix oreMarble;
   public static final OrePrefix oreBasalt;
   public static final OrePrefix oreSand;
   public static final OrePrefix oreRedSand;
   public static final OrePrefix oreNetherrack;
   public static final OrePrefix oreEndstone;
   public static final OrePrefix crushedCentrifuged;
   public static final OrePrefix crushedPurified;
   public static final OrePrefix crushed;
   public static final OrePrefix shard;
   public static final OrePrefix clump;
   public static final OrePrefix reduced;
   public static final OrePrefix crystalline;
   public static final OrePrefix cleanGravel;
   public static final OrePrefix dirtyGravel;
   public static final OrePrefix ingotHot;
   public static final OrePrefix ingot;
   public static final OrePrefix gem;
   public static final OrePrefix gemChipped;
   public static final OrePrefix gemFlawed;
   public static final OrePrefix gemFlawless;
   public static final OrePrefix gemExquisite;
   public static final OrePrefix dustSmall;
   public static final OrePrefix dustTiny;
   public static final OrePrefix dustImpure;
   public static final OrePrefix dustPure;
   public static final OrePrefix dust;
   public static final OrePrefix nugget;
   public static final OrePrefix plateDense;
   public static final OrePrefix plateDouble;
   public static final OrePrefix plate;
   public static final OrePrefix round;
   public static final OrePrefix foil;
   public static final OrePrefix stickLong;
   public static final OrePrefix stick;
   public static final OrePrefix bolt;
   public static final OrePrefix screw;
   public static final OrePrefix ring;
   public static final OrePrefix springSmall;
   public static final OrePrefix spring;
   public static final OrePrefix wireFine;
   public static final OrePrefix rotor;
   public static final OrePrefix gearSmall;
   public static final OrePrefix gear;
   public static final OrePrefix lens;
   public static final OrePrefix toolHeadBuzzSaw;
   public static final OrePrefix toolHeadScrewdriver;
   public static final OrePrefix toolHeadDrill;
   public static final OrePrefix toolHeadChainsaw;
   public static final OrePrefix toolHeadWrench;
   public static final OrePrefix turbineBlade;
   public static final OrePrefix paneGlass;
   public static final OrePrefix blockGlass;
   public static final OrePrefix block;
   public static final OrePrefix log;
   public static final OrePrefix plank;
   public static final OrePrefix slab;
   public static final OrePrefix stair;
   public static final OrePrefix fence;
   public static final OrePrefix fenceGate;
   public static final OrePrefix door;
   public static final OrePrefix lampGt;
   public static final OrePrefix stone;
   public static final OrePrefix frameGt;
   public static final OrePrefix pipeTinyFluid;
   public static final OrePrefix pipeSmallFluid;
   public static final OrePrefix pipeNormalFluid;
   public static final OrePrefix pipeLargeFluid;
   public static final OrePrefix pipeHugeFluid;
   public static final OrePrefix pipeQuadrupleFluid;
   public static final OrePrefix pipeNonupleFluid;
   public static final OrePrefix pipeTinyItem;
   public static final OrePrefix pipeSmallItem;
   public static final OrePrefix pipeNormalItem;
   public static final OrePrefix pipeLargeItem;
   public static final OrePrefix pipeHugeItem;
   public static final OrePrefix pipeSmallRestrictive;
   public static final OrePrefix pipeNormalRestrictive;
   public static final OrePrefix pipeLargeRestrictive;
   public static final OrePrefix pipeHugeRestrictive;
   public static final OrePrefix wireGtHex;
   public static final OrePrefix wireGtOctal;
   public static final OrePrefix wireGtQuadruple;
   public static final OrePrefix wireGtDouble;
   public static final OrePrefix wireGtSingle;
   public static final OrePrefix cableGtHex;
   public static final OrePrefix cableGtOctal;
   public static final OrePrefix cableGtQuadruple;
   public static final OrePrefix cableGtDouble;
   public static final OrePrefix cableGtSingle;
   public static final OrePrefix craftingLens;
   public static final OrePrefix dye;
   public static final OrePrefix battery;
   public static final OrePrefix circuit;
   public static final OrePrefix component;
   public final String name;
   public final int id;
   public final boolean isUnificationEnabled;
   public final boolean isSelfReferencing;
   private @Nullable Predicate<Material> generationCondition;
   public final @Nullable MaterialIconType materialIconType;
   private final long materialAmount;
   public @Nullable Material materialType;
   private final List<IOreRegistrationHandler> oreProcessingHandlers;
   private final Set<Material> ignoredMaterials;
   private final Set<Material> generatedMaterials;
   private final Object2FloatMap<Material> materialAmounts;
   private boolean isMarkerPrefix;
   public byte maxStackSize;
   public final List<MaterialStack> secondaryMaterials;
   public Function<Integer, Float> heatDamageFunction;
   public Function<Material, List<String>> tooltipFunc;
   private String alternativeOreName;
   private static final ThreadLocal<OrePrefix> currentProcessingPrefix;
   private static final ThreadLocal<Material> currentMaterial;

   public static void init() {
      ingotHot.heatDamageFunction = (temp) -> (float)(temp - 1750) / 1000.0F + 2.0F;
      gemFlawless.maxStackSize = 32;
      gemExquisite.maxStackSize = 16;
      plateDouble.maxStackSize = 32;
      plateDense.maxStackSize = 7;
      rotor.maxStackSize = 16;
      gear.maxStackSize = 16;
      toolHeadBuzzSaw.maxStackSize = 16;
      toolHeadScrewdriver.maxStackSize = 16;
      toolHeadDrill.maxStackSize = 16;
      toolHeadChainsaw.maxStackSize = 16;
      toolHeadWrench.maxStackSize = 16;
      craftingLens.setMarkerPrefix(true);
      dye.setMarkerPrefix(true);
      battery.setMarkerPrefix(true);
      circuit.setMarkerPrefix(true);
      gemExquisite.setIgnored(Materials.Sugar);
      gemFlawless.setIgnored(Materials.Sugar);
      gem.setIgnored(Materials.Diamond);
      gem.setIgnored(Materials.Emerald);
      gem.setIgnored(Materials.Lapis);
      gem.setIgnored(Materials.NetherQuartz);
      gem.setIgnored(Materials.Coal);
      excludeAllGems(Materials.Charcoal);
      excludeAllGems(Materials.NetherStar);
      excludeAllGems(Materials.EnderPearl);
      excludeAllGems(Materials.EnderEye);
      excludeAllGems(Materials.Flint);
      excludeAllGemsButNormal(Materials.Lapotron);
      dust.setIgnored(Materials.Redstone);
      dust.setIgnored(Materials.Glowstone);
      dust.setIgnored(Materials.Gunpowder);
      dust.setIgnored(Materials.Sugar);
      dust.setIgnored(Materials.Bone);
      dust.setIgnored(Materials.Blaze);
      stick.setIgnored(Materials.Wood);
      stick.setIgnored(Materials.Bone);
      stick.setIgnored(Materials.Blaze);
      stick.setIgnored(Materials.Paper);
      ingot.setIgnored(Materials.Iron);
      ingot.setIgnored(Materials.Gold);
      ingot.setIgnored(Materials.Paper);
      nugget.setIgnored(Materials.Gold);
      nugget.setIgnored(Materials.Paper);
      nugget.setIgnored(Materials.Iron);
      plate.setIgnored(Materials.Paper);
      block.setIgnored(Materials.Iron);
      block.setIgnored(Materials.Gold);
      block.setIgnored(Materials.Lapis);
      block.setIgnored(Materials.Emerald);
      block.setIgnored(Materials.Redstone);
      block.setIgnored(Materials.Diamond);
      block.setIgnored(Materials.Coal);
      block.setIgnored(Materials.Glass);
      block.setIgnored(Materials.Marble);
      block.setIgnored(Materials.GraniteRed);
      block.setIgnored(Materials.Stone);
      block.setIgnored(Materials.Glowstone);
      block.setIgnored(Materials.Endstone);
      block.setIgnored(Materials.Wheat);
      block.setIgnored(Materials.Oilsands);
      block.setIgnored(Materials.Wood);
      block.setIgnored(Materials.TreatedWood);
      block.setIgnored(Materials.RawRubber);
      block.setIgnored(Materials.Clay);
      block.setIgnored(Materials.Brick);
      block.setIgnored(Materials.Bone);
      block.setIgnored(Materials.NetherQuartz);
      block.setIgnored(Materials.Ice);
      block.setIgnored(Materials.Netherrack);
      block.setIgnored(Materials.Concrete);
      block.setIgnored(Materials.Blaze);
      block.setIgnored(Materials.Lapotron);
      ore.addSecondaryMaterial(new MaterialStack(Materials.Stone, dust.materialAmount));
      oreNetherrack.addSecondaryMaterial(new MaterialStack(Materials.Netherrack, dust.materialAmount));
      oreEndstone.addSecondaryMaterial(new MaterialStack(Materials.Endstone, dust.materialAmount));
      if (ConfigHolder.worldgen.allUniqueStoneTypes) {
         oreGranite.addSecondaryMaterial(new MaterialStack(Materials.Granite, dust.materialAmount));
         oreDiorite.addSecondaryMaterial(new MaterialStack(Materials.Diorite, dust.materialAmount));
         oreAndesite.addSecondaryMaterial(new MaterialStack(Materials.Andesite, dust.materialAmount));
         oreRedgranite.addSecondaryMaterial(new MaterialStack(Materials.GraniteRed, dust.materialAmount));
         oreBlackgranite.addSecondaryMaterial(new MaterialStack(Materials.GraniteBlack, dust.materialAmount));
         oreBasalt.addSecondaryMaterial(new MaterialStack(Materials.Basalt, dust.materialAmount));
         oreMarble.addSecondaryMaterial(new MaterialStack(Materials.Marble, dust.materialAmount));
         oreSand.addSecondaryMaterial(new MaterialStack(Materials.SiliconDioxide, dustTiny.materialAmount));
         oreRedSand.addSecondaryMaterial(new MaterialStack(Materials.SiliconDioxide, dustTiny.materialAmount));
      }

      crushed.addSecondaryMaterial(new MaterialStack(Materials.Stone, dust.materialAmount));
      toolHeadDrill.addSecondaryMaterial(new MaterialStack(Materials.Steel, plate.materialAmount * 4L));
      toolHeadChainsaw.addSecondaryMaterial(new MaterialStack(Materials.Steel, plate.materialAmount * 4L + ring.materialAmount * 2L));
      toolHeadWrench.addSecondaryMaterial(new MaterialStack(Materials.Steel, ring.materialAmount + screw.materialAmount * 2L));
      pipeSmallRestrictive.addSecondaryMaterial(new MaterialStack(Materials.Iron, ring.materialAmount * 2L));
      pipeNormalRestrictive.addSecondaryMaterial(new MaterialStack(Materials.Iron, ring.materialAmount * 2L));
      pipeLargeRestrictive.addSecondaryMaterial(new MaterialStack(Materials.Iron, ring.materialAmount * 2L));
      pipeHugeRestrictive.addSecondaryMaterial(new MaterialStack(Materials.Iron, ring.materialAmount * 2L));
      cableGtSingle.addSecondaryMaterial(new MaterialStack(Materials.Rubber, plate.materialAmount));
      cableGtDouble.addSecondaryMaterial(new MaterialStack(Materials.Rubber, plate.materialAmount));
      cableGtQuadruple.addSecondaryMaterial(new MaterialStack(Materials.Rubber, plate.materialAmount * 2L));
      cableGtOctal.addSecondaryMaterial(new MaterialStack(Materials.Rubber, plate.materialAmount * 3L));
      cableGtHex.addSecondaryMaterial(new MaterialStack(Materials.Rubber, plate.materialAmount * 5L));
      plate.setIgnored(Materials.BorosilicateGlass);
      foil.setIgnored(Materials.BorosilicateGlass);
      dustSmall.setIgnored(Materials.Lapotron);
      dustTiny.setIgnored(Materials.Lapotron);
      block.modifyMaterialAmount(Materials.Glowstone, 4.0F);
      block.modifyMaterialAmount(Materials.NetherQuartz, 4.0F);
      block.modifyMaterialAmount(Materials.CertusQuartz, 4.0F);
      block.modifyMaterialAmount(Materials.Brick, 4.0F);
      block.modifyMaterialAmount(Materials.Clay, 4.0F);
      block.modifyMaterialAmount(Materials.Glass, 1.0F);
      block.modifyMaterialAmount(Materials.Ice, 1.0F);
      block.modifyMaterialAmount(Materials.Obsidian, 1.0F);
      block.modifyMaterialAmount(Materials.Concrete, 1.0F);
      stick.modifyMaterialAmount(Materials.Blaze, 4.0F);
      stick.modifyMaterialAmount(Materials.Bone, 5.0F);
   }

   private static void excludeAllGems(Material material) {
      gem.setIgnored(material);
      excludeAllGemsButNormal(material);
   }

   private static void excludeAllGemsButNormal(Material material) {
      gemChipped.setIgnored(material);
      gemFlawed.setIgnored(material);
      gemFlawless.setIgnored(material);
      gemExquisite.setIgnored(material);
   }

   public OrePrefix(String name, long materialAmount, @Nullable Material material, @Nullable MaterialIconType materialIconType, long flags, @Nullable Predicate<Material> condition) {
      this(name, materialAmount, material, materialIconType, flags, condition, (Function)null);
   }

   public OrePrefix(String name, long materialAmount, @Nullable Material material, @Nullable MaterialIconType materialIconType, long flags, @Nullable Predicate<Material> condition, @Nullable Function<Material, List<String>> tooltipFunc) {
      this.oreProcessingHandlers = new ArrayList();
      this.ignoredMaterials = new HashSet();
      this.generatedMaterials = new HashSet();
      this.materialAmounts = new Object2FloatOpenHashMap();
      this.isMarkerPrefix = false;
      this.maxStackSize = 64;
      this.secondaryMaterials = new ArrayList();
      this.heatDamageFunction = null;
      this.alternativeOreName = null;
      Preconditions.checkArgument(!PREFIXES.containsKey(name), "OrePrefix " + name + " already registered!");
      this.name = name;
      this.id = idCounter.getAndIncrement();
      this.materialAmount = materialAmount;
      this.isSelfReferencing = (flags & 2L) != 0L;
      this.isUnificationEnabled = (flags & 1L) != 0L;
      this.materialIconType = materialIconType;
      this.generationCondition = condition;
      this.tooltipFunc = tooltipFunc;
      if (this.isSelfReferencing) {
         Preconditions.checkNotNull(material, "Material is null for self-referencing OrePrefix");
         this.materialType = material;
      }

      PREFIXES.put(name, this);
   }

   public String name() {
      return this.name;
   }

   public void addSecondaryMaterial(MaterialStack secondaryMaterial) {
      Preconditions.checkNotNull(secondaryMaterial, "secondaryMaterial");
      this.secondaryMaterials.add(secondaryMaterial);
   }

   public void setMarkerPrefix(boolean isMarkerPrefix) {
      this.isMarkerPrefix = isMarkerPrefix;
   }

   public long getMaterialAmount(@Nullable Material material) {
      return material != null && this.isAmountModified(material) ? (long)(3628800.0F * this.materialAmounts.getFloat(material)) : this.materialAmount;
   }

   @ZenMethod
   public static OrePrefix getPrefix(String prefixName) {
      return getPrefix(prefixName, (OrePrefix)null);
   }

   public static OrePrefix getPrefix(String prefixName, @Nullable OrePrefix replacement) {
      return (OrePrefix)PREFIXES.getOrDefault(prefixName, replacement);
   }

   public boolean doGenerateItem(Material material) {
      return !this.isSelfReferencing && !this.isIgnored(material) && (this.generationCondition == null || this.generationCondition.test(material));
   }

   public void setGenerationCondition(@Nullable Predicate<Material> in) {
      this.generationCondition = in;
   }

   public boolean addProcessingHandler(IOreRegistrationHandler... processingHandler) {
      Preconditions.checkNotNull(processingHandler);
      Validate.noNullElements(processingHandler);
      return this.oreProcessingHandlers.addAll(Arrays.asList(processingHandler));
   }

   public <T extends IMaterialProperty> void addProcessingHandler(PropertyKey<T> propertyKey, TriConsumer<OrePrefix, Material, T> handler) {
      this.addProcessingHandler((orePrefix, material) -> {
         if (material.hasProperty(propertyKey) && !material.hasFlag(MaterialFlags.NO_UNIFICATION)) {
            handler.accept(orePrefix, material, material.getProperty(propertyKey));
         }

      });
   }

   public void processOreRegistration(@Nullable Material material) {
      if (this.isSelfReferencing && material == null) {
         material = this.materialType;
      }

      if (material != null) {
         this.generatedMaterials.add(material);
      }

   }

   public static void runMaterialHandlers() {
      for(OrePrefix orePrefix : PREFIXES.values()) {
         orePrefix.runGeneratedMaterialHandlers();
      }

   }

   public static OrePrefix getCurrentProcessingPrefix() {
      return (OrePrefix)currentProcessingPrefix.get();
   }

   public static Material getCurrentMaterial() {
      return (Material)currentMaterial.get();
   }

   private void runGeneratedMaterialHandlers() {
      currentProcessingPrefix.set(this);

      for(Material registeredMaterial : this.generatedMaterials) {
         currentMaterial.set(registeredMaterial);

         for(IOreRegistrationHandler registrationHandler : this.oreProcessingHandlers) {
            registrationHandler.processMaterial(this, registeredMaterial);
         }

         currentMaterial.remove();
      }

      this.generatedMaterials.clear();
      currentProcessingPrefix.remove();
   }

   public void setAlternativeOreName(String name) {
      this.alternativeOreName = name;
   }

   public String getAlternativeOreName() {
      return this.alternativeOreName;
   }

   public String getLocalNameForItem(@NotNull Material material) {
      String specifiedUnlocalized = "item." + material.getUnlocalizedName() + "." + this.name;
      if (LocalizationUtils.hasKey(specifiedUnlocalized)) {
         return LocalizationUtils.format(specifiedUnlocalized, new Object[0]);
      } else {
         String unlocalized = this.findUnlocalizedName(material);
         String matLocalized = material.getLocalizedName();
         String formatted = LocalizationUtils.format(unlocalized, new Object[]{matLocalized});
         return formatted.equals(unlocalized) ? matLocalized : formatted;
      }
   }

   private String findUnlocalizedName(@NotNull Material material) {
      if (material.hasProperty(PropertyKey.POLYMER)) {
         String localizationKey = String.format("item.material.oreprefix.polymer.%s", this.name);
         if (LocalizationUtils.hasKey(localizationKey)) {
            return localizationKey;
         }
      }

      return String.format("item.material.oreprefix.%s", this.name);
   }

   public boolean isIgnored(Material material) {
      return this.ignoredMaterials.contains(material);
   }

   @ZenMethod
   public void setIgnored(Material material) {
      this.ignoredMaterials.add(material);
   }

   @ZenMethod
   public void removeIgnored(@NotNull Material material) {
      this.ignoredMaterials.remove(material);
   }

   public boolean isAmountModified(Material material) {
      return this.materialAmounts.containsKey(material);
   }

   @ZenMethod
   public void modifyMaterialAmount(@NotNull Material material, float amount) {
      this.materialAmounts.put(material, amount);
   }

   public boolean isMarkerPrefix() {
      return this.isMarkerPrefix;
   }

   public @NotNull ItemStack getItemForm(@NotNull Material material) {
      return OreDictUnifier.get(this, material);
   }

   public @NotNull ItemStack getItemForm(@NotNull Material material, int count) {
      return OreDictUnifier.get(this, material, count);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         OrePrefix orePrefix = (OrePrefix)o;
         return this.name.equals(orePrefix.name);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.name.hashCode();
   }

   public static Collection<OrePrefix> values() {
      return PREFIXES.values();
   }

   public String toString() {
      return this.name + "/" + this.id;
   }

   static {
      ore = new OrePrefix("ore", -1L, (Material)null, MaterialIconType.ore, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty);
      oreGranite = new OrePrefix("oreGranite", -1L, (Material)null, MaterialIconType.ore, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty);
      oreDiorite = new OrePrefix("oreDiorite", -1L, (Material)null, MaterialIconType.ore, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty);
      oreAndesite = new OrePrefix("oreAndesite", -1L, (Material)null, MaterialIconType.ore, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty);
      oreBlackgranite = new OrePrefix("oreBlackgranite", -1L, (Material)null, MaterialIconType.ore, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty);
      oreRedgranite = new OrePrefix("oreRedgranite", -1L, (Material)null, MaterialIconType.ore, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty);
      oreMarble = new OrePrefix("oreMarble", -1L, (Material)null, MaterialIconType.ore, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty);
      oreBasalt = new OrePrefix("oreBasalt", -1L, (Material)null, MaterialIconType.ore, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty);
      oreSand = new OrePrefix("oreSand", -1L, (Material)null, MaterialIconType.ore, 1L, (Predicate)null);
      oreRedSand = new OrePrefix("oreRedSand", -1L, (Material)null, MaterialIconType.ore, 1L, (Predicate)null);
      oreNetherrack = new OrePrefix("oreNetherrack", -1L, (Material)null, MaterialIconType.ore, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty);
      oreEndstone = new OrePrefix("oreEndstone", -1L, (Material)null, MaterialIconType.ore, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty);
      crushedCentrifuged = new OrePrefix("crushedCentrifuged", -1L, (Material)null, MaterialIconType.crushedCentrifuged, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty);
      crushedPurified = new OrePrefix("crushedPurified", -1L, (Material)null, MaterialIconType.crushedPurified, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty);
      crushed = new OrePrefix("crushed", -1L, (Material)null, MaterialIconType.crushed, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty, (mat) -> Collections.singletonList(I18n.func_135052_a("metaitem.crushed.tooltip.purify", new Object[0])));
      shard = new OrePrefix("shard", -1L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      clump = new OrePrefix("clump", -1L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      reduced = new OrePrefix("reduced", -1L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      crystalline = new OrePrefix("crystalline", -1L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      cleanGravel = new OrePrefix("cleanGravel", -1L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      dirtyGravel = new OrePrefix("dirtyGravel", -1L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      ingotHot = new OrePrefix("ingotHot", 3628800L, (Material)null, MaterialIconType.ingotHot, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasBlastProperty.and((mat) -> ((BlastProperty)mat.getProperty(PropertyKey.BLAST)).getBlastTemperature() > 1750));
      ingot = new OrePrefix("ingot", 3628800L, (Material)null, MaterialIconType.ingot, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasIngotProperty);
      gem = new OrePrefix("gem", 3628800L, (Material)null, MaterialIconType.gem, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasGemProperty);
      gemChipped = new OrePrefix("gemChipped", 907200L, (Material)null, MaterialIconType.gemChipped, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasGemProperty.and((unused) -> ConfigHolder.recipes.generateLowQualityGems));
      gemFlawed = new OrePrefix("gemFlawed", 1814400L, (Material)null, MaterialIconType.gemFlawed, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasGemProperty.and((unused) -> ConfigHolder.recipes.generateLowQualityGems));
      gemFlawless = new OrePrefix("gemFlawless", 7257600L, (Material)null, MaterialIconType.gemFlawless, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasGemProperty);
      gemExquisite = new OrePrefix("gemExquisite", 14515200L, (Material)null, MaterialIconType.gemExquisite, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasGemProperty);
      dustSmall = new OrePrefix("dustSmall", 907200L, (Material)null, MaterialIconType.dustSmall, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasDustProperty);
      dustTiny = new OrePrefix("dustTiny", 403200L, (Material)null, MaterialIconType.dustTiny, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasDustProperty);
      dustImpure = new OrePrefix("dustImpure", 3628800L, (Material)null, MaterialIconType.dustImpure, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty, (mat) -> Collections.singletonList(I18n.func_135052_a("metaitem.dust.tooltip.purify", new Object[0])));
      dustPure = new OrePrefix("dustPure", 3628800L, (Material)null, MaterialIconType.dustPure, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty, (mat) -> Collections.singletonList(I18n.func_135052_a("metaitem.dust.tooltip.purify", new Object[0])));
      dust = new OrePrefix("dust", 3628800L, (Material)null, MaterialIconType.dust, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasDustProperty);
      nugget = new OrePrefix("nugget", 403200L, (Material)null, MaterialIconType.nugget, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasIngotProperty);
      plateDense = new OrePrefix("plateDense", 32659200L, (Material)null, MaterialIconType.plateDense, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_DENSE));
      plateDouble = new OrePrefix("plateDouble", 7257600L, (Material)null, MaterialIconType.plateDouble, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasIngotProperty.and((mat) -> mat.hasFlags(new MaterialFlag[]{MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_DOUBLE_PLATE}) && !mat.hasFlag(MaterialFlags.NO_SMASHING)));
      plate = new OrePrefix("plate", 3628800L, (Material)null, MaterialIconType.plate, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_PLATE));
      round = new OrePrefix("round", 403200L, (Material)null, MaterialIconType.round, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_ROUND));
      foil = new OrePrefix("foil", 907200L, (Material)null, MaterialIconType.foil, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_FOIL));
      stickLong = new OrePrefix("stickLong", 3628800L, (Material)null, MaterialIconType.stickLong, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_LONG_ROD));
      stick = new OrePrefix("stick", 1814400L, (Material)null, MaterialIconType.stick, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_ROD));
      bolt = new OrePrefix("bolt", 453600L, (Material)null, MaterialIconType.bolt, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_BOLT_SCREW));
      screw = new OrePrefix("screw", 403200L, (Material)null, MaterialIconType.screw, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_BOLT_SCREW));
      ring = new OrePrefix("ring", 907200L, (Material)null, MaterialIconType.ring, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_RING));
      springSmall = new OrePrefix("springSmall", 907200L, (Material)null, MaterialIconType.springSmall, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_SPRING_SMALL) && !mat.hasFlag(MaterialFlags.NO_SMASHING));
      spring = new OrePrefix("spring", 3628800L, (Material)null, MaterialIconType.spring, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_SPRING) && !mat.hasFlag(MaterialFlags.NO_SMASHING));
      wireFine = new OrePrefix("wireFine", 453600L, (Material)null, MaterialIconType.wireFine, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_FINE_WIRE));
      rotor = new OrePrefix("rotor", 14515200L, (Material)null, MaterialIconType.rotor, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_ROTOR));
      gearSmall = new OrePrefix("gearSmall", 3628800L, (Material)null, MaterialIconType.gearSmall, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_SMALL_GEAR));
      gear = new OrePrefix("gear", 14515200L, (Material)null, MaterialIconType.gear, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_GEAR));
      lens = new OrePrefix("lens", 2721600L, (Material)null, MaterialIconType.lens, 1L, (mat) -> mat.hasFlag(MaterialFlags.GENERATE_LENS));
      toolHeadBuzzSaw = new OrePrefix("toolHeadBuzzSaw", 14515200L, (Material)null, MaterialIconType.toolHeadBuzzSaw, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasNoCraftingToolProperty.and((mat) -> mat.hasFlag(MaterialFlags.GENERATE_PLATE)));
      toolHeadScrewdriver = new OrePrefix("toolHeadScrewdriver", 3628800L, (Material)null, MaterialIconType.toolHeadScrewdriver, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasNoCraftingToolProperty.and((mat) -> mat.hasFlag(MaterialFlags.GENERATE_LONG_ROD)));
      toolHeadDrill = new OrePrefix("toolHeadDrill", 14515200L, (Material)null, MaterialIconType.toolHeadDrill, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasToolProperty.and((mat) -> mat.hasFlag(MaterialFlags.GENERATE_PLATE)));
      toolHeadChainsaw = new OrePrefix("toolHeadChainsaw", 7257600L, (Material)null, MaterialIconType.toolHeadChainsaw, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasNoCraftingToolProperty.and((mat) -> mat.hasFlag(MaterialFlags.GENERATE_PLATE)));
      toolHeadWrench = new OrePrefix("toolHeadWrench", 14515200L, (Material)null, MaterialIconType.toolHeadWrench, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasNoCraftingToolProperty.and((mat) -> mat.hasFlag(MaterialFlags.GENERATE_PLATE)));
      turbineBlade = new OrePrefix("turbineBlade", 36288000L, (Material)null, MaterialIconType.turbineBlade, 1L, gregtech.api.unification.ore.OrePrefix.Conditions.hasRotorProperty.and((m) -> m.hasFlags(new MaterialFlag[]{MaterialFlags.GENERATE_BOLT_SCREW, MaterialFlags.GENERATE_PLATE}) && !m.hasProperty(PropertyKey.GEM)));
      paneGlass = new OrePrefix("paneGlass", -1L, Color.Colorless, (MaterialIconType)null, 2L, (Predicate)null);
      blockGlass = new OrePrefix("blockGlass", -1L, Color.Colorless, (MaterialIconType)null, 2L, (Predicate)null);
      block = new OrePrefix("block", 32659200L, (Material)null, MaterialIconType.block, 1L, (Predicate)null);
      log = new OrePrefix("log", -1L, (Material)null, (MaterialIconType)null, 0L, (Predicate)null);
      plank = new OrePrefix("plank", -1L, (Material)null, (MaterialIconType)null, 0L, (Predicate)null);
      slab = new OrePrefix("slab", -1L, (Material)null, (MaterialIconType)null, 0L, (Predicate)null);
      stair = new OrePrefix("stair", -1L, (Material)null, (MaterialIconType)null, 0L, (Predicate)null);
      fence = new OrePrefix("fence", -1L, (Material)null, (MaterialIconType)null, 0L, (Predicate)null);
      fenceGate = new OrePrefix("fenceGate", -1L, (Material)null, (MaterialIconType)null, 0L, (Predicate)null);
      door = new OrePrefix("door", -1L, (Material)null, (MaterialIconType)null, 0L, (Predicate)null);
      lampGt = new OrePrefix("lampGt", -1L, (Material)null, (MaterialIconType)null, 0L, (Predicate)null);
      stone = new OrePrefix("stone", -1L, Materials.Stone, (MaterialIconType)null, 2L, (Predicate)null);
      frameGt = new OrePrefix("frameGt", 7257600L, (Material)null, (MaterialIconType)null, 1L, (material) -> material.hasFlag(MaterialFlags.GENERATE_FRAME));
      pipeTinyFluid = new OrePrefix("pipeTinyFluid", 1814400L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeSmallFluid = new OrePrefix("pipeSmallFluid", 3628800L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeNormalFluid = new OrePrefix("pipeNormalFluid", 10886400L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeLargeFluid = new OrePrefix("pipeLargeFluid", 21772800L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeHugeFluid = new OrePrefix("pipeHugeFluid", 43545600L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeQuadrupleFluid = new OrePrefix("pipeQuadrupleFluid", 14515200L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeNonupleFluid = new OrePrefix("pipeNonupleFluid", 32659200L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeTinyItem = new OrePrefix("pipeTinyItem", 1814400L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeSmallItem = new OrePrefix("pipeSmallItem", 3628800L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeNormalItem = new OrePrefix("pipeNormalItem", 10886400L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeLargeItem = new OrePrefix("pipeLargeItem", 21772800L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeHugeItem = new OrePrefix("pipeHugeItem", 43545600L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeSmallRestrictive = new OrePrefix("pipeSmallRestrictive", 3628800L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeNormalRestrictive = new OrePrefix("pipeNormalRestrictive", 10886400L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeLargeRestrictive = new OrePrefix("pipeLargeRestrictive", 21772800L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      pipeHugeRestrictive = new OrePrefix("pipeHugeRestrictive", 43545600L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      wireGtHex = new OrePrefix("wireGtHex", 29030400L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      wireGtOctal = new OrePrefix("wireGtOctal", 14515200L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      wireGtQuadruple = new OrePrefix("wireGtQuadruple", 7257600L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      wireGtDouble = new OrePrefix("wireGtDouble", 3628800L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      wireGtSingle = new OrePrefix("wireGtSingle", 1814400L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      cableGtHex = new OrePrefix("cableGtHex", 29030400L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      cableGtOctal = new OrePrefix("cableGtOctal", 14515200L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      cableGtQuadruple = new OrePrefix("cableGtQuadruple", 7257600L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      cableGtDouble = new OrePrefix("cableGtDouble", 3628800L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      cableGtSingle = new OrePrefix("cableGtSingle", 1814400L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      craftingLens = new OrePrefix("craftingLens", -1L, (Material)null, (MaterialIconType)null, 0L, (Predicate)null);
      dye = new OrePrefix("dye", -1L, (Material)null, (MaterialIconType)null, 0L, (Predicate)null);
      battery = new OrePrefix("battery", -1L, (Material)null, (MaterialIconType)null, 0L, (Predicate)null);
      circuit = new OrePrefix("circuit", -1L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      component = new OrePrefix("component", -1L, (Material)null, (MaterialIconType)null, 1L, (Predicate)null);
      currentProcessingPrefix = new ThreadLocal();
      currentMaterial = new ThreadLocal();
   }
}
