package com.gmail.thelilchicken01.tff.entity.arrow;

import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class IcyArrow extends AbstractArrow {
	
	private boolean extraShot = false;

	public IcyArrow(EntityType<? extends IcyArrow> p_37411_, Level p_37412_) {
		super(p_37411_, p_37412_);
	}

	public IcyArrow(Level p_37419_, LivingEntity p_37420_) {
		super(ModEntityTypes.icy_arrow.get(), p_37420_, p_37419_);
	}

	public IcyArrow(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
		super(ModEntityTypes.icy_arrow.get(), p_37415_, p_37416_, p_37417_, p_37414_);
	}
		   
	@Override
	protected void onHitEntity(EntityHitResult result) {
		
		if (result.getEntity() instanceof LivingEntity) {
			((LivingEntity) result.getEntity()).setTicksFrozen(240);
			
		}
		
		super.onHitEntity(result);
	} 
	
	public void isExtraShot(boolean extra) {
		extraShot = extra;
	}

	@Override
	protected ItemStack getPickupItem() {
		
		return !extraShot ? new ItemStack(ItemInit.ICY_ARROW.get(), 1) : ItemStack.EMPTY;
	}
	
	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	

}
