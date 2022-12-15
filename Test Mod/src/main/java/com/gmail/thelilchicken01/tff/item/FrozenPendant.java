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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
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

public class FrozenPendant extends Item implements ICurioItem {
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	
	private int enemySlowdownSeconds = 5;

	public FrozenPendant(Properties properties) {
		super(properties);
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    //builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "bonus move speed", 0.2, AttributeModifier.Operation.ADDITION));

	    this.defaultModifiers = builder.build();
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
			ItemStack stack) {
		
		return this.defaultModifiers;
		
	}
	
	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		
		List<Entity> nearbyEntities = slotContext.entity().getLevel().getEntities(slotContext.entity(), new AABB(
			slotContext.entity().getX() - 4, slotContext.entity().getY()
			- 2, slotContext.entity().getZ() - 4, slotContext.entity().getX() + 4, slotContext.entity().getY() + 2, slotContext.entity().getZ() + 4));
		
		for (int x = 0; x < nearbyEntities.size(); x++) {
			
			if (nearbyEntities.get(x) instanceof Monster) {
				
				Monster currentEntity = ((Monster) nearbyEntities.get(x));
				
				if (!currentEntity.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
				
					currentEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, enemySlowdownSeconds * 20, 1));
						
					slotContext.entity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, (enemySlowdownSeconds + 1) * 20, 2));
				
				}
				
			}
		
		}
		
		ICurioItem.super.curioTick(slotContext, stack);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("When worn in the charm slot, leeches the").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("movement speed of nearby monsters.").withStyle(ChatFormatting.GRAY));
		
		super.appendHoverText(stack, world, lore, flag);
	}
	

}
