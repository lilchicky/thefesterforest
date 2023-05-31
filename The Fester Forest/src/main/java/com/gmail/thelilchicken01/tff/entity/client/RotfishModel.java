package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RotfishEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RotfishModel extends AnimatedGeoModel<RotfishEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(RotfishEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/rotfish.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(RotfishEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/rotfish.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(RotfishEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/rotfish/rotfish.png");
	}

}
