package com.entisy.techniq.data.client;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.core.init.ModBlocks;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

	public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, Techniq.MOD_ID, exFileHelper);
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(ModBlocks.COPPER_BLOCK.get());
		simpleBlock(ModBlocks.COPPER_ORE.get());
		simpleBlock(ModBlocks.NETHER_COPPER_ORE.get());
		simpleBlock(ModBlocks.END_COPPER_ORE.get());
		simpleBlock(ModBlocks.NETHER_IRON_ORE.get());
		simpleBlock(ModBlocks.END_IRON_ORE.get());
		simpleBlock(ModBlocks.NETHER_GOLD_ORE.get());
		simpleBlock(ModBlocks.END_GOLD_ORE.get());
		simpleBlock(ModBlocks.NETHER_DIAMOND_ORE.get());
		simpleBlock(ModBlocks.END_DIAMOND_ORE.get());
		simpleBlock(ModBlocks.NETHER_REDSTONE_ORE.get());
		simpleBlock(ModBlocks.END_REDSTONE_ORE.get());
		simpleBlock(ModBlocks.QUARTZ_ORE.get());
		simpleBlock(ModBlocks.END_QUARTZ_ORE.get());
		simpleBlock(ModBlocks.NETHER_COAL_ORE.get());
		simpleBlock(ModBlocks.END_COAL_ORE.get());
		simpleBlock(ModBlocks.NETHER_LAPIS_ORE.get());
		simpleBlock(ModBlocks.END_LAPIS_ORE.get());
		simpleBlock(ModBlocks.NETHER_EMERALD_ORE.get());
		simpleBlock(ModBlocks.END_EMERALD_ORE.get());
		simpleBlock(ModBlocks.DISPLAY_CASE.get());
		simpleBlock(ModBlocks.STEEL_BLOCK.get());

		simpleBlock(ModBlocks.ELECTRICAL_FURNACE.get());
		simpleBlock(ModBlocks.METAL_PRESS.get());
		simpleBlock(ModBlocks.ALLOY_SMELTER.get());
		simpleBlock(ModBlocks.BATTERY.get());
		simpleBlock(ModBlocks.FURNACE_GENERATOR.get());
		
//		horizontalBlock(BlockInit.ELECTRICAL_FURNACE.get(),
//				models().orientableWithBottom(BlockInit.ELECTRICAL_FURNACE.get().getRegistryName().getPath(),
//						modLoc("block/machine_block_side"), modLoc("block/electrical_furnace_front"),
//						modLoc("block/machine_block_bottom"), modLoc("block/machine_block_top")));
//		horizontalBlock(BlockInit.METAL_PRESS.get(),
//				models().orientableWithBottom(BlockInit.METAL_PRESS.get().getRegistryName().getPath(),
//						modLoc("block/machine_block_side"), modLoc("block/metal_press_front"),
//						modLoc("block/machine_block_bottom"), modLoc("block/machine_block_top")));
//		horizontalBlock(BlockInit.ALLOY_SMELTER.get(),
//				models().orientableWithBottom(BlockInit.ALLOY_SMELTER.get().getRegistryName().getPath(),
//						modLoc("block/machine_block_side"), modLoc("block/metal_press_front"),
//						modLoc("block/machine_block_bottom"), modLoc("block/machine_block_top")));
	}
}
