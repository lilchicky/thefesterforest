package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.CorrodedShroomEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CorrodedShroomRenderer extends GeoEntityRenderer<CorrodedShroomEntity> {

	public CorrodedShroomRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CorrodedShroomModel());
		this.shadowRadius = 0.3f;
	}

	@Override
	public ResourceLocation getTextureLocation(CorrodedShroomEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/corroded_shroom/corroded_shroom.png");
	}
	
	@Override
	public RenderType getRenderType(CorrodedShroomEntity animatable, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight, ResourceLocation texture) {
		poseStack.scale(1.0f, 1.0f, 1.0f); //scale size of entity
		return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
	}
	
	
}
