package com.gmail.thelilchicken01.tff.item;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ForgemasterHeart extends Item implements ICurioItem {
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public ForgemasterHeart(Properties properties) {
		super(properties);
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "more move speed", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "bonus attack damage", 4, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "bonus attack speed", 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
	    builder.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "minus health", -0.5, AttributeModifier.Operation.MULTIPLY_TOTAL));
	    
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
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("The still beating heart of the Forgemaster. Equipping it").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("grants you unnatural abilities, but you cannot shake off").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("the searing pain in your chest.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		
		super.appendHoverText(stack, world, lore, flag);
	}
	

}
