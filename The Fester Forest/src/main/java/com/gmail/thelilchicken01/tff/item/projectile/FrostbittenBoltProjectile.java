package com.gmail.thelilchicken01.tff.item.projectile;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.projectile.FrostbittenBolt;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FrostbittenBoltProjectile extends Item {
	
	private int damage;

	public FrostbittenBoltProjectile(Properties properties, int damage) {
		super(properties);
		this.damage = damage;
	}
	
	public FrostbittenBolt createProjectile(Level world, ItemStack stack, LivingEntity shooter) {
		FrostbittenBolt entity = new FrostbittenBolt(world, shooter);
		entity.setItem(stack);
		entity.setDamage(damage);
		return entity;
	}
	
	public FrostbittenBolt createProjectile(Level world, ItemStack stack, LivingEntity shooter, LivingEntity target) {
		FrostbittenBolt entity = new FrostbittenBolt(world, shooter, target);
		entity.setItem(stack);
		entity.setDamage(damage);
		return entity;
	}
	
	public void onLivingEntityHit(FrostbittenBolt projectile, LivingEntity target, @Nullable Entity shooter, Level world) {
	}
	
	public double modifyDamage(double damage, FrostbittenBolt projectile, Entity target, @Nullable Entity shooter, Level world) {
		return damage;
	}

}
