package gregtechlite.gtlitecore.core.module;

import java.util.Comparator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(
   mv = {2, 1, 0},
   k = 3,
   xi = 48
)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 ModuleManagerImpl.kt\ngregtechlite/gtlitecore/core/module/ModuleManagerImpl\n*L\n1#1,102:1\n97#2:103\n*E\n"})
public final class ModuleManagerImpl$setup$lambda$1$$inlined$sortedBy$1<T> implements Comparator {
   public final int compare(T a, T b) {
      Map.Entry it = (Map.Entry)a;
      int var4 = 0;
      Comparable var10000 = (Comparable)((String)it.getKey());
      it = (Map.Entry)b;
      Comparable var5 = var10000;
      var4 = 0;
      return ComparisonsKt.compareValues(var5, (Comparable)((String)it.getKey()));
   }
}
