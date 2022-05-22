package com.entisy.techniq.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class MachineFrameItem extends Item {

    public MachineFrameItem(Properties properties) {
        super(properties);
    }

    private static final VoxelShape ALL = Stream.of(
            Block.box(2, 0, 0, 14, 2, 2),
            Block.box(2, 0, 14, 14, 2, 16),
            Block.box(0, 0, 2, 2, 2, 14),
            Block.box(14, 0, 2, 16, 2, 14),
            Block.box(0, 0, 0, 2, 16, 2),
            Block.box(14, 0, 0, 16, 16, 2),
            Block.box(14, 0, 14, 16, 16, 16),
            Block.box(0, 0, 14, 2, 16, 16),
            Block.box(0, 14, 2, 2, 16, 14),
            Block.box(14, 14, 2, 16, 16, 14),
            Block.box(2, 14, 14, 14, 16, 16),
            Block.box(2, 14, 0, 14, 16, 2)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return ALL;
    }
}
