package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.SeathrownSkeletonEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SeathrownSkeletonModel extends AnimatedGeoModel<SeathrownSkeletonEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(SeathrownSkeletonEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/seathrown_skeleton.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(SeathrownSkeletonEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/seathrown_skeleton.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(SeathrownSkeletonEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/seathrown_skeleton/seathrown_skeleton.png");
	}

}
