package com.entisy.techniq.core.capabilities.item;

public class ItemStorage implements IItemStorage {
    protected int item;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public ItemStorage(int capacity) {
        this(capacity, capacity, capacity, 0);
    }

    public ItemStorage(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public ItemStorage(int capacity, int maxReceive, int maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public ItemStorage(int capacity, int maxReceive, int maxExtract, int item) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.item = Math.max(0, Math.min(capacity, item));
    }

    @Override
    public int receiveItem(int maxReceive, boolean simulate) {
        if (!canReceive())
            return 0;

        int itemReceived = Math.min(capacity - item, Math.min(this.maxReceive, maxReceive));
        if (!simulate)
            item += itemReceived;
        return itemReceived;
    }

    @Override
    public int extractItem(int maxExtract, boolean simulate) {
        if (!canExtract())
            return 0;

        int itemExtracted = Math.min(item, Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            item -= itemExtracted;
        return itemExtracted;
    }

    @Override
    public int getItemStored() {
        return item;
    }

    @Override
    public int getMaxItemStored() {
        return capacity;
    }

    @Override
    public boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
        return this.maxReceive > 0;
    }
}