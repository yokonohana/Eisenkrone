package com.zweihander.eisenkrone.systems.core;

import com.mojang.serialization.Codec;
import com.zweihander.eisenkrone.Eisenkrone;
import net.minecraft.resources.ResourceLocation;

/**
 * CodeX — общие Codec-утилиты для трансформации примитивов (например, String → ResourceLocation с namespace мода).
 *
 */
public class CodeX {
    public static final Codec<ResourceLocation> RESOURCE_LOCATION_CODEC = Codec.STRING.xmap(
            path -> ResourceLocation.fromNamespaceAndPath(Eisenkrone.MODID, path),
            ResourceLocation::getPath
    );
}