package com.gmail.thelilchicken01.tff.item.dull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiFunction;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.client.CommonEventBusSubscriber;
import com.gmail.thelilchicken01.tff.client.HandlerPriority;
import com.gmail.thelilchicken01.tff.client.PlayerHurtHandler;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.item_util.ICuriosUtil;
import com.gmail.thelilchicken01.tff.util.InventoryHelper;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.items.IItemHandler;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ReetleReagents extends Item implements ICuriosUtil {
	
	private String[] drops = {"Reetle Queen", "Fester Forest Loot Chests"};

	public ReetleReagents() {
		super(new Properties().tab(TheFesterForest.TFF_TAB).stacksTo(1));
		
		CommonEventBusSubscriber.registerPlayerHurtHandlers(new PlayerHurtHandler() {
			
			@Override
			public boolean canApply(Player player, LivingAttackEvent event) {
				
				return event.getSource() == DamageSource.FALL && 
						player.fallDistance > 0.0f && 
						InventoryHelper.playerHasItem(player, ItemInit.REETLE_REAGENTS.get(), ICuriosUtil.Type.BELT);
				
			}
			
			@Override
			public boolean apply(Player player, LivingAttackEvent event) {
				
				return true;
				
			}
			
			@Override
			public HandlerPriority getPriority() {
				
				return HandlerPriority.HIGH;
				
			}
			
		});
		
	}
	
	@Override
	public Type getCuriosType() {
		
		return Type.BELT;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		lore.add(new TextComponent("Dull").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("A small, worn bag full of Reetle limbs.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent("Having them on you seems to prevent fall damage.").withStyle(ChatFormatting.GRAY));
		lore.add(new TextComponent(""));
		lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
		for (int x = 0; x < drops.length; x++) {
			lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
		}
		lore.add(new TextComponent(""));
		
		super.appendHoverText(stack, world, lore, flag);
	}

	@Override
	public void onWornTick(ItemStack stack, LivingEntity player) {
		
	}
	

}
