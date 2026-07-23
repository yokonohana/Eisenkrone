package com.zweihander.eisenkrone.systems.modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttributeInstance {
    private final int base;
    private final Map<ResourceLocation, AppliedModifier> modifiers = new HashMap<>();

    public AttributeInstance(int base) {
        this.base = base;
    }

    public int getBase() {
        return base;
    }

    public List<AppliedModifier> getModifiers() {
        return List.copyOf(modifiers.values());
    }

    public void addModifier(AppliedModifier modifier) {
        AppliedModifier existing = modifiers.get(modifier.getSource());
        if (existing != null) {
            existing.increaseAmount(modifier.getAmount());
        } else {
            modifiers.put(modifier.getSource(), modifier);
        }
    }

    public void removeModifier(ResourceLocation source) {
        modifiers.remove(source);
    }

    public int getTotal() {
        int notDisabledModifiers = 0;

        for (Map.Entry<ResourceLocation, AppliedModifier> entry: modifiers.entrySet()) {
            AppliedModifier modifier = entry.getValue();

            if (!modifier.isDisabled()) {
                notDisabledModifiers += modifier.getAmount();
            }
        }

        return base + notDisabledModifiers;
    }

    private record AttributeInstanceData(int base, List<AppliedModifier> modifiers) {
        public static final Codec<AttributeInstanceData> CODEC = RecordCodecBuilder.create(
                attributeInstanceDataInstance -> attributeInstanceDataInstance.group(
                        Codec.INT.fieldOf("base").forGetter(AttributeInstanceData::base),
                        AppliedModifier.CODEC.listOf().fieldOf("modifiers").forGetter(AttributeInstanceData::modifiers)
                ).apply(attributeInstanceDataInstance, AttributeInstanceData::new)
        );
    }

    public static final Codec<AttributeInstance> CODEC = AttributeInstanceData.CODEC.xmap(
            data -> {
                AttributeInstance attributeInstance = new AttributeInstance(data.base());
                for (AppliedModifier entry : data.modifiers()) {
                    attributeInstance.addModifier(entry);
                }
                return attributeInstance;
            },
            attributeInstance -> new AttributeInstanceData(
                    attributeInstance.getBase(), attributeInstance.getModifiers()
            )
    );
}
