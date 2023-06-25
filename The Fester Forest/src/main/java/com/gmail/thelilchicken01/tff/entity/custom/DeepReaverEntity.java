package com.gmail.thelilchicken01.tff.entity.custom;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.ModWaterMonster;
import com.gmail.thelilchicken01.tff.init.ParticleInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class DeepReaverEntity extends ModWaterMonster implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private int dashCooldown = 4;
	private int dashCounter = 0;
	
	private int dashWarmup = 1;
	private int dashWarmupCounter = 0;
	
	private Vec3 targetVector;
	
	protected final WaterBoundPathNavigation waterNavigation;
	protected final GroundPathNavigation groundNavigation;
	
	protected RandomStrollGoal randomStrollGoal;

	public DeepReaverEntity(EntityType<? extends Monster> p_33002_, Level level) {
		
		super(p_33002_, level);
		
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
		this.waterNavigation = new WaterBoundPathNavigation(this, level);
	    this.groundNavigation = new GroundPathNavigation(this, level);
		this.moveControl = new DeepReaverEntity.WaterMoveControl(this);
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 100.00)
				.add(Attributes.ATTACK_DAMAGE, 24.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.ARMOR, 16.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.2f).build();
	}
	
	@Override
	public MobType getMobType() {
		return MobType.WATER;
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.9f;
	}
	
	@Override
	public void updateSwimming() {
		if (!this.level.isClientSide) {
	         if (this.isEffectiveAi() && this.isInWater()) {
	            this.navigation = this.waterNavigation;
	            this.setSwimming(true);
	         } else {
	            this.navigation = this.groundNavigation;
	            this.setSwimming(false);
	         }
	      }
	}
	
	@Override
	protected void dropExperience() {
		ExperienceOrb.award((ServerLevel)this.level, this.position(), 400);
	}
	
	@Override
	public boolean isPersistenceRequired() {
		return true;
	}
	
	@Override
	public void tick() {
		super.tick();
		
		dashCounter++;
		
		if (dashCounter > (dashCooldown * 20) && getTarget() != null && getTarget() instanceof Player && isInWater()) {
			
			if (dashWarmupCounter == 0) {
				
				playSound(SoundEvents.GUARDIAN_ATTACK, 0.5f, 1.0f);
				
				Vec3 currentPos = getEyePosition();
				Vec3 targetPos = getTarget().getPosition(1.0f);
				targetVector = targetPos.subtract(currentPos).normalize();
				
			}
			
			dashWarmupCounter++;
			
			if (dashWarmupCounter > (dashWarmup * 20)) {
				
				setDeltaMovement(targetVector);
				
				dashCounter = 0;
				dashWarmupCounter = 0;
				
			}
			
		}
		
	}
	
	protected void registerGoals() {
		
		this.goalSelector.addGoal(2, new DeepReaverEntity.WaterMeleeAttackGoal(this, 1.3, false));
	    this.goalSelector.addGoal(4, new DeepReaverEntity.FishSwimGoal(this));
	    this.goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.005, false));
	    this.goalSelector.addGoal(8, new RandomStrollGoal(this, 1.000));
	    
	    this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, DeepReaverEntity.class)).setAlertOthers(DeepReaverEntity.class));
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::okTarget));
		
	}
	
	@Override
	public boolean okTarget(@Nullable LivingEntity target) {
		if (target != null) {
			return this.isSwimming() ? target.isInWater() : true;
		} 
		
		else {
			return false;
		}
	}
	
	protected PathNavigation createNavigation(Level world) {
		return this.navigation == null ? new WaterBoundPathNavigation(this, world) : this.navigation;
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.FISH_SWIM, 0.15f, 0.5f); // VOLUME - PITCH
		
	}
	
	public boolean canBreatheUnderwater() {
		return true;
	}
	
	protected SoundEvent getAmbientSound() { return SoundEvents.GUARDIAN_AMBIENT; }
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.TROPICAL_FISH_HURT; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.TROPICAL_FISH_DEATH; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if(event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.deep_reaver.walk", true));
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.deep_reaver.idle", true));
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
		
		private final DeepReaverEntity fish;

		public WaterMeleeAttackGoal(DeepReaverEntity fish, double p_25553_, boolean p_25554_) {
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
	
	static class WaterMoveControl extends MoveControl {
	      private final DeepReaverEntity thisFish;

	      WaterMoveControl(DeepReaverEntity fish) {
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

	   static class FishSwimGoal extends RandomSwimmingGoal {

	      public FishSwimGoal(DeepReaverEntity fish) {
	         super(fish, 1.0D, 40);
	      }

	      public boolean canUse() {
	         return super.canUse();
	      }
	   }

}
