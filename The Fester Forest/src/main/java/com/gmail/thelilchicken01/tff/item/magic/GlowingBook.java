package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;
import java.util.stream.IntStream;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item.item_types.MagicItem;

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

public class GlowingBook extends Item implements MagicItem {
	
	private String[] drops = {"Glacial Titan"};
	
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
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Magic").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("An uncomfortably bright book, with the writings").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("of an old revealing spell.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Apply glowing to all nearby living things.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("Has a max of 32 entities.").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("Magic").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("An uncomfortably bright book, with the writings").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("of an old revealing spell.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}

}