package com.zweihander.eisenkrone.systems.careers;

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

public class CareerJsonLoader extends SimpleJsonResourceReloadListener {

    public CareerJsonLoader() {
        super(new Gson(), "careers");
    }

    @Override
    protected void apply(@NotNull Map<ResourceLocation, JsonElement> resourceLocationJsonElementMap,
                         @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
        CareerRegistry.clear();

        for (Map.Entry<ResourceLocation, JsonElement> entry : resourceLocationJsonElementMap.entrySet()) {
            ResourceLocation id = entry.getKey();
            JsonElement value = entry.getValue();

            DataResult<CareerDefinition> searchResult = CareerDefinition.CODEC.parse(JsonOps.INSTANCE, value);
            Optional<CareerDefinition> mbCareer = searchResult.resultOrPartial(err -> System.err.println(
                    err + " from file: " + id
            ));

            mbCareer.ifPresent(career -> CareerRegistry.registry(id, career));
        }

        LogUtils.getLogger().info("Loaded {} careers", CareerRegistry.getAll().size());
    }
}
