package com.gmail.thelilchicken01.tff.dataGen.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockstateProvider extends BlockStateProvider {

	public ModBlockstateProvider(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, TheFesterForest.modid, helper);
		
	}

	@Override
	protected void registerStatesAndModels() {

		simpleBlock(BlockInit.rotting_bricks.get());
		simpleBlock(BlockInit.rotting_stone.get());
		simpleBlock(BlockInit.fester_ore.get());
		simpleBlock(BlockInit.slimy_planks.get());
		simpleBlock(BlockInit.cracked_rotting_stone.get());
		simpleBlock(BlockInit.mossy_rotting_stone.get());
		simpleBlock(BlockInit.rotting_stone_bricks.get());
		
	}

	
	
}
