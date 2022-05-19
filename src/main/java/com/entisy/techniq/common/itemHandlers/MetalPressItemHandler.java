package com.entisy.techniq.common.itemHandlers;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class MetalPressItemHandler extends ItemStackHandler {

	public MetalPressItemHandler(int size, ItemStack... items) {
		super(size);
		for (int i = 0; i < items.length; i++) {
			stacks.set(i, items[i]);
		}
	}

	public void clear() {
		for (int i = 0; i < getSlots(); i++) {
			stacks.set(i, ItemStack.EMPTY);
			onContentsChanged(i);
		}
	}

	public boolean isEmpty() {
		for (ItemStack stack : stacks) {
			if (stack.isEmpty() || stack.getItem() == Items.AIR) {
				return true;
			}
		}
		return false;
	}
	
	public boolean onContentChanged(int slot) {
		super.onContentsChanged(0);
		return true;
	}

	public ItemStack decreaseStackSize(int index, int count) {
		ItemStack stack = getStackInSlot(index);
		stack.shrink(count);
		onContentsChanged(index);
		return stack;
	}

	public void removeStackFromSlot(int index) {
		stacks.set(index, ItemStack.EMPTY);
		onContentsChanged(index);
	}

	public NonNullList<ItemStack> toNonNullList() {
		NonNullList<ItemStack> items = NonNullList.create();
		for (ItemStack stack : stacks) {
			items.add(stack);
		}
		return items;
	}

	public void setNonNullList(NonNullList<ItemStack> items) {
		if (items.size() == 0)
			return;
		if (items.size() != getSlots())
			throw new IndexOutOfBoundsException("NonNullList must be same size as ItemStackHandler!");
		for (int index = 0; index < items.size(); index++) {
			stacks.set(index, items.get(index));
		}
	}
	
	@Override
	public int getSlots() {
		return 2;
	}

	@Override
	public String toString() {
		return stacks.toString();
	}
}
