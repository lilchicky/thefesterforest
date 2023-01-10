package com.gmail.thelilchicken01.tff.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.entity.custom.CrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.entity.custom.ForgemasterEntity;
import com.gmail.thelilchicken01.tff.entity.custom.PlayerCrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.entity.custom.PylonEntity;
import com.gmail.thelilchicken01.tff.entity.custom.ReetleQueenEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RottingSkeletonEntity;
import com.gmail.thelilchicken01.tff.entity.custom.VolatileGhostEntity;
import com.gmail.thelilchicken01.tff.entity.custom.WightEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
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
		event.put(ModEntityTypes.forgemaster.get(), ForgemasterEntity.setAttributes());
		event.put(ModEntityTypes.player_crunch_beetle.get(), PlayerCrunchBeetleEntity.setAttributes());
		event.put(ModEntityTypes.pylon.get(), PylonEntity.setAttributes());
		event.put(ModEntityTypes.reetle_queen.get(), ReetleQueenEntity.setAttributes());
		
	}
	
	@SubscribeEvent
	public static void onEntityAttributeModificationEvent(final EntityAttributeModificationEvent event) {
		
		event.add(EntityType.PLAYER, ForgeMod.REACH_DISTANCE.get());
		event.add(EntityType.PLAYER, ForgeMod.ATTACK_RANGE.get());
		event.add(EntityType.PLAYER, ForgeMod.ENTITY_GRAVITY.get());
		
	}
	
}
