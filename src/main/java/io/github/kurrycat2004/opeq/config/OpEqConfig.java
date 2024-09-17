package io.github.kurrycat2004.opeq.config;

import io.github.kurrycat2004.Tags;
import io.github.kurrycat2004.opeq.util.OpEqEmcHelper;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

@Config(modid = Tags.MODID)
@Mod.EventBusSubscriber(modid = Tags.MODID)
public class OpEqConfig {
    @Config.LangKey("opeq.config.hide_emc")
    public static boolean hideEmc = false;

    @Config.LangKey("opeq.config.stabilized_tags")
    public static String[] stabilizedTags = new String[]{"akashictome:data", "omniwand:data"};

    public static void preInit() {
        OpEqEmcHelper.STABILIZED_TAGS.clear();
        OpEqEmcHelper.STABILIZED_TAGS.addAll(Arrays.asList(OpEqConfig.stabilizedTags));
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (!event.getModID().equals(Tags.MODID)) return;
        ConfigManager.sync(Tags.MODID, Config.Type.INSTANCE);
        OpEqEmcHelper.STABILIZED_TAGS.clear();
        OpEqEmcHelper.STABILIZED_TAGS.addAll(Arrays.asList(OpEqConfig.stabilizedTags));
    }
}
