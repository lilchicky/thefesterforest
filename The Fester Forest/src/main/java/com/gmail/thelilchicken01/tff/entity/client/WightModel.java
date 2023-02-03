package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.WightEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WightModel extends AnimatedGeoModel<WightEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(WightEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/wight.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(WightEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/wight.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(WightEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/wight/wight.png");
	}

}
