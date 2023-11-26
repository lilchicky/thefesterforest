package com.gmail.thelilchicken01.tff.dataGen.client;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.BlockInit;
import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

	public ModItemModelProvider(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, TheFesterForest.MODID, helper);
		
		
	}
	
	protected void simpleBlockItem(Item item) {
		getBuilder(item.getRegistryName().toString()).parent(getExistingFile(modLoc("block/" + item.getRegistryName().getPath())));
	}
	
	protected void oneLayerItem(Item item, ResourceLocation texture) {
		ResourceLocation itemTexture = new ResourceLocation(texture.getNamespace(), "item/" + texture.getPath());
		if (existingFileHelper.exists(itemTexture, PackType.CLIENT_RESOURCES, ".png", "textures")) {
			getBuilder(item.getRegistryName().getPath()).parent(getExistingFile(mcLoc("item/generated")))
				.texture("layer0", itemTexture);
		}
		else {
			System.out.println("Texture for " + item.getRegistryName().toString() + " not present at " + itemTexture.toString());
		}
	}
	
	protected void oneLayerItem(Item item) {
		oneLayerItem(item, item.getRegistryName());
	}
	
	protected void handheldItem(Item item, ResourceLocation texture) {
		ResourceLocation itemTexture = new ResourceLocation(texture.getNamespace(), "item/" + texture.getPath());
		if (existingFileHelper.exists(itemTexture, PackType.CLIENT_RESOURCES, ".png", "textures")) {
			getBuilder(item.getRegistryName().getPath()).parent(getExistingFile(mcLoc("item/handheld")))
				.texture("layer0", itemTexture);
		}
		else {
			System.out.println("Texture for " + item.getRegistryName().toString() + " not present at " + itemTexture.toString());
		}
	}
	
	protected void handheldItem(Item item) {
		handheldItem(item, item.getRegistryName());
	}
	
	@Override
	protected void registerModels() {
		// Block Item
		simpleBlockItem(BlockInit.ROTTING_BRICKS.get().asItem());
		//simpleBlockItem(BlockInit.ROTTING_STONE.get().asItem());
		simpleBlockItem(BlockInit.FESTER_ORE.get().asItem());
		simpleBlockItem(BlockInit.SLIMY_PLANKS.get().asItem());
		//simpleBlockItem(BlockInit.CRACKED_ROTTING_STONE.get().asItem());
		simpleBlockItem(BlockInit.MOSSY_ROTTING_STONE.get().asItem());
		simpleBlockItem(BlockInit.ROTTING_STONE_BRICKS.get().asItem());
		//simpleBlockItem(BlockInit.CRUSHED_ROTTING_STONE.get().asItem());
		simpleBlockItem(BlockInit.ROTTING_GRAVE_DIRT.get().asItem());
		simpleBlockItem(BlockInit.SOUL_ROT.get().asItem());
		simpleBlockItem(BlockInit.ROTTING_SAND.get().asItem());
		simpleBlockItem(BlockInit.COMPRESSED_ROTTING_SAND.get().asItem());
		simpleBlockItem(BlockInit.ROTTING_GLASS.get().asItem());
		simpleBlockItem(BlockInit.CRACKED_ROTTING_STONE_BRICKS.get().asItem());
		simpleBlockItem(BlockInit.BLUE_FESTER_SHROOM_BLOCK.get().asItem());
		simpleBlockItem(BlockInit.ORANGE_FESTER_SHROOM_BLOCK.get().asItem());
		simpleBlockItem(BlockInit.CUBED_FUNGUS.get().asItem());
		simpleBlockItem(BlockInit.FROSTBITTEN_PLANKS.get().asItem());
		
		// Simple 2D Item
		oneLayerItem(ItemInit.ROTTING_BRICK.get());
		oneLayerItem(ItemInit.ROTTING_CATALYST.get());
		oneLayerItem(ItemInit.BUG_CARCASS.get());
		oneLayerItem(ItemInit.ANCIENT_WHISTLE.get());
		oneLayerItem(ItemInit.VOLATILE_NECKLACE.get());
		oneLayerItem(ItemInit.FROZEN_PENDANT.get());
		oneLayerItem(ItemInit.POCKET_SAND.get());
		oneLayerItem(ItemInit.FLESH_BALL.get());
		oneLayerItem(ItemInit.EXPLOSIVE_POWDER.get());
		oneLayerItem(ItemInit.ANGELIC_WHISTLE.get());
		oneLayerItem(ItemInit.BRANCH_CHARGE.get());
		oneLayerItem(ItemInit.BONE_CHARGE.get());
		oneLayerItem(ItemInit.METEOR_CHARGE.get());
		oneLayerItem(ItemInit.FORGEMASTER_HEART.get());
		oneLayerItem(ItemInit.HEAVY_STONE.get());
		oneLayerItem(ItemInit.PURIFYING_POWDER.get());
		oneLayerItem(ItemInit.BUG_EGGS.get());
		oneLayerItem(ItemInit.MECHANICAL_EYE.get());
		oneLayerItem(ItemInit.MECHANICAL_HELMET.get());
		oneLayerItem(ItemInit.MECHANICAL_CHESTPLATE.get());
		oneLayerItem(ItemInit.MECHANICAL_LEGGINGS.get());
		oneLayerItem(ItemInit.MECHANICAL_BOOTS.get());
		oneLayerItem(ItemInit.MECHANICAL_APPLE.get());
		oneLayerItem(ItemInit.SHIELD_BOOK.get());
		oneLayerItem(ItemInit.VOLATILE_BOOTS.get());
		oneLayerItem(ItemInit.VOLATILE_CHESTPLATE.get());
		oneLayerItem(ItemInit.VOLATILE_LEGGINGS.get());
		oneLayerItem(ItemInit.VOLATILE_HELMET.get());
		oneLayerItem(ItemInit.REETLE_REAGENTS.get());
		oneLayerItem(ItemInit.REETLE_ELYTRA.get());
		oneLayerItem(ItemInit.REETLE_HELMET.get());
		oneLayerItem(ItemInit.REETLE_CHESTPLATE.get());
		oneLayerItem(ItemInit.REETLE_LEGGINGS.get());
		oneLayerItem(ItemInit.REETLE_BOOTS.get());
		oneLayerItem(ItemInit.REETLE_SHELL.get());
		oneLayerItem(ItemInit.REETLE_QUEEN_ANTENNAE.get());
		oneLayerItem(ItemInit.ROTTING_CARROT.get());
		oneLayerItem(ItemInit.ROTTING_PIE.get());
		oneLayerItem(ItemInit.GRAVITY_GAUNTLET.get());
		oneLayerItem(ItemInit.MOON_SHOES.get());
		oneLayerItem(ItemInit.BANSHEE_HELMET.get());
		oneLayerItem(ItemInit.BANSHEE_CHESTPLATE.get());
		oneLayerItem(ItemInit.BANSHEE_LEGGINGS.get());
		oneLayerItem(ItemInit.BANSHEE_BOOTS.get());
		oneLayerItem(ItemInit.GOOPY_JELLO.get());
		oneLayerItem(ItemInit.SLIPPERY_GOOP.get());
		oneLayerItem(ItemInit.GOOPY_HELMET.get());
		oneLayerItem(ItemInit.GOOPY_CHESTPLATE.get());
		oneLayerItem(ItemInit.GOOPY_LEGGINGS.get());
		oneLayerItem(ItemInit.GOOPY_BOOTS.get());
		oneLayerItem(ItemInit.ROTTING_SLIMEBALL.get());
		oneLayerItem(ItemInit.RAW_ROTFISH.get());
		oneLayerItem(ItemInit.COOKED_ROTFISH.get());
		oneLayerItem(ItemInit.ROTFISH_FANG.get());
		oneLayerItem(ItemInit.ELECTRIC_CHARGE.get());
		oneLayerItem(ItemInit.SHOCK_NECKLACE.get());
		oneLayerItem(ItemInit.AMBECTRUM_DONUT.get());
		oneLayerItem(ItemInit.AMBECTRUM_JELLY.get());
		oneLayerItem(ItemInit.FESTER_CHUNK.get());
		oneLayerItem(ItemInit.ENERGETIC_FUNGUS.get());
		oneLayerItem(ItemInit.SHROOM_BUCKET.get());
		oneLayerItem(ItemInit.SHROOM_HAT.get());
		oneLayerItem(ItemInit.THICK_BONE.get());
		oneLayerItem(ItemInit.ROTFISH_SPECIAL.get());
		oneLayerItem(ItemInit.SHROOM_CLUSTER.get());
		oneLayerItem(ItemInit.COOKED_SHROOM_CLUSTER.get());
		oneLayerItem(ItemInit.ROTFISH_BOOTS.get());
		oneLayerItem(ItemInit.ROTFISH_CHESTPLATE.get());
		oneLayerItem(ItemInit.ROTFISH_LEGGINGS.get());
		oneLayerItem(ItemInit.ROTFISH_HELMET.get());
		oneLayerItem(ItemInit.ICE_SPIKE.get());
		oneLayerItem(ItemInit.FOOD_BOOK.get());
		oneLayerItem(ItemInit.METAL_SCRAP.get());
		oneLayerItem(ItemInit.GLOWING_BOOK.get());
		oneLayerItem(ItemInit.FROZEN_ROCK.get());
		oneLayerItem(ItemInit.ICE_BOOK.get());
		oneLayerItem(ItemInit.FROST_BOLT.get());
		oneLayerItem(ItemInit.FROZEN_APPLE.get());
		oneLayerItem(ItemInit.FROZEN_HEART.get());
		oneLayerItem(ItemInit.ROTTING_BOLT.get());
		oneLayerItem(ItemInit.FROZEN_BOOTS.get());
		oneLayerItem(ItemInit.FROZEN_LEGGINGS.get());
		oneLayerItem(ItemInit.FROZEN_CHESTPLATE.get());
		oneLayerItem(ItemInit.FROZEN_HELMET.get());
		oneLayerItem(ItemInit.FROZEN_SHARD.get());
		oneLayerItem(ItemInit.GLACIAL_STOMACH.get());
		oneLayerItem(ItemInit.GOOPY_STONE.get());
		oneLayerItem(ItemInit.FLOWER_CROWN.get());
		oneLayerItem(ItemInit.ICY_ARROW.get());
		oneLayerItem(ItemInit.GLACIAL_BOOTS.get());
		oneLayerItem(ItemInit.GLACIAL_LEGGINGS.get());
		oneLayerItem(ItemInit.GLACIAL_CHESTPLATE.get());
		oneLayerItem(ItemInit.GLACIAL_HELMET.get());
		oneLayerItem(ItemInit.ICE_RAMBLE_BUCKET.get());
		oneLayerItem(ItemInit.FROSTBITTEN_BOLT.get());
		oneLayerItem(ItemInit.AMBECTRUM_ICE_CREAM_SANDWICH.get());
		oneLayerItem(ItemInit.SLIMY_SUNDAE.get());
		oneLayerItem(ItemInit.GOOPY_SMOOTHIE.get());
		
		//Simple handheld items
		handheldItem(ItemInit.BONE_PICKAXE.get());
		handheldItem(ItemInit.GOOPY_STICK.get());
		handheldItem(ItemInit.GOOPY_SWORD.get());
		handheldItem(ItemInit.SHOCK_SWORD.get());
		handheldItem(ItemInit.ELECTRIC_PICKAXE.get());
		handheldItem(ItemInit.REAVER_BLADE.get());
		handheldItem(ItemInit.ANCIENT_DAGGER.get());
		handheldItem(ItemInit.ANCIENT_LONGSWORD.get());
		handheldItem(ItemInit.FESTERING_CLUB.get());
		handheldItem(ItemInit.ANCIENT_HATCHET.get());
		handheldItem(ItemInit.ANCIENT_HAMMER.get());
		handheldItem(ItemInit.SHARD_OF_ICE.get());
		handheldItem(ItemInit.VERDANT_BRANCH.get());
		handheldItem(ItemInit.DULL_ORB.get());
		handheldItem(ItemInit.FLAME_ORB.get());
		handheldItem(ItemInit.LEVITATE_ORB.get());
		handheldItem(ItemInit.POISON_ORB.get());
		handheldItem(ItemInit.FROZEN_ORB.get());
		handheldItem(ItemInit.LIFE_ORB.get());
		handheldItem(ItemInit.WITHER_ORB.get());
		handheldItem(ItemInit.REETLE_ORB.get());
		handheldItem(ItemInit.FORGEMASTER_ORB.get());
		handheldItem(ItemInit.GLACIATED_STAFF.get());
		handheldItem(ItemInit.ROTTING_STAFF.get());
		handheldItem(ItemInit.FROST_MALLET.get());
		handheldItem(ItemInit.FROSTBITTEN_ORB.get());
		handheldItem(ItemInit.ROTFISH_ORB.get());
		handheldItem(ItemInit.ICY_PAXEL.get());
		
	}
	
}
