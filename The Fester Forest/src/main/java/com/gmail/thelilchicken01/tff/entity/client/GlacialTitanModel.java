package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.AmbectrumEntity;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.entity.custom.GlacialTitanEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RotfishEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GlacialTitanModel extends AnimatedGeoModel<GlacialTitanEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(GlacialTitanEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/glacial_titan.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(GlacialTitanEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/glacial_titan.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(GlacialTitanEntity object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/glacial_titan/glacial_titan.png");
	}

}
