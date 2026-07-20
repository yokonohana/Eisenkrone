package com.zweihander.eisenkrone.systems.core;

import com.zweihander.eisenkrone.Eisenkrone;
import com.zweihander.eisenkrone.systems.skills.SkillJsonLoader;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

@EventBusSubscriber(modid = Eisenkrone.MODID)
public class CoreSystemsSetup {

    @SubscribeEvent
    static void onAddReloadListener(AddReloadListenerEvent event) {
            event.addListener(new SkillJsonLoader());
    }
}
