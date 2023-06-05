package com.gmail.thelilchicken01.tff.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class FungalGrowth extends BushBlock implements LiquidBlockContainer, BonemealableBlock {

	public FungalGrowth() {
		super(BlockBehaviour.Properties.copy(Blocks.SEAGRASS));
	}

	@Override
	public boolean canPlaceLiquid(BlockGetter p_54766_, BlockPos p_54767_, BlockState p_54768_, Fluid p_54769_) {
		return false;
	}

	@Override
	public boolean placeLiquid(LevelAccessor p_54770_, BlockPos p_54771_, BlockState p_54772_, FluidState p_54773_) {
		return false;
	}
	
	@Override
	public FluidState getFluidState(BlockState p_154537_) {
		return Fluids.WATER.getSource(false);
	}
	   
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_154503_) {
		FluidState fluidstate = p_154503_.getLevel().getFluidState(p_154503_.getClickedPos());
		return fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8 ? super.getStateForPlacement(p_154503_) : null;
	}

	@Override
	public BlockState updateShape(BlockState p_154530_, Direction p_154531_, BlockState p_154532_, LevelAccessor p_154533_, BlockPos p_154534_, BlockPos p_154535_) {
		BlockState blockstate = super.updateShape(p_154530_, p_154531_, p_154532_, p_154533_, p_154534_, p_154535_);
		if (!blockstate.isAir()) {
			p_154533_.scheduleTick(p_154534_, Fluids.WATER, Fluids.WATER.getTickDelay(p_154533_));
		}

		return blockstate;
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter p_50897_, BlockPos p_50898_, BlockState p_50899_,
			boolean p_50900_) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level p_50901_, Random p_50902_, BlockPos p_50903_, BlockState p_50904_) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, Random rand, BlockPos blockposin, BlockState state) {
		BlockState blockstate = BlockInit.TALL_FUNGAL_GROWTH.get().defaultBlockState();
	    BlockState blockstate1 = blockstate.setValue(FungalGrowthTall.HALF, DoubleBlockHalf.UPPER);
	    BlockPos blockpos = blockposin.above();
	    if (level.getBlockState(blockpos).is(Blocks.WATER)) {
	    	level.setBlock(blockpos, blockstate, 2);
	    	level.setBlock(blockpos, blockstate1, 2);
	    }
		
	}
	
}
