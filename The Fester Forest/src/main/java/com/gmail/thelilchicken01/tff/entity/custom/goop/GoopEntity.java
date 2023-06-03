package com.gmail.thelilchicken01.tff.entity.custom.goop;

import java.util.EnumSet;

import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GoopEntity extends Monster implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);

	public GoopEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		this.moveControl = new GoopMoveControl(this);
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 95.00)
				.add(Attributes.ARMOR, 10.0f)
				.add(Attributes.ATTACK_DAMAGE, 12.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.2f).build();
	}
	
	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}
	
	protected void registerGoals() {
		
		//first num is prio
		
		this.goalSelector.addGoal(1, new GoopEntity.GoopEntityFloatGoal(this));
	    this.goalSelector.addGoal(2, new GoopEntity.GoopAttackGoal(this));
	    this.goalSelector.addGoal(3, new GoopEntity.GoopEntityRandomDirectionGoal(this));
	    this.goalSelector.addGoal(5, new GoopEntity.GoopEntityKeepOnJumpingGoal(this));
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (p_33641_) -> {
	        return Math.abs(p_33641_.getY() - this.getY()) <= 4.0D;
	    }));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
		
	}
	
	@Override
	protected void tickDeath() {
		
		++this.deathTime;
	    if (this.deathTime == 20 && !this.level.isClientSide()) {
	    	if (!getLevel().isClientSide()) {
				
				MediumGoopEntity newGoop = new MediumGoopEntity(ModEntityTypes.GOOP_MEDIUM.get(), getLevel());
				
				newGoop.setPos(getX(), getY(), getZ());
				newGoop.setTarget(getTarget());
				
				getLevel().addFreshEntity(newGoop);
				
			}
	        this.level.broadcastEntityEvent(this, (byte)60);
	        this.remove(Entity.RemovalReason.KILLED);
	    }
		
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.HONEY_BLOCK_BREAK, 0.0f, 0.1f); // VOLUME - PITCH
		
	}
	
	
	protected SoundEvent getAmbientSound() { return null; }
	
	@Override
	protected void playHurtSound(DamageSource p_21493_) {
		
		this.playSound(SoundEvents.HONEY_BLOCK_HIT, 0.2f, 1.5f);
	}
	
	protected SoundEvent getDeathSound() {return SoundEvents.HONEY_BLOCK_FALL; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if(event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goop.walk", true));
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goop.idle", true));
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
	
	@Override
	public void playerTouch(Player p_33611_) {
	    if (this.isDealsDamage()) {
	        this.dealDamage(p_33611_);
	    }

	}
	
	protected void dealDamage(LivingEntity p_33638_) {
		if (this.isAlive()) {
			if (this.hasLineOfSight(p_33638_) && p_33638_.hurt(DamageSource.mobAttack(this), (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE))) {
				this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
				this.doEnchantDamageEffects(this, p_33638_);
			}
		}

	}
	
	protected int getJumpDelay() {
	    return this.random.nextInt(5) + 3;
	}
	
	protected SoundEvent getJumpSound() {
	    return SoundEvents.SLIME_JUMP;
	}
	
	protected boolean isDealsDamage() {
	    return this.isEffectiveAi();
	}
	
	static class GoopAttackGoal extends Goal {
	      private final GoopEntity goop;
	      private int growTiredTimer;

	      public GoopAttackGoal(GoopEntity p_33648_) {
	         this.goop = p_33648_;
	         this.setFlags(EnumSet.of(Goal.Flag.LOOK));
	      }

	      public boolean canUse() {
	         LivingEntity livingentity = this.goop.getTarget();
	         if (livingentity == null) {
	            return false;
	         } else {
	            return true;//!this.goop.canAttack(livingentity) ? false : this.goop.getMoveControl() instanceof GoopEntity.GoopMoveControl;
	         }
	      }

	      public void start() {
	         this.growTiredTimer = reducedTickDelay(300);
	         super.start();
	      }

	      public boolean canContinueToUse() {
	         LivingEntity livingentity = this.goop.getTarget();
	         if (livingentity == null) {
	            return false;
	         } else if (!this.goop.canAttack(livingentity)) {
	            return false;
	         } else {
	            return --this.growTiredTimer > 0;
	         }
	      }

	      public boolean requiresUpdateEveryTick() {
	         return true;
	      }

	      public void tick() {
	         LivingEntity livingentity = this.goop.getTarget();
	         if (livingentity != null) {
	            this.goop.lookAt(livingentity, 10.0F, 10.0F);
	         }

	         ((GoopEntity.GoopMoveControl)this.goop.getMoveControl()).setDirection(this.goop.getYRot());
	      }
	   }

	   static class GoopEntityFloatGoal extends Goal {
	      private final GoopEntity goop;

	      public GoopEntityFloatGoal(GoopEntity p_33655_) {
	         this.goop = p_33655_;
	         this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	         p_33655_.getNavigation().setCanFloat(true);
	      }

	      public boolean canUse() {
	         return (this.goop.isInWater() || this.goop.isInLava()) && this.goop.getMoveControl() instanceof GoopEntity.GoopMoveControl;
	      }

	      public boolean requiresUpdateEveryTick() {
	         return true;
	      }

	      public void tick() {
	         if (this.goop.getRandom().nextFloat() < 0.8F) {
	            this.goop.getJumpControl().jump();
	         }

	         ((GoopEntity.GoopMoveControl)this.goop.getMoveControl()).setWantedMovement(1.2D);
	      }
	   }

	   static class GoopEntityKeepOnJumpingGoal extends Goal {
	      private final GoopEntity goop;

	      public GoopEntityKeepOnJumpingGoal(GoopEntity p_33660_) {
	         this.goop = p_33660_;
	         this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	      }

	      public boolean canUse() {
	         return !this.goop.isPassenger();
	      }

	      public void tick() {
	         ((GoopEntity.GoopMoveControl)this.goop.getMoveControl()).setWantedMovement(1.0D);
	      }
	   }

	   static class GoopMoveControl extends MoveControl {
	      private float yRot;
	      private int jumpDelay;
	      private final GoopEntity goop;

	      public GoopMoveControl(GoopEntity p_33668_) {
	         super(p_33668_);
	         this.goop = p_33668_;
	         this.yRot = 180.0F * p_33668_.getYRot() / (float)Math.PI;
	      }

	      public void setDirection(float p_33673_) {
	         this.yRot = p_33673_;
	      }

	      public void setWantedMovement(double p_33671_) {
	         this.speedModifier = p_33671_;
	         this.operation = MoveControl.Operation.MOVE_TO;
	      }

	      public void tick() {
	         this.mob.setYRot(this.rotlerp(this.mob.getYRot(), this.yRot, 90.0F));
	         this.mob.yHeadRot = this.mob.getYRot();
	         this.mob.yBodyRot = this.mob.getYRot();
	         if (this.operation != MoveControl.Operation.MOVE_TO) {
	            this.mob.setZza(0.0F);
	         } else {
	            this.operation = MoveControl.Operation.WAIT;
	            if (this.mob.isOnGround()) {
	               this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
	               if (this.jumpDelay-- <= 0) {
	                  this.jumpDelay = this.goop.getJumpDelay();

	                  this.goop.getJumpControl().jump();
	                  this.goop.playSound(this.goop.getJumpSound(), this.goop.getSoundVolume(), 0.1f);
	               } else {
	                  //this.goop.xxa = 0.0F;
	                  //this.goop.zza = 0.0F;
	                  this.mob.setSpeed((float) this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
	               }
	            } else {
	               this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
	            }

	         }
	      }
	   }

	   static class GoopEntityRandomDirectionGoal extends Goal {
	      private final GoopEntity goop;
	      private float chosenDegrees;
	      private int nextRandomizeTime;

	      public GoopEntityRandomDirectionGoal(GoopEntity p_33679_) {
	         this.goop = p_33679_;
	         this.setFlags(EnumSet.of(Goal.Flag.LOOK));
	      }

	      public boolean canUse() {
	         return this.goop.getTarget() == null && (this.goop.onGround || this.goop.isInWater() || this.goop.isInLava() || this.goop.hasEffect(MobEffects.LEVITATION)) && this.goop.getMoveControl() instanceof GoopEntity.GoopMoveControl;
	      }

	      public void tick() {
	         if (--this.nextRandomizeTime <= 0) {
	            this.nextRandomizeTime = this.adjustedTickDelay(40 + this.goop.getRandom().nextInt(60));
	            this.chosenDegrees = (float)this.goop.getRandom().nextInt(360);
	         }

	         ((GoopEntity.GoopMoveControl)this.goop.getMoveControl()).setDirection(this.chosenDegrees);
	      }
	   }

}
