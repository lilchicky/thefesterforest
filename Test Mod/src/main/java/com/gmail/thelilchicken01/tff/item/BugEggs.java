package com.gmail.thelilchicken01.tff.item;

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

public class BugEggs extends Item {
	
	private int cooldownSeconds = 20;
	
	public BugEggs(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		for (int x = 0; x < 5; x++) {
			
			PlayerCrunchBeetleEntity beetle = new PlayerCrunchBeetleEntity(ModEntityTypes.player_crunch_beetle.get(), world);
			
			beetle.setOwnerUUID(player.getUUID());
			beetle.setPos(player.getX(), player.getY(), player.getZ());
			
			world.addFreshEntity(beetle);
		}
		
		player.playSound(SoundEvents.TURTLE_EGG_CRACK, 1.2f, 1.7f);
		
		player.getCooldowns().addCooldown(this, cooldownSeconds * 20);

		return super.use(world, player, hand);
		
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("A bottle of a mysterious, highly reactive powder.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click to launch yourself somewhere.").withStyle(ChatFormatting.AQUA));
		}
		else {
			lore.add(new TextComponent("A bottle of a mysterious, highly reactive powder.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
