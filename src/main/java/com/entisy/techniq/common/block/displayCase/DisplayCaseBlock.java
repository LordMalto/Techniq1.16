//package com.entisy.techniq.common.block.displayCase;
//
//import java.util.stream.Stream;
//
//import com.entisy.techniq.core.init.ModTileEntityTypes;
//
//import net.minecraft.block.AbstractBlock;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.material.Material;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.item.BlockItemUseContext;
//import net.minecraft.state.DirectionProperty;
//import net.minecraft.state.StateContainer.Builder;
//import net.minecraft.state.properties.BlockStateProperties;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.ActionResultType;
//import net.minecraft.util.Direction;
//import net.minecraft.util.Hand;
//import net.minecraft.util.Mirror;
//import net.minecraft.util.Rotation;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.BlockRayTraceResult;
//import net.minecraft.util.math.shapes.IBooleanFunction;
//import net.minecraft.util.math.shapes.ISelectionContext;
//import net.minecraft.util.math.shapes.VoxelShape;
//import net.minecraft.util.math.shapes.VoxelShapes;
//import net.minecraft.world.IBlockReader;
//import net.minecraft.world.IWorld;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.network.NetworkHooks;
//
//public class DisplayCaseBlock extends Block {
//
//	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
//
//	/*
//	 * VOXEL SHAPE
//	 */
//	private static final VoxelShape NORTH = Stream
//			.of(Block.box(3, 9, 3, 13, 10, 13), Block.box(6, 1, 6, 10, 9, 10), Block.box(3, 0, 3, 13, 1, 13),
//					Block.box(3, 10, 3, 13, 11, 4))
//			.reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
//	private static final VoxelShape EAST = Stream
//			.of(Block.box(3, 9, 3, 13, 10, 13), Block.box(6, 1, 6, 10, 9, 10), Block.box(3, 0, 3, 13, 1, 13),
//					Block.box(12, 10, 3, 13, 11, 13))
//			.reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
//	private static final VoxelShape SOUTH = Stream
//			.of(Block.box(3, 9, 3, 13, 10, 13), Block.box(6, 1, 6, 10, 9, 10), Block.box(3, 0, 3, 13, 1, 13),
//					Block.box(3, 10, 12, 13, 11, 13))
//			.reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
//	private static final VoxelShape WEST = Stream
//			.of(Block.box(3, 9, 3, 13, 10, 13), Block.box(6, 1, 6, 10, 9, 10), Block.box(3, 0, 3, 13, 1, 13),
//					Block.box(3, 10, 3, 4, 11, 13))
//			.reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
//	/*
//	 * END
//	 */
//
//	public DisplayCaseBlock() {
//		super(AbstractBlock.Properties.of(Material.METAL).strength(15f).sound(SoundType.METAL));
//		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
//	}
//
//	@Override
//	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
//		switch (state.getValue(FACING)) {
//		case EAST:
//			return EAST;
//		case SOUTH:
//			return SOUTH;
//		case WEST:
//			return WEST;
//		default:
//			return NORTH;
//		}
//	}
//
//	@Override
//	public boolean hasTileEntity(BlockState state) {
//		return true;
//	}
//
//	@SuppressWarnings("deprecation")
//	@Override
//	public BlockState mirror(BlockState state, Mirror mirror) {
//		return state.rotate(mirror.getRotation(state.getValue(FACING)));
//	}
//
//	@Override
//	public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
//		return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
//	}
//
//	@Override
//	public BlockState getStateForPlacement(BlockItemUseContext context) {
//		return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
//	}
//
//	@Override
//	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
//		super.createBlockStateDefinition(builder);
//		builder.add(FACING);
//	}
//
//	@Override
//	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
//		return ModTileEntityTypes.DISPLAY_CASE_TILE_ENTITY_TYPE.get().create();
//	}
//
//	@SuppressWarnings("deprecation")
//	@Override
//	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
//			BlockRayTraceResult rayTrace) {
//		if (!world.isClientSide()) {
//			TileEntity tileEntity = world.getBlockEntity(pos);
//			if (tileEntity instanceof DisplayCaseTileEntity) {
//				NetworkHooks.openGui((ServerPlayerEntity) player, (DisplayCaseTileEntity) tileEntity, pos);
//			}
//		}
//		return super.use(state, world, pos, player, hand, rayTrace);
//	}
//}
