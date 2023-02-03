package com.gmail.thelilchicken01.tff.world;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.world.gen.ModOreGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheFesterForest.MODID)
public class ModWorldEvents {

	@SubscribeEvent
	public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
		
		ModOreGeneration.generateOres(event); //This MUST be above vegetal_decoration, must be in order (ctrl click steps from mod generation classes to find order)
		
	}
	
}
