package com.gmail.thelilchicken01.tff.enchantment;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.item.item_types.MagicItem;
import com.gmail.thelilchicken01.tff.item.item.item_types.MagicWeapon;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchants {

	public static RegistryObject<Enchantment> arcanePower, quickcast;
	public static final EnchantmentCategory MAGIC = EnchantmentCategory.create("TFF_MAGIC", (item) -> item instanceof MagicItem);
	public static final EnchantmentCategory MAGIC_WEAPONS = EnchantmentCategory.create("TFF_MAGIC_WEAPON", (item) -> item instanceof MagicWeapon);
	public static final DeferredRegister<Enchantment> MOD_ENCHANTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, TheFesterForest.MODID);

	static {
		arcanePower = MOD_ENCHANTS.register("arcane_power", () -> new ArcanePowerEnchant(MAGIC_WEAPONS, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}));
		quickcast = MOD_ENCHANTS.register("quickcast", () -> new QuickcastEnchant(MAGIC, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}));
	}
	
}
