package com.gmail.thelilchicken01.tff.world.feature;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TffConfiguredFeatures {

	public static final DeferredRegister<ConfiguredFeature<?, ?>> configured_features = DeferredRegister
			.create(Registry.CONFIGURED_FEATURE_REGISTRY, TheFesterForest.modid);
	
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
