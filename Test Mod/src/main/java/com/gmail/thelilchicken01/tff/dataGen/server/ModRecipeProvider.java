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
		ShapedRecipeBuilder.shaped(BlockInit.rotting_bricks.get(), 4) // add , int to get number of output
			.define('a', ItemInit.rotting_brick.get().asItem())
			.unlockedBy("has_" + ItemInit.rotting_brick.get().getRegistryName(), has(ItemInit.rotting_brick.get().asItem())) // what unlocks the recipe when you get that item
			.pattern("aa ").pattern("aa ").save(consumer, new ResourceLocation(TheFesterForest.modid, BlockInit.rotting_bricks.get().getRegistryName().getPath()));
		
		ShapedRecipeBuilder.shaped(BlockInit.rotting_stone_bricks.get())
			.define('a', BlockInit.rotting_stone.get().asItem())
			.unlockedBy("has_" + BlockInit.rotting_stone.get().getRegistryName(), has(BlockInit.rotting_stone.get().asItem()))
			.pattern("aa ").pattern("aa ").save(consumer, new ResourceLocation(TheFesterForest.modid, BlockInit.rotting_stone_bricks.get().getRegistryName().getPath()));
		
		ShapedRecipeBuilder.shaped(ItemInit.angelic_whistle.get(), 1) // add , int to get number of output
		.define('a', ItemInit.ancient_whistle.get().asItem())
		.define('b', Items.NETHER_STAR)
		.define('c', Items.FEATHER)
		.define('d', Items.IRON_INGOT)
		.unlockedBy("has_" + ItemInit.ancient_whistle.get().getRegistryName(), 
				has(ItemInit.ancient_whistle.get().asItem())) // what unlocks the recipe when you get that item
		.pattern(" d ").pattern("cac").pattern(" b ").save(consumer, 
				new ResourceLocation(TheFesterForest.modid, ItemInit.angelic_whistle.get().getRegistryName().getPath()));
		
		// Shapeless
		ShapelessRecipeBuilder.shapeless(ItemInit.rotting_brick.get().asItem(), 4).requires(BlockInit.rotting_bricks.get()) //get item
			.unlockedBy("has_" + BlockInit.rotting_bricks.get().getRegistryName(), has(BlockInit.rotting_bricks.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.modid, ItemInit.rotting_brick.get().getRegistryName().getPath()));
		
		ShapelessRecipeBuilder.shapeless(BlockInit.mossy_rotting_stone.get().asItem(), 1).requires(BlockInit.rotting_stone.get()).requires(Blocks.VINE)
			.unlockedBy("has_" + BlockInit.rotting_stone.get().getRegistryName(), has(BlockInit.rotting_stone.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.modid, BlockInit.mossy_rotting_stone.get().getRegistryName().getPath()));
		
		//Cooking Example
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockInit.fester_ore.get().asItem()),
			ItemInit.rotting_brick.get(), 15, 100)
			.unlockedBy("has_" + BlockInit.fester_ore.get().getRegistryName(), has(BlockInit.fester_ore.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.modid, BlockInit.fester_ore.get().getRegistryName().getPath() + "_smelting"));
		
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockInit.rotting_stone.get().asItem()),
			BlockInit.cracked_rotting_stone.get(), 1, 100)
			.unlockedBy("has_" + BlockInit.rotting_stone.get().getRegistryName(), has(BlockInit.rotting_stone.get().asItem()))
			.save(consumer, new ResourceLocation(TheFesterForest.modid, BlockInit.rotting_stone.get().getRegistryName().getPath() + "_smelting"));
	
		
	}
	
}
