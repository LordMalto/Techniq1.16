package com.entisy.techniq.client.screen;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.container.ElectricalFurnaceContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ElectricalFurnaceScreen extends ContainerScreen<ElectricalFurnaceContainer> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(Techniq.MOD_ID,
			"textures/gui/electrical_furnace.png");

	public ElectricalFurnaceScreen(ElectricalFurnaceContainer container, PlayerInventory inv, ITextComponent title) {
		super(container, inv, title);
		leftPos = 0;
		topPos = 0;
		width = 176;
		height = 165;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0f,  1.0f, 1.0f, 1.0f);
		minecraft.getTextureManager().bind(TEXTURE);
		
		int x= (this.width - this.getXSize()) / 2;
        int y = (this.height-this.getYSize()) /2;
        this.blit(stack, x, y, 0, 0, getXSize(), getYSize());
		
		// draw progress bar/arrow
		blit(stack, leftPos + 80, topPos + 35, 0, 166, getMenu().getSmeltProgressionScaled(), 16);
	}

	@Override
	protected void renderLabels(MatrixStack stack, int mouseX, int mouseY) {
		font.draw(stack, title.getContents(), 8.0f, 8.0f, 4210752); // hover text
		font.draw(stack, inventory.getDisplayName().getContents(), 8.0f, 69.0f, 4210752);
	}

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTicks);
		renderTooltip(stack, mouseX, mouseY);
	}
}
