package com.gmail.thelilchicken01.tff.entity.custom;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.ModWaterMonster;

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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class RotfishEntity extends ModWaterMonster implements IAnimatable {
	
	private AnimationFactory factory = GeckoLibUtil.createFactory(this);
	
	protected RandomStrollGoal randomStrollGoal;

	public RotfishEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		
		super(p_33002_, p_33003_);
		
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
		this.moveControl = new ModWaterMonster.WaterMoveControl(this);
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 35.00)
				.add(Attributes.ATTACK_DAMAGE, 18.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.ARMOR, 4.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.3f).build();
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
		
		this.goalSelector.addGoal(2, new ModWaterMonster.WaterMeleeAttackGoal(this, 1.3, false));
	    this.goalSelector.addGoal(4, new ModWaterMonster.FishSwimGoal(this));
	    
	    this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, RotfishEntity.class)).setAlertOthers(RotfishEntity.class));
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::okTarget));
		
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
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.TROPICAL_FISH_HURT; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.TROPICAL_FISH_DEATH; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if(event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rotfish.walk", EDefaultLoopTypes.LOOP));
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rotfish.idle", EDefaultLoopTypes.LOOP));
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
	
}
