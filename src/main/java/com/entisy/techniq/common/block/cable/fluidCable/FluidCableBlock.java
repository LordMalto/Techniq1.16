package com.entisy.techniq.common.block.cable.fluidCable;

import com.entisy.techniq.api.ConnectionType;
import com.entisy.techniq.api.IWrenchable;
import com.entisy.techniq.common.block.SixWayMachineBlock;
import com.entisy.techniq.core.capabilities.fluid.IFluidStorage;
import com.entisy.techniq.core.util.FluidUtils;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class FluidCableBlock extends SixWayMachineBlock implements IWrenchable {

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

    public FluidCableBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, ConnectionType.NONE)
                .setValue(EAST, ConnectionType.NONE)
                .setValue(SOUTH, ConnectionType.NONE)
                .setValue(WEST, ConnectionType.NONE)
                .setValue(UP, ConnectionType.NONE)
                .setValue(DOWN, ConnectionType.NONE));
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

    private static ConnectionType createConnection(IBlockReader worldIn, BlockPos pos, Direction side,
                                                   ConnectionType current) {
        TileEntity tileEntity = worldIn.getBlockEntity(pos.relative(side));
        if (tileEntity instanceof FluidCableTileEntity) {
            return ConnectionType.BOTH;
        } else if (tileEntity != null) {
            // uncomment later
//            IFluidStorage fluid = FluidUtils.getFluidFromSideOrNull(tileEntity, side.getOpposite());
//            if (fluid != null) {
//                if (fluid.canExtract()) {
//                    return current == ConnectionType.NONE ? ConnectionType.IN : current;
//                } else if (fluid.canReceive()) {
//                    return current == ConnectionType.NONE ? ConnectionType.OUT : current;
//                }
//            }
        }
        return ConnectionType.NONE;
    }

    public static ConnectionType getConnection(BlockState state, Direction side) {
        return state.getValue(FACING_TO_PROPERTY_MAP.get(side));
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> BlockState cycleProperty(BlockState state, Property<T> propertyIn) {
        T value = getAdjacentValue(propertyIn.getPossibleValues(), state.getValue(propertyIn));
        if (value == ConnectionType.NONE)
            value = (T) ConnectionType.IN;
        return state.setValue(propertyIn, value);
    }

    private static <T> T getAdjacentValue(Iterable<T> iterable, @Nullable T t) {
        return Util.findNextInIterable(iterable, t);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FluidCableTileEntity();
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

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
                                  BlockPos currentPos, BlockPos facingPos) {
        if (worldIn.getBlockEntity(facingPos) instanceof FluidCableTileEntity)
            FluidCableNetworkManager.invalidateNetwork(worldIn, currentPos);

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

    @Override
    public ActionResultType onWrench(ItemUseContext context) {
        BlockPos pos = context.getClickedPos();
        World world = context.getLevel();
        BlockState state = world.getBlockState(pos);
        Vector3d relative = context.getClickLocation().subtract(pos.getX(), pos.getY(), pos.getZ());

        Direction side = getClickedConnection(relative);
        if (side != null) {
            TileEntity other = world.getBlockEntity(pos.relative(side));
            if (!(other instanceof FluidCableTileEntity)) {
                BlockState state1 = cycleProperty(state, FACING_TO_PROPERTY_MAP.get(side));
                world.setBlock(pos, state1, 18);
                FluidCableNetworkManager.invalidateNetwork(world, pos);
                return ActionResultType.SUCCESS;
            }
        }

        return ActionResultType.PASS;
    }
}
