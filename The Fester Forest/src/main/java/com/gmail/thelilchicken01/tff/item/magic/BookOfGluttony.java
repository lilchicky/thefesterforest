package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item.item_types.MagicItem;

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

public class BookOfGluttony extends Item implements MagicItem {
	
	private String[] drops = {"Frostbitten King"};
	
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
	public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> lore, TooltipFlag p_41424_) {
		
		lore.add(new TextComponent("Magic").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("A leather book detailing a spell of").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("hunger satisfaction (Can be eaten).").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
			
		super.appendHoverText(p_41421_, p_41422_, lore, p_41424_);
	}

}
