package com.gmail.thelilchicken01.tff.item.mixed;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item_util.EffectsUtil;
import com.gmail.thelilchicken01.tff.item.item_util.ModTiers;
import com.gmail.thelilchicken01.tff.item.item_util.TFFPickaxeItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

public class AncientHammer extends TFFPickaxeItem {
	
	private String[] drops = {"Fester Forest Loot Chests"};

	public AncientHammer() {
		super(ModTiers.METAL, 0, -3.3f, new Properties().tab(TheFesterForest.TFF_TAB).durability(2384));
	}
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		
		target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
		
		return super.hurtEnemy(stack, target, attacker);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		
		return enchantment.category.canEnchant(ItemInit.ANCIENT_GREATSWORD.get()) ||
				super.canApplyAtEnchantingTable(stack, enchantment);
	}

	@Override
	public String itemType() {
		return "melee.tool";
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
