package com.entisy.techniq.common.block.cable.itemCable;

import com.entisy.techniq.core.capabilities.item.CapabilityItem;
import com.entisy.techniq.core.init.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemCableTileEntity extends TileEntity {

    int itemStored;

    public ItemCableTileEntity() {
        super(ModTileEntityTypes.CABLE_TILE_ENTITY.get());
    }

    public String getConduitNetworkData() {
        if (level == null) return "world is null";

        ItemCableNetwork net = ItemCableNetworkManager.get(level, worldPosition);
        return net != null ? net.toString() : "null";
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        this.itemStored = compound.getInt("ItemStored");
        super.load(state, compound);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.putInt("ItemStored", itemStored);
        return super.save(compound);
    }

    @Override
    public void setRemoved() {
        if (level != null) {
            ItemCableNetworkManager.invalidateNetwork(level, worldPosition);
        }
        super.setRemoved();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (level != null && !remove && cap == CapabilityItem.ITEM && side != null) {
            LazyOptional<ItemCableNetwork> networkOptional = ItemCableNetworkManager.getLazy(level, worldPosition);
            if (networkOptional.isPresent()) {
                return networkOptional.orElseThrow(IllegalStateException::new).getConnection(worldPosition, side).getLazyOptional().cast();
            }
        }
        return LazyOptional.empty();
    }
}
