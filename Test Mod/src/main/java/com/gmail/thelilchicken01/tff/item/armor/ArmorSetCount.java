package com.gmail.thelilchicken01.tff.item.armor;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

public class ArmorSetCount {
		
	private Player player;
	private ArmorItem[] armorItem;
	private int setCount;
	
	public ArmorSetCount(Player player, ArmorItem[] armorItem) {
		
		this.player = player;
		this.armorItem = armorItem;
		
	}
	
	public int getArmorSet() {
		
		for(int x = 0; x < armorItem.length; x++) {
			
			if (player.getItemBySlot(armorItem[x].getSlot()).toString()
					.equals(new ItemStack(armorItem[x]).toString())) {
				setCount++;
			}
			
		}
		
		return setCount;
		
	}
	
	public void registerPotionEffect(MobEffect effect, int power) {
		
		if (!player.hasEffect(effect)) {
			player.addEffect(new MobEffectInstance(effect, 60, power, false, false));
		}
		else {
			if (player.getEffect(effect).getDuration() < 40) {
				player.addEffect(new MobEffectInstance(effect, 60, power, false, false));
			}
		}
		
	}
	
}
