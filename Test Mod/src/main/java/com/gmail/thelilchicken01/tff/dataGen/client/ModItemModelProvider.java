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
	}
	
}
