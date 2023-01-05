package com.gmail.thelilchicken01.tff.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class MechanicalApple extends Item {

	public MechanicalApple(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity player) {
		return super.finishUsingItem(stack, world, player);
	}
	
	//TagInit.Items.tff_apples.contains(player.getItemInHand(hand).getItem()))

}
