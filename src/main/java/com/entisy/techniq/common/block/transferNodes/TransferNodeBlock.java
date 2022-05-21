package com.entisy.techniq.common.block.transferNodes;

import com.entisy.techniq.api.ConnectionType;
import com.entisy.techniq.common.block.SixWayMachineBlock;
import com.entisy.techniq.common.block.MachineTileEntity;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;
import java.util.Map;

public class TransferNodeBlock extends SixWayMachineBlock {

	public static final EnumProperty<ConnectionType> NORTH = EnumProperty.create("north", ConnectionType.class);
	public static final EnumProperty<ConnectionType> EAST = EnumProperty.create("east", ConnectionType.class);
	public static final EnumProperty<ConnectionType> SOUTH = EnumProperty.create("south", ConnectionType.class);
	public static final EnumProperty<ConnectionType> WEST = EnumProperty.create("west", ConnectionType.class);
	public static final EnumProperty<ConnectionType> UP = EnumProperty.create("up", ConnectionType.class);
	public static final EnumProperty<ConnectionType> DOWN = EnumProperty.create("down", ConnectionType.class);
	public static final Map<Direction, EnumProperty<ConnectionType>> FACING_TO_PROPERTY_MAP = Util
			.make(Maps.newEnumMap(Direction.class), (map) -> {
				map.put(Direction.NORTH, NORTH);
				map.put(Direction.EAST, EAST);
				map.put(Direction.SOUTH, SOUTH);
				map.put(Direction.WEST, WEST);
				map.put(Direction.UP, UP);
				map.put(Direction.DOWN, DOWN);
			});

	public TransferNodeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, ConnectionType.NONE)
                .setValue(EAST, ConnectionType.NONE)
                .setValue(SOUTH, ConnectionType.NONE)
                .setValue(WEST, ConnectionType.NONE)
                .setValue(UP, ConnectionType.NONE)
                .setValue(DOWN, ConnectionType.NONE));
    }

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TransferNodeTileEntity();
	}
	
	@Nullable
	private static Direction getClickedConnection(Vector3d relative) {
		if (relative.x < 0.25)
			return Direction.WEST;
		if (relative.x > 0.75)
			return Direction.EAST;
		if (relative.y < 0.25)
			return Direction.DOWN;
		if (relative.y > 0.75)
			return Direction.UP;
		if (relative.z < 0.25)
			return Direction.NORTH;
		if (relative.z > 0.75)
			return Direction.SOUTH;
		return null;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.makeConnections(context.getLevel(), context.getClickedPos());
	}

	public BlockState makeConnections(IBlockReader worldIn, BlockPos pos) {
		return this.defaultBlockState()
				.setValue(DOWN, createConnection(worldIn, pos, Direction.DOWN, ConnectionType.NONE))
				.setValue(UP, createConnection(worldIn, pos, Direction.UP, ConnectionType.NONE))
				.setValue(NORTH, createConnection(worldIn, pos, Direction.NORTH, ConnectionType.NONE))
				.setValue(EAST, createConnection(worldIn, pos, Direction.EAST, ConnectionType.NONE))
				.setValue(SOUTH, createConnection(worldIn, pos, Direction.SOUTH, ConnectionType.NONE))
				.setValue(WEST, createConnection(worldIn, pos, Direction.WEST, ConnectionType.NONE));
	}

	private static ConnectionType createConnection(IBlockReader worldIn, BlockPos pos, Direction side,
			ConnectionType current) {
		TileEntity tileEntity = worldIn.getBlockEntity(pos.relative(side));
		if (tileEntity instanceof TransferNodeTileEntity) {
			return ConnectionType.BOTH;
//		} else if (tileEntity != null) {
//			IEnergyStorage energy = EnergyUtils.getEnergyFromSideOrNull(tileEntity, side.getOpposite());
//			if (energy != null) {
//				if (energy.canExtract()) {
//					return current == ConnectionType.NONE ? ConnectionType.IN : current;
//				} else if (energy.canReceive()) {
//					return current == ConnectionType.NONE ? ConnectionType.OUT : current;
//				}
//			}
		}
		if (tileEntity instanceof MachineTileEntity) {
			return ConnectionType.OUT;
		}
		return ConnectionType.NONE;
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (worldIn.getBlockEntity(facingPos) instanceof TransferNodeTileEntity)
			TransferNodeNetworkManager.invalidateNetwork(worldIn, currentPos);

		EnumProperty<ConnectionType> property = FACING_TO_PROPERTY_MAP.get(facing);
		ConnectionType current = stateIn.getValue(property);
		return stateIn.setValue(property, createConnection(worldIn, currentPos, facing, current));
	}

	@Override
	protected int getAABBIndex(BlockState state) {
		int i = 0;

		for (int j = 0; j < Direction.values().length; ++j) {
			if (state.getValue(FACING_TO_PROPERTY_MAP.get(Direction.values()[j])) != ConnectionType.NONE) {
				i |= 1 << j;
			}
		}

		return i;
	}

	public static ConnectionType getConnection(BlockState state, Direction side) {
		return state.getValue(FACING_TO_PROPERTY_MAP.get(side));
	}
}
