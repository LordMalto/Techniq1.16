package com.entisy.techniq.core.capabilities.fluid;

public interface IFluidStorage {
    /**
     * Adds fluid to the storage. Returns quantity of fluid that was accepted.
     *
     * @param maxReceive Maximum amount of fluid to be inserted.
     * @param simulate   If TRUE, the insertion will only be simulated.
     * @return Amount of fluid that was (or would have been, if simulated) accepted by the storage.
     */
    int receiveFluid(int maxReceive, boolean simulate);

    /**
     * Removes fluid from the storage. Returns quantity of fluid that was removed.
     *
     * @param maxExtract Maximum amount of fluid to be extracted.
     * @param simulate   If TRUE, the extraction will only be simulated.
     * @return Amount of fluid that was (or would have been, if simulated) extracted from the storage.
     */
    int extractFluid(int maxExtract, boolean simulate);

    /**
     * Returns the amount of fluid currently stored.
     */
    int getFluidStored();

    /**
     * Returns the maximum amount of fluid that can be stored.
     */
    int getMaxFluidStored();

    /**
     * Returns if this storage can have fluid extracted.
     * If this is false, then any calls to extractfluid will return 0.
     */
    boolean canExtract();

    /**
     * Used to determine if this storage can receive fluid.
     * If this is false, then any calls to receivefluid will return 0.
     */
    boolean canReceive();

}
