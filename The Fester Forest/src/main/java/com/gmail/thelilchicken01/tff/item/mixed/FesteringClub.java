package com.gmail.thelilchicken01.tff.item.mixed;

import java.util.List;

import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item.EffectsUtil;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FesteringClub extends SwordItem {
	
	private String[] drops = {"Fester Forest Loot Chests"};
	
	private int strength;

	public FesteringClub(Tier tier, int damage, float aspeed, Properties properties) {
		super(tier, damage, aspeed, properties);
	}
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		
		if (attacker instanceof Player) {
			if (ArmorSets.BANSHEE.getArmorSet((Player) attacker) == SetCount.TWO) {
				strength = 3;
			}
			else if (ArmorSets.BANSHEE.getArmorSet((Player) attacker) == SetCount.FOUR) {
				strength = 7;
			}
			else {
				strength = 1;
			}
		}
		
		target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, strength));
		
		if (!(target.getMobType() == MobType.UNDEAD)) {
			target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, strength));
		}
		
		return super.hurtEnemy(stack, target, attacker);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("Magic, Melee").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("An ancient wooden club, full of rot and decay.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("Applies wither and poison to anything hit for").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("5 seconds. Only applies poison to non Undead mobs.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
