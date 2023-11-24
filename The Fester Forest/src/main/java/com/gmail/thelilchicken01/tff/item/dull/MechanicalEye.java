package com.gmail.thelilchicken01.tff.item.dull;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
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
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class MechanicalEye extends Item implements ICurioItem {
	
	private String[] drops = {"The Forgemaster", "Crafted"};
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public MechanicalEye(Properties properties) {
		super(properties);
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "attack damage", 2.0, AttributeModifier.Operation.ADDITION));

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
		
		if (slotContext.entity() instanceof Player && slotContext.entity().tickCount % 15 == 0 && !slotContext.entity().getLevel().isClientSide()) {
			ItemUtil.registerPotionEffect(MobEffects.NIGHT_VISION, 0, (Player) slotContext.entity(), 319);
		}
				
	}
	
	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
		
		if (slotContext.entity().hasEffect(MobEffects.NIGHT_VISION)) {
			
			if (slotContext.entity().getEffect(MobEffects.NIGHT_VISION).getDuration() < (17 * 20)) {
				
				slotContext.entity().removeEffect(MobEffects.NIGHT_VISION);
				
			}
			
		}
		
		ICurioItem.super.onUnequip(slotContext, newStack, stack);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("Dull").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("The mechanical eye of the Forgemaster.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("Shoving it into your skull grants enhanced vision").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("and accuracy.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
		
		super.appendHoverText(stack, world, lore, flag);
	}
	

}
