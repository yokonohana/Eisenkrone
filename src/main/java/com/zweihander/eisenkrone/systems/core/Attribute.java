package com.zweihander.eisenkrone.systems.core;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum Attribute implements StringRepresentable {
    COMBAT("combat"),
    BRAWN("brawn"),
    AGILITY("agility"),
    PERCEPTION("perception"),
    INTELLIGENCE("intelligence"),
    WILLPOWER("willpower"),
    FELLOWSHIP("fellowship");

    public static final Codec<Attribute> CODEC = StringRepresentable.fromEnum(Attribute::values);

    private final String serializedName;

    Attribute(String serializedName) {
        this.serializedName = serializedName;
    }

    @Override
    public @NotNull String getSerializedName() {
        return serializedName;
    }
}
