package com.gmail.thelilchicken01.tff.item.dull;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.custom.CorrodedShroomEntity;
import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ShroomBucket extends Item {

	public ShroomBucket() {
		super(new Properties().tab(TheFesterForest.TFF_TAB).stacksTo(1));
	}
	
	@Override
	public InteractionResult useOn(UseOnContext context) {
		
		BlockState state = context.getLevel().getBlockState(context.getClickedPos().above());
		
		if (state == Blocks.AIR.defaultBlockState() || state == Blocks.WATER.defaultBlockState()) {
		
			CorrodedShroomEntity shroom = new CorrodedShroomEntity(ModEntityTypes.CORRODED_SHROOM.get(), context.getLevel());
			shroom.moveTo(context.getClickedPos().above(), 0.0f, 0.0f);
		
			if (!context.getLevel().isClientSide()) {
				context.getLevel().addFreshEntity(shroom);
				Player player = context.getPlayer();
			
				player.getItemInHand(player.getUsedItemHand()).shrink(1);
				
				if (!player.isCreative()) {
					ItemStack bucket = new ItemStack(Items.BUCKET);
					player.addItem(bucket);
				}
			
				return InteractionResult.SUCCESS;
			}
		
		}
		
		return InteractionResult.FAIL;
	}

}
