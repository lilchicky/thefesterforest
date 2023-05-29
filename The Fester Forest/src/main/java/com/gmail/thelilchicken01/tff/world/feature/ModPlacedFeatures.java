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
	
	public static final Holder<PlacedFeature> FESTER_ORE_PLACED = PlacementUtils.register("fester_ore_placed", 
			ModConfiguredFeatures.FESTER_ORE, ModOrePlacement.commonOrePlacement(TFFCommonConfigs.FESTER_ORE_VEINS_PER_CHUNK.get(), //veins per chunk
					HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(64), VerticalAnchor.aboveBottom(144)))); //height range
	
}
