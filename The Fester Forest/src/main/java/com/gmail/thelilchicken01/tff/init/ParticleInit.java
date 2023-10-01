package com.gmail.thelilchicken01.tff.init;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.google.common.base.Supplier;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticleInit {
	
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TheFesterForest.MODID);
	
	public static final RegistryObject<SimpleParticleType> HELLFLAME_PARTICLES = register("hellflame_particle", 
			() -> new SimpleParticleType(true));
	
	public static final RegistryObject<SimpleParticleType> TFF_PORTAL_PARTICLES = register("tff_portal_particles", 
			() -> new SimpleParticleType(true));
	
	public static final RegistryObject<SimpleParticleType> POCKET_SAND_PARTICLE = register("pocket_sand_particle",
			() -> new SimpleParticleType(true));
	
	public static final RegistryObject<SimpleParticleType> BLOOD_PARTICLE = register("blood_particle",
			() -> new SimpleParticleType(true));
	
	public static final RegistryObject<SimpleParticleType> BONE_PARTICLE = register("bone_particle",
			() -> new SimpleParticleType(true));
	
	public static final RegistryObject<SimpleParticleType> BRANCH_PARTICLE = register("branch_particle",
			() -> new SimpleParticleType(true));
	
	public static final RegistryObject<SimpleParticleType> ICY_EXPLOSION_PARTICLE = register("icy_explosion_particle",
			() -> new SimpleParticleType(true));
	
	private static <T extends ParticleType<?>> RegistryObject<T> register(final String name, final Supplier<T> particle) {
		return PARTICLES.register(name, particle);
	}
	
}
