package com.gmail.thelilchicken01.tff.init;

import java.util.function.Function;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.block.CorrodedShroom;
import com.gmail.thelilchicken01.tff.block.ModFlammableRotatedPillarBlock;
import com.gmail.thelilchicken01.tff.block.NonFlammableRotatedPillarBlock;
import com.gmail.thelilchicken01.tff.block.RottingFlower;
import com.gmail.thelilchicken01.tff.block.RottingGraveDirt;
import com.gmail.thelilchicken01.tff.block.RottingSand;
import com.gmail.thelilchicken01.tff.block.RottingwoodSapling;
import com.gmail.thelilchicken01.tff.block.SickeningFlower;
import com.gmail.thelilchicken01.tff.block.SlimyBell;
import com.gmail.thelilchicken01.tff.block.SlimySapling;
import com.gmail.thelilchicken01.tff.block.TffPortalBlock;
import com.gmail.thelilchicken01.tff.block.WeepingGrass;
import com.gmail.thelilchicken01.tff.world.feature.tree.RottingTreeGrower;
import com.gmail.thelilchicken01.tff.world.feature.tree.SlimyTreeGrower;
import com.google.common.base.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheFesterForest.MODID);
	
	public static final DeferredRegister<Item> BLOCK_ITEMS = ItemInit.ITEMS; 
	
	
//	//Rotting Wood
//	public static final RegistryObject<Block> rottingWood = register("rotting_wood_test", 
//			() -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN).strength(1.0f).sound(SoundType.METAL).requiresCorrectToolForDrops()), 
//			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	/*
	 * 
	 * Rotting Wood
	 * 
	 */
	
	//Rotting Log
	public static final RegistryObject<Block> ROTTING_LOG = register("rotting_log", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.NETHERRACK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rotting Log Stripped
		public static final RegistryObject<Block> STRIPPED_ROTTING_LOG = register("stripped_rotting_log", 
				() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.NETHERRACK)), 
				object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
		
	//Rotting Wood
	public static final RegistryObject<Block> ROTTING_WOOD = register("rotting_wood", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).sound(SoundType.NETHERRACK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
		
	//Rotting Wood Stripped
	public static final RegistryObject<Block> STRIPPED_ROTTING_WOOD = register("stripped_rotting_wood", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.NETHERRACK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rotting Wood Leaves
	public static final RegistryObject<Block> ROTTING_LEAVES = register("rotting_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).sound(SoundType.HONEY_BLOCK)) {

				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}
					
				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}
				
				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
					
			}, object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rotting Wood Planks
	public static final RegistryObject<Block> ROTTING_PLANKS = register("rotting_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHERRACK)) {

				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}
				
				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}
				
				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
				
			}, object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rottingwood Stairs
	public static final RegistryObject<Block> ROTTINGWOOD_STAIRS = register("rottingwood_stairs", 
			() -> new StairBlock(() -> BlockInit.ROTTING_PLANKS.get().defaultBlockState(), 
					BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHERRACK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rottingwood Slab
	public static final RegistryObject<Block> ROTTINGWOOD_SLAB = register("rottingwood_slab", 
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHERRACK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rottingwood Fence
	public static final RegistryObject<Block> ROTTINGWOOD_FENCE = register("rottingwood_fence", 
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHERRACK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
		
	//Rottingwood Gate
	public static final RegistryObject<Block> ROTTINGWOOD_FENCE_GATE = register("rottingwood_fence_gate", 
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHERRACK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Slimy Wood
	 * 
	 */
	
	//Slimy Log
	public static final RegistryObject<Block> SLIMY_LOG = register("slimy_log", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Slimy Log Stripped
	public static final RegistryObject<Block> STRIPPED_SLIMY_LOG = register("stripped_slimy_log", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
			
	//Slimy Wood
	public static final RegistryObject<Block> SLIMY_WOOD = register("slimy_wood", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
			
	//Slimy Wood Stripped
	public static final RegistryObject<Block> STRIPPED_SLIMY_WOOD = register("stripped_slimy_wood", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Slimy Leaves
	public static final RegistryObject<Block> SLIMY_LEAVES = register("slimy_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).sound(SoundType.SLIME_BLOCK)) {

				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}
						
				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}
					
				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
					
			}, object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Slimy Wood Planks
	public static final RegistryObject<Block> SLIMY_PLANKS = register("slimy_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.SLIME_BLOCK)) {
				
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}
				
				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}
					
				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
					
			}, object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Slimy Stairs
	public static final RegistryObject<Block> SLIMY_STAIRS = register("slimy_stairs", 
			() -> new StairBlock(() -> BlockInit.SLIMY_PLANKS.get().defaultBlockState(), 
					BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
		
	//Slimy Slab
	public static final RegistryObject<Block> SLIMY_SLAB = register("slimy_slab", 
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Slimy Fence
	public static final RegistryObject<Block> SLIMY_FENCE = register("slimy_fence", 
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
			
	//Slimy Gate
	public static final RegistryObject<Block> SLIMY_FENCE_GATE = register("slimy_fence_gate", 
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Rotting Stone
	 * 
	 */
	
	//Rotting Stone
	public static final RegistryObject<Block> ROTTING_STONE = register("rotting_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));	

	//Crushed Rotting Stone
	public static final RegistryObject<Block> CRUSHED_ROTTING_STONE = register("crushed_rotting_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).sound(SoundType.STONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Cracked Rotting Stone
	public static final RegistryObject<Block> CRACKED_ROTTING_STONE = register("cracked_rotting_stone", 
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Mossy Rotting Stone
	public static final RegistryObject<Block> MOSSY_ROTTING_STONE = register("mossy_rotting_stone", 
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Rotting Stone Bricks
	 * 
	 */
	
	//Rotting Stone Bricks
	public static final RegistryObject<Block> ROTTING_STONE_BRICKS = register("rotting_stone_bricks", 
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Cracked Rotting Stone Bricks
	public static final RegistryObject<Block> CRACKED_ROTTING_STONE_BRICKS = register("cracked_rotting_stone_bricks", 
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rotting Stone Brick Stairs
	public static final RegistryObject<Block> ROTTING_STONE_BRICK_STAIRS = register("rotting_stone_brick_stairs", 
			() -> new StairBlock(() -> BlockInit.ROTTING_STONE_BRICKS.get().defaultBlockState(), 
					BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rotting Stone Brick Slab
	public static final RegistryObject<Block> ROTTING_STONE_BRICK_SLAB = register("rotting_stone_brick_slab", 
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rotting Stone Brick Wall
	public static final RegistryObject<Block> ROTTING_STONE_BRICK_WALL = register("rotting_stone_brick_wall", 
			() -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Rotting Sand (And Blocks)
	 * 
	 */
	
	//Rotting Sand
	public static final RegistryObject<Block> ROTTING_SAND = register("rotting_sand", () -> new RottingSand(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Compressed Rotting Sand
	public static final RegistryObject<Block> COMPRESSED_ROTTING_SAND = register("compressed_rotting_sand", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)
			.requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rotting Glass
	public static final RegistryObject<Block> ROTTING_GLASS = register("rotting_glass", () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.TINTED_GLASS).noOcclusion()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Compressed Rotting Sand Stairs
	public static final RegistryObject<Block> COMPRESSED_ROTTING_SAND_STAIRS = register("compressed_rotting_sand_stairs", 
			() -> new StairBlock(() -> BlockInit.COMPRESSED_ROTTING_SAND.get().defaultBlockState(), 
					BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).requiresCorrectToolForDrops()), 
					object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Compressed Rotting Sand Slab
	public static final RegistryObject<Block> COMPRESSED_ROTTING_SAND_SLAB = register("compressed_rotting_sand_slab", 
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Compressed Rotting Sand Wall
	public static final RegistryObject<Block> COMPRESSED_ROTTING_SAND_WALL = register("compressed_rotting_sand_wall", 
			() -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Fester Bricks and Fester Ore
	 * 
	 */
	
	//Fester Ore
	public static final RegistryObject<Block> FESTER_ORE = register("fester_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Fester Bricks
	public static final RegistryObject<Block> ROTTING_BRICKS = register("rotting_bricks", 
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Fester Brick Stairs
		public static final RegistryObject<Block> FESTER_BRICK_STAIRS = register("fester_brick_stairs", 
				() -> new StairBlock(() -> BlockInit.ROTTING_BRICKS.get().defaultBlockState(), 
						BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops()), 
				object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
		
	//Fester Brick Slab
	public static final RegistryObject<Block> FESTER_BRICK_SLAB = register("fester_brick_slab", 
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Fester Brick Wall
	public static final RegistryObject<Block> FESTER_BRICK_WALL = register("fester_brick_wall", 
			() -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Rotting Dirts
	 * 
	 */
	
	//Rotting Grass Block
	public static final RegistryObject<Block> ROTTING_GRASS = register("rotting_grass", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rotting Dirt
	public static final RegistryObject<Block> ROTTING_DIRT = register("rotting_dirt", 
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Grave Rotting Dirt
	public static final RegistryObject<Block> ROTTING_GRAVE_DIRT = register("rotting_grave_dirt", () -> new RottingGraveDirt(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));	
	
	/*
	 * 
	 * Plants
	 * 
	 */
	
	//Rotting Sapling
	public static final RegistryObject<Block> ROTTINGWOOD_SAPLING = register("rottingwood_sapling", 
			() -> new RottingwoodSapling(new RottingTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Slimy Sapling
	public static final RegistryObject<Block> SLIMY_SAPLING = register("slimy_sapling", 
			() -> new SlimySapling(new SlimyTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rotting Flower
	public static final RegistryObject<FlowerBlock> ROTTING_FLOWER = register("rotting_flower", () -> new RottingFlower(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Sickening Flower
	public static final RegistryObject<FlowerBlock> SICKENING_FLOWER = register("sickening_flower", () -> new SickeningFlower(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Slimy Flower
	public static final RegistryObject<FlowerBlock> SLIMY_FLOWER = register("slimy_bell", () -> new SlimyBell(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Rotting Tall Grass
	public static final RegistryObject<FlowerBlock> ROTTING_TALL_GRASS = register("rotting_tall_grass", () -> new FlowerBlock(MobEffects.MOVEMENT_SLOWDOWN, 20, Properties.copy(Blocks.GRASS)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Weeping Grass
	public static final RegistryObject<TallFlowerBlock> WEEPING_GRASS = register("weeping_grass", () -> new WeepingGrass(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Corrupted Shroom
	public static final RegistryObject<FlowerBlock> CORRODED_SHROOM = register("corroded_shroom", () -> new CorrodedShroom(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Fester Shroom Blocks
	 * 
	 */
	
	//Blue Fester Shroom Block
	public static final RegistryObject<Block> BLUE_FESTER_SHROOM_BLOCK = register("blue_fester_shroom_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Orange Fester Shroom Block
	public static final RegistryObject<Block> ORANGE_FESTER_SHROOM_BLOCK = register("orange_fester_shroom_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Fester Shroom Stem
	public static final RegistryObject<Block> FESTER_SHROOM_STEM = register("fester_shroom_stem", 
			() -> new NonFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Cubed Fungus
	public static final RegistryObject<Block> CUBED_FUNGUS = register("cubed_fungus", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Ocean
	 * 
	 */
	
	//Rotting Bone Block
	public static final RegistryObject<Block> ROTTING_BONE_BLOCK = register("rotting_bone_block", 
			() -> new NonFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK).sound(SoundType.BONE_BLOCK).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Miscellaneous
	 * 
	 */
	
	//Reetlelight
	public static final RegistryObject<Block> REETLELIGHT = register("reetlelight", 
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.SHROOMLIGHT).emissiveRendering((state, getter, pos) -> {
				return true;
			}).lightLevel(state -> 15)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	//Soul Rot
	public static final RegistryObject<Block> SOUL_ROT = register("soul_rot", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SAND)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.TFF_TAB)));
	
	/*
	 * 
	 * Unobtainable
	 * 
	 */
	
	//Rotting Portal
	public static final RegistryObject<Block> TFF_PORTAL = register("tff_portal", () -> new TffPortalBlock(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	
	//Rotting Flower Pot
	public static final RegistryObject<FlowerPotBlock> ROTTING_FLOWER_POT = BLOCKS.register("rotting_flower_pot", 
			() -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ROTTING_FLOWER, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	
	//Sickening Flower Pot
	public static final RegistryObject<FlowerPotBlock> SICKENING_FLOWER_POT = BLOCKS.register("sickening_flower_pot", 
			() -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SICKENING_FLOWER, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	
	//Corrupted Mushroom Flower Pot
	public static final RegistryObject<FlowerPotBlock> CORRODED_SHROOM_FLOWER_POT = BLOCKS.register("corroded_shroom_flower_pot", 
			() -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CORRODED_SHROOM, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	
	private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<? extends T> block) {
		return BLOCKS.register(name, block);
	}
	
	private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
		RegistryObject<T> obj = registerBlock(name, block);
		BLOCK_ITEMS.register(name, item.apply(obj));
		return obj;
	}
	
}
