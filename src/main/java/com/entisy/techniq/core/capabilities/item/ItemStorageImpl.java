package com.entisy.techniq.core.capabilities.item;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.EnumMap;

public class ItemStorageImpl extends ItemStorageImplBase {
    private final EnumMap<Direction, LazyOptional<Connection>> connections = new EnumMap<>(Direction.class);
    private final TileEntity tileEntity;

    public ItemStorageImpl(int capacity, int maxReceive, int maxExtract, TileEntity tileEntity) {
        super(capacity, maxReceive, maxExtract);
        this.tileEntity = tileEntity;
        Arrays.stream(Direction.values()).forEach(d -> connections.put(d, LazyOptional.of(Connection::new)));
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (side == null) return super.getCapability(cap, null);
        return CapabilityItem.ITEM.orEmpty(cap, connections.get(side).cast());
    }

    @Override
    public void invalidate() {
        super.invalidate();
        connections.values().forEach(LazyOptional::invalidate);
    }

    /**
     * Add item, bypassing max receive limit. Useful for generators, which would normally not
     * receive item from other blocks.
     *
     * @param amount The amount of item
     */
    public void createItem(int amount) {
        this.item = Math.min(this.item + amount, getMaxItemStored());
    }

    /**
     * Remove item, bypassing max extract limit. Useful for machines which consume item, which
     * would normally not send item to other blocks.
     *
     * @param amount The amount of item to remove
     */
    public void consumeItem(int amount) {
        this.item = Math.max(this.item - amount, 0);
    }

    /**
     * Sets item directly. Should only be used for syncing data from server to client.
     *
     * @param amount The new amount of stored item
     */
    public void setItemDirectly(int amount) {
        this.item = amount;
    }

    /**
     * Wrapper which will prevent item from being sent back to the sender on the same tick
     */
    public class Connection implements IItemStorage {
        private long lastReceiveTick;

        @Override
        public int receiveItem(int maxReceive, boolean simulate) {
            World world = ItemStorageImpl.this.tileEntity.getLevel();
            if (world == null) return 0;

            int received = ItemStorageImpl.this.receiveItem(maxReceive, simulate);
            if (received > 0 && !simulate)
                this.lastReceiveTick = world.getGameTime();
            return received;
        }

        @Override
        public int extractItem(int maxExtract, boolean simulate) {
            World world = ItemStorageImpl.this.tileEntity.getLevel();
            if (world == null) return 0;

            long time = world.getGameTime();
            if (time != this.lastReceiveTick) {
                return ItemStorageImpl.this.extractItem(maxExtract, simulate);
            }
            return 0;
        }

        @Override
        public int getItemStored() {
            return ItemStorageImpl.this.getItemStored();
        }

        @Override
        public int getMaxItemStored() {
            return ItemStorageImpl.this.getMaxItemStored();
        }

        @Override
        public boolean canExtract() {
            return ItemStorageImpl.this.canExtract();
        }

        @Override
        public boolean canReceive() {
            return ItemStorageImpl.this.canReceive();
        }
    }
}
