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
		dropSelf(BlockInit.rotting_bricks.get());
		dropSelf(BlockInit.rotting_flower.get());
		dropSelf(BlockInit.rotting_stone.get());
		dropSelf(BlockInit.fester_ore.get());
		dropSelf(BlockInit.slimy_log.get());
		dropSelf(BlockInit.slimy_sapling.get());
		dropSelf(BlockInit.rottingwood_sapling.get());
		dropSelf(BlockInit.slimy_planks.get());
		dropSelf(BlockInit.slimy_wood.get());
		dropSelf(BlockInit.stripped_slimy_log.get());
		dropSelf(BlockInit.stripped_slimy_wood.get());
		dropSelf(BlockInit.cracked_rotting_stone.get());
		dropSelf(BlockInit.mossy_rotting_stone.get());
		dropSelf(BlockInit.rotting_stone_bricks.get());
		dropSelf(BlockInit.rotting_stone_brick_slab.get());
		dropSelf(BlockInit.rotting_stone_brick_stairs.get());
		dropSelf(BlockInit.rotting_stone_brick_wall.get());
		dropSelf(BlockInit.rottingwood_fence.get());
		dropSelf(BlockInit.rottingwood_fence_gate.get());
		dropSelf(BlockInit.rottingwood_stairs.get());
		dropSelf(BlockInit.rottingwood_slab.get());
		dropSelf(BlockInit.slimy_stairs.get());
		dropSelf(BlockInit.slimy_slab.get());
		dropSelf(BlockInit.slimy_fence.get());
		dropSelf(BlockInit.slimy_fence_gate.get());
		dropSelf(BlockInit.reetlelight.get());
		dropSelf(BlockInit.crushed_rotting_stone.get());
		dropSelf(BlockInit.rotting_grave_dirt.get());
		
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
