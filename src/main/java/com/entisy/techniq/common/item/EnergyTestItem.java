package com.entisy.techniq.common.item;

import com.entisy.techniq.common.item.energy.EnergyItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;

public class EnergyTestItem extends EnergyItem {

    public EnergyTestItem(int maxEnergy) {
        super(maxEnergy, 10, 0);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        ItemStack item = player.getItemInHand(hand);
        item.getCapability(CapabilityEnergy.ENERGY).ifPresent(e -> e.receiveEnergy(5,false));

        return ActionResult.success(item);
    }

    @Override
    public boolean isChargable() {
        return true;
    }
}
