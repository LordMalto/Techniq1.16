package com.entisy.techniq.core.energy;

import net.minecraftforge.energy.EnergyStorage;

public class ModEnergyStorage extends EnergyStorage {


    public ModEnergyStorage(int capacity) {
        super(capacity);
    }

    public ModEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public ModEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public ModEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    public ModEnergyStorage(){
        super(0);
    }

    public void setEnergy(int energy){
        this.energy = energy;
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