package com.entisy.techniq.data;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.core.init.ModBlocks;
import com.entisy.techniq.core.init.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen, Techniq.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ModTags.Blocks.ORES_COPPER).add(ModBlocks.COPPER_ORE.get());
        tag(ModTags.Blocks.ORES_COPPER).add(ModBlocks.NETHER_COPPER_ORE.get());
        tag(ModTags.Blocks.ORES_COPPER).add(ModBlocks.END_COPPER_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_COPPER);
        tag(ModTags.Blocks.ORES_GOLD).add(Blocks.GOLD_ORE);
        tag(ModTags.Blocks.ORES_GOLD).add(ModBlocks.NETHER_GOLD_ORE.get());
        tag(ModTags.Blocks.ORES_GOLD).add(ModBlocks.END_GOLD_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_GOLD);
        tag(ModTags.Blocks.ORES_DIAMOND).add(Blocks.DIAMOND_ORE);
        tag(ModTags.Blocks.ORES_DIAMOND).add(ModBlocks.NETHER_DIAMOND_ORE.get());
        tag(ModTags.Blocks.ORES_DIAMOND).add(ModBlocks.END_DIAMOND_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_DIAMOND);
        tag(ModTags.Blocks.ORES_REDSTONE).add(Blocks.REDSTONE_ORE);
        tag(ModTags.Blocks.ORES_REDSTONE).add(ModBlocks.NETHER_REDSTONE_ORE.get());
        tag(ModTags.Blocks.ORES_REDSTONE).add(ModBlocks.END_REDSTONE_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_REDSTONE);
        tag(ModTags.Blocks.ORES_COAL).add(Blocks.COAL_ORE);
        tag(ModTags.Blocks.ORES_COAL).add(ModBlocks.NETHER_COAL_ORE.get());
        tag(ModTags.Blocks.ORES_COAL).add(ModBlocks.END_COAL_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_COAL);
        tag(ModTags.Blocks.ORES_EMERALD).add(Blocks.EMERALD_ORE);
        tag(ModTags.Blocks.ORES_EMERALD).add(ModBlocks.NETHER_EMERALD_ORE.get());
        tag(ModTags.Blocks.ORES_EMERALD).add(ModBlocks.END_EMERALD_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_EMERALD);
        tag(ModTags.Blocks.ORES_QUARTZ).add(Blocks.NETHER_QUARTZ_ORE);
        tag(ModTags.Blocks.ORES_QUARTZ).add(ModBlocks.QUARTZ_ORE.get());
        tag(ModTags.Blocks.ORES_QUARTZ).add(ModBlocks.END_QUARTZ_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_QUARTZ);
        tag(ModTags.Blocks.ORES_LAPIS).add(Blocks.LAPIS_ORE);
        tag(ModTags.Blocks.ORES_LAPIS).add(ModBlocks.NETHER_LAPIS_ORE.get());
        tag(ModTags.Blocks.ORES_LAPIS).add(ModBlocks.END_LAPIS_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_LAPIS);
        tag(ModTags.Blocks.ORES_IRON).add(Blocks.IRON_ORE);
        tag(ModTags.Blocks.ORES_IRON).add(ModBlocks.NETHER_IRON_ORE.get());
        tag(ModTags.Blocks.ORES_IRON).add(ModBlocks.END_IRON_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_IRON);
        tag(ModTags.Blocks.STORAGE_BLOCKS_COPPER).add(ModBlocks.COPPER_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_COPPER);
        tag(ModTags.Blocks.STORAGE_BLOCKS_STEEL).add(ModBlocks.STEEL_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_STEEL);

        // MOD TAGS
        tag(ModTags.Blocks.MACHINE_BLOCKS).add(ModBlocks.ALLOY_SMELTER.get());
        tag(ModTags.Blocks.MACHINE_BLOCKS).add(ModBlocks.ELECTRICAL_FURNACE.get());
        tag(ModTags.Blocks.MACHINE_BLOCKS).add(ModBlocks.METAL_PRESS.get());
        tag(ModTags.Blocks.MACHINE_BLOCKS).add(ModBlocks.CABLE.get());
//        tag(ModTags.Blocks.MACHINE_BLOCKS).add(ModBlocks.ITEM_CABLE.get());
//        tag(ModTags.Blocks.MACHINE_BLOCKS).add(ModBlocks.FLUID_CABLE.get());
        tag(ModTags.Blocks.MACHINE_BLOCKS).add(ModBlocks.BATTERY.get());
        tag(ModTags.Blocks.MACHINE_BLOCKS).add(ModBlocks.FURNACE_GENERATOR.get());
        tag(ModTags.Blocks.MACHINE_BLOCKS).add(ModBlocks.SIMPLE_ORE_MINER.get());
        tag(ModTags.Blocks.MACHINE_BLOCKS).add(ModBlocks.ADVANCED_ORE_MINER.get());
        tag(ModTags.Blocks.MACHINE_BLOCKS).add(ModBlocks.REFINERY.get());
    }
}
