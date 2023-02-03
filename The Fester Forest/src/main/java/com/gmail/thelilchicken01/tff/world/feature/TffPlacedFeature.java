package com.gmail.thelilchicken01.tff.world.feature;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TffPlacedFeature {
	
	public static final DeferredRegister<PlacedFeature> placed_features = 
			DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, TheFesterForest.modid);
	
	// Sickening Flower
	public static final RegistryObject<PlacedFeature> sickening_flower_placed = placed_features.register(
			"sickening_flower_placed", () -> new PlacedFeature(TffConfiguredFeatures.sickening_flower.getHolder().get(),
					patch(3)));
	
	// Rotting Flower
	public static final RegistryObject<PlacedFeature> rotting_flower_placed = placed_features.register(
			"rotting_flower_placed", () -> new PlacedFeature(TffConfiguredFeatures.rotting_flower.getHolder().get(),
					patch(6)));
	
	// Rotting Tall Grass
	public static final RegistryObject<PlacedFeature> rotting_tall_grass_placed = placed_features.register(
			"rotting_tall_grass_placed", () -> new PlacedFeature(TffConfiguredFeatures.rotting_tall_grass.getHolder().get(),
					patch(15)));
	
    private static List<PlacementModifier> patch(int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }
	
}
