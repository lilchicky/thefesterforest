package com.gmail.thelilchicken01.tff.client;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public interface PlayerDeathHandler extends PrioritizedHandler {
	boolean canApply(Player player, LivingDeathEvent event);
	boolean apply(Player player, LivingDeathEvent event);
}
