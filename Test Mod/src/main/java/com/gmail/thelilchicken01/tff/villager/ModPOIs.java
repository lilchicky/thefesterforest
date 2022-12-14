package com.gmail.thelilchicken01.tff.villager;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPOIs {

	public static final DeferredRegister<PoiType> poi = DeferredRegister.create(ForgeRegistries.POI_TYPES, TheFesterForest.modid);
	
	public static final RegistryObject<PoiType> tff_portal = poi.register("tff_portal", () -> new PoiType("tff_portal", PoiType.getBlockStates(BlockInit.tff_portal.get()), 0, 1));
	
	public static void register(IEventBus bus) {
		poi.register(bus);
	}
	
}
