package com.gmail.thelilchicken01.tff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gmail.thelilchicken01.tff.config.TFFClientConfigs;
import com.gmail.thelilchicken01.tff.config.TFFCommonConfigs;
import com.gmail.thelilchicken01.tff.elytra.ReetleElytraArmorStandLayer;
import com.gmail.thelilchicken01.tff.elytra.ReetleElytraLayer;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.init.ParticleInit;
import com.gmail.thelilchicken01.tff.villager.ModPOIs;
import com.gmail.thelilchicken01.tff.world.dimension.ModDimensions;
import com.gmail.thelilchicken01.tff.world.structures.ModStructures;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import software.bernie.geckolib3.GeckoLib;

// Thanks to Cy4's tutorials, got me from nothing to this :)
// Thanks to Kaupenjoe's Tutorials
// Thanks to WafflesAreBetter for all the dimension help
// Thanks to Undergarden for the portal code
// Thanks to TelepathicGrunt for the structure code!
// Thanks to GunsWithoutRoses (The best gun mod ever created) for being the only projectile reference I could find
// Thanks to Reliquary for help with doing things on player death/hurt events

@Mod("tff")
public class TheFesterForest {
	
	public static final String modid = "tff";
	
	public static final Logger LOGGER = LogManager.getLogger();	
	//Damage Sources
	public static final DamageSource banshee = new DamageSource(modid + "_banshee").bypassArmor();
	public static final DamageSource volatile_ghost = new DamageSource(modid + "_volatile_ghost").setExplosion();
	public static final DamageSource sand_damage = new DamageSource(modid + "_sand_damage");
	public static final DamageSource knockup_damage = new DamageSource(modid + "_knockup_damage");
	
	public static final CreativeModeTab tff_tab = new CreativeModeTab(modid) {

		@Override
		@OnlyIn(Dist.CLIENT)
		public net.minecraft.world.item.ItemStack makeIcon() {
			return new ItemStack(BlockInit.rotting_log.get());
		}
		
	};
	
	public TheFesterForest() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		ItemInit.items.register(bus);
		BlockInit.blocks.register(bus);
		ParticleInit.particles.register(bus);
		ModDimensions.register();
		ModPOIs.register(bus);
		ModEntityTypes.register(bus);
		ModStructures.register(bus);
		
		GeckoLib.initialize();
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TFFClientConfigs.SPEC, "tff-client.toml");
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TFFCommonConfigs.SPEC, "tff-common.toml");
		
		MinecraftForge.EVENT_BUS.register(this);
		
		if (FMLEnvironment.dist.isClient()) bus.addListener(this::registerElytraLayer);
		
	}
	
	@OnlyIn(Dist.CLIENT)
	private void registerElytraLayer(EntityRenderersEvent event) {
		if(event instanceof EntityRenderersEvent.AddLayers addLayersEvent){
			EntityModelSet entityModels = addLayersEvent.getEntityModels();
			addLayersEvent.getSkins().forEach(s -> {
				LivingEntityRenderer<? extends Player, ? extends EntityModel<? extends Player>> livingEntityRenderer = addLayersEvent.getSkin(s);
				if(livingEntityRenderer instanceof PlayerRenderer playerRenderer){
					playerRenderer.addLayer(new ReetleElytraLayer(playerRenderer, entityModels));
				}
			});
			LivingEntityRenderer<ArmorStand, ? extends EntityModel<ArmorStand>> livingEntityRenderer = addLayersEvent.getRenderer(EntityType.ARMOR_STAND);
			if(livingEntityRenderer instanceof ArmorStandRenderer armorStandRenderer){
				armorStandRenderer.addLayer(new ReetleElytraArmorStandLayer(armorStandRenderer, entityModels));
			}

		}
	}
	
}
