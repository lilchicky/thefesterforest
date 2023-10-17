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
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

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
		
		// Frostbitten Wood
		ShapedRecipeBuilder.shaped(BlockInit.FROSTBITTEN_WOOD.get(), 3) // add , int to get number of output
			.define('a', BlockInit.FROSTBITTEN_LOG.get().asItem())
			.unlockedBy("has_" + BlockInit.FROSTBITTEN_LOG.get().getRegistryName(), has(BlockInit.FROSTBITTEN_LOG.get().asItem())) // what unlocks the recipe when you get that item
			.pattern("aa ").pattern("aa ").save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.FROSTBITTEN_WOOD.get().getRegistryName().getPath()));
		
		// Frostbitten Wood
		ShapedRecipeBuilder.shaped(Blocks.PACKED_ICE) // add , int to get number of output
			.define('a', BlockInit.ICICLE.get().asItem())
			.unlockedBy("has_" + BlockInit.ICICLE.get().getRegistryName(), has(BlockInit.ICICLE.get().asItem())) // what unlocks the recipe when you get that item
			.pattern("aa ").pattern("aa ").save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ICICLE.get().getRegistryName().getPath() + "_into_packed_ice"));
		
		// Stripped Frostbitten Wood
		ShapedRecipeBuilder.shaped(BlockInit.STRIPPED_FROSTBITTEN_WOOD.get(), 3) // add , int to get number of output
			.define('a', BlockInit.STRIPPED_FROSTBITTEN_LOG.get().asItem())
			.unlockedBy("has_" + BlockInit.STRIPPED_FROSTBITTEN_LOG.get().getRegistryName(), has(BlockInit.STRIPPED_FROSTBITTEN_LOG.get().asItem())) // what unlocks the recipe when you get that item
			.pattern("aa ").pattern("aa ").save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.STRIPPED_FROSTBITTEN_WOOD.get().getRegistryName().getPath()));
		
		ShapedRecipeBuilder.shaped(BlockInit.COMPRESSED_ROTTING_SAND.get()) // add , int to get number of output
			.define('a', BlockInit.ROTTING_SAND.get().asItem())
			.unlockedBy("has_" + BlockInit.ROTTING_SAND.get().getRegistryName(), has(BlockInit.ROTTING_SAND.get().asItem())) // what unlocks the recipe when you get that item
			.pattern("aa ").pattern("aa ").save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.COMPRESSED_ROTTING_SAND.get().getRegistryName().getPath()));
		
		// Rottingwood Boat
		ShapedRecipeBuilder.shaped(Items.OAK_BOAT) // add , int to get number of output
			.define('a', BlockInit.ROTTING_PLANKS.get().asItem())
			.unlockedBy("has_" + BlockInit.ROTTING_PLANKS.get().getRegistryName(), has(BlockInit.ROTTING_PLANKS.get().asItem())) // what unlocks the recipe when you get that item
			.pattern("a a").pattern("aaa").save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_PLANKS.get().getRegistryName().getPath() +  "_boat"));
		
		// Slimy Boat
		ShapedRecipeBuilder.shaped(Items.OAK_BOAT) // add , int to get number of output
			.define('a', BlockInit.SLIMY_PLANKS.get().asItem())
			.unlockedBy("has_" + BlockInit.SLIMY_PLANKS.get().getRegistryName(), has(BlockInit.SLIMY_PLANKS.get().asItem())) // what unlocks the recipe when you get that item
			.pattern("a a").pattern("aaa").save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.SLIMY_PLANKS.get().getRegistryName().getPath() +  "_boat"));
		
		// Frostbitten Boat
		ShapedRecipeBuilder.shaped(Items.OAK_BOAT) // add , int to get number of output
			.define('a', BlockInit.FROSTBITTEN_PLANKS.get().asItem())
			.unlockedBy("has_" + BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName(), has(BlockInit.FROSTBITTEN_PLANKS.get().asItem())) // what unlocks the recipe when you get that item
			.pattern("a a").pattern("aaa").save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName().getPath() +  "_boat"));
		
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
		
		//Fester Brick Walls
		ShapedRecipeBuilder.shaped(BlockInit.FESTER_BRICK_WALL.get(), 6)
			.define('a', BlockInit.ROTTING_BRICKS.get().asItem())
			.unlockedBy("has_" + BlockInit.ROTTING_BRICKS.get().getRegistryName(), 
					has(BlockInit.ROTTING_BRICKS.get().asItem()))
			.pattern("aaa").pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.FESTER_BRICK_WALL.get().getRegistryName().getPath()));
		
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
		
		
		//Cubed Fungus Stairs
		ShapedRecipeBuilder.shaped(BlockInit.CUBED_FUNGUS_STAIRS.get(), 4)
			.define('a', BlockInit.CUBED_FUNGUS.get().asItem())
			.unlockedBy("has_" + BlockInit.CUBED_FUNGUS.get().getRegistryName(), 
					has(BlockInit.CUBED_FUNGUS.get().asItem()))
			.pattern("a  ").pattern("aa ").pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.CUBED_FUNGUS_STAIRS.get().getRegistryName().getPath()));
		
		//Cubed Fungus Slabs
		ShapedRecipeBuilder.shaped(BlockInit.CUBED_FUNGUS_SLAB.get(), 6)
			.define('a', BlockInit.CUBED_FUNGUS.get().asItem())
			.unlockedBy("has_" + BlockInit.CUBED_FUNGUS.get().getRegistryName(), 
					has(BlockInit.CUBED_FUNGUS.get().asItem()))
			.pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.CUBED_FUNGUS_SLAB.get().getRegistryName().getPath()));
		
		
		
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
		
		
		// Cubed Fence
		ShapedRecipeBuilder.shaped(BlockInit.CUBED_FUNGUS_FENCE.get(), 3)
			.define('a', BlockInit.CUBED_FUNGUS.get().asItem()).define('b', Items.STICK)
			.unlockedBy("has_" + BlockInit.CUBED_FUNGUS.get().getRegistryName(), 
					has(BlockInit.CUBED_FUNGUS.get().asItem()))
			.pattern("aba").pattern("aba").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.CUBED_FUNGUS_FENCE.get().getRegistryName().getPath()));
				
		// Cubed Fence Gate
		ShapedRecipeBuilder.shaped(BlockInit.CUBED_FUNGUS_GATE.get())
			.define('a', BlockInit.CUBED_FUNGUS.get().asItem()).define('b', Items.STICK)
			.unlockedBy("has_" + BlockInit.CUBED_FUNGUS.get().getRegistryName(), 
					has(BlockInit.CUBED_FUNGUS.get().asItem()))
			.pattern("bab").pattern("bab").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.CUBED_FUNGUS_GATE.get().getRegistryName().getPath()));
		
		// Frostbitten Fence
		ShapedRecipeBuilder.shaped(BlockInit.FROSTBITTEN_FENCE.get(), 3)
			.define('a', BlockInit.FROSTBITTEN_PLANKS.get().asItem()).define('b', Items.STICK)
			.unlockedBy("has_" + BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName(), 
					has(BlockInit.FROSTBITTEN_PLANKS.get().asItem()))
			.pattern("aba").pattern("aba").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.FROSTBITTEN_FENCE.get().getRegistryName().getPath()));
				
		// Frostbitten Fence Gate
		ShapedRecipeBuilder.shaped(BlockInit.FROSTBITTEN_FENCE_GATE.get())
			.define('a', BlockInit.FROSTBITTEN_PLANKS.get().asItem()).define('b', Items.STICK)
			.unlockedBy("has_" + BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName(), 
					has(BlockInit.FROSTBITTEN_PLANKS.get().asItem()))
			.pattern("bab").pattern("bab").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName().getPath()));
		
		//Frostbitten Stairs
		ShapedRecipeBuilder.shaped(BlockInit.FROSTBITTEN_STAIRS.get(), 4)
			.define('a', BlockInit.FROSTBITTEN_PLANKS.get().asItem())
			.unlockedBy("has_" + BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName(), 
					has(BlockInit.FROSTBITTEN_PLANKS.get().asItem()))
			.pattern("a  ").pattern("aa ").pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.FROSTBITTEN_STAIRS.get().getRegistryName().getPath()));
		
		//Frostbitten Slabs
		ShapedRecipeBuilder.shaped(BlockInit.FROSTBITTEN_SLAB.get(), 6)
			.define('a', BlockInit.FROSTBITTEN_PLANKS.get().asItem())
			.unlockedBy("has_" + BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName(), 
					has(BlockInit.FROSTBITTEN_PLANKS.get().asItem()))
			.pattern("aaa").save(consumer, 
					new ResourceLocation(TheFesterForest.MODID, 
							BlockInit.FROSTBITTEN_SLAB.get().getRegistryName().getPath()));
		
		
		
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
		
		// Rottingwood Pressure Plate
		ShapedRecipeBuilder.shaped(BlockInit.ROTTINGWOOD_PRESSURE_PLATE.get(), 1)
		.define('a', BlockInit.ROTTING_PLANKS.get().asItem())
		.unlockedBy("has_" + BlockInit.ROTTING_PLANKS.get().getRegistryName(), 
				has(BlockInit.ROTTING_PLANKS.get().asItem()))
		.pattern("aa ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.ROTTINGWOOD_PRESSURE_PLATE.get().getRegistryName().getPath()));
		
		// Rottingwood Door
		ShapedRecipeBuilder.shaped(BlockInit.ROTTINGWOOD_DOOR.get(), 3)
		.define('a', BlockInit.ROTTING_PLANKS.get().asItem())
		.unlockedBy("has_" + BlockInit.ROTTING_PLANKS.get().getRegistryName(), 
				has(BlockInit.ROTTING_PLANKS.get().asItem()))
		.pattern("aa ").pattern("aa ").pattern("aa ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.ROTTINGWOOD_DOOR.get().getRegistryName().getPath()));
		
		// Rottingwood Trapdoor
		ShapedRecipeBuilder.shaped(BlockInit.ROTTINGWOOD_TRAPDOOR.get(), 2)
		.define('a', BlockInit.ROTTING_PLANKS.get().asItem())
		.unlockedBy("has_" + BlockInit.ROTTING_PLANKS.get().getRegistryName(), 
				has(BlockInit.ROTTING_PLANKS.get().asItem()))
		.pattern("aaa").pattern("aaa").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.ROTTINGWOOD_TRAPDOOR.get().getRegistryName().getPath()));
		
		// Slimy Pressure Plate
		ShapedRecipeBuilder.shaped(BlockInit.SLIMY_PRESSURE_PLATE.get(), 1)
		.define('a', BlockInit.SLIMY_PLANKS.get().asItem())
		.unlockedBy("has_" + BlockInit.SLIMY_PLANKS.get().getRegistryName(), 
				has(BlockInit.SLIMY_PLANKS.get().asItem()))
		.pattern("aa ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.SLIMY_PRESSURE_PLATE.get().getRegistryName().getPath()));
		
		// Slimy Door
		ShapedRecipeBuilder.shaped(BlockInit.SLIMY_DOOR.get(), 3)
		.define('a', BlockInit.SLIMY_PLANKS.get().asItem())
		.unlockedBy("has_" + BlockInit.SLIMY_PLANKS.get().getRegistryName(), 
				has(BlockInit.SLIMY_PLANKS.get().asItem()))
		.pattern("aa ").pattern("aa ").pattern("aa ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.SLIMY_DOOR.get().getRegistryName().getPath()));
		
		// Slimy Trapdoor
		ShapedRecipeBuilder.shaped(BlockInit.SLIMY_TRAPDOOR.get(), 2)
		.define('a', BlockInit.SLIMY_PLANKS.get().asItem())
		.unlockedBy("has_" + BlockInit.SLIMY_PLANKS.get().getRegistryName(), 
				has(BlockInit.SLIMY_PLANKS.get().asItem()))
		.pattern("aaa").pattern("aaa").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.SLIMY_TRAPDOOR.get().getRegistryName().getPath()));
		
		// Frostbitten Pressure Plate
		ShapedRecipeBuilder.shaped(BlockInit.FROSTBITTEN_PRESSURE_PLATE.get(), 1)
		.define('a', BlockInit.FROSTBITTEN_PLANKS.get().asItem())
		.unlockedBy("has_" + BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName(), 
				has(BlockInit.FROSTBITTEN_PLANKS.get().asItem()))
		.pattern("aa ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.FROSTBITTEN_PRESSURE_PLATE.get().getRegistryName().getPath()));
		
		// Frostbitten Door
		ShapedRecipeBuilder.shaped(BlockInit.FROSTBITTEN_DOOR.get(), 3)
		.define('a', BlockInit.FROSTBITTEN_PLANKS.get().asItem())
		.unlockedBy("has_" + BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName(), 
				has(BlockInit.FROSTBITTEN_PLANKS.get().asItem()))
		.pattern("aa ").pattern("aa ").pattern("aa ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.FROSTBITTEN_DOOR.get().getRegistryName().getPath()));
		
		// Frostbitten Trapdoor
		ShapedRecipeBuilder.shaped(BlockInit.FROSTBITTEN_TRAPDOOR.get(), 2)
		.define('a', BlockInit.FROSTBITTEN_PLANKS.get().asItem())
		.unlockedBy("has_" + BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName(), 
				has(BlockInit.FROSTBITTEN_PLANKS.get().asItem()))
		.pattern("aaa").pattern("aaa").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.FROSTBITTEN_TRAPDOOR.get().getRegistryName().getPath()));
		
		// Cubed Fungus Pressure Plate
		ShapedRecipeBuilder.shaped(BlockInit.CUBED_FUNGUS_PRESSURE_PLATE.get(), 1)
		.define('a', BlockInit.CUBED_FUNGUS.get().asItem())
		.unlockedBy("has_" + BlockInit.CUBED_FUNGUS.get().getRegistryName(), 
				has(BlockInit.CUBED_FUNGUS.get().asItem()))
		.pattern("aa ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.CUBED_FUNGUS_PRESSURE_PLATE.get().getRegistryName().getPath()));
		
		// Cubed Fungus Door
		ShapedRecipeBuilder.shaped(BlockInit.CUBED_FUNGUS_DOOR.get(), 3)
		.define('a', BlockInit.CUBED_FUNGUS.get().asItem())
		.unlockedBy("has_" + BlockInit.CUBED_FUNGUS.get().getRegistryName(), 
				has(BlockInit.CUBED_FUNGUS.get().asItem()))
		.pattern("aa ").pattern("aa ").pattern("aa ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.CUBED_FUNGUS_DOOR.get().getRegistryName().getPath()));
		
		// Cubed Fungus Trapdoor
		ShapedRecipeBuilder.shaped(BlockInit.CUBED_FUNGUS_TRAPDOOR.get(), 2)
		.define('a', BlockInit.CUBED_FUNGUS.get().asItem())
		.unlockedBy("has_" + BlockInit.CUBED_FUNGUS.get().getRegistryName(), 
				has(BlockInit.CUBED_FUNGUS.get().asItem()))
		.pattern("aaa").pattern("aaa").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.CUBED_FUNGUS_TRAPDOOR.get().getRegistryName().getPath()));
		
		// Rotting Stone Pressure Plate
		ShapedRecipeBuilder.shaped(BlockInit.ROTTING_STONE_PRESSURE_PLATE.get(), 1)
		.define('a', BlockInit.ROTTING_STONE.get().asItem())
		.unlockedBy("has_" + BlockInit.ROTTING_STONE.get().getRegistryName(), 
				has(BlockInit.ROTTING_STONE.get().asItem()))
		.pattern("aa ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.ROTTING_STONE_PRESSURE_PLATE.get().getRegistryName().getPath()));
		
		// Compressed Rotting Sand Pressure Plate
		ShapedRecipeBuilder.shaped(BlockInit.COMPRESSED_ROTTING_SAND_PRESSURE_PLATE.get(), 1)
		.define('a', BlockInit.COMPRESSED_ROTTING_SAND.get().asItem())
		.unlockedBy("has_" + BlockInit.COMPRESSED_ROTTING_SAND.get().getRegistryName(), 
				has(BlockInit.COMPRESSED_ROTTING_SAND.get().asItem()))
		.pattern("aa ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.COMPRESSED_ROTTING_SAND_PRESSURE_PLATE.get().getRegistryName().getPath()));
		
		// Fester Brick Pressure Plate
		ShapedRecipeBuilder.shaped(BlockInit.FESTER_BRICK_PRESSURE_PLATE.get(), 1)
		.define('a', BlockInit.ROTTING_BRICKS.get().asItem())
		.unlockedBy("has_" + BlockInit.ROTTING_BRICKS.get().getRegistryName(), 
				has(BlockInit.ROTTING_BRICKS.get().asItem()))
		.pattern("aa ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						BlockInit.FESTER_BRICK_PRESSURE_PLATE.get().getRegistryName().getPath()));
		
		/*
		 *
		 * Magic Orbs
		 * 
		 */
		
		// Flame Orb
		ShapedRecipeBuilder.shaped(ItemInit.FLAME_ORB.get(), 1)
		.define('a', ItemInit.DULL_ORB.get().asItem()).define('b', Items.BLAZE_POWDER)
		.unlockedBy("has_" + ItemInit.DULL_ORB.get().getRegistryName(), 
				has(ItemInit.DULL_ORB.get().asItem()))
		.pattern(" b ").pattern("bab").pattern(" b ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.FLAME_ORB.get().getRegistryName().getPath()));
		
		// Levitate Orb
		ShapedRecipeBuilder.shaped(ItemInit.LEVITATE_ORB.get(), 1)
		.define('a', ItemInit.DULL_ORB.get().asItem()).define('b', Items.SHULKER_SHELL)
		.unlockedBy("has_" + ItemInit.DULL_ORB.get().getRegistryName(), 
				has(ItemInit.DULL_ORB.get().asItem()))
		.pattern(" b ").pattern("bab").pattern(" b ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.LEVITATE_ORB.get().getRegistryName().getPath()));
		
		// Poison Orb
		ShapedRecipeBuilder.shaped(ItemInit.POISON_ORB.get(), 1)
		.define('a', ItemInit.DULL_ORB.get().asItem()).define('b', Items.SPIDER_EYE)
		.unlockedBy("has_" + ItemInit.DULL_ORB.get().getRegistryName(), 
				has(ItemInit.DULL_ORB.get().asItem()))
		.pattern(" b ").pattern("bab").pattern(" b ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.POISON_ORB.get().getRegistryName().getPath()));
		
		// Ice Orb
		ShapedRecipeBuilder.shaped(ItemInit.FROZEN_ORB.get(), 1)
		.define('a', ItemInit.DULL_ORB.get().asItem()).define('b', Items.BLUE_ICE)
		.unlockedBy("has_" + ItemInit.DULL_ORB.get().getRegistryName(), 
				has(ItemInit.DULL_ORB.get().asItem()))
		.pattern(" b ").pattern("bab").pattern(" b ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.FROZEN_ORB.get().getRegistryName().getPath()));
		
		// Wither Orb
		ShapedRecipeBuilder.shaped(ItemInit.WITHER_ORB.get(), 1)
		.define('a', ItemInit.DULL_ORB.get().asItem()).define('b', Items.WITHER_ROSE)
		.unlockedBy("has_" + ItemInit.DULL_ORB.get().getRegistryName(), 
				has(ItemInit.DULL_ORB.get().asItem()))
		.pattern(" b ").pattern("bab").pattern(" b ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.WITHER_ORB.get().getRegistryName().getPath()));
		
		// Reetle Orb
		ShapedRecipeBuilder.shaped(ItemInit.REETLE_ORB.get(), 1)
		.define('a', ItemInit.DULL_ORB.get().asItem()).define('b', ItemInit.BUG_EGGS.get()).define('c', ItemInit.BUG_CARCASS.get())
		.unlockedBy("has_" + ItemInit.DULL_ORB.get().getRegistryName(), 
				has(ItemInit.DULL_ORB.get().asItem()))
		.pattern(" b ").pattern("cac").pattern(" c ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.REETLE_ORB.get().getRegistryName().getPath()));
		
		// Forgemaster Orb
		ShapedRecipeBuilder.shaped(ItemInit.FORGEMASTER_ORB.get(), 1)
		.define('a', ItemInit.DULL_ORB.get().asItem()).define('b', ItemInit.METAL_SCRAP.get())
		.unlockedBy("has_" + ItemInit.DULL_ORB.get().getRegistryName(), 
				has(ItemInit.DULL_ORB.get().asItem()))
		.pattern(" b ").pattern("bab").pattern(" b ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.FORGEMASTER_ORB.get().getRegistryName().getPath()));
		
		// Frostbitten Orb
		ShapedRecipeBuilder.shaped(ItemInit.FROSTBITTEN_ORB.get(), 1)
		.define('a', ItemInit.DULL_ORB.get().asItem()).define('b', ItemInit.FROZEN_SHARD.get())
		.unlockedBy("has_" + ItemInit.DULL_ORB.get().getRegistryName(), 
				has(ItemInit.DULL_ORB.get().asItem()))
		.pattern(" b ").pattern("bab").pattern(" b ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.FROSTBITTEN_ORB.get().getRegistryName().getPath()));
		
		// Rotfish Orb
		ShapedRecipeBuilder.shaped(ItemInit.ROTFISH_ORB.get(), 1)
		.define('a', ItemInit.DULL_ORB.get().asItem()).define('b', ItemInit.RAW_ROTFISH.get()).define('c', BlockInit.ROTTING_BONE_BLOCK.get())
		.unlockedBy("has_" + ItemInit.DULL_ORB.get().getRegistryName(), 
				has(ItemInit.DULL_ORB.get().asItem()))
		.pattern(" c ").pattern("bab").pattern(" b ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.ROTFISH_ORB.get().getRegistryName().getPath()));
		
		// Life Orb
		ShapedRecipeBuilder.shaped(ItemInit.LIFE_ORB.get(), 1) // add , int to get number of output
		.define('a', ItemInit.DULL_ORB.get().asItem())
		.define('b', BlockInit.ROTTING_FLOWER.get().asItem())
		.define('c', BlockInit.SICKENING_FLOWER.get().asItem())
		.define('d', BlockInit.SLIMY_FLOWER.get().asItem())
		.define('e', BlockInit.CORRODED_SHROOM.get().asItem())
		.define('f', BlockInit.WEEPING_GRASS.get().asItem())
		.define('g', BlockInit.FROSTVINE.get().asItem())
		.define('h', Items.BONE_MEAL)
		.unlockedBy("has_" + ItemInit.DULL_ORB.get().getRegistryName(), 
				has(ItemInit.DULL_ORB.get().asItem())) // what unlocks the recipe when you get that item
		.pattern("bcd").pattern("hah").pattern("efg").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, ItemInit.LIFE_ORB.get().getRegistryName().getPath()));
		
		/*
		 * Magic Weapons
		 */
		
		// Angelic Whistle
		ShapedRecipeBuilder.shaped(ItemInit.ANGELIC_WHISTLE.get(), 1) // add , int to get number of output
		.define('a', ItemInit.ANCIENT_WHISTLE.get().asItem())
		.define('b', Items.DIAMOND)
		.define('c', Items.FEATHER)
		.define('d', Items.IRON_INGOT)
		.unlockedBy("has_" + ItemInit.ANCIENT_WHISTLE.get().getRegistryName(), 
				has(ItemInit.ANCIENT_WHISTLE.get().asItem())) // what unlocks the recipe when you get that item
		.pattern(" d ").pattern("cac").pattern(" b ").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, ItemInit.ANGELIC_WHISTLE.get().getRegistryName().getPath()));
		
		// Verdant Branch
		ShapedRecipeBuilder.shaped(ItemInit.VERDANT_BRANCH.get(), 1) // add , int to get number of output
		.define('a', ItemInit.BRITTLE_BRANCH.get().asItem())
		.define('b', BlockInit.ROTTING_FLOWER.get().asItem())
		.define('c', BlockInit.SICKENING_FLOWER.get().asItem())
		.define('d', BlockInit.SLIMY_FLOWER.get().asItem())
		.define('e', BlockInit.CORRODED_SHROOM.get().asItem())
		.define('f', BlockInit.WEEPING_GRASS.get().asItem())
		.define('g', BlockInit.FROSTVINE.get().asItem())
		.define('h', Items.DIAMOND)
		.unlockedBy("has_" + ItemInit.BRITTLE_BRANCH.get().getRegistryName(), 
				has(ItemInit.BRITTLE_BRANCH.get().asItem())) // what unlocks the recipe when you get that item
		.pattern("bcd").pattern("hah").pattern("efg").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, ItemInit.VERDANT_BRANCH.get().getRegistryName().getPath()));
		
		/*
		 * 
		 * Forgemaster Crap
		 * 
		 */
		
		// boots
		ShapedRecipeBuilder.shaped(ItemInit.MECHANICAL_BOOTS.get(), 1)
		.define('a', ItemInit.METAL_SCRAP.get().asItem())
		.unlockedBy("has_" + ItemInit.METAL_SCRAP.get().getRegistryName(), 
				has(ItemInit.METAL_SCRAP.get().asItem()))
		.pattern("a a")
		.pattern("a a")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.MECHANICAL_BOOTS.get().getRegistryName().getPath()));
		
		// leggings
		ShapedRecipeBuilder.shaped(ItemInit.MECHANICAL_LEGGINGS.get(), 1)
		.define('a', ItemInit.METAL_SCRAP.get().asItem())
		.unlockedBy("has_" + ItemInit.METAL_SCRAP.get().getRegistryName(), 
				has(ItemInit.METAL_SCRAP.get().asItem()))
		.pattern("aaa")
		.pattern("a a")
		.pattern("a a")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.MECHANICAL_LEGGINGS.get().getRegistryName().getPath()));
		
		// chestplate
		ShapedRecipeBuilder.shaped(ItemInit.MECHANICAL_CHESTPLATE.get(), 1)
		.define('a', ItemInit.METAL_SCRAP.get().asItem())
		.unlockedBy("has_" + ItemInit.METAL_SCRAP.get().getRegistryName(), 
				has(ItemInit.METAL_SCRAP.get().asItem()))
		.pattern("a a")
		.pattern("aaa")
		.pattern("aaa")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.MECHANICAL_CHESTPLATE.get().getRegistryName().getPath()));
		
		// helmet
		ShapedRecipeBuilder.shaped(ItemInit.MECHANICAL_HELMET.get(), 1)
		.define('a', ItemInit.METAL_SCRAP.get().asItem())
		.unlockedBy("has_" + ItemInit.METAL_SCRAP.get().getRegistryName(), 
				has(ItemInit.METAL_SCRAP.get().asItem()))
		.pattern("aaa")
		.pattern("a a")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.MECHANICAL_HELMET.get().getRegistryName().getPath()));
		
		// hammer
		ShapedRecipeBuilder.shaped(ItemInit.FORGEMASTER_HAMMER.get(), 1)
		.define('a', ItemInit.METAL_SCRAP.get().asItem()).define('b', Items.STICK)
		.unlockedBy("has_" + ItemInit.METAL_SCRAP.get().getRegistryName(), 
				has(ItemInit.METAL_SCRAP.get().asItem()))
		.pattern("aaa")
		.pattern(" b ")
		.pattern(" b ")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.FORGEMASTER_HAMMER.get().getRegistryName().getPath()));
		
		// heart
		ShapedRecipeBuilder.shaped(ItemInit.FORGEMASTER_HEART.get(), 1)
		.define('a', ItemInit.METAL_SCRAP.get().asItem()).define('b', Items.FIRE_CHARGE)
		.unlockedBy("has_" + ItemInit.METAL_SCRAP.get().getRegistryName(), 
				has(ItemInit.METAL_SCRAP.get().asItem()))
		.pattern("aaa")
		.pattern("aba")
		.pattern("aaa")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.FORGEMASTER_HEART.get().getRegistryName().getPath()));
		
		// meteor wand
		ShapedRecipeBuilder.shaped(ItemInit.METEOR_WAND.get(), 1)
		.define('a', ItemInit.METAL_SCRAP.get().asItem()).define('b', Items.FIRE_CHARGE)
		.unlockedBy("has_" + ItemInit.METAL_SCRAP.get().getRegistryName(), 
				has(ItemInit.METAL_SCRAP.get().asItem()))
		.pattern("  b")
		.pattern(" a ")
		.pattern("a  ")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.METEOR_WAND.get().getRegistryName().getPath()));
		
		// shield book
		ShapedRecipeBuilder.shaped(ItemInit.SHIELD_BOOK.get(), 1)
		.define('a', ItemInit.METAL_SCRAP.get().asItem()).define('b', Items.BOOK)
		.unlockedBy("has_" + ItemInit.METAL_SCRAP.get().getRegistryName(), 
				has(ItemInit.METAL_SCRAP.get().asItem()))
		.pattern(" a ")
		.pattern("aba")
		.pattern(" a ")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.SHIELD_BOOK.get().getRegistryName().getPath()));
		
		// eyedbarl
		ShapedRecipeBuilder.shaped(ItemInit.MECHANICAL_EYE.get(), 1)
		.define('a', ItemInit.METAL_SCRAP.get().asItem()).define('b', Items.GOLDEN_CARROT)
		.unlockedBy("has_" + ItemInit.METAL_SCRAP.get().getRegistryName(), 
				has(ItemInit.METAL_SCRAP.get().asItem()))
		.pattern(" a ")
		.pattern("aba")
		.pattern(" a ")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.MECHANICAL_EYE.get().getRegistryName().getPath()));
		
		// mechanical apple
		ShapedRecipeBuilder.shaped(ItemInit.MECHANICAL_APPLE.get(), 8)
		.define('a', ItemInit.METAL_SCRAP.get().asItem()).define('b', Items.APPLE)
		.unlockedBy("has_" + ItemInit.METAL_SCRAP.get().getRegistryName(), 
				has(ItemInit.METAL_SCRAP.get().asItem()))
		.pattern(" a ")
		.pattern("aba")
		.pattern(" a ")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.MECHANICAL_APPLE.get().getRegistryName().getPath()));
		
		/*
		 * 
		 * Frostbitten King stuff
		 * 
		 */
		
		// food book
		ShapedRecipeBuilder.shaped(ItemInit.FOOD_BOOK.get(), 1)
		.define('a', ItemInit.FROZEN_SHARD.get().asItem()).define('b', Items.BOOK)
		.unlockedBy("has_" + ItemInit.FROZEN_SHARD.get().getRegistryName(), 
				has(ItemInit.FROZEN_SHARD.get().asItem()))
		.pattern(" a ")
		.pattern("aba")
		.pattern(" a ")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.FOOD_BOOK.get().getRegistryName().getPath()));
		
		// ice book
		ShapedRecipeBuilder.shaped(ItemInit.ICE_BOOK.get(), 1)
		.define('a', ItemInit.FROZEN_SHARD.get().asItem()).define('b', Items.BOOK)
		.unlockedBy("has_" + ItemInit.FROZEN_SHARD.get().getRegistryName(), 
				has(ItemInit.FROZEN_SHARD.get().asItem()))
		.pattern("aaa")
		.pattern(" b ")
		.pattern(" a ")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.ICE_BOOK.get().getRegistryName().getPath()));
		
		// shard of ice
		ShapedRecipeBuilder.shaped(ItemInit.SHARD_OF_ICE.get(), 1)
		.define('a', ItemInit.FROZEN_SHARD.get().asItem()).define('b', Items.STICK)
		.unlockedBy("has_" + ItemInit.FROZEN_SHARD.get().getRegistryName(), 
				has(ItemInit.FROZEN_SHARD.get().asItem()))
		.pattern(" a ")
		.pattern(" a ")
		.pattern("aba")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.SHARD_OF_ICE.get().getRegistryName().getPath()));
		
		// frozen heart
		ShapedRecipeBuilder.shaped(ItemInit.FROZEN_HEART.get(), 1)
		.define('a', ItemInit.FROZEN_SHARD.get().asItem()).define('b', Items.PACKED_ICE)
		.unlockedBy("has_" + ItemInit.FROZEN_SHARD.get().getRegistryName(), 
				has(ItemInit.FROZEN_SHARD.get().asItem()))
		.pattern("aaa")
		.pattern("aba")
		.pattern("aaa")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.FROZEN_HEART.get().getRegistryName().getPath()));
		
		// frozen helmet
		ShapedRecipeBuilder.shaped(ItemInit.FROZEN_HELMET.get(), 1)
		.define('a', ItemInit.FROZEN_SHARD.get().asItem())
		.unlockedBy("has_" + ItemInit.FROZEN_SHARD.get().getRegistryName(), 
				has(ItemInit.FROZEN_SHARD.get().asItem()))
		.pattern("aaa")
		.pattern("a a")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.FROZEN_HELMET.get().getRegistryName().getPath()));
		
		// frozen chestplate
		ShapedRecipeBuilder.shaped(ItemInit.FROZEN_CHESTPLATE.get(), 1)
		.define('a', ItemInit.FROZEN_SHARD.get().asItem())
		.unlockedBy("has_" + ItemInit.FROZEN_SHARD.get().getRegistryName(), 
				has(ItemInit.FROZEN_SHARD.get().asItem()))
		.pattern("a a")
		.pattern("aaa")
		.pattern("aaa")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.FROZEN_CHESTPLATE.get().getRegistryName().getPath()));
		
		// frozen leggings
		ShapedRecipeBuilder.shaped(ItemInit.FROZEN_LEGGINGS.get(), 1)
		.define('a', ItemInit.FROZEN_SHARD.get().asItem())
		.unlockedBy("has_" + ItemInit.FROZEN_SHARD.get().getRegistryName(), 
				has(ItemInit.FROZEN_SHARD.get().asItem()))
		.pattern("aaa")
		.pattern("a a")
		.pattern("a a")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.FROZEN_LEGGINGS.get().getRegistryName().getPath()));
		
		// frozen boots
		ShapedRecipeBuilder.shaped(ItemInit.FROZEN_BOOTS.get(), 1)
		.define('a', ItemInit.FROZEN_SHARD.get().asItem())
		.unlockedBy("has_" + ItemInit.FROZEN_SHARD.get().getRegistryName(), 
				has(ItemInit.FROZEN_SHARD.get().asItem()))
		.pattern("a a")
		.pattern("a a")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.FROZEN_BOOTS.get().getRegistryName().getPath()));
		
		// frostbitten bow
		ShapedRecipeBuilder.shaped(ItemInit.FROZEN_BOW.get(), 1)
		.define('a', ItemInit.FROZEN_SHARD.get().asItem()).define('b', Items.STRING)
		.unlockedBy("has_" + ItemInit.FROZEN_SHARD.get().getRegistryName(), 
				has(ItemInit.FROZEN_SHARD.get().asItem()))
		.pattern(" ab")
		.pattern("a b")
		.pattern(" ab")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.FROZEN_BOW.get().getRegistryName().getPath()));
		
		// frozen apple
		ShapedRecipeBuilder.shaped(ItemInit.FROZEN_APPLE.get(), 8)
		.define('a', ItemInit.FROZEN_SHARD.get().asItem()).define('b', Items.APPLE)
		.unlockedBy("has_" + ItemInit.FROZEN_SHARD.get().getRegistryName(), 
				has(ItemInit.FROZEN_SHARD.get().asItem()))
		.pattern(" a ")
		.pattern("aba")
		.pattern(" a ")
		.save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, 
						ItemInit.FROZEN_APPLE.get().getRegistryName().getPath()));
		
		/*
		 * 
		 * Baubles
		 * 
		 */
		
		// Life Orb
		ShapedRecipeBuilder.shaped(ItemInit.FLOWER_CROWN.get(), 1) // add , int to get number of output
		.define('b', BlockInit.ROTTING_FLOWER.get().asItem())
		.define('c', BlockInit.SICKENING_FLOWER.get().asItem())
		.define('d', BlockInit.SLIMY_FLOWER.get().asItem())
		.define('e', BlockInit.CORRODED_SHROOM.get().asItem())
		.define('f', BlockInit.WEEPING_GRASS.get().asItem())
		.define('g', BlockInit.FROSTVINE.get().asItem())
		.define('h', Items.EXPERIENCE_BOTTLE)
		.unlockedBy("has_" + Items.EXPERIENCE_BOTTLE.getRegistryName(), 
				has(Items.EXPERIENCE_BOTTLE.asItem())) // what unlocks the recipe when you get that item
		.pattern("bcd").pattern("h h").pattern("efg").save(consumer, 
				new ResourceLocation(TheFesterForest.MODID, ItemInit.FLOWER_CROWN.get().getRegistryName().getPath()));
		
		/*
		 * 
		 * Shapeless
		 * 
		 */
		
		// Rottingwood Button (Shapeless)
		ShapelessRecipeBuilder.shapeless(BlockInit.ROTTINGWOOD_BUTTON.get(), 1).requires(BlockInit.ROTTING_PLANKS.get()) //get item
			.unlockedBy("has_" + BlockInit.ROTTING_PLANKS.get().getRegistryName(), has(BlockInit.ROTTING_PLANKS.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTINGWOOD_BUTTON.get().getRegistryName().getPath()));
		
		// Slimy Button (Shapeless)
		ShapelessRecipeBuilder.shapeless(BlockInit.SLIMY_BUTTON.get(), 1).requires(BlockInit.SLIMY_PLANKS.get()) //get item
			.unlockedBy("has_" + BlockInit.SLIMY_PLANKS.get().getRegistryName(), has(BlockInit.SLIMY_PLANKS.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.SLIMY_BUTTON.get().getRegistryName().getPath()));
		
		// Frostbitten Button (Shapeless)
		ShapelessRecipeBuilder.shapeless(BlockInit.FROSTBITTEN_BUTTON.get(), 1).requires(BlockInit.FROSTBITTEN_PLANKS.get()) //get item
			.unlockedBy("has_" + BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName(), has(BlockInit.FROSTBITTEN_PLANKS.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.FROSTBITTEN_BUTTON.get().getRegistryName().getPath()));
		
		// Cubed Fungus Button (Shapeless)
		ShapelessRecipeBuilder.shapeless(BlockInit.CUBED_FUNGUS_BUTTON.get(), 1).requires(BlockInit.CUBED_FUNGUS.get()) //get item
			.unlockedBy("has_" + BlockInit.CUBED_FUNGUS.get().getRegistryName(), has(BlockInit.CUBED_FUNGUS.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.CUBED_FUNGUS_BUTTON.get().getRegistryName().getPath()));
		
		// Rotting Stone Button (Shapeless)
		ShapelessRecipeBuilder.shapeless(BlockInit.ROTTING_STONE_BUTTON.get(), 1).requires(BlockInit.ROTTING_STONE.get()) //get item
			.unlockedBy("has_" + BlockInit.ROTTING_STONE.get().getRegistryName(), has(BlockInit.ROTTING_STONE.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_STONE_BUTTON.get().getRegistryName().getPath()));
		
		// Compressed Rotting Sand Button (Shapeless)
		ShapelessRecipeBuilder.shapeless(BlockInit.COMPRESSED_ROTTING_SAND_BUTTON.get(), 1).requires(BlockInit.COMPRESSED_ROTTING_SAND.get()) //get item
			.unlockedBy("has_" + BlockInit.COMPRESSED_ROTTING_SAND.get().getRegistryName(), has(BlockInit.COMPRESSED_ROTTING_SAND.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.COMPRESSED_ROTTING_SAND_BUTTON.get().getRegistryName().getPath()));
		
		// Rotting Bricks from Rotting Brick Block
		ShapelessRecipeBuilder.shapeless(ItemInit.ROTTING_BRICK.get().asItem(), 4).requires(BlockInit.ROTTING_BRICKS.get()) //get item
			.unlockedBy("has_" + BlockInit.ROTTING_BRICKS.get().getRegistryName(), has(BlockInit.ROTTING_BRICKS.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.ROTTING_BRICK.get().getRegistryName().getPath()));
		
		// Frostbitten Planks from Logs
		ShapelessRecipeBuilder.shapeless(BlockInit.FROSTBITTEN_PLANKS.get().asItem(), 4).requires(BlockInit.FROSTBITTEN_LOG.get()) //get item
			.unlockedBy("has_" + BlockInit.FROSTBITTEN_LOG.get().getRegistryName(), has(BlockInit.FROSTBITTEN_LOG.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName().getPath() + "_log"));
		
		// Frostbitten Planks from Stripped Logs
		ShapelessRecipeBuilder.shapeless(BlockInit.FROSTBITTEN_PLANKS.get().asItem(), 4).requires(BlockInit.STRIPPED_FROSTBITTEN_LOG.get()) //get item
			.unlockedBy("has_" + BlockInit.STRIPPED_FROSTBITTEN_LOG.get().getRegistryName(), has(BlockInit.STRIPPED_FROSTBITTEN_LOG.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName().getPath() + "_stripped_log"));
		
		// Frostbitten Planks from Wood
		ShapelessRecipeBuilder.shapeless(BlockInit.FROSTBITTEN_PLANKS.get().asItem(), 4).requires(BlockInit.FROSTBITTEN_WOOD.get()) //get item
			.unlockedBy("has_" + BlockInit.FROSTBITTEN_WOOD.get().getRegistryName(), has(BlockInit.FROSTBITTEN_WOOD.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName().getPath() + "_wood"));
		
		// Frostbitten Planks from Stripped Wood
		ShapelessRecipeBuilder.shapeless(BlockInit.FROSTBITTEN_PLANKS.get().asItem(), 4).requires(BlockInit.STRIPPED_FROSTBITTEN_WOOD.get()) //get item
			.unlockedBy("has_" + BlockInit.STRIPPED_FROSTBITTEN_WOOD.get().getRegistryName(), has(BlockInit.STRIPPED_FROSTBITTEN_WOOD.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.FROSTBITTEN_PLANKS.get().getRegistryName().getPath() + "_stripped_wood"));
		
		// Shroom Cluster
		ShapelessRecipeBuilder.shapeless(ItemInit.SHROOM_CLUSTER.get().asItem()).requires(BlockInit.CORRODED_SHROOM.get(), 4) //get item
			.unlockedBy("has_" + BlockInit.CORRODED_SHROOM.get().getRegistryName(), has(BlockInit.CORRODED_SHROOM.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.SHROOM_CLUSTER.get().getRegistryName().getPath() + "_from_shrooms"));
		
		// Rotfish Special
		ShapelessRecipeBuilder.shapeless(ItemInit.ROTFISH_SPECIAL.get().asItem()).requires(ItemInit.COOKED_ROTFISH.get()).requires(BlockInit.CORRODED_SHROOM.get(), 2) //get item
			.unlockedBy("has_" + ItemInit.COOKED_ROTFISH.get().getRegistryName(), has(ItemInit.COOKED_ROTFISH.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.ROTFISH_SPECIAL.get().getRegistryName().getPath() + "_crafting"));
		
		// Orange Shroom to Mushrooms
		ShapelessRecipeBuilder.shapeless(BlockInit.CORRODED_SHROOM.get().asItem(), 3).requires(BlockInit.ORANGE_FESTER_SHROOM_BLOCK.get()) //get item
		.unlockedBy("has_" + BlockInit.ORANGE_FESTER_SHROOM_BLOCK.get().getRegistryName(), has(BlockInit.ORANGE_FESTER_SHROOM_BLOCK.get().asItem()))
		.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ORANGE_FESTER_SHROOM_BLOCK.get().getRegistryName().getPath() + "_to_shroom"));
		
		// Blue Shroom to Mushrooms
		ShapelessRecipeBuilder.shapeless(BlockInit.CORRODED_SHROOM.get().asItem(), 3).requires(BlockInit.BLUE_FESTER_SHROOM_BLOCK.get()) //get item
		.unlockedBy("has_" + BlockInit.BLUE_FESTER_SHROOM_BLOCK.get().getRegistryName(), has(BlockInit.BLUE_FESTER_SHROOM_BLOCK.get().asItem()))
		.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.BLUE_FESTER_SHROOM_BLOCK.get().getRegistryName().getPath() + "_to_shroom"));
		
		// Cubed Fungus
		ShapelessRecipeBuilder.shapeless(BlockInit.CUBED_FUNGUS.get().asItem(), 4).requires(BlockInit.FESTER_SHROOM_STEM.get()) //get item
		.unlockedBy("has_" + BlockInit.FESTER_SHROOM_STEM.get().getRegistryName(), has(BlockInit.FESTER_SHROOM_STEM.get().asItem()))
		.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.FESTER_SHROOM_STEM.get().getRegistryName().getPath() + "_to_cubed_fungus"));
		
		// Rotting Flower to Purple
		ShapelessRecipeBuilder.shapeless(Items.PURPLE_DYE, 1).requires(BlockInit.ROTTING_FLOWER.get()) //get item
			.unlockedBy("has_" + BlockInit.ROTTING_FLOWER.get().getRegistryName(), has(BlockInit.ROTTING_FLOWER.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_FLOWER.get().getRegistryName().getPath() + "_to_purple_dye"));
		
		// Frostvine to Green
		ShapelessRecipeBuilder.shapeless(Items.GREEN_DYE, 1).requires(BlockInit.FROSTVINE.get()) //get item
			.unlockedBy("has_" + BlockInit.FROSTVINE.get().getRegistryName(), has(BlockInit.FROSTVINE.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.FROSTVINE.get().getRegistryName().getPath() + "_to_green_dye"));
		
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
		
		// Goopy Stone
		ShapelessRecipeBuilder.shapeless(ItemInit.GOOPY_STONE.get())
			.requires(ItemInit.HEAVY_STONE.get()).requires(ItemInit.SLIPPERY_GOOP.get())
			.unlockedBy("has_" + ItemInit.HEAVY_STONE.get().getRegistryName(), 
					has(ItemInit.HEAVY_STONE.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.GOOPY_STONE.get()
					.getRegistryName().getPath()));
		
		/*
		 * 
		 * Purifying Recipes
		 * 
		 */
		
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
		
		// Purifying Rotting Bone Block
		ShapelessRecipeBuilder.shapeless(Blocks.BONE_BLOCK, 3)
			.requires(ItemInit.PURIFYING_POWDER.get()).requires(BlockInit.ROTTING_BONE_BLOCK.get(), 3)
			.unlockedBy("has_" + ItemInit.PURIFYING_POWDER.get().getRegistryName(), 
					has(ItemInit.PURIFYING_POWDER.get()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.PURIFYING_POWDER.get()
					.getRegistryName().getPath() + "_rotting_bone_block"));
		
		//Cooking Example
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemInit.FESTER_CHUNK.get()),
			ItemInit.ROTTING_BRICK.get(), 15, 200)
			.unlockedBy("has_" + BlockInit.FESTER_ORE.get().getRegistryName(), has(BlockInit.FESTER_ORE.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.FESTER_ORE.get().getRegistryName().getPath() + "_smelting"));
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockInit.FESTER_ORE.get().asItem()),
			ItemInit.ROTTING_BRICK.get(), 15, 200)
			.unlockedBy("has_" + BlockInit.FESTER_ORE.get().getRegistryName(), has(BlockInit.FESTER_ORE.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.FESTER_ORE.get().getRegistryName().getPath() + "_smelting_only_ore"));
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemInit.SHROOM_CLUSTER.get()),
				ItemInit.COOKED_SHROOM_CLUSTER.get(), 15, 200)
				.unlockedBy("has_" + ItemInit.SHROOM_CLUSTER.get().getRegistryName(), has(ItemInit.SHROOM_CLUSTER.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.COOKED_SHROOM_CLUSTER.get().getRegistryName().getPath() + "_cooking"));
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockInit.ROTTING_STONE.get().asItem()),
			BlockInit.CRACKED_ROTTING_STONE.get(), 1, 200)
			.unlockedBy("has_" + BlockInit.ROTTING_STONE.get().getRegistryName(), has(BlockInit.ROTTING_STONE.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_STONE.get().getRegistryName().getPath() + "_smelting"));
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockInit.ROTTING_STONE_BRICKS.get().asItem()),
				BlockInit.CRACKED_ROTTING_STONE_BRICKS.get(), 1, 200)
				.unlockedBy("has_" + BlockInit.ROTTING_STONE_BRICKS.get().getRegistryName(), has(BlockInit.ROTTING_STONE_BRICKS.get().asItem()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.CRACKED_ROTTING_STONE_BRICKS.get().getRegistryName().getPath() + "_smelting"));
		
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
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemInit.ROTTING_SLIMEBALL.get()),
				ItemInit.GOOPY_JELLO.get(), 1, 200)
				.unlockedBy("has_" + ItemInit.ROTTING_SLIMEBALL.get().getRegistryName(), has(ItemInit.ROTTING_SLIMEBALL.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, ItemInit.ROTTING_SLIMEBALL.get().getRegistryName().getPath() + "_smelting_rotting_slimeball"));
		
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
		
		/*
		 * Stonecutting Rotting Stone Bricks
		 */
		
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(BlockInit.ROTTING_STONE_BRICKS.get()), BlockInit.ROTTING_STONE_BRICK_SLAB.get().asItem(), 2)
				.unlockedBy("has_" + BlockInit.ROTTING_STONE_BRICKS.get().getRegistryName(), has(BlockInit.ROTTING_STONE_BRICKS.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_STONE_BRICKS.get().getRegistryName().getPath() + "_stonecutting_slab"));
		
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(BlockInit.ROTTING_STONE_BRICKS.get()), BlockInit.ROTTING_STONE_BRICK_STAIRS.get().asItem())
				.unlockedBy("has_" + BlockInit.ROTTING_STONE_BRICKS.get().getRegistryName(), has(BlockInit.ROTTING_STONE_BRICKS.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_STONE_BRICKS.get().getRegistryName().getPath() + "_stonecutting_stairs"));
		
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(BlockInit.ROTTING_STONE_BRICKS.get()), BlockInit.ROTTING_STONE_BRICK_WALL.get().asItem())
				.unlockedBy("has_" + BlockInit.ROTTING_STONE_BRICKS.get().getRegistryName(), has(BlockInit.ROTTING_STONE_BRICKS.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_STONE_BRICKS.get().getRegistryName().getPath() + "_stonecutting_wall"));
		
		/*
		 * Stonecutting Rotting Stone
		 */
		
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(BlockInit.ROTTING_STONE.get()), BlockInit.ROTTING_STONE_BRICKS.get().asItem())
				.unlockedBy("has_" + BlockInit.ROTTING_STONE.get().getRegistryName(), has(BlockInit.ROTTING_STONE.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_STONE.get().getRegistryName().getPath() + "_stonecutting_bricks"));
		
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(BlockInit.ROTTING_STONE.get()), BlockInit.ROTTING_STONE_BRICK_SLAB.get().asItem(), 2)
				.unlockedBy("has_" + BlockInit.ROTTING_STONE.get().getRegistryName(), has(BlockInit.ROTTING_STONE.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_STONE.get().getRegistryName().getPath() + "_stonecutting_slab"));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(BlockInit.ROTTING_STONE.get()), BlockInit.ROTTING_STONE_BRICK_STAIRS.get().asItem())
				.unlockedBy("has_" + BlockInit.ROTTING_STONE.get().getRegistryName(), has(BlockInit.ROTTING_STONE.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_STONE.get().getRegistryName().getPath() + "_stonecutting_stairs"));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(BlockInit.ROTTING_STONE.get()), BlockInit.ROTTING_STONE_BRICK_WALL.get().asItem())
				.unlockedBy("has_" + BlockInit.ROTTING_STONE.get().getRegistryName(), has(BlockInit.ROTTING_STONE.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_STONE.get().getRegistryName().getPath() + "_stonecutting_wall"));
		
		/*
		 * Stonecutting Compressed Rotting Sand
		 */

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(BlockInit.COMPRESSED_ROTTING_SAND.get()), BlockInit.COMPRESSED_ROTTING_SAND_SLAB.get().asItem(), 2)
				.unlockedBy("has_" + BlockInit.COMPRESSED_ROTTING_SAND.get().getRegistryName(), has(BlockInit.COMPRESSED_ROTTING_SAND.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.COMPRESSED_ROTTING_SAND.get().getRegistryName().getPath() + "_stonecutting_slab"));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(BlockInit.COMPRESSED_ROTTING_SAND.get()), BlockInit.COMPRESSED_ROTTING_SAND_STAIRS.get().asItem())
				.unlockedBy("has_" + BlockInit.COMPRESSED_ROTTING_SAND.get().getRegistryName(), has(BlockInit.COMPRESSED_ROTTING_SAND.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.COMPRESSED_ROTTING_SAND.get().getRegistryName().getPath() + "_stonecutting_stairs"));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(BlockInit.COMPRESSED_ROTTING_SAND.get()), BlockInit.COMPRESSED_ROTTING_SAND_WALL.get().asItem())
				.unlockedBy("has_" + BlockInit.COMPRESSED_ROTTING_SAND.get().getRegistryName(), has(BlockInit.COMPRESSED_ROTTING_SAND.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.COMPRESSED_ROTTING_SAND.get().getRegistryName().getPath() + "_stonecutting_wall"));
		
		/*
		 * Fester Bricks
		 */
		
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(BlockInit.ROTTING_BRICKS.get()), BlockInit.FESTER_BRICK_SLAB.get().asItem(), 2)
				.unlockedBy("has_" + BlockInit.ROTTING_BRICKS.get().getRegistryName(), has(BlockInit.ROTTING_BRICKS.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_BRICKS.get().getRegistryName().getPath() + "_stonecutting_slab"));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(BlockInit.ROTTING_BRICKS.get()), BlockInit.FESTER_BRICK_STAIRS.get().asItem())
				.unlockedBy("has_" + BlockInit.ROTTING_BRICKS.get().getRegistryName(), has(BlockInit.ROTTING_BRICKS.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_BRICKS.get().getRegistryName().getPath() + "_stonecutting_stairs"));
		
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(BlockInit.ROTTING_BRICKS.get()), BlockInit.FESTER_BRICK_WALL.get().asItem())
				.unlockedBy("has_" + BlockInit.ROTTING_BRICKS.get().getRegistryName(), has(BlockInit.ROTTING_BRICKS.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.ROTTING_BRICKS.get().getRegistryName().getPath() + "_stonecutting_wall"));
		
		/*
		 * Crushed Rotting Stone Bricks
		 */
		
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(BlockInit.CRUSHED_ROTTING_STONE.get()), BlockInit.ROTTING_STONE.get().asItem())
				.unlockedBy("has_" + BlockInit.CRUSHED_ROTTING_STONE.get().getRegistryName(), has(BlockInit.CRUSHED_ROTTING_STONE.get()))
				.save(consumer, new ResourceLocation(TheFesterForest.MODID, BlockInit.CRUSHED_ROTTING_STONE.get().getRegistryName().getPath() + "_stonecutting_crushed_to_normal"));
	
		
	}
	
}
