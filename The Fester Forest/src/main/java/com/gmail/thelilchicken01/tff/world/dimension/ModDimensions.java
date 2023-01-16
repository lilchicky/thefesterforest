package com.gmail.thelilchicken01.tff.world.dimension;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {

	public static final ResourceKey<Level> tff_key = ResourceKey.create(Registry.DIMENSION_REGISTRY, 
			new ResourceLocation(TheFesterForest.modid, "tff"));
	
	public static final ResourceKey<DimensionType> tff_type = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, tff_key.getRegistryName());
	
	public static void register() {
		
		System.out.println("Registering ModDimensions for " + TheFesterForest.modid);
		
	}
	
}
