package com.gmail.thelilchicken01.tff.dataGen.server;

import org.jetbrains.annotations.Nullable;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider {

	public ModItemTagsProvider(DataGenerator gen, BlockTagsProvider blocks,
			@Nullable ExistingFileHelper helper) {
		super(gen, blocks, TheFesterForest.modid, helper);
		
	}
	
	@Override
	protected void addTags() {
		
		tag(Tags.Items.INGOTS).add(ItemInit.rotting_brick.get().asItem());
		tag(Tags.Items.COBBLESTONE).add(BlockInit.cracked_rotting_stone.get().asItem());
		tag(Tags.Items.COBBLESTONE).add(BlockInit.mossy_rotting_stone.get().asItem());
		
	}

}
