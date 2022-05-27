package com.entisy.techniq.common.item;

import com.entisy.techniq.core.tab.TechniqTab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyItem extends Item implements IEnergyItemHandler {

    private ItemStack self;
    private int currentEnergy, capacity;
    private LazyOptional<IEnergyStorage> energy;
    private EnergyItemBase energyStorage;

    public EnergyItem(int capacity) {
        super(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB));
        this.capacity = capacity;
        energyStorage = new EnergyItemBase(100, 1, 1);
        energy = LazyOptional.of(() -> energyStorage);
        energy.ifPresent(iEnergyStorage -> {
            currentEnergy = energyStorage.getEnergyStored();
        });
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if (stack.hasTag()) {
            CompoundNBT nbt = stack.getTag();
            return ((capacity - nbt.getInt("EnergyStored")) / (double) capacity) / 2;
        } else {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("EnergyStored", currentEnergy);
            stack.setTag(nbt);
            return ((capacity - currentEnergy) / (double) capacity) / 2;
        }
    }

    public void receiveEnergy(ItemStack stack, int amount) {
        EnergyItem item = (EnergyItem) stack.getItem();
        item.energy.ifPresent(iEnergyStorage -> {
            energyStorage.setEnergy(currentEnergy + amount);
            currentEnergy = energyStorage.getEnergyStored();
        });
        stack.setDamageValue(getDamage(stack) - amount);

        if (stack.hasTag()) {
            CompoundNBT nbt = stack.getTag();
            if (nbt.contains("EnergyStored")) {
                nbt.putInt("EnergyStored", currentEnergy);
            } else {
                nbt.putInt("EnergyStored", currentEnergy);
            }
        } else {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("EnergyStored", currentEnergy);
            stack.setTag(nbt);
        }
        getDurabilityForDisplay(stack);
    }

    public void extractEnergy(ItemStack stack, int amount) {
        EnergyItem item = (EnergyItem) stack.getItem();
        item.energy.ifPresent(iEnergyStorage -> {
            energyStorage.setEnergy(currentEnergy - amount);
            currentEnergy = energyStorage.getEnergyStored();
        });
        stack.setDamageValue(stack.getDamageValue() + amount);

        if (stack.hasTag()) {
            CompoundNBT nbt = stack.getTag();
            if (nbt.contains("EnergyStored")) {
                nbt.putInt("EnergyStored", currentEnergy);
            } else {
                nbt.putInt("EnergyStored", currentEnergy);
            }
        } else {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("EnergyStored", currentEnergy);
            stack.setTag(nbt);
        }
        getDurabilityForDisplay(stack);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public EnergyItemBase getEnergyImpl() {
        return energyStorage;
    }
}
