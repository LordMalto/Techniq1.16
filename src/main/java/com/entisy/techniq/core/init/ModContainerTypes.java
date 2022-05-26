package com.entisy.techniq.core.init;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.alloySmelter.AlloySmelterContainer;
import com.entisy.techniq.common.block.battery.BatteryContainer;
//import com.entisy.techniq.common.block.displayCase.DisplayCaseContainer;
import com.entisy.techniq.common.block.electricalFurnace.ElectricalFurnaceContainer;
import com.entisy.techniq.common.block.furnaceGenerator.FurnaceGeneratorContainer;
import com.entisy.techniq.common.block.metalPress.MetalPressContainer;

import com.entisy.techniq.common.block.oreMiner.advancedOreMiner.AdvancedOreMinerContainer;
import com.entisy.techniq.common.block.oreMiner.simpleOreMiner.SimpleOreMinerContainer;
import com.entisy.techniq.common.block.refinery.RefineryContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {

	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, Techniq.MOD_ID);

//	public static final RegistryObject<ContainerType<DisplayCaseContainer>> DISPLAY_CASE_CONTAINER_TYPE = CONTAINER_TYPES
//			.register("display_case", () -> IForgeContainerType.create(DisplayCaseContainer::new));

	public static final RegistryObject<ContainerType<MetalPressContainer>> METAL_PRESS_CONTAINER_TYPE = CONTAINER_TYPES
			.register("metal_press", () -> IForgeContainerType.create(MetalPressContainer::new));

	public static final RegistryObject<ContainerType<RefineryContainer>> REFINERY_CONTAINER_TYPE = CONTAINER_TYPES
			.register("refinery", () -> IForgeContainerType.create(RefineryContainer::new));

	public static final RegistryObject<ContainerType<ElectricalFurnaceContainer>> ELECTRICAL_FURNACE_CONTAINER_TYPE = CONTAINER_TYPES
			.register("electrical_furnace", () -> IForgeContainerType.create(ElectricalFurnaceContainer::new));

	public static final RegistryObject<ContainerType<AlloySmelterContainer>> ALLOY_SMELTER_CONTAINER_TYPE = CONTAINER_TYPES
			.register("alloy_smelter", () -> IForgeContainerType.create(AlloySmelterContainer::new));

	public static final RegistryObject<ContainerType<BatteryContainer>> BATTERY_CONTAINER_TYPE = CONTAINER_TYPES
			.register("battery", () -> IForgeContainerType.create(BatteryContainer::new));

	public static final RegistryObject<ContainerType<FurnaceGeneratorContainer>> FURNACE_GENERATOR_CONTAINER_TYPE = CONTAINER_TYPES
			.register("furnace_generator", () -> IForgeContainerType.create(FurnaceGeneratorContainer::new));

	public static final RegistryObject<ContainerType<SimpleOreMinerContainer>> SIMPLE_ORE_MINER_CONTAINER_TYPE = CONTAINER_TYPES
			.register("simple_ore_miner", () -> IForgeContainerType.create(SimpleOreMinerContainer::new));

	public static final RegistryObject<ContainerType<AdvancedOreMinerContainer>> ADVANCED_ORE_MINER_CONTAINER_TYPE = CONTAINER_TYPES
			.register("furnace_container", () -> IForgeContainerType.create(AdvancedOreMinerContainer::new));
}
