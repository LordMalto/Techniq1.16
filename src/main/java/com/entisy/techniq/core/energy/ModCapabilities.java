package com.entisy.techniq.core.energy;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class ModCapabilities {

    @CapabilityInject(IFluidHandler.class)
    public static final Capability<IFluidHandler> FLUID_CAPABILITY = null;


    public static void registerCapabilities() {
    }
}
