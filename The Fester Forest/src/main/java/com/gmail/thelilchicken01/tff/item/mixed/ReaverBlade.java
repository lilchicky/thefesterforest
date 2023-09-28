package com.gmail.thelilchicken01.tff.item.mixed;

import java.util.List;

import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item.EffectsUtil;
import com.gmail.thelilchicken01.tff.item.item.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item.MagicItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ReaverBlade extends SwordItem implements MagicItem {
	
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
		}
		
		target.addEffect(new MobEffectInstance(effect.getEffect(), effect.getEffectDuration() * 20, strength));
		
		attacker.addEffect(new MobEffectInstance(effect.getEffect(), effect.getEffectDuration() * 20, strength));
		
		//System.out.println(effect.getEffect().getDescriptionId() + ", " + effect.getEffectDuration() + ", " + strength);
		
		return super.hurtEnemy(stack, target, attacker);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("Magic, Melee").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("The massive tooth of a Deep Reaver. Hitting a target").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("applies a random effect to both you and the target.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("The effect is the same for both of you.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
