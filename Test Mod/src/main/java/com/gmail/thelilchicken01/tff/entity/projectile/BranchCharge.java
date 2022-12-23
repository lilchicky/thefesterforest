package com.gmail.thelilchicken01.tff.entity.projectile;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.item.projectile.IBullet;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class BranchCharge extends Fireball {
	protected double damage = 1;
	protected boolean ignoreInvulnerability = false;
	protected double knockbackStrength = 0;
	protected int ticksSinceFired;
	protected LivingEntity target;

	public BranchCharge(EntityType<? extends BranchCharge> p_i50160_1_, Level p_i50160_2_) {
		super(p_i50160_1_, p_i50160_2_);
	}

	public BranchCharge(Level worldIn, LivingEntity shooter) {
		this(worldIn, shooter, 0, 0, 0);
		setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
		setTarget(shooter, worldIn);
	}

	public BranchCharge(Level worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
		super(ModEntityTypes.branch_charge.get(), shooter, accelX, accelY, accelZ, worldIn);
		setTarget(shooter, worldIn);
	}

	public BranchCharge(Level worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(ModEntityTypes.branch_charge.get(), x, y, z, accelX, accelY, accelZ, worldIn);
	}

	private static final double STOP_TRESHOLD = 0.01;

	@Override
	public void tick() {
		//Using a thing I save so that bullets don't get clogged up on chunk borders
		ticksSinceFired++;
		if (ticksSinceFired > 40 || getDeltaMovement().lengthSqr() < STOP_TRESHOLD) {
			remove(RemovalReason.KILLED);
		}
		
//		if(target != null) {
//			
//			Vec3 projectileVel = this.getPosition(1.0f);
//			Vec3 targetVel = target.getPosition(1.0f);
//			Vec3 newVel = ((targetVel.subtract(projectileVel)).multiply(0.05, 0.05, 0.05).normalize());
//			
//			this.setDeltaMovement(newVel);
//			System.out.println(newVel);
//			
//		}
		
		super.tick();
	}
	
	@Override
	protected ParticleOptions getTrailParticle() {
		
		return ParticleTypes.HAPPY_VILLAGER;
	}
	
	private void setTarget(LivingEntity player, Level world) {
		
		LivingEntity closestEntity = null;
		double closestEntityDistance = 100.0;
		
		List<Entity> nearestEntity = world.getEntities(player, 
				new AABB(player.getX() - 10, 
						player.getY() - 10, 
						player.getZ() - 10, 
						player.getX() + 10, 
						player.getY() + 10, 
						player.getZ() + 10));
		
		for (int x = 0; x < nearestEntity.size(); x++) {
		
			if (nearestEntity.get(x) instanceof LivingEntity) {
				
				LivingEntity currentEntity = (LivingEntity)nearestEntity.get(x);
				
				if(player.hasLineOfSight(currentEntity)) {
					
					if(player.distanceTo(currentEntity) < closestEntityDistance) {
						
						closestEntity = currentEntity;
						
					}
					
				}
			
			}
			
		}
		
		target = closestEntity;
		
	}

	@Override
	protected void onHitEntity(EntityHitResult raytrace) {
		super.onHitEntity(raytrace);
		if (!level.isClientSide) {
			Entity target = raytrace.getEntity();
			Entity shooter = getOwner();
			IBullet bullet = (IBullet) getItemRaw().getItem();
			
			if (isOnFire()) target.setSecondsOnFire(5);
			int lastHurtResistant = target.invulnerableTime;
			if (ignoreInvulnerability) target.invulnerableTime = 0;
			boolean damaged = target.hurt((new IndirectEntityDamageSource("arrow", this, shooter)).setProjectile(), (float) bullet.modifyDamage(damage, this, target, shooter, level));
			
			if (damaged && target instanceof LivingEntity) {
				LivingEntity livingTarget = (LivingEntity)target;
				if (knockbackStrength > 0) {
					double actualKnockback = knockbackStrength;
					//Knocback amplifying potion from Hanami TODO replace once it's in another mod
					//if (Holders.Hanami.INSTABILITY != null && livingTarget.isPotionActive(Holders.Hanami.INSTABILITY)) actualKnockback *= 2 + livingTarget.getActivePotionEffect(Holders.Hanami.INSTABILITY).getAmplifier();
					
					Vec3 vec = getDeltaMovement().multiply(1, 0, 1).normalize().scale(actualKnockback);
					if (vec.lengthSqr() > 0) livingTarget.push(vec.x, 0.1, vec.z);
				}

				if (shooter instanceof LivingEntity) doEnchantDamageEffects((LivingEntity)shooter, target);
				
				bullet.onLivingEntityHit(this, livingTarget, shooter, level);
			}
			else if (!damaged && ignoreInvulnerability) target.invulnerableTime = lastHurtResistant;
		}
	}

	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		//Don't disappear on blocks if we're set to noclipping
		if (!level.isClientSide && (!noPhysics || result.getType() != HitResult.Type.BLOCK)) remove(RemovalReason.KILLED);
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
		//The docs says if it's not here it's gonna be false/0 so it should be good
		ignoreInvulnerability = compound.getBoolean("ignoreinv");
		knockbackStrength = compound.getDouble("knockback");
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
	//Same as normal but ignores player velocity
	//Also I finally found why it takes floats, cause the trig functions take floats :(
	public void shootFromRotation(Entity shooter, float xRot, float yRot, float p_37255_, float speed, float spread) {
		float f = -Mth.sin(yRot * ((float) Math.PI / 180F)) * Mth.cos(xRot * ((float) Math.PI / 180F));
		float f1 = -Mth.sin((xRot + p_37255_) * ((float) Math.PI / 180F));
		float f2 = Mth.cos(yRot * ((float) Math.PI / 180F)) * Mth.cos(xRot * ((float) Math.PI / 180F));
		shoot((double) f, (double) f1, (double) f2, speed, spread);
		//Vec3 vec3 = shooter.getDeltaMovement();
		//this.setDeltaMovement(this.getDeltaMovement().add(vec3.x, shooter.isOnGround() ? 0.0D : vec3.y, vec3.z));
	}

	/**
	 * Knockback on impact, 0.6 is equivalent to Punch I.
	 */
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
		return 1;
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
