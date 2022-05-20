package com.entisy.techniq.core.init;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;

public class ModOreFeatureConfig extends OreFeatureConfig {
    public ModOreFeatureConfig(RuleTest target, BlockState state, int size) {
        super(target, state, size);
    }

    public static final class FillerBlockType {
        public static final RuleTest NATURAL_STONE = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);
        public static final RuleTest NETHERRACK = new BlockMatchRuleTest(Blocks.NETHERRACK);
        public static final RuleTest NETHER_ORE_REPLACEABLES = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        public static final RuleTest END_ORE_REPLACEABLES = new BlockMatchRuleTest(Blocks.END_STONE);
    }
}
