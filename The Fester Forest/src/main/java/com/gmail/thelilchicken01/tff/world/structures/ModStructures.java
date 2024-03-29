package com.gmail.thelilchicken01.tff.world.structures;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModStructures {

    /**
     * We are using the Deferred Registry system to register our structure as this is the preferred way on Forge.
     * This will handle registering the base structure for us at the correct time so we don't have to handle it ourselves.
     *
     * HOWEVER, do note that Deferred Registries only work for anything that is a Forge Registry. This means that
     * configured structures and configured features need to be registered directly to BuiltinRegistries as there
     * is no Deferred Registry system for them.
     */
    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, TheFesterForest.MODID);

    /**
     * Registers the base structure itself and sets what its path is. In this case,
     * this base structure will have the resourcelocation of structure_tutorial:sky_structures.
     */
    public static final RegistryObject<StructureFeature<?>> REETLE_CAVE = DEFERRED_REGISTRY_STRUCTURE.register("reetle_cave", ReetleCave::new);
    public static final RegistryObject<StructureFeature<?>> SOUL_WELL = DEFERRED_REGISTRY_STRUCTURE.register("soul_well", SoulWell::new);
    public static final RegistryObject<StructureFeature<?>> RUINS = DEFERRED_REGISTRY_STRUCTURE.register("ruins", Ruins::new);
    public static final RegistryObject<StructureFeature<?>> OCEAN_FLOOR = DEFERRED_REGISTRY_STRUCTURE.register("ocean_floor", OceanFloor::new);
    public static final RegistryObject<StructureFeature<?>> BOAT = DEFERRED_REGISTRY_STRUCTURE.register("boat", Boat::new);
    public static final RegistryObject<StructureFeature<?>> SKY_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("sky_structure", SkyStructure::new);
    public static final RegistryObject<StructureFeature<?>> FLOATING_TOWER = DEFERRED_REGISTRY_STRUCTURE.register("floating_tower", FloatingTower::new);
    public static final RegistryObject<StructureFeature<?>> ICE_CASTLE = DEFERRED_REGISTRY_STRUCTURE.register("ice_castle", IceCastle::new);
    
    public static void register(IEventBus bus) {
    	DEFERRED_REGISTRY_STRUCTURE.register(bus);
    }

}