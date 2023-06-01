package com.gmail.thelilchicken01.tff.dataGen.server;

import java.util.function.Consumer;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeProvider extends RecipeProvider {

	public ModRecipeProvider(DataGenerator gen) {
		super(gen);
		
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		
		// Shaped
		ShapedRecipeBuilder.shaped(BlockInit.ROTTING_BRICKS.get()) // add , int to get number of output
			.define('a', ItemInit.ROTTING_BRICK.get().asItem())
			.unlockedBy("has_" + ItemInit.ROTTING_BRICK.get().getRegistryName(), has(ItemInit.ROTTING_BRICK.get().asItem())) // what unlocks the recipe when you get that item
			.pattern("aa ").pattern("aa ").save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_BRICKS.get().getRegistryName().getPath()));
		
		ShapedRecipeBuilder.shaped(BlockInit.COMPRESSED_ROTTING_SAND.get()) // add , int to get number of output
			.define('a', BlockInit.ROTTING_SAND.get().asItem())
			.unlockedBy("has_" + BlockInit.ROTTING_SAND.get().getRegistryName(), has(BlockInit.ROTTING_SAND.get().asItem())) // what unlocks the recipe when you get that item
			.pattern("aa ").pattern("aa ").save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.COMPRESSED_ROTTING_SAND.get().getRegistryName().getPath()));
		
		ShapedRecipeBuilder.shaped(BlockInit.ROTTING_STONE_BRICKS.get(), 4) //what am i making
			.define('a', BlockInit.ROTTING_STONE.get().asItem()) //keys
			.unlockedBy("has_" + BlockInit.ROTTING_STONE.get().getRegistryName(), //unlocked when you get this item
					has(BlockInit.ROTTING_STONE.get().asItem()))
			.pattern("aa ").pattern("aa ").save(consumer, //the patterns with the keys above
					new ResourceLocation(TheFesterForest.MODID, //crafting name of recipe is this \/ 
							BlockInit.ROTTING_STONE_BRICKS.get().getRegistryName().getPath()));
		
		ShapedRecipeBuilder.shaped(Items.SKELETON_SKULL, 1) //what am i making
			.define('a', BlockInit.SOUL_ROT.get().asItem()) //keys
			.unlockedBy("has_" + BlockInit.SOUL_ROT.get().getRegistryName(), //unlocked when you get this item
					has(BlockInit.SOUL_ROT.get().asItem()))
			.pattern("aa ").pattern("aa ").save(consumer, //the patterns with the keys above
					new ResourceLocation(TheFesterForest.MODID, //crafting name of recipe is this \/ 
							BlockInit.SOUL_ROT.get().getRegistryName().getPath() + "_to_skulls"));
		
		//Stone Brick Stairs
		ShapedRecipeBuilder.shaped(BlockInit.ROTTING_STONE_BRICK_STAIRS.get(), 4)
			.define('a', BlockInit.ROTTING_STONE_BRICKS.get().asItem())
			.unlockedBy("has_" + BlockInit.ROTTING_STONE_BRICKS.get().getRegistryName(), 
					has(BlockInit.ROTTING_STONE_BRICKS.get().asItem()))
			.pattern("a  ").pattern("aa ").pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.ROTTING_STONE_BRICK_STAIRS.get().getRegistryName().getPath()));
		
		//Stone Brick Slabs
		ShapedRecipeBuilder.shaped(BlockInit.ROTTING_STONE_BRICK_SLAB.get(), 6)
			.define('a', BlockInit.ROTTING_STONE_BRICKS.get().asItem())
			.unlockedBy("has_" + BlockInit.ROTTING_STONE_BRICKS.get().getRegistryName(), 
					has(BlockInit.ROTTING_STONE_BRICKS.get().asItem()))
			.pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.ROTTING_STONE_BRICK_SLAB.get().getRegistryName().getPath()));
		
		//Compressed Rotting Sand Stairs
		ShapedRecipeBuilder.shaped(BlockInit.COMPRESSED_ROTTING_SAND_STAIRS.get(), 4)
			.define('a', BlockInit.COMPRESSED_ROTTING_SAND.get().asItem())
			.unlockedBy("has_" + BlockInit.COMPRESSED_ROTTING_SAND.get().getRegistryName(), 
					has(BlockInit.COMPRESSED_ROTTING_SAND.get().asItem()))
			.pattern("a  ").pattern("aa ").pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.COMPRESSED_ROTTING_SAND_STAIRS.get().getRegistryName().getPath()));
		
		//Compressed Rotting Sand Slab
		ShapedRecipeBuilder.shaped(BlockInit.COMPRESSED_ROTTING_SAND_SLAB.get(), 6)
			.define('a', BlockInit.COMPRESSED_ROTTING_SAND.get().asItem())
			.unlockedBy("has_" + BlockInit.COMPRESSED_ROTTING_SAND.get().getRegistryName(), 
					has(BlockInit.COMPRESSED_ROTTING_SAND.get().asItem()))
			.pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.COMPRESSED_ROTTING_SAND_SLAB.get().getRegistryName().getPath()));
		
		//Compressed Rotting Sand Wall
		ShapedRecipeBuilder.shaped(BlockInit.COMPRESSED_ROTTING_SAND_WALL.get(), 6)
			.define('a', BlockInit.COMPRESSED_ROTTING_SAND.get().asItem())
			.unlockedBy("has_" + BlockInit.COMPRESSED_ROTTING_SAND.get().getRegistryName(), 
					has(BlockInit.COMPRESSED_ROTTING_SAND.get().asItem()))
			.pattern("aaa").pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.COMPRESSED_ROTTING_SAND_WALL.get().getRegistryName().getPath()));
		
		//Stone Brick Walls
		ShapedRecipeBuilder.shaped(BlockInit.ROTTING_STONE_BRICK_WALL.get(), 6)
			.define('a', BlockInit.ROTTING_STONE_BRICKS.get().asItem())
			.unlockedBy("has_" + BlockInit.ROTTING_STONE_BRICKS.get().getRegistryName(), 
					has(BlockInit.ROTTING_STONE_BRICKS.get().asItem()))
			.pattern("aaa").pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.ROTTING_STONE_BRICK_WALL.get().getRegistryName().getPath()));
		
		//Rottingwood Fence
		ShapedRecipeBuilder.shaped(BlockInit.ROTTINGWOOD_FENCE.get(), 3)
			.define('a', BlockInit.ROTTING_PLANKS.get().asItem()).define('b', Items.STICK)
			.unlockedBy("has_" + BlockInit.ROTTING_PLANKS.get().getRegistryName(), 
					has(BlockInit.ROTTING_PLANKS.get().asItem()))
			.pattern("aba").pattern("aba").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.ROTTINGWOOD_FENCE.get().getRegistryName().getPath()));
		
		//Rottingwood Fence Gate
		ShapedRecipeBuilder.shaped(BlockInit.ROTTINGWOOD_FENCE_GATE.get())
			.define('a', BlockInit.ROTTING_PLANKS.get().asItem()).define('b', Items.STICK)
			.unlockedBy("has_" + BlockInit.ROTTING_PLANKS.get().getRegistryName(), 
					has(BlockInit.ROTTING_PLANKS.get().asItem()))
			.pattern("bab").pattern("bab").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.ROTTINGWOOD_FENCE_GATE.get().getRegistryName().getPath()));
		
		//Rottingwood Stairs
		ShapedRecipeBuilder.shaped(BlockInit.ROTTINGWOOD_STAIRS.get(), 4)
			.define('a', BlockInit.ROTTING_PLANKS.get().asItem())
			.unlockedBy("has_" + BlockInit.ROTTING_PLANKS.get().getRegistryName(), 
					has(BlockInit.ROTTING_PLANKS.get().asItem()))
			.pattern("a  ").pattern("aa ").pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.ROTTINGWOOD_STAIRS.get().getRegistryName().getPath()));
		
		//Slimy Stairs
		ShapedRecipeBuilder.shaped(BlockInit.SLIMY_STAIRS.get(), 4)
			.define('a', BlockInit.SLIMY_PLANKS.get().asItem())
			.unlockedBy("has_" + BlockInit.SLIMY_PLANKS.get().getRegistryName(), 
					has(BlockInit.SLIMY_PLANKS.get().asItem()))
			.pattern("a  ").pattern("aa ").pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.SLIMY_STAIRS.get().getRegistryName().getPath()));
		//Fester Stairs
		ShapedRecipeBuilder.shaped(BlockInit.FESTER_BRICK_STAIRS.get(), 4)
			.define('a', BlockInit.ROTTING_BRICKS.get().asItem())
			.unlockedBy("has_" + BlockInit.ROTTING_BRICKS.get().getRegistryName(), 
					has(BlockInit.ROTTING_BRICKS.get().asItem()))
			.pattern("a  ").pattern("aa ").pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.FESTER_BRICK_STAIRS.get().getRegistryName().getPath()));
		
		//Rottingwood Slabs
		ShapedRecipeBuilder.shaped(BlockInit.ROTTINGWOOD_SLAB.get(), 6)
			.define('a', BlockInit.ROTTING_PLANKS.get().asItem())
			.unlockedBy("has_" + BlockInit.ROTTING_PLANKS.get().getRegistryName(), 
					has(BlockInit.ROTTING_PLANKS.get().asItem()))
			.pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.ROTTINGWOOD_SLAB.get().getRegistryName().getPath()));
		
		//Slimy Slabs
		ShapedRecipeBuilder.shaped(BlockInit.SLIMY_SLAB.get(), 6)
			.define('a', BlockInit.SLIMY_PLANKS.get().asItem())
			.unlockedBy("has_" + BlockInit.SLIMY_PLANKS.get().getRegistryName(), 
					has(BlockInit.SLIMY_PLANKS.get().asItem()))
			.pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.SLIMY_SLAB.get().getRegistryName().getPath()));
		
		//Fester Brick Slabs
		ShapedRecipeBuilder.shaped(BlockInit.FESTER_BRICK_SLAB.get(), 6)
			.define('a', BlockInit.ROTTING_BRICKS.get().asItem())
			.unlockedBy("has_" + BlockInit.ROTTING_BRICKS.get().getRegistryName(), 
					has(BlockInit.ROTTING_BRICKS.get().asItem()))
			.pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.FESTER_BRICK_SLAB.get().getRegistryName().getPath()));
		
		//Slimy Fence
		ShapedRecipeBuilder.shaped(BlockInit.SLIMY_FENCE.get(), 3)
			.define('a', BlockInit.SLIMY_PLANKS.get().asItem()).define('b', Items.STICK)
			.unlockedBy("has_" + BlockInit.SLIMY_PLANKS.get().getRegistryName(), 
					has(BlockInit.SLIMY_PLANKS.get().asItem()))
			.pattern("aba").pattern("aba").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.SLIMY_FENCE.get().getRegistryName().getPath()));
				
		//Slimy Fence Gate
		ShapedRecipeBuilder.shaped(BlockInit.SLIMY_FENCE_GATE.get())
			.define('a', BlockInit.SLIMY_PLANKS.get().asItem()).define('b', Items.STICK)
			.unlockedBy("has_" + BlockInit.SLIMY_PLANKS.get().getRegistryName(), 
					has(BlockInit.SLIMY_PLANKS.get().asItem()))
			.pattern("bab").pattern("bab").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.SLIMY_FENCE_GATE.get().getRegistryName().getPath()));
		
		//Rotting Pie
		ShapedRecipeBuilder.shaped(ItemInit.ROTTING_PIE.get())
			.define('a', ItemInit.ROTTING_CARROT.get())
			.define('b', ItemInit.BUG_CARCASS.get())
			.unlockedBy("has_" + ItemInit.ROTTING_CARROT.get().getRegistryName(), 
					has(ItemInit.ROTTING_CARROT.get().asItem()))
			.unlockedBy("has_" + ItemInit.BUG_CARCASS.get().getRegistryName(), 
					has(ItemInit.BUG_CARCASS.get().asItem()))
			.pattern(" b ").pattern("bab").pattern(" b ").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							ItemInit.ROTTING_PIE.get().getRegistryName().getPath()));
		
		//Reetlelight
		ShapedRecipeBuilder.shaped(BlockInit.REETLELIGHT.get())
			.define('a', ItemInit.BUG_CARCASS.get())
			.define('b', Items.GLOWSTONE_DUST)
			.unlockedBy("has_" + ItemInit.BUG_CARCASS.get().getRegistryName(), 
					has(ItemInit.BUG_CARCASS.get().asItem()))
			.pattern(" b ").pattern("bab").pattern(" b ").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.REETLELIGHT.get().getRegistryName().getPath()));
		
		
		ShapedRecipeBuilder.shaped(ItemInit.ANGELIC_WHISTLE.get(), 1) // add , int to get number of output
		.define('a', ItemInit.ANCIENT_WHISTLE.get().asItem())
		.define('b', Items.NETHER_STAR)
		.define('c', Items.FEATHER)
		.define('d', Items.IRON_INGOT)
		.unlockedBy("has_" + ItemInit.ANCIENT_WHISTLE.get().getRegistryName(), 
				has(ItemInit.ANCIENT_WHISTLE.get().asItem())) // what unlocks the recipe when you get that item
		.pattern(" d ").pattern("cac").pattern(" b ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, ItemInit.ANGELIC_WHISTLE.get().getRegistryName().getPath()));
		
		// Shapeless
		ShapelessRecipeBuilder.shapeless(ItemInit.ROTTING_BRICK.get().asItem(), 4).requires(BlockInit.ROTTING_BRICKS.get()) //get item
			.unlockedBy("has_" + BlockInit.ROTTING_BRICKS.get().getRegistryName(), has(BlockInit.ROTTING_BRICKS.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.ROTTING_BRICK.get().getRegistryName().getPath()));
		
		// Rotting Flower to Purple
		ShapelessRecipeBuilder.shapeless(Items.PURPLE_DYE, 1).requires(BlockInit.ROTTING_FLOWER.get()) //get item
			.unlockedBy("has_" + BlockInit.ROTTING_FLOWER.get().getRegistryName(), has(BlockInit.ROTTING_FLOWER.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_FLOWER.get().getRegistryName().getPath() + "_to_purple_dye"));
		
		// Sickening Flower to Magenta
		ShapelessRecipeBuilder.shapeless(Items.MAGENTA_DYE, 1).requires(BlockInit.SICKENING_FLOWER.get()) //get item
			.unlockedBy("has_" + BlockInit.SICKENING_FLOWER.get().getRegistryName(), has(BlockInit.SICKENING_FLOWER.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.SICKENING_FLOWER.get().getRegistryName().getPath() + "_to_magenta_dye"));
		
		// Slimy Bells to Lime Dye
		ShapelessRecipeBuilder.shapeless(Items.LIME_DYE, 1).requires(BlockInit.SLIMY_FLOWER.get()) //get item
			.unlockedBy("has_" + BlockInit.SLIMY_FLOWER.get().getRegistryName(), has(BlockInit.SLIMY_FLOWER.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.SLIMY_FLOWER.get().getRegistryName().getPath() + "_to_lime_dye"));
		
		// Weeping Flower to Blue Dye
		ShapelessRecipeBuilder.shapeless(Items.BLUE_DYE, 1).requires(BlockInit.WEEPING_GRASS.get()) //get item
			.unlockedBy("has_" + BlockInit.WEEPING_GRASS.get().getRegistryName(), has(BlockInit.WEEPING_GRASS.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.WEEPING_GRASS.get().getRegistryName().getPath() + "_to_blue_dye"));
		
		ShapelessRecipeBuilder.shapeless(BlockInit.MOSSY_ROTTING_STONE.get().asItem(), 1).requires(BlockInit.ROTTING_STONE.get()).requires(Blocks.VINE)
			.unlockedBy("has_" + BlockInit.ROTTING_STONE.get().getRegistryName(), has(BlockInit.ROTTING_STONE.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.MOSSY_ROTTING_STONE.get().getRegistryName().getPath()));
		
		ShapelessRecipeBuilder.shapeless(Items.SLIME_BALL, 4).requires(ItemInit.ROTTING_SLIMEBALL.get())
			.unlockedBy("has_" + ItemInit.ROTTING_SLIMEBALL.get().getRegistryName(), has(ItemInit.ROTTING_SLIMEBALL.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.ROTTING_SLIMEBALL.get().getRegistryName().getPath()));
		
		ShapelessRecipeBuilder.shapeless(ItemInit.AMBECTRUM_DONUT.get(), 1).requires(ItemInit.AMBECTRUM_JELLY.get()).requires(Items.WHEAT).requires(Items.SUGAR)
			.unlockedBy("has_" + ItemInit.AMBECTRUM_JELLY.get().getRegistryName(), has(ItemInit.AMBECTRUM_JELLY.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.AMBECTRUM_DONUT.get().getRegistryName().getPath()));
		
		// Purifying Rotten Flesh
		ShapelessRecipeBuilder.shapeless(Items.LEATHER, 3)
			.requires(ItemInit.PURIFYING_POWDER.get()).requires(Items.ROTTEN_FLESH, 3)
			.unlockedBy("has_" + ItemInit.PURIFYING_POWDER.get().getRegistryName(), 
					has(ItemInit.PURIFYING_POWDER.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, Items.LEATHER
					.getRegistryName().getPath()));
		
		// Purifying Rotting Stone
		ShapelessRecipeBuilder.shapeless(Items.STONE, 3)
			.requires(ItemInit.PURIFYING_POWDER.get()).requires(BlockInit.ROTTING_STONE.get().asItem(), 3)
			.unlockedBy("has_" + ItemInit.PURIFYING_POWDER.get().getRegistryName(), 
					has(ItemInit.PURIFYING_POWDER.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, Items.STONE
					.getRegistryName().getPath()));
		
		// Purifying Gravel
		ShapelessRecipeBuilder.shapeless(Items.FLINT, 6)
			.requires(ItemInit.PURIFYING_POWDER.get()).requires(Items.GRAVEL, 3)
			.unlockedBy("has_" + ItemInit.PURIFYING_POWDER.get().getRegistryName(), 
					has(ItemInit.PURIFYING_POWDER.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.PURIFYING_POWDER.get()
					.getRegistryName().getPath() + "_flint"));
		
		// Purifying Netherrack
		ShapelessRecipeBuilder.shapeless(Items.NETHER_BRICKS, 3)
			.requires(ItemInit.PURIFYING_POWDER.get()).requires(Items.NETHERRACK, 3)
			.unlockedBy("has_" + ItemInit.PURIFYING_POWDER.get().getRegistryName(), 
					has(ItemInit.PURIFYING_POWDER.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.PURIFYING_POWDER.get()
					.getRegistryName().getPath() + "_nether_bricks"));
		
		// Purifying Nether Star
		ShapelessRecipeBuilder.shapeless(Items.WITHER_SKELETON_SKULL, 6)
			.requires(ItemInit.PURIFYING_POWDER.get()).requires(Items.NETHER_STAR, 1)
			.unlockedBy("has_" + ItemInit.PURIFYING_POWDER.get().getRegistryName(), 
					has(ItemInit.PURIFYING_POWDER.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.PURIFYING_POWDER.get()
					.getRegistryName().getPath() + "_nether_star"));
		
		// Purifying Ancient Debris
		ShapelessRecipeBuilder.shapeless(Items.NETHERITE_SCRAP, 5)
			.requires(ItemInit.PURIFYING_POWDER.get()).requires(Items.ANCIENT_DEBRIS, 3)
			.unlockedBy("has_" + ItemInit.PURIFYING_POWDER.get().getRegistryName(), 
					has(ItemInit.PURIFYING_POWDER.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.PURIFYING_POWDER.get()
					.getRegistryName().getPath() + "_ancient_debris"));
		
		// Purifying Blaze Rod
		ShapelessRecipeBuilder.shapeless(Items.BLAZE_POWDER, 9)
			.requires(ItemInit.PURIFYING_POWDER.get()).requires(Items.BLAZE_ROD, 3)
			.unlockedBy("has_" + ItemInit.PURIFYING_POWDER.get().getRegistryName(), 
					has(ItemInit.PURIFYING_POWDER.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.PURIFYING_POWDER.get()
					.getRegistryName().getPath() + "_blaze_rod"));
		
		// Purifying Rotting Mound
		ShapelessRecipeBuilder.shapeless(Items.BEEF, 5)
			.requires(ItemInit.PURIFYING_POWDER.get()).requires(ItemInit.ROTTING_CATALYST.get(), 1)
			.unlockedBy("has_" + ItemInit.PURIFYING_POWDER.get().getRegistryName(), 
					has(ItemInit.PURIFYING_POWDER.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.PURIFYING_POWDER.get()
					.getRegistryName().getPath() + "_rotting_mound"));
		
		// Purifying Soul Rot
		ShapelessRecipeBuilder.shapeless(Items.SOUL_SAND, 5)
			.requires(ItemInit.PURIFYING_POWDER.get()).requires(BlockInit.SOUL_ROT.get(), 3)
			.unlockedBy("has_" + ItemInit.PURIFYING_POWDER.get().getRegistryName(), 
					has(ItemInit.PURIFYING_POWDER.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.PURIFYING_POWDER.get()
					.getRegistryName().getPath() + "_soul_rot"));
		
		// Purifying Fester Bricks
		ShapelessRecipeBuilder.shapeless(Items.OBSIDIAN, 3)
			.requires(ItemInit.PURIFYING_POWDER.get()).requires(BlockInit.ROTTING_BRICKS.get(), 3)
			.unlockedBy("has_" + ItemInit.PURIFYING_POWDER.get().getRegistryName(), 
					has(ItemInit.PURIFYING_POWDER.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.PURIFYING_POWDER.get()
					.getRegistryName().getPath() + "_rotting_bricks"));
		
		// Purifying Rotting Stone Bricks
		ShapelessRecipeBuilder.shapeless(Items.STONE_BRICKS, 3)
			.requires(ItemInit.PURIFYING_POWDER.get()).requires(BlockInit.ROTTING_STONE_BRICKS.get(), 3)
			.unlockedBy("has_" + ItemInit.PURIFYING_POWDER.get().getRegistryName(), 
					has(ItemInit.PURIFYING_POWDER.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.PURIFYING_POWDER.get()
					.getRegistryName().getPath() + "_rotting_stone_bricks"));
		
		// Purifying Quartz
		ShapelessRecipeBuilder.shapeless(Items.GLASS, 3)
			.requires(ItemInit.PURIFYING_POWDER.get()).requires(Items.QUARTZ, 3)
			.unlockedBy("has_" + ItemInit.PURIFYING_POWDER.get().getRegistryName(), 
					has(ItemInit.PURIFYING_POWDER.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.PURIFYING_POWDER.get()
					.getRegistryName().getPath() + "_quartz"));
		
		//Cooking Example
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockInit.FESTER_ORE.get().asItem()),
			ItemInit.ROTTING_BRICK.get(), 15, 200)
			.unlockedBy("has_" + BlockInit.FESTER_ORE.get().getRegistryName(), has(BlockInit.FESTER_ORE.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.FESTER_ORE.get().getRegistryName().getPath() + "_smelting"));
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockInit.ROTTING_STONE.get().asItem()),
			BlockInit.CRACKED_ROTTING_STONE.get(), 1, 200)
			.unlockedBy("has_" + BlockInit.ROTTING_STONE.get().getRegistryName(), has(BlockInit.ROTTING_STONE.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_STONE.get().getRegistryName().getPath() + "_smelting"));
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemInit.GOOPY_SWORD.get()),
				ItemInit.GOOPY_JELLO.get(), 1, 200)
				.unlockedBy("has_" + ItemInit.GOOPY_SWORD.get().getRegistryName(), has(ItemInit.GOOPY_SWORD.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.GOOPY_SWORD.get().getRegistryName().getPath() + "_smelting"));
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemInit.GOOPY_HELMET.get()),
				ItemInit.GOOPY_JELLO.get(), 1, 200)
				.unlockedBy("has_" + ItemInit.GOOPY_HELMET.get().getRegistryName(), has(ItemInit.GOOPY_HELMET.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.GOOPY_HELMET.get().getRegistryName().getPath() + "_smelting"));
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemInit.GOOPY_CHESTPLATE.get()),
				ItemInit.GOOPY_JELLO.get(), 1, 200)
				.unlockedBy("has_" + ItemInit.GOOPY_CHESTPLATE.get().getRegistryName(), has(ItemInit.GOOPY_CHESTPLATE.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.GOOPY_CHESTPLATE.get().getRegistryName().getPath() + "_smelting"));
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemInit.GOOPY_LEGGINGS.get()),
				ItemInit.GOOPY_JELLO.get(), 1, 200)
				.unlockedBy("has_" + ItemInit.GOOPY_LEGGINGS.get().getRegistryName(), has(ItemInit.GOOPY_LEGGINGS.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.GOOPY_LEGGINGS.get().getRegistryName().getPath() + "_smelting"));
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemInit.GOOPY_BOOTS.get()),
				ItemInit.GOOPY_JELLO.get(), 1, 200)
				.unlockedBy("has_" + ItemInit.GOOPY_BOOTS.get().getRegistryName(), has(ItemInit.GOOPY_BOOTS.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.GOOPY_BOOTS.get().getRegistryName().getPath() + "_smelting"));
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockInit.SOUL_ROT.get()),
				Items.BONE_BLOCK, 1, 200)
				.unlockedBy("has_" + BlockInit.SOUL_ROT.get().getRegistryName(), has(BlockInit.SOUL_ROT.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.SOUL_ROT.get().getRegistryName().getPath() + "_smelting_to_bone_block"));
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemInit.RAW_ROTFISH.get()),
				ItemInit.COOKED_ROTFISH.get(), 1, 200)
				.unlockedBy("has_" + ItemInit.RAW_ROTFISH.get().getRegistryName(), has(ItemInit.RAW_ROTFISH.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.RAW_ROTFISH.get().getRegistryName().getPath() + "_smelting_raw_rotfish"));
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockInit.ROTTING_SAND.get()),
				BlockInit.ROTTING_GLASS.get(), 1, 200)
				.unlockedBy("has_" + BlockInit.ROTTING_SAND.get().getRegistryName(), has(BlockInit.ROTTING_SAND.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_GLASS.get().getRegistryName().getPath() + "_smelting"));
		
		//Smithing
		UpgradeRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_HELMET), Ingredient.of(ItemInit.REETLE_SHELL.get()), ItemInit.REETLE_HELMET.get())
			.unlocks("has_" + ItemInit.REETLE_SHELL.get().getRegistryName(), has(ItemInit.REETLE_SHELL.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.REETLE_SHELL.get().getRegistryName().getPath() + "_helmet"));
		
		UpgradeRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_CHESTPLATE), Ingredient.of(ItemInit.REETLE_SHELL.get()), ItemInit.REETLE_CHESTPLATE.get())
			.unlocks("has_" + ItemInit.REETLE_SHELL.get().getRegistryName(), has(ItemInit.REETLE_SHELL.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.REETLE_SHELL.get().getRegistryName().getPath() + "_chestplate"));
		
		UpgradeRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_LEGGINGS), Ingredient.of(ItemInit.REETLE_SHELL.get()), ItemInit.REETLE_LEGGINGS.get())
			.unlocks("has_" + ItemInit.REETLE_SHELL.get().getRegistryName(), has(ItemInit.REETLE_SHELL.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.REETLE_SHELL.get().getRegistryName().getPath() + "_leggings"));
		
		UpgradeRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_BOOTS), Ingredient.of(ItemInit.REETLE_SHELL.get()), ItemInit.REETLE_BOOTS.get())
			.unlocks("has_" + ItemInit.REETLE_SHELL.get().getRegistryName(), has(ItemInit.REETLE_SHELL.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.REETLE_SHELL.get().getRegistryName().getPath() + "_boots"));
		
		UpgradeRecipeBuilder.smithing(Ingredient.of(Items.ELYTRA), Ingredient.of(ItemInit.REETLE_SHELL.get()), ItemInit.REETLE_ELYTRA.get())
			.unlocks("has_" + ItemInit.REETLE_SHELL.get().getRegistryName(), has(ItemInit.REETLE_SHELL.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.REETLE_SHELL.get().getRegistryName().getPath() + "_elytra"));
		
		
	
		
	}
	
}
