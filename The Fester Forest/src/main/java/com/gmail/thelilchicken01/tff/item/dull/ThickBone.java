package com.gmail.thelilchicken01.tff.item.dull;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.capability.SwimHandler;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ThickBone extends TFFItem implements ICurioItem {
	
	private String[] drops = {"Seathrown Skeleton", "Fester Forest Loot Chests"};

	public ThickBone() {
		super(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB));
		
	}
	
	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		if (slotContext.entity() instanceof ServerPlayer player) {
			slotContext.entity().getCapability(SwimHandler.CAPABILITY).ifPresent(
					handler -> {
						if (!handler.isSinking()) {
							handler.setSinking(true);
							handler.syncSinking(player);
						}
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

	@Override
	public String itemType() {
		return "dull";
	}

	@Override
	public String[] dropsFrom() {
		return drops;
	}

	@Override
	public boolean isShiftable() {
		return false;
	}

}
