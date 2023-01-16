package com.gmail.thelilchicken01.tff.item.dull;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.client.CommonEventBusSubscriber;
import com.gmail.thelilchicken01.tff.client.HandlerPriority;
import com.gmail.thelilchicken01.tff.client.PlayerHurtHandler;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ReetleReagents extends Item implements ICurioItem {
	
	private String[] drops = {"Reetle Queen"};
	
	private boolean equipped;
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public ReetleReagents() {
		super(new Properties().tab(TheFesterForest.tff_tab).stacksTo(1));
		
		CommonEventBusSubscriber.registerPlayerHurtHandlers(new PlayerHurtHandler() {
			
			@Override
			public boolean canApply(Player player, LivingAttackEvent event) {
				
				return event.getSource() == DamageSource.FALL && player.fallDistance > 0.0f && equipped;
				
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
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    //builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "bonus move speed", 0.2, AttributeModifier.Operation.ADDITION));

	    this.defaultModifiers = builder.build();
		
	}
	
	@Override
	public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
		equipped = true;
		ICurioItem.super.onEquip(slotContext, prevStack, stack);
	}
	
	@Override
	public void onEquipFromUse(SlotContext slotContext, ItemStack stack) {
		equipped = true;
		ICurioItem.super.onEquipFromUse(slotContext, stack);
	}
	
	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
		equipped = false;
		ICurioItem.super.onUnequip(slotContext, newStack, stack);
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
			ItemStack stack) {
		
		return this.defaultModifiers;
		
	}
	
	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		
		ICurioItem.super.curioTick(slotContext, stack);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("Dull").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("A small, worn bag full of Reetle limbs.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("Having them on you seems to prevent fall damage.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
		
		super.appendHoverText(stack, world, lore, flag);
	}
	

}
