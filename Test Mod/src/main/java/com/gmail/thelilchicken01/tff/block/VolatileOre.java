package com.gmail.thelilchicken01.tff.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class VolatileOre extends OreBlock {

	public VolatileOre(Properties properties, UniformInt xp) {
		super(properties, xp);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player,
			InteractionHand hand, BlockHitResult result) {
		
		if(!world.isClientSide()) {
			System.out.println(" interacted with block at pos " + pos.toShortString());
		}	 
		
		return super.use(state, world, pos, player, hand, result);
	}
	
}
