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
//		simpleBlock(ModBlocks.DISPLAY_CASE.get());
        simpleBlock(ModBlocks.STEEL_BLOCK.get());

        horizontalBlock(ModBlocks.ELECTRICAL_FURNACE.get(),
                models().orientableWithBottom(ModBlocks.ELECTRICAL_FURNACE.get().getRegistryName().getPath(),
                        modLoc("block/electrical_furnace/side"), modLoc("block/electrical_furnace/front"),
                        modLoc("block/electrical_furnace/bottom"), modLoc("block/electrical_furnace/top")));
        horizontalBlock(ModBlocks.FURNACE_GENERATOR.get(),
                models().orientableWithBottom(ModBlocks.FURNACE_GENERATOR.get().getRegistryName().getPath(),
                        modLoc("block/furnace_generator/side"), modLoc("block/furnace_generator/front"),
                        modLoc("block/furnace_generator/bottom"), modLoc("block/furnace_generator/top")));
		horizontalBlock(ModBlocks.METAL_PRESS.get(),
				models().orientableWithBottom(ModBlocks.METAL_PRESS.get().getRegistryName().getPath(),
						modLoc("block/metal_press/side"), modLoc("block/metal_press/front"),
						modLoc("block/metal_press/bottom"), modLoc("block/metal_press/top")));
		horizontalBlock(ModBlocks.ALLOY_SMELTER.get(),
				models().orientableWithBottom(ModBlocks.ALLOY_SMELTER.get().getRegistryName().getPath(),
						modLoc("block/alloy_smelter/side"), modLoc("block/alloy_smelter/front"),
						modLoc("block/alloy_smelter/bottom"), modLoc("block/alloy_smelter/top")));
        horizontalBlock(ModBlocks.BATTERY.get(),
                models().orientableWithBottom(ModBlocks.BATTERY.get().getRegistryName().getPath(),
                        modLoc("block/battery/side"), modLoc("block/battery/side"),
                        modLoc("block/battery/bottom"), modLoc("block/battery/top")));
        horizontalBlock(ModBlocks.SIMPLE_ORE_MINER.get(),
                models().orientableWithBottom(ModBlocks.SIMPLE_ORE_MINER.get().getRegistryName().getPath(),
                        modLoc("block/simple_ore_miner/side"), modLoc("block/simple_ore_miner/side"),
                        modLoc("block/simple_ore_miner/bottom"), modLoc("block/simple_ore_miner/top")));
        horizontalBlock(ModBlocks.ADVANCED_ORE_MINER.get(),
                models().orientableWithBottom(ModBlocks.ADVANCED_ORE_MINER.get().getRegistryName().getPath(),
                        modLoc("block/advanced_ore_miner/side"), modLoc("block/advanced_ore_miner/side"),
                        modLoc("block/advanced_ore_miner/bottom"), modLoc("block/advanced_ore_miner/top")));
    }
}
