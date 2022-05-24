package com.entisy.techniq.common.block.furnaceGenerator;

//import com.entisy.techniq.common.block.displayCase.DisplayCaseTileEntity;
import com.entisy.techniq.core.init.ModBlocks;
import com.entisy.techniq.core.init.ModContainerTypes;
import com.entisy.techniq.core.util.FunctionalIntReferenceHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Objects;

public class FurnaceGeneratorContainer  extends Container {

    public FurnaceGeneratorTileEntity tileEntity;
    private IWorldPosCallable canInteractWithCallable;
    public FunctionalIntReferenceHolder currentSmeltTime;
    public FunctionalIntReferenceHolder currentEnergy;

    public FurnaceGeneratorContainer(final int id, final PlayerInventory inv, final FurnaceGeneratorTileEntity tileEntity) {
        super(ModContainerTypes.FURNACE_GENERATOR_CONTAINER_TYPE.get(), id);
        this.tileEntity = tileEntity;
        canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());

        final int slotSizePlus2 = 18;
        final int startX = 8;

        // furnace
        addSlot(new SlotItemHandler(tileEntity.getInventory(), 0, 80, 51));

        // inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        // hotbar
        int hotbarY = 142;
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(inv, column, startX + (column * slotSizePlus2), hotbarY));
        }

        addDataSlot(currentSmeltTime = new FunctionalIntReferenceHolder(() -> tileEntity.currentSmeltTime, v -> tileEntity.currentSmeltTime = v));
        addDataSlot(currentEnergy = new FunctionalIntReferenceHolder(() -> tileEntity.currentEnergy, value -> tileEntity.currentEnergy = value));
    }

    public FurnaceGeneratorContainer(final int id, final PlayerInventory inv, final PacketBuffer buffer) {
        this(id, inv, getTileEntity(inv, buffer));
    }

    private static FurnaceGeneratorTileEntity getTileEntity(PlayerInventory inv, PacketBuffer buffer) {
        Objects.requireNonNull(inv, "Inventory cannot be null");
        Objects.requireNonNull(buffer, "PacketBuffer cannot be null");
        final TileEntity tileEntity = inv.player.level.getBlockEntity(buffer.readBlockPos());
        if (tileEntity instanceof FurnaceGeneratorTileEntity) {
            return (FurnaceGeneratorTileEntity) tileEntity;
        }
        throw new IllegalStateException("TileEntity is not correct!");
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return stillValid(canInteractWithCallable, player, ModBlocks.FURNACE_GENERATOR.get());
    }



    @OnlyIn(Dist.CLIENT)
    public int getSmeltProgressionScaled() {
        return currentSmeltTime.get() != 0 && tileEntity.getMaxSmeltTime() != 0 ? currentSmeltTime.get() * 13 / tileEntity.getMaxSmeltTime() : 0;
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            final int inventorySize = 1;
            final int playerInventoryEnd = inventorySize + 27;
            final int playerHotbarEnd = playerInventoryEnd + 9;

            if (index == 0) {
                if (!this.moveItemStackTo(itemstack1, inventorySize, playerHotbarEnd, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != 0) {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, inventorySize, playerHotbarEnd, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, itemstack1);
        }
        return itemstack;
    }
}
