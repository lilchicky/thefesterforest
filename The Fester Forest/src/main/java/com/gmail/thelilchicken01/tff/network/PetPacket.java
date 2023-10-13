package com.gmail.thelilchicken01.tff.network;

import java.util.function.Supplier;

import com.gmail.thelilchicken01.tff.client.ClientPacketHandler;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

public class PetPacket {
	
	// Full credit to ochotonida's Artifacts on github
	
	public final boolean HAS_PET;
	
	public PetPacket(FriendlyByteBuf buffer) {
		
		HAS_PET = buffer.readBoolean();
		
	}
	
	public PetPacket(boolean hasPet) {
		
		this.HAS_PET = hasPet;
		
	}
	
	public void encode(FriendlyByteBuf buffer) {
		
		buffer.writeBoolean(HAS_PET);
		
	}
	
	public void handle(Supplier<NetworkEvent.Context> context) {
		
		context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handlePetPacket(this)));
		context.get().setPacketHandled(true);
		
	}
	
}
