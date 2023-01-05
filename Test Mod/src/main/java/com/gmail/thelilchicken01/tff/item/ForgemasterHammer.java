package com.gmail.thelilchicken01.tff.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ForgemasterHammer extends SwordItem {

	public ForgemasterHammer(Tier tier, int damage, float aspeed, Properties properties) {
		super(tier, damage, aspeed, properties);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		return super.use(world, player, hand);
	}
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		
		Vec3 playerVel = attacker.getPosition(1.0f);
		Vec3 entityVel = target.getPosition(1.0f);
		Vec3 newVel = ((entityVel.subtract(playerVel)).normalize().add(new Vec3(0.0, 0.3, 0.0)).multiply(2.8, 1.4, 2.8));
	
		target.setDeltaMovement(newVel);
		
		return super.hurtEnemy(stack, target, attacker);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("The massive hammer of the Forgemaster,").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("forged from an unknown metal and weighing tons.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("The heavy weight of the hammer throws anything it").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("touches.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
