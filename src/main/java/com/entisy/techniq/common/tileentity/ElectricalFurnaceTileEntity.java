package com.entisy.techniq.common.tileentity;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.AlloySmelterBlock;
import com.entisy.techniq.common.container.ElectricalFurnaceContainer;
import com.entisy.techniq.common.recipe.electricalFurnace.ElectricalFurnaceRecipe;
import com.entisy.techniq.core.energy.EnergyStorageImpl;
import com.entisy.techniq.core.init.RecipeSerializerInit;
import com.entisy.techniq.core.init.TileEntityTypesInit;
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

public class ElectricalFurnaceTileEntity extends MachineTileEntity implements ITickableTileEntity, INamedContainerProvider {

    public ElectricalFurnaceTileEntity(TileEntityType<?> type) {
        super(2, 200, 0, type);
    }

    public ElectricalFurnaceTileEntity() {
        this(TileEntityTypesInit.ELECTRICAL_FURNACE_TILE_ENTITY.get());
    }

    @Override
    public Container createMenu(final int windowId, final PlayerInventory inv, final PlayerEntity player) {
        return new ElectricalFurnaceContainer(windowId, inv, this);
    }

    @Override
    public void tick() {
        boolean dirty = false;
        if (level != null && !level.isClientSide()) {
            ElectricalFurnaceRecipe recipe = getRecipe(inventory.getItem(0));

            if (recipe != null) {
                if (currentEnergy >= recipe.getRequiredEnergy()) {
                    if (currentSmeltTime != maxSmeltTime) {
                        if ((inventory.getStackInSlot(1).sameItem(recipe.getResultItem()) || inventory.getStackInSlot(1).getItem() == Items.AIR) && inventory.getStackInSlot(1).getCount() < 64) {
                            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(AlloySmelterBlock.LIT, true));
                            currentSmeltTime++;
                            dirty = true;
                        }
                    } else {
                        energy.ifPresent(iEnergyStorage -> {
                            ((EnergyStorageImpl) iEnergyStorage).setEnergyDirectly(iEnergyStorage.getEnergyStored() - recipe.getRequiredEnergy());
                            currentEnergy = iEnergyStorage.getEnergyStored();
                        });
                        level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(AlloySmelterBlock.LIT, false));
                        currentSmeltTime = 0;
                        ItemStack output = recipe.getResultItem();
                        inventory.insertItem(1, output.copy(), false);
                        inventory.shrink(0, recipe.getCount(inventory.getItem(0)));
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
        return new TranslationTextComponent("container." + Techniq.MOD_ID + ".electrical_furnace");
    }

    @Override
    public ITextComponent getDisplayName() {
        return getName();
    }

    @Nullable
    private ElectricalFurnaceRecipe getRecipe(ItemStack stack) {
        if (stack == null) {
            return null;
        }

        Set<IRecipe<?>> recipes = findRecipesByType(RecipeSerializerInit.ELECTRICAL_FURNACE_TYPE, level);
        for (IRecipe<?> iRecipe : recipes) {
            ElectricalFurnaceRecipe recipe = (ElectricalFurnaceRecipe) iRecipe;
            if (recipe.matches(new RecipeWrapper(inventory), level)) {
                return recipe;
            }
        }
        return null;
    }
}
