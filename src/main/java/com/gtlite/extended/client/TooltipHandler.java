package com.gtlite.extended.client;

import com.gtlite.extended.Tags;
import com.gtlite.extended.common.item.ItemUpgradeChip;
import com.gtlite.extended.common.machine.MetaTileEntityOreProcessor;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.registry.MTEManager;
import gregtech.api.util.GTUtility;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.List;

/**
 * 芯片 / 矿石处理机的额外物品提示。
 *
 * <p>注意：这里的 {@link ItemTooltipEvent} 同样会被 HEI/JEI 的物品列表触发，
 * 所以判断条件必须<b>精确到具体 meta</b>，不能只判断“是我们 mod 的 MTE 方块”，
 * 否则矿石处理机的说明会串到石化工厂、ME 输出/输入总成上。</p>
 */
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

        // 只给“矿石处理机控制器”加说明；其他 MTE（石化工厂 / ME 输出总成 / ME 库存输入总成）不再添加任何文字
        if (stack.getItem() instanceof ItemBlock && MTEManager.getInstance() != null) {
            Block ourBlock = MTEManager.getInstance().getRegistry(Tags.MOD_ID) == null
                    ? null : MTEManager.getInstance().getRegistry(Tags.MOD_ID).getBlock();
            if (ourBlock != null && ((ItemBlock) stack.getItem()).getBlock() == ourBlock) {
                MetaTileEntity metaTileEntity = GTUtility.getMetaTileEntity(stack);
                if (metaTileEntity instanceof MetaTileEntityOreProcessor) {
                    tips.add("§6矿石处理机（3×3×3）");
                    tips.add("§7矿石 + 1000mB 水 → 全部产物（100%）");
                    tips.add("§7默认有损超频；芯片 A 解锁无损；芯片 B 解锁高级模式");
                }
            }
        }
    }
}
