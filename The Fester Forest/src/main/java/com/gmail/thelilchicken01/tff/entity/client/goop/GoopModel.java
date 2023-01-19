package com.gmail.thelilchicken01.tff.entity.client.goop;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.goop.GoopEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GoopModel extends AnimatedGeoModel<GoopEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(GoopEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.modid, "animations/goop.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(GoopEntity object) {
		
		return new ResourceLocation(TheFesterForest.modid, "geo/goop.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(GoopEntity object) {
		
		return new ResourceLocation(TheFesterForest.modid, "textures/entity/goop/goop.png");
	}

}
