package com.gmail.thelilchicken01.tff.entity.projectile;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.init.ParticleInit;
import com.gmail.thelilchicken01.tff.item.projectile.BranchProjectile;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class BranchCharge extends Fireball {
	
	protected double damage = 6;
	protected boolean ignoreInvulnerability = false;
	protected double knockbackStrength = 0.1;
	protected int ticksSinceFired;
	protected LivingEntity target = null;
	protected int poisonDuration = 5;
	
	private boolean canHitPlayer = true;
	
	private static int staticDamage = 10;

	public BranchCharge(EntityType<? extends BranchCharge> p_i50160_1_, Level p_i50160_2_) {
		super(p_i50160_1_, p_i50160_2_);
	}

	public BranchCharge(Level worldIn, LivingEntity shooter) {
		this(worldIn, shooter, 0, 0, 0);
		setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
	}
	
	public BranchCharge(Level worldIn, LivingEntity shooter, LivingEntity target) {
		this(worldIn, shooter, 0, 0, 0);
		this.target = target;
		setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
	}

	public BranchCharge(Level worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
		super(ModEntityTypes.branch_charge.get(), shooter, accelX, accelY, accelZ, worldIn);
	}

	public BranchCharge(Level worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(ModEntityTypes.branch_charge.get(), x, y, z, accelX, accelY, accelZ, worldIn);
	}
	
	private static final double STOP_TRESHOLD = 0.01;
	
	@Override
	public void tick() {
		
		super.tick();
		
		if(!getLevel().isClientSide()) {
			ticksSinceFired++;
			if (ticksSinceFired > 80 || getDeltaMovement().lengthSqr() < STOP_TRESHOLD) {
				remove(RemovalReason.KILLED);
			}
			
			if (target != null) {
				
				if (!target.isDeadOrDying()) {
				
					Vec3 currentPos = this.getPosition(1.0f);
					Vec3 targetPos = target.getPosition(1.0f).add(0.0, target.getEyeHeight() * 0.5, 0.0);
					Vec3 newVector = targetPos.subtract(currentPos).normalize();
				
					setDeltaMovement(newVector);
			    }
				
			}
			
		}
		
	}
	
	public void canHitPlayer(boolean hitPlayer) {
		
		canHitPlayer = hitPlayer;
		
	}
	
	@Override
	protected ParticleOptions getTrailParticle() {
		return ParticleInit.BRANCH_PARTICLE.get();
	}
	
	@Override
	protected void onHitEntity(EntityHitResult raytrace) {
		super.onHitEntity(raytrace);
		if (!level.isClientSide) {
			Entity target = raytrace.getEntity();
			Entity shooter = getOwner();
			BranchProjectile bullet = (BranchProjectile) getItemRaw().getItem();
			
			if (isOnFire()) target.setSecondsOnFire(5);
			int lastHurtResistant = target.invulnerableTime;
			if (ignoreInvulnerability) target.invulnerableTime = 0;
			
			boolean damaged;
			
			if (target instanceof Player) {
				if (canHitPlayer) {
					damaged = target.hurt(new IndirectEntityDamageSource(TheFesterForest.MODID + "_branch_damage",
							this, shooter).setProjectile(),
							(float) bullet.modifyDamage(damage, this, target, shooter, level));
				}
				else {
					damaged = false;
				}
			}
			else {
				damaged = target.hurt(new IndirectEntityDamageSource(TheFesterForest.MODID + "_branch_damage",
						this, shooter).setProjectile(),
						(float) bullet.modifyDamage(damage, this, target, shooter, level));
			}
			
			if (damaged && target instanceof LivingEntity) {
				LivingEntity livingTarget = (LivingEntity)target;
				
				if (!livingTarget.hasEffect(MobEffects.POISON)) {
					livingTarget.addEffect(new MobEffectInstance(MobEffects.POISON, poisonDuration * 20, 2));
				}
				
				if (knockbackStrength > 0) {
					double actualKnockback = knockbackStrength;
					
					Vec3 vec = getDeltaMovement().multiply(1, 0, 1).normalize().scale(actualKnockback);
					if (vec.lengthSqr() > 0) livingTarget.push(vec.x, 0.1, vec.z);
				}
				
				bullet.onLivingEntityHit(this, livingTarget, shooter, level);
			}
			else if (!damaged && ignoreInvulnerability) target.invulnerableTime = lastHurtResistant;
		}
	}
	
	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		if (!level.isClientSide && (!noPhysics || result.getType() != HitResult.Type.BLOCK)) remove(RemovalReason.KILLED);
	}
	
	public void setDamage(double damage) {
		this.damage = damage;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public static int getStaticDamage() {
		return staticDamage;
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
		return 1;
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
}
