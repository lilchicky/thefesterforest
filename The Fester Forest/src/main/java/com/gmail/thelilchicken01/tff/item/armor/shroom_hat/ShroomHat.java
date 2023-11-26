package com.gmail.thelilchicken01.tff.item.armor.shroom_hat;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.ModArmorMaterial;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFGeoArmorItem;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

public class ShroomHat extends TFFGeoArmorItem implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private String[] drops = {"Corroded Shroom", "Fester Forest Loot Chests"};
	
	public final Lazy<Multimap<Attribute, AttributeModifier>> LAZY = Lazy.of(() ->  {    
    	ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder(); 
	    
	    builder.put(Attributes.ARMOR, new AttributeModifier(UUID.randomUUID(), 
	    		"armor", ModArmorMaterial.SHROOM.getDefenseForSlot(EquipmentSlot.HEAD), 
	    		AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), 
	    		"armor_toughness", ModArmorMaterial.SHROOM.getToughness(), 
	    		AttributeModifier.Operation.ADDITION));
	    
        if (ForgeMod.SWIM_SPEED.isPresent()) {
       	 	builder.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.randomUUID(),
		    		"swim_speed", 0.4f, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        
    	Multimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();
    	attributeModifiers = builder.build();
    	return attributeModifiers;
    });

	public ShroomHat() {
		super(ModArmorMaterial.SHROOM, EquipmentSlot.HEAD, new Properties().tab(TheFesterForest.TFF_TAB));
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return slot == EquipmentSlot.HEAD ? this.LAZY.get() : super.getDefaultAttributeModifiers(slot);
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<ShroomHat>(this, "controller", 20, this::predicate));
		
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
	
	@SuppressWarnings("removal")
	private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.shroom_hat.idle", true));
		return PlayState.CONTINUE;
		
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
