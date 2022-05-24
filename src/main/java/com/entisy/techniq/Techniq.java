package com.entisy.techniq;

import com.entisy.techniq.core.init.*;
import com.entisy.techniq.core.tab.TechniqTab;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("techniq")
@Mod.EventBusSubscriber(modid = Techniq.MOD_ID, bus = Bus.MOD)
public class Techniq {

	public static final String MOD_ID = "techniq";

	public Techniq() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		ModItems.ITEMS.register(bus);
		ModBlocks.BLOCKS.register(bus);
		ModFluids.FLUIDS.register(bus);
		ModTileEntityTypes.TILE_ENTITY_TYPES.register(bus);
		ModContainerTypes.CONTAINER_TYPES.register(bus);
		ModRecipes.RECIPE_SERIALIZERS.register(bus);

		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(TechniqTab.TECHNIQ_TAB))
					.setRegistryName(block.getRegistryName()));
		});
	}
}
