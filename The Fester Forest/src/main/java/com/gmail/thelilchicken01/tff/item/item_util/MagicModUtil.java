package com.gmail.thelilchicken01.tff.item.item_util;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.enchantment.ModEnchants;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.custom.PlayerCrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.entity.projectile.FrostBolt;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.projectile.FrostBoltProjectile;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;

public enum MagicModUtil {
	
	FLAME,
	POISON,
	ICE,
	LEVITATE,
	LIFE,
	WITHER,
	REETLE,
	MECHANICAL,
	FROSTBITTEN,
	ROTFISH;
	
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
						target.setTicksFrozen(140 + 100);
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
				case REETLE:
					if (!shooter.getCooldowns().isOnCooldown(shooter.getOffhandItem().getItem())) {
						if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.TWO) {
							for (int x = 0; x < 2; x++) {
								
								PlayerCrunchBeetleEntity beetle = new PlayerCrunchBeetleEntity(ModEntityTypes.PLAYER_CRUNCH_BEETLE.get(), shooter.getLevel());
								
								beetle.setOwnerUUID(shooter.getUUID());
								beetle.setPos(shooter.getX(), shooter.getY(), shooter.getZ());
								beetle.tame(shooter);
								
								if (!shooter.getLevel().isClientSide()) {
									shooter.getLevel().addFreshEntity(beetle);
								}
							}
						}
						if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.FOUR) {
							for (int x = 0; x < 3; x++) {
								
								PlayerCrunchBeetleEntity beetle = new PlayerCrunchBeetleEntity(ModEntityTypes.PLAYER_CRUNCH_BEETLE.get(), shooter.getLevel());
								
								beetle.setOwnerUUID(shooter.getUUID());
								beetle.setPos(shooter.getX(), shooter.getY(), shooter.getZ());
								beetle.tame(shooter);
								
								if (!shooter.getLevel().isClientSide()) {
									shooter.getLevel().addFreshEntity(beetle);
								}
							}
						}
						else {
							PlayerCrunchBeetleEntity beetle = new PlayerCrunchBeetleEntity(ModEntityTypes.PLAYER_CRUNCH_BEETLE.get(), shooter.getLevel());
							
							beetle.setOwnerUUID(shooter.getUUID());
							beetle.setPos(shooter.getX(), shooter.getY(), shooter.getZ());
							beetle.tame(shooter);
							
							if (!shooter.getLevel().isClientSide()) {
								shooter.getLevel().addFreshEntity(beetle);
							}
						}
						shooter.awardStat(Stats.ITEM_USED.get(shooter.getOffhandItem().getItem()));
						shooter.getCooldowns().addCooldown(shooter.getOffhandItem().getItem(), 
								ItemUtil.getQuickcastCooldown(3 * 20, shooter.getOffhandItem()));
					}
					break;
				case MECHANICAL:
					if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.TWO) {
						shooter.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));
						shooter.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1));
						repairItem(shooter, 2);
					}
					if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.FOUR) {
						shooter.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 120, 2));
						shooter.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 120, 2));
						repairItem(shooter, 3);
					}
					else {
						shooter.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 80, 0));
						shooter.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 0));
						repairItem(shooter, 1);
					}
					break;
				case FROSTBITTEN:
					
					for (int x = 0; x < 360; x++) {
						
						if (x % 72 == 0) {
							
							FrostBoltProjectile bulletItem = ItemInit.FROST_BOLT.get();
							ItemStack shotAmmo = new ItemStack(ItemInit.FROST_BOLT.get());
							
							FrostBolt shot = bulletItem.createProjectile(shooter.getLevel(), shotAmmo, shooter);
							
							shot.setPos(hitEntity.getX(), hitEntity.getY() + (hitEntity.getBbHeight() * 0.5), hitEntity.getZ());
						
							shot.shootFromRotation(hitEntity, 0.0f, x, 0.0f, 1.2f, 0.0f);
							
							if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.TWO) {
								shot.setDamage(10);
							}
							if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.FOUR) {
								shot.setDamage(15);
							}
							else {
								shot.setDamage(5);
							}
							
							shot.setIgnoreInvulnerability(false);
							shot.canHitPlayer(false);
						
							shooter.getLevel().addFreshEntity(shot);
							
						}
						
					}
					
					break;
				case ROTFISH:
					
					EffectsUtil effect = EffectsUtil.getRandomEffect();
					
					int strength;
					
					if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.TWO) {
						strength = ((int) (Math.random() * 3)) + 2;
					}
					else if (ArmorSets.BANSHEE.getArmorSet(shooter) == SetCount.FOUR) {
						strength = ((int) (Math.random() * 3)) + 4;
					}
					else {
						strength = (int) (Math.random() * 3);
					}
					
					shooter.addEffect(new MobEffectInstance(effect.getEffect(), (effect.getEffectDuration() * 20), strength));
					
					if (hitEntity instanceof LivingEntity) {
						((LivingEntity) hitEntity).addEffect(new MobEffectInstance(effect.getEffect(), (effect.getEffectDuration() * 20), strength));
					}
					
					break;
				default:
					break;
		
			}
		}
		
	}
	
	private static void repairItem(Player player, int repairAmount) {
		
		player.getArmorSlots().forEach(item -> {
			
			item.setDamageValue(item.getDamageValue() - repairAmount);
			
		});
		
	}

}
