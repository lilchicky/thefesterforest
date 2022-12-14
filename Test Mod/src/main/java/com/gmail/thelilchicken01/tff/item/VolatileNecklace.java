package com.gmail.thelilchicken01.tff.item;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class VolatileNecklace extends Item implements ICurioItem {
	
	private int burnTimer = 0;
	private int burnCooldownSeconds = 5;

	public VolatileNecklace(Properties properties) {
		super(properties);
		
	}
	
//	@Override
//	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slot, UUID uuid,
//			ItemStack stack) {
//
//		Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
//		if (!slot.cosmetic()) {
//			multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "Weapon Damage", 4, AttributeModifier.Operation.ADDITION));
//		}
//		
//		return multimap;
//		
//	}
	
	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
		
		ICurioItem.super.onUnequip(slotContext, newStack, stack);
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
