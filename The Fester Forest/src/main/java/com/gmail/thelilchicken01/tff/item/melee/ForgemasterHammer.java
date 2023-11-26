package com.gmail.thelilchicken01.tff.item.melee;

import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFSwordItem;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.phys.Vec3;

public class ForgemasterHammer extends TFFSwordItem {
	
	private String[] drops = {"The Forgemaster", "Crafted"};

	public ForgemasterHammer(Tier tier, int damage, float aspeed, Properties properties) {
		super(tier, damage, aspeed, properties);
	}
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		
		Vec3 playerVel = attacker.getPosition(1.0f);
		Vec3 entityVel = target.getPosition(1.0f);
		Vec3 newVel = ((entityVel.subtract(playerVel)).normalize().add(new Vec3(0.0, 0.3, 0.0)).multiply(2.8, 1.4, 2.8));
	
		target.setDeltaMovement(newVel);
		target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 3));
		
		return super.hurtEnemy(stack, target, attacker);
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
