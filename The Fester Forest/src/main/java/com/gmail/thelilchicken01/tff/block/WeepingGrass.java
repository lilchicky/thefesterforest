package com.gmail.thelilchicken01.tff.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class WeepingGrass extends TallFlowerBlock {

	public WeepingGrass() {
		super(BlockBehaviour.Properties.copy(Blocks.ROSE_BUSH));
	}

}
