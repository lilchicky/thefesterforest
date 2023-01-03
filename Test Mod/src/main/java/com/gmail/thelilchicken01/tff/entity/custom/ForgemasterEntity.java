package com.gmail.thelilchicken01.tff.entity.custom;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.projectile.MeteorCharge;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.projectile.Meteor;

import net.minecraft.client.ParticleStatus;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ForgemasterEntity extends Monster implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private double shootCooldown = 0.5;
	private int shootCounter;
	private int shootDamage = 15;
	
	private int launchRange = 3;
	private int launchCounter;
	private int launchCooldown = 5;

	public ForgemasterEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 800.00)
				.add(Attributes.ATTACK_DAMAGE, 30.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.ARMOR, 10.0f)
				.add(Attributes.KNOCKBACK_RESISTANCE, 10.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.15f).build();
	}
	
	@Override
	protected void dropExperience() {
		ExperienceOrb.award((ServerLevel)this.level, this.position(), 1000);
	}
	
	@Override
	public void tick() {
		
		shootCounter++;
		launchCounter++;
		
		if (shootCounter > shootCooldown * 20 && this.getTarget() != null) {
			
			ItemStack ammo = new ItemStack(ItemInit.meteor_charge.get());
			Meteor bulletItem = ItemInit.meteor_charge.get(); //these will be the same, but are what is being shot
			
			MeteorCharge shot = bulletItem.createProjectile(this.level, ammo, this);// level, shot item, this entity
			//shooting from rotation of entity looking
			
			shot.shootFromRotation(this, this.getXRot(), this.getYRot(), 
					0, 0.4f, (float)0); //speed, inaccuracy
			shot.setDamage(shootDamage); // set damage
			shot.setIgnoreInvulnerability(false);
			
			playSound(SoundEvents.BLAZE_SHOOT, 0.2f, 0.5f);
			this.level.addFreshEntity(shot);
			
			shootCounter = 0;
			
		}
		
		if (launchCounter > launchCooldown * 20 && this.getTarget() != null) {
			
			List<Entity> nearbyEntities = this.getLevel().getEntities(this, 
					new AABB(this.getX() - launchRange, 
							this.getY() - launchRange, 
							this.getZ() - launchRange, 
							this.getX() + launchRange, 
							this.getY() + launchRange, 
							this.getZ() + launchRange));
			
			this.playSound(SoundEvents.GENERIC_EXPLODE, 1.0f, 0.4f);
			
			for (int x = 0; x < 10; x++) {
				
				getLevel().addParticle(ParticleTypes.EXPLOSION, 
						this.getX() + ((Math.random() - 0.5) * 6), 
						this.getY(), 
						this.getZ() + ((Math.random() - 0.5) * 6), 
						0.0f, 
						0.0f, 
						0.0f);
				
			}
			
			for (int x = 0; x < nearbyEntities.size(); x++) {
			
				if (nearbyEntities.get(x) instanceof LivingEntity) {
					
					LivingEntity current = (LivingEntity)nearbyEntities.get(x);
					
					current.setDeltaMovement(current.getDeltaMovement().add(0.0, 1.0, 0.0).multiply(0.0, 2.0, 0.0));
					current.hurt(TheFesterForest.knockup_damage, 10);
				
				}
				
			}	
			
			launchCounter = 0;
			
		}
		
		super.tick();
	}
	
	protected void registerGoals() {
		
		//first num is prio
		
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.005, false)); // speed modifier, follow even if no line of sight
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0f)); //look distance
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.00));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)));
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
