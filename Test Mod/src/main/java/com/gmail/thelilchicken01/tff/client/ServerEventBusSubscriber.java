package com.gmail.thelilchicken01.tff.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.entity.custom.CrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RottingSkeletonEntity;
import com.gmail.thelilchicken01.tff.entity.custom.VolatileGhostEntity;
import com.gmail.thelilchicken01.tff.entity.custom.WightEntity;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = TheFesterForest.modid, bus = Bus.MOD, value = Dist.DEDICATED_SERVER)
public class ServerEventBusSubscriber {

	@SubscribeEvent
	public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
		
		event.put(ModEntityTypes.rotting_skeleton.get(), RottingSkeletonEntity.setAttributes());
		event.put(ModEntityTypes.crunch_beetle.get(), CrunchBeetleEntity.setAttributes());
		event.put(ModEntityTypes.banshee.get(), BansheeEntity.setAttributes());
		event.put(ModEntityTypes.volatile_ghost.get(), VolatileGhostEntity.setAttributes());
		event.put(ModEntityTypes.wight.get(), WightEntity.setAttributes());
		
	}
	
}
