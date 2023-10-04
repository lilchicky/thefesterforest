package com.gmail.thelilchicken01.tff.item.armor.reetleArmor;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.armor.ModArmorMaterial;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item.ItemUtil;
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

public class ReetleBoots extends ArmorItem {
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	
	private String[] drops = {"Crafted"};

	public ReetleBoots() {
		super(ModArmorMaterial.REETLE, EquipmentSlot.FEET, new Properties().tab(TheFesterForest.TFF_TAB));
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		
	    builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), 
	    		"attack_damage", -0.1, AttributeModifier.Operation.MULTIPLY_BASE));
	    
	    builder.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), 
	    		"max_health", 6, AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.ARMOR, new AttributeModifier(UUID.randomUUID(), 
	    		"armor", ModArmorMaterial.REETLE.getDefenseForSlot(EquipmentSlot.FEET), 
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
		return slot == EquipmentSlot.FEET ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
	}
	
	@Override
	public void onArmorTick(ItemStack stack, Level level, Player player) {
		
		super.onArmorTick(stack, level, player);
		
		if ( player.tickCount % 15 == 0 && !player.getLevel().isClientSide()) {
		
			if (ArmorSets.REETLE.getArmorSet(player) == SetCount.TWO) {
				ItemUtil.registerPotionEffect(MobEffects.DAMAGE_RESISTANCE, 0, player, 39);
			}
			if (ArmorSets.REETLE.getArmorSet(player) == SetCount.FOUR) {
				ItemUtil.registerPotionEffect(MobEffects.DAMAGE_RESISTANCE, 1, player, 39);
				ItemUtil.registerPotionEffect(MobEffects.SATURATION, 0, player, 39);
			}
		
		}
		
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Armor").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Netherite boots reinforced with Reetle exoskeletons.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("The heavy weight offers great protection, but").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("makes it difficult to lift your feet.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Set Bonus: Caraplate").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.BOLD));
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
			lore.add(new TextComponent("Armor").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Netherite boots reinforced with Reetle exoskeletons.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("The heavy weight offers great protection, but").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("makes it difficult to lift your feet.").withStyle(ChatFormatting.GRAY));
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
