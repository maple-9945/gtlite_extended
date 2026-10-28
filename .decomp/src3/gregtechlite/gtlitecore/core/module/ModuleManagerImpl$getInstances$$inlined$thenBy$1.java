package gregtechlite.gtlitecore.core.module;

import gregtechlite.gtlitecore.api.module.CustomModule;
import gregtechlite.gtlitecore.api.module.Module;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(
   mv = {2, 1, 0},
   k = 3,
   xi = 48
)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$thenBy$1\n+ 2 ModuleManagerImpl.kt\ngregtechlite/gtlitecore/core/module/ModuleManagerImpl\n*L\n1#1,145:1\n458#2:146\n*E\n"})
public final class ModuleManagerImpl$getInstances$$inlined$thenBy$1<T> implements Comparator {
   // $FF: synthetic field
   final Comparator $this_thenBy;

   public ModuleManagerImpl$getInstances$$inlined$thenBy$1(Comparator $receiver) {
      this.$this_thenBy = $receiver;
   }

   public final int compare(T a, T b) {
      int previousCompare = this.$this_thenBy.compare(a, b);
      int var10000;
      if (previousCompare != 0) {
         var10000 = previousCompare;
      } else {
         CustomModule it = (CustomModule)a;
         int var5 = 0;
         Comparable var9 = (Comparable)((Module)it.getClass().getAnnotation(Module.class)).moduleId();
         it = (CustomModule)b;
         Comparable var6 = var9;
         var5 = 0;
         var10000 = ComparisonsKt.compareValues(var6, (Comparable)((Module)it.getClass().getAnnotation(Module.class)).moduleId());
      }

      return var10000;
   }
}
