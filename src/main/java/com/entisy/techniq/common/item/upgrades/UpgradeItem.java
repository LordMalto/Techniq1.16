package com.entisy.techniq.common.item.upgrades;

import com.entisy.techniq.core.tab.TechniqTab;
import net.minecraft.item.Item;

public class UpgradeItem extends Item implements Upgrade{

    private int stage;

    public UpgradeItem() {
        super(new Item.Properties().stacksTo(8).tab(TechniqTab.TECHNIQ_TAB));
    }

    @Override
    public UpgradeType getUpgradeType() {
        return null;
    }

    @Override
    public int getUpgradeStage() {
        return 0;
    }
}
