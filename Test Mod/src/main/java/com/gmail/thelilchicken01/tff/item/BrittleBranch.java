package com.gmail.thelilchicken01.tff.item;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.gmail.thelilchicken01.tff.entity.projectile.BranchCharge;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.projectile.BranchProjectile;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BrittleBranch extends ProjectileWeaponItem {
	
	private String[] drops = {"Rotting Skeleton"};
	
	protected int bonusDamage;
	protected double inaccuracy;
	protected boolean ignoreInvulnerability = true;
	protected float projectileSpeed = 0.5f;
	protected int cooldown = 10;
	
	protected Supplier<Ingredient> repairMaterial;

	public BrittleBranch(Properties properties, double inaccuracy) {
		super(properties);
		this.inaccuracy = inaccuracy;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack gun = player.getItemInHand(hand);

		BranchProjectile bulletItem = ItemInit.branch_charge.get();
		if (!world.isClientSide()) {
			boolean bulletFree = true;
			
			ItemStack shotAmmo = new ItemStack(ItemInit.branch_charge.get());
			shoot(world, player, gun, shotAmmo, bulletItem, bulletFree);
			
			gun.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
		}
		
		world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GRASS_BREAK, SoundSource.PLAYERS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
		player.awardStat(Stats.ITEM_USED.get(this));
		
		player.getCooldowns().addCooldown(this, cooldown * 20);
		return super.use(world, player, hand);
	}
	
	protected void shoot(Level world, Player player, ItemStack gun, ItemStack ammo, BranchProjectile bulletItem, boolean bulletFree) {
		BranchCharge shot = bulletItem.createProjectile(world, ammo, player);
		BranchCharge shot2 = bulletItem.createProjectile(world, ammo, player);
		BranchCharge shot3 = bulletItem.createProjectile(world, ammo, player);
		
		shot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, projectileSpeed, (float)inaccuracy); //speed, inaccuracy
		shot.setDamage(shot.getDamage());
		shot.setIgnoreInvulnerability(ignoreInvulnerability);
		
		shot2.shootFromRotation(player, player.getXRot(), player.getYRot() + 15, 0, projectileSpeed, (float)inaccuracy); //speed, inaccuracy
		shot2.setDamage(shot.getDamage());
		shot2.setIgnoreInvulnerability(ignoreInvulnerability);
		
		shot3.shootFromRotation(player, player.getXRot(), player.getYRot() - 15, 0, projectileSpeed, (float)inaccuracy); //speed, inaccuracy
		shot3.setDamage(shot.getDamage());
		shot3.setIgnoreInvulnerability(ignoreInvulnerability);

		world.addFreshEntity(shot);
		world.addFreshEntity(shot2);
		world.addFreshEntity(shot3);
	}
	
	public BrittleBranch fireSound(Supplier<SoundEvent> fireSound) {
		
		return this;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}

	private static final Predicate<ItemStack> BULLETS = (stack) -> stack.getItem() instanceof BranchProjectile;

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return BULLETS;
	}
	
	public BrittleBranch repair(Supplier<Ingredient> repairMaterial) {
		this.repairMaterial = repairMaterial;
		return this;
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
		return (repairMaterial != null && repairMaterial.get().test(repair)) || super.isValidRepairItem(toRepair, repair);
	}

	@Override
	public int getDefaultProjectileRange() {
		return 15;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("An old, fragile branch.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click launch 3 projectiles in front of you.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("Each projectile does " + BranchCharge.getStaticDamage() + " damage and inflicts").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("poison.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("An old, fragile branch.").withStyle(ChatFormatting.GRAY));
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
