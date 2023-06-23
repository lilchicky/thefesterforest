package com.gmail.thelilchicken01.tff.dataGen.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockstateProvider extends BlockStateProvider {

	public ModBlockstateProvider(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, TheFesterForest.MODID, helper);
		
	}

	@Override
	protected void registerStatesAndModels() {

		simpleBlock(BlockInit.ROTTING_BRICKS.get());
		//simpleBlock(BlockInit.ROTTING_STONE.get());
		simpleBlock(BlockInit.FESTER_ORE.get());
		simpleBlock(BlockInit.SLIMY_PLANKS.get());
		simpleBlock(BlockInit.CRACKED_ROTTING_STONE.get());
		simpleBlock(BlockInit.MOSSY_ROTTING_STONE.get());
		simpleBlock(BlockInit.ROTTING_STONE_BRICKS.get());
		simpleBlock(BlockInit.CRUSHED_ROTTING_STONE.get());
		simpleBlock(BlockInit.ROTTING_GRAVE_DIRT.get());
		simpleBlock(BlockInit.SOUL_ROT.get());
		simpleBlock(BlockInit.ROTTING_SAND.get());
		simpleBlock(BlockInit.COMPRESSED_ROTTING_SAND.get());
		simpleBlock(BlockInit.ROTTING_GLASS.get());
		simpleBlock(BlockInit.CRACKED_ROTTING_STONE_BRICKS.get());
		simpleBlock(BlockInit.BLUE_FESTER_SHROOM_BLOCK.get());
		simpleBlock(BlockInit.ORANGE_FESTER_SHROOM_BLOCK.get());
		simpleBlock(BlockInit.CUBED_FUNGUS.get());
		
	}

	
	
}
