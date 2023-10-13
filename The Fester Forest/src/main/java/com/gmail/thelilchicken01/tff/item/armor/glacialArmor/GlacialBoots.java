package com.gmail.thelilchicken01.tff.item.armor.glacialArmor;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.capability.PetSpawnHandler;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.custom.IceRambleEntity;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.ModArmorMaterial;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;

import net.minecraft.ChatFormatting;
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

public class GlacialBoots extends ArmorItem {
	
	private String[] drops = {"Glacial Titan"};

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
								
								IceRambleEntity ramble = new IceRambleEntity(ModEntityTypes.ICE_RAMBLE.get(), player.getLevel());
								
								ramble.setOwnerUUID(player.getUUID());
								ramble.setPos(player.getX(), player.getY(), player.getZ());
								ramble.tame(player);
								
								player.getLevel().addFreshEntity(ramble);
							}
							handler.setHasPet(true);
							handler.syncPet(serverPlayer);
						}
					);
			}
		}
		else {
			if (player instanceof ServerPlayer serverPlayer) {
				player.getCapability(PetSpawnHandler.CAPABILITY).ifPresent(
						handler -> {
							
							handler.setHasPet(false);
							handler.syncPet(serverPlayer);
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
			lore.add(new TextComponent("Cold boots made of mysterious, unbreaking ice.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Set Bonus: Cryostasis, Magicka").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent("2+ Pieces: Freeze all monsters within 5 blocks.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("Minor buffs to all Magic items.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("4 Pieces: Freeze all monsters within 10 blocks.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("Major buffs to all Magic items.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Pairs with other Magic buff armors.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Unbreakable").withStyle(ChatFormatting.BLUE));
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("Armor").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Cold boots made of mysterious, unbreaking ice.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Unbreakable").withStyle(ChatFormatting.BLUE));
			lore.add(new TextComponent(""));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
