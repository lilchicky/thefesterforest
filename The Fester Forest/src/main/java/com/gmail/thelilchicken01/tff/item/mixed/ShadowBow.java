package com.gmail.thelilchicken01.tff.item.mixed;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShadowBow extends BowItem {
	
	private String[] drops = {"Banshee", "Fester Forest Loot Chests"};
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public ShadowBow() {
		super(new Properties().durability(966).tab(TheFesterForest.tff_tab));
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, 
	    		"Weapon modifier", 8.0, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, 
	    		"Weapon modifier", -2.4f, AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), 
	    		"Weapon modifier", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
	    
	    this.defaultModifiers = builder.build();
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		
		return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		
		return enchantment.category.canEnchant(ItemInit.ancientGreatsword.get()) ||
				super.canApplyAtEnchantingTable(stack, enchantment);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("Melee, Ranged").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("A dark, shadowy bow with razor sharp limbs.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("Functions as both a sword and a bow, accepting").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("both kinds of enchantments.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));

		
		super.appendHoverText(stack, world, lore, flag);
	}

}
