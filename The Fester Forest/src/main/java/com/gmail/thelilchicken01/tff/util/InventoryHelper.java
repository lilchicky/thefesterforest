package com.gmail.thelilchicken01.tff.util;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

import com.gmail.thelilchicken01.tff.item.item.ICuriosUtil;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

/*
 * 
 * Full credit to Reliquary by flypepper. Could not have done this without their code!
 * 
 */

public class InventoryHelper {

	private InventoryHelper() {}
	
	private static final Set<BiFunction<Player, ICuriosUtil.Type, IItemHandler>> baubleHandlerFactory = new HashSet<>();
	
	public static void addBaublesItemHandlerFactory(BiFunction<Player, ICuriosUtil.Type, IItemHandler> factory) {
		
		baubleHandlerFactory.add(factory);
		
	}
	
	public static boolean playerHasItem(Player player, Item item, ICuriosUtil.Type baubleType) {
		
		return hasItemInBaubleInventories(player, item, baubleType);
		
	}
	
	private static boolean hasItemInBaubleInventories(Player player, Item item, ICuriosUtil.Type baubleType) {
		
		for (BiFunction<Player, ICuriosUtil.Type, IItemHandler> factory : baubleHandlerFactory) {
			
			IItemHandler handler = factory.apply(player, baubleType);
			for (int i = 0; i < handler.getSlots(); i++) {
				
				ItemStack baubleStack = handler.getStackInSlot(i);
				if (!baubleStack.isEmpty() && baubleStack.getItem() == item)  {
					
					return true;
				}
			}
		}
		return false;
	}
	
}
