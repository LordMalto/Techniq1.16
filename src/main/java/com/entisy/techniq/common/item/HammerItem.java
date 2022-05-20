package com.entisy.techniq.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HammerItem extends Item {

	public HammerItem(Properties properties) {
		super(properties.defaultDurability(110));
	}
	
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack item = itemStack.copy();
		item.hurt(1, null, null);
		if (item.getDamageValue() > 110/*durability*/ - 1) return ItemStack.EMPTY;
		return item;
	}

	@Override
	public boolean isDamageable(ItemStack stack) {
		return true;
	}
}
