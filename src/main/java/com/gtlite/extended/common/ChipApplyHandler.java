package com.gtlite.extended.common;

import com.gtlite.extended.Tags;
import com.gtlite.extended.common.item.ItemUpgradeChip;
import com.gtlite.extended.common.machine.IChipUpgradable;
import com.gtlite.extended.common.network.MessageOreProcessorSync;
import com.gtlite.extended.common.network.ModNetwork;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.util.GTUtility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** 芯片右键机器：A 开无损，B 在 A 基础上开高级模式（所有 {@link IChipUpgradable} 机器通用）。 */
@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public final class ChipApplyHandler {

    private ChipApplyHandler() {
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        if (event.getWorld().isRemote) return;
        EntityPlayer player = event.getEntityPlayer();
        if (player == null) return;

        ItemStack held = player.getHeldItemMainhand();
        if (!(held.getItem() instanceof ItemUpgradeChip)) return;

        MetaTileEntity mte = GTUtility.getMetaTileEntity(event.getWorld(), event.getPos());
        if (!(mte instanceof IChipUpgradable)) return;

        IChipUpgradable machine = (IChipUpgradable) mte;
        ItemUpgradeChip chip = (ItemUpgradeChip) held.getItem();

        if (chip.isPerfectOc()) {
            machine.setLosslessOverclock(true);
            player.sendMessage(new TextComponentString("§b[GTLite Extended] 已开启无损超频"));
        } else if (chip.isAdvanced()) {
            if (!machine.isLosslessOverclock()) {
                player.sendMessage(new TextComponentString("§c[GTLite Extended] 需要先使用无损超频芯片"));
                return;
            }
            machine.setAdvancedMode(true);
            player.sendMessage(new TextComponentString("§b[GTLite Extended] 已开启高级模式（零耗电 + 无限并行）"));
        }

        if (player instanceof EntityPlayerMP) {
            ModNetwork.INSTANCE.sendTo(
                    new MessageOreProcessorSync(machine.isLosslessOverclock(), machine.isAdvancedMode()),
                    (EntityPlayerMP) player);
        }
        event.setCanceled(true);
    }
}
