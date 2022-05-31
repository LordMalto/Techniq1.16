package com.entisy.techniq.core.capabilities.fluid;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidStorageImplBase extends FluidStorage implements ICapabilityProvider {
    private final LazyOptional<FluidStorageImplBase> lazy;

    public FluidStorageImplBase(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract, 0);
        this.lazy = LazyOptional.of(() -> this);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return CapabilityFluid.FLUID.orEmpty(cap, lazy.cast());
    }

    public void invalidate() {
        this.lazy.invalidate();
    }
}
