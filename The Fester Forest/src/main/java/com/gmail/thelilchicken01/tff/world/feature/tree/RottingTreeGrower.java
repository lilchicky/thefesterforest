package com.gmail.thelilchicken01.tff.world.feature.tree;

import java.util.Random;

import com.gmail.thelilchicken01.tff.world.feature.TffConfiguredFeatures;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class RottingTreeGrower extends AbstractTreeGrower {

	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random p_204307_, boolean p_204308_) {
		return TffConfiguredFeatures.ROTTINGWOOD_TREE.getHolder().get();
	}

}
