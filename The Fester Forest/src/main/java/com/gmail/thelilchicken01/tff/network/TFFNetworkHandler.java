package com.gmail.thelilchicken01.tff.network;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class TFFNetworkHandler {

	private static final String PROTOCOL_VERSION = "1";
	
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(TheFesterForest.MODID, "main"),
			() -> PROTOCOL_VERSION, 
			PROTOCOL_VERSION::equals, 
			PROTOCOL_VERSION::equals
		);
	
	public static void register() {
		INSTANCE.registerMessage(0, SinkPacket.class, SinkPacket::encode, SinkPacket::new, SinkPacket::handle);
		INSTANCE.registerMessage(0, PetPacket.class, PetPacket::encode, PetPacket::new, PetPacket::handle);
	}
	
}
