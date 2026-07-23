package com.zweihander.eisenkrone.systems.ancestry;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.zweihander.eisenkrone.Eisenkrone;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

public class AncestryJsonLoader extends SimpleJsonResourceReloadListener {

    public AncestryJsonLoader() {
        super(new Gson(), "ancestry");
    }

    @Override
    protected void apply(@NotNull Map<ResourceLocation, JsonElement> resourceLocationJsonElementMap,
                         @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
        AncestryRegistry.clear();

        for (Map.Entry<ResourceLocation, JsonElement> entry : resourceLocationJsonElementMap.entrySet()) {
            ResourceLocation id = entry.getKey();
            JsonElement value = entry.getValue();

            DataResult<AncestryDefinition> searchResult = AncestryDefinition.CODEC.parse(JsonOps.INSTANCE, value);
            Optional<AncestryDefinition> mbAncestry = searchResult.resultOrPartial(err -> System.err.println(
                    err + " from file: " + id
            ));

            mbAncestry.ifPresent(ancestry -> AncestryRegistry.registry(id, ancestry));
        }

        LogUtils.getLogger().info("Loaded {} ancestry", AncestryRegistry.getAll().size());
    }
}
