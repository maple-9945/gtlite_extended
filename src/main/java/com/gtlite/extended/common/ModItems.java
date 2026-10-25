package com.gtlite.extended.common;

import com.gtlite.extended.Tags;
import com.gtlite.extended.common.item.ItemUpgradeChip;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public final class ModItems {

    public static final ItemUpgradeChip CHIP_PERFECT_OC =
            new ItemUpgradeChip(ItemUpgradeChip.TYPE_PERFECT_OC, "chip_perfect_oc");
    public static final ItemUpgradeChip CHIP_ADVANCED =
            new ItemUpgradeChip(ItemUpgradeChip.TYPE_ADVANCED, "chip_advanced");

    private ModItems() {
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(CHIP_PERFECT_OC);
        event.getRegistry().register(CHIP_ADVANCED);
    }
}
