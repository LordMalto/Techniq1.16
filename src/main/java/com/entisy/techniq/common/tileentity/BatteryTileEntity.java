package com.entisy.techniq.common.tileentity;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.container.BatteryContainer;
import com.entisy.techniq.core.energy.EnergyStorageImpl;
import com.entisy.techniq.core.init.TileEntityTypesInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class BatteryTileEntity extends MachineTileEntity implements ITickableTileEntity, INamedContainerProvider {


    private ITextComponent name;
    public int currentEnergy = 0;
    public static final int maxEnergy = 25000;
    public static final int maxEnergyReceive = 500;
    public static final int maxEnergyExtract = 500;

    private final EnergyStorageImpl energyStorage;

    private final LazyOptional<IEnergyStorage> energy;

    public BatteryTileEntity(TileEntityType<?> type) {
        super(type);
        energyStorage = createEnergy(maxEnergy);
        energy = LazyOptional.of(() -> energyStorage);
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

    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        save(nbt);
        return new SUpdateTileEntityPacket(getBlockPos(), 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager manager, SUpdateTileEntityPacket packet) {
        deserializeNBT(packet.getTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = new CompoundNBT();
        save(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT nbt) {
        deserializeNBT(nbt);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
        if (capability == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(capability, side);
    }


    private EnergyStorageImpl createEnergy(int capacity) {
        return new EnergyStorageImpl(capacity, maxEnergyReceive, maxEnergyExtract, this);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        energy.invalidate();
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

    @Nullable
    public ITextComponent getCustomName() {
        return name;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        if (nbt.contains("CustomName", Constants.NBT.TAG_STRING)) {
            name = ITextComponent.Serializer.fromJson(nbt.getString("CustomName"));
        }
        currentEnergy = nbt.getInt("CurrentEnergy");
        energyStorage.setEnergyDirectly(currentEnergy); //Original
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        if (name != null) {
            nbt.putString("CustomName", ITextComponent.Serializer.toJson(name));
        }
        energy.ifPresent(iEnergyStorage -> {
            nbt.putInt("CurrentEnergy", iEnergyStorage.getEnergyStored());
        });
        return nbt;
    }
}
