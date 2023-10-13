package com.gmail.thelilchicken01.tff.client;

import com.gmail.thelilchicken01.tff.capability.PetSpawnHandler;
import com.gmail.thelilchicken01.tff.capability.SwimHandler;
import com.gmail.thelilchicken01.tff.network.PetPacket;
import com.gmail.thelilchicken01.tff.network.SinkPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class ClientPacketHandler {
	
	public static void handleSinkPacket(SinkPacket packet) {
		
		Player player = Minecraft.getInstance().player;
		if (player != null) {
			player.getCapability(SwimHandler.CAPABILITY).ifPresent(handler -> handler.setSinking(packet.SHOULD_SINK));
		}
		
	}
	
	public static void handlePetPacket(PetPacket packet) {
		
		Player player = Minecraft.getInstance().player;
		if (player != null) {
			player.getCapability(PetSpawnHandler.CAPABILITY).ifPresent(handler -> handler.setHasPet(packet.HAS_PET));
		}
		
	}
	
}
