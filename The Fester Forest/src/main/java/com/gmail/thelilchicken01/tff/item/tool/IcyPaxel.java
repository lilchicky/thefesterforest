package com.gmail.thelilchicken01.tff.item.tool;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.item.ModTiers;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class IcyPaxel extends PaxelItem {
	
	private String[] drops = {"Glacial Titan"};

	public IcyPaxel() {
		super(ModTiers.PAXEL_DIAMOND, 0, 0.0f, new Properties().tab(TheFesterForest.TFF_TAB).durability(1650));
		
	}
	
	@Override
	public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> lore, TooltipFlag p_41424_) {
		
		lore.add(new TextComponent("Tool").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("An old multitool, long since encased in ice.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("Able to break any block with diamond efficiency.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
		
		super.appendHoverText(p_41421_, p_41422_, lore, p_41424_);
	}

}
