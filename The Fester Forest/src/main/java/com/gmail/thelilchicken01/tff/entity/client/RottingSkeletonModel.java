package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.RottingSkeletonEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RottingSkeletonModel extends AnimatedGeoModel<RottingSkeletonEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(RottingSkeletonEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/rotting_skeleton.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(RottingSkeletonEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/rotting_skeleton.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(RottingSkeletonEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/rotting_skeleton/rotting_skeleton.png");
	}

}
