package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.item_util.MagicModUtil;
import com.gmail.thelilchicken01.tff.item.item_util.MagicOrb;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LevitateOrb extends Item implements MagicOrb {
	
	private String[] drops = {"Crafted"};

	public LevitateOrb() {
		super(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB));
		
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("Magic").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("A glassy orb imbued with the weightlessness of").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("the End.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Hold in offhand to apply onhit effects to Fester").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
		lore.add(new TextComponent("Forest magic weapons.").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
		
		super.appendHoverText(stack, world, lore, flag);
	}

	@Override
	public MagicModUtil getOrbType() {
		return MagicModUtil.LEVITATE;
	}

}
