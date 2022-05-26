package com.entisy.techniq.core.capabilities.fluid;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;

public class CapabilityFluid
{
    @CapabilityInject(IFluidStorage.class)
    public static Capability<IFluidStorage> FLUID = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IFluidStorage.class, new Capability.IStorage<IFluidStorage>() {
                    @Override
                    public INBT writeNBT(Capability<IFluidStorage> capability, IFluidStorage instance, Direction side) {
                        return IntNBT.valueOf(instance.getFluidStored());
                    }

                    @Override
                    public void readNBT(Capability<IFluidStorage> capability, IFluidStorage instance, Direction side, INBT nbt) {
                        if (!(instance instanceof FluidStorage))
                            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                        ((FluidStorage)instance).fluid = ((IntNBT)nbt).getAsInt();
                    }
                },
                () -> new FluidStorage(1000));
    }
}
