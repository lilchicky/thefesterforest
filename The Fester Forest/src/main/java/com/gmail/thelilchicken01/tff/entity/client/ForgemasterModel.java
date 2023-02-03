package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.ForgemasterEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ForgemasterModel extends AnimatedGeoModel<ForgemasterEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(ForgemasterEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/forgemaster.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(ForgemasterEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/forgemaster.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(ForgemasterEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/forgemaster/forgemaster.png");
	}

}
