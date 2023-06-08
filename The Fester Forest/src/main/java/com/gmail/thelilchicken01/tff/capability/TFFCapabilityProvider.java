package com.gmail.thelilchicken01.tff.capability;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class TFFCapabilityProvider<HANDLER> implements ICapabilityProvider {
	
	protected final Capability<HANDLER> capability;
	protected final Direction facing;
	protected final HANDLER instance;
	protected final LazyOptional<HANDLER> lazyOptional;

	public TFFCapabilityProvider(final Capability<HANDLER> cap, @Nullable final Direction side, final HANDLER instance) {
		
		this.capability = Preconditions.checkNotNull(cap, "capability");
		this.facing = side;
		this.instance = Preconditions.checkNotNull(instance, "instance");
		
		lazyOptional = LazyOptional.of(() -> this.instance);
		
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(final Capability<T> cap, @Nullable final Direction facing) {
		
		return getCapability().orEmpty(cap, lazyOptional);
		
	}
	
	public final Capability<HANDLER> getCapability() {
		return capability;
	}
	
	@Nullable
	public Direction getFacing() {
		return facing;
	}
	
	public final HANDLER getInstance() {
		return instance;
	}

}
