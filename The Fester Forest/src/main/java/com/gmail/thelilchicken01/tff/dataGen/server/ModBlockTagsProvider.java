package com.gmail.thelilchicken01.tff.dataGen.server;

import org.jetbrains.annotations.Nullable;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockTagsProvider extends BlockTagsProvider {

	public ModBlockTagsProvider(DataGenerator gen,
			@Nullable ExistingFileHelper helper) {
		super(gen, TheFesterForest.MODID, helper);
		
		
	}
	
	@Override
	protected void addTags() {
		
		//tag(TagInit.Blocks.COOL_BLOCKS).add(Blocks.ACACIA_BUTTON).add(BlockInit.rotting_log.get());
		tag(Tags.Blocks.ORES).add(BlockInit.FESTER_ORE.get());
		tag(Tags.Blocks.STONE).add(BlockInit.ROTTING_STONE.get());
		tag(Tags.Blocks.STONE).add(BlockInit.CRACKED_ROTTING_STONE.get());
		tag(Tags.Blocks.STONE).add(BlockInit.MOSSY_ROTTING_STONE.get());
	}

}
