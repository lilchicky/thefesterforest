package com.gmail.thelilchicken01.tff.init;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.google.common.base.Supplier;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticleInit {
	
	public static final DeferredRegister<ParticleType<?>> particles = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TheFesterForest.modid);
	
	public static final RegistryObject<SimpleParticleType> hellflame_particle = register("hellflame_particle", 
			() -> new SimpleParticleType(true));
	
	public static final RegistryObject<SimpleParticleType> tff_portal_particles = register("tff_portal_particles", 
			() -> new SimpleParticleType(true));
	
	public static final RegistryObject<SimpleParticleType> pocket_sand_particle = register("pocket_sand_particle",
			() -> new SimpleParticleType(true));
	
	public static final RegistryObject<SimpleParticleType> blood_particle = register("blood_particle",
			() -> new SimpleParticleType(true));
	
	public static final RegistryObject<SimpleParticleType> bone_particle = register("bone_particle",
			() -> new SimpleParticleType(true));
	
	public static final RegistryObject<SimpleParticleType> branch_particle = register("branch_particle",
			() -> new SimpleParticleType(true));
	
	private static <T extends ParticleType<?>> RegistryObject<T> register(final String name, final Supplier<T> particle) {
		return particles.register(name, particle);
	}
	
}
