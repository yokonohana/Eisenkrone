package com.zweihander.eisenkrone.systems.ancestry;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum SizeType implements StringRepresentable {
    SMALL("small"),
    NORMAL("normal"),
    LARGE("large"),
    HUGE("huge");

    public static final Codec<SizeType> CODEC = StringRepresentable.fromEnum(SizeType::values);

    private final String serializedName;

    SizeType(String serializedName) {
        this.serializedName = serializedName;
    }

    @Override
    public @NotNull String getSerializedName() {
        return serializedName;
    }
}
