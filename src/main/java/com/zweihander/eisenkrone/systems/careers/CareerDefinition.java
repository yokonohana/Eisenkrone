package com.zweihander.eisenkrone.systems.careers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.zweihander.eisenkrone.systems.advances.AdvancesDefinition;

public record CareerDefinition (
        String display_name,
        CareerType type,
        String desc,
        String career_trait,
        String special_trait,
        String drawback,
        AdvancesDefinition advances
) {
    public final static Codec<CareerDefinition> CODEC = RecordCodecBuilder.create(careerDefinitionInstance ->
            careerDefinitionInstance.group(
                    Codec.STRING.fieldOf("display_name").forGetter(CareerDefinition::display_name),
                    CareerType.CODEC.fieldOf("type").forGetter(CareerDefinition::type),
                    Codec.STRING.fieldOf("desc").forGetter(CareerDefinition::desc),
                    Codec.STRING.fieldOf("career_trait").forGetter(CareerDefinition::career_trait),
                    Codec.STRING.optionalFieldOf("special_trait", "").forGetter(CareerDefinition::special_trait),
                    Codec.STRING.optionalFieldOf("drawback", "").forGetter(CareerDefinition::drawback),
                    AdvancesDefinition.CODEC.fieldOf("advances").forGetter(CareerDefinition::advances)
            ).apply(careerDefinitionInstance, CareerDefinition::new));
}
