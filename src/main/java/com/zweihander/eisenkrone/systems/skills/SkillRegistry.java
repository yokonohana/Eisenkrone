package com.zweihander.eisenkrone.systems.skills;

import net.minecraft.resources.ResourceLocation;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;

public class SkillRegistry {
    // БД навыков. Место их храненния.
    private static final LinkedHashMap<ResourceLocation, SkillDefinition> store = new LinkedHashMap<>();

    private SkillRegistry() {}

    // Метод: поиск по ID.
    public static SkillDefinition getById(ResourceLocation id) {
        return store.get(id); // Потом делать проверки на null
    }

    // Метод: добавить навык в реестр.
    public static void registry(ResourceLocation id, SkillDefinition skill) {
        store.put(id, skill);
    }

    // Метод: получить список всех навыков.
    public static Collection<SkillDefinition> getAll() {
        return Collections.unmodifiableCollection(store.values());
    }

    // Метод: очистить store.
    public static void clear() {
        store.clear();
    }
}
