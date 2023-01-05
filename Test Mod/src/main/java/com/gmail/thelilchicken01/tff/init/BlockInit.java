package com.gmail.thelilchicken01.tff.init;

import java.util.function.Function;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.block.ModFlammableRotatedPillarBlock;
import com.gmail.thelilchicken01.tff.block.RottingFlower;
import com.gmail.thelilchicken01.tff.block.RottingwoodSapling;
import com.gmail.thelilchicken01.tff.block.SlimySapling;
import com.gmail.thelilchicken01.tff.block.TffPortalBlock;
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
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	
	public static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, TheFesterForest.modid);
	
	public static final DeferredRegister<Item> items = ItemInit.items; 
	
	
//	//Rotting Wood
//	public static final RegistryObject<Block> rottingWood = register("rotting_wood_test", 
//			() -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN).strength(1.0f).sound(SoundType.METAL).requiresCorrectToolForDrops()), 
//			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	// Volatile Lamp
	public static final RegistryObject<Block> volatileLamp = register("volatile_lamp", 
			() -> new Block(BlockBehaviour.Properties.of(Material.ICE, MaterialColor.COLOR_ORANGE).strength(0.5f).sound(SoundType.AMETHYST).emissiveRendering((state, getter, pos) -> {
				return true;
			}).lightLevel(state -> 15)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Stone
	public static final RegistryObject<Block> rotting_stone = register("rotting_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Fester Ore
	public static final RegistryObject<Block> fester_ore = register("fester_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Log
	public static final RegistryObject<Block> rotting_log = register("rotting_log", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.NETHERRACK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Log Stripped
		public static final RegistryObject<Block> stripped_rotting_log = register("stripped_rotting_log", 
				() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.NETHERRACK)), 
				object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
		
	//Rotting Wood
	public static final RegistryObject<Block> rotting_wood = register("rotting_wood", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).sound(SoundType.NETHERRACK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
		
	//Rotting Wood Stripped
	public static final RegistryObject<Block> stripped_rotting_wood = register("stripped_rotting_wood", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.NETHERRACK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Wood Planks
	public static final RegistryObject<Block> rotting_planks = register("rotting_planks",
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
				
			}, object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Wood Planks
	public static final RegistryObject<Block> slimy_planks = register("slimy_planks",
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
					
			}, object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Wood Leaves
	public static final RegistryObject<Block> rotting_leaves = register("rotting_leaves",
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
					
			}, object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Slimy Leaves
	public static final RegistryObject<Block> slimy_leaves = register("slimy_leaves",
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
					
			}, object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
		
	//Rotting Sapling
	public static final RegistryObject<Block> rottingwood_sapling = register("rottingwood_sapling", 
			() -> new RottingwoodSapling(new RottingTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Slimy Sapling
	public static final RegistryObject<Block> slimy_sapling = register("slimy_sapling", 
			() -> new SlimySapling(new SlimyTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Dirt
	public static final RegistryObject<Block> rotting_dirt = register("rotting_dirt", 
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Bricks
	public static final RegistryObject<Block> rotting_bricks = register("rotting_bricks", 
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
		
	//Rotting Portal
	public static final RegistryObject<Block> tff_portal = register("tff_portal", () -> new TffPortalBlock(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
	
	//Rotting Flower
	public static final RegistryObject<FlowerBlock> rotting_flower = register("rotting_flower", () -> new RottingFlower(), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Tall Grass
	public static final RegistryObject<FlowerBlock> rotting_tall_grass = register("rotting_tall_grass", () -> new FlowerBlock(MobEffects.MOVEMENT_SLOWDOWN, 20, Properties.copy(Blocks.GRASS)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Flower Pot
	public static final RegistryObject<FlowerPotBlock> rotting_flower_pot = blocks.register("rotting_flower_pot", 
			() -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, rotting_flower, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
	
	
	//Rotting Grass Block
	public static final RegistryObject<Block> rotting_grass = register("rotting_grass", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)),
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Log
	public static final RegistryObject<Block> slimy_log = register("slimy_log", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Slimy Log Stripped
	public static final RegistryObject<Block> stripped_slimy_log = register("stripped_slimy_log", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
			
	//Slimy Wood
	public static final RegistryObject<Block> slimy_wood = register("slimy_wood", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
			
	//Slimy Wood Stripped
	public static final RegistryObject<Block> stripped_slimy_wood = register("stripped_slimy_wood", 
			() -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Cracked Rotting Stone
	public static final RegistryObject<Block> cracked_rotting_stone = register("cracked_rotting_stone", 
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Mossy Rotting Stone
	public static final RegistryObject<Block> mossy_rotting_stone = register("mossy_rotting_stone", 
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Stone Bricks
	public static final RegistryObject<Block> rotting_stone_bricks = register("rotting_stone_bricks", 
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Stone Brick Stairs
	public static final RegistryObject<Block> rotting_stone_brick_stairs = register("rotting_stone_brick_stairs", 
			() -> new StairBlock(() -> BlockInit.rotting_stone_bricks.get().defaultBlockState(), 
					BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Stone Brick Slab
	public static final RegistryObject<Block> rotting_stone_brick_slab = register("rotting_stone_brick_slab", 
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rotting Stone Brick Wall
	public static final RegistryObject<Block> rotting_stone_brick_wall = register("rotting_stone_brick_wall", 
			() -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rottingwood Fence
	public static final RegistryObject<Block> rottingwood_fence = register("rottingwood_fence", 
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHERRACK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
		
	//Rottingwood Gate
	public static final RegistryObject<Block> rottingwood_fence_gate = register("rottingwood_fence_gate", 
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHERRACK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rottingwood Stairs
	public static final RegistryObject<Block> rottingwood_stairs = register("rottingwood_stairs", 
			() -> new StairBlock(() -> BlockInit.rotting_planks.get().defaultBlockState(), 
					BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHERRACK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Rottingwood Slab
	public static final RegistryObject<Block> rottingwood_slab = register("rottingwood_slab", 
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHERRACK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Slimy Stairs
	public static final RegistryObject<Block> slimy_stairs = register("slimy_stairs", 
			() -> new StairBlock(() -> BlockInit.slimy_planks.get().defaultBlockState(), 
					BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
		
	//Slimy Slab
	public static final RegistryObject<Block> slimy_slab = register("slimy_slab", 
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	//Slimy Fence
	public static final RegistryObject<Block> slimy_fence = register("slimy_fence", 
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
			
	//Slimy Gate
	public static final RegistryObject<Block> slimy_fence_gate = register("slimy_fence_gate", 
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.SLIME_BLOCK)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(TheFesterForest.tff_tab)));
	
	private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<? extends T> block) {
		return blocks.register(name, block);
	}
	
	private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
		RegistryObject<T> obj = registerBlock(name, block);
		items.register(name, item.apply(obj));
		return obj;
	}
	
}
