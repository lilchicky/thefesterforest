package com.gmail.thelilchicken01.tff.item.tool;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.item_util.ModTiers;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFPickaxeItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class BonePickaxe extends TFFPickaxeItem {
	
	private String[] drops = {"Fester Forest Loot Chests"};

	public BonePickaxe() {
		super(ModTiers.BONE, -2, -3.0f, new Properties().tab(TheFesterForest.TFF_TAB).durability(2384));
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
		return false;
	}

}
