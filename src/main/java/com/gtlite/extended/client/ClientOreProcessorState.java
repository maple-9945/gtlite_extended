package com.gtlite.extended.client;

/** 客户端缓存的芯片状态（GUI 读取）。 */
public final class ClientOreProcessorState {

    private static boolean lossless;
    private static boolean advanced;

    private ClientOreProcessorState() {
    }

    public static void update(boolean newLossless, boolean newAdvanced) {
        lossless = newLossless;
        advanced = newAdvanced;
    }

    public static boolean isLossless() {
        return lossless;
    }

    public static boolean isAdvanced() {
        return advanced;
    }
}
