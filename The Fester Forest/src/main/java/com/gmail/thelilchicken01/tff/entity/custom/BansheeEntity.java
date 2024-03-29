package com.gmail.thelilchicken01.tff.entity.custom;

import java.util.List;
import java.util.Vector;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.item_util.ICuriosUtil;
import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;
import com.gmail.thelilchicken01.tff.util.InventoryHelper;
import com.mojang.math.Vector3d;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
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
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class BansheeEntity extends Monster implements IAnimatable {
	
	private AnimationFactory factory = GeckoLibUtil.createFactory(this);

	public BansheeEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 30.00)
				.add(Attributes.ATTACK_DAMAGE, 14.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.ARMOR, 5.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.24f).build();
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
	
	@Override
	public void tick() {
		
		super.tick();
		
		if (this.tickCount % 100 == 0 && this.getHealth() > 0.0f) {
			
			List<Player> nearbyPlayers = this.getLevel().getNearbyEntities(Player.class, TargetingConditions.DEFAULT, this, new AABB(
					this.getX() - 4, 
					this.getY() - 4, 
					this.getZ() - 4, 
					this.getX() + 4, 
					this.getY() + 4, 
					this.getZ() + 4));
			
			if (nearbyPlayers.size() != 0) {
				
				boolean playerIsTargetable = true;
				
				for (Player player : nearbyPlayers) {
					
					playerIsTargetable = !(player.isCreative() || player.isSpectator());
				
					if (playerIsTargetable && this.hasLineOfSight(player) && !player.isCrouching()) {
					
						Vec3 playerVel = this.getPosition(1.0f);
						Vec3 entityVel = player.getPosition(1.0f);
						Vec3 newVel = ((entityVel.subtract(playerVel)).add(new Vec3(0.0, 0.6, 0.0)).multiply(0.4, 0.8, 0.4).normalize());
					
						player.setDeltaMovement(newVel);
						player.hurt(ItemUtil.entityDamageSource("banshee_mob", player, this).bypassArmor(), 2);
					
					}
				
				}
				
				this.playSound(SoundEvents.PHANTOM_AMBIENT, 1.2f, 1.4f);
			
			}
			
		}
		
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.WOOL_STEP, 0.0f, 0.1f); // VOLUME - PITCH
		
	}
	
	
	protected SoundEvent getAmbientSound() { return null; }
	
	@Override
	protected void playHurtSound(DamageSource p_21493_) {
		
		this.playSound(SoundEvents.WITHER_SKELETON_HURT, 0.2f, 1.5f);
	}
	
	protected SoundEvent getDeathSound() {return SoundEvents.SKELETON_HURT; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if(event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.banshee.walk", EDefaultLoopTypes.LOOP));
			return PlayState.CONTINUE;
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.banshee.idle", EDefaultLoopTypes.LOOP));
		return PlayState.CONTINUE;
		
	}

	@Override
	public void registerControllers(AnimationData data) {
		
		data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
		
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

}
