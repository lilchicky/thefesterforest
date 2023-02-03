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

public class SickeningFlower extends FlowerBlock {

	public SickeningFlower() {
		super(MobEffects.REGENERATION, 40, BlockBehaviour.Properties.copy(Blocks.DANDELION));
		
	}
	
	@Override
	public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest,
			FluidState fluid) {
		
		if (!player.hasEffect(MobEffects.ABSORPTION)) {
			
			player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 0));
			
		}
		
		return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
		
	}

}
