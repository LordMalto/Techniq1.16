package com.entisy.techniq.common.block.cable.fluidCable;

import com.entisy.techniq.core.capabilities.fluid.CapabilityFluid;
import com.entisy.techniq.core.init.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidCableTileEntity extends TileEntity {

    int fluidStored;

    public FluidCableTileEntity() {
        super(ModTileEntityTypes.CABLE_TILE_ENTITY.get());
    }

    public String getConduitNetworkData() {
        if (level == null) return "world is null";

        FluidCableNetwork net = FluidCableNetworkManager.get(level, worldPosition);
        return net != null ? net.toString() : "null";
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        this.fluidStored = compound.getInt("FluidStored");
        super.load(state, compound);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.putInt("FluidStored", fluidStored);
        return super.save(compound);
    }

    @Override
    public void setRemoved() {
        if (level != null) {
            FluidCableNetworkManager.invalidateNetwork(level, worldPosition);
        }
        super.setRemoved();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (level != null && !remove && cap == CapabilityFluid.FLUID && side != null) {
            LazyOptional<FluidCableNetwork> networkOptional = FluidCableNetworkManager.getLazy(level, worldPosition);
            if (networkOptional.isPresent()) {
                return networkOptional.orElseThrow(IllegalStateException::new).getConnection(worldPosition, side).getLazyOptional().cast();
            }
        }
        return LazyOptional.empty();
    }
}
