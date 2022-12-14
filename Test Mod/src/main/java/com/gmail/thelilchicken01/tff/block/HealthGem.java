package com.gmail.thelilchicken01.tff.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HealthGem extends Block {
	
	public static final DirectionProperty facing = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty lit = BooleanProperty.create("lit");
	
	public static final VoxelShape north = Shapes.box(0.375, 0, 0.375, 0.625, 0.3125, 0.625);
	public static final VoxelShape south = Shapes.box(0.375, 0, 0.375, 0.625, 0.3125, 0.625);
	public static final VoxelShape east = Shapes.box(0.375, 0, 0.375, 0.625, 0.3125, 0.625);
	public static final VoxelShape west = Shapes.box(0.375, 0, 0.375, 0.625, 0.3125, 0.625);

	public HealthGem(Properties properties) {
		super(properties.lightLevel(state -> {
			return state.getValue(lit) ? 4 : 0;
		}));
		
		this.registerDefaultState(this.defaultBlockState().setValue(facing, Direction.NORTH).setValue(lit, true));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(facing, lit);
		super.createBlockStateDefinition(builder);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos,
			CollisionContext context) {
		switch (state.getValue(facing)) {
		case EAST:
			return east;
		case SOUTH:
			return south;
		case WEST:
			return west;
		default:
			return north;
		
		}

	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(facing, context.getHorizontalDirection().getOpposite());
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(facing)));
	}
	
	@Override
	public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
		return state.setValue(facing, direction.rotate(state.getValue(facing)));
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player,
			InteractionHand hand, BlockHitResult result) {
		
		if(!world.isClientSide()) {
			if (state.getValue(lit)) {
				System.out.println(" interacted with block at pos " + pos.toShortString());
				world.setBlock(pos, state.cycle(lit), 4);
				player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 1), player);
				state.setValue(lit, false);
			}
		}		 
		
		return super.use(state, world, pos, player, hand, result);
	}
	
	
	
}
