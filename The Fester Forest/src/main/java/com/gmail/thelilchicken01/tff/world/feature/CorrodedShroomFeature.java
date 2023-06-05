package com.gmail.thelilchicken01.tff.world.feature;

import java.util.Random;

import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class CorrodedShroomFeature extends Feature<ProbabilityFeatureConfiguration> {

	public CorrodedShroomFeature(Codec<ProbabilityFeatureConfiguration> p_65786_) {
		super(p_65786_);
	}
	
	public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> p_160318_) {
	      boolean flag = false;
	      Random random = p_160318_.random();
	      WorldGenLevel worldgenlevel = p_160318_.level();
	      BlockPos blockpos = p_160318_.origin();
	      int i = random.nextInt(8) - random.nextInt(8);
	      int j = random.nextInt(8) - random.nextInt(8);
	      int k = worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR, blockpos.getX() + i, blockpos.getZ() + j);
	      BlockPos blockpos1 = new BlockPos(blockpos.getX() + i, k, blockpos.getZ() + j);
	      if (worldgenlevel.getBlockState(blockpos1).is(Blocks.WATER)) {
	         BlockState blockstate = BlockInit.CORRODED_SHROOM.get().defaultBlockState();
	         if (blockstate.canSurvive(worldgenlevel, blockpos1)) {
	            worldgenlevel.setBlock(blockpos1, blockstate, 2);

	            flag = true;
	         }
	      }

	      return flag;
	   }

}
