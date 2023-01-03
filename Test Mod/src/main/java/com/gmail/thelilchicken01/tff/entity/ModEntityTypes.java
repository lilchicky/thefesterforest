package com.gmail.thelilchicken01.tff.entity;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.entity.custom.CrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.entity.custom.ForgemasterEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RottingSkeletonEntity;
import com.gmail.thelilchicken01.tff.entity.custom.VolatileGhostEntity;
import com.gmail.thelilchicken01.tff.entity.custom.WightEntity;
import com.gmail.thelilchicken01.tff.entity.projectile.BoneCharge;
import com.gmail.thelilchicken01.tff.entity.projectile.BranchCharge;
import com.gmail.thelilchicken01.tff.entity.projectile.MeteorCharge;

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
	
	public static RegistryObject<EntityType<BranchCharge>> branch_charge;
	public static RegistryObject<EntityType<BoneCharge>> bone_charge;
	public static RegistryObject<EntityType<MeteorCharge>> meteor_charge;
	
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
	
	//Wight
	public static final RegistryObject<EntityType<WightEntity>> wight =
			entity_types.register("wight", () -> EntityType.Builder.of(WightEntity::new, MobCategory.MONSTER)
					.sized(0.8f, 1.875f).build(new ResourceLocation(TheFesterForest.modid, "wight").toString()));
	
	//Forgemaster
	public static final RegistryObject<EntityType<ForgemasterEntity>> forgemaster =
			entity_types.register("forgemaster", () -> EntityType.Builder.of(ForgemasterEntity::new, MobCategory.MONSTER)
					.sized(1.75f, 4.2f).build(new ResourceLocation(TheFesterForest.modid, "forgemaster").toString()));
	
	//Brittle Branch Bullet
	
	static {
		branch_charge = entity_types.register("branch_charge", () -> EntityType.Builder
				.<BranchCharge>of(BranchCharge::new, MobCategory.MISC)
				.sized(0.2f, 0.2f).setUpdateInterval(2).setTrackingRange(64).setShouldReceiveVelocityUpdates(true)
				.build(TheFesterForest.modid + ":branch_charge"));
	}
	
	//Bone Charge Bullet
	
	static {
		bone_charge = entity_types.register("bone_charge", () -> EntityType.Builder
				.<BoneCharge>of(BoneCharge::new, MobCategory.MISC)
				.sized(0.3125f, 0.3125f).setUpdateInterval(2).setTrackingRange(64).setShouldReceiveVelocityUpdates(true)
				.build(TheFesterForest.modid + ":bone_charge"));
	}
	
	//Meteor
	
	static {
		meteor_charge = entity_types.register("meteor_charge", () -> EntityType.Builder
				.<MeteorCharge>of(MeteorCharge::new, MobCategory.MISC)
				.sized(0.4125f, 0.4125f).setUpdateInterval(2).setTrackingRange(64).setShouldReceiveVelocityUpdates(true)
				.build(TheFesterForest.modid + ":meteor_charge"));
	}
	
	
	public static void register(IEventBus eventBus) {
		entity_types.register(eventBus);
	}
	
}
