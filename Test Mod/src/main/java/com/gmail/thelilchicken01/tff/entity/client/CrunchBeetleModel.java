package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.CrunchBeetleEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CrunchBeetleModel extends AnimatedGeoModel<CrunchBeetleEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(CrunchBeetleEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.modid, "animations/crunch_beetle.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(CrunchBeetleEntity object) {
		
		return new ResourceLocation(TheFesterForest.modid, "geo/crunch_beetle.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(CrunchBeetleEntity object) {
		
		return new ResourceLocation(TheFesterForest.modid, "textures/entity/crunch_beetle/crunch_beetle.png");
	}

}
