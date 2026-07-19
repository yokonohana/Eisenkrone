package com.zweihander.eisenkrone.systems.skills;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.zweihander.eisenkrone.systems.core.Attribute;

import java.util.List;

public record SkillDefinition (
        String display_name,
        String desc,
        Attribute primary_attr,
        SkillType type,
        List<String> focuses
) {
    public final static Codec<SkillDefinition> CODEC = RecordCodecBuilder.create(
            skillDefinitionInstance -> skillDefinitionInstance.group(
                    Codec.STRING.fieldOf("display_name").forGetter(SkillDefinition::display_name),
                    Codec.STRING.fieldOf("desc").forGetter(SkillDefinition::desc),
                    Attribute.CODEC.fieldOf("primary_attr").forGetter(SkillDefinition::primary_attr),
                    SkillType.CODEC.fieldOf("type").forGetter(SkillDefinition::type),
                    Codec.STRING.listOf().optionalFieldOf("focuses", List.of()).forGetter(SkillDefinition::focuses)
            ).apply(skillDefinitionInstance, SkillDefinition::new));
}
