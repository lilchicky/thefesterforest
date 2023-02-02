package com.gmail.thelilchicken01.tff.world.feature;

import com.gmail.thelilchicken01.tff.config.TFFCommonConfigs;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class ModPlacedFeatures {

	public static final Holder<PlacedFeature> rottingwood_tree_placed = PlacementUtils.register("rottingwood_tree_placed", 
			ModConfiguredFeatures.rottingwood_tree_spawn, VegetationPlacements.treePlacement(
					PlacementUtils.countExtra(5, 0.2f, 3))); //base per chunk, chance that -> this many extra spawn //MUST BE 0.1, 0.2, 0.25, 0.5
	
	public static final Holder<PlacedFeature> slimy_tree_placed = PlacementUtils.register("slimy_tree_placed", 
			ModConfiguredFeatures.slimy_tree_spawn, VegetationPlacements.treePlacement(
					PlacementUtils.countExtra(0, 0.1f, 1)));
	
	public static final Holder<PlacedFeature> rotting_flower_placed = PlacementUtils.register("rotting_flower_placed", 
			ModConfiguredFeatures.rotting_flower, RarityFilter.onAverageOnceEvery(2),
			InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	
	public static final Holder<PlacedFeature> sickening_flower_placed = PlacementUtils.register("sickening_flower_placed", 
			ModConfiguredFeatures.sickening_flower, RarityFilter.onAverageOnceEvery(2),
			InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	
	public static final Holder<PlacedFeature> rotting_tall_grass_placed = PlacementUtils.register("rotting_tall_grass_placed", 
			ModConfiguredFeatures.rotting_tall_grass, RarityFilter.onAverageOnceEvery(1),
			InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	
	public static final Holder<PlacedFeature> fester_ore_placed = PlacementUtils.register("fester_ore_placed", 
			ModConfiguredFeatures.fester_ore, ModOrePlacement.commonOrePlacement(TFFCommonConfigs.FESTER_ORE_VEINS_PER_CHUNK.get(), //veins per chunk
					HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(64), VerticalAnchor.aboveBottom(144)))); //height range
	
}
