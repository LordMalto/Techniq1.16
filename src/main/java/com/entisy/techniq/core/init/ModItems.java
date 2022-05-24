package com.entisy.techniq.core.init;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.item.HammerItem;
import com.entisy.techniq.common.item.MachineFrameItem;
import com.entisy.techniq.common.item.WrenchItem;
import com.entisy.techniq.core.tab.TechniqTab;

import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Techniq.MOD_ID);

	// INGOTS
	public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> REDSTONE_ALLOY_INGOT = ITEMS.register("redstone_alloy_ingot",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
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
	public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));

	// RODS
	public static final RegistryObject<Item> COPPER_ROD = ITEMS.register("copper_rod",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> IRON_ROD = ITEMS.register("iron_rod",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> STEEL_ROD = ITEMS.register("steel_rod",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> GOLD_ROD = ITEMS.register("gold_rod",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> DIAMOND_ROD = ITEMS.register("diamond_rod",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));

	// POWDERS
	public static final RegistryObject<Item> COAL_POWDER = ITEMS.register("coal_powder",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> IRON_POWDER = ITEMS.register("iron_powder",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> DIAMOND_POWDER = ITEMS.register("diamond_powder",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> LAPIS_POWDER = ITEMS.register("lapis_powder",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> EMERALD_POWDER = ITEMS.register("emerald_powder",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> GOLD_POWDER = ITEMS.register("gold_powder",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> QUARTZ_POWDER = ITEMS.register("quartz_powder",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));

	// TOOLS
	public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer",
			() -> new HammerItem(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> WRENCH = ITEMS.register("wrench",
			WrenchItem::new);

	// OTHER
	public static final RegistryObject<Item> DRILL = ITEMS.register("drill",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> DRILL_HEAD = ITEMS.register("drill_head",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> PLASTIC = ITEMS.register("plastic",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));

	// FRAMES
	public static final RegistryObject<Item> MACHINE_FRAME = ITEMS.register("machine_frame",
			() -> new MachineFrameItem(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> HEAVY_MACHINE_FRAME = ITEMS.register("heavy_machine_frame",
			() -> new MachineFrameItem(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> DIAMOND_MACHINE_FRAME = ITEMS.register("diamond_machine_frame",
			() -> new MachineFrameItem(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<Item> GOLD_MACHINE_FRAME = ITEMS.register("gold_machine_frame",
			() -> new MachineFrameItem(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));

	// BUCKETS
	public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket",
			() -> new BucketItem(
					() -> ModFluids.OIL_FLUID.get(),
					new Item.Properties()
							.tab(TechniqTab.TECHNIQ_TAB)
							.stacksTo(1)
			)
	);
}
