package com.gmail.thelilchicken01.tff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gmail.thelilchicken01.tff.client.ClientProxy;
import com.gmail.thelilchicken01.tff.client.CommonProxy;
import com.gmail.thelilchicken01.tff.config.TFFClientConfigs;
import com.gmail.thelilchicken01.tff.config.TFFCommonConfigs;
import com.gmail.thelilchicken01.tff.elytra.ModModelLayers;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.init.ParticleInit;
import com.gmail.thelilchicken01.tff.villager.ModPOIs;
import com.gmail.thelilchicken01.tff.world.dimension.ModDimensions;
import com.gmail.thelilchicken01.tff.world.structures.ModStructures;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
	
	public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
	
	public static final CreativeModeTab tff_tab = new CreativeModeTab(modid) {

		@Override
		@OnlyIn(Dist.CLIENT)
		public net.minecraft.world.item.ItemStack makeIcon() {
			return new ItemStack(BlockInit.rotting_log.get());
		}
		
	};
	
	public TheFesterForest() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		bus.addListener(this::setupClient);
		bus.addListener(this::setupEntityModelLayers);
		
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
	}
	
	private void setupClient(FMLClientSetupEvent event) {
        PROXY.clientInit();
    }
	
	private void setupEntityModelLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
		
		ModModelLayers.register(event);
		
	}
	
}
