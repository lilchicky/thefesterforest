package com.gmail.thelilchicken01.tff.item.item;

import java.util.List;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

public class ItemUtil {
	
	public static void registerPotionEffect(MobEffect effect, int power, Player player) {
		
		if (!player.hasEffect(effect)) {
			player.addEffect(new MobEffectInstance(effect, 60, power, false, false));
		}
		else {
			if (player.getEffect(effect).getDuration() < 40) {
				player.addEffect(new MobEffectInstance(effect, 60, power, false, false));
			}
		}
		
	}
	
	public static List<Entity> getEntitiesInArea(Player player, int rangeHor, int rangeVert) {
		
		List<Entity> nearbyEntities = player.getLevel().getEntities(player, new AABB(
				player.getX() - rangeHor, 
				player.getY() - rangeVert, 
				player.getZ() - rangeHor, 
				player.getX() + rangeHor, 
				player.getY() + rangeVert, 
				player.getZ() + rangeHor));
		
		return nearbyEntities;
		
	}
	
}
