package com.gmail.thelilchicken01.tff.item.item_util;

import java.util.List;

import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.item.GeoArmorItem;

public abstract class TFFGeoArmorItem extends GeoArmorItem {

	public TFFGeoArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
		super(material, slot, properties);
	}
	
	public abstract ArmorSets getSet();
	public abstract String[] dropsFrom();
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TranslatableComponent("type.tff.armor").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("description.tff." + this.getRegistryName().getPath()).withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("set.tff." + getSet().getName()).withStyle(ChatFormatting.AQUA));
			lore.add(new TranslatableComponent("set.tff." + getSet().getName() + ".half_set").withStyle(ChatFormatting.AQUA));
			lore.add(new TranslatableComponent("set.tff." + getSet().getName() + ".full_set").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("type.tff.drops_from").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < dropsFrom().length; x++) {
				lore.add(new TextComponent(dropsFrom()[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(" "));
		}
		else {
			lore.add(new TranslatableComponent("type.tff.armor").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("description.tff." + this.getRegistryName().getPath()).withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("type.tff.more_info").withStyle(ChatFormatting.YELLOW));
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("type.tff.drops_from").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < dropsFrom().length; x++) {
				lore.add(new TextComponent(dropsFrom()[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(" "));
		}
		
		super.appendHoverText(stack, world, lore, flag);
		
	}

}
