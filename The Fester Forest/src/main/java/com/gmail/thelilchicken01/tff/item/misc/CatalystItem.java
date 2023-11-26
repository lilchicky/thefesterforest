package com.gmail.thelilchicken01.tff.item.misc;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.block.TffPortalBlock;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFItem;
import com.gmail.thelilchicken01.tff.world.dimension.ModDimensions;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CatalystItem  extends TFFItem {
	
	private String[] drops = {"Overworld Dungeon Chests"};
	
	public CatalystItem() {
		super (new Properties().tab(TheFesterForest.TFF_TAB).stacksTo(1).rarity(Rarity.RARE));
	}
	
	@Override
	public InteractionResult useOn(UseOnContext context) {
		if(context.getPlayer().level.dimension() == ModDimensions.TFF_KEY || context.getPlayer().level.dimension() == Level.OVERWORLD) {
			for (Direction direction : Direction.Plane.VERTICAL) {
				BlockPos framePos = context.getClickedPos().relative(direction);
				if (((TffPortalBlock) BlockInit.TFF_PORTAL.get()).trySpawnPortal(context.getLevel(), framePos)) {
					
					
					context.getLevel().playSound(context.getPlayer(), framePos, SoundEvents.PORTAL_TRIGGER, SoundSource.BLOCKS, 0.2f, 0.3f);
					context.getPlayer().getItemInHand(context.getHand()).shrink(1);
					return InteractionResult.CONSUME;
					
				}
				else return InteractionResult.FAIL;
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
		return drops;
	}

	@Override
	public boolean isShiftable() {
		return true;
	}
	
}
