package com.gmail.thelilchicken01.tff.item.food;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class FrozenApple extends Item {
	
	private String[] drops = {"Frostbitten King", "Crafted"};

	public FrozenApple(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity player) {
		player.setTicksFrozen(180);
		return super.finishUsingItem(stack, world, player);
	}
	
	@Override
	public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> lore, TooltipFlag p_41424_) {
		
		lore.add(new TextComponent("Food").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
		
		super.appendHoverText(p_41421_, p_41422_, lore, p_41424_);
	}
	
	//TagInit.Items.tff_apples.contains(player.getItemInHand(hand).getItem()))

}
