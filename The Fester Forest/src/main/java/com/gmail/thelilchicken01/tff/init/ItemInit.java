package com.gmail.thelilchicken01.tff.init;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.item.armor.bansheeArmor.BansheeBoots;
import com.gmail.thelilchicken01.tff.item.armor.bansheeArmor.BansheeChestplate;
import com.gmail.thelilchicken01.tff.item.armor.bansheeArmor.BansheeHelmet;
import com.gmail.thelilchicken01.tff.item.armor.bansheeArmor.BansheeLeggings;
import com.gmail.thelilchicken01.tff.item.armor.frozenArmor.FrozenBoots;
import com.gmail.thelilchicken01.tff.item.armor.frozenArmor.FrozenChestplate;
import com.gmail.thelilchicken01.tff.item.armor.frozenArmor.FrozenHelmet;
import com.gmail.thelilchicken01.tff.item.armor.frozenArmor.FrozenLeggings;
import com.gmail.thelilchicken01.tff.item.armor.glacialArmor.GlacialBoots;
import com.gmail.thelilchicken01.tff.item.armor.glacialArmor.GlacialChestplate;
import com.gmail.thelilchicken01.tff.item.armor.glacialArmor.GlacialHelmet;
import com.gmail.thelilchicken01.tff.item.armor.glacialArmor.GlacialLeggings;
import com.gmail.thelilchicken01.tff.item.armor.goop.GoopyBoots;
import com.gmail.thelilchicken01.tff.item.armor.goop.GoopyChestplate;
import com.gmail.thelilchicken01.tff.item.armor.goop.GoopyHelmet;
import com.gmail.thelilchicken01.tff.item.armor.goop.GoopyLeggings;
import com.gmail.thelilchicken01.tff.item.armor.mechanicalArmor.MechanicalBoots;
import com.gmail.thelilchicken01.tff.item.armor.mechanicalArmor.MechanicalChestplate;
import com.gmail.thelilchicken01.tff.item.armor.mechanicalArmor.MechanicalHelmet;
import com.gmail.thelilchicken01.tff.item.armor.mechanicalArmor.MechanicalLeggings;
import com.gmail.thelilchicken01.tff.item.armor.reetleArmor.ReetleBoots;
import com.gmail.thelilchicken01.tff.item.armor.reetleArmor.ReetleChestplate;
import com.gmail.thelilchicken01.tff.item.armor.reetleArmor.ReetleElytra;
import com.gmail.thelilchicken01.tff.item.armor.reetleArmor.ReetleHelmet;
import com.gmail.thelilchicken01.tff.item.armor.reetleArmor.ReetleLeggings;
import com.gmail.thelilchicken01.tff.item.armor.rotfish.RotfishBoots;
import com.gmail.thelilchicken01.tff.item.armor.rotfish.RotfishChestplate;
import com.gmail.thelilchicken01.tff.item.armor.rotfish.RotfishHelmet;
import com.gmail.thelilchicken01.tff.item.armor.rotfish.RotfishLeggings;
import com.gmail.thelilchicken01.tff.item.armor.shroom_hat.ShroomHat;
import com.gmail.thelilchicken01.tff.item.armor.volatileArmor.VolatileBoots;
import com.gmail.thelilchicken01.tff.item.armor.volatileArmor.VolatileChestplate;
import com.gmail.thelilchicken01.tff.item.armor.volatileArmor.VolatileHelmet;
import com.gmail.thelilchicken01.tff.item.armor.volatileArmor.VolatileLeggings;
import com.gmail.thelilchicken01.tff.item.dull.AncientWhistle;
import com.gmail.thelilchicken01.tff.item.dull.AngelicWhistle;
import com.gmail.thelilchicken01.tff.item.dull.DualWieldSword;
import com.gmail.thelilchicken01.tff.item.dull.ExplosivePowder;
import com.gmail.thelilchicken01.tff.item.dull.FleshBall;
import com.gmail.thelilchicken01.tff.item.dull.ForgemasterHeart;
import com.gmail.thelilchicken01.tff.item.dull.FrozenHeart;
import com.gmail.thelilchicken01.tff.item.dull.FrozenShard;
import com.gmail.thelilchicken01.tff.item.dull.GlacialStomach;
import com.gmail.thelilchicken01.tff.item.dull.GoopyStone;
import com.gmail.thelilchicken01.tff.item.dull.GravityGauntlets;
import com.gmail.thelilchicken01.tff.item.dull.HeavyStone;
import com.gmail.thelilchicken01.tff.item.dull.MechanicalEye;
import com.gmail.thelilchicken01.tff.item.dull.MetalScrap;
import com.gmail.thelilchicken01.tff.item.dull.MoonShoes;
import com.gmail.thelilchicken01.tff.item.dull.PocketSand;
import com.gmail.thelilchicken01.tff.item.dull.PurifyingPowder;
import com.gmail.thelilchicken01.tff.item.dull.ReetleQueenAntennae;
import com.gmail.thelilchicken01.tff.item.dull.ReetleReagents;
import com.gmail.thelilchicken01.tff.item.dull.ReetleShellPlates;
import com.gmail.thelilchicken01.tff.item.dull.RotfishFang;
import com.gmail.thelilchicken01.tff.item.dull.ShockNecklace;
import com.gmail.thelilchicken01.tff.item.dull.ShroomBucket;
import com.gmail.thelilchicken01.tff.item.dull.SlipperyGoop;
import com.gmail.thelilchicken01.tff.item.dull.ThickBone;
import com.gmail.thelilchicken01.tff.item.food.AmbectrumDonut;
import com.gmail.thelilchicken01.tff.item.food.AmbectrumIceCreamSandwich;
import com.gmail.thelilchicken01.tff.item.food.AmbectrumJelly;
import com.gmail.thelilchicken01.tff.item.food.BugCarcass;
import com.gmail.thelilchicken01.tff.item.food.CookedRotfish;
import com.gmail.thelilchicken01.tff.item.food.CookedShroomCluster;
import com.gmail.thelilchicken01.tff.item.food.FrozenApple;
import com.gmail.thelilchicken01.tff.item.food.GoopyJello;
import com.gmail.thelilchicken01.tff.item.food.GoopySmoothie;
import com.gmail.thelilchicken01.tff.item.food.MechanicalApple;
import com.gmail.thelilchicken01.tff.item.food.RawRotfish;
import com.gmail.thelilchicken01.tff.item.food.RotfishSpecial;
import com.gmail.thelilchicken01.tff.item.food.RottingCarrot;
import com.gmail.thelilchicken01.tff.item.food.RottingPie;
import com.gmail.thelilchicken01.tff.item.food.ShroomCluster;
import com.gmail.thelilchicken01.tff.item.food.SlimySundae;
import com.gmail.thelilchicken01.tff.item.food.VolatileApple;
import com.gmail.thelilchicken01.tff.item.item_util.ModTiers;
import com.gmail.thelilchicken01.tff.item.magic.BoneLauncher;
import com.gmail.thelilchicken01.tff.item.magic.BookOfGluttony;
import com.gmail.thelilchicken01.tff.item.magic.BrittleBranch;
import com.gmail.thelilchicken01.tff.item.magic.BugEggs;
import com.gmail.thelilchicken01.tff.item.magic.DullOrb;
import com.gmail.thelilchicken01.tff.item.magic.EnergeticFungus;
import com.gmail.thelilchicken01.tff.item.magic.FlameOrb;
import com.gmail.thelilchicken01.tff.item.magic.FlowerCrown;
import com.gmail.thelilchicken01.tff.item.magic.ForgemasterOrb;
import com.gmail.thelilchicken01.tff.item.magic.FrostbittenOrb;
import com.gmail.thelilchicken01.tff.item.magic.FrozenOrb;
import com.gmail.thelilchicken01.tff.item.magic.FrozenPendant;
import com.gmail.thelilchicken01.tff.item.magic.GlaciatedStaff;
import com.gmail.thelilchicken01.tff.item.magic.GlowingBook;
import com.gmail.thelilchicken01.tff.item.magic.IceBook;
import com.gmail.thelilchicken01.tff.item.magic.LevitateOrb;
import com.gmail.thelilchicken01.tff.item.magic.LifeOrb;
import com.gmail.thelilchicken01.tff.item.magic.MeteorWand;
import com.gmail.thelilchicken01.tff.item.magic.PoisonOrb;
import com.gmail.thelilchicken01.tff.item.magic.ReetleOrb;
import com.gmail.thelilchicken01.tff.item.magic.RotfishOrb;
import com.gmail.thelilchicken01.tff.item.magic.RottingStaff;
import com.gmail.thelilchicken01.tff.item.magic.ShieldBook;
import com.gmail.thelilchicken01.tff.item.magic.VerdantBranch;
import com.gmail.thelilchicken01.tff.item.magic.VolatileNecklace;
import com.gmail.thelilchicken01.tff.item.magic.WitherOrb;
import com.gmail.thelilchicken01.tff.item.melee.AncientDagger;
import com.gmail.thelilchicken01.tff.item.melee.AncientGreatsword;
import com.gmail.thelilchicken01.tff.item.melee.AncientHatchet;
import com.gmail.thelilchicken01.tff.item.melee.AncientLongsword;
import com.gmail.thelilchicken01.tff.item.melee.BoneScythe;
import com.gmail.thelilchicken01.tff.item.melee.ForgemasterHammer;
import com.gmail.thelilchicken01.tff.item.melee.FrostMallet;
import com.gmail.thelilchicken01.tff.item.melee.GoopySword;
import com.gmail.thelilchicken01.tff.item.melee.LifeScythe;
import com.gmail.thelilchicken01.tff.item.melee.SeathrownPike;
import com.gmail.thelilchicken01.tff.item.melee.ShardOfIce;
import com.gmail.thelilchicken01.tff.item.melee.ShockSword;
import com.gmail.thelilchicken01.tff.item.misc.CatalystItem;
import com.gmail.thelilchicken01.tff.item.mixed.AncientHammer;
import com.gmail.thelilchicken01.tff.item.mixed.FesteringClub;
import com.gmail.thelilchicken01.tff.item.mixed.ReaverBlade;
import com.gmail.thelilchicken01.tff.item.mixed.ShadowBow;
import com.gmail.thelilchicken01.tff.item.mixed.VolatileSword;
import com.gmail.thelilchicken01.tff.item.pet_buckets.IceRambleBucket;
import com.gmail.thelilchicken01.tff.item.projectile.BoneShot;
import com.gmail.thelilchicken01.tff.item.projectile.BranchProjectile;
import com.gmail.thelilchicken01.tff.item.projectile.ElectricShot;
import com.gmail.thelilchicken01.tff.item.projectile.FrostBoltProjectile;
import com.gmail.thelilchicken01.tff.item.projectile.FrostbittenBoltProjectile;
import com.gmail.thelilchicken01.tff.item.projectile.FrozenRockShot;
import com.gmail.thelilchicken01.tff.item.projectile.IceSpikeShot;
import com.gmail.thelilchicken01.tff.item.projectile.Meteor;
import com.gmail.thelilchicken01.tff.item.projectile.RottingBoltShot;
import com.gmail.thelilchicken01.tff.item.ranged.FrozenBow;
import com.gmail.thelilchicken01.tff.item.ranged.IcyBow;
import com.gmail.thelilchicken01.tff.item.ranged.arrows.IcyArrowItem;
import com.gmail.thelilchicken01.tff.item.tool.BonePickaxe;
import com.gmail.thelilchicken01.tff.item.tool.ElectricPickaxe;
import com.gmail.thelilchicken01.tff.item.tool.GoopyStick;
import com.gmail.thelilchicken01.tff.item.tool.IcyPaxel;
import com.google.common.base.Supplier;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheFesterForest.MODID);
	
	/*
	 * 
	 * Spawn Eggs
	 * 
	 */
	
	//Rotting Skeleton Spawn Egg
	public static final RegistryObject<Item> ROTTING_SKELETON_SPAWN_EGG = register("rotting_skeleton_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.ROTTING_SKELETON, 0xa4775b, 0x81218e, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Crunch Beetle Spawn Egg
	public static final RegistryObject<Item> CRUNCH_BEETLE_SPAWN_EGG = register("crunch_beetle_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.CRUNCH_BEETLE, 0xa84e32, 0x5c1e0b, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Banshee Spawn Egg
	public static final RegistryObject<Item> BANSHEE_SPAWN_EGG = register("banshee_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.BANSHEE, 0x1a1a1a, 0x2b2b2b, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Reetle Queen Spawn Egg
	public static final RegistryObject<Item> REETLE_QUEEN_SPAWN_EGG = register("reetle_queen_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.REETLE_QUEEN, 0xc46543, 0x35cd28, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Volatile Ghost Spawn Egg
	public static final RegistryObject<Item> VOLATILE_GHOST_SPAWN_EGG = register("volatile_ghost_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.VOLATILE_GHOST, 0xff4000, 0xff2600, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Wight Spawn Egg
	public static final RegistryObject<Item> WIGHT_SPAWN_EGG = register("wight_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.WIGHT, 0xfc034e, 0xfc0303, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Forgemaster Spawn Egg
	public static final RegistryObject<Item> FORGEMASTER_SPAWN_EGG = register("forgemaster_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.FORGEMASTER, 0x616161, 0x404040, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Forgemasters Pylon Spawn Egg
	public static final RegistryObject<Item> PYLON_SPAWN_EGG = register("pylon_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.PYLON, 0x404040, 0xf02213, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Goop Spawn Egg
	public static final RegistryObject<Item> GOOP_SPAWN_EGG = register("goop_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.GOOP, 0x521252, 0x260926, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rotfish Spawn Egg
	public static final RegistryObject<Item> ROTFISH_SPAWN_EGG = register("rotfish_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.ROTFISH, 0x7b32a8, 0x4325b0, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Ambectrum Spawn Egg
	public static final RegistryObject<Item> AMBECTRUM_SPAWN_EGG = register("ambectrum_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.AMBECTRUM, 0x02d5d9, 0x45fcff, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Seathrown Skeleton Spawn Egg
	public static final RegistryObject<Item> SEATHROWN_SKELETON_SPAWN_EGG = register("seathrown_skeleton_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.SEATHROWN_SKELETON, 0x3d3d3d, 0x595959, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Corroded Shroom Spawn Egg
	public static final RegistryObject<Item> CORRODED_SHROOM_SPAWN_EGG = register("corroded_shroom_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.CORRODED_SHROOM, 0x1f1c5c, 0x804617, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Deep Reaver Spawn Egg
	public static final RegistryObject<Item> DEEP_REAVER_SPAWN_EGG = register("deep_reaver_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.DEEP_REAVER, 0x411820, 0x672854, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Ice Ramble Spawn Egg
	public static final RegistryObject<Item> ICE_RAMBLE_SPAWN_EGG = register("ice_ramble_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.ICE_RAMBLE, 0x5288e9, 0xbbcfff, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Frostbitten King Spawn Egg
	public static final RegistryObject<Item> FROSTBITTEN_KING_SPAWN_EGG = register("frostbitten_king_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.FROSTBITTEN_KING, 0xb4c9fd, 0xffffff, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Glacial Titan Spawn Egg
	public static final RegistryObject<Item> GLACIAL_TITAN_SPAWN_EGG = register("glacial_titan_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.GLACIAL_TITAN, 0x88aaff, 0x4069b4, //Background, Spots
					new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Armors
	 * 
	 */
	
	//Volatile Helmet
	public static final RegistryObject<Item> VOLATILE_HELMET = register("volatile_helmet", 
			() -> new VolatileHelmet());
		
	//Volatile Chestplate
	public static final RegistryObject<Item> VOLATILE_CHESTPLATE = register("volatile_chestplate", 
			() -> new VolatileChestplate());
			
	//Volatile Leggings
	public static final RegistryObject<Item> VOLATILE_LEGGINGS = register("volatile_leggings", 
			() -> new VolatileLeggings());
		
	//Volatile Boots
	public static final RegistryObject<Item> VOLATILE_BOOTS = register("volatile_boots", 
			() -> new VolatileBoots());
	
	//Reetle Elytra
	public static final RegistryObject<Item> REETLE_ELYTRA = register("reetle_elytra", 
			() -> new ReetleElytra());
	
	//Reetle Helmet
	public static final RegistryObject<Item> REETLE_HELMET = register("reetle_helmet", 
			() -> new ReetleHelmet());
			
	//Reetle Chestplate
	public static final RegistryObject<Item> REETLE_CHESTPLATE = register("reetle_chestplate", 
			() -> new ReetleChestplate());
				
	//Reetle Leggings
	public static final RegistryObject<Item> REETLE_LEGGINGS = register("reetle_leggings", 
			() -> new ReetleLeggings());
			
	//Reetle Boots
	public static final RegistryObject<Item> REETLE_BOOTS = register("reetle_boots", 
			() -> new ReetleBoots());
	
	//Mechanical Helmet
	public static final RegistryObject<Item> MECHANICAL_HELMET = register("mechanical_helmet", 
			() -> new MechanicalHelmet());
	
	//Mechanical Chestplate
	public static final RegistryObject<Item> MECHANICAL_CHESTPLATE = register("mechanical_chestplate", 
			() -> new MechanicalChestplate());
		
	//Mechanical Leggings
	public static final RegistryObject<Item> MECHANICAL_LEGGINGS = register("mechanical_leggings", 
			() -> new MechanicalLeggings());
		
	//Mechanical Boots
	public static final RegistryObject<Item> MECHANICAL_BOOTS = register("mechanical_boots", 
			() -> new MechanicalBoots());
	
	//Goopy Helmet
	public static final RegistryObject<Item> GOOPY_HELMET = register("goopy_helmet", 
			() -> new GoopyHelmet());
			
	//Goopy Chestplate
	public static final RegistryObject<Item> GOOPY_CHESTPLATE = register("goopy_chestplate", 
			() -> new GoopyChestplate());
				
	//Goopy Leggings
	public static final RegistryObject<Item> GOOPY_LEGGINGS = register("goopy_leggings", 
			() -> new GoopyLeggings());
			
	//Goopy Boots
	public static final RegistryObject<Item> GOOPY_BOOTS = register("goopy_boots", 
			() -> new GoopyBoots());
	
	//Banshee Helmet
	public static final RegistryObject<Item> BANSHEE_HELMET = register("banshee_helmet", 
			() -> new BansheeHelmet());
			
	//Banshee Chestplate
	public static final RegistryObject<Item> BANSHEE_CHESTPLATE = register("banshee_chestplate", 
			() -> new BansheeChestplate());
				
	//Banshee Leggings
	public static final RegistryObject<Item> BANSHEE_LEGGINGS = register("banshee_leggings", 
			() -> new BansheeLeggings());
			
	//Banshee Boots
	public static final RegistryObject<Item> BANSHEE_BOOTS = register("banshee_boots", 
			() -> new BansheeBoots());
	
	//Rotfish Helmet
	public static final RegistryObject<Item> ROTFISH_HELMET = register("rotfish_helmet", 
			() -> new RotfishHelmet());
			
	//Rotfish Chestplate
	public static final RegistryObject<Item> ROTFISH_CHESTPLATE = register("rotfish_chestplate", 
			() -> new RotfishChestplate());
				
	//Rotfish Leggings
	public static final RegistryObject<Item> ROTFISH_LEGGINGS = register("rotfish_leggings", 
			() -> new RotfishLeggings());
			
	//Rotfish Boots
	public static final RegistryObject<Item> ROTFISH_BOOTS = register("rotfish_boots", 
			() -> new RotfishBoots());
	
	//Frozen Helmet
	public static final RegistryObject<Item> FROZEN_HELMET = register("frozen_helmet", 
			() -> new FrozenHelmet());
			
	//Frozen Chestplate
	public static final RegistryObject<Item> FROZEN_CHESTPLATE = register("frozen_chestplate", 
			() -> new FrozenChestplate());
				
	//Frozen Leggings
	public static final RegistryObject<Item> FROZEN_LEGGINGS = register("frozen_leggings", 
			() -> new FrozenLeggings());
			
	//Frozen Boots
	public static final RegistryObject<Item> FROZEN_BOOTS = register("frozen_boots", 
			() -> new FrozenBoots());
	
	//Glacial Helmet
	public static final RegistryObject<Item> GLACIAL_HELMET = register("glacial_helmet", 
			() -> new GlacialHelmet());
			
	//Glacial Chestplate
	public static final RegistryObject<Item> GLACIAL_CHESTPLATE = register("glacial_chestplate", 
			() -> new GlacialChestplate());
				
	//Glacial Leggings
	public static final RegistryObject<Item> GLACIAL_LEGGINGS = register("glacial_leggings", 
			() -> new GlacialLeggings());
			
	//Glacial Boots
	public static final RegistryObject<Item> GLACIAL_BOOTS = register("glacial_boots", 
			() -> new GlacialBoots());
	
	/*
	 * 
	 * Melee
	 * 
	 */
	
	// Volatile Sword
	public static final RegistryObject<Item> VOLATILE_SWORD = register("volatile_sword", 
			() -> new VolatileSword(ModTiers.VOLATILE, -3, -2.2f, new Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Forgemaster Hammer
	public static final RegistryObject<Item> FORGEMASTER_HAMMER = register("forgemaster_hammer", 
			() -> new ForgemasterHammer(ModTiers.MECHANICAL, 14, -3.5f, new Properties().tab(TheFesterForest.TFF_TAB)
					.durability(3200)));
	
	//Goopy Sword
	public static final RegistryObject<Item> GOOPY_SWORD = register("goopy_sword", 
			() -> new GoopySword(ModTiers.GOOP, 0, -2.4f, new Properties().tab(TheFesterForest.TFF_TAB).durability(1678)));
	
	//Bone Scythe
	public static final RegistryObject<Item> BONE_SCYTHE = register("bone_scythe", 
			() -> new BoneScythe(ModTiers.BONE, 4, -3.0f, new Properties().tab(TheFesterForest.TFF_TAB)
					.durability(561)));
	
	//Life Scythe
	public static final RegistryObject<Item> LIFE_SCYTHE = register("life_scythe", 
			() -> new LifeScythe(ModTiers.BONE, 2, -3.2f, new Properties().tab(TheFesterForest.TFF_TAB)
					.durability(1400)));
	
	//Shock Sword
	public static final RegistryObject<Item> SHOCK_SWORD = register("shock_sword", 
			() -> new ShockSword(ModTiers.ELECTRIC, -5, -1.8f, new Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Seathrown Pike
	public static final RegistryObject<Item> SEATHROWN_PIKE = register("seathrown_pike", 
			() -> new SeathrownPike(ModTiers.METAL, -1, -2.8f, new Properties().tab(TheFesterForest.TFF_TAB).durability(550)));
	
	//Reaver Blade
	public static final RegistryObject<Item> REAVER_BLADE = register("reaver_blade", 
			() -> new ReaverBlade(ModTiers.BONE, -6, -1f, new Properties().tab(TheFesterForest.TFF_TAB).durability(1750)));
	
	//Frost Mallet
	public static final RegistryObject<Item> FROST_MALLET = register("frost_mallet",
			() -> new FrostMallet());
	
	//Shard of Ice
	public static final RegistryObject<Item> SHARD_OF_ICE = register("shard_of_ice",
			() -> new ShardOfIce(new Properties().tab(TheFesterForest.TFF_TAB).durability(1790), 0.1));
	
	//Ancient Greatsword
	public static final RegistryObject<Item> ANCIENT_GREATSWORD = register("ancient_greatsword", 
			() -> new AncientGreatsword(ModTiers.METAL, -1, -3f, new Properties().tab(TheFesterForest.TFF_TAB).durability(550)));
	
	//Ancient Dagger
	public static final RegistryObject<Item> ANCIENT_DAGGER = register("ancient_dagger", 
			() -> new AncientDagger(ModTiers.METAL, -7, -1.0f, new Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Ancient Hatchet
	public static final RegistryObject<Item> ANCIENT_HATCHET = register("ancient_hatchet", 
			() -> new AncientHatchet(ModTiers.METAL, -4, -2.0f, new Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Ancient Longsword
	public static final RegistryObject<Item> ANCIENT_LONGSWORD = register("ancient_longsword", 
			() -> new AncientLongsword(ModTiers.METAL, 10, -2.4f, new Properties().tab(TheFesterForest.TFF_TAB).durability(5)));
	
	//Festering Club
	public static final RegistryObject<Item> FESTERING_CLUB = register("festering_club", 
			() -> new FesteringClub(ModTiers.WOODEN, -3, -2.4f, new Properties().tab(TheFesterForest.TFF_TAB).durability(420)));
	
	//Ancient Hammer
	public static final RegistryObject<Item> ANCIENT_HAMMER = register("ancient_hammer",
			() -> new AncientHammer());
	
	/*
	 * 
	 * Magic
	 * 
	 */
	
	//Bug Eggs
	public static final RegistryObject<BugEggs> BUG_EGGS = register("bug_eggs",
			() -> new BugEggs(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	//Shield Book
	public static final RegistryObject<Item> SHIELD_BOOK = register("shield_book", 
			() -> new ShieldBook());
	
	//Meteor Wand
	public static final RegistryObject<Item> METEOR_WAND = register("meteor_wand",
			() -> new MeteorWand(new Properties().tab(TheFesterForest.TFF_TAB).durability(875), 
					0.1).repair(() -> Ingredient.of(Items.IRON_INGOT)));
	
	//Bone Launcher
	public static final RegistryObject<Item> BONE_LAUNCHER = register("bone_launcher",
			() -> new BoneLauncher(new Properties().tab(TheFesterForest.TFF_TAB).durability(734), 
					0.1).repair(() -> Ingredient.of(Items.BONE_BLOCK)));
	
	//Brittle Branch
	public static final RegistryObject<Item> BRITTLE_BRANCH = register("brittle_branch",
			() -> new BrittleBranch(new Properties().tab(TheFesterForest.TFF_TAB).durability(144), 
					0.1).repair(() -> Ingredient.of(Items.DEAD_BUSH)));
	
	//Verdant Branch
	public static final RegistryObject<Item> VERDANT_BRANCH = register("verdant_branch",
			() -> new VerdantBranch(new Properties().tab(TheFesterForest.TFF_TAB).durability(288), 
					0.1).repair(() -> Ingredient.of(BlockInit.ROTTING_TALL_GRASS.get())));
	
	//Book of Glowing
	public static final RegistryObject<Item> GLOWING_BOOK = register("glowing_book", 
			() -> new GlowingBook());
	
	//Glaciated Staff
	public static final RegistryObject<Item> GLACIATED_STAFF = register("glaciated_staff",
			() -> new GlaciatedStaff(new Properties().tab(TheFesterForest.TFF_TAB).durability(154), 
					0.1).repair(() -> Ingredient.of(Items.PACKED_ICE)));
	
	//Book of Gluttony
	public static final RegistryObject<Item> FOOD_BOOK = register("food_book", 
			() -> new BookOfGluttony());
	
	//Book of Frost
	public static final RegistryObject<Item> ICE_BOOK = register("ice_book", 
			() -> new IceBook());
	
	//Rotting Staff
	public static final RegistryObject<Item> ROTTING_STAFF = register("rotting_staff", 
			() -> new RottingStaff());
	
	//Dull Orb
	public static final RegistryObject<Item> DULL_ORB = register("dull_orb",
			() -> new DullOrb());
	
	//Flame Orb
	public static final RegistryObject<Item> FLAME_ORB = register("flame_orb",
			() -> new FlameOrb());
	
	//Levitate Orb
	public static final RegistryObject<Item> LEVITATE_ORB = register("levitate_orb",
			() -> new LevitateOrb());
	
	//Levitate Orb
	public static final RegistryObject<Item> POISON_ORB = register("poison_orb",
			() -> new PoisonOrb());
	
	//Frozen Orb
	public static final RegistryObject<Item> FROZEN_ORB = register("frozen_orb",
			() -> new FrozenOrb());
	
	//Life Orb
	public static final RegistryObject<Item> LIFE_ORB = register("life_orb",
			() -> new LifeOrb());
	
	//Wither Orb
	public static final RegistryObject<Item> WITHER_ORB = register("wither_orb",
			() -> new WitherOrb());
	
	//Reetle Orb
	public static final RegistryObject<Item> REETLE_ORB = register("reetle_orb",
			() -> new ReetleOrb());
	
	//Forgemaster Orb
	public static final RegistryObject<Item> FORGEMASTER_ORB = register("forgemaster_orb",
			() -> new ForgemasterOrb());
	
	//Frostbitten Orb
	public static final RegistryObject<Item> FROSTBITTEN_ORB = register("frostbitten_orb",
			() -> new FrostbittenOrb());
	
	//Rotfish Orb
	public static final RegistryObject<Item> ROTFISH_ORB = register("rotfish_orb",
			() -> new RotfishOrb());
	
	/*
	 * 
	 * Ranged
	 * 
	 */
	
	//Shadow Bow
	public static final RegistryObject<Item> SHADOW_BOW = register("shadow_bow", 
			() -> new ShadowBow());
	
	//Icy Bow
	public static final RegistryObject<Item> ICY_BOW = register("icy_bow", 
			() -> new IcyBow());
	
	//Frozen Bow
	public static final RegistryObject<Item> FROZEN_BOW = register("frozen_bow", 
			() -> new FrozenBow());
	
	/*
	 * Arrows
	 */
	
	public static final RegistryObject<Item> ICY_ARROW = register("icy_arrow",
			() -> new IcyArrowItem());
	
	/*
	 * 
	 * Tools
	 * 
	 */
	
	//Goopy Paxel
	public static final RegistryObject<Item> GOOPY_STICK = register("goopy_stick",
			() -> new GoopyStick());
	
	//Bone Pickaxe
	public static final RegistryObject<Item> BONE_PICKAXE = register("bone_pickaxe",
			() -> new BonePickaxe());
	
	//Electric Pickaxe
	public static final RegistryObject<Item> ELECTRIC_PICKAXE = register("electric_pickaxe",
			() -> new ElectricPickaxe());
	
	//Icy Paxel
	public static final RegistryObject<Item> ICY_PAXEL = register("icy_paxel",
			() -> new IcyPaxel());
	
	/*
	 * 
	 * Dull
	 * 
	 */
	
	//Explosive Powder
	public static final RegistryObject<Item> EXPLOSIVE_POWDER = register("explosive_powder",
			() -> new ExplosivePowder(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	//Reetle Shell
	public static final RegistryObject<Item> REETLE_SHELL = register("reetle_shell", 
			() -> new ReetleShellPlates());
	
	//Pocket Sand
	public static final RegistryObject<Item> POCKET_SAND = register("pocket_sand",
			() -> new PocketSand(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	//Rotting Slimeball
	public static final RegistryObject<Item> ROTTING_SLIMEBALL = register("rotting_slimeball", 
			() -> new Item(new Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Ancient Whistle
	public static final RegistryObject<Item> ANCIENT_WHISTLE = register("ancient_whistle",
			() -> new AncientWhistle(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	//Angelic Whistle
	public static final RegistryObject<Item> ANGELIC_WHISTLE = register("angelic_whistle",
			() -> new AngelicWhistle(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	//Flesh Ball
	public static final RegistryObject<Item> FLESH_BALL = register("flesh_ball", 
			() -> new FleshBall(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Boss Scraps
	 * 
	 */
	
	//Metal Scrap
	public static final RegistryObject<Item> METAL_SCRAP = register("metal_scrap",
			() -> new MetalScrap());
	
	//Frozen Shard
	public static final RegistryObject<Item> FROZEN_SHARD = register("frozen_shard",
			() -> new FrozenShard());
	
	/*
	 * 
	 * Baubles
	 * 
	 */
	
	//Moon Shoes
	public static final RegistryObject<Item> MOON_SHOES = register("moon_shoes", 
			() -> new MoonShoes());
	
	//Gravity Gauntlet
	public static final RegistryObject<Item> GRAVITY_GAUNTLET = register("gravity_gauntlet", 
			() -> new GravityGauntlets());
	
	//Reetle Queen Antennae
	public static final RegistryObject<Item> REETLE_QUEEN_ANTENNAE = register("reetle_queen_antennae", 
			() -> new ReetleQueenAntennae());
	
	//Bag of Reetle Legs
	public static final RegistryObject<Item> REETLE_REAGENTS = register("reetle_reagents", 
			() -> new ReetleReagents());
	
	//Forgemaster's Heart
	public static final RegistryObject<Item> FORGEMASTER_HEART = register("forgemaster_heart",
			() -> new ForgemasterHeart(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	//Frozen Heart
	public static final RegistryObject<Item> FROZEN_HEART = register("frozen_heart",
			() -> new FrozenHeart(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	//Heavy Stone
	public static final RegistryObject<Item> HEAVY_STONE = register("heavy_stone",
			() -> new HeavyStone(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	//Volatile Necklace
	public static final RegistryObject<Item> VOLATILE_NECKLACE = register("volatile_necklace",
			() -> new VolatileNecklace(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	//Frozen Pendant
	public static final RegistryObject<Item> FROZEN_PENDANT = register("frozen_pendant",
			() -> new FrozenPendant(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	//Mechanical Eye
	public static final RegistryObject<Item> MECHANICAL_EYE = register("mechanical_eye",
			() -> new MechanicalEye(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	//Frozen Pendant
	public static final RegistryObject<Item> SLIPPERY_GOOP = register("slippery_goop",
			() -> new SlipperyGoop());
	
	//Dual Wield Sword
	public static final RegistryObject<Item> DUAL_WIELD_SWORD = register(
			"dual_wield_sword", () -> new DualWieldSword());
	
	//Rotfish Fang
	public static final RegistryObject<Item> ROTFISH_FANG = register("rotfish_fang",
			() -> new RotfishFang());
	
	//Shock Necklace
	public static final RegistryObject<Item> SHOCK_NECKLACE = register("shock_necklace",
			() -> new ShockNecklace());
	
	//Energetic Fungus
	public static final RegistryObject<Item> ENERGETIC_FUNGUS = register("energetic_fungus",
			() -> new EnergeticFungus());
	
	//Thick Bone
	public static final RegistryObject<Item> THICK_BONE = register("thick_bone",
			() -> new ThickBone());
	
	//Glacial Stomach
	public static final RegistryObject<Item> GLACIAL_STOMACH = register("glacial_stomach",
			() -> new GlacialStomach());
	
	//Goopy Stone
	public static final RegistryObject<Item> GOOPY_STONE = register("goopy_stone",
			() -> new GoopyStone());
	
	//Flower Crown
	public static final RegistryObject<Item> FLOWER_CROWN = register("flower_crown",
			() -> new FlowerCrown(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Foods
	 * 
	 */
	
	//Goopy Jello
	public static final RegistryObject<Item> GOOPY_JELLO = register("goopy_jello", 
			() -> new GoopyJello());
	
	//Rotting Carrot
	public static final RegistryObject<Item> ROTTING_CARROT = register("rotting_carrot", 
			() -> new RottingCarrot());
	
	//Rotting Pie
	public static final RegistryObject<Item> ROTTING_PIE = register("rotting_pie", 
			() -> new RottingPie());
	
	//Bug Carcass
	public static final RegistryObject<Item> BUG_CARCASS = register("bug_carcass", 
			() -> new BugCarcass());
	
	//Raw Rotfish
	public static final RegistryObject<Item> RAW_ROTFISH = register("raw_rotfish", 
			() -> new RawRotfish());
	
	//Cooked Rotfish
	public static final RegistryObject<Item> COOKED_ROTFISH = register("cooked_rotfish", 
			() -> new CookedRotfish());
	
	//Ambectrum Jelly
	public static final RegistryObject<Item> AMBECTRUM_JELLY = register("ambectrum_jelly", 
			() -> new AmbectrumJelly());
		
	//Ambectrum Donut
	public static final RegistryObject<Item> AMBECTRUM_DONUT = register("ambectrum_donut", 
			() -> new AmbectrumDonut());
	
	//Rotfish Special
	public static final RegistryObject<Item> ROTFISH_SPECIAL = register("rotfish_special", 
			() -> new RotfishSpecial());
	
	//Shroom Cluster
	public static final RegistryObject<Item> SHROOM_CLUSTER = register("shroom_cluster", 
			() -> new ShroomCluster());
		
	//Cooked Shroom Cluster
	public static final RegistryObject<Item> COOKED_SHROOM_CLUSTER = register("cooked_shroom_cluster", 
			() -> new CookedShroomCluster());
	
	//Goopy Smoothie
	public static final RegistryObject<Item> GOOPY_SMOOTHIE = register("goopy_smoothie", 
			() -> new GoopySmoothie(new Properties().tab(TheFesterForest.TFF_TAB).food(new FoodProperties.Builder().saturationMod(0.3f).nutrition(6)
					.effect(() -> new MobEffectInstance(MobEffects.HEAL, 20, 1), 1f).build())));
	
	//Slimy Sundae
	public static final RegistryObject<Item> SLIMY_SUNDAE = register("slimy_sundae", 
			() -> new SlimySundae());
	
	//Ambectrum Ice Cream Sandwich
	public static final RegistryObject<Item> AMBECTRUM_ICE_CREAM_SANDWICH = register("ambectrum_ice_cream_sandwich", 
			() -> new AmbectrumIceCreamSandwich());
	
	//Volatile Apple
	public static final RegistryObject<Item> VOLATILE_APPLE = register("volatile_apple", 
			() -> new VolatileApple(new Properties().tab(TheFesterForest.TFF_TAB).food(new FoodProperties.Builder().alwaysEat().saturationMod(0.3f).nutrition(4)
					.effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1f).build())));
	
	//Mechanical Apple
	public static final RegistryObject<Item> MECHANICAL_APPLE = register("mechanical_apple", 
			() -> new MechanicalApple(new Properties().tab(TheFesterForest.TFF_TAB).food(new FoodProperties.Builder().alwaysEat().saturationMod(0.3f).nutrition(4)
					.effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2880, 2), 1f).build())));
	
	//Frozen Apple
	public static final RegistryObject<Item> FROZEN_APPLE = register("frozen_apple", 
			() -> new FrozenApple(new Properties().tab(TheFesterForest.TFF_TAB).food(new FoodProperties.Builder().alwaysEat().saturationMod(0.3f).nutrition(4)
					.effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2880, 2), 1f).build())));
	
	/*
	 * 
	 * Misc Items
	 * 
	 */
	
	//Purifying Powder
	public static final RegistryObject<Item> PURIFYING_POWDER = register("purifying_powder",
			() -> new PurifyingPowder(new Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Fester Chunk
	public static final RegistryObject<Item> FESTER_CHUNK = register("fester_chunk", 
			() -> new Item(new Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rotting Brick
	public static final RegistryObject<Item> ROTTING_BRICK = register("rotting_brick", 
			() -> new Item(new Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Shroom Hat
	public static final RegistryObject<Item> SHROOM_HAT = register("shroom_hat", 
			() -> new ShroomHat());
		
	//Rotting Catalyst
	public static final RegistryObject<Item> ROTTING_CATALYST = register("rotting_catalyst", CatalystItem::new);
	
	//Shroom Bucket
	public static final RegistryObject<Item> SHROOM_BUCKET = register("shroom_bucket", 
			() -> new ShroomBucket());
	
	//Ice Ramble Bucket
	public static final RegistryObject<Item> ICE_RAMBLE_BUCKET = register("ice_ramble_bucket", 
			() -> new IceRambleBucket());
	
	/*
	 * 
	 * Projectile Items
	 * 
	 */
	
	//Branch Charge
	public static final RegistryObject<BranchProjectile> BRANCH_CHARGE = register("branch_charge",
			() -> new BranchProjectile(new Properties().stacksTo(1), 10));// damage of bullet
	
	//Bone Charge
	public static final RegistryObject<BoneShot> BONE_CHARGE = register("bone_charge",
			() -> new BoneShot(new Properties().stacksTo(1), 2));// damage of bullet
	
	//Meteor
	public static final RegistryObject<Meteor> METEOR_CHARGE = register("meteor_charge",
			() -> new Meteor(new Properties().stacksTo(1), 15));
	
	//Electric Charge
	public static final RegistryObject<ElectricShot> ELECTRIC_CHARGE = register("electric_charge",
			() -> new ElectricShot(new Properties().stacksTo(1), 15));
	
	//Ice Spike
	public static final RegistryObject<IceSpikeShot> ICE_SPIKE = register("ice_spike",
			() -> new IceSpikeShot(new Properties().stacksTo(1), 15));
	
	//Frozen Rock
	public static final RegistryObject<FrozenRockShot> FROZEN_ROCK = register("frozen_rock",
			() -> new FrozenRockShot(new Properties().stacksTo(1), 15));
	
	//Frost Bolt
	public static final RegistryObject<FrostBoltProjectile> FROST_BOLT = register("frost_bolt",
			() -> new FrostBoltProjectile(new Properties().stacksTo(1), 15));
	
	//Frostbitten Bolt
	public static final RegistryObject<FrostbittenBoltProjectile> FROSTBITTEN_BOLT = register("frostbitten_bolt",
			() -> new FrostbittenBoltProjectile(new Properties().stacksTo(1), 15));
	
	//Rotting Bolt
	public static final RegistryObject<RottingBoltShot> ROTTING_BOLT = register("rotting_bolt",
			() -> new RottingBoltShot(new Properties().stacksTo(1), 15));
	
	/*
	 * 
	 * Registry
	 * 
	 */
	
	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
		return ITEMS.register(name, item);
	}
	
}
