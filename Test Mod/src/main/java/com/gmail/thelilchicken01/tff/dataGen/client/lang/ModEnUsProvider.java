package com.gmail.thelilchicken01.tff.dataGen.client.lang;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnUsProvider extends LanguageProvider {

	public ModEnUsProvider(DataGenerator gen) {
		super(gen, TheFesterForest.modid, "en_us");
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
		//Other
		add(BlockInit.rotting_bricks.get(), "Fester Bricks");
		add(ItemInit.rotting_brick.get(), "Fester Brick");
		add(ItemInit.rotting_catalyst.get(), "Rotting Catalyst");
		add(ItemInit.volatileSword.get(), "§6Volatile Sword");
		add(ItemInit.ancientGreatsword.get(), "Ancient Greatsword");
		add(BlockInit.volatileLamp.get(), "Volatile Lamp");
		add(BlockInit.volatileOre.get(), "Volatile Ore");
		add(BlockInit.healthgem.get(), "Health Gem");
		add(ItemInit.volatileApple.get(), "§6Volatile Apple");
		add(BlockInit.rotting_wood.get(), "Rottingwood");
		add(BlockInit.rotting_log.get(), "Rottingwood Log");
		add(BlockInit.stripped_rotting_wood.get(), "Stripped Rottingwood");
		add(BlockInit.stripped_rotting_log.get(), "Stripped Rottingwood Log");
		add(BlockInit.rotting_leaves.get(), "Rottingwood Leaves");
		add(BlockInit.rotting_planks.get(), "Rottingwood Planks");
		add(BlockInit.rottingwood_sapling.get(), "Rottingwood Sapling");
		add(BlockInit.rotting_dirt.get(), "Rotting Dirt");
		add(BlockInit.rotting_flower.get(), "Rotting Rose");
		add(BlockInit.rotting_flower_pot.get(), "Rotting Flower Pot");
		add(BlockInit.rotting_stone.get(), "Rotting Stone");
		add(BlockInit.rotting_grass.get(), "Rotting Grass Block");
		add(BlockInit.fester_ore.get(), "Fester Ore");
		add(BlockInit.rotting_tall_grass.get(), "Rotting Tallgrass");
		add(BlockInit.slimy_log.get(), "Slimy Log");
		add(BlockInit.slimy_leaves.get(), "Slimy Leaves");
		add(BlockInit.slimy_sapling.get(), "Slimy Sapling");
		add(BlockInit.slimy_planks.get(), "Slimy Planks");
		add(BlockInit.slimy_wood.get(), "Slimy Wood");
		add(BlockInit.stripped_slimy_log.get(), "Stripped Slimy Log");
		add(BlockInit.stripped_slimy_wood.get(), "Stripped Slimy Wood");
		add(ItemInit.rotting_skeleton_spawn_egg.get(), "Rotting Skeleton Spawn Egg");
		add(ItemInit.bug_carcass.get(), "Crunch Beetle Carcass");
		add(ModEntityTypes.crunch_beetle.get(), "Crunch Beetle");
		add(ModEntityTypes.rotting_skeleton.get(), "Rotting Skeleton");
		add(ItemInit.crunch_beetle_spawn_egg.get(), "Crunch Beetle Spawn Egg");
		add(ItemInit.banshee_spawn_egg.get(), "Banshee Spawn Egg");
		add(ModEntityTypes.banshee.get(), "Banshee");
		add(ItemInit.ancient_whistle.get(), "Ancient Whistle");
		add(BlockInit.cracked_rotting_stone.get(), "Cracked Rotting Stone");
		add(BlockInit.mossy_rotting_stone.get(), "Mossy Rotting Stone");
		add(BlockInit.rotting_stone_bricks.get(), "Rotting Stone Bricks");
		add(ItemInit.dual_wield_sword.get(), "Dual Wield Sword");
		add(ItemInit.volatile_ghost_spawn_egg.get(), "Volatile Ghost Spawn Egg");
		add(ModEntityTypes.volatile_ghost.get(), "Volatile Ghost");
		add(ItemInit.volatile_necklace.get(), "Volatile Necklace");
		add(ItemInit.frozen_pendant.get(), "Frozen Pendant");
		add(ItemInit.pocket_sand.get(), "Pocket Sand");
		add(ItemInit.flesh_ball.get(), "Ball of Flesh");
		add(ItemInit.life_scythe.get(), "Scythe of Draining");
		add("itemGroup.tff", "The Fester Forest");
		
	}

}
