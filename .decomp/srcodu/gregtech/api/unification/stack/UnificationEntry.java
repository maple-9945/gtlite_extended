package gregtech.api.unification.stack;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import java.util.Objects;
import org.jetbrains.annotations.Nullable;

public class UnificationEntry {
   public final OrePrefix orePrefix;
   public final @Nullable Material material;

   public UnificationEntry(OrePrefix orePrefix, @Nullable Material material) {
      this.orePrefix = orePrefix;
      this.material = material;
   }

   public UnificationEntry(OrePrefix orePrefix) {
      this.orePrefix = orePrefix;
      this.material = null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         UnificationEntry that = (UnificationEntry)o;
         return this.orePrefix != that.orePrefix ? false : Objects.equals(this.material, that.material);
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.orePrefix.hashCode();
      result = 31 * result + (this.material != null ? this.material.hashCode() : 0);
      return result;
   }

   public String toString() {
      return this.orePrefix.name() + (this.material != null ? this.material.toCamelCaseString() : "");
   }
}
