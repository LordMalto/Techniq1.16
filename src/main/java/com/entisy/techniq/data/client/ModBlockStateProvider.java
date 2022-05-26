package com.entisy.techniq.data.client;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.SimpleBlock;
import com.entisy.techniq.core.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Techniq.MOD_ID, exFileHelper);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    protected void registerStatesAndModels() {
        ModBlocks.getSimpleBlocks().forEach(b -> simpleBlock((Block) b));
        ModBlocks.getComplexBlocks().forEach(b -> complexBlock((Block) b));
        ModBlocks.getSemiComplexBlocks().forEach(b -> semiComplexBlock((Block) b));
    }

    private void semiComplexBlock(Block block) {
        horizontalBlock(block, models().orientableWithBottom(block.getRegistryName().getPath(),
                getSide(block), getSide(block), getBottom(block), getTop(block)));
    }

    private void complexBlock(Block block) {
        horizontalBlock(block, models().orientableWithBottom(block.getRegistryName().getPath(),
                        getSide(block), getFront(block), getBottom(block), getTop(block)));
    }

    private ResourceLocation getSide(Block block) {
        System.out.println(block.getRegistryName());
        return modLoc("block/" + block.getRegistryName().toString().replace(Techniq.MOD_ID + ":", "") + "/side");
    }

    private ResourceLocation getFront(Block block) {
        return modLoc("block/" + block.getRegistryName().toString().replace(Techniq.MOD_ID + ":", "") + "/front");
    }

    private ResourceLocation getBottom(Block block) {
        return modLoc("block/" + block.getRegistryName().toString().replace(Techniq.MOD_ID + ":", "") + "/bottom");
    }

    private ResourceLocation getTop(Block block) {
        return modLoc("block/" + block.getRegistryName().toString().replace(Techniq.MOD_ID + ":", "") + "/top");
    }
}
