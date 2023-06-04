package com.gmail.thelilchicken01.tff.block;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CorrodedShroom extends FlowerBlock {

	public CorrodedShroom() {
		super(MobEffects.BLINDNESS, 40, BlockBehaviour.Properties.copy(Blocks.DANDELION));
		
	}

}
