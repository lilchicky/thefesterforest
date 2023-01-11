package com.gmail.thelilchicken01.tff.item.armor;

import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

public enum ArmorSets {
	
	//Mechanical Set
	MECHANICAL(new ArmorItem[]{(ArmorItem) ItemInit.mechanical_boots.get(),
			(ArmorItem) ItemInit.mechanical_leggings.get(),
			(ArmorItem) ItemInit.mechanical_chestplate.get(),
			(ArmorItem) ItemInit.mechanical_helmet.get()}),
	//Volatile Set
	VOLATILE(new ArmorItem[]{(ArmorItem) ItemInit.volatile_boots.get(),
			(ArmorItem) ItemInit.volatile_leggings.get(),
			(ArmorItem) ItemInit.volatile_chestplate.get(),
			(ArmorItem) ItemInit.volatile_helmet.get()}),
	
	//Reetle Set
	REETLE(new ArmorItem[]{(ArmorItem) ItemInit.reetle_boots.get(),
			(ArmorItem) ItemInit.reetle_leggings.get(),
			(ArmorItem) ItemInit.reetle_chestplate.get(),
			(ArmorItem) ItemInit.reetle_helmet.get(),
			(ArmorItem) ItemInit.reetle_elytra.get()});
	
	private final ArmorItem[] armorItem;
	
	private ArmorSets(ArmorItem[] armorItem) {
		
		this.armorItem = armorItem;
		
	}
	
	public ArmorItem[] getSet() {
		
		return armorItem;
		
	}
	
	public SetCount getArmorSet(Player player) {
		
		int setCount = 0;
		
		for(int x = 0; x < armorItem.length; x++) {
			
			if (player.getItemBySlot(armorItem[x].getSlot()).toString()
					.equals(new ItemStack(armorItem[x]).toString())) {
				setCount++;
			}
			
		}
		
		if (setCount == 2 || setCount == 3) {
			
			return SetCount.TWO;
			
		}
		else if (setCount == 4) {
			
			return SetCount.FOUR;
			
		}
		else {
			
			return SetCount.EMPTY;
			
		}
		
	}
	
}
