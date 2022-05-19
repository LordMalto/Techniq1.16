package com.entisy.techniq.core.util.jei;

import java.util.Objects;
import java.util.stream.Collectors;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.recipe.electricalFurnace.ElectricalFurnaceRecipe;
import com.entisy.techniq.common.recipe.metalPress.MetalPressRecipe;
import com.entisy.techniq.core.init.BlockInit;
import com.entisy.techniq.core.init.RecipeSerializerInit;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(Techniq.MOD_ID, "jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new MetalPressRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new ElectricalFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new AlloySmelterRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(BlockInit.ELECTRICAL_FURNACE.get()),
				ElectricalFurnaceRecipeCategory.UID);
		registration.addRecipeCatalyst(new ItemStack(BlockInit.METAL_PRESS.get()),
				MetalPressRecipeCategory.UID);
	}

	@SuppressWarnings("resource")
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
		registration.addRecipes(
				recipeManager.getAllRecipesFor(RecipeSerializerInit.METAL_PRESS_TYPE).stream()
						.filter(recipe -> recipe instanceof MetalPressRecipe).collect(Collectors.toList()),
				MetalPressRecipeCategory.UID);
		registration.addRecipes(
				recipeManager.getAllRecipesFor(RecipeSerializerInit.ELECTRICAL_FURNACE_TYPE).stream()
						.filter(recipe -> recipe instanceof ElectricalFurnaceRecipe).collect(Collectors.toList()),
				ElectricalFurnaceRecipeCategory.UID);
	}
}
