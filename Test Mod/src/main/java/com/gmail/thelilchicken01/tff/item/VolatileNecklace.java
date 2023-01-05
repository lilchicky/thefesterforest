package com.gmail.thelilchicken01.tff.item;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class VolatileNecklace extends Item implements ICurioItem {
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public VolatileNecklace(Properties properties) {
		super(properties);
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "reduced_health", -4.0, AttributeModifier.Operation.ADDITION));

	    this.defaultModifiers = builder.build();
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
			ItemStack stack) {
		
		return this.defaultModifiers;
		
	}
	
	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		
		if (slotContext.entity().isOnFire()) {
			if (!slotContext.entity().hasEffect(MobEffects.DAMAGE_BOOST)) {
				slotContext.entity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 0, false, false));
			}
			else {
				if (slotContext.entity().getEffect(MobEffects.DAMAGE_BOOST).getDuration() < 40) {
					slotContext.entity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 0, false, false));
				}
			}
		}
		
		ICurioItem.super.curioTick(slotContext, stack);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("A scorching necklace fitted with a fiery charm.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("While on fire, gain strength.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("A scorching necklace fitted with a fiery charm.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
			lore.add(new TextComponent(""));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}
	

}
