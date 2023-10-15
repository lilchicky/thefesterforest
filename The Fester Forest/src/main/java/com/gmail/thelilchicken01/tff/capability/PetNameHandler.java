package com.gmail.thelilchicken01.tff.capability;

import java.util.UUID;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.network.PetNamePacket;
import com.gmail.thelilchicken01.tff.network.TFFNetworkHandler;

import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

public class PetNameHandler implements INBTSerializable<CompoundTag> {
	
	/*
	 * Code thanks to ochotonida on GitHub!
	 */
	
	public static final Capability<PetNameHandler> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
	
	private UUID petUUID = Util.NIL_UUID;
	
	private final LivingEntity entity;
	private static final Direction DEFAULT_FACING = null;
	
	public PetNameHandler(@Nullable final LivingEntity entity) {
		this.entity = entity;
	}
	
	public void setPetUUID(UUID petUUID) {
		this.petUUID = petUUID;
	}
	
	public void syncPetUUID(ServerPlayer player) {
		
		TFFNetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PetNamePacket(petUUID));
		
	}
	
	public static LazyOptional<PetNameHandler> getSavedUUID(final LivingEntity entity) {
		return entity.getCapability(CAPABILITY, DEFAULT_FACING);
	}
	
	public UUID getCurrentUUID() {
		return petUUID;
	}
	
	public static ICapabilityProvider createProvider(final PetNameHandler uuid) {
		return new TFFCapabilityProvider<>(CAPABILITY, DEFAULT_FACING, uuid);
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putUUID("ArmorPetUUID", getCurrentUUID());
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		setPetUUID(nbt.getUUID("ArmorPetUUID"));
		
	}
	
	public static void setup() {
		MinecraftForge.EVENT_BUS.addListener(PetNameHandler::onRegisterCapabilities);
	}
	
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		
		event.register(PetNameHandler.class);
		
	}
	
	public void syncronise() {
		
		
		
	}
	
	@Mod.EventBusSubscriber(modid = TheFesterForest.MODID)
	private static class EventHandler {
		
		@SubscribeEvent
		public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
			
			if (event.getObject() instanceof LivingEntity) {
				
				final PetNameHandler handler = new PetNameHandler((LivingEntity) event.getObject());
				event.addCapability(new ResourceLocation(TheFesterForest.MODID, "_pet_uuid"), createProvider(handler));
				
			}
			
		}
		
		@SubscribeEvent
		public static void playerClone(final PlayerEvent.Clone event) {
			
			getSavedUUID(event.getOriginal()).ifPresent(
					oldUUID -> {
						
						getSavedUUID(event.getPlayer()).ifPresent(
								newUUID -> {
									newUUID.setPetUUID(oldUUID.getCurrentUUID());
								}
							);
						
					}
				);
			
		}
		
		@SubscribeEvent
		public static void playerChangeDimension(final PlayerEvent.PlayerChangedDimensionEvent event) {
			
			getSavedUUID(event.getPlayer()).ifPresent(PetNameHandler::syncronise);
			
		}
		
	}

}
