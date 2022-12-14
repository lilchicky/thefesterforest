package com.gmail.thelilchicken01.tff.item;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class RottingPie extends Item {
	
	private String[] drops = {"Crafted", "Fester Forest Loot Chests"};

	public RottingPie() {
		super(new Properties().tab(TheFesterForest.tff_tab).food(
				new FoodProperties.Builder().saturationMod(2.2f).nutrition(12)
				.effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 100, 255), 1f)
				.effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 40, 0), 1f)
				.build()));
	}
	
	@Override
	public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> lore, TooltipFlag p_41424_) {

		lore.add(new TextComponent("A delectable combination of rot and decay.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
			
		super.appendHoverText(p_41421_, p_41422_, lore, p_41424_);
	}

}
