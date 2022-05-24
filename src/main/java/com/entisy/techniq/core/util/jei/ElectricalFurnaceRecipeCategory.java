package com.entisy.techniq.core.util.jei;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.electricalFurnace.recipe.ElectricalFurnaceRecipe;
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

public class ElectricalFurnaceRecipeCategory implements IRecipeCategory<ElectricalFurnaceRecipe> {

	public static final ResourceLocation UID = new ResourceLocation(Techniq.MOD_ID, "electrical_furnace");
	public static final ResourceLocation TEXTURE = new ResourceLocation(Techniq.MOD_ID, "textures/block/electrical_furnace/jei.png");

	private final IDrawable background;
	private final IDrawable icon;

	public ElectricalFurnaceRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 104, 48);
		this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.ELECTRICAL_FURNACE.get()));
	}

	@Override
	public ResourceLocation getUid() {
		return UID;
	}

	@Override
	public Class<? extends ElectricalFurnaceRecipe> getRecipeClass() {
		return ElectricalFurnaceRecipe.class;
	}

	@Override
	public String getTitle() {
		return ModBlocks.ELECTRICAL_FURNACE.get().getName().getString();
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
	public void setIngredients(ElectricalFurnaceRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, ElectricalFurnaceRecipe recipe, IIngredients ingredients) {
		recipeLayout.getItemStacks().init(0, true, 11, 15);
		recipeLayout.getItemStacks().init(1, false, 71, 15);
		recipeLayout.getItemStacks().set(ingredients);
	}

	@Override
	public void draw(ElectricalFurnaceRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
		FontRenderer font = Minecraft.getInstance().font;
		font.draw(matrixStack, recipe.getRequiredEnergy() + "RF", (float) 11, (float) 5, 4210752);
		font.draw(matrixStack, recipe.getSmeltTimeInSeconds() + "s", (float) 11, (float) 35, 4210752);
	}
}