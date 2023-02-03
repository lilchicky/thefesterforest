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
	
	public static final DeferredRegister<PlacedFeature> placed_features = 
			DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, TheFesterForest.modid);
	
	// Rottingwood Tree
	public static final RegistryObject<PlacedFeature> rottingwood_tree_placed = placed_features.register(
			"rottingwood_tree_placed", () -> new PlacedFeature(TffConfiguredFeatures.rottingwood_tree.getHolder().get(), tree(5)));
	
	// Slimy Tree
	public static final RegistryObject<PlacedFeature> slimy_tree_placed = placed_features.register(
			"slimy_tree_placed", () -> new PlacedFeature(TffConfiguredFeatures.slimy_tree.getHolder().get(), tree(1)));
	
	// Sickening Flower
	public static final RegistryObject<PlacedFeature> sickening_flower_placed = placed_features.register(
			"sickening_flower_placed", () -> new PlacedFeature(TffConfiguredFeatures.sickening_flower.getHolder().get(),
					patch(6)));
	
	// Rotting Flower
	public static final RegistryObject<PlacedFeature> rotting_flower_placed = placed_features.register(
			"rotting_flower_placed", () -> new PlacedFeature(TffConfiguredFeatures.rotting_flower.getHolder().get(),
					patch(9)));
	
	// Rotting Tall Grass
	public static final RegistryObject<PlacedFeature> rotting_tall_grass_placed = placed_features.register(
			"rotting_tall_grass_placed", () -> new PlacedFeature(TffConfiguredFeatures.rotting_tall_grass.getHolder().get(),
					patch(128)));
	
    private static List<PlacementModifier> patch(int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }
    
    private static List<PlacementModifier> tree(int count) {
        return List.of(CountOnEveryLayerPlacement.of(count), BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
    }
	
}
