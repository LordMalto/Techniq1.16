package com.entisy.techniq.core.capabilities.item;

public interface IItemStorage
{
    /**
     * Adds item to the storage. Returns quantity of item that was accepted.
     *
     * @param maxReceive
     *            Maximum amount of item to be inserted.
     * @param simulate
     *            If TRUE, the insertion will only be simulated.
     * @return Amount of item that was (or would have been, if simulated) accepted by the storage.
     */
    int receiveItem(int maxReceive, boolean simulate);

    /**
     * Removes item from the storage. Returns quantity of item that was removed.
     *
     * @param maxExtract
     *            Maximum amount of item to be extracted.
     * @param simulate
     *            If TRUE, the extraction will only be simulated.
     * @return Amount of item that was (or would have been, if simulated) extracted from the storage.
     */
    int extractItem(int maxExtract, boolean simulate);

    /**
     * Returns the amount of item currently stored.
     */
    int getItemStored();

    /**
     * Returns the maximum amount of item that can be stored.
     */
    int getMaxItemStored();

    /**
     * Returns if this storage can have item extracted.
     * If this is false, then any calls to extractitem will return 0.
     */
    boolean canExtract();

    /**
     * Used to determine if this storage can receive item.
     * If this is false, then any calls to receiveitem will return 0.
     */
    boolean canReceive();

}
