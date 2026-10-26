package com.gtlite.extended.common.machine;

/** 矿石处理机的芯片逻辑：状态来自 {@link MetaTileEntityOreProcessor}。 */
public class OreProcessorRecipeLogic extends AdvancedModeRecipeLogic {

    private final MetaTileEntityOreProcessor machine;

    public OreProcessorRecipeLogic(MetaTileEntityOreProcessor machine) {
        super(machine);
        this.machine = machine;
    }

    @Override
    protected boolean isLosslessOverclock() {
        return machine.isLosslessOverclock();
    }

    @Override
    protected boolean isAdvancedMode() {
        return machine.isAdvancedMode();
    }
}
