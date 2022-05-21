package com.entisy.techniq.common.block.electricalFurnace;

import com.entisy.techniq.common.block.MachineBlock;
import com.entisy.techniq.core.init.TileEntityTypesInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ElectricalFurnaceBlock extends MachineBlock {

	public ElectricalFurnaceBlock() {
		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, false));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.ELECTRICAL_FURNACE_TILE_ENTITY.get().create();
	}

	@Override
	public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack item) {
		super.setPlacedBy(world, pos, state, placer, item);
		if (!item.getDisplayName().getString().isEmpty()) {
			TileEntity tileEntity = world.getBlockEntity(pos);
			if (tileEntity instanceof ElectricalFurnaceTileEntity) {
				((ElectricalFurnaceTileEntity) tileEntity).setCustomName(item.getDisplayName());
			}
		}
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockRayTraceResult rayTrace) {
		if (world != null && !world.isClientSide()) {
			TileEntity tileEntity = world.getBlockEntity(pos);
			if (tileEntity instanceof ElectricalFurnaceTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
		TileEntity tileEntity = world.getBlockEntity(pos);
		if (tileEntity instanceof ElectricalFurnaceTileEntity && state.getBlock() != newState.getBlock()) {
			ElectricalFurnaceTileEntity furnace = (ElectricalFurnaceTileEntity) tileEntity;
			furnace.getInventory().toNonNullList().forEach(item -> {
				ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), item);
				world.addFreshEntity(itemEntity);
			});
		}
		if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
			world.removeBlockEntity(pos);
		}
	}
}
