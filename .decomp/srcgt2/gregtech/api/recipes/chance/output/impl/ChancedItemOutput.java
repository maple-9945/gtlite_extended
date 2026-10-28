package gregtech.api.recipes.chance.output.impl;

import com.cleanroommc.modularui.network.NetworkUtils;
import gregtech.api.recipes.chance.output.BoostableChanceOutput;
import gregtech.api.util.GTStringUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import org.jetbrains.annotations.NotNull;

public class ChancedItemOutput extends BoostableChanceOutput<ItemStack> {
   public ChancedItemOutput(@NotNull ItemStack ingredient, int chance, int chanceBoost) {
      super(ingredient, chance, chanceBoost);
   }

   public @NotNull ChancedItemOutput copy() {
      return new ChancedItemOutput(((ItemStack)this.getIngredient()).func_77946_l(), this.getChance(), this.getChanceBoost());
   }

   public String toString() {
      return "ChancedItemOutput{ingredient=" + GTStringUtils.prettyPrintItemStack((ItemStack)this.getIngredient()) + ", chance=" + this.getChance() + ", chanceBoost=" + this.getChanceBoost() + '}';
   }

   public static ChancedItemOutput fromBuffer(PacketBuffer buffer) {
      return new ChancedItemOutput(NetworkUtils.readItemStack(buffer), buffer.func_150792_a(), buffer.func_150792_a());
   }

   public static void toBuffer(PacketBuffer buffer, ChancedItemOutput value) {
      NetworkUtils.writeItemStack(buffer, (ItemStack)value.getIngredient());
      buffer.func_150787_b(value.getChance());
      buffer.func_150787_b(value.getChanceBoost());
   }
}
