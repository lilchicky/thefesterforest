package com.gmail.thelilchicken01.tff.item.item;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.capability.SwimHandler;
import com.gmail.thelilchicken01.tff.enchantment.ArcanePowerEnchant;
import com.gmail.thelilchicken01.tff.enchantment.ModEnchants;
import com.gmail.thelilchicken01.tff.enchantment.QuickcastEnchant;
import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
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
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
	
	public static IndirectEntityDamageSource entityDamageSource(String name, Entity target, Entity shooter) {
		
		return new IndirectEntityDamageSource(TheFesterForest.MODID + "_" + name, target, shooter);
		
	}
	
	public static double getArcanePowerDamageMod(ItemStack stack) {
		
		return ArcanePowerEnchant.getDamage(EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.arcanePower.get(), stack));
		
	}
	
	public static int getQuickcastCooldown(int cooldown, ItemStack stack) {
		
		return QuickcastEnchant.cdr(EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.quickcast.get(), stack), cooldown);
		
	}
	
}
