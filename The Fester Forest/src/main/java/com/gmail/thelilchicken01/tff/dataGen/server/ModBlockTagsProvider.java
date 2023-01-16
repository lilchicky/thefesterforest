package com.gmail.thelilchicken01.tff.dataGen.server;

import org.jetbrains.annotations.Nullable;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {

	public ModBlockTagsProvider(DataGenerator gen,
			@Nullable ExistingFileHelper helper) {
		super(gen, TheFesterForest.modid, helper);
		
		
	}
	
	@Override
	protected void addTags() {
		
		//tag(TagInit.Blocks.COOL_BLOCKS).add(Blocks.ACACIA_BUTTON).add(BlockInit.rotting_log.get());
		tag(Tags.Blocks.ORES).add(BlockInit.fester_ore.get());
		tag(Tags.Blocks.STONE).add(BlockInit.rotting_stone.get());
		tag(Tags.Blocks.STONE).add(BlockInit.cracked_rotting_stone.get());
		tag(Tags.Blocks.STONE).add(BlockInit.mossy_rotting_stone.get());
	}

}
