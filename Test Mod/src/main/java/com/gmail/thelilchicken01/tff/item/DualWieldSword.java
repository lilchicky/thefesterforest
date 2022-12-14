package com.gmail.thelilchicken01.tff.item;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class DualWieldSword extends Item {

	public DualWieldSword() {
		super(new Properties().stacksTo(1).tab(TheFesterForest.tff_tab));
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		
		Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
		
		if(slot == EquipmentSlot.OFFHAND) {
			multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "Weapon Damage", 6.0, Operation.ADDITION));
		}
		
		return multimap;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("A short sword, extremely effective when used").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("alongside another item.").withStyle(ChatFormatting.GRAY));
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
