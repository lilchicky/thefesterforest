package com.gmail.thelilchicken01.tff.world.feature;

import java.util.List;

import com.gmail.thelilchicken01.tff.config.TFFCommonConfigs;
import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModConfiguredFeatures {
	
	//Rottingwood Tree
	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> rottingwood_tree = 
			FeatureUtils.register("rottingwood", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(BlockInit.rotting_log.get()), //Block that makes the trunk
					new GiantTrunkPlacer(8, 12, 13), //place trunks straight up (middle click for more)
					BlockStateProvider.simple(BlockInit.rotting_leaves.get()), //Block that makes the leaves
					new MegaPineFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), ConstantInt.of((int)(Math.random() *10) + 8)), //how are the leaves placed (middle click for more) WIDTH, HEIGHT OFF TRUNK, HEIGHT
					new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(BlockInit.rotting_dirt.get())).build());
	
	public static final Holder<PlacedFeature> rottingwood_tree_checked = PlacementUtils.register("rottingwood_tree_checked", rottingwood_tree, 
			PlacementUtils.filteredByBlockSurvival(BlockInit.rottingwood_sapling.get()));
	
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> rottingwood_tree_spawn = 
			FeatureUtils.register("rottingwood_tree_spawn", Feature.RANDOM_SELECTOR,
					new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(rottingwood_tree_checked, 0.5F)), rottingwood_tree_checked));
	
	//Slimy Tree
	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> slimy_tree = 
			FeatureUtils.register("slimy_tree", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(BlockInit.slimy_log.get()), //Block that makes the trunk
					new StraightTrunkPlacer(5, 7, 5), //place trunks straight up (middle click for more)
					BlockStateProvider.simple(BlockInit.slimy_leaves.get()), //Block that makes the leaves
					new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 4), //how are the leaves placed (middle click for more) WIDTH, HEIGHT OFF TRUNK, HEIGHT
					new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(BlockInit.rotting_dirt.get())).build());
	
	public static final Holder<PlacedFeature> slimy_tree_checked = PlacementUtils.register("slimy_tree_checked", slimy_tree, 
			PlacementUtils.filteredByBlockSurvival(BlockInit.slimy_sapling.get()));
	
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> slimy_tree_spawn = 
			FeatureUtils.register("slimy_tree_spawn", Feature.RANDOM_SELECTOR,
					new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(slimy_tree_checked, 0.5F)), slimy_tree_checked));
	
	
	// Rotting Flower
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> rotting_flower = 
			FeatureUtils.register("rotting_flower", Feature.FLOWER, 
					new RandomPatchConfiguration(64, 2, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, //tries, xspread, yspread
							new SimpleBlockConfiguration(BlockStateProvider.simple(BlockInit.rotting_flower.get())))));
	
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> rotting_tall_grass = 
			FeatureUtils.register("rotting_tall_grass", Feature.FLOWER, 
					new RandomPatchConfiguration(512, 16, 7, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, //tries, xspread, yspread
							new SimpleBlockConfiguration(BlockStateProvider.simple(BlockInit.rotting_tall_grass.get())))));
	
	public static final List<OreConfiguration.TargetBlockState> overworld_fester_ore = List.of(
			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.fester_ore.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.fester_ore.get().defaultBlockState()));
	
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> fester_ore = 
			FeatureUtils.register("fester_ore", Feature.ORE, new OreConfiguration(
					overworld_fester_ore, TFFCommonConfigs.FESTER_ORE_VEIN_SIZE.get())); //last number is vein size, not below 3
	
}
