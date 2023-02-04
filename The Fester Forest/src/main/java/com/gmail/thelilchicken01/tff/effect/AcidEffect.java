package com.gmail.thelilchicken01.tff.effect;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class AcidEffect extends MobEffect {
	
	private int counter = 20;
	private int cooldown = 20;

	protected AcidEffect() {
		super(MobEffectCategory.HARMFUL, 6037898);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int pAmplifier) {
		
		super.applyEffectTick(entity, pAmplifier);
		
		counter++;
		
		if (counter > cooldown) {
			
			entity.hurt(TheFesterForest.GOOP_ACID, pAmplifier + 2);
			
			counter = 0;
			
		}
		
	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		
		return true;
		
	}

}
