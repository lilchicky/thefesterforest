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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ReetleChestplate extends ArmorItem {
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	
	private String[] drops = {"Crafted"};

	public ReetleChestplate() {
		super(ModArmorMaterial.REETLE, EquipmentSlot.CHEST, new Properties().tab(TheFesterForest.tff_tab));
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), 
	    		"attack_damage", -4, AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), 
	    		"max_health", 10, AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.ARMOR, new AttributeModifier(UUID.randomUUID(), 
	    		"armor", ModArmorMaterial.REETLE.getDefenseForSlot(EquipmentSlot.CHEST), 
	    		AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), 
	    		"armor_toughness", ModArmorMaterial.REETLE.getToughness(), 
	    		AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), 
	    		"knockback_resistance", ModArmorMaterial.REETLE.getKnockbackResistance(), 
	    		AttributeModifier.Operation.ADDITION));

	    this.defaultModifiers = builder.build();
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return slot == EquipmentSlot.CHEST ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
	}
	
	@Override
	public void onArmorTick(ItemStack stack, Level level, Player player) {
		
		if (getSetCount(player) == 2 || getSetCount(player) == 3) {
			if (!player.hasEffect(MobEffects.DAMAGE_RESISTANCE)) {
				player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 60, 0, false, false));
			}
			else {
				if (player.getEffect(MobEffects.DAMAGE_RESISTANCE).getDuration() < 40) {
					player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 60, 0, false, false));
				}
			}
		}
		
		super.onArmorTick(stack, level, player);
	}
	
	private int getSetCount(Player player) {
		
		int wornPieces = 0;
		
		if (player.getItemBySlot(EquipmentSlot.HEAD).toString()
				.equals(new ItemStack(ItemInit.reetle_helmet.get()).toString())) {
			wornPieces++;
		}
		if (player.getItemBySlot(EquipmentSlot.CHEST).toString()
				.equals(new ItemStack(ItemInit.reetle_chestplate.get()).toString()) ||
				player.getItemBySlot(EquipmentSlot.CHEST).toString()
				.equals(new ItemStack(ItemInit.reetle_elytra.get()).toString())) {
			wornPieces++;
		}
		if (player.getItemBySlot(EquipmentSlot.LEGS).toString()
				.equals(new ItemStack(ItemInit.reetle_leggings.get()).toString())) {
			wornPieces++;
		}
		if (player.getItemBySlot(EquipmentSlot.FEET).toString()
				.equals(new ItemStack(ItemInit.reetle_boots.get()).toString())) {
			wornPieces++;
		}
		
		return wornPieces;
		
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Netherite chestplate reinforced with Reetle exoskeletons.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("The heavy weight offers great protection, but").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("severely limits your ability to move your arms.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Set Bonus:").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("2+ Pieces: Resistance 1").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("4 Pieces: Resistance 2 and Saturation").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("Netherite chestplate reinforced with Reetle exoskeletons.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("The heavy weight offers great protection, but").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("severely limits your ability to move your arms.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}
	
}
