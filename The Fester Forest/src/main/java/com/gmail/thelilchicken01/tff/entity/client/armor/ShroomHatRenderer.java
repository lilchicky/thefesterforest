package com.gmail.thelilchicken01.tff.entity.client.armor;

import com.gmail.thelilchicken01.tff.item.armor.shroom_hat.ShroomHat;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ShroomHatRenderer extends GeoArmorRenderer<ShroomHat> {

	public ShroomHatRenderer() {
		super(new ShroomHatModel());
		
		this.headBone = "armorHead";
		this.bodyBone = "armorBody";
		this.rightArmBone = "armorRightArm";
		this.leftArmBone = "armorLeftArm";
		this.rightLegBone = "armorLeftLeg";
		this.leftLegBone = "armorRightLeg";
		this.rightBootBone = "armorLeftBoot";
		this.leftBootBone = "armorRightBoot";
	}
	
}
