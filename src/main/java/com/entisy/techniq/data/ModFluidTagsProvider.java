package com.entisy.techniq.data;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.core.init.ModFluids;
import com.entisy.techniq.core.init.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModFluidTagsProvider extends FluidTagsProvider {

    public ModFluidTagsProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen, Techniq.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ModTags.Fluids.FLUIDS_OIL).add(ModFluids.OIL_FLUID.get());
    }
}
