package com.gmail.thelilchicken01.tff.entity.custom;

import java.util.EnumSet;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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

public class CorrodedShroomEntity extends Monster implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	protected RandomStrollGoal randomStrollGoal;

	public CorrodedShroomEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		
		super(p_33002_, p_33003_);
		
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
		this.moveControl = new CorrodedShroomEntity.WaterMoveControl(this);
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 10.00)
				.add(Attributes.ATTACK_DAMAGE, 5.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.ARMOR, 0.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.25f).build();
	}
	
	@Override
	public MobType getMobType() {
		return MobType.WATER;
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.9f;
	}
	
	protected void registerGoals() {
		
		this.goalSelector.addGoal(2, new CorrodedShroomEntity.WaterMeleeAttackGoal(this, 1.3, false));
	    this.goalSelector.addGoal(4, new CorrodedShroomEntity.FishSwimGoal(this));
	    
	    this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, CorrodedShroomEntity.class)).setAlertOthers(CorrodedShroomEntity.class));
		
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
		
		this.playSound(SoundEvents.FISH_SWIM, 0.07f, 0.5f); // VOLUME - PITCH
		
	}
	
	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		
		if (player.getItemInHand(hand).getItem() == BlockInit.CORRODED_SHROOM.get().asItem()) {
			
			if (this.getLevel().isClientSide) {
				
				this.getLevel().addParticle(ParticleTypes.HEART, this.getX(), this.getY() + 1, this.getZ(), 0.0d, 0.25d, 0.0d);
				
			}
			
			player.getItemInHand(hand).shrink(1);
			
		}
		
		return super.mobInteract(player, hand);
	}
	
	public boolean canBreatheUnderwater() {
		return true;
	}
	
	protected SoundEvent getAmbientSound() { return SoundEvents.TROPICAL_FISH_AMBIENT; }
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.DOLPHIN_HURT; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.DOLPHIN_DEATH; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if(event.isMoving()) {
			
			if (this.isInWater()) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.corroded_shroom.swim", true));
				return PlayState.CONTINUE;
			}
			
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.corroded_shroom.walk", true));
			return PlayState.CONTINUE;
			
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.corroded_shroom.idle", true));
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
		
		private final CorrodedShroomEntity SEATHROWN_SKELETON;

		public WaterMeleeAttackGoal(CorrodedShroomEntity fish, double p_25553_, boolean p_25554_) {
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
	      private final CorrodedShroomEntity thisFish;

	      WaterMoveControl(CorrodedShroomEntity seathrownSkeleton) {
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

	      public FishSwimGoal(CorrodedShroomEntity seathrownSkeleton) {
	         super(seathrownSkeleton, 1.0D, 40);
	      }

	      public boolean canUse() {
	         return super.canUse();
	      }
	   }

}
