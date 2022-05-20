package com.entisy.techniq.data;

import com.entisy.techniq.Techniq;
import com.entisy.techniq.common.recipe.alloySmelter.AlloySmelterRecipeBuilder;
import com.entisy.techniq.common.recipe.metalPress.MetalPressRecipeBuilder;
import com.entisy.techniq.common.recipe.electricalFurnace.ElectricalFurnaceRecipeBuilder;
import com.entisy.techniq.core.init.BlockInit;
import com.entisy.techniq.core.init.ItemInit;
import com.entisy.techniq.core.init.TagsInit;
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

        ShapelessRecipeBuilder.shapeless(ItemInit.COPPER_INGOT.get(), 9).requires(BlockInit.COPPER_BLOCK.get())
                .unlockedBy("has_item", has(BlockInit.COPPER_BLOCK.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(ItemInit.STEEL_INGOT.get(), 9).requires(BlockInit.STEEL_BLOCK.get())
                .unlockedBy("has_item", has(BlockInit.STEEL_BLOCK.get())).save(consumer);


        ShapelessRecipeBuilder.shapeless(ItemInit.COPPER_PLATE.get()).requires(TagsInit.Items.INGOTS_COPPER)
                .requires(ItemInit.HAMMER.get()).unlockedBy("has_item", has(ItemInit.HAMMER.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(ItemInit.STEEL_PLATE.get()).requires(TagsInit.Items.INGOTS_STEEL)
                .requires(ItemInit.HAMMER.get()).unlockedBy("has_item", has(ItemInit.HAMMER.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(ItemInit.IRON_PLATE.get()).requires(Items.IRON_INGOT)
                .requires(ItemInit.HAMMER.get()).unlockedBy("has_item", has(ItemInit.HAMMER.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(ItemInit.GOLD_PLATE.get()).requires(Items.GOLD_INGOT)
                .requires(ItemInit.HAMMER.get()).unlockedBy("has_item", has(ItemInit.HAMMER.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(ItemInit.DIAMOND_PLATE.get()).requires(Items.DIAMOND)
                .requires(ItemInit.HAMMER.get()).unlockedBy("has_item", has(ItemInit.HAMMER.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(ItemInit.IRON_ROD.get()).requires(TagsInit.Items.PLATES_IRON)
                .requires(ItemInit.HAMMER.get()).unlockedBy("has_item", has(ItemInit.HAMMER.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(ItemInit.STEEL_ROD.get()).requires(TagsInit.Items.PLATES_STEEL)
                .requires(ItemInit.HAMMER.get()).unlockedBy("has_item", has(ItemInit.HAMMER.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(ItemInit.COPPER_ROD.get()).requires(TagsInit.Items.PLATES_COPPER)
                .requires(ItemInit.HAMMER.get()).unlockedBy("has_item", has(ItemInit.HAMMER.get())).save(consumer);

        // METAL PRESS
        // PLATES
        MetalPressRecipeBuilder.metalPress(ItemInit.COPPER_PLATE.get()).requires(TagsInit.Items.INGOTS_COPPER)
                .unlockedBy("has_item", has(TagsInit.Items.INGOTS_COPPER)).save(consumer, "copper_plate" + mp);

        MetalPressRecipeBuilder.metalPress(ItemInit.IRON_PLATE.get()).requires(Items.IRON_INGOT)
                .unlockedBy("has_item", has(Items.IRON_INGOT)).save(consumer, "iron_plate" + mp);

        MetalPressRecipeBuilder.metalPress(ItemInit.GOLD_PLATE.get()).requires(Items.GOLD_INGOT)
                .unlockedBy("has_item", has(Items.GOLD_INGOT)).save(consumer, "gold_plate" + mp);

        MetalPressRecipeBuilder.metalPress(ItemInit.DIAMOND_PLATE.get()).requires(Items.DIAMOND)
                .unlockedBy("has_item", has(Items.DIAMOND)).save(consumer, "diamond_plate" + mp);

        MetalPressRecipeBuilder.metalPress(ItemInit.STEEL_PLATE.get()).requires(TagsInit.Items.INGOTS_STEEL)
                .unlockedBy("has_item", has(TagsInit.Items.INGOTS_STEEL)).save(consumer, "steel_plate" + mp);

        // RODS
        MetalPressRecipeBuilder.metalPress(ItemInit.COPPER_ROD.get()).requires(TagsInit.Items.PLATES_COPPER)
                .unlockedBy("has_item", has(ItemInit.COPPER_INGOT.get())).save(consumer, "copper_rod" + mp);

        MetalPressRecipeBuilder.metalPress(ItemInit.IRON_ROD.get()).requires(TagsInit.Items.PLATES_IRON)
                .unlockedBy("has_item", has(Items.IRON_INGOT)).save(consumer, "iron_rod" + mp);

        MetalPressRecipeBuilder.metalPress(ItemInit.STEEL_ROD.get()).requires(TagsInit.Items.PLATES_STEEL)
                .unlockedBy("has_item", has(Items.GOLD_INGOT)).save(consumer, "steel_rop" + mp);

        // ELECTRICAL FURNACE
        ElectricalFurnaceRecipeBuilder.smelting(ItemInit.COPPER_INGOT.get()).requires(TagsInit.Items.ORES_COPPER)
                .unlockedBy("has_item", has(TagsInit.Items.ORES_COPPER)).save(consumer, "copper_ingot" + ef);

        ElectricalFurnaceRecipeBuilder.smelting(Items.IRON_INGOT).requires(TagsInit.Items.ORES_IRON)
                .unlockedBy("has_item", has(TagsInit.Items.ORES_IRON)).save(consumer, "iron_ingot" + ef);

        ElectricalFurnaceRecipeBuilder.smelting(Items.GOLD_INGOT).requires(TagsInit.Items.ORES_GOLD)
                .unlockedBy("has_item", has(TagsInit.Items.ORES_GOLD)).save(consumer, "gold_ingot" + ef);

        ElectricalFurnaceRecipeBuilder.smelting(Items.COAL).requires(TagsInit.Items.ORES_COAL)
                .unlockedBy("has_item", has(TagsInit.Items.ORES_COAL)).save(consumer, "coal" + ef);

        ElectricalFurnaceRecipeBuilder.smelting(Items.QUARTZ).requires(TagsInit.Items.ORES_QUARTZ)
                .unlockedBy("has_item", has(TagsInit.Items.ORES_QUARTZ)).save(consumer, "quartz" + ef);

        ElectricalFurnaceRecipeBuilder.smelting(Items.REDSTONE).requires(TagsInit.Items.ORES_REDSTONE)
                .unlockedBy("has_item", has(TagsInit.Items.ORES_REDSTONE)).save(consumer, "redstone" + ef);

        ElectricalFurnaceRecipeBuilder.smelting(Items.EMERALD).requires(TagsInit.Items.ORES_EMERALD)
                .unlockedBy("has_item", has(TagsInit.Items.ORES_EMERALD)).save(consumer, "emerald" + ef);

        ElectricalFurnaceRecipeBuilder.smelting(Items.DIAMOND).requires(TagsInit.Items.ORES_DIAMOND)
                .unlockedBy("has_item", has(TagsInit.Items.ORES_DIAMOND)).save(consumer, "diamond" + ef);

        ElectricalFurnaceRecipeBuilder.smelting(Items.LAPIS_LAZULI).requires(TagsInit.Items.ORES_LAPIS)
                .unlockedBy("has_item", has(TagsInit.Items.ORES_LAPIS)).save(consumer, "lapis" + ef);

        // HAMMER
        ShapedRecipeBuilder.shaped(ItemInit.HAMMER.get()).define('I', Items.IRON_INGOT).define('S', Items.STICK)
                .pattern(" I ")
                .pattern(" SI")
                .pattern("S  ").unlockedBy("has_item", has(ItemInit.HAMMER.get())).save(consumer);

        // ALLOY SMELTER
        AlloySmelterRecipeBuilder.smelting(ItemInit.REDSTONE_ALLOY_INGOT.get()).requires(Items.REDSTONE, 3, Items.IRON_INGOT, 1).unlockedBy("has_item", has(Items.REDSTONE)).save(consumer, "redstone_alloy_ingot" + as);
        AlloySmelterRecipeBuilder.smelting(ItemInit.STEEL_INGOT.get()).requires(Items.IRON_INGOT, 2, Items.COAL, 1).unlockedBy("has_item", has(Items.IRON_INGOT)).save(consumer, "steel_ingot" + as);

//        ShapelessRecipeBuilder.shapeless(ModItems.ALUMINUM_INGOT.get(), 9).requires(ModBlocks.ALUMINUM_BLOCK.get()).unlockedBy("has_item", has(ModItems.ALUMINUM_INGOT.get())).save(consumer);
//
//        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ALUMINUM_ORE.get()), ModItems.ALUMINUM_INGOT.get(), 0.7F, 100).unlockedBy("has_item", has(ModBlocks.ALUMINUM_ORE.get())).save(consumer, modid("aluminium_ore_blasting"));
//
//        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ALUMINUM_ORE.get()), ModItems.ALUMINUM_INGOT.get(), 0.7F, 200).unlockedBy("has_item", has(ModBlocks.ALUMINUM_ORE.get())).save(consumer, modid("aluminium_ore_smelting"));
//
        ShapedRecipeBuilder.shaped(ItemInit.MACHINE_FRAME.get()).define('R', ItemInit.IRON_ROD.get()).define('P', ItemInit.IRON_PLATE.get())
                .pattern("PRP")
                .pattern("R R")
                .pattern("PRP").unlockedBy("has_item", has(ItemInit.IRON_ROD.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ItemInit.HEAVY_MACHINE_FRAME.get()).define('R', ItemInit.STEEL_ROD.get()).define('P', ItemInit.STEEL_PLATE.get())
                .pattern("PRP")
                .pattern("R R")
                .pattern("PRP").unlockedBy("has_item", has(ItemInit.STEEL_ROD.get())).save(consumer);
        ShapedRecipeBuilder.shaped(BlockInit.ALLOY_SMELTER.get()).define('B', Blocks.IRON_BLOCK).define('I', Items.IRON_INGOT).define('M', ItemInit.MACHINE_FRAME.get())
                .pattern("BIB")
                .pattern("IMI")
                .pattern("BIB").unlockedBy("has_item", has(ItemInit.MACHINE_FRAME.get())).save(consumer);
        ShapedRecipeBuilder.shaped(BlockInit.ELECTRICAL_FURNACE.get()).define('B', Blocks.IRON_BLOCK).define('I', ItemInit.STEEL_INGOT.get()).define('M', ItemInit.MACHINE_FRAME.get()).define('F', Blocks.FURNACE)
                .pattern("BIB")
                .pattern("IMI")
                .pattern("BFB").unlockedBy("has_item", has(ItemInit.MACHINE_FRAME.get())).save(consumer);
        ShapedRecipeBuilder.shaped(BlockInit.METAL_PRESS.get()).define('S', BlockInit.STEEL_BLOCK.get()).define('B', Blocks.IRON_BLOCK).define('I', ItemInit.STEEL_INGOT.get()).define('H', ItemInit.HEAVY_MACHINE_FRAME.get())
                .pattern("SIS")
                .pattern("IHI")
                .pattern("BIB").unlockedBy("has_item", has(ItemInit.MACHINE_FRAME.get())).save(consumer);

        // METAL BLOCKS
        ShapedRecipeBuilder.shaped(BlockInit.COPPER_BLOCK.get()).define('I', ItemInit.COPPER_INGOT.get())
                .pattern("III")
                .pattern("III")
                .pattern("III").unlockedBy("has_item", has(ItemInit.COPPER_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(BlockInit.STEEL_BLOCK.get()).define('I', ItemInit.STEEL_INGOT.get())
                .pattern("III")
                .pattern("III")
                .pattern("III").unlockedBy("has_item", has(ItemInit.STEEL_INGOT.get())).save(consumer);
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
