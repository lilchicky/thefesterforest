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
			"rottingwood_tree_placed", () -> new PlacedFeature(TffConfiguredFeatures.ROTTINGWOOD_TREE.getHolder().get(), tree(7)));
	
	// Frostbitten Tree
	public static final RegistryObject<PlacedFeature> FROSTBITTEN_TREE_PLACED = PLACED_FEATURES.register(
			"frostbitten_tree_placed", () -> new PlacedFeature(TffConfiguredFeatures.FROSTBITTEN_TREE.getHolder().get(), tree(7)));
	
	// Slimy Tree
	public static final RegistryObject<PlacedFeature> SLIMY_TREE_PLACED = PLACED_FEATURES.register(
			"slimy_tree_placed", () -> new PlacedFeature(TffConfiguredFeatures.SLIMY_TREE.getHolder().get(), tree(1)));
	
	// Blue Ocean Tree
	public static final RegistryObject<PlacedFeature> BLUE_OCEAN_TREE = PLACED_FEATURES.register(
			"blue_ocean_tree_placed", () -> new PlacedFeature(TffConfiguredFeatures.BLUE_OCEAN_TREE.getHolder().get(), tree(1)));
	
	// Orange Ocean Tree
	public static final RegistryObject<PlacedFeature> ORANGE_OCEAN_TREE = PLACED_FEATURES.register(
			"orange_ocean_tree_placed", () -> new PlacedFeature(TffConfiguredFeatures.ORANGE_OCEAN_TREE.getHolder().get(), tree(1)));
	
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
	
	// Corroded Shroom Water
	public static final RegistryObject<PlacedFeature> CORRODED_SHROOM_WATER_PLACED = PLACED_FEATURES.register(
			"corroded_shroom_water_placed", () -> new PlacedFeature(TffConfiguredFeatures.CORRODED_SHROOM_WATER.getHolder().get(),
					patch(15)));
	
	// Corroded Shroom Land
	public static final RegistryObject<PlacedFeature> CORRODED_SHROOM_LAND_PLACED = PLACED_FEATURES.register(
			"corroded_shroom_land_placed", () -> new PlacedFeature(TffConfiguredFeatures.CORRODED_SHROOM_LAND.getHolder().get(),
					patch(18)));
	
	// Fungal Growth Placed
	public static final RegistryObject<PlacedFeature> FUNGAL_GROWTH_PLACED = PLACED_FEATURES.register(
			"fungal_growth_placed", () -> new PlacedFeature(TffConfiguredFeatures.FUNGAL_GROWTH.getHolder().get(),
					patch(10)));
	
	// Slimy Flower
	public static final RegistryObject<PlacedFeature> SLIMY_FLOWER_PLACED = PLACED_FEATURES.register(
			"slimy_flower_placed", () -> new PlacedFeature(TffConfiguredFeatures.SLIMY_FLOWER.getHolder().get(),
					patch(128)));
	
	// Rotting Tall Grass
	public static final RegistryObject<PlacedFeature> ROTTING_TALL_GRASS_PLACED = PLACED_FEATURES.register(
			"rotting_tall_grass_placed", () -> new PlacedFeature(TffConfiguredFeatures.ROTTING_TALL_GRASS.getHolder().get(),
					patch(228)));
	
	// Weeping Grass Common
	public static final RegistryObject<PlacedFeature> WEEPING_GRASS_COMMON_PLACED = PLACED_FEATURES.register(
			"weeping_grass_common_placed", () -> new PlacedFeature(TffConfiguredFeatures.WEEPING_GRASS_COMMON.getHolder().get(),
					patch(228)));
	
	// Weeping Grass Rare
	public static final RegistryObject<PlacedFeature> WEEPING_GRASS_RARE_PLACED = PLACED_FEATURES.register(
			"weeping_grass_rare_placed", () -> new PlacedFeature(TffConfiguredFeatures.WEEPING_GRASS_RARE.getHolder().get(),
					patch(10)));
	
    private static List<PlacementModifier> patch(int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }
    
    private static List<PlacementModifier> tree(int count) {
        return List.of(CountOnEveryLayerPlacement.of(count), BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
    }
	
}
