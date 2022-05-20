package com.entisy.techniq.common.block.client.screen;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.container.DisplayCaseContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DisplayCaseScreen extends ContainerScreen<DisplayCaseContainer> {

	private static final ResourceLocation DISPLAY_CASE_GUI = new ResourceLocation(Techniq.MOD_ID, "textures/gui/display_case.png");
	
	public DisplayCaseScreen(DisplayCaseContainer container, PlayerInventory inv, ITextComponent title) {
		super(container, inv, title);
		
		leftPos = 0;
		topPos = 0;
		width = 175;
		height = 201;
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTicks);
		renderTooltip(stack, mouseX, mouseY);
	}
	
	@Override
	protected void renderLabels(MatrixStack stack, int x, int y) {
		font.draw(stack, inventory.getDisplayName(), (float) inventoryLabelX, (float) inventoryLabelY, 4210752);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		minecraft.textureManager.bind(DISPLAY_CASE_GUI);
		int x = (width - getXSize()) / 2;
		int y = (height - getYSize()) / 2;
		blit(stack, x, y, 0, 0, getXSize(), getYSize());
	}
}
