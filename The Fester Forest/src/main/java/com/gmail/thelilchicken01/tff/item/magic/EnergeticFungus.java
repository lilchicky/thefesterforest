package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFItem;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class EnergeticFungus extends TFFItem implements ICurioItem {
	
	private String[] drops = {"Corroded Shroom", "Fester Forest Loot Chests"};
	
	private int regenLevel = 3;
	
	private double[] oldLoc = new double[3];

	public EnergeticFungus() {
		super(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB));
		
	}
	
	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		
		ICurioItem.super.curioTick(slotContext, stack);
		
		if (slotContext.entity() instanceof Player player) {
			
			if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.TWO) {
				
				regenLevel = 7;
				
			}
			if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.FOUR) {
				
				regenLevel = 11;
				
			}
			
			if ( player.tickCount % 15 == 0 && !player.getLevel().isClientSide()) {
			
				if (oldLoc[0] == player.getX() && oldLoc[1] == player.getY() && oldLoc[2] == player.getZ()) {
					if (!slotContext.entity().hasEffect(MobEffects.REGENERATION)) {
						slotContext.entity().addEffect(new MobEffectInstance(MobEffects.REGENERATION, 19, regenLevel, false, true));
					}
					else {
						if (slotContext.entity().getEffect(MobEffects.REGENERATION).getDuration() < 19) {
							slotContext.entity().addEffect(new MobEffectInstance(MobEffects.REGENERATION, 19, regenLevel, false, true));
						}
					}
				}
			
			}
			
			oldLoc[0] = player.getX();
			oldLoc[1] = player.getY();
			oldLoc[2] = player.getZ();
			
		}
		
	}

	@Override
	public String itemType() {
		return "magic";
	}

	@Override
	public String[] dropsFrom() {
		return drops;
	}

	@Override
	public boolean isShiftable() {
		return true;
	}
	

}
