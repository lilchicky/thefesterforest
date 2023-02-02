package com.gmail.thelilchicken01.tff.block;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class SickeningFlower extends FlowerBlock {

	public SickeningFlower() {
		super(MobEffects.REGENERATION, 40, BlockBehaviour.Properties.copy(Blocks.DANDELION));
		
	}

}
