package com.gmail.thelilchicken01.tff.entity.custom;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.dull.ShroomBucket;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class CorrodedShroomEntity extends TamableAnimal implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	protected RandomStrollGoal randomStrollGoal;
	
	private int fedLevel = 0;

	public CorrodedShroomEntity(EntityType<? extends TamableAnimal> p_33002_, Level p_33003_) {
		
		super(p_33002_, p_33003_);
		
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
		this.moveControl = new CorrodedShroomEntity.WaterMoveControl(this);
	}
	
	public static AttributeSupplier setAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 10.00)
				.add(Attributes.ATTACK_DAMAGE, 5.0f)
				.add(Attributes.ATTACK_SPEED, 2.0f)
				.add(Attributes.ARMOR, 0.0f)
				.add(Attributes.MOVEMENT_SPEED, 0.25f).build();
	}
	
	@Override
	public MobType getMobType() {
		return MobType.WATER;
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.9f;
	}
	
	protected void registerGoals() {
		
		this.goalSelector.addGoal(2, new CorrodedShroomEntity.WaterMeleeAttackGoal(this, 1.3, false));
	    this.goalSelector.addGoal(4, new CorrodedShroomEntity.FishSwimGoal(this));
	    
	    this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, CorrodedShroomEntity.class)).setAlertOthers(CorrodedShroomEntity.class));
		
	}
	
	public boolean okTarget(@Nullable LivingEntity target) {
		if (target != null) {
			return target.isInWater();
		} 
		
		else {
			return false;
		}
	}
	
	protected PathNavigation createNavigation(Level world) {
		return new WaterBoundPathNavigation(this, world);
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		
		this.playSound(SoundEvents.FISH_SWIM, 0.07f, 0.5f); // VOLUME - PITCH
		
	}
	
	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		
		if (player.getItemInHand(hand).getItem() == BlockInit.CORRODED_SHROOM.get().asItem()) {
			
			fedLevel++;
			
			if (getLevel().isClientSide()) {
				
				getLevel().addParticle(ParticleTypes.HEART, this.getX(), this.getY() + 1, this.getZ(), 0.0d, 0.25d, 0.0d);
				
			}
			
			if (fedLevel > (16 + (Math.random() * 16))) {
				
				if (isBaby()) {
					this.setBaby(false);
				}
				else {
					CorrodedShroomEntity baby = new CorrodedShroomEntity(ModEntityTypes.CORRODED_SHROOM.get(), getLevel());
					baby.setBaby(true);
					baby.setPos(this.getX(), this.getY(), this.getZ());
				
					if (!getLevel().isClientSide()) {
					
						getLevel().addFreshEntity(baby);
					
					}
					if ((Player) player instanceof ServerPlayer) {
				         CriteriaTriggers.BRED_ANIMALS.trigger((ServerPlayer) player, this, this, baby);
				    }
				}
				
				fedLevel = 0;
				
			}
			
			player.getItemInHand(hand).shrink(1);
			
		}
		
		if ((player.getItemInHand(hand).getItem() == Items.BUCKET || player.getItemInHand(hand).getItem() == Items.WATER_BUCKET) && !isBaby()) {
			
			player.getItemInHand(hand).shrink(1);
			
			ItemStack bucket = new ItemStack(ItemInit.SHROOM_BUCKET.get());
			
			player.addItem(bucket);
			
			this.remove(RemovalReason.DISCARDED);
			
		}
		
		return super.mobInteract(player, hand);
	}
	
	public boolean canBreatheUnderwater() {
		return true;
	}
	
	protected SoundEvent getAmbientSound() { return SoundEvents.TROPICAL_FISH_AMBIENT; }
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.DOLPHIN_HURT; }
	
	protected SoundEvent getDeathSound() {return SoundEvents.DOLPHIN_DEATH; }
	
	protected float getSoundVolume() {return 1.0f;}
	
	@SuppressWarnings("removal")
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		
		if (this.isInWater()) {
			
			if (event.isMoving()) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.corroded_shroom.swim", true));
				return PlayState.CONTINUE;
			}
			
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.corroded_shroom.idle", true));
			return PlayState.CONTINUE;
			
		}
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.corroded_shroom.walk", true));
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
	
	static class WaterMeleeAttackGoal extends MeleeAttackGoal {
		
		private final CorrodedShroomEntity SEATHROWN_SKELETON;

		public WaterMeleeAttackGoal(CorrodedShroomEntity fish, double p_25553_, boolean p_25554_) {
			super(fish, p_25553_, p_25554_);
			this.SEATHROWN_SKELETON = fish;
		}
		
		@Override
		public boolean canUse() {
			return super.canUse() && this.SEATHROWN_SKELETON.okTarget(this.SEATHROWN_SKELETON.getTarget());
		}
		
		@Override
		public boolean canContinueToUse() {
			return super.canContinueToUse() && this.SEATHROWN_SKELETON.okTarget(this.SEATHROWN_SKELETON.getTarget());
		}
		
	}
	
	static class WaterMoveControl extends MoveControl {
	      private final CorrodedShroomEntity thisFish;

	      WaterMoveControl(CorrodedShroomEntity seathrownSkeleton) {
	         super(seathrownSkeleton);
	         this.thisFish = seathrownSkeleton;
	      }

	      public void tick() {
	         if (this.thisFish.isEyeInFluid(FluidTags.WATER)) {
	            this.thisFish.setDeltaMovement(this.thisFish.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
	         }

	         if (this.operation == MoveControl.Operation.MOVE_TO && !this.thisFish.getNavigation().isDone()) {
	            float f = (float)(this.speedModifier * this.thisFish.getAttributeValue(Attributes.MOVEMENT_SPEED));
	            this.thisFish.setSpeed(Mth.lerp(0.125F, this.thisFish.getSpeed(), f));
	            double d0 = this.wantedX - this.thisFish.getX();
	            double d1 = this.wantedY - this.thisFish.getY();
	            double d2 = this.wantedZ - this.thisFish.getZ();
	            if (d1 != 0.0D) {
	               double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
	               this.thisFish.setDeltaMovement(this.thisFish.getDeltaMovement().add(0.0D, (double)this.thisFish.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
	            }

	            if (d0 != 0.0D || d2 != 0.0D) {
	               float f1 = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
	               this.thisFish.setYRot(this.rotlerp(this.thisFish.getYRot(), f1, 90.0F));
	               this.thisFish.yBodyRot = this.thisFish.getYRot();
	            }

	         } else {
	            this.thisFish.setSpeed(0.0F);
	         }
	      }
	   }

	   static class FishSwimGoal extends RandomSwimmingGoal {

	      public FishSwimGoal(CorrodedShroomEntity seathrownSkeleton) {
	         super(seathrownSkeleton, 1.0D, 40);
	      }

	      public boolean canUse() {
	         return super.canUse();
	      }
	   }

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		
		return ModEntityTypes.CORRODED_SHROOM.get().create(level);
	}

}
