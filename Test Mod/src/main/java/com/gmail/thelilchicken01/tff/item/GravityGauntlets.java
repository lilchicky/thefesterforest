package com.gmail.thelilchicken01.tff.item;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
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
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class GravityGauntlets extends Item implements ICurioItem {
	
	private String[] drops = {"Fester Forest Loot Chests"};
	
	public final Lazy<Multimap<Attribute, AttributeModifier>> LAZY = Lazy.of(() ->  {    
    	ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder(); 
         
         if (ForgeMod.REACH_DISTANCE.isPresent() && ForgeMod.ATTACK_RANGE.isPresent()) {
             builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.randomUUID(), "Weapon modifier", 2.0, AttributeModifier.Operation.ADDITION));
             builder.put(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier(UUID.randomUUID(), "Weapon modifier", 2.0, AttributeModifier.Operation.ADDITION));
         }
    	Multimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();
    	attributeModifiers = builder.build();
    	return attributeModifiers;
    });

	public GravityGauntlets() {
		super(new Properties().tab(TheFesterForest.tff_tab).stacksTo(1));
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
			ItemStack stack) {
		
		return LAZY.get();
		
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("A stone gauntlet infused with a mysterious magic.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("Allows you to interact with far away blocks and creatures.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));

		
		super.appendHoverText(stack, world, lore, flag);
	}
	

}
