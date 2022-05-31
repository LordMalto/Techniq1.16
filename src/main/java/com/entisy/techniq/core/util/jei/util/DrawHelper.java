package com.entisy.techniq.core.util.jei.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;

import java.util.Arrays;
import java.util.List;

public class DrawHelper {
    public static final FontRenderer font = Minecraft.getInstance().font;

    public void blit(MatrixStack p_238474_1_, int p_238474_2_, int p_238474_3_, int p_238474_4_, int p_238474_5_, int p_238474_6_, int p_238474_7_) {
        blit(p_238474_1_, p_238474_2_, p_238474_3_, /*blit offset*/0, (float)p_238474_4_, (float)p_238474_5_, p_238474_6_, p_238474_7_, 256, 256);
    }

    public static void blit(MatrixStack p_238464_0_, int p_238464_1_, int p_238464_2_, int p_238464_3_, float p_238464_4_, float p_238464_5_, int p_238464_6_, int p_238464_7_, int p_238464_8_, int p_238464_9_) {
        innerBlit(p_238464_0_, p_238464_1_, p_238464_1_ + p_238464_6_, p_238464_2_, p_238464_2_ + p_238464_7_, p_238464_3_, p_238464_6_, p_238464_7_, p_238464_4_, p_238464_5_, p_238464_9_, p_238464_8_);
    }

    private static void innerBlit(MatrixStack p_238469_0_, int p_238469_1_, int p_238469_2_, int p_238469_3_, int p_238469_4_, int p_238469_5_, int p_238469_6_, int p_238469_7_, float p_238469_8_, float p_238469_9_, int p_238469_10_, int p_238469_11_) {
        innerBlit(p_238469_0_.last().pose(), p_238469_1_, p_238469_2_, p_238469_3_, p_238469_4_, p_238469_5_, (p_238469_8_ + 0.0F) / (float)p_238469_10_, (p_238469_8_ + (float)p_238469_6_) / (float)p_238469_10_, (p_238469_9_ + 0.0F) / (float)p_238469_11_, (p_238469_9_ + (float)p_238469_7_) / (float)p_238469_11_);
    }

    private static void innerBlit(Matrix4f p_238461_0_, int p_238461_1_, int p_238461_2_, int p_238461_3_, int p_238461_4_, int p_238461_5_, float p_238461_6_, float p_238461_7_, float p_238461_8_, float p_238461_9_) {
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.vertex(p_238461_0_, (float)p_238461_1_, (float)p_238461_4_, (float)p_238461_5_).uv(p_238461_6_, p_238461_9_).endVertex();
        bufferbuilder.vertex(p_238461_0_, (float)p_238461_2_, (float)p_238461_4_, (float)p_238461_5_).uv(p_238461_7_, p_238461_9_).endVertex();
        bufferbuilder.vertex(p_238461_0_, (float)p_238461_2_, (float)p_238461_3_, (float)p_238461_5_).uv(p_238461_7_, p_238461_8_).endVertex();
        bufferbuilder.vertex(p_238461_0_, (float)p_238461_1_, (float)p_238461_3_, (float)p_238461_5_).uv(p_238461_6_, p_238461_8_).endVertex();
        bufferbuilder.end();
        RenderSystem.enableAlphaTest();
        WorldVertexBufferUploader.end(bufferbuilder);
    }

    public void renderTooltip(MatrixStack p_238652_1_, ITextComponent p_238652_2_, int p_238652_3_, int p_238652_4_) {
        this.renderComponentTooltip(p_238652_1_, Arrays.asList(p_238652_2_), p_238652_3_, p_238652_4_);
    }

    public void renderComponentTooltip(MatrixStack p_243308_1_, List<ITextComponent> p_243308_2_, int p_243308_3_, int p_243308_4_) {
        this.renderWrappedToolTip(p_243308_1_, p_243308_2_, p_243308_3_, p_243308_4_, font);
    }

    public void renderWrappedToolTip(MatrixStack matrixStack, List<? extends ITextProperties> tooltips, int mouseX, int mouseY, FontRenderer font) {
        net.minecraftforge.fml.client.gui.GuiUtils.drawHoveringText(matrixStack, tooltips, mouseX, mouseY, 136, 74, -1, font);
    }
}
