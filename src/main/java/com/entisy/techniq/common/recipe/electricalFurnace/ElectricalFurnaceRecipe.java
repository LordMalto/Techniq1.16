package com.entisy.techniq.common.recipe.electricalFurnace;

import com.entisy.techniq.core.init.RecipeSerializerInit;

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
	
	public ElectricalFurnaceRecipe(ResourceLocation id, Ingredient input, ItemStack output) {
		this.id = id;
		this.input = input;
		this.output = output;
	}
	
	public boolean match(IInventory inv) {
		if (input.test(inv.getItem(0))) {
			return true;
		}
		return false;
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
		return RecipeSerializerInit.ELECTRICAL_FURNACE_SERIALIZER.get();
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
