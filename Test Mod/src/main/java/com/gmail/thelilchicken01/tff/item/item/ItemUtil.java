package com.gmail.thelilchicken01.tff.item.item;

import java.util.List;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

public class ItemUtil {
	
	public static void registerPotionEffect(MobEffect effect, int power, Player player, int duration) {
		
		if (!player.hasEffect(effect)) {
			player.addEffect(new MobEffectInstance(effect, duration * 20, power, false, false));
		}
		else {
			if (player.getEffect(effect).getDuration() < (duration - 1) * 20) {
				player.addEffect(new MobEffectInstance(effect, duration * 20, power, false, false));
			}
		}
		
	}
	
	public static List<LivingEntity> getLivingInArea(Player player, int rangeHor, int rangeVert) {
		
		List<LivingEntity> nearbyEntities = player.getLevel().getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, player, new AABB(
				player.getX() - rangeHor, 
				player.getY() - rangeVert, 
				player.getZ() - rangeHor, 
				player.getX() + rangeHor, 
				player.getY() + rangeVert, 
				player.getZ() + rangeHor));
		
		return nearbyEntities;
		
	}
	
	public static List<Monster> getMonstersInArea(Player player, int rangeHor, int rangeVert) {
		
		List<Monster> nearbyEntities = player.getLevel().getNearbyEntities(Monster.class, TargetingConditions.DEFAULT, player, new AABB(
				player.getX() - rangeHor, 
				player.getY() - rangeVert, 
				player.getZ() - rangeHor, 
				player.getX() + rangeHor, 
				player.getY() + rangeVert, 
				player.getZ() + rangeHor));
		
		return nearbyEntities;
		
	}
	
}
