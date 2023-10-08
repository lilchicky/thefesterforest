package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.projectile.FrostBolt;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item.item_types.MagicItem;
import com.gmail.thelilchicken01.tff.item.item.item_types.MagicWeapon;
import com.gmail.thelilchicken01.tff.item.projectile.FrostBoltProjectile;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IceBook extends Item implements MagicItem, MagicWeapon {
	
	private String[] drops = {"Frostbitten King"};
	
	private int cooldown = 15;
	private int range = 12;
	private int maxTargets = 4;
	private int shotDamage = 15;

	public IceBook() {
		
		super(new Properties().tab(TheFesterForest.TFF_TAB).durability(64));
		
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
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
		
		List<LivingEntity> nearbyEntities = ItemUtil.getLivingInArea(player, range, 2);
		
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
		
		player.awardStat(Stats.ITEM_USED.get(this));
		player.getCooldowns().addCooldown(this, ItemUtil.getQuickcastCooldown(cooldown * 20, player.getItemInHand(hand)));
		
		return super.use(world, player, hand);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Magic").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A frosty book explaining how to cast an explosion").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("of icy projectiles.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click to shoot nearby entities.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("Max targets scales with Magic buffs.").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("Magic").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A frosty book explaining how to cast an explosion").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("of icy projectiles.").withStyle(ChatFormatting.GRAY));
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
