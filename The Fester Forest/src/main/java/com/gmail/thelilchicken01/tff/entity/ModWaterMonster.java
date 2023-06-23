package com.gmail.thelilchicken01.tff.entity;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
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
				(spawnType == MobSpawnType.SPAWNER || levelAccessor.getBlockState(blockpos.below()) == Blocks.WATER.defaultBlockState()));
	}	
	
	public boolean okTarget(@Nullable LivingEntity target) {
		if (target != null) {
			return target.isInWater();
		} 
		
		else {
			return false;
		}
	}
	
	public static class WaterMeleeAttackGoal extends MeleeAttackGoal {
		
		private final ModWaterMonster fish;

		public WaterMeleeAttackGoal(ModWaterMonster fish, double p_25553_, boolean p_25554_) {
			super(fish, p_25553_, p_25554_);
			this.fish = fish;
		}
		
		@Override
		public boolean canUse() {
			return super.canUse() && this.fish.okTarget(this.fish.getTarget());
		}
		
		@Override
		public boolean canContinueToUse() {
			return super.canContinueToUse() && this.fish.okTarget(this.fish.getTarget());
		}
		
	}
	
	public static class WaterMoveControl extends MoveControl {
	      private final ModWaterMonster thisFish;

	      public WaterMoveControl(ModWaterMonster fish) {
	         super(fish);
	         this.thisFish = fish;
	      }

	      public void tick() {
	         if (this.thisFish.isEyeInFluid(FluidTags.WATER)) {
	            this.thisFish.setDeltaMovement(this.thisFish.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
	         }

	         if (this.operation == MoveControl.Operation.MOVE_TO && !this.thisFish.getNavigation().isDone()) {
	            float f = (float)(this.speedModifier * this.thisFish.getAttributeValue(Attributes.MOVEMENT_SPEED));
	            this.thisFish.setSpeed(Mth.lerp(0.125F, this.thisFish.getSpeed(), f));
	            double d0 = this.wantedX - this.thisFish.getX();
	            double d1 = this.wantedY - this.thisFish.getY();
	            double d2 = this.wantedZ - this.thisFish.getZ();
	            if (d1 != 0.0D) {
	               double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
	               this.thisFish.setDeltaMovement(this.thisFish.getDeltaMovement().add(0.0D, (double)this.thisFish.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
	            }

	            if (d0 != 0.0D || d2 != 0.0D) {
	               float f1 = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
	               this.thisFish.setYRot(this.rotlerp(this.thisFish.getYRot(), f1, 90.0F));
	               this.thisFish.yBodyRot = this.thisFish.getYRot();
	            }

	         } else {
	            this.thisFish.setSpeed(0.0F);
	         }
	      }
	   }
	
	public static class FishSwimGoal extends RandomSwimmingGoal {

		public FishSwimGoal(ModWaterMonster fish) {
			super(fish, 1.0D, 40);
		}

		public boolean canUse() {
			return super.canUse();
		}
	}
}
