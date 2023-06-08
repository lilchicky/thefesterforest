package com.gmail.thelilchicken01.tff.item.melee;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;
import top.theillusivec4.curios.api.SlotContext;

public class SeathrownPike extends SwordItem {
	
	private String[] drops = {"Seathrown Skeleton", "Fester Forest Loot Chests"};
	
	public final Lazy<Multimap<Attribute, AttributeModifier>> LAZY = Lazy.of(() ->  {    
    	ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder(); 
         
         if (ForgeMod.REACH_DISTANCE.isPresent() && ForgeMod.ATTACK_RANGE.isPresent()) {
             builder.put(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier(UUID.randomUUID(), "attack_range", 2.0, AttributeModifier.Operation.ADDITION));
         }
    	Multimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();
    	attributeModifiers = builder.build();
    	return attributeModifiers;
    });

	public SeathrownPike(Tier tier, int damage, float aspeed, Properties properties) {
		super(tier, damage, aspeed, properties);
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return slot == EquipmentSlot.MAINHAND ? LAZY.get() : super.getAttributeModifiers(slot, stack);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("Melee").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("A sharp point on the end of a very long stick.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("Slightly wet and very unwieldly.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
