package com.entisy.techniq.core.init;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class FeatureInit {
	
	public static void addOres(final BiomeLoadingEvent event)
	{
		// OVERWORLD
		addOre(event, ModOreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.COPPER_ORE.get().defaultBlockState(), 10, 0, 60, 10);
		addOre(event, ModOreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.QUARTZ_ORE.get().defaultBlockState(), 5, 0, 60, 5);

		// NETHER
		addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, BlockInit.NETHER_COAL_ORE.get().defaultBlockState(), 5, 0, 60, 5);
		addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, BlockInit.NETHER_COPPER_ORE.get().defaultBlockState(), 5, 0, 60, 5);
		addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, BlockInit.NETHER_DIAMOND_ORE.get().defaultBlockState(), 5, 0, 60, 5);
		addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, BlockInit.NETHER_EMERALD_ORE.get().defaultBlockState(), 5, 0, 60, 5);
		addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, BlockInit.NETHER_GOLD_ORE.get().defaultBlockState(), 5, 0, 60, 5);
		addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, BlockInit.NETHER_IRON_ORE.get().defaultBlockState(), 5, 0, 60, 5);
		addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, BlockInit.NETHER_LAPIS_ORE.get().defaultBlockState(), 5, 0, 60, 5);
		addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, BlockInit.NETHER_REDSTONE_ORE.get().defaultBlockState(), 5, 0, 60, 5);

		// END
		addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, BlockInit.END_COAL_ORE.get().defaultBlockState(), 5, 0, 60, 5);
		addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, BlockInit.END_COPPER_ORE.get().defaultBlockState(), 5, 0, 60, 5);
		addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, BlockInit.END_DIAMOND_ORE.get().defaultBlockState(), 5, 0, 60, 5);
		addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, BlockInit.END_EMERALD_ORE.get().defaultBlockState(), 5, 0, 60, 5);
		addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, BlockInit.END_GOLD_ORE.get().defaultBlockState(), 5, 0, 60, 5);
		addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, BlockInit.END_IRON_ORE.get().defaultBlockState(), 5, 0, 60, 5);
		addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, BlockInit.END_LAPIS_ORE.get().defaultBlockState(), 5, 0, 60, 5);
		addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, BlockInit.END_REDSTONE_ORE.get().defaultBlockState(), 5, 0, 60, 5);
	}

	public static void addOre(final BiomeLoadingEvent event, RuleTest rule, BlockState state, int veinSize,
			int minHeight, int maxHeight, int amount) {
		event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE.configured(new OreFeatureConfig(rule, state, veinSize))
						.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
						.squared().count(amount));
	}
}
