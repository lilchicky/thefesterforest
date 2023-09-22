package com.gmail.thelilchicken01.tff.world.feature.tree_decorator;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.world.feature.TffTreeDecorators;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class IcicleDecorator extends TreeDecorator {
	public static final IcicleDecorator INSTANCE = new IcicleDecorator();
	public static final Codec<IcicleDecorator> CODEC = Codec.unit(() -> INSTANCE);

	@Override
	protected TreeDecoratorType<?> type() {
		return TffTreeDecorators.ICICLE_DECORATOR.get();
	}

	@Override
	public void place(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, Random random, List<BlockPos> logPositions, List<BlockPos> leafPositions) {
		leafPositions.forEach((pos -> {
			if(random.nextInt(3) == 0) {
				BlockPos downPos = pos.below();
				if(Feature.isAir(level, downPos)) {
					blockSetter.accept(downPos, BlockInit.FROSTBITTEN_PLANKS.get().defaultBlockState());
				}
			}
		}));
	}
}
