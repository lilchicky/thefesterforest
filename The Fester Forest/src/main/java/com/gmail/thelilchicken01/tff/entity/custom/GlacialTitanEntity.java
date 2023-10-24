package com.gmail.thelilchicken01.tff.entity.custom;

import com.gmail.thelilchicken01.tff.entity.projectile.FrostbittenBolt;
import com.gmail.thelilchicken01.tff.entity.projectile.FrozenRock;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.projectile.FrostbittenBoltProjectile;
import com.gmail.thelilchicken01.tff.item.projectile.FrozenRockShot;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GlacialTitanEntity extends Monster implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);

	public GlacialTitanEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 120.00)
				.add(Attributes.ATTACK_DAMAGE, 18.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.ARMOR, 10.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.15f).build();
	}
	
	@Override
	public void tick() {
		
		super.tick();
		
		if (this.tickCount % 80 == 0) {
			if (this.getTarget() != null && this.getTarget() instanceof Player player) {
				
				FrozenRockShot bulletItem = ItemInit.FROZEN_ROCK.get();
				ItemStack shotAmmo = new ItemStack(ItemInit.FROZEN_ROCK.get());
				
				FrozenRock shot = bulletItem.createProjectile(getLevel(), shotAmmo, this); 
				
				shot.setPos(getX(), getY() + getEyeHeight() + 1.0, getZ());
				
				Vec3 currentPos = getEyePosition().add(0.0, 1.0, 0.0);
				Vec3 targetPos = player.getPosition(1.0f);
				Vec3 targetVector = targetPos.subtract(currentPos).normalize();
				
				shot.shoot(targetVector.x, targetVector.y + 0.1, targetVector.z, 0.6f, 0.0f);
				shot.setDamage(25);
				shot.setShatterDamage(25.0);
				shot.setIgnoreInvulnerability(false);
	
				getLevel().addFreshEntity(shot);
				
			}
		}
		
	}
	
	protected void registerGoals() {
		
		//first num is prio
		
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.005, false)); // speed modifier, follow even if no line of sight
		//this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0f)); //look distance
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.00));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)));
		this.targetSelector.addGoal(1, (new NearestAttackableTargetGoal<>(this, Player.class, true)));
		
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.GRASS_STEP, 0.15f, 0.5f); // VOLUME - PITCH
		
	}
	
	protected SoundEvent getAmbientSound() { return SoundEvents.ZOMBIE_AMBIENT; }
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.GLASS_BREAK; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.ZOMBIE_DEATH; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if(event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.glacial_titan.walk", true));
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.glacial_titan.idle", true));
		return PlayState.CONTINUE;
		
	}
	
	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
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
