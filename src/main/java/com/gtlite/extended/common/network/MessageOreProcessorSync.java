package com.gtlite.extended.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/** 服务端 -> 客户端：同步矿石处理机的芯片状态（GUI 显示用）。 */
public class MessageOreProcessorSync implements IMessage {

    private boolean lossless;
    private boolean advanced;

    public MessageOreProcessorSync() {
    }

    public MessageOreProcessorSync(boolean lossless, boolean advanced) {
        this.lossless = lossless;
        this.advanced = advanced;
    }

    public boolean isLossless() {
        return lossless;
    }

    public boolean isAdvanced() {
        return advanced;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        lossless = buf.readBoolean();
        advanced = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(lossless);
        buf.writeBoolean(advanced);
    }

    public static class Handler implements IMessageHandler<MessageOreProcessorSync, IMessage> {
        @Override
        public IMessage onMessage(MessageOreProcessorSync message, MessageContext ctx) {
            net.minecraft.client.Minecraft.getMinecraft().addScheduledTask(() ->
                    com.gtlite.extended.client.ClientOreProcessorState.update(
                            message.isLossless(), message.isAdvanced()));
            return null;
        }
    }
}
