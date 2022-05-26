package com.entisy.techniq.core.util.jei;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.metalPress.recipe.MetalPressRecipe;
import com.entisy.techniq.core.init.ModBlocks;
import com.entisy.techniq.core.util.jei.util.DrawHelper;
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
import net.minecraft.util.text.StringTextComponent;

public class MetalPressRecipeCategory extends DrawHelper implements IRecipeCategory<MetalPressRecipe> {
	
	public static final ResourceLocation UID = new ResourceLocation(Techniq.MOD_ID, "metal_press");
	public static final ResourceLocation TEXTURE = new ResourceLocation(Techniq.MOD_ID, "textures/block/metal_press/jei.png");
	
	private final IDrawable background;
	private final IDrawable icon;
	
	public MetalPressRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 134, 74);
		this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.METAL_PRESS.get()));
	}
	
	@Override
	public ResourceLocation getUid() {
		return UID;
	}

	@Override
	public Class<? extends MetalPressRecipe> getRecipeClass() {
		return MetalPressRecipe.class;
	}

	@Override
	public String getTitle() {
		return ModBlocks.METAL_PRESS.get().getName().getString();
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
	public void setIngredients(MetalPressRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, MetalPressRecipe recipe, IIngredients ingredients) {
		recipeLayout.getItemStacks().init(0, true, 11, 28);
		recipeLayout.getItemStacks().init(1, false, 71, 28);
		recipeLayout.getItemStacks().set(ingredients);
	}
	
	@Override
	public void draw(MetalPressRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		// Energy bar
		int pixel = recipe.getRequiredEnergy() * 50 / 10000;
		blit(matrixStack, 110, (50 - pixel) + 12, 134, (50 - pixel), 12, 50);
		// Energy tooltip
		if (mouseX >= 110 && mouseX < 110 + 12) {
			if (mouseY >= 12 && mouseY < 12 + 50) {
				// Energy bar
				blit(matrixStack, 110, (50 - pixel) + 12, 134, (50 - pixel), 12, 50);
				// Tooltip
				renderTooltip(matrixStack, new StringTextComponent(recipe.getRequiredEnergy() + "RF"), (int) mouseX, (int) mouseY);
			}
		}
		// Work Time
		font.draw(matrixStack, recipe.getSmeltTimeInSeconds() + "s", 12, 18, 4210752);
	}
}
