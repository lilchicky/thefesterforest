package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;
import java.util.stream.IntStream;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item_util.MagicItem;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFItem;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GlowingBook extends TFFItem implements MagicItem {
	
	private String[] drops = {"Glacial Titan", "Fester Forest Loot Chests"};
	
	private int cooldown = 45;
	private int range = 8;

	public GlowingBook() {
		
		super(new Properties().tab(TheFesterForest.TFF_TAB).durability(64));
		
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOOK_PAGE_TURN, SoundSource.PLAYERS, 0.2F, world.getRandom().nextFloat() * 0.4F + 0.8F);
		
		if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.TWO) {
			range = 16;
			cooldown = 30;
		}
		if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.FOUR) {
			range = 24;
			cooldown = 15;
		}
		
		List<LivingEntity> nearbyEntities = ItemUtil.getLivingInArea(player, range, range);
		
		if (nearbyEntities.size() <= 32) {
		
			for (LivingEntity currentEntity : nearbyEntities) {
			
				currentEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 30 * 20, 0, false, false));
			
			}
		
		}
		else {
			
			for (int x = 0; x < 32; x++) {
				
				nearbyEntities.get(x).addEffect(new MobEffectInstance(MobEffects.GLOWING, 30 * 20, 0, false, false));
				
			}
			
		}
		
		player.getItemInHand(hand).hurtAndBreak(
				1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
		
		player.awardStat(Stats.ITEM_USED.get(this));
		player.getCooldowns().addCooldown(this, ItemUtil.getQuickcastCooldown(cooldown * 20, player.getItemInHand(hand)));
		
		return super.use(world, player, hand);
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
		return true;
	}

}
