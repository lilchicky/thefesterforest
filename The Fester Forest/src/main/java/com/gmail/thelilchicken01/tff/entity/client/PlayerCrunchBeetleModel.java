package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.PlayerCrunchBeetleEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PlayerCrunchBeetleModel extends AnimatedGeoModel<PlayerCrunchBeetleEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(PlayerCrunchBeetleEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/crunch_beetle.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(PlayerCrunchBeetleEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/crunch_beetle.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(PlayerCrunchBeetleEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/crunch_beetle/crunch_beetle.png");
	}

}
