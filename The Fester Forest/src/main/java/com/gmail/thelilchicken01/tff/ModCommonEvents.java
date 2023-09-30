package com.gmail.thelilchicken01.tff;

import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.ModWaterMonster;
import com.gmail.thelilchicken01.tff.entity.custom.AmbectrumEntity;
import com.gmail.thelilchicken01.tff.entity.custom.CorrodedShroomEntity;
import com.gmail.thelilchicken01.tff.entity.custom.CrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RotfishEntity;
import com.gmail.thelilchicken01.tff.entity.custom.SeathrownSkeletonEntity;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.network.TFFNetworkHandler;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod.EventBusSubscriber(modid = TheFesterForest.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonEvents {

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
		
		event.enqueueWork(() -> {
			
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockInit.ROTTING_FLOWER.getId(), BlockInit.ROTTING_FLOWER_POT);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockInit.SICKENING_FLOWER.getId(), BlockInit.SICKENING_FLOWER_POT);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockInit.CORRODED_SHROOM.getId(), BlockInit.CORRODED_SHROOM_FLOWER_POT);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockInit.FROSTVINE.getId(), BlockInit.FROSTVINE_FLOWER_POT);
			
			InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().build());
			InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CHARM.getMessageBuilder().build());
			InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BODY.getMessageBuilder().build());
			InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().build());
			InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BELT.getMessageBuilder().build());
			InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HANDS.getMessageBuilder().build());
			InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("feet").priority(220).icon(InventoryMenu.EMPTY_ARMOR_SLOT_BOOTS).build());
			
			TFFNetworkHandler.register();
			
			setupSpawningRules();
			
		});
		
	}
	
	private static void setupSpawningRules() {
		
		/*
		 * Land Monsters
		 */
		
		SpawnPlacements.register(ModEntityTypes.ROTTING_SKELETON.get(), 
				SpawnPlacements.Type.ON_GROUND, 
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 
				Monster::checkMonsterSpawnRules);
		
		SpawnPlacements.register(ModEntityTypes.BANSHEE.get(), 
				SpawnPlacements.Type.ON_GROUND, 
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 
				Monster::checkMonsterSpawnRules);
		
		SpawnPlacements.register(ModEntityTypes.VOLATILE_GHOST.get(), 
				SpawnPlacements.Type.ON_GROUND, 
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 
				Monster::checkMonsterSpawnRules);
		
		SpawnPlacements.register(ModEntityTypes.WIGHT.get(), 
				SpawnPlacements.Type.ON_GROUND, 
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 
				Monster::checkMonsterSpawnRules);
		
		SpawnPlacements.register(ModEntityTypes.GOOP.get(), 
				SpawnPlacements.Type.ON_GROUND, 
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 
				Monster::checkMonsterSpawnRules);
		
		/*
		 * Land Creatures
		 */
		
		SpawnPlacements.register(ModEntityTypes.CRUNCH_BEETLE.get(), 
				SpawnPlacements.Type.ON_GROUND, 
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 
				CrunchBeetleEntity::checkTFFAnimalSpawnRules);
		
		/*
		 * Water Monsters
		 */
		
		SpawnPlacements.register(ModEntityTypes.AMBECTRUM.get(), 
				SpawnPlacements.Type.NO_RESTRICTIONS, 
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 
				AmbectrumEntity::checkSurfaceWaterMonsterSpawnRules);
		
		SpawnPlacements.register(ModEntityTypes.ROTFISH.get(), 
				SpawnPlacements.Type.NO_RESTRICTIONS, 
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 
				RotfishEntity::checkSurfaceWaterMonsterSpawnRules);
		
		SpawnPlacements.register(ModEntityTypes.SEATHROWN_SKELETON.get(), 
				SpawnPlacements.Type.NO_RESTRICTIONS, 
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 
				SeathrownSkeletonEntity::checkSurfaceWaterMonsterSpawnRules);
		
	}
	
}
