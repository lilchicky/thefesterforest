package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BansheeModel extends AnimatedGeoModel<BansheeEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(BansheeEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/banshee.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(BansheeEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/banshee.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(BansheeEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/banshee/banshee.png");
	}

}
