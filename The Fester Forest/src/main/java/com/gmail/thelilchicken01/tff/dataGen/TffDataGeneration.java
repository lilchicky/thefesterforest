package com.gmail.thelilchicken01.tff.dataGen;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.dataGen.client.ModBlockstateProvider;
import com.gmail.thelilchicken01.tff.dataGen.client.ModItemModelProvider;
import com.gmail.thelilchicken01.tff.dataGen.client.lang.ModEnUsProvider;
import com.gmail.thelilchicken01.tff.dataGen.server.ModBlockTagsProvider;
import com.gmail.thelilchicken01.tff.dataGen.server.ModItemTagsProvider;
import com.gmail.thelilchicken01.tff.dataGen.server.ModLootTableProvider;
import com.gmail.thelilchicken01.tff.dataGen.server.ModRecipeProvider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = TheFesterForest.MODID, bus = Bus.MOD)
public class TffDataGeneration {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper()	;
		
		if (event.includeClient()) {
			//client data gen
			
			gen.addProvider(new ModBlockstateProvider(gen, helper));
			gen.addProvider(new ModItemModelProvider(gen, helper));
			gen.addProvider(new ModEnUsProvider(gen));
			
		}
		
		if (event.includeServer()) {

			ModBlockTagsProvider blockTags = new ModBlockTagsProvider(gen, helper);
			
			gen.addProvider(new ModRecipeProvider(gen));
			gen.addProvider(blockTags);
			gen.addProvider(new ModItemTagsProvider(gen, blockTags, helper));
			gen.addProvider(new ModLootTableProvider(gen));
			
		}
		
;	}
	
}
