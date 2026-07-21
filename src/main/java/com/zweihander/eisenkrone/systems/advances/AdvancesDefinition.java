package com.zweihander.eisenkrone.systems.advances;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.zweihander.eisenkrone.systems.core.AttributeModifier;
import com.zweihander.eisenkrone.systems.core.CodeX;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record AdvancesDefinition (
        List<ResourceLocation> skills,
        List<AttributeModifier> bonus,
        List<String> talents
) {
    public final static Codec<AdvancesDefinition> CODEC = RecordCodecBuilder.create(
            advancesDefinitionInstance -> advancesDefinitionInstance.group(
                    CodeX.RESOURCE_LOCATION_CODEC.listOf().optionalFieldOf("skills", List.of()).forGetter(AdvancesDefinition::skills),
                    AttributeModifier.CODEC.listOf().optionalFieldOf("bonus", List.of()).forGetter(AdvancesDefinition::bonus),
                    Codec.STRING.listOf().optionalFieldOf("talents", List.of()).forGetter(AdvancesDefinition::talents)
            ).apply(advancesDefinitionInstance, AdvancesDefinition::new));
}
