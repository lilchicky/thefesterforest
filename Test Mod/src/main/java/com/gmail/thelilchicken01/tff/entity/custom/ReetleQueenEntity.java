package com.gmail.thelilchicken01.tff.entity.custom;

import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ReetleQueenEntity extends Monster implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private int spawnSeconds = 2;
	private int spawnCooldown;

	public ReetleQueenEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 120.00)
				.add(Attributes.ATTACK_DAMAGE, 15.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.01f)
				.add(Attributes.KNOCKBACK_RESISTANCE, 10.0f).build();
	}
	
	protected void registerGoals() {
		
		//first num is prio
		
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.005, false)); // speed modifier, follow even if no line of sight (this was PanicGoal(this, speed mod)
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0f)); //look distance
		//this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.00));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(CrunchBeetleEntity.class));
		
	}
	
	@Override
	public boolean isPushable() {
		return false;
	}
	
	@Override
	protected void dropExperience() {
		ExperienceOrb.award((ServerLevel)this.level, this.position(), 400);
	}
	
	@Override
	public void tick() {
		
		spawnCooldown++;
		
		if (spawnCooldown > spawnSeconds * 20 && getTarget() instanceof Player) {
			
			CrunchBeetleEntity minion = new CrunchBeetleEntity(ModEntityTypes.crunch_beetle.get(), getLevel());
			
			minion.setPos(getX(), getY(), getZ());
			minion.setTarget(getTarget());
			minion.setHealth(1.0f);
			
			getLevel().addFreshEntity(minion);
			
			spawnCooldown = 0;
			
		}
		
		super.tick();
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.GRASS_STEP, 0.1f, 1.0f); // VOLUME - PITCH
		
	}
	
	protected SoundEvent getAmbientSound() { return SoundEvents.SILVERFISH_STEP; }
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.TURTLE_EGG_BREAK; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.SILVERFISH_DEATH; }
	
	protected float getSoundVolume() {return 0.2f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.reetle_queen.idlevar2", true));
		
		return PlayState.CONTINUE;
		
	}
	
	private PlayState attackPredicate(AnimationEvent event) {
		
		if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
			
			event.getController().markNeedsReload();
			
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.reetle_queen.attack", false));
			
			this.swinging = false;
			
		}
		
		return PlayState.CONTINUE;
		
	}

	@Override
	public void registerControllers(AnimationData data) {
		
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
		data.addAnimationController(new AnimationController(this, "attackcontroller", 0, this::attackPredicate));
		
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

}
