package com.entisy.techniq.core.capabilities.fluid;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.EnumMap;

public class FluidStorageImpl extends FluidStorageImplBase {
    private final EnumMap<Direction, LazyOptional<Connection>> connections = new EnumMap<>(Direction.class);
    private final TileEntity tileEntity;

    public FluidStorageImpl(int capacity, int maxReceive, int maxExtract, TileEntity tileEntity) {
        super(capacity, maxReceive, maxExtract);
        this.tileEntity = tileEntity;
        Arrays.stream(Direction.values()).forEach(d -> connections.put(d, LazyOptional.of(Connection::new)));
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (side == null) return super.getCapability(cap, null);
        return CapabilityFluid.FLUID.orEmpty(cap, connections.get(side).cast());
    }

    @Override
    public void invalidate() {
        super.invalidate();
        connections.values().forEach(LazyOptional::invalidate);
    }

    /**
     * Add fluid, bypassing max receive limit. Useful for generators, which would normally not
     * receive fluid from other blocks.
     *
     * @param amount The amount of fluid
     */
    public void createFluid(int amount) {
        this.fluid = Math.min(this.fluid + amount, getMaxFluidStored());
    }

    /**
     * Remove fluid, bypassing max extract limit. Useful for machines which consume fluid, which
     * would normally not send fluid to other blocks.
     *
     * @param amount The amount of fluid to remove
     */
    public void consumeFluid(int amount) {
        this.fluid = Math.max(this.fluid - amount, 0);
    }

    /**
     * Sets fluid directly. Should only be used for syncing data from server to client.
     *
     * @param amount The new amount of stored fluid
     */
    public void setFluidDirectly(int amount) {
        this.fluid = amount;
    }

    /**
     * Wrapper which will prevent fluid from being sent back to the sender on the same tick
     */
    public class Connection implements IFluidStorage {
        private long lastReceiveTick;

        @Override
        public int receiveFluid(int maxReceive, boolean simulate) {
            World world = FluidStorageImpl.this.tileEntity.getLevel();
            if (world == null) return 0;

            int received = FluidStorageImpl.this.receiveFluid(maxReceive, simulate);
            if (received > 0 && !simulate)
                this.lastReceiveTick = world.getGameTime();
            return received;
        }

        @Override
        public int extractFluid(int maxExtract, boolean simulate) {
            World world = FluidStorageImpl.this.tileEntity.getLevel();
            if (world == null) return 0;

            long time = world.getGameTime();
            if (time != this.lastReceiveTick) {
                return FluidStorageImpl.this.extractFluid(maxExtract, simulate);
            }
            return 0;
        }

        @Override
        public int getFluidStored() {
            return FluidStorageImpl.this.getFluidStored();
        }

        @Override
        public int getMaxFluidStored() {
            return FluidStorageImpl.this.getMaxFluidStored();
        }

        @Override
        public boolean canExtract() {
            return FluidStorageImpl.this.canExtract();
        }

        @Override
        public boolean canReceive() {
            return FluidStorageImpl.this.canReceive();
        }
    }
}
