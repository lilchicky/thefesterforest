package com.gmail.thelilchicken01.tff.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.network.TFFNetworkHandler;
import com.gmail.thelilchicken01.tff.network.SinkPacket;

import be.florens.expandability.api.forge.PlayerSwimEvent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.network.PacketDistributor;

public class SwimHandler implements INBTSerializable<CompoundTag> {
	
	/*
	 * Code thanks to ochotonida on GitHub!
	 */
	
	public static final Capability<SwimHandler> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
	
	private boolean shouldSink;
	
	public void setSinking(boolean shouldSink) {
		this.shouldSink = shouldSink;
	}
	
	public void syncSinking(ServerPlayer player) {
		
		TFFNetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SinkPacket(shouldSink));
		
	}
	
	public boolean isSinking() {
		return shouldSink;
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
		MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, SwimHandler::onAttachCapabilities);
		MinecraftForge.EVENT_BUS.addListener(SwimHandler::onPlayerSwim);
	}
	
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		
		event.register(SwimHandler.class);
		
	}
	
	public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player) {
			SwimHandlerProvider provider = new SwimHandlerProvider();
			event.addCapability(new ResourceLocation(TheFesterForest.MODID, "swim_handler"), provider);
			event.addListener(provider::invalidate);
		}
	}
	
	public static void onPlayerSwim(PlayerSwimEvent event) {
		event.getPlayer().getCapability(CAPABILITY).ifPresent(
				handler -> {
					
					if(event.getResult() == Event.Result.DEFAULT) {
						if (handler.isSinking()) {
							event.setResult(Event.Result.DENY);
						}
					}
					
				}
			);
	}

}
