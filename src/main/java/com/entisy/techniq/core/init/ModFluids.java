package com.entisy.techniq.core.init;

import com.entisy.techniq.Techniq;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids {

    public static final ResourceLocation WATER_STILL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS,
            Techniq.MOD_ID);

    // OIL
    public static final RegistryObject<FlowingFluid> OIL_FLUID = FLUIDS.register(
            "oil_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.OIL_PROPERTIES));
    public static final RegistryObject<FlowingFluid> OIL_FLOWING = FLUIDS.register(
            "oil_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.OIL_PROPERTIES));
    public static final ForgeFlowingFluid.Properties OIL_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> OIL_FLUID.get(),
            () -> OIL_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL, WATER_FLOWING)
                    .density(15)
                    .luminosity(2)
                    .viscosity(5)
                    .sound(SoundEvents.WATER_AMBIENT)
                    .overlay(WATER_OVERLAY)
                    .color(0xbf542400))
            .slopeFindDistance(2)
            .levelDecreasePerBlock(2)
            .block(() -> ModFluids.OIL_BLOCK.get())
            .bucket(() -> ModItems.OIL_BUCKET.get()
    );
    public static final RegistryObject<FlowingFluidBlock> OIL_BLOCK = ModBlocks.BLOCKS.register(
            "oil",
            () -> new FlowingFluidBlock(
                    () -> ModFluids.OIL_FLUID.get(),
                    AbstractBlock.Properties.of(Material.WATER)
                            .noCollission()
                            .strength(100f)
                            .noDrops()
            )
    );
}
