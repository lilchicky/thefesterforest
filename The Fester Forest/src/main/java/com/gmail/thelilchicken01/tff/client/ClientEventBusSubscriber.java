package com.gmail.thelilchicken01.tff.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.client.AmbectrumRenderer;
import com.gmail.thelilchicken01.tff.entity.client.BansheeRenderer;
import com.gmail.thelilchicken01.tff.entity.client.CorrodedShroomRenderer;
import com.gmail.thelilchicken01.tff.entity.client.CrunchBeetleRenderer;
import com.gmail.thelilchicken01.tff.entity.client.DeepReaverRenderer;
import com.gmail.thelilchicken01.tff.entity.client.ForgemasterRenderer;
import com.gmail.thelilchicken01.tff.entity.client.PlayerCrunchBeetleRenderer;
import com.gmail.thelilchicken01.tff.entity.client.PylonRenderer;
import com.gmail.thelilchicken01.tff.entity.client.ReetleQueenRenderer;
import com.gmail.thelilchicken01.tff.entity.client.RotfishRenderer;
import com.gmail.thelilchicken01.tff.entity.client.RottingSkeletonRenderer;
import com.gmail.thelilchicken01.tff.entity.client.SeathrownSkeletonRenderer;
import com.gmail.thelilchicken01.tff.entity.client.VolatileGhostRenderer;
import com.gmail.thelilchicken01.tff.entity.client.WightRenderer;
import com.gmail.thelilchicken01.tff.entity.client.armor.ShroomHatRenderer;
import com.gmail.thelilchicken01.tff.entity.client.goop.GoopRenderer;
import com.gmail.thelilchicken01.tff.entity.client.goop.MediumGoopRenderer;
import com.gmail.thelilchicken01.tff.entity.client.goop.SmallGoopRenderer;
import com.gmail.thelilchicken01.tff.entity.custom.AmbectrumEntity;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.entity.custom.CorrodedShroomEntity;
import com.gmail.thelilchicken01.tff.entity.custom.CrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.entity.custom.DeepReaverEntity;
import com.gmail.thelilchicken01.tff.entity.custom.ForgemasterEntity;
import com.gmail.thelilchicken01.tff.entity.custom.PlayerCrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.entity.custom.PylonEntity;
import com.gmail.thelilchicken01.tff.entity.custom.ReetleQueenEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RotfishEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RottingSkeletonEntity;
import com.gmail.thelilchicken01.tff.entity.custom.SeathrownSkeletonEntity;
import com.gmail.thelilchicken01.tff.entity.custom.VolatileGhostEntity;
import com.gmail.thelilchicken01.tff.entity.custom.WightEntity;
import com.gmail.thelilchicken01.tff.entity.custom.goop.GoopEntity;
import com.gmail.thelilchicken01.tff.entity.custom.goop.MediumGoopEntity;
import com.gmail.thelilchicken01.tff.entity.custom.goop.SmallGoopEntity;
import com.gmail.thelilchicken01.tff.entity.projectile.BoneCharge;
import com.gmail.thelilchicken01.tff.entity.projectile.BranchCharge;
import com.gmail.thelilchicken01.tff.entity.projectile.ElectricCharge;
import com.gmail.thelilchicken01.tff.entity.projectile.FrozenRock;
import com.gmail.thelilchicken01.tff.entity.projectile.IceSpike;
import com.gmail.thelilchicken01.tff.entity.projectile.MeteorCharge;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.init.ParticleInit;
import com.gmail.thelilchicken01.tff.item.armor.reetleArmor.ReetleElytra;
import com.gmail.thelilchicken01.tff.item.armor.shroom_hat.ShroomHat;
import com.gmail.thelilchicken01.tff.particle.BloodParticle;
import com.gmail.thelilchicken01.tff.particle.BoneParticle;
import com.gmail.thelilchicken01.tff.particle.BranchParticle;
import com.gmail.thelilchicken01.tff.particle.HellflameParticle;
import com.gmail.thelilchicken01.tff.particle.IcyExplosionParticle;
import com.gmail.thelilchicken01.tff.particle.PocketSandParticle;
import com.gmail.thelilchicken01.tff.particle.TffPortalParticle;
import com.gmail.thelilchicken01.tff.util.ModItemProperties;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = TheFesterForest.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ModItemProperties.addCustomItemProperties();
		
		ItemBlockRenderTypes.setRenderLayer(BlockInit.ROTTING_LEAVES.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.ROTTINGWOOD_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.TFF_PORTAL.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.ROTTING_FLOWER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.ROTTING_FLOWER_POT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.SICKENING_FLOWER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.SLIMY_FLOWER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.SICKENING_FLOWER_POT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.ROTTING_TALL_GRASS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.SLIMY_LEAVES.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.SLIMY_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.WEEPING_GRASS.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.ROTTING_GLASS.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.CORRODED_SHROOM_FLOWER_POT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.CORRODED_SHROOM.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.FUNGAL_GROWTH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.TALL_FUNGAL_GROWTH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.FROSTBITTEN_LEAVES.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.FROSTBITTEN_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.ICICLE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.FROSTVINE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.FROSTVINE_FLOWER_POT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.ROTTINGWOOD_TRAPDOOR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.ROTTINGWOOD_DOOR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.SLIMY_TRAPDOOR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.SLIMY_DOOR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.FROSTBITTEN_TRAPDOOR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.FROSTBITTEN_DOOR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.CUBED_FUNGUS_TRAPDOOR.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.CUBED_FUNGUS_DOOR.get(), RenderType.translucent());
		
		EntityRenderers.register(ModEntityTypes.ROTTING_SKELETON.get(), RottingSkeletonRenderer::new);
		EntityRenderers.register(ModEntityTypes.CRUNCH_BEETLE.get(), CrunchBeetleRenderer::new);
		EntityRenderers.register(ModEntityTypes.BANSHEE.get(), BansheeRenderer::new);
		EntityRenderers.register(ModEntityTypes.VOLATILE_GHOST.get(), VolatileGhostRenderer::new);
		EntityRenderers.register(ModEntityTypes.WIGHT.get(), WightRenderer::new);
		EntityRenderers.register(ModEntityTypes.FORGEMASTER.get(), ForgemasterRenderer::new);
		EntityRenderers.register(ModEntityTypes.PLAYER_CRUNCH_BEETLE.get(), PlayerCrunchBeetleRenderer::new);
		EntityRenderers.register(ModEntityTypes.PYLON.get(), PylonRenderer::new);
		EntityRenderers.register(ModEntityTypes.REETLE_QUEEN.get(), ReetleQueenRenderer::new);
		EntityRenderers.register(ModEntityTypes.GOOP.get(), GoopRenderer::new);
		EntityRenderers.register(ModEntityTypes.GOOP_MEDIUM.get(), MediumGoopRenderer::new);
		EntityRenderers.register(ModEntityTypes.GOOP_SMALL.get(), SmallGoopRenderer::new);
		EntityRenderers.register(ModEntityTypes.ROTFISH.get(), RotfishRenderer::new);
		EntityRenderers.register(ModEntityTypes.AMBECTRUM.get(), AmbectrumRenderer::new);
		EntityRenderers.register(ModEntityTypes.SEATHROWN_SKELETON.get(), SeathrownSkeletonRenderer::new);
		EntityRenderers.register(ModEntityTypes.CORRODED_SHROOM.get(), CorrodedShroomRenderer::new);
		EntityRenderers.register(ModEntityTypes.DEEP_REAVER.get(), DeepReaverRenderer::new);
		
		ItemProperties.register(ItemInit.REETLE_ELYTRA.get(), new ResourceLocation(TheFesterForest.MODID, "broken"),
				(stack, arg1, arg2, arg3) -> ReetleElytra.isUsable(stack) ? 0 : 1);
	}
	
	@SubscribeEvent
	public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
		
		event.put(ModEntityTypes.ROTTING_SKELETON.get(), RottingSkeletonEntity.setAttributes());
		event.put(ModEntityTypes.CRUNCH_BEETLE.get(), CrunchBeetleEntity.setAttributes());
		event.put(ModEntityTypes.BANSHEE.get(), BansheeEntity.setAttributes());
		event.put(ModEntityTypes.VOLATILE_GHOST.get(), VolatileGhostEntity.setAttributes());
		event.put(ModEntityTypes.WIGHT.get(), WightEntity.setAttributes());
		event.put(ModEntityTypes.FORGEMASTER.get(), ForgemasterEntity.setAttributes());
		event.put(ModEntityTypes.PLAYER_CRUNCH_BEETLE.get(), PlayerCrunchBeetleEntity.setAttributes());
		event.put(ModEntityTypes.PYLON.get(), PylonEntity.setAttributes());
		event.put(ModEntityTypes.REETLE_QUEEN.get(), ReetleQueenEntity.setAttributes());
		event.put(ModEntityTypes.GOOP.get(), GoopEntity.setAttributes());
		event.put(ModEntityTypes.GOOP_MEDIUM.get(), MediumGoopEntity.setAttributes());
		event.put(ModEntityTypes.GOOP_SMALL.get(), SmallGoopEntity.setAttributes());
		event.put(ModEntityTypes.ROTFISH.get(), RotfishEntity.setAttributes());
		event.put(ModEntityTypes.AMBECTRUM.get(), AmbectrumEntity.setAttributes());
		event.put(ModEntityTypes.SEATHROWN_SKELETON.get(), SeathrownSkeletonEntity.setAttributes());
		event.put(ModEntityTypes.CORRODED_SHROOM.get(), CorrodedShroomEntity.setAttributes());
		event.put(ModEntityTypes.DEEP_REAVER.get(), DeepReaverEntity.setAttributes());
		
	}
	
	@SuppressWarnings("removal")
	@SubscribeEvent
	public static void registerArmorRenderer(final EntityRenderersEvent.AddLayers event) {
		
		GeoArmorRenderer.registerArmorRenderer(ShroomHat.class, new ShroomHatRenderer());
		
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
    	
    	event.registerEntityRenderer(ModEntityTypes.electric_charge.get(), 
    			(context) -> new ThrownItemRenderer<ElectricCharge>(context));
    	
    	event.registerEntityRenderer(ModEntityTypes.ice_spike.get(), 
    			(context) -> new ThrownItemRenderer<IceSpike>(context));
    	
    	event.registerEntityRenderer(ModEntityTypes.frozen_rock.get(), 
    			(context) -> new ThrownItemRenderer<FrozenRock>(context));
    }
	
	@SubscribeEvent
	public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
		
		Minecraft.getInstance().particleEngine.register(ParticleInit.HELLFLAME_PARTICLES.get(), HellflameParticle.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.TFF_PORTAL_PARTICLES.get(), TffPortalParticle.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.POCKET_SAND_PARTICLE.get(), PocketSandParticle.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.BLOOD_PARTICLE.get(), BloodParticle.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.BONE_PARTICLE.get(), BoneParticle.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.BRANCH_PARTICLE.get(), BranchParticle.Provider::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.ICY_EXPLOSION_PARTICLE.get(), IcyExplosionParticle.Provider::new);
	}
	
}
