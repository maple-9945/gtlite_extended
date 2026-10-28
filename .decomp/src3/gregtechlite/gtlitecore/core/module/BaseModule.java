package gregtechlite.gtlitecore.core.module;

import gregtechlite.gtlitecore.GTLiteMod;
import gregtechlite.gtlitecore.api.module.CustomModule;
import gregtechlite.gtlitecore.api.module.CustomModule.DefaultImpls;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import net.minecraft.util.ResourceLocation;
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

@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"},
   d2 = {"Lgregtechlite/gtlitecore/core/module/BaseModule;", "Lgregtechlite/gtlitecore/api/module/CustomModule;", "<init>", "()V", "dependencyUids", "", "Lnet/minecraft/util/ResourceLocation;", "getDependencyUids", "()Ljava/util/Set;", "gtlitecore"}
)
public abstract class BaseModule implements CustomModule {
   @NotNull
   private final Set<ResourceLocation> dependencyUids;

   public BaseModule() {
      ResourceLocation[] var1 = new ResourceLocation[]{GTLiteMod.Companion.id("core")};
      this.dependencyUids = SetsKt.mutableSetOf(var1);
   }

   @NotNull
   public Set<ResourceLocation> getDependencyUids() {
      return this.dependencyUids;
   }

   public void construction(@NotNull FMLConstructionEvent event) {
      DefaultImpls.construction(this, event);
   }

   public void preInit(@NotNull FMLPreInitializationEvent event) {
      DefaultImpls.preInit(this, event);
   }

   public void init(@NotNull FMLInitializationEvent event) {
      DefaultImpls.init(this, event);
   }

   public void postInit(@NotNull FMLPostInitializationEvent event) {
      DefaultImpls.postInit(this, event);
   }

   public void loadComplete(@NotNull FMLLoadCompleteEvent event) {
      DefaultImpls.loadComplete(this, event);
   }

   public void serverAboutToStart(@NotNull FMLServerAboutToStartEvent event) {
      DefaultImpls.serverAboutToStart(this, event);
   }

   public void serverStarting(@NotNull FMLServerStartingEvent event) {
      DefaultImpls.serverStarting(this, event);
   }

   public void serverStarted(@NotNull FMLServerStartedEvent event) {
      DefaultImpls.serverStarted(this, event);
   }

   public void serverStopping(@NotNull FMLServerStoppingEvent event) {
      DefaultImpls.serverStopping(this, event);
   }

   public void serverStopped(@NotNull FMLServerStoppedEvent event) {
      DefaultImpls.serverStopped(this, event);
   }

   public void registerPackets() {
      DefaultImpls.registerPackets(this);
   }

   @NotNull
   public List<Class<?>> getEventBusSubscribers() {
      return DefaultImpls.getEventBusSubscribers(this);
   }

   @NotNull
   public List<Class<?>> getTerrainGenBusSubscribers() {
      return DefaultImpls.getTerrainGenBusSubscribers(this);
   }

   @NotNull
   public List<Class<?>> getOreGenBusSubscribers() {
      return DefaultImpls.getOreGenBusSubscribers(this);
   }

   public boolean processIMC(@NotNull FMLInterModComms.IMCMessage message) {
      return DefaultImpls.processIMC(this, message);
   }
}
