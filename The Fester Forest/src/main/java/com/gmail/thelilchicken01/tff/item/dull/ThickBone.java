package com.gmail.thelilchicken01.tff.item.dull;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.client.SwimHandler;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ThickBone extends Item implements ICurioItem {

	public ThickBone() {
		super(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB));
		
	}
	
	@Override
	public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
		if (slotContext.entity() instanceof ServerPlayer player) {
			
			slotContext.entity().getCapability(SwimHandler.CAPABILITY).ifPresent(
					handler -> {
						handler.setSinking(true);
						handler.syncSinking(player);
					}
				);
			
		}
	}
	
	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
		if (slotContext.entity() instanceof ServerPlayer player) {
			
			slotContext.entity().getCapability(SwimHandler.CAPABILITY).ifPresent(
					handler -> {
						handler.setSinking(false);
						handler.syncSinking(player);
					}
				);
			
		}
	}

}
