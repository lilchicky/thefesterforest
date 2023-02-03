package com.gmail.thelilchicken01.tff.world.feature;

import java.util.List;

import com.gmail.thelilchicken01.tff.config.TFFCommonConfigs;
import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModConfiguredFeatures {
	
	public static final Holder<PlacedFeature> rottingwood_tree_checked = PlacementUtils.register("rottingwood_tree_checked", TffConfiguredFeatures.rottingwood_tree.getHolder().get(), 
			PlacementUtils.filteredByBlockSurvival(BlockInit.rottingwood_sapling.get()));
	
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> rottingwood_tree_spawn = 
			FeatureUtils.register("rottingwood_tree_spawn", Feature.RANDOM_SELECTOR,
					new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(rottingwood_tree_checked, 0.5F)), rottingwood_tree_checked));
	
	public static final Holder<PlacedFeature> slimy_tree_checked = PlacementUtils.register("slimy_tree_checked", TffConfiguredFeatures.slimy_tree.getHolder().get(), 
			PlacementUtils.filteredByBlockSurvival(BlockInit.slimy_sapling.get()));
	
	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> slimy_tree_spawn = 
			FeatureUtils.register("slimy_tree_spawn", Feature.RANDOM_SELECTOR,
					new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(slimy_tree_checked, 0.5F)), slimy_tree_checked));
	
	public static final List<OreConfiguration.TargetBlockState> overworld_fester_ore = List.of(
			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.fester_ore.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.fester_ore.get().defaultBlockState()));
	
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> fester_ore = 
			FeatureUtils.register("fester_ore", Feature.ORE, new OreConfiguration(
					overworld_fester_ore, TFFCommonConfigs.FESTER_ORE_VEIN_SIZE.get())); //last number is vein size, not below 3

}
