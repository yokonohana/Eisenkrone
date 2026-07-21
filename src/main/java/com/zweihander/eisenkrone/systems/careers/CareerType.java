package com.zweihander.eisenkrone.systems.careers;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum CareerType implements StringRepresentable {
    ACADEMIC("academic"),
    COMMONER("commoner"),
    KNAVE("knave"),
    RANGER("ranger"),
    SOCIALITE("socialite"),
    WARRIOR("warrior");

    public static final Codec<CareerType> CODEC = StringRepresentable.fromEnum(CareerType::values);

    private final String serializedName;

    CareerType(String serializedName) {
        this.serializedName = serializedName;
    }

    @Override
    public @NotNull String getSerializedName() {
        return serializedName;
    }
}
