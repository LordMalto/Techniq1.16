package com.entisy.techniq.core.init;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class InitCapabilities {

    @CapabilityInject(IFluidHandler.class)
    public static final Capability<IFluidHandler> FLUID_CAPABILITY = null;


    public static void registerCapabilities() {
        
    }
}
