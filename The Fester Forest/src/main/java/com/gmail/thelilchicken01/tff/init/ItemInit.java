package com.gmail.thelilchicken01.tff.init;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.item.armor.bansheeArmor.BansheeBoots;
import com.gmail.thelilchicken01.tff.item.armor.bansheeArmor.BansheeChestplate;
import com.gmail.thelilchicken01.tff.item.armor.bansheeArmor.BansheeHelmet;
import com.gmail.thelilchicken01.tff.item.armor.bansheeArmor.BansheeLeggings;
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
import com.gmail.thelilchicken01.tff.item.dull.GravityGauntlets;
import com.gmail.thelilchicken01.tff.item.dull.HeavyStone;
import com.gmail.thelilchicken01.tff.item.dull.MechanicalEye;
import com.gmail.thelilchicken01.tff.item.dull.MoonShoes;
import com.gmail.thelilchicken01.tff.item.dull.PocketSand;
import com.gmail.thelilchicken01.tff.item.dull.PurifyingPowder;
import com.gmail.thelilchicken01.tff.item.dull.ReetleQueenAntennae;
import com.gmail.thelilchicken01.tff.item.dull.ReetleReagents;
import com.gmail.thelilchicken01.tff.item.dull.ReetleShellPlates;
import com.gmail.thelilchicken01.tff.item.dull.SlipperyGoop;
import com.gmail.thelilchicken01.tff.item.food.BugCarcass;
import com.gmail.thelilchicken01.tff.item.food.CookedRotfish;
import com.gmail.thelilchicken01.tff.item.food.GoopyJello;
import com.gmail.thelilchicken01.tff.item.food.MechanicalApple;
import com.gmail.thelilchicken01.tff.item.food.RawRotfish;
import com.gmail.thelilchicken01.tff.item.food.RottingCarrot;
import com.gmail.thelilchicken01.tff.item.food.RottingPie;
import com.gmail.thelilchicken01.tff.item.food.VolatileApple;
import com.gmail.thelilchicken01.tff.item.item.ModTiers;
import com.gmail.thelilchicken01.tff.item.magic.BoneLauncher;
import com.gmail.thelilchicken01.tff.item.magic.BrittleBranch;
import com.gmail.thelilchicken01.tff.item.magic.FrozenPendant;
import com.gmail.thelilchicken01.tff.item.magic.MeteorWand;
import com.gmail.thelilchicken01.tff.item.magic.ShieldBook;
import com.gmail.thelilchicken01.tff.item.magic.VolatileNecklace;
import com.gmail.thelilchicken01.tff.item.melee.AncientGreatsword;
import com.gmail.thelilchicken01.tff.item.melee.BoneScythe;
import com.gmail.thelilchicken01.tff.item.melee.ForgemasterHammer;
import com.gmail.thelilchicken01.tff.item.melee.GoopySword;
import com.gmail.thelilchicken01.tff.item.melee.LifeScythe;
import com.gmail.thelilchicken01.tff.item.misc.BugEggs;
import com.gmail.thelilchicken01.tff.item.misc.CatalystItem;
import com.gmail.thelilchicken01.tff.item.mixed.ShadowBow;
import com.gmail.thelilchicken01.tff.item.mixed.VolatileSword;
import com.gmail.thelilchicken01.tff.item.projectile.BoneShot;
import com.gmail.thelilchicken01.tff.item.projectile.BranchProjectile;
import com.gmail.thelilchicken01.tff.item.projectile.Meteor;
import com.gmail.thelilchicken01.tff.item.tool.BonePickaxe;
import com.gmail.thelilchicken01.tff.item.tool.GoopyStick;
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
	
	/*
	 * 
	 * Volatile Items
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
	
	//Explosive Powder
	public static final RegistryObject<Item> EXPLOSIVE_POWDER = register("explosive_powder",
			() -> new ExplosivePowder(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	// Volatile Sword
	public static final RegistryObject<Item> VOLATILE_SWORD = register("volatile_sword", 
			() -> new VolatileSword(ModTiers.VOLATILE, -3, -2.2f, new Properties().tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Reetle Items
	 * 
	 */
	
	//Reetle Shell
	public static final RegistryObject<Item> REETLE_SHELL = register("reetle_shell", 
			() -> new ReetleShellPlates());
	
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
	
	//Bug Eggs
	public static final RegistryObject<BugEggs> BUG_EGGS = register("bug_eggs",
			() -> new BugEggs(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	//Pocket Sand
	public static final RegistryObject<Item> POCKET_SAND = register("pocket_sand",
			() -> new PocketSand(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Mechanical Items
	 * 
	 */
	
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
	
	//Shield Book
	public static final RegistryObject<Item> SHIELD_BOOK = register("shield_book", 
			() -> new ShieldBook());
	
	//Meteor Wand
	public static final RegistryObject<Item> METEOR_WAND = register("meteor_wand",
			() -> new MeteorWand(new Properties().tab(TheFesterForest.TFF_TAB).durability(875), 
					0.1).repair(() -> Ingredient.of(Items.IRON_INGOT)));
	
	//Forgemaster Hammer
	public static final RegistryObject<Item> FORGEMASTER_HAMMER = register("forgemaster_hammer", 
			() -> new ForgemasterHammer(ModTiers.MECHANICAL, 14, -3.5f, new Properties().tab(TheFesterForest.TFF_TAB)
					.durability(3200)));
	
	/*
	 * 
	 * Goopy Items
	 * 
	 */
	
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
	
	//Goopy Paxel
	public static final RegistryObject<Item> GOOPY_STICK = register("goopy_stick",
			() -> new GoopyStick());
	
	//Goopy Sword
	public static final RegistryObject<Item> GOOPY_SWORD = register("goopy_sword", 
			() -> new GoopySword(ModTiers.GOOP, 0, -2.4f, new Properties().tab(TheFesterForest.TFF_TAB).durability(1678)));
	
	//Rotting Slimeball
	public static final RegistryObject<Item> ROTTING_SLIMEBALL = register("rotting_slimeball", 
			() -> new Item(new Properties().tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Banshee Items
	 * 
	 */
	
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
	
	//Shadow Bow
	public static final RegistryObject<Item> SHADOW_BOW = register("shadow_bow", 
			() -> new ShadowBow());
	
	//Ancient Whistle
	public static final RegistryObject<Item> ANCIENT_WHISTLE = register("ancient_whistle",
			() -> new AncientWhistle(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	//Angelic Whistle
	public static final RegistryObject<Item> ANGELIC_WHISTLE = register("angelic_whistle",
			() -> new AngelicWhistle(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Wight Items
	 * 
	 */
	
	//Bone Launcher
	public static final RegistryObject<Item> BONE_LAUNCHER = register("bone_launcher",
			() -> new BoneLauncher(new Properties().tab(TheFesterForest.TFF_TAB).durability(340), 
					0.1).repair(() -> Ingredient.of(Items.BONE_BLOCK)));
	
	//Bone Scythe
	public static final RegistryObject<Item> BONE_SCYTHE = register("bone_scythe", 
			() -> new BoneScythe(ModTiers.BONE, 4, -3.0f, new Properties().tab(TheFesterForest.TFF_TAB)
					.durability(561)));
	
	//Flesh Ball
	public static final RegistryObject<Item> FLESH_BALL = register("flesh_ball", 
			() -> new FleshBall(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB)));
	
	//Life Scythe
	public static final RegistryObject<Item> LIFE_SCYTHE = register("life_scythe", 
			() -> new LifeScythe(ModTiers.BONE, 2, -3.2f, new Properties().tab(TheFesterForest.TFF_TAB)
					.durability(1400)));
	
	//Bone Pickaxe
	public static final RegistryObject<Item> BONE_PICKAXE = register("bone_pickaxe",
			() -> new BonePickaxe());
	
	/*
	 * 
	 * Rotting Skeleton Items
	 * 
	 */
	
	//Brittle Branch
	public static final RegistryObject<Item> BRITTLE_BRANCH = register("brittle_branch",
			() -> new BrittleBranch(new Properties().tab(TheFesterForest.TFF_TAB).durability(144), 
					0.1).repair(() -> Ingredient.of(Items.DEAD_BUSH)));
	
	//Ancient Greatsword
	public static final RegistryObject<Item> ANCIENT_GREATSWORD = register("ancient_greatsword", 
			() -> new AncientGreatsword(ModTiers.METAL, -1, -3f, new Properties().tab(TheFesterForest.TFF_TAB).durability(550)));
	
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
	
	//Volatile Apple
	public static final RegistryObject<Item> VOLATILE_APPLE = register("volatile_apple", 
			() -> new VolatileApple(new Properties().tab(TheFesterForest.TFF_TAB).food(new FoodProperties.Builder().alwaysEat().saturationMod(0.3f).nutrition(4)
					.effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1f).build())));
	
	//Mechanical Apple
	public static final RegistryObject<Item> MECHANICAL_APPLE = register("mechanical_apple", 
			() -> new MechanicalApple(new Properties().tab(TheFesterForest.TFF_TAB).food(new FoodProperties.Builder().alwaysEat().saturationMod(0.3f).nutrition(4)
					.effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2880, 2), 1f).build())));
	
	/*
	 * 
	 * Misc Items
	 * 
	 */
	
	//Purifying Powder
	public static final RegistryObject<Item> PURIFYING_POWDER = register("purifying_powder",
			() -> new PurifyingPowder(new Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rotting Brick
	public static final RegistryObject<Item> ROTTING_BRICK = register("rotting_brick", 
			() -> new Item(new Properties().tab(TheFesterForest.TFF_TAB)));
		
	//Rotting Catalyst
	public static final RegistryObject<Item> ROTTING_CATALYST = register("rotting_catalyst", CatalystItem::new);
	
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
	
	/*
	 * 
	 * Registry
	 * 
	 */
	
	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
		return ITEMS.register(name, item);
	}
	
}
