package com.entisy.techniq.data.client;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.core.init.BlockInit;

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
		simpleBlock(BlockInit.COPPER_BLOCK.get());
		simpleBlock(BlockInit.COPPER_ORE.get());
		simpleBlock(BlockInit.NETHER_COPPER_ORE.get());
		simpleBlock(BlockInit.END_COPPER_ORE.get());
		simpleBlock(BlockInit.NETHER_IRON_ORE.get());
		simpleBlock(BlockInit.END_IRON_ORE.get());
		simpleBlock(BlockInit.NETHER_GOLD_ORE.get());
		simpleBlock(BlockInit.END_GOLD_ORE.get());
		simpleBlock(BlockInit.NETHER_DIAMOND_ORE.get());
		simpleBlock(BlockInit.END_DIAMOND_ORE.get());
		simpleBlock(BlockInit.NETHER_REDSTONE_ORE.get());
		simpleBlock(BlockInit.END_REDSTONE_ORE.get());
		simpleBlock(BlockInit.QUARTZ_ORE.get());
		simpleBlock(BlockInit.END_QUARTZ_ORE.get());
		simpleBlock(BlockInit.NETHER_COAL_ORE.get());
		simpleBlock(BlockInit.END_COAL_ORE.get());
		simpleBlock(BlockInit.NETHER_LAPIS_ORE.get());
		simpleBlock(BlockInit.END_LAPIS_ORE.get());
		simpleBlock(BlockInit.NETHER_EMERALD_ORE.get());
		simpleBlock(BlockInit.END_EMERALD_ORE.get());
		simpleBlock(BlockInit.DISPLAY_CASE.get());
		
		simpleBlock(BlockInit.ELECTRICAL_FURNACE.get());
		simpleBlock(BlockInit.METAL_PRESS.get());
		simpleBlock(BlockInit.ALLOY_SMELTER.get());
		
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
