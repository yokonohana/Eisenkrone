package com.zweihander.eisenkrone.systems.ancestry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.zweihander.eisenkrone.systems.core.AttributeModifier;

import java.util.List;

public record AncestryDefinition (
        String display_name,
        String desc,
        SizeType size,
        List<AttributeModifier> modifiers,
        List<String> traits
) {
    public final static Codec<AncestryDefinition> CODEC = RecordCodecBuilder.create(ancestryDefinitionInstance ->
            ancestryDefinitionInstance.group(
                    Codec.STRING.fieldOf("display_name").forGetter(AncestryDefinition::display_name),
                    Codec.STRING.fieldOf("desc").forGetter(AncestryDefinition::desc),
                    SizeType.CODEC.fieldOf("size").forGetter(AncestryDefinition::size),
                    AttributeModifier.CODEC.listOf().optionalFieldOf("modifiers", List.of()).forGetter(AncestryDefinition::modifiers),
                    Codec.STRING.listOf().optionalFieldOf("traits", List.of()).forGetter(AncestryDefinition::traits)
            ).apply(ancestryDefinitionInstance, AncestryDefinition::new));
}
