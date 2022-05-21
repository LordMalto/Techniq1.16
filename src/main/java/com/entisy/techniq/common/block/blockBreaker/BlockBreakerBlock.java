package com.entisy.techniq.common.block.blockBreaker;

import com.entisy.techniq.core.init.TileEntityTypesInit;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockBreakerBlock extends Block {

	public BlockBreakerBlock() {
		super(AbstractBlock.Properties.of(Material.METAL).strength(15f).sound(SoundType.METAL));
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.BLOCK_BREAKER_TILE_ENTITY_TYPE.get().create();
	}
}
