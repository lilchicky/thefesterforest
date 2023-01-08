package com.gmail.thelilchicken01.tff.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheFesterForest.modid, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {
	
	public Object getArmorRenderProperties() {
        return null;
    }
	
	public void clientInit() {
    }
	
}
