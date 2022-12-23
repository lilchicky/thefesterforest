package com.gmail.thelilchicken01.tff.item.projectile;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.projectile.BranchCharge;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IBullet {
	/**
	 * Creates a projectile and set its stats and stuff. The gun will give it velocity and spawn it in the world.
	 */
	BranchCharge createProjectile(Level world, ItemStack stack, LivingEntity shooter);
	
	/**
	 * Called on server only when a default projectile (or one that extends it) sucessfully damages a LivingEntity (so after damage).
	 * <br/>May change that later.
	 */
	default void onLivingEntityHit(BranchCharge projectile, LivingEntity target, @Nullable Entity shooter, Level world) {}
	
	/**
	 * Called on server only as damage is being applied when a bullet carrying this item hits. The target may not be a LivingEntity.
	 * <br/>May change that later.
	 */
	default double modifyDamage(double damage, BranchCharge projectile, Entity target, @Nullable Entity shooter, Level world) {
		return damage;
	}

}
