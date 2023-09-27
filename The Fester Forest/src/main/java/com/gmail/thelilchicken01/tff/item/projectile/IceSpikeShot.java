package com.gmail.thelilchicken01.tff.item.projectile;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.projectile.BoneCharge;
import com.gmail.thelilchicken01.tff.entity.projectile.IceSpike;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IceSpikeShot extends Item {
	
	private int damage;

	public IceSpikeShot(Properties properties, int damage) {
		super(properties);
		this.damage = damage;
	}
	
	public IceSpike createProjectile(Level world, ItemStack stack, LivingEntity shooter) {
		IceSpike entity = new IceSpike(world, shooter);
		entity.setItem(stack);
		entity.setDamage(damage);
		return entity;
	}
	
	public void onLivingEntityHit(IceSpike projectile, LivingEntity target, @Nullable Entity shooter, Level world) {
	}
	
	public double modifyDamage(double damage, IceSpike projectile, Entity target, @Nullable Entity shooter, Level world) {
		return damage;
	}

}
