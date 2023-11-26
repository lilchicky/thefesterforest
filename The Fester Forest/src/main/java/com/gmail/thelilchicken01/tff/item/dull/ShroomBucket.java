package com.gmail.thelilchicken01.tff.item.dull;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.custom.CorrodedShroomEntity;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShroomBucket extends TFFItem {

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
				
				player.awardStat(Stats.ITEM_USED.get(this));
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

	@Override
	public String itemType() {
		return "dull";
	}

	@Override
	public String[] dropsFrom() {
		return null;
	}

	@Override
	public boolean isShiftable() {
		return false;
	}

}
