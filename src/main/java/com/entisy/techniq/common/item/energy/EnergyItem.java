package com.entisy.techniq.common.item.energy;

import com.entisy.techniq.core.tab.TechniqTab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyItem extends Item implements IEnergyItemHandler {

    public int currentEnergy, capacity;
    public LazyOptional<IEnergyStorage> energy;
    public EnergyItemBase energyStorage;

    public EnergyItem(int capacity) {
        super(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB));
        this.capacity = capacity;
        energyStorage = new EnergyItemBase(100, 1, 1);
        energy = LazyOptional.of(() -> energyStorage);
        energy.ifPresent(iEnergyStorage -> {
            currentEnergy = energyStorage.getEnergyStored();
        });
    }

    public void receiveEnergy(int amount, ItemStack stack) {
        stack.setDamageValue(getDamage(stack) - amount);
        energy.ifPresent(iEnergyStorage -> {
            energyStorage.setEnergy(currentEnergy + amount);
            currentEnergy = energyStorage.getEnergyStored();
        });
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

    public void extractEnergy(int amount, ItemStack stack) {
        stack.setDamageValue(stack.getDamageValue() + amount);
        energy.ifPresent(iEnergyStorage -> {
            energyStorage.setEnergy(currentEnergy - amount);
            currentEnergy = energyStorage.getEnergyStored();
        });
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
        return false;
    }

    @Override
    public EnergyItemBase getEnergyImpl() {
        return energyStorage;
    }

    public boolean isChargable() {
        return false;
    }
}
