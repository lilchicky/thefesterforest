package com.gmail.thelilchicken01.tff.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ForgemasterHammer extends SwordItem {
	
	private String[] drops = {"The Forgemaster"};

	public ForgemasterHammer(Tier tier, int damage, float aspeed, Properties properties) {
		super(tier, damage, aspeed, properties);
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
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("The massive hammer of the Forgemaster,").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("forged from an unknown metal and weighing tons.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("The heavy weight of the hammer throws anything it").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("touches.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
