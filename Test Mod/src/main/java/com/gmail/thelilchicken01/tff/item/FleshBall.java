package com.gmail.thelilchicken01.tff.item;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.init.ParticleInit;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class FleshBall extends Item {
	
	private int duration = 8;
	
	public FleshBall(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		player.playSound(SoundEvents.SLIME_JUMP, 1.0f, 0.5f);
		
		player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration * 20, 19));
		player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration * 20, 4));
		
		player.getCooldowns().addCooldown(this, 400);	
		
		return super.use(world, player, hand);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("A goopy ball of warm, pulsating flesh.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click to apply regeneration 20 and slowness").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("5 for " + duration + " seconds.").withStyle(ChatFormatting.AQUA));
		}
		else {
			lore.add(new TextComponent("A goopy ball of warm, pulsating flesh.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
