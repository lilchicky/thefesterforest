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
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public enum ArmorSets {
	
	//Volatile Set
	VOLATILE(() -> {
		return new Item[]{ItemInit.VOLATILE_BOOTS.get(),
				ItemInit.VOLATILE_LEGGINGS.get(),
				ItemInit.VOLATILE_CHESTPLATE.get(),
				ItemInit.VOLATILE_HELMET.get()};
	}),
	
	//Banshee Set
	BANSHEE(() -> {
		return new Item[]{ItemInit.BANSHEE_BOOTS.get(),
				ItemInit.BANSHEE_LEGGINGS.get(),
				ItemInit.BANSHEE_CHESTPLATE.get(),
				ItemInit.BANSHEE_HELMET.get(),
				ItemInit.FROZEN_BOOTS.get(),
				ItemInit.FROZEN_LEGGINGS.get(),
				ItemInit.FROZEN_CHESTPLATE.get(),
				ItemInit.FROZEN_HELMET.get()};
	}),
	
	//Reetle Set
	REETLE(() -> {
		return new Item[]{ItemInit.REETLE_BOOTS.get(),
				ItemInit.REETLE_LEGGINGS.get(),
				ItemInit.REETLE_CHESTPLATE.get(),
				ItemInit.REETLE_HELMET.get(),
				ItemInit.REETLE_ELYTRA.get()};
	}),
	
	//Goopy Set
	GOOP(() -> {
		return new Item[]{ItemInit.GOOPY_BOOTS.get(),
				ItemInit.GOOPY_LEGGINGS.get(),
				ItemInit.GOOPY_CHESTPLATE.get(),
				ItemInit.GOOPY_HELMET.get()};
	}),
	
	//Rotfish Set
	ROTFISH(() -> {
		return new Item[]{ItemInit.ROTFISH_BOOTS.get(),
				ItemInit.ROTFISH_LEGGINGS.get(),
				ItemInit.ROTFISH_CHESTPLATE.get(),
				ItemInit.ROTFISH_HELMET.get(),
				ItemInit.SHROOM_HAT.get()};
	}),
	
	//Mechanical Set
	MECHANICAL(() -> {
		return new Item[]{ItemInit.MECHANICAL_BOOTS.get(),
				ItemInit.MECHANICAL_LEGGINGS.get(),
				ItemInit.MECHANICAL_CHESTPLATE.get(),
				ItemInit.MECHANICAL_HELMET.get()};
	}),
	
	//Frozen Set
	FROZEN(() -> {
		return new Item[]{ItemInit.FROZEN_BOOTS.get(),
				ItemInit.FROZEN_LEGGINGS.get(),
				ItemInit.FROZEN_CHESTPLATE.get(),
				ItemInit.FROZEN_HELMET.get()};
	}),
	
	//Glacial Set
	GLACIAL(() -> {
		return new Item[]{ItemInit.GLACIAL_BOOTS.get(),
				ItemInit.GLACIAL_LEGGINGS.get(),
				ItemInit.GLACIAL_CHESTPLATE.get(),
				ItemInit.GLACIAL_HELMET.get()};
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
				
				if (player.getItemBySlot(((ArmorItem) armorSet.get()[x]).getSlot()).getItem() == armorSet.get()[x]) {
					setCount++;
				}
				
			}
			else if (armorSet.get()[x] instanceof ElytraItem) {
				
				if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == armorSet.get()[x]) {
					setCount++;
				}
				
			}
			
		}
		
		if (setCount == 2 || setCount == 3) {
			
			return SetCount.TWO;
			
		}
		else if (setCount >= 4) {
			
			return SetCount.FOUR;
			
		}
		else {
			
			return SetCount.EMPTY;
			
		}
		
	}
	
}
