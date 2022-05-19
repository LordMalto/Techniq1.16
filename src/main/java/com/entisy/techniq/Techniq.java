package com.entisy.techniq;

import com.entisy.techniq.core.init.BlockInit;
import com.entisy.techniq.core.init.ContainerTypesInit;
import com.entisy.techniq.core.init.FeatureInit;
import com.entisy.techniq.core.init.ItemInit;
import com.entisy.techniq.core.init.RecipeSerializerInit;
import com.entisy.techniq.core.init.TileEntityTypesInit;
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
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("techniq")
@Mod.EventBusSubscriber(modid = Techniq.MOD_ID, bus = Bus.MOD)
public class Techniq {

	public static final String MOD_ID = "techniq";

	public Techniq() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		ItemInit.ITEMS.register(bus);
		BlockInit.BLOCKS.register(bus);
		TileEntityTypesInit.TILE_ENTITY_TYPES.register(bus);
		ContainerTypesInit.CONTAINER_TYPES.register(bus);
		RecipeSerializerInit.RECIPE_SERIALIZERS.register(bus);
		
		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, FeatureInit::addOres);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(TechniqTab.TECHNIQ_TAB))
					.setRegistryName(block.getRegistryName()));
		});
	}
}
