package com.gmail.thelilchicken01.tff.init;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public final class TagInit {
	
	public static final class Blocks {
		
		public static final TagKey<Block> PORTAL_FRAME_BLOCKS = mod("portal_frame_blocks");
		
		public static final TagKey<Block> SLIMY_WOODS = mod("slimy_woods");
		
		public static final TagKey<Block> ROTTING_WOODS = mod("rotting_woods");
		
		public static final TagKey<Block> TFF_STONES = mod("tff_stones");
		
		private static TagKey<Block> mod(String path) {
			return BlockTags.create(new ResourceLocation(TheFesterForest.MODID, path));
		}
		
	}
	
	public static final class Items {
		
		public static final TagKey<Item> SLIMY_WOODS = mod("slimy_woods");
		
		public static final TagKey<Item> TFF_STONES = mod("tff_stones");
		
		private static TagKey<Item> mod(String path) {
			return ItemTags.create(new ResourceLocation(TheFesterForest.MODID, path));
		}
		
	}
	
}
