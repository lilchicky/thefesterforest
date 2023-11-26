package com.gmail.thelilchicken01.tff.dataGen.client.lang;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.effect.ModEffects;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.ChatFormatting;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.TextComponent;
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
		add("death.attack.tff_goop_acid.player", "%2$s turned %1$s into a puddle of slime");
		add("death.attack.tff_banshee_mob", "%1$s had their eardrums ruptured by %2$s");
		add("death.attack.tff_banshee_mob.player", "%1$s had their eardrums ruptured by %2$s");
		add("death.attack.tff_electric_charge", "%1$s met a shocking end");
		add("death.attack.tff_frozen_damage", "%1$s was pierced by an icicle");
		add("death.attack.tff_frozen_damage.player", "%2$s was pierced by an icicle from %1$s");
		add("death.attack.tff_frozen_rock_damage", "%1$s was smashed by a large chunk of ice");
		add("death.attack.tff_frozen_rock_damage.player", "%2$s crushed %1$s with a large chunk of ice");
		add("death.attack.tff_frost_bolt_damage", "%1$s needed to chill out");
		add("death.attack.tff_frost_bolt_damage.player", "%2$s forced %1$s to chill out");
		add("death.attack.tff_rotting_bolt_damage", "%1$s rotted away into nothingness");
		add("death.attack.tff_rotting_bolt_damage.player", "%1$s was rotted away by %2$s");
		add("death.attack.tff_frostbitten_bolt_damage", "%1$s was frozen by the Frostbitten King");
		add("death.attack.tff_frostbitten_bolt_damage.player", "%1$s was frozen by %2$s");
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
		add(ItemInit.FROZEN_PENDANT.get(), "Frozen Charm");
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
		add(ItemInit.FORGEMASTER_HAMMER.get(), "Forgemaster's Hammer");
		add(ItemInit.MECHANICAL_EYE.get(), "Mechanical Eye");
		add(ItemInit.METEOR_WAND.get(), "Meteor Wand");
		add(ItemInit.MECHANICAL_BOOTS.get(), "Mechanical Boots");
		add(ItemInit.MECHANICAL_CHESTPLATE.get(), "Mechanical Chestplate");
		add(ItemInit.MECHANICAL_LEGGINGS.get(), "Mechanical Leggings");
		add(ItemInit.MECHANICAL_HELMET.get(), "Mechanical Helmet");
		add(ItemInit.MECHANICAL_APPLE.get(), "Mechanical Apple");
		add(ItemInit.SHIELD_BOOK.get(), "Book of Shielding");
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
		add(ItemInit.REETLE_SHELL.get(), "Reetle Shell Plate");
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
		add(ItemInit.GOOPY_HELMET.get(), "Goopy Head Glob");
		add(ItemInit.GOOPY_CHESTPLATE.get(), "Goopy Body Sludge");
		add(ItemInit.GOOPY_LEGGINGS.get(), "Goopy Pants");
		add(ItemInit.GOOPY_BOOTS.get(), "Goopy Shoes");
		add(ItemInit.ROTTING_SLIMEBALL.get(), "Rotting Slimeball");
		add(BlockInit.FESTER_BRICK_STAIRS.get(), "Fester Brick Stairs");
		add(BlockInit.FESTER_BRICK_SLAB.get(), "Fester Brick Slab");
		add(BlockInit.WEEPING_GRASS.get(), "Weeping Grass");
		add(BlockInit.SOUL_ROT.get(), "Soul Rot");
		add(BlockInit.TFF_PORTAL.get(), "The Fester Forest Portal");
		add(ItemInit.ROTFISH_SPAWN_EGG.get(), "Rotfish Spawn Egg");
		add(ModEntityTypes.ROTFISH.get(), "Rotfish");
		add(ItemInit.RAW_ROTFISH.get(), "Raw Rotfish");
		add(ItemInit.COOKED_ROTFISH.get(), "Cooked Rotfish");
		add(ItemInit.ROTFISH_FANG.get(), "Rotfish Fang");
		add(ItemInit.AMBECTRUM_SPAWN_EGG.get(), "Ambectrum Spawn Egg");
		add(ModEntityTypes.AMBECTRUM.get(), "Ambectrum");
		add(ItemInit.ELECTRIC_CHARGE.get(), "Electric Charge");
		add(ModEffects.PARALYSIS.get(), "Paralysis");
		add(ItemInit.SHOCK_SWORD.get(), "Shock Sword");
		add(ItemInit.SHOCK_NECKLACE.get(), "Shock Necklace");
		add(ItemInit.ELECTRIC_PICKAXE.get(), "Electric Pickaxe");
		add(ItemInit.AMBECTRUM_DONUT.get(), "Ambectrum Donut");
		add(ItemInit.AMBECTRUM_JELLY.get(), "Ambectrum Jelly");
		add(ItemInit.SEATHROWN_SKELETON_SPAWN_EGG.get(), "Seathrown Skeleton Spawn Egg");
		add(ModEntityTypes.SEATHROWN_SKELETON.get(), "Seathrown Skeleton");
		add(BlockInit.ROTTING_SAND.get(), "Rotting Sand");
		add(BlockInit.COMPRESSED_ROTTING_SAND.get(), "Compressed Rotting Sand");
		add(BlockInit.COMPRESSED_ROTTING_SAND_STAIRS.get(), "Compressed Rotting Sand Stairs");
		add(BlockInit.COMPRESSED_ROTTING_SAND_SLAB.get(), "Compressed Rotting Sand Slab");
		add(BlockInit.ROTTING_GLASS.get(), "Rotting Glass");
		add(BlockInit.COMPRESSED_ROTTING_SAND_WALL.get(), "Compressed Rotting Sand Wall");
		add(BlockInit.CRACKED_ROTTING_STONE_BRICKS.get(), "Cracked Rotting Stone Bricks");
		add(BlockInit.FESTER_BRICK_WALL.get(), "Fester Brick Wall");
		add(BlockInit.ROTTING_BONE_BLOCK.get(), "Rotting Bone Block");
		add(BlockInit.BLUE_FESTER_SHROOM_BLOCK.get(), "Blue Fester Shroom Block");
		add(BlockInit.ORANGE_FESTER_SHROOM_BLOCK.get(), "Orange Fester Shroom Block");
		add(BlockInit.FESTER_SHROOM_STEM.get(), "Fester Shroom Stem");
		add(ItemInit.CORRODED_SHROOM_SPAWN_EGG.get(), "Corroded Shroom Spawn Egg");
		add(ModEntityTypes.CORRODED_SHROOM.get(), "Corroded Shroom");
		add(BlockInit.CORRODED_SHROOM.get(), "Corroded Shroom");
		add(BlockInit.CORRODED_SHROOM_FLOWER_POT.get(), "Corroded Shroom Flower Pot");
		add(BlockInit.SICKENING_FLOWER_POT.get(), "Sickening Amaranth Flower Pot");
		add(BlockInit.CUBED_FUNGUS.get(), "Cubed Fungus");
		add(BlockInit.CUBED_FUNGUS_STAIRS.get(), "Cubed Fungus Stairs");
		add(BlockInit.CUBED_FUNGUS_SLAB.get(), "Cubed Fungus Slab");
		add(BlockInit.CUBED_FUNGUS_FENCE.get(), "Cubed Fungus Fence");
		add(BlockInit.CUBED_FUNGUS_GATE.get(), "Cubed Fungus Gate");
		add(ItemInit.FESTER_CHUNK.get(), "Festering Chunk");
		add(ItemInit.ENERGETIC_FUNGUS.get(), "Energetic Fungus");
		add(BlockInit.FUNGAL_GROWTH.get(), "Fungal Growth");
		add(BlockInit.TALL_FUNGAL_GROWTH.get(), "Tall Fungal Growth");
		add(ItemInit.SHROOM_BUCKET.get(), "Shroom Bucket");
		add(ItemInit.SHROOM_HAT.get(), "Shroom Hat");
		add(ItemInit.THICK_BONE.get(), "Thick Bone");
		add(ItemInit.SEATHROWN_PIKE.get(), "Seathrown Pike");
		add(ItemInit.ROTFISH_SPECIAL.get(), "Rotfish Special");
		add(ItemInit.SHROOM_CLUSTER.get(), "Shroom Cluster");
		add(ItemInit.COOKED_SHROOM_CLUSTER.get(), "Cooked Shroom Cluster");
		add(ItemInit.ROTFISH_BOOTS.get(), "Rotfish Scale Mail Boots");
		add(ItemInit.ROTFISH_CHESTPLATE.get(), "Rotfish Scale Mail Chestplate");
		add(ItemInit.ROTFISH_LEGGINGS.get(), "Rotfish Scale Mail Leggings");
		add(ItemInit.ROTFISH_HELMET.get(), "Rotfish Scale Mail Leggings");
		add(ModEntityTypes.DEEP_REAVER.get(), "Deep Reaver");
		add(ItemInit.DEEP_REAVER_SPAWN_EGG.get(), "Deep Reaver Spawn Egg");
		add(ItemInit.REAVER_BLADE.get(), "Reaver Blade");
		add(BlockInit.FROSTBITTEN_LEAVES.get(), "Frostbitten Leaves");
		add(BlockInit.FROSTBITTEN_SAPLING.get(), "Frostbitten Sapling");
		add(BlockInit.FROSTBITTEN_PLANKS.get(), "Frostbitten Planks");
		add(BlockInit.FROSTBITTEN_LOG.get(), "Frostbitten Log");
		add(BlockInit.STRIPPED_FROSTBITTEN_LOG.get(), "Stripped Frostbitten Log");
		add(BlockInit.FROSTBITTEN_WOOD.get(), "Frostbitten Wood");
		add(BlockInit.STRIPPED_FROSTBITTEN_WOOD.get(), "Stripped Frostbitten Wood");
		add(BlockInit.ICICLE.get(), "Icicle");
		add(BlockInit.FROSTVINE.get(), "Frostvine");
		add(BlockInit.FROSTVINE_FLOWER_POT.get(), "Frostvine Flower Pot");
		add(ItemInit.ANCIENT_DAGGER.get(), "Ancient Dagger");
		add(ItemInit.ANCIENT_LONGSWORD.get(), "Ancient Longsword");
		add(ItemInit.FESTERING_CLUB.get(), "Festering Club");
		add(ItemInit.ANCIENT_HATCHET.get(), "Ancient Hatchet");
		add(ItemInit.ANCIENT_HAMMER.get(), "Ancient Hammer");
		add(BlockInit.FROSTBITTEN_STAIRS.get(), "Frostbitten Stairs");
		add(BlockInit.FROSTBITTEN_SLAB.get(), "Frostbitten Slab");
		add(BlockInit.FROSTBITTEN_FENCE.get(), "Frostbitten Fence");
		add(BlockInit.FROSTBITTEN_FENCE_GATE.get(), "Frostbitten Fence Gate");
		add(ItemInit.SHARD_OF_ICE.get(), "Shard of Ice");
		add(ItemInit.FROST_MALLET.get(), "Frost Mallet");
		add(ItemInit.FROZEN_BOOTS.get(), "Frozen Boots");
		add(ItemInit.FROZEN_LEGGINGS.get(), "Frozen Leggings");
		add(ItemInit.FROZEN_CHESTPLATE.get(), "Frozen Chestplate");
		add(ItemInit.FROZEN_HELMET.get(), "Frozen Helmet");
		add(ItemInit.ICY_PAXEL.get(), "Icy Paxel");
		add(ItemInit.ICY_BOW.get(), "Icy Bow");
		add(BlockInit.ROTTINGWOOD_BUTTON.get(), "Rottingwood Button");
		add(BlockInit.ROTTINGWOOD_PRESSURE_PLATE.get(), "Rottingwood Pressure Plate");
		add(BlockInit.ROTTINGWOOD_DOOR.get(), "Rottingwood Door");
		add(BlockInit.ROTTINGWOOD_TRAPDOOR.get(), "Rottingwood Trapdoor");
		add(BlockInit.SLIMY_BUTTON.get(), "Slimy Button");
		add(BlockInit.SLIMY_PRESSURE_PLATE.get(), "Slimy Pressure Plate");
		add(BlockInit.SLIMY_DOOR.get(), "Slimy Door");
		add(BlockInit.SLIMY_TRAPDOOR.get(), "Slimy Trapdoor");
		add(BlockInit.FROSTBITTEN_BUTTON.get(), "Frostbitten Button");
		add(BlockInit.FROSTBITTEN_PRESSURE_PLATE.get(), "Frostbitten Pressure Plate");
		add(BlockInit.FROSTBITTEN_DOOR.get(), "Frostbitten Door");
		add(BlockInit.FROSTBITTEN_TRAPDOOR.get(), "Frostbitten Trapdoor");
		add(BlockInit.CUBED_FUNGUS_BUTTON.get(), "Cubed Fungus Button");
		add(BlockInit.CUBED_FUNGUS_PRESSURE_PLATE.get(), "Cubed Fungus Pressure Plate");
		add(BlockInit.CUBED_FUNGUS_DOOR.get(), "Cubed Fungus Door");
		add(BlockInit.CUBED_FUNGUS_TRAPDOOR.get(), "Cubed Fungus Trapdoor");
		add(BlockInit.ROTTING_STONE_BUTTON.get(), "Rotting Stone Button");
		add(BlockInit.ROTTING_STONE_PRESSURE_PLATE.get(), "Rotting Stone Pressure Plate");
		add(BlockInit.FESTER_BRICK_PRESSURE_PLATE.get(), "Player Only Pressure Plate");
		add(BlockInit.COMPRESSED_ROTTING_SAND_BUTTON.get(), "Compressed Rotting Sand Button");
		add(BlockInit.COMPRESSED_ROTTING_SAND_PRESSURE_PLATE.get(), "Compressed Rotting Sand Pressure Plate");
		add(ItemInit.FOOD_BOOK.get(), "Book of Gluttony");
		add(ItemInit.VERDANT_BRANCH.get(), "Verdant Branch");
		add(ItemInit.DULL_ORB.get(), "Dull Orb");
		add(ItemInit.FLAME_ORB.get(), "Orb of Embers");
		add(ItemInit.LEVITATE_ORB.get(), "Orb of Weightlessness");
		add(ItemInit.POISON_ORB.get(), "Orb of Toxins");
		add(ItemInit.FROZEN_ORB.get(), "Orb of Frost");
		add(ItemInit.LIFE_ORB.get(), "Orb of Life");
		add(ItemInit.WITHER_ORB.get(), "Orb of Decay");
		add(ItemInit.REETLE_ORB.get(), "Orb of Skittering");
		add(ItemInit.FORGEMASTER_ORB.get(), "Orb of the Machine");
		add(ItemInit.GLOWING_BOOK.get(), "Book of Luminosity");
		add(ItemInit.GLACIATED_STAFF.get(), "Glacial Staff");
		add(ItemInit.ICE_BOOK.get(), "Book of Frost");
		add(ItemInit.ROTTING_STAFF.get(), "Rotting Staff");
		add(ItemInit.FROSTBITTEN_ORB.get(), "Orb of the Eternal Winter");
		add(ItemInit.ROTFISH_ORB.get(), "Orb of the Depths");
		add(ItemInit.FLOWER_CROWN.get(), "Flower Crown");
		add(ItemInit.ICE_SPIKE.get(), "Ice Spike");
		add(ItemInit.FROZEN_ROCK.get(), "Frozen Rock");
		add(ModEntityTypes.frozen_rock.get(), "Frozen Rock");
		add(ItemInit.FROST_BOLT.get(), "Frost Bolt");
		add(ModEntityTypes.frost_bolt.get(), "Frost Bolt");
		add(ItemInit.ROTTING_BOLT.get(), "Rotting Bolt");
		add(ModEntityTypes.rotting_bolt.get(), "Rotting Bolt");
		add(ItemInit.ICY_ARROW.get(), "Icy Arrow");
		add(ItemInit.FROZEN_BOW.get(), "Frostbitten Bow");
		add(ItemInit.METAL_SCRAP.get(), "Metal Scrap");
		add(ItemInit.FROZEN_HEART.get(), "Frozen Heart");
		add(ItemInit.FROZEN_SHARD.get(), "Frozen Shard");
		add(ItemInit.GLACIAL_STOMACH.get(), "Glacial Stomach");
		add(ItemInit.GOOPY_STONE.get(), "Goopy Stone");
		add(ItemInit.ICE_RAMBLE_SPAWN_EGG.get(), "Ice Ramble Spawn Egg");
		add(ItemInit.FROZEN_APPLE.get(), "Frozen Apple");
		add(ModEntityTypes.ICE_RAMBLE.get(), "Ice Ramble");
		add(ItemInit.GLACIAL_BOOTS.get(), "Glacial Boots");
		add(ItemInit.GLACIAL_CHESTPLATE.get(), "Glacial Chestplate");
		add(ItemInit.GLACIAL_HELMET.get(), "Glacial Helmet");
		add(ItemInit.GLACIAL_LEGGINGS.get(), "Glacial Leggings");
		add(ItemInit.ICE_RAMBLE_BUCKET.get(), "Bucket of Ice Ramble");
		add(ItemInit.FROSTBITTEN_BOLT.get(), "Frostbitten Bolt");
		add(ModEntityTypes.frostbitten_bolt.get(), "Frostbitten Bolt");
		add(ModEntityTypes.FROSTBITTEN_KING.get(), "Frostbitten King");
		add(ItemInit.FROSTBITTEN_KING_SPAWN_EGG.get(), "Frostbitten King Spawn Egg");
		add(ItemInit.GLACIAL_TITAN_SPAWN_EGG.get(), "Glacial Titan Spawn Egg");
		add(ModEntityTypes.GLACIAL_TITAN.get(), "Glacial Titan");
		
		/*
		 * 
		 * Special Cases (Curios, Tabs, Potions, etc.)
		 * 
		 */
		
		add("curios.modifiers.feet", "When on feet:");
		add("curios.identifier.feet", "Feet");
		add("itemGroup.tff", "The Fester Forest");
		add("enchantment.tff.arcane_power", "Arcane Power");
		add("enchantment.tff.quickcast", "Quickcast");
		add("enchantment.tff.arcane_power.desc", "Increases damage of Fester Forest magic items.");
		add("enchantment.tff.quickcast.desc", "Decreases cooldowns of Fester Forest magic items.");
		
		/*
		 * 
		 * Item Types
		 * 
		 */
		
		add("type.tff.more_info", "Press SHIFT for more info.");
		add("type.tff.drops_from", "Drops From:");
		add("type.tff.hostile_target", "Targeting Hostile Mobs");
		add("type.tff.all_target", "Targeting All Living Things");
		add("type.tff.belongs_to", "Belongs to:");
		
		add("type.tff.dull", "Dull");
		add("type.tff.magic", "Magic");
		add("type.tff.melee", "Melee");
		add("type.tff.ranged", "Ranged");
		add("type.tff.food", "Food");
		add("type.tff.arrow", "Arrow");
		add("type.tff.tool", "Tool");
		add("type.tff.melee.tool", "Melee/Tool");
		add("type.tff.melee.magic", "Melee/Magic");
		add("type.tff.melee.ranged", "Melee/Ranged");
		
		/*
		 * 
		 * Item Descriptions
		 * 
		 */
		
		// Dull
		add("description.tff.ancient_whistle", "An ancient, crumbling, black whistle.");
		add("description.tff.dual_wield_sword", "A short, lightweight sword, easy to wield alongside another weapon.");
		add("description.tff.angelic_whistle", "An impossibly light whistle.");
		add("description.tff.explosive_powder", "A bottle of a mysterious, highly reactive powder.");
		add("description.tff.flesh_ball", "A wet ball of warm, pulsating flesh.");
		add("description.tff.forgemaster_heart", "The still-beating mechanical heart of the Forgemaster.");
		add("description.tff.frozen_heart", "The cold, hard heart of the Frostbitten King.");
		add("description.tff.glacial_stomach", "A powerful stomach granting immunity to Hunger and Nausea.");
		add("description.tff.goopy_stone", "A sticky stone, preventing you from receiving Levitation or Slowness.");
		add("description.tff.gravity_gauntlet", "A stone gauntlet infused with a mysterious magic.");
		add("description.tff.heavy_stone", "A heavy stone that makes you much too heavy to levitate.");
		add("description.tff.mechanical_eye", "One of the Forgemaster's eyes. Using it grants Night Vision.");
		add("description.tff.metal_scrap", "Some old pieces of the Forgemaster's armor.");
		add("description.tff.frozen_shard", "Small slivers of the ice that once held the Frostbitten King.");
		add("description.tff.moon_shoes", "A pair of incredibly lightweight stone shoes.");
		add("description.tff.pocket_sand", "A small pile of sand, seemingly never decreasing in size.");
		add("description.tff.purifying_powder", "A mysterious powder capable of changing the properties of items.");
		add("description.tff.reetle_queen_antennae", "The Reetles follow their Queen.");
		add("description.tff.reetle_reagents", "A small bag of reetle limbs, seemingly protecting the holder from fall damage.");
		add("description.tff.reetle_shell", "The tough exoskeleton of the Reetle Queen.");
		add("description.tff.rotfish_fang", "The fang of a Rotfish. The venom grants immunity to Poison and Wither.");
		add("description.tff.shock_necklace", "A small ball of energy on a gold chain.");
		add("description.tff.shroom_bucket", "A bucket with a Shroom in it. Mushrum...");
		add("description.tff.slippery_goop", "A bottle of a very slippery slime. Grants immunity to Slowness.");
		add("description.tff.thick_bone", "A very, very heavy bone. Makes you much too heavy to float.");
		add("description.tff.rotting_catalyst", "A rotting pile of organic material.");
		
		// Food
		add("description.tff.ambectrum_donut", "A sweet donut, coated in a layer of Ambectrum jelly.");
		add("description.tff.ambectrum_jelly", "A slightly minty glowing blue jelly.");
		add("description.tff.bug_carcass", "The crunchy remains of a Reetle.");
		add("description.tff.cooked_rotfish", "Salt, pepper, and love go a long way.");
		add("description.tff.cooked_shroom_cluster", "A collection of earthy, slightly salty roasted mushrooms.");
		add("description.tff.frozen_apple", "An apple encased in crunchy ice.");
		add("description.tff.goopy_jello", "A very slimy block of bitter jello.");
		add("description.tff.mechanical_apple", "A metallic apple full of small wires and electronics.");
		add("description.tff.raw_rotfish", "A disgusting fish carcass, barely held together.");
		add("description.tff.rotfish_special", "A cooked rotfish, complimented by roasted mushrooms.");
		add("description.tff.rotting_carrot", "An old, squishy carrot.");
		add("description.tff.rotting_pie", "A delectable combination of rot and decay.");
		add("description.tff.shroom_cluster", "A bundle of wet, salty mushrooms.");
		add("description.tff.volatile_apple", "A crunchy, burning apple.");
		
		// Magic
		add("description.tff.bone_launcer", "An old stick with a broken bone fitted to one end.");
		add("description.tff.food_book", "A dusty leather book detailing a spell of hungerlessness.");
		add("description.tff.brittle_branch", "An ancient, delicate branch.");
		add("description.tff.bug_eggs", "A small collection of Reetle eggs.");
		add("description.tff.dull_orb", "A dull Magic Orb... Perhaps it can be crafted into something?");
		add("description.tff.energetic_fungus", "A bizarre bundle of fragile fungus.");
		add("description.tff.flame_orb", "A glassy orb imbued with the fires of the Nether.");
		add("description.tff.flower_crown", "A beautiful collection of vibrant flowers woven into a crown.");
		add("description.tff.forgemaster_orb", "A glassy orb imbued with the mending fervor of the Forge.");
		add("description.tff.frostbitten_orb", "A glassy orb imbued with the living, growing ice that encased the Frostbitten King.");
		add("description.tff.frozen_orb", "A glassy orb imbued with the freezing winds of glaciers.");
		add("description.tff.frozen_pendant", "A small, ice cold metal charm.");
		add("description.tff.glaciated_staff", "A cold staff, once wielded by a mighty titan.");
		add("description.tff.glowing_book", "A glowing book, containing the writings of an old revealing spell.");
		add("description.tff.ice_book", "A frosty book explaining how to cast an explosion of icy projectiles.");
		add("description.tff.levitate_orb", "A glassy orb imbued with the weightlessness of the End.");
		add("description.tff.life_orb", "A glassy orb imbued with the life of the Fester Forest.");
		add("description.tff.meteor_wand", "A scorching metal rod with a molten ball of rock fixed to one end.");
		add("description.tff.poison_orb", "A glassy orb imbued with the toxins of spider venom.");
		add("description.tff.reetle_orb", "A glassy orb imbued with the soul of the Reetle Queen.");
		add("description.tff.rotfish_orb", "A glassy orb imbued with the chaos of the ocean.");
		add("description.tff.rotting_staff", "A mushy staff with an odd purple flame on one end.");
		add("description.tff.shield_book", "An old dusty spellbook, detailing the encantations of a shielding spell.");
		add("description.tff.verdant_branch", "A once dead branch, now thriving with life.");
		add("description.tff.volatile_necklace", "A scorching necklace fitted with a fiery charm.");
		add("description.tff.wither_orb", "A glassy orb imbued with the souls of the dead.");
		
		// Melee
		add("description.tff.ancient_dagger", "An ancient dagger, quick and sharp.");
		add("description.tff.ancient_greatsword", "A massive, ancient iron blade.");
		add("description.tff.ancient_hatchet", "An old, lightweight hatchet.");
		add("description.tff.ancient_longsword", "A once powerful blade, now chipped and crumbling.");
		add("description.tff.bone_scythe", "A razor-sharp scythe made of ancient bone.");
		add("description.tff.forgemaster_hammer", "The massive, incredibly heavy hammer of the Forgemaster.");
		add("description.tff.frost_mallet", "A large block of ice on the end of a heavy handle.");
		add("description.tff.goopy_sword", "A big slimy sword. Coats anything hit in an acidic goop. Seems edible...");
		add("description.tff.life_scythe", "A warm scythe that pulses with a soft heartbeat.");
		add("description.tff.seathrown_pike", "A sharp point on the end of a very long, wet stick.");
		add("description.tff.shard_of_ice", "A very sharp shard of solid ice.");
		add("description.tff.shock_sword", "An electric beam stabilised by two prongs.");
		
		// Mixed
		add("description.tff.ancient_hammer", "An old, heavy hammer.");
		add("description.tff.festering_club", "A rotting wooden club, laced with dark tendrils.");
		add("description.tff.reaver_blade", "The massive fang of a Deep Reaver.");
		add("description.tff.shadow_bow", "A dark, shadowy bow with razor sharp limbs.");
		add("description.tff.volatile_sword", "A flaming blade fitted to a scorching hilt.");
		
		// Pet Buckets
		add("description.tff.ice_ramble_bucket", "A bucket holding an Ice Ramble.");
		
		// Ranged
		add("description.tff.icy_arrow", "Freezes mobs on hit.");
		add("description.tff.frozen_bow", "A near indestructible bow, crafted from an ancient ice.");
		add("description.tff.icy_bow", "A glassy bow made of a mysterious ice.");
		
		// Tool
		add("description.tff.bone_pickaxe", "A lightweight pickaxe crafted from an assortment of sturdy bones.");
		add("description.tff.electric_pickaxe", "A pickaxe formed almost entirely of an odd electric plasma.");
		add("description.tff.goopy_stick", "A tree branch dripping with acidic goop.");
		add("description.tff.icy_paxel", "An old multitool frozen in ice.");
		
		/*
		 * 
		 * Item Abilities
		 * 
		 */
		
		// Orbs
		add("ability.tff.flame_orb", "Hold in your Off Hand to apply onhit effects to Fester Forest Magic items.");
		add("ability.tff.forgemaster_orb", "Hold in your Off Hand to apply onhit effects to Fester Forest Magic items.");
		add("ability.tff.frostbitten_orb", "Hold in your Off Hand to apply onhit effects to Fester Forest Magic items.");
		add("ability.tff.frozen_orb", "Hold in your Off Hand to apply onhit effects to Fester Forest Magic items.");
		add("ability.tff.levitate_orb", "Hold in your Off Hand to apply onhit effects to Fester Forest Magic items.");
		add("ability.tff.life_orb", "Hold in your Off Hand to apply onhit effects to Fester Forest Magic items.");
		add("ability.tff.poison_orb", "Hold in your Off Hand to apply onhit effects to Fester Forest Magic items.");
		add("ability.tff.reetle_orb", "Hold in your Off Hand to apply onhit effects to Fester Forest Magic items.");
		add("ability.tff.rotfish_orb", "Hold in your Off Hand to apply onhit effects to Fester Forest Magic items.");
		add("ability.tff.wither_orb", "Hold in your Off Hand to apply onhit effects to Fester Forest Magic items.");
		
		// Other
		add("ability.tff.ancient_whistle", "Use item to emit a powerful shockwave of sound around you. Any mobs hit will fly away from you. Both yourself and any mobs hit will take armor piercing damage.");
		add("ability.tff.angelic_whistle", "Use item to create a beautiful harmony of music, launching and lifting all surrounding mobs away from you. Any mobs that hear your music take armor piercing damage.");
		add("ability.tff.explosive_powder", "Use item to violently launch yourself forward.");
		add("ability.tff.flesh_ball", "Use item to squeeze the ball and give yourself very high regeneration at the expense of your movement speed.");
		add("ability.tff.pocket_sand", "Use item to throw sand from your pocket, blinding any mobs hit.");
		add("ability.tff.bone_launcer", "Use item to launch a low damage, armor piercing bone projectile.");
		add("ability.tff.brittle_branch", "Use item to launch multiple poisoned projectiles.");
		add("ability.tff.bug_eggs", "Use item to summon a collection of friendly Reetles to fight for you.");
		add("ability.tff.energetic_fungus", "Gain Regeneration when you are not moving.");
		add("ability.tff.flower_crown", "When worn, slain mobs drop more experience.");
		add("ability.tff.frozen_pendant", "When worn, the charm leeches the movement from nearby mobs, and returns it to the wearer.");
		add("ability.tff.glaciated_staff", "Use item to launch a massive ice boulder. The boulder explodes on impact, damaging nearby mobs.");
		add("ability.tff.glowing_book", "Use item to apply Glowing to nearby mobs.");
		add("ability.tff.ice_book", "Use item to launch a projectile at nearby mobs. Crouch while using item to change targeting mode.");
		add("ability.tff.meteor_wand", "Use item on a block or mob to summon a meteor above your head that will land at that location. Does not work well under a roof!");
		add("ability.tff.rotting_staff", "Use item to launch a rotting bolt.");
		add("ability.tff.shield_book", "Use item to temporarily grant yourself a large number of Absorption hearts.");
		add("ability.tff.verdant_branch", "Use item on a mob to summon multiple poisoned homing projectiles.");
		add("ability.tff.volatile_necklace", "While on fire, gain Strength.");
		add("ability.tff.ancient_hatchet", "Continuous attacks grant stacking attack speed.");
		add("ability.tff.forgemaster_hammer", "The massive weight of the hammer applies large amounts of knockback to any mob hit.");
		add("ability.tff.frost_mallet", "Use item to slam the mallet into the ground, dealing 50% of your max health to all nearby mobs.");
		add("ability.tff.life_scythe", "Use item to grant yourself absorption and resistance for a short time. This power does, however, make you very hungry...");
		add("ability.tff.shard_of_ice", "Use item to launch an icy projectile, dealing the same damage you would with a melee attack.");
		add("ability.tff.shock_sword", "Has a chance to paralyze any mob hit.");
		add("ability.tff.rotting_catalyst", "Used to ignite a portal to the Fester Forest made of Fester Bricks, similar to a Nether portal.");
		add("ability.tff.ancient_hammer", "Slows enemies on hit. Can also be used as a Diamond pickaxe.");
		add("ability.tff.festering_club", "Mobs hit are given Poison and Wither for a short time.");
		add("ability.tff.reaver_blade", "Hitting a mob afflicts both yourself and the target with the same random effect of a random strength.");
		add("ability.tff.shadow_bow", "Functions as both a bow and a sword, accepting both kinds of enchantments.");
		add("ability.tff.volatile_sword", "Use item to emit a burst of flame, damaging and igniting all nearby mobs.");
		add("ability.tff.frozen_bow", "Converts all shot arrows into Icy Arrows. Shoots 3 arrows at a time, for the cost of one. All shot arrows do an additional 20% damage.");
		add("ability.tff.icy_bow", "Converts all shot arrows into Icy Arrows.");
		add("ability.tff.electric_pickaxe", "Breaking an \"Underground\" block adds a charge, with each charge increasing mining speed. Breaking a non \"Underground\" block dissipates all charges.");
		add("ability.tff.goopy_pickaxe", "The goop is able to break any block with diamond efficiency.");
		add("ability.tff.icy_paxel", "This tool can break any block with diamond efficiency.");
		
	}

}
