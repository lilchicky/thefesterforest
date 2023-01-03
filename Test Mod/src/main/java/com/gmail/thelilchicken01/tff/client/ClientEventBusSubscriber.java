package com.gmail.thelilchicken01.tff.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.client.BansheeRenderer;
import com.gmail.thelilchicken01.tff.entity.client.CrunchBeetleRenderer;
import com.gmail.thelilchicken01.tff.entity.client.ForgemasterRenderer;
import com.gmail.thelilchicken01.tff.entity.client.RottingSkeletonRenderer;
import com.gmail.thelilchicken01.tff.entity.client.VolatileGhostRenderer;
import com.gmail.thelilchicken01.tff.entity.client.WightRenderer;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.entity.custom.CrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.entity.custom.ForgemasterEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RottingSkeletonEntity;
import com.gmail.thelilchicken01.tff.entity.custom.VolatileGhostEntity;
import com.gmail.thelilchicken01.tff.entity.custom.WightEntity;
import com.gmail.thelilchicken01.tff.entity.projectile.BoneCharge;
import com.gmail.thelilchicken01.tff.entity.projectile.BranchCharge;
import com.gmail.thelilchicken01.tff.entity.projectile.MeteorCharge;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ParticleInit;
import com.gmail.thelilchicken01.tff.particle.BloodParticle;
import com.gmail.thelilchicken01.tff.particle.BoneParticle;
import com.gmail.thelilchicken01.tff.particle.BranchParticle;
import com.gmail.thelilchicken01.tff.particle.HellflameParticle;
import com.gmail.thelilchicken01.tff.particle.PocketSandParticle;
import com.gmail.thelilchicken01.tff.particle.TffPortalParticle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TheFesterForest.modid, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(BlockInit.healthgem.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.rotting_leaves.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.rottingwood_sapling.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.tff_portal.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.rotting_flower.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.rotting_flower_pot.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.rotting_tall_grass.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.slimy_leaves.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.slimy_sapling.get(), RenderType.cutout());
		
		EntityRenderers.register(ModEntityTypes.rotting_skeleton.get(), RottingSkeletonRenderer::new);
		EntityRenderers.register(ModEntityTypes.crunch_beetle.get(), CrunchBeetleRenderer::new);
		EntityRenderers.register(ModEntityTypes.banshee.get(), BansheeRenderer::new);
		EntityRenderers.register(ModEntityTypes.volatile_ghost.get(), VolatileGhostRenderer::new);
		EntityRenderers.register(ModEntityTypes.wight.get(), WightRenderer::new);
		EntityRenderers.register(ModEntityTypes.forgemaster.get(), ForgemasterRenderer::new);
	}
	
	@SubscribeEvent
	public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
		
		event.put(ModEntityTypes.rotting_skeleton.get(), RottingSkeletonEntity.setAttributes());
		event.put(ModEntityTypes.crunch_beetle.get(), CrunchBeetleEntity.setAttributes());
		event.put(ModEntityTypes.banshee.get(), BansheeEntity.setAttributes());
		event.put(ModEntityTypes.volatile_ghost.get(), VolatileGhostEntity.setAttributes());
		event.put(ModEntityTypes.wight.get(), WightEntity.setAttributes());
		event.put(ModEntityTypes.forgemaster.get(), ForgemasterEntity.setAttributes());
		
	}
	
	@SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		//Same renderer as potions
    	event.registerEntityRenderer(ModEntityTypes.branch_charge.get(), 
    			(context) -> new ThrownItemRenderer<BranchCharge>(context));
    	
    	event.registerEntityRenderer(ModEntityTypes.bone_charge.get(), 
    			(context) -> new ThrownItemRenderer<BoneCharge>(context));
    	
    	event.registerEntityRenderer(ModEntityTypes.meteor_charge.get(), 
    			(context) -> new ThrownItemRenderer<MeteorCharge>(context));
    }
	
	@SubscribeEvent
	public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
		
		Minecraft.getInstance().particleEngine.register(ParticleInit.hellflame_particle.get(), HellflameParticle.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.tff_portal_particles.get(), TffPortalParticle.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.pocket_sand_particle.get(), PocketSandParticle.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.blood_particle.get(), BloodParticle.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.bone_particle.get(), BoneParticle.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.branch_particle.get(), BranchParticle.Provider::new);
	}
	
}
