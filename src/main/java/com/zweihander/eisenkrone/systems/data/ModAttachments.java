package com.zweihander.eisenkrone.systems.data;

import com.zweihander.eisenkrone.Eisenkrone;
import com.zweihander.eisenkrone.systems.data.attributes.AttributeContainer;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Eisenkrone.MODID);

    public static final Supplier<AttachmentType<AttributeContainer>> ATTRIBUTES = ATTACHMENT_TYPES.register("attributes",
            () -> AttachmentType.builder(AttributeContainer::new).serialize(AttributeContainer.CODEC).build());
}
