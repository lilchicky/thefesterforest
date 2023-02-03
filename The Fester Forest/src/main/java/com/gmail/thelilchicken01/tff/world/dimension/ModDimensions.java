package com.gmail.thelilchicken01.tff.world.dimension;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {

	public static final ResourceKey<Level> TFF_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY, 
			new ResourceLocation(TheFesterForest.MODID, "tff"));
	
	public static final ResourceKey<DimensionType> TFF_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, TFF_KEY.getRegistryName());
	
	public static void register() {
		
		System.out.println("Registering ModDimensions for " + TheFesterForest.MODID);
		
	}
	
}
