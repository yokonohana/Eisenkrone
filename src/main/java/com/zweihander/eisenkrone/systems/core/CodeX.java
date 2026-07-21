package com.zweihander.eisenkrone.systems.core;

import com.mojang.serialization.Codec;
import com.zweihander.eisenkrone.Eisenkrone;
import net.minecraft.resources.ResourceLocation;

public class ModCodecs {
    public static final Codec<ResourceLocation> SKILL_ID_CODEC = Codec.STRING.xmap(
            path -> ResourceLocation.fromNamespaceAndPath(Eisenkrone.MODID, path),
            ResourceLocation::getPath
    );
}