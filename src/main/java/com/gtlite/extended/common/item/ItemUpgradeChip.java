package com.gtlite.extended.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/** 升级芯片：A（无损超频）/ B（高级模式，需 A 解锁）。 */
public class ItemUpgradeChip extends Item {

    public static final String TYPE_PERFECT_OC = "perfect_oc";
    public static final String TYPE_ADVANCED = "advanced";

    private final String chipType;

    public ItemUpgradeChip(String chipType, String registryName) {
        this.chipType = chipType;
        setRegistryName("gtlite_extended", registryName);
        setTranslationKey("gtlite_extended." + registryName);
        setCreativeTab(CreativeTabs.MISC);
    }

    public String getChipType() {
        return chipType;
    }

    public boolean isPerfectOc() {
        return TYPE_PERFECT_OC.equals(chipType);
    }

    public boolean isAdvanced() {
        return TYPE_ADVANCED.equals(chipType);
    }

    public ItemStack asStack() {
        return new ItemStack(this);
    }
}
