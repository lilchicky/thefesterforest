package com.gmail.thelilchicken01.tff.world.gen;

import java.util.List;

import com.gmail.thelilchicken01.tff.world.feature.ModPlacedFeatures;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModOreGeneration {
	
	public static void generateOres(final BiomeLoadingEvent event) {
		
		List<Holder<PlacedFeature>> base = event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);
		
		base.add(ModPlacedFeatures.fester_ore_placed);
		
	}
	
}
