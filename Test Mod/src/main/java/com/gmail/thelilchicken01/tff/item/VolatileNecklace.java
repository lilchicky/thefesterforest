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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
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

public class VolatileNecklace extends Item implements ICurioItem {
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	
	private int enemyRadiusIgniteSeconds = 1;
	private int enemyRadiusDamage = 2;
	private int enemyRadius = 3;
	
	private int burnTimer = 0;
	private int burnCooldownSeconds = 5;

	public VolatileNecklace(Properties properties) {
		super(properties);
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "weapon damage", 2.0, AttributeModifier.Operation.ADDITION));

	    this.defaultModifiers = builder.build();
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
			ItemStack stack) {
		
		return this.defaultModifiers;
		
	}
	
	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		
		if(burnTimer < (burnCooldownSeconds * 20)) {
			
			burnTimer++;
			
		}
		
		if(burnTimer >= (burnCooldownSeconds * 20)) {
			List<Entity> nearbyEntities = slotContext.entity().getLevel().getEntities(slotContext.entity(), new AABB(
				slotContext.entity().getX() - enemyRadius, slotContext.entity().getY()
				- 2, slotContext.entity().getZ() - enemyRadius, slotContext.entity().getX() + enemyRadius, slotContext.entity().getY() + 2, slotContext.entity().getZ() + enemyRadius));
		
			for (int x = 0; x < nearbyEntities.size(); x++) {
			
				if (nearbyEntities.get(x) instanceof Monster && !nearbyEntities.get(x).isOnFire()) {
				
					nearbyEntities.get(x).setRemainingFireTicks(enemyRadiusIgniteSeconds * 20);
					nearbyEntities.get(x).hurt(TheFesterForest.volatile_ghost, enemyRadiusDamage);
				
				}
			
			}
			
			burnTimer = 0;
		}
		
		ICurioItem.super.curioTick(slotContext, stack);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("A scorching necklace fitted with a fiery charm.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Every " + burnCooldownSeconds / 2 + " seconds, ignite all").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("nearby monsters for " + enemyRadiusIgniteSeconds + " seconds").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("and damage them for " + enemyRadiusDamage + " health in a").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(enemyRadius + " block radius around you.").withStyle(ChatFormatting.AQUA));
		}
		else {
			lore.add(new TextComponent("A scorching necklace fitted with a fiery charm.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}
	

}
