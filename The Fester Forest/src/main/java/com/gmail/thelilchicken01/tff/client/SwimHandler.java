package com.gmail.thelilchicken01.tff.client;

import com.gmail.thelilchicken01.tff.network.SinkPacket;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.network.PacketDistributor;
import top.theillusivec4.curios.common.network.NetworkHandler;

public class SwimHandler implements INBTSerializable<CompoundTag> {
	
	public static final Capability<SwimHandler> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
	
	private boolean shouldSink;
	
	public void setSinking(boolean shouldSink) {
		this.shouldSink = shouldSink;
	}
	
	public void syncSinking(ServerPlayer player) {
		
		NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SinkPacket(shouldSink));
		
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
	}
	
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		
		event.register(SwimHandler.class);
		
	}

}
