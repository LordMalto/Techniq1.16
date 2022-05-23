package com.entisy.techniq.data;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.entisy.techniq.core.init.ModBlocks;
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
            dropSelf(ModBlocks.COPPER_BLOCK.get());
            dropSelf(ModBlocks.COPPER_ORE.get());
            dropSelf(ModBlocks.NETHER_COPPER_ORE.get());
            dropSelf(ModBlocks.END_COPPER_ORE.get());
            dropSelf(ModBlocks.NETHER_IRON_ORE.get());
            dropSelf(ModBlocks.END_IRON_ORE.get());
            dropSelf(ModBlocks.NETHER_COAL_ORE.get());
            dropSelf(ModBlocks.END_COAL_ORE.get());
            dropSelf(ModBlocks.QUARTZ_ORE.get());
            dropSelf(ModBlocks.END_QUARTZ_ORE.get());
            dropSelf(ModBlocks.NETHER_GOLD_ORE.get());
            dropSelf(ModBlocks.END_GOLD_ORE.get());
            dropSelf(ModBlocks.NETHER_REDSTONE_ORE.get());
            dropSelf(ModBlocks.END_REDSTONE_ORE.get());
            dropSelf(ModBlocks.NETHER_EMERALD_ORE.get());
            dropSelf(ModBlocks.END_EMERALD_ORE.get());
            dropSelf(ModBlocks.NETHER_DIAMOND_ORE.get());
            dropSelf(ModBlocks.END_DIAMOND_ORE.get());
            dropSelf(ModBlocks.NETHER_LAPIS_ORE.get());
            dropSelf(ModBlocks.END_LAPIS_ORE.get());
            dropSelf(ModBlocks.ELECTRICAL_FURNACE.get());
            dropSelf(ModBlocks.METAL_PRESS.get());
            dropSelf(ModBlocks.ALLOY_SMELTER.get());
//            dropSelf(ModBlocks.BLOCK_BREAKER.get());
//            dropSelf(ModBlocks.DISPLAY_CASE.get());
            dropSelf(ModBlocks.CABLE.get());
            dropSelf(ModBlocks.ITEM_CABLE.get());
            dropSelf(ModBlocks.FLUID_CABLE.get());
            dropSelf(ModBlocks.STEEL_BLOCK.get());
            dropSelf(ModBlocks.BATTERY.get());
            dropSelf(ModBlocks.FURNACE_GENERATOR.get());
            dropSelf(ModBlocks.SIMPLE_ORE_MINER.get());
            dropSelf(ModBlocks.ADVANCED_ORE_MINER.get());

            //dropOther(ModBlocks.MACHINE_BLOCK.get(), ModItems.RUBY.get());

        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
        }
    }
}
