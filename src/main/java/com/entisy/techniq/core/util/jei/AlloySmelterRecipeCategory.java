package com.entisy.techniq.core.util.jei;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.alloySmelter.recipe.AlloySmelterRecipe;
import com.entisy.techniq.core.init.ModBlocks;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AlloySmelterRecipeCategory implements IRecipeCategory<AlloySmelterRecipe>{
	
	public static final ResourceLocation UID = new ResourceLocation(Techniq.MOD_ID, "alloy_smelter");
	public static final ResourceLocation TEXTURE = new ResourceLocation(Techniq.MOD_ID, "textures/block/alloy_smelter/jei.png");
	
	private final IDrawable background;
	private final IDrawable icon;
	
	public AlloySmelterRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 114, 67);
		this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.ALLOY_SMELTER.get()));
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
		return ModBlocks.ALLOY_SMELTER.get().getName().getString();
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
		recipeLayout.getItemStacks().init(0, true, 11, 11);
		recipeLayout.getItemStacks().init(1, true, 11, 38);
		recipeLayout.getItemStacks().init(2, false, 81, 24);
		recipeLayout.getItemStacks().set(ingredients);
	}
	
	@Override
	public void draw(AlloySmelterRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		FontRenderer font = Minecraft.getInstance().font;
		font.draw(matrixStack, recipe.getRequiredEnergy() + "RF", (float) 77, (float) 7, 4210752);
		font.draw(matrixStack, recipe.getSmeltTimeInSeconds() + "s", (float) 77, (float) 49, 4210752);
	}
}
