package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.ReetleQueenEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ReetleQueenModel extends AnimatedGeoModel<ReetleQueenEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(ReetleQueenEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.modid, "animations/reetle_queen.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(ReetleQueenEntity object) {
		
		return new ResourceLocation(TheFesterForest.modid, "geo/reetle_queen.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(ReetleQueenEntity object) {
		
		return new ResourceLocation(TheFesterForest.modid, "textures/entity/reetle_queen/reetle_queen.png");
	}

}
