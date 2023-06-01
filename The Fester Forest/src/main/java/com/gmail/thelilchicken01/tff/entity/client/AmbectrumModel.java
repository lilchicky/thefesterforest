package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.AmbectrumEntity;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RotfishEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AmbectrumModel extends AnimatedGeoModel<AmbectrumEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(AmbectrumEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/ambectrum.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(AmbectrumEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/ambectrum.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(AmbectrumEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/ambectrum/ambectrum.png");
	}

}
