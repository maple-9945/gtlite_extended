package gregtechlite.gtlitecore.common;

import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.registry.MTEManager;
import gregtech.api.metatileentity.registry.MTERegistry;
import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.event.MaterialRegistryEvent;
import gregtech.api.unification.material.event.PostMaterialEvent;
import gregtech.api.unification.material.registry.MaterialRegistry;
import gregtech.loaders.recipe.CraftingComponent;
import gregtechlite.gtlitecore.api.metatileentity.sync.MetaTileEntitySyncBatcher;
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties;
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials;
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags;
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet;
import gregtechlite.gtlitecore.api.unification.material.info.MaterialInfoCache;
import gregtechlite.gtlitecore.api.unification.material.properties.AlloyBlastPropertyAdder;
import gregtechlite.gtlitecore.api.unification.material.properties.AmorphousLensPropertyAdder;
import gregtechlite.gtlitecore.api.unification.material.properties.GTLiteMaterialProperties;
import gregtechlite.gtlitecore.api.unification.material.properties.GTLiteToolPropertyAdder;
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix;
import gregtechlite.gtlitecore.common.item.GTLiteToolItems;
import gregtechlite.gtlitecore.loader.RecyclingDataLoader;
import gregtechlite.gtlitecore.loader.WoodTypeEntryLoader;
import gregtechlite.gtlitecore.loader.ore.OreDictionaryLoader;
import gregtechlite.gtlitecore.loader.recipe.RecipeManager;
import gregtechlite.gtlitecore.loader.recipe.component.CraftingComponents;
import gregtechlite.gtlitecore.loader.recipe.handler.RecipeHandlers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00070\u0005¢\u0006\u0002\b\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\rH\u0007J\u0016\u0010\u000e\u001a\u00020\n2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0007J\u0015\u0010\u0011\u001a\u00070\u0012¢\u0006\u0002\b\u00062\u0006\u0010\u0007\u001a\u00020\u0013H\u0007J\u0016\u0010\u0014\u001a\u00020\n2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0007J\u0016\u0010\u0017\u001a\u00020\n2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0007J\u0010\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0019H\u0007¨\u0006\u001a"},
   d2 = {"Lgregtechlite/gtlitecore/common/EventHandlers;", "", "<init>", "()V", "createMaterialRegistry", "Lgregtech/api/unification/material/registry/MaterialRegistry;", "Lorg/jetbrains/annotations/NotNull;", "event", "Lgregtech/api/unification/material/event/MaterialRegistryEvent;", "registerMaterials", "", "Lgregtech/api/unification/material/event/MaterialEvent;", "registerPostMaterials", "Lgregtech/api/unification/material/event/PostMaterialEvent;", "registerMaterialComponents", "Lgregtech/api/GregTechAPI$RegisterEvent;", "Lgregtech/loaders/recipe/CraftingComponent;", "createMetaTileEntityRegistry", "Lgregtech/api/metatileentity/registry/MTERegistry;", "Lgregtech/api/metatileentity/registry/MTEManager$MTERegistryEvent;", "registerRecipes", "Lnet/minecraftforge/event/RegistryEvent$Register;", "Lnet/minecraft/item/crafting/IRecipe;", "registerRecipeHandlers", "onWorldTickEnd", "Lnet/minecraftforge/fml/common/gameevent/TickEvent$WorldTickEvent;", "gtlitecore"}
)
public final class EventHandlers {
   @NotNull
   public static final EventHandlers INSTANCE = new EventHandlers();

   private EventHandlers() {
   }

   @SubscribeEvent
   @NotNull
   public final MaterialRegistry createMaterialRegistry(@NotNull MaterialRegistryEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      MaterialRegistry var10000 = GregTechAPI.materialManager.createRegistry("gtlitecore");
      Intrinsics.checkNotNullExpressionValue(var10000, "createRegistry(...)");
      return var10000;
   }

   @SubscribeEvent(
      priority = EventPriority.HIGH
   )
   public final void registerMaterials(@NotNull MaterialEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      GTLiteOrePrefix.INSTANCE.addToMetaItem();
      GTLiteMaterials.INSTANCE.init();
      GTLiteOrePrefix.INSTANCE.setOrePrefixInfos();
      GTLiteMaterialProperties.INSTANCE.init$gtlitecore();
      GTLiteToolPropertyAdder.INSTANCE.initSoftToolProperties$gtlitecore();
      GTLiteMaterialFlags.INSTANCE.setMaterialFlags$gtlitecore();
      GTLiteMaterialIconSet.INSTANCE.setMaterialIconSets();
      GTLiteToolItems.INSTANCE.registerTools$gtlitecore();
      GTLiteToolItems.INSTANCE.addToolSymbols();
      MaterialInfoCache.INSTANCE.build();
   }

   @SubscribeEvent
   public final void registerPostMaterials(@NotNull PostMaterialEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      AlloyBlastPropertyAdder.INSTANCE.preInit();
      AmorphousLensPropertyAdder.INSTANCE.preInit();
      AlloyBlastPropertyAdder.INSTANCE.init$gtlitecore();
   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public final void registerMaterialComponents(@NotNull GregTechAPI.RegisterEvent<CraftingComponent> event) {
      Intrinsics.checkNotNullParameter(event, "event");
      CraftingComponents.INSTANCE.setCraftingComponents();
   }

   @SubscribeEvent
   @NotNull
   public final MTERegistry createMetaTileEntityRegistry(@NotNull MTEManager.MTERegistryEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      MTERegistry var10000 = GregTechAPI.mteManager.createRegistry("gtlitecore");
      Intrinsics.checkNotNullExpressionValue(var10000, "createRegistry(...)");
      return var10000;
   }

   @SubscribeEvent(
      priority = EventPriority.LOW
   )
   public final void registerRecipes(@NotNull RegistryEvent.Register<IRecipe> event) {
      Intrinsics.checkNotNullParameter(event, "event");
      GTLiteRecipeProperties.INSTANCE.init$gtlitecore();
      RecyclingDataLoader.INSTANCE.init();
      OreDictionaryLoader.INSTANCE.init();
      WoodTypeEntryLoader.INSTANCE.init();
      RecipeManager.INSTANCE.init();
   }

   @SubscribeEvent
   public final void registerRecipeHandlers(@NotNull RegistryEvent.Register<IRecipe> event) {
      Intrinsics.checkNotNullParameter(event, "event");
      RecipeHandlers.INSTANCE.init();
   }

   @SubscribeEvent
   public final void onWorldTickEnd(@NotNull TickEvent.WorldTickEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (event.phase == Phase.END && !event.world.field_72995_K) {
         MetaTileEntitySyncBatcher.INSTANCE.get(event.world.field_73011_w.getDimension()).flush();
      }

   }
}
