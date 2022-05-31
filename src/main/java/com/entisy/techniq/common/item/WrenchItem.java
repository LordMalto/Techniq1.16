package com.entisy.techniq.common.item;

import com.entisy.techniq.api.IWrenchable;
import com.entisy.techniq.core.tab.TechniqTab;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.Property;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class WrenchItem extends Item {

    public WrenchItem() {
        super(new Properties().tab(TechniqTab.TECHNIQ_TAB).stacksTo(1));
    }

    private static <T extends Comparable<T>> BlockState cycleProperty(BlockState state, Property<T> propertyIn) {
        return state.setValue(propertyIn, getAdjacentValue(propertyIn.getPossibleValues(), state.getValue(propertyIn)));
    }

    private static <T> T getAdjacentValue(Iterable<T> iterable, @Nullable T t) {
        return Util.findNextInIterable(iterable, t);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player == null) return ActionResultType.PASS;

        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);

        if (state.getBlock() instanceof IWrenchable) {
            ActionResultType result = ((IWrenchable) state.getBlock()).onWrench(context);
            if (result != ActionResultType.PASS) {
                return result;
            }
        }

        if (player.isCrouching() && state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            BlockState state1 = cycleProperty(state, BlockStateProperties.HORIZONTAL_FACING);
            world.setBlock(pos, state1, 18);
            return ActionResultType.SUCCESS;
        }

        return super.useOn(context);
    }
}
