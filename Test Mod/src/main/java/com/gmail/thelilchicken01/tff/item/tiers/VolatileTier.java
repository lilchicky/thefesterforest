package com.gmail.thelilchicken01.tff.item.tiers;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class VolatileTier implements Tier {

	@Override
	public int getUses() {
		return 800;
	}

	@Override
	public float getSpeed() {
		return -0.0f;
	}

	@Override
	public float getAttackDamageBonus() {
		return 10.0f;
	}
	
	

	@Override
	public int getLevel() {
		return 5;
	}

	@Override
	public int getEnchantmentValue() {
		return 1;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return null;
	}

}
