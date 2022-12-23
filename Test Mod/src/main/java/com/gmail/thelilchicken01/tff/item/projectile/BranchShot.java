package com.gmail.thelilchicken01.tff.item.projectile;

import java.util.List;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.projectile.BranchCharge;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BranchShot extends Item implements IBullet {
	private int damage;

	public BranchShot(Properties properties, int damage) {
		super(properties);
		this.damage = damage;
	}

	@Override
	public BranchCharge createProjectile(Level world, ItemStack stack, LivingEntity shooter) {
		BranchCharge entity = new BranchCharge(world, shooter);
		entity.setItem(stack);
		entity.setDamage(damage);
		return entity;
	}

}
