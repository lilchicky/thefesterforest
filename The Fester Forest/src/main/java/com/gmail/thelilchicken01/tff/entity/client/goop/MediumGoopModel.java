package com.gmail.thelilchicken01.tff.entity.client.goop;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.goop.MediumGoopEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MediumGoopModel extends AnimatedGeoModel<MediumGoopEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(MediumGoopEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.modid, "animations/goop.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(MediumGoopEntity object) {
		
		return new ResourceLocation(TheFesterForest.modid, "geo/goop.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(MediumGoopEntity object) {
		
		return new ResourceLocation(TheFesterForest.modid, "textures/entity/goop/goop.png");
	}

}
