package com.gmail.thelilchicken01.tff.entity;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.entity.custom.CrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RottingSkeletonEntity;
import com.gmail.thelilchicken01.tff.entity.custom.VolatileGhostEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {

	public static final DeferredRegister<EntityType<?>> entity_types =
			DeferredRegister.create(ForgeRegistries.ENTITIES, TheFesterForest.modid);
	
	//Rotting Skeleton
	public static final RegistryObject<EntityType<RottingSkeletonEntity>> rotting_skeleton =
			entity_types.register("rotting_skeleton", () -> EntityType.Builder.of(RottingSkeletonEntity::new, MobCategory.MONSTER).sized(0.8f, 1.875f) //width, height of hitbox
					.build(new ResourceLocation(TheFesterForest.modid, "rotting_skeleton").toString()));
	
	//Crunch Beetle
	public static final RegistryObject<EntityType<CrunchBeetleEntity>> crunch_beetle =
			entity_types.register("crunch_beetle", () -> EntityType.Builder.of(CrunchBeetleEntity::new, MobCategory.CREATURE).sized(0.5f, 0.4f) //width, height of hitbox
					.build(new ResourceLocation(TheFesterForest.modid, "crunch_beetle").toString()));
	
	//Banshee
	public static final RegistryObject<EntityType<BansheeEntity>> banshee =
			entity_types.register("banshee", () -> EntityType.Builder.of(BansheeEntity::new, MobCategory.MONSTER).sized(0.8f, 1.875f) //width, height of hitbox
					.build(new ResourceLocation(TheFesterForest.modid, "banshee").toString()));
	
	//Volatile Ghost
	public static final RegistryObject<EntityType<VolatileGhostEntity>> volatile_ghost =
			entity_types.register("volatile_ghost", () -> EntityType.Builder.of(VolatileGhostEntity::new, MobCategory.MONSTER).sized(0.8f, 1.875f) //width, height of hitbox
					.build(new ResourceLocation(TheFesterForest.modid, "volatile_ghost").toString()));
	
	
	public static void register(IEventBus eventBus) {
		entity_types.register(eventBus);
	}
	
}
