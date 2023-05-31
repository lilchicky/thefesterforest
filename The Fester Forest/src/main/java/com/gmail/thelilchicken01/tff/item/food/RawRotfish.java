package com.gmail.thelilchicken01.tff.item.food;

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

public class RawRotfish extends Item {
	
	private String[] drops = {"Rotfish"};

	public RawRotfish() {
		super(new Properties().tab(TheFesterForest.TFF_TAB).food(
				new FoodProperties.Builder().saturationMod(0.2f).nutrition(4).effect(() -> new MobEffectInstance(MobEffects.POISON, 400, 0), 1f)
				.build()));
	}
	
	@Override
	public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> lore, TooltipFlag p_41424_) {
		
		lore.add(new TextComponent("Food").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("A disgusting fish carcass, barely holding together.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
			
		super.appendHoverText(p_41421_, p_41422_, lore, p_41424_);
	}

}
