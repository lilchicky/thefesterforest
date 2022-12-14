package com.gmail.thelilchicken01.tff.item;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class VolatileNecklace extends Item implements ICurioItem {
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	
	private int burnTimer = 0;
	private int burnCooldownSeconds = 5;

	public VolatileNecklace(Properties properties) {
		super(properties);
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "weapon damage", 6.0, AttributeModifier.Operation.ADDITION));

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
				slotContext.entity().getX() - 3, slotContext.entity().getY() - 2, slotContext.entity().getZ() - 3, slotContext.entity().getX() + 3, slotContext.entity().getY() + 2, slotContext.entity().getZ() + 3));
			boolean playerNear = false;
		
			for (int x = 0; x < nearbyEntities.size(); x++) {
			
				if (nearbyEntities.get(x) instanceof Monster && !nearbyEntities.get(x).isOnFire()) {
				
					nearbyEntities.get(x).setRemainingFireTicks(20);
					nearbyEntities.get(x).hurt(TheFesterForest.volatile_ghost, 2);
				
				}
			
			}
			
			burnTimer = 0;
		}
		
		ICurioItem.super.curioTick(slotContext, stack);
	}
	

}
