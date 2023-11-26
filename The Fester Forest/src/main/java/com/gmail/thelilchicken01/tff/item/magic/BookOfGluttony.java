package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item_util.MagicItem;
import com.gmail.thelilchicken01.tff.item.item_util.TFFItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class BookOfGluttony extends TFFItem implements MagicItem {
	
	private String[] drops = {"Frostbitten King", "Crafted"};
	
	private int cooldown = 10;

	public BookOfGluttony() {
		super(new Properties().tab(TheFesterForest.TFF_TAB).food(
				new FoodProperties.Builder().saturationMod(0.8f).nutrition(10)
				.build()).durability(64));
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		
		entity.eat(level, stack.copy());
		
		if (entity instanceof Player) {
			
			Player player = (Player) entity;
			
			if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.TWO) {
				
				cooldown = 8;
				
			}
			if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.FOUR) {
				
				cooldown = 6;
				
			}
		
			stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
			player.awardStat(Stats.ITEM_USED.get(this));
			player.getCooldowns().addCooldown(this, ItemUtil.getQuickcastCooldown(cooldown * 20, stack));
		}
		
		return stack;
	}

	@Override
	public String itemType() {
		return "magic";
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
