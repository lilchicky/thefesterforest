package com.gmail.thelilchicken01.tff.dataGen.client.lang;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.effect.ModEffects;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnUsProvider extends LanguageProvider {

	public ModEnUsProvider(DataGenerator gen) {
		super(gen, TheFesterForest.MODID, "en_us");
	}

	// §
	
	@Override
	protected void addTranslations() {
		//Damage Sources
		add("death.attack.tff_banshee", "%1$s blew a whistle a little too hard");
		add("death.attack.tff_banshee.player", "%1$s had their eardrums ruptured by %2$s");
		add("death.attack.tff_volatile_ghost", "%1$s played with explosives a little too much");
		add("death.attack.tff_volatile_ghost.player", "%1$s was obliterated by %2$s");
		add("death.attack.tff_sand_damage", "%1$s got too much sand in their eyes");
		add("death.attack.tff_sand_damage.player", "%2$s aggressively threw sand at %1$s");
		add("death.attack.tff_bone_damage", "%1$s got boned");
		add("death.attack.tff_bone_damage.player", "%1$s was knocked out with a bone by %2$s");
		add("death.attack.tff_branch_damage", "%1$s was strangled by branches");
		add("death.attack.tff_branch_damage.player", "%2$s strangled %1$s to death with branches");
		add("death.attack.tff_knockup_damage", "%1$s was slammed into the ground");
		add("death.attack.tff_knockup_damage.player", "%1$s was slammed into the ground by %2$s");
		add("death.attack.tff_meteor_damage", "%1$s was crushed by a flying meteor");
		add("death.attack.tff_meteor_damage.player", "%1$s was crushed by a flying meteor from %2$s");
		add("death.attack.tff_volatile_sword", "%1$s burned up by a flaming blade");
		add("death.attack.tff_volatile_sword.player", "%1$s incinerated %2$s with a fiery blade");
		add("death.attack.tff_goop_acid", "%1$s was melted into a slimy puddle");
		add("death.attack.tff_goop_acid.player", "%1$s turned %2$s into a puddle of slime");
		//Other
		add(BlockInit.ROTTING_BRICKS.get(), "Fester Bricks");
		add(ItemInit.ROTTING_BRICK.get(), "Fester Brick");
		add(ItemInit.ROTTING_CATALYST.get(), "Rotting Mound");
		add(ItemInit.VOLATILE_SWORD.get(), "Volatile Sword");
		add(ItemInit.ANCIENT_GREATSWORD.get(), "Ancient Greatsword");
		add(BlockInit.REETLELIGHT.get(), "Reetlelight");
		add(ItemInit.VOLATILE_APPLE.get(), "Volatile Apple");
		add(BlockInit.ROTTING_WOOD.get(), "Rottingwood");
		add(BlockInit.ROTTING_LOG.get(), "Rottingwood Log");
		add(BlockInit.STRIPPED_ROTTING_WOOD.get(), "Stripped Rottingwood");
		add(BlockInit.STRIPPED_ROTTING_LOG.get(), "Stripped Rottingwood Log");
		add(BlockInit.ROTTING_LEAVES.get(), "Rottingwood Leaves");
		add(BlockInit.ROTTING_PLANKS.get(), "Rottingwood Planks");
		add(BlockInit.ROTTINGWOOD_SAPLING.get(), "Rottingwood Sapling");
		add(BlockInit.ROTTING_DIRT.get(), "Rotting Dirt");
		add(BlockInit.ROTTING_FLOWER.get(), "Rotting Rose");
		add(BlockInit.ROTTING_FLOWER_POT.get(), "Rotting Flower Pot");
		add(BlockInit.ROTTING_STONE.get(), "Rotting Stone");
		add(BlockInit.ROTTING_GRASS.get(), "Rotting Grass Block");
		add(BlockInit.FESTER_ORE.get(), "Fester Ore");
		add(BlockInit.ROTTING_TALL_GRASS.get(), "Rotting Tallgrass");
		add(BlockInit.SLIMY_LOG.get(), "Slimy Log");
		add(BlockInit.SLIMY_LEAVES.get(), "Slimy Leaves");
		add(BlockInit.SLIMY_SAPLING.get(), "Slimy Sapling");
		add(BlockInit.SLIMY_PLANKS.get(), "Slimy Planks");
		add(BlockInit.SLIMY_WOOD.get(), "Slimy Wood");
		add(BlockInit.STRIPPED_SLIMY_LOG.get(), "Stripped Slimy Log");
		add(BlockInit.STRIPPED_SLIMY_WOOD.get(), "Stripped Slimy Wood");
		add(ItemInit.ROTTING_SKELETON_SPAWN_EGG.get(), "Rotting Skeleton Spawn Egg");
		add(ItemInit.BUG_CARCASS.get(), "Reetle Carcass");
		add(ModEntityTypes.CRUNCH_BEETLE.get(), "Reetle");
		add(ModEntityTypes.ROTTING_SKELETON.get(), "Rotting Skeleton");
		add(ItemInit.CRUNCH_BEETLE_SPAWN_EGG.get(), "Reetle Spawn Egg");
		add(ItemInit.BANSHEE_SPAWN_EGG.get(), "Banshee Spawn Egg");
		add(ModEntityTypes.BANSHEE.get(), "Banshee");
		add(ItemInit.ANCIENT_WHISTLE.get(), "Ancient Whistle");
		add(BlockInit.CRACKED_ROTTING_STONE.get(), "Cracked Rotting Stone");
		add(BlockInit.MOSSY_ROTTING_STONE.get(), "Mossy Rotting Stone");
		add(BlockInit.ROTTING_STONE_BRICKS.get(), "Rotting Stone Bricks");
		add(ItemInit.DUAL_WIELD_SWORD.get(), "Dual Wield Sword");
		add(ItemInit.VOLATILE_GHOST_SPAWN_EGG.get(), "Volatile Ghost Spawn Egg");
		add(ModEntityTypes.VOLATILE_GHOST.get(), "Volatile Ghost");
		add(ItemInit.VOLATILE_NECKLACE.get(), "Volatile Necklace");
		add(ItemInit.FROZEN_PENDANT.get(), "Frozen Pendant");
		add(ItemInit.POCKET_SAND.get(), "Pocket Sand");
		add(ItemInit.FLESH_BALL.get(), "Ball of Flesh");
		add(ItemInit.LIFE_SCYTHE.get(), "Scythe of Draining");
		add(ItemInit.WIGHT_SPAWN_EGG.get(), "Wight Spawn Egg");
		add(ModEntityTypes.WIGHT.get(), "Wight");
		add(ItemInit.BONE_SCYTHE.get(), "Bone Scythe");
		add(ItemInit.EXPLOSIVE_POWDER.get(), "Explosive Powder");
		add(ItemInit.ANGELIC_WHISTLE.get(), "Angelic Whistle");
		add(ItemInit.BRITTLE_BRANCH.get(), "Brittle Branch");
		add(ItemInit.BRANCH_CHARGE.get(), "Branch Charge");
		add(ItemInit.BONE_CHARGE.get(), "Bone Charge");
		add(ItemInit.BONE_LAUNCHER.get(), "Bone Launcher");
		add(BlockInit.ROTTING_STONE_BRICK_SLAB.get(), "Rotting Stone Brick Slab");
		add(BlockInit.ROTTING_STONE_BRICK_STAIRS.get(), "Rotting Stone Brick Stairs");
		add(BlockInit.ROTTING_STONE_BRICK_WALL.get(), "Rotting Stone Brick Wall");
		add(BlockInit.ROTTINGWOOD_FENCE.get(), "Rottingwood Fence");
		add(BlockInit.ROTTINGWOOD_FENCE_GATE.get(), "Rottingwood Fence Gate");
		add(BlockInit.ROTTINGWOOD_STAIRS.get(), "Rottingwood Stairs");
		add(BlockInit.ROTTINGWOOD_SLAB.get(), "Rottingwood Slab");
		add(BlockInit.SLIMY_STAIRS.get(), "Slimy Stairs");
		add(BlockInit.SLIMY_SLAB.get(), "Slimy Slab");
		add(BlockInit.SLIMY_FENCE.get(), "Slimy Fence");
		add(BlockInit.SLIMY_FENCE_GATE.get(), "Slimy Fence Gate");
		add(ModEntityTypes.FORGEMASTER.get(), "The Forgemaster");
		add(ItemInit.FORGEMASTER_SPAWN_EGG.get(), "Forgemaster Spawn Egg");
		add(ItemInit.FORGEMASTER_HEART.get(), "Forgemaster's Heart");
		add(ItemInit.HEAVY_STONE.get(), "Heavy Stone");
		add(ItemInit.PURIFYING_POWDER.get(), "Purifying Powder");
		add(ItemInit.BUG_EGGS.get(), "Reetle Eggs");
		add(ModEntityTypes.PLAYER_CRUNCH_BEETLE.get(), "Friendly Reetle");
		add(ModEntityTypes.PYLON.get(), "Forgemaster's Pylon");
		add(ItemInit.PYLON_SPAWN_EGG.get(), "Forgemaster's Pylon Spawn Egg");
		add(ItemInit.FORGEMASTER_HAMMER.get(), "§7§lForgemaster's Hammer");
		add(ItemInit.MECHANICAL_EYE.get(), "Mechanical Eye");
		add(ItemInit.METEOR_WAND.get(), "Meteor Wand");
		add(ItemInit.MECHANICAL_BOOTS.get(), "Mechanical Boots");
		add(ItemInit.MECHANICAL_CHESTPLATE.get(), "Mechanical Chestplate");
		add(ItemInit.MECHANICAL_LEGGINGS.get(), "Mechanical Leggings");
		add(ItemInit.MECHANICAL_HELMET.get(), "Mechanical Helmet");
		add(ItemInit.MECHANICAL_APPLE.get(), "Mechanical Apple");
		add(ItemInit.SHIELD_BOOK.get(), "Shield Book");
		add(ItemInit.BONE_PICKAXE.get(), "Bone Pickaxe");
		add(ItemInit.SHADOW_BOW.get(), "Shadow Bow");
		add(ItemInit.VOLATILE_BOOTS.get(), "Volatile Boots");
		add(ItemInit.VOLATILE_CHESTPLATE.get(), "Volatile Chestplate");
		add(ItemInit.VOLATILE_LEGGINGS.get(), "Volatile Leggings");
		add(ItemInit.VOLATILE_HELMET.get(), "Volatile Helmet");
		add(ModEntityTypes.REETLE_QUEEN.get(), "Reetle Queen");
		add(ItemInit.REETLE_QUEEN_SPAWN_EGG.get(), "Reetle Queen Spawn Egg");
		add(ItemInit.REETLE_REAGENTS.get(), "Reetle Reagents");
		add(ItemInit.REETLE_ELYTRA.get(), "Reetle Shell Elytra");
		add(ItemInit.REETLE_HELMET.get(), "Reetle Helmet");
		add(ItemInit.REETLE_CHESTPLATE.get(), "Reetle Chestplate");
		add(ItemInit.REETLE_LEGGINGS.get(), "Reetle Leggings");
		add(ItemInit.REETLE_BOOTS.get(), "Reetle Boots");
		add(ItemInit.REETLE_SHELL.get(), "Reetle Shell Plates");
		add(ItemInit.REETLE_QUEEN_ANTENNAE.get(), "Reetle Queen Antennae");
		add(ItemInit.ROTTING_CARROT.get(), "Rotting Carrot");
		add(ItemInit.ROTTING_PIE.get(), "Rotting Pie");
		add(ItemInit.GRAVITY_GAUNTLET.get(), "Gravity Gauntlet");
		add(ItemInit.MOON_SHOES.get(), "Moon Shoes");
		add(ItemInit.BANSHEE_HELMET.get(), "Banshee Helmet");
		add(ItemInit.BANSHEE_CHESTPLATE.get(), "Banshee Chestplate");
		add(ItemInit.BANSHEE_LEGGINGS.get(), "Banshee Leggings");
		add(ItemInit.BANSHEE_BOOTS.get(), "Banshee Boots");
		add(BlockInit.CRUSHED_ROTTING_STONE.get(), "Crushed Rotting Stone");
		add(BlockInit.ROTTING_GRAVE_DIRT.get(), "Rotting Grave Dirt");
		add(ItemInit.GOOP_SPAWN_EGG.get(), "Rotting Goop Spawn Egg");
		add(ModEntityTypes.GOOP.get(), "Rotting Goop");
		add(ModEntityTypes.GOOP_MEDIUM.get(), "Condensed Rotting Goop");
		add(ModEntityTypes.GOOP_SMALL.get(), "Miniscule Rotting Goop");
		add(ItemInit.GOOPY_JELLO.get(), "Goopy Jello");
		add(ItemInit.GOOPY_STICK.get(), "Goopy Stick");
		add(ItemInit.SLIPPERY_GOOP.get(), "Slippery Goop");
		add(BlockInit.SICKENING_FLOWER.get(), "Sickening Amaranth");
		add(BlockInit.SLIMY_FLOWER.get(), "Slimy Bell");
		add(ModEffects.GOOP_ACID.get(), "Acidic Goop");
		add(ItemInit.GOOPY_SWORD.get(), "Goopy Sword");
		add("curios.modifiers.feet", "When on feet:");
		add("curios.identifier.feet", "Feet");
		add("itemGroup.tff", "The Fester Forest");
		
	}

}
