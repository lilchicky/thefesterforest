package com.gmail.thelilchicken01.tff.entity.custom;

import com.gmail.thelilchicken01.tff.entity.projectile.BoneCharge;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.projectile.BoneShot;

import net.minecraft.core.BlockPos;
import net.minecraft.server.bossevents.CustomBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
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

public class ForgemasterEntity extends Monster implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);
	private int shootCooldown = 3;
	private int shootCounter;
	private int shootDamage = 15;

	public ForgemasterEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 800.00)
				.add(Attributes.ATTACK_DAMAGE, 30.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.ARMOR, 10.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.15f).build();
	}
	
	@Override
	protected void dropExperience() {
		ExperienceOrb.award((ServerLevel)this.level, this.position(), 1000);
	}
	
	@Override
	public void tick() {
		
		shootCounter++;
		if (shootCounter > shootCooldown * 20) {
			
			ItemStack ammo = new ItemStack(ItemInit.bone_charge.get());
			BoneShot bulletItem = ItemInit.bone_charge.get(); //these will be the same, but are what is being shot
			
			BoneCharge shot = bulletItem.createProjectile(this.level, ammo, this);// level, shot item, this entity
			//shooting from rotation of entity looking
			shot.shootFromRotation(this, this.getXRot(), this.getYRot(), 0, 0.6f, (float)0); //speed, inaccuracy
			shot.setDamage(shootDamage); // set damage
			shot.setIgnoreInvulnerability(false);

			this.level.addFreshEntity(shot);
			
			shootCounter = 0;
			
		}
		
		super.tick();
	}
	
	protected void registerGoals() {
		
		//first num is prio
		
		this.goalSelector.addGoal(6, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.005, false)); // speed modifier, follow even if no line of sight
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0f)); //look distance
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.00));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, (new NearestAttackableTargetGoal(this, Player.class, true)));
		
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.IRON_GOLEM_STEP, 0.5f, 0.1f); // VOLUME - PITCH
		
	}
	
	protected SoundEvent getAmbientSound() { return SoundEvents.BLAZE_AMBIENT; }
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.IRON_GOLEM_HURT; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.BLAZE_DEATH; }
	
	protected float getSoundVolume() {return 0.2f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if(event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.forgemaster.walk", true));
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.forgemaster.idle", true));
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

}
