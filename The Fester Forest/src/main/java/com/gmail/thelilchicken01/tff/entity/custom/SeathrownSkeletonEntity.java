package com.gmail.thelilchicken01.tff.entity.custom;

import java.util.EnumSet;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SeathrownSkeletonEntity extends Monster implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	protected RandomStrollGoal randomStrollGoal;

	public SeathrownSkeletonEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		
		super(p_33002_, p_33003_);
		
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
		this.moveControl = new SeathrownSkeletonEntity.WaterMoveControl(this);
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 40.00)
				.add(Attributes.ATTACK_DAMAGE, 18.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.ARMOR, 10.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.25f).build();
	}
	
	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.9f;
	}
	
	protected void registerGoals() {
		
		this.goalSelector.addGoal(2, new SeathrownSkeletonEntity.WaterMeleeAttackGoal(this, 1.3, false));
	    this.goalSelector.addGoal(4, new SeathrownSkeletonEntity.FishSwimGoal(this));
	    
	    this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, SeathrownSkeletonEntity.class)).setAlertOthers(SeathrownSkeletonEntity.class));
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::okTarget));
		
	}
	
	public boolean okTarget(@Nullable LivingEntity target) {
		if (target != null) {
			return target.isInWater();
		} 
		
		else {
			return false;
		}
	}
	
	protected PathNavigation createNavigation(Level world) {
		return new WaterBoundPathNavigation(this, world);
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.FISH_SWIM, 0.15f, 0.5f); // VOLUME - PITCH
		
	}
	
	public boolean canBreatheUnderwater() {
		return true;
	}
	
	protected SoundEvent getAmbientSound() { return SoundEvents.WITHER_SKELETON_AMBIENT; }
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.WITHER_SKELETON_HURT; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.WITHER_SKELETON_DEATH; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if(event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.seathrown_skeleton.walk", true));
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.seathrown_skeleton.idle", true));
		return PlayState.CONTINUE;
		
	}

	@Override
	public void registerControllers(AnimationData data) {
		
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
		
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
	
	static class WaterMeleeAttackGoal extends MeleeAttackGoal {
		
		private final SeathrownSkeletonEntity SEATHROWN_SKELETON;

		public WaterMeleeAttackGoal(SeathrownSkeletonEntity fish, double p_25553_, boolean p_25554_) {
			super(fish, p_25553_, p_25554_);
			this.SEATHROWN_SKELETON = fish;
		}
		
		@Override
		public boolean canUse() {
			return super.canUse() && this.SEATHROWN_SKELETON.okTarget(this.SEATHROWN_SKELETON.getTarget());
		}
		
		@Override
		public boolean canContinueToUse() {
			return super.canContinueToUse() && this.SEATHROWN_SKELETON.okTarget(this.SEATHROWN_SKELETON.getTarget());
		}
		
	}
	
	static class WaterMoveControl extends MoveControl {
	      private final SeathrownSkeletonEntity thisFish;

	      WaterMoveControl(SeathrownSkeletonEntity seathrownSkeleton) {
	         super(seathrownSkeleton);
	         this.thisFish = seathrownSkeleton;
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

	   static class FishSwimGoal extends RandomSwimmingGoal {

	      public FishSwimGoal(SeathrownSkeletonEntity seathrownSkeleton) {
	         super(seathrownSkeleton, 1.0D, 40);
	      }

	      public boolean canUse() {
	         return super.canUse();
	      }
	   }

}
