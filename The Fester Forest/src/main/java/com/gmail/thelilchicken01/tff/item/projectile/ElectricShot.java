package com.gmail.thelilchicken01.tff.item.projectile;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.projectile.ElectricCharge;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ElectricShot extends Item {
	
	private int damage;

	public ElectricShot(Properties properties, int damage) {
		super(properties);
		this.damage = damage;
	}
	
	public ElectricCharge createProjectile(Level world, ItemStack stack, LivingEntity shooter) {
		ElectricCharge entity = new ElectricCharge(world, shooter);
		entity.setItem(stack);
		entity.setDamage(damage);
		return entity;
	}
	
	public void onLivingEntityHit(ElectricCharge projectile, LivingEntity target, @Nullable Entity shooter, Level world) {
	}
	
	public double modifyDamage(double damage, ElectricCharge projectile, Entity target, @Nullable Entity shooter, Level world) {
		return damage;
	}

}
