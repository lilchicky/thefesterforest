package com.gmail.thelilchicken01.tff.item.armor;

import java.util.function.Supplier;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModArmorMaterial implements ArmorMaterial {

	// Mechanical Armor
	MECHANICAL("mechanical", 67, new int[]{4, 6, 7, 5}, 32, SoundEvents.NETHERITE_BLOCK_BREAK, 4.0F, 0.5F, () -> {
	    return Ingredient.of(Items.IRON_INGOT);
	}),
	
	//Volatile Armor
	VOLATILE("volatile", 15, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
	    return Ingredient.of(Items.BLAZE_POWDER);
	}),
	
	//Banshee Armor
	BANSHEE("banshee", 12, new int[]{2, 4, 5, 2}, 32, SoundEvents.WOOL_BREAK, 1.0F, 0.0F, () -> {
		return Ingredient.of(Items.BLACK_DYE);
	}),
	
	//Goopy Armor
	GOOP("goop", 40, new int[]{3, 6, 8, 3}, 7, SoundEvents.SLIME_BLOCK_BREAK, 2.0F, 0.0F, () -> {
		return Ingredient.of(ItemInit.ROTTING_SLIMEBALL.get());
	}),
	
	//Shroom Armor
	SHROOM("shroom", 32, new int[]{3, 6, 8, 3}, 7, SoundEvents.WOOL_BREAK, 2.0F, 0.0F, () -> {
		return Ingredient.of(BlockInit.CORRODED_SHROOM.get());
	}),
	
	//Rotfish Armor
	ROTFISH("rotfish", 18, new int[]{2, 4, 5, 2}, 16, SoundEvents.FISH_SWIM, 1.0F, 0.0F, () -> {
		return Ingredient.of(ItemInit.RAW_ROTFISH.get());
	}),
	
	//Reetle Armor
	REETLE("reetle", 37, new int[]{4, 6, 8, 4}, 15, SoundEvents.TURTLE_EGG_CRACK, 4.0F, 0.2F, () -> {
	    return Ingredient.of(ItemInit.BUG_CARCASS.get());
	}),
	
	//Frozen Armor
	FROZEN("frozen", -1, new int[]{2, 3, 4, 1}, 40, SoundEvents.ARMOR_EQUIP_DIAMOND, 1.0F, 0.0F, () -> {
		return Ingredient.of(Items.PACKED_ICE);
	});

	private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
	private final String name;
	private final int durabilityMultiplier;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyLoadedValue<Ingredient> repairIngredient;

	   private ModArmorMaterial(String p_40474_, int p_40475_, int[] p_40476_, int p_40477_, SoundEvent p_40478_, float p_40479_, float p_40480_, Supplier<Ingredient> p_40481_) {
	      this.name = p_40474_;
	      this.durabilityMultiplier = p_40475_;
	      this.slotProtections = p_40476_;
	      this.enchantmentValue = p_40477_;
	      this.sound = p_40478_;
	      this.toughness = p_40479_;
	      this.knockbackResistance = p_40480_;
	      this.repairIngredient = new LazyLoadedValue<>(p_40481_);
	   }

	   public int getDurabilityForSlot(EquipmentSlot p_40484_) {
	      return HEALTH_PER_SLOT[p_40484_.getIndex()] * this.durabilityMultiplier;
	   }

	   public int getDefenseForSlot(EquipmentSlot p_40487_) {
	      return this.slotProtections[p_40487_.getIndex()];
	   }

	   public int getEnchantmentValue() {
	      return this.enchantmentValue;
	   }

	   public SoundEvent getEquipSound() {
	      return this.sound;
	   }

	   public Ingredient getRepairIngredient() {
	      return this.repairIngredient.get();
	   }

	   public String getName() {
	      return TheFesterForest.MODID + ":" + this.name;
	   }

	   public float getToughness() {
	      return this.toughness;
	   }

	   public float getKnockbackResistance() {
		   return this.knockbackResistance;
	   }

}
