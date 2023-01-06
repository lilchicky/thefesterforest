package com.gmail.thelilchicken01.tff.item;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.gmail.thelilchicken01.tff.entity.projectile.BoneCharge;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.projectile.BoneShot;

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

public class BoneLauncher extends ProjectileWeaponItem {
	
	protected int bonusDamage;
	protected double inaccuracy;
	protected boolean ignoreInvulnerability = true;
	protected float projectileSpeed = 0.8f;
	
	protected Supplier<Ingredient> repairMaterial;

	public BoneLauncher(Properties properties, double inaccuracy) {
		super(properties);
		this.inaccuracy = inaccuracy;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack gun = player.getItemInHand(hand);

		BoneShot bulletItem = ItemInit.bone_charge.get();
		if (!world.isClientSide()) {
			boolean bulletFree = true;
			
			ItemStack shotAmmo = new ItemStack(ItemInit.bone_charge.get());
			shoot(world, player, gun, shotAmmo, bulletItem, bulletFree);
			
			gun.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
		}
		
		world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BONE_BLOCK_BREAK, SoundSource.PLAYERS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
		player.awardStat(Stats.ITEM_USED.get(this));
		
		player.getCooldowns().addCooldown(this, 2);
		return super.use(world, player, hand);
	}
	
	protected void shoot(Level world, Player player, ItemStack gun, ItemStack ammo, BoneShot bulletItem, boolean bulletFree) {
		BoneCharge shot = bulletItem.createProjectile(world, ammo, player);
		shot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, projectileSpeed, (float)inaccuracy); //speed, inaccuracy
		shot.setDamage(shot.getDamage());
		shot.setIgnoreInvulnerability(ignoreInvulnerability);

		world.addFreshEntity(shot);
	}
	
	public BoneLauncher fireSound(Supplier<SoundEvent> fireSound) {
		
		return this;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}

	private static final Predicate<ItemStack> BULLETS = (stack) -> stack.getItem() instanceof BoneShot;

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return BULLETS;
	}
	
	public BoneLauncher repair(Supplier<Ingredient> repairMaterial) {
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
			lore.add(new TextComponent("An old stick with a broken bone fitted to the end.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click to launch a bone from your stick.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("Each bone does 2 damage, ignoring armor and invulnerability").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("ticks.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("An old stick with a broken bone fitted to the end.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Press SHIFT for more info.").withStyle(ChatFormatting.YELLOW));
			lore.add(new TextComponent(""));
		}
		
		super.appendHoverText(stack, world, lore, flag);
	}

}
