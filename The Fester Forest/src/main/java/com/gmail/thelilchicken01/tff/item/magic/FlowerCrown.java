package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item_util.ICuriosUtil.Type;
import com.gmail.thelilchicken01.tff.util.InventoryHelper;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class FlowerCrown extends Item implements ICurioItem {
	
	private String[] drops = {"Crafted"};
	
	private static int xpMult = 2;
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public FlowerCrown(Properties properties) {
		super(properties);
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "increased_health", 2.0, AttributeModifier.Operation.ADDITION));

	    this.defaultModifiers = builder.build();
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
			ItemStack stack) {
		
		return this.defaultModifiers;
		
	}
	
	/*
	 * Code by ochitonida
	 */
	
	public static int getModifiedExperience(int originalXP, @Nullable LivingEntity entity, Player attacker) {
        if (entity != null && InventoryHelper.playerHasItem(attacker, ItemInit.FLOWER_CROWN.get(), Type.HEAD)) {
            if (entity instanceof Player) {
                return originalXP; // players shouldn't drop extra XP
            }
            
            if (ArmorSets.BANSHEE.getArmorSet(attacker) == SetCount.TWO) {
				
				xpMult = 3;
				
			}
			if (ArmorSets.BANSHEE.getArmorSet(attacker) == SetCount.FOUR) {
				
				xpMult = 4;
				
			}

            int experienceBonus = (int) (originalXP * xpMult);
            return originalXP + Math.max(0, experienceBonus);
        }
        return originalXP;
    }
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Magic").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A beautiful collection of vibrant flowers woven").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("into a crown.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Killed entities drop more experience.").withStyle(ChatFormatting.AQUA));
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
			lore.add(new TextComponent("A beautiful collection of vibrant flowers woven").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("into a crown.").withStyle(ChatFormatting.GRAY));
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
