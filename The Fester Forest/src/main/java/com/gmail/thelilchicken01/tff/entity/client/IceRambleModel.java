package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.IceRambleEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class IceRambleModel extends AnimatedGeoModel<IceRambleEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(IceRambleEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/ice_ramble.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(IceRambleEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/ice_ramble.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(IceRambleEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/ice_ramble/ice_ramble.png");
	}

}
