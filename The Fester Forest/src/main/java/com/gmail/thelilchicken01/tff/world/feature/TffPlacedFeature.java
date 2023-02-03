package com.gmail.thelilchicken01.tff.world.feature;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountOnEveryLayerPlacement;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TffPlacedFeature {
	
	public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = 
			DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, TheFesterForest.MODID);
	
	// Rottingwood Tree
	public static final RegistryObject<PlacedFeature> ROTTINGWOOD_TREE_PLACED = PLACED_FEATURES.register(
			"rottingwood_tree_placed", () -> new PlacedFeature(TffConfiguredFeatures.ROTTINGWOOD_TREE.getHolder().get(), tree(5)));
	
	// Slimy Tree
	public static final RegistryObject<PlacedFeature> SLIMY_TREE_PLACED = PLACED_FEATURES.register(
			"slimy_tree_placed", () -> new PlacedFeature(TffConfiguredFeatures.SLIMY_TREE.getHolder().get(), tree(1)));
	
	// Slimy Tree Heavy
	public static final RegistryObject<PlacedFeature> SLIMY_TREE_HEAVY_PLACED = PLACED_FEATURES.register(
			"slimy_tree_heavy_placed", () -> new PlacedFeature(TffConfiguredFeatures.SLIMY_TREE.getHolder().get(), tree(5)));
	
	// Sickening Flower
	public static final RegistryObject<PlacedFeature> SICKENING_FLOWER_PLACED = PLACED_FEATURES.register(
			"sickening_flower_placed", () -> new PlacedFeature(TffConfiguredFeatures.SICKENING_FLOWER.getHolder().get(),
					patch(10)));
	
	// Rotting Flower
	public static final RegistryObject<PlacedFeature> ROTTING_FLOWER_PLACED = PLACED_FEATURES.register(
			"rotting_flower_placed", () -> new PlacedFeature(TffConfiguredFeatures.ROTTING_FLOWER.getHolder().get(),
					patch(18)));
	
	// Slimy Flower
	public static final RegistryObject<PlacedFeature> SLIMY_FLOWER_PLACED = PLACED_FEATURES.register(
			"slimy_flower_placed", () -> new PlacedFeature(TffConfiguredFeatures.SLIMY_FLOWER.getHolder().get(),
					patch(128)));
	
	// Rotting Tall Grass
	public static final RegistryObject<PlacedFeature> ROTTING_TALL_GRASS_PLACED = PLACED_FEATURES.register(
			"rotting_tall_grass_placed", () -> new PlacedFeature(TffConfiguredFeatures.ROTTING_TALL_GRASS.getHolder().get(),
					patch(228)));
	
    private static List<PlacementModifier> patch(int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }
    
    private static List<PlacementModifier> tree(int count) {
        return List.of(CountOnEveryLayerPlacement.of(count), BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
    }
	
}
