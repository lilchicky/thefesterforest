package com.gmail.thelilchicken01.tff.block;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {

	public ModFlammableRotatedPillarBlock(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		
		return super.isFlammable(state, level, pos, direction);
	}
	
	@Override
	public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		
		return super.getFlammability(state, level, pos, direction);
	}
	
	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		
		return super.getFireSpreadSpeed(state, level, pos, direction);
	}
	
	@SuppressWarnings("removal")
	@Nullable
	@Override
	public BlockState getToolModifiedState(BlockState state, Level world, BlockPos pos, Player player, ItemStack stack,
			ToolAction toolAction) {
		
		if(stack.getItem() instanceof AxeItem) {
			if(state.is(BlockInit.rotting_log.get())) {
				return BlockInit.stripped_rotting_log.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			}
			
			if(state.is(BlockInit.rotting_wood.get())) {
				return BlockInit.stripped_rotting_wood.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			}
			
			// Slimy Log
			if(state.is(BlockInit.slimy_log.get())) {
				return BlockInit.stripped_slimy_log.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			}
			
			if(state.is(BlockInit.slimy_wood.get())) {
				return BlockInit.stripped_slimy_wood.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			}
		}
		
		return super.getToolModifiedState(state, world, pos, player, stack, toolAction);
	}

}
