package com.gmail.thelilchicken01.tff.entity.arrow.arrow_renderers;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.arrow.IcyArrow;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class IcyArrowRenderer extends ArrowRenderer<IcyArrow> {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(TheFesterForest.MODID, "textures/entity/arrows/icy_arrow.png");

	public IcyArrowRenderer(Context context) {
		super(context);
		
	}

	@Override
	public ResourceLocation getTextureLocation(IcyArrow p_114482_) {
		
		return TEXTURE;
	}

}
