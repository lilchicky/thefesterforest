package com.gmail.thelilchicken01.tff.dataGen.server;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.dataGen.BaseLootTableProvider;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

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
		dropSelf(BlockInit.ROTTING_STONE_BRICK_STAIRS.get());
		dropSelf(BlockInit.ROTTING_STONE_BRICK_WALL.get());
		dropSelf(BlockInit.ROTTINGWOOD_FENCE.get());
		dropSelf(BlockInit.ROTTINGWOOD_FENCE_GATE.get());
		dropSelf(BlockInit.ROTTINGWOOD_STAIRS.get());
		dropSelf(BlockInit.SLIMY_STAIRS.get());
		dropSelf(BlockInit.SLIMY_FENCE.get());
		dropSelf(BlockInit.SLIMY_FENCE_GATE.get());
		dropSelf(BlockInit.REETLELIGHT.get());
		dropSelf(BlockInit.CRUSHED_ROTTING_STONE.get());
		dropSelf(BlockInit.ROTTING_GRAVE_DIRT.get());
		dropSelf(BlockInit.SICKENING_FLOWER.get());
		dropSelf(BlockInit.SLIMY_FLOWER.get());
		dropSelf(BlockInit.FESTER_BRICK_STAIRS.get());
		dropSelf(BlockInit.WEEPING_GRASS.get());
		dropSelf(BlockInit.SOUL_ROT.get());
		dropSelf(BlockInit.ROTTING_SAND.get());
		dropSelf(BlockInit.COMPRESSED_ROTTING_SAND.get());
		dropSelf(BlockInit.COMPRESSED_ROTTING_SAND_STAIRS.get());
		dropSelf(BlockInit.COMPRESSED_ROTTING_SAND_WALL.get());
		dropSelf(BlockInit.CRACKED_ROTTING_STONE_BRICKS.get());
		dropSelf(BlockInit.FESTER_BRICK_WALL.get());
		dropSelf(BlockInit.ROTTING_BONE_BLOCK.get());
		dropSelf(BlockInit.BLUE_FESTER_SHROOM_BLOCK.get());
		dropSelf(BlockInit.ORANGE_FESTER_SHROOM_BLOCK.get());
		dropSelf(BlockInit.FESTER_SHROOM_STEM.get());
		dropSelf(BlockInit.CORRODED_SHROOM.get());
		dropSelf(BlockInit.SICKENING_FLOWER.get());
		dropSelf(BlockInit.CUBED_FUNGUS.get());
		dropSelf(BlockInit.CUBED_FUNGUS_STAIRS.get());
		dropSelf(BlockInit.CUBED_FUNGUS_FENCE.get());
		dropSelf(BlockInit.CUBED_FUNGUS_GATE.get());
		dropSelf(BlockInit.FROSTBITTEN_SAPLING.get());
		dropSelf(BlockInit.FROSTBITTEN_PLANKS.get());
		dropSelf(BlockInit.FROSTBITTEN_LOG.get());
		dropSelf(BlockInit.STRIPPED_FROSTBITTEN_LOG.get());
		dropSelf(BlockInit.FROSTBITTEN_WOOD.get());
		dropSelf(BlockInit.STRIPPED_FROSTBITTEN_WOOD.get());
		dropSelf(BlockInit.ICICLE.get());
		dropSelf(BlockInit.FROSTVINE.get());
		dropSelf(BlockInit.FROSTBITTEN_STAIRS.get());
		dropSelf(BlockInit.FROSTBITTEN_FENCE.get());
		dropSelf(BlockInit.FROSTBITTEN_FENCE_GATE.get());
		
		dropSelf(BlockInit.ROTTINGWOOD_BUTTON.get());
		dropSelf(BlockInit.ROTTINGWOOD_TRAPDOOR.get());
		dropSelf(BlockInit.ROTTINGWOOD_PRESSURE_PLATE.get());
		
		silkTouch(BlockInit.ROTTING_GLASS.get(), Blocks.AIR.asItem(), 0, 0);
		silkTouch(BlockInit.FESTER_ORE.get(), ItemInit.FESTER_CHUNK.get(), 1, 1);
		
		//silkTouch(BlockInit.volatileOre.get(), Items.BLAZE_POWDER, 1, 4);
		/*
		 * DO NOT UNDER ANY CIRCUMSTANCES USE THE DROPSELF FOR SLABS. WRITE THE LOOT TABLE YOURSELF.
		 */
	}
	
	protected void silkTouch(Block block, Item silk, int fortuneMin, int fortuneMax) {
		add(block, createSilkTouchTable(block.getRegistryName().getPath(),
				block, silk, fortuneMin, fortuneMax));
	}
	
	protected void dropSelf(Block block) {
		add(block, createSimpleTable(block.getRegistryName().getPath(), block));
	}
	
}
