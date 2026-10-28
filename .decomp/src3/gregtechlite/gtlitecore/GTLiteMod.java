package gregtechlite.gtlitecore;

import com.google.common.collect.ImmutableList;
import gregtechlite.gtlitecore.api.GTLiteAPI;
import gregtechlite.gtlitecore.core.module.GTLiteModules;
import gregtechlite.gtlitecore.core.module.ModuleContainerRegistryEvent;
import gregtechlite.gtlitecore.core.module.ModuleManagerImpl;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.CustomProperty;
import net.minecraftforge.fml.common.Mod.EventHandler;
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
import org.jetbrains.annotations.NotNull;

@Mod(
   modid = "gtlitecore",
   name = "GregTech Lite Core",
   version = "1.0.0",
   dependencies = "required:forge@[14.23.5.2847,);required:forgelin_continuous@[2.1.0.0,);required-after:morphismlib@[1.0.0,);required-after:modularui@[2.5.0-rc4,);required-after:gregtech@[2.8.7-beta,);after:jei@[4.30.0,);",
   acceptedMinecraftVersions = "[1.12.2]",
   modLanguageAdapter = "io.github.chaosunity.forgelin.KotlinAdapter",
   customProperties = {@CustomProperty(
   k = "license",
   v = "Apache 2.0"
), @CustomProperty(
   k = "issueTrackerUrl",
   v = "https://github.com/GregTechLite/GregTech-Lite-Modpack/issues"
), @CustomProperty(
   k = "iconItem",
   v = "gtlitecore:gtlite_meta_item_1:1"
)}
)
@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0011H\u0007J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0013H\u0007J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0015H\u0007J\u0010\u0010\u0016\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0017H\u0007J\u0010\u0010\u0018\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0019H\u0007J\u0010\u0010\u001a\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u001bH\u0007J\u0010\u0010\u001c\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u001dH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"},
   d2 = {"Lgregtechlite/gtlitecore/GTLiteMod;", "", "<init>", "()V", "moduleManager", "Lgregtechlite/gtlitecore/core/module/ModuleManagerImpl;", "onConstruction", "", "event", "Lnet/minecraftforge/fml/common/event/FMLConstructionEvent;", "onPreInit", "Lnet/minecraftforge/fml/common/event/FMLPreInitializationEvent;", "onInit", "Lnet/minecraftforge/fml/common/event/FMLInitializationEvent;", "onPostInit", "Lnet/minecraftforge/fml/common/event/FMLPostInitializationEvent;", "loadComplete", "Lnet/minecraftforge/fml/common/event/FMLLoadCompleteEvent;", "serverAboutToStart", "Lnet/minecraftforge/fml/common/event/FMLServerAboutToStartEvent;", "serverStarting", "Lnet/minecraftforge/fml/common/event/FMLServerStartingEvent;", "serverStarted", "Lnet/minecraftforge/fml/common/event/FMLServerStartedEvent;", "serverStopping", "Lnet/minecraftforge/fml/common/event/FMLServerStoppingEvent;", "serverStopped", "Lnet/minecraftforge/fml/common/event/FMLServerStoppedEvent;", "respondIMC", "Lnet/minecraftforge/fml/common/event/FMLInterModComms$IMCEvent;", "Companion", "gtlitecore"}
)
public final class GTLiteMod {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private ModuleManagerImpl moduleManager;

   public GTLiteMod() {
      GTLiteAPI.INSTANCE.setInstance(this);
   }

   @EventHandler
   public final void onConstruction(@NotNull FMLConstructionEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      this.moduleManager = ModuleManagerImpl.instance;
      GTLiteAPI var10000 = GTLiteAPI.INSTANCE;
      ModuleManagerImpl var10001 = this.moduleManager;
      if (var10001 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var10001 = null;
      }

      var10000.setModuleManager(var10001);
      ModuleManagerImpl var2 = this.moduleManager;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var2 = null;
      }

      var2.registerContainer(new GTLiteModules());
      MinecraftForge.EVENT_BUS.post(new ModuleContainerRegistryEvent());
      var2 = this.moduleManager;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var2 = null;
      }

      ASMDataTable var5 = event.getASMHarvestedData();
      Intrinsics.checkNotNullExpressionValue(var5, "getASMHarvestedData(...)");
      File var10002 = Loader.instance().getConfigDir();
      Intrinsics.checkNotNullExpressionValue(var10002, "getConfigDir(...)");
      var2.setup(var5, var10002);
      var2 = this.moduleManager;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var2 = null;
      }

      var2.onConstruction(event);
   }

   @EventHandler
   public final void onPreInit(@NotNull FMLPreInitializationEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      ModuleManagerImpl var10000 = this.moduleManager;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var10000 = null;
      }

      var10000.onPreInit(event);
   }

   @EventHandler
   public final void onInit(@NotNull FMLInitializationEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      ModuleManagerImpl var10000 = this.moduleManager;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var10000 = null;
      }

      var10000.onInit(event);
   }

   @EventHandler
   public final void onPostInit(@NotNull FMLPostInitializationEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      ModuleManagerImpl var10000 = this.moduleManager;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var10000 = null;
      }

      var10000.onPostInit(event);
      var10000 = this.moduleManager;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var10000 = null;
      }

      ImmutableList var10001 = FMLInterModComms.fetchRuntimeMessages(GTLiteAPI.INSTANCE.getInstance());
      Intrinsics.checkNotNullExpressionValue(var10001, "fetchRuntimeMessages(...)");
      var10000.processIMC(var10001);
   }

   @EventHandler
   public final void loadComplete(@NotNull FMLLoadCompleteEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      ModuleManagerImpl var10000 = this.moduleManager;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var10000 = null;
      }

      var10000.onLoadComplete(event);
   }

   @EventHandler
   public final void serverAboutToStart(@NotNull FMLServerAboutToStartEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      ModuleManagerImpl var10000 = this.moduleManager;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var10000 = null;
      }

      var10000.onServerAboutToStart(event);
   }

   @EventHandler
   public final void serverStarting(@NotNull FMLServerStartingEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      ModuleManagerImpl var10000 = this.moduleManager;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var10000 = null;
      }

      var10000.onServerStarting(event);
   }

   @EventHandler
   public final void serverStarted(@NotNull FMLServerStartedEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      ModuleManagerImpl var10000 = this.moduleManager;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var10000 = null;
      }

      var10000.onServerStarted(event);
   }

   @EventHandler
   public final void serverStopping(@NotNull FMLServerStoppingEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      ModuleManagerImpl var10000 = this.moduleManager;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var10000 = null;
      }

      var10000.onServerStopping(event);
   }

   @EventHandler
   public final void serverStopped(@NotNull FMLServerStoppedEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      ModuleManagerImpl var10000 = this.moduleManager;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var10000 = null;
      }

      var10000.onServerStopped(event);
   }

   @EventHandler
   public final void respondIMC(@NotNull FMLInterModComms.IMCEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      ModuleManagerImpl var10000 = this.moduleManager;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
         var10000 = null;
      }

      ImmutableList var10001 = event.getMessages();
      Intrinsics.checkNotNullExpressionValue(var10001, "getMessages(...)");
      var10000.processIMC(var10001);
   }

   @JvmStatic
   @NotNull
   public static final ResourceLocation id(@NotNull String path) {
      return Companion.id(path);
   }
}
