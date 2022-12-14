package com.gmail.thelilchicken01.tff.world.feature;

import java.util.List;

import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class ModOrePlacement {
	
	public static List<PlacementModifier> orePlacement(PlacementModifier modifier, PlacementModifier modifier2) {
		
		return List.of(modifier, InSquarePlacement.spread(), modifier2, BiomeFilter.biome());
		
	}
	
	public static List<PlacementModifier> commonOrePlacement(int num, PlacementModifier modifier) {
		
		return orePlacement(CountPlacement.of(num), modifier);
		
	}
	
	public static List<PlacementModifier> rareOrePlacement(int num, PlacementModifier modifier) {
		
		return orePlacement(RarityFilter.onAverageOnceEvery(num), modifier);
		
	}
	
	
	
}
