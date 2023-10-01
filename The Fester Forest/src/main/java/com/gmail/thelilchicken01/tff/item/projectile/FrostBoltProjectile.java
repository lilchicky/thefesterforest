package com.gmail.thelilchicken01.tff.item.projectile;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.projectile.BoneCharge;
import com.gmail.thelilchicken01.tff.entity.projectile.FrostBolt;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FrostBoltProjectile extends Item {
	
	private int damage;

	public FrostBoltProjectile(Properties properties, int damage) {
		super(properties);
		this.damage = damage;
	}
	
	public FrostBolt createProjectile(Level world, ItemStack stack, LivingEntity shooter) {
		FrostBolt entity = new FrostBolt(world, shooter);
		entity.setItem(stack);
		entity.setDamage(damage);
		return entity;
	}
	
	public FrostBolt createProjectile(Level world, ItemStack stack, LivingEntity shooter, LivingEntity target) {
		FrostBolt entity = new FrostBolt(world, shooter, target);
		entity.setItem(stack);
		entity.setDamage(damage);
		return entity;
	}
	
	public void onLivingEntityHit(FrostBolt projectile, LivingEntity target, @Nullable Entity shooter, Level world) {
	}
	
	public double modifyDamage(double damage, FrostBolt projectile, Entity target, @Nullable Entity shooter, Level world) {
		return damage;
	}

}
