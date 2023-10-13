package com.gmail.thelilchicken01.tff.capability;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.network.PetPacket;
import com.gmail.thelilchicken01.tff.network.TFFNetworkHandler;

import be.florens.expandability.api.forge.PlayerSwimEvent;
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
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

public class PetSpawnHandler implements INBTSerializable<CompoundTag> {
	
	/*
	 * Code thanks to ochotonida on GitHub!
	 */
	
	public static final Capability<PetSpawnHandler> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
	
	private boolean hasPet;
	
	private final LivingEntity entity;
	private static final Direction DEFAULT_FACING = null;
	
	public PetSpawnHandler(@Nullable final LivingEntity entity) {
		this.entity = entity;
	}
	
	public void setHasPet(boolean hasPet) {
		this.hasPet = hasPet;
	}
	
	public void syncPet(ServerPlayer player) {
		
		TFFNetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PetPacket(hasPet));
		
	}
	
	public static LazyOptional<PetSpawnHandler> getHasPet(final LivingEntity entity) {
		return entity.getCapability(CAPABILITY, DEFAULT_FACING);
	}
	
	public boolean hasPet() {
		return hasPet;
	}
	
	public static ICapabilityProvider createProvider(final PetSpawnHandler hasPet) {
		return new PetSpawnHandlerProvider<>(CAPABILITY, DEFAULT_FACING, hasPet);
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putBoolean("HasPet", hasPet());
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		setHasPet(nbt.getBoolean("HasPet"));
		
	}
	
	public static void setup() {
		MinecraftForge.EVENT_BUS.addListener(PetSpawnHandler::onRegisterCapabilities);
	}
	
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		
		event.register(PetSpawnHandler.class);
		
	}
	
	public void syncronise() {
		
		
		
	}
	
	@Mod.EventBusSubscriber(modid = TheFesterForest.MODID)
	private static class EventHandler {
		
		@SubscribeEvent
		public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
			
			if (event.getObject() instanceof LivingEntity) {
				
				final PetSpawnHandler hasPet = new PetSpawnHandler((LivingEntity) event.getObject());
				event.addCapability(new ResourceLocation(TheFesterForest.MODID, "has_pet"), createProvider(hasPet));
				
			}
			
		}
		
		@SubscribeEvent
		public static void playerClone(final PlayerEvent.Clone event) {
			
			getHasPet(event.getOriginal()).ifPresent(
					oldHasPet -> {
						
						getHasPet(event.getPlayer()).ifPresent(
								newHasPet -> {
									newHasPet.setHasPet(oldHasPet.hasPet());
								}
							);
						
					}
				);
			
		}
		
		@SubscribeEvent
		public static void playerChangeDimension(final PlayerEvent.PlayerChangedDimensionEvent event) {
			
			getHasPet(event.getPlayer()).ifPresent(PetSpawnHandler::syncronise);
			
		}
		
	}

}
