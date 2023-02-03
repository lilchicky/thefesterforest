package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.RottingSkeletonEntity;
import com.gmail.thelilchicken01.tff.entity.custom.VolatileGhostEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class VolatileGhostModel extends AnimatedGeoModel<VolatileGhostEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(VolatileGhostEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/volatile_ghost.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(VolatileGhostEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/volatile_ghost.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(VolatileGhostEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/volatile_ghost/volatile_ghost.png");
	}

}
