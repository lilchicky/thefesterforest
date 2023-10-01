package com.gmail.thelilchicken01.tff.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IcyExplosionParticle extends TextureSheetParticle {

	protected IcyExplosionParticle(ClientLevel level, double x, double y, double z, SpriteSet spriteSet,
			double xd, double yd, double zd) {
		super(level, x, y, z, xd, yd, zd);
		
		this.friction = 0.8f;
		this.xd = (xd + (Math.random() - 0.5)) * 0.2; //delta speeds (velocities)
		this.yd = (yd + (Math.random() - 0.5)) * 0.2;
		this.zd = (zd + (Math.random() - 0.5)) * 0.2;
		this.quadSize *= 2.5f; //size of particle
		this.lifetime = 40; //lifetime in ticks
		this.setSpriteFromAge(spriteSet); //must have
		
		this.rCol = 1f; //all of these are colors
		this.gCol = 1f;
		this.bCol = 1f;
		
	}
	
	@Override
	public void tick() {
		super.tick();
		fadeOut();
	}

	private void fadeOut() {
		this.alpha = (-(1/(float)lifetime) * age + 1);
	}
	
	@Override
	public ParticleRenderType getRenderType() {
		
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> {
		
		private final SpriteSet sprites;
		
		public Provider(SpriteSet spriteSet) {
			this.sprites = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType particleType, ClientLevel world, double x, double y, double z, double xd, double yd, double zd) {
			
			return new IcyExplosionParticle(world, x, y, z, this.sprites, xd, yd, zd);
			
		}
		
	}
	
	
	
}
