package com.gmail.thelilchicken01.tff.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class VolatileApple extends Item {

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
	
	//TagInit.Items.tff_apples.contains(player.getItemInHand(hand).getItem()))

}
