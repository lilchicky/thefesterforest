package com.gmail.thelilchicken01.tff.item.dull;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.custom.CrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.entity.custom.PlayerCrunchBeetleEntity;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ReetleQueenAntennae extends TFFItem implements ICurioItem {
	
	private String[] drops = {"Reetle Queen", "Fester Forest Loot Chests"};
	
	private int checkCounter;

	public ReetleQueenAntennae() {
		super(new Properties().tab(TheFesterForest.TFF_TAB).stacksTo(1));
		
	}
	
	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		
		ICurioItem.super.curioTick(slotContext, stack);
		
		if (!slotContext.entity().getLevel().isClientSide()) {
		
			checkCounter++;
		
			if (checkCounter % 10 == 0) {
			
				Player player = (Player) slotContext.entity();
				Level world = player.getLevel();
			
			
				List<Entity> nearbyEntities = world.getEntities(player, 
						new AABB(player.getX() - 4, 
								player.getY() - 4, 
								player.getZ() - 4, 
								player.getX() + 4, 
								player.getY() + 4, 
								player.getZ() + 4));
			
				for (int x = 0; x < nearbyEntities.size(); x++) {
					
					if (nearbyEntities.get(x) instanceof CrunchBeetleEntity) {
					
						CrunchBeetleEntity beetle = (CrunchBeetleEntity) nearbyEntities.get(x);
						PlayerCrunchBeetleEntity tamedBeetle = new PlayerCrunchBeetleEntity(ModEntityTypes.PLAYER_CRUNCH_BEETLE.get(), world);
					
						if (beetle.isSummoned()) {
							beetle.setTarget(null);
						}
						else {
							
							tamedBeetle.setPos(beetle.getX(), beetle.getY(), beetle.getZ());
							tamedBeetle.setOwnerUUID(player.getUUID());
										
							world.addFreshEntity(tamedBeetle);
							tamedBeetle.setLifeSpanSeconds(120);
							
							beetle.remove(RemovalReason.KILLED);
							
						}
					
					}
				
				}
					
				checkCounter = 0;
				
			}
	
		}
			
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
		return false;
	}
	

}
