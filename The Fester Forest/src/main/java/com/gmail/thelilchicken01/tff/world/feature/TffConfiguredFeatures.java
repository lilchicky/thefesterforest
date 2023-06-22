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
import net.minecraft.world.level.levelgen.feature.SeagrassFeature;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TffConfiguredFeatures {

	public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister
			.create(Registry.CONFIGURED_FEATURE_REGISTRY, TheFesterForest.MODID);
	
	
	
	//Rottingwood Tree
	public static final RegistryObject<ConfiguredFeature<?, ?>> ROTTINGWOOD_TREE = 
			CONFIGURED_FEATURES.register("rottingwood_tree", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(BlockInit.ROTTING_LOG.get()), //Block that makes the trunk
					new GiantTrunkPlacer(8, 12, 13), //place trunks straight up (middle click for more)
					BlockStateProvider.simple(BlockInit.ROTTING_LEAVES.get()), //Block that makes the leaves
					new MegaPineFoliagePlacer(ConstantInt.of((int)((Math.random()) + 1)), ConstantInt.of(2), ConstantInt.of((int)(Math.random() *10) + 8)), //how are the leaves placed (middle click for more) WIDTH, HEIGHT OFF TRUNK, HEIGHT
					new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(BlockInit.ROTTING_DIRT.get())).build()));
	
	//Slimy Tree
	public static final RegistryObject<ConfiguredFeature<?, ?>> SLIMY_TREE = 
			CONFIGURED_FEATURES.register("slimy_tree", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(BlockInit.SLIMY_LOG.get()), //Block that makes the trunk
					new StraightTrunkPlacer(5, 7, 5), //place trunks straight up (middle click for more)
					BlockStateProvider.simple(BlockInit.SLIMY_LEAVES.get()), //Block that makes the leaves
					new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 4), //how are the leaves placed (middle click for more) WIDTH, HEIGHT OFF TRUNK, HEIGHT
					new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(BlockInit.ROTTING_DIRT.get())).build()));
	
	//Blue Ocean Foliage
	public static final RegistryObject<ConfiguredFeature<?, ?>> BLUE_OCEAN_TREE = 
			CONFIGURED_FEATURES.register("blue_ocean_tree", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(BlockInit.FESTER_SHROOM_STEM.get()), //Block that makes the trunk
					new ForkingTrunkPlacer(2, 3, 1), //place trunks straight up (middle click for more)
					BlockStateProvider.simple(BlockInit.BLUE_FESTER_SHROOM_BLOCK.get()), //Block that makes the leaves
					new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), //how are the leaves placed (middle click for more) WIDTH, HEIGHT OFF TRUNK, HEIGHT
					new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(BlockInit.ROTTING_SAND.get())).build()));
	
	//Orange Ocean Foliage
	public static final RegistryObject<ConfiguredFeature<?, ?>> ORANGE_OCEAN_TREE = 
			CONFIGURED_FEATURES.register("orange_ocean_tree", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(BlockInit.FESTER_SHROOM_STEM.get()), //Block that makes the trunk
					new ForkingTrunkPlacer(2, 3, 1), //place trunks straight up (middle click for more)
					BlockStateProvider.simple(BlockInit.ORANGE_FESTER_SHROOM_BLOCK.get()), //Block that makes the leaves
					new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), //how are the leaves placed (middle click for more) WIDTH, HEIGHT OFF TRUNK, HEIGHT
					new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(BlockInit.ROTTING_SAND.get())).build()));
	
	
	
	// Sickening Flower
	public static final RegistryObject<ConfiguredFeature<?, ?>> SICKENING_FLOWER = 
			CONFIGURED_FEATURES.register("sickening_flower", () -> new ConfiguredFeature<>(Feature.FLOWER,
					patch(BlockInit.SICKENING_FLOWER.get(), 16)));
	
	// Slimy Flower
	public static final RegistryObject<ConfiguredFeature<?, ?>> SLIMY_FLOWER = 
			CONFIGURED_FEATURES.register("slimy_flower", () -> new ConfiguredFeature<>(Feature.FLOWER,
					patch(BlockInit.SLIMY_FLOWER.get(), 32)));
	
	// Rotting Flower
	public static final RegistryObject<ConfiguredFeature<?, ?>> ROTTING_FLOWER = 
			CONFIGURED_FEATURES.register("rotting_flower", () -> new ConfiguredFeature<>(Feature.FLOWER,
					patch(BlockInit.ROTTING_FLOWER.get(), 32)));
	
	// Rotting Tall Grass
	public static final RegistryObject<ConfiguredFeature<?, ?>> ROTTING_TALL_GRASS = 
			CONFIGURED_FEATURES.register("rotting_tall_grass", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH,
					patch(BlockInit.ROTTING_TALL_GRASS.get(), 32)));
	
	// Weeping Grass Common
	public static final RegistryObject<ConfiguredFeature<?, ?>> WEEPING_GRASS_COMMON = 
			CONFIGURED_FEATURES.register("weeping_grass_common", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH,
					patch(BlockInit.WEEPING_GRASS.get(), 32)));
	
	// Weeping Grass Sparse
	public static final RegistryObject<ConfiguredFeature<?, ?>> WEEPING_GRASS_RARE = 
			CONFIGURED_FEATURES.register("weeping_grass_rare", () -> new ConfiguredFeature<>(Feature.FLOWER,
					patch(BlockInit.WEEPING_GRASS.get(), 16)));
	
	// Corroded Mushrooms Water
	public static final RegistryObject<ConfiguredFeature<?, ?>> CORRODED_SHROOM_WATER = 
			CONFIGURED_FEATURES.register("corroded_shroom_water", () -> new ConfiguredFeature<>(ModPatchConfig.CORRODED_SHROOM_FEATURE.get(),
					new ProbabilityFeatureConfiguration(0.2f)));
	
	// Corroded Mushrooms Water
	public static final RegistryObject<ConfiguredFeature<?, ?>> FUNGAL_GROWTH = 
			CONFIGURED_FEATURES.register("fungal_growth", () -> new ConfiguredFeature<>(ModPatchConfig.FUNGAL_GROWTH.get(),
					new ProbabilityFeatureConfiguration(0.2f)));
	
	// Corroded Mushrooms Land
	public static final RegistryObject<ConfiguredFeature<?, ?>> CORRODED_SHROOM_LAND = 
			CONFIGURED_FEATURES.register("corroded_shroom_land", () -> new ConfiguredFeature<>(Feature.FLOWER,
					patch(BlockInit.ROTTING_FLOWER.get(), 32)));
						
    private static RandomPatchConfiguration patch(Block block, int tries) {
    	
    	return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block))));
    
    }
	
}
