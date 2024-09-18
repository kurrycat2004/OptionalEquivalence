package io.github.kurrycat2004.opeq.gameObj;

import io.github.kurrycat2004.Tags;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.NotNull;

import static io.github.kurrycat2004.opeq.gameObj.ObjectHolder.registerModel;

@Mod.EventBusSubscriber(modid = Tags.MODID, value = Side.CLIENT)
public class ClientEventHandler {
    @SubscribeEvent
    public static void registerModels(@NotNull ModelRegistryEvent evt) {
        registerModel(ObjectHolder.STABILIZATION_TAPE);
        registerModel(ObjectHolder.DESTABILIZATION_SCISSORS);
    }
}
