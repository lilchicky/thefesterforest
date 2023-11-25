package com.gmail.thelilchicken01.tff.item.dull;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.item_util.TFFItem;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ExplosivePowder extends TFFItem {
	
	private String[] drops = {"Volatile Ghost", "Fester Forest Loot Chests"};
	
	public ExplosivePowder(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		player.setDeltaMovement(player.getLookAngle().multiply(2, 2, 2));
		player.hurt(TheFesterForest.VOLATILE_GHOST, 4);
		player.playSound(SoundEvents.GENERIC_EXPLODE, 1.2f, 0.5f);
		
		if(world.isClientSide()) {
			world.addParticle(ParticleTypes.EXPLOSION, player.getX(), player.getY(), player.getZ(), 0.5, 0.5, 0.5);
		}
		
		player.getCooldowns().addCooldown(this, 10);

		return super.use(world, player, hand);
		
	}

	@Override
	public String itemType() {
		return "dull";
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
