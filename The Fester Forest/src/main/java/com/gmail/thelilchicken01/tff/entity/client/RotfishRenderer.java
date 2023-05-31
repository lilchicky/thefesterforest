package com.gmail.thelilchicken01.tff.entity.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RotfishEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RottingSkeletonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RotfishRenderer extends GeoEntityRenderer<RotfishEntity> {

	public RotfishRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new RotfishModel());
		this.shadowRadius = 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(RotfishEntity animatable) {
		
		return new ResourceLocation(TheFesterForest.MODID, "textures/entity/rotfish/rotfish.png");
	}
	
	@Override
	public RenderType getRenderType(RotfishEntity animatable, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight, ResourceLocation texture) {
		poseStack.scale(1.0f, 1.0f, 1.0f); //scale size of entity
		return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
	}
	
	
}
