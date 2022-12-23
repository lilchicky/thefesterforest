package com.gmail.thelilchicken01.tff.item.projectile;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.projectile.BoneCharge;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BoneShot extends Item {
	
	private int damage;

	public BoneShot(Properties properties, int damage) {
		super(properties);
		this.damage = damage;
	}
	
	public BoneCharge createProjectile(Level world, ItemStack stack, LivingEntity shooter) {
		BoneCharge entity = new BoneCharge(world, shooter);
		entity.setItem(stack);
		entity.setDamage(damage);
		return entity;
	}
	
	public void onLivingEntityHit(BoneCharge projectile, LivingEntity target, @Nullable Entity shooter, Level world) {
	}
	
	public double modifyDamage(double damage, BoneCharge projectile, Entity target, @Nullable Entity shooter, Level world) {
		return damage;
	}

}
