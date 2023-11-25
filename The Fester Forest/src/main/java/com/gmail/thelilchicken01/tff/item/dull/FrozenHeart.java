package com.gmail.thelilchicken01.tff.item.dull;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item_util.TFFItem;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class FrozenHeart extends TFFItem implements ICurioItem {
	
	private String[] drops = {"Frostbitten King", "Crafted"};
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public FrozenHeart(Properties properties) {
		super(properties);
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "attack_damage", -6, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "movement_speed", -0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
	    builder.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "extra_health", 1.0f, AttributeModifier.Operation.MULTIPLY_TOTAL));
	    builder.put(Attributes.ARMOR, new AttributeModifier(UUID.randomUUID(), "extra_armor", 0.5f, AttributeModifier.Operation.MULTIPLY_TOTAL));
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "extra_armor_toughness", 0.5f, AttributeModifier.Operation.MULTIPLY_TOTAL));
	    
	    this.defaultModifiers = builder.build();
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
			ItemStack stack) {
		
		return this.defaultModifiers;
		
	}
	
	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		
		ICurioItem.super.curioTick(slotContext, stack);
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
