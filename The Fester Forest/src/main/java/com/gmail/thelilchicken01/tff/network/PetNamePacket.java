package com.gmail.thelilchicken01.tff.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.gmail.thelilchicken01.tff.client.ClientPacketHandler;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

public class PetNamePacket {
	
	// Full credit to ochotonida's Artifacts on github
	
	public final UUID PET_NAME;
	
	public PetNamePacket(FriendlyByteBuf buffer) {
		
		PET_NAME = buffer.readUUID();
		
	}
	
	public PetNamePacket(UUID petUUID) {
		
		this.PET_NAME = petUUID;
		
	}
	
	public void encode(FriendlyByteBuf buffer) {
		
		buffer.writeUUID(PET_NAME);
		
	}
	
	public void handle(Supplier<NetworkEvent.Context> context) {
		
		context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handlePetNamePacket(this)));
		context.get().setPacketHandled(true);
		
	}
	
}
