package com.gmail.thelilchicken01.tff.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ArcanePowerEnchant extends Enchantment {

	protected ArcanePowerEnchant(EnchantmentCategory category, EquipmentSlot[] slot) {
		super(Rarity.UNCOMMON, category, slot);
	}
	
	@Override
	public int getMaxLevel() {
		return 5;
	}
	
	public static double getDamage(int level) {
		return 1 + (0.1 * level);
	}
	
}
