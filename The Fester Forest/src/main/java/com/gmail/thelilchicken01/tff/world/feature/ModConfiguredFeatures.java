package com.gmail.thelilchicken01.tff.world.feature;

import java.util.List;

import com.gmail.thelilchicken01.tff.config.TFFCommonConfigs;
import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModConfiguredFeatures {
	
	public static final Holder<PlacedFeature> ROTTINGWOOD_TREE_CHECKED = PlacementUtils.register("rottingwood_tree_checked", TffConfiguredFeatures.ROTTINGWOOD_TREE.getHolder().get(), 
			PlacementUtils.filteredByBlockSurvival(BlockInit.ROTTINGWOOD_SAPLING.get()));
	
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> ROTTINGWOOD_TREE_SPAWN = 
			FeatureUtils.register("rottingwood_tree_spawn", Feature.RANDOM_SELECTOR,
					new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(ROTTINGWOOD_TREE_CHECKED, 0.5F)), ROTTINGWOOD_TREE_CHECKED));
	
	public static final Holder<PlacedFeature> SLIMY_TREE_CHECKED = PlacementUtils.register("slimy_tree_checked", TffConfiguredFeatures.SLIMY_TREE.getHolder().get(), 
			PlacementUtils.filteredByBlockSurvival(BlockInit.SLIMY_SAPLING.get()));
	
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> SLIMY_TREE_SPAWN = 
			FeatureUtils.register("slimy_tree_spawn", Feature.RANDOM_SELECTOR,
					new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(SLIMY_TREE_CHECKED, 0.5F)), SLIMY_TREE_CHECKED));
	
	public static final List<OreConfiguration.TargetBlockState> OVERWORLD_FESTER_ORE = List.of(
			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.FESTER_ORE.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.FESTER_ORE.get().defaultBlockState()));
	
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> FESTER_ORE = 
			FeatureUtils.register("fester_ore", Feature.ORE, new OreConfiguration(
					OVERWORLD_FESTER_ORE, TFFCommonConfigs.FESTER_ORE_VEIN_SIZE.get())); //last number is vein size, not below 3

}
