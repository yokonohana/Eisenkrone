package com.zweihander.eisenkrone.systems.core;

import com.zweihander.eisenkrone.Eisenkrone;
import com.zweihander.eisenkrone.systems.ancestry.AncestryJsonLoader;
import com.zweihander.eisenkrone.systems.careers.CareerJsonLoader;
import com.zweihander.eisenkrone.systems.data.ModAttachments;
import com.zweihander.eisenkrone.systems.skills.SkillJsonLoader;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = Eisenkrone.MODID)
public class CoreSystemsSetup {

    @SubscribeEvent
    static void onAddReloadListener(AddReloadListenerEvent event) {
        event.addListener(new SkillJsonLoader());
        event.addListener(new CareerJsonLoader());
        event.addListener(new AncestryJsonLoader());
    }

    @SubscribeEvent
    static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
//        AttributeContainer attributeContainer =
                event.getEntity().getData(ModAttachments.ATTRIBUTES.get());
//        AttributeInstance attributeBrawnInstance = attributeContainer.get(Attribute.BRAWN);
//
//        ResourceLocation source = ResourceLocation.fromNamespaceAndPath(Eisenkrone.MODID,"test");
//
//        int totalValue = attributeContainer.getTotal(Attribute.BRAWN);
//        LogUtils.getLogger().info("Player BRAWN total: {}", totalValue);
//
//        attributeBrawnInstance.addModifier(new AppliedModifier(source, 5));
//        attributeBrawnInstance.addModifier(new AppliedModifier(source, 5)); // тот же source
//        totalValue = attributeContainer.getTotal(Attribute.BRAWN);
//        LogUtils.getLogger().info("Player BRAWN total: {}", totalValue);
    }
}
