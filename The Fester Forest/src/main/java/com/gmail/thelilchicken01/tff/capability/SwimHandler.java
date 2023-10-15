package com.gmail.thelilchicken01.tff.capability;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.network.TFFNetworkHandler;
import com.gmail.thelilchicken01.tff.network.SinkPacket;

import be.florens.expandability.api.forge.PlayerSwimEvent;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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

public class SwimHandler implements INBTSerializable<CompoundTag> {
	
	/*
	 * Code thanks to ochotonida on GitHub!
	 */
	
	public static final Capability<SwimHandler> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
	
	private boolean shouldSink;
	
	private final LivingEntity entity;
	private static final Direction DEFAULT_FACING = null;
	
	public SwimHandler(@Nullable final LivingEntity entity) {
		this.entity = entity;
	}
	
	public void setSinking(boolean shouldSink) {
		this.shouldSink = shouldSink;
	}
	
	public void syncSinking(ServerPlayer player) {
		
		TFFNetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SinkPacket(shouldSink));
		
	}
	
	public static LazyOptional<SwimHandler> getSinking(final LivingEntity entity) {
		return entity.getCapability(CAPABILITY, DEFAULT_FACING);
	}
	
	public boolean isSinking() {
		return shouldSink;
	}
	
	public static ICapabilityProvider createProvider(final SwimHandler shouldSink) {
		return new TFFCapabilityProvider<>(CAPABILITY, DEFAULT_FACING, shouldSink);
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putBoolean("ShouldSink", isSinking());
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		setSinking(nbt.getBoolean("ShouldSink"));
		
	}
	
	public static void setup() {
		MinecraftForge.EVENT_BUS.addListener(SwimHandler::onRegisterCapabilities);
		MinecraftForge.EVENT_BUS.addListener(SwimHandler::onPlayerSwim);
	}
	
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		
		event.register(SwimHandler.class);
		
	}
	
	public static void onPlayerSwim(PlayerSwimEvent event) {
		event.getPlayer().getCapability(CAPABILITY).ifPresent(
				handler -> {
					
					if (handler.isSinking()) {
						event.setResult(Event.Result.DENY);
					}
					
				}
			);
	}
	
	public void syncronise() {
		
		
		
	}
	
	@Mod.EventBusSubscriber(modid = TheFesterForest.MODID)
	private static class EventHandler {
		
		@SubscribeEvent
		public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
			
			if (event.getObject() instanceof LivingEntity) {
				
				final SwimHandler shouldSink = new SwimHandler((LivingEntity) event.getObject());
				event.addCapability(new ResourceLocation(TheFesterForest.MODID, "swim_handler"), createProvider(shouldSink));
				
			}
			
		}
		
		@SubscribeEvent
		public static void playerClone(final PlayerEvent.Clone event) {
			
			getSinking(event.getOriginal()).ifPresent(
					oldIsSinking -> {
						
						getSinking(event.getPlayer()).ifPresent(
								newIsSinking -> {
									newIsSinking.setSinking(oldIsSinking.isSinking());
								}
							);
						
					}
				);
			
		}
		
		@SubscribeEvent
		public static void playerChangeDimension(final PlayerEvent.PlayerChangedDimensionEvent event) {
			
			getSinking(event.getPlayer()).ifPresent(SwimHandler::syncronise);
			
		}
		
	}

}
