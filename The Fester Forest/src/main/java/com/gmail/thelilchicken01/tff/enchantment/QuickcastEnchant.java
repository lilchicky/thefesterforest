package com.gmail.thelilchicken01.tff.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class QuickcastEnchant extends Enchantment {

	protected QuickcastEnchant(EnchantmentCategory category, EquipmentSlot[] slot) {
		super(Rarity.RARE, category, slot);
	}
	
	@Override
	public int getMaxLevel() {
		return 5;
	}
	
	public static int cdr(int level, int cooldown) {
		return (int)(cooldown / Math.pow((level+1), 0.2));
	}
	
}
