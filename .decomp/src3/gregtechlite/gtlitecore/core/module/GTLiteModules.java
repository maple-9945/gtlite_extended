package gregtechlite.gtlitecore.core.module;

import gregtechlite.gtlitecore.api.module.CustomModuleContainer;
import gregtechlite.gtlitecore.api.module.ModuleContainer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@ModuleContainer
@Metadata(
   mv = {2, 1, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0001\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"},
   d2 = {"Lgregtechlite/gtlitecore/core/module/GTLiteModules;", "Lgregtechlite/gtlitecore/api/module/CustomModuleContainer;", "<init>", "()V", "id", "", "getId", "()Ljava/lang/String;", "Companion", "gtlitecore"}
)
public final class GTLiteModules implements CustomModuleContainer {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final String id = "gtlitecore";
   @NotNull
   public static final String MODULE_CORE = "core";
   @NotNull
   public static final String MODULE_INTEGRATION = "integration";
   @NotNull
   public static final String MODULE_WORLDGEN = "worldgen";
   @NotNull
   public static final String MODULE_JEI = "jei";
   @NotNull
   public static final String MODULE_TOP = "top";
   @NotNull
   public static final String MODULE_AE2 = "appeng";

   @NotNull
   public String getId() {
      return this.id;
   }

   @Metadata(
      mv = {2, 1, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"},
      d2 = {"Lgregtechlite/gtlitecore/core/module/GTLiteModules$Companion;", "", "<init>", "()V", "MODULE_CORE", "", "MODULE_INTEGRATION", "MODULE_WORLDGEN", "MODULE_JEI", "MODULE_TOP", "MODULE_AE2", "gtlitecore"}
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
