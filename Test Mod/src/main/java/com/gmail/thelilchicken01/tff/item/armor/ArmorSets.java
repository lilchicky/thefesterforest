package com.gmail.thelilchicken01.tff.item.armor;

import java.util.function.Supplier;

import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public enum ArmorSets {
	
	//Volatile Set
	VOLATILE(() -> {
		return new Item[]{ItemInit.volatile_boots.get(),
				ItemInit.volatile_leggings.get(),
				ItemInit.volatile_chestplate.get(),
				ItemInit.volatile_helmet.get()};
	}),
	
	//Reetle Set
	REETLE(() -> {
		return new Item[]{ItemInit.reetle_boots.get(),
				ItemInit.reetle_leggings.get(),
				ItemInit.reetle_chestplate.get(),
				ItemInit.reetle_helmet.get(),
				ItemInit.reetle_elytra.get()};
	}),
	
	//Mechanical Set
	MECHANICAL(() -> {
		return new Item[]{ItemInit.mechanical_boots.get(),
				ItemInit.mechanical_leggings.get(),
				ItemInit.mechanical_chestplate.get(),
				ItemInit.mechanical_helmet.get()};
	});
	
	private final LazyLoadedValue<Item[]> armorSet;
	
	private ArmorSets(Supplier<Item[]> armorSet) {
		
		this.armorSet = new LazyLoadedValue<>(armorSet);
		
	}
	
	public Item[] getSet() {
		
		return this.armorSet.get();
		
	}
	
	public SetCount getArmorSet(Player player) {
		
		int setCount = 0;
		
		for(int x = 0; x < armorSet.get().length; x++) {
			
			if (armorSet.get()[x] instanceof ArmorItem) {
				
				if (player.getItemBySlot(((ArmorItem) armorSet.get()[x]).getSlot()).toString()
						.equals(new ItemStack(armorSet.get()[x]).toString())) {
					setCount++;
				}
				
			}
			else if (armorSet.get()[x] instanceof ElytraItem) {
				
				if (player.getItemBySlot(EquipmentSlot.CHEST).toString()
						.equals(new ItemStack(armorSet.get()[x]).toString())) {
					setCount++;
				}
				
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
