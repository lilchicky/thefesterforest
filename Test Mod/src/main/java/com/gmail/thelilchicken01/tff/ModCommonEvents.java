package com.gmail.thelilchicken01.tff;

import com.gmail.thelilchicken01.tff.init.BlockInit;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod.EventBusSubscriber(modid = TheFesterForest.modid, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonEvents {

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
		
		event.enqueueWork(() -> {
			
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockInit.rotting_flower.getId(), BlockInit.rotting_flower_pot);
			InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().build());
			
		});
		
	}
	
}
