package com.entisy.techniq.data;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.entisy.techniq.core.init.BlockInit;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class ModLootTableProvider extends LootTableProvider
{
    public ModLootTableProvider(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables()
    {
        return ImmutableList.of(Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((resourceLocation, lootTable) -> LootTableManager.validate(validationtracker, resourceLocation, lootTable));
    }

    public static class ModBlockLootTables extends BlockLootTables
    {
        @Override
        protected void addTables()
        {
            dropSelf(BlockInit.COPPER_BLOCK.get());
            dropSelf(BlockInit.COPPER_ORE.get());
            dropSelf(BlockInit.NETHER_COPPER_ORE.get());
            dropSelf(BlockInit.END_COPPER_ORE.get());
            dropSelf(BlockInit.NETHER_IRON_ORE.get());
            dropSelf(BlockInit.END_IRON_ORE.get());
            dropSelf(BlockInit.NETHER_COAL_ORE.get());
            dropSelf(BlockInit.END_COAL_ORE.get());
            dropSelf(BlockInit.QUARTZ_ORE.get());
            dropSelf(BlockInit.END_QUARTZ_ORE.get());
            dropSelf(BlockInit.NETHER_GOLD_ORE.get());
            dropSelf(BlockInit.END_GOLD_ORE.get());
            dropSelf(BlockInit.NETHER_REDSTONE_ORE.get());
            dropSelf(BlockInit.END_REDSTONE_ORE.get());
            dropSelf(BlockInit.NETHER_EMERALD_ORE.get());
            dropSelf(BlockInit.END_EMERALD_ORE.get());
            dropSelf(BlockInit.NETHER_DIAMOND_ORE.get());
            dropSelf(BlockInit.END_DIAMOND_ORE.get());
            dropSelf(BlockInit.NETHER_LAPIS_ORE.get());
            dropSelf(BlockInit.END_LAPIS_ORE.get());
            dropSelf(BlockInit.ELECTRICAL_FURNACE.get());
            dropSelf(BlockInit.METAL_PRESS.get());
            dropSelf(BlockInit.ALLOY_SMELTER.get());
            dropSelf(BlockInit.BLOCK_BREAKER.get());
            dropSelf(BlockInit.DISPLAY_CASE.get());
            dropSelf(BlockInit.CABLE.get());
            dropSelf(BlockInit.TRANSFER_NODE.get());

            //dropOther(ModBlocks.MACHINE_BLOCK.get(), ModItems.RUBY.get());

        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
        }
    }
}
