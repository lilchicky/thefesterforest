package com.gmail.thelilchicken01.tff.item.mixed;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.item_util.TFFBowItem;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShadowBow extends TFFBowItem {
	
	private String[] drops = {"Banshee", "Fester Forest Loot Chests"};
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public ShadowBow() {
		super(new Properties().durability(966).tab(TheFesterForest.TFF_TAB));
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, 
	    		"attack_damage", 8.0, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, 
	    		"attack_speed", -2.4f, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), 
	    		"movement_speed", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
	    
	    this.defaultModifiers = builder.build();
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		
		return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		
		return enchantment.category.canEnchant(ItemInit.ANCIENT_GREATSWORD.get()) ||
				super.canApplyAtEnchantingTable(stack, enchantment);
	}

	@Override
	public String itemType() {
		return "melee.ranged";
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
