package com.entisy.techniq.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class SteelMachineFrameBlock extends Block {

    public SteelMachineFrameBlock() {
        super(AbstractBlock.Properties.copy(Blocks.IRON_BLOCK));
    }
}
