package com.gmail.thelilchicken01.tff.item.mixed;

import java.util.List;

import com.gmail.thelilchicken01.tff.item.armor.ArmorSets;
import com.gmail.thelilchicken01.tff.item.armor.SetCount;
import com.gmail.thelilchicken01.tff.item.item.ItemUtil;
import com.gmail.thelilchicken01.tff.item.item.item_types.MagicItem;
import com.gmail.thelilchicken01.tff.item.item.item_types.MagicWeapon;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class VolatileSword extends SwordItem implements MagicItem, MagicWeapon {
	
	private String[] drops = {"Volatile Ghost", "Fester Forest Loot Chests"};
	
	//private boolean isSound = false;
	
	public static Level thisWorld;
	
	private static int flameDamage = 5;
	private static int flameSeconds = 3;
	private static int slowDuration = 10;

	public VolatileSword(Tier tier, int damage, float aspeed, Properties properties) {
		
		super(tier, damage, aspeed, properties);
		
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		thisWorld = world;
		//is server side
		if (!world.isClientSide()) {
			//Debug - print in console when right click
			//System.out.println(player.getName().getString() + " has used item with hand " + hand.name());
			//create a list of nearby entities every time you right click
			List<LivingEntity> nearbyEntities = ItemUtil.getLivingInArea(player, 4, 2);
			//run function to interact with surrounding mobs
			getEnts(nearbyEntities, player, world, player.getItemInHand(hand));
			//put sword on cooldown
			player.getCooldowns().addCooldown(this, ItemUtil.getQuickcastCooldown(60, player.getItemInHand(hand))); //default 100
			
		}
		//is client side
		else {
			
			circleFlame(world, player);
			
		}
		return super.use(world, player, hand);
	}
	
//	private void spawnFoundParticles(UseOnContext context, BlockPos positionClicked) {
//		
//	}
	
	//On hit an enemy slow them down and light them on fire
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		
		//target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200), attacker);
		target.setRemainingFireTicks(flameSeconds * 20);
		
		return super.hurtEnemy(stack, target, attacker);
	}
	
	public void circleFlame(Level world, Player player) {
		
		for (int x = 0; x < 360; x++) {
			
			if (x % 10 == 0) {
				
				world.addParticle(ParticleTypes.FLAME, player.getX(), player.getY() + 0.5d, player.getZ(), 
						((Math.cos(x) * 0.75d) * (Math.random() + 0.5)), 0.0d + ((Math.random() - 0.5) * 0.25), ((Math.sin(x) * 0.75d) * (Math.random() + 0.5)));
				
				world.addParticle(ParticleTypes.FLAME, player.getX(), player.getY() + 0.5d, player.getZ(), 
						((Math.cos(x) * 0.55d) * (Math.random() + 0.5)), 0.0d + ((Math.random() - 0.5) * 0.25), ((Math.sin(x) * 0.55d) * (Math.random() + 0.5)));
				
				world.addParticle(ParticleTypes.FLAME, player.getX(), player.getY() + 0.5d, player.getZ(), 
						((Math.cos(x) * 0.35d) * (Math.random() + 0.5)), 0.0d + ((Math.random() - 0.5) * 0.25), ((Math.sin(x) * 0.35d) * (Math.random() + 0.5)));
				//System.out.println("X: " + (Math.cos(x) * 0.65d) + " Y:" + 0.0d + " Z:" + (Math.sin(x) * 0.35d)); //(Math.sin(x) * 0.65d)
				
			}
			
		}
		
	}
	
	
	public void getEnts(List<LivingEntity> entityList, Player player, Level world, ItemStack stack) {
		
		//play this blaze shoot sound every time you cast, regardless if you hit something
		
		player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
		
		//circleFlame(world, player);
		
		//for every entity in a 3x3 square radius, if they're alive, light them on fire and hurt them
		
		for (int x = 0; x < entityList.size(); x++) {
				
			LivingEntity currentEntity = entityList.get(x);
			
			if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.TWO) {
				
				flameDamage = 10;
				
			}
			if (ArmorSets.BANSHEE.getArmorSet(player) == SetCount.FOUR) {
				
				flameDamage = 15;
				
			}
			
			currentEntity.setRemainingFireTicks(flameSeconds * 20);
			currentEntity.hurt(ItemUtil.entityDamageSource("volatile_sword", entityList.get(x), player), (float)(flameDamage * ItemUtil.getArcanePowerDamageMod(stack)));
			currentEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, slowDuration * 20, 2));
			//isSound = true;
		}
		
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level world, List<Component> lore, TooltipFlag flag) {
		
		if(Screen.hasShiftDown()) {
			lore.add(new TextComponent("Magic, Melee").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A flaming blade fitted to a scorching hilt.").withStyle(ChatFormatting.GRAY));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Right click to create a fiery burst around you.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("Anything within 4 blocks of you will be dealt " + flameDamage).withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("damage and set on fire for " + flameSeconds + " seconds,").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("as well as being slowed for " + slowDuration + " seconds.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent("The sword will also ignite anything hit for " + flameSeconds + " seconds.").withStyle(ChatFormatting.AQUA));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("Drops From:").withStyle(ChatFormatting.LIGHT_PURPLE));
			for (int x = 0; x < drops.length; x++) {
				lore.add(new TextComponent(drops[x]).withStyle(ChatFormatting.LIGHT_PURPLE));
			}
			lore.add(new TextComponent(""));
		}
		else {
			lore.add(new TextComponent("Magic, Melee").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.BOLD));
			lore.add(new TextComponent(""));
			lore.add(new TextComponent("A flaming blade fitted to a scorching hilt.").withStyle(ChatFormatting.GRAY));
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
