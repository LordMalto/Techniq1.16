package com.entisy.techniq.data;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.core.init.ItemInit;
import com.entisy.techniq.core.init.TagsInit;

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
        copy(TagsInit.Blocks.ORES_COPPER, TagsInit.Items.ORES_COPPER);
        copy(TagsInit.Blocks.ORES_IRON, TagsInit.Items.ORES_IRON);
        copy(TagsInit.Blocks.ORES_GOLD, TagsInit.Items.ORES_GOLD);
        copy(TagsInit.Blocks.ORES_COAL, TagsInit.Items.ORES_COAL);
        copy(TagsInit.Blocks.ORES_QUARTZ, TagsInit.Items.ORES_QUARTZ);
        copy(TagsInit.Blocks.ORES_REDSTONE, TagsInit.Items.ORES_REDSTONE);
        copy(TagsInit.Blocks.ORES_EMERALD, TagsInit.Items.ORES_EMERALD);
        copy(TagsInit.Blocks.ORES_DIAMOND, TagsInit.Items.ORES_DIAMOND);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(TagsInit.Blocks.STORAGE_BLOCKS_COPPER, TagsInit.Items.STORAGE_BLOCKS_COPPER);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        // MOD TAGS
        copy(TagsInit.Blocks.MACHINE_BLOCKS, TagsInit.Items.MACHINE_BLOCKS);

        // ITEM TAGS
        tag(TagsInit.Items.INGOTS_COPPER).add(ItemInit.COPPER_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(TagsInit.Items.INGOTS_COPPER);
        tag(TagsInit.Items.INGOTS_REDSTONE_ALLOY).add(ItemInit.REDSTONE_ALLOY_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(TagsInit.Items.INGOTS_REDSTONE_ALLOY);
        tag(TagsInit.Items.PLATES_COPPER).add(ItemInit.COPPER_PLATE.get());
        tag(TagsInit.Items.PLATES_IRON).add(ItemInit.IRON_PLATE.get());
        tag(TagsInit.Items.PLATES_GOLD).add(ItemInit.GOLD_PLATE.get());
        tag(TagsInit.Items.PLATES_DIAMOND).add(ItemInit.DIAMOND_PLATE.get());
    }
}
