package com.gmail.thelilchicken01.tff.block;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.AABB;

public class FesterBrickPressurePlate extends PressurePlateBlock {

	public FesterBrickPressurePlate() {
		super(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties
        		.copy(Blocks.OBSIDIAN).sound(SoundType.STONE).requiresCorrectToolForDrops());
	}
	
	@Override
	protected int getSignalStrength(Level level, BlockPos blockpos) {
		AABB aabb = TOUCH_AABB.move(blockpos);
		List<? extends Entity> list;
		
		list = level.getEntitiesOfClass(Player.class, aabb);
		
		if (!list.isEmpty()) {
			for(Entity entity : list) {
				if (!entity.isIgnoringBlockTriggers()) {
					return 15;
				}
			}
		}

		return 0;
	}

}
