package com.gmail.thelilchicken01.tff.item.item_util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/*
 * Extensive thanks and credit to reliquary - I cannot figure out how to otherwise cancel fall damages
 */

public interface ICuriosUtil {
	
	Type getCuriosType();
	
	void onWornTick(ItemStack stack, LivingEntity player);
	
	default void onEquipped(String identifier, LivingEntity player) {
		
		
	}
	
	enum Type {
		
		BELT("belt"),
		HEAD("head"),
		NONE("none");
		
		private final String identifier;
		
		Type(String identifier) {
			
			this.identifier = identifier;
			
		}
		
		public String getIdentifier() {
			
			return identifier;
			
		}
		
	}
	
}
