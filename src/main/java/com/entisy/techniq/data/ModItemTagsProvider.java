package com.entisy.techniq.data;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.core.init.ModItems;
import com.entisy.techniq.core.init.ModTags;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider
{
    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, Techniq.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags()
    {
        copy(ModTags.Blocks.ORES_COPPER, ModTags.Items.ORES_COPPER);
        copy(ModTags.Blocks.ORES_IRON, ModTags.Items.ORES_IRON);
        copy(ModTags.Blocks.ORES_GOLD, ModTags.Items.ORES_GOLD);
        copy(ModTags.Blocks.ORES_COAL, ModTags.Items.ORES_COAL);
        copy(ModTags.Blocks.ORES_QUARTZ, ModTags.Items.ORES_QUARTZ);
        copy(ModTags.Blocks.ORES_REDSTONE, ModTags.Items.ORES_REDSTONE);
        copy(ModTags.Blocks.ORES_EMERALD, ModTags.Items.ORES_EMERALD);
        copy(ModTags.Blocks.ORES_DIAMOND, ModTags.Items.ORES_DIAMOND);
        copy(ModTags.Blocks.ORES_LAPIS, ModTags.Items.ORES_LAPIS);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(ModTags.Blocks.STORAGE_BLOCKS_COPPER, ModTags.Items.STORAGE_BLOCKS_COPPER);
        copy(ModTags.Blocks.STORAGE_BLOCKS_STEEL, ModTags.Items.STORAGE_BLOCKS_STEEL);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        // MOD TAGS
        copy(ModTags.Blocks.MACHINE_BLOCKS, ModTags.Items.MACHINE_BLOCKS);

        // ITEM TAGS
        tag(ModTags.Items.INGOTS_COPPER).add(ModItems.COPPER_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(ModTags.Items.INGOTS_COPPER);
        tag(ModTags.Items.INGOTS_REDSTONE_ALLOY).add(ModItems.REDSTONE_ALLOY_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(ModTags.Items.INGOTS_REDSTONE_ALLOY);
        tag(ModTags.Items.PLATES_COPPER).add(ModItems.COPPER_PLATE.get());
        tag(ModTags.Items.PLATES_IRON).add(ModItems.IRON_PLATE.get());
        tag(ModTags.Items.PLATES_GOLD).add(ModItems.GOLD_PLATE.get());
        tag(ModTags.Items.PLATES_DIAMOND).add(ModItems.DIAMOND_PLATE.get());
        tag(ModTags.Items.RODS_COPPER).add(ModItems.COPPER_ROD.get());
        tag(ModTags.Items.RODS_IRON).add(ModItems.IRON_ROD.get());
        tag(ModTags.Items.RODS_STEEL).add(ModItems.STEEL_ROD.get());
        tag(ModTags.Items.RODS_GOLD).add(ModItems.GOLD_ROD.get());
        tag(ModTags.Items.RODS_DIAMOND).add(ModItems.DIAMOND_ROD.get());
        tag(ModTags.Items.PLATES_STEEL).add(ModItems.STEEL_PLATE.get());
        tag(ModTags.Items.INGOTS_STEEL).add(ModItems.STEEL_INGOT.get());
        tag(ModTags.Items.BUCKETS_OIL).add(ModItems.OIL_BUCKET.get());

        tag(ModTags.Items.POWDER).add(ModItems.COAL_POWDER.get());
        tag(ModTags.Items.POWDER).add(ModItems.QUARTZ_POWDER.get());
        tag(ModTags.Items.POWDER).add(ModItems.GOLD_POWDER.get());
        tag(ModTags.Items.POWDER).add(ModItems.EMERALD_POWDER.get());
        tag(ModTags.Items.POWDER).add(ModItems.LAPIS_POWDER.get());
        tag(ModTags.Items.POWDER).add(ModItems.DIAMOND_POWDER.get());
        tag(ModTags.Items.POWDER).add(ModItems.IRON_POWDER.get());
    }
}
