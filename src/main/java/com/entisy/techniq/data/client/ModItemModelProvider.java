package com.entisy.techniq.data.client;

import com.entisy.techniq.Techniq;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

	public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Techniq.MOD_ID, existingFileHelper);
	}
	
	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	protected void registerModels() {
		withExistingParent("copper_block", modLoc("block/copper_block"));
		withExistingParent("copper_ore", modLoc("block/copper_ore"));
		withExistingParent("nether_copper_ore", modLoc("block/nether_copper_ore"));
		withExistingParent("end_copper_ore", modLoc("block/end_copper_ore"));
		withExistingParent("nether_iron_ore", modLoc("block/nether_iron_ore"));
		withExistingParent("end_iron_ore", modLoc("block/end_iron_ore"));
		withExistingParent("nether_diamond_ore", modLoc("block/nether_diamond_ore"));
		withExistingParent("end_diamond_ore", modLoc("block/end_diamond_ore"));
		withExistingParent("nether_gold_ore", modLoc("block/nether_gold_ore"));
		withExistingParent("end_gold_ore", modLoc("block/end_gold_ore"));
		withExistingParent("nether_coal_ore", modLoc("block/nether_coal_ore"));
		withExistingParent("end_coal_ore", modLoc("block/end_coal_ore"));
		withExistingParent("nether_redstone_ore", modLoc("block/nether_redstone_ore"));
		withExistingParent("end_redstone_ore", modLoc("block/end_redstone_ore"));
		withExistingParent("nether_emerald_ore", modLoc("block/nether_emerald_ore"));
		withExistingParent("end_emerald_ore", modLoc("block/end_emerald_ore"));
		withExistingParent("quartz_ore", modLoc("block/quartz_ore"));
		withExistingParent("end_quartz_ore", modLoc("block/end_quartz_ore"));
		withExistingParent("nether_lapis_ore", modLoc("block/nether_lapis_ore"));
		withExistingParent("end_lapis_ore", modLoc("block/end_lapis_ore"));
		withExistingParent("display_case", modLoc("block/display_case"));
		withExistingParent("electrical_furnace", modLoc("block/electrical_furnace"));
		withExistingParent("metal_press", modLoc("block/metal_press"));
		withExistingParent("alloy_smelter", modLoc("block/alloy_smelter"));
		withExistingParent("steel_block", modLoc("block/steel_block"));
		withExistingParent("battery",modLoc("block/battery"));
		withExistingParent("furnace_generator",modLoc("block/furnace_generator"));

		ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

		builder("copper_ingot", itemGenerated);
		builder("redstone_alloy_ingot", itemGenerated);
		builder("copper_plate", itemGenerated);
		builder("iron_plate", itemGenerated);
		builder("gold_plate", itemGenerated);
		builder("diamond_plate", itemGenerated);
		builder("hammer", itemGenerated);
		builder("iron_rod", itemGenerated);
		builder("copper_rod", itemGenerated);
		builder("steel_rod", itemGenerated);
		builder("steel_plate", itemGenerated);
		builder("steel_ingot", itemGenerated);
	}
	
	private ItemModelBuilder builder(String name, ModelFile parent) {
		return getBuilder(name).parent(parent).texture("layer0", "item/" + name);
	}
}
