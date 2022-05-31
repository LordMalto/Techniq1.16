package com.entisy.techniq.core.util;

import com.entisy.techniq.core.capabilities.item.CapabilityItem;
import com.entisy.techniq.core.capabilities.item.IItemHandler;
import com.entisy.techniq.core.capabilities.item.IItemStorage;
import com.entisy.techniq.core.capabilities.item.ItemStorage;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class ItemUtils {
    private ItemUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static void trySendToNeighbors(IBlockReader world, BlockPos pos, IItemHandler itemHandler, int maxSend) {
        for (Direction side : Direction.values()) {
            if (itemHandler.getItemStored() == 0) {
                return;
            }
            trySendTo(world, pos, itemHandler, maxSend, side);
        }
    }

    public static void trySendTo(IBlockReader world, BlockPos pos, IItemHandler itemHandler, int maxSend, Direction side) {
        TileEntity tileEntity = world.getBlockEntity(pos.relative(side));
        if (tileEntity != null) {
            IItemStorage item = itemHandler.getItem(side).orElse(new ItemStorage(0));
            tileEntity.getCapability(CapabilityItem.ITEM, side.getOpposite()).ifPresent(other -> trySendItem(maxSend, item, other));
        }
    }

    private static void trySendItem(int maxSend, IItemStorage item, IItemStorage other) {
        if (other.canReceive()) {
            int toSend = item.extractItem(maxSend, true);
            int sent = other.receiveItem(toSend, false);
            if (sent > 0) {
                item.extractItem(sent, false);
            }
        }
    }

    /**
     * Gets the item capability for the block at the given position. If it does not have an item
     * capability, or the block is not a tile entity, this returns null.
     *
     * @param world The world
     * @param pos   The position to check
     * @return The item capability, or null if not present
     */
    @SuppressWarnings("ConstantConditions")
    @Nullable
    public static IItemStorage getItem(IWorldReader world, BlockPos pos) {
        if (!world.isAreaLoaded(pos, 1)) return null;
        TileEntity tileEntity = world.getBlockEntity(pos);
        return tileEntity != null ? tileEntity.getCapability(CapabilityItem.ITEM).orElse(null) : null;
    }

    /**
     * Gets the item capability of the object (item, etc), or null if it does not have one.
     *
     * @param provider The capability provider
     * @return The item capability, or null if not present
     */
    @SuppressWarnings("ConstantConditions")
    @Nullable
    public static IItemStorage getItem(ICapabilityProvider provider) {
        return provider.getCapability(CapabilityItem.ITEM).orElse(null);
    }

    /**
     * Gets the item capability of the object (item, etc), or null if it does not have one. Tries
     * to get the capability for the given side first, then null side.
     *
     * @param provider The capability provider
     * @param side     The side being accessed
     * @return The item capability, or null if not present
     */
    @SuppressWarnings("ConstantConditions")
    @Nullable
    public static IItemStorage getItemFromSideOrNull(ICapabilityProvider provider, Direction side) {
        return provider.getCapability(CapabilityItem.ITEM, side).orElse(provider.getCapability(CapabilityItem.ITEM).orElse(null));
    }

}
