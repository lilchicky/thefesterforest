package com.gmail.thelilchicken01.tff.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class TFFCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> FESTER_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> FESTER_ORE_VEIN_SIZE;

    static {
        BUILDER.push("The Fester Forest configuration");

        FESTER_ORE_VEINS_PER_CHUNK = BUILDER.comment("Fester ore veins per chunk. Lower numbers mean rarer veins.")
                .define("Veins Per Chunk", 7);
        FESTER_ORE_VEIN_SIZE = BUILDER.comment("Fester ore vein size. Higher numbers mean larger ore veins.")
                .defineInRange("Vein Size", 9, 4, 20);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
