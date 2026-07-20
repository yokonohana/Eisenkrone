package com.zweihander.eisenkrone.systems.skills;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

import static com.zweihander.eisenkrone.systems.skills.SkillDefinition.CODEC;

public class SkillJsonLoader extends SimpleJsonResourceReloadListener {

    public SkillJsonLoader() {
        super(new Gson(), "skills");
    }

    @Override
    protected void apply(@NotNull Map<ResourceLocation, JsonElement> resourceLocationJsonElementMap,
                         @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
        SkillRegistry.clear();

        for (Map.Entry<ResourceLocation, JsonElement> entry : resourceLocationJsonElementMap.entrySet()) {
            ResourceLocation id = entry.getKey();
            JsonElement rawJson = entry.getValue();

            DataResult<SkillDefinition> searchResult = CODEC.parse(JsonOps.INSTANCE, rawJson);
            Optional<SkillDefinition> mbSkill = searchResult.resultOrPartial(err -> System.err.println(
                    err + " from file: " + id
            ));

            mbSkill.ifPresent(skill -> SkillRegistry.registry(id, skill));
        }

        LogUtils.getLogger().info("Loaded {} skills", SkillRegistry.getAll().size());
    }
}
