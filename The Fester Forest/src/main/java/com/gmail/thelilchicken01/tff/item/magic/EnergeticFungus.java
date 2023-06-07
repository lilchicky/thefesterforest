package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
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

public class EnergeticFungus extends Item implements ICurioItem {
	
	private String[] drops = {"Corroded Shroom", "Fester Forest Loot Chests"};
	
	private int regenLevel = 3;
	
	private double oldX;
	private double oldY;
	private double oldZ;
	
	private int secondCounter;

	public EnergeticFungus() {
		super(new Properties().stacksTo(1).tab(TheFesterForest.TFF_TAB));
		
	}
	
	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		
		ICurioItem.super.curioTick(slotContext, stack);
		
		if (slotContext.entity() instanceof Player && secondCounter > 20) {
			
			Player player = (Player) slotContext.entity();
			
			if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.TWO) {
				
				regenLevel = 7;
				
			}
			if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.FOUR) {
				
				regenLevel = 11;
				
			}
			
			if (player.getX() == oldX &&
					player.getY() == oldY &&
					player.getZ() == oldZ) {
				if (!slotContext.entity().hasEffect(MobEffects.REGENERATION)) {
					slotContext.entity().addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, regenLevel, false, true));
				}
				else {
					if (slotContext.entity().getEffect(MobEffects.REGENERATION).getDuration() < 40) {
						slotContext.entity().addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, regenLevel, false, true));
					}
				}
			}
			
			oldX = player.getX();
			oldY = player.getY();
			oldZ = player.getZ();
			
			secondCounter = 0;
			
		}
		
		secondCounter++;
		
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Magic").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A bizarre collection of fragile fungus.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("They grow extremely fast when not disturbed.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("While not moving, gain Regeneration.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("\"..and so it left, ready to grow into something more.\"").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("Magic").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A bizarre collection of fragile fungus.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("They grow extremely fast when not disturbed.").withStyle(ChatFormatting.GRAY));
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
