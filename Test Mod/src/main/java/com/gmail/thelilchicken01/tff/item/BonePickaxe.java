package com.gmail.thelilchicken01.tff.item;

import com.gmail.thelilchicken01.tff.TheFesterForest;
import com.gmail.thelilchicken01.tff.item.tiers.ModTiers;

import net.minecraft.world.item.PickaxeItem;

public class BonePickaxe extends PickaxeItem {

	public BonePickaxe() {
		super(ModTiers.BONE, -2, -3.0f, new Properties().tab(TheFesterForest.tff_tab).durability(2384));
	}

}
