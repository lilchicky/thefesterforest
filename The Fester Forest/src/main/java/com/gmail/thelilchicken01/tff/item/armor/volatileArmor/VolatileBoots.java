package com.gmail.thelilchicken01.tff.item.armor.volatileArmor;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.ModArmorMaterial;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item_util.TFFArmorItem;
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

public class VolatileBoots extends TFFArmorItem {
	
	private String[] drops = {"Volatile Ghost", "Fester Forest Loot Chests"};
	
	private int damageSeconds = 3;
	private int damageTick;
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public VolatileBoots() {
		super(ModArmorMaterial.VOLATILE, EquipmentSlot.FEET, 
				new Properties().tab(TheFesterForest.TFF_TAB));
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		
	    builder.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), 
	    		"max_health", -2.0, AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), 
	    		"damage", 2.0, AttributeModifier.Operation.ADDITION));
	    
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
		
		if (ArmorSets.VOLATILE.getArmorSet(player) == SetCount.TWO) {
			if ( player.tickCount % 15 == 0 && !player.getLevel().isClientSide()) {
				ItemUtil.registerPotionEffect(MobEffects.FIRE_RESISTANCE, 0, player, 39);
			}
		}
		if (ArmorSets.VOLATILE.getArmorSet(player) == SetCount.FOUR) {
			if ( player.tickCount % 15 == 0 && !player.getLevel().isClientSide()) {
				ItemUtil.registerPotionEffect(MobEffects.FIRE_RESISTANCE, 0, player, 39);
			}
			
			if (damageTick > damageSeconds * 20) {
			
				if(!level.isClientSide()) {
				
					List<Monster> nearbyEntities = ItemUtil.getMonstersInArea(player, 4, 4);
				
					int size = nearbyEntities.size();
				
					for (int x = 0; x < size; x++) {
						
						nearbyEntities.get(x).setSecondsOnFire(damageSeconds);
						
						nearbyEntities.get(x).hurt(ItemUtil.entityDamageSource("volatile_ghost", nearbyEntities.get(x), player), 4);
						
					}	
				
				}
			
				damageTick = 0;
			}
			
		}
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return slot == EquipmentSlot.FEET ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
	}

	@Override
	public ArmorSets getSet() {
		return ArmorSets.VOLATILE;
	}

	@Override
	public String[] dropsFrom() {
		return drops;
	}

}
