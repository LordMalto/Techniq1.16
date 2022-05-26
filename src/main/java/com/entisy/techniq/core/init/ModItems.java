package com.entisy.techniq.core.init;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.item.HammerItem;
import com.entisy.techniq.common.item.MachineFrameItem;
import com.entisy.techniq.common.item.WrenchItem;
import com.entisy.techniq.core.tab.TechniqTab;

import com.entisy.techniq.core.util.SimpleList;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

	/*
	{
	"ITEMS": "ITEMS",
	"item.techniq.coal_powder": "Coal Powder",
	"item.techniq.copper_ingot": "Copper Ingot",
	"item.techniq.copper_plate": "Copper Plate",
	"item.techniq.copper_rod": "Copper Rod",
	"item.techniq.diamond_machine_frame": "Diamond Machine Frame",
	"item.techniq.diamond_plate": "Diamond Plate",
	"item.techniq.diamond_powder": "Diamond Powder",
	"item.techniq.diamond_rod": "Diamond Rod",
	"item.techniq.drill": "Drill",
	"item.techniq.drill_head": "Drill Head",
	"item.techniq.emerald_powder": "Emerald Powder",
	"item.techniq.gold_machine_frame": "Gold Machine Frame",
	"item.techniq.gold_plate": "Gold Plate",
	"item.techniq.gold_powder": "Gold Powder",
	"item.techniq.gold_rod": "Gold Rod",
	"item.techniq.hammer": "Hammer",
	"item.techniq.heavy_machine_frame": "Heavy Machine Frame",
	"item.techniq.iron_plate": "Iron Plate",
	"item.techniq.iron_powder": "Iron Powder",
	"item.techniq.iron_rod": "Iron Rod",
	"item.techniq.lapis_powder": "Lapis Powder",
	"item.techniq.machine_frame": "Machine Frame",
	"item.techniq.oil_bucket": "Oil Bucket",
	"item.techniq.plastic": "Plastic",
	"item.techniq.plastic_piece": "Plastic Piece",
	"item.techniq.quartz_powder": "Quartz Powder",
	"item.techniq.redstone_alloy_ingot": "Redstone Alloy Ingot",
	"item.techniq.steel_ingot": "Steel Ingot",
	"item.techniq.steel_plate": "Steel Plate",
	"item.techniq.steel_rod": "Steel Rod",
	"item.techniq.wrench": "Wrench",

	"BLOCKS": "BLOCKS",
	"block.techniq.advanced_ore_miner": "Advanced Ore Miner",
	"block.techniq.alloy_smelter": "Alloy Smelter",
	"block.techniq.battery": "Battery",
	"block.techniq.block_breaker": "Block Breaker",
	"block.techniq.copper_block": "Copper Block",
	"block.techniq.copper_ore": "Copper Ore",
	"block.techniq.display_case": "Display Case",
	"block.techniq.electrical_furnace": "Electrical Furnace",
	"block.techniq.end_coal_ore": "End Coal Ore",
	"block.techniq.end_copper_ore": "End Copper Ore",
	"block.techniq.end_diamond_ore": "End Diamond Ore",
	"block.techniq.end_emerald_ore": "End Emerald Ore",
	"block.techniq.end_gold_ore": "End Gold Ore",
	"block.techniq.end_iron_ore": "End Iron Ore",
	"block.techniq.end_lapis_ore": "End Lapis Ore",
	"block.techniq.end_quartz_ore": "End Quartz Ore",
	"block.techniq.end_redstone_ore": "End Redstone Ore",
	"block.techniq.cable": "Energy Cable",
	"block.techniq.fluid_cable": "Fluid Cable",
	"block.techniq.furnace_generator": "Furnace Generator",
	"block.techniq.item_cable": "Item Cable",
	"block.techniq.metal_press": "Metal Press",
	"block.techniq.nether_coal_ore": "Nether Coal Ore",
	"block.techniq.nether_copper_ore": "Nether Copper Ore",
	"block.techniq.nether_diamond_ore": "Nether Diamond Ore",
	"block.techniq.nether_emerald_ore": "Nether Emerald Ore",
	"block.techniq.nether_gold_ore": "Nether Gold Ore",
	"block.techniq.nether_iron_ore": "Nether Iron Ore",
	"block.techniq.nether_lapis_ore": "Nether Lapis Ore",
	"block.techniq.nether_redstone_ore": "Nether Redstone Ore",
	"block.techniq.oil": "Oil",
	"block.techniq.quartz_ore": "Quartz Ore",
	"block.techniq.refinery": "Refinery",
	"block.techniq.simple_ore_miner": "Simple Ore Miner",
	"block.techniq.steel_block": "Steel Block",

	"FLUIDS": "FLUIDS",
	"fluid.techniq.oil": "Oil",

	"TABS": "TABS",
	"itemGroup.techniq_tab": "Techniq"
}
	 */

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
	public static final RegistryObject<Item> PLASTIC_PIECE = ITEMS.register("plastic_piece",
			() -> new Item(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));

	// FRAMES
	public static final RegistryObject<MachineFrameItem> MACHINE_FRAME = ITEMS.register("basic_machine_frame",
			() -> new MachineFrameItem(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<MachineFrameItem> HEAVY_MACHINE_FRAME = ITEMS.register("heavy_machine_frame",
			() -> new MachineFrameItem(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<MachineFrameItem> DIAMOND_MACHINE_FRAME = ITEMS.register("diamond_machine_frame",
			() -> new MachineFrameItem(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));
	public static final RegistryObject<MachineFrameItem> GOLD_MACHINE_FRAME = ITEMS.register("gold_machine_frame",
			() -> new MachineFrameItem(new Item.Properties().tab(TechniqTab.TECHNIQ_TAB)));

	// BUCKETS
	public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket",
			() -> new BucketItem(
					() -> ModFluids.OIL,
					new Item.Properties()
							.tab(TechniqTab.TECHNIQ_TAB)
							.stacksTo(1)
			)
	);

	public static final SimpleList<Item> getItems() {
		SimpleList<Item> ret = new SimpleList<>();
		ITEMS.getEntries().forEach(i -> {
			if (i.get() instanceof MachineFrameItem || i.get() instanceof BlockItem) {}
			else {
				ret.append(i.get());
			}
		});
		return ret;
	}

	public static final SimpleList<Item> getAllItems() {
		SimpleList<Item> ret = new SimpleList<>();
		ITEMS.getEntries().forEach(i -> {
			if (i.get() instanceof BlockItem) {}
			else {
				ret.append(i.get());
			}
		});
		return ret;
	}

	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(TechniqTab.TECHNIQ_TAB))
					.setRegistryName(block.getRegistryName()));
		});
	}
}
