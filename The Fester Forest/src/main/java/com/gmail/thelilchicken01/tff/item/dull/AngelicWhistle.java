package com.gmail.thelilchicken01.tff.item.dull;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.item.item.ItemUtil;

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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AngelicWhistle extends Item {
	
	private int whistleDamage = 10;
	private int slowFallDuration = 5;
	
	private String[] drops = {"Crafted"};
	
	public AngelicWhistle(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		if(!world.isClientSide()) {
		
			List<LivingEntity> nearbyEntities = ItemUtil.getLivingInArea(player, 4, 4);
				
			player.playSound(SoundEvents.NOTE_BLOCK_CHIME, 1.2f, 1.4f);
			
			player.getCooldowns().addCooldown(this, 100);
			
			for (int x = 0; x < nearbyEntities.size(); x++) {
			
				if (!(nearbyEntities.get(x) instanceof BansheeEntity)) {
					
					Vec3 playerVel = player.getPosition(1.0f);
					Vec3 entityVel = nearbyEntities.get(x).getPosition(1.0f);
					Vec3 newVel = ((entityVel.subtract(playerVel)).normalize().add(new Vec3(0.0, 0.6, 0.0)).multiply(2.0, 2.0, 2.0));
				
					nearbyEntities.get(x).setDeltaMovement(newVel);
					nearbyEntities.get(x).hurt(ItemUtil.entityDamageSource("banshee", nearbyEntities.get(x), player).bypassArmor(), whistleDamage);
					nearbyEntities.get(x).addEffect(
							new MobEffectInstance(MobEffects.LEVITATION, slowFallDuration * 20, 0));
				
				}
				
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
			lore.add(new TextComponent("An impossibly light whistle, crafted from an old Ancient Whistle.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click to play an angelic harmony of sound").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("around you. Any entities that hear it will fly away from you and").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("recieve slow falling for " + slowFallDuration + " seconds.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("Mobs hit around you take " + whistleDamage + " armor piercing damage.").withStyle(ChatFormatting.AQUA));
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
			lore.add(new TextComponent("An impossibly light whistle, crafted from an old Ancient Whistle.").withStyle(ChatFormatting.GRAY));
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
