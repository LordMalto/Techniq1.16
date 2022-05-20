package com.entisy.techniq.common.client.screen;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.container.AlloySmelterContainer;
import com.entisy.techniq.common.container.FurnaceGeneratorContainer;
import com.entisy.techniq.common.tileentity.FurnaceGeneratorTileEntity;
import com.entisy.techniq.common.tileentity.MetalPressTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FurnaceGeneratorScreen extends ContainerScreen<FurnaceGeneratorContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Techniq.MOD_ID,
            "textures/gui/furnace_generator.png");

    private final FurnaceGeneratorContainer container;

    public FurnaceGeneratorScreen(FurnaceGeneratorContainer container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
        leftPos = 0;
        topPos = 0;
        width = 176;
        height = 165;
        this.container = container;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        minecraft.getTextureManager().bind(TEXTURE);

        int x = (this.width - this.getXSize()) / 2;
        int y = (this.height - this.getYSize()) / 2;
        this.blit(stack, x, y, 0, 0, getXSize(), getYSize());

        // draw energy bar
        int currentEnergy = getMenu().currentEnergy.get();
        int pixel = currentEnergy != 0 ? currentEnergy * 50 / 25000 : 0;
        blit(stack, getGuiLeft() + 154, getGuiTop() + (50 - pixel) + 18, 176, (50 - pixel), 12, 50);

        // draw progress bar/arrow
        blit(stack, getGuiLeft() + 81, getGuiTop() + (13-getMenu().getSmeltProgressionScaled()) + 33, 0, 166+(13-getMenu().getSmeltProgressionScaled()), 14, 13);
    }

    @Override
    protected void renderLabels(MatrixStack stack, int mouseX, int mouseY) {
        font.draw(stack, title.getContents(), 8.0f, 8.0f, 4210752); // hover text
        font.draw(stack, inventory.getDisplayName().getContents(), 8.0f, 69.0f, 4210752);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderTooltip(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.renderTooltip(matrixStack, mouseX, mouseY);

        if (mouseX >= getGuiLeft() + 154 && mouseX < getGuiLeft() + 154 + 12) {
            if (mouseY >= getGuiTop() + 18 && mouseY < getGuiTop() + 18 + 50) {

                // rendering the amount of energy stored e.g. 5000/25000

                int currentEnergy = getMenu().currentEnergy.get();

                this.renderTooltip(matrixStack,
                        new StringTextComponent(currentEnergy + "/" + FurnaceGeneratorTileEntity.maxEnergy), mouseX, mouseY);
            }
        }
    }
}
