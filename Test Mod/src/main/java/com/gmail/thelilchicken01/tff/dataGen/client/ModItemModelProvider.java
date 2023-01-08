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
		super(generator, TheFesterForest.modid, helper);
		
		
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
		simpleBlockItem(BlockInit.rotting_bricks.get().asItem());
		simpleBlockItem(BlockInit.rotting_stone.get().asItem());
		simpleBlockItem(BlockInit.fester_ore.get().asItem());
		simpleBlockItem(BlockInit.slimy_planks.get().asItem());
		simpleBlockItem(BlockInit.cracked_rotting_stone.get().asItem());
		simpleBlockItem(BlockInit.mossy_rotting_stone.get().asItem());
		simpleBlockItem(BlockInit.rotting_stone_bricks.get().asItem());
		
		// Simple 2D Item
		oneLayerItem(ItemInit.rotting_brick.get());
		oneLayerItem(ItemInit.rotting_catalyst.get());
		oneLayerItem(ItemInit.bug_carcass.get());
		oneLayerItem(ItemInit.ancient_whistle.get());
		oneLayerItem(ItemInit.volatile_necklace.get());
		oneLayerItem(ItemInit.frozen_pendant.get());
		oneLayerItem(ItemInit.pocket_sand.get());
		oneLayerItem(ItemInit.flesh_ball.get());
		oneLayerItem(ItemInit.explosive_powder.get());
		oneLayerItem(ItemInit.angelic_whistle.get());
		oneLayerItem(ItemInit.branch_charge.get());
		oneLayerItem(ItemInit.bone_charge.get());
		oneLayerItem(ItemInit.meteor_charge.get());
		oneLayerItem(ItemInit.forgemaster_heart.get());
		oneLayerItem(ItemInit.heavy_stone.get());
		oneLayerItem(ItemInit.purifying_powder.get());
		oneLayerItem(ItemInit.bug_eggs.get());
		oneLayerItem(ItemInit.mechanical_eye.get());
		oneLayerItem(ItemInit.mechanical_helmet.get());
		oneLayerItem(ItemInit.mechanical_chestplate.get());
		oneLayerItem(ItemInit.mechanical_leggings.get());
		oneLayerItem(ItemInit.mechanical_boots.get());
		oneLayerItem(ItemInit.mechanical_apple.get());
		oneLayerItem(ItemInit.shield_book.get());
		oneLayerItem(ItemInit.volatile_boots.get());
		oneLayerItem(ItemInit.volatile_chestplate.get());
		oneLayerItem(ItemInit.volatile_leggings.get());
		oneLayerItem(ItemInit.volatile_helmet.get());
		oneLayerItem(ItemInit.reetle_reagents.get());
		
		//Simple handheld items
		handheldItem(ItemInit.bone_pickaxe.get());
		
	}
	
}
