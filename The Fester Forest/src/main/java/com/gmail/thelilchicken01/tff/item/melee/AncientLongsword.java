package com.gmail.thelilchicken01.tff.item.melee;

import com.gmail.thelilchicken01.tff.item.item_util.tff_item_overrides.TFFSwordItem;

import net.minecraft.world.item.Tier;

public class AncientLongsword extends TFFSwordItem {
	
	private String[] drops = {"Fester Forest Loot Chests"};

	public AncientLongsword(Tier tier, int damage, float aspeed, Properties properties) {
		super(tier, damage, aspeed, properties);
	}

	@Override
	public String itemType() {
		return "melee";
	}

	@Override
	public String[] dropsFrom() {
		return drops;
	}

	@Override
	public boolean isShiftable() {
		return false;
	}

}
