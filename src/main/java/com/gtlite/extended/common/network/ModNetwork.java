package com.gtlite.extended.common.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class ModNetwork {

    public static final String CHANNEL = "gtlite_extended_net";
    public static SimpleNetworkWrapper INSTANCE;

    private ModNetwork() {
    }

    public static void register() {
        if (INSTANCE != null) return;
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL);
        INSTANCE.registerMessage(MessageOreProcessorSync.Handler.class, MessageOreProcessorSync.class, 0, Side.CLIENT);
    }
}
