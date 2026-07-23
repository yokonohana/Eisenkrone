package com.zweihander.eisenkrone.systems.ancestry;

import net.minecraft.resources.ResourceLocation;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;

public class AncestryRegistry {
    private final static LinkedHashMap<ResourceLocation, AncestryDefinition> store = new LinkedHashMap<>();

    private AncestryRegistry() {}

    public static void registry(ResourceLocation id, AncestryDefinition ancestry) {
        store.put(id, ancestry);
    }

    public static AncestryDefinition getById(ResourceLocation id) {
        return store.get(id);
    }

    public static Collection<AncestryDefinition> getAll() {
        return Collections.unmodifiableCollection(store.values());
    }

    public static void clear() {
        store.clear();
    }
}
