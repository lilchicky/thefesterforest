package com.gmail.thelilchicken01.tff.item.armor.bansheeArmor;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.armor.ModArmorMaterial;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item.ItemUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

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
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;

public class BansheeBoots extends ArmorItem {
	
	private String[] drops = {"Banshee"};
	
	public final Lazy<Multimap<Attribute, AttributeModifier>> LAZY = Lazy.of(() ->  {    
    	ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder(); 
    	
    	builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), 
	    		"move_speed", 0.2, AttributeModifier.Operation.MULTIPLY_BASE));
	    
	    builder.put(Attributes.ARMOR, new AttributeModifier(UUID.randomUUID(), 
	    		"armor", ModArmorMaterial.BANSHEE.getDefenseForSlot(EquipmentSlot.FEET), 
	    		AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), 
	    		"armor_toughness", ModArmorMaterial.BANSHEE.getToughness(), 
	    		AttributeModifier.Operation.ADDITION));
         
        if (ForgeMod.STEP_HEIGHT_ADDITION.isPresent()) {
        	 builder.put(ForgeMod.STEP_HEIGHT_ADDITION.get(), new AttributeModifier(UUID.randomUUID(),
 		    		"step_height", 1.0f, AttributeModifier.Operation.ADDITION));
        }
        
    	Multimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();
    	attributeModifiers = builder.build();
    	return attributeModifiers;
    });

	public BansheeBoots() {
		super(ModArmorMaterial.BANSHEE, EquipmentSlot.FEET, 
				new Properties().tab(TheFesterForest.tff_tab));
		
	}
	
	@Override
	public void onArmorTick(ItemStack stack, Level level, Player player) {
		
		super.onArmorTick(stack, level, player);
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return slot == EquipmentSlot.FEET ? this.LAZY.get() : super.getDefaultAttributeModifiers(slot);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Armor").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A light pair of dark, cloth boots.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("They seem to be pulsing with magic.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Set Bonus:").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("2+ Pieces: Minor buffs to all Magic items").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("4 Pieces: Major buffs to all Magic items").withStyle(ChatFormatting.AQUA));
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
			lore.add(new TextComponent("A light pair of dark, cloth boots.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("They seem to be pulsing with magic.").withStyle(ChatFormatting.GRAY));
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
