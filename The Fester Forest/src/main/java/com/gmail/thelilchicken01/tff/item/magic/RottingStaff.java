package com.gmail.thelilchicken01.tff.item.magic;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.entity.projectile.RottingBolt;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item.item_types.MagicItem;
import com.gmail.thelilchicken01.tff.item.item.item_types.MagicWeapon;
import com.gmail.thelilchicken01.tff.item.projectile.RottingBoltShot;

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
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RottingStaff extends ProjectileWeaponItem implements MagicItem, MagicWeapon {
	
	protected int bonusDamage;
	protected double inaccuracy;
	protected boolean ignoreInvulnerability = false;
	protected float projectileSpeed = 0.9f;
	
	protected Supplier<Ingredient> repairMaterial;
	
	private String[] drops = {"Fester Forest Loot Chests"};

	public RottingStaff() {
		super(new Properties().tab(TheFesterForest.TFF_TAB).durability(264));
		this.inaccuracy = inaccuracy;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack gun = player.getItemInHand(hand);

		RottingBoltShot bulletItem = ItemInit.ROTTING_BOLT.get();
		if (!world.isClientSide()) {
			boolean bulletFree = true;
			
			ItemStack shotAmmo = new ItemStack(ItemInit.ROTTING_BOLT.get());
			shoot(world, player, gun, shotAmmo, bulletItem, bulletFree);
			
			gun.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
		}
		
		world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SOUL_SAND_BREAK, SoundSource.PLAYERS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
		player.awardStat(Stats.ITEM_USED.get(this));
		
		player.getCooldowns().addCooldown(this, ItemUtil.getQuickcastCooldown(2, gun));
		return super.use(world, player, hand);
	}
	
	protected void shoot(Level world, Player player, ItemStack gun, ItemStack ammo, RottingBoltShot bulletItem, boolean bulletFree) {
		RottingBolt shot = bulletItem.createProjectile(world, ammo, player);
		shot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, projectileSpeed, (float)inaccuracy); //speed, inaccuracy
		if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.TWO) {
			shot.setDamage(15 * ItemUtil.getArcanePowerDamageMod(gun));
		}
		if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.FOUR) {
			shot.setDamage(20 * ItemUtil.getArcanePowerDamageMod(gun));
		}
		else {
			shot.setDamage(15 * ItemUtil.getArcanePowerDamageMod(gun));
		}
		shot.setIgnoreInvulnerability(ignoreInvulnerability);

		world.addFreshEntity(shot);
	}
	
	public RottingStaff fireSound(Supplier<SoundEvent> fireSound) {
		
		return this;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}

	private static final Predicate<ItemStack> BULLETS = (stack) -> stack.getItem() instanceof RottingBoltShot;

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return BULLETS;
	}
	
	public RottingStaff repair(Supplier<Ingredient> repairMaterial) {
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
			lore.add(new TextComponent("A mushy staff with an odd purple flame on one end.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click to launch a rotting bolt.").withStyle(ChatFormatting.AQUA));
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
			lore.add(new TextComponent("A mushy staff with an odd purple flame on one end.").withStyle(ChatFormatting.GRAY));
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
