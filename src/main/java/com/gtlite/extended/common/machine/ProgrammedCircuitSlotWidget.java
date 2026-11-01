package com.gtlite.extended.common.machine;

import gregtech.api.capability.impl.GhostCircuitItemStackHandler;
import gregtech.api.gui.Widget;
import gregtech.api.gui.widgets.GhostCircuitSlotWidget;
import gregtech.api.gui.widgets.WidgetGroup;
import gregtech.api.util.LocalizationUtils;
import gregtech.client.utils.TooltipHelper;

/**
 * 带“选择电路”面板的编程电路槽。
 *
 * <p>继承 GT 原生 {@link GhostCircuitSlotWidget}：
 * 左键 +1、右键 -1、Shift+右键清空、滚轮调节全部保留 GT 行为；
 * 这里只补两件事：</p>
 * <ol>
 *     <li>构造时设置 {@code consumer} 生成 tooltip，避免 GT 的
 *         {@code drawInForeground} 在 {@code tooltipText == null} 时 NPE；</li>
 *     <li>Shift+左键切换 0~32 的选择面板（GT 1.12.2 的旧 GUI 没有这个面板）。</li>
 * </ol>
 */
public class ProgrammedCircuitSlotWidget extends GhostCircuitSlotWidget {

    private final GhostCircuitItemStackHandler handler;
    private WidgetGroup selector;
    private Widget swapWidget;

    public ProgrammedCircuitSlotWidget(GhostCircuitItemStackHandler circuitInventory,
                                       int slotIndex, int xPosition, int yPosition) {
        super(circuitInventory, slotIndex, xPosition, yPosition);
        this.handler = circuitInventory;
        // GT 的 GhostCircuitSlotWidget.drawInForeground 在 tooltipText 为 null 且 consumer 为 null 时会 NPE。
        // 这里挂上 consumer：绘制/点击时按当前电路值刷新 tooltip。
        this.setConsumer(widget -> this.updateTooltip());
    }

    /** 选择面板与“面板打开时隐藏”的控件（本总成里是流体配置）。 */
    public ProgrammedCircuitSlotWidget attachSelector(WidgetGroup selector, Widget swapWidget) {
        this.selector = selector;
        this.swapWidget = swapWidget;
        return this;
    }

    /** 用当前电路值刷新 GT 的 tooltip（{@code gregtech.gui.configurator_slot.tooltip}）。 */
    private void updateTooltip() {
        int value = this.handler.getCircuitValue();
        String configString = value < 0
                ? LocalizationUtils.format("gregtech.gui.configurator_slot.no_value")
                : String.valueOf(value);
        this.setTooltipText("gregtech.gui.configurator_slot.tooltip", configString);
    }

    /** 选择面板里点某个值（客户端与服务端回调都会执行）。 */
    public void selectValue(int value) {
        this.handler.setCircuitValue(value);
        // updateTooltip 走 LocalizationUtils（客户端本地化），只在客户端执行，避免专用服务端崩溃
        if (isClientSide()) {
            this.updateTooltip();
        }
        this.hideSelector();
    }

    public void hideSelector() {
        if (this.selector == null) {
            return;
        }
        this.selector.setVisible(false);
        if (this.swapWidget != null) {
            this.swapWidget.setVisible(true);
        }
    }

    private void toggleSelector() {
        if (this.selector == null) {
            return;
        }
        boolean show = !this.selector.isVisible();
        this.selector.setVisible(show);
        if (this.swapWidget != null) {
            this.swapWidget.setVisible(!show);
        }
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        if (button == 0 && TooltipHelper.isShiftDown() && this.isMouseOverElement(mouseX, mouseY)) {
            this.toggleSelector();
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}
