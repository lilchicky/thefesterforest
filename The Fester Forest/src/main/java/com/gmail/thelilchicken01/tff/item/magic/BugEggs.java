package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;

import com.gmail.thelilchicken01.tff.enchantment.ModEnchants;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.custom.PlayerCrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item_util.MagicItem;
import com.gmail.thelilchicken01.tff.item.item_util.MagicWeapon;
import com.gmail.thelilchicken01.tff.item.item_util.TFFItem;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BugEggs extends TFFItem implements MagicItem, MagicWeapon {
	
	private String[] drops = {"Reetle Queen", "Fester Forest Loot Chests"};
	
	private int cooldownSeconds = 20;
	
	public BugEggs(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		int bugs;
		
		if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.TWO) {
			bugs = 5 + (EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.arcanePower.get(), player.getItemInHand(hand)));
		}
		if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.FOUR) {
			bugs = 7 + (EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.arcanePower.get(), player.getItemInHand(hand)));
		}
		else {
			bugs = 3 + (EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.arcanePower.get(), player.getItemInHand(hand)));
		}
		
		for (int x = 0; x < bugs; x++) {
			
			PlayerCrunchBeetleEntity beetle = new PlayerCrunchBeetleEntity(ModEntityTypes.PLAYER_CRUNCH_BEETLE.get(), world);
			
			beetle.setOwnerUUID(player.getUUID());
			beetle.setPos(player.getX(), player.getY(), player.getZ());
			beetle.tame(player);
			
			world.addFreshEntity(beetle);
		}
		
		player.playSound(SoundEvents.TURTLE_EGG_CRACK, 1.2f, 1.7f);
		
		player.getCooldowns().addCooldown(this, ItemUtil.getQuickcastCooldown(cooldownSeconds * 20, player.getItemInHand(hand)));

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
