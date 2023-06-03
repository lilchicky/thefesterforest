package com.gmail.thelilchicken01.tff.dataGen.server;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.dataGen.BaseLootTableProvider;
import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class ModLootTableProvider extends BaseLootTableProvider {

	public ModLootTableProvider(DataGenerator gen) {
		super(gen);
	}

	@Override
	protected void addTables() {
		//createSimpleTable(BlockInit.rotting_bricks.get().getRegistryName().getPath(), BlockInit.rotting_bricks.get());
		dropSelf(BlockInit.ROTTING_BRICKS.get());
		dropSelf(BlockInit.ROTTING_FLOWER.get());
		dropSelf(BlockInit.ROTTING_STONE.get());
		dropSelf(BlockInit.FESTER_ORE.get());
		dropSelf(BlockInit.SLIMY_LOG.get());
		dropSelf(BlockInit.SLIMY_SAPLING.get());
		dropSelf(BlockInit.ROTTINGWOOD_SAPLING.get());
		dropSelf(BlockInit.SLIMY_PLANKS.get());
		dropSelf(BlockInit.SLIMY_WOOD.get());
		dropSelf(BlockInit.STRIPPED_SLIMY_LOG.get());
		dropSelf(BlockInit.STRIPPED_SLIMY_WOOD.get());
		dropSelf(BlockInit.CRACKED_ROTTING_STONE.get());
		dropSelf(BlockInit.MOSSY_ROTTING_STONE.get());
		dropSelf(BlockInit.ROTTING_STONE_BRICKS.get());
		dropSelf(BlockInit.ROTTING_STONE_BRICK_SLAB.get());
		dropSelf(BlockInit.ROTTING_STONE_BRICK_STAIRS.get());
		dropSelf(BlockInit.ROTTING_STONE_BRICK_WALL.get());
		dropSelf(BlockInit.ROTTINGWOOD_FENCE.get());
		dropSelf(BlockInit.ROTTINGWOOD_FENCE_GATE.get());
		dropSelf(BlockInit.ROTTINGWOOD_STAIRS.get());
		dropSelf(BlockInit.ROTTINGWOOD_SLAB.get());
		dropSelf(BlockInit.SLIMY_STAIRS.get());
		dropSelf(BlockInit.SLIMY_SLAB.get());
		dropSelf(BlockInit.SLIMY_FENCE.get());
		dropSelf(BlockInit.SLIMY_FENCE_GATE.get());
		dropSelf(BlockInit.REETLELIGHT.get());
		dropSelf(BlockInit.CRUSHED_ROTTING_STONE.get());
		dropSelf(BlockInit.ROTTING_GRAVE_DIRT.get());
		dropSelf(BlockInit.SICKENING_FLOWER.get());
		dropSelf(BlockInit.SLIMY_FLOWER.get());
		dropSelf(BlockInit.FESTER_BRICK_STAIRS.get());
		dropSelf(BlockInit.FESTER_BRICK_SLAB.get());
		dropSelf(BlockInit.WEEPING_GRASS.get());
		dropSelf(BlockInit.SOUL_ROT.get());
		dropSelf(BlockInit.ROTTING_SAND.get());
		dropSelf(BlockInit.COMPRESSED_ROTTING_SAND.get());
		dropSelf(BlockInit.COMPRESSED_ROTTING_SAND_STAIRS.get());
		dropSelf(BlockInit.COMPRESSED_ROTTING_SAND_SLAB.get());
		dropSelf(BlockInit.COMPRESSED_ROTTING_SAND_WALL.get());
		dropSelf(BlockInit.CRACKED_ROTTING_STONE_BRICKS.get());
		dropSelf(BlockInit.FESTER_BRICK_WALL.get());
		dropSelf(BlockInit.ROTTING_BONE_BLOCK.get());
		
		silkTouch(BlockInit.ROTTING_GLASS.get(), BlockInit.ROTTING_GLASS.get().asItem(), 0, 0);
		
		//silkTouch(BlockInit.volatileOre.get(), Items.BLAZE_POWDER, 1, 4);
		
	}
	
	protected void silkTouch(Block block, Item silk, int fortuneMin, int fortuneMax) {
		add(block, createSilkTouchTable(block.getRegistryName().getPath(),
				block, silk, fortuneMin, fortuneMax));
	}
	
	protected void dropSelf(Block block) {
		add(block, createSimpleTable(block.getRegistryName().getPath(), block));
	}
	
}
