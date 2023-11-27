package com.gmail.thelilchicken01.tff.item.food;

import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFItem;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class GoopySmoothie extends TFFItem {
	
	private String[] drops = {"Crafted"};

	public GoopySmoothie(Properties properties) {
		super(properties);
	}
	
	@Override
	public SoundEvent getEatingSound() {
		return SoundEvents.HONEY_DRINK;
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
		if (!world.isClientSide()) {
			if (user instanceof Player player) {
			
				if (!player.isCreative()) {
					player.heal(6.0f);
					ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
					player.addItem(bottle);
				}
			}
		}
		return super.finishUsingItem(stack, world, user);
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

}
