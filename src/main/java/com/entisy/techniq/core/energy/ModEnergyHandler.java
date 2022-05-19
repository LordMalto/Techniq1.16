package com.entisy.techniq.core.energy;

import net.minecraftforge.energy.EnergyStorage;

public class ModEnergyHandler extends EnergyStorage {

    public ModEnergyHandler(int capacity) {
        super(capacity);
    }

    public ModEnergyHandler(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public ModEnergyHandler(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public ModEnergyHandler(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    public ModEnergyHandler() {
        super(0);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return super.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return super.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return super.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return super.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return super.canExtract();
    }

    @Override
    public boolean canReceive() {
        return super.canReceive();
    }


}