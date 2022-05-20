package com.entisy.techniq.common.block.client.tileentityrenderer;

import com.entisy.techniq.common.tileentity.DisplayCaseTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class DisplayCaseTileEntityRenderer extends TileEntityRenderer<DisplayCaseTileEntity> {

	private Minecraft minecraft = Minecraft.getInstance();

	public DisplayCaseTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void render(DisplayCaseTileEntity tileEntity, float partialTicks, MatrixStack stack,
			IRenderTypeBuffer buffer, int compinedLight, int combinedOverlay) {
		if (tileEntity.getItem().equals(ItemStack.EMPTY) || tileEntity.getItem().equals(Items.AIR))
			return;
		ClientPlayerEntity player = minecraft.player;
		int ligtLevel = getLightLevel(tileEntity.getLevel(), tileEntity.getBlockPos().above());
		renderItem(tileEntity.getItem(), new double[] { 0.5d, 1d, 0.5d },
				Vector3f.YP.rotationDegrees(-player.getYHeadRot()), stack, buffer, partialTicks, combinedOverlay,
				ligtLevel, 0.8f);
		ITextComponent label = tileEntity.getItem().hasCustomHoverName() ? tileEntity.getItem().getDisplayName()
				: new TranslationTextComponent(tileEntity.getItem().getDescriptionId());
		renderLabel(stack, buffer, ligtLevel, new double[] { .5d, 1.3d, .5d }, label, 0xffffff);
	}

	private void renderItem(ItemStack item, double[] translation, Quaternion rotation, MatrixStack stack,
			IRenderTypeBuffer buffer, float partialTicks, int combinedOverlay, int lightLevel, float scale) {
		stack.pushPose();
		stack.translate(translation[0], translation[1], translation[2]);
		stack.mulPose(rotation);
		stack.scale(scale, scale, scale);
		IBakedModel model = minecraft.getItemRenderer().getModel(item, null, null);
		minecraft.getItemRenderer().render(item, ItemCameraTransforms.TransformType.GROUND, true, stack, buffer,
				lightLevel, combinedOverlay, model);
		stack.popPose();
	}

	private void renderLabel(MatrixStack stack, IRenderTypeBuffer buffer, int lightLevel, double[] corner,
			ITextComponent text, int color) {
		FontRenderer font = minecraft.font;
		stack.pushPose();
		float scale = 0.01f;
		int opacity = (int) (.4f * 255.0f) << 24;
		float offset = (float) (-font.width(text) / 2);
		Matrix4f matrix = stack.last().pose();
		stack.translate(corner[0], corner[1], corner[2]);
		stack.scale(scale, scale, scale);
		stack.mulPose(minecraft.getEntityRenderDispatcher().cameraOrientation());
		stack.mulPose(Vector3f.ZP.rotationDegrees(180f));
		font.drawInBatch(text, offset, 0, color, false, matrix, buffer, false, opacity, lightLevel);
		stack.popPose();
	}

	private int getLightLevel(World world, BlockPos pos) {
		int blockLight = world.getBrightness(LightType.BLOCK, pos);
		int skyLight = world.getBrightness(LightType.SKY, pos);
		return LightTexture.pack(blockLight, skyLight);
	}
}
