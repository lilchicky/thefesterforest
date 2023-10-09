package com.gmail.thelilchicken01.tff.item.ranged;

import java.util.List;
import java.util.UUID;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.arrow.IcyArrow;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FrozenBow extends BowItem {
	
	private String[] drops = {"Frostbitten King"};
	
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public FrozenBow() {
		super(new Properties().durability(-1).tab(TheFesterForest.TFF_TAB));
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		
	    builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), 
	    		"movement_speed", 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
	    
	    this.defaultModifiers = builder.build();
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		
		return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
	}
	
	@Override
	public AbstractArrow customArrow(AbstractArrow arrow) {
		
		return new IcyArrow(arrow.getLevel(), (LivingEntity)arrow.getOwner());
	}
	
	@Override
	public void releaseUsing(ItemStack p_40667_, Level p_40668_, LivingEntity p_40669_, int p_40670_) {
		if (p_40669_ instanceof Player) {
			Player player = (Player)p_40669_;
			boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, p_40667_) > 0;
			ItemStack itemstack = player.getProjectile(p_40667_);

			int i = this.getUseDuration(p_40667_) - p_40670_;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(p_40667_, p_40668_, player, i, !itemstack.isEmpty() || flag);
			if (i < 0) return;

			if (!itemstack.isEmpty() || flag) {
				if (itemstack.isEmpty()) {
					itemstack = new ItemStack(ItemInit.ICY_ARROW.get());
				}

				float f = getPowerForTime(i);
				if (!((double)f < 0.1D)) {
					boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, p_40667_, player));
					if (!p_40668_.isClientSide) {
						ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
						AbstractArrow abstractarrow = arrowitem.createArrow(p_40668_, itemstack, player);
						AbstractArrow abstractarrow2 = arrowitem.createArrow(p_40668_, itemstack, player);
						AbstractArrow abstractarrow3 = arrowitem.createArrow(p_40668_, itemstack, player);
						
						abstractarrow = customArrow(abstractarrow);
						abstractarrow2 = customArrow(abstractarrow);
						abstractarrow3 = customArrow(abstractarrow);
						
						abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);
						abstractarrow2.shootFromRotation(player, player.getXRot(), player.getYRot() - 15, 0.0F, f * 3.0F, 1.0F);
						abstractarrow3.shootFromRotation(player, player.getXRot(), player.getYRot() + 15, 0.0F, f * 3.0F, 1.0F);
						if (f == 1.0F) {
							abstractarrow.setCritArrow(true);
							abstractarrow2.setCritArrow(true);
							abstractarrow3.setCritArrow(true);
						}

						int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, p_40667_);
						if (j > 0) {
							abstractarrow.setBaseDamage((abstractarrow.getBaseDamage() * 1.2) + (double)j * 0.5D + 0.5D);
							abstractarrow2.setBaseDamage((abstractarrow.getBaseDamage() * 1.2) + (double)j * 0.5D + 0.5D);
							abstractarrow3.setBaseDamage((abstractarrow.getBaseDamage() * 1.2) + (double)j * 0.5D + 0.5D);
						}

						int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, p_40667_);
						if (k > 0) {
							abstractarrow.setKnockback(k);
							abstractarrow2.setKnockback(k);
							abstractarrow3.setKnockback(k);
						}

						if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, p_40667_) > 0) {
							abstractarrow.setSecondsOnFire(100);
							abstractarrow2.setSecondsOnFire(100);
							abstractarrow3.setSecondsOnFire(100);
						}

						p_40667_.hurtAndBreak(1, player, (p_40665_) -> {
							p_40665_.broadcastBreakEvent(player.getUsedItemHand());
						});
						
						if (!flag1) {
							abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
							abstractarrow2.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
							abstractarrow3.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
						}
						else {
							abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
							abstractarrow2.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
							abstractarrow3.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
						}
		                  
						p_40668_.addFreshEntity(abstractarrow);
						p_40668_.addFreshEntity(abstractarrow2);
						p_40668_.addFreshEntity(abstractarrow3);
					}

					p_40668_.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (p_40668_.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
					if (!flag1 && !player.getAbilities().instabuild) {
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							player.getInventory().removeItem(itemstack);
						}
					}

					player.awardStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Ranged").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("An ancient bow, corrupted by the same indestructible").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("ice that once held the Frostbitten King.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Converts all shot arrows into Icy Arrows.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("Fires 3 arrows for the cost of one.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("Arrows do an additional 20% damage.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Unbreakable").withStyle(ChatFormatting.BLUE));
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("Ranged").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("An ancient bow, corrupted by the same indestructible").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("ice that once held the Frostbitten King.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Unbreakable").withStyle(ChatFormatting.BLUE));
			lore.add(new TextComponent(""));
		}

		
		super.appendHoverText(stack, world, lore, flag);
	}

}
