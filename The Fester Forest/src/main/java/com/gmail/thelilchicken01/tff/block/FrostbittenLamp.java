package com.gmail.thelilchicken01.tff.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class FrostbittenLamp extends Block {

	public FrostbittenLamp() {
		super(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).emissiveRendering((state, getter, pos) -> {
			return true;
		}).lightLevel((state) -> 15));
	}
}