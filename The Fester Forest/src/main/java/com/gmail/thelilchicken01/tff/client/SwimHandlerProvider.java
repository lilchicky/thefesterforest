package com.gmail.thelilchicken01.tff.client;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class SwimHandlerProvider implements ICapabilitySerializable<CompoundTag> {
	
	private final SwimHandler HANDLER = new SwimHandler();
	private final LazyOptional<SwimHandler> OPTIONAL_HANDLER = LazyOptional.of(() -> HANDLER);
	
	public void invalidate() {
		OPTIONAL_HANDLER.invalidate();
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return SwimHandler.CAPABILITY.orEmpty(cap, OPTIONAL_HANDLER);
	}

	@Override
	public CompoundTag serializeNBT() {
		return HANDLER.serializeNBT();
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		HANDLER.deserializeNBT(nbt);
		
	}

}
