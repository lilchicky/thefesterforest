package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.PylonEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PylonModel extends AnimatedGeoModel<PylonEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(PylonEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.modid, "animations/pylon.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(PylonEntity object) {
		
		return new ResourceLocation(TheFesterForest.modid, "geo/pylon.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(PylonEntity object) {
		
		return new ResourceLocation(TheFesterForest.modid, "textures/entity/pylon/pylon.png");
	}

}
