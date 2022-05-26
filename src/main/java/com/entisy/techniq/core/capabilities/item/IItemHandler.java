package com.entisy.techniq.core.capabilities.item;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

public interface IItemHandler {
    ItemStorageImpl getItemImpl();

    default LazyOptional<IItemStorage> getItem(@Nullable Direction side) {
        return getItemImpl().getCapability(CapabilityItem.ITEM, side);
    }

    default int getItemStored() {
        IItemStorage item = getItem(null).orElse(new ItemStorage(25_000));
        return item.getItemStored();
    }

    default int getMaxItemStored() {
        IItemStorage item = getItem(null).orElse(new ItemStorage(100_000));
        return item.getMaxItemStored();
    }

    default void setItemStoredDirectly(int value) {
        getItem(null).ifPresent(e -> {
            if (e instanceof ItemStorageImpl) {
                ((ItemStorageImpl) e).setItemDirectly(value);
            }
        });
    }

    default void readItem(CompoundNBT tags) {
        setItemStoredDirectly(tags.getInt("Item"));
    }

    default void writeItem(CompoundNBT tags) {
        tags.putInt("Item", getItemStored());
    }
}
