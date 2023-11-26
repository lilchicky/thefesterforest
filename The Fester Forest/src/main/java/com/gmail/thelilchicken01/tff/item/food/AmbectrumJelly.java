package com.gmail.thelilchicken01.tff.item.food;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFItem;

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

public class AmbectrumJelly extends TFFItem {
	
	private String[] drops = {"Ambectrum", "Fester Forest Loot Chests"};

	public AmbectrumJelly() {
		super(new Properties().tab(TheFesterForest.TFF_TAB).food(
				new FoodProperties.Builder().saturationMod(0.3f).nutrition(6)
				.build()));
	}

	@Override
	public String itemType() {
		return "food";
	}

	@Override
	public String[] dropsFrom() {
		return drops;
	}

	@Override
	public boolean isShiftable() {
		return false;
	}

}
