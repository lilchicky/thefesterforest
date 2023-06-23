package com.gmail.thelilchicken01.tff.entity.custom;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.ModWaterMonster;
import com.gmail.thelilchicken01.tff.entity.projectile.ElectricCharge;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.projectile.ElectricShot;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
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

public class AmbectrumEntity extends ModWaterMonster implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	protected RandomStrollGoal randomStrollGoal;
	
	private double shootCooldown = 4; // default shot cooldown in seconds, before modifiers
	private int shootCounter;
	private int shootDamage = 15;

	public AmbectrumEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		
		super(p_33002_, p_33003_);
		
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
		this.moveControl = new AmbectrumEntity.WaterMoveControl(this);
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 115.00)
				.add(Attributes.ATTACK_DAMAGE, 18.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.ARMOR, 4.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.05f).build();
	}
	
	@Override
	public MobType getMobType() {
		return MobType.WATER;
	}
	
	protected void registerGoals() {
		
		this.goalSelector.addGoal(2, new AmbectrumEntity.WaterMeleeAttackGoal(this, 1.3, false));
	    this.goalSelector.addGoal(4, new AmbectrumEntity.FishSwimGoal(this));
	    
	    this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, AmbectrumEntity.class)).setAlertOthers(AmbectrumEntity.class));
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::okTarget));
		
	}
	
	public void tick() {
		
		super.tick();
		
		shootCounter++;
		
		if(!getLevel().isClientSide()) {
		
			if (shootCounter > (shootCooldown * 20) && this.okTarget(this.getTarget())) {
				
				ItemStack ammo = new ItemStack(ItemInit.ELECTRIC_CHARGE.get());
				ElectricShot bulletItem = ItemInit.ELECTRIC_CHARGE.get(); //these will be the same, but are what is being shot
				
				ElectricCharge shot = bulletItem.createProjectile(this.level, ammo, this);// level, shot item, this entity
				
				Vec3 currentPos = getEyePosition();
				Vec3 targetPos = getTarget().getPosition(1.0f);
				Vec3 targetVector = targetPos.subtract(currentPos).normalize();
				
				shot.shoot(targetVector.x, targetVector.y + 0.1, targetVector.z, 0.4f, 0.0f);
				shot.setDamage(shootDamage); // set damage
				shot.setIgnoreInvulnerability(false);
				shot.setDefaultVelocity(targetVector);
				
				playSound(SoundEvents.BEE_HURT, 2.0f, 0.5f);
				this.level.addFreshEntity(shot);
				
				shootCounter = 0;
				
			}
		}
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
	
	protected void handleAirSupply(int p_30344_) {
		if (this.isAlive() && !this.isInWaterOrBubble()) {
			this.setAirSupply(p_30344_ - 1);
			if (this.getAirSupply() == -20) {
				this.setAirSupply(0);
				this.hurt(DamageSource.DROWN, 2.0F);
			}
		} 
		
		else {
			this.setAirSupply(300);
		}

	}
	
	@Override
	public void baseTick() {
		int i = this.getAirSupply();
	    super.baseTick();
	    this.handleAirSupply(i);
	}
	
	@Override
	public void aiStep() {
		if (this.isInWaterOrBubble()) {
            this.setAirSupply(300);
         } else if (this.onGround) {
            this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.4F), 0.5D, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.4F)));
            this.setYRot(this.random.nextFloat() * 360.0F);
            this.onGround = false;
            this.hasImpulse = true;
        }
		super.aiStep();
	}
	
	protected SoundEvent getAmbientSound() { return SoundEvents.TROPICAL_FISH_AMBIENT; }
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.GLOW_SQUID_HURT; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.GLOW_SQUID_DEATH; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	@SuppressWarnings("removal")
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ambectrum.idle", true));
		return PlayState.CONTINUE;
		
	}

	@Override
	public void registerControllers(AnimationData data) {
		
		data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
		
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
	
	static class WaterMeleeAttackGoal extends MeleeAttackGoal {
		
		private final AmbectrumEntity AMBECTRUM;

		public WaterMeleeAttackGoal(AmbectrumEntity fish, double p_25553_, boolean p_25554_) {
			super(fish, p_25553_, p_25554_);
			this.AMBECTRUM = fish;
		}
		
		@Override
		public boolean canUse() {
			return super.canUse() && this.AMBECTRUM.okTarget(this.AMBECTRUM.getTarget());
		}
		
		@Override
		public boolean canContinueToUse() {
			return super.canContinueToUse() && this.AMBECTRUM.okTarget(this.AMBECTRUM.getTarget());
		}
		
	}
	
	static class WaterMoveControl extends MoveControl {
	      private final AmbectrumEntity thisFish;

	      WaterMoveControl(AmbectrumEntity ambectrum) {
	         super(ambectrum);
	         this.thisFish = ambectrum;
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

	      public FishSwimGoal(AmbectrumEntity ambectrum) {
	         super(ambectrum, 1.0D, 40);
	      }

	      public boolean canUse() {
	         return super.canUse();
	      }
	   }

}
