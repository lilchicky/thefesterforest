package com.gmail.thelilchicken01.tff.entity.custom;

import java.util.List;

import javax.xml.stream.Location;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.projectile.MeteorCharge;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.projectile.Meteor;

import net.minecraft.client.ParticleStatus;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ForgemasterEntity extends Monster implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(
			this.getDisplayName(), 
			BossEvent.BossBarColor.BLUE, 
			BossEvent.BossBarOverlay.NOTCHED_20)).setDarkenScreen(true);
	
	private double shootCooldown = 1; // default shot cooldown in seconds, before modifiers
	private int shootCounter;
	private int shootDamage = 15; // meteor damage
	
	private int launchRange = 3; // radius the knockup can hit you
	private int launchCounter;
	private int launchCooldown = 5; // knockup cooldown
	
	private int pylonCounter;
	private int pylonCooldown = 25; // main cooldown
	private int pylonCharge = 15; // cooldown before healing
	private int pylonCount = 16; // how many pylons are created
	private int pylonRadius = 12; // radius pylons are created in
	private int pylonChargeCounter;
	private boolean pylonActive = false; 
	
	private static float totalHealth = 800.0f; // entity health
	
	private int phase2health;
	private int phase3health;

	public ForgemasterEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		
		phase2health = (int) (getMaxHealth() * 0.8); // health threshold for phase 2
		phase3health = (int) (getMaxHealth() * 0.3); // health threshold for phase 3
		
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, totalHealth)
				.add(Attributes.ATTACK_DAMAGE, 30.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.ARMOR, 10.0f)
				.add(Attributes.KNOCKBACK_RESISTANCE, 10.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.15f).build();
	}
	
	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}
	
	@Override
	public boolean isPersistenceRequired() {
		return true;
	}
	
	@Override
	protected void dropExperience() {
		ExperienceOrb.award((ServerLevel)this.level, this.position(), 1000);
	}
	
	@Override
	public void tick() {
		
		super.tick();
		
		shootCounter++;
		launchCounter++;
		if (!pylonActive) {
			pylonCounter++;
		}
		else {
			pylonChargeCounter++;
		}
		
		//Always Running
		
		double cooldownMod = 1;
		
		if (getHealth() < phase2health) {
			cooldownMod = 0.6;
		}
		
		else if (getHealth() < phase3health) {
			cooldownMod = 0.3;
		}
		
		if(!getLevel().isClientSide) {
		
			if (shootCounter > (shootCooldown * cooldownMod) * 20 && this.getTarget() != null) {
				
				ItemStack ammo = new ItemStack(ItemInit.METEOR_CHARGE.get());
				Meteor bulletItem = ItemInit.METEOR_CHARGE.get(); //these will be the same, but are what is being shot
				
				MeteorCharge shot = bulletItem.createProjectile(this.level, ammo, this);// level, shot item, this entity
				
				Vec3 currentPos = getEyePosition();
				Vec3 targetPos = getTarget().getPosition(1.0f);
				Vec3 targetVector = targetPos.subtract(currentPos).normalize();
				
				shot.shoot(targetVector.x, targetVector.y + 0.1, targetVector.z, 0.4f, 0.0f);
				shot.setDamage(shootDamage); // set damage
				shot.setIgnoreInvulnerability(false);
				
				playSound(SoundEvents.BLAZE_SHOOT, 0.2f, 0.5f);
				this.level.addFreshEntity(shot);
				
				shootCounter = 0;
				
			}
			
			//Phase 2
			
			if (launchCounter > launchCooldown * 20 && this.getTarget() != null && getHealth() < phase2health) {
				
				List<Entity> nearbyEntities = this.getLevel().getEntities(this, 
						new AABB(this.getX() - launchRange, 
								this.getY() - launchRange, 
								this.getZ() - launchRange, 
								this.getX() + launchRange, 
								this.getY() + launchRange, 
								this.getZ() + launchRange));
				
				this.playSound(SoundEvents.GENERIC_EXPLODE, 1.0f, 0.4f);
				
				for (int x = 0; x < nearbyEntities.size(); x++) {
				
					if (nearbyEntities.get(x) instanceof LivingEntity && !(nearbyEntities.get(x) instanceof PylonEntity)) {
						
						LivingEntity current = (LivingEntity)nearbyEntities.get(x);
						
						current.setDeltaMovement(current.getDeltaMovement().add(0.0, 1.0, 0.0).multiply(0.0, 2.0, 0.0));
						current.hurt(TheFesterForest.KNOCKUP_DAMAGE, 10);
					
					}
					
				}	
				
				launchCounter = 0;
				
			}
			
			// Phase 3
			
			if (pylonCounter > pylonCooldown * 20 && getHealth() < phase3health) {
				
				playSound(SoundEvents.ANVIL_USE, 1.0f, 0.01f);
				
				int spawnedPylons = 0;
				int spawnTries = 0;
				
				pylonSpawn:
				while (spawnedPylons < pylonCount) {
					
					spawnTries++;
					
					PylonEntity pylon = new PylonEntity(ModEntityTypes.PYLON.get(), getLevel());
					
					double randomX = getX() + ((Math.random() - 0.5) * (pylonRadius * 2));
					double randomY = getY();
					double randomZ = getZ() + ((Math.random() - 0.5) * (pylonRadius * 2));
					
					BlockPos blockIn = new BlockPos(randomX, 
							randomY, 
							randomZ);
					
					BlockPos blockOn = new BlockPos(randomX, 
							randomY - 1, 
							randomZ);
					
					Block blockInside = getLevel().getBlockState(blockIn).getBlock();
					Block blockOnTopOf = getLevel().getBlockState(blockOn).getBlock();
					
					if (blockInside == Blocks.AIR && (blockOnTopOf == BlockInit.ROTTING_PLANKS.get() || 
							blockOnTopOf == BlockInit.ROTTING_WOOD.get())) {
						
						pylon.setPos(blockIn.getX(), blockIn.getY(), blockIn.getZ());
						
						getLevel().addFreshEntity(pylon);
						
						spawnedPylons++;
						
					}
					
					if (spawnTries > 100) {
						System.out.println("Chicky's Fester Forest: No available pylon spawn blocks! (Not a bug)");
						break pylonSpawn;
					}
					
					
				}
				
				pylonActive = true;
				pylonCounter = 0;
				
			}
			
			if (pylonChargeCounter > pylonCharge * 20 && getHealth() < phase3health) {
				
				int livingPylons = 0;
				
				playSound(SoundEvents.ANVIL_DESTROY, 1.0f, 0.01f);
				
				List<Entity> nearbyPylons = this.getLevel().getEntities(this, 
					new AABB(this.getX() - (pylonRadius + 16), 
							this.getY() - pylonRadius, 
							this.getZ() - (pylonRadius + 16), 
							this.getX() + (pylonRadius + 16), 
							this.getY() + pylonRadius, 
							this.getZ() + (pylonRadius + 16)));
				
				for (int x = 0; x < nearbyPylons.size(); x++) {
					
					if (nearbyPylons.get(x) instanceof PylonEntity) {
						
						((PylonEntity) nearbyPylons.get(x)).remove(RemovalReason.KILLED);
						livingPylons++;
						
					}
					
				}
				
				heal((float) livingPylons * 35);
				
				playSound(SoundEvents.REDSTONE_TORCH_BURNOUT, 1.0f, 0.1f);
				
				pylonActive = false;
				pylonChargeCounter = 0;
				
			}
		}
		
		if (getLevel().isClientSide) {
			
			if(launchCounter > launchCooldown * 20 && this.getTarget() != null && getHealth() < phase2health) {
				for (int x = 0; x < 10; x++) {
					
					getLevel().addParticle(ParticleTypes.EXPLOSION, 
							this.getX() + ((Math.random() - 0.5) * 6), 
							this.getY(), 
							this.getZ() + ((Math.random() - 0.5) * 6), 
							0.0f, 
							0.0f, 
							0.0f);
					
				}
			}
		}
	}
	
	protected void registerGoals() {
		
		//first num is prio
		
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.005, false)); // speed modifier, follow even if no line of sight
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0f)); //look distance
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.00));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
		this.targetSelector.addGoal(2, (new NearestAttackableTargetGoal<>(this, Player.class, true)));
		
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.IRON_GOLEM_STEP, 0.5f, 0.1f); // VOLUME - PITCH
		
	}
	
	protected SoundEvent getAmbientSound() { return SoundEvents.BLAZE_AMBIENT; }
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.IRON_GOLEM_HURT; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.BLAZE_DEATH; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if(event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.forgemaster.walk", true));
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.forgemaster.idle", true));
		return PlayState.CONTINUE;
		
	}
	
	private PlayState attackPredicate(AnimationEvent event) {
		
		if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
			
			event.getController().markNeedsReload();
			
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.forgemaster.attack", false));
			
			this.swinging = false;
			
		}
		
		return PlayState.CONTINUE;
		
	}

	@Override
	public void registerControllers(AnimationData data) {
		
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
		data.addAnimationController(new AnimationController(this, "attackcontroller", 0, this::attackPredicate));
		
	}
	
	public void startSeenByPlayer(ServerPlayer p_31483_) {
	    super.startSeenByPlayer(p_31483_);
	    this.bossEvent.addPlayer(p_31483_);
	}

	public void stopSeenByPlayer(ServerPlayer p_31488_) {
	    super.stopSeenByPlayer(p_31488_);
	    this.bossEvent.removePlayer(p_31488_);
	}
	
	@Override
	protected void customServerAiStep() {
		
		this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
		
		super.customServerAiStep();
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

}
