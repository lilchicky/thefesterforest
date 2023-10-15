package com.gmail.thelilchicken01.tff.item.pet_buckets;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.custom.IceRambleEntity;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
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

public class IceRambleBucket extends Item {

	public IceRambleBucket() {
		super(new Properties().tab(TheFesterForest.TFF_TAB).stacksTo(1));
	}
	
	@Override
	public InteractionResult useOn(UseOnContext context) {
		
		BlockState state = context.getLevel().getBlockState(context.getClickedPos().above());
		
		if (state == Blocks.AIR.defaultBlockState() || state == Blocks.WATER.defaultBlockState()) {
		
			IceRambleEntity ramble = new IceRambleEntity(ModEntityTypes.ICE_RAMBLE.get(), context.getLevel());
			if (context.getItemInHand().hasTag()) {
				
				UUID ownerUUID = context.getItemInHand().getTag().getUUID("tff.owner");
				Player player = context.getLevel().getPlayerByUUID(ownerUUID);
				
				ramble.setOwnerUUID(ownerUUID);
				ramble.tame(player);
				ramble.connectToPlayer(player);
				ramble.moveTo(context.getClickedPos().above(), 0.0f, 0.0f);
			}
			else {
				//ramble.setOwnerUUID(context.getPlayer().getUUID());
				//ramble.tame(context.getPlayer());
				//ramble.connectToPlayer(context.getPlayer());
				ramble.moveTo(context.getClickedPos().above(), 0.0f, 0.0f);
			}
		
			if (!context.getLevel().isClientSide()) {
				context.getLevel().addFreshEntity(ramble);
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
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("Dull").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("A bucket holding an Ice Ramble.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		if (stack.hasTag()) {
			UUID owner = stack.getTag().getUUID("tff.owner");
			lore.add(new TextComponent("Belongs to " + world.getPlayerByUUID(owner).getName().getString()).withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
