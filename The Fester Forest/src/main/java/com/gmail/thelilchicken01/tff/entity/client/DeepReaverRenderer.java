package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.DeepReaverEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DeepReaverRenderer extends GeoEntityRenderer<DeepReaverEntity> {

	public DeepReaverRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new DeepReaverModel());
		this.shadowRadius = 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(DeepReaverEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/deep_reaver/deep_reaver.png");
	}
	
	@Override
	public RenderType getRenderType(DeepReaverEntity animatable, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight, ResourceLocation texture) {
		poseStack.scale(2.5f, 2.5f, 2.5f); //scale size of entity
		return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
	}
	
	
}
