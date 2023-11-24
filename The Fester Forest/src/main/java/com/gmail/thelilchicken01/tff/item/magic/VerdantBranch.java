package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.gmail.thelilchicken01.tff.entity.projectile.BranchCharge;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item_util.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item_util.MagicItem;
import com.gmail.thelilchicken01.tff.item.item_util.MagicWeapon;
import com.gmail.thelilchicken01.tff.item.projectile.BranchProjectile;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;

public class VerdantBranch extends ProjectileWeaponItem implements MagicItem, MagicWeapon {
	
	private String[] drops = {"Crafted"};
	
	protected int bonusDamage;
	protected double inaccuracy;
	protected boolean ignoreInvulnerability = true;
	protected float projectileSpeed = 0.5f;
	protected int cooldown = 8;
	
	protected Supplier<Ingredient> repairMaterial;
	
	public final Lazy<Multimap<Attribute, AttributeModifier>> LAZY = Lazy.of(() ->  {    
    	ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder(); 
         
         if (ForgeMod.REACH_DISTANCE.isPresent()) {
             builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.randomUUID(), 
     	    		"reach_range", 3.0, AttributeModifier.Operation.ADDITION));
         }
    	Multimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();
    	attributeModifiers = builder.build();
    	return attributeModifiers;
    });

	public VerdantBranch(Properties properties, double inaccuracy) {
		super(properties);
		this.inaccuracy = inaccuracy;
		
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return slot == EquipmentSlot.MAINHAND ? LAZY.get() : super.getAttributeModifiers(slot, stack);
	}
	
	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target,
			InteractionHand hand) {

		if (!player.getCooldowns().isOnCooldown(this)) {
		
			BranchProjectile bulletItem = ItemInit.BRANCH_CHARGE.get();
			if (!player.level.isClientSide()) {
				boolean bulletFree = true;
			
				ItemStack shotAmmo = new ItemStack(ItemInit.BRANCH_CHARGE.get());
				shoot(player.level, player, stack, shotAmmo, bulletItem, bulletFree, target);
			
				stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
			}
		
			player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GRASS_BREAK, SoundSource.PLAYERS, 1.0F, player.level.getRandom().nextFloat() * 0.4F + 0.8F);
			player.awardStat(Stats.ITEM_USED.get(this));
		
			player.getCooldowns().addCooldown(this, (int)(ItemUtil.getQuickcastCooldown(cooldown * 20, stack) * 0.9));
			
		}
		
		return super.interactLivingEntity(stack, player, target, hand);
	}
	
	protected Vec3 shootTowardsTarget(BranchCharge shot, LivingEntity target) {
		
		Vec3 currentPos = shot.getPosition(1.0f);
		Vec3 targetPos = target.getPosition(1.0f).add(0.0, target.getEyeHeight() * 0.5, 0.0);
		return targetPos.subtract(currentPos).normalize();
		
	}
	
	protected double[] getCoordMod(double rot) {
		
		double[] result = new double[2];
		
		double x = Math.cos(Math.toRadians(rot));
		double y = Math.sin(Math.toRadians(rot));
		
		result[0] = x;
		result[1] = y;
		
		return result;
		
	}
	
	protected void setBulletInfo(BranchCharge shot, ItemStack gun, Player player, LivingEntity target, double xMod, double yMod, double zMod) {
		
		shot.setPos(shot.getX() + xMod,
				shot.getY() + yMod,
				shot.getZ() + zMod);
		
		Vec3 targetVector = shootTowardsTarget(shot, target);
		
		shot.shoot(targetVector.x, targetVector.y + 0.1, targetVector.z, projectileSpeed, (float)inaccuracy);
		shot.setDamage(shot.getDamage() * ItemUtil.getArcanePowerDamageMod(gun));
		shot.setIgnoreInvulnerability(ignoreInvulnerability);
		shot.canHitPlayer(false);
		
	}
	
	protected void shoot(Level world, Player player, ItemStack gun, ItemStack ammo, BranchProjectile bulletItem, boolean bulletFree, LivingEntity target) {
		BranchCharge shot = bulletItem.createProjectile(world, ammo, player, target);
		BranchCharge shot2 = bulletItem.createProjectile(world, ammo, player, target); 
		BranchCharge shot3 = bulletItem.createProjectile(world, ammo, player, target);
		
		setBulletInfo(shot, gun, player, target, Math.cos(Math.toRadians(player.getYRot() + 90)), 1.5, Math.sin(Math.toRadians(player.getYRot() + 90)));
		
		setBulletInfo(shot2, gun, player, target, (getCoordMod(player.getYRot())[0] * 1), 1.2, (getCoordMod(player.getYRot())[1] * 0.75));
		
		setBulletInfo(shot3, gun, player, target, (-getCoordMod(player.getYRot())[0] * 1), 1.2, (-getCoordMod(player.getYRot())[1] * 0.75));
		
		if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.TWO) {
			BranchCharge shot4 = bulletItem.createProjectile(world, ammo, player, target);
			BranchCharge shot5 = bulletItem.createProjectile(world, ammo, player, target);
			
			setBulletInfo(shot4, gun, player, target, (getCoordMod(player.getYRot())[0] * 1), 1.0, (getCoordMod(player.getYRot())[1] * 1.25));
			
			setBulletInfo(shot5, gun, player, target, (-getCoordMod(player.getYRot())[0] * 1), 1.0, (-getCoordMod(player.getYRot())[1] * 1.25));
			
			world.addFreshEntity(shot4);
			world.addFreshEntity(shot5);
		}
		if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.FOUR) {
			BranchCharge shot4 = bulletItem.createProjectile(world, ammo, player, target);
			BranchCharge shot5 = bulletItem.createProjectile(world, ammo, player, target);
			BranchCharge shot6 = bulletItem.createProjectile(world, ammo, player, target);
			BranchCharge shot7 = bulletItem.createProjectile(world, ammo, player, target);
			
			setBulletInfo(shot4, gun, player, target, (getCoordMod(player.getYRot())[0] * 1), 1.0, (getCoordMod(player.getYRot())[1] * 1.25));
			
			setBulletInfo(shot5, gun, player, target, (-getCoordMod(player.getYRot())[0] * 1), 1.0, (-getCoordMod(player.getYRot())[1] * 1.25));
			
			setBulletInfo(shot6, gun, player, target, (getCoordMod(player.getYRot())[0] * 1), 1.6, (getCoordMod(player.getYRot())[1] * 0.6));
			
			setBulletInfo(shot7, gun, player, target, (-getCoordMod(player.getYRot())[0] * 1), 1.6, (-getCoordMod(player.getYRot())[1] * 0.6));
			
			world.addFreshEntity(shot4);
			world.addFreshEntity(shot5);
			world.addFreshEntity(shot6);
			world.addFreshEntity(shot7);
		}

		world.addFreshEntity(shot);
		world.addFreshEntity(shot2);
		world.addFreshEntity(shot3);
		
	}
	
	public VerdantBranch fireSound(Supplier<SoundEvent> fireSound) {
		
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
	
	public VerdantBranch repair(Supplier<Ingredient> repairMaterial) {
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
			lore.add(new TextComponent("A once dead branch, now thriving with life.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click an enemy to create 3 shots that").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("lock onto and fire at your target.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("Each projectile inflicts poison.").withStyle(ChatFormatting.AQUA));
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
			lore.add(new TextComponent("A once dead branch, now thriving with life.").withStyle(ChatFormatting.GRAY));
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
