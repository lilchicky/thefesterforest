package com.gmail.thelilchicken01.tff.integration;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEITffModPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		
		return new ResourceLocation(TheFesterForest.modid, "jei_plugin");
	}
	
	//These are for if I add a new workstation.

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		
		IModPlugin.super.registerCategories(registration);
	}
	
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		
		IModPlugin.super.registerRecipes(registration);
	}
	
}
