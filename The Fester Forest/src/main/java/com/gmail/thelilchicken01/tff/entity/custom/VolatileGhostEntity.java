package com.gmail.thelilchicken01.tff.entity.custom;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class VolatileGhostEntity extends Monster implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private int explodeDamage = 50;
	private int nauseaDurationSeconds = 10;
	private boolean primed = false;
	private int primedTimer = 0;
	private List<Entity> nearbyEntities;

	public VolatileGhostEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 18.0f)
				.add(Attributes.ATTACK_DAMAGE, 12.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.ARMOR, 2.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.2f).build();
	}
	
	protected void registerGoals() {
		
		//first num is prio
		
		this.goalSelector.addGoal(5, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.4, false)); // speed modifier, follow even if no line of sight
		//this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0f)); //look distance
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.00));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
		this.targetSelector.addGoal(2, (new NearestAttackableTargetGoal<>(this, Player.class, true)));
		
	}
	
	@Override
	public void tick() {
		
		super.tick();
		
		if (!primed) {
			
			nearbyEntities = this.getLevel().getEntities(this, new AABB(this.getX() - 4, this.getY() - 4, this.getZ() - 4, this.getX() + 4, this.getY() + 4, this.getZ() + 4));
			
			playerCheck:
			for (int x = 0; x < nearbyEntities.size(); x++) {
				if (nearbyEntities.get(x) instanceof Player) {
					primed = true;
					this.playSound(SoundEvents.CREEPER_PRIMED, 1.0f, 1.5f);
					break playerCheck;
				}
			}
			
		}
		
		if (primed && primedTimer < 80) {
			primedTimer++;
			
		}
		
		if (primed && primedTimer >= 80) {
			
			this.playSound(SoundEvents.GENERIC_EXPLODE, 1.2f, 0.5f);
			
			this.getLevel().addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 0.5, 0.5, 0.5);
			
			nearbyEntities = this.getLevel().getEntities(this, new AABB(this.getX() - 4, this.getY() - 4, this.getZ() - 4, this.getX() + 4, this.getY() + 4, this.getZ() + 4));
		
			for (int x = 0; x < nearbyEntities.size(); x++) {
		
				if (nearbyEntities.get(x) instanceof LivingEntity) {						
		
					LivingEntity currentEntity = (LivingEntity) nearbyEntities.get(x);
				
					nearbyEntities.get(x).hurt(TheFesterForest.VOLATILE_GHOST, explodeDamage);
					currentEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, (nauseaDurationSeconds * 20), 200));
				}
		
			}
			
			primedTimer = 0;
			primed = false;
			this.remove(RemovalReason.KILLED);
		}
		
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 0.0f, 0.5f); // VOLUME - PITCH
		
	}
	
	protected SoundEvent getAmbientSound() { return SoundEvents.WOOL_BREAK; }
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.PLAYER_HURT_ON_FIRE; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.SKELETON_DEATH; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if(event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.volatile_ghost.walk", true));
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.volatile_ghost.idle", true));
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
