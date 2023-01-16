package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class VolatileNecklace extends Item implements ICurioItem {
	
	private String[] drops = {"Fester Forest Loot Chests"};
	
	private int strengthLevel = 0;
	
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
		
		ICurioItem.super.curioTick(slotContext, stack);
		
		if (slotContext.entity() instanceof Player) {
			
			Player player = (Player) slotContext.entity();
			
			if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.TWO) {
				
				strengthLevel = 1;
				
			}
			if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.FOUR) {
				
				strengthLevel = 2;
				
			}
			
		}
		
		if (slotContext.entity().isOnFire()) {
			if (!slotContext.entity().hasEffect(MobEffects.DAMAGE_BOOST)) {
				slotContext.entity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, strengthLevel, false, false));
			}
			else {
				if (slotContext.entity().getEffect(MobEffects.DAMAGE_BOOST).getDuration() < 40) {
					slotContext.entity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, strengthLevel, false, false));
				}
			}
		}
		
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Magic").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A scorching necklace fitted with a fiery charm.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("While on fire, gain strength.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("Magic").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A scorching necklace fitted with a fiery charm.").withStyle(ChatFormatting.GRAY));
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
