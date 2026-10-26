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
        // 注意：1.12.2 这套映射的 Item#setTranslationKey 会自动补 "item." 前缀，
        // 所以这里只传 "gtlite_extended.xxx"，最终 unlocalized name = item.gtlite_extended.xxx，
        // 显示名键 = item.gtlite_extended.xxx.name（与 lang 文件一致）。
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
