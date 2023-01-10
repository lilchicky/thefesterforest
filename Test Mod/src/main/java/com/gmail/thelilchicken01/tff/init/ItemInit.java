package com.gmail.thelilchicken01.tff.init;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.item.AncientGreatsword;
import com.gmail.thelilchicken01.tff.item.AncientWhistle;
import com.gmail.thelilchicken01.tff.item.AngelicWhistle;
import com.gmail.thelilchicken01.tff.item.BoneLauncher;
import com.gmail.thelilchicken01.tff.item.BonePickaxe;
import com.gmail.thelilchicken01.tff.item.BoneScythe;
import com.gmail.thelilchicken01.tff.item.BrittleBranch;
import com.gmail.thelilchicken01.tff.item.BugCarcass;
import com.gmail.thelilchicken01.tff.item.BugEggs;
import com.gmail.thelilchicken01.tff.item.CatalystItem;
import com.gmail.thelilchicken01.tff.item.DualWieldSword;
import com.gmail.thelilchicken01.tff.item.ExplosivePowder;
import com.gmail.thelilchicken01.tff.item.FleshBall;
import com.gmail.thelilchicken01.tff.item.ForgemasterHammer;
import com.gmail.thelilchicken01.tff.item.ForgemasterHeart;
import com.gmail.thelilchicken01.tff.item.FrozenPendant;
import com.gmail.thelilchicken01.tff.item.GravityGauntlets;
import com.gmail.thelilchicken01.tff.item.HeavyStone;
import com.gmail.thelilchicken01.tff.item.LifeScythe;
import com.gmail.thelilchicken01.tff.item.MechanicalApple;
import com.gmail.thelilchicken01.tff.item.MechanicalBoots;
import com.gmail.thelilchicken01.tff.item.MechanicalChestplate;
import com.gmail.thelilchicken01.tff.item.MechanicalEye;
import com.gmail.thelilchicken01.tff.item.MechanicalHelmet;
import com.gmail.thelilchicken01.tff.item.MechanicalLeggings;
import com.gmail.thelilchicken01.tff.item.MeteorWand;
import com.gmail.thelilchicken01.tff.item.PocketSand;
import com.gmail.thelilchicken01.tff.item.PurifyingPowder;
import com.gmail.thelilchicken01.tff.item.ReetleBoots;
import com.gmail.thelilchicken01.tff.item.ReetleChestplate;
import com.gmail.thelilchicken01.tff.item.ReetleElytra;
import com.gmail.thelilchicken01.tff.item.ReetleHelmet;
import com.gmail.thelilchicken01.tff.item.ReetleLeggings;
import com.gmail.thelilchicken01.tff.item.ReetleQueenAntennae;
import com.gmail.thelilchicken01.tff.item.ReetleReagents;
import com.gmail.thelilchicken01.tff.item.ReetleShellPlates;
import com.gmail.thelilchicken01.tff.item.RottingCarrot;
import com.gmail.thelilchicken01.tff.item.RottingPie;
import com.gmail.thelilchicken01.tff.item.ShadowBow;
import com.gmail.thelilchicken01.tff.item.ShieldBook;
import com.gmail.thelilchicken01.tff.item.VolatileApple;
import com.gmail.thelilchicken01.tff.item.VolatileBoots;
import com.gmail.thelilchicken01.tff.item.VolatileChestplate;
import com.gmail.thelilchicken01.tff.item.VolatileHelmet;
import com.gmail.thelilchicken01.tff.item.VolatileLeggings;
import com.gmail.thelilchicken01.tff.item.VolatileNecklace;
import com.gmail.thelilchicken01.tff.item.VolatileSword;
import com.gmail.thelilchicken01.tff.item.projectile.BoneShot;
import com.gmail.thelilchicken01.tff.item.projectile.BranchProjectile;
import com.gmail.thelilchicken01.tff.item.projectile.Meteor;
import com.gmail.thelilchicken01.tff.item.tiers.ModTiers;
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
	
	public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, TheFesterForest.modid);
	
	/*
	 * For swords - VolatileTier base is 4. Iron Sword base is 1.6. -2.4 will give base sword, larger subtracted number
	 * will give slower speed, smaller subtracted number will give higher attack speed.
	 */
	
	// Volatile Sword
	public static final RegistryObject<Item> volatileSword = register("volatile_sword", 
			() -> new VolatileSword(ModTiers.VOLATILE, -3, -2.2f, new Properties().tab(TheFesterForest.tff_tab)));
	
	//Ancient Greatsword
	public static final RegistryObject<Item> ancientGreatsword = register("ancient_greatsword", 
			() -> new AncientGreatsword(ModTiers.VOLATILE, -1, -3f, new Properties().tab(TheFesterForest.tff_tab).durability(550)));
	
	//Bone Pickaxe
	public static final RegistryObject<Item> bone_pickaxe = register("bone_pickaxe",
			() -> new BonePickaxe());
	
	//Volatile Apple
	public static final RegistryObject<Item> volatileApple = register("volatile_apple", 
			() -> new VolatileApple(new Properties().tab(TheFesterForest.tff_tab).food(new FoodProperties.Builder().alwaysEat().saturationMod(0.3f).nutrition(4)
					.effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1f).build())));
	
	//Mechanical Apple
	public static final RegistryObject<Item> mechanical_apple = register("mechanical_apple", 
			() -> new MechanicalApple(new Properties().tab(TheFesterForest.tff_tab).food(new FoodProperties.Builder().alwaysEat().saturationMod(0.3f).nutrition(4)
					.effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2880, 2), 1f).build())));
	
	//Bug Carcass
	public static final RegistryObject<Item> bug_carcass = register("bug_carcass", 
			() -> new BugCarcass());
	
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
	
	//Reetle Queen Spawn Egg
	public static final RegistryObject<Item> reetle_queen_spawn_egg = items.register("reetle_queen_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.reetle_queen, 0xc46543, 0x35cd28, //Background, Spots
					new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Volatile Ghost Spawn Egg
	public static final RegistryObject<Item> volatile_ghost_spawn_egg = items.register("volatile_ghost_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.volatile_ghost, 0xff4000, 0xff2600, //Background, Spots
					new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Wight Spawn Egg
	public static final RegistryObject<Item> wight_spawn_egg = items.register("wight_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.wight, 0xfc034e, 0xfc0303, //Background, Spots
					new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Forgemaster Spawn Egg
	public static final RegistryObject<Item> forgemaster_spawn_egg = items.register("forgemaster_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.forgemaster, 0x616161, 0x404040, //Background, Spots
					new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Forgemasters Pylon Spawn Egg
	public static final RegistryObject<Item> pylon_spawn_egg = items.register("pylon_spawn_egg", 
			() -> new ForgeSpawnEggItem(ModEntityTypes.pylon, 0x404040, 0xf02213, //Background, Spots
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
	
	//Mechanical Eye
	public static final RegistryObject<Item> mechanical_eye = items.register("mechanical_eye",
			() -> new MechanicalEye(new Properties().stacksTo(1).tab(TheFesterForest.tff_tab)));
	
	//Purifying Powder
	public static final RegistryObject<Item> purifying_powder = items.register("purifying_powder",
			() -> new PurifyingPowder(new Properties().tab(TheFesterForest.tff_tab)));
	
	//Heavy Stone
	public static final RegistryObject<Item> heavy_stone = items.register("heavy_stone",
			() -> new HeavyStone(new Properties().stacksTo(1).tab(TheFesterForest.tff_tab)));
	
	//Forgemaster's Heart
	public static final RegistryObject<Item> forgemaster_heart = items.register("forgemaster_heart",
			() -> new ForgemasterHeart(new Properties().stacksTo(1).tab(TheFesterForest.tff_tab)));
	
	//Pocket Sand
	public static final RegistryObject<Item> pocket_sand = items.register("pocket_sand",
			() -> new PocketSand(new Properties().stacksTo(1).tab(TheFesterForest.tff_tab)));
	
	//Flesh Ball
	public static final RegistryObject<Item> flesh_ball = items.register("flesh_ball", 
			() -> new FleshBall(new Properties().stacksTo(1).tab(TheFesterForest.tff_tab)));
	
	//Life Scythe
	public static final RegistryObject<Item> life_scythe = register("life_scythe", 
			() -> new LifeScythe(ModTiers.BONE, 2, -3.2f, new Properties().tab(TheFesterForest.tff_tab)
					.durability(1400)));
	
	//Forgemaster Hammer
	public static final RegistryObject<Item> forgemaster_hammer = register("forgemaster_hammer", 
			() -> new ForgemasterHammer(ModTiers.MECHANICAL, 14, -3.5f, new Properties().tab(TheFesterForest.tff_tab)
					.durability(3200)));
		
	//Bone Scythe
	public static final RegistryObject<Item> bone_scythe = register("bone_scythe", 
			() -> new BoneScythe(ModTiers.BONE, 1, -3.0f, new Properties().tab(TheFesterForest.tff_tab)
					.durability(561)));
	
	//Explosive Powder
	public static final RegistryObject<Item> explosive_powder = register("explosive_powder",
			() -> new ExplosivePowder(new Properties().stacksTo(1).tab(TheFesterForest.tff_tab)));
	
	//Angelic Whistle
	public static final RegistryObject<Item> angelic_whistle = items.register("angelic_whistle",
			() -> new AngelicWhistle(new Properties().stacksTo(1).tab(TheFesterForest.tff_tab)));
	
	//Brittle Branch
	public static final RegistryObject<Item> brittle_branch = items.register("brittle_branch",
			() -> new BrittleBranch(new Properties().tab(TheFesterForest.tff_tab).durability(144), 
					0.1).repair(() -> Ingredient.of(Items.DEAD_BUSH)));
	
	//Meteor Wand
	public static final RegistryObject<Item> meteor_wand = items.register("meteor_wand",
			() -> new MeteorWand(new Properties().tab(TheFesterForest.tff_tab).durability(875), 
					0.1).repair(() -> Ingredient.of(Items.IRON_INGOT)));
	
	//Branch Charge
	public static final RegistryObject<BranchProjectile> branch_charge = items.register("branch_charge",
			() -> new BranchProjectile(new Properties().stacksTo(1), 10));// damage of bullet
	
	//Bone Charge
	public static final RegistryObject<BoneShot> bone_charge = items.register("bone_charge",
			() -> new BoneShot(new Properties().stacksTo(1), 2));// damage of bullet
	
	//Meteor
	public static final RegistryObject<Meteor> meteor_charge = items.register("meteor_charge",
			() -> new Meteor(new Properties().stacksTo(1), 15));
	
	//Bug Eggs
	public static final RegistryObject<BugEggs> bug_eggs = items.register("bug_eggs",
			() -> new BugEggs(new Properties().stacksTo(1).tab(TheFesterForest.tff_tab)));
	
	//Bone Launcher
	public static final RegistryObject<Item> bone_launcher = items.register("bone_launcher",
			() -> new BoneLauncher(new Properties().tab(TheFesterForest.tff_tab).durability(340), 
					0.1).repair(() -> Ingredient.of(Items.BONE_BLOCK)));
	
	//Mechanical Helmet
	public static final RegistryObject<Item> mechanical_helmet = items.register("mechanical_helmet", 
			() -> new MechanicalHelmet());
	
	//Mechanical Chestplate
	public static final RegistryObject<Item> mechanical_chestplate = items.register("mechanical_chestplate", 
			() -> new MechanicalChestplate());
		
	//Mechanical Leggings
	public static final RegistryObject<Item> mechanical_leggings = items.register("mechanical_leggings", 
			() -> new MechanicalLeggings());
		
	//Mechanical Boots
	public static final RegistryObject<Item> mechanical_boots = items.register("mechanical_boots", 
			() -> new MechanicalBoots());
	
	//Volatile Helmet
	public static final RegistryObject<Item> volatile_helmet = items.register("volatile_helmet", 
			() -> new VolatileHelmet());
		
	//Volatile Chestplate
	public static final RegistryObject<Item> volatile_chestplate = items.register("volatile_chestplate", 
			() -> new VolatileChestplate());
			
	//Volatile Leggings
	public static final RegistryObject<Item> volatile_leggings = items.register("volatile_leggings", 
			() -> new VolatileLeggings());
		
	//Volatile Boots
	public static final RegistryObject<Item> volatile_boots = items.register("volatile_boots", 
			() -> new VolatileBoots());
	
	//Shield Book
	public static final RegistryObject<Item> shield_book = items.register("shield_book", 
			() -> new ShieldBook());
	
	//Shadow Bow
	public static final RegistryObject<Item> shadow_bow = items.register("shadow_bow", 
			() -> new ShadowBow());
	
	//Bag of Reetle Legs
	public static final RegistryObject<Item> reetle_reagents = items.register("reetle_reagents", 
			() -> new ReetleReagents());
	
	//Reetle Elytra
	public static final RegistryObject<Item> reetle_elytra = items.register("reetle_elytra", 
			() -> new ReetleElytra());
	
	//Reetle Helmet
	public static final RegistryObject<Item> reetle_helmet = items.register("reetle_helmet", 
			() -> new ReetleHelmet());
			
	//Reetle Chestplate
	public static final RegistryObject<Item> reetle_chestplate = items.register("reetle_chestplate", 
			() -> new ReetleChestplate());
				
	//Reetle Leggings
	public static final RegistryObject<Item> reetle_leggings = items.register("reetle_leggings", 
			() -> new ReetleLeggings());
			
	//Reetle Boots
	public static final RegistryObject<Item> reetle_boots = items.register("reetle_boots", 
			() -> new ReetleBoots());
	
	//Reetle Shell
	public static final RegistryObject<Item> reetle_shell = items.register("reetle_shell", 
			() -> new ReetleShellPlates());
	
	//Reetle Queen Antennae
	public static final RegistryObject<Item> reetle_queen_antennae = items.register("reetle_queen_antennae", 
			() -> new ReetleQueenAntennae());
	//Rotting Carrot
	public static final RegistryObject<Item> rotting_carrot = items.register("rotting_carrot", 
			() -> new RottingCarrot());
	
	//Rotting Pie
	public static final RegistryObject<Item> rotting_pie = items.register("rotting_pie", 
			() -> new RottingPie());
	
	//Gravity Gauntlet
	public static final RegistryObject<Item> gravity_gauntlet = items.register("gravity_gauntlet", 
			() -> new GravityGauntlets());
	
	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
		return items.register(name, item);
	}
	
}
