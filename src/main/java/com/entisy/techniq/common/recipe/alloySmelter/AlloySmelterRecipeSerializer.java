package com.entisy.techniq.common.recipe.alloySmelter;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class AlloySmelterRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
		implements IRecipeSerializer<AlloySmelterRecipe> {

	@Override
	public AlloySmelterRecipe fromJson(ResourceLocation id, JsonObject json) {
		Ingredient input1 = Ingredient.fromJson(JSONUtils.getAsJsonArray(json, "ingredients").get(0));
		Ingredient input2 = Ingredient.fromJson(JSONUtils.getAsJsonArray(json, "ingredients").get(1));
		ItemStack output = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "output"), true);
		return new AlloySmelterRecipe(id, input1, input2, output);
	}

	@Override
	public AlloySmelterRecipe fromNetwork(ResourceLocation id, PacketBuffer buffer) {
		Ingredient input1 = Ingredient.fromNetwork(buffer);
		Ingredient input2 = Ingredient.fromNetwork(buffer);
		ItemStack output = buffer.readItem();
		return new AlloySmelterRecipe(id, input1, input2, output);
	}

	@Override
	public void toNetwork(PacketBuffer buffer, AlloySmelterRecipe recipe) {
		Ingredient input1 = recipe.getIngredients().get(0);
		Ingredient input2 = recipe.getIngredients().get(1);
		input1.toNetwork(buffer);
		input2.toNetwork(buffer);
		buffer.writeItemStack(recipe.getResultItem(), false);
	}
}
