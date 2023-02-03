package com.gmail.thelilchicken01.tff.block;

import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class SlimySapling extends SaplingBlock {

	public SlimySapling(AbstractTreeGrower treeGrower, Properties properties) {
		super(treeGrower, properties);
		
	}
	
	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
		return state.is(BlockInit.ROTTING_DIRT.get()) || state.is(BlockTags.DIRT) || state.is(BlockInit.ROTTING_GRASS.get());
	}

}
