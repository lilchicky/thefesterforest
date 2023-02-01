package com.gmail.thelilchicken01.tff.item.tool;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PaxelItem extends DiggerItem {

	public PaxelItem(Tier tier, int p_42962_, float p_42963_, Item.Properties p_42964_) {
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

}
