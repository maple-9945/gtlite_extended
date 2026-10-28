package gregtech.common;

import gregtech.api.GregTechAPI;
import gregtech.api.block.VariantItemBlock;
import gregtech.api.block.coil.CoilRegistry;
import gregtech.api.block.coil.CustomCoilBlock;
import gregtech.api.block.machines.MachineItemBlock;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.toolitem.IGTTool;
import gregtech.api.metatileentity.registry.MTERegistry;
import gregtech.api.recipes.GTRecipeInputCache;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.GTRecipeOreInput;
import gregtech.api.recipes.properties.impl.FusionEUToStartProperty;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.DustProperty;
import gregtech.api.unification.material.properties.FluidPipeProperties;
import gregtech.api.unification.material.properties.ItemPipeProperties;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.WireProperties;
import gregtech.api.unification.material.registry.MaterialRegistry;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.ore.StoneType;
import gregtech.api.unification.stack.RecyclingData;
import gregtech.api.util.AssemblyLineManager;
import gregtech.api.util.GTControlledRegistry;
import gregtech.api.util.GTLog;
import gregtech.common.blocks.BlockCompressed;
import gregtech.common.blocks.BlockFrame;
import gregtech.common.blocks.BlockLamp;
import gregtech.common.blocks.BlockOre;
import gregtech.common.blocks.BlockSurfaceRock;
import gregtech.common.blocks.LampItemBlock;
import gregtech.common.blocks.MaterialItemBlock;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.OreItemBlock;
import gregtech.common.blocks.StoneVariantBlock;
import gregtech.common.items.MetaItems;
import gregtech.common.items.ToolItems;
import gregtech.common.pipelike.cable.BlockCable;
import gregtech.common.pipelike.cable.ItemBlockCable;
import gregtech.common.pipelike.fluidpipe.BlockFluidPipe;
import gregtech.common.pipelike.fluidpipe.ItemBlockFluidPipe;
import gregtech.common.pipelike.itempipe.BlockItemPipe;
import gregtech.common.pipelike.itempipe.ItemBlockItemPipe;
import gregtech.common.pipelike.laser.BlockLaserPipe;
import gregtech.common.pipelike.laser.ItemBlockLaserPipe;
import gregtech.common.pipelike.optical.BlockOpticalPipe;
import gregtech.common.pipelike.optical.ItemBlockOpticalPipe;
import gregtech.datafix.GTDataFixers;
import gregtech.integration.groovy.GroovyScriptModule;
import gregtech.loaders.MaterialInfoLoader;
import gregtech.loaders.OreDictionaryLoader;
import gregtech.loaders.recipe.CraftingComponent;
import gregtech.loaders.recipe.GTRecipeManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.ArrayUtils;

@EventBusSubscriber(
   modid = "gregtech"
)
public class CommonProxy {
   @SubscribeEvent
   public static void registerBlocks(RegistryEvent.Register<Block> event) {
      GTLog.logger.info("Registering Blocks...");
      IForgeRegistry<Block> registry = event.getRegistry();

      for(MTERegistry r : GregTechAPI.mteManager.getRegistries()) {
         if (!registry.getKeys().isEmpty()) {
            registry.register(r.getBlock());
         }
      }

      for(CoilRegistry r : GregTechAPI.coilManager.getRegistries()) {
         for(CustomCoilBlock block : r) {
            registry.register(block);
         }
      }

      StoneType.init();

      for(MaterialRegistry materialRegistry : GregTechAPI.materialManager.getRegistries()) {
         for(Material material : materialRegistry) {
            if (material.hasProperty(PropertyKey.ORE) && !material.hasFlag(MaterialFlags.DISABLE_ORE_BLOCK)) {
               createOreBlock(material);
            }

            if (material.hasProperty(PropertyKey.WIRE)) {
               for(BlockCable cable : (BlockCable[])MetaBlocks.CABLES.get(materialRegistry.getModid())) {
                  if (cable.isValidPipeMaterial(material)) {
                     cable.addPipeMaterial(material, (WireProperties)material.getProperty(PropertyKey.WIRE));
                  }
               }
            }

            if (material.hasProperty(PropertyKey.FLUID_PIPE)) {
               for(BlockFluidPipe pipe : (BlockFluidPipe[])MetaBlocks.FLUID_PIPES.get(materialRegistry.getModid())) {
                  if (pipe.isValidPipeMaterial(material)) {
                     pipe.addPipeMaterial(material, (FluidPipeProperties)material.getProperty(PropertyKey.FLUID_PIPE));
                  }
               }
            }

            if (material.hasProperty(PropertyKey.ITEM_PIPE)) {
               for(BlockItemPipe pipe : (BlockItemPipe[])MetaBlocks.ITEM_PIPES.get(materialRegistry.getModid())) {
                  if (pipe.isValidPipeMaterial(material)) {
                     pipe.addPipeMaterial(material, (ItemPipeProperties)material.getProperty(PropertyKey.ITEM_PIPE));
                  }
               }
            }
         }

         for(BlockCable cable : (BlockCable[])MetaBlocks.CABLES.get(materialRegistry.getModid())) {
            if (!cable.getEnabledMaterials().isEmpty()) {
               registry.register(cable);
            }
         }

         for(BlockFluidPipe pipe : (BlockFluidPipe[])MetaBlocks.FLUID_PIPES.get(materialRegistry.getModid())) {
            if (!pipe.getEnabledMaterials().isEmpty()) {
               registry.register(pipe);
            }
         }

         for(BlockItemPipe pipe : (BlockItemPipe[])MetaBlocks.ITEM_PIPES.get(materialRegistry.getModid())) {
            if (!pipe.getEnabledMaterials().isEmpty()) {
               registry.register(pipe);
            }
         }
      }

      for(BlockOpticalPipe pipe : MetaBlocks.OPTICAL_PIPES) {
         registry.register(pipe);
      }

      for(BlockLaserPipe pipe : MetaBlocks.LASER_PIPES) {
         registry.register(pipe);
      }

      registry.register(MetaBlocks.LD_ITEM_PIPE);
      registry.register(MetaBlocks.LD_FLUID_PIPE);
      registry.register(MetaBlocks.HERMETIC_CASING);
      registry.register(MetaBlocks.CLEANROOM_CASING);
      registry.register(MetaBlocks.COMPUTER_CASING);
      registry.register(MetaBlocks.BATTERY_BLOCK);
      registry.register(MetaBlocks.FOAM);
      registry.register(MetaBlocks.REINFORCED_FOAM);
      registry.register(MetaBlocks.PETRIFIED_FOAM);
      registry.register(MetaBlocks.REINFORCED_PETRIFIED_FOAM);
      registry.register(MetaBlocks.BOILER_CASING);
      registry.register(MetaBlocks.BOILER_FIREBOX_CASING);
      registry.register(MetaBlocks.METAL_CASING);
      registry.register(MetaBlocks.TURBINE_CASING);
      registry.register(MetaBlocks.MACHINE_CASING);
      registry.register(MetaBlocks.STEAM_CASING);
      registry.register(MetaBlocks.MULTIBLOCK_CASING);
      registry.register(MetaBlocks.TRANSPARENT_CASING);
      registry.register(MetaBlocks.WIRE_COIL);
      registry.register(MetaBlocks.FUSION_CASING);
      registry.register(MetaBlocks.WARNING_SIGN);
      registry.register(MetaBlocks.WARNING_SIGN_1);
      registry.register(MetaBlocks.ASPHALT);

      for(StoneVariantBlock block : MetaBlocks.STONE_BLOCKS.values()) {
         registry.register(block);
      }

      registry.register(MetaBlocks.RUBBER_LOG);
      registry.register(MetaBlocks.RUBBER_LEAVES);
      registry.register(MetaBlocks.RUBBER_SAPLING);
      registry.register(MetaBlocks.PLANKS);
      registry.register(MetaBlocks.WOOD_SLAB);
      registry.register(MetaBlocks.DOUBLE_WOOD_SLAB);
      registry.register(MetaBlocks.RUBBER_WOOD_STAIRS);
      registry.register(MetaBlocks.TREATED_WOOD_STAIRS);
      registry.register(MetaBlocks.RUBBER_WOOD_FENCE);
      registry.register(MetaBlocks.TREATED_WOOD_FENCE);
      registry.register(MetaBlocks.RUBBER_WOOD_FENCE_GATE);
      registry.register(MetaBlocks.TREATED_WOOD_FENCE_GATE);
      registry.register(MetaBlocks.RUBBER_WOOD_DOOR);
      registry.register(MetaBlocks.TREATED_WOOD_DOOR);
      registry.register(MetaBlocks.BRITTLE_CHARCOAL);
      registry.register(MetaBlocks.POWDERBARREL);
      registry.register(MetaBlocks.ITNT);
      registry.register(MetaBlocks.METAL_SHEET);
      registry.register(MetaBlocks.LARGE_METAL_SHEET);
      registry.register(MetaBlocks.STUDS);

      for(BlockLamp block : MetaBlocks.LAMPS.values()) {
         registry.register(block);
      }

      for(BlockLamp block : MetaBlocks.BORDERLESS_LAMPS.values()) {
         registry.register(block);
      }

      for(BlockCompressed block : MetaBlocks.COMPRESSED_BLOCKS) {
         registry.register(block);
      }

      for(BlockFrame block : MetaBlocks.FRAME_BLOCKS) {
         registry.register(block);
      }

      for(BlockSurfaceRock block : MetaBlocks.SURFACE_ROCK_BLOCKS) {
         registry.register(block);
      }

      for(BlockOre block : MetaBlocks.ORES) {
         registry.register(block);
      }

   }

   private static void createOreBlock(Material material) {
      StoneType[] stoneTypeBuffer = new StoneType[16];
      int generationIndex = 0;

      for(StoneType stoneType : StoneType.STONE_TYPE_REGISTRY) {
         int id = StoneType.STONE_TYPE_REGISTRY.func_148757_b(stoneType);
         int index = id / 16;
         if (index > generationIndex) {
            createOreBlock(material, (StoneType[])copyNotNull(stoneTypeBuffer), generationIndex);
            Arrays.fill(stoneTypeBuffer, (Object)null);
         }

         stoneTypeBuffer[id % 16] = stoneType;
         generationIndex = index;
      }

      createOreBlock(material, (StoneType[])copyNotNull(stoneTypeBuffer), generationIndex);
   }

   private static <T> T[] copyNotNull(T[] src) {
      int nullIndex = ArrayUtils.indexOf(src, (Object)null);
      return (T[])Arrays.copyOfRange(src, 0, nullIndex == -1 ? src.length : nullIndex);
   }

   private static void createOreBlock(Material material, StoneType[] stoneTypes, int index) {
      BlockOre block = new BlockOre(material, stoneTypes);
      block.setRegistryName("ore_" + material + "_" + index);

      for(StoneType stoneType : stoneTypes) {
         ((Map)GregTechAPI.oreBlockTable.computeIfAbsent(material, (m) -> new HashMap())).put(stoneType, block);
      }

      MetaBlocks.ORES.add(block);
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public static void registerBlocksLast(RegistryEvent.Register<Block> event) {
      List var10000 = MetaBlocks.FLUID_BLOCKS;
      IForgeRegistry var10001 = event.getRegistry();
      Objects.requireNonNull(var10001);
      var10000.forEach(var10001::register);
   }

   @SubscribeEvent
   public static void registerItems(RegistryEvent.Register<Item> event) {
      GTLog.logger.info("Registering Items...");
      IForgeRegistry<Item> registry = event.getRegistry();

      for(MetaItem<?> item : MetaItems.ITEMS) {
         registry.register(item);
         item.registerSubItems();
      }

      for(IGTTool tool : ToolItems.getAllTools()) {
         registry.register(tool.get());
      }

      GTRecipeManager.preLoad();

      for(MTERegistry r : GregTechAPI.mteManager.getRegistries()) {
         if (!r.func_148742_b().isEmpty()) {
            registry.register(createItemBlock(r.getBlock(), MachineItemBlock::new));
         }
      }

      for(MaterialRegistry materialRegistry : GregTechAPI.materialManager.getRegistries()) {
         for(BlockCable cable : (BlockCable[])MetaBlocks.CABLES.get(materialRegistry.getModid())) {
            if (!cable.getEnabledMaterials().isEmpty()) {
               registry.register(createItemBlock(cable, ItemBlockCable::new));
            }
         }

         for(BlockFluidPipe pipe : (BlockFluidPipe[])MetaBlocks.FLUID_PIPES.get(materialRegistry.getModid())) {
            if (!pipe.getEnabledMaterials().isEmpty()) {
               registry.register(createItemBlock(pipe, ItemBlockFluidPipe::new));
            }
         }

         for(BlockItemPipe pipe : (BlockItemPipe[])MetaBlocks.ITEM_PIPES.get(materialRegistry.getModid())) {
            if (!pipe.getEnabledMaterials().isEmpty()) {
               registry.register(createItemBlock(pipe, ItemBlockItemPipe::new));
            }
         }
      }

      for(BlockOpticalPipe pipe : MetaBlocks.OPTICAL_PIPES) {
         registry.register(createItemBlock(pipe, ItemBlockOpticalPipe::new));
      }

      for(BlockLaserPipe pipe : MetaBlocks.LASER_PIPES) {
         registry.register(createItemBlock(pipe, ItemBlockLaserPipe::new));
      }

      registry.register(createItemBlock(MetaBlocks.LD_ITEM_PIPE, ItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.LD_FLUID_PIPE, ItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.HERMETIC_CASING, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.CLEANROOM_CASING, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.COMPUTER_CASING, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.BATTERY_BLOCK, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.BOILER_CASING, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.BOILER_FIREBOX_CASING, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.METAL_CASING, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.TURBINE_CASING, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.MACHINE_CASING, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.STEAM_CASING, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.MULTIBLOCK_CASING, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.TRANSPARENT_CASING, VariantItemBlock::new));

      for(CoilRegistry coilRegistry : GregTechAPI.coilManager.getRegistries()) {
         for(CustomCoilBlock block : coilRegistry) {
            registry.register(createItemBlock(block, VariantItemBlock::new));
         }
      }

      registry.register(createItemBlock(MetaBlocks.WIRE_COIL, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.FUSION_CASING, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.WARNING_SIGN, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.WARNING_SIGN_1, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.METAL_SHEET, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.LARGE_METAL_SHEET, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.STUDS, VariantItemBlock::new));

      for(BlockLamp block : MetaBlocks.LAMPS.values()) {
         registry.register(createItemBlock(block, LampItemBlock::new));
      }

      for(BlockLamp block : MetaBlocks.BORDERLESS_LAMPS.values()) {
         registry.register(createItemBlock(block, LampItemBlock::new));
      }

      registry.register(createItemBlock(MetaBlocks.ASPHALT, VariantItemBlock::new));

      for(StoneVariantBlock block : MetaBlocks.STONE_BLOCKS.values()) {
         registry.register(createItemBlock(block, VariantItemBlock::new));
      }

      registry.register(createItemBlock(MetaBlocks.PLANKS, VariantItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.WOOD_SLAB, (b) -> new ItemSlab(b, b, MetaBlocks.DOUBLE_WOOD_SLAB)));
      registry.register(createItemBlock(MetaBlocks.RUBBER_WOOD_STAIRS, ItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.TREATED_WOOD_STAIRS, ItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.RUBBER_WOOD_FENCE, ItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.TREATED_WOOD_FENCE, ItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.RUBBER_WOOD_FENCE_GATE, ItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.TREATED_WOOD_FENCE_GATE, ItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.BRITTLE_CHARCOAL, ItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.RUBBER_LOG, ItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.RUBBER_LEAVES, ItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.RUBBER_SAPLING, ItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.POWDERBARREL, ItemBlock::new));
      registry.register(createItemBlock(MetaBlocks.ITNT, ItemBlock::new));

      for(BlockCompressed block : MetaBlocks.COMPRESSED_BLOCKS) {
         registry.register(createItemBlock(block, (b) -> new MaterialItemBlock(b, OrePrefix.block)));
      }

      for(BlockFrame block : MetaBlocks.FRAME_BLOCKS) {
         registry.register(createItemBlock(block, (b) -> new MaterialItemBlock(b, OrePrefix.frameGt)));
      }

      for(BlockOre block : MetaBlocks.ORES) {
         registry.register(createItemBlock(block, OreItemBlock::new));
      }

   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public static void initComponents(RegistryEvent.Register<IRecipe> event) {
      GTRecipeInputCache.enableCache();
      CraftingComponent.initializeComponents();
      MinecraftForge.EVENT_BUS.post(new GregTechAPI.RegisterEvent((GTControlledRegistry)null, CraftingComponent.class));
   }

   @SubscribeEvent
   public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
      FusionEUToStartProperty.registerFusionTier(6, "(MK1)");
      FusionEUToStartProperty.registerFusionTier(7, "(MK2)");
      FusionEUToStartProperty.registerFusionTier(8, "(MK3)");
      AssemblyLineManager.registerScannerLogic();
      GTLog.logger.info("Registering ore dictionary...");
      MetaItems.registerOreDict();
      ToolItems.registerOreDict();
      MetaBlocks.registerOreDict();
      OreDictionaryLoader.init();
      MaterialInfoLoader.init();
      MinecraftForge.EVENT_BUS.post(new GregTechAPI.RegisterEvent((GTControlledRegistry)null, RecyclingData.class));
      GTLog.logger.info("Registering recipes...");
      GTRecipeManager.load();
   }

   @SubscribeEvent(
      priority = EventPriority.LOW
   )
   public static void runEarlyMaterialHandlers(RegistryEvent.Register<IRecipe> event) {
      GTLog.logger.info("Running early material handlers...");
      OrePrefix.runMaterialHandlers();
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public static void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
      GTLog.logger.info("Running late material handlers...");
      OrePrefix.runMaterialHandlers();
      GTRecipeManager.loadLatest();
      if (Loader.instance().hasReachedState(LoaderState.AVAILABLE)) {
         GTRecipeInputCache.disableCache();
      }

   }

   @SubscribeEvent
   public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {
      if (event.getModID().equals("gregtech")) {
         ConfigManager.sync("gregtech", Type.INSTANCE);
      }

   }

   @SubscribeEvent
   public static void modifyFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
      ItemStack stack = event.getItemStack();
      Block block = Block.func_149634_a(stack.func_77973_b());
      if (block == MetaBlocks.RUBBER_SAPLING) {
         event.setBurnTime(100);
      } else if (block == MetaBlocks.WOOD_SLAB) {
         event.setBurnTime(150);
      } else if (block instanceof BlockCompressed) {
         Material material = ((BlockCompressed)block).getGtMaterial(stack);
         DustProperty property = (DustProperty)material.getProperty(PropertyKey.DUST);
         if (property != null && property.getBurnTime() > 0) {
            double materialUnitsInBlock = (double)OrePrefix.block.getMaterialAmount(material) / (double)3628800.0F;
            event.setBurnTime((int)(materialUnitsInBlock * (double)property.getBurnTime()));
         }
      }

   }

   private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
      ItemBlock itemBlock = (ItemBlock)producer.apply(block);
      ResourceLocation registryName = block.getRegistryName();
      if (registryName == null) {
         throw new IllegalArgumentException("Block " + block.func_149739_a() + " has no registry name.");
      } else {
         itemBlock.setRegistryName(registryName);
         return itemBlock;
      }
   }

   public void onPreLoad() {
   }

   public void onLoad() {
      GTDataFixers.init();
   }

   public void onPostLoad() {
      if (ConfigHolder.compat.removeSmeltingForEBFMetals) {
         ModHandler.removeSmeltingEBFMetals();
      }

   }

   public void onLoadComplete() {
      GTRecipeInputCache.disableCache();
      if (!GroovyScriptModule.isCurrentlyRunning() && (!GregTechAPI.moduleManager.isModuleEnabled("jei_integration") || FMLCommonHandler.instance().getSide().isServer())) {
         GTRecipeOreInput.refreshStackCache();
      }

   }

   public boolean isFancyGraphics() {
      return true;
   }
}
