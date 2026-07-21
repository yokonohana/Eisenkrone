package com.zweihander.eisenkrone.systems.core;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record AttributeModifier (
        Attribute attribute,
        int amount
) {
    public final static Codec<AttributeModifier> CODEC = RecordCodecBuilder.create(attributeModifierInstance ->
            attributeModifierInstance.group(
                    Attribute.CODEC.fieldOf("attribute").forGetter(AttributeModifier::attribute),
                    Codec.INT.fieldOf("amount").forGetter(AttributeModifier::amount)
            ).apply(attributeModifierInstance, AttributeModifier::new));
}
