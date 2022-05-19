package com.entisy.techniq.common.recipe.alloySmelter;

import com.entisy.techniq.core.init.RecipeSerializerInit;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class AlloySmelterRecipe implements IAlloySmelterRecipe {

	private final ResourceLocation id;
	private final Ingredient input1;
	private final Ingredient input2;
	private final ItemStack output;

	public AlloySmelterRecipe(ResourceLocation id, Ingredient input1, Ingredient input2, ItemStack output) {
		this.id = id;
		this.input1 = input1;
		this.input2 = input2;
		this.output = output;
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
		return RecipeSerializerInit.ALLOY_SMELTER_SERIALIZER.get();
	}

	@Override
	public Ingredient getInput() {
		return input1;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return NonNullList.of(null, input1);
	}

	@Override
	public boolean matches(RecipeWrapper wrapper, World level) {
		if (input1.test(wrapper.getItem(0)) && input2.test(wrapper.getItem(1))
				|| input1.test(wrapper.getItem(1)) && input2.test(wrapper.getItem(0))) {
			return true;
		}
		return false;
	}
}
