package com.gmail.thelilchicken01.tff.block;

import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.HangingRootsBlock;
import net.minecraft.world.level.block.state.BlockState;

public class Icicle extends HangingRootsBlock {

	public Icicle(Properties properties) {
		super(properties.dynamicShape());
	}

	@Override
	public boolean canSurvive(BlockState p_153347_, LevelReader p_153348_, BlockPos p_153349_) {
		BlockPos blockpos = p_153349_.above();
		BlockState blockstate = p_153348_.getBlockState(blockpos);
		return blockstate.is(BlockInit.FROSTBITTEN_LEAVES.get());
	}
	
}
