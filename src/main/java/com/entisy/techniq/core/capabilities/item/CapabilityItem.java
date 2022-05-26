package com.entisy.techniq.core.capabilities.item;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityItem
{
    @CapabilityInject(IItemStorage.class)
    public static Capability<IItemStorage> ITEM = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IItemStorage.class, new Capability.IStorage<IItemStorage>() {
                    @Override
                    public INBT writeNBT(Capability<IItemStorage> capability, IItemStorage instance, Direction side) {
                        return IntNBT.valueOf(instance.getItemStored());
                    }

                    @Override
                    public void readNBT(Capability<IItemStorage> capability, IItemStorage instance, Direction side, INBT nbt) {
                        if (!(instance instanceof ItemStorage))
                            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                        ((ItemStorage)instance).item = ((IntNBT)nbt).getAsInt();
                    }
                },
                () -> new ItemStorage(1000));
    }
}
