package com.gmail.thelilchicken01.tff.item.projectile;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.projectile.BoneCharge;
import com.gmail.thelilchicken01.tff.entity.projectile.RottingBolt;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RottingBoltShot extends Item {
	
	private int damage;

	public RottingBoltShot(Properties properties, int damage) {
		super(properties);
		this.damage = damage;
	}
	
	public RottingBolt createProjectile(Level world, ItemStack stack, LivingEntity shooter) {
		RottingBolt entity = new RottingBolt(world, shooter);
		entity.setItem(stack);
		entity.setDamage(damage);
		return entity;
	}
	
	public RottingBolt createProjectile(Level world, ItemStack stack, LivingEntity shooter, LivingEntity target) {
		RottingBolt entity = new RottingBolt(world, shooter, target);
		entity.setItem(stack);
		entity.setDamage(damage);
		return entity;
	}
	
	public void onLivingEntityHit(RottingBolt projectile, LivingEntity target, @Nullable Entity shooter, Level world) {
	}
	
	public double modifyDamage(double damage, RottingBolt projectile, Entity target, @Nullable Entity shooter, Level world) {
		return damage;
	}

}
