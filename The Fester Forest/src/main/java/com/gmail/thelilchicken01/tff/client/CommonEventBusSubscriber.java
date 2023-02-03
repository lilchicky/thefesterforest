package com.gmail.thelilchicken01.tff.client;

import java.util.Set;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.google.common.collect.Sets;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheFesterForest.MODID)
public class CommonEventBusSubscriber {

	private static final Set<PlayerHurtHandler> PLAYER_HURT_HANDLERS = Sets.newTreeSet(new HandlerPriorityComparator());
	private static final Set<PlayerDeathHandler> PLAYER_DEATH_HANDLERS = Sets.newTreeSet(new HandlerPriorityComparator());
	
	public static void registerPlayerHurtHandlers(PlayerHurtHandler handler) {
		PLAYER_HURT_HANDLERS.add(handler);
	}
	
	public static void registerPlayerDeathHandler(PlayerDeathHandler handler) {
		PLAYER_DEATH_HANDLERS.add(handler);
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void beforePlayerHurt(LivingAttackEvent event) {
		
		Entity entity = event.getEntity();
		
		if (!(entity instanceof Player)) {
			return;
		}
		
		Player player = (Player) entity;
		
		boolean cancel = false;
		
		for (PlayerHurtHandler handler : PLAYER_HURT_HANDLERS) {
			
			if (handler.canApply(player, event) && handler.apply(player, event)) {
				
				cancel = true;
				break;
				
			}
			
		}
		
		if (cancel) {
			
			event.setCanceled(true);
			event.setResult(null);
			
		}
		
	}
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void beforePlayerDeath(LivingDeathEvent event) {
		
		Entity entity = event.getEntity();
		
		if (!(entity instanceof Player)) {
			return;
		}
		
		Player player = (Player) entity;
		
		boolean cancel = false;
		
		for (PlayerDeathHandler handler : PLAYER_DEATH_HANDLERS) {
			
			if (handler.canApply(player, event) && handler.apply(player, event)) {
				
				cancel = true;
				break;
				
			}
			
		}
		
		if (cancel) {
			
			event.setCanceled(true);
			event.setResult(null);
			
		}
		
	}
	
}
