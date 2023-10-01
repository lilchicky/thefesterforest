package com.gmail.thelilchicken01.tff.item.item;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item.item_types.MagicOrb;

import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;

public enum MagicModUtil {
	
	FLAME,
	POISON,
	ICE,
	LEVITATE,
	LIFE,
	WITHER;
	
	public static void getMagicMod(Player shooter, Entity hitEntity, @Nullable MagicModUtil mod) {
		
		if (hitEntity instanceof LivingEntity && mod != null) {
			
			LivingEntity target = (LivingEntity) hitEntity;
		
			switch (mod) {
		
				case FLAME:
					if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.TWO) {
						target.setRemainingFireTicks(80);
					}
					if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.FOUR) {
						target.setRemainingFireTicks(120);
					}
					else {
						target.setRemainingFireTicks(60);
					}
					break;
				case POISON:
					if (!(target.getMobType() == MobType.UNDEAD)) {
						if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.TWO) {
							target.addEffect(new MobEffectInstance(MobEffects.POISON, 150, 2));
						}
						if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.FOUR) {
							target.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 3));
						}
						else {
							target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
						}
					}
					break;
				case ICE:
					if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.TWO) {
						target.setTicksFrozen(140 + 80);
					}
					if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.FOUR) {
						target.setTicksFrozen(140 + 1000);
					}
					else {
						target.setTicksFrozen(140 + 60);
					}
					break;
				case LEVITATE:
					if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.TWO) {
						target.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 1));
					}
					if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.FOUR) {
						target.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 80, 2));
					}
					else {
						target.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40, 0));
					}
					break;
				case LIFE:
					if (!shooter.getCooldowns().isOnCooldown(shooter.getOffhandItem().getItem())) {
						if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.TWO) {
							shooter.heal(3.0f);
							shooter.awardStat(Stats.ITEM_USED.get(shooter.getOffhandItem().getItem()));
							shooter.getCooldowns().addCooldown(shooter.getOffhandItem().getItem(), 
									ItemUtil.getQuickcastCooldown(5 * 20, shooter.getOffhandItem()));
						}
						if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.FOUR) {
							shooter.heal(4.0f);
							shooter.awardStat(Stats.ITEM_USED.get(shooter.getOffhandItem().getItem()));
							shooter.getCooldowns().addCooldown(shooter.getOffhandItem().getItem(), 
									ItemUtil.getQuickcastCooldown(5 * 20, shooter.getOffhandItem()));
						}
						else {
							shooter.heal(2.0f);
							shooter.awardStat(Stats.ITEM_USED.get(shooter.getOffhandItem().getItem()));
							shooter.getCooldowns().addCooldown(shooter.getOffhandItem().getItem(), 
									ItemUtil.getQuickcastCooldown(5 * 20, shooter.getOffhandItem()));
						}
					}
					break;
				case WITHER:
					if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.TWO) {
						target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 2));
					}
					if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.FOUR) {
						target.addEffect(new MobEffectInstance(MobEffects.WITHER, 120, 4));
					}
					else {
						target.addEffect(new MobEffectInstance(MobEffects.WITHER, 80, 1));
					}
					break;
				default:
					break;
		
			}
		}
		
	}

}
