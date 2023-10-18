package com.gmail.thelilchicken01.tff.entity.custom;

import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.init.ParticleInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class WightEntity extends Monster implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);

	public WightEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 120.00)
				.add(Attributes.ATTACK_DAMAGE, 30.0f)
				.add(Attributes.ARMOR, 15.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.25f).build();
	}
	
	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}
	
	protected void registerGoals() {
		
		//first num is prio
		
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.005, false)); // speed modifier, follow even if no line of sight
		//this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0f)); //look distance
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.00));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
		this.targetSelector.addGoal(2, (new NearestAttackableTargetGoal<>(this, Player.class, true)));
		
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.NETHERRACK_STEP, 0.15f, 0.5f); // VOLUME - PITCH
		
	}
	
	@Override
	public boolean doHurtTarget(Entity target) {
		
		if (!target.getLevel().isClientSide()) {
			
			this.heal((float) 0.5 * (getMaxHealth() - getHealth()));
			this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 2));
			
			this.getLevel().addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 0.5, 0.5, 0.5);
			
		}
		
		return super.doHurtTarget(target);
	}
	
	protected SoundEvent getAmbientSound() { return SoundEvents.SKELETON_AMBIENT; }
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.SLIME_BLOCK_HIT; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.SKELETON_HURT; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if(event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.wight.walk", true));
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.wight.idle", true));
		return PlayState.CONTINUE;
		
	}

	@Override
	public void registerControllers(AnimationData data) {
		
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
		
	}
	
	@Override
	public ItemStack getMainHandItem() {
		
		return new ItemStack(ItemInit.LIFE_SCYTHE.get(), 1);
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

}
