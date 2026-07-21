package com.zweihander.eisenkrone.systems.careers;

import net.minecraft.resources.ResourceLocation;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;

public class CareerRegistry {
    private final static LinkedHashMap<ResourceLocation, CareerDefinition> store = new LinkedHashMap<>();

    private CareerRegistry() {}

    // Методы: очистка, получение по id, получение всех карьер, регистрация
    public static void registry(ResourceLocation id, CareerDefinition career) {
        store.put(id, career);
    }

    public static CareerDefinition getById(ResourceLocation id) {
        return store.get(id);
    }

    public static Collection<CareerDefinition> getAll() {
        return Collections.unmodifiableCollection(store.values());
    }

    public static void clear() {
        store.clear();
    }
}
