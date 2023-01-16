package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.gmail.thelilchicken01.tff.entity.projectile.MeteorCharge;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.projectile.BranchProjectile;
import com.gmail.thelilchicken01.tff.item.projectile.Meteor;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MeteorWand extends ProjectileWeaponItem {
	
	private String[] drops = {"The Forgemaster"};
	
	protected int shotDamage = 20;
	protected double inaccuracy;
	protected boolean ignoreInvulnerability = true;
	protected float projectileSpeed = 0.5f;
	protected int cooldown = 7;
	
	protected Supplier<Ingredient> repairMaterial;

	public MeteorWand(Properties properties, double inaccuracy) {
		super(properties);
		this.inaccuracy = inaccuracy;
	}
	
	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target,
			InteractionHand hand) {
		
		if (!player.getLevel().isClientSide) {
				
			Meteor bulletItem = ItemInit.meteor_charge.get();
			ItemStack shotAmmo = new ItemStack(ItemInit.meteor_charge.get());
			
			MeteorCharge shot = bulletItem.createProjectile(player.getLevel(), shotAmmo, player);
			
			Vec3 currentPos = player.getEyePosition().add(0.0, 4.0, 0.0);
			Vec3 targetPos = new Vec3(target.getX(), target.getY(), target.getZ());
			Vec3 targetVector = targetPos.subtract(currentPos).normalize();
			
			shot.shoot(targetVector.x, targetVector.y + 0.1, targetVector.z, 0.4f, 0.0f);
			shot.setPos(shot.getX(),
					shot.getY() + 4.0,
					shot.getZ());
			shot.setDamage(shotDamage); // set damage
			shot.setIgnoreInvulnerability(false);
			
			player.getLevel().addFreshEntity(shot);
			
			player.getItemInHand(hand).hurtAndBreak(
					1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
		}
		
		player.awardStat(Stats.ITEM_USED.get(this));
		player.getCooldowns().addCooldown(this, cooldown * 20);
		
		return super.interactLivingEntity(stack, player, target, hand);
	}
	
	@Override
	public InteractionResult useOn(UseOnContext context) {
		
		if (!context.getLevel().isClientSide()) {
			BlockPos clicked = context.getClickedPos();
				
			Meteor bulletItem = ItemInit.meteor_charge.get();
			ItemStack shotAmmo = new ItemStack(ItemInit.meteor_charge.get());
			
			MeteorCharge shot = bulletItem.createProjectile(context.getLevel(), shotAmmo, context.getPlayer());
			
			Vec3 currentPos = context.getPlayer().getEyePosition().add(0.0, 4.0, 0.0);
			Vec3 targetPos = new Vec3(clicked.getX() + 0.5, clicked.getY(), clicked.getZ() + 0.5);
			Vec3 targetVector = targetPos.subtract(currentPos).normalize();
			
			shot.shoot(targetVector.x, targetVector.y + 0.1, targetVector.z, 0.4f, 0.0f);
			shot.setPos(shot.getX(),
					shot.getY() + 4.0,
					shot.getZ());
			shot.setDamage(shotDamage); // set damage
			shot.setIgnoreInvulnerability(false);
			
			context.getLevel().addFreshEntity(shot);
			
			context.getPlayer().getItemInHand(context.getPlayer().getUsedItemHand()).hurtAndBreak(
					1, context.getPlayer(), (p) -> p.broadcastBreakEvent(context.getPlayer().getUsedItemHand()));
		}
		
		context.getPlayer().awardStat(Stats.ITEM_USED.get(this));
		context.getPlayer().getCooldowns().addCooldown(this, cooldown * 20);
		
		return super.useOn(context);
	}
	
	protected void shoot(Level world, Player player, ItemStack gun, ItemStack ammo, Meteor bulletItem, boolean bulletFree) {
		MeteorCharge shot = bulletItem.createProjectile(world, ammo, player);
		
		shot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, projectileSpeed, (float)inaccuracy); //speed, inaccuracy
		shot.setDamage(shot.getDamage());
		shot.setIgnoreInvulnerability(ignoreInvulnerability);

		world.addFreshEntity(shot);
	}
	
	public MeteorWand fireSound(Supplier<SoundEvent> fireSound) {
		
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
	
	public MeteorWand repair(Supplier<Ingredient> repairMaterial) {
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
			lore.add(new TextComponent("Magic").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A heavy metal rod with a scorching meteor attached to one end.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("Used by the Forgemaster to summon meteors.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click a block or mob to summon a meteor above your head.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("The meteor does " + shotDamage + " damage and will land where").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("you right clicked. Doesn't work well indoors!").withStyle(ChatFormatting.AQUA));
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
			lore.add(new TextComponent("A heavy metal rod with a scorching meteor attached to one end.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent("Used by the Forgemaster to summon meteors.").withStyle(ChatFormatting.GRAY));
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
