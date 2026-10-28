package gregtechlite.gtlitecore.api;

import com.morphismmc.morphismlib.util.SidedLogger;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 1, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\"\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"\u0015\u0010\n\u001a\u00020\u0007*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t\"\u0015\u0010\f\u001a\u00020\u0007*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\r\u0010\t\"\u0015\u0010\u000e\u001a\u00020\u0007*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\t¨\u0006\u0010"},
   d2 = {"MOD_ID", "", "MOD_NAME", "MOD_VERSION", "LOGGER", "Lcom/morphismmc/morphismlib/util/SidedLogger;", "t", "", "getT", "(I)I", "s", "getS", "min", "getMin", "hr", "getHr", "gtlitecore"}
)
@JvmName(
   name = "GTLiteValues"
)
public final class GTLiteValues {
   @NotNull
   public static final String MOD_ID = "gtlitecore";
   @NotNull
   public static final String MOD_NAME = "GregTech Lite Core";
   @NotNull
   public static final String MOD_VERSION = "1.0.0";
   @JvmField
   @NotNull
   public static final SidedLogger LOGGER = new SidedLogger("gtlitecore");

   public static final int getT(int $this$t) {
      return $this$t * 1;
   }

   public static final int getS(int $this$s) {
      return $this$s * getT(20);
   }

   public static final int getMin(int $this$min) {
      return $this$min * getS(60);
   }

   public static final int getHr(int $this$hr) {
      return $this$hr * getMin(60);
   }
}
