package com.gmail.thelilchicken01.tff.item;

import java.util.List;

import com.gmail.thelilchicken01.tff.init.ParticleInit;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LifeScythe extends SwordItem {
	
	private String[] drops = {"Wight", "Fester Forest Loot Chests"};
	
	private int absorptionSeconds = 15;
	private int resistanceSeconds = 15;
	private int hungerSeconds = 40;

	public LifeScythe(Tier tier, int damage, float aspeed, Properties properties) {
		super(tier, damage, aspeed, properties);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		if (world.isClientSide()) {
			for (int x = 0; x < 50; x++) {
				world.addParticle(ParticleInit.blood_particle.get(), player.getX(), player.getY() + 0.5d, player.getZ(), 
						((Math.cos(x) * 0.75d) * ((Math.random() - 0.5) * 0.2)), 
						0.0d + ((Math.random() - 0.5) * 0.5), 
						((Math.sin(x) * 0.75d) * ((Math.random() - 0.5) * 0.2)));
			}
		}
		
		player.playSound(SoundEvents.SLIME_JUMP, 1.0f, 0.01f);
		
		player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, absorptionSeconds * 20, 4));
		player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, resistanceSeconds * 20, 2));
		player.addEffect(new MobEffectInstance(MobEffects.HUNGER, hungerSeconds * 20, 7));
		
		this.damageItem(getDefaultInstance(), 2, null, null);
		
		player.getCooldowns().addCooldown(this, 600);
		
		return super.use(world, player, hand);
	}
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		
		attacker.heal(4.0f);
		
		return super.hurtEnemy(stack, target, attacker);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("A vibrating, living scythe, capable of healing").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("its wielder on every attack.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click to momentarily power yourself up,").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("gaining absorption hearts for " + absorptionSeconds + " seconds and").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("resistance for " + resistanceSeconds + " seconds. This boost comes").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("at a cost, however - you become ravenously hungry for").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(hungerSeconds + " seconds.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("A vibrating, living scythe, capable of healing").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("its wielder on every attack.").withStyle(ChatFormatting.GRAY));
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
