package com.gmail.thelilchicken01.tff.item.armor.frozenArmor;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.ModArmorMaterial;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;

public class FrozenHelmet extends ArmorItem {
	
	private String[] drops = {"Frostbitten King", "Crafted"};
	
	public final Lazy<Multimap<Attribute, AttributeModifier>> LAZY = Lazy.of(() ->  {    
    	ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder(); 
    	
    	builder.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), 
	    		"max_health", ModArmorMaterial.FROZEN.getDefenseForSlot(EquipmentSlot.HEAD) + 9, AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.ARMOR, new AttributeModifier(UUID.randomUUID(), 
	    		"armor", ModArmorMaterial.FROZEN.getDefenseForSlot(EquipmentSlot.HEAD), 
	    		AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), 
	    		"armor_toughness", ModArmorMaterial.FROZEN.getToughness(), 
	    		AttributeModifier.Operation.ADDITION));
        
        if (ForgeMod.ATTACK_RANGE.isPresent()) {
       	 	builder.put(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier(UUID.randomUUID(),
		    		"attack_range", 1.0f, AttributeModifier.Operation.ADDITION));
        }
        
        if (ForgeMod.REACH_DISTANCE.isPresent()) {
       	 	builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.randomUUID(),
		    		"reach_distance", 1.0f, AttributeModifier.Operation.ADDITION));
        }
        
    	Multimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();
    	attributeModifiers = builder.build();
    	return attributeModifiers;
    });

	public FrozenHelmet() {
		super(ModArmorMaterial.FROZEN, EquipmentSlot.HEAD, 
				new Properties().tab(TheFesterForest.TFF_TAB));
		
	}
	
	@Override
	public void onArmorTick(ItemStack stack, Level level, Player player) {
		
		super.onArmorTick(stack, level, player);
		
		if (ArmorSets.FROZEN.getArmorSet(player) == SetCount.TWO) {
			if ( player.tickCount % 15 == 0 && !player.getLevel().isClientSide()) {
				List<Monster> nearbyEntities = ItemUtil.getMonstersInArea(player, 5, 5);
				
				for (Monster currentMonster : nearbyEntities) {
				
					currentMonster.setTicksFrozen(180);
					
				}
			}
		}
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return slot == EquipmentSlot.HEAD ? this.LAZY.get() : super.getDefaultAttributeModifiers(slot);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Armor").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("An extremely hard helmet. Gives you a brain").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("freeze.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Set Bonus: Cryostasis, Magicka").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent("2+ Pieces: Freeze all monsters within 5 blocks.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("Minor buffs to all Magic items.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("4 Pieces: Freeze all monsters within 10 blocks.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("Major buffs to all Magic items.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Pairs with other Magic buff armors.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
		}
		else {
			lore.add(new TextComponent("Armor").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("An extremely hard helmet. Gives you a brain").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("freeze.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
