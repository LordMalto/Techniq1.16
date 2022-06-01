
package com.entisy.techniq.common.item.upgrades;

public class SpeedUpgradeItem extends UpgradeItem implements Upgrade {

    @Override
    public UpgradeType getUpgradeType() {
        return UpgradeType.RANGE;
    }

    @Override
    public int getUpgradeStage() {
        return 2; // adds 2 blocks to the range
    }
}
