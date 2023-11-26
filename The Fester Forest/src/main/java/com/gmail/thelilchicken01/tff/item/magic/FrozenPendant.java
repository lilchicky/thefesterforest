package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item_util.TFFItem;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class FrozenPendant extends TFFItem implements ICurioItem {
	
	private String[] drops = {"Banshee", "Fester Forest Loot Chests"};
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	
	private int enemySlowdownSeconds = 1;
	private int enemySlowdownLevel = 1;
	private int speedBuff = 1;

	public FrozenPendant(Properties properties) {
		super(properties);
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    //builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "bonus move speed", 0.2, AttributeModifier.Operation.ADDITION));

	    this.defaultModifiers = builder.build();
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
			ItemStack stack) {
		
		return this.defaultModifiers;
		
	}
	
	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		
		ICurioItem.super.curioTick(slotContext, stack);
		
		if (slotContext.entity() instanceof Player) {
			
			Player player = (Player) slotContext.entity();
			
			if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.TWO) {
				
				enemySlowdownLevel = 2;
				
			}
			if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.FOUR) {
				
				enemySlowdownLevel = 3;
				speedBuff = 2;
				
			}
			
		}
		
		List<Entity> nearbyEntities = slotContext.entity().getLevel().getEntities(slotContext.entity(), new AABB(
			slotContext.entity().getX() - 4, slotContext.entity().getY()
			- 2, slotContext.entity().getZ() - 4, slotContext.entity().getX() + 4, slotContext.entity().getY() + 2, slotContext.entity().getZ() + 4));
		
		for (int x = 0; x < nearbyEntities.size(); x++) {
			
			if (nearbyEntities.get(x) instanceof Monster) {
				
				Monster currentEntity = ((Monster) nearbyEntities.get(x));
				
				currentEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, enemySlowdownSeconds * 20, enemySlowdownLevel));
				
				if ( slotContext.entity().tickCount % 15 == 0 && !slotContext.entity().getLevel().isClientSide()) {
						
					slotContext.entity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 39, speedBuff, false, false));
				
				}
				
			}
		
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
