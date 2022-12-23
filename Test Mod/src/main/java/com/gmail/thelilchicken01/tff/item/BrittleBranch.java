package com.gmail.thelilchicken01.tff.item;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.gmail.thelilchicken01.tff.entity.projectile.BranchCharge;
import com.gmail.thelilchicken01.tff.init.ItemInit;
import com.gmail.thelilchicken01.tff.item.projectile.BranchShot;
import com.gmail.thelilchicken01.tff.item.projectile.IBullet;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class BrittleBranch extends ProjectileWeaponItem {
	protected int bonusDamage;
	protected double damageMultiplier;
	protected int fireDelay;
	protected double inaccuracy;
	protected double projectileSpeed = 0.6;
	private int enchantability;
	protected boolean ignoreInvulnerability = false;
	protected double chanceFreeShot = 0;
	//protected Supplier<SoundEvent> fireSound = (Supplier<SoundEvent>) SoundEvents.GENERIC_EXPLODE;
	//Hey guess what if I just put the repair material it crashes... so well let's do like vanilla and just use a supplier
	protected Supplier<Ingredient> repairMaterial;
	//damage, damage multiplier, fire delay, inaccuracy, enchantability
	public BrittleBranch(Properties properties, int bonusDamage, double damageMultiplier, 
			int fireDelay, double inaccuracy, int enchantability) {
		super(properties);
		this.bonusDamage = bonusDamage;
		this.damageMultiplier = damageMultiplier;
		this.enchantability = enchantability;
		this.fireDelay = fireDelay;
		this.inaccuracy = inaccuracy;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack gun = player.getItemInHand(hand);

		IBullet bulletItem = (IBullet) (ItemInit.branch_charge.get());
		if (!world.isClientSide) {
			boolean bulletFree = true;
			
			//Workaround for quivers not respecting getAmmoPredicate()
			ItemStack shotAmmo = new ItemStack(ItemInit.branch_charge.get());
			shoot(world, player, gun, shotAmmo, bulletItem, bulletFree);
			
			gun.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
		}
		
		world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.PLAYERS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
		player.awardStat(Stats.ITEM_USED.get(this));
		
		player.getCooldowns().addCooldown(this, 20);
		return super.use(world, player, hand);
	}
	
	/**
	 * Fires one shot after all the checks have passed. You can actually fire whatever you want here.<br>
	 * Ammo is consumed afterwards, we're only shooting the bullet(s) here.
	 * @param world world
	 * @param player Player that shoots
	 * @param gun Gun shooting
	 * @param ammo Ammo being shot
	 * @param bulletItem IBullet used for the shot, may not match the ammo
	 * @param bulletFree true if no ammo was actually consumed (creative or Preserving enchant for example)
	 */
	protected void shoot(Level world, Player player, ItemStack gun, ItemStack ammo, IBullet bulletItem, boolean bulletFree) {
		BranchCharge shot = bulletItem.createProjectile(world, ammo, player);
		shot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, (float)getProjectileSpeed(gun, player), (float)getInaccuracy(gun, player));
		shot.setDamage((shot.getDamage() + getBonusDamage(gun, player)) * getDamageMultiplier(gun, player));
		shot.setIgnoreInvulnerability(ignoreInvulnerability);
		changeBullet(world, player, gun, shot, bulletFree);

		world.addFreshEntity(shot);
	}
	
	/**
	 * Lets the gun do custom stuff to default bullets without redoing all the base stuff from shoot.
	 */
	protected void changeBullet(Level world, Player player, ItemStack gun, BranchCharge bullet, boolean bulletFree) {
		
	}
	
	/**
	 * Rolls chance to know if ammo should be consumed for the shot. Applies both the baseline chance and Preserving enchantment.<br>
	 * If you change this don't forget to tweak getInverseChanceFreeShot accordingly for the tooltip (and call super).
	 */
	public boolean shouldConsumeAmmo(Level world, ItemStack stack, Player player) {
		
		return true;
	}

	/**
	 * Gets the flat bonus damage (applied BEFORE the multiplier). This takes into account Impact enchantment.
	 */
	public double getBonusDamage(ItemStack stack, @Nullable Player player) {
		
		return bonusDamage;
	}
	
	public double getDamageMultiplier(ItemStack stack, @Nullable Player player) {
		return damageMultiplier;
	}
	
	/**
	 * Gets the min time in ticks between 2 shots. This takes into account Sleight of Hand enchantment.
	 */
	public int getFireDelay(ItemStack stack, @Nullable Player player) {
		return 1;
	}
	
	/**
	 * Checks if the gun has baseline perfect accuracy.<br>
	 * Used for tooltip and for Bullseye (which can't be applied since it would do nothing).
	 */
	public boolean hasPerfectAccuracy() {
		return inaccuracy <= 0;
	}
	
	/**
	 * Gets the inaccuracy, taking into account Bullseye enchantment.<br>
	 * Accuracy is actually inarccuracy internally, because it's easier to math.<br>
	 * The formula is just accuracy = 1 / inaccuracy.
	 */
	public double getInaccuracy(ItemStack stack, @Nullable Player player) {
		return 0.1;
	}
	
	public double getProjectileSpeed(ItemStack stack, @Nullable Player player) {
		//I wanted to follow kat's suggestion and make bullseye for snipers increase projectile speed
		//But high projectile speed cause weird "snapping" issues on bullets
		return projectileSpeed;
	}
	
	/**
	 * Chance to actually CONSUME ammo, to properly multiply probabilities together.<br>
	 * Tooltip then does the math to display it nicely.
	 */
	public double getInverseChanceFreeShot(ItemStack stack, @Nullable Player player) {
		return 0.5;
	}
	
	/**
	 * Says if the damage is changed from base value. Used for tooltip.
	 */
	protected boolean isDamageModified(ItemStack stack) {
		return false;
	}
	
	/**
	 * Says if the fire delay is changed from base value. Used for tooltip.
	 */
	protected boolean isFireDelayModified(ItemStack stack) {
		return false;
	}
	
	/**
	 * Says if the (in)accuracy is changed from base value. Used for tooltip.
	 */
	protected boolean isInaccuracyModified(ItemStack stack) {
		return false;
	}
	
	/**
	 * Says if the chance for free shots is changed from base value. Used for tooltip.
	 */
	protected boolean isChanceFreeShotModified(ItemStack stack) {
		return true;
	}

	/**
	 * Sets whether the bullets ignore invulnerability frame (default no), used when making the item for registering.
	 */
	public BrittleBranch ignoreInvulnerability(boolean ignoreInvulnerability) {
		this.ignoreInvulnerability = ignoreInvulnerability;
		return this;
	}

	/**
	 * Sets a chance to NOT consume ammo, used when making the item for registering.
	 */
	public BrittleBranch chanceFreeShot(double chanceFreeShot) {
		this.chanceFreeShot = chanceFreeShot;
		return this;
	}
	
	/**
	 * Sets the firing sound, used when making the item for registering.
	 */
	public BrittleBranch fireSound(Supplier<SoundEvent> fireSound) {
		
		return this;
	}

	/**
	 * Sets a projectile speed, used when making the item for registering.<br>
	 * Base value is 3. High values (like 5-6) cause weird behavior so don't with the base bullets.
	 */
	public BrittleBranch projectileSpeed(double projectileSpeed) {
		this.projectileSpeed = projectileSpeed;
		return this;
	}
	
	/**
	 * Sets the repair material, used when making the item for registering.
	 */
	public BrittleBranch repair(Supplier<Ingredient> repairMaterial) {
		this.repairMaterial = repairMaterial;
		return this;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		//Disallow Bullseye if the gun has perfect accuracy
		
		return enchantment == Enchantments.MENDING || enchantment == Enchantments.UNBREAKING;
	}
	
	/**
	 * Add more tooltips that will be displayed below the base stats.
	 */
	protected void addExtraStatsTooltip(ItemStack stack, @Nullable Level world, List<Component> tooltip) {
		
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	public int getEnchantmentValue() {
		return enchantability;
	}

	//TODO ammo types
	private static final Predicate<ItemStack> BULLETS = (stack) -> stack.getItem() instanceof BranchShot;

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return BULLETS;
	}

	@Override
	public int getDefaultProjectileRange() {
		// No idea what this is yet, so using the Bow value (Crossbow is 8)
		return 15;
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
		return (repairMaterial != null && repairMaterial.get().test(repair)) || super.isValidRepairItem(toRepair, repair);
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return !ItemStack.isSameIgnoreDurability(oldStack, newStack);
	}

}