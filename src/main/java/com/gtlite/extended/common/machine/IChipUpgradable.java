package com.gtlite.extended.common.machine;

/** 可被升级芯片强化的多方块机器。 */
public interface IChipUpgradable {

    boolean isLosslessOverclock();

    void setLosslessOverclock(boolean value);

    boolean isAdvancedMode();

    void setAdvancedMode(boolean value);
}
