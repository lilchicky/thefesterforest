package com.gmail.thelilchicken01.tff.item.dull;

import java.util.List;

import com.gmail.thelilchicken01.tff.entity.custom.BansheeEntity;
import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFItem;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class AngelicWhistle extends TFFItem {
	
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
	public String itemType() {
		return "dull";
	}
	
	@Override
	public boolean isShiftable() {
		return true;
	}

	@Override
	public String[] dropsFrom() {
		return drops;
	}

}
