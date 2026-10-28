package gregtech.api.recipes.chance.output;

import gregtech.api.GTValues;
import gregtech.api.recipes.chance.boost.BoostableChanceEntry;
import gregtech.api.recipes.chance.boost.ChanceBoostFunction;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

public interface ChancedOutputLogic {
   ChancedOutputLogic OR = new 1();
   ChancedOutputLogic AND = new 2();
   ChancedOutputLogic XOR = new 3();
   ChancedOutputLogic NONE = new 4();

   static int getChance(@NotNull ChancedOutput<?> entry, @NotNull ChanceBoostFunction boostFunction, int baseTier, int machineTier) {
      if (entry instanceof BoostableChanceEntry) {
         BoostableChanceEntry<?> boostableChanceEntry = (BoostableChanceEntry)entry;
         return boostFunction.getBoostedChance(boostableChanceEntry, baseTier, machineTier);
      } else {
         return entry.getChance();
      }
   }

   static boolean passesChance(int chance) {
      return chance > 0 && GTValues.RNG.nextInt(getMaxChancedValue()) <= chance;
   }

   static int getMaxChancedValue() {
      return 10000;
   }

   <I, T extends ChancedOutput<I>> @Nullable @Unmodifiable List<@NotNull T> roll(@NotNull @Unmodifiable List<@NotNull T> var1, @NotNull ChanceBoostFunction var2, int var3, int var4);

   @NotNull String getTranslationKey();
}
