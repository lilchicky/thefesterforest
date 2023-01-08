package com.gmail.thelilchicken01.tff.elytra;

import com.gmail.thelilchicken01.tff.init.ItemInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;

public class CustomElytraRenderProperties implements IItemRenderProperties {
	
	public static ModelReetleElytra reetle_elytra;
	
	private static boolean init = false;
	
	public static void initializeModels() {
		
		init = true;
		
		reetle_elytra = new ModelReetleElytra(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.reetle_elytra));
		
	}
	
	public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
		
		if (!init) {
			initializeModels();
		}
		
		else if (itemStack.getItem() == ItemInit.reetle_elytra.get()) {
			return reetle_elytra;
		}
		
		return _default;
	}
	
}
