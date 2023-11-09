package com.gmail.thelilchicken01.tff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gmail.thelilchicken01.tff.capability.SwimHandler;
import com.gmail.thelilchicken01.tff.config.TFFClientConfigs;
import com.gmail.thelilchicken01.tff.config.TFFCommonConfigs;
import com.gmail.thelilchicken01.tff.effect.ModEffects;
import com.gmail.thelilchicken01.tff.elytra.ReetleElytraArmorStandLayer;
import com.gmail.thelilchicken01.tff.elytra.ReetleElytraLayer;
import com.gmail.thelilchicken01.tff.enchantment.ModEnchants;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.init.ParticleInit;
import com.gmail.thelilchicken01.tff.integration.ModCompat;
import com.gmail.thelilchicken01.tff.item.magic.FlowerCrown;
import com.gmail.thelilchicken01.tff.network.TFFNetworkHandler;
import com.gmail.thelilchicken01.tff.villager.ModPOIs;
import com.gmail.thelilchicken01.tff.world.dimension.ModDimensions;
import com.gmail.thelilchicken01.tff.world.feature.ModPatchConfig;
import com.gmail.thelilchicken01.tff.world.feature.TffConfiguredFeatures;
import com.gmail.thelilchicken01.tff.world.feature.TffPlacedFeature;
import com.gmail.thelilchicken01.tff.world.feature.TffTreeDecorators;
import com.gmail.thelilchicken01.tff.world.structures.ModStructures;

import be.florens.expandability.ExpandAbility;
import be.florens.expandability.forge.ExpandAbilityForge;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import software.bernie.geckolib3.GeckoLib;

// Thanks to Cy4's tutorials, got me from nothing to this :)
// Thanks to Kaupenjoe's Tutorials
// Thanks to WafflesAreBetter for all the dimension help
// Thanks to Undergarden for the portal code
// Thanks to TelepathicGrunt for the structure code!
// Thanks to GunsWithoutRoses (The best gun mod ever created) for custom projectiles
// Thanks to Reliquary for help with doing things on player death/hurt events
// Thanks to ochotonida for help with capabilities and network code

@Mod("tff")
public class TheFesterForest {
	
	public static final String MODID = "tff";
	
	public static final Logger LOGGER = LogManager.getLogger();	
	
	//Damage Sources
	public static final DamageSource BANSHEE = new DamageSource(MODID + "_banshee").bypassArmor();
	public static final DamageSource VOLATILE_GHOST = new DamageSource(MODID + "_volatile_ghost").setExplosion();
	public static final DamageSource SAND_DAMAGE = new DamageSource(MODID + "_sand_damage");
	public static final DamageSource KNOCKUP_DAMAGE = new DamageSource(MODID + "_knockup_damage");
	public static final DamageSource GOOP_ACID = new DamageSource(MODID + "_goop_acid").bypassArmor();
	
	public static final CreativeModeTab TFF_TAB = new CreativeModeTab(MODID) {

		@Override
		@OnlyIn(Dist.CLIENT)
		public net.minecraft.world.item.ItemStack makeIcon() {
			return new ItemStack(BlockInit.ROTTING_LOG.get());
		}
		
	};
	
	public TheFesterForest() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		LOGGER.info("Initializing the Spook");
		
		ItemInit.ITEMS.register(bus);
		LOGGER.info("Items initialized");
		
		BlockInit.BLOCKS.register(bus);
		LOGGER.info("Blocks Initialized");
		
		ParticleInit.PARTICLES.register(bus);
		LOGGER.info("Particles initialized");
		
		TffConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
		TffPlacedFeature.PLACED_FEATURES.register(bus);
		TffTreeDecorators.TREE_DECORATORS.register(bus);
		LOGGER.info("Features initialized");
		
		ModDimensions.register();
		LOGGER.info("The Fester Forest dimension initialized");
		
		ModPOIs.register(bus);
		LOGGER.info("POIs initialized");
		
		ModEntityTypes.register(bus);
		LOGGER.info("Entities initialized");
		
		ModEffects.register(bus);
		LOGGER.info("Potion Effects initialized");
		
		ModStructures.register(bus);
		LOGGER.info("Structures initialized");
		
		ModEnchants.MOD_ENCHANTS.register(bus);
		LOGGER.info("Enchantments initialized");
		
		ModPatchConfig.MOD_FEATURES.register(bus);
		
		ModCompat.initCompat();
		SwimHandler.setup();
		
		GeckoLib.initialize();
		ExpandAbility.init();
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TFFClientConfigs.SPEC, "tff-client.toml");
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TFFCommonConfigs.SPEC, "tff-common.toml");
		LOGGER.info("Config Loaded");
		
		MinecraftForge.EVENT_BUS.register(this);
		
		if (FMLEnvironment.dist.isClient()) bus.addListener(this::registerElytraLayer);
		
		LOGGER.info("Happy Adventuring!");
		
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
