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
    private final Ingredient input1, input2;
    private final ItemStack output;
    private int requiredEnergy = 200;
    private int smeltTime = 200;

    public AlloySmelterRecipe(ResourceLocation id, Ingredient input1, Ingredient input2, ItemStack output, int requiredEnergy, int smeltTime) {
        this.id = id;
        this.input1 = input1;
        this.input2 = input2;
        this.output = output;
        this.requiredEnergy = requiredEnergy;
        this.smeltTime = smeltTime;
    }

    public int getRequiredEnergy() {
        return requiredEnergy;
    }

    public int getSmeltTime() {
        return smeltTime;
    }

    public int getCount(ItemStack item) {
        if (item.getItem() == getIngredients().get(0).getItems()[0].getItem()) return input1.getItems()[0].getCount();
        if (item.getItem() == getIngredients().get(1).getItems()[0].getItem()) return input2.getItems()[0].getCount();
        return 1;
    }

    public void setSmeltTime(int value) {
        smeltTime = value;
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
        return NonNullList.of(null, input1, input2);
    }

    @Override
    public boolean matches(RecipeWrapper wrapper, World level) {
        return (input1.test(wrapper.getItem(0)) && input2.test(wrapper.getItem(1)) && wrapper.getItem(0).getCount() >= input1.getItems()[0].getCount() && wrapper.getItem(1).getCount() >= input2.getItems()[0].getCount()) || input1.test(wrapper.getItem(1)) && input2.test(wrapper.getItem(0)) && wrapper.getItem(0).getCount() >= input2.getItems()[0].getCount() && wrapper.getItem(1).getCount() >= input1.getItems()[0].getCount();
    }
}
