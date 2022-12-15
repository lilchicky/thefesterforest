package com.gmail.thelilchicken01.tff.init;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.item.AncientGreatsword;
import com.gmail.thelilchicken01.tff.item.AncientWhistle;
import com.gmail.thelilchicken01.tff.item.CatalystItem;
import com.gmail.thelilchicken01.tff.item.DualWieldSword;
import com.gmail.thelilchicken01.tff.item.FrozenPendant;
import com.gmail.thelilchicken01.tff.item.VolatileApple;
import com.gmail.thelilchicken01.tff.item.VolatileNecklace;
import com.gmail.thelilchicken01.tff.item.VolatileSword;
import com.gmail.thelilchicken01.tff.item.tiers.VolatileTier;
import com.google.common.base.Supplier;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	
	public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, TheFesterForest.modid);
	
	/*
	 * For swords - VolatileTier base is 4. Iron Sword base is 1.6. -2.4 will give base sword, larger subtracted number
	 * will give slower speed, smaller subtracted number will give higher attack speed.
	 */
	
	// Volatile Sword
	public static final RegistryObject<Item> volatileSword = register("volatile_sword", 
			() -> new VolatileSword(new VolatileTier(), -3, -2.2f, new Properties().tab(TheFesterForest.tff_tab)));
	
	//Ancient Greatsword
	public static final RegistryObject<Item> ancientGreatsword = register("ancient_greatsword", 
			() -> new AncientGreatsword(new VolatileTier(), -1, -3f, new Properties().tab(TheFesterForest.tff_tab).durability(550)));
	
	//Volatile Apple
	public static final RegistryObject<Item> volatileApple = register("volatile_apple", 
			() -> new VolatileApple(new Properties().tab(TheFesterForest.tff_tab).food(new FoodProperties.Builder().alwaysEat().saturationMod(14.0f).nutrition(6)
					.effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1f).build())));
	
	//Bug Carcass
	public static final RegistryObject<Item> bug_carcass = register("bug_carcass", 
			() -> new Item(new Properties().tab(TheFesterForest.tff_tab).food(new FoodProperties.Builder().saturationMod(14.0f).nutrition(8)
					.build())));
	
	//Rotting Brick
	public static final RegistryObject<Item> rotting_brick = register("rotting_brick", 
			() -> new Item(new Properties().tab(TheFesterForest.tff_tab)));
		
	//Rotting Catalyst
	public static final RegistryObject<Item> rotting_catalyst = register("rotting_catalyst", CatalystItem::new);
	
	//Dual Wield Sword
	public static final RegistryObject<Item> dual_wield_sword = register("dual_wield_sword", () -> new DualWieldSword());
		
	//Rotting Skeleton Spawn Egg
	public static final RegistryObject<Item> rotting_skeleton_spawn_egg = items.register("rotting_skeleton_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.rotting_skeleton, 0xa4775b, 0x81218e, //Background, Spots
					new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Crunch Beetle Spawn Egg
	public static final RegistryObject<Item> crunch_beetle_spawn_egg = items.register("crunch_beetle_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.crunch_beetle, 0xa84e32, 0x5c1e0b, //Background, Spots
					new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Banshee Spawn Egg
	public static final RegistryObject<Item> banshee_spawn_egg = items.register("banshee_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.banshee, 0x1a1a1a, 0x2b2b2b, //Background, Spots
					new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Volatile Ghost Spawn Egg
	public static final RegistryObject<Item> volatile_ghost_spawn_egg = items.register("volatile_ghost_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.volatile_ghost, 0xff4000, 0xff2600, //Background, Spots
					new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Ancient Whistle
	public static final RegistryObject<Item> ancient_whistle = items.register("ancient_whistle",
			() -> new AncientWhistle(new Properties().stacksTo(1).tab(TheFesterForest.tff_tab)));
	
	//Volatile Necklace
	public static final RegistryObject<Item> volatile_necklace = items.register("volatile_necklace",
			() -> new VolatileNecklace(new Properties().stacksTo(1).tab(TheFesterForest.tff_tab)));
	
	//Frozen Pendant
	public static final RegistryObject<Item> frozen_pendant = items.register("frozen_pendant",
			() -> new FrozenPendant(new Properties().stacksTo(1).tab(TheFesterForest.tff_tab)));
	
	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
		return items.register(name, item);
	}
	
}
