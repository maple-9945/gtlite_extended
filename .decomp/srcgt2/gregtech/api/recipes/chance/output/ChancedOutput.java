package gregtech.api.recipes.chance.output;

import gregtech.api.recipes.chance.BaseChanceEntry;
import org.jetbrains.annotations.NotNull;

public abstract class ChancedOutput<T> extends BaseChanceEntry<T> {
   public ChancedOutput(@NotNull T ingredient, int chance) {
      super(ingredient, chance);
   }
}
