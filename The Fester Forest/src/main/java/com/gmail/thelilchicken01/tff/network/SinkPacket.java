package com.gmail.thelilchicken01.tff.network;

import java.util.function.Supplier;

import com.gmail.thelilchicken01.tff.client.ClientPacketHandler;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

public class SinkPacket {
	
	// Full credit to ochotonida's Artifacts on github
	
	public final boolean SHOULD_SINK;
	
	public SinkPacket(FriendlyByteBuf buffer) {
		
		SHOULD_SINK = buffer.readBoolean();
		
	}
	
	public SinkPacket(boolean shouldSink) {
		
		this.SHOULD_SINK = shouldSink;
		
	}
	
	public void encode(FriendlyByteBuf buffer) {
		
		buffer.writeBoolean(SHOULD_SINK);
		
	}
	
	public void handle(Supplier<NetworkEvent.Context> context) {
		
		context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleSinkPacket(this)));
		context.get().setPacketHandled(true);
		
	}
	
}
