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
import com.gmail.thelilchicken01.tff.item.item.ItemUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
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

public class RotfishHelmet extends ArmorItem {
	
	private String[] drops = {"Deep Reaver", "Fester Forest Loot Chests"};
	
	public final Lazy<Multimap<Attribute, AttributeModifier>> LAZY = Lazy.of(() ->  {    
    	ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder(); 
	    
	    builder.put(Attributes.ARMOR, new AttributeModifier(UUID.randomUUID(), 
	    		"armor", ModArmorMaterial.GOOP.getDefenseForSlot(EquipmentSlot.HEAD), 
	    		AttributeModifier.Operation.ADDITION));
	    
	    builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), 
	    		"armor_toughness", ModArmorMaterial.GOOP.getToughness(), 
	    		AttributeModifier.Operation.ADDITION));
	    
        if (ForgeMod.SWIM_SPEED.isPresent()) {
       	 	builder.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.randomUUID(),
		    		"swim_speed", 0.15f, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        
    	Multimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();
    	attributeModifiers = builder.build();
    	return attributeModifiers;
    });

	public RotfishHelmet() {
		super(ModArmorMaterial.ROTFISH, EquipmentSlot.HEAD, 
				new Properties().tab(TheFesterForest.TFF_TAB));
		
	}
	
	@Override
	public void onArmorTick(ItemStack stack, Level level, Player player) {
		
		super.onArmorTick(stack, level, player);
		
		if ( player.tickCount % 15 == 0 && !player.getLevel().isClientSide()) {
		
			if (ArmorSets.ROTFISH.getArmorSet(player) == SetCount.TWO) {
				ItemUtil.registerPotionEffect(MobEffects.WATER_BREATHING, 0, player, 39);
			}
		
		}
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return slot == EquipmentSlot.HEAD ? this.LAZY.get() : super.getDefaultAttributeModifiers(slot);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Armor").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A rotfish scale helmet fitted with a small viewing port.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Set Bonus:").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("2+ Pieces: Gain Water Breathing.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("4 Pieces: Gain Conduit Power. If you swim (holding sprint in water) for").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("over 5 seconds, also gain Dolphin's Grace.").withStyle(ChatFormatting.AQUA));
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
			lore.add(new TextComponent("A rotfish scale helmet fitted with a small viewing port.").withStyle(ChatFormatting.GRAY));
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
