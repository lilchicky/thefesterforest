package com.gmail.thelilchicken01.tff.entity;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.entity.custom.CrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.entity.custom.ForgemasterEntity;
import com.gmail.thelilchicken01.tff.entity.custom.PlayerCrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.entity.custom.PylonEntity;
import com.gmail.thelilchicken01.tff.entity.custom.ReetleQueenEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RotfishEntity;
import com.gmail.thelilchicken01.tff.entity.custom.RottingSkeletonEntity;
import com.gmail.thelilchicken01.tff.entity.custom.VolatileGhostEntity;
import com.gmail.thelilchicken01.tff.entity.custom.WightEntity;
import com.gmail.thelilchicken01.tff.entity.custom.goop.GoopEntity;
import com.gmail.thelilchicken01.tff.entity.custom.goop.MediumGoopEntity;
import com.gmail.thelilchicken01.tff.entity.custom.goop.SmallGoopEntity;
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

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
			DeferredRegister.create(ForgeRegistries.ENTITIES, TheFesterForest.MODID);
	
	public static RegistryObject<EntityType<BranchCharge>> branch_charge;
	public static RegistryObject<EntityType<BoneCharge>> bone_charge;
	public static RegistryObject<EntityType<MeteorCharge>> meteor_charge;
	
	//Rotting Skeleton
	public static final RegistryObject<EntityType<RottingSkeletonEntity>> ROTTING_SKELETON =
			ENTITY_TYPES.register("rotting_skeleton", () -> EntityType.Builder.of(RottingSkeletonEntity::new, MobCategory.MONSTER).sized(0.8f, 1.875f) //width, height of hitbox
					.build(new ResourceLocation(TheFesterForest.MODID, "rotting_skeleton").toString()));
	
	//Crunch Beetle
	public static final RegistryObject<EntityType<CrunchBeetleEntity>> CRUNCH_BEETLE =
			ENTITY_TYPES.register("crunch_beetle", () -> EntityType.Builder.of(CrunchBeetleEntity::new, MobCategory.CREATURE).sized(0.5f, 0.4f) //width, height of hitbox
					.build(new ResourceLocation(TheFesterForest.MODID, "crunch_beetle").toString()));
	
	//Player Crunch Beetle
	public static final RegistryObject<EntityType<PlayerCrunchBeetleEntity>> PLAYER_CRUNCH_BEETLE =
			ENTITY_TYPES.register("player_crunch_beetle", () -> EntityType.Builder.of(PlayerCrunchBeetleEntity::new, MobCategory.CREATURE).sized(0.5f, 0.4f).setShouldReceiveVelocityUpdates(true) //width, height of hitbox
					.build(new ResourceLocation(TheFesterForest.MODID, "crunch_beetle").toString()));
	
	//Banshee
	public static final RegistryObject<EntityType<BansheeEntity>> BANSHEE =
			ENTITY_TYPES.register("banshee", () -> EntityType.Builder.of(BansheeEntity::new, MobCategory.MONSTER).sized(0.8f, 1.875f) //width, height of hitbox
					.build(new ResourceLocation(TheFesterForest.MODID, "banshee").toString()));
	
	//Volatile Ghost
	public static final RegistryObject<EntityType<VolatileGhostEntity>> VOLATILE_GHOST =
			ENTITY_TYPES.register("volatile_ghost", () -> EntityType.Builder.of(VolatileGhostEntity::new, MobCategory.MONSTER).sized(0.8f, 1.875f) //width, height of hitbox
					.build(new ResourceLocation(TheFesterForest.MODID, "volatile_ghost").toString()));
	
	//Wight
	public static final RegistryObject<EntityType<WightEntity>> WIGHT =
			ENTITY_TYPES.register("wight", () -> EntityType.Builder.of(WightEntity::new, MobCategory.MONSTER)
					.sized(0.8f, 1.875f).build(new ResourceLocation(TheFesterForest.MODID, "wight").toString()));
	
	//Forgemaster's Pylon
	public static final RegistryObject<EntityType<PylonEntity>> PYLON =
			ENTITY_TYPES.register("pylon", () -> EntityType.Builder.of(PylonEntity::new, MobCategory.MONSTER)
					.sized(0.6f, 1.7f).build(new ResourceLocation(TheFesterForest.MODID, "pylon").toString()));
	
	//Forgemaster
	public static final RegistryObject<EntityType<ForgemasterEntity>> FORGEMASTER =
			ENTITY_TYPES.register("forgemaster", () -> EntityType.Builder.of(ForgemasterEntity::new, MobCategory.MONSTER)
					.sized(1.75f, 4.2f).build(new ResourceLocation(TheFesterForest.MODID, "forgemaster").toString()));
	
	//Reetle Queen
	public static final RegistryObject<EntityType<ReetleQueenEntity>> REETLE_QUEEN =
			ENTITY_TYPES.register("reetle_queen", () -> EntityType.Builder.of(ReetleQueenEntity::new, MobCategory.MONSTER)
					.sized(1.4f, 0.5f).build(new ResourceLocation(TheFesterForest.MODID, "reetle_queen").toString()));
	
	//Goop
	public static final RegistryObject<EntityType<GoopEntity>> GOOP =
			ENTITY_TYPES.register("goop", () -> EntityType.Builder.of(GoopEntity::new, MobCategory.MONSTER)
					.sized(1.4f, 1.4f).build(new ResourceLocation(TheFesterForest.MODID, "goop").toString()));
	
	//Medium Goop
	public static final RegistryObject<EntityType<MediumGoopEntity>> GOOP_MEDIUM =
			ENTITY_TYPES.register("goop_medium", () -> EntityType.Builder.of(MediumGoopEntity::new, MobCategory.MONSTER)
					.sized(1.4f * 0.6666f, 1.4f * 0.6666f).build(new ResourceLocation(TheFesterForest.MODID, "goop_medium").toString()));
		
	//Small Goop
	public static final RegistryObject<EntityType<SmallGoopEntity>> GOOP_SMALL =
			ENTITY_TYPES.register("goop_small", () -> EntityType.Builder.of(SmallGoopEntity::new, MobCategory.MONSTER)
					.sized(1.4f * 0.4f, 1.4f * 0.4f).build(new ResourceLocation(TheFesterForest.MODID, "goop_small").toString()));
	
	//Rotfish
	public static final RegistryObject<EntityType<RotfishEntity>> ROTFISH =
			ENTITY_TYPES.register("rotfish", () -> EntityType.Builder.of(RotfishEntity::new, MobCategory.MONSTER)
					.sized(1.0f * 1.0f, 1.0f).build(new ResourceLocation(TheFesterForest.MODID, "rotfish").toString()));
	
	//Brittle Branch Bullet
	
	static {
		branch_charge = ENTITY_TYPES.register("branch_charge", () -> EntityType.Builder
				.<BranchCharge>of(BranchCharge::new, MobCategory.MISC)
				.sized(0.2f, 0.2f).setUpdateInterval(2).setTrackingRange(64).setShouldReceiveVelocityUpdates(true)
				.build(TheFesterForest.MODID + ":branch_charge"));
	}
	
	//Bone Charge Bullet
	
	static {
		bone_charge = ENTITY_TYPES.register("bone_charge", () -> EntityType.Builder
				.<BoneCharge>of(BoneCharge::new, MobCategory.MISC)
				.sized(0.3125f, 0.3125f).setUpdateInterval(2).setTrackingRange(64).setShouldReceiveVelocityUpdates(true)
				.build(TheFesterForest.MODID + ":bone_charge"));
	}
	
	//Meteor
	
	static {
		meteor_charge = ENTITY_TYPES.register("meteor_charge", () -> EntityType.Builder
				.<MeteorCharge>of(MeteorCharge::new, MobCategory.MISC)
				.sized(0.4125f, 0.4125f).setUpdateInterval(2).setTrackingRange(64).setShouldReceiveVelocityUpdates(true)
				.build(TheFesterForest.MODID + ":meteor_charge"));
	}
	
	
	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}
	
}
