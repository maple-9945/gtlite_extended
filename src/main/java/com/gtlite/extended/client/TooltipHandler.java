package com.gtlite.extended.client;

import com.gtlite.extended.Tags;
import com.gtlite.extended.common.item.ItemUpgradeChip;
import gregtech.api.metatileentity.registry.MTEManager;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.List;

/** 机器 / 芯片物品提示。 */
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Tags.MOD_ID)
public final class TooltipHandler {

    private TooltipHandler() {
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        List<String> tips = event.getToolTip();

        if (stack.getItem() instanceof ItemUpgradeChip) {
            ItemUpgradeChip chip = (ItemUpgradeChip) stack.getItem();
            if (chip.isPerfectOc()) {
                tips.add("§b无损超频芯片");
                tips.add("§7右键矿石处理机：有损 → 无损超频");
            } else if (chip.isAdvanced()) {
                tips.add("§d高级模式芯片");
                tips.add("§7需先解锁无损超频，右键后：");
                tips.add("§7零耗电 + 无限并行 + 瞬间完成");
            }
            return;
        }

        if (stack.getItem() instanceof ItemBlock && MTEManager.getInstance() != null) {
            Block ourBlock = MTEManager.getInstance().getRegistry(Tags.MOD_ID) == null
                    ? null : MTEManager.getInstance().getRegistry(Tags.MOD_ID).getBlock();
            if (ourBlock != null && ((ItemBlock) stack.getItem()).getBlock() == ourBlock) {
                tips.add("§6矿石处理机（3×3×3）");
                tips.add("§7矿石 + 1000mB 水 → 全部产物（100%）");
                tips.add("§7默认有损超频；芯片 A 解锁无损；芯片 B 解锁高级模式");
            }
        }
    }
}
