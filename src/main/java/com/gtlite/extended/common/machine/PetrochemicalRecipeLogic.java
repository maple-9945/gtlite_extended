package com.gtlite.extended.common.machine;

/** 石化工厂的芯片逻辑：状态来自 {@link MetaTileEntityPetrochemicalPlant}。 */
public class PetrochemicalRecipeLogic extends AdvancedModeRecipeLogic {

    private final MetaTileEntityPetrochemicalPlant machine;

    public PetrochemicalRecipeLogic(MetaTileEntityPetrochemicalPlant machine) {
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
