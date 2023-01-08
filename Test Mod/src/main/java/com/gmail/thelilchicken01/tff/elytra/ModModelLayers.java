package com.gmail.thelilchicken01.tff.elytra;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
public class ModModelLayers {

	public static final ModelLayerLocation reetle_elytra = createLocation("reetle_elytra", "main");
	
	public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
		
		event.registerLayerDefinition(reetle_elytra, () -> ModelReetleElytra.createLayer(new CubeDeformation(1.0F)));
		
	}
	
	private static ModelLayerLocation createLocation(String model, String layer) {
		
		return new ModelLayerLocation(new ResourceLocation("tff", model), layer);
		
	}
	
}
