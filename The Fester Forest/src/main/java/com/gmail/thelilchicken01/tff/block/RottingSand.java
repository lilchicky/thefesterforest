package com.gmail.thelilchicken01.tff.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GravelBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RottingSand extends GravelBlock {

	public RottingSand() {
		super(BlockBehaviour.Properties.copy(Blocks.SOUL_SAND));
		
	}

}
