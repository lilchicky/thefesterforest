package com.gmail.thelilchicken01.tff.item.dull;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.ParticleInit;
import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PocketSand extends Item {
	
	private String[] drops = {"Reetle"};
	
	private int sandDamage = 2;
	private int slownessDuration = 4;
	private int blindnessDuration = 4;
	
	public PocketSand(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		List<Entity> coneEntities = world.getEntities(player, new AABB(
				player.getX() + (player.getLookAngle().x * 8),
				player.getY() + 2,
				player.getZ() + (player.getLookAngle().z * 8),
				player.getX(),
				player.getY() - 2,
				player.getZ()));
		
		player.playSound(SoundEvents.SAND_BREAK, 1.0f, 0.5f);
		
		if(world.isClientSide()) {
			for (int x = 0; x < 150; x++) {
				world.addParticle(ParticleInit.POCKET_SAND_PARTICLE.get(), player.getX(), player.getY() + 0.5d, player.getZ(), 
						(player.getLookAngle().x + ((Math.random() - 0.5) * 0.85)), 
						(player.getLookAngle().y + ((Math.random() - 0.5) * 0.85)), 
						(player.getLookAngle().z  + ((Math.random() - 0.5) * 0.85)));
				
				
			}
		}
		
		player.getCooldowns().addCooldown(this, 60);
		
		for (int x = 0; x < coneEntities.size(); x++) {
		
			if (coneEntities.get(x) instanceof LivingEntity && player.hasLineOfSight(coneEntities.get(x))) {
				
				coneEntities.get(x).hurt(ItemUtil.entityDamageSource("sand_damage", coneEntities.get(x), player), sandDamage);
				
				((LivingEntity) coneEntities.get(x)).addEffect(
						new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, slownessDuration * 20, 0));
				((LivingEntity) coneEntities.get(x)).addEffect(
						new MobEffectInstance(MobEffects.BLINDNESS, blindnessDuration * 20, 0));
			
			}
			
		}	
		
		return super.use(world, player, hand);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Dull").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A mysterious pile of sand, seemingly never going away.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click to quickly throw sand from your pocket,").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("damaging and slowing anything in front of you.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("For Solomon").withStyle(ChatFormatting.RED));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("Dull").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A mysterious pile of sand, seemingly never going away.").withStyle(ChatFormatting.GRAY));
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
