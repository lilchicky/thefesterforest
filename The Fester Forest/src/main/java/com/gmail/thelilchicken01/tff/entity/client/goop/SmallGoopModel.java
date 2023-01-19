package com.gmail.thelilchicken01.tff.entity.client.goop;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.goop.SmallGoopEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SmallGoopModel extends AnimatedGeoModel<SmallGoopEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(SmallGoopEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.modid, "animations/goop.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(SmallGoopEntity object) {
		
		return new ResourceLocation(TheFesterForest.modid, "geo/goop.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(SmallGoopEntity object) {
		
		return new ResourceLocation(TheFesterForest.modid, "textures/entity/goop/goop.png");
	}

}
