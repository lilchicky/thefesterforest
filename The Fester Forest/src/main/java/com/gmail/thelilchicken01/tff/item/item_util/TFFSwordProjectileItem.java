package com.gmail.thelilchicken01.tff.item.item_util;

import java.util.List;
import java.util.function.Predicate;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public abstract class TFFSwordProjectileItem extends SwordItem {
	public static final Predicate<ItemStack> ARROW_ONLY = (p_43017_) -> {
	      return p_43017_.is(ItemTags.ARROWS);
	   };
	   public static final Predicate<ItemStack> ARROW_OR_FIREWORK = ARROW_ONLY.or((p_43015_) -> {
	      return p_43015_.is(Items.FIREWORK_ROCKET);
	   });

	   public TFFSwordProjectileItem(Tier tier, int damage, float aspeed, Item.Properties p_43009_) {
	      super(tier, damage, aspeed, p_43009_);
	   }

	   public Predicate<ItemStack> getSupportedHeldProjectiles() {
	      return this.getAllSupportedProjectiles();
	   }

	   public abstract Predicate<ItemStack> getAllSupportedProjectiles();

	   public static ItemStack getHeldProjectile(LivingEntity p_43011_, Predicate<ItemStack> p_43012_) {
	      if (p_43012_.test(p_43011_.getItemInHand(InteractionHand.OFF_HAND))) {
	         return p_43011_.getItemInHand(InteractionHand.OFF_HAND);
	      } else {
	         return p_43012_.test(p_43011_.getItemInHand(InteractionHand.MAIN_HAND)) ? p_43011_.getItemInHand(InteractionHand.MAIN_HAND) : ItemStack.EMPTY;
	      }
	   }

	   public int getEnchantmentValue() {
	      return 1;
	   }

	   public abstract int getDefaultProjectileRange();
	   
	   public abstract String itemType();
		public abstract String[] dropsFrom();
		public abstract boolean isShiftable();
		
		@Override
		public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
			
			if(isShiftable() && Screen.hasShiftDown()) {
				lore.add(new TranslatableComponent("type.tff." + itemType()).withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
				lore.add(new TextComponent(" "));
				lore.add(new TranslatableComponent("description.tff." + this.getRegistryName().getPath()).withStyle(ChatFormatting.GRAY));
				lore.add(new TextComponent(" "));
				lore.add(new TranslatableComponent("ability.tff." + this.getRegistryName().getPath()).withStyle(ChatFormatting.AQUA));
				lore.add(new TextComponent(" "));
				if (dropsFrom() != null) {
					lore.add(new TranslatableComponent("type.tff.drops_from").withStyle(ChatFormatting.LIGHT_PURPLE));
					for (int x = 0; x < dropsFrom().length; x++) {
						lore.add(new TextComponent(dropsFrom()[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
					}
					lore.add(new TextComponent(" "));
				}
			}
			else {
				lore.add(new TranslatableComponent("type.tff." + itemType()).withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
				lore.add(new TextComponent(" "));
				lore.add(new TranslatableComponent("description.tff." + this.getRegistryName().getPath()).withStyle(ChatFormatting.GRAY));
				lore.add(new TextComponent(" "));
				if (isShiftable()) {
					lore.add(new TranslatableComponent("type.tff.more_info").withStyle(ChatFormatting.YELLOW));
					lore.add(new TextComponent(" "));
				}
				if (dropsFrom() != null) {
					lore.add(new TranslatableComponent("type.tff.drops_from").withStyle(ChatFormatting.LIGHT_PURPLE));
					for (int x = 0; x < dropsFrom().length; x++) {
						lore.add(new TextComponent(dropsFrom()[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
					}
					lore.add(new TextComponent(" "));
				}
			}
			
			super.appendHoverText(stack, world, lore, flag);
			
		}
}
