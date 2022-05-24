package com.entisy.techniq.common.block.alloySmelter;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.alloySmelter.recipe.AlloySmelterRecipe;
import com.entisy.techniq.common.block.MachineTileEntity;
import com.entisy.techniq.core.energy.EnergyStorageImpl;
import com.entisy.techniq.core.energy.IEnergyHandler;
import com.entisy.techniq.core.init.ModRecipes;
import com.entisy.techniq.core.init.ModTileEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nullable;
import java.util.Set;

public class AlloySmelterTileEntity extends MachineTileEntity implements ITickableTileEntity, INamedContainerProvider, IEnergyHandler {

    public AlloySmelterTileEntity(TileEntityType<?> type) {
        super(3, 200, 0, type);
    }

    public AlloySmelterTileEntity() {
        this(ModTileEntityTypes.ALLOY_SMELTER_TILE_ENTITY.get());
    }

    @Override
    public Container createMenu(final int windowId, final PlayerInventory inv, final PlayerEntity player) {
        return new AlloySmelterContainer(windowId, inv, this);
    }

    @Override
    public void tick() {
        boolean dirty = false;
        if (level != null && !level.isClientSide()) {
            if (getRecipe() != null) {
                if (currentEnergy >= getRecipe().getRequiredEnergy()) {
                    if (currentSmeltTime != getRecipe().getSmeltTime()) {
                        if ((inventory.getStackInSlot(2).sameItem(getRecipe().getResultItem()) || inventory.getStackInSlot(2).getItem() == Items.AIR) && inventory.getStackInSlot(2).getCount() < 64) {
                            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(AlloySmelterBlock.LIT, true));
                            currentSmeltTime++;
                            dirty = true;
                        }
                    } else {
                        energy.ifPresent(iEnergyStorage -> {
                            ((EnergyStorageImpl)iEnergyStorage).setEnergyDirectly(iEnergyStorage.getEnergyStored() - getRecipe().getRequiredEnergy());
                            currentEnergy = iEnergyStorage.getEnergyStored();
                        });

                        level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(AlloySmelterBlock.LIT, false));
                        currentSmeltTime = 0;

                        //Recipe Handling
                        ItemStack output = getRecipe().getResultItem();
                        inventory.insertItem(2, output.copy(), false);

                        inventory.shrink(0, getRecipe().getCount(inventory.getItem(0)));
                        inventory.shrink(1, getRecipe().getCount(inventory.getItem(1)));
                        dirty = true;
                    }
                }
            } else {
                level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(AlloySmelterBlock.LIT, false));
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
        return new TranslationTextComponent("container." + Techniq.MOD_ID + ".alloy_smelter");
    }

    @Override
    public ITextComponent getDisplayName() {
        return getName();
    }

    @Nullable
    private AlloySmelterRecipe getRecipe(ItemStack stack) {
        if (stack == null) {
            return null;
        }

        Set<IRecipe<?>> recipes = findRecipesByType(ModRecipes.ALLOY_SMELTER_TYPE, level);
        for (IRecipe<?> iRecipe : recipes) {
            AlloySmelterRecipe recipe = (AlloySmelterRecipe) iRecipe;
            if (recipe.matches(new RecipeWrapper(inventory), level)) {
                return recipe;
            }
        }

        return null;
    }

    public int getMaxSmeltTime() {
        return getRecipe().getSmeltTime();
    }

    public AlloySmelterRecipe getRecipe() {
        return getRecipe(getInventory().getItem(0)) != null ? getRecipe(getInventory().getItem(0)) : getRecipe(getInventory().getItem(1));
    }

    @Override
    public EnergyStorageImpl getEnergyImpl() {
        return energyStorage;
    }
}
