package com.zweihander.eisenkrone.systems.advances;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record AdvancesDefinition (
        List<String> skills,
        List<SkillBonus> bonus,
        List<String> talents
) {
    public final static Codec<AdvancesDefinition> CODEC = RecordCodecBuilder.create(
            advancesDefinitionInstance -> advancesDefinitionInstance.group(
                    Codec.STRING.listOf().optionalFieldOf("skills", List.of()).forGetter(AdvancesDefinition::skills),
                    SkillBonus.CODEC.listOf().optionalFieldOf("bonus", List.of()).forGetter(AdvancesDefinition::bonus),
                    Codec.STRING.listOf().optionalFieldOf("talents", List.of()).forGetter(AdvancesDefinition::talents)
            ).apply(advancesDefinitionInstance, AdvancesDefinition::new));
}
