package com.gtlite.extended.common.gui;

import com.gtlite.extended.common.machine.MetaTileEntityMEAssembly;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.IRenderContext;
import gregtech.api.gui.Widget;
import gregtech.api.util.Position;
import gregtech.client.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 统一的“幽灵标记槽”（Ghost Slot）：
 *
 * <ul>
 *     <li>左键：把光标上的物品设为物品标记（不消耗物品，只记 1 个）；光标为空时清除物品标记；</li>
 *     <li>右键：把光标上流体容器里的流体设为流体标记（不消耗容器，只记 1 桶）；
 *         光标上没有流体容器时清除流体标记；</li>
 *     <li>一个槽位要么是物品标记、要么是流体标记（同时只会存在一个），未标记时显示为空槽；</li>
 *     <li>点击通过 {@link Widget#writeClientAction(int, java.util.function.Consumer)} 发到服务端，
 *         服务端写进 {@link MetaTileEntityMEAssembly} 的标记数组后再同步回客户端。</li>
 * </ul>
 */
public class GhostFilterSlotWidget extends Widget {

    private static final int ACTION_SET_ITEM = 0;
    private static final int ACTION_SET_FLUID = 1;
    private static final int ACTION_CLEAR_ITEM = 2;
    private static final int ACTION_CLEAR_FLUID = 3;

    private final MetaTileEntityMEAssembly assembly;
    private final int filterIndex;

    public GhostFilterSlotWidget(MetaTileEntityMEAssembly assembly, int filterIndex, int x, int y) {
        super(x, y, 18, 18);
        this.assembly = assembly;
        this.filterIndex = filterIndex;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawInBackground(int mouseX, int mouseY, float partialTicks, IRenderContext context) {
        Position pos = this.getPosition();
        GuiTextures.SLOT.draw(pos.x, pos.y, 18, 18);
        ItemStack item = this.assembly.getItemFilter(this.filterIndex);
        FluidStack fluid = this.assembly.getFluidFilter(this.filterIndex);
        if (item != null && !item.isEmpty()) {
            RenderUtil.drawItemStack(item, pos.x + 1, pos.y + 1);
            this.drawStock(this.assembly.getItemStock(this.filterIndex));
        } else if (fluid != null) {
            RenderUtil.drawFluidForGui(fluid, fluid.amount, pos.x + 1, pos.y + 1, 16, 16);
            this.drawStock(this.assembly.getFluidStock(this.filterIndex));
        }
    }

    /** 在槽位右下角画 AE 网络里的总存量（离线/0 显示红色 0）。 */
    @SideOnly(Side.CLIENT)
    private void drawStock(long stock) {
        Position pos = this.getPosition();
        String text = formatStock(stock);
        int color = stock > 0L ? 0xFFFFFF : 0xFF5555;
        float scale = 0.5F;
        int width = Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
        float x = pos.x + 17 - width * scale;
        float y = pos.y + 17 - 8 * scale;
        Widget.drawText(text, x, y, scale, color);
    }

    /** 科学计数缩写：999 / 1.2k / 1.5M / 2.3G / 4.1T … */
    public static String formatStock(long value) {
        if (value < 1000L) {
            return Long.toString(value);
        }
        String[] units = {"k", "M", "G", "T", "P", "E"};
        double result = value;
        int unit = -1;
        while (result >= 1000.0D && unit < units.length - 1) {
            result /= 1000.0D;
            unit++;
        }
        String suffix = units[unit];
        if (result >= 100.0D) {
            return String.format(Locale.ROOT, "%.0f%s", result, suffix);
        }
        if (result >= 10.0D) {
            return String.format(Locale.ROOT, "%.1f%s", result, suffix);
        }
        return String.format(Locale.ROOT, "%.2f%s", result, suffix);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawInForeground(int mouseX, int mouseY) {
        if (!this.isMouseOverElement(mouseX, mouseY)) {
            return;
        }
        List<String> tooltip = new ArrayList<>();
        ItemStack item = this.assembly.getItemFilter(this.filterIndex);
        FluidStack fluid = this.assembly.getFluidFilter(this.filterIndex);
        long stock = 0L;
        boolean marked = false;
        if (item != null && !item.isEmpty()) {
            tooltip.addAll(getItemToolTip(item));
            stock = this.assembly.getItemStock(this.filterIndex);
            marked = true;
        } else if (fluid != null) {
            tooltip.add(fluid.getLocalizedName());
            tooltip.add(fluid.amount + " mB");
            stock = this.assembly.getFluidStock(this.filterIndex);
            marked = true;
        } else {
            tooltip.add(I18n.translateToLocal("gtlite_extended.gui.ghost_hint"));
        }
        if (marked) {
            String text = formatStock(stock);
            tooltip.add((stock > 0L ? "§aAE: " : "§cAE: ") + text);
        }
        this.drawHoveringText(ItemStack.EMPTY, tooltip, 300, mouseX, mouseY);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        if (!this.isMouseOverElement(mouseX, mouseY) || !this.isActive()) {
            return false;
        }
        EntityPlayer player = this.gui == null ? null : this.gui.entityPlayer;
        if (player == null) {
            return false;
        }
        ItemStack held = player.inventory.getItemStack();
        if (button == 0) {
            if (held != null && !held.isEmpty()) {
                ItemStack marker = held.copy();
                marker.setCount(1);
                this.writeClientAction(ACTION_SET_ITEM, buf -> buf.writeItemStack(marker));
            } else {
                this.writeClientAction(ACTION_CLEAR_ITEM, buf -> {
                });
            }
            return true;
        }
        if (button == 1) {
            FluidStack contained = held == null || held.isEmpty() ? null : FluidUtil.getFluidContained(held);
            if (contained != null && contained.getFluid() != null && contained.amount > 0) {
                FluidStack marker = contained.copy();
                marker.amount = Fluid.BUCKET_VOLUME;
                NBTTagCompound tag = new NBTTagCompound();
                marker.writeToNBT(tag);
                this.writeClientAction(ACTION_SET_FLUID, buf -> buf.writeCompoundTag(tag));
            } else {
                this.writeClientAction(ACTION_CLEAR_FLUID, buf -> {
                });
            }
            return true;
        }
        return false;
    }

    @Override
    public void handleClientAction(int id, PacketBuffer buffer) {
        try {
            switch (id) {
                case ACTION_SET_ITEM: {
                    this.assembly.setItemFilter(this.filterIndex, buffer.readItemStack());
                    break;
                }
                case ACTION_SET_FLUID: {
                    NBTTagCompound tag = buffer.readCompoundTag();
                    this.assembly.setFluidFilter(this.filterIndex,
                            tag == null ? null : FluidStack.loadFluidStackFromNBT(tag));
                    break;
                }
                case ACTION_CLEAR_ITEM: {
                    this.assembly.setItemFilter(this.filterIndex, ItemStack.EMPTY);
                    break;
                }
                case ACTION_CLEAR_FLUID: {
                    this.assembly.setFluidFilter(this.filterIndex, null);
                    break;
                }
                default:
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
