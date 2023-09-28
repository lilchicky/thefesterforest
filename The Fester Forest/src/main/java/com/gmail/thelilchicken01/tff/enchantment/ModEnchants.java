package com.gmail.thelilchicken01.tff.enchantment;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.item.MagicItem;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchants {

	public static RegistryObject<Enchantment> arcanePower;
	public static final EnchantmentCategory MAGIC = EnchantmentCategory.create("TFF_MAGIC", (item) -> item instanceof MagicItem);
	public static final DeferredRegister<Enchantment> MOD_ENCHANTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, TheFesterForest.MODID);

	static {
		arcanePower = MOD_ENCHANTS.register("arcane_power", () -> new ArcanePowerEnchant(MAGIC, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}));
	}
	
}