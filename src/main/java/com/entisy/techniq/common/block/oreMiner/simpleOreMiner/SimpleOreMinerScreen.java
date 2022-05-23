package com.entisy.techniq.common.block.oreMiner.simpleOreMiner;

import com.entisy.techniq.Techniq;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleOreMinerScreen extends ContainerScreen<SimpleOreMinerContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Techniq.MOD_ID,
            "textures/gui/simple_ore_miner.png");

    private final SimpleOreMinerContainer container;

    public SimpleOreMinerScreen(SimpleOreMinerContainer container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
        this.container = container;
        leftPos = 0;
        topPos = 0;
        width = 176;
        height = 165;
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
        AtomicInteger current = new AtomicInteger();

        container.getCapabilityFromTE().ifPresent(iEnergyStorage -> {
            current.set(iEnergyStorage.getEnergyStored());
        });

        int pixel = current.get() != 0 ? current.get() * 50 / 25000 : 0;
        blit(stack, getGuiLeft() + 154, getGuiTop() + (50 - pixel) + 18, 176, (50 - pixel), 12, 50);

        // draw progress bar/arrow
        int p = 0;
        if (!getMenu().tileEntity.isBlockNull()) p = getMenu().getMiningProgressionScaled();
        if (!getMenu().tileEntity.isBlockNull()) blit(stack, getGuiLeft() + 66, getGuiTop() + (16 - p) + 35, 188, (16 - p), 4, 16);
    }

    @Override
    protected void renderLabels(MatrixStack stack, int mouseX, int mouseY) {
        font.draw(stack, title.getContents(), 8.0f, 8.0f, 4210752); // hover text
        font.draw(stack, inventory.getDisplayName().getContents(), 8.0f, 69.0f, Color.BLUE.getBlue());
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
                AtomicInteger current = new AtomicInteger();

                container.getCapabilityFromTE().ifPresent(iEnergyStorage -> {
                    current.set(iEnergyStorage.getEnergyStored());
                });

                this.renderTooltip(matrixStack,
                        new StringTextComponent(current.get() + "/" + SimpleOreMinerTileEntity.maxEnergy), mouseX, mouseY);
            }
        }
        if (mouseX >= getGuiLeft() + 66 && mouseX < getGuiLeft() + 66 + 4) {
            if (mouseY >= getGuiTop() + 35 && mouseY < getGuiTop() + 35 + 16) {
                // rendering the mining progression e.g. 15%
                int current = 0;
                if (!getMenu().tileEntity.isBlockNull()) current = getMenu().tileEntity.currentSmeltTime * 100 / getMenu().tileEntity.getMiningTime();
                this.renderTooltip(matrixStack, new StringTextComponent(current + "%"), mouseX, mouseY);
            }
        }
    }
}
