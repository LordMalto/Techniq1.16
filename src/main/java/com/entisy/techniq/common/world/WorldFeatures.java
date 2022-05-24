package com.entisy.techniq.common.world;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.world.feature.OilLakesFeature;
import com.entisy.techniq.core.init.ModBlocks;
import com.entisy.techniq.core.init.ModFluids;
import com.entisy.techniq.core.init.ModOreFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Techniq.MOD_ID)
public class WorldFeatures {

    private static final Lazy<ConfiguredFeature<?, ?>> OIL_LAKES_STANDARD = Lazy.of(() -> createOilLakeFeature(1f));
    private static final Lazy<ConfiguredFeature<?, ?>> OIL_LAKES_COMMON = Lazy.of(() -> createOilLakeFeature(0.67f));

    private WorldFeatures() {
    }

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().register(OilLakesFeature.INSTANCE.setRegistryName(new ResourceLocation(Techniq.MOD_ID, "oil_lakes")));

        registerConfiguredFeature("oil_lakes_standard", OIL_LAKES_STANDARD.get());
        registerConfiguredFeature("oil_lakes_common", OIL_LAKES_COMMON.get());
    }

    private static void registerConfiguredFeature(String name, ConfiguredFeature<?, ?> configuredFeature) {
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Techniq.MOD_ID, name), configuredFeature);
    }

    @SubscribeEvent
    public static void addFeaturesToBiomes(BiomeLoadingEvent event) {
        if (event.getCategory() != Biome.Category.NETHER && event.getCategory() != Biome.Category.THEEND) {
            addOilLakes(event);
        }
        // OVERWORLD
        addOre(event, ModOreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.COPPER_ORE.get().defaultBlockState(), 10, 0, 60, 10);
        addOre(event, ModOreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.QUARTZ_ORE.get().defaultBlockState(), 5, 0, 60, 5);

        // NETHER
        addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_COAL_ORE.get().defaultBlockState(), 5, 0, 60, 5);
        addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_COPPER_ORE.get().defaultBlockState(), 5, 0, 60, 5);
        addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_DIAMOND_ORE.get().defaultBlockState(), 5, 0, 60, 5);
        addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_EMERALD_ORE.get().defaultBlockState(), 5, 0, 60, 5);
        addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_GOLD_ORE.get().defaultBlockState(), 5, 0, 60, 5);
        addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_IRON_ORE.get().defaultBlockState(), 5, 0, 60, 5);
        addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_LAPIS_ORE.get().defaultBlockState(), 5, 0, 60, 5);
        addOre(event, ModOreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_REDSTONE_ORE.get().defaultBlockState(), 5, 0, 60, 5);

        // END
        addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, ModBlocks.END_COAL_ORE.get().defaultBlockState(), 5, 0, 60, 5);
        addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, ModBlocks.END_COPPER_ORE.get().defaultBlockState(), 5, 0, 60, 5);
        addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, ModBlocks.END_DIAMOND_ORE.get().defaultBlockState(), 5, 0, 60, 5);
        addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, ModBlocks.END_EMERALD_ORE.get().defaultBlockState(), 5, 0, 60, 5);
        addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, ModBlocks.END_GOLD_ORE.get().defaultBlockState(), 5, 0, 60, 5);
        addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, ModBlocks.END_IRON_ORE.get().defaultBlockState(), 5, 0, 60, 5);
        addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, ModBlocks.END_LAPIS_ORE.get().defaultBlockState(), 5, 0, 60, 5);
        addOre(event, ModOreFeatureConfig.FillerBlockType.END_ORE_REPLACEABLES, ModBlocks.END_REDSTONE_ORE.get().defaultBlockState(), 5, 0, 60, 5);
    }

    private static ConfiguredFeature<?, ?> createOilLakeFeature(float multi) {

        return OilLakesFeature.INSTANCE
                .configured(new BlockStateFeatureConfig(ModFluids.OIL_BLOCK.get().defaultBlockState()))
                .decorated(Placement.WATER_LAKE.configured(new ChanceConfig((int) (multi * 1))));
    }

    private static void addOilLakes(BiomeLoadingEvent biome) {
        ConfiguredFeature<?, ?> feature = biome.getName().equals(Biomes.DESERT.getRegistryName())
                ? OIL_LAKES_COMMON.get() : OIL_LAKES_STANDARD.get();
        biome.getGeneration().addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, feature);
    }

    public static void addOre(final BiomeLoadingEvent event, RuleTest rule, BlockState state, int veinSize,
                              int minHeight, int maxHeight, int amount) {
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(new OreFeatureConfig(rule, state, veinSize))
                        .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
                        .squared().count(amount));
    }
}
