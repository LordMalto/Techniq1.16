package com.entisy.techniq.common.block.electricalFurnace.recipe;

import com.entisy.techniq.core.init.ModRecipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class ElectricalFurnaceRecipe implements IElectricalFurnaceRecipe {
	
	private final ResourceLocation id;
	private Ingredient input;
	private final ItemStack output;
	private int requiredEnergy = 200;
	private int smeltTime = 200;
	
	public ElectricalFurnaceRecipe(ResourceLocation id, Ingredient input, ItemStack output, int requiredEnergy, int smeltTime) {
		this.id = id;
		this.input = input;
		this.output = output;
		this.requiredEnergy = requiredEnergy;
		this.smeltTime = smeltTime;
	}
	
	public boolean match(IInventory inv) {
		if (input.test(inv.getItem(0))) {
			return true;
		}
		return false;
	}

	public int getRequiredEnergy() {
		return requiredEnergy;
	}

	public int getSmeltTime() {
		return smeltTime;
	}

	public int getCount(ItemStack item) {
		return input.getItems()[0].getCount() | 1;
	}

	@Override
	public ItemStack assemble(RecipeWrapper wrapper) {
		return output;
	}

	@Override
	public ItemStack getResultItem() {
		return output;
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return ModRecipe.ELECTRICAL_FURNACE_SERIALIZER.get();
	}

	@Override
	public Ingredient getInput() {
		return input;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return NonNullList.of(null, input);
	}

	@Override
	public boolean matches(RecipeWrapper wrapper, World p_77569_2_) {
		if (input.test(wrapper.getItem(0))) {
			return true;
		}
		return false;
	}
}
