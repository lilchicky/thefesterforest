package com.gmail.thelilchicken01.tff.item.melee;

import java.util.List;

import com.gmail.thelilchicken01.tff.effect.ModEffects;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShockSword extends SwordItem {
	
	private String[] drops = {"Ambectrum", "Fester Forest Loot Chests"};

	public ShockSword(Tier tier, int damage, float aspeed, Properties properties) {
		super(tier, damage, aspeed, properties);
	}
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity entityHurt, LivingEntity entityAttacker) {
		
		if (Math.random() < 0.3333333) {
			entityHurt.addEffect(new MobEffectInstance(ModEffects.PARALYSIS.get(), 40, 0));
		}
		
		return super.hurtEnemy(stack, entityHurt, entityAttacker);
		
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("Melee").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("An electric beam stabilised by two prongs.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("Has a chance to paralyze targets.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
