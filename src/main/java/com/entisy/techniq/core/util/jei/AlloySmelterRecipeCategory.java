package com.entisy.techniq.core.util.jei;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.recipe.alloySmelter.AlloySmelterRecipe;
import com.entisy.techniq.core.init.BlockInit;
import com.mojang.blaze3d.matrix.MatrixStack;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AlloySmelterRecipeCategory implements IRecipeCategory<AlloySmelterRecipe>{
	
	public static final ResourceLocation UID = new ResourceLocation(Techniq.MOD_ID, "alloy_smelter");
	public static final ResourceLocation TEXTURE = new ResourceLocation(Techniq.MOD_ID, "textures/gui/alloy_smelter.png");
	
	private final IDrawable background;
	private final IDrawable icon;
	private final IDrawableStatic arrow;
	
	public AlloySmelterRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 80);
		this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.ALLOY_SMELTER.get()));
		this.arrow = helper.createDrawable(TEXTURE, 0, 166, 22, 16);
	}
	
	@Override
	public ResourceLocation getUid() {
		return UID;
	}

	@Override
	public Class<? extends AlloySmelterRecipe> getRecipeClass() {
		return AlloySmelterRecipe.class;
	}

	@Override
	public String getTitle() {
		return BlockInit.ALLOY_SMELTER.get().getName().getString();
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setIngredients(AlloySmelterRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AlloySmelterRecipe recipe, IIngredients ingredients) {
		recipeLayout.getItemStacks().init(0, true, 55, 34);
		recipeLayout.getItemStacks().init(1, true, 115, 34);
		recipeLayout.getItemStacks().init(2, false, 115, 34);
		recipeLayout.getItemStacks().set(ingredients);
	}
	
	@Override
	public void draw(AlloySmelterRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		arrow.draw(matrixStack, 80, 35);
	}
}
