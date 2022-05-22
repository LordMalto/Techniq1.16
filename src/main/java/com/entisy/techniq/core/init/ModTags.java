package com.entisy.techniq.core.init;

import com.entisy.techniq.Techniq;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class ModTags {
	
	public static final class Blocks {
		
		public static final INamedTag<Block> ORES_COPPER = forge("ores/copper");
		public static final INamedTag<Block> ORES_IRON = forge("ores/iron");
		public static final INamedTag<Block> ORES_GOLD = forge("ores/gold");
		public static final INamedTag<Block> ORES_COAL = forge("ores/coal");
		public static final INamedTag<Block> ORES_DIAMOND = forge("ores/diamond");
		public static final INamedTag<Block> ORES_EMERALD = forge("ores/emerald");
		public static final INamedTag<Block> ORES_REDSTONE = forge("ores/redstone");
		public static final INamedTag<Block> ORES_LAPIS = forge("ores/lapis");
		public static final INamedTag<Block> ORES_QUARTZ = forge("ores/quartz");
		public static final INamedTag<Block> STORAGE_BLOCKS_COPPER = forge("storage_blocks/copper");
		public static final INamedTag<Block> STORAGE_BLOCKS_STEEL = forge("storage_blocks/steel");

		public static final INamedTag<Block> MACHINE_BLOCKS = mod("machine_blocks");

		private static INamedTag<Block> forge(String path) {
			return BlockTags.bind(new ResourceLocation("forge", path).toString());
		}

		private static INamedTag<Block> mod(String path) {
			return BlockTags.bind(new ResourceLocation(Techniq.MOD_ID, path).toString());
		}
	}

	public static final class Items {

		public static final INamedTag<Item> ORES_COPPER = forge("ores/copper");
		public static final INamedTag<Item> ORES_IRON = forge("ores/iron");
		public static final INamedTag<Item> ORES_GOLD = forge("ores/gold");
		public static final INamedTag<Item> ORES_COAL = forge("ores/coal");
		public static final INamedTag<Item> ORES_DIAMOND = forge("ores/diamond");
		public static final INamedTag<Item> ORES_EMERALD = forge("ores/emerald");
		public static final INamedTag<Item> ORES_REDSTONE = forge("ores/redstone");
		public static final INamedTag<Item> ORES_LAPIS = forge("ores/lapis");
		public static final INamedTag<Item> ORES_QUARTZ = forge("ores/quartz");
		public static final INamedTag<Item> STORAGE_BLOCKS_COPPER = forge("storage_blocks/copper");
		public static final INamedTag<Item> STORAGE_BLOCKS_STEEL = forge("storage_blocks/steel");

		public static final INamedTag<Item> INGOTS_COPPER = forge("ingots/copper");
		public static final INamedTag<Item> INGOTS_REDSTONE_ALLOY = forge("ingots/redstone_alloy");
		public static final INamedTag<Item> PLATES_COPPER = forge("plates/copper");
		public static final INamedTag<Item> PLATES_IRON = forge("plates/iron");
		public static final INamedTag<Item> PLATES_GOLD = forge("plates/gold");
		public static final INamedTag<Item> PLATES_DIAMOND = forge("plates/diamond");
		public static final INamedTag<Item> RODS_COPPER = forge("rods/copper");
		public static final INamedTag<Item> RODS_IRON = forge("rods/iron");
		public static final INamedTag<Item> RODS_STEEL = forge("rods/steel");
		public static final INamedTag<Item> PLATES_STEEL = forge("plates/steel");
		public static final INamedTag<Item> INGOTS_STEEL = forge("ingots/steel");

		public static final INamedTag<Item> MACHINE_BLOCKS = mod("machine_blocks");
		
		private static INamedTag<Item> forge(String path) {
			return ItemTags.bind(new ResourceLocation("forge", path).toString());
		}
		
		private static INamedTag<Item> mod(String path) {
			return ItemTags.bind(new ResourceLocation(Techniq.MOD_ID, path).toString());
		}
	}
}
