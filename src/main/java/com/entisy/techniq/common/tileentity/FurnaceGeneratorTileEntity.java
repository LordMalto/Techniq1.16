package com.entisy.techniq.common.tileentity;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.container.FurnaceGeneratorContainer;
import com.entisy.techniq.common.itemHandlers.FurnaceGeneratorItemHandler;
import com.entisy.techniq.core.energy.EnergyStorageImpl;
import com.entisy.techniq.core.init.TileEntityTypesInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class FurnaceGeneratorTileEntity extends MachineTileEntity implements ITickableTileEntity, INamedContainerProvider {

    public static final int slots = 1;
    private ITextComponent name;
    public int currentSmeltTime;
    public final int maxSmeltTime = 120;
    private FurnaceGeneratorItemHandler inventory;

    public int currentEnergy = 0;
    public static final int maxEnergy = 25000;
    public static final int maxEnergyReceive = 0;
    public static final int maxEnergyExtract = 500;

    private final EnergyStorageImpl energyStorage;

    private final LazyOptional<IEnergyStorage> energy;

    public FurnaceGeneratorTileEntity(TileEntityType<?> type) {
        super(type);
        inventory = new FurnaceGeneratorItemHandler(slots);
        energyStorage = createEnergy(maxEnergy);
        energy = LazyOptional.of(() -> energyStorage);
    }

    public FurnaceGeneratorTileEntity() {
        this(TileEntityTypesInit.FURNACE_GENERATOR_TILE_ENTITY.get());
    }

    @Override
    public Container createMenu(final int windowId, final PlayerInventory inv, final PlayerEntity player) {
        return new FurnaceGeneratorContainer(windowId, inv, this);
    }

    @Override
    public void tick() {
//        boolean dirty = false;
//        if (level != null && !level.isClientSide()) {
//            if (recipe != null) {
//                if (currentEnergy >= recipe.getRequiredEnergy()) {
//                    if (currentSmeltTime != maxSmeltTime) {
//                        if ((inventory.getStackInSlot(2).sameItem(recipe.getResultItem()) || inventory.getStackInSlot(2).getItem() == Items.AIR) && inventory.getStackInSlot(2).getCount() < 64) {
//                            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(AlloySmelterBlock.LIT, true));
//                            currentSmeltTime++;
//                            dirty = true;
//                        }
//                    } else {
//                        energy.ifPresent(iEnergyStorage -> {
//                            ((EnergyStorageImpl)iEnergyStorage).setEnergyDirectly(iEnergyStorage.getEnergyStored() - recipe.getRequiredEnergy());
//                            currentEnergy = iEnergyStorage.getEnergyStored();
//                        });
//
//                        level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(AlloySmelterBlock.LIT, false));
//                        currentSmeltTime = 0;
//
//                        //Recipe Handling
//                        ItemStack output = recipe.getResultItem();
//                        inventory.insertItem(2, output.copy(), false);
//
//                        inventory.shrink(0, recipe.getCount(inventory.getItem(0)));
//                        inventory.shrink(1, recipe.getCount(inventory.getItem(1)));
//                        dirty = true;
//                    }
//                }
//            } else {
//                level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(AlloySmelterBlock.LIT, false));
//                currentSmeltTime = 0;
//                dirty = true;
//            }
//        }
//        if (dirty) {
//            setChanged();
//            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
//        }
    }

    public void setCustomName(ITextComponent name) {
        this.name = name;
    }

    public ITextComponent getName() {
        return name != null ? name : getDefaultName();
    }

    public ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + Techniq.MOD_ID + ".furnace_generator");
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
        NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(this.inventory.getSlots(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, inv);
        inventory.setNonNullList(inv);
        currentSmeltTime = nbt.getInt("CurrentSmeltTime");
        currentEnergy = nbt.getInt("CurrentEnergy");
        energyStorage.setEnergyDirectly(currentEnergy); //Original
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        if (name != null) {
            nbt.putString("CustomName", ITextComponent.Serializer.toJson(name));
        }
        ItemStackHelper.saveAllItems(nbt, inventory.toNonNullList());
        nbt.putInt("CurrentSmeltTime", currentSmeltTime);
        energy.ifPresent(iEnergyStorage -> {
            nbt.putInt("CurrentEnergy", iEnergyStorage.getEnergyStored());
        });
        return nbt;
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

    public IItemHandler getInventory() {
        return inventory;
    }


    private EnergyStorageImpl createEnergy(int capacity) {
        return new EnergyStorageImpl(capacity, maxEnergyReceive, maxEnergyExtract, this);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        energy.invalidate();
    }
}