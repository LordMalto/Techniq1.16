//package com.entisy.techniq.common.block.blockBreaker;
//
//import com.entisy.techniq.core.init.ModTileEntityTypes;
//
//import net.minecraft.block.Blocks;
//import net.minecraft.tileentity.ITickableTileEntity;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.tileentity.TileEntityType;
//
//public class BlockBreakerTileEntity extends TileEntity implements ITickableTileEntity {
//
//	public BlockBreakerTileEntity(TileEntityType<?> type) {
//		super(type);
//	}
//
//	public BlockBreakerTileEntity()
//	{
//		this(ModTileEntityTypes.BLOCK_BREAKER_TILE_ENTITY_TYPE.get());
//	}
//
//	@Override
//	public void tick() {
//		this.level.setBlock(this.getBlockPos().below(), Blocks.AIR.defaultBlockState(), 0);
//	}
//}
