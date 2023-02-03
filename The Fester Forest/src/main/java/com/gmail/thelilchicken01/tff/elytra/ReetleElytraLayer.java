package com.gmail.thelilchicken01.tff.elytra;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ReetleElytraLayer extends ElytraLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(TheFesterForest.MODID, "textures/entity/reetle_elytra.png");
	
	public ReetleElytraLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> rendererIn, EntityModelSet modelSet) {
		
		super(rendererIn, modelSet);
		
	}
	
	@Override
	public boolean shouldRender(ItemStack stack, AbstractClientPlayer entity) {
		
		return stack.getItem() == ItemInit.REETLE_ELYTRA.get();
	}
	
	@Override
	public ResourceLocation getElytraTexture(ItemStack stack, AbstractClientPlayer entity) {
		
		return TEXTURE;
	}
	
}
