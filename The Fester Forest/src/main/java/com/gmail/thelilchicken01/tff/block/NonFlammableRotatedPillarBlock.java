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

public class NonFlammableRotatedPillarBlock extends RotatedPillarBlock {

	public NonFlammableRotatedPillarBlock(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		
		return false;
	}
	
	@Override
	public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		
		return super.getFlammability(state, level, pos, direction);
	}
	
	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		
		return super.getFireSpreadSpeed(state, level, pos, direction);
	}

}
