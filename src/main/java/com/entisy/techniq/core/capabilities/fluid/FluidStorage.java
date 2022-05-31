package com.entisy.techniq.core.capabilities.fluid;

public class FluidStorage implements IFluidStorage {
    protected int fluid;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public FluidStorage(int capacity) {
        this(capacity, capacity, capacity, 0);
    }

    public FluidStorage(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public FluidStorage(int capacity, int maxReceive, int maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public FluidStorage(int capacity, int maxReceive, int maxExtract, int fluid) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.fluid = Math.max(0, Math.min(capacity, fluid));
    }

    @Override
    public int receiveFluid(int maxReceive, boolean simulate) {
        if (!canReceive())
            return 0;

        int fluidReceived = Math.min(capacity - fluid, Math.min(this.maxReceive, maxReceive));
        if (!simulate)
            fluid += fluidReceived;
        return fluidReceived;
    }

    @Override
    public int extractFluid(int maxExtract, boolean simulate) {
        if (!canExtract())
            return 0;

        int fluidExtracted = Math.min(fluid, Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            fluid -= fluidExtracted;
        return fluidExtracted;
    }

    @Override
    public int getFluidStored() {
        return fluid;
    }

    @Override
    public int getMaxFluidStored() {
        return capacity;
    }

    @Override
    public boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
        return this.maxReceive > 0;
    }
}