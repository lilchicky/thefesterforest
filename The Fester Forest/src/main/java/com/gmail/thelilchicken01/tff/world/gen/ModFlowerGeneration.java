package com.gmail.thelilchicken01.tff.world.gen;

import java.util.List;
import java.util.Set;

import com.gmail.thelilchicken01.tff.world.feature.ModPlacedFeatures;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModFlowerGeneration {
	@SuppressWarnings("deprecation")
	public static void generateFlowers(final BiomeLoadingEvent event) {
		
		ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
		
		Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
		//if (types.contains(BiomeDictionary.Type.PLAINS)) {
		if(types.contains(BiomeDictionary.Type.FOREST) && types.contains(BiomeDictionary.Type.DENSE) && types.contains(BiomeDictionary.Type.DEAD) && types.contains(BiomeDictionary.Type.LUSH) && 
				types.contains(BiomeDictionary.Type.COLD)) {
			
			//System.out.println("Plains Flower Attempt Place");
			
			List<Holder<PlacedFeature>> base = event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
			
			base.add(ModPlacedFeatures.rotting_flower_placed);
			base.add(ModPlacedFeatures.rotting_tall_grass_placed);
			//base.add(ModPlacedFeatures.sickening_flower_placed);
			
		}
		else {
			//System.out.println("Flower Wrong Biome");
		}
		
	}
	
}
