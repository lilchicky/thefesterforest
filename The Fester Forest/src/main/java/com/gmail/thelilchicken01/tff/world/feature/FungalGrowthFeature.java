package com.gmail.thelilchicken01.tff.world.feature;

import java.util.Random;

import com.gmail.thelilchicken01.tff.block.FungalGrowthTall;
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

public class FungalGrowthFeature extends Feature<ProbabilityFeatureConfiguration> {

	public FungalGrowthFeature(Codec<ProbabilityFeatureConfiguration> p_65786_) {
		super(p_65786_);
	}
	
	   public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> p_160318_) {
		      boolean flag = false;
		      Random random = p_160318_.random();
		      WorldGenLevel worldgenlevel = p_160318_.level();
		      BlockPos blockpos = p_160318_.origin();
		      ProbabilityFeatureConfiguration probabilityfeatureconfiguration = p_160318_.config();
		      int i = random.nextInt(8) - random.nextInt(8);
		      int j = random.nextInt(8) - random.nextInt(8);
		      int k = worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR, blockpos.getX() + i, blockpos.getZ() + j);
		      BlockPos blockpos1 = new BlockPos(blockpos.getX() + i, k, blockpos.getZ() + j);
		      if (worldgenlevel.getBlockState(blockpos1).is(Blocks.WATER)) {
		         boolean flag1 = random.nextDouble() < (double)probabilityfeatureconfiguration.probability;
		         BlockState blockstate = flag1 ? BlockInit.TALL_FUNGAL_GROWTH.get().defaultBlockState() : BlockInit.FUNGAL_GROWTH.get().defaultBlockState();
		         if (blockstate.canSurvive(worldgenlevel, blockpos1)) {
		            if (flag1) {
		               BlockState blockstate1 = blockstate.setValue(FungalGrowthTall.HALF, DoubleBlockHalf.UPPER);
		               BlockPos blockpos2 = blockpos1.above();
		               if (worldgenlevel.getBlockState(blockpos2).is(Blocks.WATER)) {
		                  worldgenlevel.setBlock(blockpos1, blockstate, 2);
		                  worldgenlevel.setBlock(blockpos2, blockstate1, 2);
		               }
		            } else {
		               worldgenlevel.setBlock(blockpos1, blockstate, 2);
		            }

		            flag = true;
		         }
		      }

		      return flag;
		   }

}
