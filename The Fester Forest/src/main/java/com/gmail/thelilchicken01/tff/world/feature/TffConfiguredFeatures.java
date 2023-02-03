package com.gmail.thelilchicken01.tff.world.feature;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TffConfiguredFeatures {

	public static final DeferredRegister<ConfiguredFeature<?, ?>> configured_features = DeferredRegister
			.create(Registry.CONFIGURED_FEATURE_REGISTRY, TheFesterForest.modid);
	
	
	
	//Rottingwood Tree
	public static final RegistryObject<ConfiguredFeature<?, ?>> rottingwood_tree = 
			configured_features.register("rottingwood_tree", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(BlockInit.rotting_log.get()), //Block that makes the trunk
					new GiantTrunkPlacer(8, 12, 13), //place trunks straight up (middle click for more)
					BlockStateProvider.simple(BlockInit.rotting_leaves.get()), //Block that makes the leaves
					new MegaPineFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), ConstantInt.of((int)(Math.random() *10) + 8)), //how are the leaves placed (middle click for more) WIDTH, HEIGHT OFF TRUNK, HEIGHT
					new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(BlockInit.rotting_dirt.get())).build()));
	
	//Slimy Tree
	public static final RegistryObject<ConfiguredFeature<?, ?>> slimy_tree = 
			configured_features.register("slimy_tree", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(BlockInit.slimy_log.get()), //Block that makes the trunk
					new StraightTrunkPlacer(5, 7, 5), //place trunks straight up (middle click for more)
					BlockStateProvider.simple(BlockInit.slimy_leaves.get()), //Block that makes the leaves
					new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 4), //how are the leaves placed (middle click for more) WIDTH, HEIGHT OFF TRUNK, HEIGHT
					new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(BlockInit.rotting_dirt.get())).build()));
	
	
	
	// Sickening Flower
	public static final RegistryObject<ConfiguredFeature<?, ?>> sickening_flower = 
			configured_features.register("sickening_flower", () -> new ConfiguredFeature<>(Feature.FLOWER,
					patch(BlockInit.sickening_flower.get(), 16)));
	
	// Rotting Flower
	public static final RegistryObject<ConfiguredFeature<?, ?>> rotting_flower = 
			configured_features.register("rotting_flower", () -> new ConfiguredFeature<>(Feature.FLOWER,
					patch(BlockInit.rotting_flower.get(), 32)));
	
	// Rotting Tall Grass
	public static final RegistryObject<ConfiguredFeature<?, ?>> rotting_tall_grass = 
			configured_features.register("rotting_tall_grass", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH,
					patch(BlockInit.rotting_tall_grass.get(), 32)));
						
    private static RandomPatchConfiguration patch(Block block, int tries) {
    	
    	return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block))));
    
    }
	
}
