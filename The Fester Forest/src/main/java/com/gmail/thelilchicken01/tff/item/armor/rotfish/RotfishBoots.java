package com.gmail.thelilchicken01.tff.item.armor.rotfish;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.client.CommonEventBusSubscriber;
import com.gmail.thelilchicken01.tff.client.HandlerPriority;
import com.gmail.thelilchicken01.tff.client.PlayerHurtHandler;
import com.gmail.thelilchicken01.tff.effect.ModEffects;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.armor.ModArmorMaterial;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item_util.TFFArmorItem;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class RotfishBoots extends TFFArmorItem {
	
	private String[] drops = {"Deep Reaver", "Fester Forest Loot Chests"};
	
	private int dolphinsGraceCounter = 0;
	
	public final Lazy<Multimap<Attribute, AttributeModifier>> LAZY = Lazy.of(() ->  {    
    	ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder(); 
	    
	    builder.put(Attributes.ARMOR, new AttributeModifier(UUID.randomUUID(), 
	    		"armor", ModArmorMaterial.GOOP.getDefenseForSlot(EquipmentSlot.FEET), 
	    		AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), 
	    		"armor_toughness", ModArmorMaterial.GOOP.getToughness(), 
	    		AttributeModifier.Operation.ADDITION));
	    
        if (ForgeMod.SWIM_SPEED.isPresent()) {
       	 	builder.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.randomUUID(),
		    		"swim_speed", 0.2f, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        
    	Multimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();
    	attributeModifiers = builder.build();
    	return attributeModifiers;
    });

	public RotfishBoots() {
		super(ModArmorMaterial.ROTFISH, EquipmentSlot.FEET, 
				new Properties().tab(TheFesterForest.TFF_TAB));
		
	}
	
	@Override
	public void onArmorTick(ItemStack stack, Level level, Player player) {
		
		super.onArmorTick(stack, level, player);
		
		if(player.isSwimming()) {
			dolphinsGraceCounter++;
		}
		else {
			dolphinsGraceCounter = 0;
		}
		
		if ( player.tickCount % 15 == 0 && !player.getLevel().isClientSide()) {
		
			if (ArmorSets.ROTFISH.getArmorSet(player) == SetCount.TWO) {
				ItemUtil.registerPotionEffect(MobEffects.WATER_BREATHING, 0, player, 39);
			}
			if (ArmorSets.ROTFISH.getArmorSet(player) == SetCount.FOUR) {
				ItemUtil.registerPotionEffect(MobEffects.CONDUIT_POWER, 0, player, 39);
				if (dolphinsGraceCounter >= 100) {
					ItemUtil.registerPotionEffect(MobEffects.DOLPHINS_GRACE, 0, player, 39);
					dolphinsGraceCounter = 101;
				}
			}
		
		}
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return slot == EquipmentSlot.FEET ? this.LAZY.get() : super.getDefaultAttributeModifiers(slot);
	}

	@Override
	public ArmorSets getSet() {
		return ArmorSets.ROTFISH;
	}

	@Override
	public String[] dropsFrom() {
		return drops;
	}

}
