package com.entisy.techniq.data;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.core.init.BlockInit;
import com.entisy.techniq.core.init.TagsInit;

import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider
{
    public ModBlockTagsProvider(DataGenerator gen, ExistingFileHelper existingFileHelper)
    {
        super(gen, Techniq.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags()
    {
        tag(TagsInit.Blocks.ORES_COPPER).add(BlockInit.COPPER_ORE.get());
        tag(TagsInit.Blocks.ORES_COPPER).add(BlockInit.NETHER_COPPER_ORE.get());
        tag(TagsInit.Blocks.ORES_COPPER).add(BlockInit.END_COPPER_ORE.get());
        tag(Tags.Blocks.ORES).addTag(TagsInit.Blocks.ORES_COPPER);
        tag(TagsInit.Blocks.ORES_GOLD).add(Blocks.GOLD_ORE);
        tag(TagsInit.Blocks.ORES_GOLD).add(BlockInit.NETHER_GOLD_ORE.get());
        tag(TagsInit.Blocks.ORES_GOLD).add(BlockInit.END_GOLD_ORE.get());
        tag(Tags.Blocks.ORES).addTag(TagsInit.Blocks.ORES_GOLD);
        tag(TagsInit.Blocks.ORES_DIAMOND).add(Blocks.DIAMOND_ORE);
        tag(TagsInit.Blocks.ORES_DIAMOND).add(BlockInit.NETHER_DIAMOND_ORE.get());
        tag(TagsInit.Blocks.ORES_DIAMOND).add(BlockInit.END_DIAMOND_ORE.get());
        tag(Tags.Blocks.ORES).addTag(TagsInit.Blocks.ORES_DIAMOND);
        tag(TagsInit.Blocks.ORES_REDSTONE).add(Blocks.REDSTONE_ORE);
        tag(TagsInit.Blocks.ORES_REDSTONE).add(BlockInit.NETHER_REDSTONE_ORE.get());
        tag(TagsInit.Blocks.ORES_REDSTONE).add(BlockInit.END_REDSTONE_ORE.get());
        tag(Tags.Blocks.ORES).addTag(TagsInit.Blocks.ORES_REDSTONE);
        tag(TagsInit.Blocks.ORES_COAL).add(Blocks.COAL_ORE);
        tag(TagsInit.Blocks.ORES_COAL).add(BlockInit.NETHER_COAL_ORE.get());
        tag(TagsInit.Blocks.ORES_COAL).add(BlockInit.END_COAL_ORE.get());
        tag(Tags.Blocks.ORES).addTag(TagsInit.Blocks.ORES_COAL);
        tag(TagsInit.Blocks.ORES_EMERALD).add(Blocks.EMERALD_ORE);
        tag(TagsInit.Blocks.ORES_EMERALD).add(BlockInit.NETHER_EMERALD_ORE.get());
        tag(TagsInit.Blocks.ORES_EMERALD).add(BlockInit.END_EMERALD_ORE.get());
        tag(Tags.Blocks.ORES).addTag(TagsInit.Blocks.ORES_EMERALD);
        tag(TagsInit.Blocks.ORES_QUARTZ).add(Blocks.NETHER_QUARTZ_ORE);
        tag(TagsInit.Blocks.ORES_QUARTZ).add(BlockInit.QUARTZ_ORE.get());
        tag(TagsInit.Blocks.ORES_QUARTZ).add(BlockInit.END_QUARTZ_ORE.get());
        tag(Tags.Blocks.ORES).addTag(TagsInit.Blocks.ORES_QUARTZ);
        tag(TagsInit.Blocks.ORES_LAPIS).add(Blocks.LAPIS_ORE);
        tag(TagsInit.Blocks.ORES_LAPIS).add(BlockInit.NETHER_LAPIS_ORE.get());
        tag(TagsInit.Blocks.ORES_LAPIS).add(BlockInit.END_LAPIS_ORE.get());
        tag(Tags.Blocks.ORES).addTag(TagsInit.Blocks.ORES_LAPIS);
        tag(TagsInit.Blocks.ORES_IRON).add(Blocks.IRON_ORE);
        tag(TagsInit.Blocks.ORES_IRON).add(BlockInit.NETHER_IRON_ORE.get());
        tag(TagsInit.Blocks.ORES_IRON).add(BlockInit.END_IRON_ORE.get());
        tag(Tags.Blocks.ORES).addTag(TagsInit.Blocks.ORES_IRON);
        tag(TagsInit.Blocks.STORAGE_BLOCKS_COPPER).add(BlockInit.COPPER_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(TagsInit.Blocks.STORAGE_BLOCKS_COPPER);
        tag(TagsInit.Blocks.STORAGE_BLOCKS_STEEL).add(BlockInit.STEEL_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(TagsInit.Blocks.STORAGE_BLOCKS_STEEL);

        // MOD TAGS
        tag(TagsInit.Blocks.MACHINE_BLOCKS).add(BlockInit.ALLOY_SMELTER.get());
        tag(TagsInit.Blocks.MACHINE_BLOCKS).add(BlockInit.ELECTRICAL_FURNACE.get());
        tag(TagsInit.Blocks.MACHINE_BLOCKS).add(BlockInit.METAL_PRESS.get());
        tag(TagsInit.Blocks.MACHINE_BLOCKS).add(BlockInit.CABLE.get());
        tag(TagsInit.Blocks.MACHINE_BLOCKS).add(BlockInit.TRANSFER_NODE.get());
        tag(TagsInit.Blocks.MACHINE_BLOCKS).add(BlockInit.BATTERY.get());
        tag(TagsInit.Blocks.MACHINE_BLOCKS).add(BlockInit.FURNACE_GENERATOR.get());
    }
}
