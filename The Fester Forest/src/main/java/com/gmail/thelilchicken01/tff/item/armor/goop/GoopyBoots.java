package com.gmail.thelilchicken01.tff.item.armor.goop;

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
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFArmorItem;
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

public class GoopyBoots extends TFFArmorItem {
	
	private String[] drops = {"Rotting Goop", "Fester Forest Loot Chests"};
	
	public final Lazy<Multimap<Attribute, AttributeModifier>> LAZY = Lazy.of(() ->  {    
    	ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder(); 
	    
	    builder.put(Attributes.ARMOR, new AttributeModifier(UUID.randomUUID(), 
	    		"armor", ModArmorMaterial.GOOP.getDefenseForSlot(EquipmentSlot.FEET), 
	    		AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), 
	    		"armor_toughness", ModArmorMaterial.GOOP.getToughness(), 
	    		AttributeModifier.Operation.ADDITION));
        
    	Multimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();
    	attributeModifiers = builder.build();
    	return attributeModifiers;
    });

	public GoopyBoots() {
		super(ModArmorMaterial.GOOP, EquipmentSlot.FEET, 
				new Properties().tab(TheFesterForest.TFF_TAB));
		
		CommonEventBusSubscriber.registerPlayerHurtHandlers(new PlayerHurtHandler() {
			
			@Override
			public boolean canApply(Player player, LivingAttackEvent event) {
				
				if (event.getSource().getEntity() instanceof LivingEntity && player.getItemBySlot(EquipmentSlot.FEET).getItem() == ItemInit.GOOPY_BOOTS.get()) {
					
					LivingEntity entity = (LivingEntity) event.getSource().getEntity();
					
					if (ArmorSets.GOOP.getArmorSet(player) == SetCount.TWO) {
						entity.addEffect(new MobEffectInstance(ModEffects.GOOP_ACID.get(), 100, 2));
					}
					if (ArmorSets.GOOP.getArmorSet(player) == SetCount.FOUR) {
						entity.addEffect(new MobEffectInstance(ModEffects.GOOP_ACID.get(), 100, 4));
					}
					
				}
				
				return false;
				
			}
			
			@Override
			public boolean apply(Player player, LivingAttackEvent event) {
				
				return true;
				
			}
			
			@Override
			public HandlerPriority getPriority() {
				
				return HandlerPriority.HIGH;
				
			}
			
		});
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return slot == EquipmentSlot.FEET ? this.LAZY.get() : super.getDefaultAttributeModifiers(slot);
	}

	@Override
	public ArmorSets getSet() {
		return ArmorSets.GOOP;
	}

	@Override
	public String[] dropsFrom() {
		return drops;
	}

}
