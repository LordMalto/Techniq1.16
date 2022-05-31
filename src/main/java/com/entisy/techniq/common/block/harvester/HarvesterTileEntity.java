package com.entisy.techniq.common.block.harvester;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.MachineTileEntity;
import com.entisy.techniq.core.capabilities.energy.EnergyStorageImpl;
import com.entisy.techniq.core.capabilities.energy.IEnergyHandler;
import com.entisy.techniq.core.init.ModTileEntityTypes;
import com.entisy.techniq.core.util.Pair;
import com.entisy.techniq.core.util.SimpleList;
import com.entisy.techniq.core.util.SimpleMap;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Constants;

public class HarvesterTileEntity extends MachineTileEntity implements ITickableTileEntity, INamedContainerProvider, IEnergyHandler {

    private int workTime = 20;
    private int radius = 6;
    private Direction direction;

    public HarvesterTileEntity() {
        super(9, 500, 0, ModTileEntityTypes.HARVESTER_TILE_ENTITY_TYPE.get());
    }

    @Override
    public Container createMenu(final int windowId, final PlayerInventory inv, final PlayerEntity player) {
        return new HarvesterContainer(windowId, inv, this);
    }

    @Override
    public void tick() {
        boolean dirty = false;
        if (level != null && !level.isClientSide()) {
            energy.ifPresent(iEnergyStorage -> {
                currentEnergy = iEnergyStorage.getEnergyStored();
            });
            if (currentEnergy >= getRequiredEnergy()) {
                if (currentSmeltTime != workTime) {
                    currentSmeltTime++;
                    dirty = true;
                } else {
                    dirty = harvest();
                }
            }
        }
        if (dirty) {
            setChanged();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    private boolean harvest() {
        for (int i = 0; i < getHarvestableBlocks().size(); i++) {
            for (BlockPos pos : getHarvestableBlocks().list()) {
                CropsBlock crop = (CropsBlock) level.getBlockState(pos).getBlock();
                SimpleMap<Pair<Boolean, Integer>, ItemStack> map = tryMoveStack(6);
                for (int j = 0; j < map.size(); j++) {
                    if (map.getKeys().get(i).getKey()) {
                        inventory.insertItem(map.getKeys().get(i).getValue(), map.getValues().get(i).copy(), false);
                    }
                }
                energy.ifPresent(iEnergyStorage -> {
                    energyStorage.setEnergyDirectly(currentEnergy - getRequiredEnergy());
                    currentEnergy = energyStorage.getEnergyStored();
                });
                currentSmeltTime = 0;
                level.destroyBlock(pos, false);
                level.setBlock(pos, crop.defaultBlockState().setValue(CropsBlock.AGE, 0), 0);
                return true;

            }
        }
        return false;
    }

    private SimpleList<ItemStack> getResultItems(BlockPos pos) {
        CropsBlock block = (CropsBlock) level.getBlockState(pos).getBlock();
        SimpleList<ItemStack> ret = new SimpleList<>();
        CropsBlock.getDrops(block.defaultBlockState(), (ServerWorld) level, pos, this)
                .forEach(i -> ret.append(i));
        return ret;
    }

    public void setCustomName(ITextComponent name) {
        this.name = name;
    }

    public ITextComponent getName() {
        return name != null ? name : getDefaultName();
    }

    public ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + Techniq.MOD_ID + ".harvester");
    }

    @Override
    public ITextComponent getDisplayName() {
        return getName();
    }

    private int getRequiredEnergy() {
        return 100;
    }

    private SimpleList<BlockPos> getHarvestableBlocks() {
        SimpleList<BlockPos> ret = new SimpleList<>();
        for (BlockPos pos : getBlocksInRange().list()) {
            System.out.println(1);
            Block block = getLevel().getBlockState(pos).getBlock();
            if (block instanceof CropsBlock) {
                System.out.println(2);
                if (((CropsBlock) block).isMaxAge(block.defaultBlockState())) {
                    System.out.println(3);
                    ret.append(pos);

                }
            }

        }
        return ret;
    }

    private SimpleList<BlockPos> getBlocksInRange() {
        SimpleList<BlockPos> ret = new SimpleList<>();
        BlockPos harvester = getBlockPos();
        System.out.println(radius);
        for (int x = -radius; x < radius+1; x++) {
            for (int z = -radius; z < radius+1; z++) {
                ret.append(harvester.offset(x, 0, z));
            }
        }
        return ret;
    }

    //TODO: getResultItem(Block block)

    private SimpleMap<Pair<Boolean, Integer>, ItemStack> tryMoveStack(int slots) {
        SimpleList<BlockPos> map = getHarvestableBlocks();
        SimpleMap<Pair<Boolean, Integer>, ItemStack> ret = new SimpleMap<>();
        for (int i = 0; i < slots; i++) {
            for (int j = 0; j < map.size(); j++) {
                SimpleList<ItemStack> list = getResultItems(map.get(i));
                for (ItemStack stack : list.get()) {
                    if ((inventory.getItem(i).sameItem(stack) && inventory.getItem(i).getCount() < 64) || (inventory.getItem(i).getItem() == Items.AIR) || inventory.getItem(i).getStack() == ItemStack.EMPTY) {
                        ret.append(new Pair<>(true, i), list.get(i));
                    }
                }

            }
        }
        return ret;
    }

    @Override
    public EnergyStorageImpl getEnergyImpl() {
        return energyStorage;
    }
}
