package com.entisy.techniq.core.capabilities.fluid;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

public interface IFluidHandler {
    FluidStorageImpl getFluidImpl();

    default LazyOptional<IFluidStorage> getFluid(@Nullable Direction side) {
        return getFluidImpl().getCapability(CapabilityFluid.FLUID, side);
    }

    default int getFluidStored() {
        IFluidStorage fluid = getFluid(null).orElse(new FluidStorage(25_000));
        return fluid.getFluidStored();
    }

    default int getMaxFluidStored() {
        IFluidStorage fluid = getFluid(null).orElse(new FluidStorage(100_000));
        return fluid.getMaxFluidStored();
    }

    default void setFluidStoredDirectly(int value) {
        getFluid(null).ifPresent(e -> {
            if (e instanceof FluidStorageImpl) {
                ((FluidStorageImpl) e).setFluidDirectly(value);
            }
        });
    }

    default void readFluid(CompoundNBT tags) {
        setFluidStoredDirectly(tags.getInt("Fluid"));
    }

    default void writeFluid(CompoundNBT tags) {
        tags.putInt("Fluid", getFluidStored());
    }
}
