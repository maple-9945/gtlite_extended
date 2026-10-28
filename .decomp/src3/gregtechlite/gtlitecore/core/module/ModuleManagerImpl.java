package gregtechlite.gtlitecore.core.module;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.morphismmc.morphismlib.util.SidedLogger;
import gregtechlite.gtlitecore.api.collection.MoreCollectionsKt;
import gregtechlite.gtlitecore.api.module.CustomModule;
import gregtechlite.gtlitecore.api.module.CustomModuleContainer;
import gregtechlite.gtlitecore.api.module.Module;
import gregtechlite.gtlitecore.api.module.ModuleContainer;
import gregtechlite.gtlitecore.api.module.ModuleManager;
import gregtechlite.gtlitecore.api.module.ModuleStage;
import gregtechlite.gtlitecore.api.module.ModuleManager.DefaultImpls;
import it.unimi.dsi.fastutil.objects.Object2ReferenceMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000Ê\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010!\n\u0002\b\u0002\u0018\u0000 M2\u00020\u0001:\u0001MB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\fH\u0016J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\rJ\b\u0010\u001b\u001a\u00020\tH\u0016J\b\u0010\u001c\u001a\u00020\u0014H\u0016J\u0010\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u0014H\u0016J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\tH\u0016J\u0016\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&J\u000e\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020)J\u000e\u0010*\u001a\u00020 2\u0006\u0010(\u001a\u00020+J\u000e\u0010,\u001a\u00020 2\u0006\u0010(\u001a\u00020-J\u000e\u0010.\u001a\u00020 2\u0006\u0010(\u001a\u00020/J\u000e\u00100\u001a\u00020 2\u0006\u0010(\u001a\u000201J\u000e\u00102\u001a\u00020 2\u0006\u0010(\u001a\u000203J\u000e\u00104\u001a\u00020 2\u0006\u0010(\u001a\u000205J\u000e\u00106\u001a\u00020 2\u0006\u0010(\u001a\u000207J\u000e\u00108\u001a\u00020 2\u0006\u0010(\u001a\u000209J\u000e\u0010:\u001a\u00020 2\u0006\u0010(\u001a\u00020;J\u0014\u0010<\u001a\u00020 2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020?0>J\u0010\u0010@\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\rH\u0002J\b\u0010A\u001a\u00020\u0016H\u0002J\u0010\u0010B\u001a\u00020 2\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010C\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\rH\u0002J\"\u0010D\u001a\u00020 2\u0018\u0010E\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0G0FH\u0002J\u0018\u0010H\u001a\u0004\u0018\u00010\r2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\r0GH\u0002J\u0016\u0010I\u001a\b\u0012\u0004\u0012\u00020\r0G2\u0006\u0010#\u001a\u00020$H\u0002J\"\u0010J\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0L0K2\u0006\u0010#\u001a\u00020$H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\r0\u0010j\b\u0012\u0004\u0012\u00020\r`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006N"},
   d2 = {"Lgregtechlite/gtlitecore/core/module/ModuleManagerImpl;", "Lgregtechlite/gtlitecore/api/module/ModuleManager;", "<init>", "()V", "logger", "Lorg/apache/logging/log4j/Logger;", "containers", "Lit/unimi/dsi/fastutil/objects/Object2ReferenceMap;", "", "Lgregtechlite/gtlitecore/api/module/CustomModuleContainer;", "sortedModules", "Ljava/util/LinkedHashMap;", "Lnet/minecraft/util/ResourceLocation;", "Lgregtechlite/gtlitecore/api/module/CustomModule;", "Lkotlin/collections/LinkedHashMap;", "loadedModules", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "currentContainer", "currentStage", "Lgregtechlite/gtlitecore/api/module/ModuleStage;", "config", "Lnet/minecraftforge/common/config/Configuration;", "isModuleEnabled", "", "namespace", "module", "getLoadedContainer", "getStage", "hasPassedStage", "stage", "registerContainer", "", "container", "setup", "dataTable", "Lnet/minecraftforge/fml/common/discovery/ASMDataTable;", "configDir", "Ljava/io/File;", "onConstruction", "event", "Lnet/minecraftforge/fml/common/event/FMLConstructionEvent;", "onPreInit", "Lnet/minecraftforge/fml/common/event/FMLPreInitializationEvent;", "onInit", "Lnet/minecraftforge/fml/common/event/FMLInitializationEvent;", "onPostInit", "Lnet/minecraftforge/fml/common/event/FMLPostInitializationEvent;", "onLoadComplete", "Lnet/minecraftforge/fml/common/event/FMLLoadCompleteEvent;", "onServerAboutToStart", "Lnet/minecraftforge/fml/common/event/FMLServerAboutToStartEvent;", "onServerStarting", "Lnet/minecraftforge/fml/common/event/FMLServerStartingEvent;", "onServerStarted", "Lnet/minecraftforge/fml/common/event/FMLServerStartedEvent;", "onServerStopping", "Lnet/minecraftforge/fml/common/event/FMLServerStoppingEvent;", "onServerStopped", "Lnet/minecraftforge/fml/common/event/FMLServerStoppedEvent;", "processIMC", "messages", "Lcom/google/common/collect/ImmutableList;", "Lnet/minecraftforge/fml/common/event/FMLInterModComms$IMCMessage;", "getComment", "getConfiguration", "discoverContainers", "getContainerId", "configureModules", "modules", "", "", "getCoreModule", "getInstances", "getModules", "", "", "Companion", "gtlitecore"}
)
@SourceDebugExtension({"SMAP\nModuleManagerImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ModuleManagerImpl.kt\ngregtechlite/gtlitecore/core/module/ModuleManagerImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,477:1\n1053#2:478\n295#2,2:479\n1734#2,3:481\n381#3,7:484\n*S KotlinDebug\n*F\n+ 1 ModuleManagerImpl.kt\ngregtechlite/gtlitecore/core/module/ModuleManagerImpl\n*L\n97#1:478\n393#1:479,2\n432#1:481,3\n472#1:484,7\n*E\n"})
public final class ModuleManagerImpl implements ModuleManager {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final Logger logger = (Logger)(new SidedLogger("gtlitecore-module-loader"));
   @NotNull
   private Object2ReferenceMap<String, CustomModuleContainer> containers = MoreCollectionsKt.openRefLinkedMapOf();
   @NotNull
   private final LinkedHashMap<ResourceLocation, CustomModule> sortedModules = new LinkedHashMap();
   @NotNull
   private final LinkedHashSet<CustomModule> loadedModules = new LinkedHashSet();
   @Nullable
   private CustomModuleContainer currentContainer;
   @NotNull
   private ModuleStage currentStage;
   @Nullable
   private Configuration config;
   @JvmField
   @NotNull
   public static final ModuleManagerImpl instance = new ModuleManagerImpl();
   @NotNull
   private static final String MODULE_CFG_FILE_NAME = "modules.cfg";
   @NotNull
   private static final String MODULE_CFG_CATEGORY_NAME = "modules";
   @Nullable
   private static File configFolder;

   private ModuleManagerImpl() {
      this.currentStage = ModuleStage.C_SETUP;
   }

   public boolean isModuleEnabled(@NotNull ResourceLocation namespace) {
      Intrinsics.checkNotNullParameter(namespace, "namespace");
      return this.sortedModules.containsKey(namespace);
   }

   public final boolean isModuleEnabled(@NotNull CustomModule module) {
      Intrinsics.checkNotNullParameter(module, "module");
      Module annotation = (Module)module.getClass().getAnnotation(Module.class);
      String comment = this.getComment(module);
      String propertyKey = annotation.containerId() + ':' + annotation.moduleId();
      Property property = this.getConfiguration().get("modules", propertyKey, true, comment);
      return property.getBoolean();
   }

   @NotNull
   public CustomModuleContainer getLoadedContainer() {
      CustomModuleContainer var10000 = this.currentContainer;
      if (var10000 == null) {
         String var1 = "Required value was null.";
         throw new IllegalStateException(var1.toString());
      } else {
         return var10000;
      }
   }

   @NotNull
   public ModuleStage getStage() {
      return this.currentStage;
   }

   public boolean hasPassedStage(@NotNull ModuleStage stage) {
      Intrinsics.checkNotNullParameter(stage, "stage");
      return this.currentStage.ordinal() > stage.ordinal();
   }

   public void registerContainer(@NotNull CustomModuleContainer container) {
      Intrinsics.checkNotNullParameter(container, "container");
      if (this.currentStage != ModuleStage.C_SETUP) {
         this.logger.error("Failed to register ModuleContainer '{}', as Module loading has already begun", container);
      } else {
         ((Map)this.containers).put(container.getId(), container);
      }
   }

   public final void setup(@NotNull ASMDataTable dataTable, @NotNull File configDir) {
      Intrinsics.checkNotNullParameter(dataTable, "dataTable");
      Intrinsics.checkNotNullParameter(configDir, "configDir");
      this.discoverContainers(dataTable);
      Object2ReferenceMap var3 = MoreCollectionsKt.openRefLinkedMapOf();
      Object2ReferenceMap sortedContainers = var3;
      int var5 = 0;
      Set var10000 = this.containers.entrySet();
      Intrinsics.checkNotNullExpressionValue(var10000, "<get-entries>(...)");
      Iterable $this$sortedBy$iv = (Iterable)var10000;
      int $i$f$sortedBy = 0;

      for(Map.Entry var14 : CollectionsKt.sortedWith($this$sortedBy$iv, new ModuleManagerImpl$setup$lambda$1$$inlined$sortedBy$1())) {
         Intrinsics.checkNotNull(var14);
         String k = (String)var14.getKey();
         CustomModuleContainer v = (CustomModuleContainer)var14.getValue();
         ((Map)sortedContainers).put(k, v);
      }

      this.containers = var3;
      this.currentStage = ModuleStage.M_SETUP;
      Companion var22 = Companion;
      configFolder = new File(configDir, "gtlitecore");
      Map modules = this.getModules(dataTable);
      this.configureModules(modules);
      Iterator var23 = this.loadedModules.iterator();
      Intrinsics.checkNotNullExpressionValue(var23, "iterator(...)");
      Iterator var12 = var23;

      while(var12.hasNext()) {
         Object var24 = var12.next();
         Intrinsics.checkNotNullExpressionValue(var24, "next(...)");
         CustomModule module = (CustomModule)var24;
         this.currentContainer = (CustomModuleContainer)this.containers.get(this.getContainerId(module));
         module.getLogger().debug("Registering Event Handlers");

         for(Class eventClass : module.getEventBusSubscribers()) {
            MinecraftForge.EVENT_BUS.register(eventClass);
         }

         for(Class terrainGenClass : module.getTerrainGenBusSubscribers()) {
            MinecraftForge.TERRAIN_GEN_BUS.register(terrainGenClass);
         }

         for(Class oreGenClass : module.getOreGenBusSubscribers()) {
            MinecraftForge.ORE_GEN_BUS.register(oreGenClass);
         }
      }

      this.currentContainer = null;
   }

   public final void onConstruction(@NotNull FMLConstructionEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      this.currentStage = ModuleStage.CONSTRUCTION;
      Iterator var10000 = this.loadedModules.iterator();
      Intrinsics.checkNotNullExpressionValue(var10000, "iterator(...)");
      Iterator var2 = var10000;

      while(var2.hasNext()) {
         Object var4 = var2.next();
         Intrinsics.checkNotNullExpressionValue(var4, "next(...)");
         CustomModule module = (CustomModule)var4;
         this.currentContainer = (CustomModuleContainer)this.containers.get(this.getContainerId(module));
         module.getLogger().debug("Construction start");
         module.construction(event);
         module.getLogger().debug("Construction complete");
      }

   }

   public final void onPreInit(@NotNull FMLPreInitializationEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      this.currentStage = ModuleStage.PRE_INIT;
      Iterator var10000 = this.loadedModules.iterator();
      Intrinsics.checkNotNullExpressionValue(var10000, "iterator(...)");
      Iterator var2 = var10000;

      while(var2.hasNext()) {
         Object var6 = var2.next();
         Intrinsics.checkNotNullExpressionValue(var6, "next(...)");
         CustomModule module = (CustomModule)var6;
         this.currentContainer = (CustomModuleContainer)this.containers.get(this.getContainerId(module));
         module.getLogger().debug("Registering packets");
         module.registerPackets();
      }

      var10000 = this.loadedModules.iterator();
      Intrinsics.checkNotNullExpressionValue(var10000, "iterator(...)");
      var2 = var10000;

      while(var2.hasNext()) {
         Object var8 = var2.next();
         Intrinsics.checkNotNullExpressionValue(var8, "next(...)");
         CustomModule module = (CustomModule)var8;
         this.currentContainer = (CustomModuleContainer)this.containers.get(this.getContainerId(module));
         module.getLogger().debug("Pre-Init start");
         module.preInit(event);
         module.getLogger().debug("Pre-Init complete");
      }

   }

   public final void onInit(@NotNull FMLInitializationEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      this.currentStage = ModuleStage.INIT;
      Iterator var10000 = this.loadedModules.iterator();
      Intrinsics.checkNotNullExpressionValue(var10000, "iterator(...)");
      Iterator var2 = var10000;

      while(var2.hasNext()) {
         Object var4 = var2.next();
         Intrinsics.checkNotNullExpressionValue(var4, "next(...)");
         CustomModule module = (CustomModule)var4;
         this.currentContainer = (CustomModuleContainer)this.containers.get(this.getContainerId(module));
         module.getLogger().debug("Init start");
         module.init(event);
         module.getLogger().debug("Init complete");
      }

   }

   public final void onPostInit(@NotNull FMLPostInitializationEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      this.currentStage = ModuleStage.POST_INIT;
      Iterator var10000 = this.loadedModules.iterator();
      Intrinsics.checkNotNullExpressionValue(var10000, "iterator(...)");
      Iterator var2 = var10000;

      while(var2.hasNext()) {
         Object var4 = var2.next();
         Intrinsics.checkNotNullExpressionValue(var4, "next(...)");
         CustomModule module = (CustomModule)var4;
         this.currentContainer = (CustomModuleContainer)this.containers.get(this.getContainerId(module));
         module.getLogger().debug("Post-Init start");
         module.postInit(event);
         module.getLogger().debug("Post-Init complete");
      }

   }

   public final void onLoadComplete(@NotNull FMLLoadCompleteEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      this.currentStage = ModuleStage.LOAD_COMPLETE;
      Iterator var10000 = this.loadedModules.iterator();
      Intrinsics.checkNotNullExpressionValue(var10000, "iterator(...)");
      Iterator var2 = var10000;

      while(var2.hasNext()) {
         Object var4 = var2.next();
         Intrinsics.checkNotNullExpressionValue(var4, "next(...)");
         CustomModule module = (CustomModule)var4;
         this.currentContainer = (CustomModuleContainer)this.containers.get(this.getContainerId(module));
         module.getLogger().debug("Load Complete start");
         module.loadComplete(event);
         module.getLogger().debug("Load Complete complete");
      }

   }

   public final void onServerAboutToStart(@NotNull FMLServerAboutToStartEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      this.currentStage = ModuleStage.SERVER_ABOUT_TO_START;
      Iterator var10000 = this.loadedModules.iterator();
      Intrinsics.checkNotNullExpressionValue(var10000, "iterator(...)");
      Iterator var2 = var10000;

      while(var2.hasNext()) {
         Object var4 = var2.next();
         Intrinsics.checkNotNullExpressionValue(var4, "next(...)");
         CustomModule module = (CustomModule)var4;
         this.currentContainer = (CustomModuleContainer)this.containers.get(this.getContainerId(module));
         module.getLogger().debug("Server About To Start start");
         module.serverAboutToStart(event);
         module.getLogger().debug("Server About To Start complete");
      }

   }

   public final void onServerStarting(@NotNull FMLServerStartingEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      this.currentStage = ModuleStage.SERVER_STARTING;
      Iterator var10000 = this.loadedModules.iterator();
      Intrinsics.checkNotNullExpressionValue(var10000, "iterator(...)");
      Iterator var2 = var10000;

      while(var2.hasNext()) {
         Object var4 = var2.next();
         Intrinsics.checkNotNullExpressionValue(var4, "next(...)");
         CustomModule module = (CustomModule)var4;
         this.currentContainer = (CustomModuleContainer)this.containers.get(this.getContainerId(module));
         module.getLogger().debug("Server Starting start");
         module.serverStarting(event);
         module.getLogger().debug("Server Starting complete");
      }

   }

   public final void onServerStarted(@NotNull FMLServerStartedEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      this.currentStage = ModuleStage.SERVER_STARTED;
      Iterator var10000 = this.loadedModules.iterator();
      Intrinsics.checkNotNullExpressionValue(var10000, "iterator(...)");
      Iterator var2 = var10000;

      while(var2.hasNext()) {
         Object var4 = var2.next();
         Intrinsics.checkNotNullExpressionValue(var4, "next(...)");
         CustomModule module = (CustomModule)var4;
         this.currentContainer = (CustomModuleContainer)this.containers.get(this.getContainerId(module));
         module.getLogger().debug("Server Started start");
         module.serverStarted(event);
         module.getLogger().debug("Server Started complete");
      }

   }

   public final void onServerStopping(@NotNull FMLServerStoppingEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      Iterator var10000 = this.loadedModules.iterator();
      Intrinsics.checkNotNullExpressionValue(var10000, "iterator(...)");
      Iterator var2 = var10000;

      while(var2.hasNext()) {
         Object var4 = var2.next();
         Intrinsics.checkNotNullExpressionValue(var4, "next(...)");
         CustomModule module = (CustomModule)var4;
         this.currentContainer = (CustomModuleContainer)this.containers.get(this.getContainerId(module));
         module.serverStopping(event);
      }

   }

   public final void onServerStopped(@NotNull FMLServerStoppedEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      Iterator var10000 = this.loadedModules.iterator();
      Intrinsics.checkNotNullExpressionValue(var10000, "iterator(...)");
      Iterator var2 = var10000;

      while(var2.hasNext()) {
         Object var4 = var2.next();
         Intrinsics.checkNotNullExpressionValue(var4, "next(...)");
         CustomModule module = (CustomModule)var4;
         this.currentContainer = (CustomModuleContainer)this.containers.get(this.getContainerId(module));
         module.serverStopped(event);
      }

   }

   public final void processIMC(@NotNull ImmutableList<FMLInterModComms.IMCMessage> messages) {
      Intrinsics.checkNotNullParameter(messages, "messages");
      UnmodifiableIterator var10000 = messages.iterator();
      Intrinsics.checkNotNullExpressionValue(var10000, "iterator(...)");
      UnmodifiableIterator var2 = var10000;

      while(var2.hasNext()) {
         FMLInterModComms.IMCMessage message = (FMLInterModComms.IMCMessage)var2.next();
         Iterator var6 = this.loadedModules.iterator();
         Intrinsics.checkNotNullExpressionValue(var6, "iterator(...)");
         Iterator var4 = var6;

         while(var4.hasNext()) {
            Object var7 = var4.next();
            Intrinsics.checkNotNullExpressionValue(var7, "next(...)");
            CustomModule module = (CustomModule)var7;
            Intrinsics.checkNotNull(message);
            if (module.processIMC(message)) {
               break;
            }
         }
      }

   }

   private final String getComment(CustomModule module) {
      Module annotation = (Module)module.getClass().getAnnotation(Module.class);
      Set dependencies = module.getDependencyUids();
      String[] modDependencies = annotation.modDependencies();
      int var5 = annotation.descriptions().length();
      StringBuilder var6 = new StringBuilder(var5);
      int var8 = 0;
      var6.append(annotation.descriptions());
      if (!((Collection)dependencies).isEmpty()) {
         var6.append("\nModule Dependencies: [ " + CollectionsKt.joinToString$default((Iterable)dependencies, (CharSequence)", ", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null) + " ] ");
      }

      if (modDependencies.length != 0) {
         var6.append("\nMod Dependencies: [ " + ArraysKt.joinToString$default(modDependencies, (CharSequence)", ", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null) + " ]");
      }

      return var6.toString();
   }

   private final Configuration getConfiguration() {
      if (this.config == null) {
         Configuration var10001 = new Configuration;
         File var10003 = new File;
         File var10005 = configFolder;
         if (var10005 == null) {
            String var1 = "Required value was null.";
            throw new IllegalStateException(var1.toString());
         }

         var10003.<init>(var10005, "modules.cfg");
         var10001.<init>(var10003);
         this.config = var10001;
      }

      Configuration var10000 = this.config;
      Intrinsics.checkNotNull(var10000);
      return var10000;
   }

   private final void discoverContainers(ASMDataTable dataTable) {
      for(ASMDataTable.ASMData data : dataTable.getAll(ModuleContainer.class.getCanonicalName())) {
         try {
            Class clazz = Class.forName(data.getClassName());
            if (CustomModuleContainer.class.isAssignableFrom(clazz)) {
               Object var10001 = clazz.getDeclaredConstructor().newInstance();
               Intrinsics.checkNotNull(var10001, "null cannot be cast to non-null type gregtechlite.gtlitecore.api.module.CustomModuleContainer");
               this.registerContainer((CustomModuleContainer)var10001);
            } else {
               this.logger.error("Module Container class '{}' is not an instanceof correspondenced interface", clazz.getName());
            }
         } catch (ReflectiveOperationException e) {
            this.logger.error("Could not initialize Module Container '{}'", data.getClassName(), e);
         }
      }

   }

   private final String getContainerId(CustomModule module) {
      return ((Module)module.getClass().getAnnotation(Module.class)).containerId();
   }

   private final void configureModules(Map<String, ? extends List<? extends CustomModule>> modules) {
      Locale locale = Locale.getDefault();
      Locale.setDefault(Locale.ENGLISH);
      ObjectSet toLoad = MoreCollectionsKt.openLinkedSetOf();
      ReferenceSet modulesToLoad = MoreCollectionsKt.openRefLinkedSetOf();
      Configuration config = this.getConfiguration();
      config.load();
      config.addCustomCategoryComment("modules", "Module configuration file. Can individually enable/disable modules from the mod and its addons");
      ObjectIterator var10000 = ((ReferenceCollection)this.containers.values()).iterator();
      Intrinsics.checkNotNullExpressionValue(var10000, "iterator(...)");
      ObjectIterator var6 = var10000;

      while(var6.hasNext()) {
         CustomModuleContainer container = (CustomModuleContainer)var6.next();
         List var23 = (List)modules.get(container.getId());
         if (var23 != null) {
            List containerModules = var23;
            CustomModule var24 = this.getCoreModule(containerModules);
            if (var24 == null) {
               throw new IllegalStateException("Could not find Core Module for Module Container " + container.getId());
            }

            CustomModule coreModule = var24;

            for(CustomModule module : CollectionsKt.plus((Collection)CollectionsKt.listOf(coreModule), (Iterable)CollectionsKt.minus((Iterable)containerModules, coreModule))) {
               if (!this.isModuleEnabled(module)) {
                  this.logger.debug("Module disabled: {}", module);
               } else {
                  Module annotation = (Module)module.getClass().getAnnotation(Module.class);
                  toLoad.add(new ResourceLocation(container.getId(), annotation.moduleId()));
                  modulesToLoad.add(module);
               }
            }
         }
      }

      do {
         removed = CollectionsKt.removeAll((Iterable)modulesToLoad, ModuleManagerImpl::configureModules$lambda$3);
      } while(removed);

      while(true) {
         Iterable $this$firstOrNull$iv = (Iterable)modulesToLoad;
         int $i$f$firstOrNull = 0;
         Iterator var19 = $this$firstOrNull$iv.iterator();

         while(true) {
            if (!var19.hasNext()) {
               var25 = null;
               break;
            }

            Object element$iv = var19.next();
            CustomModule it = (CustomModule)element$iv;
            int var22 = 0;
            if (this.sortedModules.keySet().containsAll((Collection)it.getDependencyUids())) {
               var25 = element$iv;
               break;
            }
         }

         CustomModule var26 = (CustomModule)var25;
         if (var26 == null) {
            this.loadedModules.addAll(this.sortedModules.values());
            if (config.hasChanged()) {
               config.save();
            }

            Locale.setDefault(locale);
            return;
         }

         CustomModule module = var26;
         Module annotation = (Module)module.getClass().getAnnotation(Module.class);
         ((Map)this.sortedModules).put(new ResourceLocation(annotation.containerId(), annotation.moduleId()), module);
         modulesToLoad.remove(module);
      }
   }

   private final CustomModule getCoreModule(List<? extends CustomModule> modules) {
      for(CustomModule module : modules) {
         Module annotation = (Module)module.getClass().getAnnotation(Module.class);
         if (annotation.isCore()) {
            return module;
         }
      }

      return null;
   }

   private final List<CustomModule> getInstances(ASMDataTable dataTable) {
      Set dataSet = dataTable.getAll(Module.class.getCanonicalName());
      ArrayList instances = new ArrayList();

      for(ASMDataTable.ASMData data : dataSet) {
         Object var10000 = data.getAnnotationInfo().get("moduleId");
         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type kotlin.String");
         String moduleId = (String)var10000;
         Object var8 = data.getAnnotationInfo().get("modDependencies");
         List modDependencies = var8 instanceof List ? (List)var8 : null;
         if (modDependencies != null) {
            Iterable $this$all$iv = (Iterable)modDependencies;
            int $i$f$all = 0;
            boolean var19;
            if ($this$all$iv instanceof Collection && ((Collection)$this$all$iv).isEmpty()) {
               var19 = true;
            } else {
               Iterator var10 = $this$all$iv.iterator();

               while(true) {
                  if (!var10.hasNext()) {
                     var19 = true;
                     break;
                  }

                  Object element$iv = var10.next();
                  String it = (String)element$iv;
                  int var13 = 0;
                  if (!Loader.isModLoaded(it)) {
                     var19 = false;
                     break;
                  }
               }
            }

            if (!var19) {
               this.logger.info("Module '{}' is missing at least one of mod dependencies: '{}', skipping loading...", moduleId, modDependencies);
               continue;
            }
         }

         try {
            Class clazz = Class.forName(data.getClassName());
            if (CustomModule.class.isAssignableFrom(clazz)) {
               Object var10001 = clazz.getDeclaredConstructor().newInstance();
               Intrinsics.checkNotNull(var10001, "null cannot be cast to non-null type gregtechlite.gtlitecore.api.module.CustomModule");
               Boolean var20 = instances.add((CustomModule)var10001);
            } else {
               this.logger.error("Module of class '{}' with id '{}' is not an instanceof Custom Module", clazz.getName(), moduleId);
               Unit var21 = Unit.INSTANCE;
            }
         } catch (ReflectiveOperationException e) {
            this.logger.error("Could not initialize Module '{}'", moduleId, e);
            Unit var17 = Unit.INSTANCE;
         }
      }

      Iterable var22 = (Iterable)instances;
      Comparator var15 = new ModuleManagerImpl$getInstances$$inlined$compareBy$1();
      return CollectionsKt.sortedWith(var22, new ModuleManagerImpl$getInstances$$inlined$thenBy$1(var15));
   }

   private final Map<String, List<CustomModule>> getModules(ASMDataTable dataTable) {
      List instances = this.getInstances(dataTable);
      Object2ReferenceMap modules = MoreCollectionsKt.openRefLinkedMapOf();

      for(CustomModule module : instances) {
         Module annotation = (Module)module.getClass().getAnnotation(Module.class);
         Map $this$getOrPut$iv = (Map)modules;
         Object key$iv = annotation.containerId();
         int $i$f$getOrPut = 0;
         Object value$iv = $this$getOrPut$iv.get(key$iv);
         Object var10000;
         if (value$iv == null) {
            int var11 = 0;
            Object answer$iv = (List)(new ArrayList());
            $this$getOrPut$iv.put(key$iv, answer$iv);
            var10000 = answer$iv;
         } else {
            var10000 = value$iv;
         }

         ((List)var10000).add(module);
      }

      return (Map)modules;
   }

   public boolean isModuleEnabled(@NotNull String moduleId) {
      return DefaultImpls.isModuleEnabled(this, moduleId);
   }

   public boolean isModuleEnabled(@NotNull String containerId, @NotNull String moduleId) {
      return DefaultImpls.isModuleEnabled(this, containerId, moduleId);
   }

   private static final boolean configureModules$lambda$3(ObjectSet $toLoad, ModuleManagerImpl this$0, CustomModule it) {
      Set dependencies = it.getDependencyUids();
      boolean missing = !$toLoad.containsAll((Collection)dependencies);
      if (missing) {
         String moduleId = ((Module)it.getClass().getAnnotation(Module.class)).moduleId();
         $toLoad.remove(new ResourceLocation(moduleId));
         this$0.logger.info("Module '{}' is missing at least one of Module dependencies: '{}', skipping loading...", moduleId, dependencies);
      }

      return missing;
   }

   @Metadata(
      mv = {2, 1, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00058\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"},
      d2 = {"Lgregtechlite/gtlitecore/core/module/ModuleManagerImpl$Companion;", "", "<init>", "()V", "instance", "Lgregtechlite/gtlitecore/core/module/ModuleManagerImpl;", "MODULE_CFG_FILE_NAME", "", "MODULE_CFG_CATEGORY_NAME", "configFolder", "Ljava/io/File;", "gtlitecore"}
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
