package com.entisy.techniq.common.tileentity;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.AlloySmelterBlock;
import com.entisy.techniq.common.block.BatteryBlock;
import com.entisy.techniq.common.block.FurnaceGeneratorBlock;
import com.entisy.techniq.common.container.BatteryContainer;
import com.entisy.techniq.core.energy.EnergyStorageImpl;
import com.entisy.techniq.core.energy.IEnergyHandler;
import com.entisy.techniq.core.init.BlockInit;
import com.entisy.techniq.core.init.TileEntityTypesInit;
import com.entisy.techniq.core.util.EnergyUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class BatteryTileEntity extends MachineTileEntity implements ITickableTileEntity, INamedContainerProvider, IEnergyHandler {

    private static final int MAX_ENERGY_WORKING_TICK = 200;

    public BatteryTileEntity(TileEntityType<?> type) {
        super(0, 500, 0, type);
    }

    public BatteryTileEntity() {
        this(TileEntityTypesInit.BATTERY_TILE_ENTITY.get());
    }

    @Nullable
    @Override
    public Container createMenu(final int windowId, final PlayerInventory inv, final PlayerEntity player) {
        return new BatteryContainer(windowId, inv, this);
    }

    @Override
    public void tick() {
        boolean dirty = false;
        if (level != null && !level.isClientSide) {
            if(currentEnergy < maxEnergy) {
                if (currentSmeltTime != MAX_ENERGY_WORKING_TICK) {
                    level.setBlockAndUpdate(getBlockPos(), getBlockState());
                    currentSmeltTime++;
                    dirty = true;
                } else {
                    energy.ifPresent(iEnergyStorage -> {
                        currentEnergy = iEnergyStorage.getEnergyStored();
                    });
                    level.setBlockAndUpdate(getBlockPos(), getBlockState());
                    currentSmeltTime = 0;
                    dirty = true;
                }
            } else {
                level.setBlockAndUpdate(getBlockPos(), getBlockState());
                currentSmeltTime = 0;
                dirty = true;
            }
        }
        if (dirty) {
            setChanged();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    public void setCustomName(ITextComponent name) {
        this.name = name;
    }

    public ITextComponent getName() {
        return name != null ? name : getDefaultName();
    }

    public ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + Techniq.MOD_ID + ".battery");
    }

    @Override
    public ITextComponent getDisplayName() {
        return getName();
    }

    @Override
    public EnergyStorageImpl getEnergyImpl() {
        return energyStorage;
    }
}
