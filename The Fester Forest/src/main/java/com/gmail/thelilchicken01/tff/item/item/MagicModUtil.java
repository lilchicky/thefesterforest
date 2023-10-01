package com.gmail.thelilchicken01.tff.item.item;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.custom.PlayerCrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;

import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public enum MagicModUtil {
	
	FLAME,
	POISON,
	ICE,
	LEVITATE,
	LIFE,
	WITHER,
	REETLE,
	MECHANICAL;
	
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
				default:
					break;
		
			}
		}
		
	}
	
	private static void repairItem(Player player, int repairAmount) {
		
		@Nullable ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
		@Nullable ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
		@Nullable ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
		@Nullable ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
		
		if (helmet != null) {
			helmet.setDamageValue(helmet.getDamageValue() + repairAmount);
		}
		if (chestplate != null) {
			chestplate.setDamageValue(helmet.getDamageValue() + repairAmount);
		}
		if (leggings != null) {
			leggings.setDamageValue(helmet.getDamageValue() + repairAmount);
		}
		if (boots != null) {
			boots.setDamageValue(helmet.getDamageValue() + repairAmount);
		}
		
	}

}
