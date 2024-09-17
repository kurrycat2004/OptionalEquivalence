package io.github.kurrycat2004.opeq.config.settings;

import io.github.kurrycat2004.opeq.util.OpEqEmcHelper;
import net.minecraftforge.common.config.Config;

import java.util.Arrays;

public class ServerSettings {
    @Config.Ignore
    public static final ServerSettings INSTANCE = new ServerSettings();

    @Config.LangKey("opeq.config.server_settings.stabilized_tags")
    public String[] stabilizedTags = new String[]{"akashictome:data", "omniwand:data"};

    public static void updateStabilizedTagsSet() {
        OpEqEmcHelper.STABILIZED_TAGS.clear();
        OpEqEmcHelper.STABILIZED_TAGS.addAll(Arrays.asList(INSTANCE.stabilizedTags));
    }
}
