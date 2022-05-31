package com.entisy.techniq.core.util.jei;

import com.entisy.techniq.common.block.metalPress.MetalPressContainer;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;

import javax.annotation.Nullable;

public class MetalPressRecipeTransferHandler implements IRecipeTransferHandler<MetalPressContainer> {

    @Override
    public Class getContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public IRecipeTransferError transferRecipe(MetalPressContainer container, Object recipe, IRecipeLayout recipeLayout, PlayerEntity player, boolean maxTransfer, boolean doTransfer) {
        return IRecipeTransferHandler.super.transferRecipe(container, recipe, recipeLayout, player, maxTransfer, doTransfer);
    }
}
