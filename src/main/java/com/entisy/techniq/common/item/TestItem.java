package com.entisy.techniq.common.item;

import com.entisy.techniq.common.item.energy.EnergyItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TestItem extends EnergyItem {

    public TestItem() {
        super(100);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand).copy();

        receiveEnergy(10, stack);
        System.out.println(currentEnergy);
        return ActionResult.consume(stack);
    }

    @Override
    public boolean isChargable() {
        return true;
    }
}
