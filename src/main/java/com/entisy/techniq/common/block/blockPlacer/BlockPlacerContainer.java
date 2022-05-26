package com.entisy.techniq.common.block.blockPlacer;

import com.entisy.techniq.common.slots.OutputSlot;
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
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Objects;

public class BlockPlacerContainer extends Container {

    public final BlockPlacerTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;
    public FunctionalIntReferenceHolder currentSmeltTime;
    public FunctionalIntReferenceHolder currentEnergy;

    public BlockPlacerContainer(final int id, final PlayerInventory inv, final BlockPlacerTileEntity tileEntity) {
        super(ModContainerTypes.BLOCK_PLACER_CONTAINER_TYPE.get(), id);
        this.tileEntity = tileEntity;
        canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());

        final int slotSizePlus2 = 18;
        final int startX = 8;

        // furnace
        addSlot(new SlotItemHandler(tileEntity.getInventory(), 0, 70, 17));
        addSlot(new SlotItemHandler(tileEntity.getInventory(), 1, 88, 17));
        addSlot(new SlotItemHandler(tileEntity.getInventory(), 2, 70, 35));
        addSlot(new SlotItemHandler(tileEntity.getInventory(), 3, 88, 35));
        addSlot(new SlotItemHandler(tileEntity.getInventory(), 4, 70, 53));
        addSlot(new SlotItemHandler(tileEntity.getInventory(), 5, 88, 53));

        // inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * slotSizePlus2, 166 - (4 - row) * slotSizePlus2 - 10));
            }
        }

        // hotbar
        int hotbarY = 142;
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(inv, column, startX + (column * slotSizePlus2), hotbarY));
        }

        addDataSlot(currentSmeltTime = new FunctionalIntReferenceHolder(() -> tileEntity.currentSmeltTime, value -> tileEntity.currentSmeltTime = value));
        addDataSlot(currentEnergy = new FunctionalIntReferenceHolder(() -> tileEntity.currentEnergy, value -> tileEntity.currentEnergy = value));
    }

    public BlockPlacerContainer(final int id, final PlayerInventory inv, final PacketBuffer buffer) {
        this(id, inv, getTileEntity(inv, buffer));
    }

    private static BlockPlacerTileEntity getTileEntity(PlayerInventory inv, PacketBuffer buffer) {
        Objects.requireNonNull(inv, "Inventory cannot be null");
        Objects.requireNonNull(buffer, "PacketBuffer cannot be null");
        final TileEntity tileEntity = inv.player.level.getBlockEntity(buffer.readBlockPos());
        if (tileEntity instanceof BlockPlacerTileEntity) {
            return (BlockPlacerTileEntity) tileEntity;
        }
        throw new IllegalStateException("TileEntity is not correct!");
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return stillValid(canInteractWithCallable, player, ModBlocks.BLOCK_PLACER.get());
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();

            int slots = 6;
            int invSize = slots + 27;
            int hotbar = invSize + 9;

            itemstack = itemstack1.copy();
            if (index == 0 || index == 1 || index == 2 || index == 3 || index == 4 || index == 5) {
                if (!this.moveItemStackTo(itemstack1, slots, hotbar, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != 0 && index != 1 && index != 2 && index != 3 && index != 4 && index != 5) {
                if (index >= slots && index < invSize) {
                    if (!this.moveItemStackTo(itemstack1, 0, 6, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= invSize && index < hotbar && !this.moveItemStackTo(itemstack1, 0, 6, false)) {
                    return ItemStack.EMPTY;
                }
            }else if (!this.moveItemStackTo(itemstack1, slots, hotbar, false)) {
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

    public LazyOptional<IEnergyStorage> getCapabilityFromTE() {
        return this.tileEntity.getCapability(CapabilityEnergy.ENERGY);
    }
}
