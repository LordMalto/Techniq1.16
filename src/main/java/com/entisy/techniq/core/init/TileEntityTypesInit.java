package com.entisy.techniq.core.init;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.cable.CableTileEntity;
import com.entisy.techniq.common.tileentity.AlloySmelterTileEntity;
import com.entisy.techniq.common.tileentity.BlockBreakerTileEntity;
import com.entisy.techniq.common.tileentity.DisplayCaseTileEntity;
import com.entisy.techniq.common.tileentity.ElectricalFurnaceTileEntity;
import com.entisy.techniq.common.tileentity.MetalPressTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypesInit {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, Techniq.MOD_ID);

	public static final RegistryObject<TileEntityType<BlockBreakerTileEntity>> BLOCK_BREAKER_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES
			.register("block_breaker", () -> TileEntityType.Builder
					.of(BlockBreakerTileEntity::new, BlockInit.BLOCK_BREAKER.get()).build(null));
	public static final RegistryObject<TileEntityType<DisplayCaseTileEntity>> DISPLAY_CASE_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES
			.register("display_case", () -> TileEntityType.Builder
					.of(DisplayCaseTileEntity::new, BlockInit.DISPLAY_CASE.get()).build(null));
	public static final RegistryObject<TileEntityType<MetalPressTileEntity>> METAL_PRESS_TILE_ENTITY = TILE_ENTITY_TYPES
			.register("metal_press", () -> TileEntityType.Builder
					.of(MetalPressTileEntity::new, BlockInit.METAL_PRESS.get()).build(null));
	public static final RegistryObject<TileEntityType<ElectricalFurnaceTileEntity>> ELECTRICAL_FURNACE_TILE_ENTITY = TILE_ENTITY_TYPES
			.register("electrical_furnace", () -> TileEntityType.Builder
					.of(ElectricalFurnaceTileEntity::new, BlockInit.ELECTRICAL_FURNACE.get()).build(null));
	public static final RegistryObject<TileEntityType<AlloySmelterTileEntity>> ALLOY_SMELTER_TILE_ENTITY = TILE_ENTITY_TYPES
			.register("alloy_smelter", () -> TileEntityType.Builder
					.of(AlloySmelterTileEntity::new, BlockInit.ALLOY_SMELTER.get()).build(null));
	public static final RegistryObject<TileEntityType<CableTileEntity>> CABLE_TILE_ENTITY = TILE_ENTITY_TYPES
			.register("cable", () -> TileEntityType.Builder
					.of(CableTileEntity::new, BlockInit.CABLE.get()).build(null));
}
