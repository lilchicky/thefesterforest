package com.gmail.thelilchicken01.tff.item;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.tiers.ModArmorMaterial;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

public class MechanicalLeggings extends ArmorItem {
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public MechanicalLeggings() {
		super(ModArmorMaterial.MECHANICAL, EquipmentSlot.LEGS, 
				new Properties().tab(TheFesterForest.tff_tab));
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		
	    builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), 
	    		"move_speed", 0.35, AttributeModifier.Operation.MULTIPLY_BASE));
	    
	    builder.put(Attributes.ARMOR, new AttributeModifier(UUID.randomUUID(), 
	    		"armor", ModArmorMaterial.MECHANICAL.getDefenseForSlot(EquipmentSlot.LEGS), 
	    		AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), 
	    		"armor_toughness", ModArmorMaterial.MECHANICAL.getToughness(), 
	    		AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), 
	    		"knockback_resistance", ModArmorMaterial.MECHANICAL.getKnockbackResistance(), 
	    		AttributeModifier.Operation.ADDITION));

	    this.defaultModifiers = builder.build();
		
	}
	
	@Override
	public void onArmorTick(ItemStack stack, Level level, Player player) {

		if (getSetCount(player) == 2 || getSetCount(player) == 3) {
			if (!player.hasEffect(MobEffects.DIG_SPEED)) {
				player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 0, false, false));
			}
		}
		if (getSetCount(player) == 4) {
			if (!player.hasEffect(MobEffects.DIG_SPEED)) {
				player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 1, false, false));
			}
			if (!player.hasEffect(MobEffects.REGENERATION)) {
				player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 0, false, false));
			}
		}
		
		super.onArmorTick(stack, level, player);
	}
	
	private int getSetCount(Player player) {
		
		int wornPieces = 0;
		
		for (int x = 0; x <= 3; x++) {
			
			switch(x) {
			
			case 0:
				if (player.getItemBySlot(EquipmentSlot.HEAD).toString()
						.equals(new ItemStack(ItemInit.mechanical_helmet.get()).toString())) {
					wornPieces++;
				}
				break;
			case 1:
				if (player.getItemBySlot(EquipmentSlot.CHEST).toString()
						.equals(new ItemStack(ItemInit.mechanical_chestplate.get()).toString())) {
					wornPieces++;
				}
				break;
			case 2:
				if (player.getItemBySlot(EquipmentSlot.LEGS).toString()
						.equals(new ItemStack(ItemInit.mechanical_leggings.get()).toString())) {
					wornPieces++;
				}
				break;
			case 3:
				if (player.getItemBySlot(EquipmentSlot.FEET).toString()
						.equals(new ItemStack(ItemInit.mechanical_boots.get()).toString())) {
					wornPieces++;
				}
				break;
			default:
				break;
			
			}
			
		}
		
		return wornPieces;
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return slot == EquipmentSlot.LEGS ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("The Forgemaster's leggings.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("Equipping grants a large boost to move speed.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Set Bonus:").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("2+ Pieces: Haste 1").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("4 Pieces: Haste 2 and Regeneration 2").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("The Forgemaster's boots.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("Equipping grants a boost to jumping.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
			lore.add(new TextComponent(""));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
