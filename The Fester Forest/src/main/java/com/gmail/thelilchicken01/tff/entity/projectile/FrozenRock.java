package com.gmail.thelilchicken01.tff.entity.projectile;

import java.util.List;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.init.ParticleInit;
import com.gmail.thelilchicken01.tff.item.item.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item.MagicModUtil;
import com.gmail.thelilchicken01.tff.item.item.item_types.MagicOrb;
import com.gmail.thelilchicken01.tff.item.projectile.FrozenRockShot;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class FrozenRock extends Fireball {
	
	protected double damage = 15;
	protected boolean ignoreInvulnerability = false;
	protected double knockbackStrength = 0.1;
	protected int ticksSinceFired;
	protected LivingEntity target;
	
	private double shatterDamage = 15;

	public FrozenRock(EntityType<? extends FrozenRock> p_i50160_1_, Level p_i50160_2_) {
		super(p_i50160_1_, p_i50160_2_);
	}

	public FrozenRock(Level worldIn, LivingEntity shooter) {
		this(worldIn, shooter, 0, 0, 0);
		setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
	}

	public FrozenRock(Level worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
		super(ModEntityTypes.frozen_rock.get(), shooter, accelX, accelY, accelZ, worldIn);
	}

	public FrozenRock(Level worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(ModEntityTypes.frozen_rock.get(), x, y, z, accelX, accelY, accelZ, worldIn);
	}
	
	private static final double STOP_TRESHOLD = 0.01;
	
	@Override
	public void tick() {
		
		super.tick();
		
		if(!getLevel().isClientSide()) {
			ticksSinceFired++;
			if (ticksSinceFired > 20 || getDeltaMovement().lengthSqr() < STOP_TRESHOLD) {
				remove(RemovalReason.KILLED);
			}
		}
		
	}
	
	@Override
	protected ParticleOptions getTrailParticle() {
		return ParticleTypes.SNOWFLAKE;
	}
	
	public void setShatterDamage(double d) {
		shatterDamage = d;
	}
	
	@Override
	protected void onHitEntity(EntityHitResult raytrace) {
		super.onHitEntity(raytrace);
		if (!level.isClientSide) {
			Entity target = raytrace.getEntity();
			Entity shooter = getOwner();
			FrozenRockShot bullet = (FrozenRockShot) getItemRaw().getItem();
			
			if (isOnFire()) target.setSecondsOnFire(5);
			int lastHurtResistant = target.invulnerableTime;
			if (ignoreInvulnerability) target.invulnerableTime = 0;
			boolean damaged = target.hurt(new IndirectEntityDamageSource(TheFesterForest.MODID + "_frozen_rock_damage",
					this, shooter).setProjectile(),
					(float) bullet.modifyDamage(damage, this, target, shooter, level));
			
			if (damaged && target instanceof LivingEntity) {
				LivingEntity livingTarget = (LivingEntity)target;
				if (knockbackStrength > 0) {
					double actualKnockback = knockbackStrength;
					
					Vec3 vec = getDeltaMovement().multiply(1, 0, 1).normalize().scale(actualKnockback);
					if (vec.lengthSqr() > 0) livingTarget.push(vec.x, 0.1, vec.z);
				}
				
				livingTarget.setTicksFrozen(240);
				
				if (shooter instanceof Player) {
					Player player = (Player) shooter;
					
					List<LivingEntity> nearbyEntities = this.getLevel().getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, player, new AABB(
							this.getX() - 3, 
							this.getY() - 3, 
							this.getZ() - 3, 
							this.getX() + 3, 
							this.getY() + 3, 
							this.getZ() + 3));
					
					for (LivingEntity currentEntity : nearbyEntities) {
						
						if (player.getOffhandItem().getItem() instanceof MagicOrb) {
							MagicModUtil.getMagicMod(player, currentEntity, ((MagicOrb) (player.getOffhandItem().getItem())).getOrbType());
						}
						
						currentEntity.hurt(new IndirectEntityDamageSource(TheFesterForest.MODID + "_frozen_rock_damage",
						this, shooter), (float)shatterDamage);
						currentEntity.setTicksFrozen(240);
						
					}
					
				}

				if (shooter instanceof LivingEntity) {
					doEnchantDamageEffects((LivingEntity)shooter, target);
					if (shooter instanceof Player) {
						Player player = (Player) shooter;
						if (player.getOffhandItem().getItem() instanceof MagicOrb) {
							MagicModUtil.getMagicMod(player, target, ((MagicOrb) (player.getOffhandItem().getItem())).getOrbType());
						}
					}
				}
				
				bullet.onLivingEntityHit(this, livingTarget, shooter, level);
			}
			else if (!damaged && ignoreInvulnerability) target.invulnerableTime = lastHurtResistant;
		}
	}
	
	@Override
	protected void onHitBlock(BlockHitResult result) {
		
		if (!level.isClientSide) {
			
			BlockPos hitLoc = result.getBlockPos();
			@Nullable Entity owner = this.getOwner();
			if (owner != null && owner instanceof Player) {
				Player shooter = (Player) owner;
				
				List<LivingEntity> nearbyEntities = this.getLevel().getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, shooter, new AABB(
						hitLoc.getX() - 3, 
						hitLoc.getY() - 3, 
						hitLoc.getZ() - 3, 
						hitLoc.getX() + 3, 
						hitLoc.getY() + 3, 
						hitLoc.getZ() + 3));
				
				for (LivingEntity currentEntity : nearbyEntities) {
					
					if (shooter.getOffhandItem().getItem() instanceof MagicOrb) {
						MagicModUtil.getMagicMod(shooter, currentEntity, ((MagicOrb) (shooter.getOffhandItem().getItem())).getOrbType());
					}
					
					currentEntity.hurt(new IndirectEntityDamageSource(TheFesterForest.MODID + "_frozen_rock_damage",
					this, shooter), (float)shatterDamage);
					currentEntity.setTicksFrozen(240);
					
				}
				
			}
			
		}
		
		super.onHitBlock(result);
	}
	
	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		playSound(SoundEvents.GLASS_BREAK, 0.2f, 1.0f);
		if (!level.isClientSide && (!noPhysics || result.getType() != HitResult.Type.BLOCK)) remove(RemovalReason.KILLED);
	}
	
	@Override
	public void onRemovedFromWorld() {
		for (int x = 0; x < 360; x++) {
			
			if (x % 10 == 0) {
				
				this.getLevel().addParticle(ParticleInit.ICY_EXPLOSION_PARTICLE.get(), this.getX(), this.getY() + 0.5d, this.getZ(), 
						((Math.cos(x) * 1.75d) * (Math.random() + 0.5)), 0.0d + ((Math.random() - 0.5) * 0.25), ((Math.sin(x) * 1.75d) * (Math.random() + 0.5)));
				
				this.getLevel().addParticle(ParticleInit.ICY_EXPLOSION_PARTICLE.get(), this.getX(), this.getY() + 0.5d, this.getZ(), 
						((Math.cos(x) * 1.55d) * (Math.random() + 0.5)), 0.0d + ((Math.random() - 0.5) * 0.25), ((Math.sin(x) * 1.55d) * (Math.random() + 0.5)));
				
				this.getLevel().addParticle(ParticleInit.ICY_EXPLOSION_PARTICLE.get(), this.getX(), this.getY() + 0.5d, this.getZ(), 
						((Math.cos(x) * 1.35d) * (Math.random() + 0.5)), 0.0d + ((Math.random() - 0.5) * 0.25), ((Math.sin(x) * 1.35d) * (Math.random() + 0.5)));
				
			}
			
		}
		super.onRemovedFromWorld();
	}
	
	public void setDamage(double damage) {
		this.damage = damage;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public void setIgnoreInvulnerability(boolean ignoreInvulnerability) {
		this.ignoreInvulnerability = ignoreInvulnerability;
	}
	
	@Override
	public void shootFromRotation(Entity shooter, float xRot, float yRot, float p_37255_, float speed, float spread) {
		float f = -Mth.sin(yRot * ((float) Math.PI / 180F)) * Mth.cos(xRot * ((float) Math.PI / 180F));
		float f1 = -Mth.sin((xRot + p_37255_) * ((float) Math.PI / 180F));
		float f2 = Mth.cos(yRot * ((float) Math.PI / 180F)) * Mth.cos(xRot * ((float) Math.PI / 180F));
		shoot((double) f, (double) f1, (double) f2, speed, spread);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("tsf", ticksSinceFired);
		compound.putDouble("damage", damage);
		if (ignoreInvulnerability) compound.putBoolean("ignoreinv", ignoreInvulnerability);
		if (knockbackStrength != 0) compound.putDouble("knockback", knockbackStrength);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		ticksSinceFired = compound.getInt("tsf");
		damage = compound.getDouble("damage");
		ignoreInvulnerability = compound.getBoolean("ignoreinv");
		knockbackStrength = compound.getDouble("knockback");
	}
	
	public void setKnockbackStrength(double knockbackStrength) {
		this.knockbackStrength = knockbackStrength;
	}

	@Override
	public boolean isPickable() {
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		return false;
	}

	@Override
	protected boolean shouldBurn() {
		return false;
	}

	@Override
	protected float getInertia() {
		return 1.0f;
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
}
