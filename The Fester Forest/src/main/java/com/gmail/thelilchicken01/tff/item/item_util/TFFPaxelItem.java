package com.gmail.thelilchicken01.tff.item.item_util;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public abstract class TFFPaxelItem extends DiggerItem {

	public TFFPaxelItem(Tier tier, int p_42962_, float p_42963_, Item.Properties p_42964_) {
		super((float)p_42962_, p_42963_, tier, BlockTags.MINEABLE_WITH_PICKAXE, p_42964_);
	}
	
	@Override
	public float getDestroySpeed(ItemStack p_41004_, BlockState p_41005_) {
		return this.speed;
	}
	
	@Override
	public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
		
		return (state.is(BlockTags.MINEABLE_WITH_AXE) && net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops(getTier(), state)) ||
				(state.is(BlockTags.MINEABLE_WITH_PICKAXE) && net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops(getTier(), state)) ||
				(state.is(BlockTags.MINEABLE_WITH_HOE) && net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops(getTier(), state)) ||
				(state.is(BlockTags.MINEABLE_WITH_SHOVEL) && net.minecraftforge.common.TierSortingRegistry.isCorrectTierForDrops(getTier(), state));
	}
	
	@Override
	public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
	   return net.minecraftforge.common.ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction) ||
			   net.minecraftforge.common.ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction) ||
			   net.minecraftforge.common.ToolActions.DEFAULT_HOE_ACTIONS.contains(toolAction) ||
			   net.minecraftforge.common.ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		
		return enchantment.category.canEnchant(Items.IRON_PICKAXE) ||
				enchantment.category.canEnchant(Items.IRON_AXE) ||
				enchantment.category.canEnchant(Items.IRON_SHOVEL) ||
				enchantment.category.canEnchant(Items.IRON_HOE);
	}
	
	public abstract String itemType();
	public abstract String[] dropsFrom();
	public abstract boolean isShiftable();
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(isShiftable() && Screen.hasShiftDown()) {
			lore.add(new TranslatableComponent("type.tff." + itemType()).withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("description.tff." + this.getRegistryName().getPath()).withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("ability.tff." + this.getRegistryName().getPath()).withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(" "));
			if (dropsFrom() != null) {
				lore.add(new TranslatableComponent("type.tff.drops_from").withStyle(ChatFormatting.LIGHT_PURPLE));
				for (int x = 0; x < dropsFrom().length; x++) {
					lore.add(new TextComponent(dropsFrom()[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
				}
				lore.add(new TextComponent(" "));
			}
		}
		else {
			lore.add(new TranslatableComponent("type.tff." + itemType()).withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("description.tff." + this.getRegistryName().getPath()).withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(" "));
			if (isShiftable()) {
				lore.add(new TranslatableComponent("type.tff.more_info").withStyle(ChatFormatting.YELLOW));
				lore.add(new TextComponent(" "));
			}
			if (dropsFrom() != null) {
				lore.add(new TranslatableComponent("type.tff.drops_from").withStyle(ChatFormatting.LIGHT_PURPLE));
				for (int x = 0; x < dropsFrom().length; x++) {
					lore.add(new TextComponent(dropsFrom()[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
				}
				lore.add(new TextComponent(" "));
			}
		}
		
		super.appendHoverText(stack, world, lore, flag);
		
	}

}
