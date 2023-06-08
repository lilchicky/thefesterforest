package com.gmail.thelilchicken01.tff.capability;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;

public class SwimHandlerProvider<HANDLER> extends TFFCapabilityProvider<HANDLER> implements INBTSerializable<Tag> {
	
	private final INBTSerializable<Tag> serializableInstance;
	
	@SuppressWarnings("unchecked")
	public SwimHandlerProvider(final Capability<HANDLER> cap, @Nullable final Direction facing, final HANDLER instance) {
		
		super(cap, facing, instance);
		
		Preconditions.checkArgument(instance instanceof INBTSerializable, "instance must implement INBTSerializable");
		
		serializableInstance = (INBTSerializable<Tag>) instance;
		
	}
	
	@Override
	public Tag serializeNBT() {
		return serializableInstance.serializeNBT();
	}
	
	@Override
	public void deserializeNBT(final Tag nbt) {
		serializableInstance.deserializeNBT(nbt);
		
	}

}
