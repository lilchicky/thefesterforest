package com.gmail.thelilchicken01.tff.item;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.tiers.ModArmorMaterial;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class VolatileBoots extends ArmorItem {
	
	private String[] drops = {"Volatile Ghost", "Fester Forest Loot Chests"};
	
	private int damageSeconds = 3;
	private int damageTick;
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public VolatileBoots() {
		super(ModArmorMaterial.VOLATILE, EquipmentSlot.FEET, 
				new Properties().tab(TheFesterForest.tff_tab));
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		
	    builder.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), 
	    		"max_health", -2.0, AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.ARMOR, new AttributeModifier(UUID.randomUUID(), 
	    		"armor", ModArmorMaterial.VOLATILE.getDefenseForSlot(EquipmentSlot.FEET), 
	    		AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), 
	    		"armor_toughness", ModArmorMaterial.VOLATILE.getToughness(), 
	    		AttributeModifier.Operation.ADDITION));

	    this.defaultModifiers = builder.build();
		
	}
	
	@Override
	public void onArmorTick(ItemStack stack, Level level, Player player) {
		
		super.onArmorTick(stack, level, player);
		
		damageTick++;
		
		if (getSetCount(player) == 2 || getSetCount(player) == 3) {
			if (!player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
				player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, false));
			}
			else {
				if (player.getEffect(MobEffects.FIRE_RESISTANCE).getDuration() < 40) {
					player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, false));
				}
			}
		}
		if (getSetCount(player) == 4) {
			if (!player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
				player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, false));
			}
			else {
				if (player.getEffect(MobEffects.FIRE_RESISTANCE).getDuration() < 40) {
					player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, false));
				}
			}
			
			if (damageTick > damageSeconds * 20) {
				
				if(!level.isClientSide()) {
					
					List<Entity> nearbyEntities = level.getEntities(player, new AABB(
							player.getX() - 4, 
							player.getY() - 4, 
							player.getZ() - 4, 
							player.getX() + 4, 
							player.getY() + 4, 
							player.getZ() + 4));
					
					for (int x = 0; x < nearbyEntities.size(); x++) {
					
						if (nearbyEntities.get(x) instanceof Monster) {
							
							nearbyEntities.get(x).setSecondsOnFire(damageSeconds);
							
							nearbyEntities.get(x).hurt(TheFesterForest.volatile_ghost, 4);
						
						}
						
					}	
				}
				
				damageTick = 0;
				
			}
			
		}
		
	}
	
	private int getSetCount(Player player) {
		
		int wornPieces = 0;
		
		if (player.getItemBySlot(EquipmentSlot.HEAD).toString()
				.equals(new ItemStack(ItemInit.volatile_helmet.get()).toString())) {
			wornPieces++;
		}
		if (player.getItemBySlot(EquipmentSlot.CHEST).toString()
				.equals(new ItemStack(ItemInit.volatile_chestplate.get()).toString())) {
			wornPieces++;
		}
		if (player.getItemBySlot(EquipmentSlot.LEGS).toString()
				.equals(new ItemStack(ItemInit.volatile_leggings.get()).toString())) {
			wornPieces++;
		}
		if (player.getItemBySlot(EquipmentSlot.FEET).toString()
				.equals(new ItemStack(ItemInit.volatile_boots.get()).toString())) {
			wornPieces++;
		}
		
		return wornPieces;
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return slot == EquipmentSlot.FEET ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("A delicate pair of boots, searing").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("to the touch.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Set Bonus:").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("2+ Pieces: Fire Resistance").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("4 Pieces: Fire Resistance, and do 4 damage to surrounding").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("monsters every 3 seconds.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("A delicate pair of boots, searing").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("to the touch.").withStyle(ChatFormatting.GRAY));
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
