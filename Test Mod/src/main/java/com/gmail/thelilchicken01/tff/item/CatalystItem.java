package com.gmail.thelilchicken01.tff.item;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.block.TffPortalBlock;
import com.gmail.thelilchicken01.tff.init.BlockInit;
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

public class CatalystItem  extends Item {
	public CatalystItem() {
		super (new Properties().tab(TheFesterForest.tff_tab).stacksTo(1).rarity(Rarity.RARE));
	}
	
	@Override
	public InteractionResult useOn(UseOnContext context) {
		if(context.getPlayer().level.dimension() == ModDimensions.tff_key || context.getPlayer().level.dimension() == Level.OVERWORLD) {
			for (Direction direction : Direction.Plane.VERTICAL) {
				BlockPos framePos = context.getClickedPos().relative(direction);
				if (((TffPortalBlock) BlockInit.tff_portal.get()).trySpawnPortal(context.getLevel(), framePos)) {
					
					
					context.getLevel().playSound(context.getPlayer(), framePos, SoundEvents.PORTAL_TRIGGER, SoundSource.BLOCKS, 0.2f, 0.3f);
					return InteractionResult.CONSUME;
					
				}
				else return InteractionResult.FAIL;
			}
		}
		
		return InteractionResult.FAIL;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("A rotting pile of organic material.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click inside a rotten portal frame").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("to create a doorway to the Fester Forest.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("The portal frame is built with Fester Bricks,").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("crafted from smelted Fester Ore.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("A rotting pile of organic material.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
			lore.add(new TextComponent(""));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}
	
}
