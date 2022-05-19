package com.entisy.techniq.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HammerItem extends Item {

	public HammerItem(Properties properties) {
		super(properties);
		properties.stacksTo(1);
		properties.durability(65);
	}
	
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack item = itemStack.copy();
		item.setDamageValue(item.getDamageValue() + 1);
		return item;
	}
}
