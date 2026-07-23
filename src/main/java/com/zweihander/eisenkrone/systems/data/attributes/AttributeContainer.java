package com.zweihander.eisenkrone.systems.data.attributes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.zweihander.eisenkrone.systems.core.Attribute;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class AttributeContainer {
    private final EnumMap<Attribute, AttributeInstance> instances = new EnumMap<>(Attribute.class);

    // конструктор — инициализирует все 7 атрибутов с base = 0 (заглушка)
    public AttributeContainer() {
        for (Attribute attr : Attribute.values()) {
            instances.put(attr, new AttributeInstance(0));
        }
    }

    public void setAttributeInstance(Attribute attribute, AttributeInstance attributeInstance) {
        instances.put(attribute, attributeInstance);
    }

    // get(Attribute attribute) — возвращает AttributeInstance
    public AttributeInstance get(Attribute attribute) {
        return instances.get(attribute);
    }

    // getTotal(Attribute attribute) — шорткат-обёртка над get(...).getTotal()
    public int getTotal(Attribute attribute) {
        return get(attribute).getTotal();
    }

    private record MiddlewareAttributeContainer(Attribute attribute, AttributeInstance attributeInstance) {
        public static final Codec<MiddlewareAttributeContainer> CODEC = RecordCodecBuilder.create(
                middlewareAttributeContainerInstance -> middlewareAttributeContainerInstance.group(
                        Attribute.CODEC.fieldOf("attribute").forGetter(MiddlewareAttributeContainer::attribute),
                        AttributeInstance.CODEC.fieldOf("attributeInstance").forGetter(MiddlewareAttributeContainer::attributeInstance)
                ).apply(middlewareAttributeContainerInstance, MiddlewareAttributeContainer::new)
        );
    }

    private record AttributeContainerData(List<MiddlewareAttributeContainer> middlewareAttributeContainerList) {
        public static final Codec<AttributeContainerData> CODEC = RecordCodecBuilder.create(
                attributeContainerDataInstance -> attributeContainerDataInstance.group(
                        MiddlewareAttributeContainer.CODEC.listOf().fieldOf("middlewareAttributeContainerList")
                                .forGetter(AttributeContainerData::middlewareAttributeContainerList)
                ).apply(attributeContainerDataInstance, AttributeContainerData::new)
        );
    }

    public static final Codec<AttributeContainer> CODEC = AttributeContainerData.CODEC.xmap(
            data -> {
                AttributeContainer attributeContainer = new AttributeContainer();
                List<MiddlewareAttributeContainer> middlewareAttributeContainer = data.middlewareAttributeContainerList();

                for (MiddlewareAttributeContainer entry : middlewareAttributeContainer) {
                    Attribute attribute = entry.attribute();
                    AttributeInstance attributeInstance = entry.attributeInstance();
                    attributeContainer.setAttributeInstance(attribute, attributeInstance);
                }

                return attributeContainer;
            },
            attributeContainer -> {
                List<MiddlewareAttributeContainer> list = new ArrayList<>();

                for (Attribute key: Attribute.values()) {
                    AttributeInstance value = attributeContainer.instances.get(key);
                    MiddlewareAttributeContainer entry = new MiddlewareAttributeContainer(key, value);
                    list.add(entry);
                }

                return new AttributeContainerData(list);
            }
    );
}
