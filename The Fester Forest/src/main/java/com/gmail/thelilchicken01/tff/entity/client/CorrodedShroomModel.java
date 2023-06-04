package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.CorrodedShroomEntity;
import com.gmail.thelilchicken01.tff.entity.custom.CrunchBeetleEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CorrodedShroomModel extends AnimatedGeoModel<CorrodedShroomEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(CorrodedShroomEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/corroded_shroom.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(CorrodedShroomEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/corroded_shroom.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(CorrodedShroomEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/corroded_shroom/corroded_shroom.png");
	}

}
