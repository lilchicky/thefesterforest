package com.gmail.thelilchicken01.tff.entity.custom;

import java.util.List;

import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class ReetleQueenEntity extends TamableAnimal implements IAnimatable {
	
	private AnimationFactory factory = GeckoLibUtil.createFactory(this);
	
	private int spawnSeconds = 1;
	private int spawnCooldown;
	
	private boolean pacified = false;
	
	private List<Entity> nearbyEntities;

	public ReetleQueenEntity(EntityType<? extends TamableAnimal> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 120.00)
				.add(Attributes.ATTACK_DAMAGE, 24.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.01f)
				.add(Attributes.ARMOR, 25.0f)
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
	public boolean isPersistenceRequired() {
		return true;
	}
	
	@Override
	public void tick() {
		
		super.tick();
		
		if(!getLevel().isClientSide()) {
		
			if(!pacified) spawnCooldown++;
		
			if (spawnCooldown > spawnSeconds * 20 && getTarget() instanceof Player && !pacified) {
			
				CrunchBeetleEntity minion = new CrunchBeetleEntity(ModEntityTypes.CRUNCH_BEETLE.get(), getLevel());
			
				minion.setPos(getX(), getY(), getZ());
				minion.setTarget(getTarget());
				minion.setHealth(1.0f);
				minion.setSummoned();
			
				getLevel().addFreshEntity(minion);
			
				spawnCooldown = 0;
			
			}
		
			if (this.hasCustomName()) {
				if (this.getCustomName().equals(new TextComponent("Little Lady"))) {
					
					
					nearbyEntities = this.getLevel().getEntities(this, new AABB(this.getX() - 8, this.getY() - 8, this.getZ() - 8, this.getX() + 8, this.getY() + 8, this.getZ() + 8));
					
					for (int x = 0; x < nearbyEntities.size(); x++) {
						
						if (nearbyEntities.get(x) instanceof Player) {
							
							if ((Player) nearbyEntities.get(x) instanceof ServerPlayer) {
						         CriteriaTriggers.TAME_ANIMAL.trigger((ServerPlayer)nearbyEntities.get(x), this);
						    }
							
						}
						
					}
					
					this.targetSelector.removeAllGoals();
					this.goalSelector.removeGoal(new MeleeAttackGoal(this, 1.005, false));
					this.setTarget(null);
					pacified = true;
				}
			}
		
		}
	}
	
	@Override
	public MobType getMobType() {
		return MobType.ARTHROPOD;
	}
	
	@Override
	public boolean canBeLeashed(Player p_21418_) {
		
		return pacified;
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.GRASS_STEP, 0.1f, 1.0f); // VOLUME - PITCH
		
	}
	
	protected SoundEvent getAmbientSound() { return SoundEvents.SILVERFISH_STEP; }
	
	@Override
	public float getVoicePitch() {
		return 1.2f;
	}
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.TURTLE_EGG_BREAK; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.SILVERFISH_DEATH; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.reetle_queen.idlevar2", EDefaultLoopTypes.LOOP));
		
		return PlayState.CONTINUE;
		
	}
	
	private <E extends IAnimatable> PlayState attackPredicate(AnimationEvent<E> event) {
		
		if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
			
			event.getController().markNeedsReload();
			
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.reetle_queen.attack", EDefaultLoopTypes.PLAY_ONCE));
			
			this.swinging = false;
			
		}
		
		return PlayState.CONTINUE;
		
	}

	@Override
	public void registerControllers(AnimationData data) {
		
		data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
		data.addAnimationController(new AnimationController<>(this, "attackcontroller", 0, this::attackPredicate));
		
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
