package com.zweihander.eisenkrone.systems.modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

public class AppliedModifier {
    private final ResourceLocation source;
    private int amount;
    private boolean disabled;

    public AppliedModifier(ResourceLocation source, int amount) {
        this.source = source;
        this.amount = amount;
        this.disabled = false;
    }

    public ResourceLocation getSource() {
        return source;
    }

    public int getAmount() {
        return amount;
    }

    public void increaseAmount(int addAmount) {
        amount += addAmount;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    private record AppliedModifierData(ResourceLocation source, int amount, boolean disabled) {
        public static final Codec<AppliedModifierData> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        ResourceLocation.CODEC.fieldOf("source").forGetter(AppliedModifierData::source),
                        Codec.INT.fieldOf("amount").forGetter(AppliedModifierData::amount),
                        Codec.BOOL.fieldOf("disabled").forGetter(AppliedModifierData::disabled)
                ).apply(instance, AppliedModifierData::new));
    }

    public static final Codec<AppliedModifier> CODEC = AppliedModifierData.CODEC.xmap(
            data -> {
                AppliedModifier modifier = new AppliedModifier(data.source(), data.amount());
                modifier.setDisabled(data.disabled());
                return modifier;
            },
            modifier -> new AppliedModifierData(modifier.getSource(), modifier.getAmount(), modifier.isDisabled())
    );
}
