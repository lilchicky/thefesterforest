package com.gmail.thelilchicken01.tff.entity.custom;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.capability.PetNameHandler;
import com.gmail.thelilchicken01.tff.capability.PetSpawnHandler;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item.ItemUtil;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
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

public class IceRambleEntity extends TamableAnimal implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(IceRambleEntity.class, EntityDataSerializers.OPTIONAL_UUID);

	public IceRambleEntity(EntityType<? extends TamableAnimal> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		//setInvulnerable(true);
		//setPersistenceRequired();
		
	}
	
	@Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OWNER_UUID, Optional.of(Util.NIL_UUID));
    }
	
	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 200.0f)
				.add(Attributes.ATTACK_DAMAGE, 10.0f)
				.add(Attributes.ATTACK_SPEED, 1.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.24f).build();
	}
	
	protected void registerGoals() {
		
		//first num is prio
		
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false)); // speed modifier, follow even if no line of sight (this was PanicGoal(this, speed mod)
		this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.00));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0f)); //look distance
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		
		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
	    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Monster.class, true));
		
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.GRASS_STEP, 0.1f, 1.0f); // VOLUME - PITCH
		
	}
	
	public void connectToPlayer(Player player) {
		if (player instanceof ServerPlayer serverPlayer) {
			player.getCapability(PetNameHandler.CAPABILITY).ifPresent(
					handler -> {
						
						handler.setPetUUID(getUUID());
						handler.syncPetUUID(serverPlayer);
					}
				);
		}
	}
	
	private boolean ownerNearby() {
		boolean nearby = false;
		
		List<Entity> nearbyEntities = this.getLevel().getEntities(this, new AABB(
				this.getX() - 32, 
				this.getY() - 32, 
				this.getZ() - 32, 
				this.getX() + 32, 
				this.getY() + 32, 
				this.getZ() + 32));
		
		playerCheck:
		for (int x = 0; x < nearbyEntities.size(); x++) {
			if (nearbyEntities.get(x) instanceof Player) {
				Player playerCheck = (Player) nearbyEntities.get(x);
				if (this.isOwnedBy(playerCheck)) {
					nearby = true;
					break playerCheck;
				}
			}
		}
		
		return nearby;
		
	}
	
	private void killSelf(Player player) {
		if (player instanceof ServerPlayer serverPlayer) {
			player.getCapability(PetSpawnHandler.CAPABILITY).ifPresent(
					handler -> {
						
						handler.setHasPet(false);
						handler.syncPet(serverPlayer);
					}
				);
			// set UUID to player, as placeholder
			player.getCapability(PetNameHandler.CAPABILITY).ifPresent(
					handler -> {
						
						handler.setPetUUID(Util.NIL_UUID);
						handler.syncPetUUID(serverPlayer);
					}
				);
		}
		this.remove(RemovalReason.KILLED);
	}
	
	@Override
	public void tick() {
		
		super.tick();
		
		if (this.getOwner() != null && this.getOwner() instanceof Player player) {
			
			if (ArmorSets.GLACIAL.getArmorSet((Player) this.getOwner()) == SetCount.FOUR) {
				addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 15, 0));
			}
			
			if (ArmorSets.GLACIAL.getArmorSet((Player) this.getOwner()) == SetCount.EMPTY || !ownerNearby()) {
				killSelf(player);
			}
			
		}
		
		if (this.getHealth() <= 0 || getOwner() == null) {
			if (this.getOwner() != null && this.getOwner() instanceof Player player) {
				killSelf(player);
			}
			this.remove(RemovalReason.KILLED);
		}
		
	}
	
	protected SoundEvent getAmbientSound() { return null; }
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.GLASS_BREAK; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.GLASS_BREAK; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if(event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ice_ramble.walk", true));
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ice_ramble.idle", true));
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
