package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.DeepReaverEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DeepReaverModel extends AnimatedGeoModel<DeepReaverEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(DeepReaverEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/deep_reaver.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(DeepReaverEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/deep_reaver.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(DeepReaverEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/deep_reaver/deep_reaver.png");
	}

}
