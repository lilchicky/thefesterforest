package com.gmail.thelilchicken01.tff.item.misc;

import java.util.List;

import com.gmail.thelilchicken01.tff.entity.ModEntityTypes;
import com.gmail.thelilchicken01.tff.entity.custom.PlayerCrunchBeetleEntity;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
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

public class BugEggs extends Item {
	
	private String[] drops = {"Reetle Queen", "Fester Forest Loot Chests"};
	
	private int cooldownSeconds = 20;
	
	public BugEggs(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		for (int x = 0; x < 5; x++) {
			
			PlayerCrunchBeetleEntity beetle = new PlayerCrunchBeetleEntity(ModEntityTypes.PLAYER_CRUNCH_BEETLE.get(), world);
			
			beetle.setOwnerUUID(player.getUUID());
			beetle.setPos(player.getX(), player.getY(), player.getZ());
			beetle.tame(player);
			
			world.addFreshEntity(beetle);
		}
		
		player.playSound(SoundEvents.TURTLE_EGG_CRACK, 1.2f, 1.7f);
		
		player.getCooldowns().addCooldown(this, cooldownSeconds * 20);

		return super.use(world, player, hand);
		
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Summoning").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A small collection of Reetle eggs.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click to summon 5 friendly Reetles").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("to fight for you for 20 seconds.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("Summoning").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A small collection of Reetle eggs.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
