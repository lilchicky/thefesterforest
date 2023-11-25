package com.gmail.thelilchicken01.tff.item.food;

import java.util.List;

import com.gmail.thelilchicken01.tff.item.item_util.TFFItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class VolatileApple extends TFFItem {
	
	private String[] drops = {"Volatile Ghost", "Fester Forest Loot Chests"};

	public VolatileApple(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
		return 1000;
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity player) {
		if (!world.isClientSide()) {
			player.setRemainingFireTicks(1200);
		}
		return super.finishUsingItem(stack, world, player);
	}

	@Override
	public String itemType() {
		return "food";
	}

	@Override
	public String[] dropsFrom() {
		return drops;
	}

	@Override
	public boolean isShiftable() {
		return false;
	}
	
	//TagInit.Items.tff_apples.contains(player.getItemInHand(hand).getItem()))

}
