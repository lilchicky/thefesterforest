package com.gmail.thelilchicken01.tff.world.feature.tree;

import java.util.Random;

import com.gmail.thelilchicken01.tff.world.feature.ModConfiguredFeatures;
import com.gmail.thelilchicken01.tff.world.feature.TffConfiguredFeatures;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class SlimyTreeGrower extends AbstractTreeGrower {

	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random p_204307_, boolean p_204308_) {
		return TffConfiguredFeatures.SLIMY_TREE.getHolder().get();
	}

}
