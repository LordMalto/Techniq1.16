package com.entisy.techniq.common.block.displayCase;

import java.util.Objects;

import com.entisy.techniq.core.init.ModBlocks;
import com.entisy.techniq.core.init.ModContainerTypes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

public class DisplayCaseContainer extends Container {

	public final DisplayCaseTileEntity tileEntity;
	private final IWorldPosCallable canInteractWithCallable;

	public DisplayCaseContainer(final int windowId, final PlayerInventory inv, final DisplayCaseTileEntity tileEntity) {
		super(ModContainerTypes.DISPLAY_CASE_CONTAINER_TYPE.get(), windowId);
		this.tileEntity = tileEntity;
		this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());

		// Tile Entity
		addSlot(new Slot((IInventory) tileEntity, 0, 80, 35));

		// Main Player Inventory
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				addSlot(new Slot(inv, column + row * 9 + 9, 8 + column * 18, 166 - (4 - row) * 18 - 10));
			}
		}

		// Player Hotbar
		for (int column = 0; column < 9; column++) {
			addSlot(new Slot(inv, column, 8 + column * 18, 142));
		}
	}

	public DisplayCaseContainer(final int windowId, final PlayerInventory inv, final PacketBuffer data) {
		this(windowId, inv, getTileEntity(inv, data));
	}

	private static DisplayCaseTileEntity getTileEntity(final PlayerInventory inv, final PacketBuffer data) {
		Objects.requireNonNull(inv, "Player Inventory cannot be null.");
		Objects.requireNonNull(data, "Packet Buffer cannot be null.");
		final TileEntity tileEntity = inv.player.level.getBlockEntity(data.readBlockPos());
		if (tileEntity instanceof DisplayCaseTileEntity) {
			return (DisplayCaseTileEntity) tileEntity;
		}
		throw new IllegalStateException("Tile Entity is not correct");
	}

	@Override
	public boolean stillValid(PlayerEntity player) {
		return stillValid(canInteractWithCallable, player, ModBlocks.DISPLAY_CASE.get());
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			if (index < DisplayCaseTileEntity.slots
					&& !moveItemStackTo(stack1, DisplayCaseTileEntity.slots, slots.size(), true)) {
				return ItemStack.EMPTY;
			}
			if (!moveItemStackTo(stack1, 0, DisplayCaseTileEntity.slots, false)) {
				return ItemStack.EMPTY;
			}
			if (stack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return stack;
	}
}
