package gregtech.api.recipes.chance.output;

import gregtech.api.recipes.chance.boost.ChanceBoostFunction;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

public class ChancedOutputList<I, T extends ChancedOutput<I>> {
   private final ChancedOutputLogic chancedOutputLogic;
   private final List<T> chancedEntries;

   public static <I, T extends ChancedOutput<I>> @NotNull ChancedOutputList<I, T> empty() {
      return new ChancedOutputList<I, T>(ChancedOutputLogic.NONE, Collections.emptyList());
   }

   public ChancedOutputList(@NotNull ChancedOutputLogic chancedOutputLogic, @NotNull List<@NotNull T> chancedEntries) {
      this.chancedOutputLogic = chancedOutputLogic;
      this.chancedEntries = chancedEntries;
   }

   public @NotNull @Unmodifiable List<@NotNull T> getChancedEntries() {
      return this.chancedEntries;
   }

   public @Nullable @Unmodifiable List<T> roll(@NotNull ChanceBoostFunction boostFunction, int baseTier, int machineTier) {
      return this.chancedOutputLogic.roll(this.getChancedEntries(), boostFunction, baseTier, machineTier);
   }

   public @NotNull ChancedOutputLogic getChancedOutputLogic() {
      return this.chancedOutputLogic;
   }

   public String toString() {
      return this.chancedEntries.toString();
   }
}
