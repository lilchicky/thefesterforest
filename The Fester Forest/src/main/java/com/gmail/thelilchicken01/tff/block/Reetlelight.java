package com.gmail.thelilchicken01.tff.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class Reetlelight extends Block {
	
	public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

	public Reetlelight() {
		super(BlockBehaviour.Properties.copy(Blocks.SHROOMLIGHT).emissiveRendering((state, getter, pos) -> {
			return true;
		}).lightLevel(state -> state.getValue(LIT) ? 0 : 15));
		this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.valueOf(false)));
	}

	   @Nullable
	   public BlockState getStateForPlacement(BlockPlaceContext p_55659_) {
	      return this.defaultBlockState().setValue(LIT, Boolean.valueOf(p_55659_.getLevel().hasNeighborSignal(p_55659_.getClickedPos())));
	   }

	   public void neighborChanged(BlockState state, Level level, BlockPos blockpos, Block block, BlockPos blockpos2, boolean bool) {
	      if (!level.isClientSide) {
	         boolean flag = state.getValue(LIT);
	         if (flag != level.hasNeighborSignal(blockpos)) {
	            if (flag) {
	            	level.scheduleTick(blockpos, this, 4);
	            } else {
	            	level.setBlock(blockpos, state.cycle(LIT), 2);
	            }
	         }

	      }
	   }

	   public void tick(BlockState blockState, ServerLevel level, BlockPos blockpos, Random rand) {
	      if (blockState.getValue(LIT) && !level.hasNeighborSignal(blockpos)) {
	    	  level.setBlock(blockpos, blockState.cycle(LIT), 2);
	      }

	   }

	   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
	      builder.add(LIT);
	   }
	}