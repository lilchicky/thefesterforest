package com.gmail.thelilchicken01.tff.item.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public abstract class TFFItem extends Item {

	public TFFItem(Properties properties) {
		super(properties);
	}
	
	public abstract String itemType();
	public abstract String itemName();
	public abstract String[] dropsFrom();
	
	public boolean isShiftable() {
		return false;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(isShiftable() && Screen.hasShiftDown()) {
			lore.add(new TranslatableComponent("type.tff." + itemType()).withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent("\u200B"));
			lore.add(new TranslatableComponent("description.tff." + itemName()).withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("\u200B"));
			lore.add(new TranslatableComponent("ability.tff." + itemName()).withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("\u200B"));
			lore.add(new TranslatableComponent("type.tff.drops_from").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < dropsFrom().length; x++) {
				lore.add(new TextComponent(dropsFrom()[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent("\u200B"));
		}
		else {
			lore.add(new TranslatableComponent("type.tff." + itemType()).withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent("\u200B"));
			lore.add(new TranslatableComponent("description.tff." + itemName()).withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("\u200B"));
			if (isShiftable()) {
				lore.add(new TranslatableComponent("type.tff.more_info").withStyle(ChatFormatting.YELLOW));
				lore.add(new TextComponent("\u200B"));
			}
			lore.add(new TranslatableComponent("type.tff.drops_from").withStyle(ChatFormatting.LIGHT_PURPLE));
			lore.add(new TextComponent("\u200B"));
			for (int x = 0; x < dropsFrom().length; x++) {
				lore.add(new TextComponent(dropsFrom()[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent("\u200B"));
		}
		
		super.appendHoverText(stack, world, lore, flag);
		
	}

}
