package com.gmail.thelilchicken01.tff.client;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public interface PlayerHurtHandler extends PrioritizedHandler {
	boolean canApply(Player player, LivingAttackEvent event);
	boolean apply(Player player, LivingAttackEvent event);
}
