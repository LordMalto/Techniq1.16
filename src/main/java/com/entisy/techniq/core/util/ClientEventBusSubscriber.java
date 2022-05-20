package com.entisy.techniq.core.util;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.client.screen.AlloySmelterScreen;
import com.entisy.techniq.common.client.screen.DisplayCaseScreen;
import com.entisy.techniq.common.client.screen.ElectricalFurnaceScreen;
import com.entisy.techniq.common.client.screen.MetalPressScreen;
import com.entisy.techniq.common.client.tileentityrenderer.DisplayCaseTileEntityRenderer;
import com.entisy.techniq.core.init.ContainerTypesInit;
import com.entisy.techniq.core.init.TileEntityTypesInit;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Techniq.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ScreenManager.register(ContainerTypesInit.DISPLAY_CASE_CONTAINER_TYPE.get(), DisplayCaseScreen::new);
		ScreenManager.register(ContainerTypesInit.METAL_PRESS_CONTAINER_TYPE.get(), MetalPressScreen::new);
		ScreenManager.register(ContainerTypesInit.ELECTRICAL_FURNACE_CONTAINER_TYPE.get(), ElectricalFurnaceScreen::new);
		ScreenManager.register(ContainerTypesInit.ALLOY_SMELTER_CONTAINER_TYPE.get(), AlloySmelterScreen::new);

		ClientRegistry.bindTileEntityRenderer(TileEntityTypesInit.DISPLAY_CASE_TILE_ENTITY_TYPE.get(),
				DisplayCaseTileEntityRenderer::new);
	}
}
