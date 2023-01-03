package com.gmail.thelilchicken01.tff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gmail.thelilchicken01.tff.client.ClientEventBusSubscriber;
import com.gmail.thelilchicken01.tff.client.ServerEventBusSubscriber;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.event.ModEventBusEvents;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.init.ParticleInit;
import com.gmail.thelilchicken01.tff.villager.ModPOIs;
import com.gmail.thelilchicken01.tff.world.dimension.ModDimensions;
import com.gmail.thelilchicken01.tff.world.structures.ModStructures;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

// Thanks to Cy4's tutorials, got me from nothing to this :)
// Thanks to Kaupenjoe's Tutorials
// Thanks to WafflesAreBetter for all the dimension help
// Thanks to Undergarden for the portal code
// Thanks to TelepathicGrunt for the structure code!
// Thanks to GunsWithoutRoses (The best gun mod ever created) for being the only projectile reference I could find

@Mod("tff")
public class TheFesterForest {
	
	public static final String modid = "tff";
	
	public static final ClientEventBusSubscriber ebs = new ClientEventBusSubscriber();
	public static final ServerEventBusSubscriber sebs = new ServerEventBusSubscriber();
	public static final ModEventBusEvents mebe = new ModEventBusEvents();
	
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
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
}
