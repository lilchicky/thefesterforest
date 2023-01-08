package com.gmail.thelilchicken01.tff.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.elytra.CustomElytraRenderProperties;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.ReinforcedElytra;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = TheFesterForest.modid, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
    public Object getArmorRenderProperties() {
        return new CustomElytraRenderProperties();
    }
	
	public void clientInit() {
		
		ItemProperties.register(ItemInit.reetle_elytra.get(), new ResourceLocation("broken"), (stack, p_239428_1_, p_239428_2_, j) -> {
            return ReinforcedElytra.isUsable(stack) ? 0.0F : 1.0F;
        });
		
	}
	
}
