package com.entisy.techniq.core.util;

import com.entisy.techniq.core.capabilities.fluid.CapabilityFluid;
import com.entisy.techniq.core.capabilities.fluid.FluidStorage;
import com.entisy.techniq.core.capabilities.fluid.IFluidHandler;
import com.entisy.techniq.core.capabilities.fluid.IFluidStorage;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class FluidUtils {
    private FluidUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static void trySendToNeighbors(IBlockReader world, BlockPos pos, IFluidHandler fluidHandler, int maxSend) {
        for (Direction side : Direction.values()) {
            if (fluidHandler.getFluidStored() == 0) {
                return;
            }
            trySendTo(world, pos, fluidHandler, maxSend, side);
        }
    }

    public static void trySendTo(IBlockReader world, BlockPos pos, IFluidHandler fluidHandler, int maxSend, Direction side) {
        TileEntity tileEntity = world.getBlockEntity(pos.relative(side));
        if (tileEntity != null) {
            IFluidStorage fluid = fluidHandler.getFluid(side).orElse(new FluidStorage(0));
            tileEntity.getCapability(CapabilityFluid.FLUID, side.getOpposite()).ifPresent(other -> trySendFluid(maxSend, fluid, other));
        }
    }

    private static void trySendFluid(int maxSend, IFluidStorage fluid, IFluidStorage other) {
        if (other.canReceive()) {
            int toSend = fluid.extractFluid(maxSend, true);
            int sent = other.receiveFluid(toSend, false);
            if (sent > 0) {
                fluid.extractFluid(sent, false);
            }
        }
    }

    /**
     * Gets the fluid capability for the block at the given position. If it does not have an fluid
     * capability, or the block is not a tile entity, this returns null.
     *
     * @param world The world
     * @param pos   The position to check
     * @return The fluid capability, or null if not present
     */
    @SuppressWarnings("ConstantConditions")
    @Nullable
    public static IFluidStorage getFluid(IWorldReader world, BlockPos pos) {
        if (!world.isAreaLoaded(pos, 1)) return null;
        TileEntity tileEntity = world.getBlockEntity(pos);
        return tileEntity != null ? tileEntity.getCapability(CapabilityFluid.FLUID).orElse(null) : null;
    }

    /**
     * Gets the fluid capability of the object (item, etc), or null if it does not have one.
     *
     * @param provider The capability provider
     * @return The fluid capability, or null if not present
     */
    @SuppressWarnings("ConstantConditions")
    @Nullable
    public static IFluidStorage getFluid(ICapabilityProvider provider) {
        return provider.getCapability(CapabilityFluid.FLUID).orElse(null);
    }

    /**
     * Gets the fluid capability of the object (item, etc), or null if it does not have one. Tries
     * to get the capability for the given side first, then null side.
     *
     * @param provider The capability provider
     * @param side     The side being accessed
     * @return The fluid capability, or null if not present
     */
    @SuppressWarnings("ConstantConditions")
    @Nullable
    public static IFluidStorage getFluidFromSideOrNull(ICapabilityProvider provider, Direction side) {
        return provider.getCapability(CapabilityFluid.FLUID, side).orElse(provider.getCapability(CapabilityFluid.FLUID).orElse(null));
    }

}
