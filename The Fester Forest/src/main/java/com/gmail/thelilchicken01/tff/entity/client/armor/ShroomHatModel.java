package com.gmail.thelilchicken01.tff.entity.client.armor;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.armor.shroom_hat.ShroomHat;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShroomHatModel extends AnimatedGeoModel<ShroomHat> {

	@Override
	public ResourceLocation getAnimationFileLocation(ShroomHat animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "animations/shroom_hat.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(ShroomHat object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "geo/shroom_hat.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(ShroomHat object) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/armor/shroom_hat/shroom_hat.png");
	}
	
}
