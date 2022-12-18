package com.gmail.thelilchicken01.tff.item;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;

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
import net.minecraft.world.phys.Vec3;

public class AngelicWhistle extends Item {
	
	private int whistleDamage = 10;
	private int slowFallDuration = 10;
	
	public AngelicWhistle(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		List<Entity> nearbyEntities = world.getEntities(player, new AABB(player.getX() - 4, player.getY() - 3, player.getZ() - 4, player.getX() + 4, player.getY() + 2, player.getZ() + 4));
			
		player.playSound(SoundEvents.NOTE_BLOCK_CHIME, 1.2f, 1.4f);
		
		player.getCooldowns().addCooldown(this, 100);
		
		for (int x = 0; x < nearbyEntities.size(); x++) {
		
			if ((!(nearbyEntities.get(x) instanceof BansheeEntity)) && nearbyEntities.get(x) instanceof LivingEntity) {
				
				Vec3 playerVel = player.getPosition(1.0f);
				Vec3 entityVel = nearbyEntities.get(x).getPosition(1.0f);
				Vec3 newVel = ((entityVel.subtract(playerVel)).add(new Vec3(0.0, 0.6, 0.0)).multiply(0.4, 2, 0.4));
			
				nearbyEntities.get(x).setDeltaMovement(newVel);
				nearbyEntities.get(x).hurt(TheFesterForest.banshee, whistleDamage);
				((LivingEntity) nearbyEntities.get(x)).addEffect(
						new MobEffectInstance(MobEffects.SLOW_FALLING, slowFallDuration * 20, 19));
			
			}
			
		}	
		
		return super.use(world, player, hand);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("An impossibly light whistle, crafted from an old Ancient Whistle.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click to play an angelic harmony of sound").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("around you. Any entities that hear it will fly away from you and").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("recieve slow falling for " + slowFallDuration + " seconds.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("Mobs hit around you take " + whistleDamage + " armor piercing damage.").withStyle(ChatFormatting.AQUA));
		}
		else {
			lore.add(new TextComponent("An impossibly light whistle, crafted from an old Ancient Whistle.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
