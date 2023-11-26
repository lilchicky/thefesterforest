package com.gmail.thelilchicken01.tff.item.mixed;

import java.util.List;

import com.gmail.thelilchicken01.tff.enchantment.ModEnchants;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item_util.EffectsUtil;
import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item_util.MagicItem;
import com.gmail.thelilchicken01.tff.item.item_util.MagicModUtil;
import com.gmail.thelilchicken01.tff.item.item_util.MagicOrb;
import com.gmail.thelilchicken01.tff.item.item_util.MagicWeapon;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFSwordItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ReaverBlade extends TFFSwordItem implements MagicItem, MagicWeapon {
	
	private String[] drops = {"Deep Reaver", "Fester Forest Loot Chests"};
	
	private int strength;

	public ReaverBlade(Tier tier, int damage, float aspeed, Properties properties) {
		super(tier, damage, aspeed, properties);
	}
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		
		EffectsUtil effect = EffectsUtil.getRandomEffect();
		
		if (attacker instanceof Player) {
			if (ArmorSets.BANSHEE.getArmorSet((Player) attacker) == SetCount.TWO) {
				strength = ((int) (Math.random() * 6)) + 4;
				strength = (int) (strength * ItemUtil.getArcanePowerDamageMod(stack));
			}
			else if (ArmorSets.BANSHEE.getArmorSet((Player) attacker) == SetCount.FOUR) {
				strength = ((int) (Math.random() * 6)) + 8;
				strength = (int) (strength * ItemUtil.getArcanePowerDamageMod(stack));
			}
			else {
				strength = (int) (Math.random() * 6);
				strength = (int) (strength * ItemUtil.getArcanePowerDamageMod(stack));
			}
			
			if (((Player)(attacker)).getOffhandItem().getItem() instanceof MagicOrb) {
				MagicModUtil.getMagicMod(((Player)(attacker)), target, ((MagicOrb) (((Player)(attacker)).getOffhandItem().getItem())).getOrbType());
			}
		}
		
		target.addEffect(new MobEffectInstance(effect.getEffect(), (effect.getEffectDuration() * 20) + 
				(EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.quickcast.get(), stack) * 20), strength));
		
		attacker.addEffect(new MobEffectInstance(effect.getEffect(), (effect.getEffectDuration() * 20) + 
				(EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.quickcast.get(), stack) * 20), strength));
		
		//System.out.println(effect.getEffect().getDescriptionId() + ", " + effect.getEffectDuration() + ", " + strength);
		
		return super.hurtEnemy(stack, target, attacker);
	}

	@Override
	public String itemType() {
		return "melee.magic";
	}

	@Override
	public String[] dropsFrom() {
		return drops;
	}

	@Override
	public boolean isShiftable() {
		return true;
	}

}
