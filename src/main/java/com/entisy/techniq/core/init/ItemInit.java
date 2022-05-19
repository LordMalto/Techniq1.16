package com.entisy.techniq.core.init;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.item.HammerItem;
import com.entisy.techniq.core.tab.TechniqTab;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Techniq.MOD_ID);

	// INGOTS
	public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	
	// PLATES
	public static final RegistryObject<Item> COPPER_PLATE = ITEMS.register("copper_plate",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> GOLD_PLATE = ITEMS.register("gold_plate",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> DIAMOND_PLATE = ITEMS.register("diamond_plate",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	
	// TOOLS
	public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer",
			() -> new HammerItem(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
}
