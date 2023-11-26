package com.gmail.thelilchicken01.tff.item.melee;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.init.ParticleInit;
import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item_util.ModTiers;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFSwordItem;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

public class FrostMallet extends TFFSwordItem {
	
	private String[] drops = {"Glacial Titan", "Fester Forest Loot Chests"};
	
	private int cooldown = 25;

	public FrostMallet() {
		super(ModTiers.FROZEN, 5, -3.3f, new Properties().tab(TheFesterForest.TFF_TAB).durability(1384));
	}
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		
		target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
		target.setTicksFrozen(180);
		
		return super.hurtEnemy(stack, target, attacker);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		
		List<LivingEntity> nearbyEntities = ItemUtil.getLivingInArea(player, 3, 2);
		
		for (LivingEntity currentEntity : nearbyEntities) {
			
			currentEntity.hurt(new IndirectEntityDamageSource(TheFesterForest.MODID, currentEntity, player), (int)(player.getMaxHealth() * 0.5));
			currentEntity.setTicksFrozen(180);
			currentEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
			currentEntity.setDeltaMovement(currentEntity.getDeltaMovement().add(0.0, 0.1, 0.0).normalize());
			
		}
		
		for (int x = 0; x < 360; x++) {
			
			if (x % 10 == 0) {
				
				level.addParticle(ParticleInit.ICY_EXPLOSION_PARTICLE.get(), player.getX(), player.getY() + 0.5d, player.getZ(), 
						((Math.cos(x) * 1.75d) * (Math.random() + 0.5)), 0.0d + ((Math.random() - 0.5) * 0.15), ((Math.sin(x) * 1.75d) * (Math.random() + 0.5)));
				
				level.addParticle(ParticleInit.ICY_EXPLOSION_PARTICLE.get(), player.getX(), player.getY() + 0.5d, player.getZ(), 
						((Math.cos(x) * 1.55d) * (Math.random() + 0.5)), 0.0d + ((Math.random() - 0.5) * 0.15), ((Math.sin(x) * 1.55d) * (Math.random() + 0.5)));
				
				level.addParticle(ParticleInit.ICY_EXPLOSION_PARTICLE.get(), player.getX(), player.getY() + 0.5d, player.getZ(), 
						((Math.cos(x) * 1.35d) * (Math.random() + 0.5)), 0.0d + ((Math.random() - 0.5) * 0.15), ((Math.sin(x) * 1.35d) * (Math.random() + 0.5)));
				
			}
			
		}
		
		level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
		player.awardStat(Stats.ITEM_USED.get(this));
		
		player.getCooldowns().addCooldown(this, cooldown * 20);
		
		return super.use(level, player, hand);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}

	@Override
	public String itemType() {
		return "melee";
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
