package com.gmail.thelilchicken01.tff.world.gen;

import java.util.List;
import java.util.Set;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.mixed.VolatileSword;
import com.gmail.thelilchicken01.tff.world.feature.ModPlacedFeatures;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.registries.ForgeRegistry;

public class ModTreeGeneration {
	
	public static ResourceKey<Biome> thefesterforest = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(TheFesterForest.modid, "thefesterforest"));
	public static ResourceKey<Biome> fester_ocean = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(TheFesterForest.modid, "fester_ocean"));

	@SuppressWarnings("deprecation")
	public static void generateTrees(final BiomeLoadingEvent event) {
		
		ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
		
		BiomeDictionary.addTypes(thefesterforest, BiomeDictionary.Type.FOREST);
		BiomeDictionary.addTypes(thefesterforest, BiomeDictionary.Type.DENSE);
		BiomeDictionary.addTypes(thefesterforest, BiomeDictionary.Type.DEAD);
		BiomeDictionary.addTypes(thefesterforest, BiomeDictionary.Type.LUSH);
		BiomeDictionary.addTypes(thefesterforest, BiomeDictionary.Type.COLD);
		
		BiomeDictionary.addTypes(fester_ocean, BiomeDictionary.Type.FOREST);
		BiomeDictionary.addTypes(fester_ocean, BiomeDictionary.Type.DENSE);
		BiomeDictionary.addTypes(fester_ocean, BiomeDictionary.Type.DEAD);
		BiomeDictionary.addTypes(fester_ocean, BiomeDictionary.Type.LUSH);
		BiomeDictionary.addTypes(fester_ocean, BiomeDictionary.Type.COLD);
		Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
		
		//types.contains(BiomeDictionary.Type.FOREST) && 
		
		//TODO figure out a better way of doing this to prevent random conflicts
		
		//System.out.println(event.getName());
		//System.out.println(types.toString());
		
		if(types.contains(BiomeDictionary.Type.FOREST) && types.contains(BiomeDictionary.Type.DENSE) && types.contains(BiomeDictionary.Type.DEAD) && types.contains(BiomeDictionary.Type.LUSH) && 
				types.contains(BiomeDictionary.Type.COLD)) {
			
			List<Holder<PlacedFeature>> base = 
					event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
			
			base.add(ModPlacedFeatures.slimy_tree_placed);
			base.add(ModPlacedFeatures.rottingwood_tree_placed);
			
		}
		
	}
	
}
