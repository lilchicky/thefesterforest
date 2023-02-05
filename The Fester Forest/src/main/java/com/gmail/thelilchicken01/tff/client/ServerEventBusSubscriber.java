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
import com.gmail.thelilchicken01.tff.entity.custom.goop.GoopEntity;
import com.gmail.thelilchicken01.tff.entity.custom.goop.MediumGoopEntity;
import com.gmail.thelilchicken01.tff.entity.custom.goop.SmallGoopEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = TheFesterForest.MODID, bus = Bus.MOD, value = Dist.DEDICATED_SERVER)
public class ServerEventBusSubscriber {

	@SubscribeEvent
	public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
		
		event.put(ModEntityTypes.ROTTING_SKELETON.get(), RottingSkeletonEntity.setAttributes());
		event.put(ModEntityTypes.CRUNCH_BEETLE.get(), CrunchBeetleEntity.setAttributes());
		event.put(ModEntityTypes.BANSHEE.get(), BansheeEntity.setAttributes());
		event.put(ModEntityTypes.VOLATILE_GHOST.get(), VolatileGhostEntity.setAttributes());
		event.put(ModEntityTypes.WIGHT.get(), WightEntity.setAttributes());
		event.put(ModEntityTypes.FORGEMASTER.get(), ForgemasterEntity.setAttributes());
		event.put(ModEntityTypes.PLAYER_CRUNCH_BEETLE.get(), PlayerCrunchBeetleEntity.setAttributes());
		event.put(ModEntityTypes.PYLON.get(), PylonEntity.setAttributes());
		event.put(ModEntityTypes.REETLE_QUEEN.get(), ReetleQueenEntity.setAttributes());
		event.put(ModEntityTypes.GOOP.get(), GoopEntity.setAttributes());
		event.put(ModEntityTypes.GOOP_MEDIUM.get(), MediumGoopEntity.setAttributes());
		event.put(ModEntityTypes.GOOP_SMALL.get(), SmallGoopEntity.setAttributes());
		
	}
	
	@SubscribeEvent
	public static void onEntityAttributeModificationEvent(final EntityAttributeModificationEvent event) {
		
		event.add(EntityType.PLAYER, ForgeMod.REACH_DISTANCE.get());
		event.add(EntityType.PLAYER, ForgeMod.ATTACK_RANGE.get());
		event.add(EntityType.PLAYER, ForgeMod.ENTITY_GRAVITY.get());
		event.add(EntityType.PLAYER, ForgeMod.STEP_HEIGHT_ADDITION.get());
		event.add(EntityType.PLAYER, ForgeMod.SWIM_SPEED.get());
		
	}
	
}
