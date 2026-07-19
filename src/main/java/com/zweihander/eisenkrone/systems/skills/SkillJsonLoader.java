package com.zweihander.eisenkrone.systems.skills;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class SkillJsonLoader extends SimpleJsonResourceReloadListener {

    public SkillJsonLoader() {
        super(new Gson(), "skills");
    }

    @Override
    protected void apply(@NotNull Map<ResourceLocation, JsonElement> resourceLocationJsonElementMap,
                         @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
        SkillRegistry.clear();

    }
}
