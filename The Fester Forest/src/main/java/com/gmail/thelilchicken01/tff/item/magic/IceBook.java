package com.gmail.thelilchicken01.tff.item.magic;

import java.util.ArrayList;
import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.projectile.FrostBolt;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item_util.MagicItem;
import com.gmail.thelilchicken01.tff.item.item_util.MagicWeapon;
import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFItem;
import com.gmail.thelilchicken01.tff.item.projectile.FrostBoltProjectile;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IceBook extends Item implements MagicItem, MagicWeapon {
	
	private String[] drops = {"Frostbitten King", "Crafted"};
	
	private int cooldown = 15;
	private int range = 12;
	private int maxTargets = 4;
	private int shotDamage = 15;
	
	private boolean targetOnlyHostile = true;

	public IceBook() {
		
		super(new Properties().tab(TheFesterForest.TFF_TAB).durability(64));
		
	}
	
	@Override
	public boolean isFoil(ItemStack stack) {
		return targetOnlyHostile ? super.isFoil(stack) : true;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		if (player instanceof ServerPlayer && ((ServerPlayer) player).isCrouching()) {
			
			targetOnlyHostile = !targetOnlyHostile;
			if (targetOnlyHostile) {
				player.displayClientMessage(new TranslatableComponent("type.tff.hostile_target.player").withStyle(ChatFormatting.WHITE), true);
			}
			else {
				player.displayClientMessage(new TranslatableComponent("type.tff.all_target.player").withStyle(ChatFormatting.WHITE), true);
			}
			
		}
		else {
		
			ItemStack stack = player.getItemInHand(hand);
			
			world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOOK_PAGE_TURN, SoundSource.PLAYERS, 0.2F, world.getRandom().nextFloat() * 0.4F + 0.8F);
			
			if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.TWO) {
				range = 16;
				maxTargets = 8;
				shotDamage = 20;
			}
			if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.FOUR) {
				range = 20;
				maxTargets = 16;
				shotDamage = 25;
			}
			
			List<LivingEntity> nearbyEntities = new ArrayList<LivingEntity>();
			
			if (targetOnlyHostile) {
			
				List<Monster> nearbyMonsters = ItemUtil.getMonstersInArea(player, range, 2);
				for (Monster monsters : nearbyMonsters) {
					nearbyEntities.add((LivingEntity) monsters);
				}
			}
			else {
				nearbyEntities = ItemUtil.getLivingInArea(player, range, 2);
			}
			
			if (nearbyEntities.size() <= maxTargets) {
			
				for (LivingEntity currentEntity : nearbyEntities) {
					
					if (!(currentEntity instanceof Player)) {
					
						FrostBoltProjectile bulletItem = ItemInit.FROST_BOLT.get();
						ItemStack shotAmmo = new ItemStack(ItemInit.FROST_BOLT.get());
					
						FrostBolt shot = bulletItem.createProjectile(player.getLevel(), shotAmmo, player);
				
						Vec3 currentPos = shot.getPosition(1.0f);
						Vec3 targetPos = currentEntity.getPosition(1.0f).add(0.0, currentEntity.getBbHeight() * 0.05, 0.0);
						Vec3 targetVector = targetPos.subtract(currentPos).normalize();
				
						shot.shoot(targetVector.x, targetVector.y + 0.1, targetVector.z, 1.2f, 0.0f);
						shot.setDamage(shotDamage * ItemUtil.getArcanePowerDamageMod(stack));
						shot.setIgnoreInvulnerability(false);
						shot.canHitPlayer(false);
					
						currentEntity.setTicksFrozen(240);
				
						player.getLevel().addFreshEntity(shot);
					
					}
				
				}
			
			}
			else {
				
				for (int x = 0; x < maxTargets; x++) {
					
					FrostBoltProjectile bulletItem = ItemInit.FROST_BOLT.get();
					ItemStack shotAmmo = new ItemStack(ItemInit.FROST_BOLT.get());
					
					FrostBolt shot = bulletItem.createProjectile(player.getLevel(), shotAmmo, player);
				
					Vec3 currentPos = shot.getPosition(1.0f);
					Vec3 targetPos = nearbyEntities.get(x).getPosition(1.0f).add(0.0, nearbyEntities.get(x).getBbHeight() * 0.05, 0.0);
					Vec3 targetVector = targetPos.subtract(currentPos).normalize();
				
					shot.shoot(targetVector.x, targetVector.y + 0.1, targetVector.z, 1.2f, 0.0f);
					shot.setDamage(shotDamage * ItemUtil.getArcanePowerDamageMod(stack));
					shot.setIgnoreInvulnerability(false);
					shot.canHitPlayer(false);
				
					player.getLevel().addFreshEntity(shot);
					
				}
				
			}
			
			player.getItemInHand(hand).hurtAndBreak(
					1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
			
			if (player instanceof ServerPlayer) {
			
				((ServerPlayer)player).getCooldowns().addCooldown(this, ItemUtil.getQuickcastCooldown(cooldown * 20, player.getItemInHand(hand)));
			
			}
			
		}
		
		player.awardStat(Stats.ITEM_USED.get(this));
		
		return super.use(world, player, hand);
		
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TranslatableComponent("type.tff.magic").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("description.tff.ice_book").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("ability.tff.ice_book").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(" "));
			if (targetOnlyHostile) {
				lore.add(new TranslatableComponent("type.tff.hostile_target").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
			}
			else {
				lore.add(new TranslatableComponent("type.tff.all_target").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
			}
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("type.tff.drops_from").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(" "));
		}
		else {
			lore.add(new TranslatableComponent("type.tff.magic").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("description.tff.ice_book").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("type.tff.more_info").withStyle(ChatFormatting.YELLOW));
			lore.add(new TextComponent(" "));
			if (targetOnlyHostile) {
				lore.add(new TranslatableComponent("type.tff.hostile_target").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
			}
			else {
				lore.add(new TranslatableComponent("type.tff.all_target").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
			}
			lore.add(new TextComponent(" "));
			lore.add(new TranslatableComponent("type.tff.drops_from").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(" "));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
