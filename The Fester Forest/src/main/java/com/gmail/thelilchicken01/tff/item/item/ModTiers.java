package com.gmail.thelilchicken01.tff.item.item;

import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
	
	public static final ForgeTier VOLATILE = new ForgeTier(4, 1600, 0.0F, 
			10.0F, 15, BlockTags.NEEDS_DIAMOND_TOOL, () -> {
	    return Ingredient.of(Items.BLAZE_POWDER);
	});
	
	public static final ForgeTier PAXEL_DIAMOND = new ForgeTier(3, 1561, 8.0F, 
			3.0F, 10, BlockTags.NEEDS_DIAMOND_TOOL, () -> {
	    return Ingredient.of(ItemInit.ROTTING_SLIMEBALL.get());
	});
	
	public static final ForgeTier METAL = new ForgeTier(4, 1600, 0.0F, 
			10.0F, 15, BlockTags.NEEDS_IRON_TOOL, () -> {
	    return Ingredient.of(Items.IRON_INGOT);
	});
	
	public static final ForgeTier GOOP = new ForgeTier(4, 1600, 0.0F, 
			4.0F, 15, BlockTags.NEEDS_IRON_TOOL, () -> {
	    return Ingredient.of(ItemInit.ROTTING_SLIMEBALL.get());
	});
	
	public static final ForgeTier MECHANICAL = new ForgeTier(4, 1600, 0.0F, 
			10.0F, 15, BlockTags.NEEDS_DIAMOND_TOOL, () -> {
	    return Ingredient.of(Items.IRON_INGOT);
	});
	
	public static final ForgeTier BONE = new ForgeTier(4, 1600, 24.0F, 
			10.0F, 15, BlockTags.NEEDS_DIAMOND_TOOL, () -> {
	    return Ingredient.of(Items.BONE);
	});

}
