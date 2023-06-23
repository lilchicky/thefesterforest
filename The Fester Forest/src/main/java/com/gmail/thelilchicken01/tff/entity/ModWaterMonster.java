package com.gmail.thelilchicken01.tff.entity;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public abstract class ModWaterMonster extends Monster {

	protected ModWaterMonster(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
	}

	public static boolean checkSurfaceWaterMonsterSpawnRules(EntityType<? extends Monster> entity, LevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos blockpos, Random rand) {
		return (levelAccessor.getDifficulty() != Difficulty.PEACEFUL && 
				(spawnType == MobSpawnType.SPAWNER || (blockpos.getY() <= 63 && blockpos.getY() >= 43)));
	}	
	
}
