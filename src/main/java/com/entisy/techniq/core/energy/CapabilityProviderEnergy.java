package com.entisy.techniq.core.energy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityProviderEnergy implements ICapabilitySerializable<INBT> {
    @CapabilityInject(IEnergyStorage.class)
    private ModEnergyHandler modEnergyHandler;  // initially null until our first call to getCachedInventory
    private final LazyOptional<IEnergyStorage> lazyInitialisionSupplier = LazyOptional.of(this::getEnergy);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if (cap == CapabilityEnergy.ENERGY) return (LazyOptional<T>) (lazyInitialisionSupplier);
        return LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("energy", getEnergy().getEnergyStored());
        return nbt;
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        modEnergyHandler.receiveEnergy(((CompoundNBT) nbt).getInt("energy"), false);
        CapabilityEnergy.ENERGY.readNBT(getEnergy(), null, nbt);
    }

    private IEnergyStorage getEnergy() {
        if (modEnergyHandler == null) {
            modEnergyHandler = new ModEnergyHandler(25000);
        }

        return modEnergyHandler;
    }
}