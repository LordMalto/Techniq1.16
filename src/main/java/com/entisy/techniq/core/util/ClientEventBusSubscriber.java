package com.entisy.techniq.core.util;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.alloySmelter.AlloySmelterScreen;
import com.entisy.techniq.common.block.battery.BatteryScreen;
//import com.entisy.techniq.common.block.displayCase.DisplayCaseScreen;
import com.entisy.techniq.common.block.electricalFurnace.ElectricalFurnaceScreen;
import com.entisy.techniq.common.block.furnaceGenerator.FurnaceGeneratorScreen;
import com.entisy.techniq.common.block.metalPress.MetalPressScreen;
//import com.entisy.techniq.common.block.displayCase.DisplayCaseTileEntityRenderer;
import com.entisy.techniq.common.block.oreMiner.advancedOreMiner.AdvancedOreMinerScreen;
import com.entisy.techniq.common.block.oreMiner.simpleOreMiner.SimpleOreMinerScreen;
import com.entisy.techniq.core.init.ModContainerTypes;
import com.entisy.techniq.core.init.ModTileEntityTypes;

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
//		ScreenManager.register(ModContainerTypes.DISPLAY_CASE_CONTAINER_TYPE.get(), DisplayCaseScreen::new);
		ScreenManager.register(ModContainerTypes.METAL_PRESS_CONTAINER_TYPE.get(), MetalPressScreen::new);
		ScreenManager.register(ModContainerTypes.ELECTRICAL_FURNACE_CONTAINER_TYPE.get(), ElectricalFurnaceScreen::new);
		ScreenManager.register(ModContainerTypes.ALLOY_SMELTER_CONTAINER_TYPE.get(), AlloySmelterScreen::new);
		ScreenManager.register(ModContainerTypes.BATTERY_CONTAINER_TYPE.get(), BatteryScreen::new);
		ScreenManager.register(ModContainerTypes.FURNACE_GENERATOR_CONTAINER_TYPE.get(), FurnaceGeneratorScreen::new);
		ScreenManager.register(ModContainerTypes.SIMPLE_ORE_MINER_CONTAINER_TYPE.get(), SimpleOreMinerScreen::new);
		ScreenManager.register(ModContainerTypes.ADVANCED_ORE_MINER_CONTAINER_TYPE.get(), AdvancedOreMinerScreen::new);

//		ClientRegistry.bindTileEntityRenderer(ModTileEntityTypes.DISPLAY_CASE_TILE_ENTITY_TYPE.get(),
//				DisplayCaseTileEntityRenderer::new);
	}
}
