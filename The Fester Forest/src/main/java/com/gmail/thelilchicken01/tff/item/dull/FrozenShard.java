package com.gmail.thelilchicken01.tff.item.dull;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.item_util.TFFItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FrozenShard extends TFFItem {
	
	private String[] drops = {"Frostbitten King"};
	
	public FrozenShard() {
		super(new Properties().tab(TheFesterForest.TFF_TAB));
		
	}

	@Override
	public String itemType() {
		return "dull";
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
