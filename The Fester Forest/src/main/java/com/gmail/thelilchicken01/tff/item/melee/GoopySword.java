package com.gmail.thelilchicken01.tff.item.melee;

import java.util.List;

import com.gmail.thelilchicken01.tff.effect.ModEffects;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFSwordItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GoopySword extends TFFSwordItem {
	
	private String[] drops = {"Rotting Goop", "Fester Forest Loot Chests"};

	public GoopySword(Tier tier, int damage, float aspeed, Properties properties) {
		super(tier, damage, aspeed, properties.food(
				new FoodProperties.Builder().saturationMod(0.1f).nutrition(1)
				.build()));
	}
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity entityHurt, LivingEntity entityAttacker) {
		
		entityHurt.addEffect(new MobEffectInstance(ModEffects.GOOP_ACID.get(), 100, 4));
		
		return super.hurtEnemy(stack, entityHurt, entityAttacker);
		
	}

	@Override
	public String itemType() {
		return "melee";
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
