package com.entisy.techniq.core.util.jei;

import java.util.Objects;
import java.util.stream.Collectors;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.alloySmelter.recipe.AlloySmelterRecipe;
import com.entisy.techniq.common.block.electricalFurnace.recipe.ElectricalFurnaceRecipe;
import com.entisy.techniq.common.block.metalPress.recipe.MetalPressRecipe;
import com.entisy.techniq.core.init.ModBlocks;
import com.entisy.techniq.core.init.ModItems;
import com.entisy.techniq.core.init.ModRecipes;

import com.entisy.techniq.core.init.ModTags;
import jdk.nashorn.internal.ir.Block;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipe;
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

	//TODO
//	@Override
//	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
//		registration.addRecipeTransferHandler(new MetalPressRecipeTransferHandler(), MetalPressRecipeCategory.UID);
//	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTRICAL_FURNACE.get()),
				ElectricalFurnaceRecipeCategory.UID);
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.METAL_PRESS.get()),
				MetalPressRecipeCategory.UID);
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.ALLOY_SMELTER.get()),
				AlloySmelterRecipeCategory.UID);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
		IIngredientManager ingredientManager = registration.getIngredientManager();

		registration.addRecipes(
				recipeManager.getAllRecipesFor(ModRecipes.METAL_PRESS_TYPE).stream()
						.filter(recipe -> recipe instanceof MetalPressRecipe).collect(Collectors.toList()),
				MetalPressRecipeCategory.UID);
		// Electrical Furnace
		registration.addRecipes(
				recipeManager.getAllRecipesFor(ModRecipes.ELECTRICAL_FURNACE_TYPE)
						.stream().filter(recipe -> recipe instanceof ElectricalFurnaceRecipe).collect(Collectors.toList()),
				ElectricalFurnaceRecipeCategory.UID);
		// Alloy Smelter
		registration.addRecipes(
				recipeManager.getAllRecipesFor(ModRecipes.ALLOY_SMELTER_TYPE).stream()
						.filter(recipe -> recipe instanceof AlloySmelterRecipe).collect(Collectors.toList()),
				AlloySmelterRecipeCategory.UID);
		// Ore Miner
		registration.addIngredientInfo(
				new ItemStack(ModBlocks.SIMPLE_ORE_MINER.get()),
				ingredientManager.getIngredientType(ItemStack.class),
				"Place on top af any vanilla ore and provide with energy to mine some ore.");
		registration.addIngredientInfo(
				new ItemStack(ModBlocks.ADVANCED_ORE_MINER.get()),
				ingredientManager.getIngredientType(ItemStack.class),
				"Place on top af any vanilla ore and provide with energy to mine some ore.");
		// Powder
		for (Item item : ModTags.Items.POWDER.getValues()) {
			registration.addIngredientInfo(
					new ItemStack(item),
					ingredientManager.getIngredientType(ItemStack.class),
					"Can be obtained from Ore Miners.");
		}
		// Wrench
		registration.addIngredientInfo(
				new ItemStack(ModItems.WRENCH.get()),
				ingredientManager.getIngredientType(ItemStack.class),
				"Can be used to change the input/output of cables. (green = output, red = input, none = both)");
	}
}
