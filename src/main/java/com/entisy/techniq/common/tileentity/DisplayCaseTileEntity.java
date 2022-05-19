package com.entisy.techniq.common.tileentity;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.container.DisplayCaseContainer;
import com.entisy.techniq.core.init.TileEntityTypesInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class DisplayCaseTileEntity extends LockableLootTileEntity {

	public static int slots = 1;
	
	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);
	
	public DisplayCaseTileEntity(TileEntityType<?> type) {
		super(type);
	}
	
	public DisplayCaseTileEntity()
	{
		this(TileEntityTypesInit.DISPLAY_CASE_TILE_ENTITY_TYPE.get());
	}

	@Override
	public int getContainerSize() {
		return slots;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return items;
	}
	
	public ItemStack getItem() {
		return items.get(0);
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		this.items = items;
	}

	@Override
	public ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + Techniq.MOD_ID + ".display_case");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory inv) {
		return new DisplayCaseContainer(id, inv, this);
	}
	
	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		super.save(nbt);
		if (!trySaveLootTable(nbt)) {
			ItemStackHelper.saveAllItems(nbt, items);
		}
		return nbt;
	}
	
	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		if (!tryLoadLootTable(nbt)) {
			ItemStackHelper.loadAllItems(nbt, items);
		}
	}
}
