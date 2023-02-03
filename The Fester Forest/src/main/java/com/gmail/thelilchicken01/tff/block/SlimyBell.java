package com.gmail.thelilchicken01.tff.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class SlimyBell extends FlowerBlock {

	public SlimyBell() {
		super(MobEffects.JUMP, 40, BlockBehaviour.Properties.copy(Blocks.DANDELION));
		
	}
	
	@Override
	public void entityInside(BlockState p_60495_, Level p_60496_, BlockPos p_60497_, Entity entity) {
		
		if(entity instanceof Player) {
			
			if (!((Player) entity).hasEffect(MobEffects.JUMP)) {
				
				((Player) entity).addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 0));
				
			}
			
		}
		
		super.entityInside(p_60495_, p_60496_, p_60497_, entity);
	}

}
