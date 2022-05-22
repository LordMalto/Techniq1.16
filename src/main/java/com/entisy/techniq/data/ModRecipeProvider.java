package com.entisy.techniq.data;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.block.alloySmelter.recipe.AlloySmelterRecipeBuilder;
import com.entisy.techniq.common.block.metalPress.recipe.MetalPressRecipeBuilder;
import com.entisy.techniq.common.block.electricalFurnace.recipe.ElectricalFurnaceRecipeBuilder;
import com.entisy.techniq.core.init.ModBlocks;
import com.entisy.techniq.core.init.ModItems;
import com.entisy.techniq.core.init.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    private static ResourceLocation modid(String path) {
        return new ResourceLocation(Techniq.MOD_ID, path);
    }

    @Override
    public void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {

        final String mp = "_from_metal_press";
        final String ef = "_from_electrical_furnace";
        final String as = "_from_alloy_smelter";

        ShapelessRecipeBuilder
                .shapeless(ModItems.COPPER_INGOT.get(), 9)
                .requires(ModBlocks.COPPER_BLOCK.get())
                .unlockedBy("has_item", has(ModBlocks.COPPER_BLOCK.get()))
                .save(consumer);
        ShapelessRecipeBuilder
                .shapeless(ModItems.STEEL_INGOT.get(), 9)
                .requires(ModBlocks.STEEL_BLOCK.get())
                .unlockedBy("has_item", has(ModBlocks.STEEL_BLOCK.get()))
                .save(consumer);
        ShapelessRecipeBuilder
                .shapeless(ModItems.COPPER_PLATE.get())
                .requires(ModTags.Items.INGOTS_COPPER)
                .requires(ModItems.HAMMER.get())
                .unlockedBy("has_item", has(ModItems.HAMMER.get()))
                .save(consumer);
        ShapelessRecipeBuilder
                .shapeless(ModItems.STEEL_PLATE.get())
                .requires(ModTags.Items.INGOTS_STEEL)
                .requires(ModItems.HAMMER.get())
                .unlockedBy("has_item", has(ModItems.HAMMER.get()))
                .save(consumer);
        ShapelessRecipeBuilder
                .shapeless(ModItems.IRON_PLATE.get())
                .requires(Items.IRON_INGOT)
                .requires(ModItems.HAMMER.get())
                .unlockedBy("has_item", has(ModItems.HAMMER.get()))
                .save(consumer);
        ShapelessRecipeBuilder
                .shapeless(ModItems.GOLD_PLATE.get())
                .requires(Items.GOLD_INGOT)
                .requires(ModItems.HAMMER.get())
                .unlockedBy("has_item", has(ModItems.HAMMER.get()))
                .save(consumer);
        ShapelessRecipeBuilder
                .shapeless(ModItems.DIAMOND_PLATE.get())
                .requires(Items.DIAMOND)
                .requires(ModItems.HAMMER.get())
                .unlockedBy("has_item", has(ModItems.HAMMER.get()))
                .save(consumer);
        ShapelessRecipeBuilder
                .shapeless(ModItems.IRON_ROD.get())
                .requires(ModTags.Items.PLATES_IRON)
                .requires(ModItems.HAMMER.get())
                .unlockedBy("has_item", has(ModItems.HAMMER.get()))
                .save(consumer);
        ShapelessRecipeBuilder
                .shapeless(ModItems.STEEL_ROD.get())
                .requires(ModTags.Items.PLATES_STEEL)
                .requires(ModItems.HAMMER.get())
                .unlockedBy("has_item", has(ModItems.HAMMER.get()))
                .save(consumer);
        ShapelessRecipeBuilder
                .shapeless(ModItems.COPPER_ROD.get())
                .requires(ModTags.Items.PLATES_COPPER)
                .requires(ModItems.HAMMER.get())
                .unlockedBy("has_item", has(ModItems.HAMMER.get()))
                .save(consumer);

        // METAL PRESS
        // PLATES
        MetalPressRecipeBuilder
                .metalPress(ModItems.COPPER_PLATE.get())
                .requires(ModTags.Items.INGOTS_COPPER)
                .requiredEnergy(400)
                .unlockedBy("has_item", has(ModTags.Items.INGOTS_COPPER))
                .save(consumer, "copper_plate" + mp);
        MetalPressRecipeBuilder
                .metalPress(ModItems.IRON_PLATE.get())
                .requires(Items.IRON_INGOT)
                .requiredEnergy(400)
                .unlockedBy("has_item", has(Items.IRON_INGOT))
                .save(consumer, "iron_plate" + mp);
        MetalPressRecipeBuilder
                .metalPress(ModItems.GOLD_PLATE.get())
                .requires(Items.GOLD_INGOT)
                .requiredEnergy(400)
                .unlockedBy("has_item", has(Items.GOLD_INGOT))
                .save(consumer, "gold_plate" + mp);
        MetalPressRecipeBuilder
                .metalPress(ModItems.DIAMOND_PLATE.get())
                .requires(Items.DIAMOND)
                .requiredEnergy(400)
                .unlockedBy("has_item", has(Items.DIAMOND))
                .save(consumer, "diamond_plate" + mp);
        MetalPressRecipeBuilder
                .metalPress(ModItems.STEEL_PLATE.get())
                .requires(ModTags.Items.INGOTS_STEEL)
                .requiredEnergy(400)
                .unlockedBy("has_item", has(ModTags.Items.INGOTS_STEEL))
                .save(consumer, "steel_plate" + mp);

        // RODS
        MetalPressRecipeBuilder
                .metalPress(ModItems.COPPER_ROD.get())
                .requires(ModTags.Items.PLATES_COPPER)
                .requiredEnergy(300)
                .unlockedBy("has_item", has(ModItems.COPPER_INGOT.get()))
                .save(consumer, "copper_rod" + mp);
        MetalPressRecipeBuilder
                .metalPress(ModItems.IRON_ROD.get())
                .requires(ModTags.Items.PLATES_IRON)
                .requiredEnergy(300)
                .unlockedBy("has_item", has(Items.IRON_INGOT))
                .save(consumer, "iron_rod" + mp);
        MetalPressRecipeBuilder
                .metalPress(ModItems.STEEL_ROD.get())
                .requires(ModTags.Items.PLATES_STEEL)
                .requiredEnergy(300)
                .unlockedBy("has_item", has(Items.GOLD_INGOT))
                .save(consumer, "steel_rop" + mp);

        // ELECTRICAL FURNACE
        ElectricalFurnaceRecipeBuilder
                .smelting(ModItems.COPPER_INGOT.get())
                .requires(ModTags.Items.ORES_COPPER)
                .unlockedBy("has_item", has(ModTags.Items.ORES_COPPER))
                .save(consumer, "copper_ingot" + ef);
        ElectricalFurnaceRecipeBuilder
                .smelting(Items.IRON_INGOT)
                .requires(ModTags.Items.ORES_IRON)
                .unlockedBy("has_item", has(ModTags.Items.ORES_IRON))
                .save(consumer, "iron_ingot" + ef);
        ElectricalFurnaceRecipeBuilder
                .smelting(Items.GOLD_INGOT)
                .requires(ModTags.Items.ORES_GOLD)
                .unlockedBy("has_item", has(ModTags.Items.ORES_GOLD))
                .save(consumer, "gold_ingot" + ef);
        ElectricalFurnaceRecipeBuilder
                .smelting(Items.COAL)
                .requires(ModTags.Items.ORES_COAL)
                .unlockedBy("has_item", has(ModTags.Items.ORES_COAL))
                .save(consumer, "coal" + ef);
        ElectricalFurnaceRecipeBuilder
                .smelting(Items.QUARTZ)
                .requires(ModTags.Items.ORES_QUARTZ)
                .unlockedBy("has_item", has(ModTags.Items.ORES_QUARTZ))
                .save(consumer, "quartz" + ef);
        ElectricalFurnaceRecipeBuilder
                .smelting(Items.REDSTONE)
                .requires(ModTags.Items.ORES_REDSTONE)
                .unlockedBy("has_item", has(ModTags.Items.ORES_REDSTONE))
                .save(consumer, "redstone" + ef);
        ElectricalFurnaceRecipeBuilder
                .smelting(Items.EMERALD)
                .requires(ModTags.Items.ORES_EMERALD)
                .unlockedBy("has_item", has(ModTags.Items.ORES_EMERALD))
                .save(consumer, "emerald" + ef);
        ElectricalFurnaceRecipeBuilder
                .smelting(Items.DIAMOND)
                .requires(ModTags.Items.ORES_DIAMOND)
                .unlockedBy("has_item", has(ModTags.Items.ORES_DIAMOND))
                .save(consumer, "diamond" + ef);
        ElectricalFurnaceRecipeBuilder
                .smelting(Items.LAPIS_LAZULI)
                .requires(ModTags.Items.ORES_LAPIS)
                .unlockedBy("has_item", has(ModTags.Items.ORES_LAPIS))
                .save(consumer, "lapis" + ef);

        // HAMMER
        ShapedRecipeBuilder.shaped(ModItems.HAMMER.get()).define('I', Items.IRON_INGOT).define('S', Items.STICK)
                .pattern(" I ")
                .pattern(" SI")
                .pattern("S  ").unlockedBy("has_item", has(ModItems.HAMMER.get())).save(consumer);

        // ALLOY SMELTER
        AlloySmelterRecipeBuilder
                .smelting(ModItems.REDSTONE_ALLOY_INGOT.get())
                .requires(Items.REDSTONE, 3, Items.IRON_INGOT, 1)
                .unlockedBy("has_item", has(Items.REDSTONE))
                .save(consumer, "redstone_alloy_ingot" + as);
        AlloySmelterRecipeBuilder
                .smelting(ModItems.STEEL_INGOT.get())
                .requires(Items.IRON_INGOT, 2, Items.COAL, 1)
                .unlockedBy("has_item", has(Items.IRON_INGOT))
                .save(consumer, "steel_ingot" + as);

//        ShapelessRecipeBuilder.shapeless(ModItems.ALUMINUM_INGOT.get(), 9).requires(ModBlocks.ALUMINUM_BLOCK.get()).unlockedBy("has_item", has(ModItems.ALUMINUM_INGOT.get())).save(consumer);
//
//        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ALUMINUM_ORE.get()), ModItems.ALUMINUM_INGOT.get(), 0.7F, 100).unlockedBy("has_item", has(ModBlocks.ALUMINUM_ORE.get())).save(consumer, modid("aluminium_ore_blasting"));
//
//        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ALUMINUM_ORE.get()), ModItems.ALUMINUM_INGOT.get(), 0.7F, 200).unlockedBy("has_item", has(ModBlocks.ALUMINUM_ORE.get())).save(consumer, modid("aluminium_ore_smelting"));
//
        ShapedRecipeBuilder.shaped(ModItems.MACHINE_FRAME.get()).define('R', ModItems.IRON_ROD.get()).define('P', ModItems.IRON_PLATE.get())
                .pattern("PRP")
                .pattern("R R")
                .pattern("PRP").unlockedBy("has_item", has(ModItems.IRON_ROD.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.HEAVY_MACHINE_FRAME.get()).define('R', ModItems.STEEL_ROD.get()).define('P', ModItems.STEEL_PLATE.get())
                .pattern("PRP")
                .pattern("R R")
                .pattern("PRP").unlockedBy("has_item", has(ModItems.STEEL_ROD.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.ALLOY_SMELTER.get()).define('B', Blocks.IRON_BLOCK).define('I', Items.IRON_INGOT).define('M', ModItems.MACHINE_FRAME.get())
                .pattern("BIB")
                .pattern("IMI")
                .pattern("BIB").unlockedBy("has_item", has(ModItems.MACHINE_FRAME.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.ELECTRICAL_FURNACE.get()).define('B', Blocks.IRON_BLOCK).define('I', ModItems.STEEL_INGOT.get()).define('M', ModItems.MACHINE_FRAME.get()).define('F', Blocks.FURNACE)
                .pattern("BIB")
                .pattern("IMI")
                .pattern("BFB").unlockedBy("has_item", has(ModItems.MACHINE_FRAME.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.FURNACE_GENERATOR.get()).define('B', Blocks.IRON_BLOCK).define('I', ModItems.STEEL_INGOT.get()).define('M', ModItems.HEAVY_MACHINE_FRAME.get()).define('F', Blocks.FURNACE)
                .pattern("BIB")
                .pattern("IMI")
                .pattern("BFB").unlockedBy("has_item", has(ModItems.MACHINE_FRAME.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.METAL_PRESS.get()).define('S', ModBlocks.STEEL_BLOCK.get()).define('B', Blocks.IRON_BLOCK).define('I', ModItems.STEEL_INGOT.get()).define('H', ModItems.HEAVY_MACHINE_FRAME.get())
                .pattern("SIS")
                .pattern("IHI")
                .pattern("BIB").unlockedBy("has_item", has(ModItems.MACHINE_FRAME.get())).save(consumer);

        // METAL BLOCKS
        ShapedRecipeBuilder.shaped(ModBlocks.COPPER_BLOCK.get()).define('I', ModItems.COPPER_INGOT.get())
                .pattern("III")
                .pattern("III")
                .pattern("III").unlockedBy("has_item", has(ModItems.COPPER_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.STEEL_BLOCK.get()).define('I', ModItems.STEEL_INGOT.get())
                .pattern("III")
                .pattern("III")
                .pattern("III").unlockedBy("has_item", has(ModItems.STEEL_INGOT.get())).save(consumer);
//
//        ShapedRecipeBuilder.shaped(ModItems.ALUMINUM_ROD.get()).define('#', ModItems.ALUMINUM_PLATE.get()).pattern("#").pattern("#").unlockedBy("has_item", has(ModItems.ALUMINUM_PLATE.get())).save(consumer);
//
//        ShapedRecipeBuilder.shaped(ModBlocks.COPPER_FENCE.get()).define('R', ModItems.COPPER_ROD.get()).define('I', ModItems.COPPER_INGOT.get()).pattern("IRI").pattern("IRI").unlockedBy("has_item", has(ModItems.COPPER_ROD.get())).save(consumer);
//
//        ShapedRecipeBuilder.shaped(ModBlocks.MACHINE_BLOCK.get()).define('R', ModItems.IRON_ROD.get()).define('P', ModItems.IRON_PLATE.get()).define('I', Items.IRON_INGOT).pattern("PRP").pattern("RIR").pattern("PRP").unlockedBy("has_item", has(ModItems.IRON_ROD.get())).save(consumer);
//
//        ShapedRecipeBuilder.shaped(ModItems.HAMMER.get()).define('I', Items.IRON_INGOT).define('S', Items.STICK).pattern(" I ").pattern(" SI").pattern("S  ").unlockedBy("has_item", has(Items.IRON_INGOT)).save(consumer);
    }
}
