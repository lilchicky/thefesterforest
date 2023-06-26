package com.gmail.thelilchicken01.tff.item.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

public enum EffectsUtil {
	
	SPEED(MobEffects.MOVEMENT_SPEED, 3),
	SLOWNESS(MobEffects.MOVEMENT_SLOWDOWN, 3),
	HASTE(MobEffects.DIG_SPEED, 3),
	MINING_FATIGUE(MobEffects.DIG_SLOWDOWN, 3),
	STRENGTH(MobEffects.DAMAGE_BOOST, 3),
	INSTANT_HEALTH(MobEffects.HEAL, 1),
	INSTANT_DAMAGE(MobEffects.HARM, 1),
	JUMP_BOOST(MobEffects.JUMP, 3),
	NAUSEA(MobEffects.CONFUSION, 15),
	REGENERATION(MobEffects.REGENERATION, 5),
	RESISTANCE(MobEffects.DAMAGE_RESISTANCE, 4),
	FIRE_RESISTANCE(MobEffects.FIRE_RESISTANCE, 10),
	WATER_BREATHING(MobEffects.WATER_BREATHING, 10),
	INVISIBILITY(MobEffects.INVISIBILITY, 15),
	BLINDNESS(MobEffects.BLINDNESS, 4),
	NIGHT_VISION(MobEffects.NIGHT_VISION, 20),
	HUNGER(MobEffects.HUNGER, 6),
	WEAKNESS(MobEffects.WEAKNESS, 3),
	POISON(MobEffects.HUNGER, 5),
	WITHER(MobEffects.WITHER, 5),
	HEALTH_BOOST(MobEffects.HEALTH_BOOST, 10),
	ABSORPTION(MobEffects.ABSORPTION, 10),
	SATURATION(MobEffects.SATURATION, 10),
	GLOWING(MobEffects.GLOWING, 25),
	LEVITATION(MobEffects.LEVITATION, 3),
	LUCK(MobEffects.LUCK, 25),
	BAD_LUCK(MobEffects.UNLUCK, 25),
	SLOW_FALLING(MobEffects.SLOW_FALLING, 15),
	CONDUIT_POWER(MobEffects.CONDUIT_POWER, 15),
	DOLPHINS_GRACE(MobEffects.DOLPHINS_GRACE, 15);
	
	
	private final MobEffect effect;
	private final int duration;

	private EffectsUtil(MobEffect effect, int duration) {
		
		this.effect = effect;
		this.duration = duration;
		
	}
	
	public MobEffect getEffect() {
		return effect;
	}
	
	public int getEffectDuration() {
		return duration;
	}
	
	public static EffectsUtil getRandomEffect() {
		
		EffectsUtil[] effectsArray = new EffectsUtil[] {
				SPEED,
				SLOWNESS,
				HASTE,
				MINING_FATIGUE,
				STRENGTH,
				INSTANT_HEALTH,
				INSTANT_DAMAGE,
				JUMP_BOOST,
				NAUSEA,
				REGENERATION,
				RESISTANCE,
				FIRE_RESISTANCE,
				WATER_BREATHING,
				INVISIBILITY,
				BLINDNESS,
				NIGHT_VISION,
				HUNGER,
				WEAKNESS,
				POISON,
				WITHER,
				HEALTH_BOOST,
				ABSORPTION,
				SATURATION,
				GLOWING,
				LEVITATION,
				LUCK,
				BAD_LUCK,
				SLOW_FALLING,
				CONDUIT_POWER,
				DOLPHINS_GRACE
		};
		
		return effectsArray[(int) (Math.random() * effectsArray.length)];
		
	}
	
}
