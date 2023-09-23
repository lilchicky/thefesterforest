package com.gmail.thelilchicken01.tff.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class Frostvine extends FlowerBlock {

	public Frostvine() {
		super(MobEffects.MOVEMENT_SLOWDOWN, 40, BlockBehaviour.Properties.copy(Blocks.DANDELION));
		
	}
	
	@Override
	public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest,
			FluidState fluid) {
		
		player.setTicksFrozen(player.getTicksFrozen() + 20);
		
		return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
		
	}

}