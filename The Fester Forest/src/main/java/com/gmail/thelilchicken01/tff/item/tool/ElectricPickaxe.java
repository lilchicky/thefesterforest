package com.gmail.thelilchicken01.tff.item.tool;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.item.ModTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ElectricPickaxe extends PickaxeItem {
	
	private String[] drops = {"Ambectrum", "Fester Forest Loot Chests"};

	public ElectricPickaxe() {
		super(ModTiers.ELECTRIC, -4, -3.0f, new Properties().tab(TheFesterForest.TFF_TAB).durability(1750));
		
	}
	
	
	
	@Override
	public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> lore, TooltipFlag p_41424_) {
		
		lore.add(new TextComponent("Tool").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Breaking underground blocks with this pickaxe fills it with charges,").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("up to 100. Each charge increases your mining speed slightly. Mining").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("a non-underground block dissipates all charges.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Blocks considered \"Underground\" are tagged with #tff:underground_mineable").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
		lore.add(new TextComponent("when looking at a block in the F3 menu. Uses Forge tags.").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
		
		super.appendHoverText(p_41421_, p_41422_, lore, p_41424_);
	}

}
