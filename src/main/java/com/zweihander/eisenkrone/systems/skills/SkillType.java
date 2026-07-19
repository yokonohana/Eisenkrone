package com.zweihander.eisenkrone.systems.skills;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum SkillType implements StringRepresentable {
    COMMON("common"),
    SPECIAL("special");

    public static final Codec<SkillType> CODEC = StringRepresentable.fromEnum(SkillType::values);

    private final String serializedName;

    SkillType(String serializedName) {
        this.serializedName = serializedName;
    }

    @Override
    public @NotNull String getSerializedName() {
        return serializedName;
    }
}