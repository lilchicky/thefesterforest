package com.gmail.thelilchicken01.tff.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.AbstractSkeleton;
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

public class PlayerCrunchBeetleEntity extends TamableAnimal implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);
	private int lifespanSeconds = 20;
	private int lifespanTicker = 0;

	public PlayerCrunchBeetleEntity(EntityType<? extends TamableAnimal> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 7.00)
				.add(Attributes.ATTACK_DAMAGE, 2.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.3f).build();
	}
	
	protected void registerGoals() {
		
		//first num is prio
		
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.005, false)); // speed modifier, follow even if no line of sight (this was PanicGoal(this, speed mod)
		this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.00));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0f)); //look distance
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		
		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
	    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Monster.class, true));
		
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.GRASS_STEP, 0.1f, 1.0f); // VOLUME - PITCH
		
	}
	
	@Override
	public void tick() {
		
		lifespanTicker++;
		
		if (lifespanTicker > lifespanSeconds * 20) {
			
			remove(RemovalReason.KILLED);
			
		}
		
		super.tick();
	}
	
	protected SoundEvent getAmbientSound() { return SoundEvents.SILVERFISH_STEP; }
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.TURTLE_EGG_BREAK; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.SILVERFISH_DEATH; }
	
	protected float getSoundVolume() {return 0.2f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if(event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crunch_beetle.walk", true));
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crunch_beetle.idle", true));
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
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		
		return null;
	}

}
