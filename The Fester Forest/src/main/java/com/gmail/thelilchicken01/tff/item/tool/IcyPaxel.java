package com.gmail.thelilchicken01.tff.item.tool;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.item_util.ModTiers;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFPaxelItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class IcyPaxel extends TFFPaxelItem {
	
	private String[] drops = {"Glacial Titan", "Fester Forest Loot Chests"};

	public IcyPaxel() {
		super(ModTiers.PAXEL_DIAMOND, 0, 0.0f, new Properties().tab(TheFesterForest.TFF_TAB).durability(1650));
		
	}

	@Override
	public String itemType() {
		return "tool";
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
