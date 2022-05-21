package com.entisy.techniq.core.init;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.alloySmelter.AlloySmelterTileEntity;
import com.entisy.techniq.common.block.battery.BatteryTileEntity;
import com.entisy.techniq.common.block.blockBreaker.BlockBreakerTileEntity;
import com.entisy.techniq.common.block.cable.CableTileEntity;
import com.entisy.techniq.common.block.displayCase.DisplayCaseTileEntity;
import com.entisy.techniq.common.block.electricalFurnace.ElectricalFurnaceTileEntity;
import com.entisy.techniq.common.block.furnaceGenerator.FurnaceGeneratorTileEntity;
import com.entisy.techniq.common.block.metalPress.MetalPressTileEntity;
import com.entisy.techniq.common.block.transferNodes.TransferNodeTileEntity;

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
	public static final RegistryObject<TileEntityType<TransferNodeTileEntity>> TRANSFER_NODE_TILE_ENTITY = TILE_ENTITY_TYPES
			.register("transfer_node", () -> TileEntityType.Builder
					.of(TransferNodeTileEntity::new, BlockInit.TRANSFER_NODE.get()).build(null));
	public static final RegistryObject<TileEntityType<BatteryTileEntity>> BATTERY_TILE_ENTITY = TILE_ENTITY_TYPES
			.register("battery", () -> TileEntityType.Builder
					.of(BatteryTileEntity::new, BlockInit.BATTERY.get()).build(null));

	public static final RegistryObject<TileEntityType<FurnaceGeneratorTileEntity>> FURNACE_GENERATOR_TILE_ENTITY = TILE_ENTITY_TYPES
			.register("furnace_generator", () -> TileEntityType.Builder
					.of(FurnaceGeneratorTileEntity::new, BlockInit.FURNACE_GENERATOR.get()).build(null));
}
