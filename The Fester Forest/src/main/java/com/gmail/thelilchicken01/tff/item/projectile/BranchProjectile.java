package com.gmail.thelilchicken01.tff.item.projectile;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.projectile.BoneCharge;
import com.gmail.thelilchicken01.tff.entity.projectile.BranchCharge;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BranchProjectile extends Item {
	
	private int damage;

	public BranchProjectile(Properties properties, int damage) {
		super(properties);
		this.damage = damage;
	}
	
	public BranchCharge createProjectile(Level world, ItemStack stack, LivingEntity shooter) {
		BranchCharge entity = new BranchCharge(world, shooter);
		entity.setItem(stack);
		entity.setDamage(damage);
		return entity;
	}
	
	public BranchCharge createProjectile(Level world, ItemStack stack, LivingEntity shooter, LivingEntity target) {
		BranchCharge entity = new BranchCharge(world, shooter, target);
		entity.setItem(stack);
		entity.setDamage(damage);
		return entity;
	}
	
	public void onLivingEntityHit(BranchCharge projectile, LivingEntity target, @Nullable Entity shooter, Level world) {
	}
	
	public double modifyDamage(double damage, BranchCharge projectile, Entity target, @Nullable Entity shooter, Level world) {
		return damage;
	}

}
