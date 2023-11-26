package com.gmail.thelilchicken01.tff.item.armor.glacialArmor;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.capability.PetNameHandler;
import com.gmail.thelilchicken01.tff.capability.PetSpawnHandler;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.custom.IceRambleEntity;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.ModArmorMaterial;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFArmorItem;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GlacialBoots extends TFFArmorItem {
	
	private String[] drops = {"Glacial Titan", "Fester Forest Loot Chests"};

	public GlacialBoots() {
		super(ModArmorMaterial.GLACIAL, EquipmentSlot.FEET, 
				new Properties().tab(TheFesterForest.TFF_TAB));
		
	}
	
	@Override
	public void onArmorTick(ItemStack stack, Level level, Player player) {
		
		super.onArmorTick(stack, level, player);
		
		if (ArmorSets.GLACIAL.getArmorSet(player) == SetCount.TWO || ArmorSets.GLACIAL.getArmorSet(player) == SetCount.FOUR) {
			
			if (player instanceof ServerPlayer serverPlayer) {
				
				player.getCapability(PetSpawnHandler.CAPABILITY).ifPresent(
						handler -> {
							if (!handler.hasPet()) {
								player.getCapability(PetNameHandler.CAPABILITY).ifPresent(
										nameHandler -> {
											if (nameHandler.getCurrentUUID() == Util.NIL_UUID) {
												IceRambleEntity ramble = new IceRambleEntity(ModEntityTypes.ICE_RAMBLE.get(), player.getLevel());
								
												ramble.setOwnerUUID(player.getUUID());
												ramble.setPos(player.getX(), player.getY(), player.getZ());
												ramble.tame(player);
												ramble.connectToPlayer(player);
								
												player.getLevel().addFreshEntity(ramble);
												
												nameHandler.setPetUUID(ramble.getUUID());
												nameHandler.syncPetUUID(serverPlayer);
											}
										}
									);
								handler.setHasPet(true);
								handler.syncPet(serverPlayer);
							}
						}
					);
			}
		}
		else {
			if (player instanceof ServerPlayer serverPlayer) {
				player.getCapability(PetSpawnHandler.CAPABILITY).ifPresent(
						handler -> {
							if (handler.hasPet()) {
								handler.setHasPet(false);
								handler.syncPet(serverPlayer);
							}
						}
					);
			}
		}
	}

	@Override
	public ArmorSets getSet() {
		return ArmorSets.GLACIAL;
	}

	@Override
	public String[] dropsFrom() {
		return drops;
	}

}
