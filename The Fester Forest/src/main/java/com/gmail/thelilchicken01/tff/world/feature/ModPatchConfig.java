package com.gmail.thelilchicken01.tff.world.feature;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.google.common.base.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SeagrassFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPatchConfig {

	public static final DeferredRegister<Feature<?>> MOD_FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TheFesterForest.MODID);
	
	public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> CORRODED_SHROOM_FEATURE = MOD_FEATURES.register("corroded_shroom_feature", 
			() -> new CorrodedShroomFeature(ProbabilityFeatureConfiguration.CODEC));
	
}
