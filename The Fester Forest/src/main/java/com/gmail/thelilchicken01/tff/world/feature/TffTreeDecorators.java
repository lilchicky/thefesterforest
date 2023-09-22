package com.gmail.thelilchicken01.tff.world.feature;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.world.feature.tree_decorator.IcicleDecorator;

import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TffTreeDecorators {
	
	public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, TheFesterForest.MODID);
	
	public static final RegistryObject<TreeDecoratorType<IcicleDecorator>> ICICLE_DECORATOR = TREE_DECORATORS.register("icicle_decorator", () -> new TreeDecoratorType<>(IcicleDecorator.CODEC));
	
}
