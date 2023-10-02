package com.gmail.thelilchicken01.tff.item.melee;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item.EffectsUtil;
import com.gmail.thelilchicken01.tff.item.item.ItemUtil;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AncientHatchet extends SwordItem {
	
	private String[] drops = {"Fester Forest Loot Chests"};

	public AncientHatchet(Tier tier, int damage, float aspeed, Properties properties) {
		super(tier, damage, aspeed, properties);
		
	}
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity player) {
		
		if (!player.hasEffect(MobEffects.DIG_SPEED)) {
			player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 39, 0, false, false));
		}
		else if (player.getEffect(MobEffects.DIG_SPEED).getAmplifier() < 20) {
			player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 39, player.getEffect(MobEffects.DIG_SPEED).getAmplifier() + 1, false, false));
		}
		else {
			player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 39, 20, false, false));
		}
		
		return super.hurtEnemy(stack, player, target);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("Melee").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("An old hatchet, great at quickly slicing at victims.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("Attacking foes causes you to become enraged,").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("steadily increasing attack speed the more you attack.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
