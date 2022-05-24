package com.entisy.techniq.common.world.feature;

import com.entisy.techniq.core.util.silentchaos512.MathUtils;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.LakesFeature;

import java.util.Random;

public class OilLakesFeature extends LakesFeature {

    public static final OilLakesFeature INSTANCE = new OilLakesFeature(BlockStateFeatureConfig.CODEC);

    public OilLakesFeature(Codec<BlockStateFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
        // Occasionally allow surface lakes
        if (MathUtils.tryPercentage(0.1)) {
            return super.place(worldIn, generator, rand, pos, config);
        }

        // Place around Y 20-40 normally
        BlockPos newPos = new BlockPos(pos.getX(), rand.nextInt(20) + 20, pos.getZ());
        return super.place(worldIn, generator, rand, newPos, config);
    }
}
