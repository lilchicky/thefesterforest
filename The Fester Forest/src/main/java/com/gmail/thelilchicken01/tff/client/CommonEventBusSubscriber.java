package com.gmail.thelilchicken01.tff.client;

import java.util.Set;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.google.common.collect.Sets;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheFesterForest.MODID)
public class CommonEventBusSubscriber {
	
	/*
	 * Thanks to Mo Enchants for the momentum code!
	 */

	private static final Set<PlayerHurtHandler> PLAYER_HURT_HANDLERS = Sets.newTreeSet(new HandlerPriorityComparator());
	private static final Set<PlayerDeathHandler> PLAYER_DEATH_HANDLERS = Sets.newTreeSet(new HandlerPriorityComparator());
	
	private static ItemStack pickaxe;
	
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
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		
		Player player = event.getPlayer();
		
		InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;
		
		ItemStack usedItem = player.getItemInHand(hand);
		
		if (!(usedItem.getItem() == ItemInit.ELECTRIC_PICKAXE.get())) return;
		
		CompoundTag compound = usedItem.getOrCreateTag();
		int momentum = compound.getInt("momentum");
		
		String cachedBlock = compound.getString("block");
        String currentBlock = event.getState().getBlock().getDescriptionId();
        if (!cachedBlock.equals(currentBlock)) {
        	compound.putInt("momentum", 0);
        	compound.putString("block", currentBlock);
        } 
        else {
        	if (momentum < 100) {
        		compound.putInt("momentum", momentum + 1);
        	}
        }

	}
	
	@SubscribeEvent
	public static void breakSpeed(PlayerEvent.BreakSpeed event) {
		
		Player player = event.getPlayer();
		
		InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;
		
		ItemStack usedItem = player.getItemInHand(hand);
		
		if (!(usedItem.getItem() == ItemInit.ELECTRIC_PICKAXE.get())) return;
		
		
		CompoundTag compound = usedItem.getOrCreateTag();
		int momentum = compound.getInt("momentum");
		float oldSpeed = event.getOriginalSpeed();
		float newSpeed = oldSpeed + 0.5f * momentum;
		event.setNewSpeed(newSpeed);
		
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
