package com.gmail.thelilchicken01.tff.effect;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class Paralysis extends MobEffect {

	protected Paralysis() {
		super(MobEffectCategory.HARMFUL, 2463422);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int pAmplifier) {
		
		super.applyEffectTick(entity, pAmplifier);
		
		entity.setDeltaMovement(0.0, 0.0, 0.0);
		
	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		
		return true;
		
	}

}
