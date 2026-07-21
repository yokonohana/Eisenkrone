package com.zweihander.eisenkrone.systems.advances;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum SkillBonus implements StringRepresentable {
    COMBAT_BONUS("CB"),
    BRAWN_BONUS("BB"),
    AGILITY_BONUS("AB"),
    PERCEPTION_BONUS("PB"),
    INTELLIGENCE_BONUS("IB"),
    FELLOWSHIP_BONUS("FB"),
    WILLPOWER_BONUS("WB");

    public static final Codec<SkillBonus> CODEC = StringRepresentable.fromEnum(SkillBonus::values);

    private final String serializedName;

    SkillBonus(String serializedName) {
        this.serializedName = serializedName;
    }

    @Override
    public @NotNull String getSerializedName() {
        return serializedName;
    }
}
