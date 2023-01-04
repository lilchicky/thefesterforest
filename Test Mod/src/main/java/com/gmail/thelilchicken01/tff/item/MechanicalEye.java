package com.gmail.thelilchicken01.tff.item;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class MechanicalEye extends Item implements ICurioItem {
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public MechanicalEye(Properties properties) {
		super(properties);
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "attack damage", 2.0, AttributeModifier.Operation.ADDITION));

	    this.defaultModifiers = builder.build();
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
			ItemStack stack) {
		
		return this.defaultModifiers;
		
	}
	
	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
				
		if (!slotContext.entity().hasEffect(MobEffects.NIGHT_VISION)) {
			
			slotContext.entity().addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 
					15 * 20, 2, false, false));
			
		}
		
		if (slotContext.entity().hasEffect(MobEffects.NIGHT_VISION)) {
			
			if (slotContext.entity().getEffect(MobEffects.NIGHT_VISION).getDuration() < (11 * 20)) {
				
				slotContext.entity().addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 
						15 * 20, 2, false, false));
			
			}
			
		}
				
		ICurioItem.super.curioTick(slotContext, stack);
	}
	
	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
		
		if (slotContext.entity().hasEffect(MobEffects.NIGHT_VISION)) {
			
			if (slotContext.entity().getEffect(MobEffects.NIGHT_VISION).getDuration() < (16 * 20)) {
				
				slotContext.entity().removeEffect(MobEffects.NIGHT_VISION);
				
			}
			
		}
		
		ICurioItem.super.onUnequip(slotContext, newStack, stack);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("The mechanical eye of the Forgemaster.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("Shoving it into your skull grants enhanced vision").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("and accuracy.").withStyle(ChatFormatting.GRAY));
		
		super.appendHoverText(stack, world, lore, flag);
	}
	

}
