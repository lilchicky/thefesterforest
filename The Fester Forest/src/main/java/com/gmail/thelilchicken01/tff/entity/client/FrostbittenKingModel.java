package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.FrostbittenKingEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FrostbittenKingModel extends AnimatedGeoModel<FrostbittenKingEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(FrostbittenKingEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/frostbitten_king.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(FrostbittenKingEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/frostbitten_king.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(FrostbittenKingEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/frostbitten_king/frostbitten_king.png");
	}

}
