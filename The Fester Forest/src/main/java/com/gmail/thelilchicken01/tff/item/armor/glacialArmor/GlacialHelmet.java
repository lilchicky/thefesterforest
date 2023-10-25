package com.gmail.thelilchicken01.tff.item.armor.glacialArmor;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.capability.PetNameHandler;
import com.gmail.thelilchicken01.tff.capability.PetSpawnHandler;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.custom.IceRambleEntity;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.ModArmorMaterial;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item.ItemUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;

public class GlacialHelmet extends ArmorItem {
	
	private String[] drops = {"Glacial Titan", "Fester Forest Loot Chests"};

	public GlacialHelmet() {
		super(ModArmorMaterial.GLACIAL, EquipmentSlot.HEAD, 
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
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Armor").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("An interesting cap made of delicate ice. Doesn't").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("fit very well.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Set Bonus: Beastmaster (Glacial)").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent("2+ Pieces: Summon an Ice Ramble to fight for you.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("4 Pieces: Your Ice Ramble familiar becomes much stronger.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Ice Rambles can be picked up and moved with a bucket.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("Armor").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("An interesting cap made of delicate ice. Doesn't").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("fit very well.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
