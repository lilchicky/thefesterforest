package com.gmail.thelilchicken01.tff.entity.custom;

import java.util.List;

import com.gmail.thelilchicken01.tff.entity.projectile.FrostbittenBolt;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.projectile.FrostbittenBoltProjectile;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.BossEvent.BossBarColor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
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

public class FrostbittenKingEntity extends Monster implements IAnimatable {
	
	// Animation
	private AnimationFactory factory = GeckoLibUtil.createFactory(this);
	private boolean aboutToAttack = false;
	private boolean aboutToCooldown = false;
	private boolean rolling = false;
	
	// Bullets
	FrostbittenBoltProjectile bulletItem = ItemInit.FROSTBITTEN_BOLT.get();
	ItemStack shotAmmo = new ItemStack(ItemInit.FROSTBITTEN_BOLT.get());
	private int shotDamage = 30;
	
	// Phase and invulnerability related
	private double phase1 = 0.9;
	private double phase2 = 0.75;
	private double phase3 = 0.5;
	private double phase4 = 0.25;
	
	private int vLength = 10 * 20;
	private int ivLength = 30 * 20;
	
	private int vCounter = 0;
	
	// Values regarding whether an attack can take place
	boolean canAttack = false;
	int attackCooldown = 20;
	int attackValue = 0;
	int attackSpacer = 0;
	int rand = 0;
	List<Player> nearbyPlayers;
	int arenaRadius = 18;
	
	// Values for attack functions to be generated on attack
	int lines = 2;
	int spiralLines = 2;
	int rings = 1;
	int randoms = 25;
	
	// Counters for pulses on attacks
	private int defaultStraight = 20;
	private int straightCounter = 20;
	private int defaultSpiral = 35;
	private int spiralCounter = 35;
	
	private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(
			this.getDisplayName(), 
			BossEvent.BossBarColor.PURPLE, 
			BossEvent.BossBarOverlay.NOTCHED_20)).setDarkenScreen(true);

	public FrostbittenKingEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		setPersistenceRequired();
		setInvulnerable(true);
		bossEvent.setColor(BossBarColor.WHITE);
	}
	
	@Override
	public boolean isPushable() {
		return false;
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}
	
	@Override
	public boolean isNoAi() {
		return true;
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 2000.00)
				.add(Attributes.ATTACK_DAMAGE, 69.0f)
				.add(Attributes.ATTACK_SPEED, 420.0f)
				.add(Attributes.ARMOR, 30.0f)
				.add(Attributes.KNOCKBACK_RESISTANCE, 10.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.0001f).build();
	}
	
	protected void registerGoals() {
		
		//first num is prio
		
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 28.0f)); //look distance
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)));
		this.targetSelector.addGoal(1, (new NearestAttackableTargetGoal<>(this, Player.class, true)));
		
	}
	
	@Override
	public void tick() {
		
		super.tick();
		
		if (this.tickCount % 20 == 0) {
		
			if (this.hasCustomName()) {
				if (this.getCustomName().equals(new TextComponent("do a barrel roll"))) {
					rolling = true;
				}
				else {
					rolling = false;
				}
			}
		
		}
		
		nearbyPlayers = this.getLevel().getNearbyEntities(Player.class, TargetingConditions.DEFAULT, this, new AABB(
				this.getX() - arenaRadius, 
				this.getY() - arenaRadius, 
				this.getZ() - arenaRadius, 
				this.getX() + arenaRadius, 
				this.getY() + arenaRadius, 
				this.getZ() + arenaRadius));
		
		/*
		 * Get tick mod based on current health
		 */
		if (getHealth() < (getMaxHealth() * phase4)) {
			attackCooldown = 5;
		}
		else if (getHealth() < (getMaxHealth() * phase3)) {
			attackCooldown = 10;
		}
		else if (getHealth() < (getMaxHealth() * phase2)) {
			attackCooldown = 20;
		}
		else if (getHealth() < (getMaxHealth() * phase1)) {
			attackCooldown = 80;
		}
		else {
			attackCooldown = 100;
		}
		
		if (nearbyPlayers.size() != 0) {
			
			vCounter++;
			
			if (this.tickCount % 2 == 0) {
			
				for (Player player : nearbyPlayers) {
					if ((!(player.isSpectator() || player.isCreative()) && ((getY() + 2) <= player.getY() || getY() > player.getY())) && this.isInvulnerable()) {
						FrostbittenBolt shot = bulletItem.createProjectile(getLevel(), shotAmmo, this); 
						
						shot.setPos(getX(), getY() + getEyeHeight(), getZ());
						
						Vec3 currentPos = getEyePosition();
						Vec3 targetPos = player.getPosition(1.0f);
						Vec3 targetVector = targetPos.subtract(currentPos).normalize();
						
						shot.shoot(targetVector.x, targetVector.y + 0.1, targetVector.z, 0.6f, 0.0f);
						shot.setExtraShot(true);
						shot.setDamage(shotDamage / 2);
						shot.setIgnoreInvulnerability(true);
			
						getLevel().addFreshEntity(shot);
					}
				}
			
			}
		
			if (vCounter <= ivLength) {
				
				setInvulnerable(true);
				
				if (vCounter >= ivLength - 60) {
					
					if (vCounter == ivLength - 60) {
						bossEvent.setColor(BossBarColor.RED);
					}
					else if (vCounter == ivLength - 50) {
						bossEvent.setColor(BossBarColor.WHITE);
					}
					else if (vCounter == ivLength - 40) {
						bossEvent.setColor(BossBarColor.RED);
					}
					else if (vCounter == ivLength - 30) {
						bossEvent.setColor(BossBarColor.WHITE);
					}
					else if (vCounter == ivLength - 20) {
						bossEvent.setColor(BossBarColor.RED);
						aboutToCooldown = true;
					}
					else if (vCounter == ivLength - 10) {
						bossEvent.setColor(BossBarColor.WHITE);
					}
				}
				else {
					bossEvent.setColor(BossBarColor.WHITE);
				}
			
				/*
				 * Begin the Ability to Attack
				 */
				if (!canAttack && this.tickCount % attackCooldown == 0) {
					canAttack = true;
					attackValue = (int)(Math.random() * 5);
					lines = (int)(Math.random() * 7) + 6;
					spiralLines = (int)(Math.random() * 6) + 4;
					rings = (int)(Math.random() * 5) + 3;
					randoms = (int)(Math.random() * 50) + 50;
					rand = (int)(Math.random() * 361);
					attackSpacer = 100;
				}
				
				if (canAttack) {
					attackSpacer++;
					switch (attackValue) {
						// Straight line attack
						case 0:
							if (attackSpacer >= 3 && straightCounter > 0) {
								fireLaser(lines);
								straightCounter--;
								attackSpacer = 0;
								
								if (straightCounter == 0) {
									straightCounter = defaultStraight;
									canAttack = false;
								}
							}
							break;
						// Ring attack
						case 1:
							if (attackSpacer >= 20 && rings > 0) {
								fireRing();
								rings--;
								attackSpacer = 0;
								
								if (rings == 0) {
									canAttack = false;
								}
							}
							break;
						// Random Spread
						case 2:
							if (attackSpacer >= 1 && randoms > 0) {
								fireRandom();
								randoms--;
								attackSpacer = 0;
								
								if (randoms == 0) {
									canAttack = false;
								}
							}
							break;
						// Block shot
						case 3:
							if (attackSpacer >= 10 && straightCounter > 0) {
								fireBlock(rand);
								//fireBlock(rand + 90);
								fireBlock(rand + 180);
								//fireBlock(rand + 270);
								straightCounter--;
								attackSpacer = 0;
								
								if (straightCounter == 0) {
									straightCounter = defaultStraight;
									canAttack = false;
								}
							}
							break;
						// Spiral shot
						case 4:
							if (attackSpacer >= 2 && spiralCounter > 0) {
								fireSpiral(spiralCounter);
								spiralCounter--;
								attackSpacer = 0;
								
								if (spiralCounter == 0) {
									spiralCounter = defaultSpiral;
									canAttack = false;
								}
							}
							break;
						default:
							if (attackSpacer >= 2 && spiralCounter > 0) {
								fireSpiral(spiralCounter);
								spiralCounter--;
								attackSpacer = 0;
								
								if (spiralCounter == 0) {
									spiralCounter = defaultSpiral;
									canAttack = false;
								}
							}
							break;
					}
						
				}
			
			}
			
			else {
				
				setInvulnerable(false);
				
				if (vCounter <= ivLength + vLength) {
					if (vCounter >= (ivLength + vLength) - 60) {
						
						if (vCounter == (ivLength + vLength) - 60) {
							bossEvent.setColor(BossBarColor.RED);
						}
						else if (vCounter == (ivLength + vLength) - 50) {
							bossEvent.setColor(BossBarColor.PURPLE);
						}
						else if (vCounter == (ivLength + vLength) - 40) {
							bossEvent.setColor(BossBarColor.RED);
						}
						else if (vCounter == (ivLength + vLength) - 30) {
							bossEvent.setColor(BossBarColor.PURPLE);
						}
						else if (vCounter == (ivLength + vLength) - 20) {
							bossEvent.setColor(BossBarColor.RED);
							aboutToAttack = true;
						}
						else if (vCounter == (ivLength + vLength) - 10) {
							bossEvent.setColor(BossBarColor.PURPLE);
						}
					}
					else {
						bossEvent.setColor(BossBarColor.PURPLE);
					}
				}
				else {
					vCounter = 0;
				}
			}
			
			if (!getLevel().isClientSide()) {
				bossEvent.setProgress(getHealth() / getMaxHealth());
			}
		
		}
		
		else {
			setInvulnerable(true);
			bossEvent.setColor(BossBarColor.WHITE);
		}
		
	}
	
	private void fireLaser(int lines) {
		FrostbittenBolt shot;
		
		for (int x = 0; x < 360; x++) {
			
			if (x % (int)(360 / lines) == 0) {
			
				shot = bulletItem.createProjectile(getLevel(), shotAmmo, this); 
		
				shot.setPos(getX(), getY() + 1.2, getZ());
				shot.shootFromRotation(this, 0.0f, x + rand, 0.0f, 0.6f, 0.0f);
				shot.setDamage(shotDamage);
				shot.setIgnoreInvulnerability(false);
	
				getLevel().addFreshEntity(shot);
			
			}
		}
	}
	
	private void fireBlock(int rand) {
		FrostbittenBolt shot;
			
		for (int x = -50; x < 50; x += 5) {
			
			shot = bulletItem.createProjectile(getLevel(), shotAmmo, this); 
		
			shot.setPos(getX(), getY() + 1.2, getZ());
			shot.shootFromRotation(this, 0.0f, rand + x, 0.0f, 0.3f, 0.0f);
			shot.setDamage(shotDamage);
			shot.setIgnoreInvulnerability(false);
			getLevel().addFreshEntity(shot);
		}
	}
	
	private void fireRandom() {
		FrostbittenBolt shot;
		
			for (int x = 0; x < 3; x++) {
			shot = bulletItem.createProjectile(getLevel(), shotAmmo, this); 
			
			shot.setPos(getX(), getY() + 1.2, getZ());
			shot.shootFromRotation(this, 0.0f, (int)(Math.random() * 361), 0.0f, 0.4f, 0.0f);
			shot.setDamage(shotDamage);
			shot.setIgnoreInvulnerability(false);
	
			getLevel().addFreshEntity(shot);
		
		}
	}
	
	private void fireRing() {
		FrostbittenBolt shot;
		
		for (int x = 0; x < 360; x++) {
			
			if (x % 6 == 0) {
			
				shot = bulletItem.createProjectile(getLevel(), shotAmmo, this); 
		
				shot.setPos(getX(), getY() + 0.1, getZ());
				shot.shootFromRotation(this, 0.0f, x, 0.0f, 0.35f, 0.0f);
				shot.setDamage(shotDamage);
				shot.setIgnoreInvulnerability(false);
	
				getLevel().addFreshEntity(shot);
			
			}
		}
	}
	
	private void fireSpiral(int angle) {
		FrostbittenBolt shot;
		
		for (int x = 0; x < 360; x++) {
			
			if (x % (int)(360 / spiralLines) == 0) {
			
				shot = bulletItem.createProjectile(getLevel(), shotAmmo, this); 
		
				shot.setPos(getX(), getY() + 1.2, getZ());
				shot.shootFromRotation(this, 0.0f, x + (angle * 4), 0.0f, 0.5f, 0.0f);
				shot.setDamage(shotDamage);
				shot.setIgnoreInvulnerability(false);
	
				getLevel().addFreshEntity(shot);
			
			}
		}
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 0.15f, 0.5f); // VOLUME - PITCH
		
	}
	
	protected SoundEvent getAmbientSound() { return SoundEvents.SKELETON_AMBIENT; }
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.WITHER_SKELETON_HURT; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.SKELETON_HURT; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if (rolling) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frostbitten_king.do_a_barrel_roll", EDefaultLoopTypes.LOOP));
			return PlayState.CONTINUE;
		}
		else {
			if(isInvulnerable()) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frostbitten_king.attack_phase", EDefaultLoopTypes.LOOP));
				return PlayState.CONTINUE;
			}
			else {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frostbitten_king.vuln_phase", EDefaultLoopTypes.LOOP));
				return PlayState.CONTINUE;
			}
		}
		
	}
	
	private <E extends IAnimatable> PlayState transitionPredicate(AnimationEvent<E> event) {
		
		if (!rolling) {
		
			if (aboutToAttack) {
				
				event.getController().markNeedsReload();
				
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frostbitten_king.vuln_to_attack_transition", EDefaultLoopTypes.PLAY_ONCE));
				
				aboutToAttack = false;
				
			}
			
			else if (aboutToCooldown) {
				
				event.getController().markNeedsReload();
				
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frostbitten_king.attack_to_vuln_transition", EDefaultLoopTypes.PLAY_ONCE));
				
				aboutToCooldown = false;
				
			}
		
		}
		
		return PlayState.CONTINUE;
		
	}
	
	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	@Override
	public void registerControllers(AnimationData data) {
		
		data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
		data.addAnimationController(new AnimationController<>(this, "transitioncontroller", 0, this::transitionPredicate));
		
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
