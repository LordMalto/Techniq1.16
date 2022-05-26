package com.entisy.techniq.core.util.jei;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.refinery.RefineryTileEntity;
import com.entisy.techniq.core.init.ModBlocks;
import com.entisy.techniq.core.init.ModItems;
import com.entisy.techniq.core.util.jei.util.DrawHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class RefineryRecipeCategory extends DrawHelper implements IRecipeCategory<RefineryTileEntity> {

    public static final ResourceLocation UID = new ResourceLocation(Techniq.MOD_ID, "refinery");
    public static final ResourceLocation TEXTURE = new ResourceLocation(Techniq.MOD_ID, "textures/block/refinery/jei.png");

    private final IDrawable background;
    private final IDrawable icon;

    public RefineryRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 136, 74);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.REFINERY.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends RefineryTileEntity> getRecipeClass() {
        return RefineryTileEntity.class;
    }

    @Override
    public String getTitle() {
        return ModBlocks.REFINERY.get().getName().getString();
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
    public void setIngredients(RefineryTileEntity recipe, IIngredients ingredients) {
        ingredients.setOutput(VanillaTypes.ITEM, ModItems.PLASTIC_PIECE.get().getDefaultInstance());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, RefineryTileEntity recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(2, false, 82, 28);
        recipeLayout.getItemStacks().set(ingredients);
    }

    @Override
    public void draw(RefineryTileEntity recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        // Energy bar
        int pixel = recipe.getRequiredEnergy() * 50 / 10000;
        blit(matrixStack, 112, (50 - pixel) + 12, 136, (50 - pixel), 12, 50);
        // Fluid bar
        int p = recipe.getRequiredFluid() * 50 / 10000;
        blit(matrixStack, 12, (50 - p) + 12, 148, (50 - p), 12, 50);
        // Energy tooltip
        if (mouseX >= 112 && mouseX < 112 + 12) {
            if (mouseY >= 12 && mouseY < 12 + 50) {
                // Energy bar
                blit(matrixStack, 112, (50 - pixel) + 12, 136, (50 - pixel), 12, 50);
                // Fluid bar
                blit(matrixStack, 12, (50 - p) + 12, 148, (50 - p), 12, 50);
                // Tooltip
                renderTooltip(matrixStack, new StringTextComponent(recipe.getRequiredEnergy() + "RF"), (int) mouseX, (int) mouseY);
            }
        }
        // Fluid tooltip
        if (mouseX >= 12 && mouseX < 12 + 12) {
            if (mouseY >= 12 && mouseY < 12 + 50) {
                // Energy bar
                blit(matrixStack, 112, (50 - pixel) + 12, 136, (50 - pixel), 12, 50);
                // Fluid bar
                blit(matrixStack, 12, (50 - p) + 12, 148, (50 - p), 12, 50);
                // Tooltip
                renderTooltip(matrixStack, new StringTextComponent(recipe.getRequiredFluid() + "mb"), (int) mouseX, (int) mouseY);
            }
        }
        // Work Time
        font.draw(matrixStack, recipe.getWorkTimeInSeconds() + "s", 83, 18, 4210752);
    }
}
