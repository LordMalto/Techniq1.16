package com.entisy.techniq.common.block;

import com.entisy.techniq.core.init.TagsInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;

public class MachineBlock extends Block {

    public MachineBlock() {
        super(AbstractBlock.Properties.copy(Blocks.IRON_BLOCK));
    }

    public boolean connectsTo(BlockState state, boolean p_220111_2_, Direction side) {
        Block block = state.getBlock();
        boolean flag = this.isSameBlock(block);
        boolean flag1 = block instanceof MachineBlock;
        boolean flag2 = block instanceof SixWayMachineBlock;
        return !isExceptionForConnection(block) && p_220111_2_ || flag || flag1 || flag2;
    }

    public boolean isSameBlock(Block block) {
        return TagsInit.Blocks.MACHINE_BLOCKS.contains(block);
    }
}
