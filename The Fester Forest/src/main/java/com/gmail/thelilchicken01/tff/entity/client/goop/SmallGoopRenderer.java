package com.gmail.thelilchicken01.tff.entity.client.goop;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.goop.SmallGoopEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SmallGoopRenderer extends GeoEntityRenderer<SmallGoopEntity> {
	
	private float scale = 0.3f;

	public SmallGoopRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new SmallGoopModel());
		this.shadowRadius = 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(SmallGoopEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/goop/goop.png");
	}
	
	@Override
	public RenderType getRenderType(SmallGoopEntity animatable, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight, ResourceLocation texture) {
		poseStack.scale(scale, scale, scale); //scale size of entity
		return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
	}
	
	
}
