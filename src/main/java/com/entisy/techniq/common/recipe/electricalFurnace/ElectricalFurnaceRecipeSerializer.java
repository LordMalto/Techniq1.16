package com.entisy.techniq.common.recipe.electricalFurnace;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ElectricalFurnaceRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
		implements IRecipeSerializer<ElectricalFurnaceRecipe> {

	@Override
	public ElectricalFurnaceRecipe fromJson(ResourceLocation id, JsonObject json) {
		Ingredient input = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "input"));
		ItemStack output = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "output"), true);
		int requiredEnergy = json.get("required_energy").getAsInt();
		int smeltTime = json.get("smelt_time").getAsInt();
		return new ElectricalFurnaceRecipe(id, input, output, requiredEnergy, smeltTime);
	}

	@Override
	public ElectricalFurnaceRecipe fromNetwork(ResourceLocation id, PacketBuffer buffer) {
		Ingredient input = Ingredient.fromNetwork(buffer);
		ItemStack output = buffer.readItem();
		int requiredEnergy = buffer.readInt();
		int smeltTime = buffer.readInt();
		return new ElectricalFurnaceRecipe(id, input, output, requiredEnergy, smeltTime);
	}

	@Override
	public void toNetwork(PacketBuffer buffer, ElectricalFurnaceRecipe recipe) {
		Ingredient input = recipe.getIngredients().get(0);
		input.toNetwork(buffer);
		buffer.writeItemStack(recipe.getResultItem(), false);
		buffer.writeInt(recipe.getRequiredEnergy());
		buffer.writeInt(recipe.getSmeltTime());
	}
}
