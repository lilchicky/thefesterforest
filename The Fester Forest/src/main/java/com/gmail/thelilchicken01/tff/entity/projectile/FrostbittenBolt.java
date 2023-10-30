package com.gmail.thelilchicken01.tff.entity.projectile;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.NoParticleProjectile;
import com.gmail.thelilchicken01.tff.init.ParticleInit;
import com.gmail.thelilchicken01.tff.item.item.MagicModUtil;
import com.gmail.thelilchicken01.tff.item.item.item_types.MagicOrb;
import com.gmail.thelilchicken01.tff.item.projectile.FrostbittenBoltProjectile;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class FrostbittenBolt extends NoParticleProjectile implements ItemSupplier {
	
	private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(Fireball.class, EntityDataSerializers.ITEM_STACK);
	
	protected double damage = 20;
	protected boolean ignoreInvulnerability = true;
	protected double knockbackStrength = 0.1;
	protected int ticksSinceFired;
	protected LivingEntity target = null;
	
	private boolean canHitPlayer = true;
	private boolean isExtraShot = false;
	
	private static int staticDamage = 20;

	public FrostbittenBolt(EntityType<? extends FrostbittenBolt> p_i50160_1_, Level p_i50160_2_) {
		super(p_i50160_1_, p_i50160_2_);
	}

	public FrostbittenBolt(Level worldIn, LivingEntity shooter) {
		this(worldIn, shooter, 0, 0, 0);
		setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
	}
	
	public FrostbittenBolt(Level worldIn, LivingEntity shooter, LivingEntity target) {
		this(worldIn, shooter, 0, 0, 0);
		this.target = target;
		setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
	}

	public FrostbittenBolt(Level worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
		super(ModEntityTypes.frostbitten_bolt.get(), shooter, accelX, accelY, accelZ, worldIn);
	}

	public FrostbittenBolt(Level worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(ModEntityTypes.frostbitten_bolt.get(), x, y, z, accelX, accelY, accelZ, worldIn);
	}
	
	private static final double STOP_TRESHOLD = 0.01;
	
	public void canHitPlayer(boolean hitPlayer) {
		
		canHitPlayer = hitPlayer;
		
	}
	
	public void setExtraShot(boolean extraShot) {
		isExtraShot = extraShot;
	}
	
	@Override
	public void tick() {
		
		super.tick();
		
		if(!getLevel().isClientSide()) {
			ticksSinceFired++;
			if (ticksSinceFired > 80 || getDeltaMovement().lengthSqr() < STOP_TRESHOLD) {
				remove(RemovalReason.KILLED);
			}
		}
		
	}
	
	@Override
	protected void onHitEntity(EntityHitResult raytrace) {
		super.onHitEntity(raytrace);
		if (!level.isClientSide) {
			Entity target = raytrace.getEntity();
			Entity shooter = getOwner();
			FrostbittenBoltProjectile bullet = (FrostbittenBoltProjectile) getItemRaw().getItem();
			
			if (isOnFire()) target.setSecondsOnFire(5);
			int lastHurtResistant = target.invulnerableTime;
			if (ignoreInvulnerability) target.invulnerableTime = 0;
			
			boolean damaged;
			
			if (target instanceof Player) {
				if (canHitPlayer) {
					damaged = target.hurt(new IndirectEntityDamageSource(TheFesterForest.MODID + "_frostbitten_bolt_damage",
							this, shooter).setProjectile().bypassArmor(),
							(float) bullet.modifyDamage(damage, this, target, shooter, level));
				}
				else {
					damaged = false;
				}
			}
			else {
				damaged = target.hurt(new IndirectEntityDamageSource(TheFesterForest.MODID + "_frostbitten_bolt_damage",
						this, shooter).setProjectile().bypassArmor(),
						(float) bullet.modifyDamage(damage, this, target, shooter, level));
			}
			
			if (damaged && target instanceof LivingEntity livingTarget) {
				
				if (knockbackStrength > 0) {
					double actualKnockback = knockbackStrength;
					
					Vec3 vec = getDeltaMovement().multiply(1, 0, 1).normalize().scale(actualKnockback);
					if (vec.lengthSqr() > 0) livingTarget.push(vec.x, 0.1, vec.z);
				}
				
				if (shooter instanceof LivingEntity) {
					doEnchantDamageEffects((LivingEntity)shooter, target);
				}
				
				if (isExtraShot) {
					
					if (!getLevel().isClientSide()) {
				         double d0 = livingTarget.getX();
				         double d1 = livingTarget.getY();
				         double d2 = livingTarget.getZ();

				         for(int i = 0; i < 16; ++i) {
				            double d3 = livingTarget.getX() + (livingTarget.getRandom().nextDouble() - 0.5D) * 16.0D;
				            double d4 = Mth.clamp(livingTarget.getY() + (double)(livingTarget.getRandom().nextInt(16) - 8), (double)getLevel().getMinBuildHeight(), (double)(getLevel().getMinBuildHeight() + ((ServerLevel)getLevel()).getLogicalHeight() - 1));
				            double d5 = livingTarget.getZ() + (livingTarget.getRandom().nextDouble() - 0.5D) * 16.0D;
				            if (livingTarget.isPassenger()) {
				            	livingTarget.stopRiding();
				            }

				            net.minecraftforge.event.entity.EntityTeleportEvent.ChorusFruit event = net.minecraftforge.event.ForgeEventFactory.onChorusFruitTeleport(livingTarget, d3, d4, d5);
				            if (livingTarget.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
				               SoundEvent soundevent = livingTarget instanceof Fox ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
				               getLevel().playSound((Player)null, d0, d1, d2, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
				               livingTarget.playSound(soundevent, 1.0F, 1.0F);
				               break;
				            }
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
		
		super.onHitBlock(result);
		
		if (getLevel() instanceof ServerLevel serverlevel && !(serverlevel.getBlockState(result.getBlockPos()).getBlock() == Blocks.BEDROCK)) {
		
			serverlevel.destroyBlock(result.getBlockPos(), true);
			
		}
		
	}
	
	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		playSound(SoundEvents.GLASS_BREAK, 0.2f, 1.0f);
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
		ItemStack itemstack = this.getItemRaw();
	    if (!itemstack.isEmpty()) {
	    	compound.put("Item", itemstack.save(new CompoundTag()));
	    }
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		ticksSinceFired = compound.getInt("tsf");
		damage = compound.getDouble("damage");
		ignoreInvulnerability = compound.getBoolean("ignoreinv");
		knockbackStrength = compound.getDouble("knockback");
		ItemStack itemstack = ItemStack.of(compound.getCompound("Item"));
	    this.setItem(itemstack);
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
	
	public void setItem(ItemStack p_37011_) {
		if (!p_37011_.is(Items.FIRE_CHARGE) || p_37011_.hasTag()) {
			this.getEntityData().set(DATA_ITEM_STACK, Util.make(p_37011_.copy(), (p_37015_) -> {
				p_37015_.setCount(1);
			}));
		}

	}

	protected ItemStack getItemRaw() {
		return this.getEntityData().get(DATA_ITEM_STACK);
	}

	public ItemStack getItem() {
		ItemStack itemstack = this.getItemRaw();
		return itemstack.isEmpty() ? new ItemStack(Items.FIRE_CHARGE) : itemstack;
	}

	protected void defineSynchedData() {
		this.getEntityData().define(DATA_ITEM_STACK, ItemStack.EMPTY);
	}
	
}
