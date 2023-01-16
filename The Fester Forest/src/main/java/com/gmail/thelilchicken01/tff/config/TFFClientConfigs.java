package com.gmail.thelilchicken01.tff.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class TFFClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("The Fester Forest client configuration");

        // HERE DEFINE YOUR CONFIGS

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
