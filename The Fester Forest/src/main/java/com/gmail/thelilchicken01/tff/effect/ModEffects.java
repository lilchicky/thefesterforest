package com.gmail.thelilchicken01.tff.effect;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
	
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TheFesterForest.MODID);
	
	// Goop Acid
	public static final RegistryObject<MobEffect> GOOP_ACID = MOB_EFFECTS.register("goop_acid",
			() -> new AcidEffect());
	
	public static void register(IEventBus bus) {
		
		MOB_EFFECTS.register(bus);
		
	}
	
}
