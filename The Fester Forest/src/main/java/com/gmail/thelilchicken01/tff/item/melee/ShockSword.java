package com.gmail.thelilchicken01.tff.item.melee;

import com.gmail.thelilchicken01.tff.effect.ModEffects;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFSwordItem;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class ShockSword extends TFFSwordItem {
	
	private String[] drops = {"Ambectrum", "Fester Forest Loot Chests"};

	public ShockSword(Tier tier, int damage, float aspeed, Properties properties) {
		super(tier, damage, aspeed, properties);
	}
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity entityHurt, LivingEntity entityAttacker) {
		
		if (Math.random() < 0.3333333) {
			entityHurt.addEffect(new MobEffectInstance(ModEffects.PARALYSIS.get(), 40, 0));
		}
		
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
		return true;
	}

}
