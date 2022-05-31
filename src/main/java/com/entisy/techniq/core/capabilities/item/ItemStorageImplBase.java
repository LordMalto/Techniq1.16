package com.entisy.techniq.core.capabilities.item;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemStorageImplBase extends ItemStorage implements ICapabilityProvider {
    private final LazyOptional<ItemStorageImplBase> lazy;

    public ItemStorageImplBase(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract, 0);
        this.lazy = LazyOptional.of(() -> this);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return CapabilityItem.ITEM.orEmpty(cap, lazy.cast());
    }

    public void invalidate() {
        this.lazy.invalidate();
    }
}
